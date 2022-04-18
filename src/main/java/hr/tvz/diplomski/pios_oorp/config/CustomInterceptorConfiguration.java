package hr.tvz.diplomski.pios_oorp.config;

import hr.tvz.diplomski.pios_oorp.interceptor.PopulateAdminCategoryFormsHandlerInterceptor;
import hr.tvz.diplomski.pios_oorp.interceptor.PopulateCategoryNavigationDataHandlerInterceptor;
import hr.tvz.diplomski.pios_oorp.interceptor.PopulateCurrencyDataHandlerInterceptor;
import hr.tvz.diplomski.pios_oorp.interceptor.PopulateUserDataHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Adds custom interceptors
 */
@Configuration
public class CustomInterceptorConfiguration implements WebMvcConfigurer {

    @Resource
    private PopulateUserDataHandlerInterceptor populateUserDataHandlerInterceptor;

    @Resource
    private PopulateCategoryNavigationDataHandlerInterceptor populateCategoryNavigationDataHandlerInterceptor;

    @Resource
    private PopulateAdminCategoryFormsHandlerInterceptor populateAdminCategoryFormsHandlerInterceptor;

    @Resource
    private PopulateCurrencyDataHandlerInterceptor populateCurrencyDataHandlerInterceptor;

    /**
     * Adds custom interceptors
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(populateUserDataHandlerInterceptor);
        registry.addInterceptor(populateCategoryNavigationDataHandlerInterceptor);
        registry.addInterceptor(populateAdminCategoryFormsHandlerInterceptor);
        registry.addInterceptor(populateCurrencyDataHandlerInterceptor);
    }
}
