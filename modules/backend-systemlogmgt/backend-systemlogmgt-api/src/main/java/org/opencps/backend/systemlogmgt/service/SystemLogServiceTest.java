package org.opencps.backend.systemlogmgt.service;

import org.opencps.backend.systemlogmgt.model.SystemLog;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface SystemLogServiceTest {
	public String test();
	
	public void debug(Long groupId, String moduleName, String message, String... param);
	public void error(Long groupId, String moduleName, String message, String... param);
	public void info(Long groupId, String moduleName, String message, String... param);
	public void warn(Long groupId, String moduleName, String message, String... param);
}
