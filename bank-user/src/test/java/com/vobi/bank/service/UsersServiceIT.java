package com.vobi.bank.service;

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
import com.vobi.bank.repository.UserTypeRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class UsersServiceIT {

	@Autowired
	UsersService usersService;

	@Autowired
	UserTypeRepository userTypeRepository;

	@Test
	@Order(1)
	void test() {
		assertNotNull(usersService);
		assertNotNull(userTypeRepository);
	}

	@Test
	@Order(2)
	void debeCrearUnuser() throws Exception{
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

		user = usersService.save(user);

		// Assert

		assertNotNull(user, "El user es nulo no se pudo grabar");
	}

	@Test
	@Order(3)
	void debeModificarUnuser() throws Exception{
		// Arrange
		String iduser = "pepe@hola.com";

		Users user = null;
		user = usersService.findById(iduser).get();
		user.setEnable("N");

		// Act

		user = usersService.update(user);

		// Assert

		assertNotNull(user, "El user es nulo no se pudo grabar");
	}

	@Test
	@Order(4)
	void debeBorrarUnuser() throws Exception{
		// Arrange
		String iduser = "pepe@hola.com";

		Users user = null;
		Optional<Users> userOptional = null;
		user = usersService.findById(iduser).get();

		// Act
		usersService.delete(user);
		userOptional = usersService.findById(iduser);

		// Assert
		assertFalse(userOptional.isPresent(), "No puedo borrar");
	}

	@Test
	@Order(5)
	void debeConsultarTodosLosusers() {
		// Arrange
		List<Users> users = null;

		// Act
		users = usersService.findAll();

		users.forEach(user -> log.info(user.getName()));

		// Assert
		assertFalse(users.isEmpty(), "No encontro users");
	}

}
