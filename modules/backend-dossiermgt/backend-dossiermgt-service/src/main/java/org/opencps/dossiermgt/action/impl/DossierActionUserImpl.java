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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierActionUserImpl implements DossierActionUser {

	protected Log _log = LogFactoryUtil.getLog(DossierActionUserImpl.class);

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
		// Delete record in dossierActionUser
		List<org.opencps.dossiermgt.model.DossierActionUser> dossierActionUser = DossierActionUserLocalServiceUtil
				.getListUser(dossierActionId);
		if (dossierActionUser != null && dossierActionUser.size() > 0) {
			for (org.opencps.dossiermgt.model.DossierActionUser dau : dossierActionUser) {
				DossierActionUserLocalServiceUtil.deleteDossierActionUser(dau);
			}
		}
		// Get DossierAction
		_log.info("START ROLES");
		DossierAction dossierAction = DossierActionLocalServiceUtil.getDossierAction(dossierActionId);
		_log.info("dossierActionId: "+dossierActionId);
		if (dossierAction != null) {
			_log.info("actionCode: "+dossierAction.getActionCode());
		}
		
		String actionCode = dossierAction.getActionCode();
		long serviceProcessId = dossierAction.getServiceProcessId();
		_log.info("serviceProcessId: "+dossierAction.getServiceProcessId());

		// Get ProcessAction
		ProcessAction processAction = ProcessActionLocalServiceUtil.fetchBySPID_AC(serviceProcessId, actionCode);
		String stepCode = processAction.getPostStepCode();

//		_log.info("1");
		// Get ProcessStep
		ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId, serviceProcessId);
		long processStepId = processStep.getProcessStepId();

//		_log.info("2");
		// Get List ProcessStepRole
		List<ProcessStepRole> listProcessStepRole = ProcessStepRoleLocalServiceUtil.findByP_S_ID(processStepId);
		ProcessStepRole processStepRole = null;
		for (int i = 0; i < listProcessStepRole.size(); i++) {
			processStepRole = listProcessStepRole.get(i);
			long roleId = processStepRole.getRoleId();
			boolean moderator = processStepRole.getModerator();
			int mod = 0;
			if (moderator) {
				mod = 1;
			}
//			_log.info("3");
//			_log.info("roleId: "+roleId);
			// Get list user
			List<User> users = UserLocalServiceUtil.getRoleUsers(roleId);
			for (int j = 0; j < users.size(); j++) {
				_log.info("UserID: "+i+ users.get(i).getUserId());
			}
			if (i == 0) {
				for (User user : users) {
//					_log.info("user: "+user.getUserId());
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
			} else {
				for (User user : users) {
//					_log.info("user: "+user.getUserId());
					boolean assigned = user.getUserId() == assignUserId ? true : false;
					org.opencps.dossiermgt.model.DossierActionUser dau = DossierActionUserLocalServiceUtil.getByDossierAndUser(dossierActionId, user.getUserId());
					if (dau != null) {
						dau.setModerator(mod);
						if (assigned) {
							dau.setAssigned(assigned);
							
						}
						DossierActionUserLocalServiceUtil.updateDossierActionUser(dau);
					} else {
						
						org.opencps.dossiermgt.model.DossierActionUser model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
						model.setUserId(user.getUserId());
						model.setDossierActionId(dossierActionId);
						model.setModerator(mod);
						model.setAssigned(assigned);
						model.setVisited(false);
						// Add User
						DossierActionUserLocalServiceUtil.addDossierActionUser(model);
					}
//					model.setModerator(mod);
//					model.setAssigned(assigned);
//					model.setVisited(false);
					// Add User
//					DossierActionUserLocalServiceUtil.addDossierActionUser(model);
				}
			}
			
		}
//		_log.info("END ROLES");
	}

	@Override
	public void assignDossierActionUser(long dossierActionId, long userId, long groupId, long assignUserId, JSONArray subUsers)
			throws PortalException {
		// Get list user
		// TODO insert to actionUser
		boolean assigned = true;
		org.opencps.dossiermgt.model.DossierActionUser model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
		model.setUserId(assignUserId);
		model.setDossierActionId(dossierActionId);
		model.setModerator(1);
		model.setAssigned(assigned);
		model.setVisited(false);
		// Add User
		DossierActionUserLocalServiceUtil.addDossierActionUser(model);
		for (int n = 0; n < subUsers.length(); n++) {
			JSONObject subUser = subUsers.getJSONObject(n);

			assigned = false;
			model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
			
			DossierActionUserPK pk = new DossierActionUserPK();
			
			pk.setDossierActionId(dossierActionId);
			pk.setUserId(subUser.getLong("userId"));
			
			org.opencps.dossiermgt.model.DossierActionUser dau = DossierActionUserLocalServiceUtil.fetchDossierActionUser(pk);
			
			if (Validator.isNull(dau)) {
				
				model.setUserId(subUser.getLong("userId"));
				model.setDossierActionId(dossierActionId);
				model.setModerator(0);
				model.setAssigned(assigned);
				model.setVisited(true);
				// Add User
				DossierActionUserLocalServiceUtil.addDossierActionUser(model);
			}
			


		}
	}

}
