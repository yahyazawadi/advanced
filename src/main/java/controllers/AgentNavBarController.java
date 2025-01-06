package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;  // Add this import for content container (HBox)
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class AgentNavBarController {

    // Define buttons matching FXML fx:id
    @FXML
    public Button dashboardButton;

    @FXML
    public Button newPropertiesButton;

    @FXML
    public Button viewPropertiesButton;

    @FXML
    public Button newClientButton;

    @FXML
    public Button myclientsButton;

    @FXML
    public Button sentPropertyInquiriesButton;

    @FXML
    public Button receivedPropertyInquiriesButton;
    @FXML
    public Button login;
    @FXML
    public Button viewmyPropertiesButton;
    @FXML
    public Button viewallPropertiesButton;
    @FXML
    public Button sentPropertyOffersButton;
    @FXML
    public Button receivedPropertyOffersButton;

    @FXML
    private HBox contentArea;  // Add reference to content area (HBox) in your FXML

    /**
     * Initializes the controller after the root element has been completely processed.
     */
    @FXML
    public void initialize() {
        // Assign actions to buttons dynamically
        login.setOnAction(this::login);
        dashboardButton.setOnAction(this::handleDashboardButton);
        newPropertiesButton.setOnAction(this::handleAddProperties);
        newClientButton.setOnAction(this::handleAddClient);
        myclientsButton.setOnAction(this::handleViewMyClients);
        receivedPropertyInquiriesButton .setOnAction(this::handleViewInquiries);
        sentPropertyInquiriesButton.setOnAction(this::handlePropertyInquiries);
        viewallPropertiesButton.setOnAction(this::handleViewAllProperties);
        viewmyPropertiesButton.setOnAction(this::handleViewMyProperties);
        receivedPropertyOffersButton.setOnAction(this::handleReceivedPropertyOffers);
        sentPropertyOffersButton.setOnAction(this::handleSentPropertyOffers);
        // Initial page load (optional, you can load any default page here)
        loadPage("/fxml/AgentDashboard.fxml");
    }

    private void handleViewMyProperties(ActionEvent actionEvent) {loadPage("/fxml/myproperties.fxml");
    }

    private void handleViewAllProperties(ActionEvent actionEvent) { loadPage("/fxml/allproperties.fxml");
    }

    @FXML
    private void handleViewInquiries(ActionEvent actionEvent) {
        loadPage("/fxml/Inquiries.fxml");
    }

    @FXML
    private void handlePropertyInquiries(ActionEvent actionEvent) {
        loadPage("/fxml/SentInquries.fxml");
    }

    /**
     * Handles navigation to a specific FXML file.
     * This method loads the content into the contentArea (HBox).
     */
    public void loadPage(String fxmlFile) {
        try {
            // Load the FXML file
            Parent content = FXMLLoader.load(getClass().getResource(fxmlFile));
            // Clear previous content
            contentArea.getChildren().clear();
            // Add the new content to the contentArea
            contentArea.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load " + fxmlFile);
        }
    }

    // Individual button handlers
    public void handleDashboardButton(ActionEvent event) {
        loadPage("/fxml/AgentDashboard.fxml");
    }

    public void handleAddProperties(ActionEvent event) {
        loadPage("/fxml/AddOffer.fxml");
    }

    public void handleViewProperties(ActionEvent event) {
        loadPage("/fxml/Properties.fxml");
    }

    public void handleAddClient(ActionEvent event) {
        loadPage("/fxml/AddClient.fxml");
    }

    public void handleViewMyClients(ActionEvent event) {
        loadPage("/fxml/allClients.fxml");
    }




    public void login(ActionEvent actionEvent) {
        try {
            // Load the login page FXML
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));

            // Create a new stage for the login page
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(loginRoot));
            loginStage.show();

            // Close the current stage (navbar page)
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load login page");
        }
    }

    public void handleReceivedPropertyOffers(ActionEvent actionEvent) {
        loadPage(    "/fxml/ReceivedOffers.fxml");
    }

    public void handleSentPropertyOffers(ActionEvent actionEvent) {
        loadPage(    "/fxml/SentOffers.fxml");
    }
}