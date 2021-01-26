package org.opencps.statistic.rest.engine.service;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.opencps.communication.model.ServerConfig;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DomainResponse;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.opencps.statistic.rest.dto.GovAgencyData;
import org.opencps.statistic.rest.dto.GovAgencyRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.facade.OpencpsCallGovAgencyRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.opencps.statistic.rest.service.DossierStatisticFinderService;
import org.opencps.statistic.rest.service.DossierStatisticFinderServiceImpl;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.DossierStatisticUtils;
import org.opencps.statistic.rest.util.StatisticDataUtil;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

public class StatisticSumYearService {

	private static final Log _log = LogFactoryUtil.getLog(StatisticSumYearService.class);
	private DossierStatisticFinderService dossierStatisticFinderService = new DossierStatisticFinderServiceImpl();
	private OpencpsCallRestFacade<GovAgencyRequest, GovAgencyResponse> callService = new OpencpsCallGovAgencyRestFacadeImpl();

	/* Tìm kiếm domain theo tháng và năm */
	private List<DomainResponse> getDomain(long groupId, int month, int year) {
		List<DomainResponse> domainResponses = new ArrayList<DomainResponse>();

		DossierStatisticRequest dossierStatisticRequest = new DossierStatisticRequest();

		dossierStatisticRequest.setMonth(month);
		dossierStatisticRequest.setYear(year);
		dossierStatisticRequest.setGroupId(groupId);
		dossierStatisticRequest.setStart(QueryUtil.ALL_POS);
		dossierStatisticRequest.setEnd(QueryUtil.ALL_POS);


		try {
			DossierStatisticResponse dossierStatisticResponse = dossierStatisticFinderService
					.finderDossierStatisticSystem(dossierStatisticRequest);

			Optional<List<DossierStatisticData>> optional = Optional
					.ofNullable(dossierStatisticResponse.getDossierStatisticData());

			optional.ifPresent(source -> {
				for (DossierStatisticData dossierStatisticData : source) {
					DomainResponse domainResponse = new DomainResponse();
					if (Validator.isNotNull(dossierStatisticData.getDomainCode())
							&& Validator.isNotNull(dossierStatisticData.getDomainName())) {

						domainResponse.setItemCode(dossierStatisticData.getDomainCode());
						domainResponse.setItemName(dossierStatisticData.getDomainName());

						domainResponses.add(domainResponse);
					}

				}
			});

		} catch (PortalException e) {
			_log.error(e);
		}

		/* remove duplicate */
		Set<DomainResponse> hs = new HashSet<>();
		hs.addAll(domainResponses);
		domainResponses.clear();
		domainResponses.addAll(hs);

		return domainResponses;
	}
	
	public void caculateSumYear(long companyId, long groupId, int year, List<String> lstGroupGovs, List<ServerConfig> lstScs)
			throws PortalException, UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		
		//int year = LocalDate.now().getYear();
		StatisticSumYearCalcular calcular1 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular2 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular3 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular4 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular5 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular6 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular7 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular8 = new StatisticSumYearCalcular();
//		StatisticSumYearCalcular calcular9 = new StatisticSumYearCalcular();
		
		List<String> nonGroupGovs = new ArrayList<String>();
		List<DomainResponse> domainResponses = getDomain(groupId, -1, year);
		GovAgencyRequest agencyRequest = new GovAgencyRequest();

		agencyRequest.setGroupId(groupId);
		if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
			if (lstScs.size() >= 1) {
				JSONObject scObject;
				try {
					scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
					if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
						agencyRequest.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
					}
					if (scObject.has(DossierStatisticConstants.SECRET_KEY)) {
						agencyRequest.setPassword(scObject.getString(DossierStatisticConstants.SECRET_KEY));
					}
					if (scObject.has(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY)) {
						agencyRequest.setEndpoint(scObject.getString(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY));
					}						
				} catch (JSONException e) {
					_log.error(e);
				}
			}
		}

		GovAgencyResponse agencyResponse = null;
		if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
			agencyResponse = callService.callRestService(agencyRequest);
		}
		else {
			agencyResponse = StatisticDataUtil.getLocalGovAgency(agencyRequest);
		}

		Optional<List<GovAgencyData>> govDataList = Optional.ofNullable(agencyResponse.getData());
		
