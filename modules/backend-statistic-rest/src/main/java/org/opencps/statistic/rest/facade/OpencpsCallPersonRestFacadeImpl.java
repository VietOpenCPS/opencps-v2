package org.opencps.statistic.rest.facade;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;

import org.opencps.statistic.rest.dto.GetPersonRequest;
import org.opencps.statistic.rest.dto.GetPersonResponse;
import org.opencps.statistic.rest.util.DossierStatisticConfig;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.ServerConfigContants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;
import opencps.statistic.common.webservice.facade.OpencpsRestFacade;

public class OpencpsCallPersonRestFacadeImpl extends OpencpsRestFacade<GetPersonRequest, GetPersonResponse>
		implements OpencpsCallRestFacade<GetPersonRequest, GetPersonResponse> {

	//private final static Log _log = LogFactoryUtil.getLog(OpencpsCallDossierRestFacadeImpl.class);

	@Override
	public GetPersonResponse callRestService(GetPersonRequest payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		return makeServiceCall(payload);
	}

	protected GetPersonResponse makeServiceCall(GetPersonRequest payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		
		MultiValueMap<String, String> urlQueryParams = new LinkedMultiValueMap<>();
		//System.out.println("payload.isCalculate(): "+payload.isCalculate());
		if (payload.isCalculate()) {
			if (payload.getMonth() != null) {
				urlQueryParams.add("month", payload.getMonth());
			}
			else {
				urlQueryParams.add("month", Integer.toString(LocalDate.now().getMonthValue()));
			}
			if (payload.getYear() != null) {
				urlQueryParams.add("year", payload.getYear());
			}
			else {
				urlQueryParams.add("year", Integer.toString(LocalDate.now().getYear()));
			}
		} else {
			if (Validator.isNotNull(payload.getFromStatisticDate())) {
				urlQueryParams.add("fromVotingDate", payload.getFromStatisticDate());
			}
			if (Validator.isNotNull(payload.getToStatisticDate())) {
				urlQueryParams.add("toVotingDate", payload.getToStatisticDate());
			}
		}

		if (Validator.isNotNull(payload.getGovAgencyCode())) {
			urlQueryParams.add("agency", payload.getGovAgencyCode());
		}

		urlQueryParams.add("className", "employee");

		String endPoint = Validator.isNotNull(payload.getEndpoint()) ? payload.getEndpoint() : DossierStatisticConfig.get(DossierStatisticConstants.VOTING_ENDPOINT);
		//System.out.println("endPoint: "+endPoint);
		HashMap<String, String> urlPathSegments = new HashMap<>();
		String url = buildUrl(endPoint, urlPathSegments, urlQueryParams);
		//System.out.println("url: "+url);
		// LOG.info(url);
		HttpHeaders httpHeaders = new HttpHeaders();
		
		//DossierStatisticUtils.logAsFormattedJson(LOG, httpHeaders);
		
		httpHeaders.add("groupId", Long.toString(payload.getGroupId()));
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
		
		return (GetPersonResponse) this
				.executeGenericRestCall(url, HttpMethod.GET, httpHeaders, payload, GetPersonResponse.class)
				.getBody();
	}

}
