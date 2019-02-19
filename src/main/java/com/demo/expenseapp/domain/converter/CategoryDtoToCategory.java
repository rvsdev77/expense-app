package com.demo.expenseapp.domain.converter;

import com.demo.expenseapp.domain.Category;
import com.demo.expenseapp.domain.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToCategory implements Converter<CategoryDto, Category> {

    @Override
    public Category convert(CategoryDto source) {
        if (source == null) {
            return null;
        }

        Category category = new Category();
        category.setCategoryId(source.getCategoryId());
        category.setCategoryName(category.getCategoryName());
        category.setDescription(category.getDescription());
        return category;
    }
}
