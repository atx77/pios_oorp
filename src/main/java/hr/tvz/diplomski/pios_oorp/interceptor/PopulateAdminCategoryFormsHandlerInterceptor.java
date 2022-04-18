package hr.tvz.diplomski.pios_oorp.interceptor;

import hr.tvz.diplomski.pios_oorp.form.admin.AddCategoryAdminForm;
import hr.tvz.diplomski.pios_oorp.form.admin.CategoryVisibilityAdminForm;
import hr.tvz.diplomski.pios_oorp.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Adds admin forms for adding new {@link hr.tvz.diplomski.pios_oorp.domain.Category} and
 * {@link hr.tvz.diplomski.pios_oorp.domain.Category} category visibility to every page for admin {@link hr.tvz.diplomski.pios_oorp.domain.User}
 */
@Component
public class PopulateAdminCategoryFormsHandlerInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    /**
     * Adds admin forms for adding new {@link hr.tvz.diplomski.pios_oorp.domain.Category} and changing
     * {@link hr.tvz.diplomski.pios_oorp.domain.Category} visibility to every page for admin {@link hr.tvz.diplomski.pios_oorp.domain.User}
     * @param request request
     * @param response response
     * @param handler handler
     * @param modelAndView modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            if (userService.isSessionUserAdmin()) {
                modelAndView.addObject("addCategoryAdminForm", new AddCategoryAdminForm());
                modelAndView.addObject("categoryVisibilityAdminForm", new CategoryVisibilityAdminForm());
            }
        }
    }
}
