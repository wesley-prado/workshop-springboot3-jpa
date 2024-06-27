package com.codemages.course.services;

import com.codemages.course.entities.Category;
import com.codemages.course.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
	@Mock        CategoryRepository categoryRepository;
	@InjectMocks CategoryService    categoryService;

	@Test
	void findAll() {
		List<Category> expectedCategories = List.of(
				new Category(1L, "Category 1"),
				new Category(2L, "Category 2")
		);
		when(categoryRepository.findAll()).thenReturn(expectedCategories
		);

		List<Category> actualCategories = categoryService.findAll();

		verify(categoryRepository, times(1)).findAll();
		assertEquals(
				expectedCategories,
				actualCategories,
				"Should return all categories retrieved by repository"
		);
	}

	@Test
	void findById_WhenCategoryExists() {
		Category expectedCategory = new Category(1L, "Category 1");
		when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(
				expectedCategory));

		Category actualCategory = categoryService.findById(1L);

		verify(categoryRepository, times(1)).findById(1L);
		assertEquals(
				expectedCategory,
				actualCategory,
				"Should return the category retrieved by repository"
		);
	}

	@Test
	void findById_WhenCategoryDoesNotExist() {
		when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.empty());

		Category actualCategory = categoryService.findById(1L);

		verify(categoryRepository, times(1)).findById(1L);
		assertNull(
				actualCategory,
				"Should return null when category does not exist"
		);
	}
}
