package hu.clientbase.facade;

import hu.clientbase.entity.PendingRegistration;
import hu.clientbase.entity.Role;
import hu.clientbase.entity.User;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
        return em.createQuery("Select r FROM Role r WHERE r.name like :name", Role.class).setParameter("name", name).getResultList().get(0);
    }

    public List<User> getUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    
    public User getUserByEmail(String email) {
        return em.createQuery("select u from User u where u.email like :email", User.class).setParameter("email", email).getSingleResult();
    }
}
