package org.opencps.backend.statisticmgt.dto;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.opencps.backend.statisticmgt.util.ConvertUtil;
import org.opencps.statistic.model.OpencpsDossierStatisticMgt;
import org.opencps.statistic.service.OpencpsDossierStatisticMgtLocalServiceUtil;

public class DossierStatisticMgtFinderServiceImpl implements DossierStatisticMgtFinderService{

	@Override
	public DossierStatisticMgtResponse finderDossierStatisticMgt(DossierStatisticMgtRequest dossierStatisticMgtRequest)
			throws PortalException {
		
		List<OpencpsDossierStatisticMgt> listDossierStatisticMgts = OpencpsDossierStatisticMgtLocalServiceUtil.searchDossierStatistic(dossierStatisticMgtRequest.getGroupId(), dossierStatisticMgtRequest.getMonth(), 
				dossierStatisticMgtRequest.getYear(), dossierStatisticMgtRequest.getDomainCode(), 
				dossierStatisticMgtRequest.getGovAgencyCode(), dossierStatisticMgtRequest.getGroupBy(), 
				dossierStatisticMgtRequest.getStart(), dossierStatisticMgtRequest.getEnd());
		
		return ConvertUtil.getDossierStatisticMgtResponse().convert(listDossierStatisticMgts);
	}

}
