package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.SubTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubTaskRepository {
    private Database database = new Database();

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
                SubTask subTask = new SubTask(subTaskId, description, duration);
                subTasks.add(subTask);
            }
            return subTasks;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateIsDone(SubTask task){
        try(Connection connection = database.getConnection()){
            String sql = "UPDATE SUBTASK SET IS_DONE=? WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIsDone() ? 1 : 0);
            statement.setLong(2, task.getId());
            if(statement.executeUpdate() == 0){
                throw new Exception("Update of subtask failed, no rows affected");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
