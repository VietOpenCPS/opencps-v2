/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.dossiermgt.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.ws.rs.HttpMethod;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.auth.api.keys.NotificationType;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.cache.actions.CacheActions;
import org.opencps.cache.actions.impl.CacheActionsImpl;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.service.HolidayLocalServiceUtil;
import org.opencps.datamgt.util.ExtendDueDateUtils;
import org.opencps.datamgt.util.HolidayUtils;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.DossierUserActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierPermission;
import org.opencps.dossiermgt.action.impl.DossierUserActionsImpl;
import org.opencps.dossiermgt.action.util.AutoFillFormData;
import org.opencps.dossiermgt.action.util.DocumentTypeNumberGenerator;
import org.opencps.dossiermgt.action.util.DossierActionUtils;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.action.util.DossierNumberGenerator;
import org.opencps.dossiermgt.action.util.DossierPaymentUtils;
import org.opencps.dossiermgt.action.util.KeyPay;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.action.util.PaymentUrlGenerator;
import org.opencps.dossiermgt.constants.ActionConfigTerm;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierActionUserTerm;
import org.opencps.dossiermgt.constants.DossierDocumentTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.constants.StepConfigTerm;
import org.opencps.dossiermgt.exception.DataConflictException;
import org.opencps.dossiermgt.exception.NoSuchDossierUserException;
import org.opencps.dossiermgt.exception.NoSuchPaymentFileException;
import org.opencps.dossiermgt.input.model.DossierInputModel;
import org.opencps.dossiermgt.input.model.DossierMultipleInputModel;
import org.opencps.dossiermgt.input.model.PaymentFileInputModel;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.DossierUser;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessSequence;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.PublishQueue;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierDocumentLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.PublishQueueLocalServiceUtil;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.base.CPSDossierBusinessLocalServiceBaseImpl;
import org.opencps.dossiermgt.service.persistence.DossierActionUserPK;
import org.opencps.dossiermgt.service.persistence.DossierUserPK;
import org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;

import backend.auth.api.exception.NotFoundException;

/**
 * The implementation of the cps dossier business local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.CPSDossierBusinessLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see CPSDossierBusinessLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.CPSDossierBusinessLocalServiceUtil
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor = {
		PortalException.class, SystemException.class
})
public class CPSDossierBusinessLocalServiceImpl
	extends CPSDossierBusinessLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.CPSDossierBusinessLocalServiceUtil} to access the cps dossier business local service.
	 */
	
	public static final String DOSSIER_SATUS_DC_CODE = "DOSSIER_STATUS";
	public static final String DOSSIER_SUB_SATUS_DC_CODE = "DOSSIER_SUB_STATUS";
	CacheActions cache = new CacheActionsImpl();
	int ttl = OpenCPSConfigUtil.getCacheTTL();
	
	private Dossier createCrossDossier(long groupId, ProcessAction proAction, ProcessStep curStep, DossierAction previousAction, Employee employee, Dossier dossier, User user, ServiceContext context) 
		throws PortalException {
		if (Validator.isNotNull(proAction.getCreateDossiers())) {
			//Create new HSLT
			String GOVERNMENT_AGENCY = "GOVERNMENT_AGENCY";

			String govAgencyName = getDictItemName(groupId, GOVERNMENT_AGENCY, proAction.getCreateDossiers());
			String createDossiers = proAction.getCreateDossiers();
			String govAgencyCode = StringPool.BLANK;
			String serviceCode = dossier.getServiceCode();
			String dossierTemplateNo = dossier.getDossierTemplateNo();
			
			if (createDossiers.contains(StringPool.POUND)) {
				String[] splitCDs = createDossiers.split(StringPool.POUND);
				if (splitCDs.length == 2) {
					govAgencyCode = splitCDs[0];
					
					if (splitCDs[1].contains(StringPool.AT)) {
						if (splitCDs[1].split(StringPool.AT).length != 2) {
							throw new PortalException("Cross dossier config error");
						}
						else {
							dossierTemplateNo = splitCDs[1].split(StringPool.AT)[0];
							serviceCode = splitCDs[1].split(StringPool.AT)[1];
						}
					}
					else {
						govAgencyCode = splitCDs[0];
					}
				}
			}
			else {
				if (createDossiers.contains(StringPool.AT)) {
					if (createDossiers.split(StringPool.AT).length != 2) {
						throw new PortalException("Cross dossier config error");
					}
					else {
						govAgencyCode = createDossiers.split(StringPool.AT)[0];
						serviceCode = createDossiers.split(StringPool.AT)[1];
					}
				}
				else {
					govAgencyCode = createDossiers;
				}
			}
			
			ServiceConfig serviceConfig = serviceConfigLocalService.getBySICodeAndGAC(groupId, dossier.getServiceCode(), govAgencyCode);
			
			if (serviceConfig != null) {
				List<ProcessOption> lstOptions = processOptionLocalService.getByServiceProcessId(serviceConfig.getServiceConfigId());
				//

				ProcessOption foundOption = null;
				if (createDossiers.contains(StringPool.POUND)) {
					for (ProcessOption po : lstOptions) {
						DossierTemplate dt = dossierTemplateLocalService.fetchDossierTemplate(po.getDossierTemplateId());
						if (dt.getTemplateNo().equals(dossierTemplateNo)) {
							foundOption = po;
							break;
						}
					}
				}
				else {
					if (lstOptions.size() > 0) {
						foundOption = lstOptions.get(0);
					}
				}
				if (foundOption != null) {
					ServiceProcess ltProcess = serviceProcessLocalService.fetchServiceProcess(foundOption.getServiceProcessId());
					
					DossierTemplate dossierTemplate = dossierTemplateLocalService.fetchDossierTemplate(foundOption.getDossierTemplateId());
//					String delegateName = dossier.getDelegateName();
					//String delegateName = dossier.getGovAgencyName();
					//String delegateAddress = dossier.getDelegateAddress();
					//String delegateTelNo = dossier.getDelegateTelNo();
					//String delegateEmail = dossier.getDelegateEmail();
					//String delegateIdNo = dossier.getGovAgencyCode();
					
					Dossier oldHslt = DossierLocalServiceUtil.getByG_AN_SC_GAC_DTNO_ODID(groupId, dossier.getApplicantIdNo(), dossier.getServiceCode(), govAgencyCode, dossierTemplate.getTemplateNo(), dossier.getDossierId());
					Dossier hsltDossier = null;
					
					if (oldHslt != null) {
						if (oldHslt.getOriginality() < 0) {
							oldHslt.setOriginality(-oldHslt.getOriginality());
						}
						hsltDossier = oldHslt;
					}
					if (hsltDossier == null) {
						hsltDossier = dossierLocalService.initDossier(groupId, 0l, UUID.randomUUID().toString(), 
							dossier.getCounter(), dossier.getServiceCode(),
							dossier.getServiceName(), govAgencyCode, govAgencyName, dossier.getApplicantName(), 
							dossier.getApplicantIdType(), dossier.getApplicantIdNo(), dossier.getApplicantIdDate(),
							dossier.getAddress(), dossier.getCityCode(), dossier.getCityName(), dossier.getDistrictCode(), 
							dossier.getDistrictName(), dossier.getWardCode(), dossier.getWardName(), dossier.getContactName(),
							dossier.getContactTelNo(), dossier.getContactEmail(), dossierTemplate.getTemplateNo(), 
							dossier.getPassword(), dossier.getViaPostal(), dossier.getPostalAddress(), dossier.getPostalCityCode(),
							dossier.getPostalCityName(), dossier.getPostalTelNo(), 
							dossier.getOnline(), dossier.getNotification(), dossier.getApplicantNote(), DossierTerm.ORIGINALITY_DVCTT, context);
					}
					WorkingUnit wu = WorkingUnitLocalServiceUtil.fetchByF_govAgencyCode(dossier.getGroupId(), dossier.getGovAgencyCode());

					String delegateName = null;
					String delegateAddress = null;
					String delegateTelNo = null;
					String delegateEmail = null;
					String delegateIdNo = null;
					if (wu != null) {
						delegateName = wu.getName();
						delegateAddress = wu.getAddress();
						delegateTelNo = wu.getTelNo();
						delegateEmail = wu.getEmail();
						delegateIdNo = wu.getGovAgencyCode();

					}
					else if (user != null && employee != null) {
						delegateName = employee.getFullName();
						delegateAddress = dossier.getGovAgencyName();
						delegateTelNo = employee.getTelNo();
						delegateEmail = employee.getEmail();
					}

					if (hsltDossier != null) {
						hsltDossier.setDelegateName(delegateName != null ? delegateName : StringPool.BLANK);
						hsltDossier.setDelegateAddress(delegateAddress != null ? delegateAddress : StringPool.BLANK);
						hsltDossier.setDelegateTelNo(delegateTelNo != null ? delegateTelNo : StringPool.BLANK);
						hsltDossier.setDelegateEmail(delegateEmail != null ? delegateEmail : StringPool.BLANK);
						hsltDossier.setDelegateIdNo(delegateIdNo != null ? delegateIdNo : StringPool.BLANK);
						hsltDossier.setNew(false);
						hsltDossier = dossierLocalService.updateDossier(hsltDossier);
					}

					//
					String dossierNote = StringPool.BLANK;
					if (previousAction != null) {
						dossierNote = previousAction.getActionNote();
						if (Validator.isNotNull(dossierNote)) {
							dossierNote = previousAction.getStepInstruction();
						}
					}
					if (hsltDossier != null) {
						//Set HSLT dossierId to origin dossier
						hsltDossier.setOriginDossierId(dossier.getDossierId());
						if (ltProcess.getServerNo().contains(StringPool.COMMA)) {
							if (!serviceCode.equals(dossier.getServiceCode())) {
								String serverNoProcess = ltProcess.getServerNo().split(StringPool.COMMA)[0];
								hsltDossier.setServerNo(serverNoProcess + StringPool.AT + serviceCode + StringPool.COMMA + ltProcess.getServerNo().split(StringPool.COMMA)[1]);
								hsltDossier = dossierLocalService.updateDossier(hsltDossier);
							}
						}
						else {
							hsltDossier.setServerNo(ltProcess.getServerNo());
						}
						//Update DossierName
						hsltDossier.setDossierName(dossier.getDossierName());
						hsltDossier.setOriginDossierNo(dossier.getDossierNo());
						dossierLocalService.updateDossier(hsltDossier);
						
						JSONObject jsonDataStatusText = getStatusText(groupId, DOSSIER_SATUS_DC_CODE, DossierTerm.DOSSIER_STATUS_NEW, StringPool.BLANK);
						hsltDossier = dossierLocalService.updateStatus(groupId, hsltDossier.getDossierId(), hsltDossier.getReferenceUid(),
								DossierTerm.DOSSIER_STATUS_NEW,
								jsonDataStatusText != null ? jsonDataStatusText.getString(DossierTerm.DOSSIER_STATUS_NEW) : StringPool.BLANK, StringPool.BLANK,
								StringPool.BLANK, StringPool.BLANK, dossierNote, context);
					}
					else {
						return null;								
					}
					JSONObject jsonDataStatusText = getStatusText(groupId, DOSSIER_SATUS_DC_CODE, DossierTerm.DOSSIER_STATUS_INTEROPERATING, StringPool.BLANK);
					if (curStep != null) {
						dossier = dossierLocalService.updateStatus(groupId, dossier.getDossierId(),
								dossier.getReferenceUid(), DossierTerm.DOSSIER_STATUS_INTEROPERATING,
								jsonDataStatusText != null ? jsonDataStatusText.getString(DossierTerm.DOSSIER_STATUS_INTEROPERATING) : StringPool.BLANK, StringPool.BLANK,
								StringPool.BLANK, curStep.getLockState(), dossierNote, context);	
						dossier.setDossierStatus(DossierTerm.DOSSIER_STATUS_INTEROPERATING);
						dossier.setDossierStatusText(jsonDataStatusText != null ? jsonDataStatusText.getString(DossierTerm.DOSSIER_STATUS_INTEROPERATING) : StringPool.BLANK);
						dossier.setDossierSubStatus(StringPool.BLANK);
						dossier.setDossierSubStatusText(StringPool.BLANK);
						dossier.setLockState(curStep.getLockState());
						dossier.setDossierNote(dossierNote);;
					}
					
					return hsltDossier;
				}
				else {
					return null;						
				}
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}		
	}
	
	private void createDossierDocument(long groupId, long userId, ActionConfig actionConfig, 
			Dossier dossier, DossierAction dossierAction, 
			JSONObject payloadObject, Employee employee, User user,
			ServiceContext context) throws com.liferay.portal.kernel.search.ParseException, JSONException {
		//Check if generate dossier document
		ActionConfig ac = actionConfig;
		if (ac != null) {
			if (dossier.getOriginality() != DossierTerm.ORIGINALITY_DVCTT) {
				if (Validator.isNotNull(ac.getDocumentType()) && !ac.getActionCode().startsWith("@")) {
					//Generate document
					//Generate document
					String[] documentTypes = ac.getDocumentType().split(",");
					for (String documentType : documentTypes) {
						DocumentType dt = DocumentTypeLocalServiceUtil.getByTypeCode(groupId, documentType.trim());
						if (dt != null) {
							String documentCode = DocumentTypeNumberGenerator.generateDocumentTypeNumber(groupId, ac.getCompanyId(), dt.getDocumentTypeId());
							DossierDocument dossierDocument = DossierDocumentLocalServiceUtil.addDossierDoc(groupId,
									dossier.getDossierId(), UUID.randomUUID().toString(), dossierAction.getDossierActionId(),
									dt.getTypeCode(), dt.getDocumentName(), documentCode, 0L, dt.getDocSync(), context);
												
							//Generate PDF
							String formData = dossierAction.getPayload();
							JSONObject payloadTmp = JSONFactoryUtil.createJSONObject(formData);
							if (payloadTmp != null && payloadTmp.has("complementDate")) {
								if (payloadTmp.getLong("complementDate") > 0) {
									Timestamp ts = new Timestamp(payloadTmp.getLong("complementDate"));
									SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
									payloadTmp.put("complementDate", format.format(ts));
								}
							}
	
							JSONObject formDataObj = processMergeDossierFormData(dossier, payloadTmp);
							formDataObj = processMergeDossierProcessRole(dossier, 1, formDataObj, dossierAction);
							formDataObj.put("url", context.getPortalURL());
							if (employee != null) {
								formDataObj.put("userName", employee.getFullName());
							} else {
								formDataObj.put("userName", user.getFullName());
							}
	
							Message message = new Message();
	//						_log.info("Document script: " + dt.getDocumentScript());
							JSONObject msgData = JSONFactoryUtil.createJSONObject();
							msgData.put("className", DossierDocument.class.getName());
							msgData.put("classPK", dossierDocument.getDossierDocumentId());
							msgData.put("jrxmlTemplate", dt.getDocumentScript());
							msgData.put("formData", formDataObj.toJSONString());
							msgData.put("userId", userId);
	
							message.put("msgToEngine", msgData);
							MessageBusUtil.sendMessage("jasper/engine/out/destination", message);
							
							payloadObject.put("dossierDocument", dossierDocument.getDossierDocumentId());
						}
					}
				}
			}
		}
	}
	
	private void createDossierSync(long groupId, long userId, ActionConfig actionConfig, ProcessAction proAction, DossierAction dossierAction, Dossier dossier, int syncType, 
			ProcessOption option,
			JSONObject payloadObject, Map<String, Boolean> flagChanged, 
			String actionCode, String actionUser, String actionNote, ServiceProcess serviceProcess,
			ServiceContext context) throws PortalException {
		//Create DossierSync
		String dossierRefUid = dossier.getReferenceUid();
		String syncRefUid = UUID.randomUUID().toString();

		if (syncType > 0) {
			int state = DossierActionUtils.getSyncState(syncType, dossier);
			
			//If state = 1 set pending dossier
			if (state == DossierSyncTerm.STATE_WAITING_SYNC) {
				if (dossierAction != null) {
					dossierAction.setPending(true);
					dossierActionLocalService.updateDossierAction(dossierAction);
				}
			}
			else {
				if (dossierAction != null) {
					dossierAction.setPending(false);
					dossierActionLocalService.updateDossierAction(dossierAction);				
				}
			}
			
			//Update payload
			
			JSONArray dossierFilesArr = JSONFactoryUtil.createJSONArray();
			List<DossierFile> lstFiles = DossierFileLocalServiceUtil.findByDID(dossier.getDossierId());
			if (actionConfig.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST) {
				if (dossier.getOriginDossierId() == 0) {
					if (lstFiles.size() > 0) {
						for (DossierFile df : lstFiles) {
							JSONObject dossierFileObj = JSONFactoryUtil.createJSONObject();
							dossierFileObj.put(DossierFileTerm.REFERENCE_UID, df.getReferenceUid());
							dossierFilesArr.put(dossierFileObj);
						}
					}					
				}
				else {
//					ServiceConfig serviceConfig = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, dossier.getServiceCode(), dossier.getGovAgencyCode());
//					List<ProcessOption> lstOptions = ProcessOptionLocalServiceUtil.getByServiceProcessId(serviceConfig.getServiceConfigId());
					
//					if (serviceConfig != null) {
//						if (lstOptions.size() > 0) {
//							ProcessOption processOption = lstOptions.get(0);
							ProcessOption processOption = option;
							
							DossierTemplate dossierTemplate = dossierTemplateLocalService.fetchDossierTemplate(processOption.getDossierTemplateId());
							List<DossierPart> lstParts = dossierPartLocalService.getByTemplateNo(groupId, dossierTemplate.getTemplateNo());
							
							List<DossierFile> lstOriginFiles = dossierFileLocalService.findByDID(dossier.getOriginDossierId());
							if (lstOriginFiles.size() > 0) {
								for (DossierFile df : lstOriginFiles) {
									boolean flagHslt = false;
									for (DossierPart dp : lstParts) {
										if (dp.getPartNo().equals(df.getDossierPartNo())) {
											flagHslt = true;
											break;
										}
									}
									if (flagHslt) {
										JSONObject dossierFileObj = JSONFactoryUtil.createJSONObject();
										dossierFileObj.put(DossierFileTerm.REFERENCE_UID, df.getReferenceUid());
										dossierFilesArr.put(dossierFileObj);										
									}
								}
							}										
//						}
//					}
					
				}
			}
			else {
				//Sync result files
				
			}
			
			payloadObject.put("dossierFiles", dossierFilesArr);
			
			if (Validator.isNotNull(proAction.getReturnDossierFiles())) {
				List<DossierFile> lsDossierFile = lstFiles;
				dossierFilesArr = JSONFactoryUtil.createJSONArray();

				// check return file
				List<String> returnDossierFileTemplateNos = ListUtil
						.toList(StringUtil.split(proAction.getReturnDossierFiles()));

				for (DossierFile dossierFile : lsDossierFile) {
					if (returnDossierFileTemplateNos.contains(dossierFile.getFileTemplateNo())) {
						JSONObject dossierFileObj = JSONFactoryUtil.createJSONObject();
						dossierFileObj.put(DossierFileTerm.REFERENCE_UID, dossierFile.getReferenceUid());
						dossierFilesArr.put(dossierFileObj);

					}

				}
				payloadObject.put("dossierFiles", dossierFilesArr);				
			}
			
			List<DossierDocument> lstDossierDocuments = dossierDocumentLocalService.getDossierDocumentList(dossier.getDossierId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			JSONArray dossierDocumentArr = JSONFactoryUtil.createJSONArray();

			for (DossierDocument dossierDocument : lstDossierDocuments) {
				JSONObject dossierDocumentObj = JSONFactoryUtil.createJSONObject();
				dossierDocumentObj.put(DossierDocumentTerm.REFERENCE_UID, dossierDocument.getReferenceUid());
				dossierDocumentArr.put(dossierDocumentObj);
			}
			payloadObject.put("dossierFiles", dossierFilesArr);				
			payloadObject.put("dossierDocuments", dossierDocumentArr);
			
			//Put dossier note
			payloadObject.put(DossierTerm.DOSSIER_NOTE, dossier.getDossierNote());
			//Put dossier note
			payloadObject.put(DossierTerm.SUBMIT_DATE, dossier.getSubmitDate() != null ? dossier.getSubmitDate().getTime() : 0);
			
//			_log.info("Flag changed: " + flagChanged);
			payloadObject = DossierActionUtils.buildChangedPayload(payloadObject, flagChanged, dossier);
			if (Validator.isNotNull(dossier.getServerNo())
					&& dossier.getServerNo().split(StringPool.COMMA).length > 1) {
				String serverNo = dossier.getServerNo().split(StringPool.COMMA)[0].split(StringPool.AT)[0];
				dossierSyncLocalService.updateDossierSync(groupId, userId, dossier.getDossierId(), dossierRefUid, syncRefUid,
						dossierAction.getPrimaryKey(), actionCode, proAction.getActionName(), actionUser, actionNote,
						syncType, actionConfig.getInfoType(), payloadObject.toJSONString(), serverNo, state);				
			}
			else {
				dossierSyncLocalService.updateDossierSync(groupId, userId, dossier.getDossierId(), dossierRefUid, syncRefUid,
					dossierAction.getPrimaryKey(), actionCode, proAction.getActionName(), actionUser, actionNote,
					syncType, actionConfig.getInfoType(), payloadObject.toJSONString(), dossier.getServerNo(), state);
			}
			//Gửi thông tin hồ sơ để tra cứu
			if (state == DossierSyncTerm.STATE_NOT_SYNC
					&& actionConfig != null && actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_SENT) {
				publishEvent(dossier, context);
			}
		}
		else if (actionConfig != null && actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_SENT) {
			publishEvent(dossier, context);			
		}		
	}
	
	private void doMappingAction(long groupId, long userId, Employee employee, Dossier dossier, 
			ActionConfig actionConfig, String actionUser, String actionNote, String payload, String assignUsers, 
			String payment,ServiceContext context) throws PortalException, Exception {
		if (Validator.isNotNull(actionConfig) && Validator.isNotNull(actionConfig.getMappingAction())) {
			ActionConfig mappingConfig = actionConfigLocalService.getByCode(groupId, actionConfig.getMappingAction());
			if (dossier.getOriginDossierId() != 0) {
				Dossier hslt = dossierLocalService.fetchDossier(dossier.getOriginDossierId());
				ProcessOption optionHslt = getProcessOption(hslt.getServiceCode(), hslt.getGovAgencyCode(),
						hslt.getDossierTemplateNo(), groupId);
				ProcessAction actionHslt = getProcessAction(groupId, hslt.getDossierId(), hslt.getReferenceUid(), actionConfig.getMappingAction(), optionHslt.getServiceProcessId());
				String actionUserHslt = actionUser;
				if (employee != null) {
					actionUserHslt = actionUser;
				}
				if (DossierTerm.DOSSIER_STATUS_NEW.equals(hslt.getDossierStatus())) {
					Date now = new Date();
					hslt.setSubmitDate(now);
					hslt = dossierLocalService.updateDossier(hslt);
					try {
						JSONObject payloadObj = JSONFactoryUtil.createJSONObject(payload);
						payloadObj.put(DossierTerm.SUBMIT_DATE, now.getTime());
						payload = payloadObj.toJSONString();
					}
					catch (JSONException e) {
						_log.debug(e);
					}
				}
				doAction(groupId, userId, hslt, optionHslt, actionHslt, actionConfig.getMappingAction(), actionUserHslt, actionNote, payload, assignUsers, payment, mappingConfig.getSyncType(), context);
			}
			else {
				Dossier originDossier = dossierLocalService.getByOrigin(groupId, dossier.getDossierId());
				if (originDossier != null) {					
					ProcessOption optionOrigin = getProcessOption(originDossier.getServiceCode(), originDossier.getGovAgencyCode(),
							originDossier.getDossierTemplateNo(), groupId);
					ProcessAction actionOrigin = getProcessAction(groupId, originDossier.getDossierId(), originDossier.getReferenceUid(), actionConfig.getMappingAction(), optionOrigin.getServiceProcessId());
					doAction(groupId, userId, originDossier, optionOrigin, actionOrigin, actionConfig.getMappingAction(), actionUser, actionNote, payload, assignUsers, payment, mappingConfig.getSyncType(), context);
				}
			}
		}		
	}
	
	private DossierAction createActionAndAssignUser(long groupId, long userId, ProcessStep curStep, ActionConfig actionConfig,
			DossierAction dossierAction,
			DossierAction previousAction, ProcessAction proAction, Dossier dossier, 
			String actionCode, String actionUser, String actionNote, String payload, String assignUsers, 
			String payment, ServiceProcess serviceProcess, ProcessOption option, Map<String, Boolean> flagChanged, ServiceContext context) throws PortalException {
		int actionOverdue = getActionDueDate(groupId, dossier.getDossierId(), dossier.getReferenceUid(), proAction.getProcessActionId());
		String actionName = proAction.getActionName();
		String prevStatus = dossier.getDossierStatus();

		if (curStep != null) {
			String curStatus = curStep.getDossierStatus();
			String curSubStatus = curStep.getDossierSubStatus();
			String stepCode = curStep.getStepCode();
			String stepName = curStep.getStepName();
			String stepInstruction = curStep.getStepInstruction();
			String sequenceNo = curStep.getSequenceNo();
			
			JSONObject jsonDataStatusText = getStatusText(groupId, DOSSIER_SATUS_DC_CODE, curStatus, curSubStatus);

			String fromStepCode = previousAction != null ? previousAction.getStepCode() : StringPool.BLANK;
			String fromStepName = previousAction != null ? previousAction.getStepName() : StringPool.BLANK;
			String fromSequenceNo = previousAction != null ? previousAction.getSequenceNo() : StringPool.BLANK;
			
			int state = DossierActionTerm.STATE_WAITING_PROCESSING;
			int eventStatus = (actionConfig != null ? (actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_NOT_SENT ? DossierActionTerm.EVENT_STATUS_NOT_CREATED : DossierActionTerm.EVENT_STATUS_WAIT_SENDING) : DossierActionTerm.EVENT_STATUS_NOT_CREATED);

			boolean rollbackable = false;
			if (actionConfig != null) {
				if (actionConfig.getRollbackable()) {
					rollbackable = true;
				}
				else {
				}
			}
			else {
				if (proAction.isRollbackable()) {
					rollbackable = true;
				}
				else {
				}
			}
			dossierAction = dossierActionLocalService.updateDossierAction(groupId, 0, dossier.getDossierId(),
					serviceProcess.getServiceProcessId(), dossier.getDossierActionId(), 
					fromStepCode, fromStepName, fromSequenceNo,
					actionCode, actionUser, actionName, actionNote, actionOverdue,
					stepCode, stepName, 
					sequenceNo,
					null, 0l, payload, stepInstruction, 
					state, eventStatus, rollbackable,
					context);
			dossier.setDossierActionId(dossierAction.getDossierActionId());
			
			String dossierNote = StringPool.BLANK;
			if (dossierAction != null) {
				dossierNote = dossierAction.getActionNote();
				if (Validator.isNotNull(dossierNote)) {
					dossierNote = dossierAction.getStepInstruction();
				}
			}
			//Update previous action nextActionId
			Date now = new Date();
			if (previousAction != null && dossierAction != null) {
				previousAction.setNextActionId(dossierAction.getDossierActionId());
				previousAction.setState(DossierActionTerm.STATE_ALREADY_PROCESSED);
				previousAction.setModifiedDate(now);
				previousAction = dossierActionLocalService.updateDossierAction(previousAction);
			}
			
			updateStatus(dossier, curStatus,
					jsonDataStatusText != null ? jsonDataStatusText.getString(curStatus) : StringPool.BLANK, curSubStatus,
					jsonDataStatusText != null ? jsonDataStatusText.getString(curSubStatus) : StringPool.BLANK, curStep.getLockState(), dossierNote, context);
			
			//Cập nhật cờ đồng bộ ngày tháng sang các hệ thống khác
			Map<String, Boolean> resultFlagChanged = updateProcessingDate(dossierAction, previousAction, curStep, dossier, curStatus, curSubStatus, prevStatus, actionConfig, option, serviceProcess, context);
			for (Map.Entry<String, Boolean> entry : resultFlagChanged.entrySet()) {
				flagChanged.put(entry.getKey(), entry.getValue());
			}
			dossierAction = dossierActionLocalService.fetchDossierAction(dossier.getDossierActionId());
		}

		//Thiết lập quyền thao tác hồ sơ

		int allowAssignUser = proAction.getAllowAssignUser();
		if (allowAssignUser != ProcessActionTerm.NOT_ASSIGNED) {
			if (Validator.isNotNull(assignUsers)) {
				JSONArray assignedUsersArray = JSONFactoryUtil.createJSONArray(assignUsers);
				assignDossierActionUser(dossier, allowAssignUser,
					dossierAction, userId, groupId, proAction.getAssignUserId(),
					assignedUsersArray);
				if (OpenCPSConfigUtil.isNotificationEnable()) {
					createNotificationSMS(userId, groupId, dossier, assignedUsersArray, dossierAction, context);	
				}
			} else {
				initDossierActionUser(proAction, dossier, allowAssignUser, dossierAction, userId, groupId,
						proAction.getAssignUserId());
			}
		} else {
			//Process role as step
			if (Validator.isNotNull(curStep.getRoleAsStep())) {
				copyRoleAsStep(curStep, dossier);
			}	
			else {
				initDossierActionUser(proAction, dossier, allowAssignUser, dossierAction, userId, groupId,
						proAction.getAssignUserId());						
			}
		}
		
		return dossierAction;
	}
	
	private DossierAction doActionInsideProcess(long groupId, long userId, Dossier dossier, ActionConfig actionConfig, ProcessOption option, ProcessAction proAction,
			String actionCode, String actionUser, String actionNote, String payload, String assignUsers, 
			String payment,
			int syncType,
			ServiceContext context) throws PortalException, SystemException, Exception {
		context.setUserId(userId);
		DossierAction dossierAction = null;
		Map<String, Boolean> flagChanged = new HashMap<>();
		JSONObject payloadObject = JSONFactoryUtil.createJSONObject();
		User user = userLocalService.fetchUser(userId);
		String dossierStatus = dossier.getDossierStatus().toLowerCase();
		Employee employee = null;
		Serializable employeeCache = cache.getFromCache("Employee", groupId +"_"+ userId);
//		_log.info("EMPLOYEE CACHE: " + employeeCache);
		if (employeeCache == null) {
			employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
			if (employee != null) {
				cache.addToCache("Employee",
						groupId +"_"+ userId, (Serializable) employee,
						ttl);
			}
		} else {
			employee = (Employee) employeeCache;
		}		
		try {
			payloadObject = JSONFactoryUtil.createJSONObject(payload);
		}
		catch (JSONException e) {
			_log.debug(e);
		}
		if (Validator.isNotNull(dossierStatus)) {
			if(!"new".equals(dossierStatus)) {
			} else if (dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT) {
				dossier.setSubmitDate(new Date());
			}
		}

		long dossierId = dossier.getDossierId();
		ServiceProcess serviceProcess = null;
		
		DossierAction previousAction = null;
		if (dossier.getDossierActionId() != 0) {
			previousAction = dossierActionLocalService.fetchDossierAction(dossier.getDossierActionId());
		}
		
		//Cập nhật thông tin hồ sơ dựa vào payload truyền vào khi thực hiện thao tác
		if (Validator.isNotNull(payload)) {
			JSONObject pl = payloadObject;
			updateDossierPayload(dossier, pl);
		}
		
		if ((option != null || previousAction != null) && proAction != null) {
			long serviceProcessId = (option != null ? option.getServiceProcessId() : previousAction.getServiceProcessId());
			Serializable serviceProcessCache = cache.getFromCache("ServiceProcess", groupId +"_"+ serviceProcessId);
			if (serviceProcessCache == null) {
				serviceProcess = serviceProcessLocalService.fetchServiceProcess(serviceProcessId);
				if (serviceProcess != null) {
					cache.addToCache("ServiceProcess",
							groupId +"_"+ serviceProcessId, (Serializable) serviceProcess,
							ttl);
				}
			} else {
				serviceProcess = (ServiceProcess) serviceProcessCache;
			}		
			String paymentFee = StringPool.BLANK;

			String postStepCode = proAction.getPostStepCode();
			
			//Xử lý phiếu thanh toán
			processPaymentFile(groupId, userId, payment, option, proAction, previousAction, dossier, context);
			
			//Bước sau không có thì mặc định quay lại bước trước đó
			if (Validator.isNull(postStepCode)) {
				postStepCode = previousAction.getFromStepCode();
				ProcessStep backCurStep = processStepLocalService.fetchBySC_GID(postStepCode, groupId, serviceProcessId);
				String curStatus = backCurStep.getDossierStatus();
				String curSubStatus = backCurStep.getDossierSubStatus();
				
				JSONObject jsonDataStatusText = getStatusText(groupId, DOSSIER_SATUS_DC_CODE, curStatus, curSubStatus);

				//update dossierStatus
				dossier = DossierLocalServiceUtil.updateStatus(groupId, dossierId, dossier.getReferenceUid(), curStatus,
						jsonDataStatusText != null ? jsonDataStatusText.getString(curStatus) : StringPool.BLANK, curSubStatus,
						jsonDataStatusText != null ? jsonDataStatusText.getString(curSubStatus) : StringPool.BLANK, backCurStep.getLockState(), dossier.getDossierNote(), context);
								
				dossier.setDossierActionId(previousAction.getPreviousActionId());
				dossierLocalService.updateDossier(dossier);
				
				return previousAction;
			}
			ProcessStep curStep = processStepLocalService.fetchBySC_GID(postStepCode, groupId, serviceProcessId);
			
			//Kiểm tra cấu hình cần tạo hồ sơ liên thông
			Dossier hsltDossier = createCrossDossier(groupId, proAction, curStep, previousAction, employee, dossier, user, context);
			if (Validator.isNotNull(proAction.getCreateDossiers())) {
				if (Validator.isNull(hsltDossier)) {
					return null;
				}				
			}
			
			//Cập nhật hành động và quyền người dùng với hồ sơ
			dossierAction = createActionAndAssignUser(groupId, userId, curStep, actionConfig, dossierAction, previousAction, proAction, dossier, actionCode, actionUser, actionNote, payload, assignUsers, paymentFee, serviceProcess, option, flagChanged, context);
			
//			dossier = dossierLocalService.updateDossier(dossier);
			
			//Tạo văn bản đính kèm
			createDossierDocument(groupId, userId, actionConfig, dossier, dossierAction, payloadObject, employee, user, context);
			
			//Kiểm tra xem có gửi dịch vụ vận chuyển hay không
			if (proAction.getPreCondition().toLowerCase().contains("sendviapostal=1")) {
				vnpostEvent(dossier);
			}		
		}
		else {
			
		}

		//Create notification
		if (OpenCPSConfigUtil.isNotificationEnable()) {
			createNotificationQueue(user, groupId, dossier, actionConfig, dossierAction, context);
		}
		
		//Create subcription
		createSubcription(userId, groupId, dossier, actionConfig, dossierAction, context);
		
		//Tạo thông tin đồng bộ hồ sơ
		createDossierSync(groupId, userId, actionConfig, proAction, dossierAction, dossier, syncType, option, payloadObject, flagChanged, actionCode, actionUser, actionNote, serviceProcess, context);
		
		//Thực hiện thao tác lên hồ sơ gốc hoặc hồ sơ liên thông trong trường hợp có cấu hình mappingAction
		doMappingAction(groupId, userId, employee, dossier, actionConfig, actionUser, actionNote, payload, assignUsers, payment, context);
		
		//Update dossier
		dossierLocalService.updateDossier(dossier);
		
//		Indexer<Dossier> indexer = IndexerRegistryUtil
//				.nullSafeGetIndexer(Dossier.class);
//		indexer.reindex(dossier);

		return dossierAction;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={SystemException.class, PortalException.class, Exception.class })
	public DossierAction doAction(long groupId, long userId, Dossier dossier, ProcessOption option, ProcessAction proAction,
			String actionCode, String actionUser, String actionNote, String payload, String assignUsers, 
			String payment,
			int syncType,
			ServiceContext context) throws PortalException, SystemException, Exception {
		context.setUserId(userId);
		DossierAction dossierAction = null;

		ActionConfig actionConfig = null;
		actionConfig = actionConfigLocalService.getByCode(groupId, actionCode);
		
		if (actionConfig != null && !actionConfig.getInsideProcess()) {
			dossierAction = doActionOutsideProcess(groupId, userId, dossier, actionConfig, option, proAction, actionCode, actionUser, actionNote, payload, assignUsers, payment, syncType, context);			
		}
		else {
			dossierAction = doActionInsideProcess(groupId, userId, dossier, actionConfig, option, proAction, actionCode, actionUser, actionNote, payload, assignUsers, payment, syncType, context);
		}
				
		return dossierAction;
	}
	
	private void createNotificationQueue(User user, long groupId, Dossier dossier, ActionConfig actionConfig, DossierAction dossierAction, ServiceContext context) throws PortalException {
//		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
//		User u = UserLocalServiceUtil.fetchUser(userId);
        JSONObject payloadObj = JSONFactoryUtil.createJSONObject();
        try {		
        	payloadObj.put(
					"Dossier", JSONFactoryUtil.createJSONObject(
						JSONFactoryUtil.looseSerialize(dossier)));
        	
        	if (dossierAction != null) {
        		payloadObj.put("actionCode", dossierAction.getActionCode());
        		payloadObj.put("actionUser", dossierAction.getActionUser());
        		payloadObj.put("actionName", dossierAction.getActionName());
        		payloadObj.put("actionNote", dossierAction.getActionNote());
        	}
        }
        catch (Exception e) {
        	_log.error(e);
        }
        
		if (actionConfig != null && Validator.isNotNull(actionConfig.getNotificationType())) {
//			Notificationtemplate notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, actionConfig.getNotificationType());
			Serializable notiCache = cache.getFromCache("NotificationTemplate", groupId +"_"+ actionConfig.getNotificationType());
			Notificationtemplate notiTemplate = null;
//			notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, actionConfig.getNotificationType());
			if (notiCache == null) {
				notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, actionConfig.getNotificationType());
				if (notiTemplate != null) {
					cache.addToCache("NotificationTemplate",
							groupId +"_"+ actionConfig.getNotificationType(), (Serializable) notiTemplate,
							ttl);
				}
			} else {
				notiTemplate = (Notificationtemplate) notiCache;
			}

			Date now = new Date();
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(now);
	        	  
			if (notiTemplate != null) {
				if ("minutely".equals(notiTemplate.getInterval())) {
			        cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());					
				}
				else if ("hourly".equals(notiTemplate.getInterval())) {
			        cal.add(Calendar.HOUR, notiTemplate.getExpireDuration());										
				}
				else {
			        cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());										
				}
				Date expired = cal.getTime();

				if (actionConfig.getNotificationType().startsWith("APLC")) {
					if (dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA
							|| dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) {
						try {
//							Applicant applicant = ApplicantLocalServiceUtil.fetchByAppId(dossier.getApplicantIdNo());
							List<Applicant> applicants = ApplicantLocalServiceUtil.findByAppIds(dossier.getApplicantIdNo());
							Applicant foundApplicant = (applicants.isEmpty() ? null : applicants.get(0));
							
							for (Applicant applicant : applicants) {
								long toUserId = (applicant != null ? applicant.getMappingUserId() : 0l);
								if (toUserId != 0) {
									foundApplicant = applicant;
									break;
								}
							}
							
							if (foundApplicant != null) {
								NotificationQueueLocalServiceUtil.addNotificationQueue(
										user.getUserId(), groupId, 
										actionConfig.getNotificationType(), 
										Dossier.class.getName(), 
										String.valueOf(dossier.getDossierId()), 
										payloadObj.toJSONString(), 
										user.getFullName(), 
										dossier.getApplicantName(), 
										foundApplicant.getMappingUserId(), 
										dossier.getContactEmail(), 
										dossier.getContactTelNo(), 
										now, 
										expired, 
										context);								
							}
						} catch (NoSuchUserException e) {
							_log.debug(e);
						}
					}
				}
				else if (actionConfig.getNotificationType().startsWith("USER")) {
					
				}				
			}
		}	
		
