package com.generoso.ft.gateway.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {
    TestConfiguration.class,
    LocalWiremockServer.class
})
@CucumberContextConfiguration
public class CucumberSpringConfiguration {
}
