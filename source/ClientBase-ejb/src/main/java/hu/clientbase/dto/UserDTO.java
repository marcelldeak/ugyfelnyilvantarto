package hu.clientbase.dto;

import hu.clientbase.entity.User;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import hu.clientbase.validate.ValidatorAnnotation;

@ValidatorAnnotation
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -329046416249720809L;

    private Long id;
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    @Size(min = 6)
    private String password;
    @Size(min = 2)
    private String lastName;
    @Size(min = 2)
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
        this.password = user.getPassword();
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
