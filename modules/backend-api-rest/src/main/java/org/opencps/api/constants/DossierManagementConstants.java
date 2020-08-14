package org.opencps.api.constants;

public class DossierManagementConstants {
	public static final String START_GRAPH = "graph TD\n";
	public static final String START_NODE = "(\"[";
	public static final String END_NOTE_TITLE = "] ";
	public static final String END_NODE = "\")\n";
	public static final String START_ARROW = "-->|\"";
	public static final String END_ARROW = "\"|";
	public static final String CR = "\n";
	public static final String GRAPH_STYLE = "style %s fill:#f9f,stroke:#333,stroke-width:4px";
	
	public static final String MSG_TO_ENGINE = "msgToEngine";
	public static final String DOSSIER_KEY = "dossier";
	public static final String DOSSIER_STATUS_KEY = "DOSSIER_STATUS";
	
	public static final String SPECIAL_ACTION_CODE = "9999";
	public static final String SPECIAL_ACTION_NAME = "Chuyển dịch đặc biệt";
	public static final String SET_PERMISSION_SUCCESS = "Phân Quyền thành công!!!";
	public static final String MODERATOR = "moderator";
	public static final String ASSIGNED = "assigned";
	public static final String TO_USERS = "toUsers";

	public static String mappingDossierStatusWithDVCQG(String dossierStatus) {
		switch (dossierStatus) {
			case "new":
				return "1";
			case "receiving":
				return "2";
			case "unresolved":
				return "3";
			case "processing":
				return "4";
			case "waiting":
				return "5";
			case "paying":
				return "6";
			case "cancelled":
				return "7";
			case "denied":
				return "8";
			case "releasing":
				return "9";
			case "done":
				return "10";
			default: return "11";
		}
	}
}
