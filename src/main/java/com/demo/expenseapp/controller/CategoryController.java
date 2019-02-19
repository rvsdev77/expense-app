package com.demo.expenseapp.controller;

import com.demo.expenseapp.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

//todo logging, exception handling
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = Objects.requireNonNull(categoryService, "categoryService could not be null");
    }
}
