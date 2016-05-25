package hu.clientbase.service.mv;

import hu.clientbase.dto.BasicProjectDTO;
import hu.clientbase.entity.Project;
import hu.clientbase.facade.ProjectFacade;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Stateless
@LocalBean
public class ProjectModel {

    @Inject
    private ProjectFacade cf;

    public List<BasicProjectDTO> getAllProjects() {
        List<Project> projects = cf.getAllProjects();
        List<BasicProjectDTO> ret = new LinkedList<>();

        projects.stream().forEach(p -> ret.add(new BasicProjectDTO(p)));

        return ret;
    }
}
