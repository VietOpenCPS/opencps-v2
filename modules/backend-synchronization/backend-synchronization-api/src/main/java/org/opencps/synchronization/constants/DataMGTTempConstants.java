package org.opencps.synchronization.constants;

import com.liferay.portal.kernel.util.WebKeys;

import aQute.bnd.osgi.Constants;

public class DataMGTTempConstants implements Constants, WebKeys {	
	public static final int DATA_STATUS_DEACTIVE = 0;
	public static final int DATA_STATUS_ACTIVE = 1;
	public static final int DATA_STATUS_SYNCING = 2;
	
	public static final int DATA_MUST_SYNCHRONIZED = 1;
	public static final int DATA_MUST_NOT_SYNCHRONIZED = 0;

}