//		_log.info("RUN#1" + groupId + "year" + year);
		/* filter all */
//		calcular1.filterSumYear(companyId, groupId, year, false, false, false, lstGroupGovs, lstScs, lstCurrents, domainResponses);
		calcular1.filterSumYear(companyId, groupId, year, false, false, false, lstGroupGovs, lstScs, domainResponses, govDataList);
//		_log.info("RUN#2" + groupId + "year" + year);
		/* filter domain = null, agency = null, systemId != null */
		calcular2.filterSumYear(companyId, groupId, year, false, false, true, nonGroupGovs, lstScs, domainResponses, govDataList);
		
		//LOG.info("RUN#3" + groupId + "year" + year);
		/* filter domain = null, agency != null, systemId = null */
		calcular3.filterSumYear(companyId, groupId, year, false, true, false, nonGroupGovs, lstScs, domainResponses, govDataList);
		
		//LOG.info("RUN#4" + groupId + "year" + year);
		/* filter domain != null, agency = null, systemId = null */
		calcular4.filterSumYear(companyId, groupId, year, true, false, false, nonGroupGovs, lstScs, domainResponses, govDataList);

		//LOG.info("RUN#5" + groupId + "year" + year);
		/* filter domain = null, agency != null, systemId != null */
		calcular5.filterSumYear(companyId, groupId, year, false, true, true, nonGroupGovs, lstScs, domainResponses, govDataList);

		//LOG.info("RUN#6" + groupId + "year" + year);
		/* filter domain != null, agency = null, systemId != null */
		calcular6.filterSumYear(companyId, groupId, year, true, false, true, nonGroupGovs, lstScs, domainResponses, govDataList);
		
		//LOG.info("RUN#7" + groupId + "year" + year);
		/* filter domain != null, agency != null, systemId = null */
		calcular7.filterSumYear(companyId, groupId, year, true, true, false, nonGroupGovs, lstScs, domainResponses, govDataList);
		
		//LOG.info("RUN#8" + groupId + "year" + year);
		/* filter domain != null, agency != null, systemId != null */
		calcular8.filterSumYear(companyId, groupId, year, true, true, true, nonGroupGovs, lstScs, domainResponses, govDataList);

//		calcular9.filterSumYear(companyId, groupId, year, false, false, false, lstGroupGovs, lstScs, domainResponses);
	}

	public void batchCaculateSumYear(long companyId, long groupId, int year, List<ServerConfig> lstScs,
			List<OpencpsDossierStatistic> lstCurrents, JSONObject jsonEndPoint)
			throws PortalException, UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		//int year = LocalDate.now().getYear();
		List<JSONObject> lstDossierDataObjs = new ArrayList<JSONObject>();
		
		StatisticSumYearCalcular calcular1 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular2 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular3 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular4 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular5 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular6 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular7 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular8 = new StatisticSumYearCalcular();
//		StatisticSumYearCalcular calcular9 = new StatisticSumYearCalcular();
		
		List<DomainResponse> domainResponses = getDomain(groupId, -1, year);
		
//		_log.info("RUN#1" + groupId + "year" + year);
		/* filter all */
//		calcular1.filterSumYear(companyId, groupId, year, false, false, false, lstGroupGovs, lstScs, lstCurrents, domainResponses);
		lstDossierDataObjs.addAll(calcular1.getFilterSumYear(companyId, groupId, year, false, false, false, lstScs, lstCurrents, domainResponses));
