package controllers;


//import com.example.real_estate_application.models.RealEstate;
//import com.example.real_estate_application.models.services.RealEstateDAOImp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class AddPropertyControllernew {
    //  RealEstateDAOImp realEstateDOAImp = new RealEstateDAOImp();

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
    private byte[] imageBytes;

    @FXML
    public void SaveRealEstate() {
    /*    RealEstate realEstate = new RealEstate();
        realEstate.setPrice(Integer.parseInt(tv_Price.getText()));
        realEstate.setType((String) cb_Type.getValue());
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
    */}

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
        }
    }
}

