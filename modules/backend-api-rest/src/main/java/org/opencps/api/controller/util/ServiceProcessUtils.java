package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.serviceprocess.model.ProcessActionDataModel;
import org.opencps.api.serviceprocess.model.ProcessActionReturnModel;
import org.opencps.api.serviceprocess.model.ProcessStepDataModel;
import org.opencps.api.serviceprocess.model.ProcessStepInputModel;
import org.opencps.api.serviceprocess.model.RoleDataModel;
import org.opencps.api.serviceprocess.model.RoleInputModel;
import org.opencps.api.serviceprocess.model.ServiceProcessDataModel;
import org.opencps.api.serviceprocess.model.ServiceProcessDetailModel;
import org.opencps.api.serviceprocess.model.ServiceProcessInputModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.ProcessStepTerm;
import org.opencps.dossiermgt.constants.ServiceProcessTerm;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;

public class ServiceProcessUtils {

	public static List<ServiceProcessDataModel> mappingToServiceProcessData(List<Document> documents) {

		List<ServiceProcessDataModel> outputs = new ArrayList<ServiceProcessDataModel>();

		for (Document doc : documents) {
			ServiceProcessDataModel model = new ServiceProcessDataModel();

			model.setServiceProcessId(GetterUtil.getInteger(doc.get(Field.ENTRY_CLASS_PK)));
			model.setCreateDate(doc.get(Field.CREATE_DATE));
			model.setModifiedDate(doc.get(Field.MODIFIED_DATE));
			model.setProcessNo(doc.get(ServiceProcessTerm.PROCESS_NO));
			model.setProcessName(doc.get(ServiceProcessTerm.PROCESS_NAME));
			model.setDescription(doc.get(ServiceProcessTerm.DESCRIPTION));
			model.setDurationCount(GetterUtil.getDouble(doc.get(ServiceProcessTerm.DURATION_COUNT)));
			model.setDurationUnit(GetterUtil.getInteger(doc.get(ServiceProcessTerm.DURATION_UNIT)));
			model.setCounter(GetterUtil.getInteger(doc.get(ServiceProcessTerm.COUNTER)));
			model.setGenerateDossierNo(doc.get(ServiceProcessTerm.GENERATE_DOSSIER_NO));
			model.setDossierNoPattern(doc.get(ServiceProcessTerm.DOSSIER_NO_PATTERN));
			model.setGenerateDueDate(doc.get(ServiceProcessTerm.GENERATE_DUE_DATE));
			model.setDueDatePattern(doc.get(ServiceProcessTerm.DUEDATE_PATTERN));
			model.setGeneratePassword(doc.get(ServiceProcessTerm.GENERATE_SECRET));
			model.setDirectNotification(doc.get(ServiceProcessTerm.DIRECT_NOTIFICATION));
			model.setServerNo(doc.get(ServiceProcessTerm.SERVER_NO));
			model.setServerName(doc.get(ServiceProcessTerm.SERVER_NAME));
			model.setPaymentFee(doc.get(ServiceProcessTerm.PAYMENT_FEE));

			outputs.add(model);
		}

		return outputs;
	}

	public static ServiceProcessInputModel mappingToPOST(ServiceProcess serviceProcess) {
		ServiceProcessInputModel output = new ServiceProcessInputModel();

		output.setProcessNo(serviceProcess.getProcessNo());
		output.setProcessName(serviceProcess.getProcessName());
		output.setDescription(serviceProcess.getDescription());
		output.setDurationCount(serviceProcess.getDurationCount());
		output.setDurationUnit(serviceProcess.getDurationUnit());
		output.setCounter(GetterUtil.getInteger(serviceProcess.getCounter()));
		output.setGenerateDossierNo(Boolean.toString(serviceProcess.getGenerateDossierNo()));
		output.setDossierNoPattern(serviceProcess.getDossierNoPattern());
		output.setGenerateDueDate(Boolean.toString(serviceProcess.getGenerateDueDate()));
		output.setDueDatePattern(serviceProcess.getDueDatePattern());
		output.setGeneratePassword(Boolean.toString(serviceProcess.getGeneratePassword()));
		output.setDirectNotification(Boolean.toString(serviceProcess.getDirectNotification()));
		output.setServerNo(serviceProcess.getServerNo());

		return output;


	}

