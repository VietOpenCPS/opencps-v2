package org.opencps.backend.systemlogmgt.util;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.QueryParam;

import org.opencps.backend.systemlogmgt.processimpl.QueryProcessFactoryImpl;
import org.opencps.backend.systemlogmgt.util.DatetimeUtil;
import org.opencps.backend.systemlogmgt.constant.Constants;
import org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException;

/**
 * @author trungnt
 *
 */
public class ActionUtil {

	private static Log _log = LogFactoryUtil.getLog(ActionUtil.class);

	public static JSONObject createResponseSchema(Long logId, String groupId, String moduleName, String createDate, Integer preLine, String preMethod, 
			Integer line, String method, String message, String type, String threadId, int typeList, String... msg) {
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

		result.put(Constants.CONDITIONS, condition);
		result.put(Constants.TOTAL, 0);
		result.put(Constants.DATA, JSONFactoryUtil.createJSONArray());
		result.put(Constants.TYPE, typeList);

		if (msg != null) {
			result.put(Constants.MESSAGES, StringUtil.merge(msg));
		}

		return result;
	}
	public static JSONObject getListSystemLog(Long logId, String groupId, String moduleName, Long createDate, Integer preLine, String preMethod, 
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
				return factory.getSystemLogType1(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==2) {
				return factory.getSystemLogType2(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==3) {
				return factory.getSystemLogType3(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==4) {
				return factory.getSystemLogType4(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==5) {
				return factory.getSystemLogType5(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==6) {
				return factory.getSystemLogType6(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==7) {
				return factory.getSystemLogType7(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==8) {
				return factory.getSystemLogType8(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			} else if(typeList==9) {
				return factory.getSystemLogType9(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			}
			else {
				return ActionUtil.createResponseSchema(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList);
			}
		} catch (Exception e) {
			_log.error(e);
			return ActionUtil.createResponseSchema(logId, groupId, moduleName, strCreateDate, preLine, preMethod, line, method, message, type, threadId, typeList, new String[] { e.getMessage() });
			
		}
	}
	public static JSONObject actionCreateNew(Long groupId, String moduleName, Integer preLine, String preMethod, 
			Integer line, String method, String message, String type, String threadId) {
		QueryProcessFactoryImpl factory = new QueryProcessFactoryImpl();
		try {
			return factory.createNewSystemLog(groupId, moduleName, preLine, preMethod, line, method, message, type, threadId);
		} catch (Exception e) {
			_log.error(e);
			return ActionUtil.createResponseSchema(null, groupId.toString(), moduleName, null, preLine, preMethod, line, method, message, type, threadId, 0, new String[] { e.getMessage() });
		}
	}
	public static JSONObject actionDelete(Long logId) {
		QueryProcessFactoryImpl factory = new QueryProcessFactoryImpl();
		try {
			return factory.deleteSystemLog(logId);
		} catch (Exception e) {
			_log.error(e);
			return ActionUtil.createResponseSchema(logId, null, null, null, null, null, null, null, null, null,null, 0, new String[] { e.getMessage() });
		}
	}
}
