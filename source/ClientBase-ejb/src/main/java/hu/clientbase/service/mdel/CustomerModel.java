package hu.clientbase.service.mdel;

import hu.clientbase.dto.CustomerDTO;
import hu.clientbase.entity.Customer;
import hu.clientbase.facade.CustomerFacade;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@LocalBean
public class CustomerModel {

    @Inject
    private CustomerFacade cf;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = cf.getAllCustomers();
        List<CustomerDTO> ret = new LinkedList<>();

        customers.stream().forEach(c -> ret.add(new CustomerDTO(c)));

        return ret;
    }
}
