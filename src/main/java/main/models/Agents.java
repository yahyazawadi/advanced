package main.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
        name = "agents"
)
public class Agents {
    @Id
    @Column(
            name = "id",
            nullable = false,
            unique = true
    )
    private String id;
    @Column(
            name = "first_name"
    )
    private String firstName;
    @Column(
            name = "last_name"
    )
    private String lastName;
    @Column(
            name = "email"
    )
    private String email;
    @Column(
            name = "password"
    )
    private String password;
    @Column(
            name = "phone_number"
    )
    private String phoneNumber;
    @Column(
            name = "date_created",
            nullable = false,
            updatable = false
    )
    private String dateCreated = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
    @Column(
            name = "role"
    )
    private String role;
    @Column(
            name = "company_name"
    )
    private String companyName;
    @Column(
            name = "company_address"
    )
    private String companyAddress;

    public Agents() {
    }

    public char[] getAgent_email() {
        return this.email.toCharArray();
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        if (id != null && !id.trim().isEmpty()) {
            this.id = id.trim();
        } else {
            throw new IllegalArgumentException("ID cannot be null or empty.");
        }
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return this.companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

}
