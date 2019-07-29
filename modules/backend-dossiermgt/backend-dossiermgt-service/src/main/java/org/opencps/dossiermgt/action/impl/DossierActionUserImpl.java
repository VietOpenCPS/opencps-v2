package org.opencps.dossiermgt.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencps.dossiermgt.action.DossierActionUser;
import org.opencps.dossiermgt.constants.DossierActionUserTerm;
import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.exception.NoSuchDossierUserException;
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
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.persistence.DossierActionUserPK;
import org.opencps.dossiermgt.service.persistence.DossierUserPK;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

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
	public void initDossierActionUser(ProcessAction processAction, Dossier dossier, int allowAssignUser, DossierAction dossierAction, long userId, long groupId, long assignUserId)
			throws PortalException {
		// Delete record in dossierActionUser
		List<org.opencps.dossiermgt.model.DossierActionUser> dossierActionUser = DossierActionUserLocalServiceUtil
				.getListUser(dossierAction.getDossierActionId());
		if (dossierActionUser != null && dossierActionUser.size() > 0) {
			DossierActionUserLocalServiceUtil.deleteByDossierAction(dossierAction.getDossierActionId());
//			for (org.opencps.dossiermgt.model.DossierActionUser dau : dossierActionUser) {
//				DossierActionUserLocalServiceUtil.deleteDossierActionUser(dau);
//			}
		}
		// Get DossierAction
//		_log.info("START ROLES");
//		DossierAction dossierAction = DossierActionLocalServiceUtil.getDossierAction(dossierActionId);
//		_log.info("dossierActionId: "+dossierActionId);
		if (dossierAction != null) {
//			_log.info("actionCode: "+dossierAction.getActionCode());
		}
		
//		String actionCode = dossierAction.getActionCode();
		long serviceProcessId = dossierAction.getServiceProcessId();
//		_log.info("serviceProcessId: "+dossierAction.getServiceProcessId());
		String stepCode = processAction.getPostStepCode();

//		_log.info("1");
		// Get ProcessStep
		ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId, serviceProcessId);
		
		long processStepId = processStep.getProcessStepId();

//		_log.info("2");
		// Get List ProcessStepRole
		List<ProcessStepRole> listProcessStepRole = ProcessStepRoleLocalServiceUtil.findByP_S_ID(processStepId);
//		_log.info("Process step role: " + listProcessStepRole + ", step: " + stepCode);
		ProcessStepRole processStepRole = null;
		List<DossierAction> lstStepActions = DossierActionLocalServiceUtil.getByDID_FSC_NOT_DAI(dossier.getDossierId(), stepCode, dossierAction.getDossierActionId());
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
//				if (i == 0) {
					for (User user : users) {
//						_log.info("user in assign process step role: "+user.getUserId());
						List<DossierAction> lstDoneActions = DossierActionLocalServiceUtil.getByDID_U_FSC(dossier.getDossierId(), user.getUserId(), stepCode);
						if (!lstStepActions.isEmpty()) {
							if (!lstDoneActions.isEmpty())
								mod = 1;
							else
								mod = 0;
						}
						updateDossierUser(dossier, processStepRole, user);
						addDossierActionUserByAssigned(processAction.getAllowAssignUser(), user.getUserId(), dossierAction.getDossierActionId(), mod, false, stepCode, dossier.getDossierId(), 0, 0);
					}
