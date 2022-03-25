package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomepageController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewHomepage(Model model) {
        model.addAttribute("test", "homepage");
        return PagesConstants.HOMEPAGE;
    }
}
