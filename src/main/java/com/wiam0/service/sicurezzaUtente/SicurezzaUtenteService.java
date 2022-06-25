package com.wiam0.service.sicurezzaUtente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiam0.model.entity.SicurezzaUtente;
import com.wiam0.model.entity.Utente;
import com.wiam0.model.entity.repository.SicurezzaUtenteRepo;
import com.wiam0.model.entity.repository.UtenteRepo;
import com.wiam0.model.response.BaseWiamResponse;
import com.wiam0.service.request.SicurezzaUtenteRequest;
import com.wiam0.service.response.GetDomandeSicurezzaResponse;

@Service
public class SicurezzaUtenteService {

	@Autowired private SicurezzaUtenteRepo suRepo;
	@Autowired private UtenteRepo utRepo;
	
	// certifica mail su sicurezza utente
	public BaseWiamResponse confermaMailCertificata(SicurezzaUtenteRequest request) {
		
		BaseWiamResponse oResponse = new BaseWiamResponse();
		SicurezzaUtente iRequest = new SicurezzaUtente();
		
		try {
			
		// controllo se esiste gia sicurezza per questo utente
			
		SicurezzaUtente utenteEs = suRepo.findByUtenteUsername(request.getUsername());
		
		if(utenteEs != null) {
			utenteEs.setEmailVerificata(request.isCertificaMail());
			suRepo.save(utenteEs);
		}else {
			
			Utente ut = utRepo.findByUsername(request.getUsername());
			iRequest.setUtente(ut);
			iRequest.setEmailVerificata(request.isCertificaMail());
			suRepo.save(iRequest);
		}
		}
		catch(Exception e) {
			oResponse.setIsError(true);
			return oResponse;
		}
		return oResponse;
	}
	// certifica cell, al momento non pmlemnetata in logica
	public BaseWiamResponse confermaCellCertificato(SicurezzaUtenteRequest request) {
		
		BaseWiamResponse oResponse = new BaseWiamResponse();
		SicurezzaUtente iRequest = new SicurezzaUtente();
		
		try {
			
		// controllo se esiste gia sicurezza per questo utente
			
		SicurezzaUtente utenteEs = suRepo.findByUtenteUsername(request.getUsername());
		
		if(utenteEs != null) {
			utenteEs.setEmailVerificata(request.getCertificaCell());
			suRepo.save(utenteEs);
		}else {
			
			Utente ut = utRepo.findByUsername(request.getUsername());
			iRequest.setUtente(ut);
			iRequest.setEmailVerificata(request.getCertificaCell());
			suRepo.save(iRequest);
		}
		}
		catch(Exception e) {
			oResponse.setIsError(true);
			return oResponse;
		}
		return oResponse;
	}
	
	// censisci domande e risposte sicurezza
	public BaseWiamResponse censisciDomandeErispSic(SicurezzaUtenteRequest request) {
		
		BaseWiamResponse oResponse = new BaseWiamResponse();
		SicurezzaUtente iRequest = new SicurezzaUtente();
		
		try {
			
		// controllo se esiste gia sicurezza per questo utente
			
		SicurezzaUtente utenteEs = suRepo.findByUtenteUsername(request.getUsername());
		
		if(utenteEs != null) {
			utenteEs.setDomandaSicurezza1(request.getDomandaSicurezza1());
			utenteEs.setDomandaSicurezza2(request.getDomandaSicurezza2());
			utenteEs.setDomandaSicurezza3(request.getDomandaSicurezza3());
			utenteEs.setRispostaSicurezza1(request.getRispostaSicurezza1());
			utenteEs.setRispostaSicurezza2(request.getRispostaSicurezza2());
			utenteEs.setRispostaSicurezza3(request.getRispostaSicurezza3());
		
			suRepo.save(utenteEs);
		}else {
			
			Utente ut = utRepo.findByUsername(request.getUsername());
			iRequest.setUtente(ut);
			iRequest.setDomandaSicurezza1(request.getDomandaSicurezza1());
			iRequest.setDomandaSicurezza2(request.getDomandaSicurezza2());
			iRequest.setDomandaSicurezza3(request.getDomandaSicurezza3());
			iRequest.setRispostaSicurezza1(request.getRispostaSicurezza1());
			iRequest.setRispostaSicurezza2(request.getRispostaSicurezza2());
			iRequest.setRispostaSicurezza3(request.getRispostaSicurezza3());
			suRepo.save(iRequest);
		}
		}
		catch(Exception e) {
			oResponse.setIsError(true);
			return oResponse;
		}
		return oResponse;
	}
	
	// get domande sicurezza
	public GetDomandeSicurezzaResponse getDomandeSicurezza(SicurezzaUtenteRequest request) {
		
		GetDomandeSicurezzaResponse oResponse = new GetDomandeSicurezzaResponse();
		SicurezzaUtente iRequest = new SicurezzaUtente();
		
		try {
			
		// controllo se esiste gia sicurezza per questo utente
			
		SicurezzaUtente utenteEs = suRepo.findByUtenteUsername(request.getUsername());
		
		if(utenteEs != null) {
			oResponse.setDomandaSicurezza1(utenteEs.getDomandaSicurezza1());
			oResponse.setDomandaSicurezza2(utenteEs.getDomandaSicurezza2());
			oResponse.setDomandaSicurezza3(utenteEs.getDomandaSicurezza3());

		}else {
			
			oResponse.setIsError(true);
			oResponse.setErrDsc("Domande non censite");
			oResponse.setCodiceEsito("02");
		}
		}
		catch(Exception e) {
			oResponse.setIsError(true);
			return oResponse;
		}
		return oResponse;
	}
	
	//TODO check risposte sicurezza
	public BaseWiamResponse checkDomandeSicurezza(SicurezzaUtenteRequest request) {
		
		BaseWiamResponse oResponse = new BaseWiamResponse();
		
		try {
			
		// controllo se esiste gia sicurezza per questo utente
			
		SicurezzaUtente utenteEs = suRepo.findByUtenteUsername(request.getUsername());
		
		String risp1 = utenteEs.getRispostaSicurezza1();
		String risp2 = utenteEs.getRispostaSicurezza2();
		String risp3 = utenteEs.getRispostaSicurezza3();
		
		if(!request.getRispostaSicurezza1().contentEquals(risp1) || !request.getRispostaSicurezza2().contentEquals(risp2)
				|| !request.getRispostaSicurezza3().contentEquals(risp3)) {
		
			oResponse.setIsError(true);
			oResponse.setErrDsc("Risposte non corrette");
			oResponse.setCodiceEsito("03");
		}
		}
		catch(Exception e) {
			oResponse.setIsError(true);
			return oResponse;
		}
		return oResponse;
	}
	
	//Spostare pin in altra tabella con un counter per check dataNascita
	
}
