package com.demo.expenseapp.controller;

import com.demo.expenseapp.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

//todo logging, exception handling
@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = Objects.requireNonNull(categoryService, "categoryService could not be null");
    }
}
