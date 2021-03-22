package co.com.mutantes.lamda.stadistics.constant;

public enum ResponseCodesEnum {
	SUCESSFULL(200,"OK"),INTERNAL_SERVER_ERROR(500,"Error inesperado"), NO_MUTANT(403,"No mutante"), INVALID_REQUEST(400,"Solicitud no valida" );
	
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	private int code;
	private String message;
	
	private ResponseCodesEnum(	 int code,String message){
		
		this.code = code;
		this.message = message;
	}
}
