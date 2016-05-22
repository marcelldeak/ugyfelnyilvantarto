package hu.clientbase.dto;

import hu.clientbase.entity.Address;
import hu.clientbase.entity.Event;
import hu.clientbase.entity.EventType;
import hu.clientbase.entity.Note;
import java.util.Calendar;
import java.util.List;

public class BasicEventDTO {

    private Long id;
    private EventType type;
    private Calendar dateOfStart;
    private Calendar dateOfEnd;
    private String name;
    private List<Note> notes;
    private Address address;

    public BasicEventDTO() {
        //default
    }

    public BasicEventDTO(Event e) {
        this.id = e.getId();
        this.type = e.getType();
        this.dateOfStart = e.getDateOfStart();
        this.dateOfEnd = e.getDateOfEnd();
        this.name = e.getName();
        this.notes = e.getNotes();
        this.address = e.getAddress();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
