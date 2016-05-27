package hu.clientbase.dto;

import hu.clientbase.entity.ContactChannel;
import hu.clientbase.entity.ContactChannelType;
import java.io.Serializable;
import javax.validation.constraints.Size;
import hu.clientbase.validate.ValidatorAnnotation;

@ValidatorAnnotation
public class ContactChannelDTO implements Serializable {

    private static final long serialVersionUID = -2205985382810828270L;

    private Long id;

    private ContactChannelType type;

    @Size(min = 2)
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
        this.id = contactChannel.getId();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
