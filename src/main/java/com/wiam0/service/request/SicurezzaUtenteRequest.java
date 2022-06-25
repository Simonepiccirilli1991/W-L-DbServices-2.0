package com.wiam0.service.request;

public class SicurezzaUtenteRequest {

	private String username;
	private boolean certificaMail;
	private boolean certificaCell;
	
	private String domandaSicurezza1;
	private String domandaSicurezza2;
	private String domandaSicurezza3;
	
	private String rispostaSicurezza1;
	private String rispostaSicurezza2;
	private String rispostaSicurezza3;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isCertificaMail() {
		return certificaMail;
	}
	public void setCertificaMail(boolean certificaMail) {
		this.certificaMail = certificaMail;
	}
	public boolean getCertificaCell() {
		return certificaCell;
	}
	public void setCertificaCell(boolean certificaCell) {
		this.certificaCell = certificaCell;
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
	
	
}
