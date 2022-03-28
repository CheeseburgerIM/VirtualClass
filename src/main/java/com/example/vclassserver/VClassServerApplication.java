package com.example.vclassserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.vclassserver.utils.refresher;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VClassServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VClassServerApplication.class, args);
    }

}
