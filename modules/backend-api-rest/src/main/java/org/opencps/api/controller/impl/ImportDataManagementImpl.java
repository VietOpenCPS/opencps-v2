package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.ImportDataManagement;
import org.opencps.api.controller.util.DossierFileUtils;
import org.opencps.api.controller.util.DossierMarkUtils;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.api.dossier.model.DossierPublishModel;
import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.api.dossiermark.model.DossierMarkResultDetailModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.DossierMarkActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierFileActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierMarkActionsImpl;
import org.opencps.dossiermgt.action.util.DossierNumberGenerator;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import backend.auth.api.exception.BusinessExceptionImpl;
import backend.auth.api.exception.ErrorMsgModel;

public class ImportDataManagementImpl implements ImportDataManagement{

	private static final Log _log = LogFactoryUtil.getLog(ImportDataManagementImpl.class);
	@Override
	public Response addDossierFileByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Attachment file, String id, String referenceUid,
			String dossierTemplateNo, String dossierPartNo, String dossierPartType, String fileTemplateNo,
			String displayName, String fileType, String isSync, Long createDate, Long modifiedDate) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("START CREATE DOSSIER FILE: "+groupId);
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long dossierId = GetterUtil.getLong(id);
			_log.info("dossierId: "+dossierId);
			Dossier dossier = null;

			if (dossierId != 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				if (Validator.isNull(dossier)) {
					dossier = DossierLocalServiceUtil.getByRef(groupId, id);
				}
			} else {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}

			DataHandler dataHandler = (file != null) ? file.getDataHandler() : null;
			DossierFileActions action = new DossierFileActionsImpl();


			//List<DossierPart> lstParts = DossierPartLocalServiceUtil.getByTemplateNo(groupId, dossier.getDossierTemplateNo());
