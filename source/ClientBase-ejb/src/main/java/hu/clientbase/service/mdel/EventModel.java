package hu.clientbase.service.mdel;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.entity.Event;
import hu.clientbase.facade.EventFacade;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

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
}