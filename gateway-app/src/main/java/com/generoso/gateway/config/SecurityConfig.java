package com.generoso.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable();

        http.authorizeExchange()
                .pathMatchers("/private/**").permitAll()
                .and()
                .authorizeExchange()
                .anyExchange()
                .authenticated();

        http.oauth2Login();
        http.oauth2Client();
        return http.build();
    }
}
