package hu.clientbase.facade;

import hu.clientbase.entity.Customer;
import hu.clientbase.entity.Event;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class CustomerFacade {

    @PersistenceContext(name = "crmPU")
    private EntityManager em;

    public List<Customer> getAllCustomers() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    public Customer getCustomerForEvent(Event event) {
        return em.createQuery("SELECT c FROM Customer c WHERE :event MEMBER OF c.events", Customer.class).getSingleResult();

        /*SELECT c.name FROM crm_db.customer_event CV INNER join crm_db.customer C ON CV.customer_id = c.id 
        INNER JOIN crm_db.event E on CV.events_id = e.id WHERE E.id = 12; */
    }
}
