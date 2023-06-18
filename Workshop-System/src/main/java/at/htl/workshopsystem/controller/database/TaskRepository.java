package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.Task;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private Database database = new Database();

    public Task insert(Task task) {
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO TASK (NAME, " +
                    "START_DATE, " +
                    "MECHANIC_ID, " +
                    "CAR_ID, " +
                    "CUSTOMER_ID) VALUES (?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID"});
            statement.setString(1, task.getName());
            statement.setTimestamp(2, Timestamp.valueOf(task.getStartDate()));
            statement.setLong(3, task.getFkMechanic());
            statement.setLong(4, task.getFkCar());
            statement.setLong(5, task.getFkCustomer());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating TASK failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    task.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating TASK failed, no ID obtained.");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public void update(Task task) {
        try (Connection connection = database.getConnection()) {
            String sql = "UPDATE TASK SET NAME=?, " +
                    "START_DATE=?, " +
                    "MECHANIC_ID=?, " +
                    "CAR_ID=?, " +
                    "CUSTOMER_ID=? " +
                    "WHERE ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, task.getName());
            statement.setTimestamp(2, Timestamp.valueOf(task.getStartDate()));
            statement.setLong(3, task.getFkMechanic());
            statement.setLong(4, task.getFkCar());
            statement.setLong(5, task.getFkCustomer());
            statement.setLong(6, task.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of task failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (Connection connection = database.getConnection()) {
            String sql = "DELETE FROM TASK WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from TASK failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAll() {
        List<Task> taskList = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM Task";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                LocalDateTime startDate = result.getTimestamp(3).toLocalDateTime();
                Long fk_mechanic = result.getLong(4);
                Long fk_car = result.getLong(5);
                Long fk_customer = result.getLong(6);
                Task newTask = new Task(id, name, startDate, fk_mechanic, fk_car, fk_customer);
                taskList.add(newTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public Task getById(long id) {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM TASK WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (id == result.getLong(1)) {
                    {
                        String name = result.getString(2);
                        LocalDateTime startDate = result.getTimestamp(3).toLocalDateTime();
                        Long fk_mechanic = result.getLong(4);
                        Long fk_car = result.getLong(5);
                        Long fk_customer = result.getLong(6);
                        return new Task(id, name, startDate, fk_mechanic, fk_car, fk_customer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

