package phoenix.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * Created on ২১/১/২৩
 * User Khan, C M Abdullah
 */
@Slf4j
public class JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	public static String getJsonString(Object o, Class clzz) {

		try {
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {

			log.error("unable to parse");

		}

		return "{}";
	}

	public static Map<String, Object> getJsonToObject(String jsonInString) {

		try {
			return mapper.readValue(jsonInString, Map.class);
		} catch (JsonProcessingException e) {
			log.error("unable to parse");

		}

		return Collections.emptyMap();
	}
}
