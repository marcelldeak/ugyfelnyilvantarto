package hu.clientbase.facade;

import hu.clientbase.entity.Event;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class EventFacade {

    @PersistenceContext(name = "crmPU")
    private EntityManager em;

    public List<Event> getAllEvents() {
        return em.createQuery("SELECT n FROM Event n", Event.class).getResultList();

    }

    public List<Event> getNext10Events() {
        return em.createQuery("SELECT e FROM Event e WHERE e.dateOfStart >= CURRENT_TIMESTAMP ORDER BY e.dateOfStart", Event.class).setMaxResults(10).getResultList();
    }

}
