package hu.clientbase.facade;

import hu.clientbase.entity.Project;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class ProjectFacade {

    @PersistenceContext(name = "crmPU")
    private EntityManager em;

    public List<Project> getAllProjects() {
        return em.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }
}
