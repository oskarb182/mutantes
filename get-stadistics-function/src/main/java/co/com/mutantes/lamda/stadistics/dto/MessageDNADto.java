package co.com.mutantes.lamda.stadistics.dto;

public class MessageDNADto {
	
	private boolean isMutant;
	private String [] adn;
	public boolean isMutant() {
		return isMutant;
	}
	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}
	public String[] getAdn() {
		return adn;
	}
	public void setAdn(String[] adn) {
		this.adn = adn;
	}
	
}
