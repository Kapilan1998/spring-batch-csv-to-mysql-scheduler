package com.spring.batch.scheduler.springbatchscheduler.job;

import com.spring.batch.scheduler.springbatchscheduler.entity.Employee;
import com.spring.batch.scheduler.springbatchscheduler.repository.EmployeeRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;


/**
 * EmployeeItemWriter
 * This class is responsible for writing (saving) the processed Employee data
 * into the MySQL database using Spring Data JPA.
 *
 * In Spring Batch, the writing phase comes after reading and processing:
 *   Reader  →  Processor  →  Writer
 */
@Component // Marks this as a Spring-managed bean for dependency injection
public class EmployeeItemWriter implements ItemWriter<Employee> {

    // Injects the JPA repository to interact with the database
    private final EmployeeRepository repository;

    // Constructor-based dependency injection (recommended for immutability)
    public EmployeeItemWriter(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * The write() method is called by Spring Batch after each chunk of data
     * (for example, every 10 items if chunk size = 10 in the Step configuration).
     *
     * @param chunk - a list-like container holding the current group of Employees
     *                ready to be persisted to the database.
     */
    @Override
    public void write(Chunk<? extends Employee> chunk) {
        // Save all employees in a single batch operation (efficient DB insert/update)
        repository.saveAll(chunk.getItems());
    }
}