package org.opencps.dossiermgt.action;

import java.io.InputStream;

import org.opencps.dossiermgt.model.DossierDocument;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DossierDocumentActions {

	public JSONObject getDossierDocumentByDossierId(Long dossierId, Integer start, Integer end);

	public DossierDocument addDossierDoc(long groupId, Long dossierId, long dossierActionId, String documentType,
			String documentName, String documentCode, String sourceFileName, long fileSize, InputStream inputStream,
			String fileType, ServiceContext serviceContext);

	public DossierDocument addDossierDoc(long groupId, Long dossierId, String referenceUid, long dossierActionId, String documentType,
			String documentName, String documentCode, String sourceFileName, long fileSize, InputStream inputStream,
			String fileType, ServiceContext serviceContext);
}
