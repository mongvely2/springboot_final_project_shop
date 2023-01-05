package com.example.shop.dto;

import com.example.shop.entity.CategoryEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String categoryName;
    private LocalDateTime categoryCreatedTime;
    private LocalDateTime categoryUpdatedTime;

    public static CategoryDTO toCategoryDTO(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setCategoryName(categoryEntity.getCategoryName());
        categoryDTO.setCategoryCreatedTime(categoryEntity.getCreatedTime());
        categoryDTO.setCategoryUpdatedTime(categoryEntity.getUpdatedTime());
        return categoryDTO;
    }
}
