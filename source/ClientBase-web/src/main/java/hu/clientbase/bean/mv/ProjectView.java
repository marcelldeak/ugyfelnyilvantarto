package hu.clientbase.bean.mv;

import hu.clientbase.dto.BasicProjectDTO;
import hu.clientbase.entity.Project;
import hu.clientbase.service.mdel.ProjectModel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named("projectView")
@ViewScoped
public class ProjectView implements Serializable {

    private BasicProjectDTO selectedProject;

    private List<BasicProjectDTO> projects;

    private List<BasicProjectDTO> filteredProjects;

    @Inject
    private ProjectModel model;

    @PostConstruct
    private void init() {
        projects = model.getAllProjects();
    }

    public List<BasicProjectDTO> getFilteredProjects() {
        return filteredProjects;
    }

    public void setFilteredProjects(List<BasicProjectDTO> filteredProjects) {
        this.filteredProjects = filteredProjects;
    }

    public BasicProjectDTO getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(BasicProjectDTO selectedProject) {
        this.selectedProject = selectedProject;
    }

    public List<BasicProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<BasicProjectDTO> projects) {
        this.projects = projects;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Note Selected", ((Project) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
