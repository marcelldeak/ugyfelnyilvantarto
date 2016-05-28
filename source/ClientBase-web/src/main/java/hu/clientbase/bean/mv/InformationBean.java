package hu.clientbase.bean.mv;

import hu.clientbase.dto.EventDTO;
import hu.clientbase.service.EventService;
import hu.clientbase.service.UserService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("information")
@ViewScoped
public class InformationBean implements Serializable {

    private static final long serialVersionUID = 220318079314964492L;

    @Inject
    private EventBean eventBean;

    @Inject
    private EventService eventService;

    @Inject
    private UserService userService;

    private List<EventDTO> nextEvents;

    @PostConstruct
    private void init() {
        String userEmail = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        nextEvents = eventService.getNext10EventsForUser(userService.getUserByEmail(userEmail));
    }

    public void openSelectedEventDetails(EventDTO dto) {
        eventBean.openSelectedEventDetails(dto);
    }

    public List<EventDTO> getNextEvents() {
        return nextEvents;
    }

    public void setNextEvents(List<EventDTO> nextEvents) {
        this.nextEvents = nextEvents;
    }

}
