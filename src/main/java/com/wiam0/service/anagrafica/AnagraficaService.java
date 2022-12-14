package com.wiam0.service.anagrafica;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiam0.model.entity.Anagrafica;
import com.wiam0.model.entity.Utente;
import com.wiam0.model.entity.repository.AnagraficaRepo;
import com.wiam0.model.entity.repository.UtenteRepo;
import com.wiam0.model.response.BaseWiamResponse;
import com.wiam0.service.response.AnagraficaResponse;
import com.wiam0.util.Constants;

@Service
public class AnagraficaService {

	@Autowired private AnagraficaRepo anagRepo;
	@Autowired private UtenteRepo utRepo;
	
	// insert
	public BaseWiamResponse salvaAnagrafica(String username, String bt, Anagrafica anagrafica) {
		
		BaseWiamResponse oResponse = new BaseWiamResponse();
		Utente ut = new Utente();
		try {
			// prendo utente
			if(username != null && !username.isEmpty())
				 ut = utRepo.findByUsername(username);
			else
				ut = utRepo.findByBt(bt);
			
			anagrafica.setUtente(ut);
			anagRepo.save(anagrafica);
			
		}catch(Exception e) {
			oResponse.setIsError(true);
			oResponse.setErrDsc(Constants.BaseWiamResponseC.GenericError);
			oResponse.setCodiceEsito(Constants.BaseWiamResponseC.esitoCodiceKo);
		}
		
		oResponse.setCodiceEsito("00");
		return oResponse;
	}
	
	//update
	public BaseWiamResponse modificaAnagrafica(String username, String bt, Anagrafica anagrafica) {

		BaseWiamResponse oResponse = new BaseWiamResponse();
		Anagrafica request = new Anagrafica();
		Utente ut = new Utente();
		try {
			// prendo anagrafica con username
			if(username != null && !username.isEmpty()) {
				request = anagRepo.findByUtenteUsername(username); 
			}
			// se username assente prendo utente pr bt e rimedio username per prendere anagrafica utente
			else {
				ut = utRepo.findByBt(bt); 
				request = anagRepo.findByUtenteUsername(ut.getUsername()); 
			}
			// sostituisco i dati su db con quelli in request
			request.setCodiceFiscale(anagrafica.getCodiceFiscale());
			request.setCognome(anagrafica.getCognome());
			request.setComune(anagrafica.getComune());
			request.setDataNascita(anagrafica.getDataNascita());
			request.setNazionalità(anagrafica.getNazionalità());
			request.setNome(anagrafica.getNome());
	

			anagRepo.save(request);

		}catch(Exception e) {
			oResponse.setIsError(true);
			oResponse.setErrDsc(Constants.BaseWiamResponseC.GenericError);
			oResponse.setCodiceEsito(Constants.BaseWiamResponseC.esitoCodiceKo);
		}

		oResponse.setCodiceEsito("00");
		return oResponse;
	}
	
	// cancella anagrafica utente
	public BaseWiamResponse cancellaAnagrafica(String username, String bt, Anagrafica anagrafica) {

		BaseWiamResponse oResponse = new BaseWiamResponse();
		Utente ut = new Utente();
		try {
			// prendo anagrafica con username
			if(username != null && !username.isEmpty()) {
			Anagrafica req=	anagRepo.findByUtenteUsername(username);
			anagRepo.delete(req);
			}
			// se username assente prendo utente pr bt e rimedio username per prendere anagrafica utente
			else {
				ut = utRepo.findByBt(bt); 
				Anagrafica req= anagRepo.findByUtenteUsername(ut.getUsername()); 
				anagRepo.delete(req);
			}
		}catch(Exception e) {
			oResponse.setIsError(true);
			oResponse.setErrDsc(Constants.BaseWiamResponseC.GenericError);
			oResponse.setCodiceEsito(Constants.BaseWiamResponseC.esitoCodiceKo);
		}
		
		return oResponse;
	}
	
	//get all by name
	public AnagraficaResponse getAnagraficaByName(String nome){
		
		AnagraficaResponse response = new AnagraficaResponse();
		Optional<List<Anagrafica>>  responseList = null;
		
		try {
			responseList = Optional.of(anagRepo.anagraficaByName(nome));
		}catch(Exception e) {
			response.setCodiceEsito("ERKO-05");
			response.setErrDsc("internal server error");
			response.setIsError(true);
			return response;
		}
		
		if(responseList.isEmpty()) {
			response.setCodiceEsito("ERKO-06");
			response.setErrDsc("no values found internal db");
			response.setIsError(true);
		}
		
		response.setListaAnagrafica(responseList.get());
		response.setCodiceEsito("00");
		return response;
		
	}
	
	//get all by cf
	public AnagraficaResponse getAnagraficaByCF(String cf){

		AnagraficaResponse response = new AnagraficaResponse();
		Optional<List<Anagrafica>>  responseList = null;

		try {
			responseList = anagRepo.anagraficaByCf(cf);
		}catch(Exception e) {
			response.setCodiceEsito("ERKO-05");
			response.setErrDsc("internal server error");
			response.setIsError(true);
			return response;
		}

		if(responseList.isEmpty()) {
			response.setCodiceEsito("ERKO-06");
			response.setErrDsc("no values found internal db");
			response.setIsError(true);
		}

		response.setListaAnagrafica(responseList.get());
		response.setCodiceEsito("00");
		return response;

	}
}
