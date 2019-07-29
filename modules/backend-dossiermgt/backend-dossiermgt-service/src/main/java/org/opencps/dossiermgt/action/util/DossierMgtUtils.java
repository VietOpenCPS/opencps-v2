package org.opencps.dossiermgt.action.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.PaymentFileActions;
import org.opencps.dossiermgt.action.impl.PaymentFileActionsImpl;
import org.opencps.dossiermgt.constants.ActionConfigTerm;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierActionUserTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.ServiceProcessTerm;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessSequence;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.rest.utils.OpenCPSRestClient;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessSequenceLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

public class DossierMgtUtils {
	private static final Log _log = LogFactoryUtil.getLog(DossierMgtUtils.class);
	
	public static JSONObject convertDossierToJSON(Dossier dossier) {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		
		obj.put(DossierTerm.DOSSIER_ID, dossier.getDossierId());
		obj.put(DossierTerm.GROUP_ID, dossier.getGroupId());
		obj.put(DossierTerm.SERVER_NO, dossier.getServerNo());
		obj.put(DossierTerm.REFERENCE_UID, dossier.getReferenceUid());
		obj.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
		obj.put(DossierTerm.SERVICE_NAME, dossier.getServiceName());
		obj.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
		obj.put(DossierTerm.GOV_AGENCY_NAME, dossier.getGovAgencyName());
		obj.put(DossierTerm.APPLICANT_NAME, dossier.getApplicantName());
		obj.put(DossierTerm.APPLICANT_ID_TYPE, dossier.getApplicantIdType());
		obj.put(DossierTerm.APPLICANT_ID_NO, dossier.getApplicantIdNo());
		obj.put(DossierTerm.APPLICANT_ID_DATE, dossier.getApplicantIdDate() != null ? dossier.getApplicantIdDate().getTime() : 0l);
		obj.put(DossierTerm.ADDRESS, dossier.getAddress());
		obj.put(DossierTerm.CITY_CODE, dossier.getCityCode());
		obj.put(DossierTerm.CITY_NAME, dossier.getCityName());
		obj.put(DossierTerm.DISTRICT_CODE, dossier.getDistrictCode());
		obj.put(DossierTerm.DISTRICT_NAME, dossier.getDistrictName());
		obj.put(DossierTerm.WARD_CODE, dossier.getWardCode());
		obj.put(DossierTerm.WARD_NAME, dossier.getWardName());
		obj.put(DossierTerm.CONTACT_NAME, dossier.getContactName());
		obj.put(DossierTerm.CONTACT_TEL_NO, dossier.getContactTelNo());
		obj.put(DossierTerm.CONTACT_EMAIL, dossier.getContactEmail());
		obj.put(DossierTerm.DOSSIER_NOTE, dossier.getDossierNote());
		obj.put(DossierTerm.BRIEF_NOTE, dossier.getBriefNote());
		obj.put(DossierTerm.DOSSIER_NO, dossier.getDossierNo());
		obj.put(DossierTerm.SUBMIT_DATE, dossier.getSubmitDate() != null ? dossier.getSubmitDate().getTime() : 0l);
		obj.put(DossierTerm.RECEIVE_DATE, dossier.getReceiveDate() != null ? dossier.getReceiveDate().getTime() : 0l);
		obj.put(DossierTerm.PROCESS_DATE, dossier.getProcessDate() != null ? dossier.getProcessDate().getTime() : 0l);
		obj.put(DossierTerm.DUE_DATE, dossier.getDueDate() != null ? dossier.getDueDate().getTime() : 0l);
		obj.put(DossierTerm.RELEASE_DATE, dossier.getReleaseDate() != null ? dossier.getReleaseDate().getTime() : 0l);
		obj.put(DossierTerm.FINISH_DATE, dossier.getFinishDate() != null ? dossier.getFinishDate().getTime() : 0l);
		obj.put(DossierTerm.EXTEND_DATE, dossier.getExtendDate() != null ? dossier.getExtendDate().getTime() : 0l);
		obj.put(DossierTerm.ENDORSEMENT_DATE, dossier.getEndorsementDate() != null ? dossier.getEndorsementDate().getTime() : 0l);
		obj.put(DossierTerm.CREATE_DATE, dossier.getCreateDate() != null ? dossier.getCreateDate().getTime() : 0l);
		obj.put(DossierTerm.MODIFIED_DATE, dossier.getModifiedDate() != null ? dossier.getModifiedDate().getTime() : 0l);
		obj.put(DossierTerm.CANCELLING_DATE, dossier.getCancellingDate() != null ? dossier.getCancellingDate().getTime() : 0l);
		obj.put(DossierTerm.CORRECTING_DATE, dossier.getCorrecttingDate() != null ? dossier.getCorrecttingDate().getTime() : 0l);
		obj.put(DossierTerm.DOSSIER_STATUS, dossier.getDossierStatus());
		obj.put(DossierTerm.DOSSIER_STATUS_TEXT, dossier.getDossierStatusText());
		obj.put(DossierTerm.DOSSIER_SUB_STATUS, dossier.getDossierSubStatus());
		obj.put(DossierTerm.DOSSIER_SUB_STATUS_TEXT, dossier.getDossierSubStatusText());
		obj.put(DossierTerm.ONLINE, dossier.getOnline());
		obj.put(DossierTerm.SECRET, dossier.getPassword());
		obj.put(DossierTerm.DOSSIER_NAME, dossier.getDossierName());
		obj.put(DossierTerm.DOSSIER_ACTION_ID, dossier.getDossierActionId());
		obj.put(DossierTerm.POSTAL_ADDRESS, dossier.getPostalAddress());
		obj.put(DossierTerm.POSTAL_CITY_CODE, dossier.getPostalCityCode());
		obj.put(DossierTerm.POSTAL_TEL_NO, dossier.getPostalTelNo());
		obj.put(DossierTerm.POSTAL_DISTRICT_CODE, dossier.getPostalDistrictCode());
		obj.put(DossierTerm.DELEGATE_NAME, dossier.getDelegateName());
		obj.put(DossierTerm.DELEGATE_EMAIL, dossier.getDelegateEmail());
		String serviceCode = dossier.getServiceCode();
		ServiceProcess serviceProcess;
		try {
			serviceProcess = ServiceProcessLocalServiceUtil.getServiceByCode(dossier.getGroupId(), serviceCode, dossier.getGovAgencyCode(), dossier.getDossierTemplateNo());
			if (serviceProcess == null) {
			}
			else {
				JSONObject submissionNoteObj = getDossierProcessSequencesPublishJSON(dossier.getGroupId(), dossier, serviceProcess);
				obj.put(DossierTerm.SUBMISSION_NOTE, submissionNoteObj.toJSONString());
			}
		} catch (PortalException e) {
			_log.debug(e);
		}
		
		obj.put(DossierTerm.DOSSIER_TEMPLATE_NO, dossier.getDossierTemplateNo());
		obj.put(DossierTerm.DOSSIER_TEMPLATE_NAME, dossier.getDossierTemplateName());
		obj.put(DossierTerm.VIA_POSTAL, dossier.getViaPostal());
		obj.put(DossierTerm.SERVER_NO, dossier.getServerNo());
		obj.put(DossierTerm.DELEGATE_NAME, dossier.getDelegateName());
		obj.put(DossierTerm.DELEGATE_ID_NO, dossier.getDelegateIdNo());
		obj.put(DossierTerm.DELEGATE_TELNO, dossier.getDelegateTelNo());
		obj.put(DossierTerm.DELEGATE_EMAIL, dossier.getDelegateEmail());		
		obj.put(DossierTerm.DELEGATE_ADDRESS, dossier.getDelegateAddress());
		obj.put(DossierTerm.DELEGATE_CITYCODE, dossier.getDelegateCityCode());
		obj.put(DossierTerm.DELEGATE_CITYNAME, dossier.getDelegateCityName());
		obj.put(DossierTerm.DELEGATE_DISTRICTCODE, dossier.getDelegateDistrictCode());
		obj.put(DossierTerm.DELEGATE_DISTRICTNAME, dossier.getDelegateDistrictName());
		obj.put(DossierTerm.DELEGATE_WARDCODE, dossier.getDelegateWardCode());
		obj.put(DossierTerm.DELEGATE_WARDNAME, dossier.getDelegateWardName());
		obj.put(DossierTerm.PROCESS_NO, dossier.getProcessNo());
		obj.put(DossierTerm.DURATION_COUNT, dossier.getDurationCount());		
		obj.put(DossierTerm.DURATION_UNIT, dossier.getDurationUnit());
		obj.put(DossierTerm.DOSSIER_NAME, dossier.getDossierName());
		obj.put(DossierTerm.LOCK_STATE, dossier.getLockState());
		obj.put(DossierTerm.COUNTER, dossier.getCounter());
		obj.put(DossierTerm.POSTAL_ADDRESS, dossier.getPostalAddress());
		obj.put(DossierTerm.POSTAL_CITY_CODE, dossier.getPostalCityCode());
		obj.put(DossierTerm.POSTAL_CITY_NAME, dossier.getPostalCityName());
		obj.put(DossierTerm.POSTAL_DISTRICT_CODE, dossier.getPostalDistrictCode());
		obj.put(DossierTerm.POSTAL_DISTRICT_NAME, dossier.getPostalDistrictName());
		obj.put(DossierTerm.POSTAL_WARD_CODE, dossier.getPostalWardCode());
		obj.put(DossierTerm.POSTAL_WARD_NAME, dossier.getPostalWardName());
		obj.put(DossierTerm.POSTAL_TEL_NO, dossier.getPostalTelNo());
		obj.put(DossierTerm.POSTAL_SERVICE_CODE, dossier.getPostalServiceCode());
		obj.put(DossierTerm.POSTAL_SERVICE_NAME, dossier.getPostalServiceName());
		obj.put(DossierTerm.DELEGATE_NAME, dossier.getDelegateName());
		obj.put(DossierTerm.DELEGATE_ID_NO, dossier.getDelegateIdNo());
		obj.put(DossierTerm.DELEGATE_TELNO, dossier.getDelegateTelNo());
		obj.put(DossierTerm.DELEGATE_EMAIL, dossier.getDelegateEmail());
		obj.put(DossierTerm.DELEGATE_CITYCODE, dossier.getDelegateCityCode());
		obj.put(DossierTerm.DELEGATE_CITYNAME, dossier.getDelegateCityName());
		obj.put(DossierTerm.DELEGATE_DISTRICTCODE, dossier.getDelegateDistrictCode());
		obj.put(DossierTerm.DELEGATE_DISTRICTNAME, dossier.getDelegateDistrictName());
		obj.put(DossierTerm.DELEGATE_WARDCODE, dossier.getDelegateWardCode());
		obj.put(DossierTerm.DELEGATE_WARDNAME, dossier.getDelegateWardName());
		obj.put(DossierTerm.PROCESS_NO, dossier.getProcessNo());
		obj.put(DossierTerm.DURATION_COUNT, dossier.getDurationCount());
		obj.put(DossierTerm.DURATION_UNIT, dossier.getDurationUnit());
		obj.put(DossierTerm.SAMPLE_COUNT, dossier.getSampleCount());
		obj.put(DossierTerm.DOSSIER_NAME, dossier.getDossierName());
		obj.put(DossierTerm.ORIGIN_DOSSIER_ID, dossier.getOriginDossierId());
		obj.put(DossierTerm.ORIGIN_DOSSIER_NO, dossier.getOriginDossierNo());
		
		return obj;
	}
	
