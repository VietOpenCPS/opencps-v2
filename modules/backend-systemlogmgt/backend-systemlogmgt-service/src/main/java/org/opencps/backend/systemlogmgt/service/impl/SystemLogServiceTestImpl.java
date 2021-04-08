package org.opencps.backend.systemlogmgt.service.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.UUID;

import org.opencps.backend.systemlogmgt.model.SystemLog;
import org.opencps.backend.systemlogmgt.service.SystemLogLocalServiceUtil;
import org.opencps.backend.systemlogmgt.service.SystemLogServiceTest;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class SystemLogServiceTestImpl implements SystemLogServiceTest{

	public final static ThreadLocal<String> threadIdContext = new ThreadLocal<>();
	public static long threadId = 0;
	
	@Override
	public String test() {
		return "Hello OSGI";
	}
	
	
	@BeanReference(type = SystemLogServiceTest.class)
	protected SystemLogServiceTest SystemLogServiceTest;



	@Override
	public SystemLog debug(Long groupId, String moduleName, String message, String... param) {
		
		if(Thread.currentThread().getId()!=threadId) {
			threadIdContext.remove();
			threadIdContext.set(UUID.randomUUID().toString());
			threadId = Thread.currentThread().getId();
		}
		// TODO Auto-generated method stub
		String type = Thread.currentThread().getStackTrace()[1].getMethodName();
		Integer preLine = Thread.currentThread().getStackTrace()[3].getLineNumber();
		String preMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
		Integer line = Thread.currentThread().getStackTrace()[2].getLineNumber();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String threadId = threadIdContext.get();
		String paramInput = "";
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
						paramInput += jsonObject.get("key").toString();
					} else {
						paramInput += "null";
					}
					if(Validator.isNotNull(jsonObject.get("value"))) {
						paramInput += "=" + jsonObject.get("value").toString();
					} else {
						paramInput += "=null";
					}
				} else {
					if(Validator.isNotNull(jsonObject.get("key"))) {
						paramInput += "&"+jsonObject.get("key").toString();
					} else {
						paramInput += "&null";
					}
					if(Validator.isNotNull(jsonObject.get("value"))) {
						paramInput += "=" + jsonObject.get("value").toString();
					} else {
						paramInput += "=null";
					}
				}
			}
		}
		try {
			return SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, message, type, threadId, paramInput);
		} catch (Exception e) {
			return SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, e.toString(), "ERROR", threadId, paramInput);
		}
	}
	


	@Override
	public SystemLog error(Long groupId, String moduleName, String message, String... param) {
		if(Thread.currentThread().getId()!=threadId) {
			threadIdContext.remove();
			threadIdContext.set(UUID.randomUUID().toString());
			threadId = Thread.currentThread().getId();
		}
		// TODO Auto-generated method stub
		String type = Thread.currentThread().getStackTrace()[1].getMethodName();
		Integer preLine = Thread.currentThread().getStackTrace()[3].getLineNumber();
		String preMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
		Integer line = Thread.currentThread().getStackTrace()[2].getLineNumber();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String threadId = threadIdContext.get();
		String paramInput = "";
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
						paramInput += jsonObject.get("key").toString();
					} else {
						paramInput += "null";
					}
					if(Validator.isNotNull(jsonObject.get("value"))) {
						paramInput += "=" + jsonObject.get("value").toString();
					} else {
						paramInput += "=null";
					}
				} else {
					if(Validator.isNotNull(jsonObject.get("key"))) {
						paramInput += "&"+jsonObject.get("key").toString();
					} else {
						paramInput += "&null";
					}
					if(Validator.isNotNull(jsonObject.get("value"))) {
						paramInput += "=" + jsonObject.get("value").toString();
					} else {
						paramInput += "=null";
					}
				}
			}
		}
		try {
			return SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, message, type, threadId, paramInput);
		} catch (Exception e) {
			return SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, e.toString(), "ERROR", threadId, paramInput);
		}
	}
	





	@Override
	public SystemLog info(Long groupId, String moduleName, String message, String... param) {
		if(Thread.currentThread().getId()!=threadId) {
			threadIdContext.remove();
			threadIdContext.set(UUID.randomUUID().toString());
			threadId = Thread.currentThread().getId();
		}
		// TODO Auto-generated method stub
		String type = Thread.currentThread().getStackTrace()[1].getMethodName();
		Integer preLine = Thread.currentThread().getStackTrace()[4].getLineNumber();
		String preMethod = Thread.currentThread().getStackTrace()[4].getMethodName();
		Integer line = Thread.currentThread().getStackTrace()[3].getLineNumber();
		String method = Thread.currentThread().getStackTrace()[3].getMethodName();
		String threadId = threadIdContext.get();
		String paramInput = "";
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
						paramInput += jsonObject.get("key").toString();
					} else {
						paramInput += "null";
					}
					if(Validator.isNotNull(jsonObject.get("value"))) {
						paramInput += "=" + jsonObject.get("value").toString();
					} else {
						paramInput += "=null";
					}
				} else {
					if(Validator.isNotNull(jsonObject.get("key"))) {
						paramInput += "&"+jsonObject.get("key").toString();
					} else {
						paramInput += "&null";
					}
					if(Validator.isNotNull(jsonObject.get("value"))) {
						paramInput += "=" + jsonObject.get("value").toString();
					} else {
						paramInput += "=null";
					}
				}
			}
		}
		try {
			return SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, message, type, threadId, paramInput);
		} catch (Exception e) {
			return SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, e.toString(), "ERROR", threadId, paramInput);
		}
	}
	





	@Override
	public SystemLog warn(Long groupId, String moduleName, String message, String... param) {
		if(Thread.currentThread().getId()!=threadId) {
			threadIdContext.remove();
			threadIdContext.set(UUID.randomUUID().toString());
			threadId = Thread.currentThread().getId();
		}
		// TODO Auto-generated method stub
		String type = Thread.currentThread().getStackTrace()[1].getMethodName();
		Integer preLine = Thread.currentThread().getStackTrace()[3].getLineNumber();
		String preMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
		Integer line = Thread.currentThread().getStackTrace()[2].getLineNumber();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		threadIdContext.set(UUID.randomUUID().toString());
		String threadId = threadIdContext.get();
		String paramInput = "";
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
						paramInput += jsonObject.get("key").toString();
					} else {
						paramInput += "null";
					}
					if(Validator.isNotNull(jsonObject.get("value"))) {
						paramInput += "=" + jsonObject.get("value").toString();
					} else {
						paramInput += "=null";
					}
				} else {
					if(Validator.isNotNull(jsonObject.get("key"))) {
						paramInput += "&"+ jsonObject.get("key").toString();
					} else {
						paramInput += "&null";
					}
					if(Validator.isNotNull(jsonObject.get("value"))) {
						paramInput += "=" + jsonObject.get("value").toString();
					} else {
						paramInput += "=null";
					}
				}
			}
		}
		try {
			return SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, message, type, threadId, paramInput);
		} catch (Exception e) {
			return SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, e.toString(), "ERROR", threadId, paramInput);
		}
	}



}
