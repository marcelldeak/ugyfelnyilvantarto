package hu.clientbase.bean;

import hu.clientbase.entity.Project;
import hu.clientbase.model.LazyProjectDataModel;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

@Named(value = "dtLazyView")
@ViewScoped
public class UserProjectBackingBean implements Serializable {


    private LazyDataModel<Project> lazyModel;

    private Project selectedProject;

    @PostConstruct
    public void UserProjectBackingBean() {
        lazyModel = new LazyProjectDataModel();
    }

    public LazyDataModel<Project> getLazyModel() {
        return lazyModel;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Project Selected", ((Project) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
