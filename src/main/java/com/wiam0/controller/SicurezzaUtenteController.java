package com.wiam0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiam0.model.response.BaseWiamResponse;
import com.wiam0.service.request.SicurezzaUtenteRequest;
import com.wiam0.service.response.GetDomandeSicurezzaResponse;
import com.wiam0.service.sicurezzaUtente.SicurezzaUtenteService;

@RestController
@RequestMapping("sic")
public class SicurezzaUtenteController {

	//TODO wiam completato (manca generazione e check ots) farla generalizzata e con parametro
	//specifico per mail o cell
	//, iniziare seor con action e poi app angular 
	@Autowired private SicurezzaUtenteService sicService;
	
	@PostMapping("cert/mail")
	private ResponseEntity<BaseWiamResponse> certificaMail(@RequestBody SicurezzaUtenteRequest request) {
		
		return new ResponseEntity<>(sicService.confermaMailCertificata(request), HttpStatus.OK);
		
	}
	
	@PostMapping("cert/cell")
	private ResponseEntity<BaseWiamResponse> certificacell(@RequestBody SicurezzaUtenteRequest request) {
		
		return new ResponseEntity<>(sicService.confermaCellCertificato(request), HttpStatus.OK);
		
	}
	
	@PostMapping("cens/ds")
	private ResponseEntity<BaseWiamResponse> censisciDomandeSegrete(@RequestBody SicurezzaUtenteRequest request) {
		
		return new ResponseEntity<>(sicService.censisciDomandeErispSic(request), HttpStatus.OK);
		
	}
	
	@PostMapping("get/ds")
	private ResponseEntity<GetDomandeSicurezzaResponse> getDomandeSegrete(@RequestBody SicurezzaUtenteRequest request){
		
		return new ResponseEntity<>(sicService.getDomandeSicurezza(request), HttpStatus.OK);
	}
	
	@PostMapping("check/ds")
	private ResponseEntity<BaseWiamResponse> checkDomandeSegrete(@RequestBody SicurezzaUtenteRequest request) {
		
		return new ResponseEntity<>(sicService.checkDomandeSicurezza(request), HttpStatus.OK);
		
	}
}
