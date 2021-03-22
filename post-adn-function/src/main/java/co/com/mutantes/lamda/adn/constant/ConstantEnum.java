package co.com.mutantes.lamda.adn.constant;

/**
 * Enumeracion para el manejo de constantes de funcion
 * @author Oscar Bermudez
 *
 */
public enum ConstantEnum {
	
	HUMANO("h"),MUTANTE("m");
	
	private String codigo;
	
	public String getCodigo() {
		return codigo;
	}

	private ConstantEnum(String codigo) {
		
		this.codigo = codigo;
	}
	
}
