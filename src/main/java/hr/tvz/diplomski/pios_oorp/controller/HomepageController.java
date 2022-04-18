package hr.tvz.diplomski.pios_oorp.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import hr.tvz.diplomski.pios_oorp.form.AddToCartForm;
import hr.tvz.diplomski.pios_oorp.service.BannerService;
import hr.tvz.diplomski.pios_oorp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class HomepageController {

    @Resource
    private BannerService bannerService;

    @Resource
    private ProductService productService;

    /**
     * Shows homepage with list of {@link hr.tvz.diplomski.pios_oorp.domain.Banner} and list of newest {@link hr.tvz.diplomski.pios_oorp.domain.Product} carousel
     * @param model model
     * @return home page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewHomepage(Model model) {
        model.addAttribute("title", "Naslovna stranica");
        model.addAttribute("banners", bannerService.getAllBanners());
        model.addAttribute("newestProducts", productService.findNewestProducts());
        model.addAttribute("addToCartForm", new AddToCartForm());
        return PagesConstants.HOMEPAGE;
    }
}
