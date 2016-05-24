package hu.clientbase.dto;

import hu.clientbase.entity.Contact;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ContactDTO implements Serializable {

    private static final long serialVersionUID = 1154329184668216510L;

    private Long id;

    private String firstName;

    private String lastName;

    private List<ContactChannelDTO> contactChannels;

    public ContactDTO() {
        contactChannels = new LinkedList<>();
    }

    public ContactDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        contactChannels = new LinkedList<>();
    }

    public ContactDTO(Contact contact) {
        this(contact.getFirstName(), contact.getLastName());
        this.id = contact.getId();
        contactChannels = new LinkedList<>();
        contact.getContactChannels().stream().forEach(c -> contactChannels.add(new ContactChannelDTO(c)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ContactChannelDTO> getContactChannels() {
        return contactChannels;
    }

    public void setContactChannels(List<ContactChannelDTO> contactChannels) {
        this.contactChannels = contactChannels;
    }
    
    public String getNameHR()
    {
        return firstName + " " + lastName;
    }

}
