package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.opencps.api.constants.LGSPTerm;
import org.opencps.api.controller.LGSPIntegrationManagement;
import org.opencps.api.controller.util.PasswordEncrypt;

public class LGSPIntegrationManagementImpl implements LGSPIntegrationManagement {

	private static final Log _log = LogFactoryUtil.getLog(LGSPIntegrationManagementImpl.class);

	@Override
	public Response syncIdIGSP(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, int regionId) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		_log.info("call sync Id Lgsp with gr " + groupId + " " + regionId);
//		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONObject result = null;
		if (LGSPTerm.DICTCOLLECTION_REGION.equals(code)) {
			result = LGSPTerm.syncAllRegion();
		} else {
			result = LGSPTerm.getRegion(regionId);
		}

		return Response.status(HttpStatus.SC_OK).entity(result != null ? result.toJSONString() : StringPool.BLANK).build();
	}

	@Override
	public Response secrectKeyToMD5(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String secrectKey) {
		try {
			String encryptSecrect = PasswordEncrypt.encrypt(secrectKey);
			JSONObject jsonResult = JSONFactoryUtil.createJSONObject();
			jsonResult.put("secrectKey", encryptSecrect);
			return Response.status(HttpURLConnection.HTTP_OK).entity(jsonResult.toString()).build();
		} catch (Exception e) {
			_log.debug(e);
			return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
		}
	}

}
