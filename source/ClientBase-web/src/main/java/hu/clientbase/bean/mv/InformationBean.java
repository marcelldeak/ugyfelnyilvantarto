package hu.clientbase.bean.mv;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.service.EventService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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

    private List<BasicEventDTO> nextEvents;

    @PostConstruct
    private void init() {
        nextEvents = eventService.getNext10Events();
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
