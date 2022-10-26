package com.example.springboot.controller;

import com.example.springboot.exeption.ResourceNotFoundExeption;
import com.example.springboot.model.Employee;
import com.example.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();

    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    //getBy Id restAPI
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("EmployeeDoes Not Exists"));
        return ResponseEntity.ok(employee);

    }
    //buildUpdateEmployee
    @PutMapping({"{id}"})
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails){
        Employee updatedEmployee = employeeRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundExeption("EmployeeDoes Not Exists"));
        updatedEmployee.setFirstName(employeeDetails.getFirstName());
        updatedEmployee.setLastName(employeeDetails.getLastName());
        updatedEmployee.setEmailId(employeeDetails.getEmailId());
        employeeRepository.save(updatedEmployee);

        return ResponseEntity.ok(updatedEmployee);
    }
//build deleteEmployee restApi
    @DeleteMapping("{id}")
    public  ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id){
        Employee deleted = employeeRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundExeption("EmployeeDoes Not Exists"));
        employeeRepository.delete(deleted);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
