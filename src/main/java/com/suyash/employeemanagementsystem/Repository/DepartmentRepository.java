package com.suyash.employeemanagementsystem.Repository;

import com.suyash.employeemanagementsystem.Entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, PagingAndSortingRepository<Department, Long> {
    Department findByName(String name);

    @Query("SELECT d FROM Department d ORDER BY d.name DESC")
    Page<Department> findAllOrdered(Pageable pageable);
}
