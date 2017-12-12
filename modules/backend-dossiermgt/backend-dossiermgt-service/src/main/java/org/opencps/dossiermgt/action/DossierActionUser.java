package org.opencps.dossiermgt.action;

import org.opencps.dossiermgt.service.persistence.DossierActionUserPK;

import com.liferay.portal.kernel.exception.PortalException;

public interface DossierActionUser {
	public org.opencps.dossiermgt.model.DossierActionUser addDossierActionUser(
			org.opencps.dossiermgt.model.DossierActionUser dossierActionUser);

	public org.opencps.dossiermgt.model.DossierActionUser updateDossierActionUser(
			org.opencps.dossiermgt.model.DossierActionUser dossierActionUser);

	public org.opencps.dossiermgt.model.DossierActionUser deleteDossierActionUser(
			DossierActionUserPK dossierActionUserPK) throws PortalException;

	public void initDossierActionUser(long dossierActionId, long userId, long groupId, long assignedUserId);
}
