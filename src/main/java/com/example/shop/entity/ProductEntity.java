package com.example.shop.entity;

import com.example.shop.dto.ProductDTO;
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

    @Column(length = 10)
    private String productDelively;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FileEntity> fileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartProductEntity> cartProductEntityList = new ArrayList<>();

    public static ProductEntity toSaveEntity(ProductDTO productDTO, CategoryEntity categoryEntity) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setProductPrice(productDTO.getProductPrice());
        productEntity.setProductStock(productDTO.getProductStock());
        productEntity.setProductSale(productDTO.getProductSale());
        productEntity.setProductContents(productDTO.getProductContents());
        productEntity.setProductDelete("N");
        productEntity.setProductFileAttached("N");
        productEntity.setProductHits(0);
        productEntity.setCategoryEntity(categoryEntity);
        productEntity.setProductDelively(productDTO.getProductDelively());
        return productEntity;
    }

    public static ProductEntity toSaveFileEntity(ProductDTO productDTO, CategoryEntity categoryEntity) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setProductPrice(productDTO.getProductPrice());
        productEntity.setProductStock(productDTO.getProductStock());
        productEntity.setProductSale(productDTO.getProductSale());
        productEntity.setProductContents(productDTO.getProductContents());
        productEntity.setProductDelete("N");
        productEntity.setProductFileAttached("Y");
        productEntity.setProductHits(0);
        productEntity.setCategoryEntity(categoryEntity);
        productEntity.setProductDelively(productDTO.getProductDelively());
        return productEntity;
    }




}
