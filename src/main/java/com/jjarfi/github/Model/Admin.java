package com.jjarfi.github.Model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(nullable = false, unique = true, length = 30, name = "nama")
    private String nama;
    @Column(nullable = false, unique = true, length = 30, name = "email")
    private String email;
    @Column(nullable = false, unique = true, length = 30, name = "handphone")
    private String hp;
    @Column(name = "active", nullable = false)
    private Boolean status;
    @Column(nullable = false, unique = true, length = 30, name = "username")
    private String username;
    @Column(nullable = false, length = 255, name = "password")
    private String password;
    private Timestamp createdate;


    public Admin() {
    }

    public Admin(int id, String nama, String email, String hp, Boolean status, String username, String password, Timestamp createdate) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.hp = hp;
        this.status = status;
        this.username = username;
        this.password = password;
        this.createdate = createdate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

}
