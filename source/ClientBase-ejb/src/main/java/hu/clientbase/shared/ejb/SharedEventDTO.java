package hu.clientbase.shared.ejb;

import java.io.Serializable;
import java.util.Date;

public class SharedEventDTO implements Serializable{

    private static final long serialVersionUID = -7789070717277057004L;

    private String type;
    private Date dateOfStart;
    private Date dateOfEnd;
    private String name;

    public SharedEventDTO() {
        //No-arg constructor
    }

    public SharedEventDTO(String name, String type, Date dateOfStart, Date dateOfEnd ) {
        this.type = type;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.name = name;
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
