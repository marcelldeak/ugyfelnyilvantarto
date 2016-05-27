package hu.clientbase.bean;

import hu.clientbase.bean.mv.ProjectBean;
import hu.clientbase.dto.BasicProjectDTO;
import hu.clientbase.entity.ProjectStatus;
import hu.clientbase.service.ProjectService;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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

    private Long id;

    private String name;

    private Calendar deadline;

    private ProjectStatus status;

    private Date date;

    private BasicProjectDTO projectToDelete;

    private final Date currentDate = new Date();

    public void openAddDialog() {
        Ajax.oncomplete("$('#project_add_dialog').modal('show')");
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public void openDeleteDialog(BasicProjectDTO dto) {
        projectToDelete = dto;
        Ajax.update("project_delete_name");
        Ajax.oncomplete("$('#project_delete_dialog').modal('show')");
    }

    public void delete() {
        ProjectService.delete(projectToDelete);
        projectBean.update();
        Ajax.update("a_form:projects");
        Ajax.oncomplete("$('#project_delete_dialog').modal('hide')");

    }

    public void add() {

        deadline = dateToCalendar(date);
        BasicProjectDTO dto = new BasicProjectDTO(name, deadline);

        ProjectService.create(dto);
        projectBean.update();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Project added succesfully."));
        Ajax.update("a_form:projects");
        Ajax.oncomplete("clearAndCloseAddProjectDialog(true)");
    }

    public void openEditDialog(BasicProjectDTO dto) {
        id = dto.getId();
        name = dto.getName();
        status = dto.getStatus();
        deadline = dto.getDeadline();
        Ajax.update("project_edit_form");
        Ajax.oncomplete("$('#project_edit_dialog').modal('show')");
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
        Ajax.update("a_form:projects");
        Ajax.oncomplete("clearAndCloseEditProjectDialog(true)");
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

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public ProjectStatus[] getProjectStatuses() {
        return ProjectStatus.values();
    }
}
