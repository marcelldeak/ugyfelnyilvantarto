package hu.clientbase.service;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.dto.NoteDTO;
import hu.clientbase.dto.UserDTO;
import hu.clientbase.entity.Event;
import hu.clientbase.entity.Invitation;
import hu.clientbase.entity.Note;
import hu.clientbase.entity.User;
import hu.clientbase.facade.EntityFacade;
import hu.clientbase.facade.EventFacade;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EventService {
    
    @Inject
    private EntityFacade entityFacade;
    
    @Inject
    private EventFacade eventFacade;
    
    @Inject
    private UserService userService;
    
    public void create(UserDTO userDTO, BasicEventDTO eventDTO) {
        Event event = new Event(eventDTO);
        
        User user = entityFacade.find(User.class, userDTO.getId());
        Invitation i = new Invitation(event, user);
        
        entityFacade.create(i);
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
        eventFacade.deleteInvitationsForEventByEventId(dto.getId());
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
    
    public List<UserDTO> getNotInvitedUsers(BasicEventDTO dto) {
        List invitedUsersList = new LinkedList<>();
        eventFacade.getInvitedUsersForEventByEventId(dto.getId()).stream().forEach(u -> invitedUsersList.add(new UserDTO(u)));
        
        Set<UserDTO> users = new HashSet<>(userService.getUsers());
        Set<UserDTO> invitedUsers = new HashSet<>(invitedUsersList);
        
        users.removeAll(invitedUsers);
        
        List<UserDTO> ret = new LinkedList<>();
        
        ret.addAll(users);
        
        return ret;
    }
    
    public void inviteUsers(BasicEventDTO eventDTO, List<UserDTO> userDTOs)
    {
        Event event = entityFacade.find(Event.class, eventDTO.getId());
        
        userDTOs.stream().map((u) -> entityFacade.find(User.class, u.getId())).map((user) -> new Invitation(event, user)).forEach((i) -> {
            entityFacade.create(i);
        });
    }
}
