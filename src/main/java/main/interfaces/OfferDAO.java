package main.interfaces;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.models.Offer;

import java.util.List;

public interface OfferDAO {
    public void save(Offer offer) ;
    public List<Offer> getAllOffer();


    public Offer getOfferById(Label id);

    void start(Stage primaryStage);


}
