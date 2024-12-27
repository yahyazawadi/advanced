package com.example.real_estate_application.models;

import javax.persistence.*;

@Entity
@Table(name="imag")
public class imag {
    @Id
    @GeneratedValue
    private int id;

    @Lob
    @Column(name="imag")
    private byte[] imag;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImag() {
        return imag;
    }

    public void setImag(byte[] imag) {
        this.imag = imag;
    }

}
