package hu.clientbase.facade;

import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

@Singleton
public class EntityFacade {
    
    @PersistenceContext(unitName = "crmPU")
    protected EntityManager entityManager;

    public EntityFacade() {
        //default 
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public <T> T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public <T> T update(T entity) {
        return entityManager.merge(entity);
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
