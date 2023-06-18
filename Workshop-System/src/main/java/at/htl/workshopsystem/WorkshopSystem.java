package at.htl.workshopsystem;

import at.htl.workshopsystem.view.controller.HomeController;
import atlantafx.base.theme.CupertinoLight;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class WorkshopSystem extends Application {
    public static Stage stage = null;
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        /*stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());*/
        stage.setTitle("Home");
        stage.setScene(scene);
        WorkshopSystem.stage = stage;
        //MetaData.parent = root;
        stage.show();
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String title){
        Parent root = null;
        Application.setUserAgentStylesheet(new CupertinoLight().getUserAgentStylesheet());

       /*if (fxmlFile.equals("carSearch.fxml")) {

            //Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
            //Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
            //Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());
            //Application.setUserAgentStylesheet(new NordDark().getUserAgentStylesheet());
            //Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
            //Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
            Application.setUserAgentStylesheet(new CupertinoLight().getUserAgentStylesheet());

        }*/

        try{
            FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("/at/htl/workshopsystem/" + fxmlFile));
            root = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void onPageChange(Button homeBtn, Button customersBtn, Button tasksBtn) {
        homeBtn.setOnAction(event -> WorkshopSystem.changeScene(event, "home.fxml", "Home"));
        customersBtn.setOnAction(event -> WorkshopSystem.changeScene(event, "customers.fxml", "Customers"));
        tasksBtn.setOnAction(event -> WorkshopSystem.changeScene(event, "tasks.fxml", "Tasks"));
    }
}