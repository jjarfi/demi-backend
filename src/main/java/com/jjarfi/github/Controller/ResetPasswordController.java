package com.jjarfi.github.Controller;

import com.jjarfi.github.Service.ServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResetPasswordController {
    @Autowired
    private ServiceAdmin serviceAdmin;

    // TODO: Get Form Reset Password 8/12/20
    @RequestMapping(value = {"/reset-password"}, method = RequestMethod.GET)
    public ModelAndView getResetPassword(ModelAndView model) {
        model.setViewName("recover-password");
        return model;
    }
}
