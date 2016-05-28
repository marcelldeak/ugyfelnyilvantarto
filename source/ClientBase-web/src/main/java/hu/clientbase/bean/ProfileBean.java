package hu.clientbase.bean;

import hu.clientbase.dto.UserDTO;
import hu.clientbase.service.UserService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.omnifaces.util.Ajax;
import org.primefaces.model.UploadedFile;

@Named("profile")
@ViewScoped
public class ProfileBean implements Serializable {

    private static final long serialVersionUID = -6749396864436794339L;

    @Inject
    private UserService userService;

    private Long id;
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private Date dateOfBirth;
    private String picture;

    private String confirmPassword;

    private UploadedFile upFile;


    @PostConstruct
    private void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        String eMail = context.getExternalContext().getRemoteUser();
        UserDTO user = userService.getUserByEmail(eMail);
       
        id = user.getId();
        email = user.getEmail();
        password = user.getPassword();
        lastName = user.getLastName();
        firstName = user.getFirstName();
        dateOfBirth = user.getDateOfBirth().getTime();
        picture = user.getPicture();

        confirmPassword = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UploadedFile getUpFile() {
        return upFile;
    }

    public void setUpFile(UploadedFile upFile) {
        this.upFile = upFile;
    }

    public void uploadImage() throws NoSuchAlgorithmException {
        String filename = email.replaceAll("(\\.|@)", "_");
        String extension = ".";

        switch (upFile.getContentType()) {
            case "image/jpeg":
                extension = extension.concat("jpg");
                break;
            case "image/png":
                extension = extension.concat("png");
                break;
            default:
                break;
        }

        picture = filename + extension;

        Path folder = Paths.get(System.getProperty("jboss.server.data.dir"), "Clientbase", "profile_images");
        File file = new File(folder.toString() + File.separatorChar + picture);
        file.mkdirs();

        try (InputStream fileInput = upFile.getInputstream()) {
            Files.copy(fileInput, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().getExternalContext().setResponseStatus(404);
        }

        userService.updatePicture(id, picture);

        init();
    }

    public void saveChanges() throws NoSuchAlgorithmException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfBirth);
        UserDTO user = new UserDTO(id, email, password, lastName, firstName, Boolean.TRUE, calendar, picture);
        userService.update(user);

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(email, password);
        } catch (ServletException ex) {
            context.getExternalContext().setResponseStatus(404);
        }

        init();

        Ajax.updateAll();
    }

    public String getProfileImgPath() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = request.getContextPath();

        String fileURL = Paths.get(System.getProperty("jboss.server.data.dir"), "Clientbase", "profile_images", picture)
                .toUri().toString();

        if (!picture.equals("null") && picture != null) {
            return fileURL;
        } else {
            return contextPath + "/resources/imgs/facebook-avatar.jpg";
        }

    }

}
