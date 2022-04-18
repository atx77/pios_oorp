package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.domain.Order;
import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.dto.AlertMessage;
import hr.tvz.diplomski.pios_oorp.enumeration.AlertType;
import hr.tvz.diplomski.pios_oorp.enumeration.DeliveryMode;
import hr.tvz.diplomski.pios_oorp.enumeration.PaymentMethod;
import hr.tvz.diplomski.pios_oorp.form.AddToCartForm;
import hr.tvz.diplomski.pios_oorp.form.ChangeProductQuantityInCartForm;
import hr.tvz.diplomski.pios_oorp.form.CheckoutForm;
import hr.tvz.diplomski.pios_oorp.form.RemoveProductFromCartForm;
import hr.tvz.diplomski.pios_oorp.service.CartService;
import hr.tvz.diplomski.pios_oorp.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.MessageFormat;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @Resource
    private OrderService orderService;

    /**
     * Shows cart page
     * @param model model
     * @return cart page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String viewCartPage(Model model) {
        model.addAttribute("title", "Pregled košarice");
        model.addAttribute("removeProductFromCartForm", new RemoveProductFromCartForm());
        model.addAttribute("changeProductQuantityInCartForm", new ChangeProductQuantityInCartForm());
        cartService.recalculateCartTotalPrice();
        return PagesConstants.CART;
    }

    /**
     * Shows checkout page
     * @param model model
     * @return checkout page
     */
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String viewCheckoutPage(Model model) {
        model.addAttribute("title", "Naplata");
        model.addAttribute("checkoutForm", new CheckoutForm());
        model.addAttribute("deliveryModes", DeliveryMode.values());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        cartService.recalculateCartTotalPrice();
        return PagesConstants.CHECKOUT;
    }

    /**
     * Creates new {@link Order} from {@link hr.tvz.diplomski.pios_oorp.domain.Cart} of session {@link hr.tvz.diplomski.pios_oorp.domain.User} and with payment details provided in form
     * @param form Form with payment details
     * @param redirectAttributes redirectAttributes
     * @return order details page
     */
    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public RedirectView createOrder(@Valid @ModelAttribute("checkoutForm")CheckoutForm form,
                                    RedirectAttributes redirectAttributes) {
        Order order = orderService.createOrderForCurrentUser(form);
        final RedirectView redirectView = new RedirectView("/my-account/order/details/" + order.getCode());
        redirectAttributes.addFlashAttribute("alertMessage",
                new AlertMessage(MessageFormat.format("Uspješno ste napravili narudžbu br. {0}", order.getCode()),
                        AlertType.SUCCESS)
        );
        return redirectView;
    }

    /**
     * Adds new {@link Product} with specified quantity to {@link hr.tvz.diplomski.pios_oorp.domain.Cart} of session {@link hr.tvz.diplomski.pios_oorp.domain.User}
     * @param form Form with details of product that will be added to {@link hr.tvz.diplomski.pios_oorp.domain.Cart}
     * @param redirectAttributes redirectAttributes
     * @return cart page
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RedirectView addProductToCart(@Valid @ModelAttribute("addToCartForm")AddToCartForm form,
                                         RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/cart", true);
        Product addedProduct = cartService.addProductToCart(form.getProductId(), form.getQuantity());
        redirectAttributes.addFlashAttribute("alertMessage",
                new AlertMessage(MessageFormat.format("Dodali ste proizvod \"{0}\" u košaricu s količinom {1}.", addedProduct.getName(), form.getQuantity()),
                        AlertType.SUCCESS)
        );
        return redirectView;
    }

    /**
     * Removes specified {@link Product} from {@link hr.tvz.diplomski.pios_oorp.domain.Cart} of session {@link hr.tvz.diplomski.pios_oorp.domain.User}
     * @param form Form with Id of {@link Product} which will be removed from {@link hr.tvz.diplomski.pios_oorp.domain.Cart}
     * @param redirectAttributes redirectAttributes
     * @return cart page
     */
    @RequestMapping(value = "/remove-product", method = RequestMethod.POST)
    public RedirectView removeProductFromCart(@Valid @ModelAttribute("removeProductFromCartForm")RemoveProductFromCartForm form,
                                         RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/cart", true);
        Product removedProduct = cartService.removeProductFromCart(form.getProductId());
        redirectAttributes.addFlashAttribute("alertMessage",
                new AlertMessage(MessageFormat.format("Obrisali ste proizvod \"{0}\" iz košarice.", removedProduct.getName()),
                        AlertType.SUCCESS)
        );
        return redirectView;
    }

    /**
     * Changes quantity of {@link Product} in {@link hr.tvz.diplomski.pios_oorp.domain.Cart} of session {@link hr.tvz.diplomski.pios_oorp.domain.User}
     * @param form Form with Id of {@link Product} for which quantity will be changed and quantity amount
     * @param redirectAttributes redirectAttributes
     * @return cart page
     */
    @RequestMapping(value = "/change-quantity", method = RequestMethod.POST)
    public RedirectView changeProductQuantityInCart(@Valid @ModelAttribute("changeProductQuantityInCartForm")ChangeProductQuantityInCartForm form,
                                              RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/cart", true);
        Product changedProduct = cartService.changeProductQuantityInCart(form.getProductId(), form.getQuantity());
        redirectAttributes.addFlashAttribute("alertMessage",
                new AlertMessage(MessageFormat.format("Promijenili ste količinu proizvodu \"{0}\" u {1}.", changedProduct.getName(), form.getQuantity()),
                        AlertType.SUCCESS)
        );
        return redirectView;
    }
}
