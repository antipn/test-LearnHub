package com.test.antipn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
@SpringBootApplication
public class SpringMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringMain.class, args);

    }

}
