package hu.clientbase.bean.mv;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.service.EventService;
import hu.clientbase.service.UserService;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
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

    private List<BasicEventDTO> nextEvents;

    @PostConstruct
    private void init() {
        String userEmail = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        try {
            nextEvents = eventService.getNext10EventsForUser(userService.getUserByEmail(userEmail));
        } catch (NoSuchAlgorithmException ex) {
            FacesContext.getCurrentInstance().getExternalContext().setResponseStatus(404);
        }
    }

    public void openSelectedEventDetails(BasicEventDTO dto) {
        eventBean.openSelectedEventDetails(dto);
    }

    public List<BasicEventDTO> getNextEvents() {
        return nextEvents;
    }

    public void setNextEvents(List<BasicEventDTO> nextEvents) {
        this.nextEvents = nextEvents;
    }

}
