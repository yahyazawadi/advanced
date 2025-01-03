package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
