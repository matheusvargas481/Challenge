package com.matheusvargas481.challenge.dateanalysis;

import com.matheusvargas481.challenge.dateanalysis.builder.BuildProcessor;
import com.matheusvargas481.challenge.dateanalysis.service.FileProcessor;

//
//@SpringBootApplication
public class ChallengeApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ChallengeApplication.class, args);
            FileProcessor fileProcessor = new FileProcessor();
            fileProcessor.leitura();
    }

}