package co.com.mutantes.lamda.adn.dto;

public class MessageDNADto {
	
	private boolean mutant;
	private String [] adn;

	public boolean isMutant() {
		return mutant;
	}
	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}
	public String[] getAdn() {
		return adn;
	}
	public void setAdn(String[] adn) {
		this.adn = adn;
	}
	
}
