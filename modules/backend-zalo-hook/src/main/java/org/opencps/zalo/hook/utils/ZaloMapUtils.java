/**
 * 
 */

package org.opencps.zalo.hook.utils;

import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.opencps.communication.constants.SendSMSTerm;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.model.ZaloMap;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.communication.service.ZaloMapLocalServiceUtil;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.zalo.hook.constants.ZaloHookConstantKeys;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author thanhnv
 */
public class ZaloMapUtils {

	/**
	 * Class to CRUD ZaloMap
	 */

	Map<String, Object> zaloInfo = new HashMap<String, Object>();

	ZaloMap zaloMap = null;

	public ZaloMapUtils(Map<String, Object> zaloInfo) {
		super();
		this.zaloInfo = zaloInfo;
	}

	public JSONObject unfollow() {

		try {

			String uId =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_FROM_UID);
			String oAId =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_OAID);

			zaloMap = ZaloMapLocalServiceUtil.getByUId(uId);

			if (Validator.isNull(zaloMap)) {

				String telNoIfPublic = (String) zaloInfo.get(
					ZaloHookConstantKeys.ZALO_PARAM_PHONE);

				zaloMap = ZaloMapLocalServiceUtil.updateZaloMap(
					0, 0, uId, telNoIfPublic, oAId, 0, StringPool.BLANK);
			}
			else {

				zaloMap = ZaloMapLocalServiceUtil.updateZaloMap(
					zaloMap.getZaloMapId(), zaloMap.getGroupId(), uId,
					zaloMap.getTelNo(), oAId, 0, zaloMap.getPayload());
			}

		}
		catch (PortalException e) {

			_log.error(e);
		}

		return _buildResponse(zaloMap);
	}

	public JSONObject follow() {

		try {

			String uId =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_FROM_UID);
			String oAId =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_OAID);

			zaloMap = ZaloMapLocalServiceUtil.getByUId(uId);

			if (Validator.isNull(zaloMap)) {

				String telNoIfPublic = (String) zaloInfo.get(
					ZaloHookConstantKeys.ZALO_PARAM_PHONE);

				zaloMap = ZaloMapLocalServiceUtil.updateZaloMap(
					0, 0, uId, telNoIfPublic, oAId, 1, StringPool.BLANK);
			}
			else {

				zaloMap = ZaloMapLocalServiceUtil.updateZaloMap(
					zaloMap.getZaloMapId(), zaloMap.getGroupId(), uId,
					zaloMap.getTelNo(), oAId, 1, zaloMap.getPayload());
			}

		}
		catch (PortalException e) {

			_log.error(e);
		}

		return _buildResponse(zaloMap);
	}

	public JSONObject sendmsg() {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		/**
		 * message like: check<daucach>sdt<daucach>[sdt] registry
		 */

		try {

			String message =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_MESSAGE);
			String[] messages = message.split(StringPool.SPACE);

			if (messages[0].equalsIgnoreCase(
				ZaloHookConstantKeys.ZALO_MESSAGE_SYNTAX_CHECK_TEL_NO)) {

				result = _checkTelNo(messages[1]);
			}
			else if (messages[0].equalsIgnoreCase(
				ZaloHookConstantKeys.ZALO_MESSAGE_SYNTAX_REGISTRY_TEL_NO)) {

				result = _registryTelNo(messages[1]);
			}
			else if (messages[0].equalsIgnoreCase(
				ZaloHookConstantKeys.ZALO_MESSAGE_SYNTAX_UPDATE_TEL_NO)) {

				result = _updateTelNo(messages[1]);
			}
			else if (messages[0].equalsIgnoreCase(
				ZaloHookConstantKeys.ZALO_MESSAGE_SYNTAX_SEARCH_DOSSIER)) {

				result = _searchDossier(messages[1], messages[2]);
			}
		}
		catch (Exception e) {

			_log.error(e);
		}

		return result;
	}

	public JSONObject addPhone() {

		try {

			String uId =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_FROM_UID);
			String oAId =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_OAID);
			String telNo =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_C_PARAM_TEL_NO);
			long groupId = Long.parseLong(
				(String) zaloInfo.get(
					ZaloHookConstantKeys.ZALO_C_PARAM_GROUP_ID));
			String payload = (String) zaloInfo.get(
				ZaloHookConstantKeys.ZALO_C_PARAM_PAYLOAD);

			zaloMap = ZaloMapLocalServiceUtil.getByUId(uId);

			if (Validator.isNull(zaloMap)) {

				zaloMap = ZaloMapLocalServiceUtil.updateZaloMap(
					0, groupId, uId, telNo, oAId, 1, payload);
			}
			else {

				zaloMap = ZaloMapLocalServiceUtil.updateZaloMap(
					zaloMap.getZaloMapId(), groupId, uId, telNo, oAId, 1,
					payload);
			}

		}
		catch (PortalException e) {

			_log.error(e);
		}

		return _buildResponse(zaloMap);
	}

	public JSONObject searchDossier() {

		try {

			String uId =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_FROM_UID);
			String oAId =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_OAID);
			String telNo =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_C_PARAM_TEL_NO);
			long groupId = Long.parseLong(
				(String) zaloInfo.get(
					ZaloHookConstantKeys.ZALO_C_PARAM_GROUP_ID));
			String payload = (String) zaloInfo.get(
				ZaloHookConstantKeys.ZALO_C_PARAM_PAYLOAD);

			zaloMap = ZaloMapLocalServiceUtil.getByUId(uId);

			if (Validator.isNull(zaloMap)) {

				zaloMap = ZaloMapLocalServiceUtil.updateZaloMap(
					0, groupId, uId, telNo, oAId, 1, payload);
			}
			else {

				zaloMap = ZaloMapLocalServiceUtil.updateZaloMap(
					zaloMap.getZaloMapId(), groupId, uId, telNo, oAId, 1,
					payload);
			}

		}
		catch (PortalException e) {

			_log.error(e);
		}

		return _buildResponse(zaloMap);
	}

	private JSONObject _checkTelNo(String telNo) {

		zaloMap = ZaloMapLocalServiceUtil.getByTelNo(telNo);
		String oAId =
			(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_OAID);

//		String uId =
//			(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_FROM_UID);
		if (Validator.isNotNull(zaloMap) &&
			oAId.equals(zaloMap.getZaloOAId())) {

			// _sendZalo(SendSMSTerm.ZALO_TEL_NO_EXITS_MES, uId);
		}
		else {

			// _sendZalo(SendSMSTerm.ZALO_TEL_NO_NOT_EXITS_MES, uId);
		}

		return _buildResponse(zaloMap);
	}

	private JSONObject _registryTelNo(String telNo) {

//		String uId =
//			(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_FROM_UID);
		String oAId =
			(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_OAID);

		zaloMap = ZaloMapLocalServiceUtil.getByTelNo(telNo);

		if (Validator.isNotNull(zaloMap) &&
			oAId.equals(zaloMap.getZaloOAId())) {

			// _sendZalo(SendSMSTerm.ZALO_TEL_NO_EXITS_MES, uId);
			return _buildResponse(zaloMap);
		}
		else {

			return _updateTelNo(telNo);
		}

	}

	private JSONObject _updateTelNo(String telNo) {

		try {
			String uId =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_FROM_UID);
			String oAId =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_OAID);

			zaloMap = ZaloMapLocalServiceUtil.getByUId(uId);

			if (Validator.isNull(zaloMap)) {

				zaloMap = ZaloMapLocalServiceUtil.updateZaloMap(
					0, 0, uId, telNo, oAId, 1, StringPool.BLANK);
			}
			else {

				zaloMap = ZaloMapLocalServiceUtil.updateZaloMap(
					zaloMap.getZaloMapId(), zaloMap.getGroupId(), uId, telNo,
					oAId, 1, zaloMap.getPayload());
			}

			// _sendZalo(SendSMSTerm.ZALO_UPDATE_TEL_NO_SUCCESS_MES, uId);
		}
		catch (PortalException e) {

			_log.error(e);
		}

		return _buildResponse(zaloMap);
	}

	private JSONObject _searchDossier(String dossierNo, String password) {

		try {
			String uId =
				(String) zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_FROM_UID);

			JSONObject zaloConfig = _getZaloInfo();
			Dossier dossier = DossierLocalServiceUtil.getByDossierNo(
				zaloConfig.getLong(SendSMSTerm.ZALO_GROUPID), dossierNo);
			String message;

			if (Validator.isNull(dossier)) {

				message =
					zaloConfig.getString(SendSMSTerm.ZALO_DOSSIER_NOT_FOUND_MES);

			}
			else if (!dossier.getPassword().equalsIgnoreCase(password)) {

				message = zaloConfig.getString(
					SendSMSTerm.ZALO_DOSSIER_ERROR_PASSWORD_MES);

			}
			else {

				message =
					zaloConfig.getString(SendSMSTerm.ZALO_DOSSIER_SUCCESS_MES);
				message =
					message.replaceAll(
						zaloConfig.getString(
							SendSMSTerm.ZALO_DOSSIER_NO_REPLACE),
						dossierNo);
				message = message.replaceAll(
					zaloConfig.getString(
						SendSMSTerm.ZALO_DOSSIER_STATUS_REPLACE),
					dossier.getDossierStatusText());
			}

			_sendZalo(message, uId, zaloConfig);
		}
		catch (Exception e) {

			_log.error(e);
		}

		return _buildResponse(zaloMap);
	}

	private JSONObject _getZaloInfo() {

		JSONObject zaloInfoConfig = JSONFactoryUtil.createJSONObject();

		try {

			ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(
				SendSMSTerm.SERVER_CONFIG_SERVERNO_ZALO);

			zaloInfoConfig = JSONFactoryUtil.createJSONObject(sc.getConfigs());
		}
		catch (Exception e) {
			_log.error(e);
		}

		return zaloInfoConfig;
	}

	private JSONObject _buildResponse(ZaloMap zaloMap) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		return response;
	}

	private void _sendZalo(
		String messageCode, String zaloUid, JSONObject zaloConfig) {

		try {

			JSONObject zaloConfigInfo = zaloConfig;
			if (Validator.isNull(zaloConfig)) {
				zaloConfigInfo = _getZaloInfo();
			}
			String oAIdToken =
				zaloConfigInfo.getString(SendSMSTerm.OAID_TOKEN_ACCESS);
			String textMessage = zaloConfigInfo.getString(messageCode);

			textMessage =
				Validator.isNotNull(textMessage) ? textMessage : messageCode;

			if (Validator.isNotNull(textMessage) &&
				Validator.isNotNull(zaloUid) &&
				Validator.isNotNull(oAIdToken)) {

				String targetURL =
					"https://openapi.zalo.me/v2.0/oa/message?access_token=" +
						oAIdToken;

				JSONObject payloadJSON = JSONFactoryUtil.createJSONObject(
					"{\"recipient\":{\"user_id\":\"1893010867233038754\"}, \"message\":{\"text\":\"1893010867233038754\"}}");
				JSONObject recipient = JSONFactoryUtil.createJSONObject();
				JSONObject message = JSONFactoryUtil.createJSONObject();

				recipient.put("user_id", zaloUid);

				message.put("text", textMessage);

				payloadJSON.put("recipient", recipient);

				payloadJSON.put("message", message);

				_postMessZalo(targetURL, payloadJSON.toJSONString());
			}

		}
		catch (JSONException e) {
			_log.error(e);
		}

	}

	private void _postMessZalo(String url, String param) {

		try {
			String charset = "UTF-8";
			URLConnection connection = new URL(url).openConnection();
			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Content-Type", "application/json;");

			OutputStream output = connection.getOutputStream();
			output.write(param.getBytes(charset));

			connection.getInputStream();
			_log.info("Send zalo message success");
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(ZaloMapUtils.class);

}