	public static ServiceProcessDetailModel mappingToDetail(ServiceProcess serviceProcess) {
		ServiceProcessDetailModel output = new ServiceProcessDetailModel();

		output.setServiceProcessId(GetterUtil.getInteger(serviceProcess.getPrimaryKey()));
		output.setCreateDate(APIDateTimeUtils.convertDateToString(serviceProcess.getCreateDate(),
				APIDateTimeUtils._NORMAL_PARTTERN));
		output.setModifiedDate(APIDateTimeUtils.convertDateToString(serviceProcess.getModifiedDate(),
				APIDateTimeUtils._NORMAL_PARTTERN));
		output.setProcessNo(serviceProcess.getProcessNo());
		output.setProcessName(serviceProcess.getProcessName());
		output.setDescription(serviceProcess.getDescription());
		output.setDurationCount(serviceProcess.getDurationCount());
		output.setDurationUnit(serviceProcess.getDurationUnit());
		output.setCounter(GetterUtil.getInteger(serviceProcess.getCounter()));
		output.setGenerateDossierNo(Boolean.toString(serviceProcess.getGenerateDossierNo()));
		output.setDossierNoPattern(serviceProcess.getDossierNoPattern());
		output.setGenerateDueDate(Boolean.toString(serviceProcess.getGenerateDueDate()));
		output.setDueDatePattern(serviceProcess.getDueDatePattern());
		output.setGeneratePassword(Boolean.toString(serviceProcess.getGeneratePassword()));
		output.setDirectNotification(Boolean.toString(serviceProcess.getDirectNotification()));
		output.setServerNo(serviceProcess.getServerNo());
		output.setPaymentFee(serviceProcess.getPaymentFee());
		
		ServerConfig server = ServerConfigLocalServiceUtil.getByCode(serviceProcess.getServerNo());
		
		String serverName = StringPool.BLANK;
		
		if (Validator.isNotNull(server))
			serverName = server.getServerName();

		output.setServerName(serverName);

		return output;


	}

	public static List<RoleDataModel> mappingToServiceRole(List<ServiceProcessRole> processRoles) {

		List<RoleDataModel> outputs = new ArrayList<RoleDataModel>();

		for (ServiceProcessRole role : processRoles) {
			RoleDataModel model = new RoleDataModel();

			model.setRoleId(GetterUtil.getInteger(role.getRoleId()));
			model.setRoleName(_getRoleName(role.getRoleId()));
//			model.setRoleName(role.getRoleName());
			model.setCondition(role.getCondition());
			model.setModerator(Boolean.toString(role.getModerator()));
			model.setRoleCode(role.getRoleCode());
			
			outputs.add(model);
		}

		return outputs;
	}

	public static List<RoleDataModel> mappingToStepRole(List<ProcessStepRole> stepRoles) {
		List<RoleDataModel> outputs = new ArrayList<RoleDataModel>();

		for (ProcessStepRole role : stepRoles) {

			RoleDataModel model = new RoleDataModel();

			model.setRoleId(GetterUtil.getInteger(role.getRoleId()));
			model.setRoleName(_getRoleName(role.getRoleId()));
//			model.setRoleName(role.getRoleName());
			model.setCondition(role.getCondition());
			model.setModerator(Boolean.toString(role.getModerator()));
			model.setRoleCode(role.getRoleCode());
			
			outputs.add(model);
		}

		return outputs;
	}

	public static RoleInputModel mappingToServiceRoleInput(ServiceProcessRole processRole) {

		RoleInputModel model = new RoleInputModel();

		model.setRoleId(GetterUtil.getInteger(processRole.getRoleId()));
		model.setRoleName(_getRoleName(processRole.getRoleId()));
		model.setCondition(processRole.getCondition());
		model.setModerator(Boolean.toString(processRole.getModerator()));
		model.setRoleCode(processRole.getRoleCode());
		
		return model;
	}

	public static RoleInputModel mappingToServiceRoleInput(ProcessStepRole stepRole) {

		RoleInputModel model = new RoleInputModel();

		model.setRoleId(GetterUtil.getInteger(stepRole.getRoleId()));
		model.setRoleName(_getRoleName(stepRole.getRoleId()));
		model.setCondition(stepRole.getCondition());
		model.setModerator(Boolean.toString(stepRole.getModerator()));
		model.setRoleCode(stepRole.getRoleCode());

		return model;
	}

