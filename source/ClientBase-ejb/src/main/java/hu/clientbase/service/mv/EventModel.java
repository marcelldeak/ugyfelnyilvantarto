package hu.clientbase.service.mv;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.entity.Event;
import hu.clientbase.facade.EventFacade;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Stateless
@LocalBean
public class EventModel {

    @Inject
    private EventFacade ef;

    public List<BasicEventDTO> getAllEvents() {
        List<Event> events = ef.getAllEvents();
        List<BasicEventDTO> ret = new LinkedList<>();

        events.stream().forEach(p -> ret.add(new BasicEventDTO(p)));

        return ret;
    }
    
    public List<String> nameOfEvents() {
        List<String> names = new ArrayList<>();
        for (Event event : ef.getAllEvents()) {
            names.add(event.getName());
        }
        return names;
    }
}