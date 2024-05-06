package com.suyash.employeemanagementsystem.Services;

import com.suyash.employeemanagementsystem.DTO.ApiResponseDTO;
import com.suyash.employeemanagementsystem.DTO.DepartmentDetailsRequestDTO;
import com.suyash.employeemanagementsystem.Exception.ResourceAlreadyExistsException;
import com.suyash.employeemanagementsystem.Exception.ResourceNotFoundException;
import com.suyash.employeemanagementsystem.Exception.ServiceLogicException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    /**
     * Method to create a new department
     *
     * @param newDepartmentDetails DepartmentDetailsRequestDTO object containing the details of the new department
     * @return ResponseEntity<ApiResponseDTO < ?>> containing the response details
     * @throws ResourceAlreadyExistsException if the department already exists
     * @throws ServiceLogicException          if there is an issue with the service logic
     */
    ResponseEntity<ApiResponseDTO<?>> createDepartment(DepartmentDetailsRequestDTO newDepartmentDetails)
            throws ResourceAlreadyExistsException, ServiceLogicException;

    /**
     * Method to get all departments
     *
     * @param pageable Pageable object containing the pagination details
     * @return ResponseEntity<ApiResponseDTO < ?>> containing the response details
     * @throws ServiceLogicException     if there is an issue with the service logic
     * @throws ResourceNotFoundException if the resource is not found
     */
    ResponseEntity<ApiResponseDTO<?>> getAllDepartments(Pageable pageable)
            throws ServiceLogicException, ResourceNotFoundException;

    /**
     * Method to get a department by id
     *
     * @param departmentId Long object containing the id of the department
     * @return ResponseEntity<ApiResponseDTO < ?>> containing the response details
     * @throws ResourceNotFoundException if the resource is not found
     * @throws ServiceLogicException     if there is an issue with the service logic
     */
    ResponseEntity<ApiResponseDTO<?>> getDepartmentById(Long departmentId)
            throws ResourceNotFoundException, ServiceLogicException;


    /**
     * Method to update a department by id
     *
     * @param departmentId             Long object containing the id of the department
     * @param updatedDepartmentDetails DepartmentDetailsRequestDTO object containing the updated details of the department
     * @return ResponseEntity<ApiResponseDTO < ?>> containing the response details
     * @throws ResourceNotFoundException if the resource is not found
     * @throws ServiceLogicException     if there is an issue with the service logic
     */
    ResponseEntity<ApiResponseDTO<?>> updateDepartmentById(Long departmentId, DepartmentDetailsRequestDTO updatedDepartmentDetails)
            throws ResourceNotFoundException, ServiceLogicException;

    /**
     * Method to delete a department by id
     *
     * @param departmentId Long object containing the id of the department
     * @return ResponseEntity<ApiResponseDTO < ?>> containing the response details
     * @throws ResourceNotFoundException if the resource is not found
     * @throws ServiceLogicException     if there is an issue with the service logic
     */
    ResponseEntity<ApiResponseDTO<?>> deleteDepartmentById(Long departmentId)
            throws ResourceNotFoundException, ServiceLogicException;

    /**
     * Method to get all employees in a department
     *
     * @param departmentId Long object containing the id of the department
     * @return ResponseEntity<ApiResponseDTO < ?>> containing the response details
     * @throws ServiceLogicException     if there is an issue with the service logic
     * @throws ResourceNotFoundException if the resource is not found
     */
    ResponseEntity<ApiResponseDTO<?>> getDepartmentEmployees(Long departmentId, Pageable pageable)
            throws ResourceNotFoundException, ServiceLogicException;
}
