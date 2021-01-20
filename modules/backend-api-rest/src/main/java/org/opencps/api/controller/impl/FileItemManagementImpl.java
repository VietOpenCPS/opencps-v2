package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.FileItemManagement;
import org.opencps.api.controller.util.FileItemUtils;
import org.opencps.api.fileitemmgt.model.FileItemResultsModel;
import org.opencps.api.fileitemmgt.model.FileItemSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.usermgt.action.FileItemActions;
import org.opencps.usermgt.action.impl.FileItemActionsImpl;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.model.FileItem;

import backend.auth.api.exception.BusinessExceptionImpl;

public class FileItemManagementImpl implements FileItemManagement {
	@Override
	public Response getFileItems(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, FileItemSearchModel query) {
		BackendAuth auth = new BackendAuthImpl();
		FileItemResultsModel results = new FileItemResultsModel();
		FileItemActions action = new FileItemActionsImpl();
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (query.getEnd() == null || query.getEnd() == 0) {

				query.setStart(QueryUtil.ALL_POS);

				query.setEnd(QueryUtil.ALL_POS);

			}
            // lấy danh sách fileItem mặc định là 0
			long groupId = ApplicantTerm.GROUP_ID_DEFAULT;
			int status = GetterUtil.getInteger(query.getStatus());
			
			List<FileItem> lstFileItems = action.getFileItems(groupId, status);
			
			results.setTotal(lstFileItems.size());
			
			results.getData().addAll(FileItemUtils.mappingToFileItemResults(lstFileItems));

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	
	}
}
