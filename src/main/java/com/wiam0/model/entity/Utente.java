package com.wiam0.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table( name = "utente", uniqueConstraints=
@UniqueConstraint(columnNames={"username"}))
@PrimaryKeyJoinColumn(name="utente_username")
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String bt;
	private String abi;
	@Column(unique=true)
	private String username;
	@Column(unique=true)
	private String email;
	private String cellulare;
	@OneToOne(mappedBy = "utente", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private SicurezzaUtente sicurezza;
	
	@OneToOne(mappedBy = "utente", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Anagrafica anagrafica;
	
	@OneToOne(mappedBy = "utente", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Enforcement enforment;
	
	@OneToOne(mappedBy = "utente", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private PinUtente pinUtente;
	
	@OneToOne(mappedBy = "utente", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private Account dispoUtente;
	
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getAbi() {
		return abi;
	}
	public void setAbi(String abi) {
		this.abi = abi;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public SicurezzaUtente getSicurezza() {
		return sicurezza;
	}
	public void setSicurezza(SicurezzaUtente sicurezza) {
		this.sicurezza = sicurezza;
	}
	public Anagrafica getAnagrafica() {
		return anagrafica;
	}
	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}
	public Enforcement getEnforment() {
		return enforment;
	}
	public void setEnforment(Enforcement enforment) {
		this.enforment = enforment;
	}
	public PinUtente getPinUtente() {
		return pinUtente;
	}
	public void setPinUtente(PinUtente pinUtente) {
		this.pinUtente = pinUtente;
	}
	public Account getDispoUtente() {
		return dispoUtente;
	}
	public void setDispoUtente(Account dispoUtente) {
		this.dispoUtente = dispoUtente;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
}
