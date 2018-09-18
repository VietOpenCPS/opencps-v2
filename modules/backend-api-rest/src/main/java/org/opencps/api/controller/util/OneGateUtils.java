package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Date;

import org.opencps.api.dossier.model.DossierDetailModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.util.DossierOverDueUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;

public class OneGateUtils {
	private static Log _log = LogFactoryUtil.getLog(OneGateUtils.class);
	public static DossierDetailModel mappingForGetDetail(Dossier input) {

		DossierDetailModel model = new DossierDetailModel();
		
		try {
			Document dossierDoc = DossierLocalServiceUtil.getDossierById(input.getDossierId(), input.getCompanyId());
			model.setDossierIdCTN(dossierDoc.get(DossierTerm.DOSSIER_ID+"CTN"));
		} catch (Exception e) {
			//_log.error(e);
			_log.debug(e);
			model.setDossierIdCTN("");
		}
		

		model.setDossierId(GetterUtil.getInteger(input.getDossierId()));
		model.setUserId(GetterUtil.getInteger(input.getUserId()));
		Date createDate = input.getCreateDate();
		if (createDate != null) {
			model.setCreateDate(String.valueOf(createDate.getTime()));
		} else {
			model.setCreateDate(StringPool.BLANK);
		}
		Date modifiedDate = input.getModifiedDate();
		if (modifiedDate != null) {
			model.setModifiedDate(String.valueOf(modifiedDate.getTime()));
		} else {
			model.setModifiedDate(StringPool.BLANK);
		}
		model.setReferenceUid(input.getReferenceUid());
		model.setCounter(input.getCounter());
		model.setServiceCode(input.getServiceCode());
		model.setServiceName(input.getServiceName());
		model.setGovAgencyCode(input.getGovAgencyCode());
		model.setGovAgencyName(input.getGovAgencyName());
		model.setDossierTemplateNo(input.getDossierTemplateNo());
		model.setApplicantName(input.getApplicantName());
		model.setApplicantIdType(input.getApplicantIdType());
		model.setApplicantIdNo(input.getApplicantIdNo());
		Date applicantDate = input.getApplicantIdDate();
		if (applicantDate != null) {
			model.setApplicantIdDate(String.valueOf(applicantDate.getTime()));
		} else {
			model.setApplicantIdDate(StringPool.BLANK);
		}
		model.setAddress(input.getAddress());
		model.setCityCode(input.getCityCode());
		model.setCityName(input.getCityName());
		model.setDistrictCode(input.getDistrictCode());
		model.setDistrictName(input.getDistrictName());
		model.setWardCode(input.getWardCode());
		model.setWardName(input.getWardName());
		model.setContactName(input.getContactName());
		model.setContactTelNo(input.getContactTelNo());
		model.setContactEmail(input.getContactEmail());
		model.setDossierNote(input.getDossierNote());
		model.setSubmissionNote(input.getSubmissionNote());
		model.setBriefNote(input.getBriefNote());
		model.setDossierNo(input.getDossierNo());
		model.setSubmitting(Boolean.toString(input.getSubmitting()));
		Date submitDate = input.getSubmitDate();
		if (submitDate != null) {
			model.setSubmitDate(String.valueOf(submitDate.getTime()));
		} else {
			model.setSubmitDate(StringPool.BLANK);
		}
		Date receiveDate = input.getReceiveDate();
		if (receiveDate != null) {
			model.setReceiveDate(String.valueOf(receiveDate.getTime()));
		} else {
			model.setReceiveDate(StringPool.BLANK);
		}
		Date dueDate = input.getDueDate();
		if (dueDate != null) {
			model.setDueDate(String.valueOf(dueDate.getTime()));
		} else {
			model.setDueDate(StringPool.BLANK);
		}
		Date releaseDate = input.getReleaseDate();
		if (releaseDate != null) {
			model.setReleaseDate(String.valueOf(releaseDate.getTime()));
		} else {
			model.setReleaseDate(StringPool.BLANK);
		}
		Date finishDate = input.getFinishDate();
		if (finishDate != null) {
			model.setFinishDate(String.valueOf(finishDate.getTime()));
		} else {
			model.setFinishDate(StringPool.BLANK);
		}
		Date cancellingDate = input.getCancellingDate();
		if (cancellingDate != null) {
			model.setCancellingDate(String.valueOf(cancellingDate.getTime()));
		} else {
			model.setCancellingDate(StringPool.BLANK);
		}
		Date correctingDate = input.getCorrecttingDate();
		if (correctingDate != null) {
			model.setCorrectingDate(String.valueOf(correctingDate.getTime()));
		} else {
			model.setCorrectingDate(StringPool.BLANK);
		}
		model.setDossierStatus(input.getDossierStatus());
		model.setDossierStatusText(input.getDossierStatusText());
		model.setDossierSubStatus(input.getDossierSubStatus());
		model.setDossierSubStatusText(input.getDossierSubStatusText());
		model.setViaPostal(String.valueOf(input.getViaPostal()));
		model.setPostalAddress(input.getPostalAddress());
		model.setPostalCityCode(input.getPostalCityCode());
		model.setPostalCityName(input.getPostalCityName());
		model.setPostalTelNo(input.getPostalTelNo());
		
		//Gte serviceProcess
		
		long groupId = input.getGroupId();
		
		double processBlock = 0;
		int processUnit = 0;
		
		try {
			ServiceConfig serviceConfig = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, input.getServiceCode(), input.getGovAgencyCode());
			
			ProcessOption processOption = ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId,input.getDossierTemplateNo(), serviceConfig.getServiceConfigId());
			
			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(processOption.getServiceProcessId());
			
