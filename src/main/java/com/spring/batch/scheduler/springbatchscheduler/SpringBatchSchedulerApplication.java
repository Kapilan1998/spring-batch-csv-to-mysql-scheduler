package com.spring.batch.scheduler.springbatchscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   // scheduler enabled to this application to execute task for every certain time
public class SpringBatchSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchSchedulerApplication.class, args);
    }

}
