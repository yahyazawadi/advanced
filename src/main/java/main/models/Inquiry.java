package main.models;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inquiry")
public class Inquiry {

    @Id
    @GeneratedValue
    @Column(name = "inquiryId", nullable = false, unique = true)
    private int inquiryId;


    @Column(name = "agentId")
    private int agentId;
    @Column(name = "")
    private int clientId;
    @Column(name = "")
    private String clientName;
    @Column(name = "")
    private LocalDateTime date;
    @Column(name = "")
    private String status;
    @Column(name = "")
    private int propertyId;
    @Column(name = "")
    private String details;
    @Column(name = "")
    private BigDecimal finalPrice;
    @Column(name = "")

    // Getters and Setters
    public int getInquiryId() { return inquiryId; }
    public void setInquiryId(int inquiryId) { this.inquiryId = inquiryId; }

    public int getAgentId() { return agentId; }
    public void setAgentId(int agentId) { this.agentId = agentId; }

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getPropertyId() { return propertyId; }
    public void setPropertyId(int propertyId) { this.propertyId = propertyId; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public BigDecimal getFinalPrice() { return finalPrice; }
    public void setFinalPrice(BigDecimal finalPrice) { this.finalPrice = finalPrice; }


}
