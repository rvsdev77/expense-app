package com.demo.expenseapp.service;

import com.demo.expenseapp.domain.Category;
import com.demo.expenseapp.domain.converter.CategoryDtoToCategory;
import com.demo.expenseapp.domain.converter.CategoryToCategoryDto;
import com.demo.expenseapp.domain.dto.CategoryDto;
import com.demo.expenseapp.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository repository;

    @Mock
    private CategoryToCategoryDto toCategoryDto;

    @Mock
    private CategoryDtoToCategory toCategory;

    private CategoryService service;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new CategoryServiceImpl(repository, toCategoryDto, toCategory);
    }


    @Test
    public void testConstructor_throwsNpe() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("categoryRepository should not be null");
        service = new CategoryServiceImpl(null, null, null);
    }

    @Test
    public void testGetAll() {
        Category category1 = new Category();
        category1.setCategoryId(1L);
        category1.setCategoryName("Category1");

        Category category2 = new Category();
        category1.setCategoryId(2L);
        category1.setCategoryName("Category2");

        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setCategoryId(1L);
        categoryDto1.setCategoryName("Category1");

        CategoryDto categoryDto2 = new CategoryDto();
        categoryDto2.setCategoryId(2L);
        categoryDto2.setCategoryName("Category2");

        List<Category> categories = Arrays.asList(category1, category2);

        when(repository.findAll()).thenReturn(categories);
        when(toCategoryDto.convert(category1)).thenReturn(categoryDto1);
        when(toCategoryDto.convert(category2)).thenReturn(categoryDto2);

        List<CategoryDto> dtos = service.getAll();

        verify(repository, times(1)).findAll();

        assertThat(dtos)
                .hasSize(2)
                .extracting(CategoryDto::getCategoryName)
                .containsOnly("Category1", "Category2");
    }

    @Test
    public void testSaveCategory_throwsNpe() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("The category being saved cannot be null!");
        service.saveCategory(null);
    }

    @Test
    public void testDeleteCategory() {
    }

    @Test
    public void testGetById() {
    }
}