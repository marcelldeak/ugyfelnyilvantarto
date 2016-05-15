package hu.clientbase.facade;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UserFacade {

    @PersistenceContext
    private EntityManager em;

    public Long getUserIdByUserName(String userName) {
        return em.createQuery("SELECT u.id FROM User u WHERE u.email = :email", Long.class).setParameter("email", userName).getSingleResult();
    }

}
