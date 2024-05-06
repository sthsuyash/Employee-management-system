package com.suyash.employeemanagementsystem.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDetailsRequestDTO {
    @NotBlank(message = "Department name cannot be blank")
    private String name;
}
