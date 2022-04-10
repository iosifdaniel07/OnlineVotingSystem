package com.sistemonlinevoting.Server;

import com.sistemonlinevoting.Server.dataBase.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void getUsers() {

	}

}
