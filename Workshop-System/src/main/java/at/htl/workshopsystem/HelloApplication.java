package at.htl.workshopsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            //Class.forName("oracle.jdbc.OracleDriver");

            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@student.cloud.htl-leonding.ac.at:31521:ora19db\n", "IF200210", "oracle");
            if (conn != null) {
                System.out.println("Connected");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        launch();
    }
}