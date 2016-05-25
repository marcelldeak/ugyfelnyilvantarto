package hu.clientbase.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 3492921308179920151L;

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String name;

    public Role() {
        // Entity - parameterless constructor
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

}
