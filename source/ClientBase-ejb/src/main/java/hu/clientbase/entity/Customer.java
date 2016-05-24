package hu.clientbase.entity;

import hu.clientbase.dto.CustomerDTO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = -5054686787988766419L;

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String name;

    @Basic
    @Column(name = "VAT_number")
    private String vatNumber;

    @OneToMany(targetEntity = Contact.class)
    @Cascade(CascadeType.ALL)
    private List<Contact> contacts;

    @OneToMany(targetEntity = Project.class)
    private List<Project> projects;

    @OneToMany(targetEntity = Event.class)
    private List<Event> events;

    @OneToOne(targetEntity = Address.class)
    @Cascade(CascadeType.ALL)
    private Address address;

    public Customer() {
        // Entity - parameterless constructor
    }

    public Customer(CustomerDTO dto) {
        this.name = dto.getName();
        this.vatNumber = dto.getVatNumber();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVatNumber() {
        return this.vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
