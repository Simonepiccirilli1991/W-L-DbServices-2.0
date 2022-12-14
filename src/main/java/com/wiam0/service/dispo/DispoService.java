package com.wiam0.service.dispo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.wiam0.model.entity.Account;
import com.wiam0.model.entity.Utente;
import com.wiam0.model.entity.repository.AccountRepo;
import com.wiam0.model.entity.repository.UtenteRepo;
import com.wiam0.service.request.DispoRequest;
import com.wiam0.service.response.DIspoResponse;
import com.wiam0.util.Constants;



@Service
public class DispoService {

	@Autowired
	AccountRepo dispoRepo;
	@Autowired
	UtenteRepo utRepo;

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


		Account userToPay = null;
		Account userToReceive = null;

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
			if(Boolean.TRUE.equals(makeTransactionDebit(request.getImporto(), userToPay))) {
				// ha cash quindi fare effettivo update dei 2 conti
				Double soldiConto = userToPay.getSaldoattuale();
				Double debitoConto = userToPay.getDebito();
				//caso1 ha soldi sul conto per coprire una parte
				if(soldiConto != 0) {
					//controllo se puo pagare diretto
					if(soldiConto >= request.getImporto()) {
						Double updateContoPay = soldiConto - request.getImporto();
						Double updateContoReceive = userToReceive.getSaldoattuale() + request.getImporto();
						// scalo soldi da chi paga
						userToPay.setSaldoattuale(updateContoPay);
						dispoRepo.addBalance(userToPay.getNumeroconto(), updateContoPay);
						// addo soldi a chi riceve
						dispoRepo.addBalance(userToReceive.getNumeroconto(), updateContoReceive);
					}//non puo pagare diretto
					else {
						// calcolo quanto scalare a conto e aggiungere a debito
						Double effective = request.getImporto() - soldiConto;
						debitoConto = debitoConto + effective;	
						dispoRepo.addBalanceDebit(userToPay.getNumeroconto(), 0.00, debitoConto);
						// pago chi deve riceve
						Double updateContoReceive = userToReceive.getSaldoattuale() + request.getImporto();
						dispoRepo.addBalance(userToReceive.getNumeroconto(), updateContoReceive);
					}

				}//caso2 non li ha vado diretto su debito, so gia che puo farlo se arriva qui
				else {
					// calcolo quanto scalare a conto e aggiungere a debito
					Double effective = request.getImporto() - soldiConto;
					debitoConto = debitoConto + effective;
					userToPay.setDebito(debitoConto);
					userToPay.setSaldoattuale(0.00);
					
					dispoRepo.addBalanceDebit(userToPay.getNumeroconto(), 0.00, debitoConto);
					// pago chi deve riceve
					Double updateContoReceive = userToReceive.getSaldoattuale() + request.getImporto();
					dispoRepo.addBalance(userToReceive.getNumeroconto(), updateContoReceive);
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
			Double soldiConto = userToPay.getSaldoattuale();

			if(soldiConto >= request.getImporto()) {
				Double updateContoPay = soldiConto - request.getImporto();
				// pago diretto
				dispoRepo.addBalance(userToPay.getNumeroconto(), updateContoPay);
				// addo a chi riceve
				dispoRepo.addBalance(userToReceive.getNumeroconto(), request.getImporto());
			}else {
				response.setCodiceEsito("erko-cash");
				response.setIsError(true);
				response.setErrDsc("Operazione non possibile, controllare platform");
				response.setTransactionOk(false);
				return response;
			}
		}
		
			response.setCodiceEsito("00");
			response.setTransactionOk(true);
			return response;
		}

		private Boolean makeTransactionDebit(Double importo,Account conto ) {

			//caso 1 ci sono soldi sul conto
			if(importo <= conto.getSaldoattuale()) {
				return true;
			}
			//caso 2 non ci sono soldi su conto, ma puo andare in debito
			else if(importo > conto.getSaldoattuale() && Constants.Dispo.DEBIT_LIMIT > conto.getDebito()) {

				//controllo che importo non faccia superare tetto massimo
				if(conto.getDebito()+importo <= Constants.Dispo.DEBIT_LIMIT+conto.getSaldoattuale()) 
					return true;
				
				else
					return false;

			}
			//caso 3 non dovrebbe esserci ma e venerdi e so stanco
			return false;
		}
	
		// insert
		public DIspoResponse insertDispoAccount(DispoRequest request) {
			
			DIspoResponse response = new DIspoResponse();
			
			Optional<Utente> ut = Optional.of(utRepo.findByBt(request.getBtToReceiv()));
			
			if(ut.isEmpty()) {
				response.setCodiceEsito("Erko-03");
				response.setErrDsc("utente not found");
				response.setIsError(true);
				return response;
			}
			
			Account account = new Account();
			String numeroConto = ut.get().getUsername()+ut.get().getBt().hashCode();
			account.setNumeroconto(numeroConto);
			account.setSaldoattuale(request.getImporto());
			account.setTipoConto(request.getTipoConto());
			
			Double debito = (ObjectUtils.isEmpty(request.getDebito()) ? 0.00 : request.getDebito());
			account.setDebito(debito);
			account.setUtente(ut.get());
			dispoRepo.save(account);
			
			response.setCodiceEsito("00");
			response.setNumeroConto(numeroConto);
			
			return response;
		}
		// get
		public DIspoResponse getDispoInfo(DispoRequest request) {
			
			DIspoResponse response = new DIspoResponse();
			List<Account> dispoList = new ArrayList<>();
			try {
				dispoList = dispoRepo.findAll();
			}catch(Exception e) {
				response.setCodiceEsito("erko-03");
				response.setErrDsc("Utente dispo not found");
				response.setIsError(true);
				return response;
			}
			
			Optional<Account> utDispo = dispoList.stream().filter(resp -> resp.getUtente().getBt().equals(request.getBtToReceiv())).findAny();
			if(utDispo.isEmpty()) {
				response.setCodiceEsito("erko-03");
				response.setErrDsc("Utente dispo not found");
				response.setIsError(true);
				return response;
			}
			response.setDispoInfo(utDispo.get());
			response.setCodiceEsito("00");
			
			return response;
		}
}
