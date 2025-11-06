package com.spring.batch.scheduler.springbatchscheduler.repository;

import com.spring.batch.scheduler.springbatchscheduler.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
