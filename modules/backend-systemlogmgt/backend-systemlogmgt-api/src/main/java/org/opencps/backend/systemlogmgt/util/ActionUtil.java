package org.opencps.backend.systemlogmgt.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.opencps.backend.systemlogmgt.service.SystemLogLocalServiceUtil;
import org.opencps.backend.systemlogmgt.service.SystemLogServiceTestUtil;
import org.opencps.backend.systemlogmgt.constant.Constants;
import org.opencps.backend.systemlogmgt.model.SystemLog;


/**
 * @author trungnt
 *
 */
public class ActionUtil {

	private static Log _log = LogFactoryUtil.getLog(ActionUtil.class);
	
	public static JSONObject createResponseSchema(String... msg) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		
		result.put(Constants.TOTAL, 0);
		result.put(Constants.DATA, JSONFactoryUtil.createJSONArray());

		if (msg != null) {
			result.put(Constants.MESSAGES, StringUtil.merge(msg));
		}

		return result;
	}
	public static JSONObject execDiagram(String threadId, Long logId) throws JSONException {
		
		JSONObject result = ActionUtil.createResponseSchema();
		JSONArray data = JSONFactoryUtil.createJSONArray();
		List<SystemLog> listSystemLog = null;
		try {
			listSystemLog = SystemLogLocalServiceUtil.getSystemLogByDynamicQuery(logId, null, null, null, threadId, null, null);
		} catch (Exception e) {
			_log.error(e);
			result.put(Constants.MESSAGES, e);
		}
		if(Validator.isNotNull(listSystemLog)) {
			String MessageDiagram = null;
			for(int i=0; i<listSystemLog.size(); i++) {
				if(Validator.isNull(MessageDiagram)) {
					MessageDiagram = listSystemLog.get(i).getPreMethod() + " -> " + listSystemLog.get(i).getMethod();
				} else {
					if(listSystemLog.get(i-1).getMethod().equals(listSystemLog.get(i).getPreMethod())) {
						MessageDiagram = MessageDiagram+  " -> " + listSystemLog.get(i).getMethod();
					}
				}
			}
			List<String> threadIdList = new ArrayList<String>();
			for (SystemLog systemLog : listSystemLog) {
				threadIdList.add(systemLog.getThreadId());
			}
			HashSet<String> hset = new HashSet<String>(threadIdList);
			List<String> list = new ArrayList<String>(hset);
			for (String string : list) {
				JSONArray arrayLog = JSONFactoryUtil.createJSONArray();
				JSONObject Log = JSONFactoryUtil.createJSONObject();
				Log.put("ThreadId", string);
				for (SystemLog systemLog : listSystemLog) {
					if(systemLog.getThreadId().equals(string)) {
						String strResult = JSONFactoryUtil.looseSerialize(systemLog);
						JSONObject object = JSONFactoryUtil.createJSONObject(strResult);
						arrayLog.put(ParamUtil.getValuesByColumns(object));
					}
				}
				Log.put("Log", arrayLog);
				data.put(Log);
			}
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, data);
			result.put(Constants.MESSAGES, MessageDiagram);
			
		}
		SystemLogServiceTestUtil.info(5L, "SystemLog", "Successful", "[{\"key\":\"name\",\"value\":\"Bao Cao\"},{\"key\":\"DonVi\",\"value\":\"Hau Giang\"},{\"key\":\"data\",\"value\":\"\"}]");
		return result;
		
	}
	
	public static JSONObject searchSystemLog(Long logId, Long groupId, String moduleName, String method, String threadId, Long fromDate, Long toDate) throws JSONException {
		
		JSONObject result = ActionUtil.createResponseSchema();
		JSONArray data = JSONFactoryUtil.createJSONArray();
		Date fromDateTime = null;
		Date toDateTime = null;
		List<SystemLog> listSystemLog = null;
		if(Validator.isNotNull(fromDate)) {
			fromDateTime = new Timestamp(fromDate);
		}
		if(Validator.isNotNull(toDate)) {
			toDateTime = new Timestamp(toDate);
		}
		listSystemLog = SystemLogLocalServiceUtil.getSystemLogByDynamicQuery(logId, groupId, moduleName, method, threadId, fromDateTime, toDateTime);
		if(Validator.isNotNull(listSystemLog)) {
			List<String> threadIdList = new ArrayList<String>();
			for (SystemLog systemLog : listSystemLog) {
				threadIdList.add(systemLog.getThreadId());
			}
			HashSet<String> hset = new HashSet<String>(threadIdList);
			List<String> list = new ArrayList<String>(hset);
			for (String string : list) {
				JSONArray arrayLog = JSONFactoryUtil.createJSONArray();
				JSONObject Log = JSONFactoryUtil.createJSONObject();
				Log.put("ThreadId", string);
				for (SystemLog systemLog : listSystemLog) {
					if(systemLog.getThreadId().equals(string)) {
						String strResult = JSONFactoryUtil.looseSerialize(systemLog);
						JSONObject object = JSONFactoryUtil.createJSONObject(strResult);
						arrayLog.put(ParamUtil.getValuesByColumns(object));
					}
				}
				Log.put("Log", arrayLog);
				data.put(Log);
			}
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, data);
			result.put(Constants.MESSAGES, "SuccessFull");
			SystemLogServiceTestUtil.info(5L, "SystemLog", "Successful", "[{\"key\":\"name\",\"value\":\"Bao Cao\"},{\"key\":\"DonVi\",\"value\":\"Hau Giang\"},{\"key\":\"data\",\"value\":\"\"}]");
			
		} else {
			result.put(Constants.TOTAL, 0);
			result.put(Constants.DATA, data);
			result.put(Constants.MESSAGES, "No Data - Query Sucessful");
			SystemLogServiceTestUtil.info(5L, "SystemLog", "No Data - Query Sucessful","[{\"key\":\"name\",\"value\":\"Bao Cao\"},{\"key\":\"DonVi\",\"value\":\"Hau Giang\"},{\"key\":\"data\",\"value\":\"\"}]");

		}
		
		return result;
		
	}
	
	
}
