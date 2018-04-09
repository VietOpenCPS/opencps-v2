package org.opencps.synchronization.scheduler;

import org.opencps.synchronization.constants.SyncServerTerm;

public class SyncServerUtil {
	public static boolean isSyncOk(int status) {
		switch (status) {
			case 0:
			case SyncServerTerm.STATUS_DUPLICATE:
			case SyncServerTerm.STATUS_OK:
				return true;
			default:
				return false;
		}
	}


	public static boolean isSyncDeleteGroupOk(int status) {
		switch (status) {
			case 0:
			case SyncServerTerm.STATUS_DUPLICATE:
			case SyncServerTerm.STATUS_OK:
			case SyncServerTerm.STATUS_NOT_FOUND:
				return true;
			default:
				return false;
		}
	}
}
