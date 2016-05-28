package hu.clientbase.dto;

import hu.clientbase.entity.User;
import hu.clientbase.validate.UniqueEmailCheckValidate;
import java.io.Serializable;
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

    public UserDTO() {
        //DTO
    }

    public UserDTO(String email, String password, String lastName, String firstName) {
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.lastName = user.getLastName();
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

    public String getName() {
        return firstName + " " + lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
