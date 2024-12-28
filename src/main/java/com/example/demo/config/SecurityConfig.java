package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Create a BasicAuthenticationEntryPoint and set its realmName
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("MyAppRealm"); // Set a custom realm name
        entryPoint.afterPropertiesSet(); // Ensure properties are initialized

        http
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(entryPoint)); // Use the configured entry point
        return http.build();
    }
}
