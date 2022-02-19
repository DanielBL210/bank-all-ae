
package com.vobi.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vobi.bank.domain.UserType;
import com.vobi.bank.domain.Users;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class UsersRepositoryIT {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UserTypeRepository userTypeRepository;

	@Test
	@Order(1)
	void test() {
		assertNotNull(usersRepository);
		assertNotNull(userTypeRepository);
	}

	@Test
	@Order(2)
	void debeCrearUnuser() {
		// Arrange
		Integer idUserType = 1;
		String iduser = "pepe@hola.com";

		Users user = null;
		UserType userType = userTypeRepository.findById(idUserType).get();

		user = new Users();
		user.setName("Andrea");
		user.setUserEmail(iduser);
		user.setUserType(userType);
		user.setEnable("Y");
		user.setToken("sdfsfdgsjkfhsjkdhfsjk");

		// Act

		user = usersRepository.save(user);

		// Assert

		assertNotNull(user, "El user es nulo no se pudo grabar");
	}

	@Test
	@Order(3)
	void debeModificarUnuser() {
		// Arrange
		String iduser = "pepe@hola.com";

		Users user = null;
		user = usersRepository.findById(iduser).get();
		user.setEnable("N");

		// Act

		user = usersRepository.save(user);

		// Assert

		assertNotNull(user, "El user es nulo no se pudo grabar");
	}

	@Test
	@Order(4)
	void debeBorrarUnuser() {
		// Arrange
		String iduser = "pepe@hola.com";

		Users user = null;
		Optional<Users> userOptional = null;
		user = usersRepository.findById(iduser).get();

		// Act
		usersRepository.delete(user);
		userOptional = usersRepository.findById(iduser);

		// Assert
		assertFalse(userOptional.isPresent(), "No pudo borrar");
	}

	@Test
	@Order(5)
	void debeConsultarTodosLosusers() {
		// Arrange
		List<Users> users = null;

		// Act
		users = usersRepository.findAll();

		users.forEach(user -> log.info(user.getName()));

		// Assert
		assertFalse(users.isEmpty(), "No encontro users");
	}

}
