package com.suyash.employeemanagementsystem.Repository;

import com.suyash.employeemanagementsystem.Entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID>, PagingAndSortingRepository<Employee, UUID> {
    /**
     * we can define methods in repository interfaces with custom query
     * and Spring Data JPA automatically tranlates them into appropriate SQL queries
     */
    Employee findByEmail(String email);

    @Query("SELECT e FROM Employee e ORDER BY e.updatedAt ASC, e.createdAt ASC")
    Page<Employee> findAllEmployees(Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId ORDER BY e.updatedAt ASC, e.createdAt ASC")
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department.id = :departmentId")
    Long countByDepartmentId(Long departmentId);
}
