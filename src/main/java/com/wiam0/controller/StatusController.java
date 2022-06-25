package com.wiam0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiam0.service.response.StatusRequest;
import com.wiam0.service.response.StatusResponse;
import com.wiam0.service.status.StatusService;

@RestController
@RequestMapping("st")
public class StatusController {

	@Autowired private StatusService statusService;
	
	@PostMapping("check/ss")
	public ResponseEntity<StatusResponse> checkStatus(@RequestBody StatusRequest request){
		
		return new ResponseEntity<>(statusService.statusGeneric(request), HttpStatus.OK);

	}
	//TODO rivedere status al momento nn funziona benissimo 
}
