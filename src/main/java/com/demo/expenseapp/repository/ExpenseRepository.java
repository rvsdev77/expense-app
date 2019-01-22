package com.demo.expenseapp.repository;

import com.demo.expenseapp.domain.Expense;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     * Finds expenses that are related to specified category
     */
    List<Expense> findByCategory_CategoryName(String category);

    /**
     * Finds expenses that are related to specified category
     */
    List<Expense> findByCategory_CategoryName(String category, Pageable pageable);

    /**
     * Finds expenses for specified year
     */
    @Query(value = "SELECT * from Expenses e where year(e.date) = :expectedYear",
            nativeQuery = true)
    List<Expense> findExpensesByYear(@Param("expectedYear") int year);


    /**
     * Finds expenses for specified year
     */
    @Query(value = "SELECT * from Expenses e where year(e.date) = :expectedYear",
            countQuery = "SELECT count(*) from Expense",
            nativeQuery = true)
    List<Expense> findExpensesByYear(@Param("expectedYear") int year, Pageable pageable);

    /**
     * Finds expenses for specified year and month
     */
    @Query(value = "SELECT * from Expenses e where year(e.date) = :expectedYear and month(e.date) = :expectedMonth",
            nativeQuery = true)
    List<Expense> findExpensesByYearAndMonth(@Param("expectedYear") int year, @Param("expectedMonth") int month);


    /**
     * Finds expenses for specified year and month
     */
    @Query(value = "SELECT * from Expenses e where year(e.date) = :expectedYear and month(e.date) = :expectedMonth",
            countQuery = "SELECT count(*) from Expense",
            nativeQuery = true)
    List<Expense> findExpensesByYearAndMonth(@Param("expectedYear") int year, @Param("expectedMonth") int month, Pageable pageable);


//    @Query(value = "SELECT * from Expense e where year(e.expenseDate) = :expectedYear and e.category.categoryName = :category",
//            nativeQuery = true)
//    List<Expense> findExpensesByCategoryAndYear(@Param("expectedYear") int year, @Param("category") String category);
//

//    @Query(value = "SELECT * from Expense e where year(e.expenseDate) = :expectedYear and e.category.categoryName = :category",
//            nativeQuery = true)
//    List<Expense> findExpensesByCategoryAndYear(@Param("expectedYear") int year, @Param("category") String category, Pageable pageable);

}
