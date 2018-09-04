package org.opencps.statistic.rest.facade;


import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;
import opencps.statistic.common.webservice.facade.OpencpsRestFacade;

public class OpencpsCallServiceRestFacadeImpl extends OpencpsRestFacade<String, String>
		implements OpencpsCallRestFacade<String, String> {

	@Override
	public String callRestService(String payload) throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		return makeServiceCall(payload);
	}

	@Override
	protected String makeServiceCall(String payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		
		MultiValueMap<String, String> urlQueryParams = new LinkedMultiValueMap<>();

		// get the params for EE
		HashMap<String, String> urlPathSegments = new HashMap<>();

		// build the url
		String url = buildUrl("http://google.com", urlPathSegments, urlQueryParams);

		HttpHeaders httpHeaders = new HttpHeaders();

		return executeGenericRestCall(url, HttpMethod.POST, httpHeaders, payload, String.class).getBody();

	}

}
