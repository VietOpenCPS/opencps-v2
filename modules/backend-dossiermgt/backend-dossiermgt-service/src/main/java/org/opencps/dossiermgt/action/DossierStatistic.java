package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.exception.PortalException;

public interface DossierStatistic {
	public org.opencps.dossiermgt.model.DossierStatistic insertDossierStatistic(
			org.opencps.dossiermgt.model.DossierStatistic dossierStatistic);

	public org.opencps.dossiermgt.model.DossierStatistic updateDossierStatistic(
			org.opencps.dossiermgt.model.DossierStatistic dossierStatistic);

	public org.opencps.dossiermgt.model.DossierStatistic deleteDossierStatistic(
			long dossierStatisticId) throws PortalException;

}
