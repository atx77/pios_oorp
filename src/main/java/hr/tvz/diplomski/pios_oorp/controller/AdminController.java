package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String viewAdminHomepage(Model model) {
        model.addAttribute("test", "admin homepage");
        return PagesConstants.ADMIN_HOMEPAGE;
    }
}
