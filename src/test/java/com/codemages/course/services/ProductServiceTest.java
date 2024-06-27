package com.codemages.course.services;

import com.codemages.course.entities.Product;
import com.codemages.course.repositories.ProductRepository;
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
public class ProductServiceTest {
	@Mock ProductRepository productRepository;

	@InjectMocks ProductService productService;

	@Test
	void findAll() {
		List<Product> expectedProductList = List.of(
				new Product(
						1L,
						"Smart TV 50” 4K Ultra HD",
						"Para você que gosta de reunir a família e os amigos" +
						" " +
						"para assistir algum filme engraçado",
						2296.17,
						"https://cdn.com/800x560/smart-tv-50-4k"

				),
				new Product(
						2L,
						"Notebook Gamer",
						"Para você que gosta de jogar com alta performance",
						4500.99,
						"https://cdn.com/800x560/notebook-gamer"
				)
		);
		when(productRepository.findAll()).thenReturn(expectedProductList);

		List<Product> actualProductList = productService.findAll();

		verify(productRepository, times(1)).findAll();
		assertEquals(
				expectedProductList,
				actualProductList,
				"findAll should return the expected output"
		);
	}

	@Test
	void findById_productFound() {
		Product expectedProduct = new Product(
				1L,
				"Smart TV 50” 4K Ultra HD",
				"Para você que gosta de reunir a família e os amigos" +
				" " +
				"para assistir algum filme engraçado",
				2296.17,
				"https://cdn.com/800x560/smart-tv-50-4k"
		);
		when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(
				expectedProduct));

		Product actualProduct = productService.findById(1L);

		verify(productRepository, times(1)).findById(1L);
		assertEquals(
				expectedProduct,
				actualProduct,
				"findById should return the expected product"
		);
	}

	@Test
	void findById_productNotFound() {
		when(productRepository.findById(1L)).thenReturn(java.util.Optional.empty());

		Product actualProduct = productService.findById(1L);

		verify(productRepository, times(1)).findById(1L);
		assertNull(
				actualProduct,
				"findById should return null if no product is found"
		);
	}
}
