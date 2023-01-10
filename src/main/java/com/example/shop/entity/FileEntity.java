package com.example.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "file_table")
public class FileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1)
    private String fileType;

    @Column(length = 100)
    private String originalFileName;

    @Column(length = 100)
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    public static FileEntity toSaveThumbnailFile(ProductEntity product, String originalFileName, String storedFileName) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileType("T");
        fileEntity.setOriginalFileName(originalFileName);
        fileEntity.setStoredFileName(storedFileName);
        fileEntity.setProductEntity(product);
        return fileEntity;
    }

    public static FileEntity toSaveDetailFile(ProductEntity product, String originalFileName, String storedFileName) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileType("D");
        fileEntity.setOriginalFileName(originalFileName);
        fileEntity.setStoredFileName(storedFileName);
        fileEntity.setProductEntity(product);
        return fileEntity;
    }
}
