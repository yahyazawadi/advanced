
package controllers;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.interfaces.OfferDAO;
import main.models.Offer;
import main.models.RealEstate;
import main.services.OfferDAOImp;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.ByteArrayInputStream;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class AddOfferController {
    private final OfferDAO offerDAO = new OfferDAOImp();

    @FXML
    public Label lb_PropertyId;
    @FXML
    public Label lb_ClientId;
    @FXML
    public Label lb_AgentId;
    @FXML
    public ComboBox<String> cb_OfferType;
    @FXML
    public TextArea ta_Details;
    @FXML
    private TextField tv_FinalPrice;
    @FXML
    private Label lb_offerID;
    public void seIds(Label propertyId,Label AgentId ) {
        this.lb_PropertyId = propertyId;
        this.lb_AgentId = AgentId;
        initialize();
    }

    @FXML
    public void initialize() {


                Label propertyId = lb_PropertyId;
                Label AgentId=lb_AgentId;

    }
    @FXML
    public void SaveOffer() {
        Offer offer = new Offer();
        offer.setClientId(Integer.parseInt(lb_ClientId.getText()));
        offer.setOfferId(Integer.parseInt(lb_PropertyId.getText()));
        offer.setPropertyId(Integer.parseInt(lb_offerID.getText()));
        offer.setAgentId(Integer.parseInt(lb_AgentId.getText()));
        offer.setFinalPrice(Integer.parseInt(tv_FinalPrice.getText()));
        offer.setOfferType(cb_OfferType.getValue());
        offer.setDetails(ta_Details.getText());

        // Call the save method using the instance of OfferDAOImp
        offerDAO.save(offer);

        System.out.println("Offer saved successfully!");
    }


}
