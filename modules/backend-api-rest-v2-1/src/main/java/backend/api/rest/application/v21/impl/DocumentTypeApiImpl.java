package backend.api.rest.application.v21.impl;

import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.dossiermgt.action.DocumentTypeActions;
import org.opencps.dossiermgt.action.impl.DocumentTypeActionsImpl;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.DocumentType;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import backend.api.rest.application.v21.parser.DocumentTypeParser;
import io.swagger.api.DocumentTypesApi;
import io.swagger.model.DocumentTypeInputModel;
import io.swagger.model.DocumentTypeModel;
import io.swagger.model.DocumentTypeResultModel;


public class DocumentTypeApiImpl implements DocumentTypesApi{

	@Context
	private User user;
	@Context
	private HttpHeaders header;
	@Context
	ServiceContext serviceContext;
	@Context
	HttpServletResponse respones;

	private static Log _log = LogFactoryUtil.getLog(DocumentTypeApiImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public DocumentTypeResultModel getAllDocumentTypes(String keyword, Integer start, Integer end) {
		//TODO
//		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("groupId: "+groupId);
		DocumentTypeResultModel results = null;
		try {
			
			// Check user is login
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			_log.info("groupId: "+groupId);
			if (Validator.isNull(end) || end == 0) {
				start = -1;
				end = -1;
			}
			_log.info("end: "+end);
			// Default sort by modifiedDate
			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(Field.MODIFIED_DATE + "_sortable", Sort.STRING_TYPE, true) };
			
//			if (Validator.isNotNull(search.getSort()) && Validator.isNotNull(search.getOrder())) {
//				sorts = new Sort[] { SortFactoryUtil.create(search.getSort() + "_sortable", Sort.STRING_TYPE,
//						GetterUtil.getBoolean(search.getOrder())) };
//			}
			_log.info("groupId: "+groupId);
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			_log.info("groupId: "+groupId);
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			_log.info("groupId: "+groupId);
//			params.put(Field.KEYWORD_SEARCH, search.getKeyword());
			params.put(Field.KEYWORD_SEARCH, keyword);
			_log.info("groupId: "+groupId);
			params.put(DossierTerm.USER_ID, user.getUserId());
			_log.info("groupId: "+groupId);
			
			DocumentTypeActions actions = new DocumentTypeActionsImpl();
			results = new DocumentTypeResultModel();
			
			// get JSON data deliverable
			_log.info("groupId: "+groupId);
//			_log.info("serviceContext: "+serviceContext.getCompanyId());
			JSONObject jsonData = actions.getDocumentTypeList(user.getUserId(), params, sorts, start, end,
					serviceContext);
			_log.info("groupId: "+groupId);
			int total = jsonData.getInt("total");
			results.setTotal(total);
			if (jsonData != null && total > 0) {
				results.setData(DocumentTypeParser.mappingDocumentResultModel((List<DocumentType>) jsonData.get("data")));
			}
			_log.info("groupId: "+groupId);

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		return results;
	}

	@Override
	public DocumentTypeModel createDocumentType(DocumentTypeInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("groupId_"+groupId);
		long userId = user.getUserId();
		_log.info("groupId_"+groupId);
//		BackendAuth auth = new BackendAuthImpl();
		DocumentTypeModel result = null;

		try {
			/* Check user is login - START */
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

			_log.info("groupId_"+groupId);
//			model.setTypeCode(StringEscapeUtils.escapeHtml4(model.getTypeCode()));
//			model.setTemplateClass(Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getTemplateClass()))));
//			model.setDocumentName(StringEscapeUtils.escapeHtml4(model.getDocumentName()));
//			model.setCodePattern(StringEscapeUtils.escapeHtml4(model.getCodePattern()));
//			model.setDocumentScript(StringEscapeUtils.escapeHtml4(model.getDocumentScript()));
//			model.setDocSync(Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getDocSync()))));
			
			String typeCode = StringEscapeUtils.escapeHtml4(input.getTypeCode());
			int templateClass = input.getTemplateClass();
			String documentName = StringEscapeUtils.escapeHtml4(input.getDocumentName());
			String codePattern = StringEscapeUtils.escapeHtml4(input.getCodePattern());
			String documentScript = StringEscapeUtils.escapeHtml4(input.getDocumentScript());
			int docSync = input.getDocSync();
			_log.info("groupId_"+groupId);

			/* Check user is login - END */
			DocumentTypeActions actions = new DocumentTypeActionsImpl();
			_log.info("groupId_"+groupId);

			DocumentType docType = actions.createDocType(userId, groupId, typeCode, templateClass, documentName,
					codePattern, documentScript, docSync, serviceContext);
			_log.info("groupId_"+groupId);

			if (docType != null) {
				result = DocumentTypeParser.mappingDocumentTypeModel(docType);
			}
			_log.info("groupId_"+groupId);

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}

		return result;
	}

	@Override
	public DocumentTypeModel getDocById(String id) {
//		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("groupId: "+groupId);
		Long docId = GetterUtil.getLong(id);
		_log.info("docId: "+docId);
		DocumentTypeModel result = null;
		try {
			// Check user is login
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			_log.info("groupId: "+groupId);
			// Default sort by modifiedDate
			DocumentTypeActions actions = new DocumentTypeActionsImpl();
			DocumentType docType = null;

			if (docId > 0) {
				docType = actions.getByDocId(docId);
			} else {
				docType = actions.getByTypeCode(groupId, id);
			}
			if (docType != null) {
				result = DocumentTypeParser.mappingDocumentTypeModel(docType);
			}
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		return result;
	}

	@Override
	public DocumentTypeModel removeDocById(String id) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("groupId_"+groupId);
		Long docId = GetterUtil.getLong(id);
		_log.info("docId: "+docId);
//		BackendAuth auth = new BackendAuthImpl();
		DocumentTypeModel result = null;

		try {
			/* Check user is login - START */
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

			/* Check user is login - END */
			DocumentTypeActions actions = new DocumentTypeActionsImpl();
			_log.info("groupId_"+groupId);

			DocumentType docType = actions.removeDocType(docId);
			_log.info("groupId_"+groupId);

			if (docType != null) {
				result = DocumentTypeParser.mappingDocumentTypeModel(docType);
			}
			_log.info("groupId_"+groupId);

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}

		return result;
	}

	@Override
	public DocumentTypeModel updateDocById(String id, DocumentTypeInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("groupId_"+groupId);
		long userId = user.getUserId();
		_log.info("groupId_"+groupId);
		Long docId = GetterUtil.getLong(id);
//		BackendAuth auth = new BackendAuthImpl();
		DocumentTypeModel result = null;

		try {
			/* Check user is login - START */
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

			_log.info("groupId_"+groupId);
			String typeCode = StringEscapeUtils.escapeHtml4(input.getTypeCode());
			int templateClass = input.getTemplateClass();
			String documentName = StringEscapeUtils.escapeHtml4(input.getDocumentName());
			String codePattern = StringEscapeUtils.escapeHtml4(input.getCodePattern());
			String documentScript = StringEscapeUtils.escapeHtml4(input.getDocumentScript());
			int docSync = input.getDocSync();
			_log.info("groupId_"+groupId);

			/* Check user is login - END */
			DocumentTypeActions actions = new DocumentTypeActionsImpl();
			_log.info("groupId_"+groupId);

			DocumentType docType = actions.updateDocType(docId, userId, groupId, typeCode, templateClass, documentName,
					codePattern, documentScript, docSync, serviceContext);
			_log.info("groupId_"+groupId);

			if (docType != null) {
				result = DocumentTypeParser.mappingDocumentTypeModel(docType);
			}
			_log.info("groupId_"+groupId);

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}

		return result;
	}

}
