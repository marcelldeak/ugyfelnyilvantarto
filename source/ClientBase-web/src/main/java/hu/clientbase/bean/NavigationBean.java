package hu.clientbase.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("navigation")
@ViewScoped
public class NavigationBean implements Serializable{
    
    private String page;
    
    @PostConstruct
    public void init()
    {
        page = "artifacts/home";
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

}
