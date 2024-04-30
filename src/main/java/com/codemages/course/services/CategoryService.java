package com.codemages.course.services;

import com.codemages.course.entities.Category;
import com.codemages.course.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
	private final CategoryRepository repository;

	@Autowired
	public CategoryService(CategoryRepository repository) {
		this.repository = repository;
	}

	public List<Category> findAll() {
		return repository.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> opt = repository.findById(id);

		return opt.orElse(null);
	}
}
