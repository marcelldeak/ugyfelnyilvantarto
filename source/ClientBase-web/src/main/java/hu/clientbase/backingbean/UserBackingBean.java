package hu.clientbase.backingbean;

import hu.clientbase.dto.UserDTO;
import hu.clientbase.service.UserService;
import java.awt.event.ActionEvent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Named("user")
@RequestScoped
public class UserBackingBean {

    @Inject
    private UserService userManager;

    @NotNull
    private String email;
    @NotNull
    @Size(min = 6, message = "Your password has to be at least 6 caracters long")
    private String password;
    @NotNull
    @Size(min = 2, message = "Your first name has to be at least 2 caracters long")
    private String firstName;
    @NotNull
    @Size(min = 2, message = "Your last name has to be at least 2 caracters long")
    private String lastName;

    @NotNull
    private String confirmPassword;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserBackingBean() {
    }

    public void register() {
        UserDTO user = new UserDTO();
        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("your passwords dont match");
        }
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userManager.create(user);
    }

}
