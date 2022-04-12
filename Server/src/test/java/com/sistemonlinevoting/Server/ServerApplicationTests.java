package com.sistemonlinevoting.Server;

import com.sistemonlinevoting.Server.dataBase.Candidate;
import com.sistemonlinevoting.Server.dataBase.CandidateService;
import com.sistemonlinevoting.Server.dataBase.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ServerApplicationTests {

	@Autowired
		private CandidateService candidateService;

	@Test
	void getUsers() {

	}

}
