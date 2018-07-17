package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.action.DossierActionUser;
import org.opencps.dossiermgt.constants.DossierActionUserTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierUser;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierUserLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.persistence.DossierActionUserPK;
import org.opencps.dossiermgt.service.persistence.DossierUserPK;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;
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
	public void initDossierActionUser(Dossier dossier, int allowAssignUser, long dossierActionId, long userId, long groupId, long assignUserId)
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
		if (listProcessStepRole.size() != 0) {
			for (int i = 0; i < listProcessStepRole.size(); i++) {
				processStepRole = listProcessStepRole.get(i);
				long roleId = processStepRole.getRoleId();
				boolean moderator = processStepRole.getModerator();
				int mod = 0;
				if (moderator) {
					mod = 1;
				}
//				_log.info("3");
//				_log.info("roleId: "+roleId);
				// Get list user
				List<User> users = UserLocalServiceUtil.getRoleUsers(roleId);
				for (int j = 0; j < users.size(); j++) {
					_log.info("UserID: "+i+ users.get(i).getUserId());
				}
				if (i == 0) {
					for (User user : users) {
//						_log.info("user: "+user.getUserId());
						addDossierActionUserByAssigned(processAction.getAllowAssignUser(), user.getUserId(), dossierActionId, mod, false, stepCode, dossier.getDossierId());
					}
				} else {
					for (User user : users) {
//						_log.info("user: "+user.getUserId());
						int assigned = user.getUserId() == assignUserId ? processAction.getAllowAssignUser() : ProcessActionTerm.NOT_ASSIGNED;
						org.opencps.dossiermgt.model.DossierActionUser dau = DossierActionUserLocalServiceUtil.getByDossierAndUser(dossierActionId, user.getUserId());
						if (dau != null) {
							dau.setModerator(mod);
							if (assigned != ProcessActionTerm.NOT_ASSIGNED) {
//								dau.setAssigned(assigned);
								
							}
							DossierActionUserLocalServiceUtil.updateDossierActionUser(dau);
						} else {						
							addDossierActionUserByAssigned(processAction.getAllowAssignUser(), user.getUserId(), dossierActionId, mod, false, stepCode, dossier.getDossierId());
						}
//						model.setModerator(mod);
//						model.setAssigned(assigned);
//						model.setVisited(false);
						// Add User
//						DossierActionUserLocalServiceUtil.addDossierActionUser(model);
					}
				}
				
			}			
		}
		else {
			//Get role from service process
			initDossierActionUserByServiceProcessRole(dossier, allowAssignUser, dossierActionId, userId, groupId, assignUserId);
		}
