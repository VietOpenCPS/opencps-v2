package org.opencps.statistic.rest.engine.service;

import com.liferay.portal.kernel.exception.PortalException;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

public class StatisticSumYearService {

	public void caculateSumYear(long companyId, long groupId, int year)
			throws PortalException, UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		
		//int year = LocalDate.now().getYear();
		
		StatisticSumYearCalcular calcular1 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular2 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular3 = new StatisticSumYearCalcular();
		StatisticSumYearCalcular calcular4 = new StatisticSumYearCalcular();
		
		//LOG.info("RUN#1" + groupId + "year" + year);
		/* filter all */
		calcular1.filterSumYear(companyId, groupId, year, false, false);
		//LOG.info("RUN#2" + groupId + "year" + year);
		/* filter domain, agency = null */
		calcular2.filterSumYear(companyId, groupId, year, true, false);
		
		//LOG.info("RUN#3" + groupId + "year" + year);
		/* filter domain = null, agency != null */
		calcular3.filterSumYear(companyId, groupId, year, false, true);
		
		//LOG.info("RUN#4" + groupId + "year" + year);
		/* filter domain != null, agency != null */
		calcular4.filterSumYear(companyId, groupId, year, true, true);

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

}
