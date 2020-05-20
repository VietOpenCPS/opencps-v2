package org.opencps.statistic.rest.engine.service;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.opencps.communication.model.ServerConfig;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DomainResponse;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.opencps.statistic.rest.service.DossierStatisticFinderService;
import org.opencps.statistic.rest.service.DossierStatisticFinderServiceImpl;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

public class StatisticSumYearService {

	private static final Log _log = LogFactoryUtil.getLog(StatisticSumYearService.class);
	private DossierStatisticFinderService dossierStatisticFinderService = new DossierStatisticFinderServiceImpl();

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
	
	public void caculateSumYear(long companyId, long groupId, int year, List<String> lstGroupGovs, List<ServerConfig> lstScs,
			List<OpencpsDossierStatistic> lstCurrents)
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
		
		List<String> nonGroupGovs = new ArrayList<String>();
		List<DomainResponse> domainResponses = getDomain(groupId, -1, year);
		
//		_log.info("RUN#1" + groupId + "year" + year);
		/* filter all */
		calcular1.filterSumYear(companyId, groupId, year, false, false, false, lstGroupGovs, lstScs, lstCurrents, domainResponses);
		lstDossierDataObjs.addAll(calcular1.getFilterSumYear(companyId, groupId, year, false, false, false, nonGroupGovs, lstScs, lstCurrents, domainResponses));
//		_log.info("RUN#2" + groupId + "year" + year);
		/* filter domain = null, agency = null, systemId != null */
		lstDossierDataObjs.addAll(calcular2.getFilterSumYear(companyId, groupId, year, false, false, true, nonGroupGovs, lstScs, lstCurrents, domainResponses));
		
		//LOG.info("RUN#3" + groupId + "year" + year);
		/* filter domain = null, agency != null, systemId = null */
		lstDossierDataObjs.addAll(calcular3.getFilterSumYear(companyId, groupId, year, false, true, false, nonGroupGovs, lstScs, lstCurrents, domainResponses));
		
		//LOG.info("RUN#4" + groupId + "year" + year);
		/* filter domain != null, agency = null, systemId = null */
		lstDossierDataObjs.addAll(calcular4.getFilterSumYear(companyId, groupId, year, true, false, false, nonGroupGovs, lstScs, lstCurrents, domainResponses));

		//LOG.info("RUN#5" + groupId + "year" + year);
		/* filter domain = null, agency != null, systemId != null */
		lstDossierDataObjs.addAll(calcular5.getFilterSumYear(companyId, groupId, year, false, true, true, nonGroupGovs, lstScs, lstCurrents, domainResponses));

		//LOG.info("RUN#6" + groupId + "year" + year);
		/* filter domain != null, agency = null, systemId != null */
		lstDossierDataObjs.addAll(calcular6.getFilterSumYear(companyId, groupId, year, true, false, true, nonGroupGovs, lstScs, lstCurrents, domainResponses));
		
		//LOG.info("RUN#7" + groupId + "year" + year);
		/* filter domain != null, agency != null, systemId = null */
		lstDossierDataObjs.addAll(calcular7.getFilterSumYear(companyId, groupId, year, true, true, false, nonGroupGovs, lstScs, lstCurrents, domainResponses));
		
		//LOG.info("RUN#8" + groupId + "year" + year);
		/* filter domain != null, agency != null, systemId != null */
		lstDossierDataObjs.addAll(calcular8.getFilterSumYear(companyId, groupId, year, true, true, true, nonGroupGovs, lstScs, lstCurrents, domainResponses));

//		calcular9.filterSumYear(companyId, groupId, year, false, false, false, lstGroupGovs, lstScs, lstCurrents, domainResponses);
		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();
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
			lstDossierDataObjs.addAll(calcular1.getFilterSumAllYear(companyId, groupId, month, false, false, false, lstGroupGovs, lstScs));
			//LOG.info("RUN#2" + groupId + "year" + year);
			/* filter domain = null, agency = null, systemId != null */
			lstDossierDataObjs.addAll(calcular2.getFilterSumAllYear(companyId, groupId, month, false, false, true, nonGroupGovs, lstScs));
			
			//LOG.info("RUN#3" + groupId + "year" + year);
			/* filter domain = null, agency != null, systemId = null */
			lstDossierDataObjs.addAll(calcular3.getFilterSumAllYear(companyId, groupId, month, false, true, false, nonGroupGovs, lstScs));
			
			//LOG.info("RUN#4" + groupId + "year" + year);
			/* filter domain != null, agency = null, systemId = null */
			lstDossierDataObjs.addAll(calcular4.getFilterSumAllYear(companyId, groupId, month, true, false, false, nonGroupGovs, lstScs));

			//LOG.info("RUN#5" + groupId + "year" + year);
			/* filter domain = null, agency != null, systemId != null */
			lstDossierDataObjs.addAll(calcular5.getFilterSumAllYear(companyId, groupId, month, false, true, true, nonGroupGovs, lstScs));

			//LOG.info("RUN#6" + groupId + "year" + year);
			/* filter domain != null, agency = null, systemId != null */
			lstDossierDataObjs.addAll(calcular6.getFilterSumAllYear(companyId, groupId, month, true, false, true, nonGroupGovs, lstScs));
			
			//LOG.info("RUN#7" + groupId + "year" + year);
			/* filter domain != null, agency != null, systemId = null */
			lstDossierDataObjs.addAll(calcular7.getFilterSumAllYear(companyId, groupId, month, true, true, false, nonGroupGovs, lstScs));
			
			//LOG.info("RUN#8" + groupId + "year" + year);
			/* filter domain != null, agency != null, systemId != null */
			lstDossierDataObjs.addAll(calcular8.getFilterSumAllYear(companyId, groupId, month, true, true, true, nonGroupGovs, lstScs));

//			calcular9.filterSumAllYear(companyId, groupId, month, false, false, false, lstGroupGovs, lstScs);	
			StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();
			engineUpdateAction.updateStatistic(lstDossierDataObjs);
		} catch (Exception e) {
			_log.debug(e);
		}
		
	}

}
