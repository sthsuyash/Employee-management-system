package com.suyash.employeemanagementsystem.Controller;

import com.suyash.employeemanagementsystem.DTO.ApiResponseDTO;
import com.suyash.employeemanagementsystem.DTO.DepartmentDetailsRequestDTO;
import com.suyash.employeemanagementsystem.Exception.ResourceAlreadyExistsException;
import com.suyash.employeemanagementsystem.Exception.ResourceNotFoundException;
import com.suyash.employeemanagementsystem.Exception.ServiceLogicException;
import com.suyash.employeemanagementsystem.Services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * Retrieve a list of departments
     *
     * @param pageable Pagination information
     * @return ResponseEntity containing ApiResponseDTO with list of departments
     * @throws ServiceLogicException If an error occurs while fetching departments
     */
    @GetMapping(path = "")
    public ResponseEntity<ApiResponseDTO<?>> getAllDepartments(Pageable pageable)
            throws ServiceLogicException {
        return departmentService.getAllDepartments(pageable);
    }

    /**
     * Create a new department
     *
     * @param department Department details to be created
     * @return ResponseEntity containing ApiResponseDTO with the newly created department
     * @throws ResourceAlreadyExistsException If a department with the same name already exists
     * @throws ServiceLogicException          If an error occurs while creating the department
     * @throws ResourceNotFoundException      If the department with the given id does not exist
     */
    @PostMapping(path = "")
    public ResponseEntity<ApiResponseDTO<?>> createDepartment(@Valid @RequestBody DepartmentDetailsRequestDTO department)
            throws ResourceAlreadyExistsException, ServiceLogicException, ResourceNotFoundException {
        return departmentService.createDepartment(department);
    }

    /**
     * Retrieve a department by its id
     *
     * @param id ID of the department to be fetched
     * @return ResponseEntity containing ApiResponseDTO with the department
     * @throws ResourceNotFoundException If the department with the given id does not exist
     * @throws ServiceLogicException     If an error occurs while fetching the department
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponseDTO<?>> getDepartmentById(@PathVariable("id") Long id)
            throws ResourceNotFoundException, ServiceLogicException {
        return departmentService.getDepartmentById(id);
    }

    /**
     * Update a department by its id
     *
     * @param id         ID of the department to be updated
     * @param department Department details to be updated
     * @return ResponseEntity containing ApiResponseDTO with the updated department
     * @throws ResourceNotFoundException If the department with the given id does not exist
     * @throws ServiceLogicException     If an error occurs while updating the department
     */
    @PatchMapping(path = "/{id}")
    public ResponseEntity<ApiResponseDTO<?>> updateDepartmentById(@PathVariable("id") Long id, @Valid @RequestBody DepartmentDetailsRequestDTO department)
            throws ResourceNotFoundException, ServiceLogicException {
        return departmentService.updateDepartmentById(id, department);
    }

    /**
     * Delete a department by its id
     *
     * @param id ID of the department to be deleted
     * @return ResponseEntity containing ApiResponseDTO with the deleted department
     * @throws ResourceNotFoundException If the department with the given id does not exist
     * @throws ServiceLogicException     If an error occurs while deleting the department
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponseDTO<?>> deleteDepartmentById(@PathVariable("id") Long id)
            throws ResourceNotFoundException, ServiceLogicException {
        return departmentService.deleteDepartmentById(id);
    }

    @GetMapping(path = "/{id}/employees")
    public ResponseEntity<ApiResponseDTO<?>> getEmployeesByDepartmentId(@PathVariable("id") Long id, Pageable pageable)
            throws ResourceNotFoundException, ServiceLogicException {
        return departmentService.getDepartmentEmployees(id, pageable);
    }
}
