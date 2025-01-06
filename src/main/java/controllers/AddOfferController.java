
package controllers;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.interfaces.OfferDAO;
import main.models.Offer;
import main.imp.OfferDAOImp;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class AddOfferController {
    private OfferDAOImp offerDAOImp = new OfferDAOImp();
    public Button btnSubmitOffer;
    private String email;
private  int AgentId;
    @FXML
    public Label lb_PropertyId;
    @FXML
    public TextField tv_ClientId;
    /*
    @FXML
    public Label lb_AgentId;

     */
    @FXML
    public TextField ta_email;
    @FXML
    public ComboBox<String> cb_OfferType;
    @FXML
    public TextArea ta_Details;
    @FXML
    public GridPane offerContentArea;
    @FXML
    public VBox contentArea;


    //public VBox offerContentArea;
    @FXML
    private TextField tv_FinalPrice;
    @FXML
    private Label lb_offerID;
    public void setEmail(String email) {
        //offerDAO.lb_email.setText(email);
        this.email=email;
        //this.propertyId = propertyId;// Set the email label in AddOfferController
    }

    public void setUserId(String AgentId) {
        //offerDAO.lb_AgentId.setText(userId);
        this.AgentId= Integer.parseInt(AgentId);// Set the user ID label in AddOfferController
    }
    public void setPropertyId(int propertyId) {
        ta_email.setText(UserSession.getInstance().getEmail());
        //lb_AgentId.setText(String.valueOf(UserSession.getInstance().getUserId()));
        this.lb_PropertyId.setText(String.valueOf(propertyId));
//      this.lb_email.setText(email);
  //    this.lb_AgentId.setText(String.valueOf(AgentId));
        initialize();
    }

    @FXML
    public void initialize() {

     Label propertyId = lb_PropertyId;
    // Label AgentId=lb_AgentId;
        btnSubmitOffer.setOnAction(event -> SaveOffer());
        System.out.println("inside init");
    }
    @FXML
    public void SaveOffer() {
        try {
            System.out.println("inside save offer in controller");
            if (tv_ClientId.getText().isEmpty() || tv_FinalPrice.getText().isEmpty() || cb_OfferType.getValue() == null) {
                System.out.println("All fields are required.");
                return;
            }

            Offer offer = new Offer();
            offer.setClientId(Integer.parseInt(tv_ClientId.getText()));
            offer.setPropertyId(Integer.parseInt(lb_PropertyId.getText())); // Ensure PropertyId is set
            //offer.setAgentId(this.AgentId);
            offer.setFinalPrice(Integer.parseInt(tv_FinalPrice.getText()));
            offer.setOfferType(cb_OfferType.getValue());
            offer.setDetails(ta_Details.getText());
            offer.setAgent_email(email); // Use the provided email

            offerDAOImp.save(offer);

            System.out.println("Offer saved successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error saving offer: " + e.getMessage());
        }
    }


    public GridPane getOfferContentArea() {
        return this.offerContentArea;
    }



}
