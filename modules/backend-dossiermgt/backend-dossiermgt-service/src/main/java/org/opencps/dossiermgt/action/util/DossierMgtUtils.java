package org.opencps.dossiermgt.action.util;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.action.PaymentFileActions;
import org.opencps.dossiermgt.action.impl.PaymentFileActionsImpl;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierMgtUtils {
	public static ProcessOption getProcessOption(String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
			long groupId) throws PortalException {

		ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceInfoCode, govAgencyCode);

		return ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTemplateNo,
				config.getServiceConfigId());
	}

	protected Dossier getDossier(long groupId, String refId) throws PortalException {

		Dossier dossier = null;

		dossier = DossierLocalServiceUtil.getByRef(groupId, refId);

		if (Validator.isNull(dossier)) {
			long dossierId = GetterUtil.getLong(refId);

			if (dossierId != 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			}
		}

		return dossier;
	}

	public static boolean checkPreCondition(String[] preConditions, Dossier dossier) {
		boolean result = true;
		
		
		
		for (String preCondition : preConditions) {
			switch (preCondition) {
			case "payok":
				result = result && checkPayOk(dossier);
				break;

			case "cancelling":
				result = result && checkCancelling(dossier);
				break;

			case "reject_cancelling":
				result = result && checkCancelling(dossier);
				break;

			case "correcting":
				result = result && checkCorrecting(dossier);
				break;

			case "reject_correcting":
				result = result && checkCorrecting(dossier);
				break;

			case "submitting":
				result = result && checkSubmitting(dossier);
				break;

			case "reject_submitting":
				result = result && checkSubmitting(dossier);
				break;

			case "waiting":
				result = result && checkWaiting(preCondition, dossier);
				break;
			default:
				break;
			}
		}

		return result;
	}

	private static boolean checkPayOk(Dossier dossier) {
		boolean result = true;
		PaymentFileActions actions = new PaymentFileActionsImpl();
		List<PaymentFile> paymentFiles = actions.getPaymentFiles(dossier.getDossierId());
		if (paymentFiles != null) {
			for (PaymentFile paymentFile : paymentFiles) {
				if (paymentFile.getPaymentStatus() != 2) {
					result = result && false;
				}
			}
		}
		return result;
	}

	private static boolean checkCancelling(Dossier dossier) {
		if (dossier.getCancellingDate() != null) {
			return true;
		}
		return false;
	}

	private static boolean checkCorrecting(Dossier dossier) {
		if (dossier.getCorrecttingDate() != null) {
			return true;
		}

		return false;
	}
	
	
	private static boolean checkSubmitting(Dossier dossier) {
		if (dossier.getEndorsementDate() != null) {
			return true;
		}

		return false;
	}
	
	private static boolean checkWaiting(String preCondition, Dossier dossier) {
		long dossierActionId = dossier.getDossierActionId();
		DossierAction action = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
		Date actionDate = action.getModifiedDate();
		String[] waitingArr = StringUtil.split(preCondition);
		if (waitingArr.length != 2) {
			return false;
		}
		String condition = waitingArr[0];
		String nBlock = waitingArr[1];
		if ("waiting".equals(condition)) {
			
		}
		else {
			return false;
		}
		
		return false;
	}
}
