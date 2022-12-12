package com.wiam0.service.request;

import lombok.Data;

@Data
public class DispoRequest {
	
	private String usernameToReceive;
	private String usernameToPay;
	private String btToPay;
	private Double importo;
	private String btToReceiv;
	private Double debito;
	private String tipoConto;
	
	public String getBtToPay() {
		return btToPay;
	}
	public void setBtToPay(String btToPay) {
		this.btToPay = btToPay;
	}
	public Double getImporto() {
		return importo;
	}
	public void setImporto(Double importo) {
		this.importo = importo;
	}
	public String getBtToReceiv() {
		return btToReceiv;
	}
	public void setBtToReceiv(String btToReceiv) {
		this.btToReceiv = btToReceiv;
	}
	public String getUsernameToReceive() {
		return usernameToReceive;
	}
	public void setUsernameToReceive(String usernameToReceive) {
		this.usernameToReceive = usernameToReceive;
	}
	public String getUsernameToPay() {
		return usernameToPay;
	}
	public void setUsernameToPay(String usernameToPay) {
		this.usernameToPay = usernameToPay;
	}
	
	
}
