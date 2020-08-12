package com.jjarfi.github.Controller;

import com.jjarfi.github.Model.Admin;
import com.jjarfi.github.Service.ServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private ServiceAdmin serviceAdmin;

    // TODO: Get Form Login 8/12/20
    @RequestMapping(value = {"/signin"}, method = RequestMethod.GET)
    public ModelAndView getLogin(ModelAndView model) {
        model.setViewName("signin");
        return model;
    }
}
