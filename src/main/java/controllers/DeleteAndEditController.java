package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.models.RealEstate;
import main.services.RealEstateDAOImp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DeleteAndEditController {
    RealEstateDAOImp realEstateDOAImp = new RealEstateDAOImp();
    private int propertyId;
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
    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
        initialize();
    }
    @FXML
    public void initialize() {
        RealEstate e = realEstateDOAImp.getRealEstateById(propertyId);
        if (e != null) {
            lb_ID.setText(String.valueOf(e.getId()));
            tv_City.setText(e.getcity());
            tv_Price.setText(String.valueOf(e.getPrice()));
            cb_Type.setValue(e.getType());
            tv_State.setText(e.getState());
            tv_Country.setText(e.getCountry());
            tv_Address.setText(e.getAddress());
            tv_NameOfProperty.setText(e.getNameOfProperty());
            tv_ZipCode.setText(e.getZipCode());
            ta_Description.setText(e.getDescription());

            if (e.getImag() != null) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(e.getImag());
                Image image = new Image(inputStream);
                imageView.setImage(image);
            }
        } else {
            System.out.println("Property not found!");
        }


    }

    @FXML
    public void DeleteRealEstate() {
        realEstateDOAImp.delete(propertyId);
        System.out.println("Deleted real estate successfully!");
    }

    @FXML
    public void saveRealEstateChanges() throws IOException {
        RealEstate e = realEstateDOAImp.getRealEstateById(propertyId);

        String newCity = tv_City.getText();
        String newPrice = tv_Price.getText();
        String newType = cb_Type.getValue();
        String newState = tv_State.getText();
        String newCountry = tv_Country.getText();
        String newZipCode = tv_ZipCode.getText();
        String newDescription = ta_Description.getText();
        String newNameOfProperty = tv_NameOfProperty.getText();
        String newAddress = tv_Address.getText();

        e.setType(newType);
        e.setPrice(Integer.parseInt(newPrice));
        e.setcity(newCity);
        e.setState(newState);
        e.setCountry(newCountry);
        e.setZipCode(newZipCode);
        e.setDescription(newDescription);
        e.setNameOfProperty(newNameOfProperty);
        e.setAddress(newAddress);


        if (imageBytes != null && imageBytes.length > 0) {
            e.setImag(imageBytes);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
            Image image = new Image(inputStream);
            imageView.setImage(image);
        } else {
            System.out.println("No image uploaded");
        }

        realEstateDOAImp.update(e);
        System.out.println("Real estate updated successfully!");
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
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                Image image = new Image(inputStream);
                imageView.setImage(image);
                System.out.println("Image uploaded successfully: " + selectedFile.getName());
            }
        } else {
            System.out.println("No file selected.");
        }
    }

    private byte[] readImageBytes(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.out.println("Failed to read image bytes: " + e.getMessage());
            return null;
        }
    }

    @FXML
    public void deleteImage() {
        RealEstate e = realEstateDOAImp.getRealEstateById(propertyId);
        e.setImag(null);
        realEstateDOAImp.update(e);
        imageView.setImage(null);
        imageBytes = null;
        System.out.println("Image deleted successfully!");
    }
    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/myproperties.fxml"));
            Parent viewPropertiesView = loader.load();


            ImageView propertyImage = new ImageView(new Image("image.png"));
            VBox root = new VBox(propertyImage);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

// الآن يمكن تنفيذ السطر بدون أخطاء
            Parent rootNode = propertyImage.getScene().getRoot();
            System.out.println(rootNode.getClass().getSimpleName()); // VBox


            if (rootNode instanceof BorderPane) {

                BorderPane borderPane = (BorderPane) rootNode;
                borderPane.setCenter(viewPropertiesView);
            } else if (rootNode instanceof AnchorPane) {

                AnchorPane anchorPane = (AnchorPane) rootNode;
                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(viewPropertiesView);
            } else {

                System.out.println("Unsupported root type: " + rootNode.getClass().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}