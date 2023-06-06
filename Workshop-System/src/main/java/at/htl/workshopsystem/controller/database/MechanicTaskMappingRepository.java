package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.MechanicTaskMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MechanicTaskMappingRepository {
    private Database database = new Database();

    public MechanicTaskMapping insert(MechanicTaskMapping mechanicTaskMapping) {
        try(Connection connection = database.getConnection()){
            String sql = "INSERT INTO MECHANICTASKMAPPING (MECHANIC_ID, TASK_ID) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID"});
            statement.setLong(1, mechanicTaskMapping.getMechanic().getId());
            statement.setLong(2, mechanicTaskMapping.getTask().getId());

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Creating mechanicTaskMapping failed, no rows affected.");
            }

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    mechanicTaskMapping.setId(generatedKeys.getLong(1));
                }else{
                    throw new SQLException("Creating mechanicTaskMapping failed, no ID obtained.");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return mechanicTaskMapping;
    }

    public void update(MechanicTaskMapping mechanicTaskMapping){
        try(Connection connection = database.getConnection()){
            String sql = "UPDATE MECHANICTASKMAPPING SET MECHANIC_ID=?, TASK_ID=? WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, mechanicTaskMapping.getMechanic().getId());
            statement.setLong(2, mechanicTaskMapping.getTask().getId());
            statement.setLong(3, mechanicTaskMapping.getId());

            if(statement.executeUpdate() == 0){
                throw new SQLException("Update of mechanicTaskMapping failed, no rows affected");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(long id){
        try(Connection connection = database.getConnection()){
            String sql = "DELETE FROM MECHANICTASKMAPPING WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if(statement.executeUpdate() == 0){
                throw new SQLException("Delete of mechanicTaskMapping failed, no rows affected");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<MechanicTaskMapping> getAll(){
        List<MechanicTaskMapping> mechanicTaskMappings = new ArrayList<>();
        try(Connection connection = database.getConnection()){
            String sql = "SELECT * FROM MECHANICTASKMAPPING";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()){
                mechanicTaskMappings.add(new MechanicTaskMapping(
                        result.getLong("ID"),
                        new MechanicRepository().getById(result.getLong("MECHANIC_ID")),
                        new TaskRepository().getById(result.getLong("TASK_ID"))
                ));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return mechanicTaskMappings;
    }

    public MechanicTaskMapping getById(long id){
        MechanicTaskMapping mechanicTaskMapping = null;
        try(Connection connection = database.getConnection()){
            String sql = "SELECT * FROM MECHANICTASKMAPPING WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            while(result.next()){
                if(id == result.getLong(1)){
                    System.out.println(result.getLong(1));
                    return new MechanicTaskMapping(
                            result.getLong("ID"),
                            new MechanicRepository().getById(result.getLong("MECHANIC_ID")),
                            new TaskRepository().getById(result.getLong("TASK_ID"))
                    );
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
