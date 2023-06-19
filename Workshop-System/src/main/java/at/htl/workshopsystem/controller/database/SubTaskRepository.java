package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.SubTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubTaskRepository {
    private Database database = new Database();

    public void insert(SubTask subTask, Long taskId) {
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO SUBTASK (DESCRIPTION, DURATION, IS_DONE, TASK_ID) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID"});
            statement.setString(1, subTask.getDescription());
            statement.setDouble(2, subTask.getDuration());
            statement.setInt(3, subTask.getIsDone() ? 1 : 0);
            statement.setLong(4, taskId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating subTask failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subTask.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating subTask failed, no ID obtained.");
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<SubTask> getByTaskId(Long id) {
        List<SubTask> subTasks = new ArrayList<>();

        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM SUBTASK WHERE TASK_ID=" + id;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Long subTaskId = result.getLong("ID");
                String description = result.getString("DESCRIPTION");
                double duration = result.getDouble("DURATION");
                boolean isDone = result.getInt("IS_DONE") == 0 ? false : true;
                SubTask subTask = new SubTask(subTaskId, description, duration, isDone);
                subTasks.add(subTask);
            }
            return subTasks;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(SubTask task){
        try(Connection connection = database.getConnection()){
            String sql = "UPDATE SUBTASK SET IS_DONE=?, DURATION=? WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIsDone() ? 1 : 0);
            //round up to full hours
            statement.setDouble(2, task.getDuration());
            statement.setLong(3, task.getId());
            if(statement.executeUpdate() == 0){
                throw new Exception("Update of subtask failed, no rows affected");
            }else {
                System.out.println("Update of subtask successful");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<SubTask> getAll() {
        List<SubTask> subTasks = new ArrayList<>();

        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM SUBTASK";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Long subTaskId = result.getLong("ID");
                String description = result.getString("DESCRIPTION");
                double duration = result.getDouble("DURATION");
                boolean isDone = result.getInt("IS_DONE") == 0 ? false : true;
                SubTask subTask = new SubTask(subTaskId, description, duration, isDone);
                subTasks.add(subTask);
            }
            return subTasks;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
