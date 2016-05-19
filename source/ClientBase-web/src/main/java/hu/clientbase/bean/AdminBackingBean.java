package hu.clientbase.bean;

import hu.clientbase.dto.BasicUserDTO;
import hu.clientbase.service.UserService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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

    private BasicUserDTO userToAdd;

    @PostConstruct
    private void init() {
        pendingRegistrations = userService.getPendingRegistrations();
    }

    public List<BasicUserDTO> getPendingRegistrations() {
        return pendingRegistrations;
    }

    public void setPendingRegistrations(List<BasicUserDTO> pendingRegistrations) {
        this.pendingRegistrations = pendingRegistrations;
    }

    public void rejectRegistration(BasicUserDTO dto) {
        userService.deletePendingRegistration(dto);
        pendingRegistrations = userService.getPendingRegistrations();
        Ajax.update("users_form:pending_regs");
    }

    public void acceptRegistrationQuestion(BasicUserDTO dto) {
        userToAdd = dto;
        Ajax.update("accept_dialog_user_name");
        Ajax.oncomplete("$('#accept_dialog').modal('show')");
    }

    public void acceptRegistrationAsAdmin() {
        acceptRegistration("ADMIN");

    }

    public void acceptRegistrationAsUser() {
        acceptRegistration("USER");
    }

    private void acceptRegistration(String role) {
        userService.AcceptPendingRegistration(userToAdd, role);
        pendingRegistrations = userService.getPendingRegistrations();
        Ajax.update("users_form:pending_regs");
        Ajax.oncomplete("$('#accept_dialog').modal('hide')");
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public BasicUserDTO getUserToAdd() {
        return userToAdd;
    }

    public void setUserToAdd(BasicUserDTO userToAdd) {
        this.userToAdd = userToAdd;
    }

}
