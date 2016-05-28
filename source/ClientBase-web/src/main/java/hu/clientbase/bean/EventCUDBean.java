package hu.clientbase.bean;

import hu.clientbase.bean.mv.CustomersBean;
import hu.clientbase.bean.mv.EventBean;
import hu.clientbase.dto.EventDTO;
import hu.clientbase.dto.CustomerDTO;
import hu.clientbase.dto.UserDTO;
import hu.clientbase.entity.EventType;
import hu.clientbase.entity.Note;
import hu.clientbase.service.CustomerService;
import hu.clientbase.service.EventService;
import hu.clientbase.service.UserService;
import java.io.Serializable;
import java.util.Date;
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

    private static final long serialVersionUID = -3566912365821488578L;

    @Inject
    private EventService eventService;

    @Inject
    private EventBean eventBean;

    @Inject
    private UserService userService;

    @Inject
    private CustomersBean customersBean;

    @Inject
    private CustomerService customerService;

    private Long id;

    private EventType type;

    private Date dateOfStart;

    private Date dateOfEnd;

    private String name;

    private List<Note> notes;

    private EventDTO eventToDelete;

    private final Date currentDate = new Date();

    public Date getCurrentDate() {
        return currentDate;
    }

    public void openAddDialog() {
        Ajax.oncomplete("hideShow('customer_details_dialog','event_add_dialog',true)");
    }

    public void add() {
        EventDTO eventDTO = new EventDTO(type, dateOfStart, dateOfEnd, name);
        CustomerDTO customerDTO = customersBean.getSelectedCustomer();

        customerService.addEventToCustomer(eventDTO, customerDTO);

        String userEmail = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        UserDTO user = new UserDTO();
        user = userService.getUserByEmail(userEmail);

        eventService.create(user, eventDTO);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Event added succesfully."));

        customersBean.update();
        resetFields();
        Ajax.update("customer_details_right_panel:e_list_form:e_list_table");
        Ajax.oncomplete("resetHideShow('event_add_form','event_add_dialog', 'customer_details_dialog',true)");
    }

    public void openEditDialog(EventDTO dto) {
        id = dto.getId();
        name = dto.getName();
        dateOfStart = dto.getDateOfEnd();
        dateOfEnd = dto.getDateOfEnd();
        Ajax.update("event_edit_form");
        Ajax.oncomplete("hideShow('customer_details_dialog','event_edit_dialog',true)");
    }

    public void edit() {
        EventDTO dto = new EventDTO(type, dateOfStart, dateOfEnd, name);
        dto.setId(id);

        eventService.update(dto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Event edited succesfully."));
        eventBean.update();
        Ajax.update("e_list_form:e_list_table");
        Ajax.oncomplete("resetHideShow('event_edit_form','event_edit_dialog','customer_details_dialog',true)");
    }

    public void openDeleteDialog(EventDTO dto) {
        eventToDelete = dto;
        Ajax.update("event_delete_form");
        Ajax.oncomplete("hideShow('customer_details_dialog','event_delete_dialog',true)");
    }

    public void delete() {
        CustomerDTO customerDTO = customersBean.getSelectedCustomer();
        customerService.deleteEventFromCustomer(eventToDelete, customerDTO);
        eventService.delete(eventToDelete);

        customersBean.update();

        eventBean.setSelectedEvent(null);
        eventBean.update();

        Ajax.update("customer_details_right_panel:e_list_form:e_list_table");
        Ajax.oncomplete("hideShow('event_delete_dialog','customer_details_dialog', true)");

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

    private void resetFields() {
        type = null;
        name = null;
        dateOfStart = null;
        dateOfEnd = null;
    }
}
