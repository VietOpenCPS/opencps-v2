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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
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

import javax.ws.rs.HttpMethod;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.cache.actions.CacheActions;
import org.opencps.cache.actions.impl.CacheActionsImpl;
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
import org.opencps.dossiermgt.action.impl.DossierActionUserImpl;
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
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.constants.StepConfigTerm;
import org.opencps.dossiermgt.exception.NoSuchPaymentFileException;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessSequence;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.PublishQueue;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierDocumentLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierMarkLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessSequenceLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.PublishQueueLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.base.CPSDossierBusinessLocalServiceBaseImpl;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
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
	
	private DossierAction doActionInsideProcess(long groupId, long userId, Dossier dossier, ActionConfig actionConfig, ProcessOption option, ProcessAction proAction,
			String actionCode, String actionUser, String actionNote, String payload, String assignUsers, 
			String payment,
			int syncType,
			ServiceContext context) throws PortalException, SystemException, Exception {
		context.setUserId(userId);
		DossierAction dossierAction = null;
		Map<String, Boolean> flagChanged = null;
		JSONObject payloadObject = JSONFactoryUtil.createJSONObject();
		User user = userLocalService.fetchUser(userId);
		String dossierStatus = dossier.getDossierStatus().toLowerCase();
		Employee employee = null;
		Serializable employeeCache = cache.getFromCache("Employee", groupId +"_"+ userId);
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
			JSONFactoryUtil.createJSONObject(payload);
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
		String prevStatus = dossier.getDossierStatus();
		
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
				serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);
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
			processPaymentFile(groupId, userId, paymentFee, option, proAction, previousAction, dossier, context);
			
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
			
			int actionOverdue = getActionDueDate(groupId, dossierId, dossier.getReferenceUid(), proAction.getProcessActionId());

			String actionName = proAction.getActionName();
			
			//Kiểm tra cấu hình cần tạo hồ sơ liên thông
			if (Validator.isNotNull(proAction.getCreateDossiers())) {
				//Create new HSLT
				String GOVERNMENT_AGENCY = "GOVERNMENT_AGENCY";
				
				String govAgencyName = getDictItemName(groupId, GOVERNMENT_AGENCY, proAction.getCreateDossiers());
				String createDossiers = proAction.getCreateDossiers();
				String govAgencyCode = StringPool.BLANK;
				
				if (createDossiers.contains(StringPool.POUND)) {
					String[] splitCDs = createDossiers.split(StringPool.POUND);
					if (splitCDs.length == 2) {
						govAgencyCode = splitCDs[0];
					}
				}
				else {
					govAgencyCode = createDossiers;
				}
				
				ServiceConfig serviceConfig = serviceConfigLocalService.getBySICodeAndGAC(groupId, dossier.getServiceCode(), govAgencyCode);
				
				if (serviceConfig != null) {
					List<ProcessOption> lstOptions = processOptionLocalService.getByServiceProcessId(serviceConfig.getServiceConfigId());
					//
//					Serializable serList = CacheLocalServiceUtil.getFromCache("ProcessOption", groupId +"_"+ serviceConfig.getServiceConfigId());
//					List<ProcessOption> lstOptions1 = null;
//					if (serList == null) {
//						lstOptions1 = ProcessOptionLocalServiceUtil.getByServiceProcessId(serviceConfig.getServiceConfigId());
//					} else {
//						lstOptions1 = (List<ProcessOption>) serList;
//						if (lstOptions1 != null) {
//							CacheLocalServiceUtil.addToCache("ProcessOption",
//									groupId + "_" + serviceConfig.getServiceConfigId(), (Serializable) lstOptions1,
//									(int) Time.MINUTE * 15);
//						}
//					}
					
					//Tìm kiếm cấu hình dịch vụ công phục vụ liên thông với mẫu hồ sơ gửi liên thông
					ProcessOption foundOption = null;
					if (createDossiers.contains(StringPool.POUND)) {
						String[] splitCDs = createDossiers.split(StringPool.POUND);
						if (splitCDs.length == 2) {
							String dossierTemplateNo = splitCDs[1];
							for (ProcessOption po : lstOptions) {
								DossierTemplate dt = DossierTemplateLocalServiceUtil.fetchDossierTemplate(po.getDossierTemplateId());
								if (dt.getTemplateNo().equals(dossierTemplateNo)) {
									foundOption = po;
									break;
								}
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
//						String delegateName = dossier.getDelegateName();
						String delegateName = dossier.getGovAgencyName();
						String delegateAddress = dossier.getDelegateAddress();
						String delegateTelNo = dossier.getDelegateTelNo();
						String delegateEmail = dossier.getDelegateEmail();
						String delegateIdNo = dossier.getGovAgencyCode();
						
//						Dossier oldHslt = DossierLocalServiceUtil.getByG_AN_SC_GAC_DTNO(groupId, dossier.getApplicantIdNo(), dossier.getServiceCode(), govAgencyCode, dossierTemplate.getTemplateNo());
//						long hsltDossierId = (oldHslt != null ? oldHslt.getDossierId() : 0l);
						
						//Khởi tạo hồ sơ liên thông
						Dossier hsltDossier = dossierLocalService.initDossier(groupId, 0l, UUID.randomUUID().toString(), 
								dossier.getCounter(), dossier.getServiceCode(),
								dossier.getServiceName(), govAgencyCode, govAgencyName, dossier.getApplicantName(), 
								dossier.getApplicantIdType(), dossier.getApplicantIdNo(), dossier.getApplicantIdDate(),
								dossier.getAddress(), dossier.getCityCode(), dossier.getCityName(), dossier.getDistrictCode(), 
								dossier.getDistrictName(), dossier.getWardCode(), dossier.getWardName(), dossier.getContactName(),
								dossier.getContactTelNo(), dossier.getContactEmail(), dossierTemplate.getTemplateNo(), 
								dossier.getPassword(), dossier.getViaPostal(), dossier.getPostalAddress(), dossier.getPostalCityCode(),
								dossier.getPostalCityName(), dossier.getPostalTelNo(), 
								dossier.getOnline(), dossier.getNotification(), dossier.getApplicantNote(), DossierTerm.ORIGINALITY_DVCTT, context);
						
						//Điền thông tin người gửi cơ quan gửi vào hồ sơ liên thông
						WorkingUnit wu = WorkingUnitLocalServiceUtil.fetchByF_govAgencyCode(dossier.getGroupId(), dossier.getGovAgencyCode());

						if (wu != null) {
							delegateName = wu.getName();
							delegateAddress = wu.getAddress();
							delegateTelNo = wu.getTelNo();
							delegateEmail = wu.getEmail();
							delegateIdNo = wu.getGovAgencyCode();
							
							if (hsltDossier != null) {
								hsltDossier.setDelegateName(delegateName);
								hsltDossier.setDelegateAddress(delegateAddress);
								hsltDossier.setDelegateTelNo(delegateTelNo);
								hsltDossier.setDelegateEmail(delegateEmail);
								hsltDossier.setDelegateIdNo(delegateIdNo);
								hsltDossier.setNew(false);
								hsltDossier = dossierLocalService.updateDossier(hsltDossier);
							}							
						}
						else if (user != null) {
							if (employee != null) {
								delegateName = employee.getFullName();
								delegateAddress = dossier.getGovAgencyName();
								delegateTelNo = employee.getTelNo();
								delegateEmail = employee.getEmail();
								
								if (hsltDossier != null) {
									hsltDossier.setDelegateName(delegateName);
									hsltDossier.setDelegateAddress(delegateAddress);
									hsltDossier.setDelegateTelNo(delegateTelNo);
									hsltDossier.setDelegateEmail(delegateEmail);
									hsltDossier.setDelegateIdNo(delegateIdNo);
									hsltDossier.setNew(false);
									hsltDossier = dossierLocalService.updateDossier(hsltDossier);
								}
							}
						}

						//
						String dossierNote = StringPool.BLANK;
						if (previousAction != null) {
							dossierNote = previousAction.getActionNote();
							if (Validator.isNotNull(dossierNote)) {
								dossierNote = previousAction.getStepInstruction();
							}
						}
						
						//Chuyển trạng thái cho hồ sơ liên thông
						if (hsltDossier != null) {
							//Set HSLT dossierId to origin dossier
							hsltDossier.setOriginDossierId(dossierId);
							hsltDossier.setServerNo(ltProcess.getServerNo());
							//Update DossierName
							hsltDossier.setDossierName(dossier.getDossierName());
							hsltDossier.setOriginDossierNo(dossier.getDossierNo());
							hsltDossier = dossierLocalService.updateDossier(hsltDossier);
							
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
			else {
				
			}

			//Cập nhật hành động và quyền người dùng với hồ sơ
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
				dossierAction = dossierActionLocalService.updateDossierAction(groupId, 0, dossierId,
						serviceProcessId, dossier.getDossierActionId(), 
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
				flagChanged = updateProcessingDate(dossierAction, previousAction, curStep, dossier, curStatus, curSubStatus, prevStatus, actionConfig, option, serviceProcess, context);
				dossierAction = dossierActionLocalService.fetchDossierAction(dossier.getDossierActionId());
			}

			//Thiết lập quyền thao tác hồ sơ
			DossierActionUserImpl dossierActionUser = new DossierActionUserImpl();

			int allowAssignUser = proAction.getAllowAssignUser();
			if (allowAssignUser != ProcessActionTerm.NOT_ASSIGNED) {
				if (Validator.isNotNull(assignUsers)) {
					JSONArray assignedUsersArray = JSONFactoryUtil.createJSONArray(assignUsers);
					dossierActionUser.assignDossierActionUser(dossier, allowAssignUser,
						dossierAction, userId, groupId, proAction.getAssignUserId(),
						assignedUsersArray);
					if (OpenCPSConfigUtil.isNotificationEnable()) {
						createNotificationSMS(userId, groupId, dossier, assignedUsersArray, dossierAction, context);	
					}
				} else {
					dossierActionUser.initDossierActionUser(proAction, dossier, allowAssignUser, dossierAction, userId, groupId,
							proAction.getAssignUserId());
				}
			} else {
				//Process role as step
				if (Validator.isNotNull(curStep.getRoleAsStep())) {
					dossierActionUser.copyRoleAsStep(curStep, dossier);
				}	
				else {
					dossierActionUser.initDossierActionUser(proAction, dossier, allowAssignUser, dossierAction, userId, groupId,
							proAction.getAssignUserId());						
				}
			}

			dossier = dossierLocalService.updateDossier(dossier);
				
			//Get next step
			ProcessStep nextStep = processStepLocalService.fetchBySC_GID(postStepCode, groupId, serviceProcessId);
			if (nextStep != null) {
			}
						
			//Check if generate dossier document
			ActionConfig ac = actionConfig;
			if (ac != null) {
				if (dossier.getOriginality() != DossierTerm.ORIGINALITY_DVCTT) {
					if (Validator.isNotNull(ac.getDocumentType()) && !ac.getActionCode().startsWith("@")) {
						//Generate document
						DocumentType dt = DocumentTypeLocalServiceUtil.getByTypeCode(groupId, ac.getDocumentType());
						if (dt != null) {
							String documentCode = DocumentTypeNumberGenerator.generateDocumentTypeNumber(groupId, ac.getCompanyId(), dt.getDocumentTypeId());
							DossierDocument dossierDocument = DossierDocumentLocalServiceUtil.addDossierDoc(groupId,
									dossierId, UUID.randomUUID().toString(), dossierAction.getDossierActionId(),
									dt.getTypeCode(), dt.getDocumentName(), documentCode, 0L, dt.getDocSync(), context);
												
							//Generate PDF
							String formData = dossierAction.getPayload();
//							formDataObj = processMergeDossierProcessRole(dossier, 1, formDataObj, dossierAction);
							JSONObject formDataObj = processMergeDossierFormData(dossier, JSONFactoryUtil.createJSONObject(formData));
							formDataObj = processMergeDossierProcessRole(dossier, 1, formDataObj, dossierAction);
							formDataObj.put("url", context.getPortalURL());
							Message message = new Message();
//							_log.info("Document script: " + dt.getDocumentScript());
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
		//Create DossierSync
		String dossierRefUid = dossier.getReferenceUid();
		String syncRefUid = UUID.randomUUID().toString();
		
		//Create subcription
		createSubcription(userId, groupId, dossier, actionConfig, dossierAction, context);
		
		//Tạo thông tin đồng bộ hồ sơ
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
			List<DossierFile> lstFiles = DossierFileLocalServiceUtil.findByDID(dossierId);
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
							
							DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.fetchDossierTemplate(processOption.getDossierTemplateId());
							List<DossierPart> lstParts = DossierPartLocalServiceUtil.getByTemplateNo(groupId, dossierTemplate.getTemplateNo());
							
							List<DossierFile> lstOriginFiles = DossierFileLocalServiceUtil.findByDID(dossier.getOriginDossierId());
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
			
			List<DossierDocument> lstDossierDocuments = DossierDocumentLocalServiceUtil.getDossierDocumentList(dossierId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
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
			
			dossierSyncLocalService.updateDossierSync(groupId, userId, dossierId, dossierRefUid, syncRefUid,
					dossierAction.getPrimaryKey(), actionCode, proAction.getActionName(), actionUser, actionNote,
					syncType, actionConfig.getInfoType(), payloadObject.toJSONString(), serviceProcess.getServerNo(), state);
			
			//Gửi thông tin hồ sơ để tra cứu
			if (state == DossierSyncTerm.STATE_NOT_SYNC
					&& actionConfig != null && actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_SENT) {
				publishEvent(dossier, context);
			}
		}
		else if (actionConfig != null && actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_SENT) {
			publishEvent(dossier, context);			
		}
		
		//Thực hiện thao tác lên hồ sơ gốc hoặc hồ sơ liên thông trong trường hợp có cấu hình mappingAction
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
				Dossier originDossier = dossierLocalService.getByOrigin(groupId, dossierId);
				if (originDossier != null) {					
					ProcessOption optionOrigin = getProcessOption(originDossier.getServiceCode(), originDossier.getGovAgencyCode(),
							originDossier.getDossierTemplateNo(), groupId);
					ProcessAction actionOrigin = getProcessAction(groupId, originDossier.getDossierId(), originDossier.getReferenceUid(), actionConfig.getMappingAction(), optionOrigin.getServiceProcessId());
					doAction(groupId, userId, originDossier, optionOrigin, actionOrigin, actionConfig.getMappingAction(), actionUser, actionNote, payload, assignUsers, payment, mappingConfig.getSyncType(), context);
				}
			}
		}
		
		Indexer<Dossier> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Dossier.class);
		indexer.reindex(dossier);
		
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
	
	private void createNotificationQueue(User user, long groupId, Dossier dossier, ActionConfig actionConfig, DossierAction dossierAction, ServiceContext context) {
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
//			Serializable notiCache = CacheLocalServiceUtil.getFromCache("NotificationTemplate", groupId +"_"+ actionConfig.getNotificationType());
			Notificationtemplate notiTemplate = null;
			notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, actionConfig.getNotificationType());
//			if (notiCache == null) {
//				notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, actionConfig.getNotificationType());
//				if (notiTemplate != null) {
//					CacheLocalServiceUtil.addToCache("NotificationTemplate",
//							groupId +"_"+ actionConfig.getNotificationType(), (Serializable) notiTemplate,
//							(int) Time.MINUTE * 15);
//				}
//			} else {
//				notiTemplate = (Notificationtemplate) notiCache;
//			}

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
//							e.printStackTrace();
							_log.error(e);
							//_log.error(e);
//							e.printStackTrace();
						}
					}
				}
				else if (actionConfig.getNotificationType().startsWith("USER")) {
					
				}				
			}
		}	
		
//		Notificationtemplate emplTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, "EMPL-01");
//		Serializable notiCache = CacheLocalServiceUtil.getFromCache("NotificationTemplate", groupId +"_"+ "EMPL-01");
		Notificationtemplate emplTemplate = null;
		emplTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, "EMPL-01");
//		if (notiCache == null) {
//			emplTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, "EMPL-01");
//			if (emplTemplate != null) {
//				CacheLocalServiceUtil.addToCache("NotificationTemplate",
//						groupId +"_"+ actionConfig.getNotificationType(), (Serializable) emplTemplate,
//						(int) Time.MINUTE * 15);
//			}
//		} else {
//			emplTemplate = (Notificationtemplate) notiCache;
//		}

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
//								Serializable employeeCache = CacheLocalServiceUtil.getFromCache("Employee", groupId +"_"+ dau.getUserId());
								Employee employee = null;
								employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, dau.getUserId());
