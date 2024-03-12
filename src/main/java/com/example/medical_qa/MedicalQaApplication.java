package com.example.medical_qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MedicalQaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalQaApplication.class, args);
    }

}
