package org.opencps.dossiermgt.action.util;

import java.util.List;
import java.util.Map;

import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;

import backend.auth.api.exception.NotFoundException;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierActionUtils {
	public static int getSyncState(int syncType, Dossier dossier) {
		int state = DossierSyncTerm.STATE_NOT_SYNC;
		int originality = (dossier.getOriginality() > 0 ? dossier.getOriginality() : -dossier.getOriginality());

		if (originality == DossierTerm.ORIGINALITY_DVCTT) {
			if (syncType == DossierSyncTerm.SYNCTYPE_REQUEST) {
				state = DossierSyncTerm.STATE_WAITING_SYNC;
			} else if (syncType == DossierSyncTerm.SYNCTYPE_INFORM) {
				state = DossierSyncTerm.STATE_NOT_SYNC;
			}
		} else if (originality == DossierTerm.ORIGINALITY_MOTCUA) {
			state = DossierSyncTerm.STATE_NOT_SYNC;
		} else if (originality == DossierTerm.ORIGINALITY_LIENTHONG) {
			if (syncType == DossierSyncTerm.SYNCTYPE_REQUEST) {
				state = DossierSyncTerm.STATE_NOT_SYNC;
			} else if (syncType == DossierSyncTerm.SYNCTYPE_INFORM) {
				state = DossierSyncTerm.STATE_WAITING_SYNC;
			} else if (syncType == DossierSyncTerm.SYNCTYPE_INFORM_DOSSIER) {
				state = DossierSyncTerm.STATE_WAITING_SYNC;
			}
		}

		return state;
	}

	public static JSONObject buildChangedPayload(JSONObject payloadObject, Map<String, Boolean> bResult,
			Dossier dossier) {
		if (Validator.isNotNull(dossier.getDossierNote()))
			payloadObject.put(DossierTerm.DOSSIER_NOTE, dossier.getDossierNote());
		if (dossier.getExtendDate() != null && bResult != null && bResult.containsKey(DossierTerm.EXTEND_DATE)
				&& bResult.get(DossierTerm.EXTEND_DATE))
			payloadObject.put(DossierTerm.EXTEND_DATE, dossier.getExtendDate().getTime());
		if (dossier.getReceiveDate() != null && bResult != null && bResult.containsKey(DossierTerm.RECEIVE_DATE)
				&& bResult.get(DossierTerm.RECEIVE_DATE))
			payloadObject.put(DossierTerm.RECEIVE_DATE, dossier.getReceiveDate().getTime());
		if (Validator.isNotNull(dossier.getDossierNo()) && bResult != null
				&& bResult.containsKey(DossierTerm.DOSSIER_NO) && bResult.get(DossierTerm.DOSSIER_NO))
			payloadObject.put(DossierTerm.DOSSIER_NO, dossier.getDossierNo());
		if (dossier.getDueDate() != null && bResult != null && bResult.containsKey(DossierTerm.DUE_DATE)
				&& bResult.get(DossierTerm.DUE_DATE))
			payloadObject.put(DossierTerm.DUE_DATE, dossier.getDueDate().getTime());
		if (bResult != null && bResult.containsKey(DossierTerm.FINISH_DATE) && bResult.get(DossierTerm.FINISH_DATE)
				&& dossier.getFinishDate() != null)
			payloadObject.put(DossierTerm.FINISH_DATE, dossier.getFinishDate().getTime());
		if (dossier.getSubmitDate() != null && bResult != null && bResult.containsKey(DossierTerm.SUBMIT_DATE)
				&& bResult.get(DossierTerm.SUBMIT_DATE))
			payloadObject.put(DossierTerm.SUBMIT_DATE, dossier.getSubmitDate().getTime());
		if (dossier.getReleaseDate() != null && bResult != null && bResult.containsKey(DossierTerm.RELEASE_DATE)
				&& bResult.get(DossierTerm.RELEASE_DATE)) {
			payloadObject.put(DossierTerm.RELEASE_DATE, dossier.getReleaseDate().getTime());
		}

		return payloadObject;
	}

	public static ProcessAction getProcessAction(long groupId, Dossier dossier, String actionCode,
			long serviceProcessId) throws PortalException {

		ProcessAction action = null;

		try {
			List<ProcessAction> actions = ProcessActionLocalServiceUtil.getByActionCode(groupId, actionCode,
					serviceProcessId);

			String dossierStatus = dossier.getDossierStatus();

			String dossierSubStatus = Validator.isNull(dossier.getDossierSubStatus()) ? StringPool.BLANK
					: dossier.getDossierSubStatus();
			String curStepCode = StringPool.BLANK;
			if (dossier.getDossierActionId() > 0) {
				DossierAction curAction = DossierActionLocalServiceUtil
						.fetchDossierAction(dossier.getDossierActionId());
				if (curAction != null) {
					curStepCode = curAction.getStepCode();
				}
			}

			for (ProcessAction act : actions) {

				String preStepCode = act.getPreStepCode();
				if (Validator.isNotNull(curStepCode) && !preStepCode.contentEquals(curStepCode))
					continue;

				ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(preStepCode, groupId, serviceProcessId);

				String subStepStatus = StringPool.BLANK;

				if (Validator.isNotNull(step)) {
					subStepStatus = Validator.isNull(step.getDossierSubStatus()) ? StringPool.BLANK
							: step.getDossierSubStatus();
				}

				if (Validator.isNull(step)) {
					action = act;
					break;
				} else {

					if (step.getDossierStatus().contentEquals(dossierStatus)
							&& subStepStatus.contentEquals(dossierSubStatus)) {

						action = act;
						break;
					}
				}
			}

		} catch (Exception e) {

			_log.error(e);

			throw new NotFoundException("ProcessActionNotFoundException with actionCode= " + actionCode
					+ "|serviceProcessId= " + serviceProcessId + "|referenceUid= " + dossier.getReferenceUid()
					+ "|groupId= " + groupId);
		}

		return action;
	}

	private static Log _log = LogFactoryUtil.getLog(DossierActionUtils.class.getName());
}
