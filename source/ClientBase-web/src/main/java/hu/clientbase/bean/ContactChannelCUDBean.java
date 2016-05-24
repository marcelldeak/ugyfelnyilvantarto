package hu.clientbase.bean;

import hu.clientbase.bean.mv.CustomersBean;
import hu.clientbase.dto.ContactChannelDTO;
import hu.clientbase.entity.ContactChannelType;
import hu.clientbase.service.CustomerService;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
    
    public void openAddDialog() {
        
    }

    public void add() {
        
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
}
