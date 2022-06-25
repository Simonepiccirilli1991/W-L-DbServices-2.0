package com.wiam0.service.request;

import com.wiam0.model.entity.Anagrafica;

public class AnagraficaRequest {

	
	private String username;
	private String bt;
	private Anagrafica anagrafica;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public Anagrafica getAnagrafica() {
		return anagrafica;
	}
	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}
	
	
}
