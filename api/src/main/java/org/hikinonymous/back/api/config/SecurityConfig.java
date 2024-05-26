package org.hikinonymous.back.api.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configurable
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // rest api로 개발 예정이기 때문에 disable
            .httpBasic(AbstractHttpConfigurer::disable) // security default login disable 인듯 ?
            .formLogin(AbstractHttpConfigurer::disable) // security default login disable 인듯 ?
            .authorizeHttpRequests(authorizationManager -> { // 인증이 필요한 URL 지정
                authorizationManager
                        .requestMatchers("/cms/login/**").permitAll() // 해당 URL은 인증 없이도 접근 허용
                        .anyRequest() // 그 외의 요청은
                        .authenticated(); // 인증이 필요한 URL로 지정
            })
            .logout(logoutConfig -> {
                logoutConfig
                        .invalidateHttpSession(true); // logout 후 모든 session 삭제
            })
            .sessionManagement(securitySessionManagementConfig -> {
                securitySessionManagementConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // STATELESS한 정책 설정.
            });

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
