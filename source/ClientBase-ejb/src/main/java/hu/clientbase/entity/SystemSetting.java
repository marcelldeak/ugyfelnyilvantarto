package hu.clientbase.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SystemSetting implements Serializable {

    @Basic
    @Column(name = "VAL")
    private String value;

    @Id
    @Column(name = "K")
    private String key;

    public SystemSetting() {
            // Entity - parameterless constructor
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
