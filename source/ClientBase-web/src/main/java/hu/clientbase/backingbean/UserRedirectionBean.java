package hu.clientbase.backingbean;

import hu.clientbase.entity.Role;
import hu.clientbase.service.UserService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("redirection")
public class UserRedirectionBean {

    @Inject
    UserService userService;

    private static final Logger LOG = Logger.getLogger(UserRedirectionBean.class.getName());

    public void redirectUserByRole() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        List<Role> roles = userService.getUserRolesByEmail(context.getExternalContext().getUserPrincipal().getName());

        boolean isAdmin = false;

        for (Role r : roles) {
            
            if (r.getName().toLowerCase().equals("admin")) {
                isAdmin = true;
                try {
                    context.getExternalContext().redirect(contextPath + "/pages/admin/dashboard.xhtml");
                } catch (IOException ex) {
                    context.getExternalContext().setResponseStatus(404);
                }
            }
        }
        if (!isAdmin) {
            try {
                context.getExternalContext().redirect(contextPath + "/pages/user/dashboard.xhtml");
            } catch (IOException ex) {
                context.getExternalContext().setResponseStatus(404);
            }
        }
    }

}
