package hu.clientbase.bean.mv;

import hu.clientbase.dto.UserDTO;
import hu.clientbase.service.UserService;
import org.omnifaces.util.Ajax;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "admin")
@ViewScoped
public class AdminBean implements Serializable {

    private static final long serialVersionUID = 4500091024082702835L;

    @Inject
    private transient UserService userService;
    
    private List<UserDTO> pendingRegistrations;

    private List<UserDTO> administrators;
    private List<UserDTO> filteredAdministrators;

    private List<UserDTO> users;
    private List<UserDTO> filteredUsers;

    private UserDTO userToAdd;
    private UserDTO userToDelete;

    @PostConstruct
    private void init() {
        updateView();
    }

    private void updateView() {
        FacesContext context = FacesContext.getCurrentInstance();
        pendingRegistrations = userService.getPendingRegistrations();
        administrators = userService.getAdministratorsExceptOne(context.getExternalContext().getRemoteUser());
        users = userService.getUsers();
    }

    private void acceptRegistration(String role) {
        userService.acceptPendingRegistration(userToAdd, role);
        pendingRegistrations = userService.getPendingRegistrations();
        updateView();
        Ajax.update("p_form:pending_regs","a_form:administrators","u_form:users");
        Ajax.oncomplete("$('#accept_dialog').modal('hide')");
    }

    public void rejectRegistration(UserDTO dto) {
        userService.deletePendingRegistration(dto);
        updateView();
        Ajax.update("p_form:pending_regs");
    }

    public void acceptRegistrationQuestion(UserDTO dto) {
        userToAdd = dto;
        Ajax.update("accept_dialog_user_name");
        Ajax.oncomplete("$('#accept_dialog').modal('show')");
    }

    public void deleteUserQuestion(UserDTO dto) {
        userToDelete = dto;
        Ajax.update("confirmation_dialog_user_name");
        Ajax.oncomplete("$('#confirmation_dialog').modal('show')");
    }

    public void acceptRegistrationAsAdmin() {
        acceptRegistration("ADMIN");
    }

    public void acceptRegistrationAsUser() {
        acceptRegistration("USER");
    }

    public void deleteUser() {
        userService.delete(userToDelete);
        updateView();
        Ajax.update("a_form:administrators","u_form:users");
        Ajax.oncomplete("$('#confirmation_dialog').modal('hide')");
    }

    public List<UserDTO> getPendingRegistrations() {
        return pendingRegistrations;
    }

    public List<UserDTO> getAdministrators() {
        return administrators;
    }

    public List<UserDTO> getFilteredAdministrators() {
        return filteredAdministrators;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public List<UserDTO> getFilteredUsers() {
        return filteredUsers;
    }

    public UserDTO getUserToAdd() {
        return userToAdd;
    }

    public UserDTO getUserToDelete() {
        return userToDelete;
    }

    public void setFilteredAdministrators(List<UserDTO> filteredAdministrators) {
        this.filteredAdministrators = filteredAdministrators;
    }

    public void setFilteredUsers(List<UserDTO> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }
}
