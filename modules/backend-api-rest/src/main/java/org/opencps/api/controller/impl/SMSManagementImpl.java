
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
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.SMSManagement;
import org.opencps.api.sms.model.IPacificSearchSMS;
import org.opencps.communication.constants.SendSMSTerm;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.communication.sms.utils.ViettelSMSUtils;
import org.opencps.datamgt.util.DueDateUtils;
import org.opencps.dossiermgt.action.util.AccentUtils;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

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
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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
		IPacificSearchSMS input) {

		return Response.status(200).entity(_buiderResponseSMS(input)).build();
	}

	@Override
	public Response getZaloUIdByTelNo(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String toTelNo) {

		try {
			JSONObject zaloConfig = JSONFactoryUtil.createJSONObject();
			JSONObject zaloAccessToken = JSONFactoryUtil
					.createJSONObject(zaloConfig.getString(SendSMSTerm.OAID_TOKEN_ACCESS));
			String uid = ReadFilePropertiesUtils.get(ConstantUtils.MSG_ERROR);
			if (zaloAccessToken.has(ConstantUtils.DATA)) {

				uid = zaloAccessToken.getJSONObject(ConstantUtils.DATA).getString(ConstantUtils.VALUE_LOW_USER_ID);
			}
			return Response.status(200).entity(uid).build();
		}
		catch (Exception e) {
			_log.debug(e);
			return Response.status(500).entity(StringPool.BLANK).build();
		}

	}

	private String _buiderResponseSMS(IPacificSearchSMS iPacific) {

		String result = StringPool.BLANK;
		JSONObject epacifConfig = JSONFactoryUtil.createJSONObject();
		try {

			ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(
				SendSMSTerm.SERVER_CONFIG_SERVERNO_EPACIFIC);
			epacifConfig = JSONFactoryUtil.createJSONObject(sc.getConfigs());
			Dossier dossier = null;

			if (epacifConfig.has(SendSMSTerm.EPACIFIC_GROUPID) &&
				epacifConfig.has(SendSMSTerm.EPACIFIC_MINE) &&
				epacifConfig.has(SendSMSTerm.EPACIFIC_USER) &&
				epacifConfig.has(SendSMSTerm.EPACIFIC_PASSWORD)) {

				String[] strArr = iPacific.getInfo().split(" ");
				String dossierNo = strArr[1];
				String secretCode = strArr[2];

				dossier = DossierLocalServiceUtil.getByDossierNo(
					epacifConfig.getLong(SendSMSTerm.EPACIFIC_GROUPID),
					dossierNo);

				if (!epacifConfig.getString(
					SendSMSTerm.EPACIFIC_MINE).equalsIgnoreCase(
						iPacific.getCommandCode())) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_MINE_ERROR_MES);

				}
				else if (!epacifConfig.getString(
					SendSMSTerm.EPACIFIC_USER).equalsIgnoreCase(
						iPacific.getUser())) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_USER_ERROR_MES);

				}
				else if (!epacifConfig.getString(
					SendSMSTerm.EPACIFIC_PASSWORD).equalsIgnoreCase(
						iPacific.getPassword())) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_PASSWORD_ERROR_MES);

				}
				else if (Validator.isNull(dossier)) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_D_NOT_FOUND_MES);

				}
				else if (!dossier.getPassword().equalsIgnoreCase(secretCode)) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_D_PASSWORD_ERROR_MES);

				}
				else {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_SUCCESS_MES);
					result = result.replaceAll(
						epacifConfig.getString(
							SendSMSTerm.EPACIFIC_DOSSIER_NO_REPLACE),
						dossierNo);
					result = result.replaceAll(
						epacifConfig.getString(
							SendSMSTerm.EPACIFIC_DOSSIER_STATUS_REPLACE),
						AccentUtils.removeAccent(dossier.getDossierStatusText()));
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			_log.debug(e);
			result =
				epacifConfig.getString(SendSMSTerm.EPACIFIC_SYNTAX_ERROR_MES);
		}
		catch (Exception e) {
			_log.debug(e);
		}

		_log.info(result);
		return result;
	}

	@Override
	public Response calculateDueDate(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String startDate, double durationCount, int durationUnit,
		long groupId) {

		try {

			Date startDateS =
				new SimpleDateFormat(ConstantUtils._NORMAL_DATE_TIME).parse(startDate);
			String dueDate2 =
				new SimpleDateFormat(ConstantUtils._NORMAL_DATE_TIME).format(startDateS);
			_log.info(startDateS);
			_log.info(dueDate2);
			DueDateUtils dueDateUtils = new DueDateUtils(
				startDateS, durationCount, durationUnit, groupId);
			String dueDate = new SimpleDateFormat(ConstantUtils._NORMAL_DATE_TIME).format(
				dueDateUtils.getDueDate());
			return Response.status(200).entity(dueDate).build();
		}
		catch (Exception e) {
			_log.debug(e);
		}

		return Response.status(500).entity(StringPool.BLANK).build();
	}

	@Override
	public Response postInvoiceTest(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		long dossierId) {

		try {
			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			System.out.println(
				"================groupId====================" + groupId +
					" ===" + dossierId);
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

			return Response.status(200).entity(dossier).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			return BusinessExceptionImpl.processException(e);
		}
	}

	static Log _log = LogFactoryUtil.getLog(SMSManagementImpl.class.getName());
}

class RESTFulConfiguration {

	public static final String STATUS = "status";
	public static final String MESSAGE = "message";

	public static final String SUBMIT = "submit";
	public static final String TIMER = "timer";

	public static final int TIME_OUT = 3000;

}
