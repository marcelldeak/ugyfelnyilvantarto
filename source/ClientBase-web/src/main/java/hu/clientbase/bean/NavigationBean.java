package hu.clientbase.bean;

import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("navigation")
@ViewScoped
public class NavigationBean implements Serializable {

    public String getMenuClass(String menuItem) {

        FacesContext context = FacesContext.getCurrentInstance();
        String uri[] = ((HttpServletRequest) context.getExternalContext().getRequest()).getRequestURI().split("/");
        if (uri[uri.length - 1].startsWith(menuItem)) {
            return "current";
        } else {
            return "";
        }

    }

}
