package com.example.shop.service;

import com.example.shop.dto.CategoryDTO;
import com.example.shop.entity.CategoryEntity;
import com.example.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> categorySave(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = CategoryEntity.toCategorySaveEntity(categoryDTO);
        categoryRepository.save(categoryEntity);
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (CategoryEntity category : categoryEntityList) {
            categoryDTOList.add(CategoryDTO.toCategoryDTO(category));
        }
        return categoryDTOList;
    }
}