//								if (employeeCache == null) {
//									employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, dau.getUserId());
//									if (employee != null) {
//										CacheLocalServiceUtil.addToCache("Employee",
//												groupId +"_"+ dau.getUserId(), (Serializable) employee,
//												(int) Time.MINUTE * 15);
//									}
//								} else {
//									employee = (Employee) employeeCache;
//								}

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
		long serviceProcessId = (option != null ? option.getServiceProcessId() : previousAction.getServiceProcessId());
		ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);
		String paymentFee = StringPool.BLANK;
		
		//Yêu cầu nộp tạm ứng
		if (proAction.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_YEU_CAU_NOP_TAM_UNG
				|| proAction.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_YEU_CAU_QUYET_TOAN_PHI && Validator.isNotNull(payment)) {
			Long feeAmount = 0l, serviceAmount = 0l, shipAmount = 0l;
			String paymentNote = StringPool.BLANK;
			long advanceAmount = 0l;
			long paymentAmount = 0l;
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
					PaymentFile paymentFile = paymentFileLocalService.updateApplicantFeeAmount(oldPaymentFile.getPaymentFileId(),
							proAction.getRequestPayment(), feeAmount, serviceAmount, shipAmount);
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
				paymentAmount = feeAmount + serviceAmount + shipAmount - advanceAmount;
				
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
			User u = UserLocalServiceUtil.fetchUser(userId);
			
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
						StepConfig stepConfig = StepConfigLocalServiceUtil.getByCode(groupId, dossierAction.getStepCode());
						List<DossierActionUser> lstDaus = DossierActionUserLocalServiceUtil.getByDID_DAI_SC_AS(dossier.getDossierId(), dossierAction.getDossierActionId(), dossierAction.getStepCode(), new int[] { 1, 2 });
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
			transactionId = CounterLocalServiceUtil.increment(PaymentFile.class.getName() + ".genetatorTransactionId");
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
	
	protected int getActionDueDate(long groupId, long dossierId, String refId, long processActionId) {
		// TODO add implement here

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
				
//				dossier = DossierLocalServiceUtil.updateDossier(dossier);
//			} catch (PortalException e) {
//				_log.error(e);
//				e.printStackTrace();
//			}
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
			dossierAction = DossierActionLocalServiceUtil.updateState(dossierAction.getDossierActionId(), DossierActionTerm.STATE_ALREADY_PROCESSED);								
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
					dossierAction = DossierActionLocalServiceUtil.updateState(dossierAction.getDossierActionId(), DossierActionTerm.STATE_ALREADY_PROCESSED);					
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
			DossierAction dActEnd = DossierActionLocalServiceUtil
					.fetchDossierAction(dossierAction.getDossierActionId());
//			DossierAction dActEnd = dossierAction;
			if (dActEnd != null) {
				_log.debug("dActEnd.getPreviousActionId(): "+dActEnd.getPreviousActionId());
				DossierAction dActStart = DossierActionLocalServiceUtil
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
			List<DossierFile> lstFiles = DossierFileLocalServiceUtil.getAllDossierFile(dossier.getDossierId());
			for (DossierFile df : lstFiles) {
				if (!df.getRemoved()) {
					df.setOriginal(true);
				}
				DossierFileLocalServiceUtil.updateDossierFile(df);
			}
		}
		
		//Calculate step due date
//		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
//		_log.info("dossierAction: "+dossierAction);
		dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
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
				DossierActionLocalServiceUtil.updateDossierAction(dossierAction);
//				_log.info("dActTest: "+dActTest);
			}
			else {
				DossierActionLocalServiceUtil.updateDossierAction(dossierAction);
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
		jsonData.put(DossierTerm.SERVICE_NAME, dossier.getServiceName());
		jsonData.put(DossierTerm.SAMPLE_COUNT, dossier.getSampleCount());
		jsonData.put(DossierTerm.DURATION_COUNT, dossier.getDurationCount());
		jsonData.put(DossierTerm.DURATION_UNIT, dossier.getDurationUnit());
		jsonData.put(DossierTerm.RECEIVE_DATE,
				APIDateTimeUtils.convertDateToString(dossier.getReceiveDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		jsonData.put(DossierTerm.DUE_DATE,
				APIDateTimeUtils.convertDateToString(dossier.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		if (dossier.getExtendDate() != null) {
			jsonData.put(DossierTerm.EXTEND_DATE,
					APIDateTimeUtils.convertDateToString(dossier.getExtendDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		}
		jsonData.put(DossierTerm.POSTAL_ADDRESS, dossier.getPostalAddress());
		jsonData.put(DossierTerm.COUNTER, dossier.getCounter());
		jsonData.put(DossierTerm.REGISTER_BOOK_CODE, dossier.getRegisterBookCode());
		//
		long groupId = dossier.getGroupId();
		JSONArray dossierMarkArr = JSONFactoryUtil.createJSONArray();
		long dossierId = dossier.getDossierId();
		String templateNo = dossier.getDossierTemplateNo();
		List<DossierMark> dossierMarkList = DossierMarkLocalServiceUtil.getDossierMarks(groupId, dossierId);
		if (dossierMarkList != null && dossierMarkList.size() > 0) {
			JSONObject jsonMark = null;
			String partNo;
			for (DossierMark dossierMark : dossierMarkList) {
				jsonMark = JSONFactoryUtil.createJSONObject();
				partNo = dossierMark.getDossierPartNo();
				if (Validator.isNotNull(partNo)) {
					DossierPart part = DossierPartLocalServiceUtil.getByTempAndPartNo(groupId, templateNo, partNo);
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
		jsonData.put(DossierTerm.DOSSIER_MARKS, dossierMarkArr);
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
				ProcessSequence sequence = ProcessSequenceLocalServiceUtil.findBySID_SNO(groupId, serviceProcessId,
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
			List<ProcessSequence> sequenceList = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId,
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
							ProcessSequence sequence = ProcessSequenceLocalServiceUtil.findBySID_SNO(groupId,
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
					PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);										
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
				List<PublishQueue> lstQueues = PublishQueueLocalServiceUtil.getByG_DID_SN_ST(dossier.getGroupId(), dossier.getDossierId(), sc.getServerNo(), new int[] { PublishQueueTerm.STATE_WAITING_SYNC, PublishQueueTerm.STATE_ALREADY_SENT });
				if (lstQueues == null || lstQueues.isEmpty()) {
					PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);					
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

		ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceInfoCode, govAgencyCode);

		return ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTemplateNo,
				config.getServiceConfigId());
	}
	
	protected ProcessAction getProcessAction(long groupId, long dossierId, String refId, String actionCode,
			long serviceProcessId) throws PortalException {

		ProcessAction action = null;

		try {
			List<ProcessAction> actions = ProcessActionLocalServiceUtil.getByActionCode(groupId, actionCode,
					serviceProcessId);
			Dossier dossier = getDossier(groupId, dossierId, refId);

			String dossierStatus = dossier.getDossierStatus();

			String dossierSubStatus = Validator.isNull(dossier.getDossierSubStatus()) ? StringPool.BLANK
					: dossier.getDossierSubStatus();

			for (ProcessAction act : actions) {

				String preStepCode = act.getPreStepCode();

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
			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		} else {
			dossier = DossierLocalServiceUtil.getByRef(groupId, refId);
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

		ServiceProcess serviceProcess = null;
		if (option != null) {
			long serviceProcessId = option.getServiceProcessId();
			serviceProcess = serviceProcessLocalService.fetchServiceProcess(serviceProcessId);
		}
		
		dossierAction = dossierActionLocalService.fetchDossierAction(dossier.getDossierActionId());
		ActionConfig ac = actionConfigLocalService.getByCode(groupId, actionCode);
		JSONObject payloadObject = JSONFactoryUtil.createJSONObject(payload);
		
		if (Validator.isNotNull(payload)) {
			if (DossierActionTerm.OUTSIDE_ACTION_9100.equals(actionCode)) {
				dossier = dossierLocalService.updateDossierSpecial(dossier.getDossierId(), payloadObject);							
			}
			else {
				dossier = dossierLocalService.updateDossier(dossier.getDossierId(), payloadObject);											
			}
		}
		
		//Create DossierSync
		String dossierRefUid = dossier.getReferenceUid();
		String syncRefUid = UUID.randomUUID().toString();
		if (syncType > 0) {
			int state = DossierActionUtils.getSyncState(syncType, dossier);
			//Update payload
			dossierSyncLocalService.updateDossierSync(groupId, userId, dossier.getDossierId(), dossierRefUid, syncRefUid,
					dossierAction.getPrimaryKey(), actionCode, ac.getActionName(), actionUser, actionNote,
					syncType, ac.getInfoType(), payloadObject.toJSONString(), serviceProcess != null ? serviceProcess.getServerNo() : StringPool.BLANK, state);
			
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
		
		return dossierAction;
	}
	
	private void createNotificationQueueOutsideProcess(long userId, long groupId, Dossier dossier, ActionConfig actionConfig, ServiceContext context) {
		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
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
	
	private static final long VALUE_CONVERT_DATE_TIMESTAMP = 1000 * 60 * 60 * 24;
	private static final long VALUE_CONVERT_HOUR_TIMESTAMP = 1000 * 60 * 60;

	private Log _log = LogFactoryUtil.getLog(CPSDossierBusinessLocalServiceImpl.class);
}