package hu.clientbase.bean;

import hu.clientbase.bean.mv.CustomersBean;
import hu.clientbase.dto.ContactChannelDTO;
import hu.clientbase.dto.ContactDTO;
import hu.clientbase.entity.ContactChannelType;
import hu.clientbase.service.CustomerService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Ajax;

@Named("contactChannelCUD")
@ViewScoped
public class ContactChannelCUDBean implements Serializable {

    private static final long serialVersionUID = 5207156919569672574L;
    
    @Inject
    private CustomerService customerService;

    @Inject
    private CustomersBean customersBean;
    
    private Long id;
    
    private ContactChannelType channelType;
    
    private String channelValue;
    
    private ContactChannelDTO contactChannelToDelete;
    
    private ContactDTO selectedContact;
    
    public void openAddDialog(ContactDTO dto) {
        selectedContact = dto;
        Ajax.oncomplete("$('#customer_details_dialog').modal('hide');$('#contact_channel_add_dialog').modal('show');");
    }

    public void add() {
        customerService.addContactChannel(selectedContact, new ContactChannelDTO(channelType, channelValue));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact channel added succesfully."));
        channelType = ContactChannelType.EMAIL;
        channelValue = null;
        customersBean.update();
        Ajax.update("customer_details_right_panel:contacts_list");
        Ajax.oncomplete("clearAndCloseAddContactChannelDialog(true);");
    }

    public void openEditDialog(ContactChannelDTO dto) {
     
    }

    public void edit() {
       
    }

    public void openDeleteDialog(ContactChannelDTO dto) {
       
    }

    public void delete() {
       
    }

    public ContactChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ContactChannelType channelType) {
        this.channelType = channelType;
    }

    public String getChannelValue() {
        return channelValue;
    }

    public void setChannelValue(String channelValue) {
        this.channelValue = channelValue;
    }
    
    public ContactChannelType[] getChannelTypes()
    {
        return ContactChannelType.values();
    }
}
