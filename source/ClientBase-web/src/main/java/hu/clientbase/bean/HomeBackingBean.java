package hu.clientbase.bean;

import hu.clientbase.dto.EventDTO;
import hu.clientbase.dto.ProjectDTO;
import hu.clientbase.service.EventService;
import hu.clientbase.service.ProjectService;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
    private List<ProjectDTO> projectsExpireThisWeek;
    
    private List<EventDTO> events;
    private List<EventDTO> notifications;

    public HomeBackingBean() {
        // default constructor
    }
    
    @PostConstruct
    public void init() {
        Calendar actualCalendar = Calendar.getInstance();
        actualCalendar.setTime(new Date());
        int i = 1;
        
       projects = projectService.getAllProjectOrderedByDate();
        for (ProjectDTO project : projects) {
            if (project.getDeadline().after(actualCalendar)) {
                timeLine.add(new TimelineEvent(project.getName(), project.getDeadline().getTime()));
                i++;
            }
            if (i > 10) {
                break;
            }
        }

        events = eventService.getAllEventOrderedByDate();
        for (EventDTO event : events) {
            if (event.getDateOfEnd().after(actualCalendar.getTime())) {
                timeLine.add(new TimelineEvent(event.getName(), event.getDateOfStart(), event.getDateOfEnd()));
                i++;
            }
            if (i > 20) {
                break;
            }
        }
        
        projectsExpireThisWeek = getProjectsExpireOnThisWeek();
        numberOfNotifications = 5;
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

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }

    public List<EventDTO> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<EventDTO> notifications) {
        this.notifications = notifications;
    }

    public List<ProjectDTO> getProjectsExpireThisWeek() {
        return projectsExpireThisWeek;
    }

    public void setProjectsExpireThisWeek(List<ProjectDTO> projectsExpireThisWeek) {
        this.projectsExpireThisWeek = projectsExpireThisWeek;
    }

    public List<EventDTO> getNNotifications() {
        Date actualDate = new Date();

        List<EventDTO> result = new LinkedList<>();

        int i = 1;
        for (EventDTO e : events) {
            if (e.getDateOfEnd().after(actualDate)) {
                result.add(e);
                i++;
            }
            if (i > numberOfNotifications) {
                break;
            }
        }
        return result;

    }

    public List<ProjectDTO> getProjectsExpireOnThisWeek() {
        List<ProjectDTO> result = new LinkedList<>();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        
        for (ProjectDTO p : projects) {
            if (p.getDeadline().get(Calendar.WEEK_OF_YEAR) == calendar.get(Calendar.WEEK_OF_YEAR)) {
                result.add(p);
            }
            if (p.getDeadline().get(Calendar.WEEK_OF_YEAR) > calendar.get(Calendar.WEEK_OF_YEAR)) {
                break;
            }
        }
        
        return result;
    }

}
