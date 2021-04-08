package org.opencps.backend.systemlogmgt.service;

import org.opencps.backend.systemlogmgt.model.SystemLog;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface SystemLogServiceTest {
	public String test();
	
	public SystemLog debug(Long groupId, String moduleName, String message, String... param);
	public SystemLog error(Long groupId, String moduleName, String message, String... param);
	public SystemLog info(Long groupId, String moduleName, String message, String... param);
	public SystemLog warn(Long groupId, String moduleName, String message, String... param);
}
