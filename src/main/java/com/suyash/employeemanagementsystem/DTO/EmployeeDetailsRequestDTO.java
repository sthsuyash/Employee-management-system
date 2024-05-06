package com.suyash.employeemanagementsystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsRequestDTO {
    @NotBlank(message = "Employee first name cannot be blank")
    @Size(min = 2, message = "Employee first name should have atleast 2 characters")
    private String firstName;

    @NotBlank(message = "Employee last name cannot be blank")
    @Size(min = 2, message = "Employee last name should have atleast 2 characters")
    private String lastName;

    @NotBlank(message = "Employee email cannot be blank")
    @Email
    private String email;

    @Size(min = 10, max = 10, message = "Phone number should be of 10 digits")
    @Pattern(regexp = "^[0-9]*$", message = "Phone number should contain only digits")
    private String phone;

    private String address;

    private String jobTitle;

    private Double salary;

    private Long departmentId;
}
