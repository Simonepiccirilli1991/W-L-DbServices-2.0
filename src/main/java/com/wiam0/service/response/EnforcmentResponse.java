package com.wiam0.service.response;

import com.wiam0.model.response.BaseWiamResponse;

public class EnforcmentResponse extends BaseWiamResponse{

	private String abiSottoscrizione;
	private String tipoSicurezza;
	private Boolean enforced;
	
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
	
	
}
