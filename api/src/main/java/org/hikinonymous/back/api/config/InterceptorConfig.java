package org.hikinonymous.back.api.config;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.api.interceptor.ApiInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final ApiInterceptor apiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry
                .addInterceptor(apiInterceptor)
                .addPathPatterns("/**");
    }

}
