package com.spring.batch.scheduler.springbatchscheduler.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * SchedulerConfig
 * ----------------
 * This class is responsible for triggering the Spring Batch job automatically
 * at a fixed interval — every 2 minutes — using Spring Scheduler.
 *
 * It integrates the scheduling mechanism with Spring Batch’s JobLauncher to
 * run the configured batch job (importUserJob).
 */
@Configuration
public class SchedulerConfig {

    // JobLauncher is a Spring Batch component that is used to start jobs programmatically
    @Autowired
    private JobLauncher jobLauncher;

    // Reference to the Job bean defined in BatchConfig (importUserJob)
    @Autowired
    private Job importUserJob;


    @Scheduled(cron = "0 */2 * * * *") // every 2 minutes
    public void runJob() throws Exception {
        // JobParameters ensure each job run is unique (important for Spring Batch)
        Map<String, JobParameter<?>> params = new HashMap<>();
        params.put("timestamp", new JobParameter<>(new Date(), Date.class));

        // Launch the job with the given parameters
        jobLauncher.run(importUserJob, new JobParameters(params));

        // Log the trigger time to the console
        System.out.println(" Job triggered at " + new Date());
    }
}