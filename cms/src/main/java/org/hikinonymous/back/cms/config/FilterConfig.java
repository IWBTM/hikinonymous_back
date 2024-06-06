package org.hikinonymous.back.cms.config;

import org.hikinonymous.back.cms.filter.CmsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<CmsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CmsFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
