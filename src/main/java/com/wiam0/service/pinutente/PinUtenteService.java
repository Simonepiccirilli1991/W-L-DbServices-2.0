package com.wiam0.service.pinutente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiam0.model.entity.PinUtente;
import com.wiam0.model.entity.Utente;
import com.wiam0.model.entity.repository.PinUtenteRepo;
import com.wiam0.model.entity.repository.UtenteRepo;
import com.wiam0.model.response.BaseWiamResponse;
import com.wiam0.service.request.PinUtenteRequest;

@Service
public class PinUtenteService {

	@Autowired private PinUtenteRepo pinUtRepo;
	// ho bisogno anche del repo Utente per effettuare il cambio pin
	@Autowired private UtenteRepo utRepo;

	//controllo pin e aumento counter
	public BaseWiamResponse checkPin(PinUtenteRequest request) {

		BaseWiamResponse oResponse = new BaseWiamResponse();
		try {
			PinUtente response = pinUtRepo.findByUtenteUsername(request.getUsername());

			if(!request.getPin().equals(response.getPin())) {
				int counter = response.getCounter() + 1;
				response.setCounter(counter);
				pinUtRepo.save(response);

				oResponse.setIsError(true);
				oResponse.setErrDsc("Pin non corretto");
				oResponse.setCodiceEsito("004");
			}
			else {
				int counter = 0;
				response.setCounter(counter);
				pinUtRepo.save(response);
				//TODO vedere se resettare qui e attivare sopra con logica o meno
				//la richiestaDataNascita su status
			}

		}catch(Exception e) {
			oResponse.setIsError(true);
		}

		return oResponse;
	}
	// check conformita pin
	public BaseWiamResponse checkConformitàPin(PinUtenteRequest request) {

		BaseWiamResponse oResponse = new BaseWiamResponse();

		try {
			PinUtente response = pinUtRepo.findByUtenteUsername(request.getUsername());
			// controllo che nuovo pin sia diverso da quello precedente.
			// i controlli dei caratteri sono stati inseriti nello step prima
			if(request.getPin().contentEquals(response.getPin())) {
				oResponse.setIsError(true);
				oResponse.setErrDsc("Pin uguale a quello gia esistente, sceglierne un altro");
				oResponse.setCodiceEsito("005");
			}
		}
		catch(Exception e) {
			oResponse.setIsError(true);
			oResponse.setErrDsc("errore tecnico si prega di riprovare piu tardi - utente censito?");
			oResponse.setCodiceEsito("002");
		}

		return oResponse;
	}
	// cambio pin effettivo
	public BaseWiamResponse cambioPin(PinUtenteRequest request) {

		BaseWiamResponse oResponse = new BaseWiamResponse();

		try {
			// prendo utente
			Utente ut = utRepo.findByUsername(request.getUsername());

			PinUtente pin = pinUtRepo.findByUtenteUsername(request.getUsername());
			// se lo facevo su utente facevo no step 1 meno, se ho tempo riportare
			pin.setUtente(ut);
			pin.setPin(request.getPin());

			pinUtRepo.save(pin);
		}catch(Exception e) {

			oResponse.setIsError(true);
			oResponse.setErrDsc("errore tecnico si prega di riprovare piu tardi - utente censito?");
			oResponse.setCodiceEsito("006");
		}

		return oResponse;
	}
	
}
