package com.wiam0.service.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiam0.model.entity.PinUtente;
import com.wiam0.model.entity.SicurezzaUtente;
import com.wiam0.model.entity.StatusUtente;
import com.wiam0.model.entity.Utente;
import com.wiam0.model.entity.repository.PinUtenteRepo;
import com.wiam0.model.entity.repository.SicurezzaUtenteRepo;
import com.wiam0.model.entity.repository.StatusUtenteRepo;
import com.wiam0.model.entity.repository.UtenteRepo;
import com.wiam0.service.response.StatusRequest;
import com.wiam0.service.response.StatusResponse;

@Service
public class StatusService {

	@Autowired private UtenteRepo utRepo;
	@Autowired private StatusUtenteRepo statusRepo;
	@Autowired private PinUtenteRepo pinRepo;
	@Autowired private SicurezzaUtenteRepo scRepo;
	
	
	//fatta al momento per non creare altre tabelle ecc
	//TODO quando hai tempo implementa logica diversa
	// se su sicurezza utente cellCertificato e null crasha gestire poi
	// per fare tutto il giro devono essere presenti tutti i dati se no esciamo prima
	
	//reminder, se si spacca guarda su che taballa fa ultima queery
	
	public StatusResponse statusGeneric(StatusRequest request) {
		
		StatusResponse oResponse = new StatusResponse();
		StatusUtente statusDTO = new StatusUtente();
		
		// controllo che utente sia presente su db
		try {
			boolean utEx = utRepo.existsByUsername(request.getUsername());
			// se utente non presente setto false ed esco
			if(utEx == false) {
				oResponse.setUtenteRegistrato(false);
				return oResponse;
			}
		// prendo dto utente
			Utente utenteDTO =  utRepo.findByUsername(request.getUsername());
		// controllo che non ci sia gia uno status salvato per l'utente e salvo su flag
			boolean statusEx = statusRepo.existsByUtenteUsername(request.getUsername());
			// se esiste aggiorno dto
			if(statusEx)
				statusDTO = statusRepo.findByUtenteUsername(request.getUsername());
			
		// controllo che l'utente non sia in primo accesso guardando se ha sicurezza valorizzato
			boolean scEx = scRepo.existsByUtenteUsername(request.getUsername());
	
			if(!scEx) {
				oResponse.setUtenteRegistrato(true);
				oResponse.setPrimoAccesso(true);
				// setto statusDTO e salvo
				statusDTO.setPrimoAccesso(true);
				statusDTO.setUtenteRegistrato(true);

				if(!statusEx)
					statusDTO.setUtente(utenteDTO);

				statusRepo.save(statusDTO);
				return oResponse;
			}
		// visto che sicurezza e valorizzato capisco se sms o mail dal tipo certificato
		SicurezzaUtente sicurezzaDTO = scRepo.findByUtenteUsername(request.getUsername());
		String tipoUtente = "mail";
		if(sicurezzaDTO.getCellCertificato())
			tipoUtente = "cell";
		
		// controllo se è richiesta la dataNascita
		
		PinUtente pinDTO = pinRepo.findByUtenteUsername(request.getUsername());
		boolean richiestaDataNascita = false;
		if(pinDTO.getCounter() > 3)
			richiestaDataNascita = true;
		
		// setto dto e response e salvo
		statusDTO.setPrimoAccesso(false);
		statusDTO.setRichiestaDataNascita(richiestaDataNascita);
		statusDTO.setTipoUtente(tipoUtente);
		statusDTO.setUtenteRegistrato(true);
		
		if(!statusEx)
			statusDTO.setUtente(utenteDTO);
		
		statusRepo.save(statusDTO);
		
		oResponse.setPrimoAccesso(false);
		oResponse.setUtenteRegistrato(true);
		oResponse.setRichiestaDataNascita(richiestaDataNascita);
		oResponse.setTipoUtente(tipoUtente);
			
		}catch(Exception e) {
			oResponse.setIsError(true);
			oResponse.setErrDsc("Errore tecnico di prega di riprovare piu tardi");
			oResponse.setCodiceEsito("009");
		}
		
		
		return oResponse;
	}
}
