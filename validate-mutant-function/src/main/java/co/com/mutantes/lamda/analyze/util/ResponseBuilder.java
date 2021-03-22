package co.com.mutantes.lamda.analyze.util;

import co.com.mutantes.lamda.analyze.dto.ResponseBase;
import co.com.mutantes.lamda.stadistics.constant.ResponseCodesEnum;



public class ResponseBuilder<T extends ResponseBase>{
	
	
	
	public void build(ResponseCodesEnum code, T response) {
		
		response.getResult().setStatus(code.getCode());
		response.getResult().setMessage(code.getMessage());
	}
}
