package com.sep.commonModule.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class BaseFilter {
    private int page = 1;
    private int size = 10;
    private String sortBy = "createdAt";
    private String sortDir = "desc";
    private String keyword;

    public Pageable getPageable() {
        int validPage = this.page > 0 ? this.page - 1 : 0;
        int validSize = this.size > 0 ? this.size : 10;
        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, this.sortBy);

        return PageRequest.of(validPage, validSize, sort);
    }
}