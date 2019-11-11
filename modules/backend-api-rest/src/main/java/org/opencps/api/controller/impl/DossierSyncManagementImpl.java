package org.opencps.api.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.DossierSyncManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.v21.dossiersync.model.DossierSyncV21DataModel;
import org.opencps.api.v21.dossiersync.model.DossierSyncV21ResultsModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.DossierSyncActions;
import org.opencps.dossiermgt.action.impl.DossierSyncActionsImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.model.DossierSync;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class DossierSyncManagementImpl implements DossierSyncManagement {

	Log _log = LogFactoryUtil.getLog(DossierSyncManagementImpl.class.getName());

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierSyncsByApplicant(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String action, Integer start, Integer end) {
		BackendAuth auth = new BackendAuthImpl();
		DossierSyncActions actions = new DossierSyncActionsImpl();
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (start == null || start == 0) {
				start = -1;
				end = -1;
			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			JSONObject jsonData = actions.getDossierSyncByAction(groupId, action, start, end, serviceContext);
			DossierSyncV21ResultsModel results = new DossierSyncV21ResultsModel();
			
			results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
			if (jsonData != null && jsonData.getInt(ConstantUtils.TOTAL) > 0) {
				List<DossierSyncV21DataModel> lstDatas = new ArrayList<>();
				List<DossierSync> lstSyncs = (List<DossierSync>)jsonData.get(ConstantUtils.DATA);
				for (DossierSync ds : lstSyncs) {
					DossierSyncV21DataModel model = new DossierSyncV21DataModel();
					model.setActionCode(ds.getActionCode());
					model.setActionName(ds.getActionName());
					model.setActionNote(ds.getActionNote());
					model.setActionUser(ds.getActionUser());
					model.setCreateDate(ds.getCreateDate().getTime());
					model.setDossierId(ds.getDossierId());
					model.setDossierRefUid(ds.getDossierRefUid());
					model.setDossierSyncId(ds.getDossierSyncId());
					model.setInfoType(ds.getInfoType());
					model.setPayload(ds.getPayload());
					model.setSyncRefUid(ds.getSyncRefUid());
					model.setSyncType(ds.getSyncType());
					model.setUserId(ds.getUserId());
					
					lstDatas.add(model);
				}
				results.getData().addAll(lstDatas);
			}

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.error(e);
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION));
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION));

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION));
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION));

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_INTERNAL_SERVER));
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}	
	}

}
