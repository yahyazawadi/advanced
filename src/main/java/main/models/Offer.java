package main.models;

import javax.persistence.*;

@Entity
@Table(name= "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offerId", nullable = false, unique = true)
    private int offerId;
    @Column(name = "propertyId")
     private  int propertyId;
    @Column(name = "clientId")
    private int clientId;
    @Column(name = "agentId")
    private int agentId;
    @Column(name = "offerType")
    private String offerType;
    @Column(name = "details")
    private String details;
    @Column(name = "FinalPrice")
    private int FinalPrice;

    @Column(name = "agent_email")
    private String agent_email;
    public Offer() {

    }
    public String getAgent_email() {
        return agent_email;
    }

    public void setAgent_email(String agent_email) {
        this.agent_email = agent_email;
    }

    public int getFinalPrice() {
        return FinalPrice;
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
