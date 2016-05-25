package hu.clientbase.bean;

import hu.clientbase.bean.mv.EventBean;
import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.entity.EventType;
import hu.clientbase.entity.Note;
import hu.clientbase.service.EventService;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Ajax;

@Named("eventCUD")
@ViewScoped
public class EventCUDBean implements Serializable {

    @Inject
    private EventService eventService;

    @Inject
    private EventBean eventBean;

    private Long id;

    private EventType type;

    private Calendar dateOfStart;

    private Calendar dateOfEnd;

    private String name;

    private List<Note> notes;

    private BasicEventDTO eventToDelete;

    public void openAddDialog() {
        Ajax.oncomplete("$('#event_add_dialog').modal('show')");
    }

    public void add() {
        BasicEventDTO dto = new BasicEventDTO(type, dateOfStart, dateOfEnd, name, notes);
        eventService.create(dto);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Event added succesfully."));
        eventBean.update();
        Ajax.update("e_list_form:e_list_table");
        Ajax.oncomplete("clearAndCloseAddEventDialog(true)");
    }

    public void openEditDialog(BasicEventDTO dto) {
        id = dto.getId();
        name = dto.getName();
        dateOfStart = dto.getDateOfEnd();
        dateOfEnd = dto.getDateOfEnd();
        notes = dto.getNotes();
        Ajax.update("event_edit_form");
        Ajax.oncomplete("$('#event_edit_dialog').modal('show')");
    }

    public void edit() {
        BasicEventDTO dto = new BasicEventDTO(type, dateOfStart, dateOfEnd, name, notes);
        dto.setId(id);
        eventService.update(dto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Event edited succesfully."));
        eventBean.update();
        Ajax.update("e_list_form:e_list_table");
        Ajax.oncomplete("clearAndCloseEditEvetnDialog(true)");
    }

    public void openDeleteDialog(BasicEventDTO dto) {
        eventToDelete = dto;
        Ajax.update("event_delete_name");
        Ajax.oncomplete("$('#event_delete_dialog').modal('show')");
    }

    public void delete() {
        eventService.delete(eventToDelete);
        eventBean.update();
        Ajax.update("e_list_form:e_list_table");
        Ajax.oncomplete("$('#event_delete_dialog').modal('hide')");
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

}
