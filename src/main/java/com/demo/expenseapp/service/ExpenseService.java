package com.demo.expenseapp.service;

import com.demo.expenseapp.domain.dto.CategoryExpenseStatistics;
import com.demo.expenseapp.domain.dto.ExpenseDto;

import java.util.List;

/**
 * Provides operations for expense monitoring
 */
public interface ExpenseService {

    List<ExpenseDto> getAllExpenses();

    ExpenseDto getExpenseById(Long id);

    List<ExpenseDto> getExpensesByCategoryId(Long categoryId);

    List<CategoryExpenseStatistics> getCategoryExpenseStatistics(Integer year, Integer month, Integer day);
}
