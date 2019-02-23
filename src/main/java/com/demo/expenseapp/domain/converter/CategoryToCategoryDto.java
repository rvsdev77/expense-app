package com.demo.expenseapp.domain.converter;

import com.demo.expenseapp.domain.Category;
import com.demo.expenseapp.domain.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryDto implements Converter<Category, CategoryDto> {

    @Override
    public CategoryDto convert(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }
}
