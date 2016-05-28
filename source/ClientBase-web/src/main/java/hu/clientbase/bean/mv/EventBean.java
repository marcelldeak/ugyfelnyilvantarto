package hu.clientbase.bean.mv;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.dto.UserDTO;
import hu.clientbase.service.EventService;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Ajax;

@Named("events")
@ViewScoped
public class EventBean extends AbstractBaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EventService eventService;

    private BasicEventDTO selectedEvent;

    private List<SelectItem> eventItems;

    private List<BasicEventDTO> events;

    private List<BasicEventDTO> filteredEvents;
    
    private List<UserDTO> invitableUsers;
    
    private List<UserDTO> selectedUsers;

    @Override
    public void update() {
        events = eventService.getAllEventsAsDTO();
        if (selectedEvent != null) {
            selectedEvent = events.get(events.indexOf(selectedEvent));
            invitableUsers = eventService.getNotInvitedUsers(selectedEvent);
        } 
        selectedUsers = new ArrayList<>();
    }

    public void openSelectedEventDetails(BasicEventDTO dto) {
        selectedEvent = dto;
        update();
        Ajax.update("event_details_form", "notes_form","invite");
        Ajax.oncomplete("$('#event_details_dialog').modal('show')");
    }

    public List<BasicEventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<BasicEventDTO> events) {
        this.events = events;
    }

    public List<BasicEventDTO> getFilteredEvents() {
        return filteredEvents;
    }

    public void setFilteredEvents(List<BasicEventDTO> filteredEvents) {
        this.filteredEvents = filteredEvents;
    }

    public BasicEventDTO getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(BasicEventDTO selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public List<SelectItem> getEventItems() {
        return eventItems;
    }

    public void setEventItems(List<SelectItem> eventItems) {
        this.eventItems = eventItems;
    }

    public List<UserDTO> getInvitableUsers() {
        return invitableUsers;
    }

    public void setInvitableUsers(List<UserDTO> invitableUsers) {
        this.invitableUsers = invitableUsers;
    }

    public List<UserDTO> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<UserDTO> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }
}
