package controllers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.models.Offer;
import main.imp.OfferDAOImp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.models.RealEstate;
import main.imp.RealEstateDAOImp;


public class AddOfferController {

    public GridPane ToDeleteArea;
    private OfferDAOImp offerDAOImp = new OfferDAOImp();
    @FXML
    public Button btnSubmitOffer;
    @FXML
    private String email;
    @FXML
    public Label lb_PropertyId;
    @FXML
    public TextField tv_ClientId;
    @FXML
    public TextField ta_email;
    @FXML
    public ComboBox<String> cb_OfferType;
    @FXML
    public TextArea ta_Details;

    @FXML
    public VBox contentArea;
    @FXML
    private TextField tv_FinalPrice;
    @FXML
    private Button backButton;

    public void setEmail(String email) {
        this.email=email;
    }

    public void setPropertyId(int propertyId) {
        ta_email.setText(UserSession.getInstance().getEmail());
        this.lb_PropertyId.setText(String.valueOf(propertyId));

        initialize();
        RealEstateDAOImp realEstateDOAImp = new RealEstateDAOImp();
        String labelText = lb_PropertyId.getText();
        propertyId = Integer.parseInt(labelText.trim());
        RealEstate e = realEstateDOAImp.getRealEstateById(propertyId);
        backButton.setOnAction(event -> handleBackButton(e));
    }

    @FXML
    public void initialize() {

        btnSubmitOffer.setOnAction(event -> SaveOffer());

    }
    @FXML
    public void SaveOffer() {
        try {
            if (tv_ClientId.getText().isEmpty() || tv_FinalPrice.getText().isEmpty() || cb_OfferType.getValue() == null) {
                System.out.println("All fields are required.");
                return;
            }

            Offer offer = new Offer();
            offer.setClientId(Integer.parseInt(tv_ClientId.getText()));
            offer.setPropertyId(Integer.parseInt(lb_PropertyId.getText()));
            offer.setFinalPrice(Integer.parseInt(tv_FinalPrice.getText()));
            offer.setOfferType(cb_OfferType.getValue());
            offer.setDetails(ta_Details.getText());
            offer.setAgent_email(email);

            offerDAOImp.save(offer);

            System.out.println("Offer saved successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error saving offer: " + e.getMessage());
        }
    }


    private void handleBackButton(RealEstate realEstate) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropertyDetail.fxml"));
            Parent detailView = loader.load();


            loader.<PropertyDetailController>getController().setRealEstate(realEstate);

            ToDeleteArea.getChildren().add(detailView);

            ToDeleteArea.getChildren().setAll(detailView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
