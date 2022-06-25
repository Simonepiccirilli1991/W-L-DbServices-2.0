package com.wiam0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiam0.model.entity.Anagrafica;
import com.wiam0.model.response.BaseWiamResponse;
import com.wiam0.service.anagrafica.AnagraficaService;
import com.wiam0.service.request.AnagraficaRequest;


@RestController
@RequestMapping("ag")
public class AnagraficaController {


	@Autowired private AnagraficaService anagService;

	//Functions visibile
	//TODO rivedere perchè con doppio get se bugga, vedere come fa cell0? o myKey?
	@PostMapping("/inserisci/{username}/{bt}")
	public BaseWiamResponse aggiungiAnagrafica(@PathVariable(value = "username") String username ,
			@PathVariable(value = "bt") String bt ,@RequestBody Anagrafica request) {

		BaseWiamResponse oResponse = new BaseWiamResponse();
		oResponse = anagService.salvaAnagrafica(username, username, request);
		return oResponse;
	}

	// non visibile
	@PostMapping("insert/sc/ag")
	public BaseWiamResponse aggiungiAnagraficaSc(@RequestBody AnagraficaRequest request) {

		String bt = request.getBt();
		String username = request.getUsername();
		BaseWiamResponse oResponse = null;
		oResponse = anagService.salvaAnagrafica(username, bt, request.getAnagrafica());
		return oResponse;
	}

	// modifica anagrafica utente
	@PostMapping("modd/sc/ag")
	public ResponseEntity<BaseWiamResponse>  modificaAnagraficaUt(@RequestBody AnagraficaRequest request) {

		return new ResponseEntity<>(anagService.modificaAnagrafica(request.getUsername(), request.getBt(), request.getAnagrafica()), HttpStatus.OK);
	}

	// TODO inserire delete
	@PostMapping("delete/sc/ag")
	public ResponseEntity<BaseWiamResponse> cancellaAnagrafica(@RequestBody AnagraficaRequest request){

		return new ResponseEntity<>(anagService.cancellaAnagrafica(request.getUsername(), request.getBt(), request.getAnagrafica()), HttpStatus.OK);



	}
	
}
