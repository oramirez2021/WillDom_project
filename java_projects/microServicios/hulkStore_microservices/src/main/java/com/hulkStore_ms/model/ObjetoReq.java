package com.hulkStore_ms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ObjetoReq {
	String valor;
	public ObjetoReq(String valor) {
		this.valor=valor;
		
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	/*CommonHeaderRequest_c CommonHeaderRequest;
	QueryPackageInfo_c QueryPackageInfo;

	public CommonHeaderRequest_c getCommonHeaderRequest() {
		return CommonHeaderRequest;
	}

	public void setCommonHeaderRequest(CommonHeaderRequest_c commonHeaderRequest) {
		CommonHeaderRequest = commonHeaderRequest;
	}

	public QueryPackageInfo_c getQueryPackageInfo() {
		return QueryPackageInfo;
	}

	public void setQueryPackageInfo(QueryPackageInfo_c queryPackageInfo) {
		QueryPackageInfo = queryPackageInfo;
	}*/
	
}
