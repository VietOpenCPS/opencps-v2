
package org.opencps.communication.sms.utils;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.opencps.communication.constants.SendSMSTerm;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import ws.bulkSms.impl.CcApi_PortType;
import ws.bulkSms.impl.CcApi_ServiceLocator;
import ws.bulkSms.impl.Result;

public class ViettelSMSUtils {

	public static Result sendSMS(
		long groupId, String body, String title, String toTelNo)
		throws RemoteException, ServiceException {

		CcApi_ServiceLocator locator = new CcApi_ServiceLocator();
		CcApi_PortType portType;
		JSONObject configObj = JSONFactoryUtil.createJSONObject();
		Result result = new Result();

		try {

			List<ServerConfig> lstScs =
				ServerConfigLocalServiceUtil.getByProtocol(
					groupId, SendSMSTerm.SERVER_CONFIG_PROTOCOL_VT_SMS);

			if (!lstScs.isEmpty()) {

				ServerConfig sc = lstScs.get(0);

				configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
			}
		}
		catch (Exception e) {

			_log.error(e);
		}
		finally {

			_log.debug(configObj);
			if (configObj.has(SendSMSTerm.CC_API_PORT_ADDRESS) &&
				configObj.has(SendSMSTerm.COMMAND_CODE) &&
				configObj.has(SendSMSTerm.CONTENT_TYPE) &&
				configObj.has(SendSMSTerm.CP_CODE) &&
				configObj.has(SendSMSTerm.PASSWORD) &&
				configObj.has(SendSMSTerm.REQUEST_ID) &&
				configObj.has(SendSMSTerm.SERVICE_ID) &&
				configObj.has(SendSMSTerm.USER) &&
				configObj.has(SendSMSTerm.COUNTRY_CODE) &&
				Validator.isNotNull(toTelNo)) {

				locator.setCcApiPortEndpointAddress(
					configObj.getString(SendSMSTerm.CC_API_PORT_ADDRESS));
				portType = locator.getCcApiPort();

				String toTelNoRpl = "0".equals(toTelNo.substring(0, 1))
					? configObj.getString(SendSMSTerm.COUNTRY_CODE) +
						toTelNo.substring(1)
					: toTelNo;
				result = portType.wsCpMt(
					configObj.getString(SendSMSTerm.USER),
					configObj.getString(SendSMSTerm.PASSWORD),
					configObj.getString(SendSMSTerm.CP_CODE),
					configObj.getString(SendSMSTerm.REQUEST_ID), toTelNoRpl,
					toTelNoRpl, configObj.getString(SendSMSTerm.SERVICE_ID),
					configObj.getString(SendSMSTerm.COMMAND_CODE), body,
					configObj.getString(SendSMSTerm.CONTENT_TYPE));
			}
			else {

				// handle with hashCode
				portType = locator.getCcApiPort();
				result = portType.wsCpMt(
					"viettelmcdt", "789456a@#123", "VIETTELMCDT", "1", toTelNo,
					toTelNo, "ViettelMCDT", "bulksms", body, "F");
			}
		}

		return result;
	}

	private static final Log _log =
		LogFactoryUtil.getLog(ViettelSMSUtils.class);
}
