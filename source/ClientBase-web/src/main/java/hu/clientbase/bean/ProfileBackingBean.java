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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.omnifaces.util.Ajax;
import org.omnifaces.util.Faces;
import org.primefaces.model.UploadedFile;

@Named("profile")
@ViewScoped
public class ProfileBackingBean implements Serializable {

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

    private String newPassword;
    private String confirmPassword;

    private UploadedFile upFile;

    private Date minBirthDate;
    private Date maxBirthDate;

    public ProfileBackingBean() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.set(calendar.get(Calendar.YEAR) - 18, calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        maxBirthDate = calendar.getTime();

        calendar.set(calendar.get(Calendar.YEAR) - 70, 0, 0);
        minBirthDate = calendar.getTime();
    }

    @PostConstruct
    public void init() {
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

        newPassword = "";
        confirmPassword = "";
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    public Date getMinBirthDate() {
        return minBirthDate;
    }

    public void setMinBirthDate(Date minBirthDate) {
        this.minBirthDate = minBirthDate;
    }

    public Date getMaxBirthDate() {
        return maxBirthDate;
    }

    public void setMaxBirthDate(Date maxBirthDate) {
        this.maxBirthDate = maxBirthDate;
    }

    public void uploadImage() throws NoSuchAlgorithmException {
        String filename = email.replaceAll("(\\.|@)", "_");
        String extension = ".";

        if (upFile.getContentType().equals("image/jpeg") || upFile.getContentType().equals("image/png")) {

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

            Path folder = Paths.get(Faces.getServletContext().getRealPath(""), "resources", "profile_images");

            File file = new File(folder.toString() + File.separatorChar + picture);
            file.mkdirs();

            try (InputStream fileInput = upFile.getInputstream()) {
                Files.copy(fileInput, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                FacesContext.getCurrentInstance().getExternalContext().setResponseStatus(404);
            }

            userService.updatePicture(id, picture);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong file extension. Only png, jpg or jpeg"));
        }
        init();
    }

    public void saveChanges() throws NoSuchAlgorithmException, IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfBirth);
        if (this.newPassword != null) {
            UserDTO user = new UserDTO(id, email, newPassword, lastName, firstName, Boolean.TRUE, calendar, picture);
            userService.update(user);
        } else {
            UserDTO user = new UserDTO(id, email, password, lastName, firstName, Boolean.TRUE, calendar, picture);
            userService.update(user);
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Changes saved succesfully."));
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            request.login(email, password);
        } catch (ServletException ex) {
            FacesContext.getCurrentInstance().getExternalContext().setResponseStatus(404);
        }

        init();

    }

    public String getProfileImgPath() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = request.getContextPath();

        String fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

        if (!picture.equals("null") && picture != null) {
            return fileUrl + "/resources/profile_images/" + picture;
        } else {
            return fileUrl + "/resources/profile_images/" + "avatar.jpg";
        }

    }

}
