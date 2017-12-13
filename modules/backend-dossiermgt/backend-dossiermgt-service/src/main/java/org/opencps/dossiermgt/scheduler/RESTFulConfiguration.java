package org.opencps.dossiermgt.scheduler;

import org.opencps.backend.dossiermgt.service.util.ConfigProps;

import com.liferay.portal.kernel.util.PropsUtil;

public class RESTFulConfiguration {
	
	public static final String STATUS = "status";
	public static final String MESSAGE = "message";
	
	public static final String SUBMIT = "submit";
	public static final String TIMER = "timer";
	
	//ServerConfig
	public static final String SERVER_USER = PropsUtil.get(ConfigProps.SERVER_USER);
	public static final String SERVER_PASS = PropsUtil.get(ConfigProps.SERVER_PASS);
	public static final String SERVER_PATH_BASE = PropsUtil.get(ConfigProps.SERVER_PATH_BASE);
	
	
	//ClientConfig
	public static final String CLIENT_USER = PropsUtil.get(ConfigProps.CLIENT_USER);
	public static final String CLIENT_PASS = PropsUtil.get(ConfigProps.CLIENT_PASS);
	public static final String CLIENT_PATH_BASE = PropsUtil.get(ConfigProps.CLIENT_PATH_BASE);

}
