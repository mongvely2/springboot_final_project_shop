package com.example.shop.dto;

import com.example.shop.entity.CategoryEntity;
import com.example.shop.entity.FileEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private MultipartFile productThumbnail;
    //    다중 파일첨부를 위해 List로 선언
//    -> 같은 name 값으로 여러개일 경우 List 타입 지정(체크박스 등)
    private List<MultipartFile> productDetailFile;
    private List<String> originalFileName;
    private List<String> storedFileName;
    private String thumbFileName;


    //    페이징 목록 변환을 위한 생성자 -> 페이징으로 목록에 무엇을 보여줄건지 필드
//    boardTitle, boardHits, boardWriter, id, boardHits, boardCreatedTime
//    생성자 자동으로 생성하는 단축키: alt+insert -> costructor
    public ProductDTO(Long id,
                      String productName,
                      String productPrice,
                      int productStock,
                      String productSale,
                      String productContents,
                      String productDelete,
                      String productFileAttached,
                      int productHits,
                      CategoryEntity categoryId,
                      LocalDateTime productCreatedTime,
                      LocalDateTime productUpdatedTime,
                      List<FileEntity> productThumbnail
    ) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productSale = productSale;
        this.productContents = productContents;
        this.productDelete = productDelete;
        this.productFileAttached = productFileAttached;
        this.productHits = productHits;
        this.categoryId = categoryId.getId();
        this.productCreatedTime = productCreatedTime;
        this.productUpdatedTime = productUpdatedTime;
//        this.storedFileName = productThumbnail;

//        List<String> storedFileNameList = new ArrayList<>();
//        for (int i = 0; i < productThumbnail.size(); i++) {
//            if (productThumbnail.get(i).getFileType().equals("T")) {
//                storedFileNameList.add(productThumbnail.get(i).getStoredFileName());
//                this.storedFileName = storedFileNameList;
//            }
//        }

//        List FileEntity 타입을 String 으로 바꿔주기 위한 로직
//        List<String> storedFileNameList = new ArrayList<>();
//        if (productThumbnail.get(0).getFileType().equals("T")) {
//
//        }

        for (int i = 0; i < productThumbnail.size(); i++) {
            if (productThumbnail.get(i).getFileType().equals("T")) {
                String thumbFile = productThumbnail.get(0).getStoredFileName();

//                storedFileNameList.add(getProductThumbnail());
                this.thumbFileName = thumbFile;
            }
        }

//        for (FileEntity thumb : productThumbnail) {
//            storedFileNameList.add(thumb.getStoredFileName());
//        }
//        String thumbFileName = storedFileNameList.

//        this.storedFileName = storedFileNameList;
    }

}
