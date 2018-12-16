package com.kyletiger.cloud.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableAutoConfiguration

// To use Eureka Client for registering myself and discovering other service providers.
@EnableDiscoveryClient
// @EnableDiscoveryClient == @EnableEurekaClient

// To use Spring Boot Admin Server
@EnableAdminServer

public class SpringBootAdminServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminServerApplication.class, args);
    }

    @Configuration
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable();
        }
    }
}
