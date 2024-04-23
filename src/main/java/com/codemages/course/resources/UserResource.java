package com.codemages.course.resources;

import com.codemages.course.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<User> findAll() {
		User u = new User(
				1L,
				"Wesley Prado",
				"wesleyprado.dev@gmail.com",
				"+55 15 99750-4312",
				"any_password"
		);

		return ResponseEntity.ok().body(u);
	}
}
