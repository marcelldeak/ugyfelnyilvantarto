package hu.clientbase.dto;

import hu.clientbase.entity.Address;
import java.io.Serializable;
import javax.validation.constraints.Size;
import hu.clientbase.validate.ValidatorAnnotation;

@ValidatorAnnotation
public class AddressDTO implements Serializable {

    private static final long serialVersionUID = -169417360460788582L;

    private Long id;

    @Size(min = 2)
    private String city;
    @Size(min = 2)
    private String zipCode;
    @Size(min = 2)
    private String street;
    @Size(min = 2)
    private String country;

    public AddressDTO() {
        // DTO - parameterless constructor
    }

    public AddressDTO(String city, String zipCode, String street, String country) {
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.country = country;
    }

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.city = address.getCity();
        this.zipCode = address.getZipCode();
        this.street = address.getStreet();
        this.country = address.getCountry();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressHR() {
        return city + " " + street + " (" + zipCode + " " + country + ")";
    }
}
