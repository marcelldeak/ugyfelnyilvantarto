package hu.clientbase.bean;

import hu.clientbase.service.UserService;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("redirection")
@RequestScoped
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
                context.getExternalContext().setResponseStatus(404);
            }
        } else {
            try {
                context.getExternalContext().redirect(contextPath + "/pages/user/");
            } catch (IOException ex) {
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
                context.getExternalContext().setResponseStatus(404);
            }
        }
    }

}