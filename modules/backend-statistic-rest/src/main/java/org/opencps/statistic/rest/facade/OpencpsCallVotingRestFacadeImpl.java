package org.opencps.statistic.rest.facade;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.HashMap;

import org.opencps.statistic.rest.dto.GetVotingResultRequest;
import org.opencps.statistic.rest.dto.GetVotingResultResponse;
import org.opencps.statistic.rest.util.DossierStatisticConfig;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import backend.feedback.constants.VotingTerm;
import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;
import opencps.statistic.common.webservice.facade.OpencpsRestFacade;

public class OpencpsCallVotingRestFacadeImpl extends OpencpsRestFacade<GetVotingResultRequest, GetVotingResultResponse>
		implements OpencpsCallRestFacade<GetVotingResultRequest, GetVotingResultResponse> {

	//private final static Log _log = LogFactoryUtil.getLog(OpencpsCallDossierRestFacadeImpl.class);

	private static final String QUERY_PARAM_MONTH = "month";
	private static final String QUERY_PARAM_YEAR = "year";
	private static final String QUERY_PARAM_FROM_VOTING_DATE = "fromVotingDate";
	private static final String QUERY_PARAM_TO_VOTING_DATE = "toVotingDate";

	@Override
	public GetVotingResultResponse callRestService(GetVotingResultRequest payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		return makeServiceCall(payload);
	}

	protected GetVotingResultResponse makeServiceCall(GetVotingResultRequest payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		
		MultiValueMap<String, String> urlQueryParams = new LinkedMultiValueMap<>();
		//System.out.println("payload.isCalculate(): "+payload.isCalculate());
		if (payload.isCalculate()) {
			if (payload.getMonth() != null) {
				urlQueryParams.add(QUERY_PARAM_MONTH, payload.getMonth());
				//urlQueryParams.add("month", "10");
			}
			else {
				urlQueryParams.add(QUERY_PARAM_MONTH, Integer.toString(LocalDate.now().getMonthValue()));
			}
			if (payload.getYear() != null) {
				urlQueryParams.add(QUERY_PARAM_YEAR, payload.getYear());
			}
			else {
				urlQueryParams.add(QUERY_PARAM_YEAR, Integer.toString(LocalDate.now().getYear()));
			}
		} else {
//			if (Validator.isNotNull(payload.getGovAgencyCode())) {
//				urlQueryParams.add("agency", payload.getGovAgencyCode());
//			}
			if (Validator.isNotNull(payload.getFromVotingDate())) {
				urlQueryParams.add(QUERY_PARAM_FROM_VOTING_DATE, payload.getFromVotingDate());
			}
			if (Validator.isNotNull(payload.getToVotingDate())) {
				urlQueryParams.add(QUERY_PARAM_TO_VOTING_DATE, payload.getToVotingDate());
			}
		}

		urlQueryParams.add(VotingTerm.CLASS_NAME, DossierStatisticConfig.get(DossierStatisticConstants.VOTING_CLASSNAME_DOSSIER));

		String endPoint = Validator.isNotNull(payload.getEndpoint()) ? payload.getEndpoint() : DossierStatisticConfig.get(DossierStatisticConstants.VOTING_ENDPOINT);
		//System.out.println("endPoint: "+endPoint);
		HashMap<String, String> urlPathSegments = new HashMap<>();
		String url = buildUrl(endPoint, urlPathSegments, urlQueryParams);
		//System.out.println("url: "+url);
		// LOG.info(url);
		HttpHeaders httpHeaders = new HttpHeaders();
		
		//DossierStatisticUtils.logAsFormattedJson(LOG, httpHeaders);
		
		httpHeaders.add(Field.GROUP_ID, Long.toString(payload.getGroupId()));
//		if (Validator.isNotNull(PropsUtil.get(ServerConfigContants.SERVER_SYNC_KEY))
//				&& Validator.isNotNull(PropsUtil.get(ServerConfigContants.SERVER_SYNC_SECRET))) {
//			setHttpHeadersAuthorization(httpHeaders, PropsUtil.get(ServerConfigContants.SERVER_SYNC_KEY), PropsUtil.get(ServerConfigContants.SERVER_SYNC_SECRET));
//		}
//		else {
//			httpHeaders.add("Authorization", "Basic " + DossierStatisticConfig.get(DossierStatisticConstants.OPENCPS_AUTHENCATION));
//		}
		return (GetVotingResultResponse) this
				.executeGenericRestCall(url, HttpMethod.GET, httpHeaders, payload, GetVotingResultResponse.class).getBody();
	}

}
