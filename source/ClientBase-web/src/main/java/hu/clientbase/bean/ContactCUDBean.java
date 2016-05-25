package hu.clientbase.bean;

import hu.clientbase.bean.mv.CustomersBean;
import hu.clientbase.dto.ContactDTO;
import hu.clientbase.service.CustomerService;
import org.omnifaces.util.Ajax;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("contactCUD")
@ViewScoped
public class ContactCUDBean implements Serializable {

    private static final long serialVersionUID = -6054306052800947292L;

    @Inject
    private transient CustomerService customerService;

    @Inject
    private CustomersBean customersBean;

    private Long id;

    private String firstName;

    private String lastName;

    private ContactDTO contactToDelete;

    public void openAddDialog() {
        Ajax.oncomplete("$('#customer_details_dialog').modal('hide');$('#contact_add_dialog').modal('show');");
    }

    public void add() {
        customerService.addContactToCustomer(customersBean.getSelectedCustomer(), new ContactDTO(firstName, lastName));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact person added succesfully."));
        firstName = null;
        lastName = null;
        customersBean.update();
        Ajax.update("customer_details_right_panel:contacts_list");
        Ajax.oncomplete("clearAndCloseAddContactDialog(true);");
    }

    public void openEditDialog(ContactDTO dto) {
        firstName = dto.getFirstName();
        lastName = dto.getLastName();
        id = dto.getId();
        Ajax.update("contact_edit_form");
        Ajax.oncomplete("$('#customer_details_dialog').modal('hide');$('#contact_edit_dialog').modal('show');");
    }

    public void edit() {
        ContactDTO dto = new ContactDTO(firstName, lastName);
        dto.setId(id);
        customerService.updateContact(dto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact person edited succesfully."));
        customersBean.update();
        Ajax.update("customer_details_right_panel:contacts_list");
        Ajax.oncomplete("clearAndCloseEditContactDialog(true);");
    }

    public void openDeleteDialog(ContactDTO dto) {
        contactToDelete = dto;
        Ajax.update("contact_delete_name");
        Ajax.oncomplete("$('#customer_details_dialog').modal('hide');$('#contact_delete_dialog').modal('show');");
    }

    public void delete() {
        customerService.deleteContact(customersBean.getSelectedCustomer(), contactToDelete);
        customersBean.update();
        Ajax.update("customer_details_right_panel:contacts_list");
        Ajax.oncomplete("$('#contact_delete_dialog').modal('hide');$('#customer_details_dialog').modal('show');");
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

    public ContactDTO getContactToDelete() {
        return contactToDelete;
    }

    public void setContactToDelete(ContactDTO contactToDelete) {
        this.contactToDelete = contactToDelete;
    }
}
