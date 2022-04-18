package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.form.LoginForm;
import hr.tvz.diplomski.pios_oorp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * Shows login page
     * @param model model
     * @return login page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String viewLoginPage(Model model) {
        model.addAttribute("title", "Prijava");
        model.addAttribute("loginForm", new LoginForm());
        return PagesConstants.LOGIN;
    }
}
