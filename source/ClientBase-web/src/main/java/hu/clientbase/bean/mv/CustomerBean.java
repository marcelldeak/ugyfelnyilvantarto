package hu.clientbase.bean.mv;

import hu.clientbase.dto.CustomerDTO;
import hu.clientbase.service.mdel.CustomerModel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Ajax;

@Named("customers")
@ViewScoped
public class CustomerBean implements Serializable {

    private static final long serialVersionUID = -961683443281866011L;

    @Inject
    private CustomerModel model;

    private CustomerDTO selectedCustomer;
    private List<CustomerDTO> filteredCustomers;
    private List<CustomerDTO> customers;

    @PostConstruct
    private void init() {
        updateModel();
    }

    public void updateModel() {
        customers = model.getAllCustomers();
    }

    public void selectedCustomerDetails(CustomerDTO dto) {
        selectedCustomer = dto;
        Ajax.update("customer_modal");
        Ajax.oncomplete("$('#customer_modal').modal('show')");
    }

    public List<CustomerDTO> getFilteredCustomers() {
        return filteredCustomers;
    }

    public void setFilteredCustomers(List<CustomerDTO> filteredCustomers) {
        this.filteredCustomers = filteredCustomers;
    }

    public CustomerDTO getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(CustomerDTO selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDTO> customers) {
        this.customers = customers;
    }
}
