package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.dto.AlertMessage;
import hr.tvz.diplomski.pios_oorp.enumeration.AlertType;
import hr.tvz.diplomski.pios_oorp.form.RegisterForm;
import hr.tvz.diplomski.pios_oorp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewLoginPage(Model model) {
        model.addAttribute("title", "Registracija");
        model.addAttribute("registerForm", new RegisterForm());
        return PagesConstants.REGISTER;
    }

    @RequestMapping(method = RequestMethod.POST)
    public RedirectView registerNewCustomer(@Valid @ModelAttribute("registerForm")RegisterForm registerForm,
                                RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/login", true);
        userService.registerNewCustomer(registerForm);
        redirectAttributes.addFlashAttribute("alertMessage",
                new AlertMessage("Uspješno ste se registrirali. Prijavite se.", AlertType.SUCCESS));
        return redirectView;
    }
}
