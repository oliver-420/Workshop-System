package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.Car;
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
                    "DESCRIPTION, " +
                    "START_DATE, " +
                    "DURATION " +
                    ") VALUES (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID"});
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(task.getStartDate()));
            statement.setDouble(4, task.getDuration());

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
                    "DESCRIPTIO =?, " +
                    "START_DATE=?, " +
                    "DURATIOM=? " +
                    "WHERE ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(task.getStartDate()));
            statement.setDouble(4, task.getDuration());
            statement.setLong(5, task.getId());

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
                String description = result.getString(3);
                LocalDateTime startDate = result.getTimestamp(4).toLocalDateTime();
                double duration = result.getDouble(5);
                Task newTask = new Task(id, name, description, startDate, duration);
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
                        String description = result.getString(3);
                        LocalDateTime startDate = result.getTimestamp(4).toLocalDateTime();
                        double duration = result.getDouble(5);
                        return new Task(id, name, description, startDate, duration);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

