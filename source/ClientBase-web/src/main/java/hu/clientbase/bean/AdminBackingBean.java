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

    public void rejectRegistration(Long id) {
        BasicUserDTO toDelete = new BasicUserDTO();
        toDelete.setId(id);
        userService.deletePendingRegistration(toDelete);
        pendingRegistrations = userService.getPendingRegistrations();
        Ajax.update("users_form:pending_regs");
    }
}
