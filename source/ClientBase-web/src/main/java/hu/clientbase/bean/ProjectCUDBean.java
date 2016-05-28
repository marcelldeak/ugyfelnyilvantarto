package hu.clientbase.bean;

import hu.clientbase.bean.mv.CustomersBean;
import hu.clientbase.bean.mv.ProjectBean;
import hu.clientbase.dto.BasicProjectDTO;
import hu.clientbase.dto.CustomerDTO;
import hu.clientbase.entity.Customer;
import hu.clientbase.entity.Project;
import hu.clientbase.entity.ProjectStatus;
import hu.clientbase.facade.EntityFacade;
import hu.clientbase.service.CustomerService;
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
    
    private List<BasicProjectDTO> projects;
    
    private Long id;
    
    private String name;
    
    private Calendar deadline;
    
    private ProjectStatus status;
    
    private Date date;
    
    private BasicProjectDTO projectToDelete;
    
    private Date currentDate = new Date();
    
    private static final String PROJECT_LIST = "customer_details_right_panel:a_form:projects";
    
    public void openAddDialog() {
        Ajax.oncomplete("$('#customer_details_dialog').modal('hide');$('#project_add_dialog').modal('show')");
    }
    
    public void updateView() {
        projectBean.update();
        projects = ProjectService.getAllProjectForCustomer(customerBean.getSelectedCustomer());
        Ajax.update(PROJECT_LIST);
    }
    
    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    public void openDeleteDialog(BasicProjectDTO dto) {
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
        Customer tempCustomer;
        if (date == null) {
            date = Date.from(Instant.now());
        }
        deadline = dateToCalendar(date);
        ProjectService.create(new BasicProjectDTO(name, deadline), customerBean.getSelectedCustomer());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Project added succesfully."));
        name = null;
        date = null;
        deadline = null;
        Ajax.update(PROJECT_LIST);
        Ajax.oncomplete("clearAndCloseAddProjectDialog(true);");
    }
    
    public void openEditDialog(BasicProjectDTO dto) {
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
        BasicProjectDTO dto = new BasicProjectDTO(name, deadline, status);
        dto.setId(id);
        ProjectService.update(dto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Project edited succesfully."));
        projectBean.update();
        Ajax.update(PROJECT_LIST);
        Ajax.oncomplete("clearAndCloseEditProjectDialog(true);");
    }
    
    public BasicProjectDTO getProjectToDelete() {
        return projectToDelete;
    }
    
    public void setProjectToDelete(BasicProjectDTO projectToDelete) {
        this.projectToDelete = projectToDelete;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
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
    
    public Date getCurrentDate() {
        return currentDate;
    }
    
    public Calendar getDeadline() {
        return deadline;
    }
    
    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }
    
    public List<BasicProjectDTO> getProjects() {
        Ajax.update(PROJECT_LIST);
        projects = ProjectService.getAllProjectForCustomer(customerBean.getSelectedCustomer());
        return projects;
    }
    
    public void setProjects(List<BasicProjectDTO> projects) {
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
