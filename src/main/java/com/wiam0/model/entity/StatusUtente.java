package com.wiam0.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(schema="wiam0", name = "status")
public class StatusUtente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Boolean primoAccesso;
	private Boolean utenteRegistrato;
	private String tipoUtente;
	private boolean richiestaDataNascita;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_username", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Utente utente;
	
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
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public boolean isRichiestaDataNascita() {
		return richiestaDataNascita;
	}
	public void setRichiestaDataNascita(boolean richiestaDataNascita) {
		this.richiestaDataNascita = richiestaDataNascita;
	}
	
	
	
	
}
