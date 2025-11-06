package com.spring.batch.scheduler.springbatchscheduler.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * REST Controller to manually trigger the Spring Batch job.
 * This allows you to start the job via an HTTP POST request.
 */
@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobLauncher jobLauncher; // Responsible for launching a job
    private final Job job;                 // The job to be executed

    public JobController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @PostMapping("/start")
    public String startJob() throws Exception {
        // Job parameters must be unique each time, otherwise Spring Batch won’t rerun the same job instance.
        Map<String, JobParameter<?>> params = new HashMap<>();
        params.put("time", new JobParameter<>(new Date(), Date.class));

        // Launch the job with the parameters
        jobLauncher.run(job, new JobParameters(params));

        // Return confirmation to the client
        return "Job Started at " + new Date();
    }
}