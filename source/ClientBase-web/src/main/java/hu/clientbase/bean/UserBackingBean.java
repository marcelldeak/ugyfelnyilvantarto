package hu.clientbase.bean;

import hu.clientbase.dto.UserDTO;
import hu.clientbase.service.UserService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.inject.Inject;
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

    public void badPasswordError() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "wrong Passwords", "Passwords don't match"));
    }

    public void notUniqueEmailError() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "wrong Email address", "Email address already in use"));
    }

    public boolean checkIfEmailExists() {
        return userManager.existEmail(email);
    }

    public void validateEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (checkIfEmailExists()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "wrong Email address", "Email address already in use"));
        }
    }

    
    public void register() throws NoSuchAlgorithmException {
        UserDTO user = new UserDTO();
        if (!password.equals(confirmPassword) && (userManager.existEmail(getEmail()))) { // check if passwords dont match and the email address is already in use ( both ) 
            badPasswordError();
            notUniqueEmailError();
        } else if (!password.equals(confirmPassword)) {  // check if password are matching 
            badPasswordError();
        } else if (checkIfEmailExists()) { // check if email is exists in the db
            notUniqueEmailError();
        } else {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(password.getBytes());
            String b64String = Base64.getEncoder().encodeToString(digest);
            user.setPassword(b64String);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            userManager.create(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Your Account is ready.", "Your account is ready :)"));
        }
    }
}