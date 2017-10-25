package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.exception.DossierAccessException;
import org.opencps.dossiermgt.exception.DossierPasswordException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierPermission {

	public void hasCreateDossier(long groupId, long userId, String serviceInfoCode, String govAgencyCode,
			String dossierTemplateNo) throws PortalException {
		// TODO ...
	}

	public void hasGetDetailDossier(long groupId, long userId, Dossier dossier, long serviceProcessId)
			throws PortalException {
		
		// TODO ...

		boolean isOwnner = false;

		if (dossier.getUserId() == userId) {
			isOwnner = true;
		}

		if (!isOwnner) {
			boolean isAuthorityEmpoyee = false;
			
			
			List<ServiceProcessRole> roles = ServiceProcessRoleLocalServiceUtil.findByS_P_ID(serviceProcessId);
			ok:
			for (ServiceProcessRole role : roles) {
				long [] userIds = UserLocalServiceUtil.getRoleUserIds(role.getRoleId());
				
				for (long spUid : userIds) {
					if (spUid == userId) {
						isAuthorityEmpoyee = true;
						break ok;
					}
				}
			}
			
			if (!isAuthorityEmpoyee) {
				throw new DossierAccessException("DossierAccessException");
			}
			
		}

	}

	public void checkPassword(Dossier dossier, String password) throws PortalException {

		if (Validator.isNull(dossier.getPassword())) {
			throw new DossierPasswordException("DossierPasswordException");
		}

		if (!dossier.getPassword().equalsIgnoreCase(password)) {
			throw new DossierPasswordException("DossierPasswordException");
		}
	}
	
	public void hasPermitDoAction() throws PortalException {
		
	}
}
