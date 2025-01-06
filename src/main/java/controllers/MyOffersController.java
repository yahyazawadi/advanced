package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.models.Offer; // Assuming you have an Offer model
import main.imp.OfferDAOImp; // Assuming you have a service layer to fetch data

import java.util.List;

public class MyOffersController {

    // Injected via FXML
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

    // Service instance to handle data fetching (mocking or real implementation)
    private final OfferDAOImp offerService = new OfferDAOImp();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadOffers();
       // setupSearchFilter();
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
        loadOffers(); // Reload offers when property ID is set
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
        if (propertyId > 0) { // Ensure valid property ID
            List<Offer> offers = offerService.getOffersByPropertyId(propertyId);
            offersList.setAll(offers);
            receivedInquiriesTable.setItems(offersList);
        }
    }

   /* private void setupSearchFilter() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String filter = newValue.toLowerCase();
            ObservableList<Offer> filteredList = FXCollections.observableArrayList();

            for (Offer offer : offersList) {
                if (offer.matchesFilter(filter)) {
                    filteredList.add(offer);
                }
            }
            receivedInquiriesTable.setItems(filteredList);
        });
    }*/
}
