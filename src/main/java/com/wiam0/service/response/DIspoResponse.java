package com.wiam0.service.response;

import com.wiam0.model.entity.Account;
import com.wiam0.model.response.BaseWiamResponse;

import lombok.Data;

@Data
public class DIspoResponse extends BaseWiamResponse{

	private Boolean transactionOk;
	private Account dispoInfo;
	private String numeroConto;
	
	public Boolean getTransactionOk() {
		return transactionOk;
	}

	public void setTransactionOk(Boolean transactionOk) {
		this.transactionOk = transactionOk;
	}
	
	
}
