package hu.clientbase.dto;

import hu.clientbase.entity.Event;
import hu.clientbase.entity.EventType;
import hu.clientbase.entity.Note;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import hu.clientbase.validate.ValidatorAnnotation;

@ValidatorAnnotation
public class BasicEventDTO {

    private Long id;
    @NotNull
    private EventType type;
    @NotNull
    private Date dateOfStart;
    @NotNull
    private Date dateOfEnd;
    @Size(min = 2)
    private String name;
    private List<Note> notes;

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

    }

    public BasicEventDTO(EventType type, Date dateOfStart, Date dateOfEnd, String name, List<Note> notes) {
        this.type = type;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.name = name;
        this.notes = notes;
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
}
