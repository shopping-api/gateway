package com.generoso.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;

// The class ReactiveUserDetailsServiceAutoConfiguration is removed because it generates a default user-password for security that is not necessary
@SpringBootApplication(exclude = {ReactiveUserDetailsServiceAutoConfiguration.class})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
