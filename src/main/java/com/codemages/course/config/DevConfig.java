package com.codemages.course.config;

import com.codemages.course.entities.User;
import com.codemages.course.entities.Order;
import com.codemages.course.repositories.OrderRepository;
import com.codemages.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {
	private final UserRepository  userRepository;
	private final OrderRepository orderRepository;

	@Autowired
	public DevConfig(UserRepository userRepository, OrderRepository orderRepository) {
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
	}

	@Override public void run(String... args) throws Exception {
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
		orderRepository.saveAllAndFlush(Arrays.asList(
				new Order(null, Instant.parse("2024-04-27T23:52:00Z"), u1),
				new Order(null, Instant.parse("2024-04-13T15:25:32Z"), u1),
				new Order(null, Instant.parse("2023-07-01T23:52:00Z"), u3)
		));
	}
}
