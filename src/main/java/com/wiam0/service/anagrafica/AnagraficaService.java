package com.wiam0.service.anagrafica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiam0.model.entity.Anagrafica;
import com.wiam0.model.entity.Utente;
import com.wiam0.model.entity.repository.AnagraficaRepo;
import com.wiam0.model.entity.repository.UtenteRepo;
import com.wiam0.model.response.BaseWiamResponse;
import com.wiam0.util.Constants;

@Service
public class AnagraficaService {

	@Autowired private AnagraficaRepo anagRepo;
	@Autowired private UtenteRepo utRepo;
	
	
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
		
		return oResponse;
	}
	
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
}
