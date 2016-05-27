package hu.clientbase.entity;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.facade.CustomerFacade;
import hu.clientbase.shared.ejb.SharedEventDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Event implements Serializable {
    
    private static final long serialVersionUID = 2480370132530374980L;
    
    @Transient
    @Inject
    private CustomerFacade customerFacade;

    @Transient
    @Inject
    private JMSContext context;

    @Transient
    @Resource(lookup = "java:/jms/topic/CreatedTopic")
    private Topic createdTopic;

    @Transient
    @Resource(lookup = "java:/jms/topic/RemovedTopic")
    private Topic removedTopic;

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Column(name = "date_of_start")
    private Date dateOfStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Column(name = "date_of_end")
    private Date dateOfEnd;

    @Basic
    private String name;

    @OneToMany(targetEntity = Note.class,fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<Note> notes;

    public Event() {
        // Entity - parameterless constructor
    }

    public Event(BasicEventDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.dateOfEnd = dto.getDateOfEnd();
        this.dateOfStart = dto.getDateOfStart();
        this.type = dto.getType();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Note> getNotes() {
        return this.notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public Date getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    @PostPersist
    public void sendEventToCreatedTopic() {
        try {
            SharedEventDTO sharedEventDTO = new SharedEventDTO(type.toString(),
                    dateOfStart, dateOfEnd, name);

            Message message = context.createObjectMessage(sharedEventDTO);
            Customer customer = customerFacade.getCustomerForEvent(this);
            message.setStringProperty("Type", type.toString());
            message.setStringProperty("Customer name", customer==null?"":customer.getName());

            context.createProducer().send(createdTopic, message);
        } catch (JMSException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PostRemove
    public void sendEventToRemovedTopic() {
        SharedEventDTO sharedEventDTO = new SharedEventDTO(type.toString(), dateOfStart, dateOfEnd, name);
        context.createProducer().send(removedTopic, sharedEventDTO);
    }
}
