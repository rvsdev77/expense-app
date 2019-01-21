package com.demo.expenseapp.domain;

import lombok.Data;

import javax.persistence.*;

// todo: field validation should be added
@Entity
@Table(name = "CATEGORIES")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_gen")
    @SequenceGenerator(name = "category_seq_gen", sequenceName = "SEQ_CATEGORY_ID", allocationSize = 500)
    @Column(name = "ID")
    private Long categoryId;

    @Column(name = "CATEGORY_NAME", nullable = false)
    private String categoryName;

    @Column(name = "DESCRIPTION")
    private String description;
}
