package hu.clientbase.bean.mv;

import hu.clientbase.dto.BasicProjectDTO;
import hu.clientbase.entity.Project;
import hu.clientbase.service.mv.ProjectModel;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.omnifaces.util.Ajax;

@Named("projectView")
@ViewScoped
public class ProjectBean implements Serializable {

    private static final long serialVersionUID = -961683443281866021L;

    private BasicProjectDTO selectedProject;

    private List<BasicProjectDTO> projects;

    private List<BasicProjectDTO> filteredProjects;

    private String SelectedProjectDeadlineToString;

    @Inject
    private ProjectModel model;

    @PostConstruct
    private void init() {
        update();
    }

    public void update() {
        projects = model.getAllProjects();
    }

    public void openProjedtDetails(BasicProjectDTO dto) {
        selectedProject = dto;
        if (dto.getDeadline() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            SelectedProjectDeadlineToString = sdf.format(dto.getDeadline().getTime());
        }
        update();
        Ajax.update("project_details");
        Ajax.oncomplete("$('#customer_details_dialog').modal('hide');$('#project_details_dialog').modal('show')");
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

    public String getSelectedProjectDeadlineToString() {
        return SelectedProjectDeadlineToString;
    }

    public void setSelectedProjectDeadlineToString(String SelectedProjectDeadlineToString) {
        this.SelectedProjectDeadlineToString = SelectedProjectDeadlineToString;
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
