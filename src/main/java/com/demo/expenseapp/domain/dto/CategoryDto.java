package com.demo.expenseapp.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class CategoryDto {

    @Getter
    @Setter
    private Long categoryId;

    @Getter
    @Setter
    private String categoryName;

    @Getter
    @Setter
    private String description;
}
