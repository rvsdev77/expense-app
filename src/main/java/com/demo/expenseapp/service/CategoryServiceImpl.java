package com.demo.expenseapp.service;

import com.demo.expenseapp.domain.Category;
import com.demo.expenseapp.domain.converter.CategoryDtoToCategory;
import com.demo.expenseapp.domain.converter.CategoryToCategoryDto;
import com.demo.expenseapp.domain.dto.CategoryDto;
import com.demo.expenseapp.exeptions.NotFoundException;
import com.demo.expenseapp.repository.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private CategoryRepository categoryRepository;
    private CategoryToCategoryDto categoryConverter;
    private CategoryDtoToCategory categoryDtoConverter;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryToCategoryDto categoryConverter,
                               CategoryDtoToCategory categoryDtoConverter) {
        this.categoryRepository = Objects.requireNonNull(categoryRepository, "categoryRepository should not be null");
        this.categoryConverter = Objects.requireNonNull(categoryConverter, "categoryConverter should not be null");
        this.categoryDtoConverter = Objects.requireNonNull(categoryDtoConverter, "categoryDtoConverter should not be null");
    }

    @Override
    public List<CategoryDto> getAll() {
        LOGGER.debug("Retrieving all expense categories...");

        StopWatch watch = new StopWatch();
        watch.start();

        List<Category> categories = categoryRepository.findAll();

        LOGGER.debug("Got {} records in {} ms", categories.size(), watch.getTotalTimeMillis());

        return categories.stream()
                .map(category -> categoryConverter.convert(category))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Objects.requireNonNull(categoryDto, "The category being saved cannot be null!");

        LOGGER.debug("Saving category {}", categoryDto);
        Category savedCategory = categoryRepository.save(categoryDtoConverter.convert(categoryDto));
        return categoryConverter.convert(savedCategory);
    }

    @Transactional
    @Override
    public void deleteCategory(Long id) {
        Objects.requireNonNull(id, "category id cannot be null for delete operation");
        LOGGER.debug("Deleting category with id = {}", id);

        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto getById(Long id) {
        Objects.requireNonNull(id, "category id cannot be null");
        LOGGER.debug("Fetching category by id = {}", id);

        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new NotFoundException("The category with specified id does not exist!");
        }
        return categoryConverter.convert(category.get());
    }
}
