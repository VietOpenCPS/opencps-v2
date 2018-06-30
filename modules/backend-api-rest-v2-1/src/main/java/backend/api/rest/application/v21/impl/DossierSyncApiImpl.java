package backend.api.rest.application.v21.impl;

import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.opencps.dossiermgt.action.DossierSyncActions;
import org.opencps.dossiermgt.action.impl.DossierSyncActionsImpl;
import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.model.DossierSync;

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

import backend.api.rest.application.v21.parser.DossierSyncParser;
import io.swagger.api.DossierSyncApi;
import io.swagger.model.DossierSyncResultModel;

public class DossierSyncApiImpl implements DossierSyncApi{

	@Context
	private User user;
	@Context
	private HttpHeaders header;
	@Context
	ServiceContext serviceContext;
	@Context
	HttpServletResponse respones;

	private static Log _log = LogFactoryUtil.getLog(DocumentTypeApiImpl.class);

	@Override
	public DossierSyncResultModel getDossierSyncList(String action, String top, Integer start, Integer end) {
		//TODO
//		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("groupId: "+groupId);
		DossierSyncResultModel results = null;
		try {
			
			// Check user is login
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			_log.info("groupId: "+groupId);
			if (end == 0) {
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
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			_log.info("top: "+top);
			int syncType = 0;
			if (Validator.isNotNull(top)) {
				if (top.toLowerCase().equals("request")) {
					syncType = 1;
				} else if (top.toLowerCase().equals("inform")) {
					syncType = 2;
				}
			}
			_log.info("groupId: "+groupId);
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			_log.info("groupId: "+groupId);
			params.put(DossierSyncTerm.ACTION_CODE, action);
			params.put(DossierSyncTerm.SYNC_TYPE, syncType);
			_log.info("syncType: "+syncType);
			params.put(DossierSyncTerm.USER_ID, user.getUserId());
			_log.info("USER_ID: "+user.getUserId());
			
			DossierSyncActions actions = new DossierSyncActionsImpl();
			results = new DossierSyncResultModel();
			
			// get JSON data deliverable
			_log.info("groupId: "+groupId);
//			_log.info("serviceContext: "+serviceContext.getCompanyId());
			JSONObject jsonData = actions.getDossierSyncList(user.getUserId(), action, syncType, sorts, start, end,
					serviceContext);

			int total = jsonData.getInt("total");
			results.setTotal(total);
			if (jsonData != null && total > 0) {
				results.setData(DossierSyncParser.mappingDossierSyncResultModel((List<DossierSync>) jsonData.get("data")));
			}

		} catch (Exception e) {
			_log.info(e);
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		return results;
	}

	@Override
	public DossierSyncResultModel getSyncByDossierId(Integer model, String id, Integer start, Integer end) {
		//TODO
//		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("groupId: "+groupId);
		long dossierId = GetterUtil.getLong(id);
		DossierSyncResultModel results = null;
		try {
			
			// Check user is login
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			_log.info("groupId: "+groupId);
			if (end == 0) {
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
			_log.info("model: "+model);
//			int actionCodeNo = 0;
//			if (Validator.isNotNull(model)) {
//				if (model == 0) {
//					actionCodeNo = 1;
//				} else {
//					syncType = 2;
//				}
//			}
			_log.info("groupId: "+groupId);
//			params.put(Field.GROUP_ID, String.valueOf(groupId));
			_log.info("groupId: "+groupId);
//			params.put(DossierSyncTerm.ACTION_CODE, action);
//			params.put(DossierSyncTerm.SYNC_TYPE, syncType);
//			_log.info("syncType: "+syncType);
//			params.put(DossierSyncTerm.USER_ID, user.getUserId());
			_log.info("USER_ID: "+user.getUserId());
			
			DossierSyncActions actions = new DossierSyncActionsImpl();
			results = new DossierSyncResultModel();
			
			// get JSON data deliverable
			_log.info("groupId: "+groupId);
//			_log.info("serviceContext: "+serviceContext.getCompanyId());
			JSONObject jsonData = actions.getDossierSyncById(user.getUserId(), dossierId, model, 8000, sorts, start, end,
					serviceContext);
			_log.info("groupId: "+groupId);
			int total = jsonData.getInt("total");
			results.setTotal(total);
			if (jsonData != null && total > 0) {
				results.setData(DossierSyncParser.mappingDossierSyncResultModel((List<DossierSync>) jsonData.get("data")));
			}
			_log.info("groupId: "+groupId);

		} catch (Exception e) {
			_log.info(e);
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		return results;
	}

}
