package com.demo.expenseapp.service;

import com.demo.expenseapp.domain.Category;
import com.demo.expenseapp.domain.Expense;
import com.demo.expenseapp.domain.converter.ExpenseDtoToExpense;
import com.demo.expenseapp.domain.converter.ExpenseToExpenseDto;
import com.demo.expenseapp.domain.dto.CategoryDto;
import com.demo.expenseapp.domain.dto.ExpenseDto;
import com.demo.expenseapp.repository.ExpenseRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ExpenseServiceImplTest {

    @Mock
    private ExpenseRepository repository;

    @Mock
    private ExpenseDtoToExpense toExpense;

    @Mock
    private ExpenseToExpenseDto toExpenseDto;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private ExpenseService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.service = new ExpenseServiceImpl(repository, toExpenseDto, toExpense);
    }

    @Test
    public void getCategoryExpenseStatistics() {
    }

    @Test
    public void testAddExpense_throwsNpe() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Cannot save null object as an expense");
        service.addExpense(null);
    }

    @Test
    public void addExpense() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1L);
        categoryDto.setCategoryName("Category");

        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Category");

        BigDecimal amountSpent = new BigDecimal(100);
        Date date = new Date();

        ExpenseDto dto = new ExpenseDto();
        dto.setCategory(categoryDto);
        dto.setAmountSpent(amountSpent);
        dto.setDate(date);

        Expense expense = new Expense();
        expense.setCategory(category);
        expense.setAmountSpent(amountSpent);
        expense.setExpenseDate(date);

        when(toExpense.convert(dto)).thenReturn(expense);
        when(repository.save(expense)).thenReturn(expense);
        when(toExpenseDto.convert(expense)).thenReturn(dto);

        ExpenseDto result = service.addExpense(dto);

        verify(repository, times(1)).save(expense);
        assertEquals(categoryDto, result.getCategory());
        assertEquals(amountSpent, result.getAmountSpent());
        assertEquals(date, result.getDate());
    }

    @Test
    public void testDeleteExpense_throwsNpe() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("expenseId cannot be null for delete operation");
        service.deleteExpense(null);
    }

    @Test
    public void getAllExpenses() {
    }

    @Test
    public void getExpenseById() {
    }

    @Test
    public void getExpensesByCategoryId() {
    }

    @Test
    public void getExpensesByPeriod() {
    }

    @Test
    public void getCategoryExpensesByPeriod() {
    }
}