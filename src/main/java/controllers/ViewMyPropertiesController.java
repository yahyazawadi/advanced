package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.models.RealEstate;
import main.services.RealEstateDAOImp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class ViewMyPropertiesController {

    @FXML
    private GridPane contentArea;
    private Button backButton;
    private RealEstateDAOImp realEstateDAO = new RealEstateDAOImp();

    @FXML
    public void initialize() {
        // عرض البيانات عند تهيئة الصفحة
        contentArea.setHgap(20);  // زيادة المسافة بين الأعمدة
        contentArea.setVgap(20);  // زيادة المسافة بين الصفوف
        displayRealEstateCards();
    }

    /**
     * Initializes the controller after FXML is loaded.
     */


    /**
     * Handles the Edit button click and loads the edit.fxml page.
     */


    /**
     * Loads any FXML page into the content area dynamically.
     *
     * @param fxmlFile The path to the FXML file.
     */
    public void loadPage(String fxmlFile) {
        try {
            // Load FXML content
            Node content = FXMLLoader.load(getClass().getResource(fxmlFile));

            // Set the loaded content as the ScrollPane's content
            contentArea.getContentBias();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load " + fxmlFile);
        }
    }
    private void displayRealEstateCards() {
        // استرجاع جميع العقارات من قاعدة البيانات
        List<RealEstate> realEstates = realEstateDAO.getAllRealEstates();

        // تفريغ الشبكة
        contentArea.getChildren().clear();

        int row = 0;
        int col = 0;

        for (RealEstate realEstate : realEstates) {
            // إنشاء بطاقة
            AnchorPane card = createCard(realEstate);

            // إضافتها إلى الشبكة
            contentArea.add(card, col, row);

            col++;
            if (col == 3) { // عدد الأعمدة (يمكن تغييره)
                col = 0;
                row++;
            }
        }
    }
    private AnchorPane createCard(RealEstate realEstate) {
        AnchorPane card = new AnchorPane();
        card.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC; -fx-border-width: 1; -fx-padding: 10;");
        card.setPrefWidth(380);
        card.setPrefHeight(300);

        // Image
        ImageView imageView = new ImageView();
        imageView.setFitWidth(360);
        imageView.setFitHeight(180);
        if (realEstate.getImag() != null) {
            Image image = new Image(new ByteArrayInputStream(realEstate.getImag()));
            imageView.setImage(image);
        } else {
            imageView.setImage(new Image("file:../images/placeholder.png"));
        }
        imageView.setLayoutX(10);
        imageView.setLayoutY(0);

        // Title
        Label titleLabel = new Label(realEstate.getNameOfProperty());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        titleLabel.setLayoutX(10);
        titleLabel.setLayoutY(imageView.getLayoutY() + imageView.getFitHeight() + 10);

        // Edit Button
        Button editButton = new Button("Edit");
        editButton.setStyle("-fx-font-size: 14px; -fx-background-color: #009688; -fx-text-fill: #FFFFFF;");
        editButton.setLayoutX(10);
        editButton.setLayoutY(50);

        // Attach the handleEditButtonAction function
        editButton.setOnAction(event -> handleEditButtonAction(realEstate.getId()));


        // Add components to the card
        card.getChildren().addAll(imageView, titleLabel, editButton);

        return card;
    }

    @FXML
    private void handleEditButtonAction(int propertyId) {
        try {
            // Load the DeleteAndEdit.fxml page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit.fxml"));
            Parent editView = loader.load();

            // Get the controller of the loaded page
            DeleteAndEditController controller = loader.getController();

            // Pass the property ID to the controller
            controller.setPropertyId(propertyId);

            // Replace the current content with the edit page
            contentArea.getChildren().setAll(editView);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the edit page for property ID: " + propertyId);
        }
    }



    private void openPropertyDetail(RealEstate realEstate) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropertyDetail.fxml"));
            Parent detailView = loader.load();

            // تمرير بيانات العقار
            loader.<PropertyDetailController>getController().setRealEstate(realEstate);

            // تحديث محتوى الصفحة
            contentArea.getChildren().setAll(detailView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}