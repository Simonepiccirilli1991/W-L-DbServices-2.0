package com.wiam0.service.response;

import com.wiam0.model.response.BaseWiamResponse;

public class CheckUtenteResponse extends BaseWiamResponse{

	
	private boolean utenteEsiste;

	public boolean isUtenteEsiste() {
		return utenteEsiste;
	}

	public void setUtenteEsiste(boolean utenteEsiste) {
		this.utenteEsiste = utenteEsiste;
	}
	
	
}