//		_log.info("END ROLES");
	}
	
	private void initDossierActionUserByServiceProcessRole(Dossier dossier, int allowAssignUser, long dossierActionId, long userId, long groupId, long assignUserId) {
		try {
			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.getServiceByCode(groupId, dossier.getServiceCode(), dossier.getGovAgencyCode(), dossier.getDossierTemplateNo());
			List<ServiceProcessRole> listSprs = ServiceProcessRoleLocalServiceUtil.findByS_P_ID(serviceProcess.getServiceProcessId());
			for (ServiceProcessRole spr : listSprs) {
				int mod = 0;
				boolean moderator = spr.getModerator();
				
				if (moderator) {
					mod = 1;
				}
				List<User> users = UserLocalServiceUtil.getRoleUsers(spr.getRoleId());
				for (User user : users) {
					int assigned = user.getUserId() == assignUserId ? allowAssignUser : ProcessActionTerm.NOT_ASSIGNED;
					org.opencps.dossiermgt.model.DossierActionUser dau = DossierActionUserLocalServiceUtil.getByDossierAndUser(dossierActionId, user.getUserId());
					if (dau != null) {
						dau.setModerator(mod);
						if (assigned != ProcessActionTerm.NOT_ASSIGNED) {
								
						}
						DossierActionUserLocalServiceUtil.updateDossierActionUser(dau);
					} else {						
						addDossierActionUserByAssigned(allowAssignUser, user.getUserId(), dossierActionId, mod, false, StringPool.BLANK, dossier.getDossierId());
					}
				}				
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void assignDossierActionUser(Dossier dossier, int allowAssignUser, long dossierActionId, long userId, long groupId, long assignUserId, JSONArray subUsers)
			throws PortalException {
		// Get list user
		// TODO insert to actionUser
		int moderator = 1;
		
		org.opencps.dossiermgt.model.DossierActionUser model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
		model.setUserId(assignUserId);
		model.setDossierActionId(dossierActionId);
		
		DossierUserPK duPk = new DossierUserPK();
		duPk.setDossierId(dossier.getDossierId());
		duPk.setUserId(userId);
		DossierUser du = DossierUserLocalServiceUtil.fetchDossierUser(duPk);
		
		if (du != null) {
			model.setModerator(du.getModerator());
			moderator = du.getModerator();
		}
		else {
			model.setModerator(1);
		}
		model.setVisited(false);
		// Add User
		DossierActionUserLocalServiceUtil.addDossierActionUser(model);
		for (int n = 0; n < subUsers.length(); n++) {
			JSONObject subUser = subUsers.getJSONObject(n);

			model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
			
			DossierActionUserPK pk = new DossierActionUserPK();
			
			pk.setDossierActionId(dossierActionId);
			pk.setUserId(subUser.getLong("userId"));
						
			org.opencps.dossiermgt.model.DossierActionUser dau = DossierActionUserLocalServiceUtil.fetchDossierActionUser(pk);
			
			if (Validator.isNull(dau)) {
				DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
				if (dAction != null) {
					addDossierActionUserByAssigned(allowAssignUser, assignUserId, dossierActionId, moderator, false,
							dAction.getStepCode(), dossier.getDossierId());
				} else {
					addDossierActionUserByAssigned(allowAssignUser, assignUserId, dossierActionId, moderator, false,
							StringPool.BLANK, dossier.getDossierId());
				}
			}
		}
	}

	private void addDossierActionUserByAssigned(int allowAssignUser, long userId, long dossierActionId, int moderator,
			boolean visited, String stepCode, long dossierId) {
		org.opencps.dossiermgt.model.DossierActionUser model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
		int assigned = DossierActionUserTerm.NOT_ASSIGNED;
		model.setVisited(visited);
		
		if (allowAssignUser == ProcessActionTerm.NOT_ASSIGNED) {
			model.setUserId(userId);
			model.setDossierActionId(dossierActionId);
			model.setModerator(moderator);
			
			model.setAssigned(assigned);
			// Add User
			DossierActionUserLocalServiceUtil.addDossierActionUser(model);					
		}
		else if (allowAssignUser == ProcessActionTerm.ASSIGNED_TH) {
			model.setUserId(userId);
			model.setDossierActionId(dossierActionId);
			model.setModerator(moderator);
			assigned = DossierActionUserTerm.ASSIGNED_TH;
			model.setAssigned(assigned);
			// Add User
			DossierActionUserLocalServiceUtil.addDossierActionUser(model);										
		}
		else if (allowAssignUser == ProcessActionTerm.ASSIGNED_TH_PH) {
			model.setUserId(userId);
			model.setDossierActionId(dossierActionId);
			model.setModerator(moderator);
			assigned = DossierActionUserTerm.ASSIGNED_TH;
			model.setAssigned(assigned);
			// Add User
			DossierActionUserLocalServiceUtil.addDossierActionUser(model);										

			model.setUserId(userId);
			model.setDossierActionId(dossierActionId);
			model.setModerator(moderator);
			model.setVisited(true);
			assigned = DossierActionUserTerm.ASSIGNED_PH;
			model.setAssigned(assigned);
			model.setStepCode(stepCode);
			model.setDossierId(dossierId);
			// Add User
			DossierActionUserLocalServiceUtil.addDossierActionUser(model);														
		}
		else if (allowAssignUser == ProcessActionTerm.ASSIGNED_TH_PH_TD) {
			model.setUserId(userId);
			model.setDossierActionId(dossierActionId);
			model.setModerator(moderator);
			assigned = DossierActionUserTerm.ASSIGNED_TH;
			model.setAssigned(assigned);
			model.setStepCode(stepCode);
			model.setDossierId(dossierId);
			// Add User
			DossierActionUserLocalServiceUtil.addDossierActionUser(model);										

			model.setUserId(userId);
			model.setDossierActionId(dossierActionId);
			model.setModerator(moderator);
			assigned = DossierActionUserTerm.ASSIGNED_PH;
			model.setAssigned(assigned);
			model.setStepCode(stepCode);
			model.setDossierId(dossierId);
			// Add User
			DossierActionUserLocalServiceUtil.addDossierActionUser(model);														

			model.setUserId(userId);
			model.setDossierActionId(dossierActionId);
			model.setModerator(moderator);
			assigned = DossierActionUserTerm.ASSIGNED_TD;
			model.setAssigned(assigned);
			model.setStepCode(stepCode);
			model.setDossierId(dossierId);
			// Add User
			DossierActionUserLocalServiceUtil.addDossierActionUser(model);																			
		}		
	}
}
