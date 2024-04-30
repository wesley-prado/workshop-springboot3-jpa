package com.codemages.course.services;

import com.codemages.course.entities.Product;
import com.codemages.course.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	private final ProductRepository repository;

	@Autowired
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public List<Product> findAll() {
		return repository.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> opt = repository.findById(id);

		return opt.orElse(null);
	}
}
