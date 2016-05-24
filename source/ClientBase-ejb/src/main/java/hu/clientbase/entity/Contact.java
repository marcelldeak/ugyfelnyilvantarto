package hu.clientbase.entity;

import hu.clientbase.dto.ContactDTO;
import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Contact extends Person implements Serializable {

    private static final long serialVersionUID = 3933110442996757720L;

    public Contact() {
        // Entity - parameterless constructor
    }

    public Contact(ContactDTO dto) {
        this.setFirstName(dto.getFirstName());
        this.setLastName(dto.getLastName());
    }
}
