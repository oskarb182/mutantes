package co.com.mutantes.lamda.adn.constant;

public enum ResponseCodesEnum {
	SUCESSFULL(200,"OK"),INTERNAL_SERVER_ERROR(500,"Error inesperado");
	
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
