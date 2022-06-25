package com.wiam0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiam0.model.entity.Utente;
import com.wiam0.model.response.BaseWiamResponse;
import com.wiam0.service.request.UtenteRequest;
import com.wiam0.service.response.CheckUtenteResponse;
import com.wiam0.service.utente.UtenteService;

@RestController
@RequestMapping("ut")
public class UtenteController {

	@Autowired private UtenteService utService;
	
	@PostMapping("/save/ut")
	public BaseWiamResponse salvaUtente(@RequestBody UtenteRequest utente) {
		
		BaseWiamResponse oResponse = new BaseWiamResponse();
		oResponse = utService.salvaUtente(utente);
		return oResponse;
	}
	
	@PostMapping("/modifica/ut")
	public BaseWiamResponse modificaUtente(@RequestBody Utente utente) {
		
		BaseWiamResponse oResponse = new BaseWiamResponse();
		oResponse = utService.modificaInfoUtente(utente);
		return oResponse;
	}
	
	@PostMapping("/cancella/ut")
	public BaseWiamResponse cancellaUtente(@RequestBody Utente utente) {
		BaseWiamResponse oResponse = new BaseWiamResponse();
		oResponse = utService.cancellaUtente(utente);
		return oResponse;
	}
	// controlla che utente esista
	@PostMapping("/trova/ut")
	public CheckUtenteResponse checkUtente(@RequestBody UtenteRequest request) {
		
		CheckUtenteResponse oResponse = new CheckUtenteResponse();
		oResponse = utService.utenteExist(request.getUsername(), request.getBt());
		return oResponse;
	}
	
}
