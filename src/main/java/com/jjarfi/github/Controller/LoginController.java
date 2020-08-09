package com.jjarfi.github.Controller;

import com.jjarfi.github.Model.Admin;
import com.jjarfi.github.Service.ServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/noken/admin")
public class LoginController {
    @Autowired
    private ServiceAdmin serviceAdmin;

    @GetMapping("/nama")
    public String getLogin() {
        return "login";
    }

    @GetMapping(path = "{adminId}")
    public Optional<Admin> getLoginAdmin(@PathVariable("adminId")Integer adminId){
        return serviceAdmin.findId(adminId);
    }
}
