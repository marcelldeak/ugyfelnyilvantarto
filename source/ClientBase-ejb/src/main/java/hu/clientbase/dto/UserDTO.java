package hu.clientbase.dto;

import hu.clientbase.entity.User;
import java.util.Objects;
import javax.ejb.Stateless;

@Stateless
public class UserDTO {

    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private Boolean active;

    public UserDTO() {
        //default User constructor
    }

    public UserDTO(String email, String password, String lastName, String firstName) {
        //for registry
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.lastName = user.getPassword();
        this.firstName = user.getFirstName();
        this.active = user.isActive();
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

    @Override
    public String toString() {
        return "UserDTO{" + "email=" + email + ", password=" + password + ", lastName=" + lastName + ", firstName=" + firstName + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.email);
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
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

}
