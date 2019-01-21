package com.demo.expenseapp.repository;

import com.demo.expenseapp.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // todo: add custom methods for different reports

}
