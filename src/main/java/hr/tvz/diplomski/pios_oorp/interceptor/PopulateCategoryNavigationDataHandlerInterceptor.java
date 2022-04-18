package hr.tvz.diplomski.pios_oorp.interceptor;

import hr.tvz.diplomski.pios_oorp.service.CategoryService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Adds list of all parent {@link hr.tvz.diplomski.pios_oorp.domain.Category} to every page
 */
@Component
public class PopulateCategoryNavigationDataHandlerInterceptor implements HandlerInterceptor {

    @Resource
    private CategoryService categoryService;

    /**
     * Adds list of all parent {@link hr.tvz.diplomski.pios_oorp.domain.Category} to every page
     * @param request request
     * @param response response
     * @param handler handler
     * @param modelAndView modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            modelAndView.addObject("parentCategories", categoryService.getAllParentCategories());
        }
    }
}
