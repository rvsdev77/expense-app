package com.demo.expenseapp.domain.dto;

import java.math.BigDecimal;

/**
 * Projection interface used for expense statistics
 */
public interface CategoryExpenseStatistics {

    String getCategoryName();

    BigDecimal getAmountSpent();
}
