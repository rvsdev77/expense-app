package com.demo.expenseapp.domain.converter;

import com.demo.expenseapp.domain.Expense;
import com.demo.expenseapp.domain.dto.ExpenseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ExpenseDtoToExpense implements Converter<ExpenseDto, Expense> {

    private CategoryDtoToCategory toCategory;

    public ExpenseDtoToExpense(CategoryDtoToCategory toCategory) {
        this.toCategory = Objects.requireNonNull(toCategory, "toCategory converter should not be null");
    }

    @Override
    public Expense convert(ExpenseDto source) {
        if (source == null) {
            return null;
        }

        Expense expense = new Expense();
        expense.setId(source.getId());
        expense.setCategory(toCategory.convert(source.getCategory()));
        expense.setAmountSpent(source.getAmountSpent());
        expense.setExpenseDate(source.getDate());
        expense.setDescription(source.getDescription());
        return expense;
    }
}
