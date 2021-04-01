package org.opencps.backend.systemlogmgt.util;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.QueryParam;

import org.opencps.backend.systemlogmgt.processimpl.QueryProcessFactoryImpl;
import org.opencps.backend.systemlogmgt.util.DatetimeUtil;
import org.opencps.backend.systemlogmgt.application.SystemLogApplication;
import org.opencps.backend.systemlogmgt.constant.Constants;
import org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException;
import org.opencps.backend.systemlogmgt.model.SystemLog;

/**
 * @author trungnt
 *
 */
public class ActionUtil {

	private static Log _log = LogFactoryUtil.getLog(ActionUtil.class);
	
	public static JSONObject createResponseSchema(String logId, String groupId, String moduleName, String createDate, Integer preLine, String preMethod, 
			Integer line, String method, String message, String type, String threadId, String fromDate, String toDate, int typeList, String... msg) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONObject condition = JSONFactoryUtil.createJSONObject();
		
		condition.put(Constants.LOGID, logId);
		condition.put(Constants.GROUPID, groupId);
		condition.put(Constants.MODULENAME, moduleName);
		condition.put(Constants.CREATE_DATE, createDate);
		condition.put(Constants.PRELINE, preLine);
		condition.put(Constants.PREMETHOD, preMethod);
		condition.put(Constants.LINE, line);
		condition.put(Constants.METHOD, method);
		condition.put(Constants.MESSAGE, message);
		condition.put(Constants.TYPE, type);
		condition.put(Constants.THREADID, threadId);
		condition.put(Constants.FROMDATE, fromDate);
		condition.put(Constants.TODATE, toDate);

		result.put(Constants.CONDITIONS, condition);
		result.put(Constants.TOTAL, 0);
		result.put(Constants.DATA, JSONFactoryUtil.createJSONArray());
		result.put(Constants.TYPE, typeList);

		if (msg != null) {
			result.put(Constants.MESSAGES, StringUtil.merge(msg));
		}

