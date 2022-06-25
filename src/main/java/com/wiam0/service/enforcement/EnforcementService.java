package com.wiam0.service.enforcement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiam0.model.entity.Enforcement;
import com.wiam0.model.entity.Utente;
import com.wiam0.model.entity.repository.EnforcementRepo;
import com.wiam0.model.entity.repository.UtenteRepo;
import com.wiam0.model.response.BaseWiamResponse;
import com.wiam0.service.request.EnforcmentRequest;
import com.wiam0.service.response.EnforcmentResponse;

@Service
public class EnforcementService {

	
	@Autowired private EnforcementRepo enfRepo;
	@Autowired private UtenteRepo utRepo;
	
	// confermo enforcment/ posso usarla anche per disenrollare
	public BaseWiamResponse confermaEnf(EnforcmentRequest request) {
		
		BaseWiamResponse oResponse = new BaseWiamResponse();
		Enforcement enforcementDTO = new Enforcement();
		try {
			Utente ut = utRepo.findByUsername(request.getUsername());
			
			enforcementDTO.setUtente(ut);
			enforcementDTO.setAbiSottoscrizione(request.getAbiSottoscrizione());
			enforcementDTO.setEnforced(request.getEnforced());
			enforcementDTO.setTipoSicurezza(request.getTipoSicurezza());
			
			enfRepo.save(enforcementDTO);
			
		}
		catch(Exception e) {
			
			oResponse.setCodiceEsito("007");
			oResponse.setIsError(true);
			oResponse.setErrDsc("errore in fase di enforcment , si prega di riprovare");
		}
		
		return oResponse;
	}
	
	// get info enforced
	public EnforcmentResponse getInfoEnforced(EnforcmentRequest request) {
		
		EnforcmentResponse oResponse = new EnforcmentResponse();
		
		try {
			Enforcement enfDTO = enfRepo.findByUtenteUsername(request.getUsername());
			
			oResponse.setEnforced(enfDTO.getEnforced());
			oResponse.setAbiSottoscrizione(enfDTO.getAbiSottoscrizione());
			oResponse.setTipoSicurezza(enfDTO.getTipoSicurezza());
		}catch(Exception e) {
			oResponse.setCodiceEsito("008");
			oResponse.setIsError(true);
			oResponse.setErrDsc("errore generico si prega di riprovare piu tardi");
		}
		
		return oResponse;
	}
	//TODO implementare cambio abi e sicurezza, specifici. non essenziale
}
