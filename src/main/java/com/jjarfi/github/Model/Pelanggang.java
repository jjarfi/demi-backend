package com.jjarfi.github.Model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "pelanggang")
public class Pelanggang {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String fullname;
    private String email;
    private String handphone;
    private String username;
    @Column(name = "active")
    private Boolean status;
    private Timestamp createdate;

    public Pelanggang() {
    }

    public Pelanggang(int id, String fullname, String email, String handphone, String username, Boolean status, Timestamp createdate) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.handphone = handphone;
        this.username = username;
        this.status = status;
        this.createdate = createdate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setHandphone(String handphone) {
        this.handphone = handphone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

}