//		_log.info("RUN#2" + groupId + "year" + year);
		/* filter domain = null, agency = null, systemId != null */
		lstDossierDataObjs.addAll(calcular2.getFilterSumYear(companyId, groupId, year, false, false, true, lstScs, lstCurrents, domainResponses));
		
		//LOG.info("RUN#3" + groupId + "year" + year);
		/* filter domain = null, agency != null, systemId = null */
		lstDossierDataObjs.addAll(calcular3.getFilterSumYear(companyId, groupId, year, false, true, false, lstScs, lstCurrents, domainResponses));
		
		//LOG.info("RUN#4" + groupId + "year" + year);
		/* filter domain != null, agency = null, systemId = null */
		lstDossierDataObjs.addAll(calcular4.getFilterSumYear(companyId, groupId, year, true, false, false, lstScs, lstCurrents, domainResponses));

		//LOG.info("RUN#5" + groupId + "year" + year);
		/* filter domain = null, agency != null, systemId != null */
		lstDossierDataObjs.addAll(calcular5.getFilterSumYear(companyId, groupId, year, false, true, true, lstScs, lstCurrents, domainResponses));

		//LOG.info("RUN#6" + groupId + "year" + year);
		/* filter domain != null, agency = null, systemId != null */
		lstDossierDataObjs.addAll(calcular6.getFilterSumYear(companyId, groupId, year, true, false, true, lstScs, lstCurrents, domainResponses));
		
		//LOG.info("RUN#7" + groupId + "year" + year);
		/* filter domain != null, agency != null, systemId = null */
		lstDossierDataObjs.addAll(calcular7.getFilterSumYear(companyId, groupId, year, true, true, false, lstScs, lstCurrents, domainResponses));
		
		//LOG.info("RUN#8" + groupId + "year" + year);
		/* filter domain != null, agency != null, systemId != null */
		lstDossierDataObjs.addAll(calcular8.getFilterSumYear(companyId, groupId, year, true, true, true, lstScs, lstCurrents, domainResponses));

