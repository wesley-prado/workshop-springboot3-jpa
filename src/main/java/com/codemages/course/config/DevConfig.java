package com.codemages.course.config;

import com.codemages.course.entities.*;
import com.codemages.course.entities.enums.OrderStatus;
import com.codemages.course.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {
	private final UserRepository      userRepository;
	private final OrderRepository     orderRepository;
	private final CategoryRepository  categoryRepository;
	private final ProductRepository   productRepository;
	private final OrderItemRepository orderItemRepository;

	@Autowired
	public DevConfig(
			UserRepository userRepository,
			OrderRepository orderRepository,
			CategoryRepository categoryRepository,
			ProductRepository productRepository,
			OrderItemRepository orderItemRepository
	) {
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
		this.orderItemRepository = orderItemRepository;
	}

	@Override public void run(String... args) {
		User u1 = new User(
				null,
				"Wesley Prado",
				"wesleyprado.dev@gmail.com",
				"+55 15 99750-4312",
				"any_dev_pass"
		);
		User u2 = new User(
				null,
				"Beatriz Avelino",
				"beatrizavelino@anymail.com",
				"+55 11 99999-9999",
				"another_dev_pass"
		);
		User u3 = new User(
				null,
				"Gerson Prado",
				"gersonprado@anymail.com",
				"+55 11 99999-9998",
				"another_client_pass"
		);
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		Order o1 = new Order(null, Instant.parse("2024-04-27T23:52:00Z"), u1, OrderStatus.PAID);
		Order o2 = new Order(null, Instant.parse("2024-04-13T15:25:32Z"), u1, OrderStatus.SHIPPED);
		Order o3 = new Order(
				null,
				Instant.parse("2023-07-01T23:52:00Z"),
				u3,
				OrderStatus.WAITING_PAYMENT
		);
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));

		Category c1 = new Category(null, "Electronics");
		Category c2 = new Category(null, "Books");
		Category c3 = new Category(null, "Computers");

		categoryRepository.saveAll(List.of(c1, c2, c3));

		Product p1 = new Product(
				null,
				"MacBook Pro",
				"Apple laptop",
				2500.0,
				"https://www.apple.com/macbook-pro-13/"
		);
		Product p2 = new Product(
				null,
				"PC Gamer",
				"Custom-built PC",
				1200.0,
				"https://www.pcgamer.com/"
		);
		Product p3 = new Product(
				null,
				"Rails for Dummies",
				"Ruby on Rails book",
				100.0,
				"https://www.amazon.com/Rails-Dummies-Computer-Tech/dp/1118066435"
		);
		Product p4 = new Product(
				null,
				"Spring Boot for Experts",
				"Spring Boot book",
				90.0,
				"https://www.amazon.com/Spring-Boot-Action-Craig-Walls/dp/1617292540"
		);
		Product p5 = new Product(
				null,
				"Smart TV 4K",
				"Samsung TV",
				3000.0,
				"https://www.samsung.com/br/tvs/uhd-tv/"
		);

		p1.getCategories().add(c3);
		p2.getCategories().add(c3);
		p3.getCategories().add(c2);
		p4.getCategories().add(c2);
		p5.getCategories().add(c1);
		p5.getCategories().add(c3);

		productRepository.saveAll(List.of(p1, p2, p3, p4, p5));

		OrderItem oi1 = new OrderItem(o1, p1, 5, p1.getPrice());
		OrderItem oi2 = new OrderItem(o2, p5, 10, p5.getPrice());

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2));

		Payment pay1 = new Payment(null, Instant.parse("2024-04-13T18:25:32Z"), o1);
		o1.setPayment(pay1);

		orderRepository.save(o1);
	}
}