		return result;
	}
	public static JSONObject execDiagram(String threadId) {
		
		QueryProcessFactoryImpl factory = new QueryProcessFactoryImpl();
		if(Validator.isNotNull(threadId)) {
			try {
				return factory.generateDiagram(threadId);
			} catch (Exception e) {
				_log.error(e);
				return ActionUtil.createResponseSchema(null, null, null, null, null, null, null, null, null, null, threadId, null, null, 0, new String[] { e.getMessage() });
			}
		}
		return null;
		
	}
	
	public static JSONObject findSystemLog(Long fromDate, Long toDate, String preMethods, String methods, String types, String threadId) {
		
		QueryProcessFactoryImpl factory = new QueryProcessFactoryImpl();
		
		if(Validator.isNull(fromDate) && Validator.isNull(toDate)) {
			try {
				return factory.getAllSystemLog();
			} catch (JSONException e) {
				_log.error(e);
				return ActionUtil.createResponseSchema(null, null, null, null, null, null, null, null, null, null, null, fromDate.toString(), toDate.toString(), 0, new String[] { e.getMessage() });
			}
		}
		Date fromDateTime = null;
		Date toDateTime = null;
		if(Validator.isNotNull(fromDate)) {
			fromDateTime = new Timestamp(fromDate); 
		}
		if(Validator.isNotNull(toDate)) {
			toDateTime = new Timestamp(toDate); 
		}
		try {
			return factory.findSystemLog(fromDateTime, toDateTime, preMethods, methods, types, threadId);
		} catch (Exception e) {
			_log.error(e);
			return ActionUtil.createResponseSchema(null, null, null, null, null, null, null, null, null, null, null, fromDate.toString(), toDate.toString(), 0, new String[] { e.getMessage() });
		
		}
		
		
		
	}
	public static JSONObject getListSystemLog(String logId, String groupId, String moduleName, Long createDate, Integer preLine, String preMethod, 
			Integer line, String method, String message, String type, String threadId, int typeList){
		String strCreateDate;
		if(createDate != null) {
			strCreateDate = DatetimeUtil.convertTimestampToStringDatetime(createDate, DatetimeUtil._YYYY_MM_DD);
		} else {
			strCreateDate = null;
		}
		QueryProcessFactoryImpl factory = new QueryProcessFactoryImpl();
		
		try {
			if(typeList==1) {
				factory.createNewSystemLog(5L, SystemLog.class.toString(), Thread.currentThread().getStackTrace()[2].getLineNumber(),
						Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), 
						Thread.currentThread().getStackTrace()[1].getMethodName(), "factory.getSystemLogType"+typeList, "List Type " + typeList , SystemLogApplication.threadIdContext.get());
				return factory.getSystemLogType1(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==2) {
				factory.createNewSystemLog(5L, SystemLog.class.toString(), Thread.currentThread().getStackTrace()[2].getLineNumber(),
						Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), 
						Thread.currentThread().getStackTrace()[1].getMethodName(), "factory.getSystemLogType"+typeList, "List Type " + typeList , SystemLogApplication.threadIdContext.get());
				return factory.getSystemLogType2(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==3) {
				factory.createNewSystemLog(5L, SystemLog.class.toString(), Thread.currentThread().getStackTrace()[2].getLineNumber(),
						Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), 
						Thread.currentThread().getStackTrace()[1].getMethodName(), "factory.getSystemLogType"+typeList, "List Type " + typeList , SystemLogApplication.threadIdContext.get());
				return factory.getSystemLogType3(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==4) {
				factory.createNewSystemLog(5L, SystemLog.class.toString(), Thread.currentThread().getStackTrace()[2].getLineNumber(),
						Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), 
						Thread.currentThread().getStackTrace()[1].getMethodName(), "factory.getSystemLogType"+typeList, "List Type " + typeList , SystemLogApplication.threadIdContext.get());
				return factory.getSystemLogType4(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==5) {
				factory.createNewSystemLog(5L, SystemLog.class.toString(), Thread.currentThread().getStackTrace()[2].getLineNumber(),
						Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), 
						Thread.currentThread().getStackTrace()[1].getMethodName(), "factory.getSystemLogType"+typeList, "List Type " + typeList , SystemLogApplication.threadIdContext.get());
				return factory.getSystemLogType5(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==6) {
				factory.createNewSystemLog(5L, SystemLog.class.toString(), Thread.currentThread().getStackTrace()[2].getLineNumber(),
						Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), 
						Thread.currentThread().getStackTrace()[1].getMethodName(), "factory.getSystemLogType"+typeList, "List Type " + typeList , SystemLogApplication.threadIdContext.get());
				return factory.getSystemLogType6(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==7) {
				factory.createNewSystemLog(5L, SystemLog.class.toString(), Thread.currentThread().getStackTrace()[2].getLineNumber(),
						Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), 
						Thread.currentThread().getStackTrace()[1].getMethodName(), "factory.getSystemLogType"+typeList, "List Type " + typeList , SystemLogApplication.threadIdContext.get());
				return factory.getSystemLogType7(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==8) {
				factory.createNewSystemLog(5L, SystemLog.class.toString(), Thread.currentThread().getStackTrace()[2].getLineNumber(),
						Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), 
						Thread.currentThread().getStackTrace()[1].getMethodName(), "factory.getSystemLogType"+typeList, "List Type " + typeList , SystemLogApplication.threadIdContext.get());
				return factory.getSystemLogType8(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} 
			else {
				return ActionUtil.createResponseSchema(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, null, null, typeList);
			}
		} catch (Exception e) {
			_log.error(e);
			return ActionUtil.createResponseSchema(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, null, null, typeList, new String[] { e.getMessage() });
			
		}
	}
	public static JSONObject actionCreateNew(Long groupId, String moduleName, Integer preLine, String preMethod, 
			Integer line, String method, String message, String type, String threadId) {
		QueryProcessFactoryImpl factory = new QueryProcessFactoryImpl();
		try {
			return factory.createNewSystemLog(groupId, moduleName, preLine, preMethod, line, method, message, type, threadId);
		} catch (Exception e) {
			_log.error(e);
			return ActionUtil.createResponseSchema(null, groupId.toString(), moduleName, null, preLine, preMethod, line, method, message, type, threadId, null, null, 0, new String[] { e.getMessage() });
		}
	}
	public static JSONObject actionDelete(Long logId) {
		QueryProcessFactoryImpl factory = new QueryProcessFactoryImpl();
		try {
			factory.createNewSystemLog(5L, SystemLog.class.toString(), Thread.currentThread().getStackTrace()[2].getLineNumber(),
					Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), 
					Thread.currentThread().getStackTrace()[1].getMethodName(), "factory.actionDelete logId = "+logId, "Delete" , SystemLogApplication.threadIdContext.get());
			return factory.deleteSystemLog(logId);
		} catch (Exception e) {
			_log.error(e);
			return ActionUtil.createResponseSchema(String.valueOf(logId), null, null, null, null, null, null, null, null, null,null, null, null, 0, new String[] { e.getMessage() });
		}
	}
}
