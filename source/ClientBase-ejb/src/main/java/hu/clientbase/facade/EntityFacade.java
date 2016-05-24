package hu.clientbase.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public class EntityFacade {

    @PersistenceContext(unitName = "crmPU")
    private EntityManager entityManager;

   
    public <T> void create(T entity) {
        entityManager.persist(entity);
    }

    public <T> void update(T entity) {
        entityManager.merge(entity);
    }

    public <T> void delete(T entity) {
        entityManager.remove(entity);
    }

    public <T> T find(Class<T> clazz, Object id) {
        return entityManager.find(clazz, id);
    }

    public <T> List<T> findAll(Class<T> entityClass) {
        CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return entityManager.createQuery(cq).getResultList();
    }

}
