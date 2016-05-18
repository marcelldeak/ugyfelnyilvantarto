package hu.clientbase.bean.mv;

import hu.clientbase.dto.BasicCustomerDTO;
import hu.clientbase.service.mdel.CustomerModel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("customerView")
@ViewScoped
public class CustomerView implements Serializable {

    private BasicCustomerDTO selectedCustomer;

    private List<BasicCustomerDTO> customers;

    @Inject
    private CustomerModel model;

    @PostConstruct
    private void init() {
        customers = model.getAllCustomers();
    }

    public BasicCustomerDTO getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(BasicCustomerDTO selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<BasicCustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<BasicCustomerDTO> customers) {
        this.customers = customers;
    }

}
