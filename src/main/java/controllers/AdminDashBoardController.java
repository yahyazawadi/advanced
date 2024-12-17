package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminDashBoardController {

    @FXML
    private Button DashboardButton;

    @FXML
    private Button Newproperty;

    @FXML
    private Button viewPropertiesButton1;

    @FXML
    private Button newtclient;

    @FXML
    private Button clientsButton;

    @FXML
    private Button viewPropertiesButton21;

    @FXML
    private Button propertyInquiriesButton;

    // Initialize method to set default behavior (Optional)
    @FXML
    public void initialize() {
        System.out.println("Admin Dashboard Initialized");
    }

    // Event handlers for button actions
    @FXML
    private void handleDashboardButton() {
        System.out.println("Navigating to Dashboard");
        // Add navigation logic here
    }

    @FXML
    private void handleNewPropertyButton() {
        System.out.println("Navigating to New Property");
        // Add navigation logic here
    }

    @FXML
    private void handleViewPropertiesButton() {
        System.out.println("Navigating to View Properties");
        // Add navigation logic here
    }

    @FXML
    private void handleNewClientButton() {
        System.out.println("Navigating to New Client");
        // Add navigation logic here
    }

    @FXML
    private void handleClientsButton() {
        System.out.println("Navigating to All Clients");
        // Add navigation logic here
    }

    @FXML
    private void handleNewAgentButton() {
        System.out.println("Navigating to New Agent");
        // Add navigation logic here
    }

    @FXML
    private void handlePropertyInquiriesButton() {
        System.out.println("Navigating to All Agents");
        // Add navigation logic here
    }
}
