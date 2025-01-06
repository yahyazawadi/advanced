package main.interfaces;

import main.models.Offer;

import java.util.List;

public interface OfferDAO {
    void save(Offer offer);
    List<Offer> getAllOffer();
    Offer getOfferById(int id);
}
