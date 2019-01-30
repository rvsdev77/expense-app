package com.demo.expenseapp.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

public class ExpenseDto {
    @Getter @Setter private BigDecimal amountSpent;
    @Getter @Setter private CategoryDto category;
    @Getter @Setter private Date date;
    @Getter @Setter private String description;
}
