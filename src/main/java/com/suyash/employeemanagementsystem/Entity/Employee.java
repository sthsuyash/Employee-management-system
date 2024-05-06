package com.suyash.employeemanagementsystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employee",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank()
    private String firstName;

    @NotBlank()
    private String lastName;

    @NotBlank()
    @Email
    private String email;

    @Size(min = 10, max = 10)
    private String phone;

    private String address;

    private String jobTitle;

    private Double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    /**
     * Constructor for not all the fields
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     * @param address
     * @param jobTitle
     * @param salary
     * @param department
     */
    public Employee(String firstName, String lastName, String email, String phone, String address, String jobTitle, Double salary, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.department = department;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Get the department name
     * @return department.name
     */
    public String getDepartmentName(){
        return this.department != null ? this.department.getName() : null;
    }

}
