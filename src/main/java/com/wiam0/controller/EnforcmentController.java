package com.wiam0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiam0.model.response.BaseWiamResponse;
import com.wiam0.service.enforcement.EnforcementService;
import com.wiam0.service.request.EnforcmentRequest;
import com.wiam0.service.response.EnforcmentResponse;

@RestController
@RequestMapping("enf")
public class EnforcmentController {

	@Autowired private EnforcementService enfService;
	
	@PostMapping("wi/confermaenforcement")
	private ResponseEntity<BaseWiamResponse> confermaEnforc(@RequestBody EnforcmentRequest request){
		
		return new ResponseEntity<>(enfService.confermaEnf(request), HttpStatus.OK);
		
	}
	
	@PostMapping("wi/getenforcement")
	private ResponseEntity<EnforcmentResponse> getInfoEnf(@RequestBody EnforcmentRequest request){
		
		return new ResponseEntity<>(enfService.getInfoEnforced(request), HttpStatus.OK);
	}
}
