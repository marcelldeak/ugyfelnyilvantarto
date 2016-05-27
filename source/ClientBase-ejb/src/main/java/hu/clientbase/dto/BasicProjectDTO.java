package hu.clientbase.dto;

import hu.clientbase.entity.Project;
import hu.clientbase.entity.ProjectStatus;
import java.io.Serializable;
import java.util.Calendar;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import hu.clientbase.validate.ValidatorAnnotation;

@ValidatorAnnotation
public class BasicProjectDTO implements Serializable {

    private static final long serialVersionUID = 258979332215257296L;

    private Long id;

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    private Calendar deadline;

    @NotNull
    private ProjectStatus status;

    public BasicProjectDTO() {
        // default
    }

    public BasicProjectDTO(Project p) {
        this.id = p.getId();
        this.name = p.getName();
        this.deadline = p.getDeadline();
        this.status = p.getStatus();
    }

    public BasicProjectDTO(String name, Calendar deadline) {
        if (status == null) {
            this.status = ProjectStatus.IN_PROGRESS;
        }
        this.name = name;
        this.deadline = deadline;
    }

    public BasicProjectDTO(String name, Calendar deadline, ProjectStatus status) {
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

}
