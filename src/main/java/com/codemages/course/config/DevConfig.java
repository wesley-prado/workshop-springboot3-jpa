package com.codemages.course.config;

import com.codemages.course.entities.User;
import com.codemages.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {
	private final UserRepository userRepository;

	@Autowired
	public DevConfig(UserRepository userRepository) {
		this.userRepository = userRepository;
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

		userRepository.saveAllAndFlush(Arrays.asList(u1, u2));
	}
}
