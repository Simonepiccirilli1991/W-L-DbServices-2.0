package com.wiam0.service.dispo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wiam0.model.entity.DispoConteUtente;
import com.wiam0.model.entity.repository.DispoUtenteRepo;
import com.wiam0.service.request.DispoRequest;
import com.wiam0.service.response.DIspoResponse;
import com.wiam0.util.Constants;



@Service
public class DispoService {

	@Autowired
	DispoUtenteRepo dispoRepo;
	
	
	public DIspoResponse dispoPayService(DispoRequest request) {

		DIspoResponse response = new DIspoResponse();

		if(ObjectUtils.isEmpty(request.getImporto()) || ObjectUtils.isEmpty(request.getUsernameToPay())
				|| ObjectUtils.isEmpty(request.getUsernameToReceive())) {
			response.setCodiceEsito("400");
			response.setIsError(true);
			response.setErrDsc("Missing parameter on request");
			return response;
		}
		
		
		DispoConteUtente userToPay = null;
		DispoConteUtente userToReceive = null;
		
		try {
			userToPay = dispoRepo.findByUtenteUsername(request.getUsernameToPay());
			userToReceive = dispoRepo.findByUtenteUsername(request.getUsernameToReceive());
		}catch(Exception e) {
			response.setCodiceEsito("404");
			response.setIsError(true);
			response.setErrDsc("Utente dispo conto non trovato");
			return response;
		}
		//TODO implementare gestione errore piu fica, questa fa cagare ( portarla poi su tutto il sw)
		if(ObjectUtils.isEmpty(userToPay) || ObjectUtils.isEmpty(userToReceive)) {
			response.setCodiceEsito("404");
			response.setIsError(true);
			response.setErrDsc("Utente dispo conto non trovato");
			return response;
		}
		
		// controllo che tipo di conto ha
		if(Boolean.TRUE.equals(userToPay.getTipoConto().equals(Constants.Dispo.DISPO_DEBIT))) {
			
			//TODO creare metodo che fa effettiva transazione
			response.setTransactionOk(makeTransactionDebit(request.getImporto(), userToPay));
		}else {
			//TODO creare metodo per fare transazione sui prepragati
		}
		
		return response;
	}
	
	private Boolean makeTransactionDebit(Double importo,DispoConteUtente conto ) {
		
		//caso 1 ci sono soldi sul conto
		if(importo <= conto.getSaldoAttuale()) {
			//TODO fai transazione
			return true;
		}
		//caso 2 non ci sono soldi su conto, ma puo andare in debito
		else if(importo > conto.getSaldoAttuale() && Constants.Dispo.DEBIT_LIMIT > conto.getDebito()) {
			
			//controllo che importo non faccia superare tetto massimo
			if(conto.getDebito()+importo <= Constants.Dispo.DEBIT_LIMIT+conto.getSaldoAttuale()) {
				//TODO fai transazione
			}else
				return false;
			
		}
		//caso 3 non dovrebbe esserci ma e venerdi e so stanco
		return false;
	}
}
