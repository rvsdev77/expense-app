package com.demo.expenseapp.service;

import com.demo.expenseapp.domain.dto.CategoryExpenseStatistics;
import com.demo.expenseapp.domain.dto.ExpenseDto;

import java.util.List;

/**
 * Provides operations for expense monitoring
 */
public interface ExpenseService {

    List<CategoryExpenseStatistics> getCategoryExpenseStatistics(Integer year, Integer month, Integer day);

    List<ExpenseDto> getAllExpenses();
}
