package com.wiam0.service.response;

import java.util.List;

import com.wiam0.model.entity.Anagrafica;
import com.wiam0.model.response.BaseWiamResponse;

import lombok.Data;

@Data
public class AnagraficaResponse extends BaseWiamResponse{

	private List<Anagrafica> listaAnagrafica;
	private Anagrafica anagrafica;
	
	public List<Anagrafica> getListaAnagrafica() {
		return listaAnagrafica;
	}
	public void setListaAnagrafica(List<Anagrafica> listaAnagrafica) {
		this.listaAnagrafica = listaAnagrafica;
	}
	public Anagrafica getAnagrafica() {
		return anagrafica;
	}
	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}
	
	
	
}
