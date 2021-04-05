package org.opencps.backend.systemlogmgt.service.impl;

import com.liferay.portal.kernel.bean.BeanReference;

import org.opencps.backend.systemlogmgt.service.SystemLogLocalService;
import org.opencps.backend.systemlogmgt.service.SystemLogServiceTest;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class SystemLogServiceTestImpl implements SystemLogServiceTest{

	@Override
	public String test() {
		// TODO Auto-generated method stub
		return "Hello OSGI";
	}

	
	@BeanReference(type = SystemLogServiceTest.class)
	protected SystemLogLocalService SystemLogServiceTest;
}
