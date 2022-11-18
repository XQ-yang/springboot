package com.yang.springbootspringdatajpa;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Administrator
 */
@SpringBootApplication
public class SpringbootSpringDataJpaApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootSpringDataJpaApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        LoggerFactory.getLogger(SpringbootSpringDataJpaApplication.class).info(
                "\n--------------------------------------------\n\t"+
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}{}\n\t"+
                        "External: \thttp://{}:{}{}\n\t"+
                        "API Doc: \thttp://{}:{}{}/doc.html?plus=1\n"+
                        "--------------------------------------------",
                environment.getProperty("spring.application.name"),
                environment.getProperty("server.port"),
                environment.getProperty("server.servlet.context-path"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"),
                environment.getProperty("server.servlet.context-path"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"),
                environment.getProperty("server.servlet.context-path")
        );
    }

}
