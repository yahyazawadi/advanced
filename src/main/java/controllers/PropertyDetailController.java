package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main.models.RealEstate;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class PropertyDetailController {


    @FXML
    public Button makeOfferButton;
    @FXML
    public Button makeInquiryButton;
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

    private RealEstate realEstate;

    @FXML
    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;

        // تحديث البيانات على الواجهة
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
    @FXML
    private void setMakeOfferButton(int propertyId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddOffer.fxml"));
            Parent editView = loader.load();

            DeleteAndEditController controller = loader.getController();


            controller.setPropertyId(propertyId);


            //contentArea.getChildren().setAll(editView);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the edit page for property ID: " + propertyId);
        }
    }
    @FXML
    private void makeOffer() {}
    @FXML
    private void goBack() {
        try {
            // تحميل واجهة عرض العقارات
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/allproperties.fxml"));
            Parent viewPropertiesView = loader.load();

            // الحصول على الجذر الحالي
            Parent rootNode = propertyImage.getScene().getRoot();

            if (rootNode instanceof BorderPane) {
                // إذا كان الجذر الحالي من نوع BorderPane
                BorderPane borderPane = (BorderPane) rootNode;
                borderPane.setCenter(viewPropertiesView); // استبدال الجزء الأوسط فقط
            } else if (rootNode instanceof AnchorPane) {
                // إذا كان الجذر الحالي من نوع AnchorPane
                AnchorPane anchorPane = (AnchorPane) rootNode;
                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(viewPropertiesView);
            } else {
                // إذا كان الجذر نوعًا آخر
                System.out.println("Unsupported root type: " + rootNode.getClass().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
