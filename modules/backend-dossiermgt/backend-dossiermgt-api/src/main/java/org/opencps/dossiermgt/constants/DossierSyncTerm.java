package org.opencps.dossiermgt.constants;

public class DossierSyncTerm {

	public static final String SYNC_TYPE = "syncType";
	public static final String USER_ID = "userId";
	public static final String ACTION_CODE = "actionCode";
	
	public static final int STATE_NOT_SYNC = 0;
	public static final int STATE_WAITING_SYNC = 1;
	public static final int STATE_ALREADY_SENT = 2;
	public static final int STATE_RECEIVED_ACK = 3;
	public static final int STATE_ACK_ERROR = 4;
	
	public static final int SYNCTYPE_REQUEST = 1;
	public static final int SYNCTYPE_INFORM = 2;
	
	public static final String SERVER_CONFIG_PROTOCOL_API = "API_SYNC";
	public static final String SERVER_CONFIG_PROTOCOL_MESSAGE = "MESSAGE_SYNC";
	public static final String SERVER_CONFIG_USERNAME = "username";
	public static final String SERVER_CONFIG_SECRET = "password";
	public static final String SERVER_CONFIG_URL = "url";
	public static final String SERVER_CONFIG_GROUP_ID = "groupId";
	
	public static final String PAYLOAD_SYNC_FILES = "dossierFiles";
	public static final String PAYLOAD_SYNC_DOCUMENTS = "dossierDocuments";
	public static final int MAX_RETRY = 10;
}
