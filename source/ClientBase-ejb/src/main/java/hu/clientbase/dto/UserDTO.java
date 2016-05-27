package hu.clientbase.dto;

import hu.clientbase.entity.User;
import java.io.Serializable;
import java.util.Calendar;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = -329046416249720809L;

    private Long id;
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private Boolean active;
    private Calendar dateOfBirth;
    private String picture;

    public UserDTO() {
        //DTO
    }

    public UserDTO(String email, String password, String lastName, String firstName) {
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public UserDTO(Long id,String email, String password, String lastName, String firstName, Boolean active, Calendar dateOfBirth, String picture) {
        this(email,password,lastName,firstName);
        this.id = id;
        this.active = active;
        this.dateOfBirth = dateOfBirth;
        this.picture = picture;
    }
    
    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.active = user.isActive();
        this.dateOfBirth = user.getDateOfBirth();
        this.picture = user.getPicture();
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    
}
