package com.demo.expenseapp.repository;

import com.demo.expenseapp.domain.Expense;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@SpringBootTest
@DatabaseSetup(value = {"classpath:datasets/it-categories.xml", "classpath:datasets/it-expenses.xml"})
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = {"classpath:datasets/it-expenses.xml"})
@DirtiesContext
@TestPropertySource(locations = "classpath:it-test.properties")
public class ExpenseRepositoryIT {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void testFindByYearAndMonth() {
        List<Expense> expenses = expenseRepository.findExpensesByYearAndMonth(2019, 1);
        assertEquals(2, expenses.size());
        //todo: provide more checks
    }

}