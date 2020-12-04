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
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.util.DueDateUtils;
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
	
	public static String _dateToString(Date date, String format) {

		return new SimpleDateFormat(format).format(date);
	}

	public static Date _stringToDate(String dateStr, String format) {

		try {

			return new SimpleDateFormat(format).parse(dateStr);
		}
		catch (ParseException e) {

			return null;
		}
	}
	
	public static JSONObject mergeObject(String oldObj, String newObj) {

		JSONObject mergedObj = JSONFactoryUtil.createJSONObject();

		try {

			JSONObject o1 = Validator.isNotNull(oldObj)
				? JSONFactoryUtil.createJSONObject(oldObj)
				: JSONFactoryUtil.createJSONObject();
			JSONObject o2 = Validator.isNotNull(newObj)
				? JSONFactoryUtil.createJSONObject(newObj)
				: JSONFactoryUtil.createJSONObject();
			Iterator i1 = o1.keys();
			Iterator i2 = o2.keys();
			String tmp_key;
			while (i1.hasNext()) {
				tmp_key = (String) i1.next();
				mergedObj.put(tmp_key, o1.get(tmp_key));
			}
			while (i2.hasNext()) {
				tmp_key = (String) i2.next();
				// only update if not null
				if (Validator.isNotNull(o2.get(tmp_key))) {
					mergedObj.put(tmp_key, o2.get(tmp_key));
				}
			}
		}
		catch (Exception e) {
			// e.printStackTrace();
			_log.error(e);
		}

		return mergedObj;
	}

	public static JSONObject convertDossierToJSON(Dossier dossier, long dossierActionId) {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		
		obj.put(DossierTerm.DOSSIER_ID, dossier.getDossierId());
		obj.put(Field.GROUP_ID, dossier.getGroupId());
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
		obj.put(DossierTerm.DOSSIER_ACTION_ID, dossierActionId > 0 ? dossierActionId : dossier.getDossierActionId());
		obj.put(DossierTerm.POSTAL_ADDRESS, dossier.getPostalAddress());
		obj.put(DossierTerm.POSTAL_CITY_CODE, dossier.getPostalCityCode());
		obj.put(DossierTerm.POSTAL_TEL_NO, dossier.getPostalTelNo());
		obj.put(DossierTerm.POSTAL_DISTRICT_CODE, dossier.getPostalDistrictCode());
		obj.put(DossierTerm.DELEGATE_NAME, dossier.getDelegateName());
		obj.put(DossierTerm.DELEGATE_EMAIL, dossier.getDelegateEmail());
		obj.put(DossierTerm.DOSSIER_COUNTER, dossier.getDossierCounter());
		obj.put(DossierTerm.USER_ID, dossier.getUserId());
		String serviceCode = dossier.getServiceCode();
		ServiceProcess serviceProcess;
		try {
			serviceProcess = ServiceProcessLocalServiceUtil.getServiceByCode(dossier.getGroupId(), serviceCode, dossier.getGovAgencyCode(), dossier.getDossierTemplateNo());
			if (serviceProcess == null) {
			}
			else {
				JSONObject submissionNoteObj = getDossierProcessSequencesPublishJSON(dossier.getGroupId(), dossier, dossierActionId, serviceProcess);
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
		obj.put(DossierTerm.VNPOSTAL_STATUS, dossier.getVnpostalStatus());
		obj.put(DossierTerm.VNPOSTAL_PROFILE, dossier.getVnpostalProfile());
		obj.put(DossierTerm.FROM_VIA_POSTAL, dossier.getFromViaPostal());
		
		return obj;
	}
	
	public static JSONObject getDossierProcessSequencesJSON(long groupId, Dossier dossier, ServiceProcess serviceProcess) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ProcessSequence> lstSequences = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId, serviceProcess.getServiceProcessId());

		result.put(ServiceProcessTerm.PROCESS_NO, serviceProcess.getProcessNo());
		result.put(ServiceProcessTerm.DURATION_UNIT, serviceProcess.getDurationUnit());
		result.put(ServiceProcessTerm.DURATION_COUNT, serviceProcess.getDurationCount());
		result.put(ConstantUtils.TOTAL, lstSequences.size());
		JSONArray sequenceArr = JSONFactoryUtil.createJSONArray();
		DossierAction lastDA = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		List<DossierActionUser> lstDus = DossierActionUserLocalServiceUtil.getListUser(dossier.getDossierActionId());
		
		for (ProcessSequence ps : lstSequences) {		
			JSONObject sequenceObj = JSONFactoryUtil.createJSONObject();
			sequenceObj.put(ServiceProcessTerm.SEQUENCE_NO, ps.getSequenceNo());
			sequenceObj.put(ServiceProcessTerm.SEQUENCE_NAME, ps.getSequenceName());
			sequenceObj.put(ServiceProcessTerm.SEQUENCE_ROLE, ps.getSequenceRole());
			sequenceObj.put(ServiceProcessTerm.DURATION_COUNT, ps.getDurationCount());
			
			if (lastDA != null && lastDA.getSequenceNo().equals(ps.getSequenceNo())) {
				sequenceObj.put(DossierTerm.STATUS_TEXT, lastDA.getStepName());
			}
			List<DossierAction> lstDossierActions = DossierActionLocalServiceUtil.findDossierActionByG_DID_FSN(groupId, dossier.getDossierId(), ps.getSequenceNo());
			List<DossierAction> lstPrevDossierActions = DossierActionLocalServiceUtil.findDossierActionByG_DID_SN(groupId, dossier.getDossierId(), ps.getSequenceNo());
				
			DossierAction lastAction = lstPrevDossierActions.size() > 0 ? lstPrevDossierActions.get(lstPrevDossierActions.size() - 1) : null;
			if (lastAction != null) {
				sequenceObj.put(DossierTerm.START_DATE, lastAction.getCreateDate().getTime());
			}			
			
			if (lstDossierActions.size() > 0) {
				DossierAction lastDASequence = lstDossierActions.get(lstDossierActions.size() - 1);
				if (lastDASequence.getActionOverdue() != 0) {
					String preText = (lastDASequence.getActionOverdue() > 0
							? ReadFilePropertiesUtils.get(ConstantUtils.TIME_UNDUE)
							: ReadFilePropertiesUtils.get(ConstantUtils.TIME_OVER));
					sequenceObj.put(DossierTerm.OVERDUE_TEXT, preText + lastDASequence.getActionOverdue()
							+ ReadFilePropertiesUtils.get(ConstantUtils.TIME_DAY));
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
						assignUserObj.put(Field.USER_ID, dau.getUserId());
						//
						Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, u.getUserId());
						if (employee != null) {
							assignUserObj.put(Field.USER_NAME, employee.getFullName());
						} else {
							assignUserObj.put(Field.USER_NAME, u.getFullName());
						}
						
						assignUserArr.put(assignUserObj);					
					}					
				}
			}
			for (DossierAction da : lstDossierActions) {
				if (!lstUsers.contains(da.getUserId())) {
					JSONObject assignUserObj = JSONFactoryUtil.createJSONObject();
					lstUsers.add(da.getUserId());
					assignUserObj.put(Field.USER_ID, da.getUserId());

					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, da.getUserId());
					if (employee != null) {
						assignUserObj.put(Field.USER_NAME, employee.getFullName());
					} else {
						assignUserObj.put(Field.USER_NAME, da.getUserName());
					}
					
					assignUserArr.put(assignUserObj);					
				}
			}
			
			sequenceObj.put(ProcessActionTerm.ASSIGN_USERS, assignUserArr);
			
			JSONArray actionsArr = JSONFactoryUtil.createJSONArray();
			for (DossierAction da : lstDossierActions) {
				JSONObject actionObj = JSONFactoryUtil.createJSONObject();
				
				actionObj.put(DossierActionTerm.USER_ID, da.getUserId());
				actionObj.put(DossierActionTerm.FROM_STEP_CODE, da.getFromStepCode());
				actionObj.put(DossierActionTerm.FROM_STEP_NAME, da.getFromStepName());
				actionObj.put(ServiceProcessTerm.SEQUENCE_NO, da.getSequenceNo());
				actionObj.put(DossierTerm.DOSSIER_ID, da.getDossierId());
				actionObj.put(ServiceProcessTerm.SERVICEPROCESS_ID, da.getServiceProcessId());
				actionObj.put(DossierActionTerm.PREVIOUS_ACTION_ID, da.getPreviousActionId());
				actionObj.put(DossierActionTerm.ACTION_CODE, da.getActionCode());
				actionObj.put(DossierActionTerm.ACTION_NAME, da.getActionName());
				actionObj.put(DossierActionTerm.ACTION_NOTE, da.getActionNote());
				actionObj.put(DossierActionTerm.ACTION_USER, da.getActionUser());
				actionObj.put(DossierActionTerm.ACTION_OVER_DUE, da.getActionOverdue());
				actionObj.put(DossierActionTerm.PAYLOAD, da.getPayload());
				actionObj.put(DossierActionTerm.PENDING, da.getPending());
				actionObj.put(DossierActionTerm.ROLLBACK_ABLE, da.getRollbackable());
				actionObj.put(Field.CREATE_DATE, da.getCreateDate() != null ? da.getCreateDate().getTime() : 0l);
				actionObj.put(DossierTerm.MODIFIED_DATE, da.getModifiedDate() != null ? da.getModifiedDate().getTime() : 0l);
				actionObj.put(DossierTerm.DUE_DATE, da.getDueDate() != null ? da.getDueDate().getTime() : 0l);
				actionObj.put(DossierActionTerm.NEXT_ACTION_ID, da.getNextActionId());
				actionObj.put(DossierActionTerm.STATE, da.getState());
				actionObj.put(DossierActionTerm.STEP_CODE, da.getStepCode());
				actionObj.put(DossierActionTerm.STEP_NAME, da.getStepName());
				actionObj.put(Field.USER_ID, da.getUserId());
				
				_log.debug("Action obj: " + actionObj.toJSONString());
				actionsArr.put(actionObj);
			}			
			
			sequenceObj.put(ProcessActionTerm.KEY_ACTIONS, actionsArr);
			
			sequenceArr.put(sequenceObj);
			
		}
		
		result.put(ConstantUtils.DATA, sequenceArr);
		return result;
	}
	
	public static JSONObject getDossierProcessSequencesPublishJSON(long groupId, Dossier dossier, long dossierActionId, ServiceProcess serviceProcess) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ProcessSequence> lstSequences = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId, serviceProcess.getServiceProcessId());

		result.put(ServiceProcessTerm.PROCESS_NO, serviceProcess.getProcessNo());
		result.put(ServiceProcessTerm.DURATION_UNIT, serviceProcess.getDurationUnit());
		result.put(ServiceProcessTerm.DURATION_COUNT, serviceProcess.getDurationCount());
		result.put(ConstantUtils.TOTAL, lstSequences.size());
		JSONArray sequenceArr = JSONFactoryUtil.createJSONArray();
		DossierAction lastDA = null;
		if (dossierActionId > 0) {
			lastDA = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
		}
		if (lastDA == null) {
			lastDA = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		}

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
			sequenceObj.put(ServiceProcessTerm.SEQUENCE_NO, ps.getSequenceNo());
			sequenceObj.put(ServiceProcessTerm.SEQUENCE_NAME, ps.getSequenceName());
			sequenceObj.put(ServiceProcessTerm.SEQUENCE_ROLE, ps.getSequenceRole());
			sequenceObj.put(ServiceProcessTerm.DURATION_COUNT, ps.getDurationCount());

			if (lastDA != null && lastDA.getSequenceNo().equals(ps.getSequenceNo())) {
				sequenceObj.put(DossierTerm.STATUS_TEXT, lastDA.getStepName());
			}
			List<DossierAction> lstDossierActions = DossierActionLocalServiceUtil.findDossierActionByG_DID_FSN(groupId, dossier.getDossierId(), ps.getSequenceNo());
			List<DossierAction> lstPrevDossierActions = DossierActionLocalServiceUtil.findDossierActionByG_DID_SN(groupId, dossier.getDossierId(), ps.getSequenceNo());
				
			DossierAction lastAction = lstPrevDossierActions.size() > 0 ? lstPrevDossierActions.get(lstPrevDossierActions.size() - 1) : null;
			if (lastAction != null) {
				sequenceObj.put(DossierTerm.START_DATE, lastAction.getCreateDate().getTime());
			}

			if (lstDossierActions.size() > 0) {
				DossierAction lastDASequence = lstDossierActions.get(lstDossierActions.size() - 1);
				if (lastDASequence.getActionOverdue() != 0) {
					String preText = (lastDASequence.getActionOverdue() > 0 ? ReadFilePropertiesUtils.get(ConstantUtils.TIME_UNDUE) : ReadFilePropertiesUtils.get(ConstantUtils.TIME_OVER));
					sequenceObj.put(DossierTerm.OVERDUE_TEXT, preText + lastDASequence.getActionOverdue() + ReadFilePropertiesUtils.get(ConstantUtils.TIME_DAY));
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
						assignUserObj.put(Field.USER_ID, dau.getUserId());
						//
						Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, u.getUserId());
						if (employee != null) {
							assignUserObj.put(Field.USER_NAME, employee.getFullName());
						} else {
							assignUserObj.put(Field.USER_NAME, u.getFullName());
						}
						
						assignUserArr.put(assignUserObj);					
					}					
				}
			}
			for (DossierAction da : lstDossierActions) {
				if (!lstUsers.contains(da.getUserId())) {
					JSONObject assignUserObj = JSONFactoryUtil.createJSONObject();
					lstUsers.add(da.getUserId());
					assignUserObj.put(Field.USER_ID, da.getUserId());

					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, da.getUserId());
					if (employee != null) {
						assignUserObj.put(Field.USER_NAME, employee.getFullName());
					} else {
						assignUserObj.put(Field.USER_NAME, da.getUserName());
					}
					
					assignUserArr.put(assignUserObj);					
				}
			}
			
			sequenceObj.put(ProcessActionTerm.ASSIGN_USERS, assignUserArr);
			
			JSONArray actionsArr = JSONFactoryUtil.createJSONArray();
			for (DossierAction da : lstDossierActions) {
				if (lstActions.contains(da.getActionCode())) {
					JSONObject actionObj = JSONFactoryUtil.createJSONObject();
					
					actionObj.put(DossierActionTerm.USER_ID, da.getUserId());
					actionObj.put(DossierActionTerm.FROM_STEP_CODE, da.getFromStepCode());
					actionObj.put(DossierActionTerm.FROM_STEP_NAME, da.getFromStepName());
					actionObj.put(ServiceProcessTerm.SEQUENCE_NO, da.getSequenceNo());
					actionObj.put(DossierTerm.DOSSIER_ID, da.getDossierId());
					actionObj.put(ServiceProcessTerm.SERVICEPROCESS_ID, da.getServiceProcessId());
					actionObj.put(DossierActionTerm.PREVIOUS_ACTION_ID, da.getPreviousActionId());
					actionObj.put(DossierActionTerm.ACTION_CODE, da.getActionCode());
					actionObj.put(DossierActionTerm.ACTION_NAME, da.getActionName());
					actionObj.put(DossierActionTerm.ACTION_NOTE, da.getActionNote());
					actionObj.put(DossierActionTerm.ACTION_USER, da.getActionUser());
					actionObj.put(DossierActionTerm.ACTION_OVER_DUE, da.getActionOverdue());
					actionObj.put(DossierActionTerm.PAYLOAD, da.getPayload());
					actionObj.put(DossierActionTerm.PENDING, da.getPending());
					actionObj.put(DossierActionTerm.ROLLBACK_ABLE, da.getRollbackable());
					actionObj.put(Field.CREATE_DATE, da.getCreateDate() != null ? da.getCreateDate().getTime() : 0l);
					actionObj.put(DossierTerm.MODIFIED_DATE, da.getModifiedDate() != null ? da.getModifiedDate().getTime() : 0l);
					actionObj.put(DossierTerm.DUE_DATE, da.getDueDate() != null ? da.getDueDate().getTime() : 0l);
					actionObj.put(DossierActionTerm.NEXT_ACTION_ID, da.getNextActionId());
					actionObj.put(DossierActionTerm.STATE, da.getState());
					actionObj.put(DossierActionTerm.STEP_CODE, da.getStepCode());
					actionObj.put(DossierActionTerm.STEP_NAME, da.getStepName());
					actionObj.put(Field.USER_ID, da.getUserId());
					
					_log.debug("Action obj: " + actionObj.toJSONString());
					actionsArr.put(actionObj);					
				}
			}			
			
			sequenceObj.put(ProcessActionTerm.KEY_ACTIONS, actionsArr);
			
			sequenceArr.put(sequenceObj);
			
		}
		
		result.put(ConstantUtils.DATA, sequenceArr);
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

	public static boolean checkPreCondition(String[] preConditions, Dossier dossier, User curUser) {
		boolean result = true;
		
		for (String preCondition : preConditions) {
			
			preCondition = preCondition.trim();
			
			switch (preCondition) {
			case DossierTerm.PAY_OK:
					result = result && checkPayOk(dossier);
					break;
			case DossierTerm.PAY_NOT_OK:
					result = result && checkPayNotOk(dossier);
					break;
			case DossierTerm.DOSSIER_STATUS_CANCELLING:
					result = result && checkCancelling(dossier);
					break;

			case DossierTerm.DOSSIER_STATUS_CORRECTING:
					result = result && checkCorrecting(dossier);
					break;
	
				case "reject_correcting":
					result = result && checkCorrecting(dossier);
					break;

			case DossierTerm.SUBMITTING:
					result = result && checkSubmitting(dossier);
					break;
	
				case DossierActionTerm.REJECT_SUBMITTING:
					result = result && checkSubmitting(dossier);
					break;


			case DossierTerm.DOSSIER_STATUS_WAITING:
					result = result && checkWaiting(preCondition, dossier);
					break;

			case DossierTerm.ONLINE_TRUE:
					result = result && checkDossierOnline(dossier);
					break;
			case DossierTerm.ONLINE_FALSE:
					result = result && checkDossierOnegate(dossier);
					break;
				default:
					break;
			}
			if (preCondition.contains(DossierTerm.CONTAIN_SERVICE)) {
				String[] splitCodes = preCondition.split(StringPool.EQUAL);
				if (splitCodes.length == 2) {
					result = result && checkServiceCode(splitCodes[1], dossier);
				}			
			}
			if (preCondition.contains(DossierTerm.CONTAIN_NOT_SERVICE_CODE)) {
				String[] splitCodes = preCondition.split(StringPool.EQUAL);
				if (splitCodes.length == 2) {
					result = result && checkNotServiceCode(splitCodes[1], dossier);
				}			
			}
			if (preCondition.contains(DossierTerm.AGENCYS)) {
				String[] splitAgencies = preCondition.split(StringPool.EQUAL);
				if (splitAgencies.length == 2) {
					boolean checkGovs = false;
					String govs = splitAgencies[1];
					if (govs.contains(StringPool.COMMA)) {
						String[] govsArr = StringUtil.split(govs);
						for (String gov : govsArr) {
							checkGovs = checkGovs || checkAgencyCode(gov, dossier);
						}
					} else {
						result = result && checkAgencyCode(govs, dossier);
					}
				}
			}
			if (preCondition.contains(DossierTerm.CONTAIN_NOT_AGENCY_CODE)) {
				String[] splitAgencies = preCondition.split(StringPool.EQUAL);
				if (splitAgencies.length == 2) {
					result = result && checkNotAgencyCode(splitAgencies[1], dossier);
				}							
			}
			if (preCondition.contains(DossierTerm.CONTAIN_TEMPLATE)) {
				String[] splitTemplates = preCondition.split(StringPool.EQUAL);
				if (splitTemplates.length == 2) {
					result = result && checkTemplateCode(splitTemplates[1], dossier);
				}											
			}
			if (preCondition.contains(DossierTerm.CONTAIN_ORIGINAL)) {
				String[] splitOriginalities = preCondition.split(StringPool.EQUAL);
				if (splitOriginalities.length == 2) {
					result = result && checkOriginality(splitOriginalities[1], dossier);
				}															
			}
			if (preCondition.contains(DossierTerm.CONTAIN_STEP_DONE)) {
				String[] splitSteps = preCondition.split(StringPool.EQUAL);
				if (splitSteps.length == 2) {
					result = result && checkStepDone(splitSteps[1], dossier);
				}																			
			}
			if (preCondition.contains(DossierTerm.CONTAIN_STEP_NOT_DONE)) {
				String[] splitSteps = preCondition.split(StringPool.EQUAL);
				if (splitSteps.length == 2) {
					result = result && checkStepNotDone(splitSteps[1], dossier);
				}																			
			}
			if (preCondition.contains(StringPool.POUND)) {
				String[] splitBiens = preCondition.split(StringPool.EQUAL);
				if (splitBiens.length == 2) {
					result = result && checkBien(splitBiens[0], splitBiens[1], dossier);
				}																							
			}
			if (preCondition.contains(DossierTerm.CONTAIN_VIA_POSTAL)) {
				String[] splitViaPostals = preCondition.split(StringPool.EQUAL);
				if (splitViaPostals.length == 2) {
					result = result && checkViaPostal(splitViaPostals[1], dossier);
				}																							
			}
			if (preCondition.contains(DossierTerm.CONTAIN_ROLE_DONE)) {
				String[] splitRoles = preCondition.split(StringPool.EQUAL);
				System.out.println(splitRoles[0] + StringPool.COMMA + splitRoles[1]);
				if (splitRoles.length == 2) {
					result = result && checkRoleDone(splitRoles[1], curUser, dossier);
				}																			
			}
			if (preCondition.contains(DossierTerm.CONTAIN_ROLE_CODE)) {
				String[] splitRoles = preCondition.split(StringPool.EQUAL);
				System.out.println(splitRoles[0] + StringPool.COMMA + splitRoles[1]);
				if (splitRoles.length == 2) {
					result = result && checkRoleCode(splitRoles[1], curUser, dossier);
				}
			}else{
				// roleCode
				if(preCondition.contains(DossierTerm.ROLE_CODE)){
					String[] splitRoles = preCondition.split(StringPool.EQUAL);
					if (splitRoles.length == 2) {
						result = result && checkRoleCode(splitRoles[1], curUser, dossier);
					}
				}
			}
			if (preCondition.contains(DossierTerm.CONTAIN_WAITING_OVERDUE_THAN)) {
				String[] splitDuration = preCondition.split(StringPool.GREATER_THAN);
				if (splitDuration.length == 2) {
					result = result && checkWaitingOverdueGreaterThan(splitDuration[1], dossier);
				}
			}
			if (preCondition.contains(DossierTerm.CONTAIN_WAITING_OVERDUE_LESS)) {
				String[] splitDuration = preCondition.split(StringPool.LESS_THAN_OR_EQUAL);
				if (splitDuration.length == 2) {
					result = result && checkWaitingOverdueLessThan(splitDuration[1], dossier);
				}
			}
			if (preCondition.contains(DossierTerm.CONTAIN_DURATION_COUNT_GREATER_THAN)) {
				String[] splitDuration = preCondition.split(StringPool.GREATER_THAN);
				if (splitDuration.length == 2) {
					result = result && checkDurationCountGreaterThan(splitDuration[1], dossier);
				}				
			}
			// BA Duan
			if (preCondition.contains("isRoles=")) {
				String[] splitDuration = preCondition.split("=");
				if (splitDuration.length == 2) {
					result = result && checkIsRoles(splitDuration[1], curUser, dossier);
				}
			}
			if (preCondition.contains(DossierTerm.CONTAIN_MULTIPLE_CHECK)) {
				String[] splitMultipleCheck = preCondition.split(StringPool.EQUAL);
				if (splitMultipleCheck.length == 2) {
					result = result && checkMultipleCheck(splitMultipleCheck[1], dossier.getMultipleCheck());
				}
			}
		}

		return result;
	}

	private static boolean checkIsRoles(String role, User curUser, Dossier dossier) {

		boolean flag = false;

		if (Validator.isNull(curUser)) {
			return flag;
		}
		
		String[] roles = StringUtil.split(role, StringPool.PLUS);
		List<Role> lstRoles = RoleLocalServiceUtil.getUserRoles(curUser.getUserId());
		
		for (String preconditionRoleCode : roles) {

			JobPos jobPos = JobPosLocalServiceUtil.getByJobCode(dossier.getGroupId(), preconditionRoleCode);

			for (Role r : lstRoles) {
				if (r.getRoleId() == jobPos.getMappingRoleId()) {
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}
	
		return flag;
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
		if (Validator.isNotNull(stepCode)) {
			if (stepCode.contains(StringPool.PIPE)) {
				String[] stepArr = stepCode.split(StringPool.PIPE);
				for (String step : stepArr) {
					List<DossierAction> lstDActions = DossierActionLocalServiceUtil.findDossierActionByDID_STEP(dossier.getDossierId(), step);
					if (lstDActions.size() > 0) {
						return true;
					}
				}
				return false;
			} else {
				List<DossierAction> lstDActions = DossierActionLocalServiceUtil.findDossierActionByDID_STEP(dossier.getDossierId(), stepCode);
				if (lstDActions.size() > 0) {
					return true;
				}
				else {
					return false;
				}
			}
		}

		return false;
	}

	private static boolean checkRoleDone(String roleCode, User user, Dossier dossier) {
		JobPos jobPos = JobPosLocalServiceUtil.getByJobCode(dossier.getGroupId(), roleCode);
		if (jobPos != null) {
			List<DossierActionUser> dActionUsers = DossierActionUserLocalServiceUtil.getListUser(dossier.getDossierActionId());
			
			boolean flag = false;
			for (DossierActionUser dau : dActionUsers) {
				if (dau.getUserId() == user.getUserId()) {
					List<Role> lstRoles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
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
				//User u = UserLocalServiceUtil.fetchUser(dau.getUserId());
			}
			
			return flag;
		}
		else {
			return false;
		}
	}

	private static boolean checkRoleCode(String roleCode, User user, Dossier dossier) {
		boolean flag = false;
		if (Validator.isNotNull(roleCode)) {
			if (roleCode.contains(StringPool.PLUS)) {
				String[] splitRole = StringUtil.split(StringPool.PLUS);
				if (splitRole != null && splitRole.length > 0) {
					for (String role : splitRole) {
						flag = checkRoleDone(role, user, dossier);
						if (flag) break;
					}
				}
			} else {
				flag = checkRoleDone(roleCode, user, dossier);
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
		jsonObj.put(ConstantUtils.VALUE_KEY, key);
		String dataBinding = AutoFillFormData.sampleDataBinding(jsonObj.toJSONString(), dossier.getDossierId(), context);
		JSONObject resultObj;
		try {
			resultObj = JSONFactoryUtil.createJSONObject(dataBinding);
			if (resultObj.has(ConstantUtils.VALUE_KEY) && resultObj.getString(ConstantUtils.VALUE_KEY).equals(value)) {
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
	private static boolean checkMultipleCheck(String multipleCheck, String dossierMultiple) {
		//int o = Integer.valueOf(multipleCheck);

		if (Validator.isNotNull(multipleCheck) && multipleCheck.equalsIgnoreCase(dossierMultiple)) {
			return true;
		} else {
			return false;
		}

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
		if (DossierTerm.WAITING.equals(condition)) {
			
		}
		else {
			return false;
		}
		
		return false;
	}

	private static boolean checkWaitingOverdueGreaterThan(String preCondition, Dossier dossier) {
		Date now = new Date();
		if (dossier.getReceiveDate() != null) {
			int dayDue = Integer.valueOf(preCondition);
			DueDateUtils dueDateUtil = new DueDateUtils(dossier.getReceiveDate(), dayDue * 1.0, 0, dossier.getGroupId());
			
			if (dueDateUtil.getDueDate().after(now)) {
				return false;
			}
			else {
				return true;
			}
		}
		
		return false;
	}

	private static boolean checkWaitingOverdueLessThan(String preCondition, Dossier dossier) {
		Date now = new Date();
		if (dossier.getReceiveDate() != null) {
			int dayDue = Integer.valueOf(preCondition);
			DueDateUtils dueDateUtil = new DueDateUtils(dossier.getReceiveDate(), dayDue * 1.0, 0, dossier.getGroupId());
			
			if (dueDateUtil.getDueDate().after(now)) {
				return true;
			}
			else {
				return false;
			}
		}
		
		return false;
	}

	private static boolean checkDurationCountGreaterThan(String preCondition, Dossier dossier) {
		if (dossier.getDurationUnit() == DossierTerm.DURATION_UNIT_DAY) {
			double durationCount = Double.valueOf(preCondition);
			if (dossier.getDurationCount() > durationCount) {
				return true;
			}
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
