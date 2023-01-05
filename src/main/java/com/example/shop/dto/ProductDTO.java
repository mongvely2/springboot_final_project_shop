package com.example.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String productName;
    private String productPrice;
    private int productStock;
    private String productSale;
    private String productContents;
    private String productDelete;
    private int productHits;
    private Long categoryId;

    private LocalDateTime productCreatedTime;
    private LocalDateTime productUpdatedTime;

    private String productFileAttached;
    private List<MultipartFile> productFile;
    private List<String> originalFileName;
    private List<String> storedFileName;

}
