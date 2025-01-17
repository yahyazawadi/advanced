package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class MainController {

    public StackPane contentArea;

    public void goToAdminDashboard() {
        loadPage("AdminDashBoard.fxml");
    }

    public void goToAddClient() {
        loadPage("AddCustomer.fxml");
    }


    private void loadPage(String fxmlFile) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlFile));
            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}