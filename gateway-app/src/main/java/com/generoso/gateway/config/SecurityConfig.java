package com.generoso.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http.csrf(CsrfSpec::disable);

        http.authorizeExchange(authorizeExchangeSpec -> {
            authorizeExchangeSpec.pathMatchers("/private/**").permitAll();
            authorizeExchangeSpec.anyExchange().authenticated();
        });

        http.oauth2Login(Customizer.withDefaults());
        http.oauth2Client(Customizer.withDefaults());
        return http.build();
    }
}
