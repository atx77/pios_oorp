package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.form.AddToCartForm;
import hr.tvz.diplomski.pios_oorp.form.RemoveProductFromCartForm;
import hr.tvz.diplomski.pios_oorp.service.CartService;
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

    @RequestMapping(method = RequestMethod.GET)
    public String viewCartPage(Model model) {
        model.addAttribute("title", "Pregled košarice");
        model.addAttribute("removeProductFromCartForm", new RemoveProductFromCartForm());
        cartService.recalculateCartTotalPrice();
        return PagesConstants.CART;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RedirectView addProductToCart(@Valid @ModelAttribute("addToCartForm")AddToCartForm addToCartForm,
                                         RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/cart", true);
        cartService.addProductToCart(addToCartForm.getProductId(), addToCartForm.getQuantity());
        return redirectView;
    }

    @RequestMapping(value = "/remove-product", method = RequestMethod.POST)
    public RedirectView removeProductFromCart(@Valid @ModelAttribute("removeProductFromCartForm")RemoveProductFromCartForm form,
                                         RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/cart", true);
        Product removedProduct = cartService.removeProductFromCart(form.getProductId());
        redirectAttributes.addFlashAttribute("message", MessageFormat.format("Obrisali ste proizvod \"{0}\" iz košarice.", removedProduct.getName()));
        return redirectView;
    }
}
