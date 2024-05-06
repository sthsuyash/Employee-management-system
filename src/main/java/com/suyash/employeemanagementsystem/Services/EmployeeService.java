package com.suyash.employeemanagementsystem.Services;

import com.suyash.employeemanagementsystem.DTO.ApiResponseDTO;
import com.suyash.employeemanagementsystem.DTO.EmployeeDetailsRequestDTO;
import com.suyash.employeemanagementsystem.Exception.ResourceAlreadyExistsException;
import com.suyash.employeemanagementsystem.Exception.ResourceNotFoundException;
import com.suyash.employeemanagementsystem.Exception.ServiceLogicException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * EmployeeService interface to define the methods for EmployeeService implementation
 */
@Service
public interface EmployeeService {
    /**
     * Method to create a new employee
     *
     * @param newEmployeeDetails EmployeeDetailsRequestDTO object containing the details of the new employee
     * @return ResponseEntity<ApiResponseDTO < ?>> containing the response details
     * @throws ResourceAlreadyExistsException if the employee already exists
     * @throws ServiceLogicException          if there is an issue with the service logic
     * @throws ResourceNotFoundException      if the resource is not found
     */
    ResponseEntity<ApiResponseDTO<?>> createEmployee(EmployeeDetailsRequestDTO newEmployeeDetails)
            throws ResourceAlreadyExistsException, ServiceLogicException, ResourceNotFoundException;

    /**
     * Method to get all employees
     *
     * @param pageable Pageable object containing the pagination details
     * @return ResponseEntity<ApiResponseDTO < ?>> containing the response details
     * @throws ServiceLogicException     if there is an issue with the service logic
     * @throws ResourceNotFoundException if the resource is not found
     */
    ResponseEntity<ApiResponseDTO<?>> getAllEmployees(Pageable pageable)
            throws ServiceLogicException, ResourceNotFoundException;

    /**
     * Method to get an employee by id
     *
     * @param employeeId UUID object containing the id of the employee
     * @return ResponseEntity<ApiResponseDTO < ?>> containing the response details
     * @throws ResourceNotFoundException if the resource is not found
     * @throws ServiceLogicException     if there is an issue with the service logic
     */
    ResponseEntity<ApiResponseDTO<?>> getEmployeeById(UUID employeeId)
            throws ResourceNotFoundException, ServiceLogicException;

    /**
     * Method to update an employee by id
     *
     * @param employeeId             UUID object containing the id of the employee
     * @param updatedEmployeeDetails EmployeeDetailsRequestDTO object containing the updated details of the employee
     * @return ResponseEntity<ApiResponseDTO < ?>> containing the response details
     * @throws ResourceNotFoundException if the resource is not found
     * @throws ServiceLogicException     if there is an issue with the service logic
     */
    ResponseEntity<ApiResponseDTO<?>> updateEmployeeById(UUID employeeId, EmployeeDetailsRequestDTO updatedEmployeeDetails)
            throws ResourceNotFoundException, ServiceLogicException;

    /**
     * Method to delete an employee by id
     *
     * @param employeeId UUID object containing the id of the employee
     * @return ResponseEntity<ApiResponseDTO < ?>> containing the response details
     * @throws ResourceNotFoundException if the resource is not found
     * @throws ServiceLogicException     if there is an issue with the service logic
     */
    ResponseEntity<ApiResponseDTO<?>> deleteEmployeeById(UUID employeeId)
            throws ResourceNotFoundException, ServiceLogicException;
}
