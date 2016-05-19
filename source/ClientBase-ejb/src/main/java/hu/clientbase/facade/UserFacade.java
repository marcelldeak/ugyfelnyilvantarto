package hu.clientbase.facade;

import hu.clientbase.entity.PendingRegistration;
import hu.clientbase.entity.Role;
import hu.clientbase.entity.User;
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

    public List<PendingRegistration> getPendingRegistrations() {
        return em.createQuery("SELECT p FROM PendingRegistration p", PendingRegistration.class).getResultList();
    }

    public PendingRegistration getPendingRegistrationByUser(User u) {
        return em.createQuery("SELECT p FROM PendingRegistration p WHERE p.user = :user", PendingRegistration.class).setParameter("user", u).getSingleResult();
    }

    public Role getRoleByName(String name) {
        return em.createQuery("Select r FROM Role r WHERE r.name like :name", Role.class).setParameter("name", name).getSingleResult();
    }

}
