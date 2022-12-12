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
public class DispoDbTest {

	@Autowired
	UtenteRepo utenteRepo;
	
	@Test
	public void testUtente3() {
		
		 Utente utente = new Utente();
		 utente.setAbi("abi-Test1");
		 utente.setBt("bt-Test1");
		 utente.setCellulare("cellulare-Test1");
		 utente.setEmail("email-Test1");
		 utente.setUsername("username-Test1");
		 utente.setId(55);
		 
		 utenteRepo.save(utente);
		 
		 List<Utente> response = utenteRepo.findAll();
		 
		Optional<Utente>  iUtente = response.stream().filter(resp -> resp.getUsername().equals("username-Test1")).findAny();
		
		assertThat(iUtente.get().getAbi()).isEqualTo("abi-Test1");
	}
}
