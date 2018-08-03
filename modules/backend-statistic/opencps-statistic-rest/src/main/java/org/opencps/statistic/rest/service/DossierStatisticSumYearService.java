package org.opencps.statistic.rest.service;

import java.util.List;
import java.util.Optional;

import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.opencps.statistic.rest.dto.GovAgencyData;
import org.opencps.statistic.rest.dto.GovAgencyRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.engine.DossierStatisticEngine;
import org.opencps.statistic.rest.facade.OpencpsCallGovAgencyRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.opencps.statistic.rest.util.DossierStatisticUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

public class DossierStatisticSumYearService {

	private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticEngine.class);
	
	private DossierStatisticFinderService dossierStatisticFinderService = new DossierStatisticFinderServiceImpl();

	private OpencpsCallRestFacade<GovAgencyRequest, GovAgencyResponse> callService = new OpencpsCallGovAgencyRestFacadeImpl();

	public void caculateSumYear(long groupId, int year)
			throws PortalException, UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		DossierStatisticRequest dossierStatisticRequest = new DossierStatisticRequest();
		
		dossierStatisticRequest.setMonth(-1);
		dossierStatisticRequest.setYear(year);
		dossierStatisticRequest.setGroupId(groupId);

		GovAgencyRequest agencyRequest = new GovAgencyRequest();

		agencyRequest.setGroupId(groupId);

		GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

		Optional<List<GovAgencyData>> govDataList = Optional.ofNullable(agencyResponse.getData());

		govDataList.ifPresent(source -> {
			for (GovAgencyData data : source) {
				dossierStatisticRequest.setGovAgencyCode(data.getItemCode());
				DossierStatisticResponse dossierStatisticResponse;

				try {
					dossierStatisticResponse = dossierStatisticFinderService
							.finderDossierStatistic(dossierStatisticRequest);

					DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);
					DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticResponse);

				} catch (PortalException e) {
					e.printStackTrace();
				}

			}
		});

	}
}
