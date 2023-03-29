package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.CustomerCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerCardRepository {
    private Database database = new Database();

    public CustomerCard insert(CustomerCard customerCard) {
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO CUSTOMERCARD (DISCOUNT, JOINED_AT) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID"});
            statement.setDouble(1, customerCard.getDiscount());
            statement.setDate(2, customerCard.getJoinedAt());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating customerCard failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customerCard.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating customerCard failed, no ID obtained.");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerCard;
    }

    public void update(CustomerCard customerCard) {
        try (Connection connection = database.getConnection()) {
            String sql = "UPDATE CUSTOMERCARD SET DISCOUNT=?, JOINED_AT=? WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, customerCard.getDiscount());
            statement.setDate(2, customerCard.getJoinedAt());
            statement.setLong(3, customerCard.getId());
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of customerCard failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (Connection connection = database.getConnection()) {
            String sql = "DELETE FROM CUSTOMERCARD WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from customerCard failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CustomerCard> getAll() {
        List<CustomerCard> customerCardList = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM CUSTOMERCARD";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                long id = result.getLong(1);
                double discount = result.getDouble(2);
                Date joined_at = result.getDate(3);
                CustomerCard newCard = new CustomerCard(id, joined_at, discount);
                customerCardList.add(newCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerCardList;
    }

    public CustomerCard getById(long id) {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM CUSTOMERCARD WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (id == result.getLong(1)) {
                    System.out.println(result.getLong(1));
                    return new CustomerCard(
                            result.getLong(1),
                            result.getDate(3),
                            result.getDouble(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
