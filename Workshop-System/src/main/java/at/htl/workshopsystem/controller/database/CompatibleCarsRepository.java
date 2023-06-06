package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.CompatibleCars;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompatibleCarsRepository {
    private Database database = new Database();

    public CompatibleCars insert(CompatibleCars compatibleCars){
        try(Connection connection = database.getConnection()) {
            String sql = "INSERT INTO COMPATIBLECARS (CAR_ID, PART_ID) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID"});
            statement.setLong(1, compatibleCars.getCar().getId());
            statement.setString(2, compatibleCars.getPart().getSerialNumber());

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Creating compatibleCars failed, no rows affected.");
            }

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    compatibleCars.setId(generatedKeys.getLong(1));
                }else{
                    throw new SQLException("Creating compatibleCars failed, no ID obtained.");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return compatibleCars;
    }

    public void update(CompatibleCars compatibleCars){
        try(Connection connection = database.getConnection()) {
            String sql = "UPDATE COMPATIBLECARS SET CAR_ID=?, PART_ID=? WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, compatibleCars.getCar().getId());
            statement.setString(2, compatibleCars.getPart().getSerialNumber());
            statement.setLong(3, compatibleCars.getId());

            if(statement.executeUpdate() == 0){
                throw new SQLException("Update of compatibleCars failed, no rows affected");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(long id){
        try(Connection connection = database.getConnection()) {
            String sql = "DELETE FROM COMPATIBLECARS WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if(statement.executeUpdate() == 0){
                throw new SQLException("Delete of compatibleCars failed, no rows affected");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<CompatibleCars> getAll(){
        List<CompatibleCars> compatibleCarsList = new ArrayList<>();
        try(Connection connection = database.getConnection()){
            String sql = "SELECT * FROM COMPATIBLECARS";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()){
                compatibleCarsList.add(new CompatibleCars(
                        result.getLong("ID"),
                        new CarRepository().getById(result.getLong("CAR_ID")),
                        new PartRepository().getById(result.getString("PART_ID"))
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return compatibleCarsList;
    }
    public CompatibleCars getById(long id){
        CompatibleCars compatibleCars = null;

        try(Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM COMPATIBLECARS WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();

            while(result.next()){
                if(id == result.getLong(1)){
                    System.out.println(result.getLong(1));
                    return new CompatibleCars(
                            result.getLong("ID"),
                            new CarRepository().getById(result.getLong("CAR_ID")),
                            new PartRepository().getById(result.getString("PART_ID"))
                    );
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
