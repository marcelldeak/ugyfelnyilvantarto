package hu.clientbase.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Contact extends Person implements Serializable {

    @ManyToOne
    private Customer customer;
    
    public Contact() {

    }
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    
}
