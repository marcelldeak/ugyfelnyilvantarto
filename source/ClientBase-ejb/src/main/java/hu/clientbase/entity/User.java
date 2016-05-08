package hu.clientbase.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Application_user")
public class User extends Person implements Serializable {

    @Basic
    private String password;

    @Basic
    private boolean active;

    @Temporal(TemporalType.DATE)
    @Basic
    @Column(name = "expiration_date")
    private Calendar expirationDate;

    @Temporal(TemporalType.DATE)
    @Basic
    @Column(name = "date_of_birth")
    private Calendar dateOfBirth;

    @ManyToMany(targetEntity = Event.class)
    private List<Event> events;

    @OneToMany(targetEntity = Role.class)
    private List<Role> roles;

    public User() {
        // Entity - parameterless constructor
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Calendar getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
