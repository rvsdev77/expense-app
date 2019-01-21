package com.demo.expenseapp.controller;

import com.demo.expenseapp.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

//todo: logging, exception handling
@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = Objects.requireNonNull(expenseService, "expenseService could not be null");
    }
}
