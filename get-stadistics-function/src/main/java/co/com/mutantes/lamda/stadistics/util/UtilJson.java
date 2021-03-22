package co.com.mutantes.lamda.stadistics.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilJson {

	private UtilJson() {
	}

	public static String object2Json(Object object) {
		String message = null;
		try {
			message = new ObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			message = null;
		}

		return message;
	}
}
