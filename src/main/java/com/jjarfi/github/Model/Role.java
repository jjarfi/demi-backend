package com.jjarfi.github.Model;
import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    private String id;
    @Column(name = "name")
    private String nama;

    public Role() {
    }

    public Role(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

}
