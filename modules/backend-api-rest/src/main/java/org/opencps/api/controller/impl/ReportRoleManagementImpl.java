package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.adminconfig.model.DynamicReport;
import org.opencps.adminconfig.model.ReportRole;
import org.opencps.adminconfig.service.DynamicReportLocalServiceUtil;
import org.opencps.adminconfig.service.ReportRoleLocalServiceUtil;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.ReportRoleManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.controller.util.ReportRoleUtils;
import org.opencps.api.reportrole.model.ReportRoleInputCodeModel;
import org.opencps.api.reportrole.model.ReportRoleModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class ReportRoleManagementImpl implements ReportRoleManagement {
	private static Log _log =
			LogFactoryUtil.getLog(ReportRoleManagementImpl.class);
	
	@Override
	public Response addReportRoleCode(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext,
			ReportRoleInputCodeModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			DynamicReport dr = DynamicReportLocalServiceUtil.fetchByCode(groupId, input.getReportCode());
			JobPos jp = JobPosLocalServiceUtil.getByJobCode(groupId, input.getRoleCode());
			if (dr != null && jp != null) {
				long dynamicReportId = dr.getDynamicReportId();
				long roleId = jp.getMappingRoleId();	
				ReportRole rr = ReportRoleLocalServiceUtil.addReportRole(dynamicReportId, roleId);
				ReportRoleModel model = ReportRoleUtils.mappingReportRole(rr);
				return Response.status(HttpURLConnection.HTTP_OK).entity(model).build();
			}
			else {
				return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_NOTFOUND)).build();
			}
		} catch (Exception e) {
			_log.debug(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

}
