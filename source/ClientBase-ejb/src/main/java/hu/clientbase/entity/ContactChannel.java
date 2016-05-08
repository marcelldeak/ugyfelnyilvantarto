package hu.clientbase.entity;

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
