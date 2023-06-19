package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartRepository {
    Database database = new Database();

    public Part insert(Part part) {
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO PART (" +
                    "SERIAL_NUMBER," +
                    "NAME, " +
                    "MANUFACTURER, " +
                    "PRICE, " +
                    "ADDITIONAL_CHARGE, " +
                    "QUANTITY) VALUES (?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, part.getSerialNumber());
            statement.setString(2, part.getName());
            statement.setString(3, part.getManufacturer());
            statement.setDouble(4, part.getPrice());
            statement.setDouble(5, part.getAdditionalCharge());
            statement.setFloat(6, part.getQuantity());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating part failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return part;
    }

    public void update(Part part) {
        try (Connection connection = database.getConnection()) {
            String sql = "UPDATE PART " +
                    "SET Name=?, " +
                    "MANUFACTURER=?, " +
                    "PRICE=?, " +
                    "ADDITIONAL_CHARGE=?, " +
                    "QUANTITY=? where SERIAL_NUMBER=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, part.getName());
            statement.setString(2, part.getManufacturer());
            statement.setDouble(3, part.getPrice());
            statement.setDouble(4, part.getAdditionalCharge());
            statement.setFloat(5, part.getQuantity());
            statement.setString(6, part.getSerialNumber());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of part failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String serialNumber) {
        try (Connection connection = database.getConnection()) {
            String sql = "DELETE FROM PART WHERE SERIAL_NUMBER=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, serialNumber);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from part failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Part> getAll() {
        List<Part> partList = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM PART";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String serialNumber = result.getString(1);
                String name = result.getString(2);
                String manufacturer = result.getString(3);
                double price = result.getDouble(4);
                double additionalCharge = result.getDouble(5);
                int quantity = result.getInt(6);
                Part newPart = new Part(serialNumber, name, manufacturer, price, additionalCharge, quantity);
                partList.add(newPart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partList;
    }

    public Part getById(String serialNumber) {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM PART WHERE SERIAL_NUMBER=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, serialNumber);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (serialNumber == result.getString(1)) {
                    System.out.println(result.getString(1));
                    return new Part(serialNumber,
                            result.getString(2),
                            result.getString(3),
                            result.getDouble(4),
                            result.getDouble(5),
                            result.getInt(6));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}