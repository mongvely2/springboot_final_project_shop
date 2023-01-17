package com.example.shop.dto;

import com.example.shop.entity.CategoryEntity;
import com.example.shop.entity.FileEntity;
import com.example.shop.entity.MemberEntity;
import com.example.shop.entity.ProductEntity;
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
    private String productDelively;

    public static ProductDTO toDTO(ProductEntity productEntity, CategoryEntity categoryEntity, List<FileEntity> fileEntityList) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setProductName(productEntity.getProductName());
        productDTO.setProductPrice(productEntity.getProductPrice());
        productDTO.setProductStock(productEntity.getProductStock());
        productDTO.setProductSale(productEntity.getProductSale());
        productDTO.setProductContents(productEntity.getProductContents());
        productDTO.setProductDelete(productEntity.getProductDelete());
        productDTO.setProductHits(productEntity.getProductHits());
        productDTO.setCategoryId(categoryEntity.getId());
        productDTO.setProductCreatedTime(productEntity.getCreatedTime());
        productDTO.setProductUpdatedTime(productEntity.getUpdatedTime());
        productDTO.setProductDelively(productEntity.getProductDelively());

//        첨부파일 있는 경우
        if (productEntity.getProductFileAttached().equals("Y")) {
            productDTO.setProductFileAttached(productEntity.getProductFileAttached());

            for (int i = 0; i < fileEntityList.size(); i++) {

                if (fileEntityList.get(i).getFileType().equals("T")) {
                    productDTO.setThumbFileName(fileEntityList.get(i).getStoredFileName());
                }

                if (fileEntityList.get(i).getFileType().equals("D")) {
                    List<String> originalFileNameList = new ArrayList<>();
                    List<String> storedFileNameList = new ArrayList<>();
//            첨부파일 이름 가져오기
//              BoardEntity에서 boardFileEntityList 선언해줘서 사용가능함
                    for (FileEntity var : productEntity.getFileEntityList()) {
                        originalFileNameList.add(var.getOriginalFileName());
                        storedFileNameList.add(var.getStoredFileName());
                    }
                    productDTO.setOriginalFileName(originalFileNameList);
                    productDTO.setStoredFileName(storedFileNameList);
                }

            }
        } else {
            productDTO.setProductFileAttached(productEntity.getProductFileAttached());
        }
        return productDTO;
    }


//            if (fileEntity.getFileType().equals("T")) {
//                productDTO.setThumbFileName(fileEntity.getStoredFileName());
//            }
//            if (fileEntity.getFileType().equals("D")) {
//                List<String> originalFileNameList = new ArrayList<>();
//                List<String> storedFileNameList = new ArrayList<>();
////            첨부파일 이름 가져오기
////              BoardEntity에서 boardFileEntityList 선언해줘서 사용가능함
//                for (FileEntity var : productEntity.getFileEntityList()) {
//                    originalFileNameList.add(var.getOriginalFileName());
//                    storedFileNameList.add(var.getStoredFileName());
//                }
//                productDTO.setOriginalFileName(originalFileNameList);
//                productDTO.setStoredFileName(storedFileNameList);
//            }
//        } else {
//            productDTO.setProductFileAttached(productEntity.getProductFileAttached());
//        }
//        return productDTO;
//    }


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
                      String productDelively,
                      List<FileEntity> productThumbnail
    ) {
//        if (productSale.equals("Y")) {    썸네일-productSale.equals("Y") 일 때만 썸네일 출력하기


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
            this.productDelively = productDelively;
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
//        }     썸네일-productSale.equals("Y") 일 때만 썸네일 출력하기
    }

}
