package com.codemages.course.services;

import com.codemages.course.entities.User;
import com.codemages.course.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks UserService userService;

	@Mock UserRepository userRepository;

	@Test
	void testFindAll() {
		User user1 = new User(
				1L,
				"John Doe",
				"jhon.doe@mail.com",
				"(15) 99999-9999",
				"any_password"
		);
		User user2 = new User(
				2L,
				"Jane Doe",
				"jane.doe@mail.com",
				"(15) 88888-8888",
				"another_password"
		);
		List<User> expectedOutput = List.of(user1, user2);

		when(userRepository.findAll()).thenReturn(expectedOutput);

		List<User> result = userService.findAll();

		assertEquals(2, result.size(), "findAll should return 2 users");
		assertEquals(
				expectedOutput,
				result,
				"findAll should return the expected output"
		);
		verify(userRepository, times(1)).findAll();
	}

	@Test
	void testFindById() {
		User expectedResponse = generateUserMock();

		when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(
				expectedResponse));

		User actualResponse = userService.findById(1L);

		assertEquals(
				expectedResponse,
				actualResponse,
				"findById should return the expected user"
		);
		verify(userRepository, times(1)).findById(1L);
		assertEquals(
				expectedResponse,
				actualResponse,
				"findById should return the expected user"
		);
	}

	@Test
	void testInsert() {
	}

	@Test
	void testDelete() {}

	@Test
	void testUpdate() {}

	@Test
	void testUpdateUser() {}

	private static User generateUserMock() {
		return new User(
				1L,
				"John Doe",
				"jhon.doe@mail.com",
				"(15) 99999-9999",
				"any_password"
		);
	}
}
