package com.wiam0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiam0.service.dispo.DispoService;
import com.wiam0.service.request.DispoRequest;
import com.wiam0.service.response.DIspoResponse;

@RestController
@RequestMapping("dispo")
public class DispoController {

	@Autowired
	DispoService dispoService;
	
	
	// insert
	public DIspoResponse insertDispo(@RequestBody DispoRequest request) {
		
		return dispoService.insertDispoAccount(request);
	}
	
	// get
	public DIspoResponse getDispoInfo(@RequestBody DispoRequest request) {
		
		return dispoService.getDispoInfo(request);
	}
	
	//transaction
	public DIspoResponse transactionDispo(@RequestBody DispoRequest request) {
		return dispoService.dispoPayService(request);
	}
}
