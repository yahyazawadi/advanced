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

public class AgentSideBarController {
    @FXML
    public Button dashboardButton;
    @FXML
    public Button newPropertiesButton;
    @FXML
    public Button newClientButton;
    @FXML
    public Button myclientsButton;
    @FXML
    public Button login;
    @FXML
    public Button viewmyPropertiesButton;
    @FXML
    public Button viewallPropertiesButton;
    @FXML
    private HBox contentArea;
    @FXML
    public void initialize() {
        login.setOnAction(this::login);
        dashboardButton.setOnAction(this::handleDashboardButton);
        newPropertiesButton.setOnAction(this::handleAddProperties);
        newClientButton.setOnAction(this::handleAddClient);
        myclientsButton.setOnAction(this::handleViewMyClients);
        viewallPropertiesButton.setOnAction(this::handleViewAllProperties);
        viewmyPropertiesButton.setOnAction(this::handleViewMyProperties);
        loadPage("/fxml/AgentDashBoard.fxml");
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
    public void handleDashboardButton(ActionEvent event) {loadPage("/fxml/AgentDashBoard.fxml");}

    private void handleViewMyProperties(ActionEvent actionEvent) {loadPage("/fxml/MyProperties.fxml");}

    private void handleViewAllProperties(ActionEvent actionEvent) { loadPage("/fxml/AllProperties.fxml");}

    public void handleAddProperties(ActionEvent event) {loadPage("/fxml/AddRealEstate.fxml");}

    public void handleViewProperties(ActionEvent event) {
        loadPage("/fxml/PropertyDetail.fxml");
    }

    public void handleAddClient(ActionEvent event) {
        loadPage("/fxml/AddCustomer.fxml");
    }

    public void handleViewMyClients(ActionEvent event) {
        loadPage("/fxml/AllCustomers.fxml");
    }
}