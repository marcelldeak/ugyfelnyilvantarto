package hu.clientbase.service;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.entity.Event;
import hu.clientbase.facade.EntityFacade;
import hu.clientbase.facade.EventFacade;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EventService {

    @Inject
    private EntityFacade entityFacade;

    @Inject
    private EventFacade eventFacade;

    public void create(BasicEventDTO dto) {
        Event event = new Event(dto);

        entityFacade.create(event);
    }

    public void update(BasicEventDTO dto) {
        Event event = entityFacade.find(Event.class, dto.getId());

        event.setDateOfEnd(dto.getDateOfEnd());
        event.setDateOfStart(dto.getDateOfStart());
        event.setName(dto.getName());
        event.setNotes(dto.getNotes());
        event.setType(dto.getType());

    }

    public void delete(BasicEventDTO dto) {
        Event event = entityFacade.find(Event.class, dto.getId());
        entityFacade.delete(event);
    }

    public Event find(BasicEventDTO dto) {
        return entityFacade.find(Event.class, dto.getId());
    }

    public List<BasicEventDTO> getAllEventsAsDTO() {
        List<Event> events = eventFacade.getAllEvents();
        List<BasicEventDTO> ret = new LinkedList<>();
        events.stream().forEach(e -> ret.add(new BasicEventDTO(e)));

        return ret;
    }
}