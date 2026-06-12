package com.mateo.springmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmartApplication.class, args);
    }

}
