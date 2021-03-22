package co.com.mutantes.lamda.stadistics.util;

import co.com.mutantes.lamda.stadistics.constant.ResponseCodesEnum;

import co.com.mutantes.lamda.stadistics.dto.ResponseBase;

public class ResponseBuilder<T extends ResponseBase>{
	
	
	
	
	
	public void build(ResponseCodesEnum code, T response) {
		
		response.getResult().setStatus(code.getCode());
		response.getResult().setMessage(code.getMessage());
	}
}
