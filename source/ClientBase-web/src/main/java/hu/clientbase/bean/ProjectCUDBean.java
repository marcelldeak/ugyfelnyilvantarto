package hu.clientbase.bean;

import hu.clientbase.bean.mv.CustomersBean;
import hu.clientbase.bean.mv.ProjectBean;
import hu.clientbase.entity.Customer;
import hu.clientbase.dto.ProjectDTO;
import hu.clientbase.entity.ProjectStatus;
import hu.clientbase.service.ProjectService;
import java.io.Serializable;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Ajax;

@Named("ProjectCUD")
@ViewScoped
public class ProjectCUDBean implements Serializable {
    
    private static final long serialVersionUID = 258979332215257286L;
    
    @Inject
    private ProjectService ProjectService;
    
    @Inject
    private ProjectBean projectBean;
 
    @Inject
    private CustomersBean customerBean;
    
    private List<ProjectDTO> projects;
    
    private Long id;
    
    private String name;
    
    private Calendar deadline;
    
    private ProjectStatus status;
    
    private Date date;

    private ProjectDTO projectToDelete;
    
    private Date currentDate = new Date();
    
    private static final String PROJECT_LIST = "customer_details_right_panel:a_form:projects";

    public void openAddDialog() {
        Ajax.oncomplete("$('#customer_details_dialog').modal('hide');$('#project_add_dialog').modal('show')");
    }
    
    public void updateView() {
        projectBean.update();
        projects = ProjectService.getAllProjectsForCustomer(customerBean.getSelectedCustomer());
        Ajax.update(PROJECT_LIST);
    }
    
    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }


    public void openDeleteDialog(ProjectDTO dto) {
        projectToDelete = dto;
        Ajax.update("project_delete_name");
        Ajax.oncomplete("$('#customer_details_dialog').modal('hide');$('#project_delete_dialog').modal('show')");
    }
    
    public void delete() {
        ProjectService.delete(projectToDelete);
        projectBean.update();
        Ajax.update(PROJECT_LIST);
        Ajax.update("a_form:projects");
        Ajax.oncomplete("$('#project_delete_dialog').modal('hide');$('#customer_details_dialog').modal('show')");
        
    }
    
    public void add() {
        if (date == null) {
            date = Date.from(Instant.now());
        }
        deadline = dateToCalendar(date);
        ProjectService.create(new ProjectDTO(name, deadline), customerBean.getSelectedCustomer());
        ProjectDTO dto = new ProjectDTO(name, deadline, ProjectStatus.NOT_STARTED);
        projectBean.update();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Project added succesfully."));
        name = null;
        date = null;
        deadline = null;
        Ajax.update(PROJECT_LIST);
        Ajax.oncomplete("clearAndCloseAddProjectDialog(true);");
    }



    public void openEditDialog(ProjectDTO dto) {
        id = dto.getId();
        name = dto.getName();
        status = dto.getStatus();
        deadline = dto.getDeadline();
        date = Date.from(deadline.toInstant());
        Ajax.update("project_edit_form");
        Ajax.oncomplete("$('#customer_details_dialog').modal('hide');$('#project_edit_dialog').modal('show')");
    }
    
    public void edit() {
        if (date != null) {
            deadline = dateToCalendar(date);
        }
        ProjectDTO dto = new ProjectDTO(name, deadline, status);
        dto.setId(id);
        ProjectService.update(dto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Project edited succesfully."));
        projectBean.update();
        Ajax.update(PROJECT_LIST);
        Ajax.oncomplete("clearAndCloseEditProjectDialog(true);");
    }

    
    public ProjectDTO getProjectToDelete() {
        return projectToDelete;
    }
    
    public void setProjectToDelete(ProjectDTO projectToDelete) {

        this.projectToDelete = projectToDelete;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }


    public Date getCurrentDate() {
        return currentDate;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Calendar getDeadline() {
        return deadline;
    }
    
    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }
    
    public List<ProjectDTO> getProjects() {
        Ajax.update(PROJECT_LIST);
        projects = ProjectService.getAllProjectsForCustomer(customerBean.getSelectedCustomer());
        return projects;
    }
    
    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }
    
    public ProjectStatus getStatus() {
        return status;
    }
    
    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
    
    public ProjectStatus[] getProjectStatuses() {
        return ProjectStatus.values();
    }
    
    public static String getPROJECT_LIST() {
        return PROJECT_LIST;
    }
    
}
