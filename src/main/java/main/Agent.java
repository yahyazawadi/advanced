package com.example.demotest2;

import javafx.beans.property.SimpleStringProperty;

public class Agent {

    private final SimpleStringProperty id;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty dateCreated;
    private final SimpleStringProperty role;
    private final SimpleStringProperty companyName;
    private final SimpleStringProperty companyAddress;
    private final SimpleStringProperty status;

    public Agent(String id, String firstName, String lastName, String email, String phoneNumber, String dateCreated,
                 String role, String companyName, String companyAddress, String status) {
        this.id = new SimpleStringProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.dateCreated = new SimpleStringProperty(dateCreated);
        this.role = new SimpleStringProperty(role);
        this.companyName = new SimpleStringProperty(companyName);
        this.companyAddress = new SimpleStringProperty(companyAddress);
        this.status = new SimpleStringProperty(status);
    }

    // Getters




    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getDateCreated() {
        return dateCreated.get();
    }

    public SimpleStringProperty dateCreatedProperty() {
        return dateCreated;
    }

    public String getRole() {
        return role.get();
    }

    public SimpleStringProperty roleProperty() {
        return role;
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public SimpleStringProperty companyNameProperty() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress.get();
    }

    public SimpleStringProperty companyAddressProperty() {
        return companyAddress;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress.set(companyAddress);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

}
