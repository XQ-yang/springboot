package com.yang.springbootupanddown;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 小强
 */
@SpringBootApplication
public class SpringbootUpanddownApplication {

    public static void main(String[] args) throws UnknownHostException {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootUpanddownApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        LoggerFactory.getLogger(SpringbootUpanddownApplication.class).info(
                "\n-----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t"+
                        "Local: \t\thttp://localhost:{}\n\t"+
                        "External: \thttp://{}:{}\n\t"+
                        "API Doc: \thttp://{}:{}/doc.html?plus=1\n"+
                        "-----------------------------------------------------------",
                environment.getProperty("spring.application.name"),
                environment.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port")
        );
    }

}
