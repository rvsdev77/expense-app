package com.demo.expenseapp.repository;

import com.demo.expenseapp.domain.Expense;
import com.demo.expenseapp.domain.dto.CategoryExpenseStatistics;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    /**
     * Finds expenses for selected period.
     *
     * @param start period start
     * @param end   period end
     */
    List<Expense> findByExpenseDateBetween(Date start, Date end);


    /**
     * Finds expenses for selected period.
     *
     * @param start    period start
     * @param end      period end
     * @param pageable page object, that limits result size
     */
    List<Expense> findByExpenseDateBetween(Date start, Date end, Pageable pageable);

    /**
     * Finds expenses for specified date
     */
    List<Expense> findByExpenseDate(Date date);

    /**
     * Finds expenses for specified date
     */
    List<Expense> findByExpenseDate(Date date, Pageable pageable);

//=========================== Expenses by period =========================================

    @Query(value = "SELECT * from Expenses e " +
            "where (:expectedYear is null or year(e.date) = :expectedYear) " +
            "and (:expectedMonth is null or month(e.date) = :expectedMonth) " +
            "and (:expectedDay is null or day(e.date) = :expectedDay)",
            nativeQuery = true)
    List<Expense> findExpenses(@Param("expectedYear") Integer year,
                               @Param("expectedMonth") Integer month,
                               @Param("expectedDay") Integer day);

    @Query(value = "SELECT * from Expenses e " +
            "where (:expectedYear is null or year(e.date) = :expectedYear) " +
            "and (:expectedMonth is null or month(e.date) = :expectedMonth) " +
            "and (:expectedDay is null or day(e.date) = :expectedDay)",
            nativeQuery = true)
    List<Expense> findExpenses(@Param("expectedYear") Integer year,
                               @Param("expectedMonth") Integer month,
                               @Param("expectedDay") Integer day,
                               Pageable pageable);


//=========================== Expenses by category ========================================

    /**
     * Finds expenses for specified category during specified period
     */
    @Query(value = "select * from Expenses e where e.category_id = :categoryId " +
            "and (:expectedYear is null or year(e.date) = :expectedYear) " +
            "and (:expectedMonth is null or month(e.date) = :expectedMonth) " +
            "and (:expectedDay is null or day(e.date) = :expectedDay)",
            nativeQuery = true)
    List<Expense> findExpensesByCategory(@Param("categoryId") long categoryId,
                                         @Param("expectedYear") Integer year,
                                         @Param("expectedMonth") Integer month,
                                         @Param("expectedDay") Integer day);

    /**
     * Finds expenses for specified category during specified period
     */
    @Query(value = "select * from Expenses e where e.category_id = :categoryId " +
            "and (:expectedYear is null or year(e.date) = :expectedYear) " +
            "and (:expectedMonth is null or month(e.date) = :expectedMonth) " +
            "and (:expectedDay is null or day(e.date) = :expectedDay)",
            nativeQuery = true)
    List<Expense> findExpensesByCategory(@Param("categoryId") long categoryId,
                                         @Param("expectedYear") Integer year,
                                         @Param("expectedMonth") Integer month,
                                         @Param("expectedDay") Integer day,
                                         Pageable pageable);

//================================= Cumulative Amounts ======================================================

    /**
     * Calculates total amount of expenses that occurred at specific period for specific category
     */
    @Query(value = "select sum(e.spent) from Expenses e " +
            "where e.category_id = :categoryId " +
            "and (:expectedYear is null or year(e.date) = :expectedYear) " +
            "and (:expectedMonth is null or month(e.date) = :expectedMonth) " +
            "and (:expectedDay is null or day(e.date) = :expectedDay) " +
            "group by e.category_id",
            nativeQuery = true)
    BigDecimal getTotalSpentForCategory(@Param("categoryId") long categoryId,
                                        @Param("expectedYear") Integer year,
                                        @Param("expectedMonth") Integer month,
                                        @Param("expectedDay") Integer day);
    /**
     * Calculates total amount of expenses for a specific period
     */
    @Query(value = "select sum(e.spent) from Expenses e " +
            "where (:expectedYear is null or year(e.date) = :expectedYear) " +
            "and (:expectedMonth is null or month(e.date) = :expectedMonth) " +
            "and (:expectedDay is null or day(e.date) = :expectedDay)",
            nativeQuery = true)
    BigDecimal getTotalSpent(@Param("expectedYear") Integer year,
                             @Param("expectedMonth") Integer month,
                             @Param("expectedDay") Integer day);


    /*================================ Statistics ===================================*/

    /**
     * Calculates total expenses amount grouped by categories
     */
    @Query(value = "select c.category_name as categoryName, sum(e.spent) as amountSpent from Expenses e " +
            "inner join Categories c on e.category_id=c.category_id " +
            "where (:expectedYear is null or year(e.date) = :expectedYear) " +
            "and (:expectedMonth is null or month(e.date) = :expectedMonth) " +
            "and (:expectedDay is null or day(e.date) = :expectedDay) " +
            "group by c.category_name",
            nativeQuery = true)
    List<CategoryExpenseStatistics> getCategoryExpenseStatistics(@Param("expectedYear") Integer year,
                                                                 @Param("expectedMonth") Integer month,
                                                                 @Param("expectedDay") Integer day);
}
