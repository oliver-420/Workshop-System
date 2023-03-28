package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.Mechanic;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class MechanicRepository {
    private Database database = new Database();

    public Mechanic insert(Mechanic mechanic) {
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO MECHANIC (NAME, HOURLY_WAGE) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, mechanic.get_name());
            statement.setFloat(2, mechanic.get_hourlyWage().floatValue());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of MECHANIC failed, no rows affected");
            }
            try (ResultSet keys = statement.executeQuery()) {
                if (keys.next()) {
                    System.out.println(keys.getFloat(1));
                    System.out.println(keys.getString(1));
                } else {
                    throw new SQLException("Insert into MECHANIC failed, no ID obtained");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mechanic;
    }

    public void update(Mechanic location) {

    }

    public void delete(int id) {

    }

    public List<Mechanic> getAll() {
        return null;
    }

    public Mechanic getById(int id) {
        return null;
    }
}
