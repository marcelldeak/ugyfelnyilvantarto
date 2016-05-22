package hu.clientbase.dto;

import hu.clientbase.entity.Customer;
import java.io.Serializable;

public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = -6308209911161694194L;

    private Long id;

    private String name;

    private String vatNumber;

    private AddressDTO address;

    public CustomerDTO() {
        // DTO - parameterless constructor
    }

    public CustomerDTO(String name, String vatNumber, AddressDTO address) {
        this.name = name;
        this.vatNumber = vatNumber;
        this.address = address;
    }
    
    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.vatNumber = customer.getVatNumber();
        this.address = new AddressDTO(customer.getAddress());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

}
