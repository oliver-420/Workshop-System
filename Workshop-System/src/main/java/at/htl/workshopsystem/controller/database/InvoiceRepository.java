package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.Invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class InvoiceRepository {
    private Database database = new Database();

    public Invoice insert(Invoice invoice) {
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO INVOICE (ISSUE_DATE, AMOUNT, IS_PAID, TASK_ID, CUSTOMER_ID)" +
                    " VALUES (?,?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID"});
            statement.setString(1, invoice.getDate().toString());
            statement.setDouble(2, invoice.getPrice());
            statement.setBoolean(3, invoice.getPaid());
            statement.setLong(4, invoice.getTask().getId());
            statement.setLong(5, invoice.getCustomer().getId());

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
            String sql = "UPDATE INVOICE SET ISSUE_DATE=?, AMOUNT=?, IS_PAID=?, TASK_ID=?, CUSTOMER_ID=? WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, invoice.getDate().toString());
            statement.setDouble(2, invoice.getPrice());
            statement.setBoolean(3, invoice.getPaid());
            statement.setLong(4, invoice.getTask().getId());
            statement.setLong(5, invoice.getCustomer().getId());

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
                        result.getTimestamp("ISSUE_DATE").toLocalDateTime(),
                        result.getDouble("AMOUNT"),
                        new TaskRepository().getById(result.getLong("TASK_ID")),
                        result.getBoolean("IS_PAID"),
                        new CustomerRepository().getById(result.getLong("CUSTOMER_ID"))
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
                        result.getTimestamp("ISSUE_DATE").toLocalDateTime(),
                        result.getDouble("AMOUNT"),
                        new TaskRepository().getById(result.getLong("TASK_ID")),
                        result.getBoolean("IS_PAID"),
                        new CustomerRepository().getById(result.getLong("CUSTOMER_ID"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }
}
