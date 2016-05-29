package hu.clientbase.bean;

import hu.clientbase.bean.mv.CustomersBean;
import hu.clientbase.bean.mv.ProjectBean;
import hu.clientbase.dto.AddressDTO;
import hu.clientbase.dto.CustomerDTO;
import hu.clientbase.service.CustomerService;
import org.omnifaces.util.Ajax;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("customerCUD")
@ViewScoped
public class CustomerCUDBean implements Serializable {

    private static final long serialVersionUID = 258979332215257295L;

    @Inject
    private transient CustomerService customerService;

    @Inject
    private CustomersBean customerBean;

    @Inject
    private ProjectCUDBean projectBean;
    
    private Long id;

    private String name;

    private String vatNumber;

    private String city;

    private String zipCode;

    private String street;

    private String country;

    private CustomerDTO customerToDelete;

    
    private static final String CUSTOMERS_LIST = "c_list_form:c_list_table";

    public void openAddDialog() {
        projectBean.updateView();
        Ajax.update("a_form:projects");
        Ajax.oncomplete("$('#customer_add_dialog').modal('show')");
    }

    public void add() {

        CustomerDTO dto = new CustomerDTO(name, vatNumber, new AddressDTO(city, zipCode, street, country));

        customerService.create(dto);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer added succesfully."));
        customerBean.update();
        Ajax.update(CUSTOMERS_LIST);
        Ajax.oncomplete("clearAndCloseAddCustomerDialog(true)");
    }

    public void openEditDialog(CustomerDTO dto) {
        id = dto.getId();
        name = dto.getName();
        vatNumber = dto.getVatNumber();
        city = dto.getAddress().getCity();
        zipCode = dto.getAddress().getZipCode();
        street = dto.getAddress().getStreet();
        country = dto.getAddress().getCountry();
        Ajax.update("customer_edit_form");
        Ajax.oncomplete("$('#customer_edit_dialog').modal('show')");
    }

    public void edit() {
        CustomerDTO dto = new CustomerDTO(name, vatNumber, new AddressDTO(city, zipCode, street, country));
        dto.setId(id);
        customerService.update(dto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer edited succesfully."));
        customerBean.update();
        Ajax.update(CUSTOMERS_LIST);
        Ajax.oncomplete("clearAndCloseEditCustomerDialog(true)");
    }

    public void openDeleteDialog(CustomerDTO dto) {
        customerToDelete = dto;
        Ajax.update("customer_delete_name");
        Ajax.oncomplete("$('#customer_delete_dialog').modal('show')");
    }

    public void delete() {
        customerService.delete(customerToDelete);
        customerBean.update();
        Ajax.update(CUSTOMERS_LIST);
        Ajax.oncomplete("$('#customer_delete_dialog').modal('hide')");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CustomerDTO getCustomerToDelete() {
        return customerToDelete;
    }

    public void setCustomerToDelete(CustomerDTO customerToDelete) {
        this.customerToDelete = customerToDelete;
    }

}
