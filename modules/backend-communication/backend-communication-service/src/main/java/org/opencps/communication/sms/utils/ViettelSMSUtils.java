
package org.opencps.communication.sms.utils;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.opencps.communication.constants.SendSMSTerm;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;

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
				configObj.has(SendSMSTerm.SECRET_CODE) &&
				configObj.has(SendSMSTerm.REQUEST_ID) &&
				configObj.has(SendSMSTerm.SERVICE_ID) &&		
				configObj.has(SendSMSTerm.USER) &&
				configObj.has(SendSMSTerm.COUNTRY_CODE) &&
				Validator.isNotNull(toTelNo)) {

				locator.setCcApiPortEndpointAddress(
					configObj.getString(SendSMSTerm.CC_API_PORT_ADDRESS));
				portType = locator.getCcApiPort();

				String toTelNoRpl = String.valueOf(0).equals(toTelNo.substring(0, 1))
					? configObj.getString(SendSMSTerm.COUNTRY_CODE) +
						toTelNo.substring(1)
					: toTelNo;
				String serviceId = configObj.getString(SendSMSTerm.SERVICE_ID);
				
				result = portType.wsCpMt(
					configObj.getString(SendSMSTerm.USER),
					configObj.getString(SendSMSTerm.SECRET_CODE),
					configObj.getString(SendSMSTerm.CP_CODE),
					configObj.getString(SendSMSTerm.REQUEST_ID), toTelNoRpl,
					toTelNoRpl, /*configObj.getString(SendSMSTerm.SERVICE_ID),*/ serviceId,
					configObj.getString(SendSMSTerm.COMMAND_CODE), body,
					configObj.getString(SendSMSTerm.CONTENT_TYPE));
			}
			else {

				// handle with hashCode
				portType = locator.getCcApiPort();
				result = portType.wsCpMt(
					DEFAULT_USER, DEFAULT_SECRET, DEFAULT_CP_CODE, DEFAULT_REQUEST_ID, toTelNo,
					toTelNo, DEFAULT_SERVICE_ID, DEFAULT_COMMAND_CODE, body, DEFAULT_CONTENT_TYPE);
			}
		}

		return result;
	}

	private static final String DEFAULT_USER = "viettelmcdt";
	private static final String DEFAULT_SECRET = "789456a@#123";
	private static final String DEFAULT_CP_CODE = "VIETTELMCDT";
	private static final String DEFAULT_REQUEST_ID = "1";
	private static final String DEFAULT_SERVICE_ID = "ViettelMCDT";
	private static final String DEFAULT_COMMAND_CODE = "bulksms";
	private static final String DEFAULT_CONTENT_TYPE = "F";
	
//	private static final String VN_CARRIER_VIETTEL = "Viettel";
//	private static final String VN_CARRIER_MOBIFONE = "Mobifone";
//	private static final String VN_CARRIER_VINAPHONE = "Vinaphone";
//	private static final String VN_CARRIER_GMOBILE = "Gmobile";
//	private static final String VN_CARRIER_VIETNAMOBILE = "Vietnamobile";
//	private static final String VN_CARRIER_SFONE = "SFone";
//	
//	private static String detectPhoneNumber(String number) {
//		Map<String, String> carriers = new HashMap<String, String>();
//		//Viettel
//		carriers.put("096", "Viettel");
//		carriers.put("097", "Viettel");
//		carriers.put("098", "Viettel");
//		carriers.put("032", "Viettel");
//		carriers.put("033", "Viettel");
//		carriers.put("034", "Viettel");
//		carriers.put("035", "Viettel");
//		carriers.put("036", "Viettel");
//		carriers.put("037", "Viettel");
//		carriers.put("038", "Viettel");
//		carriers.put("039", "Viettel");
//		
//		//Mobifone
//		carriers.put("090", "Mobifone");
//		carriers.put("093", "Mobifone");
//		carriers.put("070", "Mobifone");
//		carriers.put("071", "Mobifone");
//		carriers.put("072", "Mobifone");
//		carriers.put("076", "Mobifone");
//		carriers.put("078", "Mobifone");
//		
//		//Vinaphone
//		carriers.put("091", "Vinaphone");
//		carriers.put("094", "Vinaphone");
//		carriers.put("083", "Vinaphone");
//		carriers.put("085", "Vinaphone");
//		carriers.put("087", "Vinaphone");
//		carriers.put("089", "Vinaphone");
//		
//		//Gmobile
//		carriers.put("099", "Gmobile");
//
//		//Vietnamobile
//		carriers.put("092", "Vietnamobile");
//		carriers.put("056", "Vietnamobile");
//		carriers.put("058", "Vietnamobile");
//		
//		//SFone
//		carriers.put("095", "SFone");		
//		
//		if (number.length() > 10) {
//			if (number.startsWith(VN_COUNTRY_CODE)) {
//				String checkNumber = number.substring(VN_COUNTRY_CODE.length());
//				for (String key: carriers.keySet()) {
//					if (checkNumber.startsWith(key)) {
//						return carriers.get(key).toString();
//					}
//				}
//				return StringPool.BLANK;
//			}
//			else {
//				return StringPool.BLANK;
//			}
//		}
//		else if (number.length() == 10) {
//			for (String key: carriers.keySet()) {
//				if (number.startsWith(key)) {
//					return carriers.get(key).toString();
//				}
//			}	
//			return StringPool.BLANK;
//		}
//		else {
//			return StringPool.BLANK;
//		}
//	}
	
//	private static final String VN_COUNTRY_CODE = "84";
	private static final Log _log =
		LogFactoryUtil.getLog(ViettelSMSUtils.class);
}
