package com.suyash.employeemanagementsystem.Utils;

import com.suyash.employeemanagementsystem.Entity.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeResponseUtil {
    public static List<Map<String, Object>> serializeEmployees(List<Object[]> employees) {
        return employees.stream().map(EmployeeResponseUtil::serializeEmployeeList).collect(Collectors.toList());
    }

    public static Map<String, Object> serializeEmployees(Employee employee) {
        Map<String, Object> serialized = new HashMap<>();
        serialized.put("id", employee.getId());
        serialized.put("firstName", employee.getFirstName());
        serialized.put("lastName", employee.getLastName());
        serialized.put("email", employee.getEmail());
        serialized.put("phone", employee.getPhone());
        serialized.put("address", employee.getAddress());
        serialized.put("jobTitle", employee.getJobTitle());
        serialized.put("salary", employee.getSalary());
        serialized.put("department", employee.getDepartmentName());
        return serialized;
    }

    private static Map<String, Object> serializeEmployeeList(Object[] employeeArray) {
        Map<String, Object> serialized = new HashMap<>();
        serialized.put("id", employeeArray[0]);
        serialized.put("firstName", employeeArray[1]);
        serialized.put("lastName", employeeArray[2]);
        serialized.put("email", employeeArray[3]);
        serialized.put("phone", employeeArray[4]);
        serialized.put("address", employeeArray[5]);
        serialized.put("jobTitle", employeeArray[6]);
        serialized.put("salary", employeeArray[7]);
        serialized.put("department", employeeArray[8]);
        return serialized;
    }
}
