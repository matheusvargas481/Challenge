package com.matheusvargas481.challenge.dateanalysis;

import com.matheusvargas481.challenge.dateanalysis.builder.BuildProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
        BuildProcessor buildProcessor = new BuildProcessor();
        System.out.println(buildProcessor.salesmanList);
    }
}

