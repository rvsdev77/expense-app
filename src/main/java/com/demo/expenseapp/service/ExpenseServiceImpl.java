package com.demo.expenseapp.service;

import com.demo.expenseapp.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = Objects.requireNonNull(expenseRepository, "expenseRepository could not be null");
    }
}
