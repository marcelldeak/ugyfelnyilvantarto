package hu.clientbase.facade;

import hu.clientbase.entity.Project;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class ProjectFacade {

    @PersistenceContext(name = "crmPU")
    private EntityManager em;

    public List<Project> getAllProjects() {
        return em.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }
    
    public List<Project> getAllProjectOrderedByDate(){
        return em.createQuery("select p from Project p where p.status not like 'FINISHED' order by p.deadline asc", Project.class).getResultList();
    }
}
