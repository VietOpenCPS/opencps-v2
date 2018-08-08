package org.opencps.statistic.rest.service;

import java.time.LocalDate;
import java.util.HashMap;

import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.GetDossierRequest;
import org.opencps.statistic.rest.dto.GetDossierResponse;
import org.opencps.statistic.rest.dto.GovAgencyData;
import org.opencps.statistic.rest.dto.GovAgencyRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.facade.OpencpsCallDossierRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallGovAgencyRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

public class DossierStatisticUpdate {
	private OpencpsCallRestFacade<GovAgencyRequest, GovAgencyResponse> callService = new OpencpsCallGovAgencyRestFacadeImpl();

	private OpencpsCallRestFacade<GetDossierRequest, GetDossierResponse> callDossierRestService = new OpencpsCallDossierRestFacadeImpl();

	private DossierStatisticUpdateService<DossierStatisticData> updateGovService = new DossierStatisticGovUpdateServiceImpl();

	private DossierStatisticUpdateService<HashMap<String, DossierStatisticData>> updateDomainService = new DossierStatisticDomainUpdateServiceImpl();

	public static final int TYPE_1_ALL_GOV_ALL_DOMAIN = 1;

	public static final int TYPE_2_EACH_GOV_ALL_DOMAIN_AND_EACH_GOV_EACH_DOMAIN = 2;

	public static final int TYPE_4_ALL_GOV_EACH_DOMAIN = 4;

	public static final int GROUP_TYPE_SITE = 1;

	public void calulateDossierStatistic(int type, long groupId, long companyId)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException, SystemException, PortalException {
		LocalDate cal = LocalDate.now();

		int month = cal.getMonthValue();
		int year = cal.getYear();

		GetDossierResponse dossierResponse = new GetDossierResponse();


		GetDossierRequest payload = new GetDossierRequest();

		/* TYPE_1_ALL_GOV_ALL_DOMAIN */

		if (type == TYPE_1_ALL_GOV_ALL_DOMAIN) {
			HashMap<String, DossierStatisticData> domainStatisticData = new HashMap<String, DossierStatisticData>();

			DossierStatisticData govDossierStaticData = new DossierStatisticData();

			payload.setGroupId(groupId);

			dossierResponse = callDossierRestService.callRestService(payload);

			if (Validator.isNotNull(dossierResponse.getData())) {

				DossierStatisticCalcular calcular = new DossierStatisticCalcular();

				calcular.filterStatistic(domainStatisticData, govDossierStaticData, dossierResponse, month, year,
						StringPool.BLANK, StringPool.BLANK, false, groupId, companyId);

				updateGovService.updateDossierStatistic(govDossierStaticData);

			}

		}

		if (type == TYPE_2_EACH_GOV_ALL_DOMAIN_AND_EACH_GOV_EACH_DOMAIN) {

			GovAgencyRequest agencyRequest = new GovAgencyRequest();

			agencyRequest.setGroupId(groupId);

			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (Validator.isNotNull(agencyResponse.getData())) {

				for (GovAgencyData agencyData : agencyResponse.getData()) {
					
					DossierStatisticData govDossierStaticData = new DossierStatisticData();
					
					HashMap<String, DossierStatisticData> domainStatisticData = new HashMap<String, DossierStatisticData>();

					DossierStatisticCalcular calcular = new DossierStatisticCalcular();

					payload.setGroupId(groupId);
					payload.setGovAgencyCode(agencyData.getItemCode());

					dossierResponse = callDossierRestService.callRestService(payload);

					if (Validator.isNotNull(dossierResponse.getData())) {
						calcular.filterStatistic(domainStatisticData, govDossierStaticData, dossierResponse, month,
								year, agencyData.getItemCode(), agencyData.getItemName(), false, groupId, companyId);
						
						updateGovService.updateDossierStatistic(govDossierStaticData);

					}

					// updateDomainService.updateDossierStatistic(domainStatisticData);

				}

			}

		}

		if (type == 3) {

			GovAgencyRequest agencyRequest = new GovAgencyRequest();

			agencyRequest.setGroupId(groupId);

			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (Validator.isNotNull(agencyResponse.getData())) {

				for (GovAgencyData agencyData : agencyResponse.getData()) {
					DossierStatisticData govDossierStaticData = new DossierStatisticData();
					HashMap<String, DossierStatisticData> domainStatisticData = new HashMap<String, DossierStatisticData>();

					DossierStatisticCalcular calcular = new DossierStatisticCalcular();

					payload.setGroupId(groupId);
					payload.setGovAgencyCode(agencyData.getItemCode());

					dossierResponse = callDossierRestService.callRestService(payload);

					if (Validator.isNotNull(dossierResponse.getData())) {
						calcular.filterStatistic(domainStatisticData, govDossierStaticData, dossierResponse, month,
								year, agencyData.getItemCode(), agencyData.getItemName(), true, groupId, companyId);
						updateDomainService.updateDossierStatistic(domainStatisticData);

					}

					// updateGovService.updateDossierStatistic(govDossierStaticData);

				}

			}

		}

		if (type == TYPE_4_ALL_GOV_EACH_DOMAIN) {
			payload.setGroupId(groupId);

			DossierStatisticData govDossierStaticData = new DossierStatisticData();

			dossierResponse = callDossierRestService.callRestService(payload);
			
			HashMap<String, DossierStatisticData> domainStatisticData = new HashMap<String, DossierStatisticData>();

			if (Validator.isNotNull(dossierResponse.getData())) {
				DossierStatisticCalcular calcular = new DossierStatisticCalcular();

				calcular.filterStatistic(domainStatisticData, govDossierStaticData, dossierResponse, month, year,
						StringPool.BLANK, StringPool.BLANK, true, groupId, companyId);
				
				updateDomainService.updateDossierStatistic(domainStatisticData);

			}

		}

	}

}
