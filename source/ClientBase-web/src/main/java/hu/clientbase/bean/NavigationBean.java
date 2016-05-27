package hu.clientbase.bean;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named("navigation")
@ViewScoped
public class NavigationBean implements Serializable {

    private static final long serialVersionUID = -9208264782252419485L;

    public String getMenuClass(String menuItem) {

        FacesContext context = FacesContext.getCurrentInstance();
        String[] uri = ((HttpServletRequest) context.getExternalContext().getRequest()).getRequestURI().split("/");
        if (uri[uri.length - 1].startsWith(menuItem)) {
            return "current";
        } else {
            return "";
        }

    }

}
