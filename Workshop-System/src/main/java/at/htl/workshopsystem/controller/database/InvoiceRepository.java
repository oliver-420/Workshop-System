package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.Invoice;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class InvoiceRepository {
    private Database database = new Database();

    public Invoice insert(Invoice invoice) {
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO INVOICE (MECHANIC, CUSTOMER, CAR, TOTAL_DURATION, TOTAL_PRICE, ISSUE_DATE)" +
                    " VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID"});
            statement.setString(1, invoice.getMechanic());
            statement.setString(2, invoice.getCustomer());
            statement.setString(3, invoice.getCar());
            statement.setString(4, invoice.getTotalDuration());
            statement.setString(5, invoice.getTotalCost());
            statement.setString(6, invoice.getDate());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating invoice failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    invoice.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating invoice failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoice;
    }

    public void update(Invoice invoice) {
        try (Connection connection = database.getConnection()) {
            String sql = "UPDATE INVOICE SET MECHANIC=?, CUSTOMER=?, CAR=?, TOTAL_DURATION=?, TOTAL_PRICE=?, ISSUE_DATE=? WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, invoice.getMechanic());
            statement.setString(2, invoice.getCustomer());
            statement.setString(3, invoice.getCar());
            statement.setString(4, invoice.getTotalDuration());
            statement.setString(5, invoice.getTotalCost());
            statement.setString(6, invoice.getDate());
            statement.setLong(7, invoice.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of invoice failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Invoice invoice) {
        try (Connection connection = database.getConnection()) {
            String sql = "DELETE FROM INVOICE WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, invoice.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete of invoice failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Invoice> getAll(){
        List<Invoice> invoices = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM INVOICE";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                invoices.add(new Invoice(
                        result.getLong("ID"),
                        result.getString("ISSUE_DATE"),
                        result.getString("MECHANIC"),
                        result.getString("CUSTOMER"),
                        result.getString("CAR"),
                        result.getString("TOTAL_DURATION"),
                        result.getString("TOTAL_PRICE")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public Invoice getById(long id) {
        Invoice invoice = null;
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM INVOICE WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                invoice = new Invoice(
                        result.getLong("ID"),
                        result.getString("ISSUE_DATE"),
                        result.getString("MECHANIC"),
                        result.getString("CUSTOMER"),
                        result.getString("CAR"),
                        result.getString("TOTAL_DURATION"),
                        result.getString("TOTAL_PRICE")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }
}
