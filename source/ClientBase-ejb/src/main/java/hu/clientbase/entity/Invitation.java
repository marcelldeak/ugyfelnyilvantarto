package hu.clientbase.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Invitation implements Serializable {

    private static final long serialVersionUID = -6206253884461219316L;

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String description;

    @OneToOne(targetEntity = Event.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Event event;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User recipient;

    public Invitation() {
        // Entity - parameterless constructor
    }

    public Invitation(Event event, User recipient) {
        this.event = event;
        this.recipient = recipient;
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

}
