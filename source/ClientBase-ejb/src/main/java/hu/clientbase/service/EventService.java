package hu.clientbase.service;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.dto.CustomerDTO;
import hu.clientbase.dto.NoteDTO;
import hu.clientbase.dto.UserDTO;
import hu.clientbase.entity.Event;
import hu.clientbase.entity.Invitation;
import hu.clientbase.entity.Note;
import hu.clientbase.entity.User;
import hu.clientbase.facade.EntityFacade;
import hu.clientbase.facade.EventFacade;
import hu.clientbase.shared.ejb.SharedEventDTO;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.persistence.Transient;

@Stateless
public class EventService {

    @Inject
    private EntityFacade entityFacade;

    @Inject
    private EventFacade eventFacade;

    @Transient
    @Inject
    private JMSContext context;

    @Transient
    @Resource(lookup = "java:/jms/topic/CreatedTopic")
    private Topic createdTopic;

    @Transient
    @Resource(lookup = "java:/jms/topic/RemovedTopic")
    private Topic removedTopic;

    public void create(UserDTO userDTO, BasicEventDTO eventDTO, CustomerDTO customerDTO) {
        try {
            Event event = new Event(eventDTO);

            User user = entityFacade.find(User.class, userDTO.getId());

            Invitation i = new Invitation(event, user);
            entityFacade.create(i);

            SharedEventDTO sharedEventDTO = new SharedEventDTO(eventDTO.getName(), eventDTO.getType().toString(), eventDTO.getDateOfStart(), eventDTO.getDateOfEnd());
            Message message = context.createObjectMessage(sharedEventDTO);
            message.setStringProperty("type", eventDTO.getType().toString());
            message.setStringProperty("customerName", "XXX");
            context.createProducer().send(createdTopic, message);

        } catch (JMSException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(BasicEventDTO dto) {
        Event event = entityFacade.find(Event.class, dto.getId());

        event.setDateOfEnd(dto.getDateOfEnd());
        event.setDateOfStart(dto.getDateOfStart());
        event.setName(dto.getName());
        event.setType(dto.getType());

    }

    public void delete(BasicEventDTO dto) {
        Event event = entityFacade.find(Event.class, dto.getId());
        entityFacade.delete(event);

        SharedEventDTO sharedEventDTO = new SharedEventDTO(dto.getName(), dto.getType().toString(), dto.getDateOfStart(), dto.getDateOfEnd());
        context.createProducer().send(removedTopic, sharedEventDTO);
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

    public List<BasicEventDTO> getNext10Events() {
        List<BasicEventDTO> ret = new LinkedList<>();

        eventFacade.getNext10Events().stream().forEach(e -> ret.add(new BasicEventDTO(e)));

        return ret;
    }

    public void addNoteToEvent(BasicEventDTO eventDTO, NoteDTO noteDTO) {
        Event event = entityFacade.find(Event.class, eventDTO.getId());

        event.getNotes().add(new Note(noteDTO));

        entityFacade.update(event);
    }
}
