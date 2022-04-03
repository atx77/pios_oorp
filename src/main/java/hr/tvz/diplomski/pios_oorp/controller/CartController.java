package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.form.AddToCartForm;
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

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewCartPage(Model model) {
        model.addAttribute("title", "Pregled košarice");
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
}
