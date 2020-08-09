package com.jjarfi.github.Model;

import javax.persistence.*;

@Entity
@Table(name = "password")
public class Password {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String password;


    public Password() {
    }

    public Password(int id, String password) {
        this.id = id;
        this.password = password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