	public static JSONObject getDossierProcessSequencesJSON(long groupId, Dossier dossier, ServiceProcess serviceProcess) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ProcessSequence> lstSequences = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId, serviceProcess.getServiceProcessId());

		result.put(ServiceProcessTerm.PROCESS_NO, serviceProcess.getProcessNo());
		result.put(ServiceProcessTerm.DURATION_UNIT, serviceProcess.getDurationUnit());
		result.put(ServiceProcessTerm.DURATION_COUNT, serviceProcess.getDurationCount());
		result.put("total", lstSequences.size());
		JSONArray sequenceArr = JSONFactoryUtil.createJSONArray();
		DossierAction lastDA = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		List<DossierActionUser> lstDus = DossierActionUserLocalServiceUtil.getListUser(dossier.getDossierActionId());
		
		for (ProcessSequence ps : lstSequences) {		
			JSONObject sequenceObj = JSONFactoryUtil.createJSONObject();
			sequenceObj.put("sequenceNo", ps.getSequenceNo());
			sequenceObj.put("sequenceName", ps.getSequenceName());
			sequenceObj.put("sequenceRole", ps.getSequenceRole());
			sequenceObj.put("durationCount", ps.getDurationCount());
			
			if (lastDA != null && lastDA.getSequenceNo().equals(ps.getSequenceNo())) {
				sequenceObj.put("statusText", "Đang thực hiện: " + lastDA.getStepName());
			}
			List<DossierAction> lstDossierActions = DossierActionLocalServiceUtil.findDossierActionByG_DID_FSN(groupId, dossier.getDossierId(), ps.getSequenceNo());
			List<DossierAction> lstPrevDossierActions = DossierActionLocalServiceUtil.findDossierActionByG_DID_SN(groupId, dossier.getDossierId(), ps.getSequenceNo());
				
			DossierAction lastAction = lstPrevDossierActions.size() > 0 ? lstPrevDossierActions.get(lstPrevDossierActions.size() - 1) : null;
			if (lastAction != null) {
				sequenceObj.put("startDate", lastAction.getCreateDate().getTime());
			}			
			
			if (lstDossierActions.size() > 0) {
				DossierAction lastDASequence = lstDossierActions.get(lstDossierActions.size() - 1);
				if (lastDASequence.getActionOverdue() != 0) {
					String preText = (lastDASequence.getActionOverdue() > 0 ? "Còn " : "Quá ");
					sequenceObj.put("overdueText", preText + lastDASequence.getActionOverdue() + " ngày");
				}
			}
			JSONArray assignUserArr = JSONFactoryUtil.createJSONArray();
			List<Long> lstUsers = new ArrayList<>();
			
			if (lastDA != null && lastDA.getSequenceNo().equals(ps.getSequenceNo())) {
				for (DossierActionUser dau : lstDus) {
					User u = UserLocalServiceUtil.fetchUser(dau.getUserId());
					
					if (!lstUsers.contains(dau.getUserId()) && dau.getModerator() == DossierActionUserTerm.ASSIGNED_TH) {
						JSONObject assignUserObj = JSONFactoryUtil.createJSONObject();
						lstUsers.add(dau.getUserId());
						assignUserObj.put("userId", dau.getUserId());
						//
						Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, u.getUserId());
						if (employee != null) {
							assignUserObj.put("userName", employee.getFullName());
						} else {
							assignUserObj.put("userName", u.getFullName());
						}
						
						assignUserArr.put(assignUserObj);					
					}					
				}
			}
			for (DossierAction da : lstDossierActions) {
				if (!lstUsers.contains(da.getUserId())) {
					JSONObject assignUserObj = JSONFactoryUtil.createJSONObject();
					lstUsers.add(da.getUserId());
					assignUserObj.put("userId", da.getUserId());

					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, da.getUserId());
					if (employee != null) {
						assignUserObj.put("userName", employee.getFullName());
					} else {
						assignUserObj.put("userName", da.getUserName());
					}
					
					assignUserArr.put(assignUserObj);					
				}
			}
			
			sequenceObj.put("assignUsers", assignUserArr);
			
			JSONArray actionsArr = JSONFactoryUtil.createJSONArray();
			for (DossierAction da : lstDossierActions) {
				JSONObject actionObj = JSONFactoryUtil.createJSONObject();
				
				actionObj.put(DossierActionTerm.USER_ID, da.getUserId());
				actionObj.put("fromStepCode", da.getFromStepCode());
				actionObj.put("fromStepName", da.getFromStepName());
				actionObj.put("sequenceNo", da.getSequenceNo());
				actionObj.put("dossierId", da.getDossierId());
				actionObj.put("serviceProcessId", da.getServiceProcessId());
				actionObj.put("previousActionId", da.getPreviousActionId());
				actionObj.put("actionCode", da.getActionCode());
				actionObj.put("actionName", da.getActionName());
				actionObj.put("actionNote", da.getActionNote());
				actionObj.put("actionUser", da.getActionUser());
				actionObj.put("actionOverdue", da.getActionOverdue());
				actionObj.put("payload", da.getPayload());
				actionObj.put("pending", da.getPending());
				actionObj.put("rollbackable", da.getRollbackable());
				actionObj.put("createDate", da.getCreateDate() != null ? da.getCreateDate().getTime() : 0l);
				actionObj.put("modifiedDate", da.getModifiedDate() != null ? da.getModifiedDate().getTime() : 0l);
				actionObj.put("dueDate", da.getDueDate() != null ? da.getDueDate().getTime() : 0l);
				actionObj.put("nextActionId", da.getNextActionId());
				actionObj.put("state", da.getState());
				actionObj.put("stepCode", da.getStepCode());
				actionObj.put("stepName", da.getStepName());
				actionObj.put("userId", da.getUserId());				
				
				_log.debug("Action obj: " + actionObj.toJSONString());
				actionsArr.put(actionObj);
			}			
			
			sequenceObj.put("actions", actionsArr);
			
			sequenceArr.put(sequenceObj);
			
		}
		
		result.put("data", sequenceArr);
		return result;
	}
	
	public static JSONObject getDossierProcessSequencesPublishJSON(long groupId, Dossier dossier, ServiceProcess serviceProcess) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ProcessSequence> lstSequences = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId, serviceProcess.getServiceProcessId());

		result.put(ServiceProcessTerm.PROCESS_NO, serviceProcess.getProcessNo());
		result.put(ServiceProcessTerm.DURATION_UNIT, serviceProcess.getDurationUnit());
		result.put(ServiceProcessTerm.DURATION_COUNT, serviceProcess.getDurationCount());
		result.put("total", lstSequences.size());
		JSONArray sequenceArr = JSONFactoryUtil.createJSONArray();
		DossierAction lastDA = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		List<DossierActionUser> lstDus = DossierActionUserLocalServiceUtil.getListUser(dossier.getDossierActionId());
		List<ActionConfig> lstAcs = ActionConfigLocalServiceUtil.getByG_ET(groupId, ActionConfigTerm.EVENT_TYPE_SENT);
		List<ProcessSequence> lstPublishSequences = null;
		String[] acs = new String[lstAcs.size()];
		List<String> lstActions = new ArrayList<>();
		
		int count = 0;
		for (ActionConfig ac : lstAcs) {
			acs[count++] = ac.getActionCode();
			lstActions.add(ac.getActionCode());
		}
		count = 0;
		List<ProcessAction> lstPas = ProcessActionLocalServiceUtil.getByG_SID_ACS(groupId, serviceProcess.getServiceProcessId(), acs);
		String[] scs = new String[lstPas.size()];
		for (ProcessAction pa : lstPas) {
			scs[count++] = pa.getPreStepCode();
		}
		List<ProcessStep> lstPss = ProcessStepLocalServiceUtil.getByG_SP_SCS(groupId, serviceProcess.getServiceProcessId(), scs);
		String[] sns = new String[lstPss.size()];
		count = 0;
		for (ProcessStep ps : lstPss) {
			sns[count++] = ps.getSequenceNo();
		}
		lstPublishSequences = ProcessSequenceLocalServiceUtil.getByG_SID_SNOS(groupId, serviceProcess.getServiceProcessId(), sns);
		
		for (ProcessSequence ps : lstPublishSequences) {		
			JSONObject sequenceObj = JSONFactoryUtil.createJSONObject();
			sequenceObj.put("sequenceNo", ps.getSequenceNo());
			sequenceObj.put("sequenceName", ps.getSequenceName());
			sequenceObj.put("sequenceRole", ps.getSequenceRole());
			sequenceObj.put("durationCount", ps.getDurationCount());
			
			if (lastDA != null && lastDA.getSequenceNo().equals(ps.getSequenceNo())) {
				sequenceObj.put("statusText", "Đang thực hiện: " + lastDA.getStepName());
			}
			List<DossierAction> lstDossierActions = DossierActionLocalServiceUtil.findDossierActionByG_DID_FSN(groupId, dossier.getDossierId(), ps.getSequenceNo());
			List<DossierAction> lstPrevDossierActions = DossierActionLocalServiceUtil.findDossierActionByG_DID_SN(groupId, dossier.getDossierId(), ps.getSequenceNo());
				
			DossierAction lastAction = lstPrevDossierActions.size() > 0 ? lstPrevDossierActions.get(lstPrevDossierActions.size() - 1) : null;
			if (lastAction != null) {
				sequenceObj.put("startDate", lastAction.getCreateDate().getTime());
			}			
			
			if (lstDossierActions.size() > 0) {
				DossierAction lastDASequence = lstDossierActions.get(lstDossierActions.size() - 1);
				if (lastDASequence.getActionOverdue() != 0) {
					String preText = (lastDASequence.getActionOverdue() > 0 ? "Còn " : "Quá ");
					sequenceObj.put("overdueText", preText + lastDASequence.getActionOverdue() + " ngày");
				}
			}
			JSONArray assignUserArr = JSONFactoryUtil.createJSONArray();
			List<Long> lstUsers = new ArrayList<>();
			
			if (lastDA != null && lastDA.getSequenceNo().equals(ps.getSequenceNo())) {
				for (DossierActionUser dau : lstDus) {
					User u = UserLocalServiceUtil.fetchUser(dau.getUserId());
					
					if (!lstUsers.contains(dau.getUserId()) && dau.getModerator() == DossierActionUserTerm.ASSIGNED_TH) {
						JSONObject assignUserObj = JSONFactoryUtil.createJSONObject();
						lstUsers.add(dau.getUserId());
						assignUserObj.put("userId", dau.getUserId());
						//
						Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, u.getUserId());
						if (employee != null) {
							assignUserObj.put("userName", employee.getFullName());
						} else {
							assignUserObj.put("userName", u.getFullName());
						}
						
						assignUserArr.put(assignUserObj);					
					}					
				}
			}
			for (DossierAction da : lstDossierActions) {
				if (!lstUsers.contains(da.getUserId())) {
					JSONObject assignUserObj = JSONFactoryUtil.createJSONObject();
					lstUsers.add(da.getUserId());
					assignUserObj.put("userId", da.getUserId());

					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, da.getUserId());
					if (employee != null) {
						assignUserObj.put("userName", employee.getFullName());
					} else {
						assignUserObj.put("userName", da.getUserName());
					}
					
					assignUserArr.put(assignUserObj);					
				}
			}
			
			sequenceObj.put("assignUsers", assignUserArr);
			
			JSONArray actionsArr = JSONFactoryUtil.createJSONArray();
			for (DossierAction da : lstDossierActions) {
				if (lstActions.contains(da.getActionCode())) {
					JSONObject actionObj = JSONFactoryUtil.createJSONObject();
					
					actionObj.put(DossierActionTerm.USER_ID, da.getUserId());
					actionObj.put("fromStepCode", da.getFromStepCode());
					actionObj.put("fromStepName", da.getFromStepName());
					actionObj.put("sequenceNo", da.getSequenceNo());
					actionObj.put("dossierId", da.getDossierId());
					actionObj.put("serviceProcessId", da.getServiceProcessId());
					actionObj.put("previousActionId", da.getPreviousActionId());
					actionObj.put("actionCode", da.getActionCode());
					actionObj.put("actionName", da.getActionName());
					actionObj.put("actionNote", da.getActionNote());
					actionObj.put("actionUser", da.getActionUser());
					actionObj.put("actionOverdue", da.getActionOverdue());
					actionObj.put("payload", da.getPayload());
					actionObj.put("pending", da.getPending());
					actionObj.put("rollbackable", da.getRollbackable());
					actionObj.put("createDate", da.getCreateDate() != null ? da.getCreateDate().getTime() : 0l);
					actionObj.put("modifiedDate", da.getModifiedDate() != null ? da.getModifiedDate().getTime() : 0l);
					actionObj.put("dueDate", da.getDueDate() != null ? da.getDueDate().getTime() : 0l);
					actionObj.put("nextActionId", da.getNextActionId());
					actionObj.put("state", da.getState());
					actionObj.put("stepCode", da.getStepCode());
					actionObj.put("stepName", da.getStepName());
					actionObj.put("userId", da.getUserId());				
					
					_log.debug("Action obj: " + actionObj.toJSONString());
					actionsArr.put(actionObj);					
				}
			}			
			
			sequenceObj.put("actions", actionsArr);
			
			sequenceArr.put(sequenceObj);
			
		}
		
		result.put("data", sequenceArr);
		return result;
	}
	
	public static boolean isDueDateEditable(String condition) {
		try {
			JSONObject obj = JSONFactoryUtil.createJSONObject(condition);
			if (obj.has(ProcessActionTerm.DUE_DATE_EDITABLE)) {
				return obj.getBoolean(ProcessActionTerm.DUE_DATE_EDITABLE);
			}
		} catch (JSONException e) {
			_log.debug(e);
			//_log.error(e);
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
			
			preCondition = preCondition.trim();
			
			switch (preCondition) {
				case "payok":
					result = result && checkPayOk(dossier);
					break;
				case "paynotok":
					result = result && checkPayNotOk(dossier);
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
			if (preCondition.contains("notservicecode=")) {
				String[] splitCodes = preCondition.split("=");
				if (splitCodes.length == 2) {
					result = result && checkNotServiceCode(splitCodes[1], dossier);
				}			
			}
			if (preCondition.contains("agency=")) {
				String[] splitAgencies = preCondition.split("=");
				if (splitAgencies.length == 2) {
					result = result && checkAgencyCode(splitAgencies[1], dossier);
				}							
			}
			if (preCondition.contains("notagencycode=")) {
				String[] splitAgencies = preCondition.split("=");
				if (splitAgencies.length == 2) {
					result = result && checkNotAgencyCode(splitAgencies[1], dossier);
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
			if (preCondition.contains("roledone=")) {
				String[] splitRoles = preCondition.split("=");
				System.out.println(splitRoles[0] + "," + splitRoles[1]);
				if (splitRoles.length == 2) {
					result = result && checkRoleDone(splitRoles[1], dossier);
				}																			
			}
			if (preCondition.contains("rolecode=")) {
				String[] splitRoles = preCondition.split("=");
				System.out.println(splitRoles[0] + "," + splitRoles[1]);
				if (splitRoles.length == 2) {
					result = result && checkRoleCode(splitRoles[1], dossier);
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
	
	private static boolean checkNotServiceCode(String serviceCode, Dossier dossier) {
		if (serviceCode.equalsIgnoreCase(dossier.getServiceCode())) {
			return false;
		}
		else {
			return true;
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
	
	private static boolean checkNotAgencyCode(String agencyCode, Dossier dossier) {
		if (agencyCode.equalsIgnoreCase(dossier.getGovAgencyCode())) {
			return false;
		}
		else {
			return true;
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
		List<DossierAction> lstDActions = DossierActionLocalServiceUtil.findDossierActionByDID_STEP(dossier.getDossierId(), stepCode);
		if (lstDActions.size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	private static boolean checkRoleDone(String roleCode, Dossier dossier) {
		JobPos jobPos = JobPosLocalServiceUtil.getByJobCode(dossier.getGroupId(), roleCode);
		if (jobPos != null) {
			List<DossierActionUser> dActionUsers = DossierActionUserLocalServiceUtil.getListUser(dossier.getDossierActionId());
			
			boolean flag = false;
			for (DossierActionUser dau : dActionUsers) {
				User u = UserLocalServiceUtil.fetchUser(dau.getUserId());

				List<Role> lstRoles = RoleLocalServiceUtil.getUserRoles(u.getUserId());
				for (Role r : lstRoles) {
					if (r.getRoleId() == jobPos.getMappingRoleId()) {
						flag = true;
						break;
					}
				}
				if (flag) {
					return true;
				}
			}
			
			return flag;
		}
		else {
			return false;
		}
	}

	private static boolean checkRoleCode(String roleCode, Dossier dossier) {
		boolean flag = false;
		if (Validator.isNotNull(roleCode)) {
			if (roleCode.contains(StringPool.PLUS)) {
				String[] splitRole = StringUtil.split(StringPool.PLUS);
				if (splitRole != null && splitRole.length > 0) {
					for (String role : splitRole) {
						flag = checkRoleDone(role, dossier);
						if (flag) break;
					}
				}
			} else {
				flag = checkRoleDone(roleCode, dossier);
			}
		}
		return flag;
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
			_log.error(e);
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
			if (paymentFile.getPaymentStatus() != 5) {
				result = result && false;
			}
		}
		return result;
	}

	private static boolean checkPayNotOk(Dossier dossier) {
		boolean result = false;
		PaymentFileActions actions = new PaymentFileActionsImpl();
		PaymentFile paymentFile = actions.getPaymentFiles(dossier.getGroupId(), dossier.getDossierId());
		if (paymentFile != null) {
			if (paymentFile.getPaymentStatus() != 5) {
				result = true;
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
//		long dossierActionId = dossier.getDossierActionId();
//		DossierAction action = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
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

	//Calculator startDate and endDate
	public static int minDay(int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month - 1);
		// cal.set(Calendar.DAY_OF_MONTH, month);
		cal.set(Calendar.YEAR, year);
		int minDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		return minDay;
	}

	// Process delete dossier
	public static void processSyncDeleteDossier(Dossier model, int originality) {
		if (originality == DossierTerm.ORIGINALITY_MOTCUA || Math.abs(originality) == DossierTerm.ORIGINALITY_LIENTHONG) {
			long groupId = model.getGroupId();
			String serverNo = model.getServerNo();
			if (Validator.isNotNull(serverNo)) {
				ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, serverNo);
				if (sc != null) {
					String config = sc.getConfigs();
					if (Validator.isNotNull(config)) {
						try {
							JSONObject jsonData = JSONFactoryUtil.createJSONObject(config);
							if (jsonData != null) {
								OpenCPSRestClient client = OpenCPSRestClient.fromJSONObject(jsonData);
								if (client != null) {
									_log.info("Dossiser Remove DONE.....");
									client.removeDossier(model);
								}
							}
						} catch (Exception e) {
							_log.error(e);
						}
						
					}
				}
			}
		}
	}

	public static void processSyncGotoDossier(Dossier model, String stepCode) {
		if (model.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA || Math.abs(model.getOriginality()) == DossierTerm.ORIGINALITY_LIENTHONG) {
			long groupId = model.getGroupId();
			String serverNo = model.getServerNo();
			if (Validator.isNotNull(serverNo)) {
				ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, serverNo);
				if (sc != null) {
					String config = sc.getConfigs();
					if (Validator.isNotNull(config)) {
						try {
							JSONObject jsonData = JSONFactoryUtil.createJSONObject(config);
							if (jsonData != null) {
								OpenCPSRestClient client = OpenCPSRestClient.fromJSONObject(jsonData);
								if (client != null) {
									client.gotoStep(model.getReferenceUid(), stepCode);
								}
							}
						} catch (Exception e) {
							_log.error(e);
						}
						
					}
				}
			}
		}
	}	
	public static void processSyncRollbackDossier(Dossier model) {
		if (model.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA || Math.abs(model.getOriginality()) == DossierTerm.ORIGINALITY_LIENTHONG) {
			long groupId = model.getGroupId();
			String serverNo = model.getServerNo();
			if (Validator.isNotNull(serverNo)) {
				ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, serverNo);
				if (sc != null) {
					String config = sc.getConfigs();
					if (Validator.isNotNull(config)) {
						try {
							JSONObject jsonData = JSONFactoryUtil.createJSONObject(config);
							if (jsonData != null) {
								OpenCPSRestClient client = OpenCPSRestClient.fromJSONObject(jsonData);
								if (client != null) {
									client.rollback(model.getReferenceUid());
								}
							}
						} catch (Exception e) {
							_log.error(e);
						}
						
					}
				}
			}
		}
	}		
}
