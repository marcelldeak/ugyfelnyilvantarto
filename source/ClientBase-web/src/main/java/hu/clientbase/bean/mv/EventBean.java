package hu.clientbase.bean.mv;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.service.EventService;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
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

    @Override
    public void update() {
        events = eventService.getAllEventsAsDTO();

    }

    public void openSelectedEventDetails(BasicEventDTO dto) {
        selectedEvent = dto;
        update();
        Ajax.update("event_details_form");
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
    
}
