package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AddRealEstateController {

    @FXML
    private TextField tv_Location;

    @FXML
    private ComboBox<String> cb_Specifications;

    @FXML
    private TextField tv_Price;

    @FXML
    private Button btn_uploadImage;

    @FXML
    private Button btn_save;

    @FXML
    private ImageView propertyImageView;

    private File selectedImageFile;

    // Initialize method (Optional setup for ComboBox items)
    @FXML
    public void initialize() {
        cb_Specifications.getItems().addAll("Apartment", "House", "Commercial Property", "Land");
    }

    // Method to handle Image Upload Button
    @FXML
    private void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Property Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = (Stage) btn_uploadImage.getScene().getWindow();
        selectedImageFile = fileChooser.showOpenDialog(stage);

        if (selectedImageFile != null) {
            propertyImageView.setImage(new Image(selectedImageFile.toURI().toString()));
        }
    }

    // Method to handle Save Button
    @FXML
    private void SaveRealEstate() {
        String location = tv_Location.getText();
        String price = tv_Price.getText();
        String specification = cb_Specifications.getValue();

        if (location.isEmpty() || price.isEmpty() || specification == null) {
            System.out.println("Please fill all the required fields.");
            return;
        }

        // Mock save operation (Replace with actual logic)
        System.out.println("Saving Property:");
        System.out.println("Location: " + location);
        System.out.println("Price: " + price);
        System.out.println("Specification: " + specification);
        System.out.println("Image: " + (selectedImageFile != null ? selectedImageFile.getName() : "No image uploaded"));
    }
}
