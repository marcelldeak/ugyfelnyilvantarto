package hu.clientbase.bean;

import hu.clientbase.service.UserService;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.slf4j.LoggerFactory;

@Named("redirection")
public class UserRedirectionBean {

    @Inject
    UserService userService;

    public void redirectUserByRole() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();

        if (context.getExternalContext().isUserInRole("ADMIN")) {
            try {
                context.getExternalContext().redirect(contextPath + "/pages/admin/");
            } catch (IOException ex) {
                LoggerFactory.getLogger(this.getClass()).error(ex.getMessage(), ex);
                context.getExternalContext().setResponseStatus(404);
            }
        } else {
            try {
                context.getExternalContext().redirect(contextPath + "/pages/user/");
            } catch (IOException ex) {
                LoggerFactory.getLogger(this.getClass()).error(ex.getMessage(), ex);
                context.getExternalContext().setResponseStatus(404);
            }
        }

    }

    public void redirectUserIfLoggedIn() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();

        if (context.getExternalContext().getRemoteUser() != null) {
            try {
                context.getExternalContext().redirect(contextPath + "/pages/");
            } catch (IOException ex) {
                LoggerFactory.getLogger(this.getClass()).error(ex.getMessage(), ex);
                context.getExternalContext().setResponseStatus(404);
            }
        }
    }

}
