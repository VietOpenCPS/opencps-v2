package org.opencps.usermgt.constants;

import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * 
 * @author binhth
 * @see UserMGTConstants
 */
public class UserMGTConstants implements Constants, WebKeys {

	public static final String WORKINGUNIT_MGT_CENTER = "org_mobilink_workingunit_web_portlet_WorkingUnitMgtCenterPortlet";

	public static final String FILTER_BY_DEFAULT = "default";
	
	public static final int MAX_RESULT_COUNT = 15;
	
	public static final int MAX_RESULT_COUNT_REGULAR = 10;
	
	public static final int MAX_RESULT_COUNT_MINIMUM = 5;

	public static final String SUCCESS = "success";

	public static final String DELETE_SUCCESS = "delete_success";

	public static final String MVCPATH = "mvcPath";

	public static final String PORTLET_NAME = "portletName";
	
	public static final String MANAGER_EMPLOYEE = "MANAGER_EMPLOYEE";
	
	public static final String VIEW_EMPLOYEE = "VIEW_EMPLOYEE";
	
	public static final String MOVE_EMPLOYEE = "MOVE_EMPLOYEE";
	
	public static final String EDIT_DATA = "EDIT_DATA";
	
	private static final String[] LIST_PERMISSION = new String[] {"VIEW", "ADD_TO_PAGE", "CONFIGURATION", "PERMISSIONS", "PREFERENCES", "ACCESS_IN_CONTROL_PANEL", "VIEW_EMPLOYEE", "MANAGER_EMPLOYEE", "MOVE_EMPLOYEE", "EDIT_DATA"};

	public static final String[] getListPermission() {
		return LIST_PERMISSION.clone();
	}
}
