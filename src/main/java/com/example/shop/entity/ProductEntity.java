package com.example.shop.entity;

import jdk.jfr.Name;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product_table")
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String productName;

    @Column(length = 10)
    private String productPrice;

    @Column(length = 4)
    private int productStock;

    @Column(length = 1)
    private String productSale;

    @Column(length = 1000)
    private String productContents;

    @Column(length = 1)
    private String productDelete;

    @Column(length = 1)
    private String productFileAttached;

    @Column(length = 5)
    private int productHits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FileEntity> fileEntityList = new ArrayList<>();





}
