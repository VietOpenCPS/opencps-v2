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
import org.opencps.auth.utils.APIDateTimeUtils;
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
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.comparator.RoleRoleIdComparator;

import io.swagger.models.properties.StringProperty.Format;

public class DossierActionUtils {

	public static List<ActionModel> mappingToDoListActions(List<ProcessAction> processActions) {

		List<ActionModel> outputs = new ArrayList<ActionModel>();

		for (ProcessAction processAction : processActions) {

			ActionModel model = mappingToDoActionModel(processAction);
			outputs.add(model);

		}
		return outputs;
	}

	public static ActionModel mappingToDoActionModel(ProcessAction processAction) {

		long[] userIds;

		List<UserModel> userModels;
		UserModel userModel;
		if (processAction == null) {
			return null;
		}

		ActionModel model = new ActionModel();

		model.setProcessActionId((int) processAction.getProcessActionId());
		model.setActionCode(processAction.getActionCode());
		model.setActionName(processAction.getActionName());
		model.setPreStepCode(Integer.parseInt(processAction.getPreStepCode()));
		model.setPostStepCode(processAction.getPostStepCode());
		model.setAutoEvent(processAction.getAutoEvent());
		model.setPreCondition_0020(processAction.getPreCondition());
		model.setAllowAssignUser("" + (processAction.getAllowAssignUser()));
		model.setAssignUserId((int) (processAction.getAssignUserId()));

		ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(processAction.getPostStepCode(),
				processAction.getGroupId(), processAction.getServiceProcessId());

		List<ProcessStepRole> processStepRoles = ProcessStepRoleLocalServiceUtil
				.findByP_S_ID(processStep.getProcessStepId());

		for (ProcessStepRole processStepRole : processStepRoles)

		{
			userModels = new ArrayList<UserModel>();

			userIds = UserLocalServiceUtil.getRoleUserIds(processStepRole.getRoleId());

			for (int i = 0; i < userIds.length; i++) {
				userModel = new UserModel();
				User user = UserLocalServiceUtil.fetchUser(userIds[i]);

				userModel.setModerator(Boolean.toString(processStepRole.getModerator()));
				userModel.setUserId((int) user.getUserId());
				userModel.setUserName(user.getFirstName());
				userModels.add(userModel);

			}
			model.getToUsers().addAll(userModels);

		}
		return model;

	}

	public static List<ActionExecutedModel> mappingToDoListReadActionExecuted(List<DossierAction> dossierActions)

	{
		List<ActionExecutedModel> outputs = new ArrayList<ActionExecutedModel>();
		for (DossierAction dossierAction : dossierActions) {
			ActionExecutedModel actionExecutedModel = mappingToDoActionExecutedModel(dossierAction);
			outputs.add(actionExecutedModel);

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
