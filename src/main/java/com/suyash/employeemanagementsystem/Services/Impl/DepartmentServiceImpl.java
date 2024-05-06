package com.suyash.employeemanagementsystem.Services.Impl;

import com.suyash.employeemanagementsystem.DTO.ApiResponseDTO;
import com.suyash.employeemanagementsystem.DTO.ApiResponseStatus;
import com.suyash.employeemanagementsystem.DTO.DepartmentDetailsRequestDTO;
import com.suyash.employeemanagementsystem.DTO.PaginationResponseDTO;
import com.suyash.employeemanagementsystem.Entity.Department;
import com.suyash.employeemanagementsystem.Entity.Employee;
import com.suyash.employeemanagementsystem.Exception.ResourceAlreadyExistsException;
import com.suyash.employeemanagementsystem.Exception.ResourceNotFoundException;
import com.suyash.employeemanagementsystem.Exception.ServiceLogicException;
import com.suyash.employeemanagementsystem.Repository.DepartmentRepository;
import com.suyash.employeemanagementsystem.Repository.EmployeeRepository;
import com.suyash.employeemanagementsystem.Services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation class for the DepartmentService interface
 */
@Component
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository _departmentRepository;
    @Autowired
    private EmployeeRepository _employeeRepository;

    @Override
    public ResponseEntity<ApiResponseDTO<?>> createDepartment(DepartmentDetailsRequestDTO newDepartmentDetails)
            throws ResourceAlreadyExistsException, ServiceLogicException {
        try {
            if (_departmentRepository.findByName(newDepartmentDetails.getName()) != null) {
                throw new ResourceAlreadyExistsException("Department with name " + newDepartmentDetails.getName() + " already exists");
            }

            // Create new department
            Department newDepartment = new Department(
                    newDepartmentDetails.getName()
            );

            // save the new department
            _departmentRepository.save(newDepartment);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "Department created successfully", newDepartment));
        } catch (ResourceAlreadyExistsException e) {
            log.error("Error creating department: " + e.getMessage());
            throw new ResourceAlreadyExistsException("Department with name " + newDepartmentDetails.getName() + " already exists");
        } catch (Exception e) {
            log.error("Error creating department: " + e.getMessage());
            throw new ServiceLogicException("Error creating department");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> getAllDepartments(Pageable pageable) throws ServiceLogicException, ResourceNotFoundException {
        try {
            Page<Department> departments = _departmentRepository.findAllOrdered(pageable);
            List<Department> departmentList = departments.getContent();

            PaginationResponseDTO<Department> response = new PaginationResponseDTO<>();
            response.setData(departmentList);
            response.setTotalPages(departments.getTotalPages());
            response.setTotalItems(departments.getTotalElements());
            response.setCurrentPage(departments.getNumber());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "Departments fetched successfully", response));
        } catch (Exception e) {
            log.error("Error fetching departments: " + e.getMessage());
            throw new ServiceLogicException("Error fetching departments");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> getDepartmentById(Long departmentId) throws ResourceNotFoundException, ServiceLogicException {
        try {
            Department department = _departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department with id " + departmentId + " not found"));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "Department fetched successfully", department));
        } catch (ResourceNotFoundException e) {
            log.error("Error fetching department: " + e.getMessage());
            throw new ResourceNotFoundException("Department with id " + departmentId + " not found");
        } catch (Exception e) {
            log.error("Error fetching department: " + e.getMessage());
            throw new ServiceLogicException("Error fetching department");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> updateDepartmentById(Long departmentId, DepartmentDetailsRequestDTO updatedDepartmentDetails) throws ResourceNotFoundException, ServiceLogicException {
        try {
            Department department = _departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department with id " + departmentId + " not found"));

            department.setName(updatedDepartmentDetails.getName());
            department.setUpdatedAt(LocalDateTime.now());

            _departmentRepository.save(department);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "Department updated successfully", department));
        } catch (ResourceNotFoundException e) {
            log.error("Error updating department: " + e.getMessage());
            throw new ResourceNotFoundException("Department with id " + departmentId + " not found");
        } catch (Exception e) {
            log.error("Error updating department: " + e.getMessage());
            throw new ServiceLogicException("Error updating department");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> deleteDepartmentById(Long departmentId) throws ResourceNotFoundException, ServiceLogicException {
        try {
            Department department = _departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department with id " + departmentId + " not found"));

            _departmentRepository.delete(department);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "Department deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            log.error("Error deleting department: " + e.getMessage());
            throw new ResourceNotFoundException("Department with id " + departmentId + " not found");
        } catch (Exception e) {
            log.error("Error deleting department: " + e.getMessage());
            throw new ServiceLogicException("Error deleting department");
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> getDepartmentEmployees(Long departmentId, Pageable pageable) throws ResourceNotFoundException, ServiceLogicException {
        try {
            Department department = _departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department with id " + departmentId + " not found"));

            Page<Employee> employees = _employeeRepository.findByDepartmentId(departmentId, pageable);

            // Count total employees for the department
            long totalEmployees = _employeeRepository.countByDepartmentId(departmentId);

            PaginationResponseDTO<Employee> response = new PaginationResponseDTO<>();
            response.setData(employees.getContent());
            response.setTotalPages(employees.getTotalPages());
            response.setTotalItems(totalEmployees);
            response.setCurrentPage(employees.getNumber());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "Employees fetched successfully", response));
        } catch (ResourceNotFoundException e) {
            log.error("Error fetching employees: " + e.getMessage());
            throw e; // Let the calling method handle this
        } catch (Exception e) {
            log.error("Error fetching employees: " + e.getMessage(), e);
            throw new ServiceLogicException("Error fetching employees" + e);
        }
    }
}
