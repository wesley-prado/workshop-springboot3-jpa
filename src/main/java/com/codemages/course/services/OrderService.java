package com.codemages.course.services;

import com.codemages.course.entities.Order;
import com.codemages.course.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
	private final OrderRepository repository;

	@Autowired
	public OrderService(OrderRepository repository) {
		this.repository = repository;
	}

	public List<Order> findAll() {
		return repository.findAll();
	}

	@Transactional
	public Order findById(Long id) {
		Optional<Order> opt = repository.findById(id);
		return opt.orElse(null);
	}
}
