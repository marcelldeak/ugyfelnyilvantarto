/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.clientbase.bean.mv;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.entity.Event;
import hu.clientbase.service.mdel.EventModel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named("eventView")
@ViewScoped
public class EventView implements Serializable{

    private BasicEventDTO selectedEvent;

    private List<BasicEventDTO> events;

    @Inject
    private EventModel model;

    @PostConstruct
    private void init() {
        events = model.getAllEvents();
    }

    public BasicEventDTO getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(BasicEventDTO selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public List<BasicEventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<BasicEventDTO> events) {
        this.events = events;
    }

    public EventModel getModel() {
        return model;
    }

    public void setModel(EventModel model) {
        this.model = model;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Event Selected", ((Event) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
