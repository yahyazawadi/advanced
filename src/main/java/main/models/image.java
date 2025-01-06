package main.models;

import javax.persistence.*;

@Entity
@Table(name= "image")
public class image {
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
