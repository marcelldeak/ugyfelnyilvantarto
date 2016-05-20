package hu.clientbase.bean.mv;

import hu.clientbase.dto.BasicCustomerDTO;
import hu.clientbase.entity.Customer;
import hu.clientbase.entity.Project;
import hu.clientbase.service.mdel.CustomerModel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

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
      public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Customer Selected", ((Customer) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
