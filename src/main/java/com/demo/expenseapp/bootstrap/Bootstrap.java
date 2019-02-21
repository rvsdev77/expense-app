package com.demo.expenseapp.bootstrap;

import com.demo.expenseapp.domain.Category;
import com.demo.expenseapp.domain.Expense;
import com.demo.expenseapp.repository.CategoryRepository;
import com.demo.expenseapp.repository.ExpenseRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

// introduced only for initial data population
//todo: should be removed after persistent storage is introduced. mysql docker image?
//@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private ExpenseRepository expenseRepository;

    public Bootstrap(CategoryRepository categoryRepository, ExpenseRepository expenseRepository) {
        this.categoryRepository = Objects.requireNonNull(categoryRepository, "categoryRepository should not be null");
        this.expenseRepository = Objects.requireNonNull(expenseRepository, "expenseRepository should not be null");
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Category groccery = new Category();
        groccery.setCategoryName("Groccery");

        Category rent = new Category();
        rent.setCategoryName("Rent");

        Category lunch = new Category();
        lunch.setCategoryName("Lunch");

        Expense exp1 = new Expense();
        exp1.setCategory(groccery);
        exp1.setAmountSpent(BigDecimal.valueOf(10.55));
        exp1.setDescription("Groceries");
        exp1.setExpenseDate(new Date());

        Expense exp2 = new Expense();
        exp2.setCategory(rent);
        exp2.setAmountSpent(BigDecimal.valueOf(100.00));
        exp2.setDescription("Rent");
        exp2.setExpenseDate(new Date());

        Expense exp3 = new Expense();
        exp3.setCategory(lunch);
        exp3.setAmountSpent(BigDecimal.valueOf(1.00));
        exp3.setDescription("Lunch");
        exp3.setExpenseDate(new Date());

        categoryRepository.saveAll(Arrays.asList(groccery, rent, lunch));
        expenseRepository.saveAll(Arrays.asList(exp1, exp2, exp3));
    }
}