//			for (DossierPart dp : lstParts) {
//				if (dp.getPartNo().equals(dossierPartNo)) {
//					fileTemplateNo = dp.getFileTemplateNo();
//					dossierTemplateNo = dossier.getDossierTemplateNo();
//				}
//			}
			
			DossierFile oldDossierFile = null;
			if (Validator.isNotNull(dossierPartNo) && Validator.isNotNull(dossierPartType) && Validator.isNotNull(displayName)) {
				oldDossierFile = DossierFileLocalServiceUtil.getByG_DID_PART_NAME(groupId, dossierId, dossierPartNo,
						GetterUtil.getInteger(dossierPartType), displayName);
			}
				if (oldDossierFile != null && modifiedDate != null) {
					if (oldDossierFile.getModifiedDate() != null && oldDossierFile.getModifiedDate().getTime() < modifiedDate) {
						_log.info("__Start update file at:" + new Date());
						DossierFile dossierFile =  null;
						
						if (dataHandler != null && dataHandler.getInputStream() != null) {
							dossierFile = action.updateDossierFile(groupId, 
									dossier.getDossierId(), 
									oldDossierFile.getReferenceUid(), 
									displayName, 
									dataHandler.getInputStream(), serviceContext);
						} else {
							dossierFile = action.updateDossierFile(groupId, 
									dossier.getDossierId(), 
									oldDossierFile.getReferenceUid(), 
									displayName, 
									null, serviceContext);
						}
						_log.info("__End update file at:" + new Date());
						
						dossierFile.setRemoved(false);
						_log.info("__Start update dossier file at:" + new Date());
						DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
						_log.info("__End update dossier file at:" + new Date());

						DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);
						
						_log.info("__End bind to dossierFile" + new Date());

						return Response.status(200).entity(result).build();
					}
					else {
						ErrorMsgModel error = new ErrorMsgModel();
						error.setMessage("Conflict dossier file");
						error.setCode(HttpURLConnection.HTTP_CONFLICT);
						error.setDescription("Conflict dossier file");

						return Response.status(HttpURLConnection.HTTP_CONFLICT).entity(error).build();
					}
				}
				else {
					_log.info("__Start add file at:" + new Date());
					DossierFile dossierFile =  null;
					
					if (dataHandler != null && dataHandler.getInputStream() != null) {
						dossierFile = action.addDossierFile(groupId, dossier.getDossierId(), referenceUid, dossierTemplateNo,
								dossierPartNo, fileTemplateNo, displayName, dataHandler.getName(), 0,
								dataHandler.getInputStream(), fileType, isSync, serviceContext);
					} else {
						dossierFile = action.addDossierFile(groupId, dossier.getDossierId(), referenceUid, dossierTemplateNo,
								dossierPartNo, fileTemplateNo, displayName, displayName, 0, null, fileType, isSync,
								serviceContext);
					}
					
					_log.info("__End add file at:" + new Date());
					dossierFile.setRemoved(false);
					_log.info("__Start update dossier file at:" + new Date());
		
					DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
		
					_log.info("__End update dossier file at:" + new Date());
		
					DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);
					
					_log.info("__End bind to dossierFile" + new Date());
		
					return Response.status(200).entity(result).build();
				}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addDossierImportData(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierPublishModel input) {

		_log.info("START PUPISH");
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			//Get input
			String referenceUid = input.getReferenceUid();
			int counter = 0;
			String serviceCode = input.getServiceCode();
			String serviceName = input.getServiceName();
			String govAgencyCode = input.getGovAgencyCode();
			String govAgencyName = input.getGovAgencyName();
			String applicantName = input.getApplicantName();
			String applicantType = input.getApplicantIdType();
			String applicantIdNo = input.getApplicantIdNo();
			String applicantIdDate = input.getApplicantIdDate();
			String address = input.getAddress();
			String cityCode = input.getCityCode();
			String cityName = input.getCityName();
			String districtCode = input.getDistrictCode();
			String districtName = input.getDistrictName();
			String wardCode = input.getWardCode();
			String wardName = input.getWardName();
			String contactName = input.getContactName();
			String contactTelNo = input.getContactTelNo();
			String contactEmail = input.getContactEmail();
			String dossierTemplateNo = input.getDossierTemplateNo();
			String password = input.getPassword();
			String online = input.getOnline();
			String applicantNote = input.getApplicantNote();
			int originality = GetterUtil.getInteger(input.getOriginality());
			long createDateLong = GetterUtil.getLong(input.getCreateDate());
			long modifiedDateLong = GetterUtil.getLong(input.getModifiedDate());
			long submitDateLong = GetterUtil.getLong(input.getSubmitDate());
			long receiveDateLong = GetterUtil.getLong(input.getReceiveDate());
			long dueDateLong = GetterUtil.getLong(input.getDueDate());
			long releaseDateLong = GetterUtil.getLong(input.getReleaseDate());
			long finishDateLong = GetterUtil.getLong(input.getFinishDate());
			long cancellingDateLong = GetterUtil.getLong(input.getCancellingDate());
			long correcttingDateLong = GetterUtil.getLong(input.getCorrecttingDate());
			long endorsementDateLong = GetterUtil.getLong(input.getEndorsementDate());
			long extendDateLong = GetterUtil.getLong(input.getExtendDate());
			long processDateLong = GetterUtil.getLong(input.getProcessDate());
			String submissionNote = input.getSubmissionNote();
			String lockState = input.getLockState();
			String dossierNo = input.getDossierNo();
			_log.info("dossierNo: "+dossierNo);
			
			Dossier oldDossier = null;
			if (Validator.isNotNull(input.getReferenceUid())) {
				oldDossier = DossierUtils.getDossier(input.getReferenceUid(), groupId);
			} else {
				oldDossier = DossierLocalServiceUtil.getByDossierNo(groupId, dossierNo);
				//
				referenceUid = DossierNumberGenerator.generateReferenceUID(groupId);
			}
			_log.info("originality: "+originality);
			if (oldDossier == null || oldDossier.getOriginality() == 0) {
				Dossier dossier = actions.publishDossier(groupId, 0l, referenceUid, counter, serviceCode, serviceName,
						govAgencyCode, govAgencyName, applicantName, applicantType,
						applicantIdNo, applicantIdDate, address, cityCode,
							cityName, districtCode, districtName, wardCode, wardName,
							contactName, contactTelNo, contactEmail,
							dossierTemplateNo, password, 0, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
							StringPool.BLANK, Boolean.valueOf(online), false, applicantNote,
							originality, 
							createDateLong != 0 ? new Date(createDateLong) : null,
							modifiedDateLong != 0 ? new Date(modifiedDateLong) : null,
							submitDateLong != 0 ? new Date(submitDateLong) : null,
							receiveDateLong != 0 ? new Date(receiveDateLong) : null,
							dueDateLong != 0 ? new Date(dueDateLong) : null,
							releaseDateLong != 0 ? new Date(releaseDateLong) : null,
							finishDateLong != 0 ? new Date(finishDateLong) : null,
							cancellingDateLong != 0 ? new Date(cancellingDateLong) : null,
							correcttingDateLong != 0 ? new Date(correcttingDateLong) : null,
							endorsementDateLong != 0 ? new Date(endorsementDateLong) : null,
							extendDateLong != 0 ? new Date(extendDateLong) : null,
							processDateLong != 0 ? new Date(processDateLong) : null,
							serviceContext);
	
				dossier.setDossierNo(input.getDossierNo());
				dossier.setDossierStatus(input.getDossierStatus());
				dossier.setDossierStatusText(input.getDossierStatusText());
				dossier.setDossierSubStatus(input.getDossierSubStatus());
				dossier.setDossierSubStatusText(input.getDossierSubStatusText());
				dossier.setDossierActionId(input.getDossierActionId() != null ? input.getDossierActionId(): 0);
				dossier.setSubmissionNote(submissionNote);
				dossier.setLockState(lockState);
				dossier.setCounter(input.getCounter() != null ? input.getCounter() : 0);
				dossier.setPostalAddress(input.getPostalAddress());
				dossier.setPostalCityCode(input.getPostalCityCode());
				dossier.setPostalCityName(input.getPostalCityName());
				dossier.setDelegateName(input.getDelegateName());
				dossier.setDelegateAddress(input.getDelegateAddress());
				dossier.setDelegateIdNo(input.getDelegateIdNo());
				dossier.setDelegateTelNo(input.getDelegateTelNo());
				dossier.setDelegateEmail(input.getDelegateEmail());
				dossier.setDelegateAddress(input.getDelegateAddress());
				dossier.setDelegateCityCode(input.getDelegateCityCode());
				dossier.setDelegateDistrictCode(input.getDelegateDistrictCode());
				dossier.setDelegateWardCode(input.getDelegateWardCode());
				dossier.setDelegateCityName(input.getDelegateCityName());
				dossier.setDelegateDistrictName(input.getDelegateDistrictName());
				dossier.setDelegateWardName(input.getDelegateWardName());
				dossier.setDurationCount(input.getDurationCount());
				dossier.setDurationUnit(input.getDurationUnit());
//				dossier.setSampleCount(input.getSampleCount() != null ? input.getSampleCount() : 0l);
				dossier.setDossierName(input.getDossierName());
				dossier.setProcessNo(input.getProcessNo());
				
				//Update dossier
				dossier = DossierLocalServiceUtil.updateDossier(dossier);

				return Response.status(200).entity(JSONFactoryUtil.looseSerializeDeep(dossier)).build();
			}
			else {
				return Response.status(200).entity(JSONFactoryUtil.looseSerializeDeep(oldDossier)).build();				
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
}
