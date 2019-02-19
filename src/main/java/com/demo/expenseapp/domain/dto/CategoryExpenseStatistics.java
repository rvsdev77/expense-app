package com.demo.expenseapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Projection interface used for expense statistics
 */
public interface CategoryExpenseStatistics {

    @JsonProperty("category")
    String getCategoryName();

    @JsonProperty("totalSpent")
    BigDecimal getAmountSpent();
}
