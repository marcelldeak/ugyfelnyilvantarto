package hu.clientbase.dto;

import hu.clientbase.entity.Project;
import hu.clientbase.entity.ProjectStatus;
import java.util.Calendar;

public class BasicProjectDTO {

    private Long id;

    private String name;

    private Calendar deadline;
    
    private ProjectStatus status;
            
    public BasicProjectDTO() {
        // default
    }

    public BasicProjectDTO(Project p) {
        this.id = p.getId();
        this.name = p.getName();
        this.deadline=p.getDeadline();
        this.status = p.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
    
}
