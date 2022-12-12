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
@Table(name = "enforcement")
public class Enforcement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String abiSottoscrizione;
	private String tipoSicurezza;
	private Boolean enforced;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_username", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Utente utente;
	
	public String getAbiSottoscrizione() {
		return abiSottoscrizione;
	}
	public void setAbiSottoscrizione(String abiSottoscrizione) {
		this.abiSottoscrizione = abiSottoscrizione;
	}
	public String getTipoSicurezza() {
		return tipoSicurezza;
	}
	public void setTipoSicurezza(String tipoSicurezza) {
		this.tipoSicurezza = tipoSicurezza;
	}
	public Boolean getEnforced() {
		return enforced;
	}
	public void setEnforced(Boolean enforced) {
		this.enforced = enforced;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	

}
