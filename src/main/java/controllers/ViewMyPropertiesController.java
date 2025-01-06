package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.models.RealEstate;
import main.imp.RealEstateDAOImp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class ViewMyPropertiesController {
    @FXML
    public AnchorPane ToDeleteArea;
    @FXML
    private VBox offerContentArea;
    @FXML
    private GridPane contentArea;
    private Button backButton;
    private RealEstateDAOImp realEstateDAO = new RealEstateDAOImp();
    @FXML
    private TextField searchField;
    @FXML
    public void initialize() {
        // عرض البيانات عند تهيئة الصفحة
        contentArea.setHgap(20);  // زيادة المسافة بين الأعمدة
        contentArea.setVgap(20);  // زيادة المسافة بين الصفوف
        displayRealEstateCards();
    }
    private void updateContentArea(List<RealEstate> realEstates) {
        this.contentArea.getChildren().clear();
        int row = 0;
        int col = 0;

        for(RealEstate realEstate : realEstates) {
            AnchorPane card = this.createCard(realEstate);
            this.contentArea.add(card, col, row);
            ++col;
            if (col == 3) {
                col = 0;
                ++row;
            }
        }

    }
    public void handleSearch() {
        String query = this.searchField.getText().toLowerCase();
        List<RealEstate> results = this.realEstateDAO.searchRealEstate(query);
        this.contentArea.getChildren().clear();

        for(RealEstate realEstate : results) {
            this.contentArea.add(this.createCard(realEstate), 0, this.contentArea.getRowCount());
        }

    }
    @FXML
    private void openFilterWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FilterWindow.fxml"));
            Parent filterView = (Parent)loader.load();
            FilterWindowController controller = (FilterWindowController)loader.getController();
            controller.setFilterListener((priceRange, propertyType) -> {
                List<RealEstate> filteredResults = this.realEstateDAO.filterRealEstates(priceRange, propertyType);
                this.updateContentArea(filteredResults);
            });
            Stage stage = new Stage();
            stage.setTitle("Filter Properties");
            stage.setScene(new Scene(filterView));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * Initializes the controller after FXML is loaded.
     */


    /**
     * Handles the Edit button click and loads the EditAndDelete.fxml page.
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

        // Inquiries Button
        Button MyOffersButton = new Button("Offers");
        MyOffersButton.setStyle("-fx-font-size: 14px; -fx-background-color: #2196F3; -fx-text-fill: #FFFFFF;");
        MyOffersButton.setLayoutX(100); // Position it next to the Edit button
        MyOffersButton.setLayoutY(50);

        // Attach the handleInquiriesButtonAction function
        MyOffersButton.setOnAction(event -> handleOfferButtonAction(realEstate.getId()));
        // Add components to the card
        card.getChildren().addAll(imageView, titleLabel, editButton, MyOffersButton);

        return card;
    }

    @FXML
    private void handleOfferButtonAction(int propertyId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/myOffers.fxml"));
            Parent offerView = loader.load();

            MyOffersController controller = loader.getController();


            controller.setPropertyId(propertyId);


            ToDeleteArea.getChildren().setAll(offerView);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the edit page for property ID: " + propertyId);
        }
    }






    @FXML
    private void handleEditButtonAction(int propertyId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditAndDelete.fxml"));
            Parent editView = loader.load();

            DeleteAndEditController controller = loader.getController();


            controller.setPropertyId(propertyId);


            ToDeleteArea.getChildren().setAll(editView);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the edit page for property ID: " + propertyId);
        }
    }







}