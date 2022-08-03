package com.crudemployee.crud_sb.Controller;

import com.crudemployee.crud_sb.Employee.Employee;
import com.crudemployee.crud_sb.Exception.ResourceNotFoundException;
import com.crudemployee.crud_sb.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeRepository _employeeRepository;

    // returns list of all the employees
    @GetMapping(path = "/employees")
    public List<Employee> getAllEmployees() {
        return _employeeRepository.findAll();
    }

    // create new user
    @PostMapping(path = "/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return _employeeRepository.save(employee);
    }

    // get certain employee by id
    @GetMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = _employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist with id: " + id + "."));
        return ResponseEntity.ok(employee);
    }

    // update employee by id
    @PutMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeToEdit) {
        Employee employee = _employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist with id: " + id + "."));

        employee.setFirst_name(employeeToEdit.getFirst_name());
        employee.setLast_name(employeeToEdit.getLast_name());
        employee.setEmail(employeeToEdit.getEmail());

        Employee updatedEmployee = _employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }


    // delete certain employee by id
    @DeleteMapping(path = "employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        Employee employee = _employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist with id: " + id + "."));

        _employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