//				} else {
//					for (User user : users) {
////						_log.info("user: "+user.getUserId());
//						int assigned = user.getUserId() == assignUserId ? processAction.getAllowAssignUser() : ProcessActionTerm.NOT_ASSIGNED;
//						org.opencps.dossiermgt.model.DossierActionUser dau = DossierActionUserLocalServiceUtil.getByDossierAndUser(dossierActionId, user.getUserId());
//						if (dau != null) {
//							dau.setModerator(mod);
//							if (assigned != ProcessActionTerm.NOT_ASSIGNED) {
////								dau.setAssigned(assigned);
//								
//							}
//							DossierActionUserLocalServiceUtil.updateDossierActionUser(dau);
//						} else {						
//							addDossierActionUserByAssigned(processAction.getAllowAssignUser(), user.getUserId(), dossierActionId, mod, false, stepCode, dossier.getDossierId());
//						}
////						model.setModerator(mod);
////						model.setAssigned(assigned);
////						model.setVisited(false);
//						// Add User
////						DossierActionUserLocalServiceUtil.addDossierActionUser(model);
//					}
//				}
				
			}			
		}
		else {
			//Get role from service process
			initDossierActionUserByServiceProcessRole(dossier, allowAssignUser, dossierAction, userId, groupId, assignUserId);
		}
