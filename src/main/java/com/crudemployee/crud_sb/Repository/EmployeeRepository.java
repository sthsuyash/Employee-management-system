package com.crudemployee.crud_sb.Repository;

import com.crudemployee.crud_sb.Employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
