package com.codemages.course.resources;

import com.codemages.course.entities.Category;
import com.codemages.course.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	private final CategoryService service;

	@Autowired
	public CategoryResource(CategoryService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
}
