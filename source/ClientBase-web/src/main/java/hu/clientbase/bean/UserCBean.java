package hu.clientbase.bean;

import hu.clientbase.dto.BasicUserDTO;
import hu.clientbase.service.UserService;
import org.omnifaces.util.Ajax;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Named("user")
@ViewScoped
public class UserCBean implements Serializable {

    private static final long serialVersionUID = 3447186609881280513L;
    
    @Inject
    private UserService userManager;
    
    private String email;
    private String password;
    private String firstName;
    private String lastName;
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
    
    public void checkIfEmailExists() {
        if (userManager.isEmailExist(email)) {
            Ajax.oncomplete("ex_email_fail()", "continue_validation()");
        } else {
            Ajax.oncomplete("continue_validation()");
        }
    }
    
    public void register() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(password.getBytes());
        String b64String = Base64.getEncoder().encodeToString(digest);
        BasicUserDTO user = new BasicUserDTO(email, b64String, lastName, firstName);
        userManager.create(user);
        Ajax.oncomplete("reg_success()");
        
    }
}