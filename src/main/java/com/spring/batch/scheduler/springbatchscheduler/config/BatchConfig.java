package com.spring.batch.scheduler.springbatchscheduler.config;


import com.spring.batch.scheduler.springbatchscheduler.entity.Employee;
import com.spring.batch.scheduler.springbatchscheduler.job.EmployeeItemProcessor;
import com.spring.batch.scheduler.springbatchscheduler.job.EmployeeItemReader;
import com.spring.batch.scheduler.springbatchscheduler.job.EmployeeItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Configuration class for setting up Spring Batch job, step, and reader.
 * It defines how the CSV file will be read, processed, and written to the database.
 */
@Configuration
@EnableBatchProcessing // Enables Spring Batch support and registers JobRepository, JobLauncher, etc.
public class BatchConfig {

    /**
     * Defines the Batch Job — representing the entire workflow
     */
    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("importEmployeeJob", jobRepository)
                .start(step)  // The job starts by executing this single step
                .build();
    }

    /**
     * Defines a single Step in the Batch Job.
     * A step reads, processes, and writes data in chunks.
     */
    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager transactionManager,
                     EmployeeItemReader reader,
                     EmployeeItemProcessor processor,
                     EmployeeItemWriter writer) {

        return new StepBuilder("step", jobRepository)
                .<Employee, Employee>chunk(10, transactionManager)
                .reader(reader)       // Reads from CSV
                .processor(processor) // Transforms data
                .writer(writer)       // Writes to DB
                .build();
    }
}
