package com.demo.expenseapp.domain.converter;

import com.demo.expenseapp.domain.Expense;
import com.demo.expenseapp.domain.dto.ExpenseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ExpenseToExpenseDto implements Converter<Expense, ExpenseDto> {

    private CategoryToCategoryDto toCategoryDto;

    public ExpenseToExpenseDto(CategoryToCategoryDto toCategoryDto) {
        this.toCategoryDto = toCategoryDto;
    }

    @Override
    public ExpenseDto convert(Expense expense) {
        if (expense == null) {
            return null;
        }
        ExpenseDto dto = new ExpenseDto();
        dto.setId(expense.getId());
        dto.setAmountSpent(expense.getAmountSpent());
        dto.setCategory(toCategoryDto.convert(expense.getCategory()));
        dto.setDate(expense.getExpenseDate());
        dto.setDescription(expense.getDescription());
        return dto;
    }
}
