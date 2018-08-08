package org.opencps.statistic.rest.service;

import java.util.HashMap;
import java.util.Map;

import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class DossierStatisticDomainUpdateServiceImpl
		implements DossierStatisticUpdateService<HashMap<String, DossierStatisticData>> {

	private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticDomainUpdateServiceImpl.class);

	private DossierStatisticUpdateService<DossierStatisticData> updateGovService = new DossierStatisticGovUpdateServiceImpl();

	@Override
	public void updateDossierStatistic(HashMap<String, DossierStatisticData> input)
			throws PortalException, SystemException {

		for (Map.Entry me : input.entrySet()) {

			DossierStatisticData payload = (DossierStatisticData) me.getValue();

			updateGovService.updateDossierStatistic(payload);


		}

	}

}
