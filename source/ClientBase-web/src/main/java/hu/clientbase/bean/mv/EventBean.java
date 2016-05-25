package hu.clientbase.bean.mv;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.service.mv.EventModel;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


@Named("events")
@ViewScoped
public class EventBean implements Serializable {

    @Inject
    private EventModel model;

    private BasicEventDTO selectedEvent;

    private List<SelectItem> eventItems;

    private List<BasicEventDTO> events;

    private void updateModel() {
        events = model.getAllEvents();
        eventItems = new LinkedList<>();
        events.stream().forEach((e) -> eventItems.add(new SelectItem(e, e.getName())));
    }

    private void updateView() {
        return;
    }

    @PostConstruct
    private void init() {
        updateModel();
        updateView();
    }

    public BasicEventDTO getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(BasicEventDTO selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public List<SelectItem> getEventItems() {
        return eventItems;
    }

    public void setEventItems(List<SelectItem> eventItems) {
        this.eventItems = eventItems;
    }

    public List<BasicEventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<BasicEventDTO> events) {
        this.events = events;
    }
}
