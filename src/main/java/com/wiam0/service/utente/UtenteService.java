package com.wiam0.service.utente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiam0.model.entity.PinUtente;
import com.wiam0.model.entity.Utente;
import com.wiam0.model.entity.repository.PinUtenteRepo;
import com.wiam0.model.entity.repository.UtenteRepo;
import com.wiam0.model.response.BaseWiamResponse;
import com.wiam0.service.request.UtenteRequest;
import com.wiam0.service.response.CheckUtenteResponse;
import com.wiam0.util.Constants;

@Service
public class UtenteService {


	@Autowired private UtenteRepo utenteRepo;
	@Autowired private PinUtenteRepo pinRepo;

	// controlla se utente esiste
	public CheckUtenteResponse utenteExist(String username, String bt) {


		CheckUtenteResponse oResponse = new CheckUtenteResponse();
		boolean response = false;

		try {
			if(username != null && !username.isEmpty())
				response = utenteRepo.existsByUsername(username);
			else {
				response = utenteRepo.existsByBt(bt);
			}
			oResponse.setUtenteEsiste(response);

		}catch(Exception e) {
			oResponse.setIsError(true);
			return oResponse;
		}

		return oResponse;
	}
	// inserisco utente
	public BaseWiamResponse salvaUtente(UtenteRequest request) {

		BaseWiamResponse oResponse = new BaseWiamResponse();
		Utente utente = new Utente();
		PinUtente pin = new PinUtente();
		
		utente.setUsername(request.getUsername());
		utente.setAbi(request.getAbi());
		utente.setEmail(request.getEmail());
		utente.setCellulare(request.getCellulare());
		// setto pin e passo
		pin.setPin(request.getPin());
		pin.setUtente(utente);

		try {
			utenteRepo.save(utente);
			pinRepo.save(pin);
		}catch(Exception e) {
			oResponse.setIsError(true);
			oResponse.setErrDsc(Constants.BaseWiamResponseC.GenericError);
			oResponse.setCodiceEsito(Constants.BaseWiamResponseC.esitoCodiceKo);

			return oResponse;
		}

		return oResponse;
	}

	// cancello Utente
	public BaseWiamResponse cancellaUtente(Utente utente) {

		BaseWiamResponse oResponse = new BaseWiamResponse();

		try {
			utenteRepo.delete(utente);
		}catch(Exception e) {
			oResponse.setIsError(true);
			oResponse.setErrDsc(Constants.BaseWiamResponseC.GenericError);
			oResponse.setCodiceEsito(Constants.BaseWiamResponseC.esitoCodiceKo);

			return oResponse;
		}

		return oResponse;
	}
	
	// update info utente// caso da sviluppare
	
	public BaseWiamResponse modificaInfoUtente(Utente utente) {

		BaseWiamResponse oResponse = new BaseWiamResponse();
		Utente uResponse = new Utente();
		try {
			uResponse = utenteRepo.findByUsername(utente.getUsername());
			// controllo che l'utente sia gia effetivamente registrato
			if(uResponse != null)
				utenteRepo.save(utente);
		}catch(Exception e) {
			oResponse.setIsError(true);
			oResponse.setErrDsc(Constants.BaseWiamResponseC.GenericError);
			oResponse.setCodiceEsito(Constants.BaseWiamResponseC.esitoCodiceKo);

			return oResponse;
		}

		return oResponse;
	}
}
