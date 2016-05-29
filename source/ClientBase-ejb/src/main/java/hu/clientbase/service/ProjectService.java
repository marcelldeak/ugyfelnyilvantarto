package hu.clientbase.service;

import hu.clientbase.dto.CustomerDTO;
import hu.clientbase.dto.ProjectDTO;
import hu.clientbase.entity.Customer;
import hu.clientbase.entity.Project;
import hu.clientbase.facade.EntityFacade;
import hu.clientbase.facade.ProjectFacade;
import java.util.LinkedList;
import java.util.List;
import hu.clientbase.validate.LoggerInterceptor;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors({LoggerInterceptor.class})
public class ProjectService {

    @Inject
    private EntityFacade entityFacade;

    @Inject
    private ProjectFacade projectFacade;

    public void create(ProjectDTO projectDTO, CustomerDTO customerDTO) {
        Customer customer = entityFacade.find(Customer.class, customerDTO.getId());
        customer.getProjects().add(new Project(projectDTO));
        entityFacade.update(customer);

    }

    public void delete(ProjectDTO dto) {
        Project tempProject = entityFacade.find(Project.class, dto.getId());
        for (Customer customer : entityFacade.findAll(Customer.class)) {
            if (customer.getProjects().contains(tempProject)) {
                customer.getProjects().remove(tempProject);
                entityFacade.update(customer);
            }
        }
        entityFacade.delete(tempProject);
    }

    public void update(ProjectDTO dto) {
        Project tempProject = entityFacade.find(Project.class, dto.getId());
        tempProject.setDeadline(dto.getDeadline());
        tempProject.setName(dto.getName());
        tempProject.setStatus(dto.getStatus());

        entityFacade.update(tempProject);
    }

    public Project find(ProjectDTO dto) {
        return entityFacade.find(Project.class, dto.getId());
    }

    public List<ProjectDTO> getAllProject() {
        List<ProjectDTO> result = new LinkedList<>();

        for (Project p : projectFacade.getAllProjects()) {
            result.add(new ProjectDTO(p));
        }

        return result;
    }

    public List<ProjectDTO> getAllProjectForCustomer(CustomerDTO customer) {
        Customer tempCustomer = entityFacade.find(Customer.class, customer.getId());
        List<ProjectDTO> ret = new LinkedList<>();
        tempCustomer.getProjects().stream().forEach((project) -> {
            ret.add(new ProjectDTO(project));
        });
        return ret;
    }

    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectFacade.getAllProjects();
        List<ProjectDTO> ret = new LinkedList<>();

        projects.stream().forEach(p -> ret.add(new ProjectDTO(p)));

        return ret;
    }
}
