package hu.clientbase.service;

import hu.clientbase.dto.ContactChannelDTO;
import hu.clientbase.dto.ContactDTO;
import hu.clientbase.dto.CustomerDTO;
import hu.clientbase.entity.Address;
import hu.clientbase.entity.Contact;
import hu.clientbase.entity.ContactChannel;
import hu.clientbase.entity.Customer;
import hu.clientbase.facade.CustomerFacade;
import hu.clientbase.facade.EntityFacade;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class CustomerService {

    @Inject
    private EntityFacade entityFacade;

    @Inject
    private CustomerFacade customerFacade;

    public void create(CustomerDTO dto) {
        Address address = new Address(dto.getAddress());
        Customer customer = new Customer(dto);

        customer.setAddress(address);

        entityFacade.create(customer);
    }

    public void update(CustomerDTO dto) {
        Customer customer = entityFacade.find(Customer.class, dto.getId());

        customer.getAddress().setCity(dto.getAddress().getCity());
        customer.getAddress().setCountry(dto.getAddress().getCountry());
        customer.getAddress().setStreet(dto.getAddress().getStreet());
        customer.getAddress().setZipCode(dto.getAddress().getZipCode());

        customer.setName(dto.getName());
        customer.setVatNumber(dto.getVatNumber());

        entityFacade.update(customer);
    }

    public void delete(CustomerDTO dto) {
        Customer customer = entityFacade.find(Customer.class, dto.getId());

        entityFacade.delete(customer);
    }

    public void addContactToCustomer(CustomerDTO customerDTO, ContactDTO contactDTO) {
        Customer customer = entityFacade.find(Customer.class, customerDTO.getId());
        customer.getContacts().add(new Contact(contactDTO));

        entityFacade.update(customer);
    }

    public List<ContactDTO> getContactsByCustomer(CustomerDTO dto) {
        Customer customer = entityFacade.find(Customer.class, dto.getId());

        List<ContactDTO> ret = new LinkedList<>();
        customer.getContacts().stream().forEach(c -> ret.add(new ContactDTO(c)));

        return ret;
    }

    public void updateContact(ContactDTO dto) {
        Contact contact = entityFacade.find(Contact.class, dto.getId());

        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());

        entityFacade.update(contact);
    }

    public void deleteContact(CustomerDTO customerDTO, ContactDTO contactDTO) {
        Customer customer = entityFacade.find(Customer.class, customerDTO.getId());
        Contact contact = entityFacade.find(Contact.class, contactDTO.getId());
        
        customer.getContacts().remove(contact);
        
        entityFacade.update(customer);
        entityFacade.delete(contact);
    }

    public void addContactChannel(ContactDTO contactDTO, ContactChannelDTO contactChannelDTO) {
        Contact contact = entityFacade.find(Contact.class, contactDTO.getId());
        
        contact.getContactChannels().add(new ContactChannel(contactChannelDTO));
        
        entityFacade.update(contact);
    }

    public void updateContactChannel(ContactChannelDTO dto) {
        ContactChannel contactChannel = entityFacade.find(ContactChannel.class, dto.getId());
        contactChannel.setValue(dto.getValue());
        
        entityFacade.update(contactChannel);
    }

    public void deleteContactChannel(ContactDTO contactDTO, ContactChannelDTO contactChannelDTO) {
          Contact contact = entityFacade.find(Contact.class, contactDTO.getId());
          ContactChannel contactChannel = entityFacade.find(ContactChannel.class, contactChannelDTO.getId());
          
          contact.getContactChannels().remove(contactChannel);
          
          entityFacade.update(contact);
          entityFacade.delete(contactChannel);
    }

}
