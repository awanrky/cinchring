package info.markott.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * Created by mark on 9/26/16.
 */
public class Views {
	public static class Full extends Compact {}
	public static class Compact {}

	public static ResponseEntity getJsonResponseWithView(Class<?> viewClass, Object value) {
		String json;
		try {
			json = (new ObjectMapper()).writerWithView(viewClass).writeValueAsString(value);
		} catch (JsonProcessingException e) {
			return getJsonResponseFromString("{\"error\": \"unable to generate json for response\"", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return getJsonResponseFromString(json, HttpStatus.OK);
	}

	public static ResponseEntity getJsonResponseFromString(String value, HttpStatus httpStatus) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(value, responseHeaders, httpStatus);
	}
}
