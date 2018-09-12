package org.opencps.auth.api.keys;

public class ActionKeys extends com.liferay.portal.kernel.security.permission.ActionKeys{
	
	//define action key here
	public static final String SAMPLE_ACTION = "sample_action";
	public static final String AMINISTRATOR_ROLE_NAME = "Administrator";
	public static final String DATAADMIN_ROLE_NAME = "ADMINDATA";
	
	public static final String APPLICANT_CTZ = "citizen";
	public static final String APPLICANT_BUSINESS = "business";
	
	
	public static final String VIEW_EMPLOYEE = "VIEW_EMPLOYEE";
	
	public static final String UPDATE_EMPLOYEE = "UPDATE_EMPLOYEE";
	
	public static final String VIEW_PARTNER = "VIEW_PARTNER";
	
	public static final String UPDATE_PARTNER = "UPDATE_PARTNER";
	
	public static final String ADD_ACTIVITY_TEMPLATE = "ADD_ACTIVITY_TEMPLATE";
	
	public static final String ADD_DOCFILE_TEMPLATE = "ADD_DOCFILE_TEMPLATE";
	
	public static final String ADD_DOCARCHIVE_TEMPLATE = "ADD_DOCARCHIVE_TEMPLATE";
	
	public static final String ADD_PROJECT = "ADD_PROJECT";
	
	public static final String ADD_NEWSBOARD = "ADD_NEWSBOARD";

	public static final String EDIT_DATA = "EDIT_DATA";// QUYEN QUAN TRI DU LIEU

	private static final String[] LIST_PERMISSION = new String[] { "VIEW", "ADD_TO_PAGE", "CONFIGURATION", "PERMISSIONS",
			"PREFERENCES", "ACCESS_IN_CONTROL_PANEL", "VIEW_EMPLOYEE", "UPDATE_EMPLOYEE", "VIEW_PARTNER",
			"UPDATE_PARTNER", "ADD_ACTIVITY_TEMPLATE", "ADD_DOCFILE_TEMPLATE", "ADD_DOCARCHIVE_TEMPLATE", "ADD_PROJECT", "ADD_NEWSBOARD", "EDIT_DATA" };
	
	private static final String[] LIST_PERMISSION_DATA = new String[] { "VIEW_EMPLOYEE", "UPDATE_EMPLOYEE", "VIEW_PARTNER",
			"UPDATE_PARTNER", "ADD_ACTIVITY_TEMPLATE", "ADD_DOCFILE_TEMPLATE", "ADD_DOCARCHIVE_TEMPLATE", "ADD_PROJECT", "ADD_NEWSBOARD", "EDIT_DATA" };

	public static final String[] getListPermission() {
		return LIST_PERMISSION.clone();
	}

	public static final String[] getListPermissionData() {
		return LIST_PERMISSION_DATA.clone();
	}
}
