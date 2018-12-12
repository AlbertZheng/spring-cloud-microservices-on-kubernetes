package com.kyletiger.cloud.department;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = {"com.kyletiger.cloud"})
// To scan my MyBatis mapper classes.
@MapperScan("com.kyletiger.cloud.department.mapper")
// To use Eureka Client for registering myself and discovering other service providers.
@EnableDiscoveryClient
// To use Feign + Ribbon + Hystrix (If ``feign.hystrix.enabled=true``)
@EnableFeignClients
// To enable Hystrix Circuit Breaker
@EnableCircuitBreaker
// Note: @EnableHystrix == @EnableFeignClients
//@EnableHystrix
public class DepartmentMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DepartmentMicroserviceApplication.class, args);
    }
}
