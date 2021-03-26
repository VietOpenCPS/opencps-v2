package org.opencps.backend.systemlogmgt.process;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;

import java.sql.SQLException;

import org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException;

/**
 * @author trungnt
 *
 */
public interface QueryProcessFactory {
	public JSONObject getSystemLogType1(Long logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws PortalException;
	public JSONObject getSystemLogType2(Long logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	public JSONObject getSystemLogType3(Long logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	public JSONObject getSystemLogType4(Long logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	public JSONObject getSystemLogType5(Long logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	public JSONObject getSystemLogType6(Long logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	public JSONObject getSystemLogType7(Long logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	public JSONObject getSystemLogType8(Long logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	public JSONObject getSystemLogType9(Long logId, String groupId, String moduleName, String strCreateDate, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId, int typeList) throws JSONException;
	
	public JSONObject createNewSystemLog(Long groupId, String moduleName, Integer preLine, String preMethod, Integer line, String method, String message, String type, String threadId) throws JSONException;

	public JSONObject deleteSystemLog(Long logId) throws PortalException;
}
