package com.spring.batch.scheduler.springbatchscheduler.job;

import com.spring.batch.scheduler.springbatchscheduler.entity.Employee;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 * Custom FlatFileItemReader for reading Employee data from a CSV file.
 * Reads each line from the file, maps it to an Employee object.
 */
@Component
public class EmployeeItemReader extends FlatFileItemReader<Employee> {

    public EmployeeItemReader(
            // Reads the CSV file path from src/main/resources/data/employees.csv in this project folder
            @Value("${input.file.path:src/main/resources/data/employees.csv}")
            String inputFilePath) {

        // Set the CSV file path
        setResource(new FileSystemResource(inputFilePath));

        // Skip the header line (first row)
        setLinesToSkip(1);

        // Ensure records are properly separated by newlines
        setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());

        // Define how to tokenize and map each line to an Employee object
        setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("name", "email", "salary");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(Employee.class);
            }});
        }});
    }
}