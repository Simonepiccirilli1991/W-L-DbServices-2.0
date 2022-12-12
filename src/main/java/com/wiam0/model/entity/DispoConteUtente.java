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
@Table(schema="wiam0", name = "account")
public class DispoConteUtente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Double saldoAttuale;
	private String numeroConto;
	private String tipoConto;
	private Double debito;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_username", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Utente utente;
	
	public Double getSaldoAttuale() {
		return saldoAttuale;
	}
	public void setSaldoAttuale(Double saldoAttuale) {
		this.saldoAttuale = saldoAttuale;
	}
	public String getNumeroConto() {
		return numeroConto;
	}
	public void setNumeroConto(String numeroConto) {
		this.numeroConto = numeroConto;
	}
	public String getTipoConto() {
		return tipoConto;
	}
	public void setTipoConto(String tipoConto) {
		this.tipoConto = tipoConto;
	}
	public Double getDebito() {
		return debito;
	}
	public void setDebito(Double debito) {
		this.debito = debito;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	
	
	
}
