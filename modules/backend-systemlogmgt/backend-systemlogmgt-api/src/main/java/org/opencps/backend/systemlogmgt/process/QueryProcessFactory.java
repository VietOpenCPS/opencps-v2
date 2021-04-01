package org.opencps.backend.systemlogmgt.process;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;

import java.sql.SQLException;
import java.util.Date;

import org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException;

/**
 * @author trungnt
 *
 */
public interface QueryProcessFactory {
	public JSONObject getAllSystemLog() throws JSONException;
	public JSONObject findSystemLog(Date fromDate, Date toDate, String preMethods, String methods, String types, String threadId) throws JSONException;
	public JSONObject generateDiagram(String threadId) throws JSONException;
	
	public JSONObject getSystemLogType1(String logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws PortalException;
	public JSONObject getSystemLogType2(String logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	public JSONObject getSystemLogType3(String logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	public JSONObject getSystemLogType4(String logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	public JSONObject getSystemLogType5(String logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	public JSONObject getSystemLogType6(String logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	public JSONObject getSystemLogType7(String logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	public JSONObject getSystemLogType8(String logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	
	public JSONObject createNewSystemLog(Long groupId, String moduleName, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId) throws JSONException;

	public JSONObject deleteSystemLog(Long logId) throws PortalException;
}
