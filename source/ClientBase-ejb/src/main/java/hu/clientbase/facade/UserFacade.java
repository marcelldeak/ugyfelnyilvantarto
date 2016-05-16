package hu.clientbase.facade;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UserFacade {

    @PersistenceContext
    private EntityManager em;

   

}
