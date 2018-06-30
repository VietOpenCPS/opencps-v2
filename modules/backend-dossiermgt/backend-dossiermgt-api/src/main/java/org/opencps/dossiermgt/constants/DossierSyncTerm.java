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
}
