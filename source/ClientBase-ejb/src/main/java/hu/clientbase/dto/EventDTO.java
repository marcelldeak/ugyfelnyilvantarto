package hu.clientbase.dto;

import hu.clientbase.entity.Event;
import hu.clientbase.entity.EventType;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EventDTO implements Serializable {

    private static final long serialVersionUID = -5706191305558878248L;

    private Long id;
    private EventType type;
    private Date dateOfStart;
    private Date dateOfEnd;
    private String name;
    private List<NoteDTO> notes;

    public EventDTO() {
        notes = new LinkedList<>();
    }

    public EventDTO(Event e) {
        this.id = e.getId();
        this.type = e.getType();
        this.dateOfStart = e.getDateOfStart();
        this.dateOfEnd = e.getDateOfEnd();
        this.name = e.getName();

        notes = new LinkedList<>();
        e.getNotes().stream().forEach(n -> notes.add(new NoteDTO(n)));
    }

    public EventDTO(EventType type, Date dateOfStart, Date dateOfEnd, String name) {
        this.type = type;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.name = name;
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

    public List<NoteDTO> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteDTO> notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EventDTO other = (EventDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
