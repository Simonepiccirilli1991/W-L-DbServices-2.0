package com.wiam0.db.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.wiam0.model.entity.Utente;
import com.wiam0.model.entity.repository.UtenteRepo;

@DataJpaTest
@AutoConfigureTestDatabase //h2
@ActiveProfiles("test")
@Sql(scripts = "sqlscrypt/insertUtente.sql")
@Sql(scripts = "sqlscrypt/deleteUtente.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class DispoDbTest {

	@Autowired
	UtenteRepo utenteRepo;
	
	// funziona perfettamente, il problema e fargli leggere lo schema, al momento sono tolti ma va trovata fix
	@Test
	public void testUtente1() {
		 Utente response = utenteRepo.findByUsername("usernameProva1");
		 
		 System.out.println(response);
		 assertThat(response.getAbi()).isEqualTo("abiProva1");
	}
}
