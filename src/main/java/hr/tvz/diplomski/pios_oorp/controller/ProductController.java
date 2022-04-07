package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.domain.Product;
import hr.tvz.diplomski.pios_oorp.dto.AlertMessage;
import hr.tvz.diplomski.pios_oorp.enumeration.AlertType;
import hr.tvz.diplomski.pios_oorp.form.AddRecensionForm;
import hr.tvz.diplomski.pios_oorp.form.AddToCartForm;
import hr.tvz.diplomski.pios_oorp.service.ProductService;
import hr.tvz.diplomski.pios_oorp.service.RecensionService;
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
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private RecensionService recensionService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public String viewProductPage(@PathVariable("productId")Long productId, Model model) {
        Product product = productService.getProductForId(productId)
                .orElseThrow(IllegalAccessError::new);
        model.addAttribute("title", product.getName());
        model.addAttribute("product", product);
        model.addAttribute("addToCartForm", new AddToCartForm());
        model.addAttribute("addRecensionForm", new AddRecensionForm());
        model.addAttribute("userHasBoughtProduct", userService.hasUserBoughtProduct(userService.getLoggedUser(), product));
        return PagesConstants.PRODUCT;
    }

    @RequestMapping(value = "/recension/add", method = RequestMethod.POST)
    public RedirectView addNewRecension(@Valid @ModelAttribute("addRecensionForm") AddRecensionForm form,
                                         RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/product/" + form.getProductId(), true);
        Product product = productService.getProductForId(form.getProductId())
                        .orElseThrow(IllegalAccessError::new);
        recensionService.addNewRecensionForProduct(product, form.getText(), userService.getLoggedUser());
        redirectAttributes.addFlashAttribute("alertMessage",
                new AlertMessage(MessageFormat.format("Uspješno ste ostavili recenziju za proizvod \"{0}\"", product.getName()),
                        AlertType.SUCCESS)
        );
        return redirectView;
    }
}