			processBlock = serviceProcess.getDurationCount();
			
			processUnit = serviceProcess.getDurationUnit();
			
		} catch (Exception e) {
			//_log.error(e);
			_log.debug(e);
		}

		if (input.getDossierActionId() != 0) {
			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(input.getDossierActionId());

			model.setDossierActionId(dossierAction.getDossierActionId());
			model.setLastActionDate(APIDateTimeUtils.convertDateToString(dossierAction.getCreateDate(),
					APIDateTimeUtils._NORMAL_PARTTERN));
			model.setLastActionName(dossierAction.getActionName());
			model.setLastActionUser(dossierAction.getActionUser());
			model.setLastActionNote(dossierAction.getActionNote());
			model.setLastActionCode(dossierAction.getActionCode());

			model.setStepCode(dossierAction.getStepCode());
			model.setStepName(dossierAction.getStepName());

			Date stepDuedate = DossierOverDueUtils.getStepOverDue(dossierAction.getGroupId(), dossierAction.getActionOverdue(), new Date());

			if (dossierAction.getActionOverdue() != 0) {
				model.setStepOverdue(StringPool.TRUE);
			} else {
				model.setStepOverdue(StringPool.TRUE);
			}

			model.setStepDuedate(APIDateTimeUtils.convertDateToString(stepDuedate, APIDateTimeUtils._NORMAL_PARTTERN));

			ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(dossierAction.getStepCode(),
					dossierAction.getGroupId(), dossierAction.getServiceProcessId());

			model.setStepInstruction(step.getStepInstruction());
		} else {
			model.setDossierActionId(0L);
		}
		model.setApplicantNote(input.getApplicantNote());
		model.setNotification(Boolean.toString(input.getNotification()));
		model.setOnline(Boolean.toString(input.getOnline()));
		model.setLockState(input.getLockState());
		model.setDelegateAddress(input.getDelegateAddress());
		model.setDelegateCityCode(input.getDelegateCityCode());
		model.setDelegateCityName(input.getDelegateCityName());
		model.setDelegateDistrictCode(input.getDelegateDistrictCode());
		model.setDelegateDistrictName(input.getDelegateDistrictName());
		model.setDelegateEmail(input.getDelegateEmail());
		model.setDelegateIdNo(input.getDelegateIdNo());
		model.setDelegateName(input.getDelegateName());
		model.setDelegateTelNo(input.getDelegateTelNo());
		model.setDelegateWardCode(input.getDelegateWardCode());
		model.setDelegateWardName(input.getDelegateWardName());
		model.setPostalDistrictCode(input.getPostalDistrictCode());
		model.setPostalDistrictName(input.getPostalDistrictName());
		model.setPostalWardCode(input.getPostalWardCode());
		model.setPostalWardName(input.getPostalWardName());
		model.setPostalServiceCode(input.getPostalServiceCode());
		model.setDossierTemplateName(input.getDossierTemplateName());
		model.setProcessBlock(processBlock);
		model.setProcessUnit(processUnit);

		// LamTV: Process payment Fee
		long dossierId = input.getDossierId();
		PaymentFile payment = PaymentFileLocalServiceUtil.getByDossierId(groupId, dossierId);
		if (payment != null) {
			model.setPaymentFee(payment.getPaymentFee());
			model.setPaymentNote(payment.getPaymentNote());
		} else {
			model.setPaymentFee(StringPool.BLANK);
			model.setPaymentNote(StringPool.BLANK);
		}

		return model;
	}

}
