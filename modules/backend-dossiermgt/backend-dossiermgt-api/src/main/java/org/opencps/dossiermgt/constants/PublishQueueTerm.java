package org.opencps.dossiermgt.constants;

public class PublishQueueTerm {
	public static final int STATE_NOT_SYNC = 0;
	public static final int STATE_WAITING_SYNC = 1;
	public static final int STATE_ALREADY_SENT = 2;
	public static final int STATE_RECEIVED_ACK = 3;
	public static final int STATE_ACK_ERROR = 4;
	
	public static final int MAX_RETRY = 10;
}
