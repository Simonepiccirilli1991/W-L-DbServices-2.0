package com.wiam0.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.wiam0.model.entity.Account;
import com.wiam0.model.entity.repository.AccountRepo;
import com.wiam0.service.dispo.DispoService;
import com.wiam0.service.request.DispoRequest;
import com.wiam0.service.response.DIspoResponse;
import com.wiam0.util.Constants;

@SpringBootTest
@ActiveProfiles("test")
public class DIspoServiceTest {

	@Autowired
	DispoService dispoService;
	@Mock
	AccountRepo dispoRepo;
	
	//@Test // test 1 - utente   ha conto debito con saldo sopra e debito attivo
	public void makeDispoOK() {
		
		DispoRequest request = new DispoRequest();
		request.setBtToPay("testToPay");
		request.setBtToReceiv("testToReceive");
		request.setImporto(45.00);
		request.setUsernameToPay("testToPay");
		request.setUsernameToReceive("testToReceive");
		
		// setto dto di chi paga
		Account utenteToPay = new Account();
		utenteToPay.setTipoConto(Constants.Dispo.DISPO_DEBIT);
		utenteToPay.setNumeroconto("conto1");
		utenteToPay.setSaldoattuale(35.00);
		utenteToPay.setDebito(235.00);
		
		
		// setto dto di chi paga
		Account utenteToreceive = new Account();
		utenteToPay.setTipoConto(Constants.Dispo.DISPO_DEBIT);
		utenteToPay.setNumeroconto("conto2");
		utenteToPay.setSaldoattuale(0.00);
		utenteToPay.setDebito(0.00);

		when(dispoRepo.findByUtenteUsername(request.getUsernameToPay())).thenReturn(utenteToPay);
		
		when(dispoRepo.findByUtenteUsername(request.getUsernameToPay())).thenReturn(utenteToreceive);
		
		
		DIspoResponse response = dispoService.dispoPayService(request);
		
		System.out.println(response);
		
		assertThat(response.getCodiceEsito()).isEqualTo("00");
		assertThat(response.getTransactionOk()).isEqualTo(true);
	}
	
	
	@Test
	public void mockRepoTest() {
		
	}
	
}
