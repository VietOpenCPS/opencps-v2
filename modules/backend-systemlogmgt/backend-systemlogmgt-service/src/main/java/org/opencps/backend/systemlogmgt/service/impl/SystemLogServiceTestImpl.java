package org.opencps.backend.systemlogmgt.service.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.backend.systemlogmgt.service.SystemLogLocalServiceUtil;
import org.opencps.backend.systemlogmgt.service.SystemLogServiceTest;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class SystemLogServiceTestImpl implements SystemLogServiceTest{

	public final static ThreadLocal<String> threadIdAuth = new ThreadLocal<>();
	
	@Override
	public String test() {
		return "Hello OSGI";
	}
	
	
	@BeanReference(type = SystemLogServiceTest.class)
	protected SystemLogServiceTest SystemLogServiceTest;



	@Override
	public void debug(Long groupId, String moduleName, String message, String... param) {
		// TODO Auto-generated method stub
		String type = Thread.currentThread().getStackTrace()[1].getMethodName();
		Integer preLine = Thread.currentThread().getStackTrace()[3].getLineNumber();
		String preMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
		Integer line = Thread.currentThread().getStackTrace()[2].getLineNumber();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String threadId = threadIdAuth.get();
		String paramInput = null;
		if(Validator.isNotNull(param)) {
			JSONArray dataParam = null;
			try {
				dataParam = JSONFactoryUtil.createJSONArray(StringUtil.merge(param));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			for(int i=0; i<dataParam.length(); i++) {
				JSONObject jsonObject = null;
				try {
					jsonObject = JSONFactoryUtil.createJSONObject(dataParam.get(i).toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if(i==0) {
					paramInput += jsonObject.get("key").toString() + "=" + jsonObject.get("value").toString();
				} else {
					paramInput += "&" + jsonObject.get("key").toString() + "=" + jsonObject.get("value").toString();
				}
			}
		}
		try {
			SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, message, type, threadId, paramInput);
		} catch (Exception e) {
			SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, e.toString(), "ERROR", threadId, paramInput);
		}
	}
	


	@Override
	public void error(Long groupId, String moduleName, String message, String... param) {
		// TODO Auto-generated method stub
		String type = Thread.currentThread().getStackTrace()[1].getMethodName();
		Integer preLine = Thread.currentThread().getStackTrace()[3].getLineNumber();
		String preMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
		Integer line = Thread.currentThread().getStackTrace()[2].getLineNumber();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String threadId = threadIdAuth.get();
		String paramInput = null;
		if(Validator.isNotNull(param)) {
			JSONArray dataParam = null;
			try {
				dataParam = JSONFactoryUtil.createJSONArray(StringUtil.merge(param));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			for(int i=0; i<dataParam.length(); i++) {
				JSONObject jsonObject = null;
				try {
					jsonObject = JSONFactoryUtil.createJSONObject(dataParam.get(i).toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if(i==0) {
					paramInput += jsonObject.get("key").toString() + "=" + jsonObject.get("value").toString();
				} else {
					paramInput += "&" + jsonObject.get("key").toString() + "=" + jsonObject.get("value").toString();
				}
			}
		}
		try {
			SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, message, type, threadId, paramInput);
		} catch (Exception e) {
			SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, e.toString(), "ERROR", threadId, paramInput);
		}
	}
	





	@Override
	public void info(Long groupId, String moduleName, String message, String... param) {
		// TODO Auto-generated method stub
		String type = Thread.currentThread().getStackTrace()[1].getMethodName();
		Integer preLine = Thread.currentThread().getStackTrace()[3].getLineNumber();
		String preMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
		Integer line = Thread.currentThread().getStackTrace()[2].getLineNumber();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String threadId = threadIdAuth.get();
		String paramInput = null;
		if(Validator.isNotNull(param)) {
			JSONArray dataParam = null;
			try {
				dataParam = JSONFactoryUtil.createJSONArray(StringUtil.merge(param));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			for(int i=0; i<dataParam.length(); i++) {
				JSONObject jsonObject = null;
				try {
					jsonObject = JSONFactoryUtil.createJSONObject(dataParam.get(i).toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if(i==0) {
					paramInput += jsonObject.get("key").toString() + "=" + jsonObject.get("value").toString();
				} else {
					paramInput += "&" + jsonObject.get("key").toString() + "=" + jsonObject.get("value").toString();
				}
			}
		}
		try {
			SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, message, type, threadId, paramInput);
		} catch (Exception e) {
			SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, e.toString(), "ERROR", threadId, paramInput);
		}
	}
	





	@Override
	public void warn(Long groupId, String moduleName, String message, String... param) {
		// TODO Auto-generated method stub
		String type = Thread.currentThread().getStackTrace()[1].getMethodName();
		Integer preLine = Thread.currentThread().getStackTrace()[3].getLineNumber();
		String preMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
		Integer line = Thread.currentThread().getStackTrace()[2].getLineNumber();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String threadId = threadIdAuth.get();
		String paramInput = null;
		if(Validator.isNotNull(param)) {
			JSONArray dataParam = null;
			try {
				dataParam = JSONFactoryUtil.createJSONArray(StringUtil.merge(param));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			for(int i=0; i<dataParam.length(); i++) {
				JSONObject jsonObject = null;
				try {
					jsonObject = JSONFactoryUtil.createJSONObject(dataParam.get(i).toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if(i==0) {
					if(Validator.isNotNull(jsonObject.get("key"))) {
						paramInput += jsonObject.get("key").toString() + "=" + jsonObject.get("value").toString();
					}
				} else {
					if(Validator.isNotNull(jsonObject.get("key"))) {
						paramInput += "&" + jsonObject.get("key").toString() + "=" + jsonObject.get("value").toString();
					}
				}
			}
		}
		try {
			SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, message, type, threadId, paramInput);
		} catch (Exception e) {
			SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, e.toString(), "ERROR", threadId, paramInput);
		}
	}
	
}
