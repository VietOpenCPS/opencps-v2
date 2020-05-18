package org.opencps.statistic.rest.engine.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;

import org.opencps.communication.model.ServerConfig;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

public class StatisticSumYearService {

	private static final Log _log = LogFactoryUtil.getLog(StatisticSumYearService.class);

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
		StatisticSumYearCalcular calcular9 = new StatisticSumYearCalcular();
		
		List<String> nonGroupGovs = new ArrayList<String>();
		
		_log.info("RUN#1" + groupId + "year" + year);
		/* filter all */
		calcular1.filterSumYear(companyId, groupId, year, false, false, false, nonGroupGovs, lstScs);
		_log.info("RUN#2" + groupId + "year" + year);
		/* filter domain = null, agency = null, systemId != null */
		calcular2.filterSumYear(companyId, groupId, year, false, false, true, nonGroupGovs, lstScs);
		
		//LOG.info("RUN#3" + groupId + "year" + year);
		/* filter domain = null, agency != null, systemId = null */
		calcular3.filterSumYear(companyId, groupId, year, false, true, false, nonGroupGovs, lstScs);
		
		//LOG.info("RUN#4" + groupId + "year" + year);
		/* filter domain != null, agency = null, systemId = null */
		calcular4.filterSumYear(companyId, groupId, year, true, false, false, nonGroupGovs, lstScs);

		//LOG.info("RUN#5" + groupId + "year" + year);
		/* filter domain = null, agency != null, systemId != null */
		calcular5.filterSumYear(companyId, groupId, year, false, true, true, nonGroupGovs, lstScs);

		//LOG.info("RUN#6" + groupId + "year" + year);
		/* filter domain != null, agency = null, systemId != null */
		calcular6.filterSumYear(companyId, groupId, year, true, false, true, nonGroupGovs, lstScs);
		
		//LOG.info("RUN#7" + groupId + "year" + year);
		/* filter domain != null, agency != null, systemId = null */
		calcular7.filterSumYear(companyId, groupId, year, true, true, false, nonGroupGovs, lstScs);
		
		//LOG.info("RUN#8" + groupId + "year" + year);
		/* filter domain != null, agency != null, systemId != null */
		calcular8.filterSumYear(companyId, groupId, year, true, true, true, nonGroupGovs, lstScs);

		calcular9.filterSumYear(companyId, groupId, year, false, false, false, lstGroupGovs, lstScs);
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
		
		StatisticSumYearCalcular calcular1 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular2 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular3 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular4 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular5 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular6 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular7 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular8 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular9 = new StatisticSumYearCalcular();
		
		List<String> nonGroupGovs = new ArrayList<String>();
		
		try {
			//LOG.info("RUN#1" + groupId + "year" + year);
			/* filter all */
			calcular1.filterSumAllYear(companyId, groupId, month, false, false, false, nonGroupGovs, lstScs);
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

			calcular9.filterSumAllYear(companyId, groupId, month, true, true, true, lstGroupGovs, lstScs);		
		} catch (Exception e) {
			_log.debug(e);
		}
		
	}

}
