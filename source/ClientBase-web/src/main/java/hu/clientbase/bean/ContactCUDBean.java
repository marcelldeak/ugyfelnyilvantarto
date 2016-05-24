package hu.clientbase.bean;

import hu.clientbase.bean.mv.CustomersBean;
import hu.clientbase.dto.ContactDTO;
import hu.clientbase.service.CustomerService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Ajax;

@Named("contactCUD")
@ViewScoped
public class ContactCUDBean implements  Serializable {
    
    private static final long serialVersionUID = -6054306052800947292L;
    
    @Inject
    private CustomerService customerService;
    
    @Inject
    private CustomersBean customersBean;
    
    private String firstName;
    
    private String lastName;
    
    public void openAddDialog()
    {
        Ajax.oncomplete("$('#details_dialog').modal('hide');$('#add_c_dialog').modal('show');");
    }
    
    public void add()
    {
        customerService.addContactToCustomer(customersBean.getSelectedCustomer(), new ContactDTO(firstName, lastName));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact person added succesfully."));
        firstName = null;
        lastName = null;
        Ajax.update("customer_details_right_panel:contacts_list");
        Ajax.oncomplete("clearAndCloseAddContactDialog(true);");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
