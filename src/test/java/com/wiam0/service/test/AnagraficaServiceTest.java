package com.wiam0.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.wiam0.model.entity.Anagrafica;
import com.wiam0.model.entity.Utente;
import com.wiam0.model.entity.repository.AnagraficaRepo;
import com.wiam0.service.anagrafica.AnagraficaService;
import com.wiam0.service.response.AnagraficaResponse;

@SpringBootTest
@ActiveProfiles("test")
public class AnagraficaServiceTest {

	@MockBean
	AnagraficaRepo anagRepo;
	@Autowired
	AnagraficaService anagService;
	
	
	@Test
	public void findByNameTestOk() {
		
		Utente utente1 = new Utente();
		utente1.setAbi("abi1");
		utente1.setBt("bt1");
		utente1.setCellulare("cellulare1");
		utente1.setEmail("email1");
		utente1.setId(1);
		utente1.setUsername("username1");
		
		Utente utente2 = new Utente();
		utente1.setAbi("abi2");
		utente1.setBt("bt2");
		utente1.setCellulare("cellulare2");
		utente1.setEmail("email2");
		utente1.setId(2);
		utente1.setUsername("username2");
		
		Anagrafica aRequest1 = new Anagrafica();
		aRequest1.setCodiceFiscale("cf1");
		aRequest1.setCognome("cognome1");
		aRequest1.setComune("comune1");
		aRequest1.setDataNascita("21/12/1992");
		aRequest1.setNazionalità("nazionalita1");
		aRequest1.setNome("nome1");
		aRequest1.setUtente(utente1);
		
		Anagrafica aRequest2 = new Anagrafica();
		aRequest2.setCodiceFiscale("cf2");
		aRequest2.setCognome("cognome2");
		aRequest2.setComune("comune2");
		aRequest2.setDataNascita("21/12/1993");
		aRequest2.setNazionalità("nazionalita2");
		aRequest2.setNome("nome2");
		aRequest2.setUtente(utente2);
		
		List<Anagrafica> listaAnag = new ArrayList<>();
		
		listaAnag.add(aRequest1); listaAnag.add(aRequest2);
		
		when(anagRepo.anagraficaByName("")).thenReturn(listaAnag);
		
		AnagraficaResponse response = anagService.getAnagraficaByName("");
		
		assertThat(response.getListaAnagrafica().stream().filter(resp -> resp.getNome().equals("nome2")).findAny().get()).isEqualTo(aRequest2);
		
		
		
	}
	
	@Test
	public void insertAnagraficaOk() {
		
		
	}
	
}
