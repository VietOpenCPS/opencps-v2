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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

public class DossierUserActionsImpl implements DossierUserActions {

	@Override
	public DossierUser addDossierUser(long groupId, long dossierId, long userId, int moderator, boolean visited) {
		return DossierUserLocalServiceUtil.addDossierUser(groupId, dossierId, userId, moderator, visited);
	}

	@Override
	public DossierUser updateDossierUser(long groupId, long dossierId, long userId, int moderator, boolean visited) {
		try {
			return DossierUserLocalServiceUtil.updateDossierUser(dossierId, userId, moderator, visited);
		} catch (NoSuchDossierUserException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public DossierUser deleteDossierUser(long dossierId, long userId) {
		try {
			return DossierUserLocalServiceUtil.deleteDossierUser(dossierId, userId);
		} catch (NoSuchDossierUserException e) {
			e.printStackTrace();
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
//					boolean moderator = spr.getModerator();
//					
//					if (moderator) {
//						mod = 1;
//					}
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
			
		} catch (PortalException e) {
			e.printStackTrace();
		}
	}

}
