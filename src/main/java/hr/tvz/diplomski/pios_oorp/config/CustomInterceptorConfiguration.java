package hr.tvz.diplomski.pios_oorp.config;

import hr.tvz.diplomski.pios_oorp.interceptor.PopulateAdminAddCategoryFormHandlerInterceptor;
import hr.tvz.diplomski.pios_oorp.interceptor.PopulateCategoryNavigationDataHandlerInterceptor;
import hr.tvz.diplomski.pios_oorp.interceptor.PopulateUserDataHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class CustomInterceptorConfiguration implements WebMvcConfigurer {

    @Resource
    private PopulateUserDataHandlerInterceptor populateUserDataHandlerInterceptor;

    @Resource
    private PopulateCategoryNavigationDataHandlerInterceptor populateCategoryNavigationDataHandlerInterceptor;

    @Resource
    private PopulateAdminAddCategoryFormHandlerInterceptor populateAdminAddCategoryFormHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(populateUserDataHandlerInterceptor);
        registry.addInterceptor(populateCategoryNavigationDataHandlerInterceptor);
        registry.addInterceptor(populateAdminAddCategoryFormHandlerInterceptor);
    }
}
