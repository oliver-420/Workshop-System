package at.htl.workshopsystem.controller.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oracle.jdbc.replay.driver.ReplayStatisticsMBeanImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Database {
    static final String DATABASE = "db";
    static final String USERNAME = "app";
    static final String PASSWORD = "app";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            //Class.forName("oracle.jdbc.OracleDriver");

            conn = DriverManager.getConnection("jdbc:oracle:thin:@student.cloud.htl-leonding.ac.at:31521:ora19db\n", "IF200210", "oracle");
            if (conn != null) {
                System.out.println("Connected");
                conn.setAutoCommit(true);
            } else {
                System.out.println("Not Connected");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

}
