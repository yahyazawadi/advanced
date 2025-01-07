package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class AdminSideBarController {

    @FXML
    public Button dashboardButton;
    @FXML
    public Button newPropertiesButton;
    @FXML
    public Button viewPropertiesButton;
    @FXML
    public Button newClientButton;
    @FXML
    public Button clientsButton;
    @FXML
    public Button newagent;
    @FXML
    public Button viewagents;
    @FXML
    public Button login;
    @FXML
    private HBox contentArea;


    @FXML
    public void initialize() {
        login.setOnAction(this::login);
        dashboardButton.setOnAction(this::handleDashboardButton);
        newPropertiesButton.setOnAction(this::handleAddProperties);
        viewPropertiesButton.setOnAction(this::handleViewProperties);
        newClientButton.setOnAction(this::handleAddClient);
        clientsButton.setOnAction(this::handleViewClients);
        newagent.setOnAction(this::HandleNewAgent);
        viewagents.setOnAction(this::HandleViewAgents);

        loadPage("/fxml/AdminDashBoard.fxml");
    }

    public void loadPage(String fxmlFile) {
        try {
            Parent content = FXMLLoader.load(getClass().getResource(fxmlFile));
            contentArea.getChildren().clear();
            contentArea.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load " + fxmlFile);
        }
    }


    public void login(ActionEvent actionEvent) {
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(loginRoot));
            loginStage.show();

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load login page");
        }
    }
    public void handleDashboardButton(ActionEvent event) {
        loadPage("/fxml/AdminDashBoard.fxml");
    }

    public void handleAddProperties(ActionEvent event) {
        loadPage("/fxml/AddRealEstate.fxml");
    }

    public void handleViewProperties(ActionEvent event) {
        loadPage("/fxml/MyProperties.fxml");
    }

    public void handleAddClient(ActionEvent event) {
        loadPage("/fxml/AddCustomer.fxml");
    }

    public void handleViewClients(ActionEvent event) {
        loadPage("/fxml/AllCustomers.fxml");
    }

    @FXML
    private void HandleNewAgent(ActionEvent actionEvent) {
        loadPage("/fxml/AddAgents.fxml");
    }

    @FXML
    private void HandleViewAgents(ActionEvent actionEvent) {
        loadPage("/fxml/AllAgent.fxml");
    }

}