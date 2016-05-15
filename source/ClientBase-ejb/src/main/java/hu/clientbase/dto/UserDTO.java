package hu.clientbase.dto;

import hu.clientbase.entity.ContactChannel;
import hu.clientbase.entity.Event;
import hu.clientbase.entity.Role;
import hu.clientbase.entity.User;
import java.util.Calendar;
import java.util.List;

public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;
    
    private String picture;

    private String email;
    
    private String password;

    private boolean active;

    private Calendar expirationDate;

    private Calendar dateOfBirth;

    private List<Event> events;

    private List<Role> roles;
    
    private List<ContactChannel> contactChannels;

    public UserDTO() {
        // default constructor
    }
    
    public UserDTO(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.picture = user.getPicture();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.expirationDate = user.getExpirationDate();
        this.dateOfBirth = user.getDateOfBirth();
        
        this.events = user.getEvents();
        this.roles = user.getRoles();
        this.contactChannels = user.getContactChannels();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<ContactChannel> getContactChannels() {
        return contactChannels;
    }

    public void setContactChannels(List<ContactChannel> contactChannels) {
        this.contactChannels = contactChannels;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", picture=" + picture + ", email=" + email + ", password=" + password + ", active=" + active + ", expirationDate=" + expirationDate + ", dateOfBirth=" + dateOfBirth + '}';
    }
    
}
