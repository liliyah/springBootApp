package com.example.springboot.repository;

import com.example.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
    //all crud database methodes
}
