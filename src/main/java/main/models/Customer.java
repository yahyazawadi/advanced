package main.models;

import javax.persistence.*;

@Entity
@Table (name = "clients")
public class Customer {
@Id
@GeneratedValue
    private int id;

@Column (name= "fullName")
    private String fullName;

@Column (name = "email")
    private String email;

    @Column (name = "phoneNumber")
    private String phoneNumber;

    @Column (name = "location")
    private String location;

    @Column (name = "propertyType")
    private String propertyType;

    @Column (name = "budget")
    private String budget;

    @Column (name = "notes")
    private String notes;


    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
