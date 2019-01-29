package com.demo.expenseapp.domain.vo;

import java.math.BigDecimal;

public interface CategoryExpenseStatistics {

    String getCategoryName();

    BigDecimal getAmountSpent();
}
