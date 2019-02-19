package com.demo.expenseapp.service;

import com.demo.expenseapp.domain.Category;
import com.demo.expenseapp.domain.converter.CategoryToCategoryDto;
import com.demo.expenseapp.domain.dto.CategoryDto;
import com.demo.expenseapp.exeptions.NotFoundException;
import com.demo.expenseapp.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    //todo: logging
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private CategoryRepository categoryRepository;
    private CategoryToCategoryDto categoryConverter;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryToCategoryDto categoryConverter) {
        this.categoryRepository = Objects.requireNonNull(categoryRepository, "categoryRepository should not be null");
        this.categoryConverter = Objects.requireNonNull(categoryConverter, "categoryConverter should not be null");
    }

    @Override
    public List<CategoryDto> getAll() {
        LOGGER.debug("Retrieving all expense categories...");
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> categoryConverter.convert(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
//        Category saved = categoryRepository.save()
        return null;
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto getById(long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            //todo: throw specific exception + exception handling at representation layer
            throw new NotFoundException("The category with specified id does not exist!");
        }
        return categoryConverter.convert(category.get());
    }
}
