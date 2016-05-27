package hu.clientbase.shared.ejb;

import java.io.Serializable;
import java.util.Date;

public class SharedEventDTO implements Serializable{

    private Long id;
    private String type;
    private Date dateOfStart;
    private Date dateOfEnd;
    private String name;

    public SharedEventDTO() {
        //No-arg constructor
    }

    public SharedEventDTO(Long id, String type, Date dateOfStart, Date dateOfEnd, String name) {
        this.id = id;
        this.type = type;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.name = name;
    }

    public SharedEventDTO(String type, Date dateOfStart, Date dateOfEnd, String name) {
        this.type = type;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public Date getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
