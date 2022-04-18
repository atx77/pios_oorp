package hr.tvz.diplomski.pios_oorp.interceptor;

import hr.tvz.diplomski.pios_oorp.service.CategoryService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PopulateCategoryNavigationDataHandlerInterceptor implements HandlerInterceptor {

    @Resource
    private CategoryService categoryService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            modelAndView.addObject("parentCategories", categoryService.getAllParentCategories());
        }
    }
}
