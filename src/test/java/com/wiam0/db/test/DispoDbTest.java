package com.wiam0.db.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.wiam0.model.entity.DispoConteUtente;
import com.wiam0.model.entity.Utente;
import com.wiam0.model.entity.repository.DispoUtenteRepo;
import com.wiam0.model.entity.repository.UtenteRepo;
import com.wiam0.service.dispo.DispoService;
import com.wiam0.service.request.DispoRequest;
import com.wiam0.service.response.DIspoResponse;
import com.wiam0.util.Constants;

@DataJpaTest
@AutoConfigureTestDatabase //h2
@ActiveProfiles("test")
@ComponentScan("com.wiam0.service.dispo")
public class DispoDbTest {

	@Autowired
	UtenteRepo utenteRepo;
	@Autowired
	DispoUtenteRepo accountRepo;
	@Autowired
	DispoService dispoService;

	@Test
	public void testUtente() {
		
		 Utente utente = new Utente();
		 utente.setAbi("abi-Test1");
		 utente.setBt("bt-Test1");
		 utente.setCellulare("cellulare-Test1");
		 utente.setEmail("email-Test1");
		 utente.setUsername("username-Test1");
		 
		 utenteRepo.save(utente);
		 
		 List<Utente> response = utenteRepo.findAll();
		 
		Optional<Utente>  iUtente = response.stream().filter(resp -> resp.getUsername().equals("username-Test1")).findAny();
		
		assertThat(iUtente.get().getAbi()).isEqualTo("abi-Test1");
	}
	
	@Test
	public void testDispoOk() {
		
		// setto utente 1 che paga
		Utente utentePay = new Utente();
		utentePay.setAbi("utentePayAbi");
		utentePay.setBt("utentePayBt");
		utentePay.setCellulare("utentePayCellulare");
		utentePay.setEmail("utentePayEmail");
		utentePay.setUsername("utentePayUsername");
		// setto utente 2 che riceve
		Utente utenteReceive = new Utente();
		utenteReceive.setAbi("utenteReceiveAbi");
		utenteReceive.setBt("utenteReceiveBt");
		utenteReceive.setCellulare("utenteReceiveCellulare");
		utenteReceive.setEmail("utenteReceiveEmail");
		utenteReceive.setUsername("utenteReceiveUsername");
		
		List<Utente> listaUtenti = new ArrayList<Utente>();
		listaUtenti.add(utenteReceive); listaUtenti.add(utentePay);
		
		utenteRepo.saveAll(listaUtenti);
		
		DispoConteUtente accountPay = new DispoConteUtente();
		accountPay.setDebito(0.00);
		accountPay.setNumeroconto("1234");
		accountPay.setSaldoattuale(100.00);
		accountPay.setTipoConto(Constants.Dispo.DISPO_PREP);
		accountPay.setUtente(utentePay);
		
		DispoConteUtente accountReceive = new DispoConteUtente();
		accountReceive.setDebito(0.00);
		accountReceive.setNumeroconto("1111");
		accountReceive.setSaldoattuale(120.00);
		accountPay.setTipoConto(Constants.Dispo.DISPO_PREP);
		accountReceive.setUtente(utenteReceive);
		
		List<DispoConteUtente> listaAccount = new ArrayList<DispoConteUtente>();
		listaAccount.add(accountReceive); listaAccount.add(accountPay);
		
		accountRepo.saveAll(listaAccount);

		DispoRequest request = new DispoRequest();
		request.setBtToPay("utentePayBt");
		request.setBtToReceiv("utenteReceiveBt");
		request.setUsernameToPay("utentePayUsername");
		request.setUsernameToReceive("utenteReceiveUsername");
		request.setImporto(70.00);
		
		DIspoResponse response = dispoService.dispoPayService(request);
		
		assertThat(response.getCodiceEsito()).isEqualTo("00");
		
		assertThat(accountRepo.findByUtenteUsername("utenteReceiveUsername").getSaldoattuale()).isEqualTo(190.00);
		
		}
	
}
