package org.opencps.dossiermgt.action.util;

import java.util.List;

import org.opencps.dossiermgt.action.PaymentFileActions;
import org.opencps.dossiermgt.action.impl.PaymentFileActionsImpl;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierMgtUtils {
	public static boolean isDueDateEditable(String condition) {
		try {
			JSONObject obj = JSONFactoryUtil.createJSONObject(condition);
			if (obj.has(ProcessActionTerm.DUE_DATE_EDITABLE)) {
				return obj.getBoolean(ProcessActionTerm.DUE_DATE_EDITABLE);
			}
		} catch (JSONException e) {
			return false;
		}
		
		return condition.contains(ProcessActionTerm.DUE_DATE_EDITABLE);
	}
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
			
			preCondition.trim();
			
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
					
				case "online=true":
					result = result && checkDossierOnline(dossier);
					break;
				case "online=false":
					result = result && checkDossierOnegate(dossier);
					break;
				default:
					break;
			}
			if (preCondition.contains("service=")) {
				String[] splitCodes = preCondition.split("=");
				if (splitCodes.length == 2) {
					result = result && checkServiceCode(splitCodes[1], dossier);
				}			
			}
			if (preCondition.contains("agency=")) {
				String[] splitAgencies = preCondition.split("=");
				if (splitAgencies.length == 2) {
					result = result && checkAgencyCode(splitAgencies[1], dossier);
				}							
			}
			if (preCondition.contains("template=")) {
				String[] splitTemplates = preCondition.split("=");
				if (splitTemplates.length == 2) {
					result = result && checkTemplateCode(splitTemplates[1], dossier);
				}											
			}
			if (preCondition.contains("originality=")) {
				String[] splitOriginalities = preCondition.split("=");
				if (splitOriginalities.length == 2) {
					result = result && checkOriginality(splitOriginalities[1], dossier);
				}															
			}
			if (preCondition.contains("stepdone=")) {
				String[] splitSteps = preCondition.split("=");
				if (splitSteps.length == 2) {
					result = result && checkStepDone(splitSteps[1], dossier);
				}																			
			}
			if (preCondition.contains("stepnotdone=")) {
				String[] splitSteps = preCondition.split("=");
				if (splitSteps.length == 2) {
					result = result && checkStepNotDone(splitSteps[1], dossier);
				}																			
			}
			if (preCondition.contains("#")) {
				String[] splitBiens = preCondition.split("=");
				if (splitBiens.length == 2) {
					result = result && checkBien(splitBiens[0], splitBiens[1], dossier);
				}																							
			}
			if (preCondition.contains("viapostal=")) {
				String[] splitViaPostals = preCondition.split("=");
				if (splitViaPostals.length == 2) {
					result = result && checkViaPostal(splitViaPostals[1], dossier);
				}																							
			}
		}

		return result;
	}
	
	private static boolean checkServiceCode(String serviceCode, Dossier dossier) {
		if (serviceCode.equalsIgnoreCase(dossier.getServiceCode())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static boolean checkAgencyCode(String agencyCode, Dossier dossier) {
		if (agencyCode.equalsIgnoreCase(dossier.getGovAgencyCode())) {
			return true;
		}
		else {
			return false;
		}
	}

	private static boolean checkTemplateCode(String templateCode, Dossier dossier) {
		if (templateCode.equalsIgnoreCase(dossier.getDossierTemplateNo())) {
			return true;
		}
		else {
			return false;
		}
	}

	private static boolean checkStepDone(String stepCode, Dossier dossier) {
		List<DossierActionUser> lstDaus = DossierActionUserLocalServiceUtil.getByDossierAndStepCode(dossier.getDossierId(), stepCode);
		if (lstDaus.size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	private static boolean checkStepNotDone(String stepCode, Dossier dossier) {
		return !checkStepDone(stepCode, dossier);
	}
	
	private static boolean checkBien(String key, String value, Dossier dossier) {
		ServiceContext context = new ServiceContext();
		context.setUserId(dossier.getUserId());
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
		jsonObj.put("key", key);
		String dataBinding = AutoFillFormData.sampleDataBinding(jsonObj.toJSONString(), dossier.getDossierId(), context);
		JSONObject resultObj;
		try {
			resultObj = JSONFactoryUtil.createJSONObject(dataBinding);
			if (resultObj.has("key") && resultObj.getString("key").equals(value)) {
				return true;
			}
			else {
				return false;
			}
		} catch (JSONException e) {
//			e.printStackTrace();
		}
		
		return false;
	}
	
	private static boolean checkOriginality(String originality, Dossier dossier) {
		int o = Integer.valueOf(originality);
		
		if (o == dossier.getOriginality()) {
			return true;
		}
		else {
			return false;
		}
	}

	private static boolean checkViaPostal(String viaPostal, Dossier dossier) {
		int viaPostalInt = Integer.valueOf(viaPostal);
		
		if (viaPostalInt == dossier.getViaPostal()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static boolean checkDossierOnline(Dossier dossier) {
		if (dossier.getOnline()) 
			return true;
		else
			return false;
	}

	private static boolean checkDossierOnegate(Dossier dossier) {
		if (dossier.getOnline()) 
			return false;
		else
			return true;
	}
	
	private static boolean checkPayOk(Dossier dossier) {
		boolean result = true;
		PaymentFileActions actions = new PaymentFileActionsImpl();
		PaymentFile paymentFile = actions.getPaymentFiles(dossier.getGroupId(), dossier.getDossierId());
		if (paymentFile != null) {
			if (paymentFile.getPaymentStatus() != 2) {
				result = result && false;
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
//		Date actionDate = action.getModifiedDate();
		String[] waitingArr = StringUtil.split(preCondition);
		if (waitingArr.length != 2) {
			return false;
		}
		String condition = waitingArr[0];
//		String nBlock = waitingArr[1];
		if ("waiting".equals(condition)) {
			
		}
		else {
			return false;
		}
		
		return false;
	}
}
