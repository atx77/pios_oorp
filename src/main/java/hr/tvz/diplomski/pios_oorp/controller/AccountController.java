package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.domain.Order;
import hr.tvz.diplomski.pios_oorp.dto.AlertMessage;
import hr.tvz.diplomski.pios_oorp.enumeration.AlertType;
import hr.tvz.diplomski.pios_oorp.form.UpdateProfileForm;
import hr.tvz.diplomski.pios_oorp.service.OrderService;
import hr.tvz.diplomski.pios_oorp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.MessageFormat;

@Controller
@RequestMapping("/my-account")
public class AccountController {

    @Resource
    private UserService userService;

    @Resource
    private OrderService orderService;

    /**
     * Shows user account page
     * @param model model
     * @return user account page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String viewMyAccountPage(Model model) {
        model.addAttribute("title", "Moj profil");
        model.addAttribute("updateProfileForm", new UpdateProfileForm());
        model.addAttribute("orders", orderService.getOrdersForCustomer(userService.getLoggedUser()));
        return PagesConstants.MY_ACCOUNT;
    }

    /**
     * Updates user details
     * @param form Input form with user details
     * @param redirectAttributes redirectAttributes
     * @return user account page
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RedirectView updateUserProfile(@Valid @ModelAttribute("updateProfileForm")UpdateProfileForm form,
                                          RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/my-account");
        userService.updateUserPersonalInformation(form.getFirstName(), form.getLastName(), form.getPassword());
        redirectAttributes.addFlashAttribute("alertMessage",
                new AlertMessage("Uspješno ste promijenili Vaše osobne podatke", AlertType.SUCCESS));
        return redirectView;
    }

    /**
     * Shows details for {@link Order}
     * @param orderCode {@link Order} code for which details are shown
     * @param model model
     * @return order details page
     */
    @RequestMapping(value = "/order/details/{orderCode}", method = RequestMethod.GET)
    public String viewMyAccountOrderDetailsPage(@PathVariable("orderCode") final String orderCode, Model model) {
        Order order = orderService.getByCode(orderCode);
        if (!userService.isSessionUserAdmin() && !userService.getLoggedUser().equals(order.getUser())) {
            return PagesConstants.ERROR_404;
        }
        model.addAttribute("order", order);
        model.addAttribute("title", MessageFormat.format("Pregled narudžbe {0}", order.getCode()));
        return PagesConstants.ORDER_DETAILS;

    }
}