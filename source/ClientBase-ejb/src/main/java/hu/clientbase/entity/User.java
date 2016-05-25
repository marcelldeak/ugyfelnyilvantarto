package hu.clientbase.entity;

import hu.clientbase.dto.UserDTO;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Application_user")
public class User extends Person implements Serializable {

    private static final long serialVersionUID = -6418067617162502071L;

    @Basic
    private String email;

    @Basic
    private String password;

    @Basic
    private boolean active;

    @Basic
    private String picture;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    public User() {
        // Entity - parameterless constructor
    }

    public User(UserDTO user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