//		calcular9.filterSumYear(companyId, groupId, year, false, false, false, lstGroupGovs, lstScs, lstCurrents, domainResponses);
		//
		if (lstDossierDataObjs != null && lstDossierDataObjs.size() > 0 && jsonEndPoint != null) {
						
			JSONArray jsonArr = JSONFactoryUtil.createJSONArray();
			//
			for (JSONObject data : lstDossierDataObjs) {
				jsonArr.put(data);
			}
			String sbUpdate = DossierStatisticUtils.invokeUpdateStatistic(jsonEndPoint,
					JSONFactoryUtil.looseSerialize(jsonArr));
			if (Validator.isNotNull(sbUpdate)) {
				try {
					JSONObject jsonUpdate = JSONFactoryUtil.createJSONObject(sbUpdate);
					//
					if (jsonUpdate.has("value")
							&& "SUCCESSFULL".equals(jsonUpdate.getString("value"))) {
					}
				} catch (JSONException e1) {
					_log.debug(e1);
				}
			}
		}

		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();
		for (int j = 0; j<lstDossierDataObjs.size(); j++) {
			
			if (lstDossierDataObjs.get(j).getInt("month") == 0 
					&& lstDossierDataObjs.get(j).getInt("groupId") == 35417 && lstDossierDataObjs.get(j).getInt("year") == 2020
					&& lstDossierDataObjs.get(j).getString("govAgencyCode").contentEquals("SCT")) {
				_log.info("111111 :" + JSONFactoryUtil.looseSerialize(lstDossierDataObjs.get(j)));
			}
			
			if (lstDossierDataObjs.get(j).getInt("month") == 0 
					&& lstDossierDataObjs.get(j).getInt("groupId") == 35417 && lstDossierDataObjs.get(j).getInt("year") == 2021
					&& lstDossierDataObjs.get(j).getString("govAgencyCode").contentEquals("SCT")) {
				_log.info("222222 :" + JSONFactoryUtil.looseSerialize(lstDossierDataObjs.get(j)));
			}
		}
		engineUpdateAction.updateStatistic(lstDossierDataObjs);
	}

	public void votingCalculateSumYear(long companyId, long groupId, int year)
			throws PortalException, UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		
		VotingStatisticSumYearCalcular calcular = new VotingStatisticSumYearCalcular();
		
		//LOG.info("RUN#1" + groupId + "year" + year);
		/* filter all */
		calcular.filterVotingSumYear(companyId, groupId, year, false, false, false);

		//LOG.info("RUN#2" + groupId + "year" + year);
		/* filter votingCode != null, domain == null, agency = null */
		calcular.filterVotingSumYear(companyId, groupId, year, false, false, true);
		
		//LOG.info("RUN#3" + groupId + "year" + year);
		/* filter votingCode != null , domain = null, agency = null */
		calcular.filterVotingSumYear(companyId, groupId, year, false, true, false);
		
		//LOG.info("RUN#4" + groupId + "year" + year);
		/* filter votingCode = null, domain != null, agency = null */
		calcular.filterVotingSumYear(companyId, groupId, year, false, true, true);
		
		/* filter votingCode = null, domain == null, agency != null */
		calcular.filterVotingSumYear(companyId, groupId, year, true, false, false);
		
		/* filter votingCode != null, domain = null, agency != null */
		calcular.filterVotingSumYear(companyId, groupId, year, true, false, true);
		
		/* filter votingCode = null , domain != null, agency != null */
		calcular.filterVotingSumYear(companyId, groupId, year, true, true , false);
		
		/* filter votingCode != null, domain != null, agency != null */
		calcular.filterVotingSumYear(companyId, groupId, year, true, true, true);

	}

	public void personCalculateSumYear(long companyId, long groupId, int year)
			throws PortalException, UpstreamServiceTimedOutException, UpstreamServiceFailedException {

		PersonStatisticSumYearCalcular calcular = new PersonStatisticSumYearCalcular();
//		VotingStatisticSumYearCalcular calcular2 = new VotingStatisticSumYearCalcular();
//		VotingStatisticSumYearCalcular calcular3 = new VotingStatisticSumYearCalcular();
//		VotingStatisticSumYearCalcular calcular4 = new VotingStatisticSumYearCalcular();
		
		//LOG.info("RUN#1" + groupId + "year" + year);
		/* filter all */
		calcular.filterPersonSumYear(companyId, groupId, year, false, false, false);

		//LOG.info("RUN#2" + groupId + "year" + year);
		/* filter votingCode != null, domain == null, agency = null */
		calcular.filterPersonSumYear(companyId, groupId, year, false, false, true);
		
		//LOG.info("RUN#3" + groupId + "year" + year);
		/* filter votingCode != null , domain = null, agency = null */
		calcular.filterPersonSumYear(companyId, groupId, year, false, true, false);
		
		//LOG.info("RUN#4" + groupId + "year" + year);
		/* filter votingCode = null, domain != null, agency = null */
		calcular.filterPersonSumYear(companyId, groupId, year, false, true, true);
		
		/* filter votingCode = null, domain == null, agency != null */
		calcular.filterPersonSumYear(companyId, groupId, year, true, false, false);
		
		/* filter votingCode != null, domain = null, agency != null */
		calcular.filterPersonSumYear(companyId, groupId, year, true, false, true);
		
		/* filter votingCode = null , domain != null, agency != null */
		calcular.filterPersonSumYear(companyId, groupId, year, true, true , false);
		
		/* filter votingCode != null, domain != null, agency != null */
		calcular.filterPersonSumYear(companyId, groupId, year, true, true, true);

	}

	public void caculateSumAllYear(long companyId, long groupId, int month, List<String> lstGroupGovs, List<ServerConfig> lstScs) {
		//int year = LocalDate.now().getYear();
		List<JSONObject> lstDossierDataObjs = new ArrayList<JSONObject>();
		
		StatisticSumYearCalcular calcular1 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular2 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular3 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular4 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular5 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular6 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular7 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular8 = new StatisticSumYearCalcular();
//		StatisticSumYearCalcular calcular9 = new StatisticSumYearCalcular();
		
		List<String> nonGroupGovs = new ArrayList<String>();
		
		try {
			//LOG.info("RUN#1" + groupId + "year" + year);
			/* filter all */
			calcular1.filterSumAllYear(companyId, groupId, month, false, false, false, lstGroupGovs, lstScs);
			//LOG.info("RUN#2" + groupId + "year" + year);
			/* filter domain = null, agency = null, systemId != null */
			calcular2.filterSumAllYear(companyId, groupId, month, false, false, true, nonGroupGovs, lstScs);
			
			//LOG.info("RUN#3" + groupId + "year" + year);
			/* filter domain = null, agency != null, systemId = null */
			calcular3.filterSumAllYear(companyId, groupId, month, false, true, false, nonGroupGovs, lstScs);
			
			//LOG.info("RUN#4" + groupId + "year" + year);
			/* filter domain != null, agency = null, systemId = null */
			calcular4.filterSumAllYear(companyId, groupId, month, true, false, false, nonGroupGovs, lstScs);

			//LOG.info("RUN#5" + groupId + "year" + year);
			/* filter domain = null, agency != null, systemId != null */
			calcular5.filterSumAllYear(companyId, groupId, month, false, true, true, nonGroupGovs, lstScs);

			//LOG.info("RUN#6" + groupId + "year" + year);
			/* filter domain != null, agency = null, systemId != null */
			calcular6.filterSumAllYear(companyId, groupId, month, true, false, true, nonGroupGovs, lstScs);
			
			//LOG.info("RUN#7" + groupId + "year" + year);
			/* filter domain != null, agency != null, systemId = null */
			calcular7.filterSumAllYear(companyId, groupId, month, true, true, false, nonGroupGovs, lstScs);
			
			//LOG.info("RUN#8" + groupId + "year" + year);
			/* filter domain != null, agency != null, systemId != null */
			calcular8.filterSumAllYear(companyId, groupId, month, true, true, true, nonGroupGovs, lstScs);
		} catch (Exception e) {
			_log.debug(e);
		}
		
	}

	public void batchCaculateSumAllYear(long companyId, long groupId, int month, List<ServerConfig> lstScs, JSONObject jsonEndPoint) {
		//int year = LocalDate.now().getYear();
		List<JSONObject> lstDossierDataObjs = new ArrayList<JSONObject>();
		
		StatisticSumYearCalcular calcular1 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular2 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular3 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular4 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular5 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular6 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular7 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular8 = new StatisticSumYearCalcular();
		
		try {
			//LOG.info("RUN#1" + groupId + "year" + year);
			/* filter all */
			lstDossierDataObjs.addAll(calcular1.getFilterSumAllYear(companyId, groupId, month, false, false, false, lstScs));
			//LOG.info("RUN#2" + groupId + "year" + year);
			/* filter domain = null, agency = null, systemId != null */
			lstDossierDataObjs.addAll(calcular2.getFilterSumAllYear(companyId, groupId, month, false, false, true, lstScs));
			
			//LOG.info("RUN#3" + groupId + "year" + year);
			/* filter domain = null, agency != null, systemId = null */
			lstDossierDataObjs.addAll(calcular3.getFilterSumAllYear(companyId, groupId, month, false, true, false, lstScs));
			
			//LOG.info("RUN#4" + groupId + "year" + year);
			/* filter domain != null, agency = null, systemId = null */
			lstDossierDataObjs.addAll(calcular4.getFilterSumAllYear(companyId, groupId, month, true, false, false, lstScs));

			//LOG.info("RUN#5" + groupId + "year" + year);
			/* filter domain = null, agency != null, systemId != null */
			lstDossierDataObjs.addAll(calcular5.getFilterSumAllYear(companyId, groupId, month, false, true, true, lstScs));

			//LOG.info("RUN#6" + groupId + "year" + year);
			/* filter domain != null, agency = null, systemId != null */
			lstDossierDataObjs.addAll(calcular6.getFilterSumAllYear(companyId, groupId, month, true, false, true, lstScs));
			
			//LOG.info("RUN#7" + groupId + "year" + year);
			/* filter domain != null, agency != null, systemId = null */
			lstDossierDataObjs.addAll(calcular7.getFilterSumAllYear(companyId, groupId, month, true, true, false, lstScs));
			
			//LOG.info("RUN#8" + groupId + "year" + year);
			/* filter domain != null, agency != null, systemId != null */
			lstDossierDataObjs.addAll(calcular8.getFilterSumAllYear(companyId, groupId, month, true, true, true, lstScs));

//			calcular9.filterSumAllYear(companyId, groupId, month, false, false, false, lstGroupGovs, lstScs);
			if (lstDossierDataObjs != null && lstDossierDataObjs.size() > 0 && jsonEndPoint != null) {

				JSONArray jsonArr = JSONFactoryUtil.createJSONArray();
				//
				for (JSONObject data : lstDossierDataObjs) {
					jsonArr.put(data);
				}
				//
				String sbUpdate = DossierStatisticUtils.invokeUpdateStatistic(jsonEndPoint,
						JSONFactoryUtil.looseSerialize(jsonArr));
				if (Validator.isNotNull(sbUpdate)) {
					try {
						JSONObject jsonUpdate = JSONFactoryUtil.createJSONObject(sbUpdate);
						//
						if (jsonUpdate.has("value")
								&& "SUCCESSFULL".equals(jsonUpdate.getString("value"))) {
						}
					} catch (JSONException e1) {
						_log.debug(e1);
					}
				}
			}

			StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();
			engineUpdateAction.updateStatistic(lstDossierDataObjs);
		} catch (Exception e) {
			_log.debug(e);
		}
		
	}	
}
