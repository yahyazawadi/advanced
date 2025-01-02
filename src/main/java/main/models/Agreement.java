package main.models;

import javax.persistence.*;

@Entity
@Table(name="Agreement")
public class Agreement {
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

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getFinalPrice() {
        return FinalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        FinalPrice = finalPrice;
    }

    public String getMade_at() {
        return made_at;
    }

    public void setMade_at(String made_at) {
        this.made_at = made_at;
    }
}
