package org.opencps.statistic.rest.service;

import com.liferay.portal.kernel.exception.PortalException;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

public class DossierStatisticSumYearService {

	public void caculateSumYear(long companyId, long groupId, int year)
			throws PortalException, UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		
		DossierStatisticSumCalcular calcular1 = new DossierStatisticSumCalcular();
		DossierStatisticSumCalcular calcular2 = new DossierStatisticSumCalcular();
		DossierStatisticSumCalcular calcular3 = new DossierStatisticSumCalcular();
		DossierStatisticSumCalcular calcular4 = new DossierStatisticSumCalcular();
		
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


}
