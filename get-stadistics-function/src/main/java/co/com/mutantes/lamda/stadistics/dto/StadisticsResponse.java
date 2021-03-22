package co.com.mutantes.lamda.stadistics.dto;

/**
 * Clase que representa la respuesta a la consulta de solicitudes
 * @author oskar
 *
 */
public class StadisticsResponse extends ResponseBase{
	
	/**
	 * Total de muntantes
	 */
	
	private long count_mutant_dna;
	/**
	 * Tototal de mutantes
	 */
	private long count_human_dna;
	/**
	 * Porcentaje de mutantes
	 */
	private double ratio;
	
	
	public long getCount_mutant_dna() {
		return count_mutant_dna;
	}
	public void setCount_mutant_dna(long count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}
	public long getCount_human_dna() {
		return count_human_dna;
	}
	public void setCount_human_dna(long count_human_dna) {
		this.count_human_dna = count_human_dna;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
}
