//
// This file was generated by the JPA Modeler
//
package hu.clientbase.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    private String picture;

    @OneToMany(targetEntity = ContactChannel.class)
    private List<ContactChannel> contactChannels;

    public Person() {
        // Entity - parameterless constructor
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<ContactChannel> getContactChannels() {
        return this.contactChannels;
    }

    public void setContactChannels(List<ContactChannel> contactChannels) {
        this.contactChannels = contactChannels;
    }
}
