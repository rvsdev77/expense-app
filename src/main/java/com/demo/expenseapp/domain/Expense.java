package com.demo.expenseapp.domain;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// todo: field validation should be added

@Entity
@Table(name = "EXPENSES")
@Data
public class Expense implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_seq_gen")
    @SequenceGenerator(name = "expense_seq_gen", sequenceName = "SEQ_EXPENSE_ID")
    @Column(name = "EXPENSE_ID")
    private Long id;

    @Column(name = "SPENT", precision = 10, scale = 2)
    private BigDecimal amountSpent;

    @Column(name = "EXPENSE_DATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date expenseDate;

    @Column(name = "DESCRIPTION")
    private String description;

    //todo: ponder about cascade operations handling.
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;
}
