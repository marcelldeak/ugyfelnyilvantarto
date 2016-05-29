package hu.clientbase.dto;

import hu.clientbase.entity.Project;
import hu.clientbase.entity.ProjectStatus;
import java.io.Serializable;
import java.util.Calendar;

public class ProjectDTO implements Serializable,Comparable<ProjectDTO> {

    private static final long serialVersionUID = 258979332215257296L;

    private Long id;

    private String name;

    private Calendar deadline;

    private ProjectStatus status;

    public ProjectDTO() {
        // default
    }

    public ProjectDTO(Project p) {
        this.id = p.getId();
        this.name = p.getName();
        this.deadline = p.getDeadline();
        this.status = p.getStatus();
    }

    public ProjectDTO(String name, Calendar deadline) {
        if (status == null) {
            this.status = ProjectStatus.IN_PROGRESS;
        }
        this.name = name;
        this.deadline = deadline;
    }

    public ProjectDTO(String name, Calendar deadline, ProjectStatus status) {
        this.name = name;
        this.deadline = deadline;
        this.status = status;
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

    @Override
    public int compareTo(ProjectDTO p) {
    return getDeadline().compareTo(p.getDeadline());
  }
}
