package hu.clientbase.entity;

import hu.clientbase.dto.ContactDTO;
import javax.persistence.Entity;
import java.io.Serializable;

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
