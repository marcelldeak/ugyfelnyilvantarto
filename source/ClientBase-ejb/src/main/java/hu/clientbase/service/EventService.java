package hu.clientbase.service;

import hu.clientbase.dto.EventDTO;
import hu.clientbase.dto.NoteDTO;
import hu.clientbase.dto.UserDTO;
import hu.clientbase.entity.Event;
import hu.clientbase.entity.Invitation;
import hu.clientbase.entity.Note;
import hu.clientbase.entity.User;
import hu.clientbase.facade.EntityFacade;
import hu.clientbase.facade.EventFacade;
import java.util.ArrayList;
import java.util.HashSet;
import hu.clientbase.validate.LoggerInterceptor;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors({LoggerInterceptor.class})
public class EventService {

    @Inject
    private EntityFacade entityFacade;

    @Inject
    private EventFacade eventFacade;


    @Inject
    private UserService userService;

    public void create(UserDTO userDTO, EventDTO eventDTO) {
        Event event = new Event(eventDTO);

        User user = entityFacade.find(User.class, userDTO.getId());
        Invitation i = new Invitation(event, user);
        entityFacade.create(i);
    }

    public void update(EventDTO dto) {

        Event event = entityFacade.find(Event.class, dto.getId());

        event.setDateOfEnd(dto.getDateOfEnd());
        event.setDateOfStart(dto.getDateOfStart());
        event.setName(dto.getName());
        event.setType(dto.getType());
    }

    public void delete(EventDTO dto) {
        Event event = entityFacade.find(Event.class, dto.getId());
        eventFacade.deleteInvitationsForEventByEventId(dto.getId());
        entityFacade.delete(event);

    }

    public Event find(EventDTO dto) {
        return entityFacade.find(Event.class, dto.getId());
    }

    public List<EventDTO> getAllEventsAsDTO() {
        List<Event> events = eventFacade.getAllEvents();
        List<EventDTO> ret = new LinkedList<>();
        events.stream().forEach(e -> ret.add(new EventDTO(e)));

        return ret;
    }

    public List<EventDTO> getNext10EventsForUser(UserDTO dto) {
        List<EventDTO> ret = new LinkedList<>();

        eventFacade.getNext10EventsForUserByUserId(dto.getId()).stream().forEach(e -> ret.add(new EventDTO(e)));

        return ret;
    }

    public List<EventDTO> getNextEventsForUser(UserDTO dto) {
        List<EventDTO> ret = new LinkedList<>();

        eventFacade.getNextEventsForUserByUserId(dto.getId()).stream().forEach(e -> ret.add(new EventDTO(e)));

        return ret;
    }

    public List<EventDTO> getNext10Events() {
        List<EventDTO> ret = new LinkedList<>();

        return ret;
    }

    public void addNoteToEvent(EventDTO eventDTO, NoteDTO noteDTO) {
        Event event = entityFacade.find(Event.class, eventDTO.getId());

        event.getNotes().add(new Note(noteDTO));

        entityFacade.update(event);
    }

    public List<UserDTO> getNotInvitedUsers(EventDTO dto) {
        List invitedUsersList = new LinkedList<>();
        eventFacade.getInvitedUsersForEventByEventId(dto.getId()).stream().forEach(u -> invitedUsersList.add(new UserDTO(u)));

        Set<UserDTO> users = new HashSet<>(userService.getUsers());
        Set<UserDTO> invitedUsers = new HashSet<>(invitedUsersList);

        users.removeAll(invitedUsers);

        List<UserDTO> ret = new LinkedList<>();

        ret.addAll(users);

        return ret;
    }

    public void inviteUsers(EventDTO eventDTO, List<UserDTO> userDTOs) {
        Event event = entityFacade.find(Event.class, eventDTO.getId());

        userDTOs.stream().map((u) -> entityFacade.find(User.class, u.getId())).map((user) -> new Invitation(event, user)).forEach((i) -> {
            entityFacade.create(i);
        });
    }
    
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventFacade.getAllEvents();
        List<EventDTO> ret = new LinkedList<>();

        events.stream().forEach(p -> ret.add(new EventDTO(p)));

        return ret;
    }
    
    public List<EventDTO> getAllEventOrderedByDate(){
        List<EventDTO> result = new LinkedList<>();
        
        for(Event e : eventFacade.getAllEventOrderedByDate()){
            result.add(new EventDTO(e));
        }
        
        return result;
    }
    
    public List<String> nameOfEvents() {
        List<String> names = new ArrayList<>();
        for (Event event : eventFacade.getAllEvents()) {
            names.add(event.getName());
        }
        return names;
    }
}
