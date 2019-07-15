package org.opencps.dossiermgt.action.util;

import java.util.Map;

import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

public class DossierActionUtils {
	public static int getSyncState(int syncType, Dossier dossier) {
		int state = DossierSyncTerm.STATE_NOT_SYNC;
		int originality = (dossier.getOriginality() > 0 ? dossier.getOriginality() : -dossier.getOriginality());
		
		if (originality == DossierTerm.ORIGINALITY_DVCTT) {
			if (syncType == DossierSyncTerm.SYNCTYPE_REQUEST) {
				state = DossierSyncTerm.STATE_WAITING_SYNC;
			}
			else if (syncType == DossierSyncTerm.SYNCTYPE_INFORM) {
				state = DossierSyncTerm.STATE_NOT_SYNC;
			}
		}
		else if (originality == DossierTerm.ORIGINALITY_MOTCUA) {
			state = DossierSyncTerm.STATE_NOT_SYNC;
		}
		else if (originality == DossierTerm.ORIGINALITY_LIENTHONG) {
			if (syncType == DossierSyncTerm.SYNCTYPE_REQUEST) {
				state = DossierSyncTerm.STATE_NOT_SYNC;
			}
			else if (syncType == DossierSyncTerm.SYNCTYPE_INFORM) {
				state = DossierSyncTerm.STATE_WAITING_SYNC;
			}				
		}	
		
		return state;
	}
	
	public static JSONObject buildChangedPayload(JSONObject payloadObject, Map<String, Boolean> bResult, Dossier dossier) {
		if (Validator.isNotNull(dossier.getDossierNote()))
			payloadObject.put(DossierTerm.DOSSIER_NOTE, dossier.getDossierNote());
		if (dossier.getExtendDate() != null && bResult != null &&
				bResult.containsKey(DossierTerm.EXTEND_DATE) && bResult.get(DossierTerm.EXTEND_DATE))
			payloadObject.put(DossierTerm.EXTEND_DATE, dossier.getExtendDate().getTime());
		if (dossier.getReceiveDate() != null && bResult != null && bResult.containsKey(DossierTerm.RECEIVE_DATE) && bResult.get(DossierTerm.RECEIVE_DATE))
			payloadObject.put(DossierTerm.RECEIVE_DATE, dossier.getReceiveDate().getTime());
		if (Validator.isNotNull(dossier.getDossierNo()) && bResult != null && 
				bResult.containsKey(DossierTerm.DOSSIER_NO) &&
				bResult.get(DossierTerm.DOSSIER_NO))
			payloadObject.put(DossierTerm.DOSSIER_NO, dossier.getDossierNo());
		if (dossier.getDueDate() != null && bResult != null && bResult.containsKey(DossierTerm.DUE_DATE)
				&& bResult.get(DossierTerm.DUE_DATE))
			payloadObject.put(DossierTerm.DUE_DATE, dossier.getDueDate().getTime());
		if (bResult != null && bResult.containsKey(DossierTerm.FINISH_DATE)
				&& bResult.get(DossierTerm.FINISH_DATE)
				&& dossier.getFinishDate() != null)
			payloadObject.put(DossierTerm.FINISH_DATE, dossier.getFinishDate().getTime());
		if (dossier.getSubmitDate() != null &&
				bResult != null && bResult.containsKey(DossierTerm.SUBMIT_DATE)
				&& bResult.get(DossierTerm.SUBMIT_DATE))
			payloadObject.put(DossierTerm.SUBMIT_DATE, dossier.getSubmitDate().getTime());
		if (dossier.getReleaseDate() != null &&
				bResult != null && bResult.containsKey(DossierTerm.RELEASE_DATE)
				&& bResult.get(DossierTerm.RELEASE_DATE)) {
			payloadObject.put(DossierTerm.RELEASE_DATE, dossier.getReleaseDate().getTime());
		}
		
		return payloadObject;
	}
}
