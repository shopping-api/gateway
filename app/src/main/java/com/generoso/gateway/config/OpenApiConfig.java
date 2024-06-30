package com.generoso.gateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
            .title("Gateway")
            .description("Services entry-point")
            .version(buildProperties.getVersion())
            .contact(apiContact())
            .license(apiLicence());
    }

    private License apiLicence() {
        return new License()
            .name("MIT Licence")
            .url("https://opensource.org/licenses/mit-license.php");
    }

    private Contact apiContact() {
        return new Contact()
            .name("Mauricio Generoso")
            .email("mauriciomarquesgeneroso@gmail.com")
            .url("https://github.com/mauriciogeneroso");
    }
}
