package com.demo.expenseapp.service;

import com.demo.expenseapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {
    //todo: logging
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = Objects.requireNonNull(categoryRepository, "categoryRepository should not be null");
    }

}
