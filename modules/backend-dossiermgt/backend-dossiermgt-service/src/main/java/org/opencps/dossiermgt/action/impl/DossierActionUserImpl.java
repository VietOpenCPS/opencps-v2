package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.action.DossierActionUser;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.persistence.DossierActionUserPK;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

public class DossierActionUserImpl implements DossierActionUser {

	@Override
	public org.opencps.dossiermgt.model.DossierActionUser addDossierActionUser(
			org.opencps.dossiermgt.model.DossierActionUser dossierActionUser) {
		return DossierActionUserLocalServiceUtil.addDossierActionUser(dossierActionUser);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierActionUser updateDossierActionUser(
			org.opencps.dossiermgt.model.DossierActionUser dossierActionUser) {
		return DossierActionUserLocalServiceUtil.updateDossierActionUser(dossierActionUser);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierActionUser deleteDossierActionUser(
			DossierActionUserPK dossierActionUserPK) throws PortalException {
		return DossierActionUserLocalServiceUtil.deleteDossierActionUser(dossierActionUserPK);
	}

	@Override
	public void initDossierActionUser(long dossierActionId, long userId, long groupId, long assignUserId)
			throws PortalException {
		// Get DossierAction
		DossierAction dossierAction = DossierActionLocalServiceUtil.getDossierAction(dossierActionId);
		String actionCode = dossierAction.getActionCode();
		long serviceProcessId = dossierAction.getServiceProcessId();

		// Get ProcessAction
		ProcessAction processAction = ProcessActionLocalServiceUtil.fetchBySPID_AC(serviceProcessId, actionCode);
		String stepCode = processAction.getPostStepCode();

		// Get ProcessStep
		ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId, serviceProcessId);
		long processStepId = processStep.getProcessStepId();

		// Get List ProcessStepRole
		List<ProcessStepRole> listProcessStepRole = ProcessStepRoleLocalServiceUtil.findByP_S_ID(processStepId);
		for (ProcessStepRole processStepRole : listProcessStepRole) {
			long roleId = processStepRole.getRoleId();
			boolean moderator = processStepRole.getModerator();
			int mod = 0;
			if (moderator) {
				mod = 1;
			}
			// Get list user
			List<User> users = UserLocalServiceUtil.getRoleUsers(roleId);
			for (User user : users) {
				boolean assigned = user.getUserId() == assignUserId ? true : false;
				org.opencps.dossiermgt.model.DossierActionUser model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
				model.setUserId(user.getUserId());
				model.setDossierActionId(dossierActionId);
				model.setModerator(mod);
				model.setAssigned(assigned);
				model.setVisited(false);
				// Add User
				DossierActionUserLocalServiceUtil.addDossierActionUser(model);
			}
		}
	}

	@Override
	public void assignDossierActionUser(long dossierActionId, long userId, long groupId, String assignedUsers)
			throws PortalException {
		// TODO Auto-generated method stub
		
	}

}
