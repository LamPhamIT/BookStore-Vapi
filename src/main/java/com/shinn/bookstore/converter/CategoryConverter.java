package com.shinn.bookstore.converter;


import com.shinn.bookstore.dto.CategoryDTO;
import com.shinn.bookstore.model.CategoryEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryEntity toCategoryEntity(CategoryDTO categoryDTO) {
        if(categoryDTO != null) {
            CategoryEntity categoryEntity = new CategoryEntity();
            BeanUtils.copyProperties(categoryDTO, categoryEntity);
            return categoryEntity;
        }
        return null;
    }
    public CategoryDTO toCategoryDTO(CategoryEntity categoryEntity) {
       if(categoryEntity != null) {
           CategoryDTO categoryDTO = new CategoryDTO();
           BeanUtils.copyProperties(categoryEntity, categoryDTO);
           return categoryDTO;
       }
       return null;
    }
}
