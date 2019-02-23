package com.demo.expenseapp.service;

import com.demo.expenseapp.domain.dto.CategoryDto;

import java.util.List;

/**
 * Operations related to expense categories
 */
public interface CategoryService {

    /**
     * Retrieves all expense categories
     */
    List<CategoryDto> getAll();

    /**
     * Saves category
     */
    CategoryDto saveCategory(CategoryDto categoryDto);

    /**
     * Deletes expense category by id
     */
    void deleteCategory(long id);

    /**
     * Retrieves expense category by id
     */
    CategoryDto getById(long id);

}
