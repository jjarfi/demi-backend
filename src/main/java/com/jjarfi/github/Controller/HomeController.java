package com.jjarfi.github.Controller;

import com.jjarfi.github.Service.ServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private ServiceAdmin serviceAdmin;

    // TODO: Get Layout Decorate Thymeleaf dan tampilkan Dashboard 8/12/20
    @RequestMapping(value = {"/beranda"}, method = RequestMethod.GET)
    public ModelAndView getHome(ModelAndView model) {
        model.setViewName("index");
        return model;
    }
}
