package org.opencps.api.controller.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.opencps.api.dossier.model.ActionExecutedModel;
import org.opencps.api.dossier.model.ActionModel;
import org.opencps.api.dossier.model.ListContacts;
import org.opencps.api.dossier.model.UserModel;
import org.opencps.api.dossieraction.model.DossierActionNextActionModel;
import org.opencps.api.dossieraction.model.DossierActionNextActiontoUser;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.comparator.RoleRoleIdComparator;

import io.swagger.models.properties.StringProperty.Format;

public class DossierActionUtils {

	public static List<DossierActionNextActionModel> mappingToDoListActions(List<ProcessAction> lstProcessAction,
			List<User> lstUser) {

		List<DossierActionNextActionModel> outputs = new ArrayList<DossierActionNextActionModel>();

		for (ProcessAction processAction : lstProcessAction) {

			DossierActionNextActionModel model = mappingToDoActionModel(processAction);
			outputs.add(model);

		}
		return outputs;
	}

	public static DossierActionNextActionModel mappingToDoActionModel(ProcessAction processAction) {

		long[] userIds;

		List<UserModel> userModels;
		UserModel userModel;
		if (processAction == null) {
			return null;
		}

		DossierActionNextActionModel model = new DossierActionNextActionModel();

		// model.setProcessActionId((int) processAction.getProcessActionId());
		model.setActionCode(processAction.getActionCode());
		model.setActionName(processAction.getActionName());
		// model.setPreStepCode(Integer.parseInt(processAction.getPreStepCode()));
		model.setPostStepCode(processAction.getPostStepCode());
		model.setAutoEvent(processAction.getAutoEvent());
		// model.setPreCondition_0020(processAction.getPreCondition());
		// .setAllowAssignUser("" + (processAction.getAllowAssignUser()));
		model.setAssignUserId((int) (processAction.getAssignUserId()));

		ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(processAction.getPostStepCode(),
				processAction.getGroupId(), processAction.getServiceProcessId());

		List<ProcessStepRole> processStepRoles = ProcessStepRoleLocalServiceUtil
				.findByP_S_ID(processStep.getProcessStepId());

		// for (ProcessStepRole processStepRole : processStepRoles)
		//
		// {
		// userModels = new ArrayList<UserModel>();
		//
		// userIds =
		// UserLocalServiceUtil.getRoleUserIds(processStepRole.getRoleId());
		//
		// for (int i = 0; i < userIds.length; i++) {
		// userModel = new UserModel();
		// User user = UserLocalServiceUtil.fetchUser(userIds[i]);
		//
		// userModel.setModerator(Boolean.toString(processStepRole.getModerator()));
		// userModel.setUserId((int) user.getUserId());
		// userModel.setUserName(user.getFirstName());
		// userModels.add(userModel);
		//
		// }
		// model.getToUsers().addAll(userModels);
		//
		// }
		return model;

	}

	public static List<DossierActionNextActionModel> mappingToDoListReadNextActions(
			List<ProcessAction> lstProcessAction, List<User> lstUser) {
		List<DossierActionNextActionModel> outputs = new ArrayList<DossierActionNextActionModel>();
		List<DossierActionNextActiontoUser> outputUsers = new ArrayList<DossierActionNextActiontoUser>();
		for (User user : lstUser) {
			DossierActionNextActiontoUser model = new DossierActionNextActiontoUser();
			
			long userId = GetterUtil.getLong(user.getUserId());

			model.setUserId(userId);
			model.setUserName(user.getFullName());

			outputUsers.add(model);
		}
		for (ProcessAction processAction : lstProcessAction) {
			DossierActionNextActionModel model = new DossierActionNextActionModel();

			long processActionId = GetterUtil.getLong(processAction.getProcessActionId());
			long assignUserId = GetterUtil.getLong(processAction.getAssignUserUuid());

			model.setProcessActionId(processActionId);
			model.setActionCode(processAction.getActionCode());
			model.setActionName(processAction.getActionName());
			model.setPreStepCode(processAction.getPreStepCode());
			model.setPostStepCode(processAction.getPostStepCode());
			model.setAutoEvent(processAction.getAutoEvent());
			model.setPreCondition(processAction.getPreCondition());
			model.setAllowAssignUser(assignUserId);
			model.getToUsers().addAll(outputUsers);

			outputs.add(model);
		}
		return outputs;
	}

