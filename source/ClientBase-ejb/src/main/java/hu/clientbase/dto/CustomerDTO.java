package hu.clientbase.dto;

import hu.clientbase.entity.Customer;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import hu.clientbase.validate.ValidatorAnnotation;

@ValidatorAnnotation
public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = -6308209911161694194L;

    private Long id;
    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    private String vatNumber;

    @NotNull
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
