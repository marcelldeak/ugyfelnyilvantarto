package hu.clientbase.bean;

import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.dto.BasicProjectDTO;
import hu.clientbase.service.EventService;
import hu.clientbase.service.ProjectService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.omnifaces.util.Ajax;
import org.primefaces.event.SelectEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;

@ManagedBean(name = "home")
@ViewScoped
public class HomeBackingBean {

    @Inject
    private ProjectService projectService;
    
    @Inject
    private EventService eventService;
    
    private TimelineModel timeLine = new TimelineModel();

    private Integer numberOfNotifications;

    private List<BasicProjectDTO> projects;
    
    private List<BasicEventDTO> events;

    public HomeBackingBean() {
        // default constructor
    }

    @PostConstruct
    public void init() {
        projects = projectService.getAllProject();
        
        for(BasicProjectDTO project : projects){
            timeLine.add(new TimelineEvent(project.getName(), project.getDeadline().getTime()));
        }
        
        events = eventService.getAllEventsAsDTO();
        
        for(BasicEventDTO event : events){
            timeLine.add(new TimelineEvent(event.getName(),event.getDateOfStart(),event.getDateOfEnd()));
        }        
    }

    public TimelineModel getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(TimelineModel timeLine) {
        this.timeLine = timeLine;
    }

    public Integer getNumberOfNotifications() {
        return numberOfNotifications;
    }

    public void setNumberOfNotifications(Integer numberOfNotifications) {
        this.numberOfNotifications = numberOfNotifications;
    }

    public void selectEventOnTimeline(SelectEvent event) {
        
    }

    public void changeNumberOfNotifications() {
        Ajax.update("notification-list");
    }

}
