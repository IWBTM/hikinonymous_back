package org.hikinonymous.back.cms.config;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.cms.interceptor.CmsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final CmsInterceptor cmsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry
                .addInterceptor(cmsInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/cms/login/proc");
    }

}