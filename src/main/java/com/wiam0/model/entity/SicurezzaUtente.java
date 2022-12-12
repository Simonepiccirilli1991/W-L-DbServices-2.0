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
@Table(name = "sicurezzaUtente")
public class SicurezzaUtente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Boolean emailVerificata;
	private Boolean cellCertificato;
	
	private String domandaSicurezza1;
	private String domandaSicurezza2;
	private String domandaSicurezza3;
	
	private String rispostaSicurezza1;
	private String rispostaSicurezza2;
	private String rispostaSicurezza3;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_username", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Utente utente;
	
	public Boolean getEmailVerificata() {
		return emailVerificata;
	}
	public void setEmailVerificata(Boolean emailVerificata) {
		this.emailVerificata = emailVerificata;
	}
	public Boolean getCellCertificato() {
		return cellCertificato;
	}
	public void setCellCertificato(Boolean cellCertificato) {
		this.cellCertificato = cellCertificato;
	}
	public String getDomandaSicurezza1() {
		return domandaSicurezza1;
	}
	public void setDomandaSicurezza1(String domandaSicurezza1) {
		this.domandaSicurezza1 = domandaSicurezza1;
	}
	public String getDomandaSicurezza2() {
		return domandaSicurezza2;
	}
	public void setDomandaSicurezza2(String domandaSicurezza2) {
		this.domandaSicurezza2 = domandaSicurezza2;
	}
	public String getDomandaSicurezza3() {
		return domandaSicurezza3;
	}
	public void setDomandaSicurezza3(String domandaSicurezza3) {
		this.domandaSicurezza3 = domandaSicurezza3;
	}
	public String getRispostaSicurezza1() {
		return rispostaSicurezza1;
	}
	public void setRispostaSicurezza1(String rispostaSicurezza1) {
		this.rispostaSicurezza1 = rispostaSicurezza1;
	}
	public String getRispostaSicurezza2() {
		return rispostaSicurezza2;
	}
	public void setRispostaSicurezza2(String rispostaSicurezza2) {
		this.rispostaSicurezza2 = rispostaSicurezza2;
	}
	public String getRispostaSicurezza3() {
		return rispostaSicurezza3;
	}
	public void setRispostaSicurezza3(String rispostaSicurezza3) {
		this.rispostaSicurezza3 = rispostaSicurezza3;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	

}
