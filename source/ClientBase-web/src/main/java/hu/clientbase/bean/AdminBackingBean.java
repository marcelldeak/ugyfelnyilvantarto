package hu.clientbase.bean;

import hu.clientbase.dto.BasicUserDTO;
import hu.clientbase.service.UserService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Ajax;

@Named(value = "admin")
@ViewScoped
public class AdminBackingBean implements Serializable {

    @Inject
    private UserService userService;

    private List<BasicUserDTO> pendingRegistrations;

    private List<BasicUserDTO> administrators;
    private List<BasicUserDTO> filteredAdministrators;

    private List<BasicUserDTO> users;
    private List<BasicUserDTO> filteredUsers;

    private BasicUserDTO userToAdd;
    private BasicUserDTO userToDelete;

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

    public void rejectRegistration(BasicUserDTO dto) {
        userService.deletePendingRegistration(dto);
        updateView();
        Ajax.update("p_form:pending_regs");
    }

    public void acceptRegistrationQuestion(BasicUserDTO dto) {
        userToAdd = dto;
        Ajax.update("accept_dialog_user_name");
        Ajax.oncomplete("$('#accept_dialog').modal('show')");
    }

    public void deleteUserQuestion(BasicUserDTO dto) {
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

    public List<BasicUserDTO> getPendingRegistrations() {
        return pendingRegistrations;
    }

    public List<BasicUserDTO> getAdministrators() {
        return administrators;
    }

    public List<BasicUserDTO> getFilteredAdministrators() {
        return filteredAdministrators;
    }

    public List<BasicUserDTO> getUsers() {
        return users;
    }

    public List<BasicUserDTO> getFilteredUsers() {
        return filteredUsers;
    }

    public BasicUserDTO getUserToAdd() {
        return userToAdd;
    }

    public BasicUserDTO getUserToDelete() {
        return userToDelete;
    }

    public void setFilteredAdministrators(List<BasicUserDTO> filteredAdministrators) {
        this.filteredAdministrators = filteredAdministrators;
    }

    public void setFilteredUsers(List<BasicUserDTO> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }
}
