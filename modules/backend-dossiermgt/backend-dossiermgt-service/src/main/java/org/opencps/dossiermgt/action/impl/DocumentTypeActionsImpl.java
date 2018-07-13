package org.opencps.dossiermgt.action.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.DocumentTypeActions;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public class DocumentTypeActionsImpl implements DocumentTypeActions {

	private static Log _log = LogFactoryUtil.getLog(DocumentTypeActionsImpl.class);
	@Override
	public JSONObject getDocumentTypeList(long userId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
//		SearchContext searchContext = new SearchContext();

		try {
			
			List<DocumentType> docList = DocumentTypeLocalServiceUtil.getDocumentTypes(start, end);
			if (docList != null && docList.size() > 0) {
				_log.info("docList:"+docList);
			}
			result.put("data", docList);
			
			long total = DocumentTypeLocalServiceUtil.getDocumentTypesCount();
			_log.info("total:"+total);
			result.put("total", total);
			
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}
	@Override
	public DocumentType createDocType(long userId, long groupId, String typeCode, int templateClass,
			String documentName, String codePattern, String documentScript, int docSync,
			ServiceContext serviceContext) {
		return DocumentTypeLocalServiceUtil.insertDocType(userId, groupId, typeCode, templateClass, documentName,
				codePattern, documentScript, docSync, serviceContext);
	}
	@Override
	public DocumentType getByDocId(Long docId) {
		return DocumentTypeLocalServiceUtil.fetchDocumentType(docId);
	}
	@Override
	public DocumentType getByTypeCode(long groupId, String typeCode) {
		return DocumentTypeLocalServiceUtil.getByTypeCode(groupId, typeCode);
	}
	@Override
	public DocumentType removeDocType(Long docId) throws PortalException {
		return DocumentTypeLocalServiceUtil.deleteDocumentType(docId);
	}
	@Override
	public DocumentType updateDocType(Long docId, long userId, long groupId, String typeCode, int templateClass,
			String documentName, String codePattern, String documentScript, Integer docSync,
			ServiceContext serviceContext) {
		return DocumentTypeLocalServiceUtil.updateDocType(docId, userId, groupId, typeCode, templateClass, documentName,
				codePattern, documentScript, docSync, serviceContext);
	}
	@Override
	public DocumentType updateDocumentTypeDB(long userId, long groupId, String typeCode, Integer templateClass,
			String documentName, String codePattern, Integer docSync, String documentScript) {

		 return DocumentTypeLocalServiceUtil.updateDocumentTypeDB(userId, groupId, typeCode, templateClass, documentName,
				codePattern, docSync, documentScript);
	}

}
