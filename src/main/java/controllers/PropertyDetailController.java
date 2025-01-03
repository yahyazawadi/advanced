package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.models.RealEstate;

import java.io.ByteArrayInputStream;

public class PropertyDetailController {

    @FXML
    private ImageView propertyImage;
    @FXML
    private Label nameLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label stateLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label zipCodeLabel;
    @FXML
    private Label descriptionText;
    @FXML
    private Button backButton; // زر الرجوع

    private RealEstate realEstate;

    // Setter method to inject real estate data into the controller
    @FXML
    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;

        // تحديث واجهة المستخدم بناءً على البيانات
        if (realEstate.getImag() != null) {
            Image image = new Image(new ByteArrayInputStream(realEstate.getImag()));
            propertyImage.setImage(image);
        }
        nameLabel.setText(realEstate.getNameOfProperty());
        countryLabel.setText(realEstate.getCountry());
        typeLabel.setText(realEstate.getType());
        priceLabel.setText("$" + realEstate.getPrice());
        stateLabel.setText(realEstate.getState());
        cityLabel.setText(realEstate.getcity());
        zipCodeLabel.setText(realEstate.getZipCode());
        descriptionText.setText(realEstate.getDescription());
    }

    // Called after the FXML is loaded
    @FXML
    private void initialize() {
        System.out.println("initialize() method called"); // Check if initialize() is executed


        backButton.setOnAction(event -> {
            System.out.println("Back button clicked"); // Debugging line to ensure it's triggered
            goBackToList();
        });
    }

    // Handles the action when the back button is clicked
    private void goBackToList() {
        try {
            // Load the "allproperties.fxml" view when back button is clicked
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/allproperties.fxml"));
            Parent listView = loader.load();

            // Update the current content with the new view
            ((AnchorPane) backButton.getScene().getRoot()).getChildren().setAll(listView);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load FXML file.");
        }
    }
}
