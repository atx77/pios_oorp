package hr.tvz.diplomski.pios_oorp.exception.controller;

import hr.tvz.diplomski.pios_oorp.constant.PagesConstants;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorPageController implements ErrorController {

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null && HttpStatus.NOT_FOUND.value() == Integer.parseInt(status.toString())) {
            return PagesConstants.ERROR_404;
        }
        return PagesConstants.ERROR;
    }
}