	public static List<org.opencps.api.dossieraction.model.DossierActionModel> mappingToDoListReadActionExecuted(
			List<Document> documents) {
		List<org.opencps.api.dossieraction.model.DossierActionModel> outputs = new ArrayList<org.opencps.api.dossieraction.model.DossierActionModel>();
		for (Document document : documents) {
			org.opencps.api.dossieraction.model.DossierActionModel model = new org.opencps.api.dossieraction.model.DossierActionModel();

			long dossierActionId = GetterUtil.getLong(document.get("entryClassPK"));
			long userId = GetterUtil.getLong(document.get(DossierActionTerm.USER_ID));
			long actionOverDue = GetterUtil.getLong(document.get(DossierActionTerm.ACTION_OVER_DUE));
			long actionNote = GetterUtil.getLong(document.get(DossierActionTerm.ACTION_OVER_DUE));

			model.setDossierActionId(dossierActionId);
			model.setUserId(userId);
			model.setCreateDate(document.get(DossierActionTerm.CREATE_DATE));
			model.setActionCode(document.get(DossierActionTerm.ACTION_CODE));
			model.setActionUser(document.get(DossierActionTerm.ACTION_USER));
			model.setActionName(document.get(DossierActionTerm.ACTION_NAME));
			model.setActionNote(actionNote);
			model.setActionOverdue(actionOverDue);

			long serviceProcessId = GetterUtil.getLong(document.get(DossierActionTerm.SERVICE_PROCESS_ID));
			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);

			model.setDurationUnit(serviceProcess.getDurationUnit());
			model.setRollbackable(document.get(DossierActionTerm.ROLLBACK_ABLE));
			model.setStepCode(document.get(DossierActionTerm.STEP_CODE));
			model.setStepName(document.get(DossierActionTerm.STEP_NAME));
			model.setStepInstruction(document.get(DossierActionTerm.STEP_INSTRUCTION));
			model.setPayload(document.get(DossierActionTerm.PAYLOAD));
			model.setDueDate(document.get(DossierActionTerm.DUE_DATE));

			outputs.add(model);
		}
		return outputs;
	}

	public static ActionExecutedModel mappingToDoActionExecutedModel(DossierAction dossierAction) {
		if (dossierAction == null) {
			return null;
		}

		ActionExecutedModel model = new ActionExecutedModel();

		model.setDossierActionId((int) dossierAction.getDossierActionId());
		model.setUserId((int) dossierAction.getUserId());
		model.setCreateDate(APIDateTimeUtils.convertDateToString(dossierAction.getDueDate()));
		model.setActionCode(dossierAction.getActionCode());
		model.setActionUser(dossierAction.getActionUser());
		model.setActionName(dossierAction.getActionName());
		model.setActionNote(dossierAction.getActionNote());
		model.setActionOverdue(dossierAction.getActionOverdue());

		long serviceProcessId = dossierAction.getServiceProcessId();
		ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);

		model.setDurationUnit(serviceProcess.getDurationUnit());
		model.setRollbackable("" + dossierAction.getRollbackable());
		model.setStepCode(dossierAction.getStepCode());
		model.setStepName(dossierAction.getStepName());
		model.setStepInstruction(dossierAction.getStepInstruction());
		model.setPayload(dossierAction.getPayload());
		model.setDueDate(APIDateTimeUtils.convertDateToString(dossierAction.getDueDate()));

		return model;

	}

	public static List<ListContacts> mappingToDoListContacts(List<Dossier> dossiers) {
		List<ListContacts> listContacts = new ArrayList<>();
		ListContacts contact;
		for (Dossier dossier : dossiers) {

			contact = new ListContacts();
			contact.setUserId((int) dossier.getUserId());
			contact.setUserName(dossier.getUserName());
			contact.setTelNo(dossier.getContactTelNo());
			contact.setEmail(dossier.getContactEmail());
			listContacts.add(contact);
		}
		return listContacts;
	}
}
