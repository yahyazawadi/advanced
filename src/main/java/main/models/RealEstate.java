package main.models;

import javax.persistence.*;

@Entity
@Table(name="realEstate")
public class RealEstate {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
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
  @Lob
    @Column(name="imag")
    private byte[] imag;

    public void setDescription(String description) {
        Description = description;
    }
    public String getDescription() {
        return Description;
    }
    public byte[] getImag() {
        return imag;
    }

    public void setImag(byte[] imag) {
        this.imag = imag;
    }

    public String getNameOfProperty() {
        return NameOfProperty;
    }

    public void setNameOfProperty(String nameOfProperty) {
        NameOfProperty = nameOfProperty;
    }
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getcity() {
        return city;
    }

    public void setcity(String city) {
        this.city = city;
    }


}