	public static List<ProcessStepDataModel> mappingToProcessStepData(List<Document> documents) {

		List<ProcessStepDataModel> outputs = new ArrayList<ProcessStepDataModel>();

		for (Document doc : documents) {
			ProcessStepDataModel model = new ProcessStepDataModel();

			model.setProcessStepId(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
			model.setStepCode(doc.get(ProcessStepTerm.STEP_CODE));
			model.setStepName(doc.get(ProcessStepTerm.STEP_NAME));
			model.setSequenceNo(doc.get(ProcessStepTerm.SEQUENCE_NO));
			model.setDossierStatus(doc.get(ProcessStepTerm.DOSSIER_STATUS));
			model.setDossierStatusText(doc.get(ProcessStepTerm.DOSSIER_STATUS_TEXT));
			model.setDossierSubStatus(doc.get(ProcessStepTerm.DOSSIER_SUB_STATUS));
			model.setDossierSubStatusText(doc.get(ProcessStepTerm.DOSSIER_SUB_STATUS_TEXT));
			model.setDurationCount(doc.get(ProcessStepTerm.DURATION_COUNT));
			model.setStepInstruction(doc.get(ProcessStepTerm.STEP_INSTRUCTION));
			model.setBriefNote(doc.get(ProcessStepTerm.BRIEF_NOTE));
			model.setCustomProcessUrl(doc.get(ProcessStepTerm.CUSTOM_PROCESS_URL));
			model.setEditable(doc.get(ProcessStepTerm.EDITABLE));
			model.setLockState(doc.get(ProcessStepTerm.LOCK_STATE));
			if (Validator.isNotNull(doc.get(ProcessStepTerm.CHECK_INPUT))) {
				model.setCheckInput(Integer.valueOf(doc.get(ProcessStepTerm.CHECK_INPUT)));
			}

			outputs.add(model);
		}

		return outputs;
	}

	public static List<ProcessActionDataModel> mappingToProcessActionData(List<Document> documents) {

		List<ProcessActionDataModel> outputs = new ArrayList<ProcessActionDataModel>();

		for (Document doc : documents) {
			ProcessActionDataModel model = new ProcessActionDataModel();
			
			model.setProcessActionId(doc.get(Field.ENTRY_CLASS_PK));
			model.setActionCode(doc.get(ProcessActionTerm.ACTION_CODE));
			model.setActionName(doc.get(ProcessActionTerm.ACTION_NAME));
			model.setPreStepCode(doc.get(ProcessActionTerm.PRESTEP_CODE));
			model.setPreStepName(doc.get(ProcessActionTerm.PRESTEP_NAME));
			model.setPostStepCode(doc.get(ProcessActionTerm.POSTSTEP_CODE));
			model.setPostStepName(doc.get(ProcessActionTerm.POSTSTEP_NAME));
			model.setAutoEvent(doc.get(ProcessActionTerm.AUTO_EVENT));
			model.setPreCondition(doc.get(ProcessActionTerm.PRE_CONDITION));
			model.setAllowAssignUser(doc.get(ProcessActionTerm.ALLOW_ASSIGN_USER));
			model.setAssignUserId(doc.get(ProcessActionTerm.ASSIGN_USER_ID));
			model.setAssignUserName(doc.get(ProcessActionTerm.ASSIGN_USER_NAME));
			model.setRequestPayment(doc.get(ProcessActionTerm.REQUEST_PAYMENT));
			model.setPaymentFee(doc.get(ProcessActionTerm.PAYMENT_FEE));
			model.getCreateDossierFiles().addAll(ListUtil.toList(
					StringUtil.split(doc.get(ProcessActionTerm.CREATE_DOSSIER_FILES).trim(), StringPool.COMMA)));
			model.getReturnDossierFiles().addAll(ListUtil.toList(
					StringUtil.split(doc.get(ProcessActionTerm.RETURN_DOSSIER_FILES).trim(), StringPool.COMMA)));
			model.setMakeBriefNote(doc.get(ProcessActionTerm.MAKE_BRIEF_NOTE));
			model.setSyncActionCode(doc.get(ProcessActionTerm.SYNC_ACTION_CODE));
			model.setRollbackable(doc.get(ProcessActionTerm.ROLLBACKABLE));
			model.setCreateDossierNo(Boolean.valueOf(doc.get(ProcessActionTerm.CREATE_DOSSIER_NO)));
			model.seteSignature(Boolean.valueOf(doc.get(ProcessActionTerm.ESIGNATURE)));
			model.setPaymentFee(doc.get(ProcessActionTerm.PAYMENT_FEE));
			model.setCreateDossiers(doc.get(ProcessActionTerm.CREATE_DOSSIERS));
			
			if (Validator.isNull(doc.get(ProcessActionTerm.CONFIG_NOTE))) {
				ProcessAction action = ProcessActionLocalServiceUtil.fetchProcessAction(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
				
				if (Validator.isNotNull(action)) {
					String configNote = action.getConfigNote();
					
					model.setConfigNote(configNote);

				}
			} else {
				model.setConfigNote(doc.get(ProcessActionTerm.CONFIG_NOTE));

			}
			model.setDossierTemplateNo(doc.get("dossierTemplateNo"));
			
			outputs.add(model);
		}

		return outputs;
	}

	public static ProcessStepInputModel mapptingToStepPOST(ProcessStep step) {

		ProcessStepInputModel model = new ProcessStepInputModel();

		model.setProcessStepId(step.getPrimaryKey());
		model.setStepCode(step.getStepCode());
		model.setStepName(step.getStepName());
		model.setSequenceNo(step.getSequenceNo());
		model.setDossierStatus(step.getDossierStatus());
		model.setDossierStatusText(_getStatusText(step.getDossierStatus()));
		model.setDossierSubStatus(step.getDossierSubStatus());
		model.setDossierSubStatusText(_getStatusText(step.getDossierSubStatus()));
		model.setDurationCount(String.valueOf(step.getDurationCount()));
		model.setStepInstruction(step.getStepInstruction());
		model.setBriefNote(step.getBriefNote());
		model.setCustomProcessUrl(step.getCustomProcessUrl());
		model.setEditable(Boolean.toString(step.getEditable()));
		model.setCheckInput(step.getCheckInput());

		return model;
	}

	public static ProcessActionReturnModel mappingToActionPOST(ProcessAction action) {

		ProcessActionReturnModel model = new ProcessActionReturnModel();

		model.setProcessActionId(String.valueOf(action.getPrimaryKey()));
		model.setActionCode(action.getActionCode());
		model.setActionName(action.getActionName());
		model.setPreStepCode(action.getPreStepCode());
		model.setPostStepCode(action.getPostStepCode());
		model.setAutoEvent(action.getAutoEvent());
		model.setPreCondition(action.getPreCondition());
		model.setAllowAssignUser(String.valueOf(action.getAllowAssignUser()));
		model.setAssignUserId(String.valueOf(action.getAssignUserId()));

		String assignName = StringPool.BLANK;

		if (action.getAssignUserId() != 0) {
			User user = UserLocalServiceUtil.fetchUser(action.getAssignUserId());

			if (Validator.isNotNull(user)) {

				assignName = user.getFullName();
			}
		}

		model.setAssignUserName(assignName);
		model.setRequestPayment(String.valueOf(action.getRequestPayment()));
		model.setPaymentFee(action.getPaymentFee());
		model.setMakeBriefNote(action.getMakeBriefNote());
		model.setSyncActionCode(action.getSyncActionCode());
		model.setRollbackable(Boolean.toString(action.getRollbackable()));

		model.getCreateDossierFiles()
				.addAll(ListUtil.toList(StringUtil.split(action.getCreateDossierFiles(), StringPool.COMMA)));

		model.getReturnDossierFiles()
				.addAll(ListUtil.toList(StringUtil.split(action.getReturnDossierFiles(), StringPool.COMMA)));
		
		model.setCreateDossierNo(action.getCreateDossierNo());
		model.seteSignature(action.getESignature());
		model.setSignatureType(action.getSignatureType());
		model.setConfigNote(action.getConfigNote());
		model.setPaymentFee(action.getPaymentFee());
		model.setCreateDossiers(action.getCreateDossiers());
		model.setDossierTemplateNo(action.getDossierTemplateNo());
		
		return model;
	}

	private static String _getRoleName(long roleId) {
		String roleName = StringPool.BLANK;
		try {
			Role role = RoleLocalServiceUtil.getRole(roleId);
			roleName = role.getName();

		} catch (Exception e) {
			_log.error(e);
		}
		return roleName;
	}

	private static String _getStatusText(String statusCode) {
		// TODO update get status text

		String statusText = StringPool.BLANK;

		return statusText;
	}
	private static Log _log = LogFactoryUtil.getLog(ServiceProcessUtils.class);
}
