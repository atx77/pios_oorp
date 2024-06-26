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

@Component
public class PopulateAdminCategoryFormsHandlerInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

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
