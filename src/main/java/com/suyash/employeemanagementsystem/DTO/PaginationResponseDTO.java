package com.suyash.employeemanagementsystem.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PaginationResponseDTO<T> {
    private List<T> data;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}
