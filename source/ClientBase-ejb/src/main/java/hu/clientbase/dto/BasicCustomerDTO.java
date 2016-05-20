package hu.clientbase.dto;

import hu.clientbase.entity.Customer;


public class BasicCustomerDTO {
    private Long id;
    
    private String name;

    public BasicCustomerDTO() {
        // DTO
    }
    
    public BasicCustomerDTO(Customer c)
    {
        this.id = c.getId();
        this.name = c.getName();
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

}
