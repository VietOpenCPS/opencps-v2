package org.opencps.statistic.rest.facade;

import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.HashMap;

import org.opencps.statistic.rest.dto.GetDossierRequest;
import org.opencps.statistic.rest.dto.GetDossierResponse;
import org.opencps.statistic.rest.util.DossierConstants;
import org.opencps.statistic.rest.util.DossierStatisticConfig;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.DossierStatisticUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;
import opencps.statistic.common.webservice.facade.OpencpsRestFacade;

public class OpencpsCallDossierRestFacadeImpl extends OpencpsRestFacade<GetDossierRequest, GetDossierResponse>
		implements OpencpsCallRestFacade<GetDossierRequest, GetDossierResponse> {

	private final static Logger LOG = LoggerFactory.getLogger(OpencpsCallDossierRestFacadeImpl.class);

	@Override
	public GetDossierResponse callRestService(GetDossierRequest payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		return makeServiceCall(payload);
	}

/*	@Override
	protected GetDossierResponse makeServiceCall(GetDossierRequest payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		
		MultiValueMap<String, String> urlQueryParams = new LinkedMultiValueMap<>();
		
		//buildUrlQueryParams(urlQueryParams, payload);
		
		String endPoint = DossierStatisticConfig.get(DossierStatisticConstants.DOSSIER_ENDPOINT);

		//LOG.info(endPoint);

		// get the params for EE
		HashMap<String, String> urlPathSegments = new HashMap<>();

		// build the url
		String url = buildUrl(endPoint, urlPathSegments, urlQueryParams);
		
		//LOG.info(url);

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add(DossierStatisticConstants.GROUP_ID, Long.toString(payload.getGroupId()));

		return executeGenericRestCall(url, HttpMethod.GET, httpHeaders, payload, GetDossierResponse.class).getBody();

	}*/
	
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
			if (Validator.isNotNull(payload.getFromStatisticDate())) {
				urlQueryParams.add("fromStatisticDate", payload.getFromStatisticDate());
			}
			if (Validator.isNotNull(payload.getToStatisticDate())) {
				urlQueryParams.add("toStatisticDate", payload.getToStatisticDate());
			}
		}
		
		urlQueryParams.add("top", "statistic");
		
		String endPoint = DossierStatisticConfig.get(DossierStatisticConstants.DOSSIER_ENDPOINT);
		HashMap<String, String> urlPathSegments = new HashMap<>();
		String url = buildUrl(endPoint, urlPathSegments, urlQueryParams);
		// LOG.info(url);
		HttpHeaders httpHeaders = new HttpHeaders();
		
		//DossierStatisticUtils.logAsFormattedJson(LOG, httpHeaders);
		
		httpHeaders.add("groupId", Long.toString(payload.getGroupId()));
		return (GetDossierResponse) this
				.executeGenericRestCall(url, HttpMethod.GET, httpHeaders, payload, GetDossierResponse.class).getBody();
	}

	private void buildUrlQueryParams(MultiValueMap<String, String> urlQueryParams, GetDossierRequest dossierRequest) {
		urlQueryParams.add(DossierConstants.REGISTERBOOKCODE, dossierRequest.getRegisterBookCode());
		urlQueryParams.add(DossierConstants.PROCESSNO, dossierRequest.getProcessNo());
		urlQueryParams.add(DossierConstants.SERVICECODE, dossierRequest.getServiceCode());
		urlQueryParams.add(DossierConstants.GOVAGENCYCODE, dossierRequest.getGovAgencyCode());
		urlQueryParams.add(DossierConstants.APPLICANTIDTYPE, dossierRequest.getApplicantIdType());
		urlQueryParams.add(DossierConstants.APPLICANTIDNO, dossierRequest.getApplicantIdNo());
		urlQueryParams.add(DossierConstants.CITYCODE, dossierRequest.getCityCode());
		urlQueryParams.add(DossierConstants.DISTRICTCODE, dossierRequest.getDistrictCode());
		urlQueryParams.add(DossierConstants.WARDCODE, dossierRequest.getWardCode());
		urlQueryParams.add(DossierConstants.CONTACTTELNO, dossierRequest.getContactTelNo());
		urlQueryParams.add(DossierConstants.CONTACTEMAIL, dossierRequest.getContactEmail());
		urlQueryParams.add(DossierConstants.DELEGATEIDNO, dossierRequest.getDelegateIdNo());
		urlQueryParams.add(DossierConstants.DELEGATETELNO, dossierRequest.getDelegateTelNo());
		urlQueryParams.add(DossierConstants.DOSSIERSTATUS, dossierRequest.getDossierStatus());
		urlQueryParams.add(DossierConstants.DOSSIERSUBSTATUS, dossierRequest.getDossierSubStatus());
		urlQueryParams.add(DossierConstants.DOSSIERACTIONID, Long.toString(dossierRequest.getDossierActionId()));
		urlQueryParams.add(DossierConstants.VIAPOSTAL, Integer.toString(dossierRequest.getViaPostal()));
		urlQueryParams.add(DossierConstants.ONLINE, Boolean.toString(dossierRequest.isOnline()));
		urlQueryParams.add(DossierConstants.ORIGINALITY, Integer.toString(dossierRequest.getOriginality()));
		urlQueryParams.add(DossierConstants.SERVERNO, dossierRequest.getServerNo());
		urlQueryParams.add(DossierConstants.ORIGINDOSSIERID, Long.toString(dossierRequest.getOriginDossierId()));
		
		urlQueryParams.add(DossierConstants.ONLINE_VALUE, Boolean.toString(dossierRequest.isOnline()));
		urlQueryParams.add(DossierConstants.UNDUE, Boolean.toString(dossierRequest.isUndue()));
		urlQueryParams.add(DossierConstants.BETIME, Boolean.toString(dossierRequest.isBetime()));
		urlQueryParams.add(DossierConstants.ONTIME, Boolean.toString(dossierRequest.isOntime()));
		urlQueryParams.add(DossierConstants.RECEIVED, Boolean.toString(dossierRequest.isReceived()));
		urlQueryParams.add(DossierConstants.RELEASED, Boolean.toString(dossierRequest.isReleased()));
	}

}
