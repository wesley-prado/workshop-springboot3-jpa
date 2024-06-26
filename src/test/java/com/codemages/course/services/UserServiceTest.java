package com.codemages.course.services;

import com.codemages.course.entities.User;
import com.codemages.course.repositories.UserRepository;
import com.codemages.course.services.exceptions.DatabaseException;
import com.codemages.course.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks UserService userService;

	@Mock UserRepository userRepository;

	@Test
	void findAll() {
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
	void findById_WhenUserExists_ReturnsUser() {
		User expectedResponse = generateUserMock();

		when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(
				expectedResponse));

		User actualResponse = userService.findById(1L);

		verify(userRepository, times(1)).findById(1L);
		assertEquals(
				expectedResponse,
				actualResponse,
				"findById should return the expected user"
		);
	}

	@Test
	void findById_WhenUserNotExists_ThrowException() {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());

		Exception e = assertThrows(
				ResourceNotFoundException.class,
				() -> userService.findById(1L)
		);

		assertEquals(
				"Resource not found. Id 1",
				e.getMessage(),
				"Incorrect exception message"
		);
	}

	@Test
	void insert() {
		User userDTO = generateUserMock();
		userDTO.setId(null);

		User expectedResponse = new User(
				1L,
				userDTO.getName(),
				userDTO.getEmail(),
				userDTO.getPhone(),
				userDTO.getPassword()
		);
		when(userRepository.save(userDTO)).thenReturn(expectedResponse);

		User actualResponse = userService.insert(userDTO);

		verify(userRepository, times(1)).save(userDTO);
		assertEquals(
				expectedResponse,
				actualResponse,
				"The user returned is different from expected"
		);
	}

	@Test
	void delete_Success() {
		Long id = 1L;

		userService.delete(id);

		verify(userRepository, times(1)).deleteById(id);
	}

	@Test
	void delete_DataIntegrityViolationException() {
		Long id = 1L;

		doThrow(new DataIntegrityViolationException("Test exception")).when(
				userRepository).deleteById(id);

		Exception e = assertThrows(
				DatabaseException.class,
				() -> userService.delete(id)
		);

		assertEquals(
				"Test exception",
				e.getMessage(),
				"Incorrect exception message"
		);
	}

	@Test
	void update() {}

	/*Helpers*/
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
