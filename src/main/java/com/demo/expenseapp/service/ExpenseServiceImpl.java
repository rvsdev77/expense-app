package com.demo.expenseapp.service;

import com.demo.expenseapp.domain.dto.CategoryExpenseStatistics;
import com.demo.expenseapp.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseServiceImpl.class);

    private ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = Objects.requireNonNull(expenseRepository, "expenseRepository could not be null");
    }

    @Override
    public List<CategoryExpenseStatistics> getCategoryExpenseStatistics(Integer year, Integer month, Integer day) {

        return expenseRepository.getCategoryExpenseStatistics(year, month, day);
    }
}
