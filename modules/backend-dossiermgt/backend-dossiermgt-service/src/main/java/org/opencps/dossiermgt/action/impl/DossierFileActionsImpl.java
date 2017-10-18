package org.opencps.dossiermgt.action.impl;

import java.io.InputStream;

import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;

public class DossierFileActionsImpl implements DossierFileActions {

	@Override
	public DossierFile addDossierFile(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo, String displayName, String sourceFileName, long fileSize,
			InputStream inputStream, ServiceContext serviceContext) 
		throws SystemException, PortalException {
		
		return DossierFileLocalServiceUtil.addDossierFile(groupId, dossierId, referenceUid, dossierTemplateNo,
				dossierPartNo, fileTemplateNo, displayName, sourceFileName, fileSize, inputStream, serviceContext);
	}

	
}
