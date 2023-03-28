package at.htl.workshopsystem.controller.database;

import org.apache.derby.jdbc.ClientDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class Database {
    static final String USERNAME = "system";
    static final String PASSWORD = "oracle";
    public static final String URL = "jdbc:oracle:thin:@student.cloud.htl-leonding.ac.at";


    public static DataSource getDataSource(){
        DataSource dataSource = new OracleDataSource();
        dataSource.setDatabaseName(DATABASE);
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    public void createTableTest(){
        try (Connection conn = getDataSource().getConnection()) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}