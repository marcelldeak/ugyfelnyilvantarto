package hu.clientbase.entity;

import hu.clientbase.dto.ContactChannelDTO;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Contact_channel")
public class ContactChannel implements Serializable {

    private static final long serialVersionUID = 8652458704122623142L;

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    @Enumerated(EnumType.STRING)
    private ContactChannelType type;

    @Basic
    @Column(name = "contact_channel_value")
    private String value;

    public ContactChannel() {
        // Entity - parameterless constructor
    }

    public ContactChannel(ContactChannelDTO dto) {
        this.type = dto.getType();
        this.value = dto.getValue();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ContactChannelType getType() {
        return type;
    }

    public void setType(ContactChannelType type) {
        this.type = type;
    }

}
