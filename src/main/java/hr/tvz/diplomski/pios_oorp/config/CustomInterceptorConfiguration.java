package hr.tvz.diplomski.pios_oorp.config;

import hr.tvz.diplomski.pios_oorp.interceptor.PopulateUserHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class CustomInterceptorConfiguration implements WebMvcConfigurer {

    @Resource
    private PopulateUserHandlerInterceptor populateUserHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(populateUserHandlerInterceptor);
    }
}
