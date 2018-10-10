package org.opencps.statistic.rest.facade;

import java.util.HashMap;

import org.opencps.statistic.rest.dto.GovAgencyRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.dto.ServiceDomainRequest;
import org.opencps.statistic.rest.dto.ServiceDomainResponse;
import org.opencps.statistic.rest.util.DossierStatisticConfig;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;
import opencps.statistic.common.webservice.facade.OpencpsRestFacade;

public class OpencpsCallServiceDomainRestFacadeImpl extends OpencpsRestFacade<ServiceDomainRequest, ServiceDomainResponse>
		implements OpencpsCallRestFacade<ServiceDomainRequest, ServiceDomainResponse> {

	//private final static Logger LOG = LoggerFactory.getLogger(OpencpsCallGovAgencyRestFacadeImpl.class);
	
	@Override
	public ServiceDomainResponse callRestService(ServiceDomainRequest payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		return makeServiceCall(payload);
	}

	@Override
	protected ServiceDomainResponse makeServiceCall(ServiceDomainRequest payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		
		MultiValueMap<String, String> urlQueryParams = new LinkedMultiValueMap<>();
		
		String endPoint = DossierStatisticConfig.get(DossierStatisticConstants.SERVICE_DOMAIN_ENDPOINT);
		
		//LOG.info(endPoint);
		
		// get the params for EE
		HashMap<String, String> urlPathSegments = new HashMap<>();

		// build the url
		String url = buildUrl(endPoint, urlPathSegments, urlQueryParams);

		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.add(DossierStatisticConstants.GROUP_ID, Long.toString(payload.getGroupId()));

		return executeGenericRestCall(url, HttpMethod.GET, httpHeaders, payload, ServiceDomainResponse.class).getBody();

	}

}
