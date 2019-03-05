package backend.api.rest.application.v21.impl;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.opencps.dossiermgt.action.DossierSyncActions;
import org.opencps.dossiermgt.action.impl.DossierSyncActionsImpl;
import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.rest.application.api.DossierSyncApi;
import org.opencps.rest.application.model.DossierSyncResultModel;

import backend.api.rest.application.v21.parser.DossierSyncParser;

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

	@SuppressWarnings("unchecked")
	@Override
	public DossierSyncResultModel getDossierSyncList(String action, String top, Integer start, Integer end) {
		_log.info("====START GET LIST DOSSIER SYNC==== ");
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		DossierSyncResultModel results = null;
		try {
			
			// Check user is login
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			if (Validator.isNull(end) || end == 0) {
				start = -1;
				end = -1;
			}
			
			DossierSyncActions actions = new DossierSyncActionsImpl();
			results = new DossierSyncResultModel();
			
			// get JSON data deliverable
//			_log.info("serviceContext: "+serviceContext.getCompanyId());
			JSONObject jsonData = actions.getDossierSyncByAction(groupId, action, start, end, serviceContext);

			int total = jsonData.getInt("total");
			results.setTotal(total);
			if (jsonData != null && total > 0) {
				results.setData(DossierSyncParser.mappingDossierSyncResultModel((List<DossierSync>) jsonData.get("data")));
			}
			_log.info("====END GET LIST DOSSIER SYNC==== ");
		} catch (Exception e) {
			_log.debug(e);
			_log.error("====DOSSIER SYNC ERROR==== ");
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DossierSyncResultModel getSyncByDossierId(Integer info, String id, Integer start, Integer end) {
		_log.info("====START GET DOSSIER SYNC BY ID==== ");
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		DossierSyncResultModel results = null;
		try {
			
			if (end == null || end == 0) {
				start = -1;
				end = -1;
			}
			
			DossierSyncActions actions = new DossierSyncActionsImpl();
			results = new DossierSyncResultModel();
			
			JSONObject jsonData = actions.getDossierSyncByDossierAndInfo(groupId, id, info, start, end, serviceContext);
			
			int total = jsonData.getInt("total");
			results.setTotal(total);
			results.setData(new ArrayList<>());
			
			if (jsonData != null && total > 0) {
				results.setData(DossierSyncParser.mappingDossierSyncResultModel((List<DossierSync>) jsonData.get("data")));
			}
			_log.info("====END GET DOSSIER SYNC BY ID==== ");
		} catch (Exception e) {
			_log.debug(e);
			_log.error("====DOSSIER SYNC ERROR==== ");
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		
		return results;
	}

}
