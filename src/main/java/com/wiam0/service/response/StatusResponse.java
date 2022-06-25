package com.wiam0.service.response;

import com.wiam0.model.response.BaseWiamResponse;

public class StatusResponse extends BaseWiamResponse{

	
	private Boolean primoAccesso;
	private Boolean utenteRegistrato;
	private String tipoUtente;
	private boolean richiestaDataNascita;
	
	public Boolean getPrimoAccesso() {
		return primoAccesso;
	}
	public void setPrimoAccesso(Boolean primoAccesso) {
		this.primoAccesso = primoAccesso;
	}
	public Boolean getUtenteRegistrato() {
		return utenteRegistrato;
	}
	public void setUtenteRegistrato(Boolean utenteRegistrato) {
		this.utenteRegistrato = utenteRegistrato;
	}
	public String getTipoUtente() {
		return tipoUtente;
	}
	public void setTipoUtente(String tipoUtente) {
		this.tipoUtente = tipoUtente;
	}
	public boolean isRichiestaDataNascita() {
		return richiestaDataNascita;
	}
	public void setRichiestaDataNascita(boolean richiestaDataNascita) {
		this.richiestaDataNascita = richiestaDataNascita;
	}
	
	
}
