package com.suyash.employeemanagementsystem.Handler;

import com.suyash.employeemanagementsystem.DTO.ApiResponseDTO;
import com.suyash.employeemanagementsystem.DTO.ApiResponseStatus;
import com.suyash.employeemanagementsystem.Exception.ResourceAlreadyExistsException;
import com.suyash.employeemanagementsystem.Exception.ResourceNotFoundException;
import com.suyash.employeemanagementsystem.Exception.ServiceLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class EmployeeServiceExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<?>> ResourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDTO<>(ApiResponseStatus.FAIL.name(), exception.getMessage(), null));
    }

    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDTO<?>> ResourceAlreadyExistsExceptionHandler(ResourceAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseDTO<>(ApiResponseStatus.FAIL.name(), exception.getMessage(), null));
    }

    @ExceptionHandler(value = ServiceLogicException.class)
    public ResponseEntity<ApiResponseDTO<?>> ServiceLogicExceptionHandler(ServiceLogicException exception) {
        return ResponseEntity.badRequest().body(new ApiResponseDTO<>(ApiResponseStatus.FAIL.name(), exception.getMessage(), null));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<?>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        List<String> errorMessage = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.add(error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(new ApiResponseDTO<>(ApiResponseStatus.FAIL.name(), errorMessage.toString(), null));
    }
}