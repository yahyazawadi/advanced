package com.example.real_estate_application;

import com.example.real_estate_application.models.RealEstate;
import com.example.real_estate_application.models.services.RealEstateDAOImp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HelloControllerAdd {
    RealEstateDAOImp realEstateDOAImp = new RealEstateDAOImp();

    @FXML
    private Label lb_ID;
    @FXML
    private TextField tv_NameOfProperty;
    @FXML
    private TextArea ta_Description;
    @FXML
    private TextField tv_Address;
    @FXML
    private TextField tv_City;
    @FXML
    private TextField tv_State;
    @FXML
    private TextField tv_Country;
    @FXML
    private ComboBox<String> cb_Type;
    @FXML
    private TextField tv_ZipCode;
    @FXML
    private TextField tv_Price;
    @FXML
    private ImageView imageView;
    @FXML
    private byte[] imageBytes;

    @FXML
    public void SaveRealEstate() {
        RealEstate realEstate = new RealEstate();
        realEstate.setPrice(Integer.parseInt(tv_Price.getText()));
        realEstate.setType(cb_Type.getValue());
        realEstate.setcity(tv_City.getText());
        realEstate.setState(tv_State.getText());
        realEstate.setCountry(tv_Country.getText());
        realEstate.setZipCode(tv_ZipCode.getText());
        realEstate.setAddress(tv_Address.getText());
        realEstate.setDescription(ta_Description.getText());
        realEstate.setNameOfProperty(tv_NameOfProperty.getText());

        if (imageBytes != null && imageBytes.length > 0) {
            realEstate.setImag(imageBytes);
        } else {
            System.out.println("No image uploaded");
        }

        realEstateDOAImp.save(realEstate);
        System.out.println("Real Estate saved successfully!");
    }

    @FXML
    public void uploadImage() {
        Stage stage = (Stage) lb_ID.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image to upload.");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            imageBytes = readImageBytes(selectedFile);
            if (imageBytes != null) {
                System.out.println("Image uploaded successfully: " + selectedFile.getName());
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                Image image = new Image(inputStream);
                imageView.setImage(image);

            }
        } else {
            System.out.println("No file selected.");
        }
    }

    private byte[] readImageBytes(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.out.println("Failed to upload image: " + e.getMessage());
            return null;
        }
    }

    private void displayImage(byte[] imageBytes) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
        Image image = new Image(inputStream);
        imageView.setImage(image);
    }
}



