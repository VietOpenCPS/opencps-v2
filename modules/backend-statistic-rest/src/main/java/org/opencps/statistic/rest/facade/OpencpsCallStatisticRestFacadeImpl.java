package org.opencps.statistic.rest.facade;

import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.HashMap;

import org.opencps.statistic.rest.dto.DossierStatisticModel;
import org.opencps.statistic.rest.util.DossierStatisticConfig;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.ReportConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;
import opencps.statistic.common.webservice.facade.OpencpsRestFacade;

public class OpencpsCallStatisticRestFacadeImpl extends OpencpsRestFacade<DossierStatisticModel, DossierStatisticModel>
		implements OpencpsCallRestFacade<DossierStatisticModel, DossierStatisticModel> {

	private final static Logger LOG = LoggerFactory.getLogger(OpencpsCallStatisticRestFacadeImpl.class);
	private static final int DEFAULT_CONNECT_TIMEOUT = 3 * 60000;
	private static final int DEFAULT_READ_TIMEOUT = 3 * 60000;
	
	@Override
	public DossierStatisticModel callRestService(DossierStatisticModel payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		return makeServiceCall(payload);
	}

	protected DossierStatisticModel makeServiceCall(DossierStatisticModel payload)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		
		MultiValueMap<String, String> urlQueryParams = new LinkedMultiValueMap<>();
//		buildUrlQueryParams(urlQueryParams, payload);
		
		String endPoint = Validator.isNotNull(payload.getEndpoint()) ? payload.getEndpoint() : DossierStatisticConfig.get(DossierStatisticConstants.STATISTIC_REPORT_ENDPOINT);
		HashMap<String, String> urlPathSegments = new HashMap<>();
		String url = buildUrl(endPoint, urlPathSegments, urlQueryParams);
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.add("groupId", Long.toString(payload.getGroupId()));
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add(ReportConstants.BETIMESCOUNT, String.valueOf(payload.getBetimesCount()));
		params.add(ReportConstants.CANCELLEDCOUNT, String.valueOf(payload.getCancelledCount()));
		params.add(ReportConstants.DENIEDCOUNT, String.valueOf(payload.getDeniedCount()));
		params.add(ReportConstants.DOMAINCODE, String.valueOf(payload.getDomainCode()));
		params.add(ReportConstants.DOMAINNAME, String.valueOf(payload.getDomainName()));
		params.add(ReportConstants.DONECOUNT, String.valueOf(payload.getDoneCount()));
		params.add(ReportConstants.GOVAGENCYCODE, String.valueOf(payload.getGovAgencyCode()));
		params.add(ReportConstants.GOVAGENCYNAME, String.valueOf(payload.getGovAgencyName()));
		params.add(ReportConstants.GROUPAGENCYCODE, String.valueOf(payload.getGroupAgencyCode()));
		params.add(ReportConstants.INSIDECOUNT, String.valueOf(payload.getInsideCount()));
		params.add(ReportConstants.INTEROPERATINGCOUNT, String.valueOf(payload.getInteroperatingCount()));
		params.add(ReportConstants.MONTH, String.valueOf(payload.getMonth()));
		params.add(ReportConstants.ONEGATECOUNT, String.valueOf(payload.getOnegateCount()));
		params.add(ReportConstants.ONLINECOUNT, String.valueOf(payload.getOnlineCount()));
		params.add(ReportConstants.ONTIMECOUNT, String.valueOf(payload.getOntimeCount()));
		params.add(ReportConstants.ONTIMEPERCENTAGE, String.valueOf(payload.getOntimePercentage()));
		params.add(ReportConstants.OUTSIDECOUNT, String.valueOf(payload.getOutsideCount()));
		params.add(ReportConstants.OVERDUECOUNT, String.valueOf(payload.getOverdueCount()));
		params.add(ReportConstants.OVERTIMECOUNT, String.valueOf(payload.getOvertimeCount()));
		params.add(ReportConstants.OVERTIMEINSIDE, String.valueOf(payload.getOvertimeInside()));
		params.add(ReportConstants.OVERTIMEOUTSIDE, String.valueOf(payload.getOvertimeOutside()));
		params.add(ReportConstants.PAUSINGCOUNT, String.valueOf(payload.getPausingCount()));
		params.add(ReportConstants.PROCESSCOUNT, String.valueOf(payload.getProcessCount()));
		params.add(ReportConstants.PROCESSINGCOUNT, String.valueOf(payload.getProcessingCount()));
		params.add(ReportConstants.RECEIVEDCOUNT, String.valueOf(payload.getReceivedCount()));
		params.add(ReportConstants.RELEASECOUNT, String.valueOf(payload.getReleaseCount()));
		params.add(ReportConstants.RELEASINGCOUNT, String.valueOf(payload.getReleasingCount()));
		params.add(ReportConstants.REMAININGCOUNT, String.valueOf(payload.getRemainingCount()));
		params.add(ReportConstants.REPORTING, String.valueOf(payload.getReporting()));
		params.add(ReportConstants.TOTALCOUNT, String.valueOf(payload.getTotalCount()));
		params.add(ReportConstants.UNDUECOUNT, String.valueOf(payload.getUndueCount()));
		params.add(ReportConstants.UNRESOLVEDCOUNT, String.valueOf(payload.getUnresolvedCount()));
		params.add(ReportConstants.WAITINGCOUNT, String.valueOf(payload.getWaitingCount()));
		params.add(ReportConstants.YEAR, String.valueOf(payload.getYear()));
		
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
		requestFactory.setReadTimeout(DEFAULT_READ_TIMEOUT);

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, httpHeaders);
		
		DossierStatisticModel result = restTemplate.postForObject(url, request, DossierStatisticModel.class);
		
		return result;
	}
}
