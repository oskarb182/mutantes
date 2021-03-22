package co.com.mutantes.lamda.analyze.dto;

import java.util.Arrays;

public class RequestAnalyze {
	
	private String []dna;

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}

	@Override
	public String toString() {
		return "InputPost [dna=" + Arrays.toString(dna) + "]";
	}
	
	
}
