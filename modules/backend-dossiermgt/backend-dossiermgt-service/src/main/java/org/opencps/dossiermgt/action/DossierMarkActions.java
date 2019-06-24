package org.opencps.dossiermgt.action;

import java.util.List;

import org.opencps.dossiermgt.model.DossierMark;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DossierMarkActions {

	public DossierMark addDossierMark(long groupId, long dossierId, String dossierPartNo, Integer fileMark,
			Integer fileCheck, String fileComment, String recordCount, ServiceContext serviceContext)
			throws PortalException, SystemException;

	public List<DossierMark> getDossierMarks(long groupId, long dossierId);

	public DossierMark getDossierMarkbyDossierId(long groupId, long dossierId, String dossierPartNo);

}
