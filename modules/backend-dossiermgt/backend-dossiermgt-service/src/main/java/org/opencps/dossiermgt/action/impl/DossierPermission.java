package org.opencps.dossiermgt.action.impl;

import java.util.ArrayList;
import java.util.List;

import org.opencps.dossiermgt.exception.DossierAccessException;
import org.opencps.dossiermgt.exception.DossierPasswordException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierPermission {
	private static final Log _log = LogFactoryUtil.getLog(DossierPermission.class);
	
	public boolean isCitizen(long userId) {
		try {
			Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(userId);
			if (applicant != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			return false;
		}
	}

	public void hasCreateDossier(long groupId, long userId, String serviceInfoCode, String govAgencyCode,
			String dossierTemplateNo) throws PortalException {
		// TODO ...
	}

	public void hasGetDossiers(long groupId, long userId, String secetKey) throws PortalException {

	}

	public void isAllowSubmittingDossiers(long groupId, long userId, long dossierId, String referanceUid)
			throws PortalException {

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

			List<ProcessStep> processSteps = ProcessStepLocalServiceUtil
					.getProcessStepbyServiceProcessId(serviceProcessId);

			List<ProcessStepRole> processStepRoles = new ArrayList<ProcessStepRole>();

			for (ProcessStep processStep : processSteps) {
				List<ProcessStepRole> elms;

				elms = ProcessStepRoleLocalServiceUtil.findByP_S_ID(processStep.getPrimaryKey());

				processStepRoles.addAll(elms);
			}

			ok: for (ServiceProcessRole role : roles) {
				long[] userIds = UserLocalServiceUtil.getRoleUserIds(role.getRoleId());

				for (long spUid : userIds) {
					if (spUid == userId) {
						isAuthorityEmpoyee = true;
						break ok;
					}
				}
			}

			ok2: for (ProcessStepRole role : processStepRoles) {
				long[] userIds = UserLocalServiceUtil.getRoleUserIds(role.getRoleId());

				for (long spUid : userIds) {
					if (spUid == userId) {
						isAuthorityEmpoyee = true;
						break ok2;
					}
				}
			}

			if (!isAuthorityEmpoyee) {
				throw new DossierAccessException("DossierAccessException");
			}

		}

	}

	public void checkPassword(Dossier dossier, String secretCode) throws PortalException {

		if (Validator.isNull(dossier.getPassword())) {
			throw new DossierPasswordException("DossierPasswordException");
		}

		if (!dossier.getPassword().equalsIgnoreCase(secretCode)) {
			throw new DossierPasswordException("DossierPasswordException");
		}
	}

	public void allowSubmitting(long userId, long dossierid) throws PortalException {
		// TODO add logic
	}

	public void hasPermitDoAction(long groupId, long userId, Dossier dossier, long serviceProcessId,
			ProcessAction action) throws PortalException {
		boolean isOwnner = false;

		if (dossier.getUserId() == userId) {
			isOwnner = true;
		}

		if (!isOwnner) {
			boolean isAuthorityEmpoyee = false;

			ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(action.getPreStepCode(), groupId,
					serviceProcessId);

			if (Validator.isNotNull(step)) {
				List<ProcessStepRole> roles = ProcessStepRoleLocalServiceUtil.findByP_S_ID(step.getPrimaryKey());

				ok: for (ProcessStepRole role : roles) {
					long[] userIds = UserLocalServiceUtil.getRoleUserIds(role.getRoleId());

					for (long spUid : userIds) {
						if (spUid == userId) {
							isAuthorityEmpoyee = true;
							break ok;
						}
					}
				}

			}

			if (!isAuthorityEmpoyee) {
				throw new DossierAccessException("DossierAccessException");
			}

		}

	}
}
