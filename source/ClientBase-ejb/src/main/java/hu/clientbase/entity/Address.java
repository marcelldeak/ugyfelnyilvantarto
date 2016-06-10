package hu.clientbase.entity;

import hu.clientbase.dto.AddressDTO;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = -7436338257650562954L;

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String city;

    @Basic
    @Column(name = "zip_code")
    private String zipCode;

    @Basic
    private String street;

    @Basic
    private String country;

    public Address() {
        // Entity - parameterless constructor
    }

    public Address(AddressDTO dto) {
        this.city = dto.getCity();
        this.zipCode = dto.getZipCode();
        this.street = dto.getStreet();
        this.country = dto.getCountry();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
