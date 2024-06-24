package org.hikinonymous.back.cms.common.config;

import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.cms.common.interceptor.CmsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final CmsInterceptor cmsInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:3000")
                .allowedHeaders("Authorization", "Content-Type", "Accept")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(cmsInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/cms/login/proc",
                        "/swagger-ui/**", "/v3/api-docs/**"
                );
    }

}
