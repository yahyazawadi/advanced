package main.models;

import javax.persistence.*;

@Entity
@Table(name= "offer")
public class Offer {
    @Id
    @GeneratedValue
    @Column(name = "offerId")
    int offerId;
    @Column(name = "propertyId")
    int propertyId;
    @Column(name = "clientId")
    int clientId;
    @Column(name = "agentId")
    int agentId;
    @Column(name = "offerType")
    String offerType;
    @Column(name = "details")
    String details;
    @Column(name = "FinalPrice")
    int FinalPrice;
    @Column(name = "made_at")
    String made_at;

    public int getFinalPrice() {
        return FinalPrice;
    }

    public String getMade_at() {
        return made_at;
    }

    public String getDetails() {
        return details;
    }

    public String getOfferType() {
        return offerType;
    }

    public int getAgentId() {
        return agentId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public void setMade_at(String made_at) {
        this.made_at = made_at;
    }

    public void setFinalPrice(int finalPrice) {
        FinalPrice = finalPrice;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }
}
