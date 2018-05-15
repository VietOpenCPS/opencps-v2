package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.action.DossierMarkActions;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.service.DossierMarkLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

public class DossierMarkActionsImpl implements DossierMarkActions {

	private static final Log _log = LogFactoryUtil.getLog(DossierMarkActionsImpl.class);

	@Override
	public DossierMark addDossierMark(long groupId, long dossierId, String dossierPartNo, Boolean fileCheck,
			int fileType, ServiceContext serviceContext) throws PortalException, SystemException {

		return DossierMarkLocalServiceUtil.addDossierMark(groupId, dossierId, dossierPartNo, fileCheck, fileType,
				serviceContext);
	}

	@Override
	public List<DossierMark> getDossierMarks(long groupId, long dossierId) {
		
		return DossierMarkLocalServiceUtil.getDossierMarks(groupId, dossierId);
	}

	@Override
	public DossierMark getDossierMarkbyDossierId(long groupId, long dossierId, String dossierPartNo) {

		return DossierMarkLocalServiceUtil.getDossierMarkbyDossierId(groupId, dossierId, dossierPartNo);
	}

}
