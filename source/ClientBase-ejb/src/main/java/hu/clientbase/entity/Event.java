package hu.clientbase.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Column(name = "date_of_start")
    private Calendar dateOfStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Column(name = "date_of_end")
    private Calendar dateOfEnd;

    @Basic
    private String name;

    @OneToMany(targetEntity = Note.class)
    private List<Note> notes;

    @OneToOne(targetEntity = Address.class)
    private Address address;

    public Event() {
        // Entity - parameterless constructor
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

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Calendar getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Calendar dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public Calendar getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(Calendar dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }
}
