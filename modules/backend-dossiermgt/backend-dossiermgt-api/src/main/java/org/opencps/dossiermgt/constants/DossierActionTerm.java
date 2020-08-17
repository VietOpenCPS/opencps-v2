package org.opencps.dossiermgt.constants;

public class DossierActionTerm {
	public static final String DOSSIERACTION_ID = "dossierActionId";
	public static final String GROUP_ID = "groupId";
	public static final String USER_ID = "userId";
	public static final String CREATE_DATE = "createDate";
	public static final String MODIFIED_DATE = "modifiedDate";
	public static final String DOSSIER_ID = "dossierId";
	public static final String DOSSIER_NO = "dossierNo";
	public static final String SERVICE_PROCESS_ID = "serviceProcessId";
	public static final String PREVIOUS_ACTION_ID = "previousActionId";
	public static final String ACTION_CODE = "actionCode";
	public static final String ACTION_USER = "actionUser";
	public static final String ACTION_NAME = "actionName";
	public static final String ACTION_NOTE = "actionNote";
	public static final String ACTION_OVER_DUE = "actionOverdue";
	public static final String SYNC_ACTION_CODE = "syncActionCode";
	public static final String PENDING = "pending";
	public static final String ROLLBACK_ABLE = "rollbackable";
	public static final String STEP_CODE = "stepCode";
	public static final String STEP_NAME = "stepName";
	public static final String DUE_DATE = "dueDate";
	public static final String NEXT_ACTION_ID = "nextActionId";
	public static final String PAYLOAD = "payload";
	public static final String STEP_INSTRUCTION = "stepInstruction";
	public static final String AUTO = "auto";
	public static final String DOSSIER_DOCUMENT_ID = "dossierDocumentId";
	public static final String JOB_POS_NAME = "jobPosName";
	public static final String FILES = "files";
	public static final String RETURN_FILES = "returnFiles";
	public static final String CREATE_FILES = "createFiles";
	public static final String ACTIONS = "actions";
	public static final String STATE = "state";
	public static final String FROM_STEP_CODE = "fromStepCode";
	public static final String FROM_STEP_NAME = "fromStepName";
	public static final String LIST_CONTACT = "ListContacts";
	public static final String FROM_SEQUENCE_NO = "fromSequenceNo";
	public static final String SEQUENCE_NO = "sequenceNo";
	public static final String EVENT_STATUS = "eventStatus";
	public static final int STATE_WAITING_PROCESSING = 0;
	public static final int STATE_ALREADY_PROCESSED = 1;
	public static final int STATE_ROLLBACK = 2;
	
	public static final int EVENT_STATUS_NOT_CREATED = 0;
	public static final int EVENT_STATUS_WAIT_SENDING = 1;
	public static final int EVENT_STATUS_SENDED_SUCCESS = 2;
	public static final int EVENT_STATUS_SENDED_ERROR = 3;
	
	public static final String OUTSIDE_ACTION_9100 = "9100";
	public static final String OUTSIDE_ACTION_ROLLBACK = "9000";
	
	public static final String publishImportDossier_fromStepCode = "300";
	public static final String publishImportDossier_fromStepName = "Trả kết quả";
	public static final String publishImportDossier_fromSequenceNo = "04";
	public static final String publishImportDossier_actionCode = "4000";
	public static final String publishImportDossier_actionUser = "Tiếp nhận";
	public static final String publishImportDossier_actionName = "Trả hoàn thiện";
	public static final String publishImportDossier_stepCode = "400";
	public static final String publishImportDossier_stepName = "Hoàn thành";
	
	public static final String DONE_STEP = "400";
	public static final String ASSIGN_USERS = "assignUsers";
	public static final String TIMMER = "timmer";
	public static final String REJECT_CANCELLING = "reject_cancelling";
	public static final String CANCELLING = "cancelling";
	public static final String REJECT_SUBMITTING = "reject_submitting";
	public static final String SUBMITTING = "submitting";
	public static final String REJECT_CORRECTING = "reject_correcting";
	public static final String CORRECTING = "correcting";
	public static final String LST_USER = "lstUser";
}
