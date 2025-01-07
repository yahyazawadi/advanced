package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.models.RealEstate;
import main.imp.RealEstateDAOImp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class MyPropertiesController {
    @FXML
    public AnchorPane ToDeleteArea;

    private RealEstateDAOImp realEstateDAO = new RealEstateDAOImp();
    @FXML
    private TextField searchField;
    @FXML
    private GridPane contentArea;
    @FXML
    public void initialize() {

        contentArea.setHgap(20);
        contentArea.setVgap(20);
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


    private void displayRealEstateCards() {
        List<RealEstate> realEstates = realEstateDAO.getAllRealEstates();

        contentArea.getChildren().clear();

        int row = 0;
        int col = 0;

        for (RealEstate realEstate : realEstates) {
            AnchorPane card = createCard(realEstate);

            contentArea.add(card, col, row);

            col++;
            if (col == 3) {
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

        Label titleLabel = new Label(realEstate.getNameOfProperty());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        titleLabel.setLayoutX(10);
        titleLabel.setLayoutY(imageView.getLayoutY() + imageView.getFitHeight() + 10);

        Button editButton = new Button("Edit");
        editButton.setStyle("-fx-font-size: 14px; -fx-background-color: #009688; -fx-text-fill: #FFFFFF;");
        editButton.setLayoutX(10);
        editButton.setLayoutY(300);

        editButton.setOnAction(event -> handleEditButtonAction(realEstate.getId()));

        Button MyOffersButton = new Button("Offers");
        MyOffersButton.setStyle("-fx-font-size: 14px; -fx-background-color: #2196F3; -fx-text-fill: #FFFFFF;");
        MyOffersButton.setLayoutX(100);
        MyOffersButton.setLayoutY(300);

        Label priceLabel = new Label("Price: $" + realEstate.getPrice());
        priceLabel.setStyle("-fx-text-fill: #009688; -fx-font-size: 14px; -fx-font-weight: bold;");
        priceLabel.setLayoutX(10);
        priceLabel.setLayoutY(titleLabel.getLayoutY() + 50);
        MyOffersButton.setOnAction(event -> handleOfferButtonAction(realEstate.getId()));
        card.getChildren().addAll(imageView,priceLabel , titleLabel, editButton, MyOffersButton);

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
            EditAndDeleteController controller = loader.getController();
            controller.setPropertyId(propertyId);
            ToDeleteArea.getChildren().setAll(editView);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the edit page for property ID: " + propertyId);
        }
    }







}