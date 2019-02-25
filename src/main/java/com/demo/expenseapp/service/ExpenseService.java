package com.demo.expenseapp.service;

import com.demo.expenseapp.domain.dto.CategoryExpenseStatistics;
import com.demo.expenseapp.domain.dto.ExpenseDto;

import java.util.List;

/**
 * Provides operations for expense monitoring
 */
public interface ExpenseService {

    ExpenseDto addExpense(ExpenseDto expenseDto);

    void deleteExpense(Long expenseId);

    List<ExpenseDto> getAllExpenses();

    ExpenseDto getExpenseById(Long id);

    List<ExpenseDto> getExpensesByCategoryId(Long categoryId);

    List<ExpenseDto> getExpensesByPeriod(Integer year, Integer month, Integer day);

    List<ExpenseDto> getCategoryExpensesByPeriod(Long categoryId, Integer year, Integer month, Integer day);

    List<CategoryExpenseStatistics> getCategoryExpenseStatistics(Integer year, Integer month, Integer day);
}
