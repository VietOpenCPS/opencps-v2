package org.opencps.backend.systemlogmgt.processimpl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DebugGraphics;

import org.opencps.backend.systemlogmgt.util.ParamUtil;
import org.opencps.backend.systemlogmgt.application.SystemLogApplication;
import org.opencps.backend.systemlogmgt.constant.Constants;
import org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException;
import org.opencps.backend.systemlogmgt.model.SystemLog;
import org.opencps.backend.systemlogmgt.process.QueryProcessFactory;
import org.opencps.backend.systemlogmgt.service.SystemLogLocalService;
import org.opencps.backend.systemlogmgt.service.SystemLogLocalServiceUtil;
import org.opencps.backend.systemlogmgt.service.persistence.SystemLogUtil;
import org.opencps.backend.systemlogmgt.util.ActionUtil;


/**
 * @author vietdd
 *
 */
public class QueryProcessFactoryImpl implements QueryProcessFactory {

	private Log _log = LogFactoryUtil.getLog(QueryProcessFactoryImpl.class);
	
	@Override
	public JSONObject getAllSystemLog() throws JSONException {
		JSONObject result = ActionUtil.createResponseSchema(null, null, null, null, null, null, null, null, null, null, null, null, null, 0);
		JSONArray data = JSONFactoryUtil.createJSONArray();
		List<SystemLog> listSystemLog = new ArrayList<SystemLog>();
		int numberOfSystemLog = SystemLogLocalServiceUtil.getSystemLogsCount();
		try {
			listSystemLog = SystemLogLocalServiceUtil.getSystemLogs(0, numberOfSystemLog);
		} catch (Exception e) {
			result.put(Constants.MESSAGES, e);
		}
		if(Validator.isNotNull(listSystemLog)) {
			for (SystemLog systemLog : listSystemLog) {
				String strResult = JSONFactoryUtil.looseSerialize(systemLog);
				JSONObject object = JSONFactoryUtil.createJSONObject(strResult);
				data.put(object);
			}
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, ParamUtil.getValuesByColumns(data));
			result.put(Constants.MESSAGES, "Successfull");
		}
		return result;
	}
	/**
	 * Searching SystemLog by LogId
	 * @throws PortalException 
	 *
	 */
	
	@Override
	public JSONObject getSystemLogType1(String logId, String groupId, String moduleName, String strCreateDate,
			Integer preLine, String preMethod, Integer line, String method, String message, String type,
			String threadId, int typeList) throws PortalException {
		JSONObject result = ActionUtil.createResponseSchema(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, null, null, typeList);
		SystemLog systemLog = null;
		Long logIdInput = null;
		try {
			logIdInput = Long.parseLong(logId);
		} catch (Exception e) {
			result.put(Constants.MESSAGES, e);
		}
		if(Validator.isNotNull(logIdInput)) {
			try {
				systemLog = SystemLogLocalServiceUtil.getSystemLogByLogId(logIdInput);
			} catch (Exception e) {
				result.put(Constants.MESSAGES, e);
			}
			if(Validator.isNotNull(systemLog)) {
				String strResult = JSONFactoryUtil.looseSerialize(systemLog);
				JSONObject data = JSONFactoryUtil.createJSONObject(strResult);
				result.put(Constants.TOTAL, data.length());
				result.put(Constants.DATA, ParamUtil.getValuesByColumns(data));
				result.put(Constants.MESSAGES, "Successfull");
				SystemLogLocalServiceUtil.addNewSystemLog(5L, SystemLog.class.toString()
						, Thread.currentThread().getStackTrace()[2].getLineNumber(), Thread.currentThread().getStackTrace()[2].getMethodName()
						, Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName()
						, "List " + typeList +" Complete", "Searching", SystemLogApplication.threadIdContext.get());
				SystemLogApplication.threadIdContext.remove();
			}
		}
		return result; 
	}
	/**
	 * Searching SystemLog by Multiple Group Id
	 * @throws JSONException 
	 * 
	 */
	@Override
	public JSONObject getSystemLogType2(String logId, String groupId, String moduleName, String strCreateDate,
			Integer preLine, String preMethod, Integer line, String method, String message, String type,
			String threadId, int typeList) throws JSONException {
		long[] groupIdEx = ParamUtil.getArrayLongParams(groupId);
		JSONObject result = ActionUtil.createResponseSchema(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, null, null, typeList);
		JSONArray data = JSONFactoryUtil.createJSONArray();
		List<SystemLog> listSystemLog = null;
		try {
			listSystemLog = SystemLogLocalServiceUtil.getSystemLogByMultipleGroupId(groupIdEx);
		} catch (Exception e) {
			// TODO: handle exception
			_log.error(e);
			result.put(Constants.MESSAGES, e);
		}
		if(Validator.isNotNull(listSystemLog)) {
			for (SystemLog systemLog : listSystemLog) {
				String strResult = JSONFactoryUtil.looseSerialize(systemLog);
				JSONObject object = JSONFactoryUtil.createJSONObject(strResult);
				data.put(object);
			}
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, ParamUtil.getValuesByColumns(data));
			result.put(Constants.MESSAGES, "Successfull");
			SystemLogLocalServiceUtil.addNewSystemLog(5L, SystemLog.class.toString()
					, Thread.currentThread().getStackTrace()[2].getLineNumber(), Thread.currentThread().getStackTrace()[2].getMethodName()
					, Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName()
					, "List " + typeList +" Complete", "Searching", SystemLogApplication.threadIdContext.get());
			SystemLogApplication.threadIdContext.remove();
		}
		return result; 
	}
	/**
	 * Searching SystemLog by Multiple Module Name
	 * @throws JSONException 
	 * 
	 */
	@Override
	public JSONObject getSystemLogType3(String logId, String groupId, String moduleName, String strCreateDate,
			Integer preLine, String preMethod, Integer line, String method, String message, String type,
			String threadId, int typeList) throws JSONException {
		String[] moduleNameArr = ParamUtil.getArrayParams(moduleName);
		JSONObject result = ActionUtil.createResponseSchema(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, null, null, typeList);
		JSONArray data = JSONFactoryUtil.createJSONArray();
		List<SystemLog> listSystemLog = null;
		try {
			listSystemLog = SystemLogLocalServiceUtil.getSystemLogByMultipleModuleName(moduleNameArr);
		} catch (Exception e) {
			// TODO: handle exception
			_log.error(e);
			result.put(Constants.MESSAGES, e);
		}
		if(Validator.isNotNull(listSystemLog)) {
			for (SystemLog systemLog : listSystemLog) {
				String strResult = JSONFactoryUtil.looseSerialize(systemLog);
				JSONObject object = JSONFactoryUtil.createJSONObject(strResult);
				data.put(object);
			}
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, ParamUtil.getValuesByColumns(data));
			result.put(Constants.MESSAGES, "Successfull");
			SystemLogLocalServiceUtil.addNewSystemLog(5L, SystemLog.class.toString()
					, Thread.currentThread().getStackTrace()[2].getLineNumber(), Thread.currentThread().getStackTrace()[2].getMethodName()
					, Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName()
					, "List " + typeList +" Complete", "Searching", SystemLogApplication.threadIdContext.get());
			SystemLogApplication.threadIdContext.remove();
		}
		return result;
	}
	/**
	 * Searching SystemLog by Multiple Pre Method
	 * @throws JSONException 
	 * 
	 */
	@Override
	public JSONObject getSystemLogType4(String logId, String groupId, String moduleName, String strCreateDate,
			Integer preLine, String preMethod, Integer line, String method, String message, String type,
			String threadId, int typeList) throws JSONException {
		
		String[] preMethods = ParamUtil.getArrayParams(preMethod);
		JSONObject result = ActionUtil.createResponseSchema(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, null, null, typeList);
		JSONArray data = JSONFactoryUtil.createJSONArray();
		List<SystemLog> listSystemLog = null;
		try {
			listSystemLog = SystemLogLocalServiceUtil.getSystemLogByMultiplePreMethod(preMethods);
		} catch (Exception e) {
			// TODO: handle exception
			_log.error(e);
			result.put(Constants.MESSAGES, e);
		}
		if(Validator.isNotNull(listSystemLog)) {
			for (SystemLog systemLog : listSystemLog) {
				String strResult = JSONFactoryUtil.looseSerialize(systemLog);
				JSONObject object = JSONFactoryUtil.createJSONObject(strResult);
				data.put(object);
			}
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, ParamUtil.getValuesByColumns(data));
			result.put(Constants.MESSAGES, "Successfull");
			SystemLogLocalServiceUtil.addNewSystemLog(5L, SystemLog.class.toString()
					, Thread.currentThread().getStackTrace()[2].getLineNumber(), Thread.currentThread().getStackTrace()[2].getMethodName()
					, Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName()
					, "List " + typeList +" Complete", "Searching", SystemLogApplication.threadIdContext.get());
			SystemLogApplication.threadIdContext.remove();
		}
		return result;
	}
	/**
	 * Searching SystemLog by Multiple Method
	 * @throws JSONException 
	 * 
	 */
	@Override
	public JSONObject getSystemLogType5(String logId, String groupId, String moduleName, String strCreateDate,
			Integer preLine, String preMethod, Integer line, String method, String message, String type,
			String threadId, int typeList) throws JSONException {
		String[] Methods = ParamUtil.getArrayParams(method);
		JSONObject result = ActionUtil.createResponseSchema(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, null, null, typeList);
		JSONArray data = JSONFactoryUtil.createJSONArray();
		List<SystemLog> listSystemLog = null;
		try {
			listSystemLog = SystemLogLocalServiceUtil.getSystemLogByMultipleMethod(Methods);
		} catch (Exception e) {
			// TODO: handle exception
			_log.error(e);
			result.put(Constants.MESSAGES, e);
		}
		if(Validator.isNotNull(listSystemLog)) {
			for (SystemLog systemLog : listSystemLog) {
				String strResult = JSONFactoryUtil.looseSerialize(systemLog);
				JSONObject object = JSONFactoryUtil.createJSONObject(strResult);
				data.put(object);
			}
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, ParamUtil.getValuesByColumns(data));
			result.put(Constants.MESSAGES, "Successfull");
			SystemLogLocalServiceUtil.addNewSystemLog(5L, SystemLog.class.toString()
					, Thread.currentThread().getStackTrace()[2].getLineNumber(), Thread.currentThread().getStackTrace()[2].getMethodName()
					, Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName()
					, "List " + typeList +" Complete", "Searching", SystemLogApplication.threadIdContext.get());
			SystemLogApplication.threadIdContext.remove();
		}
		return result;
	}
	/**
	 * Searching SystemLog by Multiple ThreadId
	 * @throws JSONException 
	 * 
	 */
	@Override
	public JSONObject getSystemLogType6(String logId, String groupId, String moduleName, String strCreateDate,
			Integer preLine, String preMethod, Integer line, String method, String message, String type,
			String threadId, int typeList) throws JSONException {
		String[] threadIds = ParamUtil.getArrayParams(threadId);
		JSONObject result = ActionUtil.createResponseSchema(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, null, null, typeList);
		JSONArray data = JSONFactoryUtil.createJSONArray();
		List<SystemLog> listSystemLog = null;
		try {
			listSystemLog = SystemLogLocalServiceUtil.getSystemLogByMultipleThreadId(threadIds);
		} catch (Exception e) {
			// TODO: handle exception
			_log.error(e);
			result.put(Constants.MESSAGES, e);
		}
		if(Validator.isNotNull(listSystemLog)) {
			for (SystemLog systemLog : listSystemLog) {
				String strResult = JSONFactoryUtil.looseSerialize(systemLog);
				JSONObject object = JSONFactoryUtil.createJSONObject(strResult);
				data.put(object);
			}
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, ParamUtil.getValuesByColumns(data));
			result.put(Constants.MESSAGES, "Successfull");
			SystemLogLocalServiceUtil.addNewSystemLog(5L, SystemLog.class.toString()
					, Thread.currentThread().getStackTrace()[2].getLineNumber(), Thread.currentThread().getStackTrace()[2].getMethodName()
					, Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName()
					, "List " + typeList +" Complete", "Searching", SystemLogApplication.threadIdContext.get());
		}
		return result;
	}
	/**
	 * Searching SystemLog by Multiple Type
	 * @throws JSONException 
	 * 
	 */
	@Override
	public JSONObject getSystemLogType7(String logId, String groupId, String moduleName, String strCreateDate,
			Integer preLine, String preMethod, Integer line, String method, String message, String type,
			String threadId, int typeList) throws JSONException {
		String[] types = ParamUtil.getArrayParams(type);
		JSONObject result = ActionUtil.createResponseSchema(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, null, null, typeList);
		JSONArray data = JSONFactoryUtil.createJSONArray();
		List<SystemLog> listSystemLog = null;
		try {
			listSystemLog = SystemLogLocalServiceUtil.getSystemLogByMultipleType(types);
		} catch (Exception e) {
			// TODO: handle exception
			_log.error(e);
			result.put(Constants.MESSAGES, e);
		}
		if(Validator.isNotNull(listSystemLog)) {
			for (SystemLog systemLog : listSystemLog) {
				String strResult = JSONFactoryUtil.looseSerialize(systemLog);
				JSONObject object = JSONFactoryUtil.createJSONObject(strResult);
				data.put(object);
			}
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, ParamUtil.getValuesByColumns(data));
			result.put(Constants.MESSAGES, "Successfull");
			SystemLogLocalServiceUtil.addNewSystemLog(5L, SystemLog.class.toString()
					, Thread.currentThread().getStackTrace()[2].getLineNumber(), Thread.currentThread().getStackTrace()[2].getMethodName()
					, Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName()
					, "List " + typeList +" Complete", "Searching", SystemLogApplication.threadIdContext.get());
		}
		return result;
	}
	/**
	 * Searching SystemLog by Dynamic Query
	 * @throws JSONException 
	 * 
	 */
	@Override
	public JSONObject getSystemLogType8(String logId, String groupId, String moduleName, String strCreateDate,
			Integer preLine, String preMethod, Integer line, String method, String message, String type,
			String threadId, int typeList) throws JSONException {
		JSONObject result = ActionUtil.createResponseSchema(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, null, null, typeList);
		JSONArray data = JSONFactoryUtil.createJSONArray();
		List<SystemLog> listSystemLog = null;
		try {
			listSystemLog = SystemLogLocalServiceUtil.getSystemLogByDynamicQuery(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, null, null);
		} catch (Exception e) {
			// TODO: handle exception
			_log.error(e);
			result.put(Constants.MESSAGES, e);
		}
		if(Validator.isNotNull(listSystemLog)) {
			for (SystemLog systemLog : listSystemLog) {
				String strResult = JSONFactoryUtil.looseSerialize(systemLog);
				JSONObject object = JSONFactoryUtil.createJSONObject(strResult);
				data.put(object);
			}
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, ParamUtil.getValuesByColumns(data));
			result.put(Constants.MESSAGES, "Successfull");
			SystemLogLocalServiceUtil.addNewSystemLog(5L, SystemLog.class.toString()
					, Thread.currentThread().getStackTrace()[2].getLineNumber(), Thread.currentThread().getStackTrace()[2].getMethodName()
					, Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName()
					, "List " + typeList +" Complete", "Searching", SystemLogApplication.threadIdContext.get());
			SystemLogApplication.threadIdContext.remove();
		}
		return result;
	}
	
	
	
	
	@Override
	public JSONObject createNewSystemLog(Long groupId, String moduleName, Integer preLine, String preMethod,
			Integer line, String method, String message, String type, String threadId) throws JSONException {
		JSONObject result = ActionUtil.createResponseSchema(null, groupId.toString(), moduleName, null, preLine, preMethod, line, method, message, type, threadId, null, null, 0);
		SystemLog addSystemLog = null;
		try {
			addSystemLog = SystemLogLocalServiceUtil.addNewSystemLog(groupId, moduleName, preLine, preMethod, line, method, message, type, threadId);
		} catch (Exception e) {
			_log.error(e);
			result.put(Constants.MESSAGES, e);
		}
		if(Validator.isNotNull(addSystemLog)) {
			String strResult = JSONFactoryUtil.looseSerialize(addSystemLog);
			JSONObject data = ParamUtil.getValuesByColumns(JSONFactoryUtil.createJSONObject(strResult));
			result.put(Constants.DATA, data);
			result.put(Constants.MESSAGES, "Successfull");
		}
		return result;
	}

	@Override
	public JSONObject deleteSystemLog(Long logId) throws PortalException {
		SystemLog delSystemLog = null;
		JSONObject result = ActionUtil.createResponseSchema(logId.toString(), null, null, null, null, null, null, null, null, null,null, null, null, 0);
		try {
			delSystemLog = SystemLogLocalServiceUtil.deleteSystemLog(logId);
		}catch(Exception e) {
			_log.error(e);
			result.put(Constants.MESSAGES, e);
		}
		
		if(Validator.isNotNull(delSystemLog)) {
			String strResult = JSONFactoryUtil.looseSerialize(delSystemLog);
			JSONObject data = JSONFactoryUtil.createJSONObject(strResult);
			result.put(Constants.DATA, data);
			result.put(Constants.MESSAGES, "Successfull");
			String threadId = SystemLogApplication.threadIdContext.get();
			SystemLogLocalServiceUtil.addNewSystemLog(5L, SystemLog.class.toString(), Thread.currentThread().getStackTrace()[2].getLineNumber(), 
					Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber()
					, Thread.currentThread().getStackTrace()[1].getMethodName(), "Delete Complete", "This is Type", threadId);
			SystemLogApplication.threadIdContext.remove();
		}
		return result;
	}
	@Override
	public JSONObject findSystemLog(Date fromDate, Date toDate, String preMethods, String methods, String types, String threadId) throws JSONException {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		fromDate.setSeconds(0);
		toDate.setSeconds(59);
		JSONObject result = ActionUtil.createResponseSchema(null, null, null, null, null, preMethods, null, methods, null, types,threadId, dateFormat.format(fromDate), dateFormat.format(toDate), 0);
		JSONArray data = JSONFactoryUtil.createJSONArray();
		List<SystemLog> listSystemLog = null;
		try {
			listSystemLog = SystemLogLocalServiceUtil.getSystemLogByDynamicQuery(null, null, null, null, null, preMethods, null, methods, null, types, threadId, fromDate, toDate);
		} catch (Exception e) {
			// TODO: handle exception
			_log.error(e);
			result.put(Constants.MESSAGES, e);
		}
		if(Validator.isNotNull(listSystemLog)) {
			for (SystemLog systemLog : listSystemLog) {
				String strResult = JSONFactoryUtil.looseSerialize(systemLog);
				JSONObject object = JSONFactoryUtil.createJSONObject(strResult);
				data.put(object);
			}
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, ParamUtil.getValuesByColumns(data));
			result.put(Constants.MESSAGES, "Successfull");
		}
		return result;
	}
	@Override
	public JSONObject generateDiagram(String threadId) throws JSONException {
		JSONObject result = ActionUtil.createResponseSchema(null, null, null, null, null, null, null, null, null, null, threadId, null, null, 0);
		JSONArray data = JSONFactoryUtil.createJSONArray();
		String Diagram = null;
		List<SystemLog> listSystemLog = null;
		try {
			listSystemLog = SystemLogLocalServiceUtil.getSystemLogByThreadId(threadId);
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
			for (SystemLog systemLog : listSystemLog) {
				String strResult = JSONFactoryUtil.looseSerialize(systemLog);
				JSONObject object = JSONFactoryUtil.createJSONObject(strResult);
				data.put(object);
			}
			result.put(Constants.TOTAL, data.length());
			result.put(Constants.DATA, ParamUtil.getValuesByColumns(data));
			result.put(Constants.MESSAGE, MessageDiagram);
		}
		return result;
	}
	
	
	
	
	

	


	
	
	
}
