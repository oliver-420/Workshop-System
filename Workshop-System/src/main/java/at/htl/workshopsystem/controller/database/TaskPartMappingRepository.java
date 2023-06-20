package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.Part;
import at.htl.workshopsystem.model.Task;
import at.htl.workshopsystem.model.TaskPartMapping;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskPartMappingRepository {
    private Database database = new Database();

    public Task insert(Task task, Part part, int quantity, Double price) {
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO TASKPARTMAPPING (TASK_ID, Part_ID, Quantity, price) VALUES (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID"});
            statement.setLong(1, task.getId());
            statement.setString(2, part.getSerialNumber());
            statement.setInt(3, quantity);
            statement.setDouble(4, price);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating TASKPARTMAPPING failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    task.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating TASKPARTMAPPING failed, no ID obtained.");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public void update(Task task, Part part, int quantity, Double price) {
        try (Connection connection = database.getConnection()) {
            String sql = "UPDATE TASKPARTMAPPING SET NAME=?, " +
                    "PART_ID =?, " +
                    "TASK_ID=?, " +
                    "QUANTITY=?, " +
                    "price=? " +
                    "WHERE ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, part.getSerialNumber());
            statement.setLong(2, task.getId());
            statement.setInt(3, quantity);
            statement.setDouble(4, price);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of TASKPARTMAPPING failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (Connection connection = database.getConnection()) {
            String sql = "DELETE FROM TASKPARTMAPPING WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from TASKPARTMAPPING failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TaskPartMapping> getAll() {
        List<TaskPartMapping> taskPartMappings = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM TASKPARTMAPPING";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            TaskRepository taskRepository = new TaskRepository();
            PartRepository partRepository = new PartRepository();
            while (result.next()) {
                taskPartMappings.add(
                        new TaskPartMapping(
                                result.getLong(1),
                                taskRepository.getById(result.getLong(2)),
                                partRepository.getById(result.getString(3)),
                                result.getInt(4),
                                result.getDouble(5)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskPartMappings;
    }

    public TaskPartMapping getById(long id) {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM TaskPartMapping WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            TaskRepository taskRepository = new TaskRepository();
            PartRepository partRepository = new PartRepository();
            while (result.next()) {
                if (id == result.getLong(1)) {
                    {
                        return new TaskPartMapping(
                                result.getLong(1),
                                taskRepository.getById(result.getLong(2)),
                                partRepository.getById(result.getString(3)),
                                result.getInt(4),
                                result.getDouble(5)
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TaskPartMapping> getByTaskId(Long id) {
        List<TaskPartMapping> taskPartMappings = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM TaskPartMapping WHERE Task_ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            TaskRepository taskRepository = new TaskRepository();
            PartRepository partRepository = new PartRepository();
            while (result.next()) {
                taskPartMappings.add(
                        new TaskPartMapping(
                                result.getLong(1),
                                taskRepository.getById(result.getLong(2)),
                                partRepository.getById(result.getString(3)),
                                result.getInt(4),
                                result.getDouble(5)
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskPartMappings;
    }

    public List<Part> getPartsByTaskId(Long taskId) {
        List<Part> parts = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM Part WHERE SERIAL_NUMBER in (select PART_ID from TaskpartMapping where TASK_ID =?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, taskId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                parts.add(
                        new Part(
                                result.getString(1),
                                result.getString(2),
                                result.getString(3),
                                result.getDouble(4),
                                result.getDouble(5),
                                result.getInt(6)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parts;
    }

    public void deleteByTaskIdAndPartId(Long taskId, String partId) {
        try (Connection connection = database.getConnection()) {
            String sql = "DELETE FROM TASKPARTMAPPING WHERE TASK_ID=? AND PART_ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, taskId);
            statement.setString(2, partId);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from TASKPARTMAPPING failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}