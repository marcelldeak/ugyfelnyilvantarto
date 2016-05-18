package hu.clientbase.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.omnifaces.util.Ajax;

@Named("navigation")
@ViewScoped
public class NavigationBean implements Serializable {

    private String page;

    @PostConstruct
    public void init() {
        page = "artifacts/home";
        this.update();
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
        this.update();
    }

    public String getMenuClass(String menuItem) {

        if (page.equals(menuItem)) {
            return "current";
        } else {
            return "";
        }
    }
    
    public void update()
    {
        Ajax.update("menu_list","a_page_content");
    }

}
