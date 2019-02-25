package com.demo.expenseapp.service;

import com.demo.expenseapp.domain.Expense;
import com.demo.expenseapp.domain.converter.ExpenseDtoToExpense;
import com.demo.expenseapp.domain.converter.ExpenseToExpenseDto;
import com.demo.expenseapp.domain.dto.CategoryExpenseStatistics;
import com.demo.expenseapp.domain.dto.ExpenseDto;
import com.demo.expenseapp.exeptions.NotFoundException;
import com.demo.expenseapp.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseServiceImpl.class);
    private static final String EXPENSES_RESULT_MESSAGE = "Got {} records in {} ms";

    private ExpenseRepository expenseRepository;
    private ExpenseToExpenseDto toExpenseDto;
    private ExpenseDtoToExpense toExpense;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseToExpenseDto toExpenseDto, ExpenseDtoToExpense toExpense) {
        this.expenseRepository = Objects.requireNonNull(expenseRepository, "expenseRepository could not be null");
        this.toExpenseDto = Objects.requireNonNull(toExpenseDto, "toExpenseDto converter should not be null");
        this.toExpense = Objects.requireNonNull(toExpense, "toExpense converter should not be null");
    }

    @Override
    public List<CategoryExpenseStatistics> getCategoryExpenseStatistics(Integer year, Integer month, Integer day) {
        LOGGER.debug("Getting expense statistics for categories for year = {}, month = {}, day = {}", year, month, day);
        return expenseRepository.getCategoryExpenseStatistics(year, month, day);
    }

    @Transactional
    @Override
    public ExpenseDto addExpense(ExpenseDto expenseDto) {
        Objects.requireNonNull(expenseDto, "Cannot save null object as an expense");
        LOGGER.debug("Saving expense: {}", expenseDto);

        Expense saved = expenseRepository.save(toExpense.convert(expenseDto));
        return toExpenseDto.convert(saved);
    }


    @Transactional
    @Override
    public void deleteExpense(Long expenseId) {
        Objects.requireNonNull(expenseId, "expenseId cannot be null for delete operation");
        LOGGER.debug("Delting expense record with id = {}", expenseId);

        expenseRepository.deleteById(expenseId);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {
        LOGGER.debug("Retrieving all expenses records ...");

        StopWatch watch = new StopWatch();
        watch.start();

        List<Expense> expenses = expenseRepository.findAll();

        LOGGER.debug(EXPENSES_RESULT_MESSAGE, expenses.size(), watch.getTotalTimeMillis());

        return expenses.stream()
                .map(e -> toExpenseDto.convert(e))
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDto getExpenseById(Long expenseId) {
        Objects.requireNonNull(expenseId, "expenseId cannot be null");

        LOGGER.debug("Getting expense record for id = {}", expenseId);

        Optional<Expense> expense = expenseRepository.findById(expenseId);
        if (!expense.isPresent()) {
            throw new NotFoundException("The expense with specified id was not found");
        }

        return toExpenseDto.convert(expense.get());
    }

    @Override
    public List<ExpenseDto> getExpensesByCategoryId(Long categoryId) {
        Objects.requireNonNull(categoryId, "The categoryId cannot be null");
        LOGGER.debug("Retrieving expense records for categoryId = {} ", categoryId);

        StopWatch watch = new StopWatch();
        watch.start();

        List<Expense> expenses = expenseRepository.findExpensesByCategory_CategoryId(categoryId);

        LOGGER.debug(EXPENSES_RESULT_MESSAGE, expenses.size(), watch.getTotalTimeMillis());
        return expenses.stream()
                .map(e -> toExpenseDto.convert(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDto> getExpensesByPeriod(Integer year, Integer month, Integer day) {
        LOGGER.debug("Retrieving expense records for year = {}, month = {}, day = {}", year, month, day);
        StopWatch watch = new StopWatch();
        watch.start();

        List<Expense> expenses = expenseRepository.findExpenses(year, month, day);

        LOGGER.debug(EXPENSES_RESULT_MESSAGE, expenses.size(), watch.getTotalTimeMillis());
        return expenses.stream()
                .map(e -> toExpenseDto.convert(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDto> getCategoryExpensesByPeriod(Long categoryId, Integer year, Integer month, Integer day) {
        Objects.requireNonNull(categoryId, "categoryId cannot be null");

        LOGGER.debug("Retrieving expense records for categoryId = {}, year = {}, month = {}, day = {}", categoryId, year, month, day);
        StopWatch watch = new StopWatch();
        watch.start();

        List<Expense> expenses = expenseRepository.findExpensesByCategory(categoryId, year, month, day);

        LOGGER.debug(EXPENSES_RESULT_MESSAGE, expenses.size(), watch.getTotalTimeMillis());

        return expenses.stream()
                .map(e -> toExpenseDto.convert(e))
                .collect(Collectors.toList());
    }
}
