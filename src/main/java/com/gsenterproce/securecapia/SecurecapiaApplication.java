package com.gsenterproce.securecapia;

import lombok.ToString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@SpringBootApplication
public class SecurecapiaApplication {


    public static void main(String[] args) {

        SpringApplication.run(SecurecapiaApplication.class, args);
    }


}
