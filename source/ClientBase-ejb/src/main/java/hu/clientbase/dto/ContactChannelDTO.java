package hu.clientbase.dto;

import hu.clientbase.entity.ContactChannel;
import hu.clientbase.entity.ContactChannelType;
import java.io.Serializable;

public class ContactChannelDTO implements Serializable {

    private static final long serialVersionUID = -2205985382810828270L;

    private ContactChannelType type;

    private String value;

    public ContactChannelDTO() {
        // DTO - parameterless constructor
    }

    public ContactChannelDTO(ContactChannelType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ContactChannelDTO(ContactChannel contactChannel) {
        this(contactChannel.getType(), contactChannel.getValue());
    }

    public ContactChannelType getType() {
        return type;
    }

    public void setType(ContactChannelType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
