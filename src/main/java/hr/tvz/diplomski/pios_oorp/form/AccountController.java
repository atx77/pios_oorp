package hr.tvz.diplomski.pios_oorp.form;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.dto.AlertMessage;
import hr.tvz.diplomski.pios_oorp.enumeration.AlertType;
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
@RequestMapping("/my-account")
public class AccountController {

    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewMyAccountPage(Model model) {
        model.addAttribute("title", "Moj profil");
        model.addAttribute("updateProfileForm", new UpdateProfileForm());
        return PagesConstants.MY_ACCOUNT;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RedirectView updateUserProfile(@Valid @ModelAttribute("updateProfileForm")UpdateProfileForm form,
                                          RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/my-account");
        userService.updateUserPersonalInformation(form.getFirstName(), form.getLastName(), form.getPassword());
        redirectAttributes.addFlashAttribute("alertMessage",
                new AlertMessage("Uspješno ste promijenili Vaše osobne podatke", AlertType.SUCCESS));
        return redirectView;
    }
}