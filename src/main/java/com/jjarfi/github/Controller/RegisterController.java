package com.jjarfi.github.Controller;

import com.jjarfi.github.Service.ServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
    @Autowired
    private ServiceAdmin serviceAdmin;

    // TODO: Get Form Registrasi 8/12/20
    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public ModelAndView getRegister(ModelAndView model) {
        model.setViewName("signup");
        return model;
    }
}
