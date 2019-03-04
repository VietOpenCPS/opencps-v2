package org.opencps.dossiermgt.action.impl;

import java.io.InputStream;
import java.util.List;

import org.opencps.dossiermgt.action.DossierDocumentActions;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.service.DossierDocumentLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

public class DossierDocumentActionsImpl implements DossierDocumentActions {

	private static Log _log = LogFactoryUtil.getLog(DossierDocumentActionsImpl.class);
	@Override
	public JSONObject getDossierDocumentByDossierId(Long dossierId, Integer start, Integer end) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
//		SearchContext searchContext = new SearchContext();

		try {
			List<DossierDocument> docList =DossierDocumentLocalServiceUtil.getDossierDocumentList(dossierId, start, end);
			if (docList != null && docList.size() > 0) {
				_log.debug("docList:"+docList);
			}
			result.put("data", docList);
			
			long total = DossierDocumentLocalServiceUtil.countDossierDocumentList(dossierId);
			_log.debug("total:"+total);
			result.put("total", total);
//			
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}
	@Override
	public DossierDocument addDossierDoc(long groupId, Long dossierId, long dossierActionId, String documentType,
			String documentName, String documentCode, String sourceFileName, long fileSize, InputStream inputStream, String fileType,
			ServiceContext serviceContext) {
		return DossierDocumentLocalServiceUtil.addDossierDoc(groupId, dossierId, dossierActionId, documentType,
				documentName, documentCode, sourceFileName, fileSize, inputStream, fileType, serviceContext);
	}
	@Override
	public DossierDocument addDossierDoc(long groupId, Long dossierId, String referenceUid, long dossierActionId,
			String documentType, String documentName, String documentCode, String sourceFileName, long fileSize,
			InputStream inputStream, String fileType, ServiceContext serviceContext) {
		return DossierDocumentLocalServiceUtil.addDossierDoc(groupId, dossierId, referenceUid, dossierActionId, documentType,
				documentName, documentCode, sourceFileName, fileSize, inputStream, fileType, serviceContext);
	}

}
