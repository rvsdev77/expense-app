package com.demo.expenseapp.repository;

import com.demo.expenseapp.domain.Expense;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    //todo: provide methods for retrieving {categoryName, spent} for statistics

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


    /**
     * Finds expenses for specified category during specified year
     */
    @Query(value = "SELECT * from Expenses e inner join Categories c on e.category_id = c.category_id " +
            "where e.year(e.date) = :expectedYear and c.category_name = :category", nativeQuery = true)
    List<Expense> findExpensesByCategoryAndYear(@Param("expectedYear") int year, @Param("category") String category);


    /**
     * Finds expenses for specified category during specified year
     */
    @Query(value = "SELECT * from Expenses e inner join Categories c on e.category_id = c.category_id " +
            "where e.year(e.date) = :expectedYear and c.category_name = :category", nativeQuery = true)
    List<Expense> findExpensesByCategoryAndYear(@Param("expectedYear") int year,
                                                @Param("category") String category,
                                                Pageable pageable);

    /**
     * Finds expenses for specified category during specified month
     */
    @Query(value = "select * from Expenses e inner join Categories c on e.category_id=c.category_id " +
            "where year(e.date) = :expectedYear and month(e.date) = :expectedMonth and c.category_name = :category",
            nativeQuery = true)
    List<Expense> findExpensesByCategoryAndMonth(@Param("expectedYear") int year,
                                                 @Param("expectedMonth") int month,
                                                 @Param("category") String category);

    /**
     * Finds expenses for specified category during specified month
     */
    @Query(value = "select * from Expenses e inner join Categories c on e.category_id=c.category_id " +
            "where year(e.date) = :expectedYear and month(e.date) = :expectedMonth and c.category_name = :category",
            nativeQuery = true)
    List<Expense> findExpensesByCategoryAndMonth(@Param("expectedYear") int year,
                                                 @Param("expectedMonth") int month,
                                                 @Param("category") String category,
                                                 Pageable pageable);

    /**
     * Finds expenses for specified category during specified day
     */
    @Query(value = "select * from Expenses e inner join Categories c on e.category_id=c.category_id " +
            "where year(e.date) = :expectedYear and month(e.date) = :expectedMonth and day(e.date) = :expectedDay " +
            "and c.category_name = :category",
            nativeQuery = true)
    List<Expense> findExpensesByCategoryAndDay(@Param("expectedYear") int year,
                                               @Param("expectedMonth") int month,
                                               @Param("expectedDay") int day,
                                               @Param("category") String category);

    /**
     * Finds expenses for specified category during specified day
     */
    @Query(value = "select * from Expenses e inner join Categories c on e.category_id=c.category_id " +
            "where year(e.date) = :expectedYear and month(e.date) = :expectedMonth and day(e.date) = :expectedDay " +
            "and c.category_name = :category",
            nativeQuery = true)
    List<Expense> findExpensesByCategoryAndDay(@Param("expectedYear") int year,
                                               @Param("expectedMonth") int month,
                                               @Param("expectedDay") int day,
                                               @Param("category") String category,
                                               Pageable pageable);

    /**
     * Calculates total amount of expenses for specific category
     */
    @Query(value = "select sum(e.spent) from Expenses e inner join Categories c on e.category_id = c.category_id " +
            "where c.category_name = :category group by e.category_id",
            nativeQuery = true)
    BigDecimal getTotalSpentForCategory(@Param("category") String category);

    /**
     * Calculates total amount of expenses that occurred at specific year for specific category
     */
    @Query(value = "select sum(e.spent) from Expenses e inner join Categories c on e.category_id = c.category_id " +
            "where c.category_name = :category and year(e.date) = :expectedYear group by e.category_id",
            nativeQuery = true)
    BigDecimal getTotalSpentForCategoryForYear(@Param("category") String category, @Param("expectedYear") int year);

    /**
     * Calculates total amount of expenses that occurred at specific month for specific category
     */
    @Query(value = "select sum(e.spent) from Expenses e inner join Categories c on e.category_id = c.category_id " +
            "where c.category_name = :category and year(e.date) = :expectedYear and month(e.date) = :expectedMonth " +
            "group by e.category_id",
            nativeQuery = true)
    BigDecimal getTotalSpentForCategoryForMonth(@Param("category") String category, @Param("expectedYear") int year, @Param("expectedMonth") int month);

    /**
     * Calculates total amount of expenses that occurred at specific day for specific category
     */
    @Query(value = "select sum(e.spent) from Expenses e inner join Categories c on e.category_id = c.category_id " +
            "where c.category_name = :category and year(e.date) = :expectedYear and month(e.date) = :expectedMonth " +
            "and day(e.date) = :expectedDay group by e.category_id",
            nativeQuery = true)
    BigDecimal getTotalSpentForCategoryForDay(@Param("category") String category,
                                              @Param("expectedYear") int year,
                                              @Param("expectedMonth") int month,
                                              @Param("expectedDay") int day);
    /**
     * Calculates total amount of expenses for a specific year
     */
    @Query(value = "select sum(e.spent) from Expenses e where year(e.date) = :year", nativeQuery = true)
    BigDecimal getTotalSpentForYear(@Param("year") int year);

    /**
     * Calculates total amount of expenses for a specific month
     */
    @Query(value = "select sum(e.spent) from Expenses e where year(e.date) = :year and month(e.date) = :month",
            nativeQuery = true)
    BigDecimal getTotalSpentForMonth(@Param("year") int year, @Param("month") int month);

    /**
     * Calculates total amount of expenses for a specific day
     */
    @Query(value = "select sum(e.spent) from Expenses e where year(e.date) = :year" +
            " and month(e.date) = :month and day(e.date) = :day",
            nativeQuery = true)
    BigDecimal getTotalSpentForDay(@Param("year") int year, @Param("month") int month, @Param("day") int day);
}
