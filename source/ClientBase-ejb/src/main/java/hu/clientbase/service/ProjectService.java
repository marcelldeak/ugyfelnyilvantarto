package hu.clientbase.service;

import hu.clientbase.dto.BasicProjectDTO;
import hu.clientbase.dto.CustomerDTO;
import hu.clientbase.entity.Customer;
import hu.clientbase.entity.Project;
import hu.clientbase.facade.EntityFacade;
import hu.clientbase.facade.ProjectFacade;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ProjectService {

    @Inject
    private EntityFacade entityFacade;

    @Inject
    private ProjectFacade projectFacade;

    public void create(BasicProjectDTO projectDTO, CustomerDTO customerDTO) {
        Customer customer = entityFacade.find(Customer.class, customerDTO.getId());
        customer.getProjects().add(new Project(projectDTO));
        entityFacade.update(customer);

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
        tempProject.setStatus(dto.getStatus());

        entityFacade.update(tempProject);
    }

    public Project find(BasicProjectDTO dto) {
        return entityFacade.find(Project.class, dto.getId());
    }

    public List<BasicProjectDTO> getAllProject() {
        List<BasicProjectDTO> result = new LinkedList<>();

        for (Project p : projectFacade.getAllProjects()) {
            result.add(new BasicProjectDTO(p));
        }

        return result;
    }

    public List<BasicProjectDTO> getAllProjectForCustomer(CustomerDTO customer) {
        Customer tempCustomer = entityFacade.find(Customer.class, customer.getId());

        List<BasicProjectDTO> ret = new LinkedList<>();

        tempCustomer.getProjects().stream().forEach((project) -> {
            ret.add(new BasicProjectDTO(project));
        });
        return ret;
    }

}
