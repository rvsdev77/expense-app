package com.demo.expenseapp.service;

import com.demo.expenseapp.domain.Expense;
import com.demo.expenseapp.domain.converter.ExpenseToExpenseDto;
import com.demo.expenseapp.domain.dto.CategoryExpenseStatistics;
import com.demo.expenseapp.domain.dto.ExpenseDto;
import com.demo.expenseapp.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseServiceImpl.class);

    private ExpenseRepository expenseRepository;
    private ExpenseToExpenseDto toExpenseDto;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseToExpenseDto toExpenseDto) {
        this.expenseRepository = Objects.requireNonNull(expenseRepository, "expenseRepository could not be null");
        this.toExpenseDto = Objects.requireNonNull(toExpenseDto, "toExpenseDto should not be null");
    }

    @Override
    public List<CategoryExpenseStatistics> getCategoryExpenseStatistics(Integer year, Integer month, Integer day) {

        return expenseRepository.getCategoryExpenseStatistics(year, month, day);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();

        return expenses.stream()
                .map(e -> toExpenseDto.convert(e))
                .collect(Collectors.toList());

    }
}
