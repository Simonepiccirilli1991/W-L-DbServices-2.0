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
import com.wiam0.service.response.AnagraficaResponse;
import com.wiam0.util.Constants;


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

	//cancella anagrafica
	@PostMapping("delete/sc/ag")
	public ResponseEntity<BaseWiamResponse> cancellaAnagrafica(@RequestBody AnagraficaRequest request){

		return new ResponseEntity<>(anagService.cancellaAnagrafica(request.getUsername(), request.getBt(), request.getAnagrafica()), HttpStatus.OK);

	}
	// get all anagrafica by cf o nome 
	@RequestMapping("get/{tiporicerca}/info/{valore}")
	public ResponseEntity<AnagraficaResponse> getAnagrafica(@PathVariable (value = "tiporicerca") String tiporiceca,
			@PathVariable (value = "valore") String valore){
		
		AnagraficaResponse response = new AnagraficaResponse();
		
		if(tiporiceca.equals(Constants.CommonUtil.CF)) {
			
			return new ResponseEntity<>(anagService.getAnagraficaByCF(valore), HttpStatus.OK);
		}
		else if(tiporiceca.equals(Constants.CommonUtil.NAME)) {
			
			return new ResponseEntity<>(anagService.getAnagraficaByName(valore), HttpStatus.OK);
		}
		else {
			response.setErrDsc("invalid request");
			response.setCodiceEsito("ERKO-02");
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
