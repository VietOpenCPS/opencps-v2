package org.opencps.dossiermgt.action.impl;

import org.opencps.dossiermgt.action.DossierStatistic;
import org.opencps.dossiermgt.service.DossierStatisticLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;

public class DossierStatisticActionImpl implements DossierStatistic {

	@Override
	public org.opencps.dossiermgt.model.DossierStatistic insertDossierStatistic(
			org.opencps.dossiermgt.model.DossierStatistic dossierStatistic) {
		return DossierStatisticLocalServiceUtil.addDossierStatistic(dossierStatistic);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierStatistic updateDossierStatistic(
			org.opencps.dossiermgt.model.DossierStatistic dossierStatistic) {
		return DossierStatisticLocalServiceUtil.updateDossierStatistic(dossierStatistic);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierStatistic deleteDossierStatistic(long dossierStatisticId)
			throws PortalException {
		return DossierStatisticLocalServiceUtil.deleteDossierStatistic(dossierStatisticId);
	}

}
