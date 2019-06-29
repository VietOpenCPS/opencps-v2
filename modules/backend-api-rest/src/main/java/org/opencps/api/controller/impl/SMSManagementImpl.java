
package org.opencps.api.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.SMSManagement;
import org.opencps.api.sms.model.IPacificInputSMS;
import org.opencps.communication.constants.SendSMSTerm;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.communication.sms.utils.ViettelSMSUtils;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.usermgt.action.UserInterface;
import org.opencps.usermgt.action.impl.UserActions;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.BusinessExceptionImpl;
import ws.bulkSms.impl.Result;

public class SMSManagementImpl implements SMSManagement {

	@Override
	public Response sendSMS(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String body,
		String toTelNo) {

		try {
			long groupId =
				GetterUtil.getLong(header.getHeaderString("groupId"));
			Result result = ViettelSMSUtils.sendSMS(
				groupId, body, StringPool.BLANK, toTelNo);

			return Response.status(200).entity(result.getMessage()).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getInetSMS(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		IPacificInputSMS input, String toTelNo) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		if (Validator.isNull(toTelNo) && groupId <= 0) {

			_log.info("========NULL==============" + toTelNo + groupId);
			return Response.status(200).entity(StringPool.BLANK).build();
		}

		return Response.status(200).entity(_getZaloUid(groupId, toTelNo, serviceContext)).build();
	}

	@Override
	public Response postInetSMS(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		IPacificInputSMS input) {

		return Response.status(200).entity(StringPool.BLANK).build();
	}

	@Override
	public Response getZaloUIdByTelNo(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String toTelNo) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		UserInterface actions = new UserActions();

		if (Validator.isNull(toTelNo) && groupId <= 0) {

			return Response.status(200).entity(StringPool.BLANK).build();
		}

		try {

			String zUId = StringPool.BLANK;
			Applicant applicant =
				ApplicantLocalServiceUtil.fetchBy_GTelNo(groupId, toTelNo);

			if (Validator.isNotNull(applicant) &&
				Validator.isNotNull(applicant.getMappingUserId())) {

				String preferences = actions.getPreferenceByKey(
					applicant.getMappingUserId(), groupId, SendSMSTerm.ZALO_UID,
					serviceContext);

				JSONObject zUIdJSON =
					JSONFactoryUtil.createJSONObject(preferences);

				_log.info(zUIdJSON);

				zUId = zUIdJSON.getString(SendSMSTerm.UID);
			}

			return Response.status(200).entity(zUId).build();

		}
		catch (Exception e) {

			return Response.status(200).entity(StringPool.BLANK).build();
		}

	}

	private String _getZaloUid(
		long groupId, String toTelNo, ServiceContext serviceContext) {

		try {
			_log.info(
				"======================" +
					SendSMSTerm.SERVER_CONFIG_PROTOCOL_ZALO_URLS);
			List<ServerConfig> lstScs =
				ServerConfigLocalServiceUtil.getByProtocol(
					groupId, SendSMSTerm.SERVER_CONFIG_PROTOCOL_ZALO_URLS);
			ServerConfig sc = lstScs.get(0);
			JSONObject zaloConfig =
				JSONFactoryUtil.createJSONObject(sc.getConfigs());

			JSONArray zaloURLs =
				zaloConfig.getJSONArray(SendSMSTerm.ZALO_URLS_URLS);

			_log.info(zaloURLs);

			for (int i = 0; i < zaloURLs.length(); i++) {

				JSONObject zaloURL = zaloURLs.getJSONObject(i);

				InvokeREST rest = new InvokeREST();

				HashMap<String, String> properties =
					new HashMap<String, String>();

				// Call initDossier to SERVER

				String endPoint =
					zaloURL.getString(SendSMSTerm.ZALO_URLS_END_POINT) +
						"?toTelNo=" + toTelNo;

				// khong tac dung
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("toTelNo", toTelNo);

				JSONObject resPostDossier = rest.callAPI(
					zaloURL.getLong(SendSMSTerm.ZALO_URLS_GROUP_ID),
					HttpMethods.GET, MediaType.APPLICATION_JSON,
					zaloURL.getString(SendSMSTerm.ZALO_URLS_PATH_BASE),
					endPoint,
					zaloURL.getString(SendSMSTerm.ZALO_URLS_USER_NAME),
					zaloURL.getString(SendSMSTerm.ZALO_URLS_PASS_WORD),
					properties, serviceContext);
				_log.info(resPostDossier);
				String uid = resPostDossier.getString("message");

				if (Validator.isNotNull(uid)) {

					return uid;
				}

			}
		}
		catch (Exception e) {

			_log.info(e);
		}
		return StringPool.BLANK;
	}

	Log _log = LogFactoryUtil.getLog(SMSManagementImpl.class.getName());

}
