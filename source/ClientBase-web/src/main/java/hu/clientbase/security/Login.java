package hu.clientbase.security;

import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "login")
public class Login {

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        return "/login";
    }
}
