package hu.clientbase.bean.mv;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.dto.UserDTO;
import hu.clientbase.service.EventService;
import hu.clientbase.service.UserService;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Ajax;

@Named("events")
@ViewScoped
public class EventBean extends AbstractBaseBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EventService eventService;
    
    @Inject
    private UserService userService;
    
    private BasicEventDTO selectedEvent;
    
    private List<BasicEventDTO> events;
    
    private List<BasicEventDTO> filteredEvents;
    
    private List<UserDTO> invitableUsers;
    
    private List<UserDTO> selectedUsers;
    
    @Override
    public void update() {
        String userEmail = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        events = eventService.getNextEventsForUser(userService.getUserByEmail(userEmail));
       
        if (selectedEvent != null && events.contains(selectedEvent)) {
            selectedEvent = events.get(events.indexOf(selectedEvent));
            invitableUsers = eventService.getNotInvitedUsers(selectedEvent);
        }
        selectedUsers = new ArrayList<>();
    }
    
    public void openSelectedEventDetails(BasicEventDTO dto) {
        selectedEvent = dto;
        update();
        Ajax.update("event_details_form", "notes_form", "invite");
        Ajax.oncomplete("hideShow('customer_details_dialog','event_details_dialog',true)");
        
    }
    
    public void inviteUsers() {
        if (!selectedUsers.isEmpty()) {
            eventService.inviteUsers(selectedEvent, selectedUsers);
            Ajax.update("invite");
            update();
            Ajax.oncomplete("alert('Invitations sent.');");
        }
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
