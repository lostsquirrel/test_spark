package test.spark.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static ObjectMapper mapper = new ObjectMapper(); // create once, reuse
	
	public static String toJsonString(Object obj) {
		String str = "";
		try {
			str = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {}
		
		return str;
	}
}
