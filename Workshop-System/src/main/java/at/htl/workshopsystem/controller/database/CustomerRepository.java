package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.Customer;
import at.htl.workshopsystem.model.CustomerCard;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerRepository {
    private Database database = new Database();

    public Customer insert(Customer customer) {
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO CUSTOMER (NAME, PHONE_NUMBER, EMAIL) VALUES (?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID"});
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPhoneNumber());
            statement.setString(3, customer.getEmail());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating customer failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating car failed, no ID obtained.");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void update(Customer customer) {
        try (Connection connection = database.getConnection()) {
            String sql = "UPDATE CUSTOMER SET NAME=?, " +
                    "PHONE_NUMBER=?, " +
                    "EMAIL=? " +
                    "WHERE ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPhoneNumber());
            statement.setString(3, customer.getEmail());
            statement.setLong(4, customer.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of customer failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (Connection connection = database.getConnection()) {
            String sql = "DELETE FROM CUSTOMER WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from customer failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT c.*, cc.* FROM CUSTOMER  c, CUSTOMERCARD  cc WHERE c.CUSTOMER_CARD_ID = cc.ID or c.CUSTOMER_CARD_ID is null";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                String email = result.getString(3);
                String phoneNumber = result.getString(4);

                CustomerCard newCard = null;
                Long customerCardId = result.getLong(5);
                if(customerCardId != 0){
                    double discount = result.getDouble(6);
                    java.sql.Date joined_at = result.getDate("joined_at");
                    newCard = new CustomerCard(customerCardId, joined_at , discount);
                }


                customerList.add(new Customer(id, name, phoneNumber,email, newCard));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public Customer getById(long id) {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM CUSTOMER WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (id == result.getLong(1)) {
                    System.out.println(result.getLong(1));
                    return new Customer(
                            result.getLong(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(5),
                            new CustomerCardRepository().getById(result.getLong(6)
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
