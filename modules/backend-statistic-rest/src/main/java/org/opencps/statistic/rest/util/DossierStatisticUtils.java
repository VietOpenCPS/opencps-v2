package org.opencps.statistic.rest.util;

import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DossierStatisticUtils {
	public static void logAsFormattedJson(Logger logger, Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String contentFormatted = mapper.writeValueAsString(obj);
			logger.info("Content: \n {}", contentFormatted);
		} catch (JsonProcessingException e) {
			logger.info("Error printing REST request! {}", e);
		}
	}
}
