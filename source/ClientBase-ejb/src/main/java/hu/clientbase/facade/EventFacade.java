package hu.clientbase.facade;

import hu.clientbase.entity.Event;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class EventFacade {
        @PersistenceContext(name = "crmPU")
    private EntityManager em;

    public List<Event> getAllEvents() {
        return em.createQuery("SELECT n FROM Event n", Event.class).getResultList();
    }
}