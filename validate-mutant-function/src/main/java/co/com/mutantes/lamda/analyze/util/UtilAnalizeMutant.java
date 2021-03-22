package co.com.mutantes.lamda.analyze.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class UtilAnalizeMutant {
	
	private LambdaLogger logger;
	
public UtilAnalizeMutant(Context context) {
	
	logger = context.getLogger();
}
	
	private static final String[] ADN = { "A", "T", "C", "G" };
	private static final String REGEX_SEQUENCE = ".*({CHARACTER}{4,}).*";
	private static final String REPLACE_PATTERN = "{CHARACTER}";
	
	
	public boolean analizeADN(String[] dna) {
		
	

		String[][] matrix = array2Matrix(dna);

		boolean resultHoriz = findHorizontal(matrix);
		logger.log("Analisis Horizontal: "+ resultHoriz);
		
		boolean resultVert = findVertical(matrix);
		logger.log("Analisis Vertical: "+ resultVert);
		
		boolean resultDiag = findDiag(matrix);
		logger.log("Analisis Diagonal: "+ resultDiag);
		
		boolean result = resultHoriz || resultVert || resultDiag;
		logger.log("Analisis resultado: "+ result);
		return result;
	}
	
	
	private static boolean matchRegex(String character, String cadena) {

		String regex = REGEX_SEQUENCE.replace(REPLACE_PATTERN, character);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cadena);
		return matcher.matches();

	}
	
	public  boolean validateInput(String[] dna) {

		int sizeRows = dna.length;

		for (int i = 0; i < dna.length; i++) {
			if (dna[i].split("").length != sizeRows) {
				return false;
			}
		}
		
		return true;
	}
	
	private  String[][] array2Matrix(String[] dna) {

		


		int rowsSize = dna.length;
		int columnsSize = dna[0].length();
		String[][] matrix = new String[rowsSize][columnsSize];

		for (int i = 0; i < dna.length; i++) {
			String[] rowss = dna[i].split("");
			for (int j = 0; j < rowss.length; j++) {
				matrix[i][j] = rowss[j].toUpperCase();
			}
		}

		return matrix;
	}

	/***
	 * Metodo que determina si existe coincidencia en Horizontal
	 * 
	 * @param matrix
	 * @return
	 */
	private  boolean findHorizontal(String[][] matrix) {

		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < ADN.length; index++) {

			for (int i = 0; i < matrix.length; ++i) {
				sb.setLength(0);

				for (int j = 0; j < matrix[i].length; ++j) {

					sb.append(matrix[i][j]);

				}

				if (matchRegex(ADN[index], sb.toString())) {
					logger.log("Mutante Horizontal: " + sb);
					return true;
				}

			}

		}

		return false;
	}

	private  boolean findVertical(String[][] matrix) {

		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < ADN.length; index++) {

			for (int i = 0; i < matrix.length; ++i) {

				for (int j = 0; j < matrix[i].length; ++j) {

					sb.append((matrix[j][i]));

				}

				if (matchRegex(ADN[index], sb.toString())) {

					logger.log("Mutante Vertical: " + sb);
					return true;
				}
				sb.setLength(0);
			}

		}

		return false;
	}

	private  boolean findDiag(String[][] matrix) {

		for (int index = 0; index < ADN.length; index++) {

			for (int i = 0; i < matrix.length; ++i) {

				for (int j = 0; j < matrix[i].length; ++j) {

					boolean result = printDiagonal(i, j, matrix);
					if (result) {
						return true;
					}
				}

			}

		}

		return false;
	}

	private  boolean printDiagonal(int startFila, int startColum, String[][] matrix) {

		StringBuilder sb = new StringBuilder();

		for (int index = 0; index < ADN.length; index++) {

			sb.setLength(0);
			for (int i = startFila; i < matrix.length; ++i) {

				for (int j = startColum; j + i < matrix[i].length;) {

					sb.append((matrix[i][j + i]));

					break;
				}

			}
			if (matchRegex(ADN[index], sb.toString())) {
				logger.log("Mutante Diagonal: " + sb);
				return true;
			}

		}
		return false;
	}

}
