package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class AgentDashBoardController {

    // Define buttons matching FXML fx:id
    @FXML
    public Button DashboardButton;

    @FXML
    public Button NewPropertiesButton;

    @FXML
    public Button viewPropertiesButton;

    @FXML
    public Button NewClientButton;

    @FXML
    public Button clientsButton;

    @FXML
    public Button propertyInquiriesButton;

    @FXML
    public Button myPropertyInquiriesButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller after the root element has been completely processed.
     */

    @FXML
    public void initialize() {
        // Validate that buttons are not null
        if (NewPropertiesButton == null || viewPropertiesButton == null ||
                NewClientButton == null || clientsButton == null ||
                propertyInquiriesButton == null || myPropertyInquiriesButton == null) {
            throw new IllegalStateException("One or more buttons are not properly injected. Please check the FXML file.");
        }

        // Assign actions to buttons dynamically
        NewPropertiesButton.setOnAction(this::handleNewPropertiesButton);
        viewPropertiesButton.setOnAction(this::handlePropertiesClick);
        NewClientButton.setOnAction(this::handleNewClientClick);
        clientsButton.setOnAction(this::handleClientsButton);
        propertyInquiriesButton.setOnAction(this::handlePropertyInquiriesButton);
        myPropertyInquiriesButton.setOnAction(this::handleMyPropertyInquiriesButton);
    }


    /**
     * Handles navigation to a specific FXML file.
     */
    public void navigateTo(ActionEvent event, String fxmlFile) {
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlFile));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load " + fxmlFile);
        }
    }

    // Individual button handlers
    public void handleDashboardButton(ActionEvent event) {
        navigateTo(event, "/fxml/AgentDashboard.fxml");
    }

    public void handleNewPropertiesButton(ActionEvent event) {
        navigateTo(event, "/fxml/AddProperties.fxml");
    }

    public void handlePropertiesClick(ActionEvent event) {
        navigateTo(event, "/fxml/properties.fxml");
    }

    public void handleNewClientClick(ActionEvent event) {
        navigateTo(event, "/fxml/addClient.fxml");
    }

    public void handleClientsButton(ActionEvent event) {
        navigateTo(event, "/fxml/allClients.fxml");
    }

    public void handlePropertyInquiriesButton(ActionEvent event) {
        navigateTo(event, "/fxml/ClientInquiries.fxml");
    }

    public void handleMyPropertyInquiriesButton(ActionEvent event) {
        navigateTo(event, "/fxml/OfferTable.fxml");
    }
}
