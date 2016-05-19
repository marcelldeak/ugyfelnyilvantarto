package hu.clientbase.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class PendingRegistration implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    public PendingRegistration() {
        // Entity - parameterless constructor
    }

    public PendingRegistration(User user) {
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
