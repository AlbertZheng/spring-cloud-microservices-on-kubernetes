package com.kyletiger.cloud.account.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * Config Server.
 *
 * @author 郑立松 - Albert Zheng <lisong.zheng@gmail.com>
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, FlywayAutoConfiguration.class})

// Spring Cloud Edgware及更高版本中：
// 1. 已经不需要在启动类上添加@EnableEurekaClient或@EnableDiscoveryClient了！
// 2. 只需要在pom.xml里添加相关依赖，即可自动注册。
// @EnableDiscoveryClient == @EnableEurekaClient
//@EnableEurekaClient

// To use Spring Cloud Config Server
@EnableConfigServer

public class ConfigServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(ConfigServerApplication.class, args);
  }
}
