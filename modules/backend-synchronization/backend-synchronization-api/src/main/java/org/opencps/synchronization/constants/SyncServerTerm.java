package org.opencps.synchronization.constants;

public class SyncServerTerm {
	public static final String SYNC_SERVER_TYPE = "SYNC_SERVER";
	public static final String SERVER_TYPE = "serverType";
	public static final String SERVER_USERNAME = "username";
	public static final String SERVER_SECRET = "password";
	public static final String SERVER_URL = "url";
	public static final String SERVER_GROUP_ID = "groupId";
	public static final String SERVER_EXCLUDES = "excludes";
	
	public static final String METHOD_CREATE = "create";
	public static final String METHOD_UPDATE = "update";
	public static final String METHOD_DELETE = "delete";
	public static final String METHOD_UPDATE_DATAFORM = "update_dataform";
	public static final String METHOD_UPDATE_METADATA = "update_metadata";
	
	public static final String METHOD_ADD_TO_GROUP = "add_to_group";
	public static final String METHOD_REMOVE_FROM_GROUP = "remove_from_group";
	
	public static final int SERVER_INACTIVE = 0;
	public static final int SERVER_ONLINE = 1;
	public static final int SERVER_OFFLINE = 2;
	
	public static final int STATUS_DUPLICATE = 409;
	public static final int STATUS_NOT_FOUND = 404;
	public static final int STATUS_OK = 200;
	
	public static final String PULL = "isPull";
	public static final String PUSH = "isPush";
	
	public static final int PRIORITY_HIGHEST = 0;
	public static final int PRIORIOTY_HIGH = 1;
	public static final int PRIORITY_LOW = 2;
	public static final int PRIORITY_LOWEST = 3;
	
	public static final int QUEUE_STATUS_NEW = 0;
	public static final int QUEUE_STATUS_PROCESSING = 1;
	public static final int QUEUE_STATUS_FAILED = 2;
}
