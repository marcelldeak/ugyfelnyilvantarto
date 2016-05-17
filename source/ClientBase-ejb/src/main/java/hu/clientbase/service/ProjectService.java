package hu.clientbase.service;

import hu.clientbase.dto.ProjectDTO;
import hu.clientbase.entity.Project;
import hu.clientbase.facade.EntityFacade;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ApplicationScoped
@ManagedBean(name = "projectService")
public class ProjectService {

    @Inject
    private EntityFacade entityManager;
    
    public Project create(ProjectDTO project) {
        Project tempProject = new Project(project); 
        return entityManager.create(tempProject);
    }

    public Project find(long id) {
        return entityManager.find(Project.class, id);
    }

    public void Delete(long id) {
        entityManager.delete(entityManager.find(Project.class, id));
    }

    public Project update(Project project, long id) {
        project.setId(id);
        entityManager.update(project);
        return project;
    }

}
