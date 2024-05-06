package com.suyash.employeemanagementsystem.Controller;

import com.suyash.employeemanagementsystem.DTO.ApiResponseDTO;
import com.suyash.employeemanagementsystem.DTO.EmployeeDetailsRequestDTO;
import com.suyash.employeemanagementsystem.Exception.ServiceLogicException;
import com.suyash.employeemanagementsystem.Exception.ResourceAlreadyExistsException;
import com.suyash.employeemanagementsystem.Exception.ResourceNotFoundException;
import com.suyash.employeemanagementsystem.Services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller class for handling employee related requests
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    /**
     * Retrieve a list of employees in paginated form
     *
     * @param pageable Pagination information
     * @return ResponseEntity containing ApiResponseDTO with list of employees
     * @throws ServiceLogicException If an error occurs while fetching employees
     */
    @GetMapping(path = "")
    public ResponseEntity<ApiResponseDTO<?>> getAllEmployees(Pageable pageable)
            throws ServiceLogicException {
        return employeeService.getAllEmployees(pageable);
    }

    /**
     * Create a new employee
     *
     * @param employee Employee details to be created
     * @return ResponseEntity containing ApiResponseDTO with the newly created employee
     * @throws ResourceAlreadyExistsException If an employee with the same email already exists
     * @throws ServiceLogicException          If an error occurs while creating the employee
     * @throws ResourceNotFoundException      If the department with the given id does not exist
     */
    @PostMapping(path = "")
    public ResponseEntity<ApiResponseDTO<?>> createEmployee(@Valid @RequestBody EmployeeDetailsRequestDTO employee)
            throws ResourceAlreadyExistsException, ServiceLogicException, ResourceNotFoundException {
        return employeeService.createEmployee(employee);
    }

    /**
     * Retrieve an employee by their id
     *
     * @param id ID of the employee to be fetched
     * @return ResponseEntity containing ApiResponseDTO with the employee
     * @throws ServiceLogicException If an error occurs while fetching the employee
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponseDTO<?>> getEmployeeById(@PathVariable UUID id) throws ServiceLogicException {
        return employeeService.getEmployeeById(id);
    }

    /**
     * Update an employee by their id
     *
     * @param id                     ID of the employee to be updated
     * @param updatedEmployeeDetails Updated details of the employee
     * @return ResponseEntity containing ApiResponseDTO with the updated employee
     * @throws ServiceLogicException If an error occurs while updating the employee
     */
    @PatchMapping(path = "/{id}")
    public ResponseEntity<ApiResponseDTO<?>> updateEmployeeById(@PathVariable UUID id, @Valid @RequestBody EmployeeDetailsRequestDTO updatedEmployeeDetails) throws ServiceLogicException {
        return employeeService.updateEmployeeById(id, updatedEmployeeDetails);
    }

    /**
     * Delete an employee by their id
     *
     * @param id ID of the employee to be deleted
     * @return ResponseEntity containing ApiResponseDTO with the deleted employee
     * @throws ServiceLogicException If an error occurs while deleting the employee
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponseDTO<?>> deleteEmployeeById(@PathVariable UUID id) throws ServiceLogicException {
        return employeeService.deleteEmployeeById(id);
    }
}
