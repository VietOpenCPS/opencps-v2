package org.opencps.backend.systemlogmgt.service.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.StringUtil;
import org.opencps.backend.systemlogmgt.constant.Constants;
import org.opencps.backend.systemlogmgt.model.SystemLog;
import org.opencps.backend.systemlogmgt.service.SystemLogLocalService;
import org.opencps.backend.systemlogmgt.service.SystemLogLocalServiceUtil;
import org.opencps.backend.systemlogmgt.service.SystemLogServiceTest;
import org.opencps.backend.systemlogmgt.service.persistence.SystemLogPersistence;
import org.opencps.backend.systemlogmgt.service.persistence.impl.SystemLogPersistenceImpl;

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
		String type = "DEBUG";
		Integer preLine = Thread.currentThread().getStackTrace()[3].getLineNumber();
		String preMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
		Integer line = Thread.currentThread().getStackTrace()[2].getLineNumber();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String threadId = threadIdAuth.get();
		String paramStr = null;
		if(Validator.isNotNull(param)) {
			paramStr = StringUtil.merge(param);
		}
		try {
			SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, message, type, threadId, paramStr);
		} catch (Exception e) {
			SystemLogLocalServiceUtil.addSystemLog(groupId, moduleName, preLine, preMethod, line, method, e.toString(), "ERROR", threadId, paramStr);
		}
	}


	@Override
	public void error(Long groupId, String moduleName, String message, String... param) {
		// TODO Auto-generated method stub
		String type = "ERROR";
		
	}





	@Override
	public void info(Long groupId, String moduleName, String message, String... param) {
		// TODO Auto-generated method stub
		String type = "INFO";
	}





	@Override
	public void warn(Long groupId, String moduleName, String message, String... param) {
		// TODO Auto-generated method stub
		String type = "WARN";
	}
	
}
