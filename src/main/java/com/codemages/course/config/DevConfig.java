package com.codemages.course.config;

import com.codemages.course.entities.Category;
import com.codemages.course.entities.Product;
import com.codemages.course.entities.User;
import com.codemages.course.entities.Order;
import com.codemages.course.entities.enums.OrderStatus;
import com.codemages.course.repositories.CategoryRepository;
import com.codemages.course.repositories.OrderRepository;
import com.codemages.course.repositories.ProductRepository;
import com.codemages.course.repositories.UserRepository;
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
	private final UserRepository     userRepository;
	private final OrderRepository    orderRepository;
	private final CategoryRepository categoryRepository;
	private final ProductRepository  productRepository;

	@Autowired
	public DevConfig(
			UserRepository userRepository,
			OrderRepository orderRepository,
			CategoryRepository categoryRepository,
			ProductRepository productRepository
	) {
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
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
		orderRepository.saveAll(Arrays.asList(
				new Order(null, Instant.parse("2024-04-27T23:52:00Z"), u1, OrderStatus.PAID),
				new Order(null, Instant.parse("2024-04-13T15:25:32Z"), u1, OrderStatus.SHIPPED),
				new Order(
						null,
						Instant.parse("2023-07-01T23:52:00Z"),
						u3,
						OrderStatus.WAITING_PAYMENT
				)
		));
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
	}
}
