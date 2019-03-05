package backend.api.rest.application.v21.impl;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.opencps.dossiermgt.action.DocumentTypeActions;
import org.opencps.dossiermgt.action.impl.DocumentTypeActionsImpl;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.rest.application.api.DocumentTypesApi;
import org.opencps.rest.application.model.DocumentTypeInputModel;
import org.opencps.rest.application.model.DocumentTypeModel;
import org.opencps.rest.application.model.DocumentTypeResultModel;

import backend.api.rest.application.v21.parser.DocumentTypeParser;

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
		_log.info("==== START GET DOCUMENT TYPE==== ");
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		DocumentTypeResultModel results = null;
		try {
			
			// Check user is login
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			if (Validator.isNull(end) || end == 0) {
				start = -1;
				end = -1;
			}
			// Default sort by modifiedDate
			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(Field.MODIFIED_DATE + "_sortable", Sort.STRING_TYPE, true) };
			
//			if (Validator.isNotNull(search.getSort()) && Validator.isNotNull(search.getOrder())) {
//				sorts = new Sort[] { SortFactoryUtil.create(search.getSort() + "_sortable", Sort.STRING_TYPE,
//						GetterUtil.getBoolean(search.getOrder())) };
//			}
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, keyword);
			params.put(DossierTerm.USER_ID, user.getUserId());

			DocumentTypeActions actions = new DocumentTypeActionsImpl();
			results = new DocumentTypeResultModel();
			
			// get JSON data deliverable
			JSONObject jsonData = actions.getDocumentTypeList(user.getUserId(), params, sorts, start, end,
					serviceContext);
			int total = jsonData.getInt("total");
			results.setTotal(total);
			if (jsonData != null && total > 0) {
				results.setData(DocumentTypeParser.mappingDocumentResultModel((List<DocumentType>) jsonData.get("data")));
			}
			_log.info("==== END GET DOCUMENT TYPE==== ");

		} catch (Exception e) {
			_log.debug(e);
			_log.info("====EXCEPTION - ERROR==== ");
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		return results;
	}

	@Override
	public DocumentTypeModel createDocumentType(DocumentTypeInputModel input) {
		_log.info("==== START CREATE DOCUMENT TYPE==== ");
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();
		DocumentTypeModel result = null;

		try {
			/* Check user is login - START */
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

//			model.setTypeCode(HtmlUtil.escape(model.getTypeCode()));
//			model.setTemplateClass(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getTemplateClass()))));
//			model.setDocumentName(HtmlUtil.escape(model.getDocumentName()));
//			model.setCodePattern(HtmlUtil.escape(model.getCodePattern()));
//			model.setDocumentScript(HtmlUtil.escape(model.getDocumentScript()));
//			model.setDocSync(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getDocSync()))));
			
			String typeCode = HtmlUtil.escape(input.getTypeCode());
			int templateClass = input.getTemplateClass();
			String documentName = HtmlUtil.escape(input.getDocumentName());
			String codePattern = HtmlUtil.escape(input.getCodePattern());
			String documentScript = HtmlUtil.escape(input.getDocumentScript());
			int docSync = input.getDocSync();

			/* Check user is login - END */
			DocumentTypeActions actions = new DocumentTypeActionsImpl();

			DocumentType docType = actions.createDocType(userId, groupId, typeCode, templateClass, documentName,
					codePattern, documentScript, docSync, serviceContext);

			if (docType != null) {
				result = DocumentTypeParser.mappingDocumentTypeModel(docType);
			}
			_log.info("==== END CREATE DOCUMENT TYPE==== ");

		} catch (Exception e) {
			_log.debug(e);
			_log.info("==== CREATE ERROR==== ");
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}

		return result;
	}

	@Override
	public DocumentTypeModel getDocById(String id) {
		_log.info("==== START GET DOCUMENT TYPE BY ID==== ");
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		Long docId = GetterUtil.getLong(id);
		DocumentTypeModel result = null;
		try {
			// Check user is login
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
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
			_log.info("==== END GET DOCUMENT TYPE BY ID==== ");
		} catch (Exception e) {
			_log.debug(e);
			_log.info("====GET DOCUMENT ERROR==== ");
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		return result;
	}

	@Override
	public DocumentTypeModel removeDocById(String id) {
		_log.info("==== START REMOVE DOCUMENT BY ID==== ");
		//long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		Long docId = GetterUtil.getLong(id);
//		BackendAuth auth = new BackendAuthImpl();
		DocumentTypeModel result = null;

		try {
			/* Check user is login - START */
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

			/* Check user is login - END */
			DocumentTypeActions actions = new DocumentTypeActionsImpl();

			DocumentType docType = actions.removeDocType(docId);

			if (docType != null) {
				result = DocumentTypeParser.mappingDocumentTypeModel(docType);
			}
			_log.info("==== END REMOVE DOCUMENT BY ID==== ");

		} catch (Exception e) {
			_log.debug(e);
			_log.info("====REMOVE DOCUMENT ERROR==== ");
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}

		return result;
	}

	@Override
	public DocumentTypeModel updateDocById(String id, DocumentTypeInputModel input) {
		_log.info("==== START UPDATE DOCUMENT BY ID==== ");
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();
		Long docId = GetterUtil.getLong(id);
//		BackendAuth auth = new BackendAuthImpl();
		DocumentTypeModel result = null;

		try {
			/* Check user is login - START */
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

			String typeCode = HtmlUtil.escape(input.getTypeCode());
			int templateClass = input.getTemplateClass();
			String documentName = HtmlUtil.escape(input.getDocumentName());
			String codePattern = HtmlUtil.escape(input.getCodePattern());
			String documentScript = HtmlUtil.escape(input.getDocumentScript());
			int docSync = input.getDocSync();

			/* Check user is login - END */
			DocumentTypeActions actions = new DocumentTypeActionsImpl();

			DocumentType docType = actions.updateDocType(docId, userId, groupId, typeCode, templateClass, documentName,
					codePattern, documentScript, docSync, serviceContext);

			if (docType != null) {
				result = DocumentTypeParser.mappingDocumentTypeModel(docType);
			}
			_log.info("==== END UPDATE DOCUMENT BY ID==== ");

		} catch (Exception e) {
			_log.debug(e);
			_log.info("==== UPDATE DOCUMENT ERROR==== ");
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}

		return result;
	}

}
