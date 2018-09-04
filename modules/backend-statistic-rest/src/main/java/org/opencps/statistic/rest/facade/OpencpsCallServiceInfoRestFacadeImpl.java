package org.opencps.statistic.rest.facade;

import java.util.HashMap;

import org.opencps.statistic.rest.dto.CommonRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.dto.ServiceInfoResponse;
import org.opencps.statistic.rest.util.DossierStatisticConfig;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.liferay.portal.kernel.util.StringPool;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;
import opencps.statistic.common.webservice.facade.OpencpsRestFacade;

public class OpencpsCallServiceInfoRestFacadeImpl extends OpencpsRestFacade<CommonRequest, ServiceInfoResponse>
		implements OpencpsCallRestFacade<CommonRequest, ServiceInfoResponse> {

	@Override
	public ServiceInfoResponse callRestService(CommonRequest payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {

		return makeServiceCall(payload);
	}

	@Override
	protected ServiceInfoResponse makeServiceCall(CommonRequest payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		MultiValueMap<String, String> urlQueryParams = new LinkedMultiValueMap<>();

		String endPoint = DossierStatisticConfig.get(DossierStatisticConstants.SERVICE_INFO_ENDPOINT);

		// get the params for EE
		HashMap<String, String> urlPathSegments = new HashMap<>();

		//urlPathSegments.put("s", payload.getKeyword());

		// build the url
		String url = buildUrl(endPoint, urlPathSegments, urlQueryParams) + StringPool.FORWARD_SLASH + payload.getKeyword();

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add(DossierStatisticConstants.GROUP_ID, Long.toString(payload.getGroupId()));

		return executeGenericRestCall(url, HttpMethod.GET, httpHeaders, payload, ServiceInfoResponse.class).getBody();
	}

}
