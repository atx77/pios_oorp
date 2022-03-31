package hr.tvz.diplomski.pios_oorp.interceptor;

import hr.tvz.diplomski.pios_oorp.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PopulateUserDataHandlerInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            modelAndView.addObject("loggedUser", userService.getLoggedUser());
            modelAndView.addObject("isUserAdmin", userService.isSessionUserAdmin());
            modelAndView.addObject("isUserCustomer", userService.isSessionUserCustomer());
            modelAndView.addObject("isUserGuest", userService.isSessionUserGuest());
        }
    }
}
