package com.wiam0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiam0.model.response.BaseWiamResponse;
import com.wiam0.service.pinutente.PinUtenteService;
import com.wiam0.service.request.PinUtenteRequest;

@RestController
@RequestMapping("pin")
public class UtentePinController {

	@Autowired private PinUtenteService pinService;

	@PostMapping("/check")
	private ResponseEntity<BaseWiamResponse> checkPin(@RequestBody PinUtenteRequest request){

		return new ResponseEntity<>(pinService.checkPin(request), HttpStatus.OK);

	}

	@PostMapping("/checkconformita")
	private ResponseEntity<BaseWiamResponse> checkConformitaPin(@RequestBody PinUtenteRequest request){

		return new ResponseEntity<>(pinService.checkConformitàPin(request), HttpStatus.OK);

	}

	@PostMapping("/change/ut/")
	private ResponseEntity<BaseWiamResponse> cambioPin(@RequestBody PinUtenteRequest request){

		return new ResponseEntity<>(pinService.cambioPin(request), HttpStatus.OK);

	}
	// sviluppare use-case per enforcement e vedere come gestire status
}
