package hu.clientbase.facade;

import hu.clientbase.entity.Note;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class NoteFacade {
    
    @PersistenceContext(name = "crmPU")
    private EntityManager em;

    public List<Note> getAllNotes() {
        return em.createQuery("SELECT n FROM Note n", Note.class).getResultList();
    }
}
