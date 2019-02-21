package com.demo.expenseapp.controller;

import com.demo.expenseapp.domain.dto.CategoryExpenseStatistics;
import com.demo.expenseapp.domain.dto.ExpenseDto;
import com.demo.expenseapp.exeptions.NotFoundException;
import com.demo.expenseapp.service.ExpenseService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

//todo: logging, exception handling
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = Objects.requireNonNull(expenseService, "expenseService could not be null");
    }

    @GetMapping
    public List<ExpenseDto> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/statistics")
    public List<CategoryExpenseStatistics> getCategoryExpenseStatistics(@RequestParam(value = "year", required = false) Integer year,
                                                                        @RequestParam(value = "month", required = false) Integer month,
                                                                        @RequestParam(value = "day", required = false) Integer day) {
        List<CategoryExpenseStatistics> expenses = expenseService.getCategoryExpenseStatistics(year, month, day);
        if (CollectionUtils.isEmpty(expenses)) {
            throw new NotFoundException("No items were found");
        }
        return expenses;
    }
}
