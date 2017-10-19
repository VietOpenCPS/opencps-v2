package org.opencps.dossiermgt.action;

import java.io.InputStream;

import org.opencps.dossiermgt.model.DossierFile;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DossierFileActions {

	public DossierFile addDossierFile(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo, String displayName, String sourceFileName, long fileSize,
			InputStream inputStream, ServiceContext serviceContext)
		throws SystemException, PortalException ;

}
