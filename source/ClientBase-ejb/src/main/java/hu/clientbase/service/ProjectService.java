package hu.clientbase.service;

import hu.clientbase.dto.BasicProjectDTO;
import hu.clientbase.entity.Customer;
import hu.clientbase.entity.Project;
import hu.clientbase.facade.EntityFacade;
import hu.clientbase.facade.ProjectFacade;
import hu.clientbase.validate.InterceptorBinding;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ProjectService {

    @Inject
    private EntityFacade entityFacade;

    @Inject
    private ProjectFacade projectFacade;

    @InterceptorBinding
    public void create(BasicProjectDTO projectDTO) {
        Project project = new Project(projectDTO);
        entityFacade.create(project);
    }

    public void delete(BasicProjectDTO dto) {
        Project tempProject = entityFacade.find(Project.class, dto.getId());
        for (Customer customer : entityFacade.findAll(Customer.class)) {
            if (customer.getProjects().contains(tempProject)) {
                customer.getProjects().remove(tempProject);
                entityFacade.update(customer);
            }
        }
        entityFacade.delete(tempProject);
    }

    public void update(BasicProjectDTO dto) {
        Project tempProject = entityFacade.find(Project.class, dto.getId());
        tempProject.setDeadline(dto.getDeadline());
        tempProject.setName(dto.getName());
        tempProject.setStatus(tempProject.getStatus());

        entityFacade.update(tempProject);
    }

}
