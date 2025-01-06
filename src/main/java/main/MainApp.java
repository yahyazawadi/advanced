
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class
MainApp extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainApp.primaryStage = primaryStage;
        showLoginScreen(); // Load login screen at start
    }

    public static void showLoginScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showAdminDashboard() throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/Adminsidebar.fxml"));
        Scene scene = new Scene(loader.load());

        primaryStage.setTitle("Admin Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showAgentDashboard() throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/Agentsidebar.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Agent Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
