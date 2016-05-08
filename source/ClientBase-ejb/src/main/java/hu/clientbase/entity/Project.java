package hu.clientbase.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Project implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String name;

    @Temporal(TemporalType.DATE)
    @Basic
    private Calendar deadline;

    @Basic
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public Project() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDeadline() {
        return this.deadline;
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
