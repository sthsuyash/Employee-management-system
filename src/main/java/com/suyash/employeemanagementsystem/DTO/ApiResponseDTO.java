package com.suyash.employeemanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDTO<T> {
    private String status;
    private String message;
    private T response;

    public ApiResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
