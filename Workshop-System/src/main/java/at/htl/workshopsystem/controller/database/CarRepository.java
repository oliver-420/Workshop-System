package at.htl.workshopsystem.controller.database;

import at.htl.workshopsystem.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    private Database database = new Database();

    public Car insert(Car car) {
        try (Connection connection = database.getConnection()) {
            String sql = "INSERT INTO CAR (MODEL, " +
                    "MANUFACTURER, " +
                    "PRODUCTION_YEAR, " +
                    "REGISTRATION_YEAR, " +
                    "OWNER_NAME, FUEL_TYPE, " +
                    "NUMBER_PLATE) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"ID"});
            statement.setString(1, car.getModel());
            statement.setString(2, car.getManufacturer());
            statement.setFloat(3, car.getProductionYear());
            statement.setFloat(4, car.getRegistrationYear());
            statement.setString(5, car.getOwner());
            statement.setString(6, car.getFuel());
            statement.setString(7, car.getNumberPlate());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating car failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    car.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating car failed, no ID obtained.");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    public void update(Car car) {
        try (Connection connection = database.getConnection()) {
            String sql = "UPDATE CAR SET MODEL=?, " +
                    "MANUFACTURER=?, " +
                    "PRODUCTION_YEAR=?, " +
                    "REGISTRATION_YEAR=?, " +
                    "OWNER_NAME=?, " +
                    "FUEL_TYPE=?, " +
                    "NUMBER_PLATE=? " +
                    "WHERE ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, car.getModel());
            statement.setString(2, car.getManufacturer());
            statement.setFloat(3, car.getProductionYear());
            statement.setFloat(4, car.getRegistrationYear());
            statement.setString(5, car.getOwner());
            statement.setString(6, car.getFuel());
            statement.setString(7, car.getNumberPlate());
            statement.setFloat(8, car.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of car failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (Connection connection = database.getConnection()) {
            String sql = "DELETE FROM CAR WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from car failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAll() {
        List<Car> carList = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM CAR";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                long id = result.getLong(1);
                String model = result.getString(2);
                String manufacturer = result.getString(3);
                int prod_yr = result.getInt(4);
                int reg_yr = result.getInt(5);
                String owner = result.getString(6);
                String fueltype = result.getString(7);
                String numberplate = result.getString(8);
                Car newCar = new Car(id, model, manufacturer, prod_yr, reg_yr, owner, fueltype, numberplate);
                carList.add(newCar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public Car getById(long id) {
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM CAR WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (id == result.getLong(1)) {
                    System.out.println(result.getLong(1));
                    return new Car(id,
                            result.getString(2),
                            result.getString(3),
                            result.getInt(4),
                            result.getInt(5),
                            result.getString(6),
                            result.getString(7),
                            result.getString(8));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
