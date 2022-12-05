package com.wiam0.db.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

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
	
	// funziona perfettamente
	@Test
	public void testUtente1() {
		 Utente response = utenteRepo.findByUsernameTest("usernameProva1");
		 
		 System.out.println(response);
		 assertThat(response.getAbi()).isEqualTo("abiProva1");
	}
	
	@Test
	public void testUtente2() {
		 List<Utente> response = utenteRepo.findAllTest();
		 
		 System.out.println(response);
		 
		Optional<Utente>  iUtente = response.stream().filter(resp -> resp.getUsername().equals("usernameProva1")).findAny();
		
		assertThat(iUtente.get().getAbi()).isEqualTo("abiProva1");
	}
	
	//@Test
	public void testUtente3() {
		
		 Utente utente = new Utente();
		 utente.setAbi("abiTest1");
		 utente.setBt("btTest1");
		 utente.setCellulare("cellulareTest1");
		 utente.setEmail("emailTest1");
		 utente.setUsername("usernameTest1");
		 
		 utenteRepo.saveUtente(utente);
		 
		 List<Utente> response = utenteRepo.findAllTest();
		 
		Optional<Utente>  iUtente = response.stream().filter(resp -> resp.getUsername().equals("usernameTest1")).findAny();
		
		assertThat(iUtente.get().getAbi()).isEqualTo("abiTest1");
	}
}
