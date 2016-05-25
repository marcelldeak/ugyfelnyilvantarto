package hu.clientbase.bean.mv;

import hu.clientbase.dto.ContactDTO;
import hu.clientbase.dto.CustomerDTO;
import hu.clientbase.service.CustomerService;
import org.omnifaces.util.Ajax;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("customers")
@ViewScoped
public class CustomersBean implements Serializable {

    private static final long serialVersionUID = -961683443281866011L;

    @Inject
    private transient CustomerService customerService;

    private CustomerDTO selectedCustomer;
    
    private List<CustomerDTO> filteredCustomers;
    
    private List<CustomerDTO> customers;

    private List<ContactDTO> contactPersons;

    @PostConstruct
    private void init() {
        update();
    }
    public void update() {
        customers = customerService.getAllCustomers();
        if (selectedCustomer != null) {
            contactPersons = customerService.getContactsByCustomer(selectedCustomer);
        }
    }

    public void openSelectedCustomerDetails(CustomerDTO dto) {
        selectedCustomer = dto;
        update();
        Ajax.update("customer_details", "customer_details_right_panel:contacts_list");
        Ajax.oncomplete("$('#customer_details_dialog').modal('show')");
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

    public List<ContactDTO> getContactPersons() {
        return contactPersons;
    }

    public void setContactPersons(List<ContactDTO> contactPersons) {
        this.contactPersons = contactPersons;
    }

}