//		Notificationtemplate emplTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, "EMPL-01");
		Serializable emplCache = cache.getFromCache("NotificationTemplate", groupId +"_"+ "EMPL-01");
		//Notificationtemplate emplTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, "EMPL-01");
		Notificationtemplate emplTemplate = null;
		if (emplCache == null) {
			emplTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, "EMPL-01");
			if (emplTemplate != null) {
				cache.addToCache("NotificationTemplate",
						groupId +"_"+ actionConfig.getNotificationType(), (Serializable) emplTemplate,
						ttl);
			}
		} else {
			emplTemplate = (Notificationtemplate) emplCache;
		}

		Date now = new Date();
        Calendar calEmpl = Calendar.getInstance();
        calEmpl.setTime(now);
        
        if (emplTemplate != null) {
			if ("minutely".equals(emplTemplate.getInterval())) {
				calEmpl.add(Calendar.MINUTE, emplTemplate.getExpireDuration());					
			}
			else if ("hourly".equals(emplTemplate.getInterval())) {
				calEmpl.add(Calendar.HOUR, emplTemplate.getExpireDuration());										
			}
			else {
				calEmpl.add(Calendar.MINUTE, emplTemplate.getExpireDuration());										
			}
			Date expired = calEmpl.getTime();
	
			if (dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA
					|| dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) {
				try {
					String stepCode = dossierAction.getStepCode();
					StringBuilder buildX = new StringBuilder(stepCode);
					if (stepCode.length() > 0) {
						buildX.setCharAt(stepCode.length() - 1, 'x');					
					}
					String stepCodeX = buildX.toString();
					
					StepConfig stepConfig = StepConfigLocalServiceUtil.getByCode(groupId, dossierAction.getStepCode());
					StepConfig stepConfigX = StepConfigLocalServiceUtil.getByCode(groupId, stepCodeX);
					if ((stepConfig != null && stepConfig.getStepType() == StepConfigTerm.STEP_TYPE_DISPLAY_MENU_BY_PROCESSED)
							|| (stepConfigX != null && stepConfigX.getStepType() == StepConfigTerm.STEP_TYPE_DISPLAY_MENU_BY_PROCESSED)) {
						List<DossierActionUser> lstDaus = DossierActionUserLocalServiceUtil.getByDossierAndStepCode(dossier.getDossierId(), dossierAction.getStepCode());
						for (DossierActionUser dau : lstDaus) {
							if (dau.getAssigned() == DossierActionUserTerm.ASSIGNED_TH || dau.getAssigned() == DossierActionUserTerm.ASSIGNED_PH) {
//								Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, dau.getUserId());
								Serializable employeeCache = cache.getFromCache("Employee", groupId +"_"+ dau.getUserId());
								Employee employee = null;
//								employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, dau.getUserId());
								if (employeeCache == null) {
									employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, dau.getUserId());
									if (employee != null) {
										cache.addToCache("Employee",
												groupId +"_"+ dau.getUserId(), (Serializable) employee,
												ttl);
									}
								} else {
									employee = (Employee) employeeCache;
								}

								if (employee != null) {
									String telNo = employee != null ? employee.getTelNo() : StringPool.BLANK;
									String fullName = employee != null ? employee.getFullName() : StringPool.BLANK;
									long start = System.currentTimeMillis();
									NotificationQueueLocalServiceUtil.addNotificationQueue(
											user.getUserId(), groupId, 
											"EMPL-01", 
											Dossier.class.getName(), 
											String.valueOf(dossier.getDossierId()), 
											payloadObj.toJSONString(), 
											user.getFullName(), 
											fullName, 
											dau.getUserId(), 
											employee.getEmail(), 
											telNo, 
											now, 
											expired, 
											context);
									_log.debug("ADD NOTI QUEUE: " + (System.currentTimeMillis() - start));
								}
							}
						}
					}
				} catch (NoSuchUserException e) {
					_log.error(e);
					//_log.error(e);
	//				e.printStackTrace();
				}
			}	
        }
	}
	
	private void updateDossierPayload(Dossier dossier, JSONObject obj) {
		if (obj.has(DossierTerm.DOSSIER_NOTE)) {
			if (!obj.getString(DossierTerm.DOSSIER_NOTE).equals(dossier.getDossierNote())) {
				dossier.setDossierNote(obj.getString(DossierTerm.DOSSIER_NOTE));
			}
		}
		if (obj.has(DossierTerm.EXTEND_DATE) && Validator.isNotNull(obj.get(DossierTerm.EXTEND_DATE))
				&& GetterUtil.getLong(obj.get(DossierTerm.EXTEND_DATE)) > 0) {
			if (dossier.getExtendDate() == null || obj.getLong(DossierTerm.EXTEND_DATE) != dossier.getExtendDate().getTime()) {
				dossier.setExtendDate(new Date(obj.getLong(DossierTerm.EXTEND_DATE)));
			}
		}
		if (obj.has(DossierTerm.DOSSIER_NO)) {
			//_log.info("Sync dossier no");
			if (Validator.isNotNull(obj.getString(DossierTerm.DOSSIER_NO)) && !obj.getString(DossierTerm.DOSSIER_NO).equals(dossier.getDossierNo())) {
				//_log.info("Sync set dossier no");
				dossier.setDossierNo(obj.getString(DossierTerm.DOSSIER_NO));
			}
		}
		if (obj.has(DossierTerm.DUE_DATE) && Validator.isNotNull(obj.get(DossierTerm.DUE_DATE))
				&& GetterUtil.getLong(obj.get(DossierTerm.DUE_DATE)) > 0) {
			if (dossier.getDueDate() == null || obj.getLong(DossierTerm.DUE_DATE) != dossier.getDueDate().getTime()) {
				dossier.setDueDate(new Date(obj.getLong(DossierTerm.DUE_DATE)));
			}
		}
		if (obj.has(DossierTerm.FINISH_DATE) && Validator.isNotNull(obj.get(DossierTerm.FINISH_DATE))
				&& GetterUtil.getLong(obj.get(DossierTerm.FINISH_DATE)) > 0) {
			if (dossier.getFinishDate() == null || obj.getLong(DossierTerm.FINISH_DATE) != dossier.getFinishDate().getTime()) {
				dossier.setFinishDate(new Date(obj.getLong(DossierTerm.FINISH_DATE)));	
			}
		}
		if (obj.has(DossierTerm.RECEIVE_DATE) && Validator.isNotNull(obj.get(DossierTerm.RECEIVE_DATE))
				&& GetterUtil.getLong(obj.get(DossierTerm.RECEIVE_DATE)) > 0) {
			if (dossier.getReceiveDate() == null || obj.getLong(DossierTerm.RECEIVE_DATE) != dossier.getReceiveDate().getTime()) {
				dossier.setReceiveDate(new Date(obj.getLong(DossierTerm.RECEIVE_DATE)));	
			}
		}
		if (obj.has(DossierTerm.SUBMIT_DATE) && Validator.isNotNull(obj.get(DossierTerm.SUBMIT_DATE))
				&& GetterUtil.getLong(obj.get(DossierTerm.SUBMIT_DATE)) > 0) {
			if (dossier.getSubmitDate() == null || (dossier.getSubmitDate() != null && obj.getLong(DossierTerm.SUBMIT_DATE) != dossier.getSubmitDate().getTime())) {
				dossier.setSubmitDate(new Date(obj.getLong(DossierTerm.SUBMIT_DATE)));	
			}
		}
		if (obj.has(DossierTerm.EXTEND_DATE) && Validator.isNotNull(obj.get(DossierTerm.EXTEND_DATE))
				&& GetterUtil.getLong(obj.get(DossierTerm.EXTEND_DATE)) > 0) {
			if (dossier.getExtendDate() == null || obj.getLong(DossierTerm.EXTEND_DATE) != dossier.getExtendDate().getTime()) {
				dossier.setExtendDate(new Date(obj.getLong(DossierTerm.EXTEND_DATE)));	
			}
		}
		if (obj.has(DossierTerm.DOSSIER_NOTE)) {
			if (dossier.getDossierNote() == null || !obj.getString(DossierTerm.DOSSIER_NOTE).equals(dossier.getDossierNote())) {
				dossier.setDossierNote(obj.getString(DossierTerm.DOSSIER_NOTE));
			}
		}
		if (obj.has(DossierTerm.SUBMISSION_NOTE)) {
			if (!obj.getString(DossierTerm.SUBMISSION_NOTE).equals(dossier.getDossierNote())) {
				dossier.setSubmissionNote(obj.getString(DossierTerm.SUBMISSION_NOTE));
			}
		}
		if (obj.has(DossierTerm.RELEASE_DATE) && Validator.isNotNull(obj.get(DossierTerm.RELEASE_DATE))
				&& GetterUtil.getLong(obj.get(DossierTerm.RELEASE_DATE)) > 0) {
			if (dossier.getReleaseDate() == null || obj.getLong(DossierTerm.RELEASE_DATE) != dossier.getReleaseDate().getTime()) {
				dossier.setReleaseDate(new Date(obj.getLong(DossierTerm.RELEASE_DATE)));	
			}
		}
		if (obj.has(DossierTerm.LOCK_STATE)) {
			if (!obj.getString(DossierTerm.LOCK_STATE).equals(dossier.getLockState())) {
				dossier.setLockState(obj.getString(DossierTerm.LOCK_STATE));
			}
		}
		if (obj.has(DossierTerm.BRIEF_NOTE)) {
			if (!obj.getString(DossierTerm.BRIEF_NOTE).equals(dossier.getBriefNote())) {
				dossier.setBriefNote(obj.getString(DossierTerm.BRIEF_NOTE));
			}
		}	
	}
	
	private void processPaymentFile(long groupId, long userId, String payment, ProcessOption option, 
			ProcessAction proAction, DossierAction previousAction, Dossier dossier, ServiceContext context)
		throws PortalException {
//		long serviceProcessId = (option != null ? option.getServiceProcessId() : previousAction.getServiceProcessId());
//		ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);
		String paymentFee = StringPool.BLANK;
		
		//Yêu cầu nộp tạm ứng
		if (proAction.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_YEU_CAU_NOP_TAM_UNG
				|| proAction.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_YEU_CAU_QUYET_TOAN_PHI && Validator.isNotNull(payment)) {
			Long feeAmount = 0l, serviceAmount = 0l, shipAmount = 0l;
			String paymentNote = StringPool.BLANK;
			long advanceAmount = 0l;
			//long paymentAmount = 0l;
			String epaymentProfile = StringPool.BLANK;
			String bankInfo = StringPool.BLANK;
			int paymentStatus = 0;
			String paymentMethod = StringPool.BLANK;
			NumberFormat fmt = NumberFormat.getNumberInstance(LocaleUtil.getDefault());
			DecimalFormatSymbols customSymbol = new DecimalFormatSymbols();
			customSymbol.setDecimalSeparator(',');
			customSymbol.setGroupingSeparator('.');
			((DecimalFormat)fmt).setDecimalFormatSymbols(customSymbol);
			fmt.setGroupingUsed(true);
			
			try {
				JSONObject paymentObj = JSONFactoryUtil.createJSONObject(payment);
				if (paymentObj.has("paymentNote")) {
					paymentNote = paymentObj.getString("paymentNote");
				}
				if (paymentObj.has("feeAmount")) {
					feeAmount = (Long)fmt.parse(paymentObj.getString("feeAmount"));
				}
				if (paymentObj.has("serviceAmount")) {
					serviceAmount = (Long)fmt.parse(paymentObj.getString("serviceAmount"));
				}
				if (paymentObj.has("shipAmount")) {
					shipAmount = (Long)fmt.parse(paymentObj.getString("shipAmount"));
				}
				if (paymentObj.has("requestPayment")) {
					paymentStatus = paymentObj.getInt("requestPayment");
				}
				if (paymentObj.has("advanceAmount")) {
					advanceAmount = (Long)fmt.parse(paymentObj.getString("advanceAmount"));
				}
				
				JSONObject paymentObj2 = JSONFactoryUtil.createJSONObject(proAction.getPaymentFee());
				if (paymentObj2.has("paymentFee")) {
					paymentFee = paymentObj2.getString("paymentFee");
				}
			}
			catch (JSONException e) {
				_log.debug(e);
			} catch (ParseException e) {
				_log.debug(e);
			}
			PaymentFile oldPaymentFile = paymentFileLocalService.getByDossierId(groupId, dossier.getDossierId());
			
			if (oldPaymentFile != null) {
				if (Validator.isNotNull(paymentNote))
					oldPaymentFile.setPaymentNote(paymentNote);
				try {
					PaymentFile paymentFile = paymentFileLocalService.updateApplicantFeeAmount(
							oldPaymentFile.getPaymentFileId(), proAction.getRequestPayment(), feeAmount, serviceAmount,
							shipAmount, paymentNote, dossier.getOriginality());
					String generatorPayURL = PaymentUrlGenerator.generatorPayURL(groupId,
							paymentFile.getPaymentFileId(), paymentFee, dossier.getDossierId());
					JSONObject epaymentProfileJsonNew = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile());
					epaymentProfileJsonNew.put("keypayUrl", generatorPayURL);
					paymentFileLocalService.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(), epaymentProfileJsonNew.toJSONString(),
							context);
				} catch (IOException e) {
					_log.error(e);
				}
				catch (JSONException e) {
					_log.debug(e);
				}
			} else {
				long paymentAmount = feeAmount + serviceAmount + shipAmount - advanceAmount;
				
				PaymentFile paymentFile = paymentFileLocalService.createPaymentFiles(userId, groupId,
							dossier.getDossierId(), dossier.getReferenceUid(), paymentFee, advanceAmount, feeAmount,
							serviceAmount, shipAmount, paymentAmount, paymentNote, epaymentProfile, bankInfo,
							paymentStatus, paymentMethod, context);
					
				long counterPaymentFile = CounterLocalServiceUtil.increment(PaymentFile.class.getName() + "paymentFileNo");
					
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				int prefix = cal.get(Calendar.YEAR);
					
				String invoiceNo = Integer.toString(prefix) + String.format("%010d", counterPaymentFile);
					
				paymentFile.setInvoiceNo(invoiceNo);
					
				PaymentConfig paymentConfig = paymentConfigLocalService.getPaymentConfigByGovAgencyCode(groupId,
							dossier.getGovAgencyCode());
					
				if (Validator.isNotNull(paymentConfig)) {
					paymentFile.setInvoiceTemplateNo(paymentConfig.getInvoiceTemplateNo());
					paymentFile.setGovAgencyTaxNo(paymentConfig.getGovAgencyTaxNo());
					paymentFile.setGovAgencyCode(paymentConfig.getGovAgencyCode());
					paymentFile.setGovAgencyName(paymentConfig.getGovAgencyName());
				}
					
				paymentFileLocalService.updatePaymentFile(paymentFile);
				JSONObject epaymentConfigJSON = paymentConfig != null ? JSONFactoryUtil.createJSONObject(paymentConfig.getEpaymentConfig()) : JSONFactoryUtil.createJSONObject();
				JSONObject epaymentProfileJSON = JSONFactoryUtil.createJSONObject();

				if (epaymentConfigJSON.has("paymentKeypayDomain")) {
					try {
						String generatorPayURL = PaymentUrlGenerator.generatorPayURL(groupId,
								paymentFile.getPaymentFileId(), paymentFee, dossier.getDossierId());
							epaymentProfileJSON.put("keypayUrl", generatorPayURL);

							String pattern1 = "good_code=";
							String pattern2 = "&";

							String regexString = Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2);

							Pattern p = Pattern.compile(regexString);
							Matcher m = p.matcher(generatorPayURL);

							if (m.find()) {
								String goodCode = m.group(1);

								epaymentProfileJSON.put("keypayGoodCode", goodCode);
							} else {
								epaymentProfileJSON.put("keypayGoodCode", StringPool.BLANK);
							}

							epaymentProfileJSON.put("keypayMerchantCode", epaymentConfigJSON.get("paymentMerchantCode"));
							epaymentProfileJSON.put("bank", "true");
							epaymentProfileJSON.put("paygate", "true");
							epaymentProfileJSON.put("serviceAmount", serviceAmount);
							epaymentProfileJSON.put("paymentNote", paymentNote);
							epaymentProfileJSON.put("paymentFee", paymentFee);
							paymentFileLocalService.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(), epaymentProfileJSON.toJSONString(),
									context);

						} catch (IOException e) {
							_log.error(e);
						}

					} else {
						paymentFileLocalService.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(), epaymentProfileJSON.toJSONString(),
								context);
					}
				}
		} else if (proAction.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_XAC_NHAN_HOAN_THANH_THU_PHI) {
			String CINVOICEUrl = "postal/invoice";
			
			JSONObject resultObj = null;
			Map<String, Object> params = new HashMap<>();
			PaymentFile oldPaymentFile = paymentFileLocalService.getByDossierId(groupId, dossier.getDossierId());
			int intpaymentMethod = 0;
			if (Validator.isNotNull(proAction.getPreCondition())) {
				intpaymentMethod = checkPaymentMethodinPrecondition(proAction.getPreCondition());
			}
			
			if (oldPaymentFile != null && proAction.getPreCondition().toLowerCase().contains("sendinvoice=1")){
				params = createParamsInvoice(oldPaymentFile, dossier, intpaymentMethod);
				InvokeREST callRest = new InvokeREST();
				String baseUrl = RESTFulConfiguration.SERVER_PATH_BASE;
				HashMap<String, String> properties = new HashMap<String, String>();
				
				resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, "application/json", baseUrl,
						CINVOICEUrl, "", "", properties, params, context);
				
			}
			
			if (Validator.isNotNull(oldPaymentFile) ) {
				String paymentMethod = "";
				if (intpaymentMethod != 0) {
					paymentMethod = checkPaymentMethod(intpaymentMethod);
				}
				if(Validator.isNotNull(resultObj)) {
					oldPaymentFile.setEinvoice(resultObj.toString());
					oldPaymentFile.setInvoicePayload(params.toString());
					if (Validator.isNotNull(paymentMethod)) {
						oldPaymentFile.setPaymentMethod(paymentMethod);
					}
				}
				
				if (Validator.isNotNull(payment)) {
					try {
						JSONObject paymentObj = JSONFactoryUtil.createJSONObject(payment);
						if (paymentObj.has("paymentNote")) {
							oldPaymentFile.setPaymentNote(paymentObj.getString("paymentNote"));
							String epaymentProfile = oldPaymentFile.getEpaymentProfile();
							if (Validator.isNotNull(epaymentProfile)) {
								JSONObject jsonEpayment = JSONFactoryUtil.createJSONObject(epaymentProfile);
								jsonEpayment.put("paymentNote", paymentObj.getString("paymentNote"));
								oldPaymentFile.setEpaymentProfile(jsonEpayment.toJSONString());
							}
						}
					} catch (JSONException e) {
						_log.debug(e);
					}
					
				}

				oldPaymentFile.setPaymentStatus(proAction.getRequestPayment());
				paymentFileLocalService.updatePaymentFile(oldPaymentFile);
			}
		} else if (proAction.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_BAO_DA_NOP_PHI) {
			PaymentFile oldPaymentFile = paymentFileLocalService.getByDossierId(groupId, dossier.getDossierId());
			int intpaymentMethod = checkPaymentMethodinPrecondition(proAction.getPreCondition());
			String paymentMethod = checkPaymentMethod(intpaymentMethod);
			if (oldPaymentFile != null) {
				oldPaymentFile.setPaymentStatus(proAction.getRequestPayment());
				oldPaymentFile.setPaymentMethod(paymentMethod);
				
				paymentFileLocalService.updatePaymentFile(oldPaymentFile);
			}
		}
	}
	
	private void createNotificationSMS(long userId, long groupId, Dossier dossier, JSONArray assignedUsers, DossierAction dossierAction, ServiceContext context) {
//		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		User u = UserLocalServiceUtil.fetchUser(userId);
        JSONObject payloadObj = JSONFactoryUtil.createJSONObject();
        try {		
        	payloadObj.put(
					"Dossier", JSONFactoryUtil.createJSONObject(
						JSONFactoryUtil.looseSerialize(dossier)));
        	
        	if (dossierAction != null) {
        		payloadObj.put("actionCode", dossierAction.getActionCode());
        		payloadObj.put("actionUser", dossierAction.getActionUser());
        		payloadObj.put("actionName", dossierAction.getActionName());
        		payloadObj.put("actionNote", dossierAction.getActionNote());
        	}
        }
        catch (Exception e) {
        	_log.error(e);
        }

		Notificationtemplate emplTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, "EMPL-03");
		Date now = new Date();
        Calendar calEmpl = Calendar.getInstance();
        calEmpl.setTime(now);
        
        if (emplTemplate != null) {
			if ("minutely".equals(emplTemplate.getInterval())) {
				calEmpl.add(Calendar.MINUTE, emplTemplate.getExpireDuration());					
			}
			else if ("hourly".equals(emplTemplate.getInterval())) {
				calEmpl.add(Calendar.HOUR, emplTemplate.getExpireDuration());										
			}
			else {
				calEmpl.add(Calendar.MINUTE, emplTemplate.getExpireDuration());										
			}
			Date expired = calEmpl.getTime();
	
			if (dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA
					|| dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) {
				try {
					String stepCode = dossierAction.getStepCode();
					StringBuilder buildX = new StringBuilder(stepCode);
					if (stepCode.length() > 0) {
						buildX.setCharAt(stepCode.length() - 1, 'x');					
					}
					String stepCodeX = buildX.toString();
					
					StepConfig stepConfig = StepConfigLocalServiceUtil.getByCode(groupId, dossierAction.getStepCode());
					StepConfig stepConfigX = StepConfigLocalServiceUtil.getByCode(groupId, stepCodeX);
					if ((stepConfig != null && stepConfig.getStepType() == StepConfigTerm.STEP_TYPE_DISPLAY_MENU_BY_PROCESSED)
							|| (stepConfigX != null && stepConfigX.getStepType() == StepConfigTerm.STEP_TYPE_DISPLAY_MENU_BY_PROCESSED)) {
						for (int n = 0; n < assignedUsers.length(); n++) {
							JSONObject subUser = assignedUsers.getJSONObject(n);
							if (subUser != null && subUser.has(DossierActionUserTerm.ASSIGNED)
									&& subUser.getInt(DossierActionUserTerm.ASSIGNED) == DossierActionUserTerm.ASSIGNED_TH) {
								long userIdAssigned = subUser.getLong("userId");
								Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userIdAssigned);
								if (employee != null) {
									String telNo = employee != null ? employee.getTelNo() : StringPool.BLANK;
									String fullName = employee != null ? employee.getFullName() : StringPool.BLANK;
									NotificationQueueLocalServiceUtil.addNotificationQueue(
											userId, groupId, 
											"EMPL-03", 
											Dossier.class.getName(), 
											String.valueOf(dossier.getDossierId()), 
											payloadObj.toJSONString(), 
											u.getFullName(), 
											fullName, 
											userIdAssigned, 
											employee.getEmail(), 
											telNo, 
											now, 
											expired, 
											context);
								}								
							}
						}
					}
				} catch (NoSuchUserException e) {
					_log.error(e);
					//_log.error(e);
	//				e.printStackTrace();
				}
			}	
        }
	}
	
	private void createSubcription(long userId, long groupId, Dossier dossier, ActionConfig actionConfig, DossierAction dossierAction, ServiceContext context) {
		if (actionConfig != null && Validator.isNotNull(actionConfig.getNotificationType())) {
//			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
//			User u = UserLocalServiceUtil.fetchUser(userId);
			
			Notificationtemplate notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, actionConfig.getNotificationType());
			if (notiTemplate != null) {
				if (actionConfig.getDocumentType().startsWith("APLC")) {
					if (dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA
							|| dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) {
					}
				}
				else if (actionConfig.getDocumentType().startsWith("EMPL")) {
					if ((dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA
							|| dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG)
							&& dossierAction != null) {
						StepConfig stepConfig = stepConfigLocalService.getByCode(groupId, dossierAction.getStepCode());
						List<DossierActionUser> lstDaus = dossierActionUserLocalService.getByDID_DAI_SC_AS(dossier.getDossierId(), dossierAction.getDossierActionId(), dossierAction.getStepCode(), new int[] { 1, 2 });
						if ("EMPL-01".equals(actionConfig.getDocumentType())
								&& stepConfig != null && "1".equals(stepConfig.getStepType())) {
							for (DossierActionUser dau : lstDaus) {
								try {
									SubscriptionLocalServiceUtil.addSubscription(dau.getUserId(), groupId, "EMPL-01", 0);
								} catch (PortalException e) {
									_log.debug(e);
								}
							}
						}
					}					
				}
				else if (actionConfig.getDocumentType().startsWith("USER")) {
					
				}
			}
		}		
	}
	
	private List<String> _putPaymentMessage(String pattern) {
		List<String> lsDesc = new ArrayList<String>();

		lsDesc.add(0, StringPool.BLANK);
		lsDesc.add(1, StringPool.BLANK);
		lsDesc.add(2, StringPool.BLANK);
		lsDesc.add(3, StringPool.BLANK);
		lsDesc.add(4, StringPool.BLANK);

		List<String> lsMsg = DossierPaymentUtils.getMessagePayment(pattern);

		for (int i = 0; i < lsMsg.size(); i++) {
			lsDesc.set(1, lsMsg.get(i));
		}
		return lsDesc;
	}
	
	private long _genetatorTransactionId() {

		long transactionId = 0;
		try {
			transactionId = counterLocalService.increment(PaymentFile.class.getName() + ".genetatorTransactionId");
		} catch (SystemException e) {
			_log.error(e);
		}
		return transactionId;
	}
	
	private String generatorPayURL(long groupId, long paymentFileId, String pattern,
			Dossier dossier) throws IOException {
		String result = "";
		try {
			PaymentFile paymentFile = paymentFileLocalService.getPaymentFile(paymentFileId);
			PaymentConfig paymentConfig = paymentConfigLocalService.getPaymentConfigByGovAgencyCode(groupId,
					dossier.getGovAgencyCode());

			if (Validator.isNotNull(paymentConfig)) {
				List<String> lsMessages = _putPaymentMessage(pattern);

				long merchant_trans_id = _genetatorTransactionId();

				JSONObject epaymentConfigJSON = JSONFactoryUtil.createJSONObject(paymentConfig.getEpaymentConfig());

				String merchant_code = epaymentConfigJSON.getString("paymentMerchantCode");

				String good_code = generatorGoodCode(10);
				
				String net_cost = String.valueOf(paymentFile.getPaymentAmount());
				
				String ship_fee = "0";
				String tax = "0";

				String bank_code = StringPool.BLANK;

				String service_code = epaymentConfigJSON.getString("paymentServiceCode");
				String version = epaymentConfigJSON.getString("paymentVersion");
				String command = epaymentConfigJSON.getString("paymentCommand");
				String currency_code = epaymentConfigJSON.getString("paymentCurrencyCode");

				String desc_1 = StringPool.BLANK;
				String desc_2 = StringPool.BLANK;
				String desc_3 = StringPool.BLANK;
				String desc_4 = StringPool.BLANK;
				String desc_5 = StringPool.BLANK;

				if (lsMessages.size() > 0) {
					desc_1 = lsMessages.get(0);
					desc_2 = lsMessages.get(1);
					desc_3 = lsMessages.get(2);
					desc_4 = lsMessages.get(3);
					desc_5 = lsMessages.get(4);

					if (desc_1.length() >= 20) {
						desc_1 = desc_1.substring(0, 19);
					}
					if (desc_2.length() >= 30) {
						desc_2 = desc_2.substring(0, 29);
					}
					if (desc_3.length() >= 40) {
						desc_3 = desc_3.substring(0, 39);
					}
					if (desc_4.length() >= 100) {
						desc_4 = desc_4.substring(0, 89);
					}
					if (desc_5.length() > 15) {
						desc_5 = desc_5.substring(0, 15);

						if (!Validator.isDigit(desc_5)) {
							desc_5 = StringPool.BLANK;
						}
					}
				}

				String xml_description = StringPool.BLANK;
				String current_locale = epaymentConfigJSON.getString("paymentCurrentLocale");
				String country_code = epaymentConfigJSON.getString("paymentCountryCode");
				String internal_bank = epaymentConfigJSON.getString("paymentInternalBank");

				String merchant_secure_key = epaymentConfigJSON.getString("paymentMerchantSecureKey");

				// dossier = _getDossier(dossierId);

				// TODO : update returnURL keyPay

				String return_url;
				_log.info("SONDT GENURL paymentReturnUrl ====================== "+ JSONFactoryUtil.looseSerialize(epaymentConfigJSON));
//				return_url = epaymentConfigJSON.getString("paymentReturnUrl")+ "/" + dossier.getReferenceUid() + "/" + paymentFile.getReferenceUid();
				return_url = epaymentConfigJSON.getString("paymentReturnUrl")+ "&dossierId=" + dossier.getDossierId() + "&goodCode=" + good_code + "&transId=" + merchant_trans_id + "&referenceUid=" + dossier.getReferenceUid();
				_log.info("SONDT GENURL paymentReturnUrl ====================== "+ return_url);
				// http://119.17.200.66:2681/web/bo-van-hoa/dich-vu-cong/#/thanh-toan-thanh-cong?paymentPortal=KEYPAY&dossierId=77603&goodCode=123&transId=555
				KeyPay keypay = new KeyPay(String.valueOf(merchant_trans_id), merchant_code, good_code, net_cost,
						ship_fee, tax, bank_code, service_code, version, command, currency_code, desc_1, desc_2, desc_3,
						desc_4, desc_5, xml_description, current_locale, country_code, return_url, internal_bank,
						merchant_secure_key);

				// keypay.setKeypay_url(paymentConfig.getKeypayDomain());

				StringBuffer param = new StringBuffer();
				param.append("merchant_code=").append(URLEncoder.encode(keypay.getMerchant_code(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("merchant_secure_key=").append(URLEncoder.encode(keypay.getMerchant_secure_key(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("bank_code=").append(URLEncoder.encode(keypay.getBank_code(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("internal_bank=").append(URLEncoder.encode(keypay.getInternal_bank(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("merchant_trans_id=").append(URLEncoder.encode(keypay.getMerchant_trans_id(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("good_code=").append(URLEncoder.encode(keypay.getGood_code(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("net_cost=").append(URLEncoder.encode(keypay.getNet_cost(), "UTF-8") )
						.append(StringPool.AMPERSAND);
				param.append("ship_fee=").append(URLEncoder.encode(keypay.getShip_fee(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("tax=").append(URLEncoder.encode(keypay.getTax(), "UTF-8")).append(StringPool.AMPERSAND);
				param.append("return_url=").append(URLEncoder.encode(keypay.getReturn_url(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("version=").append(URLEncoder.encode(keypay.getVersion(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("command=").append(URLEncoder.encode(keypay.getCommand(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("current_locale=").append(URLEncoder.encode(keypay.getCurrent_locale(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("currency_code=").append(URLEncoder.encode(keypay.getCurrency_code(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("service_code=").append(URLEncoder.encode(keypay.getService_code(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("country_code=").append(URLEncoder.encode(keypay.getCountry_code(), "UTF-8"))
						.append(StringPool.AMPERSAND);

				param.append("desc_1=").append(URLEncoder.encode(keypay.getDesc_1(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("desc_2=").append(URLEncoder.encode(keypay.getDesc_2(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("desc_3=").append(URLEncoder.encode(keypay.getDesc_3(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("desc_4=").append(URLEncoder.encode(keypay.getDesc_4(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("desc_5=").append(URLEncoder.encode(keypay.getDesc_5(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("xml_description=").append(URLEncoder.encode(keypay.getXml_description(), "UTF-8"))
						.append(StringPool.AMPERSAND);
				param.append("secure_hash=").append(keypay.getSecure_hash());

				result = epaymentConfigJSON.getString("paymentKeypayDomain") + StringPool.QUESTION + param.toString();

			}

		} catch (NoSuchPaymentFileException e) {
			_log.debug(e);
		} catch (Exception e) {
			_log.debug(e);
		}

		return result;
	}
	
	private Map<String, Object> createParamsInvoice(PaymentFile oldPaymentFile, Dossier dossier, int intpaymentMethod) {
		Map<String, Object> params = new HashMap<>();
		
		StringBuilder address = new StringBuilder();
		address.append(dossier.getAddress());address.append(", ");
		address.append(dossier.getWardName());address.append(", ");
		address.append(dossier.getDistrictName());address.append(", ");
		address.append(dossier.getCityName());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		String dateformatted = sdf.format(new Date());
		_log.info("SONDT CINVOICE DATEFORMATED ============= " + dateformatted);
		
		params.put("userName", "HA");	
		params.put("passWord", "1"); 	    	
		params.put("soid", "0"); 
		params.put("maHoadon", "01GTKT0/001"); 
		params.put("ngayHd", dateformatted); //"01/08/2018"
		params.put("seri", "12314"); 
		params.put("maNthue", "01"); 
		params.put("kieuSo", "G"); 
		params.put("maKhackHang", Long.toString(dossier.getUserId()));
		params.put("ten", dossier.getApplicantName()); 
		params.put("phone", dossier.getContactTelNo());
		if(dossier.getApplicantIdType().contentEquals("business")) {
			params.put("tax", dossier.getApplicantIdNo()); 
		} else {
			params.put("tax", "");
		}
		params.put("dchi", address); 
		params.put("maTk", ""); 
		params.put("tenNh", ""); 
		params.put("mailH", GetterUtil.getString(dossier.getContactEmail()));
		params.put("phoneH", GetterUtil.getString(dossier.getContactTelNo()));
		params.put("tenM", GetterUtil.getString(dossier.getDelegateName()));
		params.put("maKhL", "K");
		params.put("maNt", "VND");
		params.put("tg", "1");
		if(intpaymentMethod == 3) {
			params.put("hthuc", "M");
		}else {
			params.put("hthuc", "C");
		}
		params.put("han", "");
		params.put("tlGgia", "0");
		params.put("ggia", "0");
		params.put("phi", "0");
		params.put("noidung", dossier.getDossierNo());
		params.put("tien", Long.toString(oldPaymentFile.getPaymentAmount()));
		params.put("ttoan", Long.toString(oldPaymentFile.getPaymentAmount()));
		params.put("maVtDetail", dossier.getDossierNo());
		params.put("tenDetail", GetterUtil.getString(dossier.getServiceName()));
		params.put("dvtDetail", "bo");
		params.put("luongDetail", "1");
		params.put("giaDetail", Long.toString(oldPaymentFile.getPaymentAmount()));
		params.put("tienDetail", Long.toString(oldPaymentFile.getPaymentAmount()));
		params.put("tsDetail", "0");
		params.put("thueDetail", "0");
		params.put("ttoanDetail", Long.toString(oldPaymentFile.getPaymentAmount()));
		
		return params;
	}
	
	private int checkPaymentMethodinPrecondition(String preCondition) {
		int paymentMethod = 0;
		String[] preConditions = StringUtil.split(preCondition);
		for(String pre : preConditions) {
			pre = pre.trim();
			if (pre.toLowerCase().contains("paymentmethod=")) {
				String[] splitPaymentMethod = pre.split("=");
				if (splitPaymentMethod.length == 2) {
					paymentMethod = Integer.parseInt(splitPaymentMethod[1]);
				}
				break;
			}
		}
		return paymentMethod;
	}

	private String checkPaymentMethod(int mt) {
		String pmMethod = "";
		if (mt == 1) {
			pmMethod = "Chuyển khoản"; //KeyPay
		} else if (mt == 2) {
			pmMethod = "Chuyển khoản";
		} else if (mt == 3) {
			pmMethod = "Tiền mặt";
		}
		
		return pmMethod;
	}
	
	private JSONObject getStatusText(long groupId, String collectionCode, String curStatus, String curSubStatus) {

		JSONObject jsonData = null;
		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

		if (Validator.isNotNull(dc) && Validator.isNotNull(curStatus)) {
			jsonData = JSONFactoryUtil.createJSONObject();
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(curStatus, dc.getPrimaryKey(), groupId);
			if (Validator.isNotNull(it)) {
				jsonData.put(curStatus, it.getItemName());
				if (Validator.isNotNull(curSubStatus)) {
					DictItem dItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(curSubStatus, dc.getPrimaryKey(),
							groupId);
					if (Validator.isNotNull(dItem)) {
						jsonData.put(curSubStatus, dItem.getItemName());
					}
				}
			}
		}

		return jsonData;
	}
	
	private int getActionDueDate(long groupId, long dossierId, String refId, long processActionId) {
		return 0;
	}
	
	protected String getDictItemName(long groupId, String collectionCode, String itemCode) {

		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

		if (Validator.isNotNull(dc)) {
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dc.getPrimaryKey(), groupId);
			if(Validator.isNotNull(it)){
				return it.getItemName();
			}else{
				return StringPool.BLANK;
			}
		} else {
			return StringPool.BLANK;
		}

	}
	
	private void updateStatus(Dossier dossier, String status, String statusText, String subStatus,
			String subStatusText, String lockState, String stepInstruction, ServiceContext context)
			throws PortalException {

		Date now = new Date();

		dossier.setModifiedDate(now);

		dossier.setDossierStatus(status);
		dossier.setDossierStatusText(statusText);
		dossier.setDossierSubStatus(subStatus);
		dossier.setDossierSubStatusText(subStatusText);
		dossier.setLockState(lockState);
		dossier.setDossierNote(stepInstruction);

		if (status.equalsIgnoreCase(DossierStatusConstants.RELEASING)) {
			dossier.setReleaseDate(now);
		}

		if (status.equalsIgnoreCase(DossierStatusConstants.DONE)) {
			dossier.setFinishDate(now);
		}
	}		
	
	private Map<String, Boolean> updateProcessingDate(DossierAction dossierAction, DossierAction prevAction, ProcessStep processStep, Dossier dossier, String curStatus, String curSubStatus, String prevStatus, 
			ActionConfig actionConfig,
			ProcessOption option,
			ServiceProcess serviceProcess,
			ServiceContext context) {
		Date now = new Date();
		Map<String, Boolean> bResult = new HashMap<>();
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		params.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
		params.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
		params.put(DossierTerm.DOSSIER_TEMPLATE_NO, dossier.getDossierTemplateNo());
		params.put(DossierTerm.DOSSIER_STATUS, StringPool.BLANK);
//		ServiceProcess serviceProcess =  null;
//		
//		long serviceProcessId = (option != null ? option.getServiceProcessId() : prevAction.getServiceProcessId());
//		serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);
		
//		if ((Validator.isNull(prevStatus) && DossierTerm.DOSSIER_STATUS_NEW.equals(curStatus)
//				&& (dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA))
		if ((DossierTerm.DOSSIER_STATUS_RECEIVING.equals(curStatus) || DossierTerm.DOSSIER_STATUS_NEW.equals(curStatus))
				&& dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) {

			try {
				if (Validator.isNotNull(option) && Validator.isNull(dossier.getDossierNo())) {
					String dossierRef = DossierNumberGenerator.generateDossierNumber(dossier.getGroupId(), dossier.getCompanyId(),
							dossier.getDossierId(), option.getProcessOptionId(), serviceProcess.getDossierNoPattern(), params);

					dossier.setDossierNo(dossierRef.trim());
					
//					DossierLocalServiceUtil.updateDossier(dossier);
					
					bResult.put(DossierTerm.DOSSIER_NO, true);					
				}
			} catch (PortalException e) {
				_log.debug(e);
				//_log.error(e);
//				e.printStackTrace();
			}		
		}
		
		if ((DossierTerm.DOSSIER_STATUS_NEW.equals(prevStatus)
				&& DossierTerm.DOSSIER_STATUS_RECEIVING.equals(curStatus)) || 
				(dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA && DossierTerm.DOSSIER_STATUS_NEW.equals(curStatus))) {
//			try {
//				DossierLocalServiceUtil.updateSubmittingDate(dossier.getGroupId(), dossier.getDossierId(), dossier.getReferenceUid(), now, context);
				if (Validator.isNull(dossier.getSubmitDate())) {
					dossier.setSubmitDate(now);
					bResult.put(DossierTerm.SUBMIT_DATE, true);					
				}
//			} catch (PortalException e) {
//				_log.error(e);
//				e.printStackTrace();
//			}
		}
		if (dossier.getOriginality() != DossierTerm.ORIGINALITY_DVCTT &&
				((DossierTerm.DOSSIER_STATUS_PROCESSING.equals(curStatus) && dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG)
				|| (DossierTerm.DOSSIER_STATUS_NEW.equals(curStatus) && dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA)
				|| (DossierTerm.DOSSIER_STATUS_NEW.equals(curStatus) && dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG)
				|| (actionConfig != null && actionConfig.getDateOption() == 2))
				&& dossier.getReceiveDate() == null) {
//			try {
//				DossierLocalServiceUtil.updateReceivingDate(dossier.getGroupId(), dossier.getDossierId(), dossier.getReferenceUid(), now, context);
				dossier.setReceiveDate(now);
				bResult.put(DossierTerm.RECEIVE_DATE, true);

				Double durationCount = serviceProcess.getDurationCount();
				int durationUnit = serviceProcess.getDurationUnit();
				Date dueDate = null;
				if (Validator.isNotNull(durationCount) && durationCount > 0
						&& !areEqualDouble(durationCount, 0.00d, 3)) {
					dueDate = HolidayUtils.getDueDate(now, durationCount, durationUnit, dossier.getGroupId());
				}
				
				if (Validator.isNotNull(dueDate)) {
					dossier.setDueDate(dueDate);
//					DossierLocalServiceUtil.updateDueDate(dossier.getGroupId(), dossier.getDossierId(), dossier.getReferenceUid(), dueDate, context);					
					bResult.put(DossierTerm.DUE_DATE, true);
				}
				
				dossier.setDurationCount(durationCount);
				dossier.setDurationUnit(durationUnit);
	
				if (dossier.getCounter() == 0 && Validator.isNotNull(dossier.getRegisterBookCode())) {
					long counterCode = DossierNumberGenerator.countByRegiterBookCode(dossier.getGroupId(),
							dossier.getRegisterBookCode());
					dossier.setCounter(counterCode);
				}

				if (Validator.isNull(dossier.getDossierNo())) {
					try {
						String dossierRef = DossierNumberGenerator.generateDossierNumber(dossier.getGroupId(), dossier.getCompanyId(),
								dossier.getDossierId(), option.getProcessOptionId(), serviceProcess.getDossierNoPattern(), params);
						dossier.setDossierNo(dossierRef.trim());
					} catch (Exception e) {
						_log.debug(e);
					}
				}
				
//				dossier = DossierLocalServiceUtil.updateDossier(dossier);
//			} catch (PortalException e) {
//				_log.error(e);
//				e.printStackTrace();
//			}
		}

		//Update counter and dossierNo
		if (actionConfig != null && actionConfig.getDateOption() == 2) {

			if (dossier.getCounter() == 0 && Validator.isNotNull(dossier.getRegisterBookCode())) {
				long counterCode = DossierNumberGenerator.countByRegiterBookCode(dossier.getGroupId(),
						dossier.getRegisterBookCode());
				dossier.setCounter(counterCode);
			}

			if (Validator.isNull(dossier.getDossierNo())) {
				try {
					String dossierRef = DossierNumberGenerator.generateDossierNumber(dossier.getGroupId(), dossier.getCompanyId(),
							dossier.getDossierId(), option.getProcessOptionId(), serviceProcess.getDossierNoPattern(),
							params);
					dossier.setDossierNo(dossierRef.trim());
				} catch (Exception e) {
					_log.debug(e);
				}
			}
		}
		
		if (DossierTerm.DOSSIER_STATUS_RECEIVING.equals(prevStatus) && DossierTerm.DOSSIER_STATUS_PROCESSING.equals(curStatus)) {	
//			try {
//				DossierLocalServiceUtil.updateProcessDate(dossier.getGroupId(), dossier.getDossierId(), dossier.getReferenceUid(), now, context);
				dossier.setProcessDate(now);
				bResult.put(DossierTerm.PROCESS_DATE, true);
//			} catch (PortalException e) {
//				_log.error(e);
//				e.printStackTrace();
//			}
		}
//		if (DossierTerm.DOSSIER_STATUS_RELEASING.equals(curStatus)
//				|| DossierTerm.DOSSIER_STATUS_DENIED.equals(curStatus)
//				|| DossierTerm.DOSSIER_STATUS_UNRESOLVED.equals(curStatus)
//				|| DossierTerm.DOSSIER_STATUS_CANCELLED.equals(curStatus)
//				|| DossierTerm.DOSSIER_STATUS_DONE.equals(curStatus)) {
		if (DossierTerm.DOSSIER_STATUS_RELEASING.equals(curStatus)
				|| DossierTerm.DOSSIER_STATUS_UNRESOLVED.equals(curStatus)
				|| DossierTerm.DOSSIER_STATUS_CANCELLED.equals(curStatus)
				|| (actionConfig != null && actionConfig.getDateOption() == 4)
				) {
			if (Validator.isNull(dossier.getReleaseDate())) {
//				try {
//					DossierLocalServiceUtil.updateReleaseDate(dossier.getGroupId(), dossier.getDossierId(), dossier.getReferenceUid(), now, context);
					dossier.setReleaseDate(now);
					bResult.put(DossierTerm.RELEASE_DATE, true);
//				} catch (PortalException e) {
//					_log.error(e);
//					e.printStackTrace();
//				}				
			}
		}
//		_log.info("========STEP DUE CUR STATUS: " + curStatus);
		if (DossierTerm.DOSSIER_STATUS_DENIED.equals(curStatus)
				|| DossierTerm.DOSSIER_STATUS_UNRESOLVED.equals(curStatus)
				|| DossierTerm.DOSSIER_STATUS_CANCELLED.equals(curStatus)
				|| DossierTerm.DOSSIER_STATUS_DONE.equals(curStatus)) {
//			_log.info("========STEP DUE CUR STATUS UPDATING STATE DONE");
//			dossierAction.setState(DossierActionTerm.STATE_ALREADY_PROCESSED);
//			dossierAction.setModifiedDate(new Date());
			dossierAction = dossierActionLocalService.updateState(dossierAction.getDossierActionId(), DossierActionTerm.STATE_ALREADY_PROCESSED);								
		}
		
//		if (DossierTerm.DOSSIER_STATUS_DENIED.equals(curStatus)
//				|| DossierTerm.DOSSIER_STATUS_UNRESOLVED.equals(curStatus)
//				|| DossierTerm.DOSSIER_STATUS_CANCELLED.equals(curStatus)
//				|| DossierTerm.DOSSIER_STATUS_DONE.equals(curStatus)) {
		if (DossierTerm.DOSSIER_STATUS_DENIED.equals(curStatus)
				|| DossierTerm.DOSSIER_STATUS_DONE.equals(curStatus)
				|| DossierTerm.DOSSIER_STATUS_UNRESOLVED.equals(curStatus)
				|| DossierTerm.DOSSIER_STATUS_CANCELLED.equals(curStatus)				
				|| (actionConfig != null && actionConfig.getDateOption() == 5)) {
			if (Validator.isNull(dossier.getFinishDate())) {
//				try {
//					DossierLocalServiceUtil.updateFinishDate(dossier.getGroupId(), dossier.getDossierId(), dossier.getReferenceUid(), now, context);
					dossier.setFinishDate(now);
					bResult.put(DossierTerm.FINISH_DATE, true);
//					dossierAction.setState(DossierActionTerm.STATE_ALREADY_PROCESSED);
//					dossierAction.setModifiedDate(new Date());
					dossierAction = dossierActionLocalService.updateState(dossierAction.getDossierActionId(), DossierActionTerm.STATE_ALREADY_PROCESSED);					
//				} catch (PortalException e) {
//					_log.error(e);
//					e.printStackTrace();
//				}				
			}
			if (Validator.isNull(dossier.getReleaseDate())) {
//				try {
//					DossierLocalServiceUtil.updateReleaseDate(dossier.getGroupId(), dossier.getDossierId(), dossier.getReferenceUid(), now, context);
					dossier.setReleaseDate(now);
					bResult.put(DossierTerm.RELEASE_DATE, true);
//				} catch (PortalException e) {
//					_log.error(e);
//					e.printStackTrace();
//				}				
			}			
		}
		if (DossierTerm.DOSSIER_STATUS_PROCESSING.equals(curStatus)
				|| DossierTerm.DOSSIER_STATUS_INTEROPERATING.equals(curStatus)
				|| DossierTerm.DOSSIER_STATUS_WAITING.equals(curStatus)) {
			if (Validator.isNotNull(dossier.getFinishDate())) {
				dossier.setFinishDate(null);
				bResult.put(DossierTerm.FINISH_DATE, true);
			}
			if (Validator.isNotNull(dossier.getReleaseDate())) {
				dossier.setReleaseDate(null);
				bResult.put(DossierTerm.RELEASE_DATE, true);				
			}
		}
		if (DossierTerm.DOSSIER_STATUS_RECEIVING.equals(prevStatus)) {
			bResult.put(DossierTerm.DOSSIER_NO, true);
			bResult.put(DossierTerm.RECEIVE_DATE, true);
			bResult.put(DossierTerm.PROCESS_DATE, true);
			bResult.put(DossierTerm.RELEASE_DATE, true);
			bResult.put(DossierTerm.FINISH_DATE, true);
		}
		if (DossierTerm.DOSSIER_STATUS_NEW.equals(prevStatus) && dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG
				&& Validator.isNotNull(dossier.getReceiveDate())) {
			bResult.put(DossierTerm.RECEIVE_DATE, true);
		}
		
		int dateOption = actionConfig.getDateOption();
		_log.debug("dateOption: "+dateOption);
		if (dateOption == DossierTerm.DATE_OPTION_CAL_WAITING) {
			DossierAction dActEnd = dossierActionLocalService
					.fetchDossierAction(dossierAction.getDossierActionId());
//			DossierAction dActEnd = dossierAction;
			if (dActEnd != null) {
				_log.debug("dActEnd.getPreviousActionId(): "+dActEnd.getPreviousActionId());
				DossierAction dActStart = dossierActionLocalService
						.fetchDossierAction(dActEnd.getPreviousActionId());
//				DossierAction dActStart = prevAction;
				if (dActStart != null) {
					long createEnd = dActEnd.getCreateDate().getTime();
					long createStart = dActStart.getCreateDate().getTime();
					_log.debug("createStart: "+createStart);
					_log.debug("createEnd: "+createEnd);
					if (createEnd > createStart) {
						long extendDateTimeStamp = ExtendDueDateUtils.getTimeWaitingByHoliday(createStart, createEnd, dossier.getGroupId());
						_log.debug("extendDateTimeStamp: "+extendDateTimeStamp);
						if (extendDateTimeStamp > 0) {
							long hoursCount = (long) (extendDateTimeStamp / (1000 * 60 * 60));
							_log.debug("hoursCount: "+hoursCount);
							//_log.info("dossier.getExtendDate(): "+dossier.getExtendDate());
							List<Holiday> holidayList = HolidayLocalServiceUtil
									.getHolidayByGroupIdAndType(dossier.getGroupId(), 0);
							List<Holiday> extendWorkDayList = HolidayLocalServiceUtil
									.getHolidayByGroupIdAndType(dossier.getGroupId(), 1);

							Date dueDateExtend = HolidayUtils.getEndDate(dossier.getGroupId(),
									dossier.getDueDate(), hoursCount, holidayList,
									extendWorkDayList);
							_log.debug("dueDateExtend: "+dueDateExtend);
							if (dueDateExtend != null) {
								dossier.setDueDate(dueDateExtend);
								//dossier.setCorrecttingDate(null);
								bResult.put(DossierTerm.DUE_DATE, true);
							}
						}
					}
				}
			}
		} else if (dateOption == DossierTerm.DATE_OPTION_CHANGE_DUE_DATE) {
			if (dossier.getDueDate() != null) {
				//dossier.setCorrecttingDate(dossier.getDueDate());
				//dossier.setDueDate(null);
				dossier.setLockState(DossierTerm.PAUSE_STATE);
			}
		} 
		else if (dateOption == DossierTerm.DATE_OPTION_RESET_DUE_DATE) {
			dossier.setLockState(StringPool.BLANK);
			if (dossier.getDueDate() != null) {
				if (serviceProcess != null) {
					Date newDueDate = HolidayUtils.getDueDate(new Date(),
							serviceProcess.getDurationCount(),
							serviceProcess.getDurationUnit(), dossier.getGroupId());
					if (newDueDate != null) {
						dossier.setReceiveDate(new Date());
						dossier.setDueDate(newDueDate);
						bResult.put(DossierTerm.DUE_DATE, true);
					}
				}
			}
		}
		
		//Check if dossier is done
		if (DossierTerm.DOSSIER_STATUS_DONE.equals(curStatus)) {
			List<DossierFile> lstFiles = dossierFileLocalService.getAllDossierFile(dossier.getDossierId());
			for (DossierFile df : lstFiles) {
				if (!df.getRemoved()) {
					df.setOriginal(true);
				}
				dossierFileLocalService.updateDossierFile(df);
			}
		}
		
		//Calculate step due date
//		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
//		_log.info("dossierAction: "+dossierAction);
		dossierAction = dossierActionLocalService.fetchDossierAction(dossier.getDossierActionId());
		Date rootDate = now;
		Date dueDate = null;

//		if (prevAction != null) {
//			if (prevAction.getDueDate() != null) {
//				if (rootDate.getTime() < prevAction.getDueDate().getTime()) {
//					rootDate = prevAction.getDueDate();
//				}
//			}
//		}

		Double durationCount = processStep.getDurationCount();
//		_log.info("durationCountStep: "+durationCount);
		int durationUnit = serviceProcess.getDurationUnit();
		
//		_log.info("Calculate do action duration count: " + durationCount);
		if (Validator.isNotNull(durationCount) && durationCount > 0
				&& !areEqualDouble(durationCount, 0.00d, 3)) {
//			_log.info("========STEP DUE DATE CACULATE DUE DATE");
			dueDate = HolidayUtils.getDueDate(rootDate, durationCount, durationUnit, dossier.getGroupId());
//			_log.info("dueDateAction: "+dueDate);
		}		
	
//		_log.info("========STEP DUE DATE:" + dueDate);
//		DossierLocalServiceUtil.updateDossier(dossier);
		
//		_log.info("dossierAction: " + dossierAction);
		if (dossierAction != null) {
//			_log.info("========STEP DUE DATE ACTION:" + dueDate);
			if (dueDate != null) {
				long dateNowTimeStamp = now.getTime();
				Long dueDateTimeStamp = dueDate.getTime();
				int overdue = 0;
//				_log.info("dueDateTEST: "+dueDate);
//				_log.info("Due date timestamp: " + dueDateTimeStamp);
				if (dueDateTimeStamp != null && dueDateTimeStamp > 0) {
					long subTimeStamp = dueDateTimeStamp - dateNowTimeStamp;
					if (subTimeStamp > 0) {
						overdue = calculatorOverDue(durationUnit, subTimeStamp);
//						overdue = calculatorOverDue(durationUnit, subTimeStamp, dateNowTimeStamp, dueDateTimeStamp,
//								dossierAction.getGroupId(), true);
					} else {
						overdue = -calculatorOverDue(durationUnit, subTimeStamp);
////						calculatorOverDue(int durationUnit, long subTimeStamp, long releaseDateTimeStamp,
////								long dueDateTimeStamp, long groupId, true);
//						overdue = -calculatorOverDue(durationUnit, subTimeStamp, dateNowTimeStamp, dueDateTimeStamp,
//								dossierAction.getGroupId(), true);
					}
				} else {
				}
//				_log.info("dueDateTEST111: "+dueDate);
				dossierAction.setActionOverdue(overdue);
				dossierAction.setDueDate(dueDate);
//				_log.info("========STEP DUE DATE SET DUE DATE: " + dossierAction.getStepCode());
//				DossierAction dActTest = DossierActionLocalServiceUtil.updateDossierAction(dossierAction);
				dossierActionLocalService.updateDossierAction(dossierAction);
//				_log.info("dActTest: "+dActTest);
			}
			else {
				dossierActionLocalService.updateDossierAction(dossierAction);
			}
		}
	
		return bResult;
	}
	
	public static boolean areEqualDouble(double a, double b, int precision) {
		return Math.abs(a - b) <= Math.pow(10, -precision);
	}
	
	private static int calculatorOverDue(int durationUnit, long subTimeStamp) {
		if (subTimeStamp < 0) {
			subTimeStamp = Math.abs(subTimeStamp);
		}
		double dueCount;
		double overDue;
		int retval = Double.compare(durationUnit, 1.0);
		if (retval < 0) {
			dueCount = (double) subTimeStamp / VALUE_CONVERT_DATE_TIMESTAMP;
			double subDueCount = (double) Math.round(dueCount * 100) / 100;
			overDue = (double) Math.ceil(subDueCount * 4) / 4;
			return (int)overDue;
		} else {
			dueCount = (double) subTimeStamp / VALUE_CONVERT_HOUR_TIMESTAMP;
			overDue = (double) Math.round(dueCount);
		}

		return (int)overDue;
	}

	private JSONObject processMergeDossierFormData(Dossier dossier, JSONObject jsonData) {
		jsonData.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
		jsonData.put(DossierTerm.GOV_AGENCY_NAME, dossier.getGovAgencyName());
		jsonData.put(DossierTerm.APPLICANT_ID_NO, dossier.getApplicantIdNo());
		jsonData.put(DossierTerm.APPLICANT_ID_TYPE, dossier.getApplicantIdType());
		jsonData.put(DossierTerm.APPLICANT_ID_DATE,
				APIDateTimeUtils.convertDateToString(dossier.getApplicantIdDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		jsonData.put(DossierTerm.CITY_CODE, dossier.getCityCode());
		jsonData.put(DossierTerm.CITY_NAME, dossier.getCityName());
		jsonData.put(DossierTerm.DISTRICT_CODE, dossier.getDistrictCode());
		jsonData.put(DossierTerm.DISTRICT_NAME, dossier.getDistrictName());
		jsonData.put(DossierTerm.WARD_CODE, dossier.getWardCode());
		jsonData.put(DossierTerm.WARD_NAME, dossier.getWardName());
		jsonData.put(DossierTerm.DOSSIER_NO, dossier.getDossierNo());
		jsonData.put(DossierTerm.APPLICANT_NAME, dossier.getApplicantName());
		jsonData.put(DossierTerm.ADDRESS, dossier.getAddress());
		jsonData.put(DossierTerm.CONTACT_TEL_NO, dossier.getContactTelNo());
		jsonData.put(DossierTerm.CONTACT_EMAIL, dossier.getContactEmail());
		jsonData.put(DossierTerm.CONTACT_NAME, dossier.getContactName());
		jsonData.put(DossierTerm.DELEGATE_ADDRESS, dossier.getDelegateAddress());
		jsonData.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
		jsonData.put(DossierTerm.SERVICE_NAME, dossier.getServiceName());
		jsonData.put(DossierTerm.SAMPLE_COUNT, dossier.getSampleCount());
		jsonData.put(DossierTerm.DURATION_UNIT, dossier.getDurationUnit());
		jsonData.put(DossierTerm.DURATION_COUNT, dossier.getDurationCount());
		jsonData.put(DossierTerm.SECRET_KEY, dossier.getPassword());
		jsonData.put(DossierTerm.RECEIVE_DATE,
				APIDateTimeUtils.convertDateToString(dossier.getReceiveDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		jsonData.put(DossierTerm.DELEGATE_NAME, dossier.getDelegateName());
		jsonData.put(DossierTerm.DELEGATE_EMAIL, dossier.getDelegateEmail());
		jsonData.put(DossierTerm.DELEGATE_TELNO, dossier.getDelegateTelNo());
		jsonData.put(DossierTerm.DOSSIER_NAME, dossier.getDossierName());
		jsonData.put(DossierTerm.VIA_POSTAL, dossier.getViaPostal());
		jsonData.put(DossierTerm.POSTAL_ADDRESS, dossier.getPostalAddress());
		//
		Date dueDate = dossier.getDueDate();
		if (dueDate != null) {
			ServiceProcess process = serviceProcessLocalService.getByG_PNO(dossier.getGroupId(),
					dossier.getProcessNo());
			if (process != null) {
				String dueDatePattern = process.getDueDatePattern();
				//_log.info("dueDatePattern: " + dueDatePattern);
				// _log.info("START DUEDATE TEST");
				if (Validator.isNotNull(dueDatePattern)) {
					//_log.info("START DUEDATE TEST");
					// _log.info("dueDatePattern: "+dueDatePattern);
					try {
						JSONObject jsonDueDate = JSONFactoryUtil.createJSONObject(dueDatePattern);
						//_log.info("jsonDueDate: " + jsonDueDate);
						if (jsonDueDate != null) {
							JSONObject hours = jsonDueDate.getJSONObject("hour");
							JSONObject processHours = jsonDueDate.getJSONObject("processHour");
							//_log.info("hours: " + hours);
							if (hours != null && hours.has("AM") && hours.has("PM")) {
								//_log.info("AM-PM: ");
								Calendar receiveCalendar = Calendar.getInstance();
								receiveCalendar.setTime(dossier.getReceiveDate());

								Calendar dueCalendar = Calendar.getInstance();
								//_log.info("hours: " + receiveCalendar.get(Calendar.HOUR_OF_DAY));
								if (receiveCalendar.get(Calendar.HOUR_OF_DAY) < 12) {
									dueCalendar.setTime(dossier.getDueDate());

									String hoursAfterNoon = hours.getString("AM");
									//_log.info("hoursAfterNoon: " + hoursAfterNoon);

									if (Validator.isNotNull(hoursAfterNoon)) {
										String[] splitAfter = StringUtil.split(hoursAfterNoon, StringPool.COLON);
										if (splitAfter != null) {
											dueCalendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(splitAfter[0]));
											dueCalendar.set(Calendar.MINUTE, Integer.valueOf(splitAfter[1]));
										}
									}
								} else {
									dueCalendar.setTime(dossier.getDueDate());
									String hoursAfterNoon = hours.getString("PM");
									if (Validator.isNotNull(hoursAfterNoon)) {
										String[] splitAfter = StringUtil.split(hoursAfterNoon, StringPool.COLON);
										if (splitAfter != null) {
											if (Integer.valueOf(splitAfter[0]) < 12) {
												dueCalendar.add(Calendar.DAY_OF_MONTH, 1);
												dueCalendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(splitAfter[0]));
												dueCalendar.set(Calendar.MINUTE, Integer.valueOf(splitAfter[1]));
											} else {
												//dueCalendar.add(Calendar.DAY_OF_MONTH, 1);
												dueCalendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(splitAfter[0]));
												dueCalendar.set(Calendar.MINUTE, Integer.valueOf(splitAfter[1]));
											}
										}
									}
								}
								jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils
										.convertDateToString(dueCalendar.getTime(), APIDateTimeUtils._NORMAL_PARTTERN));
							} else if (processHours != null && processHours.has("startHour") && processHours.has("dueHour")) {
								//_log.info("STRART check new: ");
								Calendar receiveCalendar = Calendar.getInstance();
								receiveCalendar.setTime(dossier.getReceiveDate());
								//
								String receiveHour = processHours.getString("startHour");
								//_log.info("receiveHour: " + receiveHour);

								if (Validator.isNotNull(receiveHour)) {
									String[] splitHour = StringUtil.split(receiveHour, StringPool.COLON);
									if (splitHour != null) {
										int hourStart = GetterUtil.getInteger(splitHour[0]);
										if (receiveCalendar.get(Calendar.HOUR_OF_DAY) < hourStart) {
											String[] splitdueHour = StringUtil.split(processHours.getString("dueHour"),
													StringPool.COLON);
											Calendar dueCalendar = Calendar.getInstance();
											if (splitdueHour != null) {
												dueCalendar.set(Calendar.HOUR_OF_DAY,
														GetterUtil.getInteger(splitdueHour[0]));
												dueCalendar.set(Calendar.MINUTE,
														GetterUtil.getInteger(splitdueHour[1]));
											} else {
												jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils.convertDateToString(
														dossier.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
											}
											jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils.convertDateToString(
													dueCalendar.getTime(), APIDateTimeUtils._NORMAL_PARTTERN));
										} else {
											jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils.convertDateToString(
													dossier.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
										}
									}
								}
							} else {
								jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils
										.convertDateToString(dossier.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
							}
						} else {
							jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils
									.convertDateToString(dossier.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
						}
					} catch (JSONException e) {
						_log.error(e);
						jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils.convertDateToString(dossier.getDueDate(),
								APIDateTimeUtils._NORMAL_PARTTERN));
					}
				} else {
					jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils.convertDateToString(dossier.getDueDate(),
							APIDateTimeUtils._NORMAL_PARTTERN));
				}
			} else {
				jsonData.put(DossierTerm.DUE_DATE,
						APIDateTimeUtils.convertDateToString(dossier.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			}
		} else {
			jsonData.put(DossierTerm.DUE_DATE, StringPool.BLANK);
		}
		//
		jsonData.put(DossierTerm.POSTAL_ADDRESS, dossier.getPostalAddress());
		jsonData.put(DossierTerm.COUNTER, dossier.getCounter());
		jsonData.put(DossierTerm.REGISTER_BOOK_CODE, dossier.getRegisterBookCode());
		jsonData.put(DossierTerm.SECRET, dossier.getPassword());
		jsonData.put(DossierTerm.BRIEF_NOTE, dossier.getBriefNote());
		jsonData.put(DossierTerm.DOSSIER_ID, dossier.getDossierId());
		//
		long groupId = dossier.getGroupId();
		JSONArray dossierMarkArr = JSONFactoryUtil.createJSONArray();
		long dossierId = dossier.getDossierId();
		String templateNo = dossier.getDossierTemplateNo();
		List<DossierMark> dossierMarkList = dossierMarkLocalService.getDossierMarksByFileMark(groupId, dossierId, 0);
		if (dossierMarkList != null && dossierMarkList.size() > 0) {
			JSONObject jsonMark = null;
			String partNo;
			for (DossierMark dossierMark : dossierMarkList) {
				jsonMark = JSONFactoryUtil.createJSONObject();
				partNo = dossierMark.getDossierPartNo();
				if (Validator.isNotNull(partNo)) {
					DossierPart part = dossierPartLocalService.getByTempAndPartNo(groupId, templateNo, partNo);
					if (part != null) {
						jsonMark.put(DossierPartTerm.DOSSIERPART_ID, part.getDossierPartId());
						jsonMark.put(DossierPartTerm.PART_NAME, part.getPartName());
						jsonMark.put(DossierPartTerm.PART_TIP, part.getPartTip());
						jsonMark.put(DossierPartTerm.PART_TYPE, part.getPartType());
					}
				}
				jsonMark.put(DossierPartTerm.PART_NO, partNo);
				jsonMark.put(DossierPartTerm.FILE_MARK, dossierMark.getFileMark());
				jsonMark.put(DossierPartTerm.FILE_CHECK, dossierMark.getFileCheck());
				jsonMark.put(DossierPartTerm.FILE_COMMENT, dossierMark.getFileComment());
//				String strDossierMark = JSONFactoryUtil.looseSerialize(dossierMark);
				dossierMarkArr.put(jsonMark);
			}
		}
		
		//Hot fix TP99
		DossierMark dossierMark = dossierMarkLocalService.getDossierMarkbyDossierId(groupId, dossierId, "TP99");
		if (dossierMark != null) {
			JSONObject jsonMark = null;
			String partNo = dossierMark.getDossierPartNo();
			if (Validator.isNotNull(partNo)) {
				List<DossierFile> fileList = dossierFileLocalService.getDossierFileByDID_DPNO(dossierId, partNo, false);
				DossierPart part = dossierPartLocalService.getByTempAndPartNo(groupId, templateNo, partNo);
				if (fileList != null && part != null) {
					for (DossierFile dossierFile : fileList) {
						jsonMark = JSONFactoryUtil.createJSONObject();
						jsonMark.put(DossierPartTerm.PART_NAME, dossierFile.getDisplayName());
						jsonMark.put(DossierPartTerm.DOSSIERPART_ID, part.getDossierPartId());
						jsonMark.put(DossierPartTerm.PART_TIP, part.getPartTip());
						jsonMark.put(DossierPartTerm.PART_TYPE, part.getPartType());
						jsonMark.put(DossierPartTerm.PART_NO, partNo);
						jsonMark.put(DossierPartTerm.FILE_MARK, dossierMark.getFileMark());
						jsonMark.put(DossierPartTerm.FILE_CHECK, dossierMark.getFileCheck());
						jsonMark.put(DossierPartTerm.FILE_COMMENT, dossierMark.getFileComment());
//						String strDossierMark = JSONFactoryUtil.looseSerialize(dossierMark);
						dossierMarkArr.put(jsonMark);
					}
				}
			}
		}
		
		jsonData.put(DossierTerm.DOSSIER_MARKS, dossierMarkArr);

		PaymentFile payment = paymentFileLocalService.getByDossierId(groupId, dossierId);
		if (payment != null) {
			jsonData.put(PaymentFileTerm.ADVANCE_AMOUNT, payment.getAdvanceAmount());
			jsonData.put(PaymentFileTerm.PAYMENT_AMOUNT, payment.getPaymentAmount());
			jsonData.put(PaymentFileTerm.PAYMENT_FEE, payment.getPaymentFee());
			jsonData.put(PaymentFileTerm.SERVICE_AMOUNT, payment.getServiceAmount());
			jsonData.put(PaymentFileTerm.SHIP_AMOUNT, payment.getShipAmount());
		}

		if (dossier.getOriginality() == DossierTerm.ORIGINALITY_HOSONHOM) {
			JSONArray groupDossierArr = JSONFactoryUtil.createJSONArray();
			List<Dossier> lstDossiers = dossierLocalService.findByG_GDID(groupId, dossier.getDossierId());
			for (Dossier d : lstDossiers) {
				JSONObject dObject = JSONFactoryUtil.createJSONObject();
				dObject.put(DossierTerm.DOSSIER_NO, d.getDossierNo());
				dObject.put(DossierTerm.APPLICANT_NAME, d.getApplicantName());
				dObject.put(DossierTerm.ADDRESS, d.getAddress());
				dObject.put(DossierTerm.CONTACT_TEL_NO, d.getContactTelNo());
				dObject.put(DossierTerm.CONTACT_EMAIL, d.getContactEmail());
				dObject.put(DossierTerm.CONTACT_NAME, d.getContactName());
				dObject.put(DossierTerm.DELEGATE_ADDRESS, d.getDelegateAddress());
				dObject.put(DossierTerm.SERVICE_CODE, d.getServiceCode());
				dObject.put(DossierTerm.SERVICE_NAME, d.getServiceName());
				dObject.put(DossierTerm.SAMPLE_COUNT, d.getSampleCount());
				dObject.put(DossierTerm.DURATION_UNIT, d.getDurationUnit());
				dObject.put(DossierTerm.DURATION_COUNT, d.getDurationCount());
				dObject.put(DossierTerm.SECRET_KEY, d.getPassword());
				dObject.put(DossierTerm.RECEIVE_DATE,
						APIDateTimeUtils.convertDateToString(d.getReceiveDate(), APIDateTimeUtils._NORMAL_PARTTERN));
				dObject.put(DossierTerm.DELEGATE_NAME, d.getDelegateName());
				dObject.put(DossierTerm.DELEGATE_EMAIL, d.getDelegateEmail());
				dObject.put(DossierTerm.DELEGATE_TELNO, d.getDelegateTelNo());
				dObject.put(DossierTerm.DOSSIER_NAME, d.getDossierName());
				dObject.put(DossierTerm.VIA_POSTAL, d.getViaPostal());
				dObject.put(DossierTerm.POSTAL_ADDRESS, d.getPostalAddress());
	
				groupDossierArr.put(dObject);
			}
			jsonData.put(DossierTerm.GROUP_DOSSIERS, groupDossierArr);
		}
		
		return jsonData;
	}

	//LamTV_ Mapping process dossier and formData
	private JSONObject processMergeDossierProcessRole(Dossier dossier, int length, JSONObject jsonData,
			DossierAction dAction) {
		//
		long groupId = dossier.getGroupId();
		if (dAction != null) {
			long serviceProcessId = dAction.getServiceProcessId();
			jsonData.put(DossierTerm.GOV_AGENCY_NAME, dossier.getGovAgencyName());
			jsonData.put(DossierTerm.TOTAL, length);
			jsonData.put(DossierTerm.ACTION_USER, dAction.getActionUser());
			String sequenceNo = dAction.getSequenceNo();
			if (Validator.isNotNull(sequenceNo)) {
				ProcessSequence sequence = processSequenceLocalService.findBySID_SNO(groupId, serviceProcessId,
						sequenceNo);
				if (sequence != null) {
					jsonData.put(DossierTerm.SEQUENCE_ROLE, sequence.getSequenceRole());
				} else {
					jsonData.put(DossierTerm.SEQUENCE_ROLE, StringPool.BLANK);
				}
			} else {
				jsonData.put(DossierTerm.SEQUENCE_ROLE, StringPool.BLANK);
			}
			// Process get Next sequence Role
			List<ProcessSequence> sequenceList = processSequenceLocalService.getByServiceProcess(groupId,
					serviceProcessId);
			String[] sequenceArr = null;
			if (sequenceList != null && !sequenceList.isEmpty()) {
				int lengthSeq = sequenceList.size();
				sequenceArr = new String[lengthSeq];
				for (int i = 0; i < lengthSeq; i++) {
					ProcessSequence processSequence = sequenceList.get(i);
					if (processSequence != null) {
						sequenceArr[i] = processSequence.getSequenceNo();
					}
				}
			}

			if (sequenceArr != null && sequenceArr.length > 0) {
				for (int i = 0; i < sequenceArr.length; i++) {
//						_log.info("sequenceArr[i]: "+sequenceArr[i]);
				}
				Arrays.sort(sequenceArr);
				for (int i = 0; i < sequenceArr.length - 1; i++) {
					String seq = sequenceArr[i];
					if (sequenceNo.equals(seq)) {
						String nextSequenceNo = sequenceArr[i + 1];
						if (Validator.isNotNull(nextSequenceNo)) {
							ProcessSequence sequence = processSequenceLocalService.findBySID_SNO(groupId,
									serviceProcessId, nextSequenceNo);
							if (sequence != null) {
								jsonData.put(DossierTerm.NEXT_SEQUENCE_ROLE, sequence.getSequenceRole());
							} else {
								jsonData.put(DossierTerm.NEXT_SEQUENCE_ROLE, StringPool.BLANK);
							}
						} else {
							jsonData.put(DossierTerm.NEXT_SEQUENCE_ROLE, StringPool.BLANK);
						}
					}
				}
			} else {
				jsonData.put(DossierTerm.NEXT_SEQUENCE_ROLE, StringPool.BLANK);
			}
		}

		return jsonData;
	}
	
	private void vnpostEvent(Dossier dossier) {
		Message message = new Message();
		JSONObject msgData = JSONFactoryUtil.createJSONObject();

		message.put("msgToEngine", msgData);
		message.put("dossier", DossierMgtUtils.convertDossierToJSON(dossier));
		
		MessageBusUtil.sendMessage("vnpost/dossier/in/destination", message);		
	}
	
	private void publishEvent(Dossier dossier, ServiceContext context) {
		if (dossier.getOriginDossierId() != 0 || Validator.isNotNull(dossier.getOriginDossierNo())) {
			return;
		}
		Message message = new Message();
		JSONObject msgData = JSONFactoryUtil.createJSONObject();

		message.put("msgToEngine", msgData);
		message.put("dossier", DossierMgtUtils.convertDossierToJSON(dossier));
		
//		MessageBusUtil.sendMessage(DossierTerm.PUBLISH_DOSSIER_DESTINATION, message);	
		
		Message lgspMessage = new Message();
		JSONObject lgspMsgData = msgData;

		lgspMessage.put("msgToEngine", lgspMsgData);
		lgspMessage.put("dossier", DossierMgtUtils.convertDossierToJSON(dossier));
		
//		MessageBusUtil.sendMessage(DossierTerm.LGSP_DOSSIER_DESTINATION, lgspMessage);	
		
		//Add publish queue
		List<ServerConfig> lstScs = ServerConfigLocalServiceUtil.getByProtocol(dossier.getGroupId(), ServerConfigTerm.PUBLISH_PROTOCOL);
		for (ServerConfig sc : lstScs) {
			try {
				List<PublishQueue> lstQueues = PublishQueueLocalServiceUtil.getByG_DID_SN_ST(dossier.getGroupId(), dossier.getDossierId(), sc.getServerNo(), new int[] { PublishQueueTerm.STATE_WAITING_SYNC, PublishQueueTerm.STATE_ALREADY_SENT });
				if (lstQueues == null || lstQueues.isEmpty()) {
					publishQueueLocalService.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);										
				}
//				PublishQueue pq = PublishQueueLocalServiceUtil.getByG_DID_SN(dossier.getGroupId(), dossier.getDossierId(), sc.getServerNo());
//				if (pq == null) {
//					PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);					
//				}
//				else {
//					if (pq.getStatus() == PublishQueueTerm.STATE_ACK_ERROR) {
//						PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), pq.getPublishQueueId(), dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);																
//					}
//				}
			} catch (PortalException e) {
				_log.debug(e);
			}
		}

		lstScs = ServerConfigLocalServiceUtil.getByProtocol(dossier.getGroupId(), ServerConfigTerm.LGSP_PROTOCOL);
		for (ServerConfig sc : lstScs) {
			try {
				List<PublishQueue> lstQueues = publishQueueLocalService.getByG_DID_SN_ST(dossier.getGroupId(), dossier.getDossierId(), sc.getServerNo(), new int[] { PublishQueueTerm.STATE_WAITING_SYNC, PublishQueueTerm.STATE_ALREADY_SENT });
				if (lstQueues == null || lstQueues.isEmpty()) {
					publishQueueLocalService.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);					
				}
//				PublishQueue pq = PublishQueueLocalServiceUtil.getByG_DID_SN(dossier.getGroupId(), dossier.getDossierId(), sc.getServerNo());
//				if (pq == null) {
//					PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);					
//				}
//				else {
//					if (pq.getStatus() == PublishQueueTerm.STATE_ACK_ERROR) {
//						PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), pq.getPublishQueueId(), dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);																
//					}
//				}
			} catch (PortalException e) {
				_log.debug(e);
			}
		}	
	}
	
	private ProcessOption getProcessOption(String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
			long groupId) throws PortalException {

		ServiceConfig config = serviceConfigLocalService.getBySICodeAndGAC(groupId, serviceInfoCode, govAgencyCode);

		return processOptionLocalService.getByDTPLNoAndServiceCF(groupId, dossierTemplateNo,
				config.getServiceConfigId());
	}
	
	protected ProcessAction getProcessAction(long groupId, long dossierId, String refId, String actionCode,
			long serviceProcessId) throws PortalException {

		ProcessAction action = null;

		try {
			List<ProcessAction> actions = processActionLocalService.getByActionCode(groupId, actionCode,
					serviceProcessId);
			Dossier dossier = getDossier(groupId, dossierId, refId);

			String dossierStatus = dossier.getDossierStatus();

			String dossierSubStatus = Validator.isNull(dossier.getDossierSubStatus()) ? StringPool.BLANK
					: dossier.getDossierSubStatus();

			for (ProcessAction act : actions) {

				String preStepCode = act.getPreStepCode();

				ProcessStep step = processStepLocalService.fetchBySC_GID(preStepCode, groupId, serviceProcessId);

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

			_log.debug(e);
			//_log.error(e);

			throw new NotFoundException("ProcessActionNotFoundException with actionCode= " + actionCode
					+ "|serviceProcessId= " + serviceProcessId + "|referenceUid= " + refId + "|groupId= " + groupId);
		}

		return action;
	}
	
	protected Dossier getDossier(long groupId, long dossierId, String refId) throws PortalException {

		Dossier dossier = null;

		if (dossierId != 0) {
			dossier = dossierLocalService.fetchDossier(dossierId);
		} else {
			dossier = dossierLocalService.getByRef(groupId, refId);
		}

		return dossier;
	}
	
	private String generatorGoodCode(int length) {

		String tempGoodCode = _generatorUniqueString(length);

		String goodCode;

		while (_checkContainsGoodCode(tempGoodCode)) {
			tempGoodCode = _generatorUniqueString(length);
		}

		/*
		 * while(_testCheck(tempGoodCode)) { tempGoodCode =
		 * _generatorUniqueString(length); }
		 */
		goodCode = tempGoodCode;

		return goodCode;
	}
	
	private boolean _checkContainsGoodCode(String keypayGoodCode) {

		boolean isContains = false;

		try {
			PaymentFile paymentFile = null;//PaymentFileLocalServiceUtil.getByGoodCode(keypayGoodCode);

			if (Validator.isNotNull(paymentFile)) {
				isContains = true;
			}
		} catch (Exception e) {
			_log.error(e);
			isContains = true;
		}

		return isContains;

	}

	private DossierAction doActionOutsideProcess(long groupId, long userId, Dossier dossier, ActionConfig actionConfig, ProcessOption option, ProcessAction proAction,
			String actionCode, String actionUser, String actionNote, String payload, String assignUsers, 
			String payment,
			int syncType,
			ServiceContext context) throws PortalException, SystemException, Exception {
		context.setUserId(userId);
		DossierAction dossierAction = null;

		String dossierStatus = dossier.getDossierStatus().toLowerCase();
		if (Validator.isNotNull(dossierStatus) && !"new".equals(dossierStatus)) {
			dossier = dossierLocalService.updateDossier(dossier);
		}

//		ServiceProcess serviceProcess = null;
//		if (option != null) {
//			long serviceProcessId = option.getServiceProcessId();
//			serviceProcess = serviceProcessLocalService.fetchServiceProcess(serviceProcessId);
//		}
		
		dossierAction = dossierActionLocalService.fetchDossierAction(dossier.getDossierActionId());
//		ActionConfig ac = actionConfigLocalService.getByCode(groupId, actionCode);
		ActionConfig ac = actionConfig;
		JSONObject payloadObject = JSONFactoryUtil.createJSONObject(payload);
		
		if (Validator.isNotNull(payload)) {
			if (DossierActionTerm.OUTSIDE_ACTION_9100.equals(actionCode)) {
				dossier = dossierLocalService.updateDossierSpecial(dossier.getDossierId(), payloadObject);							
			}
			else {
				dossier = dossierLocalService.updateDossier(dossier.getDossierId(), payloadObject);											
			}
		}
		if (DossierActionTerm.OUTSIDE_ACTION_ROLLBACK.equals(actionCode)) {
			Dossier hslt = dossierLocalService.getByOrigin(dossier.getGroupId(), dossier.getDossierId());
			
			if (dossierAction != null && (dossierAction.isRollbackable() || hslt.getOriginality() < 0)) {
				dossierActionLocalService.updateState(dossierAction.getDossierActionId(), DossierActionTerm.STATE_ROLLBACK);
			
				DossierAction previousAction = dossierActionLocalService.fetchDossierAction(dossierAction.getPreviousActionId());
				if (previousAction != null) {
					dossierActionLocalService.updateState(previousAction.getDossierActionId(), DossierActionTerm.STATE_WAITING_PROCESSING);
					dossierActionLocalService.updateNextActionId(previousAction.getDossierActionId(), 0);
					dossierLocalService.rollback(dossier, previousAction);
				}
				else {
					dossierActionLocalService.removeAction(dossierAction.getDossierActionId());
					dossier.setDossierActionId(0);
					dossier.setOriginality(-dossier.getOriginality());
					dossierLocalService.updateDossier(dossier);
				}
			}
			
		}
		
		//Create DossierSync
		String dossierRefUid = dossier.getReferenceUid();
		String syncRefUid = UUID.randomUUID().toString();
		if (syncType > 0) {
			int state = DossierActionUtils.getSyncState(syncType, dossier);
			//Update payload
			if (Validator.isNotNull(dossier.getServerNo())
					&& dossier.getServerNo().split(StringPool.BLANK).length > 1) {
				String serverNo = dossier.getServerNo().split(StringPool.COMMA)[0].split(StringPool.AT)[0];
				dossierSyncLocalService.updateDossierSync(groupId, userId, dossier.getDossierId(), dossierRefUid, syncRefUid,
						dossierAction.getPrimaryKey(), actionCode, ac.getActionName(), actionUser, actionNote,
						syncType, ac.getInfoType(), payloadObject.toJSONString(), serverNo, state);				
			}
			else {
				dossierSyncLocalService.updateDossierSync(groupId, userId, dossier.getDossierId(), dossierRefUid, syncRefUid,
					dossierAction.getPrimaryKey(), actionCode, ac.getActionName(), actionUser, actionNote,
					syncType, ac.getInfoType(), payloadObject.toJSONString(), dossier.getServerNo(), state);
			}
		}
		
		if (ac != null && dossierAction != null) {
			//Only create dossier document if 2 && 3
			if (dossier.getOriginality() != DossierTerm.ORIGINALITY_DVCTT) {
				if (Validator.isNotNull(ac.getDocumentType()) && !ac.getActionCode().startsWith("@")) {
					//Generate document
					DocumentType dt = documentTypeLocalService.getByTypeCode(groupId, ac.getDocumentType());
					if (dt != null) {
						String documentCode = DocumentTypeNumberGenerator.generateDocumentTypeNumber(groupId, ac.getCompanyId(), dt.getDocumentTypeId());
						
						DossierDocument dossierDocument = dossierDocumentLocalService.addDossierDoc(groupId, dossier.getDossierId(), UUID.randomUUID().toString(), dossierAction.getDossierActionId(), dt.getTypeCode(), dt.getDocumentName(), documentCode, 0L, dt.getDocSync(), context);
	
						//Generate PDF
						String formData = payload;
						JSONObject formDataObj = processMergeDossierFormData(dossier, JSONFactoryUtil.createJSONObject(formData));
	//					_log.info("Dossier document form data action outside: " + formDataObj.toJSONString());
						Message message = new Message();
	//					_log.info("Document script: " + dt.getDocumentScript());
						JSONObject msgData = JSONFactoryUtil.createJSONObject();
						msgData.put("className", DossierDocument.class.getName());
						msgData.put("classPK", dossierDocument.getDossierDocumentId());
						msgData.put("jrxmlTemplate", dt.getDocumentScript());
						msgData.put("formData", formDataObj.toJSONString());
						msgData.put("userId", userId);
	
						message.put("msgToEngine", msgData);
						MessageBusUtil.sendMessage("jasper/engine/out/destination", message);
					}
				}					
			}
		}
		
		if (ac != null && ac.getEventType() == ActionConfigTerm.EVENT_TYPE_SENT) {
			publishEvent(dossier, context);			
		}
		
		if (OpenCPSConfigUtil.isNotificationEnable()) {
			createNotificationQueueOutsideProcess(userId, groupId, dossier, actionConfig, context);
		}
		
		if (DossierActionTerm.OUTSIDE_ACTION_ROLLBACK.equals(actionCode)) {
			if (dossier.getOriginDossierId() != 0) {
				Dossier hslt = dossierLocalService.fetchDossier(dossier.getOriginDossierId());
				ProcessOption optionHslt = getProcessOption(hslt.getServiceCode(), hslt.getGovAgencyCode(),
						hslt.getDossierTemplateNo(), groupId);
				String actionUserHslt = actionUser;
				if (dossier.getOriginDossierId() != 0) {
					Dossier originDossier = dossierLocalService.fetchDossier(dossier.getOriginDossierId());
					if (originDossier != null) {
						DossierAction lastAction = dossierActionLocalService.fetchDossierAction(originDossier.getDossierActionId());
						ActionConfig mappingAction = actionConfigLocalService.getByCode(groupId, lastAction.getActionCode());
						if (Validator.isNotNull(mappingAction.getMappingAction())) {
							DossierAction previousOriginAction = dossierActionLocalService.fetchDossierAction(lastAction.getPreviousActionId());
							dossierActionLocalService.updateState(previousOriginAction.getDossierActionId(), DossierActionTerm.STATE_WAITING_PROCESSING);
							dossierActionLocalService.updateNextActionId(previousOriginAction.getDossierActionId(), 0);
							dossierLocalService.rollback(originDossier, previousOriginAction);
						}
					}
				}

				if (DossierTerm.DOSSIER_STATUS_NEW.equals(hslt.getDossierStatus())) {
					Date now = new Date();
					hslt.setSubmitDate(now);
					hslt = dossierLocalService.updateDossier(hslt);
					try {
						JSONObject payloadObj = JSONFactoryUtil.createJSONObject(payload);
						payloadObj.put(DossierTerm.SUBMIT_DATE, now.getTime());
						payload = payloadObj.toJSONString();
					}
					catch (JSONException e) {
						_log.debug(e);
					}
				}
				DossierAction lastAction = dossierActionLocalService.fetchDossierAction(hslt.getDossierActionId());
				ActionConfig mappingAction = actionConfigLocalService.getByCode(groupId, lastAction.getActionCode());
				if (Validator.isNotNull(mappingAction.getMappingAction())) {
					doAction(groupId, userId, hslt, optionHslt, null, DossierActionTerm.OUTSIDE_ACTION_ROLLBACK, actionUserHslt, actionNote, payload, assignUsers, payment, 0, context);
				}
			}
		}
		
		return dossierAction;
	}
	
	private void createNotificationQueueOutsideProcess(long userId, long groupId, Dossier dossier, ActionConfig actionConfig, ServiceContext context) {
		DossierAction dossierAction = dossierActionLocalService.fetchDossierAction(dossier.getDossierActionId());
		User u = UserLocalServiceUtil.fetchUser(userId);
        JSONObject payloadObj = JSONFactoryUtil.createJSONObject();
        try {		
        	payloadObj.put(
					"Dossier", JSONFactoryUtil.createJSONObject(
						JSONFactoryUtil.looseSerialize(dossier)));
        	
        	if (dossierAction != null) {
        		payloadObj.put("actionCode", dossierAction.getActionCode());
        		payloadObj.put("actionUser", dossierAction.getActionUser());
        		payloadObj.put("actionName", dossierAction.getActionName());
        		payloadObj.put("actionNote", dossierAction.getActionNote());
        	}
        }
        catch (Exception e) {
        	_log.error(e);
        }

		if (actionConfig != null && Validator.isNotNull(actionConfig.getNotificationType())) {
			Notificationtemplate notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, actionConfig.getNotificationType());
			Date now = new Date();
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(now);
	        	  
			if (notiTemplate != null) {
				if ("minutely".equals(notiTemplate.getInterval())) {
			        cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());					
				}
				else if ("hourly".equals(notiTemplate.getInterval())) {
			        cal.add(Calendar.HOUR, notiTemplate.getExpireDuration());										
				}
				else {
			        cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());										
				}
				Date expired = cal.getTime();

				if (actionConfig.getNotificationType().startsWith("APLC")) {
					if (dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA
							|| dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) {
						try {
							Applicant applicant = ApplicantLocalServiceUtil.fetchByAppId(dossier.getApplicantIdNo());
							long toUserId = (applicant != null ? applicant.getMappingUserId() : 0l);
							
							NotificationQueueLocalServiceUtil.addNotificationQueue(
									userId, groupId, 
									actionConfig.getNotificationType(), 
									Dossier.class.getName(), 
									String.valueOf(dossier.getDossierId()), 
									payloadObj.toJSONString(), 
									u.getFullName(), 
									dossier.getApplicantName(), 
									toUserId, 
									dossier.getContactEmail(), 
									dossier.getContactTelNo(), 
									now, 
									expired, 
									context);
						} catch (NoSuchUserException e) {
							_log.error(e);
						}
					}
				}
				else if (actionConfig.getNotificationType().startsWith("USER")) {
					
				}				
			}
		}	
		
	}
	
	/**
	 * @param pattern
	 * @param lenght
	 * @return
	 */
	private String _generatorUniqueString(int lenght) {

		char[] chars = "0123456789".toCharArray();

		StringBuilder sb = new StringBuilder();

		Random random = new Random();

		for (int i = 0; i < lenght; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}

		return sb.toString();

	}
	
	private void assignDossierActionUser(Dossier dossier, int allowAssignUser, DossierAction dossierAction, long userId, long groupId, long assignUserId, JSONArray assignedUsers)
			throws PortalException {
		int moderator = 1;
		List<DossierUser> lstDus = dossierUserLocalService.findByDID(dossier.getDossierId());
		HashMap<Long, DossierUser> mapDus = new HashMap<>();
		for (DossierUser du : lstDus) {
			mapDus.put(du.getUserId(), du);
		}
		List<org.opencps.dossiermgt.model.DossierActionUser> lstDaus = dossierActionUserLocalService.getByDossierId(dossier.getDossierId());
		
		HashMap<Long, Map<Long, org.opencps.dossiermgt.model.DossierActionUser>> mapDaus = new HashMap<>();
		for (org.opencps.dossiermgt.model.DossierActionUser dau : lstDaus) {
			if (mapDaus.get(dau.getDossierActionId()) != null) {
				Map<Long, org.opencps.dossiermgt.model.DossierActionUser> temp = mapDaus.get(dau.getDossierActionId());
				temp.put(dau.getUserId(), dau);
			}
			else {
				Map<Long, org.opencps.dossiermgt.model.DossierActionUser> temp = new HashMap<>();
				temp.put(dau.getUserId(), dau);
				mapDaus.put(dau.getDossierActionId(), temp);
			}
		}
		for (int n = 0; n < assignedUsers.length(); n++) {
			JSONObject subUser = assignedUsers.getJSONObject(n);
			if (subUser != null && subUser.has(DossierActionUserTerm.ASSIGNED)
					&& subUser.getInt(DossierActionUserTerm.ASSIGNED) == DossierActionUserTerm.ASSIGNED_TH) {
				DossierActionUserPK pk = new DossierActionUserPK();
				long userIdAssigned = subUser.getLong("userId");
				int assigned = subUser.has(DossierActionUserTerm.ASSIGNED) ? subUser.getInt(DossierActionUserTerm.ASSIGNED) : 0;
				
				pk.setDossierActionId(dossierAction.getDossierActionId());
				
				pk.setUserId(subUser.getLong("userId"));
	
				DossierUser dossierUser = null;
				dossierUser = mapDus.get(subUser.getLong("userId"));
				
				if (dossierUser != null) {
					//Update dossier user if assigned
					if (allowAssignUser != ProcessActionTerm.NOT_ASSIGNED) {
						dossierUser.setModerator(1);
						dossierUserLocalService.updateDossierUser(dossierUser.getDossierId(), dossierUser.getUserId(),
								dossierUser.getModerator(), dossierUser.getVisited());
						
	//					model.setModerator(dossierUser.getModerator());
						moderator = dossierUser.getModerator();
					}
	
				}
				else {
					if (allowAssignUser != ProcessActionTerm.NOT_ASSIGNED) {
						dossierUserLocalService.addDossierUser(groupId, dossier.getDossierId(), userIdAssigned, 1, true);
					}					
				}
				
//				org.opencps.dossiermgt.model.DossierActionUser dau = DossierActionUserLocalServiceUtil.fetchDossierActionUser(pk);
				org.opencps.dossiermgt.model.DossierActionUser dau = null;
//				dau = mapDaus.get(userIdAssigned);
				if (mapDaus.get(dossierAction.getDossierActionId()) != null) {
					dau = mapDaus.get(dossierAction.getDossierActionId()).get(userIdAssigned);
				}
				
				if (dau == null) {
//					DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierAction.getDossierActionId());
					DossierAction dAction = dossierAction;
					if (dAction != null) {
						addDossierActionUserByAssigned(allowAssignUser, userIdAssigned, dossierAction.getDossierActionId(), moderator, false,
								dAction.getStepCode(), dossier.getDossierId(), assigned, 0);
					} 
//					else {
//						addDossierActionUserByAssigned(allowAssignUser, userIdAssigned, dossierAction.getDossierActionId(), moderator, false,
//								StringPool.BLANK, dossier.getDossierId(), assigned);
//					}
				}
				else {
					dau.setModerator(1);
					dau.setAssigned(assigned);
					dossierActionUserLocalService.updateDossierActionUser(dau);
				}
			}
			else if (subUser != null && subUser.has(DossierActionUserTerm.ASSIGNED)
					&& subUser.getInt(DossierActionUserTerm.ASSIGNED) == DossierActionUserTerm.NOT_ASSIGNED) {
				//			model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
				DossierActionUserPK pk = new DossierActionUserPK();
				
				pk.setDossierActionId(dossierAction.getDossierActionId());
				pk.setUserId(subUser.getLong("userId"));
	
				org.opencps.dossiermgt.model.DossierActionUser dau = dossierActionUserLocalService.fetchDossierActionUser(pk);
				if (Validator.isNull(dau)) {
				}				
				else {
					dau.setModerator(0);
					dossierActionUserLocalService.updateDossierActionUser(dau);
				}
			}
		}
	}
	
	private void addDossierActionUserByAssigned(int allowAssignUser, long userId, long dossierActionId, int moderator,
			boolean visited, String stepCode, long dossierId, int assigned, int delegacy) {
		org.opencps.dossiermgt.model.DossierActionUser model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
	
//		int assigned = DossierActionUserTerm.NOT_ASSIGNED;
		model.setVisited(visited);
		model.setDossierId(dossierId);
		model.setStepCode(stepCode);
		model.setAssigned(assigned);
		model.setDelegacy(delegacy);
		//Check employee is exits and wokingStatus
		Employee employee = EmployeeLocalServiceUtil.fetchByFB_MUID(userId);
		//_log.debug("Employee : " + employee);
		if (employee != null && employee.getWorkingStatus() == 1) {

			DossierActionUserPK pk = new DossierActionUserPK(dossierActionId, userId);
			
			org.opencps.dossiermgt.model.DossierActionUser dau = dossierActionUserLocalService.fetchDossierActionUser(pk);
			model.setUserId(userId);
			model.setDossierActionId(dossierActionId);
			model.setModerator(moderator);
//			if (allowAssignUser == ProcessActionTerm.NOT_ASSIGNED) {
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				if (moderator == 1) {
//					model.setAssigned(1);
//				} else {
//					model.setAssigned(assigned);
//				}
//				if (dau == null) {
//					dossierActionUserLocalService.addDossierActionUser(model);		
//				}
//				else {
//					if (dau.getModerator() != DossierActionUserTerm.ASSIGNED_TH
//							&& model.getModerator() == DossierActionUserTerm.ASSIGNED_TH) {
//						dossierActionUserLocalService.updateDossierActionUser(model);					
//					}
//				}
//			}
//			else if (allowAssignUser == ProcessActionTerm.ASSIGNED_TH) {
//				_log.debug("Assign dau: " + userId);
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				assigned = DossierActionUserTerm.ASSIGNED_TH;
//				model.setAssigned(assigned);
//				if (dau == null) {
//					_log.debug("Assign add dau: " + userId);
//					dossierActionUserLocalService.addDossierActionUser(model);		
//				}
//				else {
//					if (dau.getModerator() != DossierActionUserTerm.ASSIGNED_TH
//							&& model.getModerator() == DossierActionUserTerm.ASSIGNED_TH) {
//						_log.debug("Assign update dau: " + userId);
//						dossierActionUserLocalService.updateDossierActionUser(model);					
//					}
//				}
//			}
//			else if (allowAssignUser == ProcessActionTerm.ASSIGNED_TH_PH) {
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				assigned = DossierActionUserTerm.ASSIGNED_TH;
//				model.setAssigned(assigned);
//				dossierActionUserLocalService.addDossierActionUser(model);										
//	
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				model.setVisited(true);
//				assigned = DossierActionUserTerm.ASSIGNED_PH;
//				model.setAssigned(assigned);
//				if (dau == null) {
//					dossierActionUserLocalService.addDossierActionUser(model);		
//				}
//				else {
//					if (dau.getModerator() != DossierActionUserTerm.ASSIGNED_TH
//							&& model.getModerator() == DossierActionUserTerm.ASSIGNED_TH) {
//						dossierActionUserLocalService.updateDossierActionUser(model);					
//					}
//				}
//			}
//			else if (allowAssignUser == ProcessActionTerm.ASSIGNED_TH_PH_TD) {
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				assigned = DossierActionUserTerm.ASSIGNED_TH;
//				model.setAssigned(assigned);
//				dossierActionUserLocalService.addDossierActionUser(model);										
//	
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				assigned = DossierActionUserTerm.ASSIGNED_PH;
//				model.setAssigned(assigned);
//				dossierActionUserLocalService.addDossierActionUser(model);														
//	
//				model.setUserId(userId);
//				model.setDossierActionId(dossierActionId);
//				model.setModerator(moderator);
//				assigned = DossierActionUserTerm.ASSIGNED_TD;
//				model.setAssigned(assigned);
//				if (dau == null) {
//					dossierActionUserLocalService.addDossierActionUser(model);		
//				}
//				else {
//					if (dau.getModerator() != DossierActionUserTerm.ASSIGNED_TH
//							&& model.getModerator() == DossierActionUserTerm.ASSIGNED_TH) {
//						dossierActionUserLocalService.updateDossierActionUser(model);					
//					}
//				}
//			}
			if (dau == null) {
				dossierActionUserLocalService.addDossierActionUser(model);		
			}
			else {
				dossierActionUserLocalService.updateDossierActionUser(model);					
			}			
		}
	}

	public void initDossierActionUser(ProcessAction processAction, Dossier dossier, int allowAssignUser, DossierAction dossierAction, long userId, long groupId, long assignUserId)
			throws PortalException {
		// Delete record in dossierActionUser
		List<org.opencps.dossiermgt.model.DossierActionUser> dossierActionUser = dossierActionUserLocalService
				.getListUser(dossierAction.getDossierActionId());
		if (dossierActionUser != null && dossierActionUser.size() > 0) {
			dossierActionUserLocalService.deleteByDossierAction(dossierAction.getDossierActionId());
		}
		
		long serviceProcessId = dossierAction.getServiceProcessId();
		String stepCode = processAction.getPostStepCode();

		// Get ProcessStep
		ProcessStep processStep = processStepLocalService.fetchBySC_GID(stepCode, groupId, serviceProcessId);
		
		long processStepId = processStep.getProcessStepId();
		int assigned;
		
		// Get List ProcessStepRole
		List<ProcessStepRole> listProcessStepRole = processStepRoleLocalService.findByP_S_ID(processStepId);
		ProcessStepRole processStepRole = null;
		List<DossierAction> lstStepActions = dossierActionLocalService.getByDID_FSC_NOT_DAI(dossier.getDossierId(), stepCode, dossierAction.getDossierActionId());
		if (listProcessStepRole.size() != 0) {
			for (int i = 0; i < listProcessStepRole.size(); i++) {
				processStepRole = listProcessStepRole.get(i);
				long roleId = processStepRole.getRoleId();
				boolean moderator = processStepRole.getModerator();
				int mod = 0;
				if (moderator) {
					mod = 1;
				}
				// Get list user
				List<User> users = UserLocalServiceUtil.getRoleUsers(roleId);
				for (User user : users) {
					Employee employee = EmployeeLocalServiceUtil.fetchByFB_MUID(user.getUserId());
					//_log.debug("Employee : " + employee);
					if (employee != null && employee.getWorkingStatus() == 1) {
						List<DossierAction> lstDoneActions = dossierActionLocalService
								.getByDID_U_FSC(dossier.getDossierId(), user.getUserId(), stepCode);
						if (!lstStepActions.isEmpty()) {
							if (!lstDoneActions.isEmpty())
								mod = 1;
							else
								mod = 0;
						}
						if (moderator) {
							assigned = DossierActionUserTerm.ASSIGNED_TH;
						}
						else {
							assigned = DossierActionUserTerm.NOT_ASSIGNED;
						}
						
						updateDossierUser(dossier, processStepRole, user);
						List<DossierActionUser> lstDau = dossierActionUserLocalService.getByDossierUserAndStepCode(dossier.getDossierId(), user.getUserId(), stepCode);
						DossierActionUser lastDau = (lstDau.size() > 0 ? lstDau.get(0) : null);
						for (DossierActionUser dau : lstDau) {
							if (dau.getDossierActionId() > lastDau.getDossierActionId()) {
								lastDau = dau;
							}
						}
						
						if (lastDau != null) {
							addDossierActionUserByAssigned(processAction.getAllowAssignUser(), user.getUserId(),
								dossierAction.getDossierActionId(), lastDau.getModerator(), false, stepCode, dossier.getDossierId(), lastDau.getAssigned(), lastDau.getDelegacy());	
						}
						else {
							addDossierActionUserByAssigned(processAction.getAllowAssignUser(), user.getUserId(),
									dossierAction.getDossierActionId(), mod, false, stepCode, dossier.getDossierId(), assigned, 0);							
						}
					}
				}
			}
		}
		else {
			//Get role from service process
			initDossierActionUserByServiceProcessRole(dossier, allowAssignUser, dossierAction, userId, groupId, assignUserId);
		}
	}

	private void updateDossierUser(Dossier dossier, ProcessStepRole processStepRole, User user) {
		DossierUserPK pk = new DossierUserPK();
		pk.setDossierId(dossier.getDossierId());
		pk.setUserId(user.getUserId());
		DossierUser du = dossierUserLocalService.fetchDossierUser(pk);
		if (du == null) {
			dossierUserLocalService.addDossierUser(dossier.getGroupId(), dossier.getDossierId(), user.getUserId(), processStepRole.getModerator() ? 1 : 0, true);
		}
		else {
			try {
				if ((processStepRole.getModerator() && du.getModerator() != DossierActionUserTerm.ASSIGNED_PH)
						|| (!processStepRole.getModerator() && du.getModerator() != DossierActionUserTerm.ASSIGNED_PH)) {
					dossierUserLocalService.updateDossierUser(dossier.getDossierId(), user.getUserId(), du.getModerator() == 0 ? (processStepRole.getModerator() ? 1 : 0) : 1, true);					
				}
			} catch (NoSuchDossierUserException e) {
				_log.error(e);
			}
		}
	}

	private void initDossierActionUserByServiceProcessRole(Dossier dossier, int allowAssignUser, DossierAction dossierAction, long userId, long groupId, long assignUserId) {
		try {
			ServiceProcess serviceProcess = serviceProcessLocalService.getServiceByCode(groupId, dossier.getServiceCode(), dossier.getGovAgencyCode(), dossier.getDossierTemplateNo());
			List<ServiceProcessRole> listSprs = serviceProcessRoleLocalService.findByS_P_ID(serviceProcess.getServiceProcessId());
			
			DossierAction da = dossierAction;
			
			for (ServiceProcessRole spr : listSprs) {
				int mod = 0;
				boolean moderator = spr.getModerator();
				
				if (moderator) {
					mod = 1;
				}
				List<User> users = UserLocalServiceUtil.getRoleUsers(spr.getRoleId());
				for (User user : users) {
					int assigned = user.getUserId() == assignUserId ? DossierActionUserTerm.ASSIGNED_TH : (moderator ? DossierActionUserTerm.ASSIGNED_TH : DossierActionUserTerm.NOT_ASSIGNED);
					org.opencps.dossiermgt.model.DossierActionUser dau = dossierActionUserLocalService.getByDossierAndUser(dossierAction.getDossierActionId(), user.getUserId());
					if (dau != null) {
						dau.setModerator(mod);
						dau.setAssigned(assigned);
						dossierActionUserLocalService.updateDossierActionUser(dau);
					} else {						
						addDossierActionUserByAssigned(allowAssignUser, user.getUserId(), dossierAction.getDossierActionId(), mod, false, da.getStepCode(), dossier.getDossierId(), assigned, 0);
					}
				}				
			}
		} catch (PortalException e) {
			_log.error(e);
		}		
	}
	
	private void copyRoleAsStep(ProcessStep curStep, Dossier dossier) {
		if (Validator.isNull(curStep.getRoleAsStep()))
			return;	
		String[] stepCodeArr = StringUtil.split(curStep.getRoleAsStep());
		if (stepCodeArr.length > 0) {
			for (String stepCode : stepCodeArr) {
				if (stepCode.startsWith("!")) {
					int index = stepCode.indexOf("!");
					String stepCodePunc = stepCode.substring(index + 1);
					List<org.opencps.dossiermgt.model.DossierActionUser> lstDaus = dossierActionUserLocalService.getByDossierAndStepCode(dossier.getDossierId(), stepCodePunc);
					List<DossierAction> lstDossierActions = dossierActionLocalService.findDossierActionByDID_STEP(dossier.getDossierId(), stepCodePunc);
					
					try {
						for (org.opencps.dossiermgt.model.DossierActionUser dau : lstDaus) {
							boolean flagDA = false;
							for (DossierAction da : lstDossierActions) {
								if (da.getUserId() == dau.getUserId()) {
									flagDA = true;
									break;
								}
							}
							if (flagDA) {
								DossierUserPK duPk = new DossierUserPK();
								duPk.setDossierId(dossier.getDossierId());
								duPk.setUserId(dau.getUserId());
								int moderator = dau.getModerator();
								
								DossierUser duModel = dossierUserLocalService.fetchDossierUser(duPk);
														
								if (duModel == null) {
									dossierUserLocalService.addDossierUser(dossier.getGroupId(), dossier.getDossierId(), 
											dau.getUserId(), moderator, true);
								}
								else {
									try {
										if (duModel.getModerator() == 0 && moderator == 1) {
											dossierUserLocalService.updateDossierUser(dossier.getDossierId(), dau.getUserId(),
													moderator, true);							
										}
									} catch (NoSuchDossierUserException e) {
		//										e.printStackTrace();
										_log.error(e);
									}					
								}	
								
								DossierActionUserPK dauPk = new DossierActionUserPK();
								dauPk.setDossierActionId(dossier.getDossierActionId());
								dauPk.setUserId(dau.getUserId());
								int assigned = moderator == 1 ? 1 : 0;
								dossierActionUserLocalService.addOrUpdateDossierActionUser(dau.getUserId(), dossier.getGroupId(), dossier.getDossierActionId(), dossier.getDossierId(), curStep.getStepCode(), moderator, assigned, true);
							}
						}
					}
					catch (Exception e) {
						_log.error(e);
					}
				}
				else {
					ServiceProcess serviceProcess = null;
					try {
						serviceProcess = serviceProcessLocalService.getServiceByCode(dossier.getGroupId(), dossier.getServiceCode(), dossier.getGovAgencyCode(), dossier.getDossierTemplateNo());
						if (serviceProcess != null) {
							ProcessStep processStep = processStepLocalService.fetchBySC_GID(stepCode, dossier.getGroupId(), serviceProcess.getServiceProcessId());
							if (processStep == null) continue;
							List<ProcessStepRole> lstRoles = processStepRoleLocalService.findByP_S_ID(processStep.getProcessStepId());
							for (ProcessStepRole psr : lstRoles) {
								List<User> users = UserLocalServiceUtil.getRoleUsers(psr.getRoleId());
								for (User u : users) {
									DossierUserPK duPk = new DossierUserPK();
									duPk.setDossierId(dossier.getDossierId());
									duPk.setUserId(u.getUserId());
									int moderator = (psr.getModerator() ? 1 : 0);
									
									DossierUser duModel = dossierUserLocalService.fetchDossierUser(duPk);
															
									if (duModel == null) {
										dossierUserLocalService.addDossierUser(dossier.getGroupId(), dossier.getDossierId(), 
												u.getUserId(), moderator, true);
									}
									else {
										try {
											if (duModel.getModerator() == 0 && moderator == 1) {
												dossierUserLocalService.updateDossierUser(dossier.getDossierId(), u.getUserId(),
														moderator, true);							
											}
										} catch (NoSuchDossierUserException e) {
											_log.error(e);
										}					
									}	
									
									DossierActionUserPK dauPk = new DossierActionUserPK();
									dauPk.setDossierActionId(dossier.getDossierActionId());
									dauPk.setUserId(u.getUserId());
									int assigned = moderator == 1 ? 1 : 0;
									dossierActionUserLocalService.addOrUpdateDossierActionUser(u.getUserId(), dossier.getGroupId(), dossier.getDossierActionId(), dossier.getDossierId(), curStep.getStepCode(), moderator, assigned, true);
								}
							}
						}
					} catch (PortalException e) {
						_log.error(e);
					}				
				}
			}
		}		
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={SystemException.class, PortalException.class, Exception.class })
	public Dossier addDossier(long groupId, Company company, User user, ServiceContext serviceContext,
			DossierInputModel input) throws UnauthenticationException, PortalException, Exception {

		BackendAuth auth = new BackendAuthImpl();
		DossierPermission dossierPermission = new DossierPermission();

		long start = System.currentTimeMillis();
		
		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		ProcessOption option = getProcessOption(input.getServiceCode(), input.getGovAgencyCode(),
				input.getDossierTemplateNo(), groupId);
		long serviceProcessId = 0;
		if (option != null) {
			serviceProcessId = option.getServiceProcessId();
		}

		boolean flag = false;
		long userId = serviceContext.getUserId();
		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
		if (employee != null) {
			long employeeId = employee.getEmployeeId();
			if (employeeId > 0) {
				List<EmployeeJobPos> empJobList = EmployeeJobPosLocalServiceUtil.findByF_EmployeeId(employeeId);
				if (empJobList != null && empJobList.size() > 0) {
					for (EmployeeJobPos employeeJobPos : empJobList) {
						long jobPosId = employeeJobPos.getJobPostId();
						if (jobPosId > 0) {
							JobPos job = JobPosLocalServiceUtil.fetchJobPos(jobPosId);
							if (job != null) {
								ServiceProcessRolePK pk = new ServiceProcessRolePK(serviceProcessId,
											job.getMappingRoleId());
								ServiceProcessRole role = serviceProcessRoleLocalService
											.fetchServiceProcessRole(pk);
								if (role != null && role.getModerator()) {
									flag = true;
									break;
								}
							}
						}
					}
				}
			}
		} else {
			flag = true;
		}
	
		if (!flag) {
			throw new UnauthenticationException("No permission create dossier");
		}
		_log.debug("CREATE DOSSIER 1: " + (System.currentTimeMillis() - start) + " ms");
		dossierPermission.hasCreateDossier(groupId, user.getUserId(), input.getServiceCode(),
				input.getGovAgencyCode(), input.getDossierTemplateNo());

		//int counter = DossierNumberGenerator.counterDossier(user.getUserId(), groupId);
		String referenceUid = input.getReferenceUid();
		int counter = 0;

		// Create dossierNote
		ServiceProcess process = null;
		boolean online = GetterUtil.getBoolean(input.getOnline());
		int originality = GetterUtil.getInteger(input.getOriginality());
		Integer viaPostal = input.getViaPostal();
		ServiceConfig config = serviceConfigLocalService.getBySICodeAndGAC(groupId, input.getServiceCode(),
				input.getGovAgencyCode());
		if (config != null && Validator.isNotNull(viaPostal)) {
			viaPostal = config.getPostService() ? (viaPostal == 0 ? 1 : viaPostal) : 0;
		}
		else if (config != null) {
			viaPostal = config.getPostService() ? 1 : 0;
		}
		if (option != null) {
			process = serviceProcessLocalService.getServiceProcess(serviceProcessId);
		}

		if (process == null) {
			throw new NotFoundException("Cant find process");
		}

		if (Validator.isNull(referenceUid) || referenceUid.trim().length() == 0)
			referenceUid = DossierNumberGenerator.generateReferenceUID(groupId);

		//Dossier checkDossier = dossierLocalService.getByRef(groupId, referenceUid);
		//if (checkDossier != null) {
		//	return checkDossier;
		//}
		
		ServiceInfo service = serviceInfoLocalService.getByCode(groupId, input.getServiceCode());
		String serviceName = StringPool.BLANK;
		if (service != null) {
			serviceName = service.getServiceName();
		}

		String govAgencyName = getDictItemName(groupId, GOVERNMENT_AGENCY, input.getGovAgencyCode());
		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(ADMINISTRATIVE_REGION, groupId);
		
		String cityName = getDictItemName(groupId, dc, input.getCityCode());
		String districtName = getDictItemName(groupId, dc, input.getDistrictCode());
		String wardName = getDictItemName(groupId, dc, input.getWardCode());
//			_log.info("Service code: " + input.getServiceCode());
		_log.debug("===ADD DOSSIER CITY NAME:" + cityName);
		String password = StringPool.BLANK;
		if (Validator.isNotNull(input.getPassword())) {
			password = input.getPassword();
		} else if (Validator.isNotNull(process.getGeneratePassword()) && process.getGeneratePassword()) {
			password = PwdGenerator.getPinNumber();
		}

		String postalCityName = StringPool.BLANK;
		
		if (Validator.isNotNull(input.getPostalCityCode())) {
			postalCityName = getDictItemName(groupId, VNPOST_CITY_CODE, input.getPostalCityCode());
		}
		Long sampleCount = (option != null ? option.getSampleCount() : 1l);
		String registerBookCode = (option != null ? (Validator.isNotNull(option.getRegisterBookCode()) ? option.getRegisterBookCode() : StringPool.BLANK) : StringPool.BLANK);
		String registerBookName = (Validator.isNotNull(registerBookCode) ? getDictItemName(groupId, REGISTER_BOOK, registerBookCode) : StringPool.BLANK);

		
		// Process group dossier
		if (originality == 9) {

			_log.debug("CREATE DOSSIER 2: " + (System.currentTimeMillis() - start) + " ms");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			Date appIdDate = null;

			try {
				appIdDate = sdf.parse(input.getApplicantIdDate());

			} catch (Exception e) {
				_log.debug(e);
			}
			Dossier dossier = dossierLocalService.initDossier(groupId, 0l, referenceUid, counter, input.getServiceCode(), serviceName,
					input.getGovAgencyCode(), govAgencyName, input.getApplicantName(), input.getApplicantIdType(),
					input.getApplicantIdNo(), appIdDate, input.getAddress(), input.getCityCode(),
					cityName, input.getDistrictCode(), districtName, input.getWardCode(), wardName,
					input.getContactName(), input.getContactTelNo(), input.getContactEmail(),
					input.getDossierTemplateNo(), password, viaPostal, input.getPostalAddress(), input.getPostalCityCode(), postalCityName,
					input.getPostalTelNo(), online, process.getDirectNotification(), input.getApplicantNote(),
					Integer.valueOf(input.getOriginality()), 
					service, process, option,
					serviceContext);

			if (Validator.isNotNull(input.getDossierName())) {
				dossier.setDossierName(input.getDossierName());
			} else {
				dossier.setDossierName(serviceName);
			}
			dossier.setSampleCount(sampleCount);
			if (Validator.isNotNull(input.getMetaData()))
				dossier.setMetaData(input.getMetaData());

			updateDelegateApplicant(dossier, input);
			// Process update dossierNo
//			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
//			params.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
//			params.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
//			params.put(DossierTerm.DOSSIER_TEMPLATE_NO, dossier.getDossierTemplateNo());
//			params.put(DossierTerm.DOSSIER_STATUS, StringPool.BLANK);

			if (option != null) {
				//Process submition note
				dossier.setSubmissionNote(option.getSubmissionNote());

//				String dossierRef = DossierNumberGenerator.generateDossierNumber(groupId, dossier.getCompanyId(),
//						dossier.getDossierId(), option.getProcessOptionId(), process.getDossierGroupPattern(), params);
//				dossier.setDossierNo(dossierRef.trim());
			}
			dossier.setViaPostal(1);

			_log.debug("CREATE DOSSIER 3: " + (System.currentTimeMillis() - start) + " ms");

			if (Validator.isNull(dossier)) {
				throw new NotFoundException("Can't add DOSSIER");
			}

			_log.debug("CREATE DOSSIER 4: " + (System.currentTimeMillis() - start) + " ms");
			//Create DossierMark
			_log.debug("originality: "+originality);
			String templateNo = dossier.getDossierTemplateNo();
			_log.debug("templateNo: "+templateNo);
			if (Validator.isNotNull(input.getDossierMarkArr())) {
				JSONArray markArr = JSONFactoryUtil.createJSONArray(input.getDossierMarkArr());
				if (markArr != null && markArr.length() > 0) {
					List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId, dossier.getDossierId());
					Map<String, DossierMark> mapMarks = new HashMap<>();
					for (DossierMark dm : lstMarks) {
						mapMarks.put(dm.getDossierPartNo(), dm);
					}

					for (int i = 0; i < markArr.length(); i++) {
						JSONObject jsonMark = markArr.getJSONObject(i);
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[markArr.length()];
						
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel model = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel();
						model.setDossierId(dossier.getDossierId());
						model.setDossierPartNo(jsonMark.getString("partNo"));
						model.setFileCheck(0);
						model.setFileMark(jsonMark.getInt("fileMark"));
						model.setFileComment(StringPool.BLANK);
						model.setRecordCount(StringPool.BLANK);
						marks[i] = model;

						dossierMarkLocalService.addBatchDossierMark(groupId, marks, mapMarks, serviceContext);
					}
				}
			} else if (Validator.isNotNull(templateNo)) {
				List<DossierPart> partList = dossierPartLocalService.getByTemplateNo(groupId, templateNo);
//						_log.info("partList: "+partList);
				if (partList != null && partList.size() > 0) {
					_log.debug("partList.size(): "+partList.size());
					_log.debug("CREATE DOSSIER 4.1: " + (System.currentTimeMillis() - start) + " ms");
					org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList.size()];
					int count = 0;
					List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId, dossier.getDossierId());
					Map<String, DossierMark> mapMarks = new HashMap<>();
					for (DossierMark dm : lstMarks) {
						mapMarks.put(dm.getDossierPartNo(), dm);
					}
					for (DossierPart dossierPart : partList) {
						int fileMark = dossierPart.getFileMark();
						String dossierPartNo = dossierPart.getPartNo();
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel model = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel();
						model.setDossierId(dossier.getDossierId());
						model.setDossierPartNo(dossierPartNo);
						model.setFileCheck(0);
						model.setFileMark(fileMark);
						model.setFileComment(StringPool.BLANK);
						model.setRecordCount(StringPool.BLANK);
						marks[count++] = model;
					}
					
					dossierMarkLocalService.addBatchDossierMark(groupId, marks, mapMarks, serviceContext);
					
					_log.debug("CREATE DOSSIER 4.2: " + (System.currentTimeMillis() - start) + " ms");
				}
			}

			//Create dossier user
			List<DossierUser> lstDus = dossierUserLocalService.findByDID(dossier.getDossierId());
			List<ServiceProcessRole> lstProcessRoles = serviceProcessRoleLocalService.findByS_P_ID(process.getServiceProcessId());
			if (lstDus.size() == 0) {
				DossierUserActions duActions = new DossierUserActionsImpl();
				duActions.initDossierUser(groupId, dossier, process, lstProcessRoles);				
			}
			
//			if (originality == DossierTerm.ORIGINALITY_DVCTT) {
//				dossierUserLocalService.addDossierUser(groupId, dossier.getDossierId(), userId, 1, true);
//			}
			_log.debug("CREATE DOSSIER 5: " + (System.currentTimeMillis() - start) + " ms");

			//Add to dossier user based on service process role
			createDossierUsers(groupId, dossier, process, lstProcessRoles);
			
			if (Validator.isNotNull(input.getServerNo())) {
				dossier.setServerNo(input.getServerNo());
			}
			_log.debug("CREATE DOSSIER 7: " + (System.currentTimeMillis() - start) + " ms");
			return dossierLocalService.updateDossier(dossier);

		} else {
			List<Dossier> oldDossiers = dossierLocalService.getByU_G_GAC_SC_DTNO_DS_O(
					userId, groupId, input.getServiceCode(), input.getGovAgencyCode(), input.getDossierTemplateNo(), StringPool.BLANK, Integer.valueOf(input.getOriginality()));
			
			Dossier dossier = null;
			
			Dossier oldRefDossier = Validator.isNotNull(input.getReferenceUid()) ? dossierLocalService.getByRef(groupId, input.getReferenceUid()) : null;
			
			if (originality == DossierTerm.ORIGINALITY_DVCTT) {
				online = true;
			}
			boolean flagOldDossier = false;
			_log.debug("CREATE DOSSIER 2: " + (System.currentTimeMillis() - start) + " ms");
			
			if (oldRefDossier != null) {
				//Check old cross dossier rollback
				if (oldRefDossier.getOriginality() < 0 && Validator.isNotNull(input.getOriginDossierNo())) {
					oldRefDossier.setOriginality(-oldRefDossier.getOriginality());
				}
				dossier = oldRefDossier;
				dossier.setSubmitDate(new Date());
				dossier.setReceiveDate(new Date());
				ServiceProcess serviceProcess = process;
				
				double durationCount = 0;
				int durationUnit = 0;
				if (serviceProcess != null ) {
					durationCount = serviceProcess.getDurationCount();
					durationUnit = serviceProcess.getDurationUnit();
					dossier.setDurationCount(durationCount);
					dossier.setDurationUnit(durationUnit);
				}

				if (durationCount > 0) {
					Date dueDate = HolidayUtils.getDueDate(new Date(), durationCount, durationUnit, groupId);
					dossier.setDueDate(dueDate);
				}
				
			}
			else if (oldDossiers.size() > 0) {
				flagOldDossier = true;
				dossier = oldDossiers.get(0);
				dossier.setApplicantName(input.getApplicantName());
				dossier.setApplicantNote(input.getApplicantNote());
				dossier.setApplicantIdNo(input.getApplicantIdNo());
				dossier.setAddress(input.getAddress());
				dossier.setContactEmail(input.getContactEmail());
				dossier.setContactName(input.getContactName());
				dossier.setContactTelNo(input.getContactTelNo());
				dossier.setDelegateName(input.getDelegateName());
				dossier.setDelegateEmail(input.getDelegateEmail());
				dossier.setDelegateAddress(input.getDelegateAddress());
				dossier.setPostalAddress(input.getPostalAddress());
				dossier.setPostalCityCode(input.getPostalCityCode());
				dossier.setPostalCityName(postalCityName);
				dossier.setPostalTelNo(input.getPostalTelNo());
				dossier.setPostalServiceCode(input.getPostalServiceCode());
				dossier.setPostalServiceName(input.getPostalServiceName());
				dossier.setPostalDistrictCode(input.getPostalDistrictCode());
				dossier.setPostalDistrictName(input.getPostalDistrictName());
				dossier.setPostalWardCode(input.getPostalWardCode());
				dossier.setPostalWardName(input.getPostalWardName());
				
				dossier.setPostalTelNo(input.getPostalTelNo());
				dossier.setViaPostal(viaPostal);
				dossier.setOriginDossierNo(input.getOriginDossierNo());
				
				if (Validator.isNotNull(registerBookCode)) {
					dossier.setRegisterBookCode(registerBookCode);
				}
				dossier.setRegisterBookName(registerBookName);
				dossier.setSampleCount(sampleCount);
				dossier.setServiceCode(input.getServiceCode());
				dossier.setGovAgencyCode(input.getGovAgencyCode());
				dossier.setDossierTemplateNo(input.getDossierTemplateNo());
				
				updateDelegateApplicant(dossier, input);
				
//					dossier.setDossierNo(input.getDossierNo());
				dossier.setSubmitDate(new Date());
//					ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);
				ServiceProcess serviceProcess = process;
				
				double durationCount = 0;
				int durationUnit = 0;
				if (serviceProcess != null ) {
					durationCount = serviceProcess.getDurationCount();
					durationUnit = serviceProcess.getDurationUnit();
					dossier.setDurationCount(durationCount);
					dossier.setDurationUnit(durationUnit);
				}

				if (durationCount > 0) {
					Date dueDate = HolidayUtils.getDueDate(new Date(), durationCount, durationUnit, groupId);
					dossier.setDueDate(dueDate);
				}

				dossier.setOnline(online);
				if (Validator.isNotNull(input.getDossierName()))
					dossier.setDossierName(input.getDossierName());
				if (serviceProcess != null) {
					dossier.setProcessNo(serviceProcess.getProcessNo());
				}
				
//					dossier = DossierLocalServiceUtil.updateDossier(dossier);
			}
			else {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date appIdDate = null;

				try {
					appIdDate = sdf.parse(input.getApplicantIdDate());

				} catch (Exception e) {
					_log.debug(e);
				}
				dossier = dossierLocalService.initDossier(groupId, 0l, referenceUid, counter, input.getServiceCode(), serviceName,
						input.getGovAgencyCode(), govAgencyName, input.getApplicantName(), input.getApplicantIdType(),
						input.getApplicantIdNo(), appIdDate, input.getAddress(), input.getCityCode(),
						cityName, input.getDistrictCode(), districtName, input.getWardCode(), wardName,
						input.getContactName(), input.getContactTelNo(), input.getContactEmail(),
						input.getDossierTemplateNo(), password, viaPostal, input.getPostalAddress(), input.getPostalCityCode(), postalCityName,
						input.getPostalTelNo(), online, process.getDirectNotification(), input.getApplicantNote(),
						Integer.valueOf(input.getOriginality()), 
						service, process, option,
						serviceContext);
				dossier.setDelegateName(input.getDelegateName());
				dossier.setDelegateEmail(input.getDelegateEmail());
				dossier.setDelegateAddress(input.getDelegateAddress());
				if (Validator.isNotNull(input.getDossierName())) {
					dossier.setDossierName(input.getDossierName());
				} else {
					dossier.setDossierName(serviceName);
				}
				dossier.setPostalCityName(postalCityName);
				dossier.setPostalTelNo(input.getPostalTelNo());
				dossier.setPostalServiceCode(input.getPostalServiceCode());
				dossier.setPostalServiceName(input.getPostalServiceName());
				dossier.setPostalDistrictCode(input.getPostalDistrictCode());
				dossier.setPostalDistrictName(input.getPostalDistrictName());
				dossier.setPostalWardCode(input.getPostalWardCode());
				dossier.setPostalWardName(input.getPostalWardName());
				dossier.setOriginDossierNo(input.getOriginDossierNo());

				//dossier.setRegisterBookCode(registerBookCode);
				//dossier.setRegisterBookName(registerBookName);
				dossier.setSampleCount(sampleCount);
				if (Validator.isNotNull(input.getMetaData()))
					dossier.setMetaData(input.getMetaData());

				updateDelegateApplicant(dossier, input);
				
				//if (process != null) {
				//	dossier.setProcessNo(process.getProcessNo());
				//}
//					dossier = DossierLocalServiceUtil.updateDossier(dossier);
			}
			_log.debug("CREATE DOSSIER 3: " + (System.currentTimeMillis() - start) + " ms");

			if (originality != DossierTerm.ORIGINALITY_LIENTHONG) {
				Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(serviceContext.getUserId());
				if (applicant != null) {
					updateApplicantInfo(dossier, 
							applicant.getApplicantIdDate(),
							applicant.getApplicantIdNo(),
							applicant.getApplicantIdType(),
							applicant.getApplicantName(),
							applicant.getAddress(),
							applicant.getCityCode(),
							applicant.getCityName(),
							applicant.getDistrictCode(),
							applicant.getDistrictName(),
							applicant.getWardCode(),
							applicant.getWardName(),
							applicant.getContactEmail(),
							applicant.getContactTelNo()
							);
				}
			}
			if (Validator.isNull(dossier)) {
				throw new NotFoundException("Cant add DOSSIER");
			}

			_log.debug("CREATE DOSSIER 4: " + (System.currentTimeMillis() - start) + " ms");
			//Create DossierMark
			_log.debug("flagOldDossier: "+flagOldDossier);
			_log.debug("originality: "+originality);
			if ((originality == DossierTerm.ORIGINALITY_MOTCUA || originality == DossierTerm.ORIGINALITY_LIENTHONG)
					&& !flagOldDossier) {
				String templateNo = dossier.getDossierTemplateNo();
				_log.debug("templateNo: "+templateNo);
				if (Validator.isNotNull(templateNo)) {
					List<DossierPart> partList = dossierPartLocalService.getByTemplateNo(groupId, templateNo);
//						_log.info("partList: "+partList);
					if (partList != null && partList.size() > 0) {
						_log.debug("partList.size(): "+partList.size());
						_log.debug("CREATE DOSSIER 4.1: " + (System.currentTimeMillis() - start) + " ms");
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList.size()];
						int count = 0;
						List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId, dossier.getDossierId());
						Map<String, DossierMark> mapMarks = new HashMap<>();
						for (DossierMark dm : lstMarks) {
							mapMarks.put(dm.getDossierPartNo(), dm);
						}
						for (DossierPart dossierPart : partList) {
							int fileMark = dossierPart.getFileMark();
							String dossierPartNo = dossierPart.getPartNo();
							org.opencps.dossiermgt.input.model.DossierMarkBatchModel model = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel();
							model.setDossierId(dossier.getDossierId());
							model.setDossierPartNo(dossierPartNo);
							model.setFileCheck(0);
							model.setFileMark(fileMark);
							model.setFileComment(StringPool.BLANK);
							marks[count++] = model;
//								DossierMarkLocalServiceUtil.addDossierMark(groupId, dossier.getDossierId(), dossierPartNo,
//										fileMark, 0, StringPool.BLANK, serviceContext);
						}
						
						dossierMarkLocalService.addBatchDossierMark(groupId, marks, mapMarks, serviceContext);
						
						_log.debug("CREATE DOSSIER 4.2: " + (System.currentTimeMillis() - start) + " ms");
					}
				}
			}

			//Create dossier user
			List<DossierUser> lstDus = dossierUserLocalService.findByDID(dossier.getDossierId());
			List<ServiceProcessRole> lstProcessRoles = serviceProcessRoleLocalService.findByS_P_ID(process.getServiceProcessId());
			if (lstDus.size() == 0) {
				DossierUserActions duActions = new DossierUserActionsImpl();
//					duActions.initDossierUser(groupId, dossier);				
				duActions.initDossierUser(groupId, dossier, process, lstProcessRoles);				
			}
			
			if (originality == DossierTerm.ORIGINALITY_DVCTT) {
				dossierUserLocalService.addDossierUser(groupId, dossier.getDossierId(), userId, 1, true);
			}
			_log.debug("CREATE DOSSIER 5: " + (System.currentTimeMillis() - start) + " ms");

//				DossierLocalServiceUtil.updateDossier(dossier);

			if (dossier != null) {
				//
				long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());

				NotificationQueue queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
				//Process add notification queue
				Date now = new Date();

				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
				
				queue.setCreateDate(now);
				queue.setModifiedDate(now);
				queue.setGroupId(groupId);
				queue.setCompanyId(company.getCompanyId());
				
				queue.setNotificationType(NotificationType.DOSSIER_01);
				queue.setClassName(Dossier.class.getName());
				queue.setClassPK(String.valueOf(dossier.getPrimaryKey()));
				queue.setToUsername(dossier.getUserName());
				queue.setToUserId(dossier.getUserId());
				queue.setToEmail(dossier.getContactEmail());
				queue.setToTelNo(dossier.getContactTelNo());
				
				JSONObject payload = JSONFactoryUtil.createJSONObject();
				try {
//						_log.info("START PAYLOAD: ");
					payload.put(
						"Dossier", JSONFactoryUtil.createJSONObject(
							JSONFactoryUtil.looseSerialize(dossier)));
				}
				catch (JSONException parse) {
					_log.error(parse);
				}
//					_log.info("payloadTest: "+payload.toJSONString());
				queue.setPayload(payload.toJSONString());
				queue.setExpireDate(cal.getTime());

				NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
			}

			_log.debug("CREATE DOSSIER 6: " + (System.currentTimeMillis() - start) + " ms");
			//Add to dossier user based on service process role
			createDossierUsers(groupId, dossier, process, lstProcessRoles);
			if (Validator.isNotNull(input.getServerNo())) {
				dossier.setServerNo(input.getServerNo());
			}
			_log.debug("CREATE DOSSIER 7: " + (System.currentTimeMillis() - start) + " ms");
			dossierLocalService.updateDossier(dossier);
			_log.debug("CREATE DOSSIER 8: " + (System.currentTimeMillis() - start) + " ms");
			
			return dossier;
		}

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
	public Dossier addMultipleDossier(long groupId, Company company, User user, ServiceContext serviceContext,
			DossierMultipleInputModel input) throws UnauthenticationException, PortalException, Exception {

		BackendAuth auth = new BackendAuthImpl();
		DossierPermission dossierPermission = new DossierPermission();

		long start = System.currentTimeMillis();
		
		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		ProcessOption option = getProcessOption(input.getServiceCode(), input.getGovAgencyCode(),
				input.getDossierTemplateNo(), groupId);
		long serviceProcessId = 0;
		if (option != null) {
			serviceProcessId = option.getServiceProcessId();
		}

		boolean flag = false;
		long userId = serviceContext.getUserId();
		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
		if (employee != null) {
			long employeeId = employee.getEmployeeId();
			if (employeeId > 0) {
				List<EmployeeJobPos> empJobList = EmployeeJobPosLocalServiceUtil.findByF_EmployeeId(employeeId);
				if (empJobList != null && empJobList.size() > 0) {
					for (EmployeeJobPos employeeJobPos : empJobList) {
						long jobPosId = employeeJobPos.getJobPostId();
						if (jobPosId > 0) {
							JobPos job = JobPosLocalServiceUtil.fetchJobPos(jobPosId);
							if (job != null) {
								ServiceProcessRolePK pk = new ServiceProcessRolePK(serviceProcessId,
											job.getMappingRoleId());
								ServiceProcessRole role = serviceProcessRoleLocalService
											.fetchServiceProcessRole(pk);
								if (role != null && role.getModerator()) {
									flag = true;
									break;
								}
							}
						}
					}
				}
			}
		} else {
			flag = true;
		}
	
		if (!flag) {
			throw new UnauthenticationException("No permission create dossier");
		}
		_log.debug("CREATE DOSSIER 1: " + (System.currentTimeMillis() - start) + " ms");
		dossierPermission.hasCreateDossier(groupId, user.getUserId(), input.getServiceCode(),
				input.getGovAgencyCode(), input.getDossierTemplateNo());

		Dossier dossier = null;
		if (Validator.isNotNull(input.getDossiers())) {
			JSONObject jsonDossier = JSONFactoryUtil.createJSONObject(input.getDossiers());
			//Get params input dossier
			String referenceUid = jsonDossier.getString(DossierTerm.REFERENCE_UID);
			if (Validator.isNull(referenceUid) || referenceUid.trim().length() == 0)
				referenceUid = DossierNumberGenerator.generateReferenceUID(groupId);
			int counter = 0;
			//boolean online = GetterUtil.getBoolean(input.getOnline());
			boolean online = false;
			int originality = input.getOriginality();
			int viaPostal = Validator.isNotNull(jsonDossier.getString(DossierTerm.VIA_POSTAL))
					? GetterUtil.getInteger(jsonDossier.getString(DossierTerm.VIA_POSTAL)): 0;
			ServiceConfig config = serviceConfigLocalService.getBySICodeAndGAC(groupId, input.getServiceCode(),
					input.getGovAgencyCode());
			if (config != null && Validator.isNotNull(viaPostal)) {
				viaPostal = config.getPostService() ? (viaPostal == 0 ? 1 : viaPostal) : 0;
			} else if (config != null) {
				viaPostal = config.getPostService() ? 1 : 0;
			}
			//Get service process
			ServiceProcess process = null;
			if (option != null) {
				process = serviceProcessLocalService.getServiceProcess(serviceProcessId);
				if (process == null) {
					throw new NotFoundException("Cant find process");
				}
			}

			ServiceInfo service = serviceInfoLocalService.getByCode(groupId, input.getServiceCode());
			String serviceName = service != null ? service.getServiceName(): StringPool.BLANK;

			String govAgencyName = getDictItemName(groupId, GOVERNMENT_AGENCY, input.getGovAgencyCode());

			//DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(ADMINISTRATIVE_REGION, groupId);
			//String cityName = getDictItemName(groupId, dc, input.getCityCode());
			//String districtName = getDictItemName(groupId, dc, input.getDistrictCode());
			//String wardName = getDictItemName(groupId, dc, input.getWardCode());
//				_log.info("Service code: " + input.getServiceCode());
			//_log.debug("===ADD DOSSIER CITY NAME:" + cityName);
			String password = StringPool.BLANK;
			if (Validator.isNotNull(jsonDossier.getString("password"))) {
				password = jsonDossier.getString("password");
			} else if (Validator.isNotNull(process.getGeneratePassword()) && process.getGeneratePassword()) {
				password = PwdGenerator.getPinNumber();
			}

			String postalCityName = StringPool.BLANK;
			if (Validator.isNotNull(jsonDossier.getString(DossierTerm.POSTAL_CITY_CODE))) {
				postalCityName = getDictItemName(groupId, VNPOST_CITY_CODE, jsonDossier.getString(DossierTerm.POSTAL_CITY_CODE));
			}
			int sampleCount = (option != null ? (int) option.getSampleCount() : 1);

			String registerBookCode = (option != null
					? (Validator.isNotNull(option.getRegisterBookCode()) ? option.getRegisterBookCode()
							: StringPool.BLANK) : StringPool.BLANK);
			String registerBookName = (Validator.isNotNull(registerBookCode)
					? getDictItemName(groupId, REGISTER_BOOK, registerBookCode) : StringPool.BLANK);
			_log.debug("CREATE DOSSIER 2: " + (System.currentTimeMillis() - start) + " ms");
			
			Long appIdDateLong = jsonDossier.getLong(DossierTerm.APPLICANT_ID_DATE);
			Date appIdDate = null;
			if (appIdDateLong > 0) {
				appIdDate = new Date(appIdDateLong);
			}

			// Params add dossier
			String applicantName = jsonDossier.getString(DossierTerm.APPLICANT_NAME);
			String applicantIdType = jsonDossier.getString(DossierTerm.APPLICANT_ID_TYPE);
			String applicantIdNo = jsonDossier.getString(DossierTerm.APPLICANT_ID_NO);
			String address = jsonDossier.getString(DossierTerm.ADDRESS);
			String contactName = jsonDossier.getString(DossierTerm.CONTACT_NAME);
			String contactTelNo = jsonDossier.getString(DossierTerm.CONTACT_TEL_NO);
			String contactEmail = jsonDossier.getString(DossierTerm.CONTACT_EMAIL);
			//
			String postalServiceCode = jsonDossier.getString(DossierTerm.POSTAL_SERVICE_CODE);
			String postalServiceName = jsonDossier.getString(DossierTerm.POSTAL_SERVICE_NAME);
			String postalAddress = jsonDossier.getString(DossierTerm.POSTAL_ADDRESS);
			String postalCityCode = jsonDossier.getString(DossierTerm.POSTAL_CITY_CODE);
			String postalDistrictCode = jsonDossier.getString(DossierTerm.POSTAL_DISTRICT_CODE);
			String postalDistrictName = jsonDossier.getString(DossierTerm.POSTAL_DISTRICT_NAME);
			String postalWardCode = jsonDossier.getString(DossierTerm.POSTAL_WARD_CODE);
			String postalWardName = jsonDossier.getString(DossierTerm.POSTAL_WARD_NAME);
			String postalTelNo = jsonDossier.getString(DossierTerm.POSTAL_TEL_NO);
			String applicantNote = jsonDossier.getString(DossierTerm.APPLICANT_NOTE);
			String delegateIdNo = jsonDossier.getString(DossierTerm.DELEGATE_ID_NO);
			String delegateName = jsonDossier.getString(DossierTerm.DELEGATE_NAME);
			String delegateTelNo = jsonDossier.getString(DossierTerm.DELEGATE_TELNO);
			String delegateEmail = jsonDossier.getString(DossierTerm.DELEGATE_EMAIL);
			String delegateAddress = jsonDossier.getString(DossierTerm.DELEGATE_ADDRESS);
			//TODO
			String delegateCityCode = jsonDossier.getString(DossierTerm.DELEGATE_CITYCODE);
			String delegateCityName = StringPool.BLANK;
			if (Validator.isNotNull(delegateCityCode)) {
				delegateCityName = getDictItemName(groupId, ADMINISTRATIVE_REGION, delegateCityCode);
			}

			String delegateDistrictCode = jsonDossier.getString(DossierTerm.DELEGATE_DISTRICTCODE);
			String delegateDistrictName = StringPool.BLANK;
			if (Validator.isNotNull(delegateDistrictCode)) {
				delegateDistrictName = getDictItemName(groupId, ADMINISTRATIVE_REGION, delegateDistrictCode);
			}

			String delegateWardCode = jsonDossier.getString(DossierTerm.DELEGATE_WARDCODE);
			String delegateWardName = StringPool.BLANK;
			if (Validator.isNotNull(delegateWardCode)) {
				delegateWardName = getDictItemName(groupId, ADMINISTRATIVE_REGION, delegateWardCode);
			}
			//
			String dossierName = Validator.isNotNull(jsonDossier.getString(DossierTerm.DOSSIER_NAME)) ? jsonDossier.getString(DossierTerm.DOSSIER_NAME) : serviceName;
			
			Long receiveDateTimeStamp = jsonDossier.getLong(DossierTerm.RECEIVE_DATE);
			Date receiveDate = null;
			if (receiveDateTimeStamp > 0) {
				receiveDate = new Date(receiveDateTimeStamp);
			}

			Long dueDateTimeStamp = jsonDossier.getLong(DossierTerm.DUE_DATE);
			Date dueDate = null;
			if (dueDateTimeStamp > 0) {
				dueDate = new Date(dueDateTimeStamp);
			}
			
			String metaData = jsonDossier.getString(DossierTerm.META_DATA);

			dossier = dossierLocalService.initMultipleDossier(groupId, 0l, referenceUid, counter,
					input.getServiceCode(), serviceName, input.getGovAgencyCode(), govAgencyName, applicantName,
					applicantIdType, applicantIdNo, appIdDate, address, contactName, contactTelNo, contactEmail,
					input.getDossierTemplateNo(), password, viaPostal, postalServiceCode, postalServiceName,
					postalAddress, postalCityCode, postalCityName, postalDistrictCode, postalDistrictName,
					postalWardCode, postalWardName, postalTelNo, online, process.getDirectNotification(), applicantNote,
					input.getOriginality(), delegateIdNo, delegateName, delegateTelNo, delegateEmail, delegateAddress,
					delegateCityCode, delegateCityName, delegateDistrictCode, delegateDistrictName, delegateWardCode,
					delegateWardName, registerBookCode, registerBookName, sampleCount, dossierName, service, process,
					option, serviceContext);

				if (receiveDate != null)
					dossier.setReceiveDate(receiveDate);
				if (dueDate != null)
					dossier.setDueDate(dueDate);
				if (Validator.isNotNull(metaData))
					dossier.setMetaData(metaData);

			//TODO: Process then
			//updateDelegateApplicant(dossier, input);
			_log.debug("CREATE DOSSIER 3: " + (System.currentTimeMillis() - start) + " ms");

			if (originality != DossierTerm.ORIGINALITY_LIENTHONG) {
				Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(serviceContext.getUserId());
				if (applicant != null) {
					updateApplicantInfo(dossier, 
							applicant.getApplicantIdDate(),
							applicant.getApplicantIdNo(),
							applicant.getApplicantIdType(),
							applicant.getApplicantName(),
							applicant.getAddress(),
							applicant.getCityCode(),
							applicant.getCityName(),
							applicant.getDistrictCode(),
							applicant.getDistrictName(),
							applicant.getWardCode(),
							applicant.getWardName(),
							applicant.getContactEmail(),
							applicant.getContactTelNo()
							);
				}
			}
			if (Validator.isNull(dossier)) {
				throw new NotFoundException("Cant add DOSSIER");
			}

			_log.debug("CREATE DOSSIER 4: " + (System.currentTimeMillis() - start) + " ms");
			//TODO
			/** Create DossierMark */
			//_log.debug("flagOldDossier: "+flagOldDossier);
			_log.debug("originality: "+originality);
			String templateNo = dossier.getDossierTemplateNo();
			_log.debug("templateNo: "+templateNo);
			long dossierId = dossier.getDossierId();
			if (originality == DossierTerm.ORIGINALITY_MOTCUA || originality == DossierTerm.ORIGINALITY_LIENTHONG) {
				if (Validator.isNotNull(input.getDossierMarkArr())) {
					JSONArray markArr = JSONFactoryUtil.createJSONArray(input.getDossierMarkArr());
					if (markArr != null && markArr.length() > 0) {
						List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId, dossierId);
						Map<String, DossierMark> mapMarks = new HashMap<>();
						for (DossierMark dm : lstMarks) {
							mapMarks.put(dm.getDossierPartNo(), dm);
						}

						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[markArr.length()];
						for (int i = 0; i < markArr.length(); i++) {
							JSONObject jsonMark = markArr.getJSONObject(i);
							//System.out.println("jsonMark: "+jsonMark);

							org.opencps.dossiermgt.input.model.DossierMarkBatchModel model = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel();
							model.setDossierId(dossier.getDossierId());
							model.setDossierPartNo(jsonMark.getString("partNo"));
							model.setFileCheck(0);
							model.setFileMark(jsonMark.getInt("fileMark"));
							model.setFileComment(StringPool.BLANK);
							model.setRecordCount(StringPool.BLANK);
							marks[i] = model;

						}
						dossierMarkLocalService.addBatchDossierMark(groupId, marks, mapMarks, serviceContext);
					}
				} else if (Validator.isNotNull(templateNo)) {
					List<DossierPart> partList = dossierPartLocalService.getByTemplateNo(groupId, templateNo);
//							_log.info("partList: "+partList);
					if (partList != null && partList.size() > 0) {
						_log.debug("partList.size(): "+partList.size());
						_log.debug("CREATE DOSSIER 4.1: " + (System.currentTimeMillis() - start) + " ms");
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList.size()];
						int count = 0;
						List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId, dossierId);
						Map<String, DossierMark> mapMarks = new HashMap<>();
						for (DossierMark dm : lstMarks) {
							mapMarks.put(dm.getDossierPartNo(), dm);
						}
						for (DossierPart dossierPart : partList) {
							int fileMark = dossierPart.getFileMark();
							String dossierPartNo = dossierPart.getPartNo();
							org.opencps.dossiermgt.input.model.DossierMarkBatchModel model = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel();
							model.setDossierId(dossier.getDossierId());
							model.setDossierPartNo(dossierPartNo);
							model.setFileCheck(0);
							model.setFileMark(fileMark);
							model.setFileComment(StringPool.BLANK);
							model.setRecordCount(StringPool.BLANK);
							marks[count++] = model;
						}
						
						dossierMarkLocalService.addBatchDossierMark(groupId, marks, mapMarks, serviceContext);
						
						_log.debug("CREATE DOSSIER 4.2: " + (System.currentTimeMillis() - start) + " ms");
					}
				}
			}

			/** 
			 * Add dossier file
			 */
			String strDossierFileId = input.getDossierFileArr();
			if (Validator.isNotNull(strDossierFileId)) {
				String[] splitDossierFileId = strDossierFileId.split(StringPool.COMMA);
				if (splitDossierFileId != null && splitDossierFileId.length > 0) {
					for (String strFileId : splitDossierFileId) {
						processCloneDossierFile(Long.valueOf(strFileId), dossier.getDossierId(), userId);
					}
				}
			}

			/**Create dossier user */
			List<DossierUser> lstDus = dossierUserLocalService.findByDID(dossier.getDossierId());
			List<ServiceProcessRole> lstProcessRoles = serviceProcessRoleLocalService.findByS_P_ID(process.getServiceProcessId());
			if (lstDus.size() == 0) {
				DossierUserActions duActions = new DossierUserActionsImpl();
				duActions.initDossierUser(groupId, dossier, process, lstProcessRoles);
			}

			if (originality == DossierTerm.ORIGINALITY_DVCTT) {
				dossierUserLocalService.addDossierUser(groupId, dossier.getDossierId(), userId, 1, true);
			}
			_log.debug("CREATE DOSSIER 5: " + (System.currentTimeMillis() - start) + " ms");

//			if (dossier != null) {
//				//
//				long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());
//
//				NotificationQueue queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
//				//Process add notification queue
//				Date now = new Date();
//
//				Calendar cal = Calendar.getInstance();
//				cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
//				
//				queue.setCreateDate(now);
//				queue.setModifiedDate(now);
//				queue.setGroupId(groupId);
//				queue.setCompanyId(company.getCompanyId());
//				
//				queue.setNotificationType(NotificationType.DOSSIER_01);
//				queue.setClassName(Dossier.class.getName());
//				queue.setClassPK(String.valueOf(dossier.getPrimaryKey()));
//				queue.setToUsername(dossier.getUserName());
//				queue.setToUserId(dossier.getUserId());
//				queue.setToEmail(dossier.getContactEmail());
//				queue.setToTelNo(dossier.getContactTelNo());
//				
//				JSONObject payload = JSONFactoryUtil.createJSONObject();
//				try {
////							_log.info("START PAYLOAD: ");
//					payload.put(
//						"Dossier", JSONFactoryUtil.createJSONObject(
//							JSONFactoryUtil.looseSerialize(dossier)));
//				}
//				catch (JSONException parse) {
//					_log.error(parse);
//				}
////						_log.info("payloadTest: "+payload.toJSONString());
//				queue.setPayload(payload.toJSONString());
//				queue.setExpireDate(cal.getTime());
//
//				NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
//			}
	
			_log.debug("CREATE DOSSIER 6: " + (System.currentTimeMillis() - start) + " ms");
			//Add to dossier user based on service process role
			createDossierUsers(groupId, dossier, process, lstProcessRoles);
			
			_log.debug("CREATE DOSSIER 7: " + (System.currentTimeMillis() - start) + " ms");
			dossierLocalService.updateDossier(dossier);
			_log.debug("CREATE DOSSIER 8: " + (System.currentTimeMillis() - start) + " ms");
			
			String payload = StringPool.BLANK;
			String actionCode = "1100";
			if (dossier.getReceiveDate() == null) {
				JSONObject jsonDate = JSONFactoryUtil.createJSONObject();
				jsonDate.put(DossierTerm.RECEIVE_DATE, (new Date()).getTime());
				
				Double durationCount = process.getDurationCount();
				if (Validator.isNotNull(String.valueOf(durationCount)) && durationCount > 0d) {
					Date dueDateCal = HolidayUtils.getDueDate(new Date(), process.getDurationCount(),
							process.getDurationUnit(), groupId);
					jsonDate.put(DossierTerm.DUE_DATE, dueDateCal != null ? dueDateCal.getTime() : 0);
				}
				if (Validator.isNotNull(jsonDate)) {
					payload = jsonDate.toJSONString();
				}
			}
			//
			ProcessAction proAction = getProcessAction(groupId, dossier, actionCode,
					serviceProcessId);
			doAction(groupId, userId, dossier, option, proAction, actionCode, StringPool.BLANK, StringPool.BLANK,
					payload, StringPool.BLANK, input.getPayment(), 0, serviceContext);

		}
		return dossier;
	}

	private void processCloneDossierFile(Long dossierFileId, long dossierId, long userId) throws PortalException {

		DossierFile dossierFileParent = dossierFileLocalService.fetchDossierFile(dossierFileId);

		if (dossierFileParent != null) {

			long dossierFileChildrenId = counterLocalService.increment(DossierFile.class.getName());

			DossierFile object = dossierFilePersistence.create(dossierFileChildrenId);

			_log.debug("****End uploadFile file at:" + new Date());

			Date now = new Date();
			User userAction = null;
			if (userId != 0) {
				userAction = userLocalService.getUser(userId);
			}

			// Add audit fields
			object.setCompanyId(dossierFileParent.getCompanyId());
			object.setGroupId(dossierFileParent.getGroupId());
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userAction != null ? userAction.getUserId() : 0l);
			object.setUserName(userAction != null ? userAction.getFullName() : StringPool.BLANK);

			// Add other fields
			object.setDossierId(dossierId);
			object.setReferenceUid(PortalUUIDUtil.generate());
			object.setDossierTemplateNo(dossierFileParent.getDossierTemplateNo());
			object.setFileEntryId(dossierFileParent.getFileEntryId());
			object.setDossierPartNo(dossierFileParent.getDossierPartNo());
			object.setFileTemplateNo(dossierFileParent.getFileTemplateNo());
			object.setDossierPartType(dossierFileParent.getDossierPartType());

			_log.debug("****Start autofill file at:" + new Date());

			object.setDisplayName(dossierFileParent.getDisplayName());
			object.setOriginal(false);
			object.setIsNew(true);
			object.setFormData(dossierFileParent.getFormData());
			object.setEForm(dossierFileParent.getEForm());
			object.setRemoved(false);
			object.setSignCheck(dossierFileParent.getSignCheck());
			object.setFormScript(dossierFileParent.getFormScript());
			object.setFormReport(dossierFileParent.getFormReport());
			object.setFormSchema(dossierFileParent.getFormSchema());

			dossierFilePersistence.update(object);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
	public Dossier addFullDossier(long groupId, Company company, User user, ServiceContext serviceContext,
			DossierMultipleInputModel input) throws UnauthenticationException, PortalException, Exception {

		BackendAuth auth = new BackendAuthImpl();
		DossierPermission dossierPermission = new DossierPermission();

		long start = System.currentTimeMillis();
		
		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		ProcessOption option = getProcessOption(input.getServiceCode(), input.getGovAgencyCode(),
				input.getDossierTemplateNo(), groupId);
		long serviceProcessId = 0;
		if (option != null) {
			serviceProcessId = option.getServiceProcessId();
		}

		boolean flag = false;
		long userId = serviceContext.getUserId();
		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
		if (employee != null) {
			long employeeId = employee.getEmployeeId();
			if (employeeId > 0) {
				List<EmployeeJobPos> empJobList = EmployeeJobPosLocalServiceUtil.findByF_EmployeeId(employeeId);
				if (empJobList != null && empJobList.size() > 0) {
					for (EmployeeJobPos employeeJobPos : empJobList) {
						long jobPosId = employeeJobPos.getJobPostId();
						if (jobPosId > 0) {
							JobPos job = JobPosLocalServiceUtil.fetchJobPos(jobPosId);
							if (job != null) {
								ServiceProcessRolePK pk = new ServiceProcessRolePK(serviceProcessId,
											job.getMappingRoleId());
								ServiceProcessRole role = serviceProcessRoleLocalService
											.fetchServiceProcessRole(pk);
								if (role != null && role.getModerator()) {
									flag = true;
									break;
								}
							}
						}
					}
				}
			}
		} else {
			flag = true;
		}
	
		if (!flag) {
			throw new UnauthenticationException("No permission create dossier");
		}
		_log.debug("CREATE DOSSIER 1: " + (System.currentTimeMillis() - start) + " ms");
		dossierPermission.hasCreateDossier(groupId, user.getUserId(), input.getServiceCode(),
				input.getGovAgencyCode(), input.getDossierTemplateNo());

		Dossier dossier = null;
		if (Validator.isNotNull(input.getDossiers())) {
			JSONObject jsonDossier = JSONFactoryUtil.createJSONObject(input.getDossiers());
			//Get params input dossier
			String referenceUid = jsonDossier.getString(DossierTerm.REFERENCE_UID);
			if (Validator.isNull(referenceUid) || referenceUid.trim().length() == 0)
				referenceUid = DossierNumberGenerator.generateReferenceUID(groupId);
			int counter = 0;
			//boolean online = GetterUtil.getBoolean(input.getOnline());
			boolean online = false;
			int originality = input.getOriginality();
			int viaPostal = Validator.isNotNull(jsonDossier.getString(DossierTerm.VIA_POSTAL))
					? GetterUtil.getInteger(jsonDossier.getString(DossierTerm.VIA_POSTAL)): 0;
			ServiceConfig config = serviceConfigLocalService.getBySICodeAndGAC(groupId, input.getServiceCode(),
					input.getGovAgencyCode());
			if (config != null && Validator.isNotNull(viaPostal)) {
				viaPostal = config.getPostService() ? (viaPostal == 0 ? 1 : viaPostal) : 0;
			} else if (config != null) {
				viaPostal = config.getPostService() ? 1 : 0;
			}
			//Get service process
			ServiceProcess process = null;
			if (option != null) {
				process = serviceProcessLocalService.getServiceProcess(serviceProcessId);
				if (process == null) {
					throw new NotFoundException("Cant find process");
				}
			}

			ServiceInfo service = serviceInfoLocalService.getByCode(groupId, input.getServiceCode());
			String serviceName = service != null ? service.getServiceName(): StringPool.BLANK;

			String govAgencyName = getDictItemName(groupId, GOVERNMENT_AGENCY, input.getGovAgencyCode());

			//DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(ADMINISTRATIVE_REGION, groupId);
			//String cityName = getDictItemName(groupId, dc, input.getCityCode());
			//String districtName = getDictItemName(groupId, dc, input.getDistrictCode());
			//String wardName = getDictItemName(groupId, dc, input.getWardCode());
//				_log.info("Service code: " + input.getServiceCode());
			//_log.debug("===ADD DOSSIER CITY NAME:" + cityName);
			String password = StringPool.BLANK;
			if (Validator.isNotNull(jsonDossier.getString("password"))) {
				password = jsonDossier.getString("password");
			} else if (Validator.isNotNull(process.getGeneratePassword()) && process.getGeneratePassword()) {
				password = PwdGenerator.getPinNumber();
			}

			String postalCityName = StringPool.BLANK;
			if (Validator.isNotNull(jsonDossier.getString(DossierTerm.POSTAL_CITY_CODE))) {
				postalCityName = getDictItemName(groupId, VNPOST_CITY_CODE, jsonDossier.getString(DossierTerm.POSTAL_CITY_CODE));
			}
			int sampleCount = (option != null ? (int) option.getSampleCount() : 1);

			String registerBookCode = (option != null
					? (Validator.isNotNull(option.getRegisterBookCode()) ? option.getRegisterBookCode()
							: StringPool.BLANK) : StringPool.BLANK);
			String registerBookName = (Validator.isNotNull(registerBookCode)
					? getDictItemName(groupId, REGISTER_BOOK, registerBookCode) : StringPool.BLANK);
			_log.debug("CREATE DOSSIER 2: " + (System.currentTimeMillis() - start) + " ms");
			
			Long appIdDateLong = jsonDossier.getLong(DossierTerm.APPLICANT_ID_DATE);
			Date appIdDate = null;
			if (appIdDateLong > 0) {
				appIdDate = new Date(appIdDateLong);
			}

			// Params add dossier
			String applicantName = jsonDossier.getString(DossierTerm.APPLICANT_NAME);
			String applicantIdType = jsonDossier.getString(DossierTerm.APPLICANT_ID_TYPE);
			String applicantIdNo = jsonDossier.getString(DossierTerm.APPLICANT_ID_NO);
			String address = jsonDossier.getString(DossierTerm.ADDRESS);
			String contactName = jsonDossier.getString(DossierTerm.CONTACT_NAME);
			String contactTelNo = jsonDossier.getString(DossierTerm.CONTACT_TEL_NO);
			String contactEmail = jsonDossier.getString(DossierTerm.CONTACT_EMAIL);
			//
			String postalServiceCode = jsonDossier.getString(DossierTerm.POSTAL_SERVICE_CODE);
			String postalServiceName = jsonDossier.getString(DossierTerm.POSTAL_SERVICE_NAME);
			String postalAddress = jsonDossier.getString(DossierTerm.POSTAL_ADDRESS);
			String postalCityCode = jsonDossier.getString(DossierTerm.POSTAL_CITY_CODE);
			String postalDistrictCode = jsonDossier.getString(DossierTerm.POSTAL_DISTRICT_CODE);
			String postalDistrictName = jsonDossier.getString(DossierTerm.POSTAL_DISTRICT_NAME);
			String postalWardCode = jsonDossier.getString(DossierTerm.POSTAL_WARD_CODE);
			String postalWardName = jsonDossier.getString(DossierTerm.POSTAL_WARD_NAME);
			String postalTelNo = jsonDossier.getString(DossierTerm.POSTAL_TEL_NO);
			String applicantNote = jsonDossier.getString(DossierTerm.APPLICANT_NOTE);
			String delegateIdNo = jsonDossier.getString(DossierTerm.DELEGATE_ID_NO);
			String delegateName = jsonDossier.getString(DossierTerm.DELEGATE_NAME);
			String delegateTelNo = jsonDossier.getString(DossierTerm.DELEGATE_TELNO);
			String delegateEmail = jsonDossier.getString(DossierTerm.DELEGATE_EMAIL);
			String delegateAddress = jsonDossier.getString(DossierTerm.DELEGATE_ADDRESS);
			//TODO
			String delegateCityCode = jsonDossier.getString(DossierTerm.DELEGATE_CITYCODE);
			String delegateCityName = StringPool.BLANK;
			if (Validator.isNotNull(delegateCityCode)) {
				delegateCityName = getDictItemName(groupId, ADMINISTRATIVE_REGION, delegateCityCode);
			}

			String delegateDistrictCode = jsonDossier.getString(DossierTerm.DELEGATE_DISTRICTCODE);
			String delegateDistrictName = StringPool.BLANK;
			if (Validator.isNotNull(delegateDistrictCode)) {
				delegateDistrictName = getDictItemName(groupId, ADMINISTRATIVE_REGION, delegateDistrictCode);
			}

			String delegateWardCode = jsonDossier.getString(DossierTerm.DELEGATE_WARDCODE);
			String delegateWardName = StringPool.BLANK;
			if (Validator.isNotNull(delegateWardCode)) {
				delegateWardName = getDictItemName(groupId, ADMINISTRATIVE_REGION, delegateWardCode);
			}
			//
			String dossierName = Validator.isNotNull(jsonDossier.getString(DossierTerm.DOSSIER_NAME)) ? jsonDossier.getString(DossierTerm.DOSSIER_NAME) : serviceName;
			
			Long receiveDateTimeStamp = jsonDossier.getLong(DossierTerm.RECEIVE_DATE);
			Date receiveDate = null;
			if (receiveDateTimeStamp > 0) {
				receiveDate = new Date(receiveDateTimeStamp);
			}

			Long dueDateTimeStamp = jsonDossier.getLong(DossierTerm.DUE_DATE);
			Date dueDate = null;
			if (dueDateTimeStamp > 0) {
				dueDate = new Date(dueDateTimeStamp);
			}
			
			String metaData = jsonDossier.getString(DossierTerm.META_DATA);

			dossier = dossierLocalService.initMultipleDossier(groupId, 0l, referenceUid, counter,
					input.getServiceCode(), serviceName, input.getGovAgencyCode(), govAgencyName, applicantName,
					applicantIdType, applicantIdNo, appIdDate, address, contactName, contactTelNo, contactEmail,
					input.getDossierTemplateNo(), password, viaPostal, postalServiceCode, postalServiceName,
					postalAddress, postalCityCode, postalCityName, postalDistrictCode, postalDistrictName,
					postalWardCode, postalWardName, postalTelNo, online, process.getDirectNotification(), applicantNote,
					input.getOriginality(), delegateIdNo, delegateName, delegateTelNo, delegateEmail, delegateAddress,
					delegateCityCode, delegateCityName, delegateDistrictCode, delegateDistrictName, delegateWardCode,
					delegateWardName, registerBookCode, registerBookName, sampleCount, dossierName, service, process,
					option, serviceContext);

				if (receiveDate != null)
					dossier.setReceiveDate(receiveDate);
				if (dueDate != null)
					dossier.setDueDate(dueDate);
				if (Validator.isNotNull(metaData))
					dossier.setMetaData(metaData);

			//TODO: Process then
			//updateDelegateApplicant(dossier, input);
			_log.debug("CREATE DOSSIER 3: " + (System.currentTimeMillis() - start) + " ms");

			if (originality != DossierTerm.ORIGINALITY_LIENTHONG) {
				Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(serviceContext.getUserId());
				if (applicant != null) {
					updateApplicantInfo(dossier, 
							applicant.getApplicantIdDate(),
							applicant.getApplicantIdNo(),
							applicant.getApplicantIdType(),
							applicant.getApplicantName(),
							applicant.getAddress(),
							applicant.getCityCode(),
							applicant.getCityName(),
							applicant.getDistrictCode(),
							applicant.getDistrictName(),
							applicant.getWardCode(),
							applicant.getWardName(),
							applicant.getContactEmail(),
							applicant.getContactTelNo()
							);
				}
			}
			if (Validator.isNull(dossier)) {
				throw new NotFoundException("Cant add DOSSIER");
			}

			_log.debug("CREATE DOSSIER 4: " + (System.currentTimeMillis() - start) + " ms");
			/** Create DossierMark */
			//_log.debug("flagOldDossier: "+flagOldDossier);
			_log.debug("originality: "+originality);
			String templateNo = dossier.getDossierTemplateNo();
			_log.debug("templateNo: "+templateNo);
			long dossierId = dossier.getDossierId();
			if (originality == DossierTerm.ORIGINALITY_MOTCUA || originality == DossierTerm.ORIGINALITY_LIENTHONG) {
				if (Validator.isNotNull(input.getDossierMarkArr())) {
					JSONArray markArr = JSONFactoryUtil.createJSONArray(input.getDossierMarkArr());
					if (markArr != null && markArr.length() > 0) {
						List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId, dossierId);
						Map<String, DossierMark> mapMarks = new HashMap<>();
						for (DossierMark dm : lstMarks) {
							mapMarks.put(dm.getDossierPartNo(), dm);
						}

						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[markArr.length()];
						for (int i = 0; i < markArr.length(); i++) {
							JSONObject jsonMark = markArr.getJSONObject(i);
							//System.out.println("jsonMark: "+jsonMark);

							org.opencps.dossiermgt.input.model.DossierMarkBatchModel model = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel();
							model.setDossierId(dossier.getDossierId());
							model.setDossierPartNo(jsonMark.getString("partNo"));
							model.setFileCheck(0);
							model.setFileMark(jsonMark.getInt("fileMark"));
							model.setFileComment(StringPool.BLANK);
							model.setRecordCount(StringPool.BLANK);
							marks[i] = model;

						}
						dossierMarkLocalService.addBatchDossierMark(groupId, marks, mapMarks, serviceContext);
					}
				} else if (Validator.isNotNull(templateNo)) {
					List<DossierPart> partList = dossierPartLocalService.getByTemplateNo(groupId, templateNo);
//							_log.info("partList: "+partList);
					if (partList != null && partList.size() > 0) {
						_log.debug("partList.size(): "+partList.size());
						_log.debug("CREATE DOSSIER 4.1: " + (System.currentTimeMillis() - start) + " ms");
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList.size()];
						int count = 0;
						List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId, dossierId);
						Map<String, DossierMark> mapMarks = new HashMap<>();
						for (DossierMark dm : lstMarks) {
							mapMarks.put(dm.getDossierPartNo(), dm);
						}
						for (DossierPart dossierPart : partList) {
							int fileMark = dossierPart.getFileMark();
							String dossierPartNo = dossierPart.getPartNo();
							org.opencps.dossiermgt.input.model.DossierMarkBatchModel model = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel();
							model.setDossierId(dossier.getDossierId());
							model.setDossierPartNo(dossierPartNo);
							model.setFileCheck(0);
							model.setFileMark(fileMark);
							model.setFileComment(StringPool.BLANK);
							model.setRecordCount(StringPool.BLANK);
							marks[count++] = model;
						}
						
						dossierMarkLocalService.addBatchDossierMark(groupId, marks, mapMarks, serviceContext);
						
						_log.debug("CREATE DOSSIER 4.2: " + (System.currentTimeMillis() - start) + " ms");
					}
				}
			}

			/** 
			 * Add dossier file
			 */
			if (Validator.isNotNull(input.getDossierFileArr())) {
				JSONArray dossierFileArr = JSONFactoryUtil.createJSONArray(input.getDossierFileArr());
				if (dossierFileArr != null && dossierFileArr.length() > 0) {

					for (int j = 0; j < dossierFileArr.length(); j++) {
						JSONObject jsonFile = dossierFileArr.getJSONObject(j);
						System.out.println("jsonFile: "+jsonFile.getString("eform"));
						boolean eform = Boolean.valueOf(jsonFile.getString("eform"));
						System.out.println("eform"+eform);
						if (eform) {
							//EFORM
							_log.info("In dossier file create by eform");
							try {
//								String referenceUidFile = UUID.randomUUID().toString();
								String partNo = jsonFile.getString(DossierPartTerm.PART_NO);
								String formData = jsonFile.getString("formData");
								DossierFile dossierFile = null;
//								DossierFileActions action = new DossierFileActionsImpl();
								DossierPart dossierPart = dossierPartLocalService.fetchByTemplatePartNo(groupId, templateNo, partNo);
								//_log.info("__file:" + file);
								//DataHandler dataHandler = (file != null) ? file.getDataHandler() : null;
								dossierFile = DossierFileLocalServiceUtil.getByGID_DID_PART_EFORM(groupId, dossierId,
										partNo, true, false);
								if (dossierFile == null) {
									_log.info("dossierFile NULL");
									dossierFile = dossierFileLocalService.addDossierFileEForm(groupId, dossierId, referenceUid,
											templateNo, partNo, dossierPart.getFileTemplateNo(), dossierPart.getPartName(), dossierPart.getPartName(), 0,
											null, StringPool.BLANK, "true", serviceContext);
								}
								
								if(Validator.isNotNull(formData)) {
									dossierFile.setFormData(formData);
								}
								if(Validator.isNotNull(eform)) {
									dossierFile.setEForm(eform);
								}

								_log.info("__Start update dossier file at:" + new Date());
								DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

								dossierFileLocalService.updateFormData(groupId, dossierId, dossierFile.getReferenceUid(), formData,
										serviceContext);
								_log.info("__End update dossier file at:" + new Date());

							} catch (Exception e) {
								_log.debug(e);
							}
						}
					}
				}
			}

			/**Create dossier user */
			List<DossierUser> lstDus = dossierUserLocalService.findByDID(dossier.getDossierId());
			List<ServiceProcessRole> lstProcessRoles = serviceProcessRoleLocalService.findByS_P_ID(process.getServiceProcessId());
			if (lstDus.size() == 0) {
				DossierUserActions duActions = new DossierUserActionsImpl();
				duActions.initDossierUser(groupId, dossier, process, lstProcessRoles);
			}

			if (originality == DossierTerm.ORIGINALITY_DVCTT) {
				dossierUserLocalService.addDossierUser(groupId, dossier.getDossierId(), userId, 1, true);
			}
			_log.debug("CREATE DOSSIER 5: " + (System.currentTimeMillis() - start) + " ms");

//			if (dossier != null) {
//				//
//				long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());
//
//				NotificationQueue queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
//				//Process add notification queue
//				Date now = new Date();
//
//				Calendar cal = Calendar.getInstance();
//				cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
//				
//				queue.setCreateDate(now);
//				queue.setModifiedDate(now);
//				queue.setGroupId(groupId);
//				queue.setCompanyId(company.getCompanyId());
//				
//				queue.setNotificationType(NotificationType.DOSSIER_01);
//				queue.setClassName(Dossier.class.getName());
//				queue.setClassPK(String.valueOf(dossier.getPrimaryKey()));
//				queue.setToUsername(dossier.getUserName());
//				queue.setToUserId(dossier.getUserId());
//				queue.setToEmail(dossier.getContactEmail());
//				queue.setToTelNo(dossier.getContactTelNo());
//				
//				JSONObject payload = JSONFactoryUtil.createJSONObject();
//				try {
////							_log.info("START PAYLOAD: ");
//					payload.put(
//						"Dossier", JSONFactoryUtil.createJSONObject(
//							JSONFactoryUtil.looseSerialize(dossier)));
//				}
//				catch (JSONException parse) {
//					_log.error(parse);
//				}
////						_log.info("payloadTest: "+payload.toJSONString());
//				queue.setPayload(payload.toJSONString());
//				queue.setExpireDate(cal.getTime());
//
//				NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
//			}
	
			_log.debug("CREATE DOSSIER 6: " + (System.currentTimeMillis() - start) + " ms");
			//Add to dossier user based on service process role
			createDossierUsers(groupId, dossier, process, lstProcessRoles);
			
			_log.debug("CREATE DOSSIER 7: " + (System.currentTimeMillis() - start) + " ms");
			dossierLocalService.updateDossier(dossier);
			_log.debug("CREATE DOSSIER 8: " + (System.currentTimeMillis() - start) + " ms");
			
			String payload = StringPool.BLANK;
			String actionCode = "1100";
			if (dossier.getReceiveDate() == null) {
				JSONObject jsonDate = JSONFactoryUtil.createJSONObject();
				jsonDate.put(DossierTerm.RECEIVE_DATE, (new Date()).getTime());
				
				Double durationCount = process.getDurationCount();
				if (Validator.isNotNull(String.valueOf(durationCount)) && durationCount > 0d) {
					Date dueDateCal = HolidayUtils.getDueDate(new Date(), process.getDurationCount(),
							process.getDurationUnit(), groupId);
					jsonDate.put(DossierTerm.DUE_DATE, dueDateCal != null ? dueDateCal.getTime() : 0);
				}
				if (Validator.isNotNull(jsonDate)) {
					payload = jsonDate.toJSONString();
				}
			}
			//
			ProcessAction proAction = getProcessAction(groupId, dossier, actionCode,
					serviceProcessId);
			doAction(groupId, userId, dossier, option, proAction, actionCode, StringPool.BLANK, StringPool.BLANK,
					payload, StringPool.BLANK, input.getPayment(), 0, serviceContext);

		}
		return dossier;
	}

	private void createDossierUsers(long groupId, Dossier dossier, ServiceProcess process, List<ServiceProcessRole> lstProcessRoles) {
		List<DossierUser> lstDaus = dossierUserLocalService.findByDID(dossier.getDossierId());
		int count = 0;
		long[] roleIds = new long[lstProcessRoles.size()];
		for (ServiceProcessRole spr : lstProcessRoles) {
			long roleId = spr.getRoleId();
			roleIds[count++] = roleId;
		}
		List<JobPos> lstJobPoses = JobPosLocalServiceUtil.findByF_mappingRoleIds(groupId, roleIds);
		Map<Long, JobPos> mapJobPoses = new HashMap<>();
		long[] jobPosIds = new long[lstJobPoses.size()];
		count = 0;
		for (JobPos jp : lstJobPoses) {
			mapJobPoses.put(jp.getJobPosId(), jp);
			jobPosIds[count++] = jp.getJobPosId();
		}
		List<EmployeeJobPos> lstTemp = EmployeeJobPosLocalServiceUtil.findByF_G_jobPostIds(groupId, jobPosIds);
		Map<Long, List<EmployeeJobPos>> mapEJPS = new HashMap<>();
		for (EmployeeJobPos ejp : lstTemp) {
			if (mapEJPS.get(ejp.getJobPostId()) != null) {
				mapEJPS.get(ejp.getJobPostId()).add(ejp);
			}
			else {
				List<EmployeeJobPos> lstEJPs = new ArrayList<>();
				lstEJPs.add(ejp);
				mapEJPS.put(ejp.getJobPostId(), lstEJPs);
			}
		}
		for (ServiceProcessRole spr : lstProcessRoles) {
			long roleId = spr.getRoleId();
			int moderator = spr.getModerator() ? 1 : 0;
//			JobPos jp = JobPosLocalServiceUtil.fetchByF_mappingRoleId(groupId, roleId);
			JobPos jp = mapJobPoses.get(roleId);
			
			if (jp != null) {
//				List<EmployeeJobPos> lstEJPs = EmployeeJobPosLocalServiceUtil.getByJobPostId(groupId, jp.getJobPosId());
				List<EmployeeJobPos> lstEJPs = mapEJPS.get(jp.getJobPosId());
				long[] employeeIds = new long[lstEJPs.size()];
				int countEmp = 0;
				for (EmployeeJobPos ejp : lstEJPs) {
					employeeIds[countEmp++] = ejp.getEmployeeId();
				}
				List<Employee> lstEmpls = EmployeeLocalServiceUtil.findByG_EMPID(groupId, employeeIds);
				HashMap<Long, Employee> mapEmpls = new HashMap<>();
				for (Employee e : lstEmpls) {
					mapEmpls.put(e.getEmployeeId(), e);
				}
				List<Employee> lstEmployees = new ArrayList<>();
//				for (EmployeeJobPos ejp : lstEJPs) {
//					Employee employee = EmployeeLocalServiceUtil.fetchEmployee(ejp.getEmployeeId());
//					if (employee != null) {
//						lstEmployees.add(employee);
//					}
//				}
				for (EmployeeJobPos ejp : lstEJPs) {
					if (mapEmpls.get(ejp.getEmployeeId()) != null) {
						lstEmployees.add(mapEmpls.get(ejp.getEmployeeId()));
					}
				}		
				HashMap<Long, DossierUser> mapDaus = new HashMap<>();
				for (DossierUser du : lstDaus) {
					mapDaus.put(du.getUserId(), du);
				}
				for (Employee e : lstEmployees) {
//					DossierUserPK pk = new DossierUserPK();
//					pk.setDossierId(dossier.getDossierId());
//					pk.setUserId(e.getMappingUserId());
//					DossierUser ds = DossierUserLocalServiceUtil.fetchDossierUser(pk);
					if (mapDaus.get(e.getMappingUserId()) == null) {
//					if (ds == null) {
						dossierUserLocalService.addDossierUser(groupId, dossier.getDossierId(), e.getMappingUserId(), moderator, Boolean.FALSE);						
					}
					else {
						DossierUser ds = mapDaus.get(e.getMappingUserId());
						
						if (moderator == 1 && ds.getModerator() == 0) {
							ds.setModerator(1);
							dossierUserLocalService.updateDossierUser(ds);
						}
					}
				}
			}
		}
	}
	
	private void updateApplicantInfo(Dossier dossier, Date applicantIdDate,
			String applicantIdNo,
			String applicantIdType,
			String applicantName,
			String address,
			String cityCode,
			String cityName,
			String districtCode,
			String districtName,
			String wardCode,
			String wardName,
			String contactEmail,
			String contactTelNo) {
		dossier.setApplicantIdDate(applicantIdDate);
		dossier.setApplicantIdNo(applicantIdNo);
		dossier.setApplicantIdType(applicantIdType);
		dossier.setApplicantName(applicantName);
		dossier.setAddress(address);
		dossier.setCityCode(cityCode);
		dossier.setCityName(cityName);
		dossier.setDistrictCode(districtCode);
		dossier.setDistrictName(districtName);
		dossier.setWardCode(wardCode);
		dossier.setWardName(wardName);
		dossier.setContactEmail(contactEmail);
		dossier.setContactTelNo(contactTelNo);
		
		dossier.setDelegateAddress(address);
		dossier.setDelegateCityCode(cityCode);
		dossier.setDelegateCityName(cityName);
		dossier.setDelegateDistrictCode(districtCode);
		dossier.setDelegateDistrictName(districtName);
		dossier.setDelegateEmail(contactEmail);
		dossier.setDelegateIdNo(applicantIdNo);
		dossier.setDelegateName(applicantName);
		dossier.setDelegateTelNo(contactTelNo);
		dossier.setDelegateWardCode(wardCode);
		dossier.setDelegateWardName(wardName);		
	}
	
	private void updateDelegateApplicant(Dossier dossier, DossierInputModel input) {
		if (Validator.isNotNull(input.getDelegateName())) {
			dossier.setDelegateName(input.getDelegateName());
		}
		if (Validator.isNotNull(input.getDelegateIdNo())) {
			dossier.setDelegateIdNo(input.getDelegateIdNo());
		}
		if (Validator.isNotNull(input.getDelegateTelNo())) {
			dossier.setDelegateTelNo(input.getDelegateTelNo());
		}
		if (Validator.isNotNull(input.getDelegateEmail())) {
			dossier.setDelegateEmail(input.getDelegateEmail());
		}
		if (Validator.isNotNull(input.getDelegateAddress())) {
			dossier.setDelegateAddress(input.getDelegateAddress());
		}
		if (Validator.isNotNull(input.getDelegateCityCode())) {
			dossier.setDelegateCityCode(input.getDelegateCityCode());
		}
		if (Validator.isNotNull(input.getDelegateCityName())) {
			dossier.setDelegateCityName(input.getDelegateCityName());
		}
		if (Validator.isNotNull(input.getDelegateDistrictCode())) {
			dossier.setDelegateDistrictCode(input.getDelegateDistrictCode());
		}
		if (Validator.isNotNull(input.getDelegateDistrictName())) {
			dossier.setDelegateDistrictName(input.getDelegateDistrictName());
		}
		if (Validator.isNotNull(input.getDelegateWardCode())) {
			dossier.setDelegateWardCode(input.getDelegateWardCode());
		}		
		if (Validator.isNotNull(input.getDelegateWardName())) {
			dossier.setDelegateWardCode(input.getDelegateWardName());
		}		
	}
	
	private String getDictItemName(long groupId, DictCollection dc, String itemCode) {

		if (Validator.isNotNull(dc)) {
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dc.getPrimaryKey(), groupId);
			if(Validator.isNotNull(it)){
				return it.getItemName();
			}else{
				return StringPool.BLANK;
			}
		} else {
			return StringPool.BLANK;
		}

	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={SystemException.class, PortalException.class, Exception.class })
	public DossierFile addDossierFileByDossierId(long groupId, Company company, User user, ServiceContext serviceContext, 
			Attachment file, String id, String referenceUid,
			String dossierTemplateNo, String dossierPartNo, String fileTemplateNo, String displayName, String fileType,
			String isSync, String formData, String removed, String eForm, Long modifiedDate) throws UnauthenticationException, PortalException, Exception {
		BackendAuth auth = new BackendAuthImpl();

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long dossierId = GetterUtil.getLong(id);
			Dossier dossier = null;

			if (dossierId != 0) {
				dossier = dossierLocalService.fetchDossier(dossierId);
				if (Validator.isNull(dossier)) {
					dossier = dossierLocalService.getByRef(groupId, id);
				}
			} else {
				dossier = dossierLocalService.getByRef(groupId, id);
			}

			DataHandler dataHandler = (file != null) ? file.getDataHandler() : null;

			long originDossierId = dossier.getOriginDossierId();
			if (originDossierId != 0) {
				//HSLT
				Dossier hsltDossier = dossierLocalService.fetchDossier(dossier.getDossierId());
				dossier = dossierLocalService.fetchDossier(dossier.getOriginDossierId());
				ServiceConfig serviceConfig = serviceConfigLocalService.getBySICodeAndGAC(groupId, dossier.getServiceCode(), hsltDossier.getGovAgencyCode());
				List<ProcessOption> lstOptions = processOptionLocalService.getByServiceProcessId(serviceConfig.getServiceConfigId());
				
				if (serviceConfig != null) {
					if (lstOptions.size() > 0) {
						ProcessOption processOption = lstOptions.get(0);
						DossierTemplate dossierTemplate = dossierTemplateLocalService.fetchDossierTemplate(processOption.getDossierTemplateId());
						List<DossierPart> lstParts = dossierPartLocalService.getByTemplateNo(groupId, dossierTemplate.getTemplateNo());
						for (DossierPart dp : lstParts) {
							if (dp.getPartNo().equals(dossierPartNo) && dp.getFileTemplateNo().equals(fileTemplateNo)) {
								dossierTemplateNo = dp.getTemplateNo();
							}
						}
					}
				}
			}
			else {
				List<DossierPart> lstParts = dossierPartLocalService.getByTemplateNo(groupId, dossier.getDossierTemplateNo());
				for (DossierPart dp : lstParts) {
					if (dp.getPartNo().equals(dossierPartNo)) {
						fileTemplateNo = dp.getFileTemplateNo();
						dossierTemplateNo = dossier.getDossierTemplateNo();
					}
				}
			}
			
			if (originDossierId > 0) {
				_log.debug("__Start add file at:" + new Date());
				DossierFile dossierFile =  null;
				DossierFile oldDossierFile = null;
				if (Validator.isNotNull(referenceUid)) {
					oldDossierFile = dossierFileLocalService.getByDossierAndRef(dossier.getDossierId(), referenceUid);
				}
				if (oldDossierFile != null && modifiedDate != null) {
					if (oldDossierFile.getModifiedDate() != null && oldDossierFile.getModifiedDate().getTime() < modifiedDate) {
				
						if (dataHandler != null && dataHandler.getInputStream() != null) {
							dossierFile = dossierFileLocalService.updateDossierFile(groupId, 
									dossier.getDossierId(), 
									referenceUid, 
									displayName, 
									StringPool.BLANK,
									dataHandler.getInputStream(), serviceContext);
						} else {
							dossierFile = dossierFileLocalService.updateDossierFile(groupId, 
									dossier.getDossierId(), 
									referenceUid, 
									displayName, 
									StringPool.BLANK,
									null, serviceContext);
						}
						
						_log.debug("__End add file at:" + new Date());
						
						if(Validator.isNotNull(formData)) {
							dossierFile.setFormData(formData);
						}
						_log.debug("REMOVED:" + removed);
						if(Validator.isNotNull(removed)) {
							dossierFile.setRemoved(Boolean.parseBoolean(removed));
						}
						if(Validator.isNotNull(eForm)) {
							dossierFile.setEForm(Boolean.parseBoolean(eForm));
						}
						_log.debug("__Start update dossier file at:" + new Date());
			
						dossierFileLocalService.updateDossierFile(dossierFile);
			
						_log.debug("__End update dossier file at:" + new Date());
			
						_log.debug("__End bind to dossierFile" + new Date());
						return dossierFile;
					}
					else {
						throw new DataConflictException("Conflict dossier file");
					}					
				}
				else {
					_log.debug("__Start add file at:" + new Date());
					if (dataHandler != null && dataHandler.getInputStream() != null) {
						dossierFile = dossierFileLocalService.addDossierFile(groupId, dossier.getDossierId(), referenceUid, dossierTemplateNo,
								dossierPartNo, fileTemplateNo, displayName, dataHandler.getName(), 0,
								dataHandler.getInputStream(), fileType, isSync, serviceContext);
					} else {
						dossierFile = dossierFileLocalService.addDossierFile(groupId, dossier.getDossierId(), referenceUid, dossierTemplateNo,
								dossierPartNo, fileTemplateNo, displayName, displayName, 0, null, fileType, isSync,
								serviceContext);
					}
					
					_log.debug("__End add file at:" + new Date());
					
					if(Validator.isNotNull(formData)) {
						dossierFile.setFormData(formData);
					}
					if(Validator.isNotNull(removed)) {
						dossierFile.setRemoved(Boolean.parseBoolean(removed));
					}
					if(Validator.isNotNull(eForm)) {
						dossierFile.setEForm(Boolean.parseBoolean(eForm));
					}
					_log.debug("__Start update dossier file at:" + new Date());
		
					dossierFileLocalService.updateDossierFile(dossierFile);
		
					_log.debug("__End update dossier file at:" + new Date());
		
					_log.debug("__End bind to dossierFile" + new Date());
					return dossierFile;
				}
			} else {
//				DossierFile lastDossierFile = DossierFileLocalServiceUtil.findLastDossierFile(dossier.getDossierId(), fileTemplateNo, dossierTemplateNo);
				//_log.info("lastDossierFile: "+lastDossierFile);
				DossierFile oldDossierFile = null;
				if (Validator.isNotNull(referenceUid)) {
					oldDossierFile = DossierFileLocalServiceUtil.getByDossierAndRef(dossier.getDossierId(), referenceUid);
				}
				if (oldDossierFile != null && modifiedDate != null) {
					if (oldDossierFile.getModifiedDate() != null && oldDossierFile.getModifiedDate().getTime() < modifiedDate) {
						_log.debug("__Start add file at:" + new Date());
						DossierFile dossierFile =  null;
						
						if (dataHandler != null && dataHandler.getInputStream() != null) {
							dossierFile = dossierFileLocalService.updateDossierFile(groupId, 
									dossier.getDossierId(), 
									referenceUid, 
									displayName, 
									StringPool.BLANK,
									dataHandler.getInputStream(), serviceContext);
						} else {
							dossierFile = dossierFileLocalService.updateDossierFile(groupId, 
									dossier.getDossierId(), 
									referenceUid, 
									displayName, 
									StringPool.BLANK,
									null, serviceContext);
						}
						
						_log.debug("__End add file at:" + new Date());
						
						if(Validator.isNotNull(formData)) {
							dossierFile.setFormData(formData);
						}
						if(Validator.isNotNull(removed)) {
							dossierFile.setRemoved(Boolean.parseBoolean(removed));
						}
						if(Validator.isNotNull(eForm)) {
							dossierFile.setEForm(Boolean.parseBoolean(eForm));
						}
						_log.debug("__Start update dossier file at:" + new Date());

						DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

						_log.debug("__End update dossier file at:" + new Date());

						_log.debug("__End bind to dossierFile" + new Date());
						return dossierFile;
					}
					else {
						throw new DataConflictException("Conflict dossier file");				
					}
				}
				else {
					_log.debug("__Start add file at:" + new Date());
					DossierFile dossierFile =  null;
					
					if (dataHandler != null && dataHandler.getInputStream() != null) {
						dossierFile = dossierFileLocalService.addDossierFile(groupId, dossier.getDossierId(), referenceUid, dossierTemplateNo,
								dossierPartNo, fileTemplateNo, displayName, dataHandler.getName(), 0,
								dataHandler.getInputStream(), fileType, isSync, serviceContext);
					} else {
						dossierFile = dossierFileLocalService.addDossierFile(groupId, dossier.getDossierId(), referenceUid, dossierTemplateNo,
								dossierPartNo, fileTemplateNo, displayName, displayName, 0, null, fileType, isSync,
								serviceContext);
					}
					
					_log.debug("__End add file at:" + new Date());
					
					if(Validator.isNotNull(formData)) {
						dossierFile.setFormData(formData);
					}
					if(Validator.isNotNull(removed)) {
						dossierFile.setRemoved(Boolean.parseBoolean(removed));
					}
					if(Validator.isNotNull(eForm)) {
						dossierFile.setEForm(Boolean.parseBoolean(eForm));
					}
					_log.debug("__Start update dossier file at:" + new Date());
		
					dossierFileLocalService.updateDossierFile(dossierFile);
		
					_log.debug("__End update dossier file at:" + new Date());
		
					_log.debug("__End bind to dossierFile" + new Date());
					return dossierFile;
				}
			}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={SystemException.class, PortalException.class, Exception.class })
	public DossierFile updateDossierFile(long groupId, Company company, ServiceContext serviceContext, long id, String referenceUid, Attachment file) 
		throws UnauthenticationException, PortalException, Exception {

		BackendAuth auth = new BackendAuthImpl();

		DataHandler dataHandle = file.getDataHandler();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		Dossier dossier = dossierLocalService.fetchDossier(id);
		if (dossier != null) {
			if (dossier.getOriginDossierId() != 0) {
				dossier = dossierLocalService.fetchDossier(dossier.getOriginDossierId());
				id = dossier.getDossierId();
			}
		}

		DossierFile dossierFile = dossierFileLocalService.updateDossierFile(groupId, id, referenceUid, dataHandle.getName(),
				StringPool.BLANK,
				dataHandle.getInputStream(), serviceContext);

		return dossierFile;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={SystemException.class, PortalException.class, Exception.class })
	public DossierFile updateDossierFileFormData(long groupId, Company company,
			ServiceContext serviceContext, long id, String referenceUid, String formdata) throws UnauthenticationException, PortalException, Exception {

		BackendAuth auth = new BackendAuthImpl();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		Dossier dossier = dossierLocalService.fetchDossier(id);
		if (dossier != null) {
			if (dossier.getOriginDossierId() != 0) {
				dossier = dossierLocalService.fetchDossier(dossier.getOriginDossierId());
				id = dossier.getOriginDossierId();
			}
		}

		DossierFile dossierFile = dossierFileLocalService.updateFormData(groupId, id, referenceUid, formdata,
					serviceContext);
		return dossierFile;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={SystemException.class, PortalException.class, Exception.class })
	public DossierFile resetformdataDossierFileFormData(long groupId, Company company,
			ServiceContext serviceContext, long id, String referenceUid, String formdata) throws UnauthenticationException, PortalException, Exception {
		BackendAuth auth = new BackendAuthImpl();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		Dossier dossier = dossierLocalService.fetchDossier(id);
		DossierFile dossierFile = null;
		if (dossier != null) {
			if (dossier.getOriginDossierId() != 0) {
				dossier = dossierLocalService.fetchDossier(dossier.getOriginDossierId());
				//id = dossier.getOriginDossierId();
			}
			dossierFile = dossierFileLocalService.getDossierFileByReferenceUid(dossier.getDossierId(), referenceUid);
			
			String defaultData = StringPool.BLANK;
	
			if (Validator.isNotNull(dossierFile)) {
				DossierPart part = dossierPartLocalService.getByFileTemplateNo(groupId,
						dossierFile.getFileTemplateNo());
	
				defaultData = AutoFillFormData.sampleDataBinding(part.getSampleData(), dossier.getDossierId(), serviceContext);
				dossierFile = dossierFileLocalService.getByReferenceUid(referenceUid).get(0);
				JSONObject defaultDataObj = JSONFactoryUtil.createJSONObject(defaultData);
				defaultDataObj.put("LicenceNo", dossierFile.getDeliverableCode());
				defaultData = defaultDataObj.toJSONString();
			}
	
			dossierFile = dossierFileLocalService.updateFormData(groupId, dossier.getDossierId(), referenceUid, defaultData,
					serviceContext);
			
			String deliverableCode = dossierFile.getDeliverableCode();
			
			if (Validator.isNotNull(deliverableCode)) {
				Deliverable deliverable = deliverableLocalService.getByCode(deliverableCode);
				deliverableLocalService.deleteDeliverable(deliverable);
			}
		}
		return dossierFile;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={SystemException.class, PortalException.class, Exception.class })
	public PaymentFile createPaymentFileByDossierId(long groupId, ServiceContext serviceContext, String id, PaymentFileInputModel input) throws UnauthenticationException, PortalException, Exception {
		long userId = serviceContext.getUserId();
		
		BackendAuth auth = new BackendAuthImpl();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}
		Dossier dossier = getDossier(id, groupId);
		PaymentFile paymentFile = null;
		if (dossier != null) {
			long dossierId = dossier.getPrimaryKey();

			if (!auth.hasResource(serviceContext, PaymentFile.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}


			PaymentFile oldPaymentFile = paymentFileLocalService.getByDossierId(groupId, dossier.getDossierId());
			String referenceUid = input.getReferenceUid();
			if (Validator.isNull(referenceUid)) {
				referenceUid = PortalUUIDUtil.generate();
			}
			
			if (oldPaymentFile != null) {
				paymentFile = oldPaymentFile;
			}
			else {
				paymentFile = paymentFileLocalService.createPaymentFiles(userId, groupId, dossierId, referenceUid,
						input.getPaymentFee(), input.getAdvanceAmount(), input.getFeeAmount(), input.getServiceAmount(),
						input.getShipAmount(), input.getPaymentAmount(), input.getPaymentNote(),
						input.getEpaymentProfile(), input.getBankInfo(), 0,
						input.getPaymentMethod(), serviceContext);		
			}
			
			paymentFile.setInvoiceTemplateNo(input.getInvoiceTemplateNo());
			if(Validator.isNotNull(input.getConfirmFileEntryId())){
				paymentFile.setConfirmFileEntryId(input.getConfirmFileEntryId());
			}
			if(Validator.isNotNull(input.getPaymentStatus())){
				paymentFile.setPaymentStatus(input.getPaymentStatus());
			}
			if(Validator.isNotNull(input.getEinvoice())) {
				paymentFile.setEinvoice(input.getEinvoice());
			}
			if(Validator.isNotNull(input.getPaymentAmount())) {
				paymentFile.setPaymentAmount(input.getPaymentAmount());
			}
			if(Validator.isNotNull(input.getPaymentMethod())){
				paymentFile.setPaymentMethod(input.getPaymentMethod());
			}
			if(Validator.isNotNull(input.getServiceAmount())){
				paymentFile.setServiceAmount(input.getServiceAmount());
			}
			if(Validator.isNotNull(input.getShipAmount())){
				paymentFile.setShipAmount(input.getShipAmount());
			}
			if(Validator.isNotNull(input.getAdvanceAmount())){
				paymentFile.setAdvanceAmount(input.getAdvanceAmount());
			}
			paymentFile = paymentFileLocalService.updatePaymentFile(paymentFile);
		}

		return paymentFile;
	}
	
	private Dossier getDossier(String id, long groupId) throws PortalException {
		long dossierId = GetterUtil.getLong(id);

		Dossier dossier = null;
		
		if (dossierId != 0) {
			dossier = dossierLocalService.fetchDossier(dossierId);
		}

		if (Validator.isNull(dossier)) {
			dossier = dossierLocalService.getByRef(groupId, id);
		}

		return dossier;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={SystemException.class, PortalException.class, Exception.class })
	public Dossier addDossierPublish(long groupId, Company company,
			User user, ServiceContext serviceContext, org.opencps.dossiermgt.input.model.DossierPublishModel input) throws UnauthenticationException, PortalException, Exception {

		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

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
			int originality = 0;
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
			
			Dossier oldDossier = null;
			if (Validator.isNotNull(input.getReferenceUid())) {
				oldDossier = getDossier(input.getReferenceUid(), groupId);
			} else {
			    oldDossier = DossierLocalServiceUtil.getByDossierNo(groupId, dossierNo);
			    referenceUid = DossierNumberGenerator.generateReferenceUID(groupId);
			}

		if (oldDossier == null || oldDossier.getOriginality() == 0) {
			Dossier dossier = actions.publishDossier(groupId, 0l, referenceUid, counter, serviceCode, serviceName,
					govAgencyCode, govAgencyName, applicantName, applicantType, applicantIdNo, applicantIdDate, address,
					cityCode, cityName, districtCode, districtName, wardCode, wardName, contactName, contactTelNo,
					contactEmail, dossierTemplateNo, password, 0, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
					StringPool.BLANK, Boolean.valueOf(online), false, applicantNote, originality,
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
					processDateLong != 0 ? new Date(processDateLong) : null, input.getDossierNo(),
					input.getDossierStatus(), input.getDossierStatusText(), input.getDossierSubStatus(),
					input.getDossierSubStatusText(),
					input.getDossierActionId() != null ? input.getDossierActionId() : 0, submissionNote, lockState,
					input.getDelegateName(), input.getDelegateIdNo(), input.getDelegateTelNo(),
					input.getDelegateEmail(), input.getDelegateAddress(), input.getDelegateCityCode(),
					input.getDelegateCityName(), input.getDelegateDistrictCode(), input.getDelegateDistrictName(),
					input.getDelegateWardCode(), input.getDelegateWardName(), input.getDurationCount(),
					input.getDurationUnit(), input.getDossierName(), input.getProcessNo(), input.getMetaData(), serviceContext);
				
				return dossier;
			}
			return oldDossier;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={SystemException.class, PortalException.class, Exception.class })
	public Dossier addFullDossier(long groupId, Company company, User user, ServiceContext serviceContext,
			DossierInputModel input) throws UnauthenticationException, PortalException, Exception {

		BackendAuth auth = new BackendAuthImpl();
		DossierPermission dossierPermission = new DossierPermission();

		long start = System.currentTimeMillis();
		
		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		ProcessOption option = getProcessOption(input.getServiceCode(), input.getGovAgencyCode(),
				input.getDossierTemplateNo(), groupId);
		long serviceProcessId = 0;
		if (option != null) {
			serviceProcessId = option.getServiceProcessId();
		}

		boolean flag = false;
		long userId = serviceContext.getUserId();
		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
		if (employee != null) {
			long employeeId = employee.getEmployeeId();
			if (employeeId > 0) {
				List<EmployeeJobPos> empJobList = EmployeeJobPosLocalServiceUtil.findByF_EmployeeId(employeeId);
				if (empJobList != null && empJobList.size() > 0) {
					for (EmployeeJobPos employeeJobPos : empJobList) {
						long jobPosId = employeeJobPos.getJobPostId();
						if (jobPosId > 0) {
							JobPos job = JobPosLocalServiceUtil.fetchJobPos(jobPosId);
							if (job != null) {
								ServiceProcessRolePK pk = new ServiceProcessRolePK(serviceProcessId,
											job.getMappingRoleId());
								ServiceProcessRole role = serviceProcessRoleLocalService
											.fetchServiceProcessRole(pk);
								if (role != null && role.getModerator()) {
									flag = true;
									break;
								}
							}
						}
					}
				}
			}
		} else {
			flag = true;
		}
	
		if (!flag) {
			throw new UnauthenticationException("No permission create dossier");
		}
		_log.debug("CREATE DOSSIER 1: " + (System.currentTimeMillis() - start) + " ms");
		dossierPermission.hasCreateDossier(groupId, user.getUserId(), input.getServiceCode(),
				input.getGovAgencyCode(), input.getDossierTemplateNo());

		//int counter = DossierNumberGenerator.counterDossier(user.getUserId(), groupId);
		String referenceUid = input.getReferenceUid();
		int counter = 0;

		// Create dossierNote
		ServiceProcess process = null;
		boolean online = GetterUtil.getBoolean(input.getOnline());
		int originality = GetterUtil.getInteger(input.getOriginality());
		Integer viaPostal = input.getViaPostal();
		ServiceConfig config = serviceConfigLocalService.getBySICodeAndGAC(groupId, input.getServiceCode(),
				input.getGovAgencyCode());
		if (config != null && Validator.isNotNull(viaPostal)) {
			viaPostal = config.getPostService() ? (viaPostal == 0 ? 1 : viaPostal) : 0;
		}
		else if (config != null) {
			viaPostal = config.getPostService() ? 1 : 0;
		}
		if (option != null) {
			process = serviceProcessLocalService.getServiceProcess(serviceProcessId);
		}

		if (process == null) {
			throw new NotFoundException("Cant find process");
		}

		if (Validator.isNull(referenceUid) || referenceUid.trim().length() == 0)
			referenceUid = DossierNumberGenerator.generateReferenceUID(groupId);

		//Dossier checkDossier = dossierLocalService.getByRef(groupId, referenceUid);
		//if (checkDossier != null) {
		//	return checkDossier;
		//}
		
		ServiceInfo service = serviceInfoLocalService.getByCode(groupId, input.getServiceCode());
		String serviceName = StringPool.BLANK;
		if (service != null) {
			serviceName = service.getServiceName();
		}

		String govAgencyName = getDictItemName(groupId, GOVERNMENT_AGENCY, input.getGovAgencyCode());
		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(ADMINISTRATIVE_REGION, groupId);
		
		String cityName = getDictItemName(groupId, dc, input.getCityCode());
		String districtName = getDictItemName(groupId, dc, input.getDistrictCode());
		String wardName = getDictItemName(groupId, dc, input.getWardCode());
//			_log.info("Service code: " + input.getServiceCode());
		_log.debug("===ADD DOSSIER CITY NAME:" + cityName);
		String password = StringPool.BLANK;
		if (Validator.isNotNull(input.getPassword())) {
			password = input.getPassword();
		} else if (Validator.isNotNull(process.getGeneratePassword()) && process.getGeneratePassword()) {
			password = PwdGenerator.getPinNumber();
		}

		String postalCityName = StringPool.BLANK;
		
		if (Validator.isNotNull(input.getPostalCityCode())) {
			postalCityName = getDictItemName(groupId, VNPOST_CITY_CODE, input.getPostalCityCode());
		}
		Long sampleCount = (option != null ? option.getSampleCount() : 1l);

		
		// Process group dossier
		if (originality == DossierTerm.ORIGINALITY_HOSONHOM) {

			_log.debug("CREATE DOSSIER 2: " + (System.currentTimeMillis() - start) + " ms");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			Date appIdDate = null;

			try {
				appIdDate = sdf.parse(input.getApplicantIdDate());

			} catch (Exception e) {
				_log.debug(e);
			}
			Dossier dossier = dossierLocalService.initDossier(groupId, 0l, referenceUid, counter, input.getServiceCode(), serviceName,
					input.getGovAgencyCode(), govAgencyName, input.getApplicantName(), input.getApplicantIdType(),
					input.getApplicantIdNo(), appIdDate, input.getAddress(), input.getCityCode(),
					cityName, input.getDistrictCode(), districtName, input.getWardCode(), wardName,
					input.getContactName(), input.getContactTelNo(), input.getContactEmail(),
					input.getDossierTemplateNo(), password, viaPostal, input.getPostalAddress(), input.getPostalCityCode(), postalCityName,
					input.getPostalTelNo(), online, process.getDirectNotification(), input.getApplicantNote(),
					Integer.valueOf(input.getOriginality()), 
					service, process, option,
					serviceContext);

			if (Validator.isNotNull(input.getDossierName())) {
				dossier.setDossierName(input.getDossierName());
			} else {
				dossier.setDossierName(serviceName);
			}
			dossier.setSampleCount(sampleCount);
			updateDelegateApplicant(dossier, input);
			// Process update dossierNo
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
			params.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
			params.put(DossierTerm.DOSSIER_TEMPLATE_NO, dossier.getDossierTemplateNo());
			params.put(DossierTerm.DOSSIER_STATUS, StringPool.BLANK);

			if (option != null) {
				//Process submition note
				dossier.setSubmissionNote(option.getSubmissionNote());

				String dossierRef = DossierNumberGenerator.generateDossierNumber(groupId, dossier.getCompanyId(),
						dossier.getDossierId(), option.getProcessOptionId(), process.getDossierGroupPattern(), params);

				dossier.setDossierNo(dossierRef.trim());
			}
			dossier.setViaPostal(1);

			_log.debug("CREATE DOSSIER 3: " + (System.currentTimeMillis() - start) + " ms");

			if (Validator.isNull(dossier)) {
				throw new NotFoundException("Can't add DOSSIER");
			}

			_log.debug("CREATE DOSSIER 4: " + (System.currentTimeMillis() - start) + " ms");
			//Create DossierMark
			_log.debug("originality: "+originality);
			String templateNo = dossier.getDossierTemplateNo();
			_log.debug("templateNo: "+templateNo);
			if (Validator.isNotNull(input.getDossierMarkArr())) {
				JSONArray markArr = JSONFactoryUtil.createJSONArray(input.getDossierMarkArr());
				if (markArr != null && markArr.length() > 0) {
					List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId, dossier.getDossierId());
					Map<String, DossierMark> mapMarks = new HashMap<>();
					for (DossierMark dm : lstMarks) {
						mapMarks.put(dm.getDossierPartNo(), dm);
					}

					for (int i = 0; i < markArr.length(); i++) {
						JSONObject jsonMark = markArr.getJSONObject(i);
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[markArr.length()];
						
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel model = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel();
						model.setDossierId(dossier.getDossierId());
						model.setDossierPartNo(jsonMark.getString("partNo"));
						model.setFileCheck(0);
						model.setFileMark(jsonMark.getInt("fileMark"));
						model.setFileComment(StringPool.BLANK);
						model.setRecordCount(StringPool.BLANK);
						marks[i] = model;

						dossierMarkLocalService.addBatchDossierMark(groupId, marks, mapMarks, serviceContext);
					}
				}
			} else if (Validator.isNotNull(templateNo)) {
				List<DossierPart> partList = dossierPartLocalService.getByTemplateNo(groupId, templateNo);
//						_log.info("partList: "+partList);
				if (partList != null && partList.size() > 0) {
					_log.debug("partList.size(): "+partList.size());
					_log.debug("CREATE DOSSIER 4.1: " + (System.currentTimeMillis() - start) + " ms");
					org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList.size()];
					int count = 0;
					List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId, dossier.getDossierId());
					Map<String, DossierMark> mapMarks = new HashMap<>();
					for (DossierMark dm : lstMarks) {
						mapMarks.put(dm.getDossierPartNo(), dm);
					}
					for (DossierPart dossierPart : partList) {
						int fileMark = dossierPart.getFileMark();
						String dossierPartNo = dossierPart.getPartNo();
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel model = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel();
						model.setDossierId(dossier.getDossierId());
						model.setDossierPartNo(dossierPartNo);
						model.setFileCheck(0);
						model.setFileMark(fileMark);
						model.setFileComment(StringPool.BLANK);
						model.setRecordCount(StringPool.BLANK);
						marks[count++] = model;
					}
					
					dossierMarkLocalService.addBatchDossierMark(groupId, marks, mapMarks, serviceContext);
					
					_log.debug("CREATE DOSSIER 4.2: " + (System.currentTimeMillis() - start) + " ms");
				}
			}

			//Create dossier user
			List<DossierUser> lstDus = dossierUserLocalService.findByDID(dossier.getDossierId());
			List<ServiceProcessRole> lstProcessRoles = serviceProcessRoleLocalService.findByS_P_ID(process.getServiceProcessId());
			if (lstDus.size() == 0) {
				DossierUserActions duActions = new DossierUserActionsImpl();
				duActions.initDossierUser(groupId, dossier, process, lstProcessRoles);				
			}
			
//			if (originality == DossierTerm.ORIGINALITY_DVCTT) {
//				dossierUserLocalService.addDossierUser(groupId, dossier.getDossierId(), userId, 1, true);
//			}
			_log.debug("CREATE DOSSIER 5: " + (System.currentTimeMillis() - start) + " ms");

			//Add to dossier user based on service process role
			createDossierUsers(groupId, dossier, process, lstProcessRoles);
			
			_log.debug("CREATE DOSSIER 7: " + (System.currentTimeMillis() - start) + " ms");
			return dossierLocalService.updateDossier(dossier);

		} else {
			List<Dossier> oldDossiers = dossierLocalService.getByU_G_GAC_SC_DTNO_DS_O(
					userId, groupId, input.getServiceCode(), input.getGovAgencyCode(), input.getDossierTemplateNo(), StringPool.BLANK, Integer.valueOf(input.getOriginality()));
			
			Dossier dossier = null;
			
			Dossier oldRefDossier = Validator.isNotNull(input.getReferenceUid()) ? dossierLocalService.getByRef(groupId, input.getReferenceUid()) : null;
			
			if (originality == DossierTerm.ORIGINALITY_DVCTT) {
				online = true;
			}
			boolean flagOldDossier = false;
			String registerBookCode = (option != null ? (Validator.isNotNull(option.getRegisterBookCode()) ? option.getRegisterBookCode() : StringPool.BLANK) : StringPool.BLANK);
			String registerBookName = (Validator.isNotNull(registerBookCode) ? getDictItemName(groupId, REGISTER_BOOK, registerBookCode) : StringPool.BLANK);
			_log.debug("CREATE DOSSIER 2: " + (System.currentTimeMillis() - start) + " ms");
			
			if (oldRefDossier != null) {
				dossier = oldRefDossier;
				dossier.setSubmitDate(new Date());
				ServiceProcess serviceProcess = process;
				
				double durationCount = 0;
				int durationUnit = 0;
				if (serviceProcess != null ) {
					durationCount = serviceProcess.getDurationCount();
					durationUnit = serviceProcess.getDurationUnit();
					dossier.setDurationCount(durationCount);
					dossier.setDurationUnit(durationUnit);
				}

				if (durationCount > 0) {
					Date dueDate = HolidayUtils.getDueDate(new Date(), durationCount, durationUnit, groupId);
					dossier.setDueDate(dueDate);
				}
				
			}
			else if (oldDossiers.size() > 0) {
				flagOldDossier = true;
				dossier = oldDossiers.get(0);
				dossier.setApplicantName(input.getApplicantName());
				dossier.setApplicantNote(input.getApplicantNote());
				dossier.setApplicantIdNo(input.getApplicantIdNo());
				dossier.setAddress(input.getAddress());
				dossier.setContactEmail(input.getContactEmail());
				dossier.setContactName(input.getContactName());
				dossier.setContactTelNo(input.getContactTelNo());
				dossier.setDelegateName(input.getDelegateName());
				dossier.setDelegateEmail(input.getDelegateEmail());
				dossier.setDelegateAddress(input.getDelegateAddress());
				dossier.setPostalAddress(input.getPostalAddress());
				dossier.setPostalCityCode(input.getPostalCityCode());
				dossier.setPostalCityName(postalCityName);
				dossier.setPostalTelNo(input.getPostalTelNo());
				dossier.setPostalServiceCode(input.getPostalServiceCode());
				dossier.setPostalServiceName(input.getPostalServiceName());
				dossier.setPostalDistrictCode(input.getPostalDistrictCode());
				dossier.setPostalDistrictName(input.getPostalDistrictName());
				dossier.setPostalWardCode(input.getPostalWardCode());
				dossier.setPostalWardName(input.getPostalWardName());
				
				dossier.setPostalTelNo(input.getPostalTelNo());
				dossier.setViaPostal(viaPostal);
				dossier.setOriginDossierNo(input.getOriginDossierNo());
				
				if (Validator.isNotNull(registerBookCode)) {
					dossier.setRegisterBookCode(registerBookCode);
				}
				dossier.setRegisterBookName(registerBookName);
				dossier.setSampleCount(sampleCount);
				dossier.setServiceCode(input.getServiceCode());
				dossier.setGovAgencyCode(input.getGovAgencyCode());
				dossier.setDossierTemplateNo(input.getDossierTemplateNo());
				
				updateDelegateApplicant(dossier, input);
				
//					dossier.setDossierNo(input.getDossierNo());
				dossier.setSubmitDate(new Date());
//					ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);
				ServiceProcess serviceProcess = process;
				
				double durationCount = 0;
				int durationUnit = 0;
				if (serviceProcess != null ) {
					durationCount = serviceProcess.getDurationCount();
					durationUnit = serviceProcess.getDurationUnit();
					dossier.setDurationCount(durationCount);
					dossier.setDurationUnit(durationUnit);
				}

				if (durationCount > 0) {
					Date dueDate = HolidayUtils.getDueDate(new Date(), durationCount, durationUnit, groupId);
					dossier.setDueDate(dueDate);
				}

				dossier.setOnline(online);
				if (Validator.isNotNull(input.getDossierName()))
					dossier.setDossierName(input.getDossierName());
				if (serviceProcess != null) {
					dossier.setProcessNo(serviceProcess.getProcessNo());
				}
				
//					dossier = DossierLocalServiceUtil.updateDossier(dossier);
			}
			else {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				Date appIdDate = null;

				try {
					appIdDate = sdf.parse(input.getApplicantIdDate());

				} catch (Exception e) {
					_log.debug(e);
				}
				dossier = dossierLocalService.initDossier(groupId, 0l, referenceUid, counter, input.getServiceCode(), serviceName,
						input.getGovAgencyCode(), govAgencyName, input.getApplicantName(), input.getApplicantIdType(),
						input.getApplicantIdNo(), appIdDate, input.getAddress(), input.getCityCode(),
						cityName, input.getDistrictCode(), districtName, input.getWardCode(), wardName,
						input.getContactName(), input.getContactTelNo(), input.getContactEmail(),
						input.getDossierTemplateNo(), password, viaPostal, input.getPostalAddress(), input.getPostalCityCode(), postalCityName,
						input.getPostalTelNo(), online, process.getDirectNotification(), input.getApplicantNote(),
						Integer.valueOf(input.getOriginality()), 
						service, process, option,
						serviceContext);
				dossier.setDelegateName(input.getDelegateName());
				dossier.setDelegateEmail(input.getDelegateEmail());
				dossier.setDelegateAddress(input.getDelegateAddress());
				if (Validator.isNotNull(input.getDossierName())) {
					dossier.setDossierName(input.getDossierName());
				} else {
					dossier.setDossierName(serviceName);
				}
				dossier.setPostalCityName(postalCityName);
				dossier.setPostalTelNo(input.getPostalTelNo());
				dossier.setPostalServiceCode(input.getPostalServiceCode());
				dossier.setPostalServiceName(input.getPostalServiceName());
				dossier.setPostalDistrictCode(input.getPostalDistrictCode());
				dossier.setPostalDistrictName(input.getPostalDistrictName());
				dossier.setPostalWardCode(input.getPostalWardCode());
				dossier.setPostalWardName(input.getPostalWardName());
				dossier.setOriginDossierNo(input.getOriginDossierNo());

				if (Validator.isNotNull(registerBookCode)) {
					dossier.setRegisterBookCode(registerBookCode);
				}
				dossier.setRegisterBookName(registerBookName);
				dossier.setSampleCount(sampleCount);

				updateDelegateApplicant(dossier, input);
				
				if (process != null) {
					dossier.setProcessNo(process.getProcessNo());
				}
//					dossier = DossierLocalServiceUtil.updateDossier(dossier);
			}
			_log.debug("CREATE DOSSIER 3: " + (System.currentTimeMillis() - start) + " ms");

			if (originality != DossierTerm.ORIGINALITY_LIENTHONG) {
				Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(serviceContext.getUserId());
				if (applicant != null) {
					updateApplicantInfo(dossier, 
							applicant.getApplicantIdDate(),
							applicant.getApplicantIdNo(),
							applicant.getApplicantIdType(),
							applicant.getApplicantName(),
							applicant.getAddress(),
							applicant.getCityCode(),
							applicant.getCityName(),
							applicant.getDistrictCode(),
							applicant.getDistrictName(),
							applicant.getWardCode(),
							applicant.getWardName(),
							applicant.getContactEmail(),
							applicant.getContactTelNo()							
							);
				}
			}
			if (Validator.isNull(dossier)) {
				throw new NotFoundException("Cant add DOSSIER");
			}

			_log.debug("CREATE DOSSIER 4: " + (System.currentTimeMillis() - start) + " ms");
			//Create DossierMark
			_log.debug("flagOldDossier: "+flagOldDossier);
			_log.debug("originality: "+originality);
			if ((originality == DossierTerm.ORIGINALITY_MOTCUA || originality == DossierTerm.ORIGINALITY_LIENTHONG)
					&& !flagOldDossier) {
				String templateNo = dossier.getDossierTemplateNo();
				_log.debug("templateNo: "+templateNo);
				if (Validator.isNotNull(templateNo)) {
					List<DossierPart> partList = dossierPartLocalService.getByTemplateNo(groupId, templateNo);
//						_log.info("partList: "+partList);
					if (partList != null && partList.size() > 0) {
						_log.debug("partList.size(): "+partList.size());
						_log.debug("CREATE DOSSIER 4.1: " + (System.currentTimeMillis() - start) + " ms");
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList.size()];
						int count = 0;
						List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId, dossier.getDossierId());
						Map<String, DossierMark> mapMarks = new HashMap<>();
						for (DossierMark dm : lstMarks) {
							mapMarks.put(dm.getDossierPartNo(), dm);
						}
						for (DossierPart dossierPart : partList) {
							int fileMark = dossierPart.getFileMark();
							String dossierPartNo = dossierPart.getPartNo();
							org.opencps.dossiermgt.input.model.DossierMarkBatchModel model = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel();
							model.setDossierId(dossier.getDossierId());
							model.setDossierPartNo(dossierPartNo);
							model.setFileCheck(0);
							model.setFileMark(fileMark);
							model.setFileComment(StringPool.BLANK);
							marks[count++] = model;
//								DossierMarkLocalServiceUtil.addDossierMark(groupId, dossier.getDossierId(), dossierPartNo,
//										fileMark, 0, StringPool.BLANK, serviceContext);
						}
						
						dossierMarkLocalService.addBatchDossierMark(groupId, marks, mapMarks, serviceContext);
						
						_log.debug("CREATE DOSSIER 4.2: " + (System.currentTimeMillis() - start) + " ms");
					}
				}
			}

			//Create dossier user
			List<DossierUser> lstDus = dossierUserLocalService.findByDID(dossier.getDossierId());
			List<ServiceProcessRole> lstProcessRoles = serviceProcessRoleLocalService.findByS_P_ID(process.getServiceProcessId());
			if (lstDus.size() == 0) {
				DossierUserActions duActions = new DossierUserActionsImpl();
//					duActions.initDossierUser(groupId, dossier);				
				duActions.initDossierUser(groupId, dossier, process, lstProcessRoles);				
			}
			
			if (originality == DossierTerm.ORIGINALITY_DVCTT) {
				dossierUserLocalService.addDossierUser(groupId, dossier.getDossierId(), userId, 1, true);
			}
			_log.debug("CREATE DOSSIER 5: " + (System.currentTimeMillis() - start) + " ms");

//				DossierLocalServiceUtil.updateDossier(dossier);

			if (dossier != null) {
				//
				long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());

				NotificationQueue queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
				//Process add notification queue
				Date now = new Date();

				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
				
				queue.setCreateDate(now);
				queue.setModifiedDate(now);
				queue.setGroupId(groupId);
				queue.setCompanyId(company.getCompanyId());
				
				queue.setNotificationType(NotificationType.DOSSIER_01);
				queue.setClassName(Dossier.class.getName());
				queue.setClassPK(String.valueOf(dossier.getPrimaryKey()));
				queue.setToUsername(dossier.getUserName());
				queue.setToUserId(dossier.getUserId());
				queue.setToEmail(dossier.getContactEmail());
				queue.setToTelNo(dossier.getContactTelNo());
				
				JSONObject payload = JSONFactoryUtil.createJSONObject();
				try {
//						_log.info("START PAYLOAD: ");
					payload.put(
						"Dossier", JSONFactoryUtil.createJSONObject(
							JSONFactoryUtil.looseSerialize(dossier)));
				}
				catch (JSONException parse) {
					_log.error(parse);
				}
//					_log.info("payloadTest: "+payload.toJSONString());
				queue.setPayload(payload.toJSONString());
				queue.setExpireDate(cal.getTime());

				NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
			}

			_log.debug("CREATE DOSSIER 6: " + (System.currentTimeMillis() - start) + " ms");
			//Add to dossier user based on service process role
			createDossierUsers(groupId, dossier, process, lstProcessRoles);
			
			_log.debug("CREATE DOSSIER 7: " + (System.currentTimeMillis() - start) + " ms");
			dossierLocalService.updateDossier(dossier);
			_log.debug("CREATE DOSSIER 8: " + (System.currentTimeMillis() - start) + " ms");
			
			return dossier;
		}

	}

	public static final String GOVERNMENT_AGENCY = "GOVERNMENT_AGENCY";
	public static final String ADMINISTRATIVE_REGION = "ADMINISTRATIVE_REGION";
	public static final String VNPOST_CITY_CODE = "VNPOST_CITY_CODE";
	public static final String REGISTER_BOOK = "REGISTER_BOOK";
	
	private static final long VALUE_CONVERT_DATE_TIMESTAMP = 1000 * 60 * 60 * 24;
	private static final long VALUE_CONVERT_HOUR_TIMESTAMP = 1000 * 60 * 60;

	private static ProcessAction getProcessAction(long groupId, Dossier dossier, String actionCode,
			long serviceProcessId) throws PortalException {

		//_log.debug("GET PROCESS ACTION____");
		ProcessAction action = null;
		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		
		try {
			List<ProcessAction> actions = ProcessActionLocalServiceUtil.getByActionCode(groupId, actionCode,
					serviceProcessId);

			//_log.debug("GET PROCESS ACTION____" + groupId + "," + actionCode + "," + serviceProcessId);

			String dossierStatus = dossier.getDossierStatus();
			String dossierSubStatus = dossier.getDossierSubStatus();
			String preStepCode;
			for (ProcessAction act : actions) {

				preStepCode = act.getPreStepCode();
				//_log.debug("LamTV_preStepCode: "+preStepCode);

				ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(preStepCode, groupId, serviceProcessId);
//				_log.info("LamTV_ProcessStep: "+step);

				if (Validator.isNull(step) && dossierAction == null) {
					action = act;
					break;
				} else {
					String stepStatus = step != null ? step.getDossierStatus() : StringPool.BLANK;
					String stepSubStatus = step != null ?  step.getDossierSubStatus() : StringPool.BLANK;
					boolean flagCheck = false;
					
					if (dossierAction != null) {
						if (act.getPreStepCode().equals(dossierAction.getStepCode())) {
							flagCheck = true;
						}
					}
					else {
						flagCheck = true;
					}
					//_log.debug("LamTV_preStepCode: "+stepStatus + "," + stepSubStatus + "," + dossierStatus + "," + dossierSubStatus + "," + act.getPreCondition() + "," + flagCheck);
					if (stepStatus.contentEquals(dossierStatus)
							&& StringUtil.containsIgnoreCase(stepSubStatus, dossierSubStatus)
							&& flagCheck) {
						if (Validator.isNotNull(act.getPreCondition()) && DossierMgtUtils.checkPreCondition(act.getPreCondition().split(StringPool.COMMA), dossier)) {
							action = act;
							break;							
						}
						else if (Validator.isNull(act.getPreCondition())) {
							action = act;
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			_log.debug(e);
		}

		return action;
	}

	private static Log _log = LogFactoryUtil.getLog(CPSDossierBusinessLocalServiceImpl.class);
}