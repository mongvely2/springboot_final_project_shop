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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (CategoryEntity category : categoryEntityList) {
            categoryDTOList.add(CategoryDTO.toCategoryDTO(category));
        }
        return categoryDTOList;
    }

    public void save(ProductDTO productDTO) throws IOException {
        Long cateId = productDTO.getCategoryId();
        CategoryEntity categoryEntity = categoryRepository.findById(cateId).get();

//        ?????????/??????????????? ??? ??? ?????? ??????
        if (productDTO.getProductThumbnail() == null && productDTO.getProductDetailFile().get(0).isEmpty()) {
            ProductEntity productEntity = ProductEntity.toSaveEntity(productDTO, categoryEntity);
            productRepository.save(productEntity);
        }

        if (productDTO.getProductThumbnail() != null && !productDTO.getProductDetailFile().get(0).isEmpty()) {
//            ???????????? ?????? ??????
            ProductEntity productEntity = ProductEntity.toSaveFileEntity(productDTO, categoryEntity);
            Long productId = productRepository.save(productEntity).getId();
            ProductEntity product = productRepository.findById(productId).get();

            if (productDTO.getProductThumbnail() != null) {
//                ?????????
                MultipartFile productThumbnail = productDTO.getProductThumbnail();
                String originalFileName = productThumbnail.getOriginalFilename();
                String storedFileName = "T_" + System.currentTimeMillis() + "_" + originalFileName;
                String filePath = "D:\\boot_final_img\\" + storedFileName;
                productThumbnail.transferTo(new File(filePath));
                FileEntity fileEntity = FileEntity.toSaveThumbnailFile(product, originalFileName, storedFileName);
                fileRepository.save(fileEntity);
            }

            if (!productDTO.getProductDetailFile().get(0).isEmpty()) {
//                ???????????????
                for (MultipartFile productDetailFile : productDTO.getProductDetailFile()) {
                    String originalFileName = productDetailFile.getOriginalFilename();
                    String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
                    String filePath = "D:\\boot_final_img\\" + storedFileName;
                    productDetailFile.transferTo(new File(filePath));
                    FileEntity fileEntity = FileEntity.toSaveDetailFile(product, originalFileName, storedFileName);
                    fileRepository.save(fileEntity);
                }

            }
        }
    }


    @Transactional
    public Page<ProductDTO> paging(Pageable pageable) {   //Pageable spring?????? import ?????? ??????(java x)
//        page: (????????? ????????????) ?????? page(???????????? 0?????? 1??????) / pageLimit: ????????? ??? ?????????????????? ????????? ???
        int page = pageable.getPageNumber() - 1;
        final int pageLimit = 9;
//        Page<> : ??????????????? ???????????? ??????????????? / List<> ??? ???????????? ??? ???
        Page<ProductEntity> productEntities = productRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
//                productEntities??? ?????? productEntity ????????? product??? ?????????
//                productDTO ????????? ????????? ?????? ?????? ??????
        Page<ProductDTO> productList = productEntities.map(
                product -> new ProductDTO
                        (
                                product.getId(),
                                product.getProductName(),
                                product.getProductPrice(),
                                product.getProductStock(),
                                product.getProductSale(),
                                product.getProductContents(),
                                product.getProductDelete(),
                                product.getProductFileAttached(),
                                product.getProductHits(),
                                product.getCategoryEntity(),
                                product.getCreatedTime(),
                                product.getUpdatedTime(),
                                product.getProductDelively(),
                                product.getFileEntityList()
                        )
        );
        return productList;
    }

    @Transactional
    public void updateHits(Long id) {
        productRepository.updateHits(id);
    }

    @Transactional
    public ProductDTO findById(Long id) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if (optionalProductEntity.isPresent()) {
            ProductEntity productEntity = optionalProductEntity.get();
            CategoryEntity categoryEntity = productEntity.getCategoryEntity();
            List<FileEntity> fileEntityList = productEntity.getFileEntityList();
//          toDTO ????????? ???????????? =  productEntity, categoryEntity, fileEntityList
            ProductDTO productDTO = ProductDTO.toDTO(productEntity, categoryEntity, fileEntityList);
            return productDTO;
        } else {
            return null;
        }
    }
}































