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
@Table(name = "account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Double saldoattuale;
	private String numeroconto;
	private String tipoConto;
	private Double debito;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_username", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Utente utente;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Double getSaldoattuale() {
		return saldoattuale;
	}
	public void setSaldoattuale(Double saldoattuale) {
		this.saldoattuale = saldoattuale;
	}
	public String getNumeroconto() {
		return numeroconto;
	}
	public void setNumeroconto(String numeroconto) {
		this.numeroconto = numeroconto;
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
