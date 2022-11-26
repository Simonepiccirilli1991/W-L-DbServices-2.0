package com.wiam0.service.dispo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
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
	
	@Transactional(isolation = Isolation.REPEATABLE_READ)
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
			// controllo che abbia i soldi o sia nel limite per debiti
			if(Boolean.TRUE.equals(makeTransactionDebit(request.getImporto(), userToReceive))) {
				// ha cash quindi fare effettivo update dei 2 conti
				Double soldiConto = userToPay.getSaldoAttuale();
				Double debitoConto = userToPay.getDebito();
				//caso1 ha soldi sul conto per coprire una parte
				if(soldiConto != 0) {
					//controllo se puo pagare diretto
					if(soldiConto >= request.getImporto()) {
						Double updateContoPay = soldiConto - request.getImporto();
						// pago diretto
						dispoRepo.addBalance(request.getBtToPay(), request.getImporto());
					}//non puo pagare diretto
					else {
						// calcolo quanto scalare a conto e aggiungere a debito
					}
					
				}//caso2 non li ha vado diretto su debito, so gia che puo farlo se arriva qui
				else {
					
				}
			}// non ha soldi e supera platform debito torno eccezione 
			else {
				response.setCodiceEsito("erko-cash");
				response.setIsError(true);
				response.setErrDsc("Operazione non possibile, controllare platform");
				response.setTransactionOk(false);
				return response;
			}
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
