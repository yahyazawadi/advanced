package main.models;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javax.persistence.Column;
import javax.persistence.Lob;

public class PropertyDetails {
    private int id;
    @Column(name = "nameOfProperty")
    private String NameOfProperty;
    @Column(name= "country")
    private String country;
    @Column(name= "Type")
    private String Type;
    @Column(name="price")
    private Integer price;
    @Column(name="state")
    private String state;
    @Column(name = "ZipCode")
    private String zipCode;
    @Column(name = "address")
    private String address;
    @Column(name="city")
    private String city;
    @Column(name = "Description")
    private String Description;

    public int getId() {
        return id;
    }

    public String getNameOfProperty() {
        return NameOfProperty;
    }

    public String getCountry() {
        return country;
    }

    public String getType() {
        return Type;
    }

    public Integer getPrice() {
        return price;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public void setPropertyId(int id) {
        this.id = id;
    }

    public void setNameOfProperty(String nameOfProperty) {
        NameOfProperty = nameOfProperty;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }
}
