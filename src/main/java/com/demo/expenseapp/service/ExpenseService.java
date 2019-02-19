package com.demo.expenseapp.service;

import com.demo.expenseapp.domain.dto.CategoryExpenseStatistics;

import java.util.List;

/**
 * Provides operations for expense monitoring
 */
public interface ExpenseService {

    List<CategoryExpenseStatistics> getCategoryExpenseStatistics(Integer year, Integer month, Integer day);
}
