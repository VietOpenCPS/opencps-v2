package org.opencps.statistic.rest.facade;

import java.util.Base64;
import java.util.HashMap;

import org.opencps.statistic.rest.dto.CommonRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.dto.ServiceInfoResponse;
import org.opencps.statistic.rest.util.DossierStatisticConfig;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.ServerConfigContants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

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

		String endPoint = Validator.isNotNull(payload.getEndpoint()) ? payload.getEndpoint() : DossierStatisticConfig.get(DossierStatisticConstants.SERVICE_INFO_ENDPOINT);

		// get the params for EE
		HashMap<String, String> urlPathSegments = new HashMap<>();

		//urlPathSegments.put("s", payload.getKeyword());

		// build the url
		String url = buildUrl(endPoint, urlPathSegments, urlQueryParams) + StringPool.FORWARD_SLASH + payload.getKeyword();

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add(DossierStatisticConstants.GROUP_ID, Long.toString(payload.getGroupId()));
//		if (Validator.isNotNull(PropsUtil.get(ServerConfigContants.SERVER_SYNC_KEY))
//				&& Validator.isNotNull(PropsUtil.get(ServerConfigContants.SERVER_SYNC_SECRET))) {
//			setHttpHeadersAuthorization(httpHeaders, PropsUtil.get(ServerConfigContants.SERVER_SYNC_KEY), PropsUtil.get(ServerConfigContants.SERVER_SYNC_SECRET));
//		}
//		else {
//			httpHeaders.add("Authorization", "Basic " + DossierStatisticConfig.get(DossierStatisticConstants.OPENCPS_AUTHENCATION));
//		}
		if (Validator.isNotNull(payload.getUsername()) && Validator.isNotNull(payload.getPassword())) {
			httpHeaders.add("Authorization", "Basic " + Base64.getEncoder().encodeToString((payload.getUsername() + ":" + payload.getPassword()).getBytes()));			
		}
		
		return executeGenericRestCall(url, HttpMethod.GET, httpHeaders, payload, ServiceInfoResponse.class).getBody();
	}

}
