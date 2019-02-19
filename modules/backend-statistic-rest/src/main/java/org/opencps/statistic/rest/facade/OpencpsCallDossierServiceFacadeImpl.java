package org.opencps.statistic.rest.facade;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.HashMap;

import org.opencps.statistic.rest.dto.GetDossierRequest;
import org.opencps.statistic.rest.dto.GetDossierResponse;
import org.opencps.statistic.rest.util.DossierConstants;
import org.opencps.statistic.rest.util.DossierStatisticConfig;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.ServerConfigContants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;
import opencps.statistic.common.webservice.facade.OpencpsRestFacade;

public class OpencpsCallDossierServiceFacadeImpl extends OpencpsRestFacade<GetDossierRequest, GetDossierResponse>
		implements OpencpsCallRestFacade<GetDossierRequest, GetDossierResponse> {

	private final static Logger LOG = LoggerFactory.getLogger(OpencpsCallDossierRestFacadeImpl.class);

	@Override
	public GetDossierResponse callRestService(GetDossierRequest payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		return makeServiceCall(payload);
	}

	protected GetDossierResponse makeServiceCall(GetDossierRequest payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		
		MultiValueMap<String, String> urlQueryParams = new LinkedMultiValueMap<>();
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
			if (Validator.isNotNull(payload.getGovAgencyCode())) {
				urlQueryParams.add("agency", payload.getGovAgencyCode());
			}
			if (Validator.isNotNull(payload.getDomain())) {
				urlQueryParams.add("domain", payload.getDomain());
			}
			if (Validator.isNotNull(payload.getFromStatisticDate())) {
				urlQueryParams.add("fromStatisticDate", payload.getFromStatisticDate());
			}
			if (Validator.isNotNull(payload.getToStatisticDate())) {
				urlQueryParams.add("toStatisticDate", payload.getToStatisticDate());
			}
			//System.out.println("fromStatisticDate: "+urlQueryParams.get("fromStatisticDate"));
			//System.out.println("toStatisticDate: "+urlQueryParams.get("toStatisticDate"));
			//Process tranfer params using search dossier
			//buildUrlQueryParams(urlQueryParams, payload);
		}
		urlQueryParams.add("top", "statistic");
		if (payload.getStart() != 0) {
			urlQueryParams.add("start", String.valueOf(payload.getStart()));			
		}
		else {
			urlQueryParams.add("start", "-1");
		}
		if (payload.getEnd() != 0) {
			urlQueryParams.add("end", String.valueOf(payload.getEnd()));
		}
		else {
			urlQueryParams.add("end", "-1");
		}
		//System.out.println("fromStatisticDate: "+urlQueryParams.get("fromStatisticDate"));
		//System.out.println("toStatisticDate: "+urlQueryParams.get("toStatisticDate"));
		//System.out.println("month: "+urlQueryParams.get("month"));
		//System.out.println("year: "+urlQueryParams.get("year"));
		//System.out.println("top: "+urlQueryParams.get("top"));
		
		String endPoint = DossierStatisticConfig.get(DossierStatisticConstants.DOSSIER_ENDPOINT);
		HashMap<String, String> urlPathSegments = new HashMap<>();
		String url = buildUrl(endPoint, urlPathSegments, urlQueryParams);
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
		return (GetDossierResponse) this
				.executeGenericRestCall(url, HttpMethod.GET, httpHeaders, payload, GetDossierResponse.class).getBody();
	}

	private void buildUrlQueryParams(MultiValueMap<String, String> urlQueryParams, GetDossierRequest dossierRequest) {
		urlQueryParams.add(DossierConstants.DOSSIER_STATUS, dossierRequest.getStatus());
		urlQueryParams.add(DossierConstants.DOSSIER_SUB_STATUS, dossierRequest.getSubstatus());
		urlQueryParams.add(DossierConstants.ONLINE, dossierRequest.getOnlineStatistic());
		urlQueryParams.add(DossierConstants.ORIGINALITY, dossierRequest.getOriginality());
		urlQueryParams.add(DossierConstants.TEMPLATE, dossierRequest.getTemplate());
		urlQueryParams.add(DossierConstants.STEP, dossierRequest.getStep());
		urlQueryParams.add(DossierConstants.DOSSIER_NO, dossierRequest.getDossierNo());
		urlQueryParams.add("domain", dossierRequest.getDomain());
	}

}
