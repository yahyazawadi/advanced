package controllers;


//import com.example.real_estate_application.models.RealEstate;
//import com.example.real_estate_application.models.services.RealEstateDAOImp;
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
import java.util.List;

public class DeleteAndEditController {
   // RealEstateDAOImp realEstateDOAImp = new RealEstateDAOImp();

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
    private ComboBox cb_Type;
    @FXML
    private TextField tv_ZipCode;
    @FXML
    private TextField tv_Price;
   @FXML
    private ImageView imageView;
    @FXML
    public void initialize() {
     /*   RealEstate e = realEstateDOAImp.getRealEstateById(18);
        lb_ID.setText(String.valueOf(e.getId()));

        lb_ID.setDisable(false);
        tv_City.setText(e.getcity());
        tv_Price.setText(String.valueOf(e.getPrice()));
        cb_Type.setValue(e.getType());
        tv_State.setText(e.getState());
        tv_Country.setText(e.getCountry());
        tv_Address.setText(e.getAddress());
        tv_NameOfProperty.setText(e.getNameOfProperty());
        tv_ZipCode.setText(e.getZipCode());
        ta_Description.setText(e.getDescription());
        e.setNameOfProperty(tv_NameOfProperty.getText());
        e.setZipCode(tv_ZipCode.getText());
        e.setDescription(ta_Description.getText());
        e.setAddress(e.getAddress());
        e.setCountry(e.getCountry());
        e.setcity(e.getcity());
        e.setPrice(e.getPrice());
        e.setType(e.getType());
        e.setState(e.getState());
        if (e.getImag() != null) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(e.getImag());
            Image image = new Image(inputStream);
            imageView.setImage(image);
        } else {
            System.out.println("No image available for this real estate.");
        }
    }


    @FXML
    public void DeleteRealEstate() {
        realEstateDOAImp.delete(18);
        System.out.println("Deleted realEstate");
    }
    @FXML
    public void saveRealEstateChanges() {
        RealEstate e = realEstateDOAImp.getRealEstateById(18);
        String newcity = tv_City.getText();
        String newPrice = tv_Price.getText();
        String newType = (String) cb_Type.getValue();
        String newState = (String) tv_State.getText();
        String newCountry = (String) tv_Country.getText();
        String newZipCode = (String) tv_ZipCode.getText();
        String newDescription = (String) ta_Description.getText();
        String newNameOfProperty = (String) tv_NameOfProperty.getText();
        String newAddress = (String) tv_Address.getText();
        String newCity = tv_City.getText();
        e.setType(newType);
        e.setPrice(Integer.parseInt(newPrice));
        e.setcity(newcity);
        e.setState(newType);
        e.setCountry(newCountry);
        e.setZipCode(newZipCode);
        e.setDescription(newDescription);
        e.setNameOfProperty(newNameOfProperty);
        e.setAddress(newAddress);
        e.setCountry(newCountry);
        realEstateDOAImp.update(e);

        System.out.println("Succeeded!");
    }
    @FXML
    public void uploadImages() {
        Stage stage = (Stage) lb_ID.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select images to upload.");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );


        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);
        if (selectedFiles != null && !selectedFiles.isEmpty()) {
            for (File file : selectedFiles) {
                byte[] imageBytes = readImageBytes(file);
                if (imageBytes != null) {
                    System.out.println("Image uploaded successfully: " + file.getName());
                } else {
                    System.out.println("Failed to upload image: " + file.getName());
                }
            }
        } else {
            System.out.println("No files selected.");
        }
    }

    private byte[] readImageBytes(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.out.println("Failed to read image bytes: " + e.getMessage());
            return null;
        }*/
    }
}
