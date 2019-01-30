package com.demo.expenseapp.repository;

import com.demo.expenseapp.domain.Expense;
import com.demo.expenseapp.domain.dto.CategoryExpenseStatistics;
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

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        assertThat(expenses)
                .hasSize(2)
                .extracting(e -> e.getCategory().getCategoryName())
                .contains("TAXI", "CINEMA");
    }

    @Test
    public void testFindExpensesByCategoryAndYear() {
        List<Expense> expenses = expenseRepository.findExpensesByCategoryAndYear(2018, "OTHER");
        assertThat(expenses)
                .hasSize(2)
                .extracting(e -> e.getCategory().getCategoryName())
                .containsOnly("OTHER");
    }

    @Test
    public void testGetTotalSpentForCategory() {
        BigDecimal total = expenseRepository.getTotalSpentForCategory("OTHER");
        BigDecimal expected = new BigDecimal("152.27");
        assertEquals(expected, total);
    }

    @Test
    public void testGetTotalSpentForCategoryForYear() {
        BigDecimal total = expenseRepository.getTotalSpentForCategoryForYear("OTHER", 2019);
        BigDecimal expected = new BigDecimal("81.00");
        assertEquals(expected, total);
    }

    @Test
    public void testGetTotalSpentForCategoryForMonth() {
        BigDecimal total = expenseRepository.getTotalSpentForMonth(2018, 7);
        BigDecimal expected = new BigDecimal("136.62");
        assertEquals(expected, total);
    }

    @Test
    public void testGetAmountSpentByCategory() {
        List<CategoryExpenseStatistics> statistics = expenseRepository.getSpentByCategory();
        assertNotNull(statistics);
    }
}