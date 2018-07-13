package org.opencps.dossiermgt.action;

import java.util.LinkedHashMap;

import org.opencps.dossiermgt.model.DocumentType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DocumentTypeActions {

	public JSONObject getDocumentTypeList(long userId, LinkedHashMap<String, Object> params, Sort[] sorts,
			int start, int end, ServiceContext serviceContext);

	public DocumentType createDocType(long userId, long groupId, String typeCode, int templateClass, String documentName,
			String codePattern, String documentScript, int docSync, ServiceContext serviceContext);

	public DocumentType getByDocId(Long docId);

	public DocumentType getByTypeCode(long groupId, String id);

	public DocumentType removeDocType(Long docId) throws PortalException;

	public DocumentType updateDocType(Long docId, long userId, long groupId, String typeCode, int templateClass,
			String documentName, String codePattern, String documentScript, Integer docSync,
			ServiceContext serviceContext);

	public DocumentType updateDocumentTypeDB(long userId, long groupId, String typeCode, Integer templateClass, String documentName,
			String codePattern, Integer docSync, String documentScript);

}
