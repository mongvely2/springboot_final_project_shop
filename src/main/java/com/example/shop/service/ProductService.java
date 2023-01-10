package com.example.shop.service;

import com.example.shop.dto.CategoryDTO;
import com.example.shop.dto.ProductDTO;
import com.example.shop.entity.CategoryEntity;
import com.example.shop.entity.FileEntity;
import com.example.shop.entity.ProductEntity;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.FileRepository;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final FileRepository fileRepository;

//    public List<CategoryDTO> categorySave(CategoryDTO categoryDTO) {
//        CategoryEntity categoryEntity = CategoryEntity.toCategorySaveEntity(categoryDTO);
//        categoryRepository.save(categoryEntity);
//        List<CategoryEntity> categoryEntityList = categoryRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
//        List<CategoryDTO> categoryDTOList = new ArrayList<>();
//        for (CategoryEntity category : categoryEntityList) {
//            categoryDTOList.add(CategoryDTO.toCategoryDTO(category));
//        }
//        return categoryDTOList;
//    }
    public void categorySave(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = CategoryEntity.toCategorySaveEntity(categoryDTO);
        categoryRepository.save(categoryEntity);
    }

    public List<CategoryDTO> categoryList() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (CategoryEntity category : categoryEntityList) {
            categoryDTOList.add(CategoryDTO.toCategoryDTO(category));
        }
        return categoryDTOList;
    }

    public void save(ProductDTO productDTO) throws IOException {
        Long cateId = productDTO.getCategoryId();
        CategoryEntity categoryEntity = categoryRepository.findById(cateId).get();

//        썸네일/상세이미지 둘 다 없는 경우
        if (productDTO.getProductThumbnail() == null && productDTO.getProductDetailFile().get(0).isEmpty()) {
            ProductEntity productEntity = ProductEntity.toSaveEntity(productDTO, categoryEntity);
            productRepository.save(productEntity);
        }

//        if (productDTO.getProductThumbnail() != null) {
////            첨부파일 있는 경우
//            ProductEntity productEntity = ProductEntity.toSaveFileEntity(productDTO, categoryEntity);
//            Long productId = productRepository.save(productEntity).getId();
//            ProductEntity product = productRepository.findById(productId).get();
//
//            MultipartFile productThumbnail = productDTO.getProductThumbnail();
//            String originalFileName = productThumbnail.getOriginalFilename();
//            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
//            String filePath = "D:\\boot_final_img\\" + storedFileName;
//            productThumbnail.transferTo(new File(filePath));
//            FileEntity fileEntity = FileEntity.toSaveThumbnailFile(product, originalFileName, storedFileName);
//            fileRepository.save(fileEntity);
//        }

        if (productDTO.getProductThumbnail() != null && !productDTO.getProductDetailFile().get(0).isEmpty()) {
//            첨부파일 있는 경우
            ProductEntity productEntity = ProductEntity.toSaveFileEntity(productDTO, categoryEntity);
            Long productId = productRepository.save(productEntity).getId();
            ProductEntity product = productRepository.findById(productId).get();

            if (productDTO.getProductThumbnail() != null) {
//                썸네일
            MultipartFile productThumbnail = productDTO.getProductThumbnail();
            String originalFileName = productThumbnail.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            String filePath = "D:\\boot_final_img\\" + storedFileName;
            productThumbnail.transferTo(new File(filePath));
            FileEntity fileEntity = FileEntity.toSaveThumbnailFile(product, originalFileName, storedFileName);
            fileRepository.save(fileEntity);
            }

            if (!productDTO.getProductDetailFile().get(0).isEmpty()) {
//                상세이미지
                for (MultipartFile productDetailFile : productDTO.getProductDetailFile()) {
                    String originalFileName = productDetailFile.getOriginalFilename();
                    String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
                    String filePath = "D:\\boot_final_img\\" + storedFileName;
                    productDetailFile.transferTo(new File(filePath));
                    FileEntity fileEntity = FileEntity.toSaveDetailFile(product, originalFileName, storedFileName);
                    fileRepository.save(fileEntity);
            }

        }
//        if (!productDTO.getProductDetailFile().get(0).isEmpty()) {
//
//            for (MultipartFile productDetailFile : productDTO.getProductDetailFile()) {
//                String originalFileName = productThumbnail.getOriginalFilename();
//                String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
//                String filePath = "D:\\boot_final_img\\" + storedFileName;
//                productThumbnail.transferTo(new File(filePath));
//                FileEntity fileEntity = FileEntity.toSaveThumbnailFile(product, originalFileName, storedFileName);
//                fileRepository.save(fileEntity);
//            }
        }
    }
}































