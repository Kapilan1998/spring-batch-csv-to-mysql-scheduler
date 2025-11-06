package com.spring.batch.scheduler.springbatchscheduler.job;

import com.spring.batch.scheduler.springbatchscheduler.entity.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * EmployeeItemProcessor
 * This class defines how each Employee record from the CSV file
 * should be processed before being written to the database.
 *
 * In Spring Batch, an ItemProcessor acts as the "middle" step between:
 *    Reader (reads raw data)  →  Processor (transforms it)  →  Writer (saves it)
 */
@Component // Marks this class as a Spring-managed bean so it can be auto-detected
public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {

    /**
     * The process() method is called for each Employee item read by the Reader.
     * It allows you to apply transformations, validation, or filtering.
     *
     * In this example:
     *  - It converts the employee's name to uppercase before saving to the DB.
     *  - Returns the modified Employee object.
     *
     * If this method returns null, that record will be filtered out and not written.
     */
    @Override
    public Employee process(Employee employee) {
        // Transform the employee name to uppercase (simple business logic)
        employee.setName(employee.getName().toUpperCase());

        // set the current timestamp when data processing happened
        employee.setProcessedAt(LocalDateTime.now());
        // Return the transformed object for writing into the database
        return employee;
    }
}