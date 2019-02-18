package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.action.DossierUserActions;
import org.opencps.dossiermgt.exception.NoSuchDossierUserException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierUser;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.DossierUserLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierUserActionsImpl implements DossierUserActions {
	private static final Log _log = LogFactoryUtil.getLog(DossierUserActionsImpl.class);
	
	@Override
	public DossierUser addDossierUser(long groupId, long dossierId, long userId, int moderator, boolean visited) {
		return DossierUserLocalServiceUtil.addDossierUser(groupId, dossierId, userId, moderator, visited);
	}

	@Override
	public DossierUser updateDossierUser(long groupId, long dossierId, long userId, int moderator, boolean visited) {
		try {
			return DossierUserLocalServiceUtil.updateDossierUser(dossierId, userId, moderator, visited);
		} catch (NoSuchDossierUserException e) {
			_log.error(e);
//			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public DossierUser deleteDossierUser(long dossierId, long userId) {
		try {
			return DossierUserLocalServiceUtil.deleteDossierUser(dossierId, userId);
		} catch (NoSuchDossierUserException e) {
			_log.error(e);
//			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void initDossierUser(long groupId, Dossier dossier) {
		try {
			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.getServiceByCode(groupId, dossier.getServiceCode(), dossier.getGovAgencyCode(), dossier.getDossierTemplateNo());
			if (serviceProcess != null) {
				List<ServiceProcessRole> listSprs = ServiceProcessRoleLocalServiceUtil.findByS_P_ID(serviceProcess.getServiceProcessId());
				for (ServiceProcessRole spr : listSprs) {
					int mod = 0;
					if (Validator.isNull(spr.getCondition())) {
//						boolean moderator = spr.getModerator();
//						
//						if (moderator) {
//							mod = 1;
//						}
						List<User> users = UserLocalServiceUtil.getRoleUsers(spr.getRoleId());
						for (User user : users) {
							org.opencps.dossiermgt.model.DossierUser du = DossierUserLocalServiceUtil.getByDossierUser(dossier.getDossierId(), user.getUserId());
							
							if (du != null) {
								du.setModerator(mod);
								du.setVisited(true);
								
								DossierUserLocalServiceUtil.updateDossierUser(dossier.getDossierId(), user.getUserId(), mod, du.getVisited());
							} else {						
								DossierUserLocalServiceUtil.addDossierUser(groupId, dossier.getDossierId(), user.getUserId(), mod, true);
							}
						}										
					}
					else {
						//Check service process role condition
						if (checkServiceProcessRoleCondition(dossier, spr)) {
							List<User> users = UserLocalServiceUtil.getRoleUsers(spr.getRoleId());
							for (User user : users) {
								org.opencps.dossiermgt.model.DossierUser du = DossierUserLocalServiceUtil.getByDossierUser(dossier.getDossierId(), user.getUserId());
								
								if (du != null) {
									du.setModerator(mod);
									du.setVisited(true);
									
									DossierUserLocalServiceUtil.updateDossierUser(dossier.getDossierId(), user.getUserId(), mod, du.getVisited());
								} else {						
									DossierUserLocalServiceUtil.addDossierUser(groupId, dossier.getDossierId(), user.getUserId(), mod, true);
								}
							}																	
						}
					}
				}				
			}
			
		} catch (PortalException e) {
			_log.error(e);
//			e.printStackTrace();
		}
	}

	private boolean checkServiceProcessRoleCondition(Dossier dossier, ServiceProcessRole role) {
		String condition = role.getCondition();
		if (Validator.isNull(condition))
			return true;
		String[] preConditions = StringUtil.split(condition);
		
		if (preConditions != null && preConditions.length > 0) {
			for (String preCondition : preConditions) {
				if (preCondition.contains("agency")) {
					String[] agencies = StringUtil.split(preCondition, StringPool.SPACE);
					if (agencies.length == 2) {
						if (!agencies[1].equalsIgnoreCase(dossier.getGovAgencyCode())) {
							return false;
						}
					}
					else {
						return false;
					}
				}
				else if (preCondition.contains("service")) {
					String[] services = StringUtil.split(preCondition, StringPool.SPACE);
					if (services.length == 2) {
						if (!services[1].equalsIgnoreCase(dossier.getServiceCode())) {
							return false;
						}
					}
					else {
						return false;
					}					
				}
				else if (preCondition.contains("template")) {
					String[] templates = StringUtil.split(preCondition, StringPool.SPACE);
					if (templates.length == 2) {
						if (!templates[1].equalsIgnoreCase(dossier.getDossierTemplateNo())) {
							return false;
						}
					}
					else {
						return false;
					}										
				}
				else {
					return false;
				}
			}
			
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void initDossierUser(long groupId, Dossier dossier, ServiceProcess serviceProcess, List<ServiceProcessRole> listSprs) {
		try {
			if (serviceProcess != null) {
//				List<ServiceProcessRole> listSprs = ServiceProcessRoleLocalServiceUtil.findByS_P_ID(serviceProcess.getServiceProcessId());
				for (ServiceProcessRole spr : listSprs) {
					int mod = 0;
					if (Validator.isNull(spr.getCondition())) {
//						boolean moderator = spr.getModerator();
//						
//						if (moderator) {
//							mod = 1;
//						}
						List<User> users = UserLocalServiceUtil.getRoleUsers(spr.getRoleId());
						for (User user : users) {
							org.opencps.dossiermgt.model.DossierUser du = DossierUserLocalServiceUtil.getByDossierUser(dossier.getDossierId(), user.getUserId());
							
							if (du != null) {
								du.setModerator(mod);
								du.setVisited(true);
								
								DossierUserLocalServiceUtil.updateDossierUser(dossier.getDossierId(), user.getUserId(), mod, du.getVisited());
							} else {						
								DossierUserLocalServiceUtil.addDossierUser(groupId, dossier.getDossierId(), user.getUserId(), mod, true);
							}
						}										
					}
					else {
						//Check service process role condition
						if (checkServiceProcessRoleCondition(dossier, spr)) {
							List<User> users = UserLocalServiceUtil.getRoleUsers(spr.getRoleId());
							for (User user : users) {
								org.opencps.dossiermgt.model.DossierUser du = DossierUserLocalServiceUtil.getByDossierUser(dossier.getDossierId(), user.getUserId());
								
								if (du != null) {
									du.setModerator(mod);
									du.setVisited(true);
									
									DossierUserLocalServiceUtil.updateDossierUser(dossier.getDossierId(), user.getUserId(), mod, du.getVisited());
								} else {						
									DossierUserLocalServiceUtil.addDossierUser(groupId, dossier.getDossierId(), user.getUserId(), mod, true);
								}
							}																	
						}
					}
				}				
			}
			
		} catch (PortalException e) {
			_log.error(e);
//			e.printStackTrace();
		}		
	}
}
