package com.suyash.employeemanagementsystem.Services.Impl;

import com.suyash.employeemanagementsystem.DTO.ApiResponseDTO;
import com.suyash.employeemanagementsystem.DTO.ApiResponseStatus;
import com.suyash.employeemanagementsystem.DTO.EmployeeDetailsRequestDTO;
import com.suyash.employeemanagementsystem.DTO.PaginationResponseDTO;
import com.suyash.employeemanagementsystem.Entity.Department;
import com.suyash.employeemanagementsystem.Entity.Employee;
import com.suyash.employeemanagementsystem.Exception.ResourceAlreadyExistsException;
import com.suyash.employeemanagementsystem.Exception.ResourceNotFoundException;
import com.suyash.employeemanagementsystem.Exception.ServiceLogicException;
import com.suyash.employeemanagementsystem.Repository.DepartmentRepository;
import com.suyash.employeemanagementsystem.Repository.EmployeeRepository;
import com.suyash.employeemanagementsystem.Services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Implementation class for the EmployeeService interface
 */
@Component
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository _employeeRepository;
    @Autowired
    private DepartmentRepository _departmentRepository;

    @Override
    public ResponseEntity<ApiResponseDTO<?>> createEmployee(EmployeeDetailsRequestDTO newEmployeeDetails)
            throws ResourceAlreadyExistsException, ServiceLogicException, ResourceNotFoundException {
        try {
            if (_employeeRepository.findByEmail(newEmployeeDetails.getEmail()) != null) {
                throw new ResourceAlreadyExistsException("Employee with email " + newEmployeeDetails.getEmail() + " already exists");
            }

            Department department = _departmentRepository.findById(newEmployeeDetails.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department with id " + newEmployeeDetails.getDepartmentId() + " not found"));

            // Create new employee
            Employee newEmployee = new Employee(
                    newEmployeeDetails.getFirstName(),
                    newEmployeeDetails.getLastName(),
                    newEmployeeDetails.getEmail(),
                    newEmployeeDetails.getPhone(),
                    newEmployeeDetails.getAddress(),
                    newEmployeeDetails.getJobTitle(),
                    newEmployeeDetails.getSalary(),
                    department
            );

            // save the new employee
            _employeeRepository.save(newEmployee);

            // return success response
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "Employee created successfully", newEmployee));
        } catch (ResourceNotFoundException e) {
            log.error("Error creating employee: " + e.getMessage());
            throw new ResourceNotFoundException("Resource not found: " + e.getMessage());
        } catch (ResourceAlreadyExistsException e) {
            log.error("Error creating employee: " + e.getMessage());
            throw new ResourceAlreadyExistsException("Resource already exists: " + e.getMessage());
        } catch (Exception e) {
            log.error("Error creating employee: " + e.getMessage());
            throw new ServiceLogicException("Error occurred while creating employee: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> getAllEmployees(Pageable pageable) throws ServiceLogicException, ResourceNotFoundException {
        try {
            Page<Employee> employeesPage = _employeeRepository.findAllEmployees(pageable);
            List<Employee> employees = employeesPage.getContent();

            PaginationResponseDTO<Employee> response = new PaginationResponseDTO<>();
            response.setData(employees);
            response.setTotalPages(employeesPage.getTotalPages());
            response.setTotalItems(employeesPage.getTotalElements());
            response.setCurrentPage(employeesPage.getNumber());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "Employees fetched successfully", response));
        } catch (Exception e) {
            throw new ServiceLogicException("Error occurred while fetching employees: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> getEmployeeById(UUID employeeId) throws ResourceNotFoundException, ServiceLogicException {
        try {
            Employee employee = _employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee with id " + employeeId + " not found"));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "Employee fetched successfully", employee));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error occurred while fetching employee: " + e.getMessage());
        } catch (Exception e) {
            throw new ServiceLogicException("Error occurred while fetching employee: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> updateEmployeeById(UUID employeeId, EmployeeDetailsRequestDTO updatedEmployeeDetails)
            throws ResourceNotFoundException, ServiceLogicException {
        try {
            Employee employee = _employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee with id " + employeeId + " not found"));

            Department department = _departmentRepository.findById(updatedEmployeeDetails.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department with id " + updatedEmployeeDetails.getDepartmentId() + " not found"));

            employee.setFirstName(updatedEmployeeDetails.getFirstName());
            employee.setLastName(updatedEmployeeDetails.getLastName());
            employee.setEmail(updatedEmployeeDetails.getEmail());
            employee.setPhone(updatedEmployeeDetails.getPhone());
            employee.setAddress(updatedEmployeeDetails.getAddress());
            employee.setJobTitle(updatedEmployeeDetails.getJobTitle());
            employee.setSalary(updatedEmployeeDetails.getSalary());
            employee.setDepartment(department);
            employee.setUpdatedAt(LocalDateTime.now());

            _employeeRepository.save(employee);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "Employee updated successfully", employee));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error occurred while updating employee: " + e.getMessage());
        } catch (Exception e) {
            throw new ServiceLogicException("Error occurred while updating employee: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> deleteEmployeeById(UUID employeeId) throws ResourceNotFoundException, ServiceLogicException {
        try {
            Employee employee = _employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee with id " + employeeId + " not found"));

            _employeeRepository.delete(employee);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "Employee deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error occurred while deleting employee: " + e.getMessage());
        } catch (Exception e) {
            throw new ServiceLogicException("Error occurred while deleting employee: " + e.getMessage());
        }
    }
}
