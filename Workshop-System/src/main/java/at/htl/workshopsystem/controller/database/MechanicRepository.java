package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.Mechanic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MechanicRepository {
    private Database database = new Database();

    public Mechanic insert(Mechanic mechanic) {
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO MECHANIC (NAME, HOURLY_WAGE) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID"});
            statement.setString(1, mechanic.getName());
            statement.setFloat(2, mechanic.getHourlyWage().floatValue());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating mechanic failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    mechanic.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating mechanic failed, no ID obtained.");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mechanic;
    }

    public void update(Mechanic mechanic) {
        try (Connection connection = database.getConnection()) {
            String sql = "UPDATE MECHANIC SET NAME=?, HOURLY_WAGE=? WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, mechanic.getName());
            statement.setFloat(2, mechanic.getHourlyWage().floatValue());
            statement.setLong(3, mechanic.getId());
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of mechanic failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (Connection connection = database.getConnection()) {
            String sql = "DELETE FROM MECHANIC WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from mechanic failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mechanic> getAll() {
        List<Mechanic> mechanicList = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM MECHANIC";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                double hourlyWage = result.getFloat(3);
                Mechanic newMechanic = new Mechanic(id, name, hourlyWage);
                mechanicList.add(newMechanic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mechanicList;
    }

    public Mechanic getById(long id) {
        Mechanic mechanic = null;
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM MECHANIC WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (id == result.getLong(1)) {
                    System.out.println(result.getLong(1));
                    return new Mechanic(
                            result.getLong(1),
                            result.getString(2),
                            (double) result.getFloat(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

