package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import main.models.Offer;
import main.imp.OfferDAOImp;

import java.io.IOException;
import java.util.List;

public class MyOffersController {

    public BorderPane ToDeleteArea;
    public Button backButton;

    @FXML
    private TextField searchField;
    @FXML
    private TableView<Offer> receivedInquiriesTable;
    @FXML
    private TableColumn<Offer, Integer> offerIdColumn;
    @FXML
    private TableColumn<Offer, Integer> propertyIdColumn;
    @FXML
    private TableColumn<Offer, Integer> clientIdColumn;
    @FXML
    private TableColumn<Offer, String> agentIdColumn;
    @FXML
    private TableColumn<Offer, String> offerTypeColumn;
    @FXML
    private TableColumn<Offer, String> detailsColumn;
    @FXML
    private TableColumn<Offer, Double> finalPriceColumn;

    private int propertyId;
    private ObservableList<Offer> offersList = FXCollections.observableArrayList();

    private final OfferDAOImp offerService = new OfferDAOImp();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadOffers();
        backButton.setOnAction(_ -> handleBackButtonAction());

    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
        loadOffers();
    }

    private void setupTableColumns() {
        offerIdColumn.setCellValueFactory(new PropertyValueFactory<>("offerId"));
        propertyIdColumn.setCellValueFactory(new PropertyValueFactory<>("propertyId"));
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        agentIdColumn.setCellValueFactory(new PropertyValueFactory<>("agentEmail"));
        offerTypeColumn.setCellValueFactory(new PropertyValueFactory<>("offerType"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<>("details"));
        finalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
    }

    private void loadOffers() {
        if (propertyId > 0) {
            List<Offer> offers = offerService.getOffersByPropertyId(propertyId);
            offersList.setAll(offers);
            receivedInquiriesTable.setItems(offersList);
        }
    }

    @FXML
    private void handleBackButtonAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MyProperties.fxml"));
            Parent MyProperties = loader.load();
            ToDeleteArea.getChildren().setAll(MyProperties);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the edit page for property ID: " + propertyId);
        }
    }

}
