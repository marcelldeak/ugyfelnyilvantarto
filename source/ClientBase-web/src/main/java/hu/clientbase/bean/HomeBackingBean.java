package hu.clientbase.bean;

import hu.clientbase.dto.EventDTO;
import hu.clientbase.dto.ProjectDTO;
import hu.clientbase.service.EventService;
import hu.clientbase.service.ProjectService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Ajax;
import org.primefaces.event.SelectEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;

@Named("home")
@ViewScoped
public class HomeBackingBean implements Serializable {

    private static final long serialVersionUID = 4900878242567290991L;

    @Inject
    private ProjectService projectService;
    
    @Inject
    private EventService eventService;
    
    private TimelineModel timeLine = new TimelineModel();

    private Integer numberOfNotifications;

    private List<ProjectDTO> projects;
    
    private List<EventDTO> events;


    @PostConstruct
    public void init() {
        projects = projectService.getAllProject();
        
        for(ProjectDTO project : projects){
            timeLine.add(new TimelineEvent(project.getName(), project.getDeadline().getTime()));
        }
        
        events = eventService.getAllEventsAsDTO();
        
        for(EventDTO event : events){
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