//		_log.info("END ROLES");
	}
	
	private void updateDossierUser(Dossier dossier, ProcessStepRole processStepRole, User user) {
		DossierUserPK pk = new DossierUserPK();
		pk.setDossierId(dossier.getDossierId());
		pk.setUserId(user.getUserId());
		DossierUser du = DossierUserLocalServiceUtil.fetchDossierUser(pk);
		if (du == null) {
			DossierUserLocalServiceUtil.addDossierUser(dossier.getGroupId(), dossier.getDossierId(), user.getUserId(), processStepRole.getModerator() ? 1 : 0, true);
		}
		else {
			try {
				if ((processStepRole.getModerator() && du.getModerator() != DossierActionUserTerm.ASSIGNED_PH)
						|| (!processStepRole.getModerator() && du.getModerator() != DossierActionUserTerm.ASSIGNED_PH)) {
					DossierUserLocalServiceUtil.updateDossierUser(dossier.getDossierId(), user.getUserId(), du.getModerator() == 0 ? (processStepRole.getModerator() ? 1 : 0) : 1, true);					
				}
			} catch (NoSuchDossierUserException e) {
				_log.error(e);
//				e.printStackTrace();
			}
		}
	}
	
	private void initDossierActionUserByServiceProcessRole(Dossier dossier, int allowAssignUser, DossierAction dossierAction, long userId, long groupId, long assignUserId) {
		try {
			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.getServiceByCode(groupId, dossier.getServiceCode(), dossier.getGovAgencyCode(), dossier.getDossierTemplateNo());
			List<ServiceProcessRole> listSprs = ServiceProcessRoleLocalServiceUtil.findByS_P_ID(serviceProcess.getServiceProcessId());
			
//			DossierAction da = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
			DossierAction da = dossierAction;
			
			for (ServiceProcessRole spr : listSprs) {
				int mod = 0;
				boolean moderator = spr.getModerator();
				
				if (moderator) {
					mod = 1;
				}
				List<User> users = UserLocalServiceUtil.getRoleUsers(spr.getRoleId());
				for (User user : users) {
					int assigned = user.getUserId() == assignUserId ? allowAssignUser : ProcessActionTerm.NOT_ASSIGNED;
					org.opencps.dossiermgt.model.DossierActionUser dau = DossierActionUserLocalServiceUtil.getByDossierAndUser(dossierAction.getDossierActionId(), user.getUserId());
					if (dau != null) {
						dau.setModerator(mod);
//						if (assigned != ProcessActionTerm.NOT_ASSIGNED) {
//								
//						}
						if (moderator) {
							dau.setAssigned(1);
						} else {
							dau.setAssigned(assigned);
						}
						DossierActionUserLocalServiceUtil.updateDossierActionUser(dau);
					} else {						
						addDossierActionUserByAssigned(allowAssignUser, user.getUserId(), dossierAction.getDossierActionId(), mod, false, da.getStepCode(), dossier.getDossierId(), 0, 0);
					}
				}				
			}
		} catch (PortalException e) {
			_log.error(e);
//			e.printStackTrace();
		}		
	}
	
	@Override
	public void assignDossierActionUser(Dossier dossier, int allowAssignUser, DossierAction dossierAction, long userId, long groupId, long assignUserId, JSONArray assignedUsers, Integer delegacy)
			throws PortalException {
		// Get list user
		// TODO insert to actionUser
		int moderator = 1;
//		org.opencps.dossiermgt.model.DossierActionUser model = null;
//		org.opencps.dossiermgt.model.DossierActionUser model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
//		model.setUserId(assignUserId);
//		model.setDossierActionId(dossierActionId);
//		
//		DossierUserPK duPk = new DossierUserPK();
//		if (dossierActionId > 0) {
//			DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
//			if (dAction != null) {
//				model.setStepCode(dAction.getStepCode());
//			}
//		}
//		duPk.setDossierId(dossier.getDossierId());
//		duPk.setUserId(userId);
//		DossierUser du = DossierUserLocalServiceUtil.fetchDossierUser(duPk);
//		
//		if (du != null) {
//			//Update dossier user if assigned
//			if (allowAssignUser != ProcessActionTerm.NOT_ASSIGNED) {
//				du.setModerator(1);
//				DossierUserLocalServiceUtil.updateDossierUser(du.getDossierId(), du.getUserId(), du.getModerator(), du.getVisited());
//				
//				model.setModerator(du.getModerator());
//				moderator = du.getModerator();
//			}
//
//		}
//		else {
//			model.setModerator(1);
//		}
		
//		model.setVisited(false);
		// Add User
//		DossierActionUserLocalServiceUtil.addDossierActionUser(model);
		List<DossierUser> lstDus = DossierUserLocalServiceUtil.findByDID(dossier.getDossierId());
		HashMap<Long, DossierUser> mapDus = new HashMap<>();
		for (DossierUser du : lstDus) {
			mapDus.put(du.getUserId(), du);
		}
		List<org.opencps.dossiermgt.model.DossierActionUser> lstDaus = DossierActionUserLocalServiceUtil.getByDossierId(dossier.getDossierId());
		
		HashMap<Long, Map<Long, org.opencps.dossiermgt.model.DossierActionUser>> mapDaus = new HashMap<>();
		for (org.opencps.dossiermgt.model.DossierActionUser dau : lstDaus) {
			if (mapDaus.get(dau.getDossierActionId()) != null) {
				Map<Long, org.opencps.dossiermgt.model.DossierActionUser> temp = mapDaus.get(dau.getDossierActionId());
				temp.put(dau.getUserId(), dau);
			}
			else {
				Map<Long, org.opencps.dossiermgt.model.DossierActionUser> temp = new HashMap<>();
				temp.put(dau.getUserId(), dau);
				mapDaus.put(dau.getDossierActionId(), temp);
			}
		}
		for (int n = 0; n < assignedUsers.length(); n++) {
			JSONObject subUser = assignedUsers.getJSONObject(n);
			if (subUser != null && subUser.has(DossierActionUserTerm.ASSIGNED)
					&& subUser.getInt(DossierActionUserTerm.ASSIGNED) == DossierActionUserTerm.ASSIGNED_TH) {
	//			model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
				DossierActionUserPK pk = new DossierActionUserPK();
				long userIdAssigned = subUser.getLong("userId");
				int assigned = subUser.has("assigned") ? subUser.getInt("assigned") : 0;
				
				pk.setDossierActionId(dossierAction.getDossierActionId());
				
				pk.setUserId(subUser.getLong("userId"));
	
//				DossierUserPK duPk = new DossierUserPK();
	//			if (dossierActionId > 0) {
	//				DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
	//				if (dAction != null) {
	//					model.setStepCode(dAction.getStepCode());
	//				}
	//			}
//				duPk.setDossierId(dossier.getDossierId());
//				duPk.setUserId(subUser.getLong("userId"));
//				DossierUser dossierUser = DossierUserLocalServiceUtil.fetchDossierUser(duPk);
				DossierUser dossierUser = null;
				dossierUser = mapDus.get(subUser.getLong("userId"));
				
				if (dossierUser != null) {
					//Update dossier user if assigned
					if (allowAssignUser != ProcessActionTerm.NOT_ASSIGNED) {
						dossierUser.setModerator(1);
						DossierUserLocalServiceUtil.updateDossierUser(dossierUser.getDossierId(), dossierUser.getUserId(),
								dossierUser.getModerator(), dossierUser.getVisited());
						
	//					model.setModerator(dossierUser.getModerator());
						moderator = dossierUser.getModerator();
					}
	
				}
				else {
					if (allowAssignUser != ProcessActionTerm.NOT_ASSIGNED) {
						DossierUserLocalServiceUtil.addDossierUser(groupId, dossier.getDossierId(), userIdAssigned, 1, true);
					}					
				}
				
//				org.opencps.dossiermgt.model.DossierActionUser dau = DossierActionUserLocalServiceUtil.fetchDossierActionUser(pk);
				org.opencps.dossiermgt.model.DossierActionUser dau = null;
//				dau = mapDaus.get(userIdAssigned);
				if (mapDaus.get(dossierAction.getDossierActionId()) != null) {
					dau = mapDaus.get(dossierAction.getDossierActionId()).get(userIdAssigned);
				}
				
				if (dau == null) {
//					DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierAction.getDossierActionId());
					DossierAction dAction = dossierAction;
					if (dAction != null) {
						addDossierActionUserByAssigned(allowAssignUser, userIdAssigned, dossierAction.getDossierActionId(), moderator, false,
								dAction.getStepCode(), dossier.getDossierId(), delegacy, assigned);
					} 
//					else {
//						addDossierActionUserByAssigned(allowAssignUser, userIdAssigned, dossierAction.getDossierActionId(), moderator, false,
//								StringPool.BLANK, dossier.getDossierId());
//					}
				}
				else {
					dau.setModerator(1);
					dau.setAssigned(assigned);
					dau.setDelegacy(delegacy);
					DossierActionUserLocalServiceUtil.updateDossierActionUser(dau);
				}
			}
			else if (subUser != null && subUser.has(DossierActionUserTerm.ASSIGNED)
					&& subUser.getInt(DossierActionUserTerm.ASSIGNED) == DossierActionUserTerm.NOT_ASSIGNED) {
				//			model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
				DossierActionUserPK pk = new DossierActionUserPK();
				int assigned = subUser.has("assigned") ? subUser.getInt("assigned") : 0;
				long userIdAssigned = subUser.getLong("userId");
				
				pk.setDossierActionId(dossierAction.getDossierActionId());
				pk.setUserId(subUser.getLong("userId"));
	
				org.opencps.dossiermgt.model.DossierActionUser dau = DossierActionUserLocalServiceUtil.fetchDossierActionUser(pk);
				if (Validator.isNull(dau)) {
					dau = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
					dau.setModerator(0);
					dau.setAssigned(assigned);
					dau.setDelegacy(0);
					dau.setDossierActionId(dossier.getDossierActionId());
					dau.setUserId(userIdAssigned);
					DossierActionUserLocalServiceUtil.addDossierActionUser(dau);
				}				
				else {
					dau.setModerator(0);
					dau.setAssigned(assigned);
					dau.setDelegacy(0);
					DossierActionUserLocalServiceUtil.updateDossierActionUser(dau);
				}
			}
		}
	}

	private void addDossierActionUserByAssigned(int allowAssignUser, long userId, long dossierActionId, int moderator,
			boolean visited, String stepCode, long dossierId, int delegacy, int assigned) {
		org.opencps.dossiermgt.model.DossierActionUser model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
	
		model.setVisited(visited);
		model.setDossierId(dossierId);
		model.setStepCode(stepCode);
		model.setDelegacy(delegacy);
		model.setAssigned(assigned);
//		_log.info("Allow assign user: " + allowAssignUser);
		//Check employee is exits and wokingStatus
		Employee employee = EmployeeLocalServiceUtil.fetchByFB_MUID(userId);
		_log.debug("Employee : " + employee);
		if (employee != null && employee.getWorkingStatus() == 1) {

			DossierActionUserPK pk = new DossierActionUserPK(dossierActionId, userId);
			
			org.opencps.dossiermgt.model.DossierActionUser dau = DossierActionUserLocalServiceUtil.fetchDossierActionUser(pk);
			model.setUserId(userId);
			model.setDossierActionId(dossierActionId);
			model.setModerator(moderator);
			
//			if (allowAssignUser == ProcessActionTerm.NOT_ASSIGNED) {
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				if (moderator == 1) {
//					model.setAssigned(1);
//				} else {
//					model.setAssigned(assigned);
//				}
//				if (dau == null) {
//					DossierActionUserLocalServiceUtil.addDossierActionUser(model);		
//				}
//				else {
//					if (dau.getModerator() != DossierActionUserTerm.ASSIGNED_TH
//							&& model.getModerator() == DossierActionUserTerm.ASSIGNED_TH) {
//						DossierActionUserLocalServiceUtil.updateDossierActionUser(model);					
//					}
//				}
//			}
//			else if (allowAssignUser == ProcessActionTerm.ASSIGNED_TH) {
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				assigned = DossierActionUserTerm.ASSIGNED_TH;
//				model.setAssigned(assigned);
//				if (dau == null) {
//					DossierActionUserLocalServiceUtil.addDossierActionUser(model);		
//				}
//				else {
//					if (dau.getModerator() != DossierActionUserTerm.ASSIGNED_TH
//							&& model.getModerator() == DossierActionUserTerm.ASSIGNED_TH) {
//						DossierActionUserLocalServiceUtil.updateDossierActionUser(model);					
//					}
//				}
//			}
//			else if (allowAssignUser == ProcessActionTerm.ASSIGNED_TH_PH) {
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				assigned = DossierActionUserTerm.ASSIGNED_TH;
//				model.setAssigned(assigned);
//				DossierActionUserLocalServiceUtil.addDossierActionUser(model);										
//	
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				model.setVisited(true);
//				assigned = DossierActionUserTerm.ASSIGNED_PH;
//				model.setAssigned(assigned);
//				if (dau == null) {
//					DossierActionUserLocalServiceUtil.addDossierActionUser(model);		
//				}
//				else {
//					if (dau.getModerator() != DossierActionUserTerm.ASSIGNED_TH
//							&& model.getModerator() == DossierActionUserTerm.ASSIGNED_TH) {
//						DossierActionUserLocalServiceUtil.updateDossierActionUser(model);					
//					}
//				}
//			}
//			else if (allowAssignUser == ProcessActionTerm.ASSIGNED_TH_PH_TD) {
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				assigned = DossierActionUserTerm.ASSIGNED_TH;
//				model.setAssigned(assigned);
//				DossierActionUserLocalServiceUtil.addDossierActionUser(model);										
//	
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				assigned = DossierActionUserTerm.ASSIGNED_PH;
//				model.setAssigned(assigned);
//				DossierActionUserLocalServiceUtil.addDossierActionUser(model);														
//	
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				assigned = DossierActionUserTerm.ASSIGNED_TD;
//				model.setAssigned(assigned);
//				if (dau == null) {
//					DossierActionUserLocalServiceUtil.addDossierActionUser(model);		
//				}
//				else {
//					if (dau.getModerator() != DossierActionUserTerm.ASSIGNED_TH
//							&& model.getModerator() == DossierActionUserTerm.ASSIGNED_TH) {
//						DossierActionUserLocalServiceUtil.updateDossierActionUser(model);					
//					}
//				}
//			}
			if (dau == null) {
				DossierActionUserLocalServiceUtil.addDossierActionUser(model);		
			}
			else {
				DossierActionUserLocalServiceUtil.updateDossierActionUser(model);					
			}					
		}
	}

	@Override
	public void copyRoleAsStep(ProcessStep curStep, Dossier dossier) {
		if (Validator.isNull(curStep.getRoleAsStep()))
			return;	
		String[] stepCodeArr = StringUtil.split(curStep.getRoleAsStep());
		if (stepCodeArr.length > 0) {
			for (String stepCode : stepCodeArr) {
				if (stepCode.startsWith("!")) {
					int index = stepCode.indexOf("!");
					String stepCodePunc = stepCode.substring(index + 1);
					List<org.opencps.dossiermgt.model.DossierActionUser> lstDaus = DossierActionUserLocalServiceUtil.getByDossierAndStepCode(dossier.getDossierId(), stepCodePunc);
					List<DossierAction> lstDossierActions = DossierActionLocalServiceUtil.findDossierActionByDID_STEP(dossier.getDossierId(), stepCodePunc);
					
					try {
						for (org.opencps.dossiermgt.model.DossierActionUser dau : lstDaus) {
							boolean flagDA = false;
							for (DossierAction da : lstDossierActions) {
								if (da.getUserId() == dau.getUserId()) {
									flagDA = true;
									break;
								}
							}
							if (flagDA) {
								DossierUserPK duPk = new DossierUserPK();
								duPk.setDossierId(dossier.getDossierId());
								duPk.setUserId(dau.getUserId());
								int moderator = dau.getModerator();
								
								DossierUser duModel = DossierUserLocalServiceUtil.fetchDossierUser(duPk);
														
								if (duModel == null) {
									DossierUserLocalServiceUtil.addDossierUser(dossier.getGroupId(), dossier.getDossierId(), 
											dau.getUserId(), moderator, true);
								}
								else {
									try {
										if (duModel.getModerator() == 0 && moderator == 1) {
											DossierUserLocalServiceUtil.updateDossierUser(dossier.getDossierId(), dau.getUserId(),
													moderator, true);							
										}
									} catch (NoSuchDossierUserException e) {
		//										e.printStackTrace();
										_log.error(e);
									}					
								}	
								
								DossierActionUserPK dauPk = new DossierActionUserPK();
								dauPk.setDossierActionId(dossier.getDossierActionId());
								dauPk.setUserId(dau.getUserId());
//								org.opencps.dossiermgt.model.DossierActionUser dauModel = DossierActionUserLocalServiceUtil.fetchDossierActionUser(dauPk);
								int assigned = moderator == 1 ? 1 : 0;
	//							if (dauModel == null) {							
	//								DossierActionUserLocalServiceUtil.addDossierActionUser(dau.getUserId(), dossier.getGroupId(), dossier.getDossierActionId(), dossier.getDossierId(), stepCodePunc, moderator, assigned, true);
	//							}
	//							else {
	//								DossierActionUserLocalServiceUtil.updateDossierActionUser(dau.getUserId(), dossier.getGroupId(), dossier.getDossierActionId(), dossier.getDossierId(), stepCodePunc, moderator, assigned, true);
	//							}						
								DossierActionUserLocalServiceUtil.addOrUpdateDossierActionUser(dau.getUserId(), dossier.getGroupId(), dossier.getDossierActionId(), dossier.getDossierId(), curStep.getStepCode(), moderator, assigned, true);
//								if (dauModel == null) {							
//									DossierActionUserLocalServiceUtil.addDossierActionUser(dau.getUserId(), dossier.getGroupId(), dossier.getDossierActionId(), dossier.getDossierId(), curStep.getStepCode(), moderator, assigned, true);
//								}
//								else {
//									DossierActionUserLocalServiceUtil.updateDossierActionUser(dau.getUserId(), dossier.getGroupId(), dossier.getDossierActionId(), dossier.getDossierId(), curStep.getStepCode(), moderator, assigned, true);
//								}
							}
						}
					}
					catch (Exception e) {
						_log.error(e);
					}
				}
				else {
	//				_log.info("Copy role from step: " + stepCode);
					ServiceProcess serviceProcess = null;
					try {
						serviceProcess = ServiceProcessLocalServiceUtil.getServiceByCode(dossier.getGroupId(), dossier.getServiceCode(), dossier.getGovAgencyCode(), dossier.getDossierTemplateNo());
						if (serviceProcess != null) {
							ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, dossier.getGroupId(), serviceProcess.getServiceProcessId());
							if (processStep == null) continue;
							List<ProcessStepRole> lstRoles = ProcessStepRoleLocalServiceUtil.findByP_S_ID(processStep.getProcessStepId());
							for (ProcessStepRole psr : lstRoles) {
	//							_log.info("Copy role from role: " + psr.getModerator());
								List<User> users = UserLocalServiceUtil.getRoleUsers(psr.getRoleId());
								for (User u : users) {
									DossierUserPK duPk = new DossierUserPK();
									duPk.setDossierId(dossier.getDossierId());
									duPk.setUserId(u.getUserId());
									int moderator = (psr.getModerator() ? 1 : 0);
									
									DossierUser duModel = DossierUserLocalServiceUtil.fetchDossierUser(duPk);
															
									if (duModel == null) {
										DossierUserLocalServiceUtil.addDossierUser(dossier.getGroupId(), dossier.getDossierId(), 
												u.getUserId(), moderator, true);
									}
									else {
										try {
											if (duModel.getModerator() == 0 && moderator == 1) {
												DossierUserLocalServiceUtil.updateDossierUser(dossier.getDossierId(), u.getUserId(),
														moderator, true);							
											}
										} catch (NoSuchDossierUserException e) {
	//										e.printStackTrace();
											_log.error(e);
										}					
									}	
									
									DossierActionUserPK dauPk = new DossierActionUserPK();
									dauPk.setDossierActionId(dossier.getDossierActionId());
									dauPk.setUserId(u.getUserId());
//									org.opencps.dossiermgt.model.DossierActionUser dauModel = DossierActionUserLocalServiceUtil.fetchDossierActionUser(dauPk);
									int assigned = moderator == 1 ? 1 : 0;
//									if (dauModel == null) {							
//										DossierActionUserLocalServiceUtil.addDossierActionUser(u.getUserId(), dossier.getGroupId(), dossier.getDossierActionId(), dossier.getDossierId(), stepCode, moderator, assigned, true);
//									}
//									else {
//										DossierActionUserLocalServiceUtil.updateDossierActionUser(u.getUserId(), dossier.getGroupId(), dossier.getDossierActionId(), dossier.getDossierId(), stepCode, moderator, assigned, true);
//									}
									DossierActionUserLocalServiceUtil.addOrUpdateDossierActionUser(u.getUserId(), dossier.getGroupId(), dossier.getDossierActionId(), dossier.getDossierId(), curStep.getStepCode(), moderator, assigned, true);
//									if (dauModel == null) {							
//										DossierActionUserLocalServiceUtil.addDossierActionUser(u.getUserId(), dossier.getGroupId(), dossier.getDossierActionId(), dossier.getDossierId(), curStep.getStepCode(), moderator, assigned, true);
//									}
//									else {
//										DossierActionUserLocalServiceUtil.updateDossierActionUser(u.getUserId(), dossier.getGroupId(), dossier.getDossierActionId(), dossier.getDossierId(), curStep.getStepCode(), moderator, assigned, true);
//									}
								}
							}
						}
					} catch (PortalException e) {
						_log.error(e);
					}				
				}
			}
		}		
	}
}
