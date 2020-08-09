package com.jjarfi.github.Model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "permissions")
public class Permissions {
    @Id
    private String id;
    private String label;
    private String value;


    public Permissions() {
    }

    public Permissions(String id, String label, String value) {
        this.id = id;
        this.label = label;
        this.value = value;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
