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

import com.fasterxml.jackson.databind.ObjectMapper;
import backend.auth.api.exception.BusinessExceptionImpl;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
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
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpMethods;
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

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.adminconfig.model.DynamicReport;
import org.opencps.adminconfig.service.DynamicReportLocalServiceUtil;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.keys.NotificationType;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.cache.actions.CacheActions;
import org.opencps.cache.actions.impl.CacheActionsImpl;
import org.opencps.communication.constants.NotificationTemplateTerm;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.util.BetimeUtils;
import org.opencps.datamgt.util.DueDatePhaseUtil;
import org.opencps.datamgt.util.DueDateUtils;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.DossierUserActions;
import org.opencps.dossiermgt.action.impl.DVCQGIntegrationActionImpl;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierPermission;
import org.opencps.dossiermgt.action.impl.DossierUserActionsImpl;
import org.opencps.dossiermgt.action.util.AccentUtils;
import org.opencps.dossiermgt.action.util.AutoFillFormData;
import org.opencps.dossiermgt.action.util.ConfigCounterNumberGenerator;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.DocumentTypeNumberGenerator;
import org.opencps.dossiermgt.action.util.DossierActionUtils;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.action.util.DossierNumberGenerator;
import org.opencps.dossiermgt.action.util.DossierPaymentUtils;
import org.opencps.dossiermgt.action.util.KeyPay;
import org.opencps.dossiermgt.action.util.NotificationUtil;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.action.util.PaymentUrlGenerator;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.action.util.VNPostCLSUtils;
import org.opencps.dossiermgt.constants.*;
import org.opencps.dossiermgt.exception.DataConflictException;
import org.opencps.dossiermgt.exception.NoSuchDossierUserException;
import org.opencps.dossiermgt.exception.NoSuchPaymentFileException;
import org.opencps.dossiermgt.input.model.DossierInputModel;
import org.opencps.dossiermgt.input.model.DossierMultipleInputModel;
import org.opencps.dossiermgt.input.model.PaymentFileInputModel;
import org.opencps.dossiermgt.input.model.ProfileInModel;
import org.opencps.dossiermgt.input.model.FrequencyDoAction;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.ConfigCounter;
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
import org.opencps.dossiermgt.model.Notarization;
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
import org.opencps.dossiermgt.model.impl.DossierImpl;
import org.opencps.dossiermgt.model.impl.DossierModelImpl;
import org.opencps.dossiermgt.rest.utils.ExecuteOneActionTerm;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.*;
import org.opencps.dossiermgt.service.base.CPSDossierBusinessLocalServiceBaseImpl;
import org.opencps.dossiermgt.service.persistence.DossierActionUserPK;
import org.opencps.dossiermgt.service.persistence.DossierUserPK;
import org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK;
import org.opencps.usermgt.constants.ApplicantDataTerm;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.FileItem;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.ApplicantDataLocalServiceUtil;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.FileItemLocalServiceUtil;
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
@Transactional(isolation = Isolation.PORTAL, rollbackFor = { PortalException.class, SystemException.class })
public class CPSDossierBusinessLocalServiceImpl extends CPSDossierBusinessLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.CPSDossierBusinessLocalServiceUtil} to access the cps dossier business local service.
	 */
	private static final String BN_TELEPHONE = "BN_telephone";
	private static final String BN_ADDRESS = "BN_address";
	private static final String BN_EMAIL = "BN_email";

	public static final String DOSSIER_SATUS_DC_CODE = "DOSSIER_STATUS";
	public static final String DOSSIER_SUB_SATUS_DC_CODE = "DOSSIER_SUB_STATUS";

	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	public static final String PAYLOAD_KEY_COMPLEMENT_DATE = "complementDate";
	public static final String PAYLOAD_KEY_dOSSIER_DOCUMENT = "dossierDocument";
	public static final String CACHE_NOTIFICATION_TEMPLATE = "NotificationTemplate";

	CacheActions cache = new CacheActionsImpl();
	int ttl = OpenCPSConfigUtil.getCacheTTL();

	private Dossier createCrossDossier(long groupId, ProcessAction proAction, ProcessStep curStep,
			DossierAction previousAction, Employee employee, Dossier dossier, User user, JSONObject payloadObj,
			ServiceContext context) throws PortalException {
		String createDossiers = null;
		_log.info("payloadObj Action: "+ payloadObj);
		if (payloadObj != null && payloadObj.has("createDossiers") && Validator.isNotNull(payloadObj.get("createDossiers"))) {
			if (Validator.isNotNull(proAction.getCreateDossiers())) {
				_log.info("proAction.getCreateDossiers(): "+proAction.getCreateDossiers());
				if (proAction.getCreateDossiers().contains(StringPool.POUND)) {
					String[] splitCDs = createDossiers.split(StringPool.POUND);
					if (splitCDs.length == 2) {
						createDossiers = String.valueOf(payloadObj.get("createDossiers")) + StringPool.POUND + splitCDs[1];
					} else {
						createDossiers = String.valueOf(payloadObj.get("createDossiers"));
					}
				} else if (proAction.getCreateDossiers().contains(StringPool.AT)) {
					String[] splitCDs = createDossiers.split(StringPool.AT);
					if (splitCDs.length != 2) {
						createDossiers = String.valueOf(payloadObj.get("createDossiers"));
					} else {
						createDossiers = String.valueOf(payloadObj.get("createDossiers")) + StringPool.AT + splitCDs[1];
					}
				} else {
					createDossiers = String.valueOf(payloadObj.get("createDossiers"));
				}
			} else {
				createDossiers = String.valueOf(payloadObj.get("createDossiers"));
			}
		} else if (Validator.isNotNull(proAction.getCreateDossiers())) {
			createDossiers = proAction.getCreateDossiers();
		}
		_log.info("createDossiers: "+createDossiers);
		if (Validator.isNotNull(createDossiers)) {
			//Create new HSLT
			String GOVERNMENT_AGENCY = ReadFilePropertiesUtils.get(ConstantUtils.GOVERNMENT_AGENCY);
			_log.info("GOVERNMENT_AGENCY: "+GOVERNMENT_AGENCY);

			//String createDossiers = proAction.getCreateDossiers();
			/*if (Validator.isNotNull(createDossiers) && createDossiers.contentEquals("$interoperaGovAgencyCode")) {
			}*/
			String govAgencyName = getDictItemName(groupId, GOVERNMENT_AGENCY, createDossiers);
			_log.info("govAgencyName: "+ govAgencyName);
			String govAgencyCode = StringPool.BLANK;
			String serviceCode = dossier.getServiceCode();
			String dossierTemplateNo = dossier.getDossierTemplateNo();
			if (createDossiers.contains(StringPool.POUND)) {
				String[] splitCDs = createDossiers.split(StringPool.POUND);
				if (splitCDs.length == 2) {
					govAgencyCode = splitCDs[0];

					if (splitCDs[1].contains(StringPool.AT)) {
						if (splitCDs[1].split(StringPool.AT).length != 2) {
							throw new PortalException(ReadFilePropertiesUtils.get(ConstantUtils.MSG_ERROR));
						} else {
							dossierTemplateNo = splitCDs[1].split(StringPool.AT)[0];
							serviceCode = splitCDs[1].split(StringPool.AT)[1];
						}
					} else {
						govAgencyCode = splitCDs[0];
						dossierTemplateNo = splitCDs[1];
					}
				}
			} else {
				if (createDossiers.contains(StringPool.AT)) {
					if (createDossiers.split(StringPool.AT).length != 2) {
						throw new PortalException(ReadFilePropertiesUtils.get(ConstantUtils.MSG_ERROR));
					} else {
						govAgencyCode = createDossiers.split(StringPool.AT)[0];
						serviceCode = createDossiers.split(StringPool.AT)[1];
					}
				} else {
					govAgencyCode = createDossiers;
				}
			}
			_log.info("govAgencyCode: " + govAgencyCode);
			_log.info("serviceCode: " + serviceCode);
			_log.info("dossierTemplateNo: " + dossierTemplateNo);

			ServiceConfig serviceConfig = serviceConfigLocalService.getBySICodeAndGAC(groupId, dossier.getServiceCode(),
					govAgencyCode);
			_log.info("serviceConfig: " + serviceConfig);
			if (serviceConfig != null) {
				List<ProcessOption> lstOptions = processOptionLocalService
						.getByServiceProcessId(serviceConfig.getServiceConfigId());
				//

				ProcessOption foundOption = null;
				if (createDossiers.contains(StringPool.POUND)) {
					for (ProcessOption po : lstOptions) {
						DossierTemplate dt = dossierTemplateLocalService
								.fetchDossierTemplate(po.getDossierTemplateId());
						if (dt.getTemplateNo().equals(dossierTemplateNo)) {
							foundOption = po;
							break;
						}
					}
				} else {
					if (lstOptions.size() > 0) {
						foundOption = lstOptions.get(0);
					}
				}
				if (foundOption != null) {
					ServiceProcess ltProcess = serviceProcessLocalService
							.fetchServiceProcess(foundOption.getServiceProcessId());

					DossierTemplate dossierTemplate = dossierTemplateLocalService
							.fetchDossierTemplate(foundOption.getDossierTemplateId());
					//					String delegateName = dossier.getDelegateName();
					//String delegateName = dossier.getGovAgencyName();
					//String delegateAddress = dossier.getDelegateAddress();
					//String delegateTelNo = dossier.getDelegateTelNo();
					//String delegateEmail = dossier.getDelegateEmail();
					//String delegateIdNo = dossier.getGovAgencyCode();

					JSONObject crossDossierObj = JSONFactoryUtil.createJSONObject();
					crossDossierObj.put(DossierTerm.DOSSIER_TEMPLATE_NO, dossierTemplate.getTemplateNo());
					crossDossierObj.put(DossierTerm.GOV_AGENCY_CODE, govAgencyCode);
					crossDossierObj.put(DossierTerm.SERVICE_CODE, serviceCode);
					payloadObj.put(DossierTerm.CROSS_DOSSIER, crossDossierObj);

					Dossier oldHslt = DossierLocalServiceUtil.getByG_AN_SC_GAC_DTNO_ODID(groupId,
							dossier.getApplicantIdNo(), dossier.getServiceCode(), govAgencyCode,
							dossierTemplate.getTemplateNo(), dossier.getDossierId());
					Dossier hsltDossier = null;

					if (oldHslt != null) {
						if (oldHslt.getOriginality() < 0) {
							oldHslt.setOriginality(-oldHslt.getOriginality());
						}
						hsltDossier = oldHslt;
					}
					if (hsltDossier == null) {
						hsltDossier = dossierLocalService.initDossier(groupId, 0l, UUID.randomUUID().toString(),
								dossier.getCounter(), dossier.getServiceCode(), dossier.getServiceName(), govAgencyCode,
								govAgencyName, dossier.getApplicantName(), dossier.getApplicantIdType(),
								dossier.getApplicantIdNo(), dossier.getApplicantIdDate(), dossier.getAddress(),
								dossier.getCityCode(), dossier.getCityName(), dossier.getDistrictCode(),
								dossier.getDistrictName(), dossier.getWardCode(), dossier.getWardName(),
								dossier.getContactName(), dossier.getContactTelNo(), dossier.getContactEmail(),
								dossierTemplate.getTemplateNo(), dossier.getPassword(), dossier.getViaPostal(),
								dossier.getPostalAddress(), dossier.getPostalCityCode(), dossier.getPostalCityName(),
								dossier.getPostalDistrictCode(), dossier.getPostalDistrictName(),
								dossier.getPostalTelNo(), dossier.getOnline(), dossier.getNotification(),
								dossier.getApplicantNote(), DossierTerm.ORIGINALITY_DVCTT, context);
					}
					_log.info("hsltDossier: " + hsltDossier);
					WorkingUnit wu = WorkingUnitLocalServiceUtil.fetchByF_govAgencyCode(dossier.getGroupId(),
							dossier.getGovAgencyCode());

					String delegateName = null;
					String delegateAddress = null;
					String delegateTelNo = null;
					String delegateEmail = null;
					String delegateIdNo = null;
					delegateIdNo = dossier.getGovAgencyCode();
					if (wu != null) {
						delegateName = wu.getName();
						delegateAddress = wu.getAddress();
						delegateTelNo = wu.getTelNo();
						delegateEmail = wu.getEmail();
						//new 3.0 comment
						//						delegateIdNo = wu.getGovAgencyCode();
					} else if (user != null && employee != null) {
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
								hsltDossier.setServerNo(serverNoProcess + StringPool.AT + serviceCode + StringPool.COMMA
										+ ltProcess.getServerNo().split(StringPool.COMMA)[1]);
								hsltDossier = dossierLocalService.updateDossier(hsltDossier);
							}
						} else {
							hsltDossier.setServerNo(ltProcess.getServerNo());
						}
						//Update DossierName
						hsltDossier.setDossierName(dossier.getDossierName());
						hsltDossier.setOriginDossierNo(dossier.getDossierNo());
						dossierLocalService.updateDossier(hsltDossier);

						JSONObject jsonDataStatusText = getStatusText(groupId,
								ReadFilePropertiesUtils.get(ConstantUtils.DOSSIER_STATUS),
								ReadFilePropertiesUtils.get(ConstantUtils.STATUS_NEW), StringPool.BLANK);
						hsltDossier = dossierLocalService.updateStatus(groupId, hsltDossier.getDossierId(),
								hsltDossier.getReferenceUid(), ReadFilePropertiesUtils.get(ConstantUtils.STATUS_NEW),
								jsonDataStatusText != null
										? jsonDataStatusText
										.getString(ReadFilePropertiesUtils.get(ConstantUtils.STATUS_NEW))
										: StringPool.BLANK,
								StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, dossierNote, context);
					} else {
						return null;
					}
					JSONObject jsonDataStatusText = getStatusText(groupId,
							ReadFilePropertiesUtils.get(ConstantUtils.DOSSIER_STATUS),
							DossierTerm.DOSSIER_STATUS_INTEROPERATING, StringPool.BLANK);
					if (curStep != null) {
						dossier = dossierLocalService.updateStatus(groupId, dossier.getDossierId(),
								dossier.getReferenceUid(), DossierTerm.DOSSIER_STATUS_INTEROPERATING,
								jsonDataStatusText != null
										? jsonDataStatusText.getString(DossierTerm.DOSSIER_STATUS_INTEROPERATING)
										: StringPool.BLANK,
								StringPool.BLANK, StringPool.BLANK, curStep.getLockState(), dossierNote, context);
						dossier.setDossierStatus(DossierTerm.DOSSIER_STATUS_INTEROPERATING);
						dossier.setDossierStatusText(jsonDataStatusText != null
								? jsonDataStatusText.getString(DossierTerm.DOSSIER_STATUS_INTEROPERATING)
								: StringPool.BLANK);
						dossier.setDossierSubStatus(StringPool.BLANK);
						dossier.setDossierSubStatusText(StringPool.BLANK);
						if (dossier != null && !DossierTerm.PAUSE_OVERDUE_LOCK_STATE.equals(dossier.getLockState())) {

							dossier.setLockState(curStep.getLockState());
						}
						dossier.setDossierNote(dossierNote);
						;
					}

					return hsltDossier;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private void createDossierDocument(long groupId, long userId, ActionConfig actionConfig, Dossier dossier,
									   DossierAction dossierAction, JSONObject payloadObject, Employee employee, User user, ServiceContext context)
			throws com.liferay.portal.kernel.search.ParseException, JSONException, SearchException {
		//Check if generate dossier document
		ActionConfig ac = actionConfig;
		if (ac != null) {
			if (dossier.getOriginality() != DossierTerm.ORIGINALITY_DVCTT) {
				if (Validator.isNotNull(ac.getDocumentType()) && !ac.getActionCode().startsWith(StringPool.AT)) {
					//Generate document
					String[] documentTypes = ac.getDocumentType().split(StringPool.COMMA);
					for (String documentType : documentTypes) {
						DocumentType dt = DocumentTypeLocalServiceUtil.getByTypeCode(groupId, documentType.trim());
						if (dt != null) {
							String documentCode = DocumentTypeNumberGenerator.generateDossierDocumentNumber(groupId,
									dossier.getCompanyId(), dossier.getServiceCode(), dossier.getGovAgencyCode(),
									dt.getCodePattern());
							String refUid = UUID.randomUUID().toString();
							if (Validator.isNotNull(dossier.getOriginDossierNo())
									&& dossier.getOriginDossierId() == 0) {
								refUid = DossierTerm.PREFIX_UUID + refUid;
							}
							DossierDocument dossierDocument = DossierDocumentLocalServiceUtil.addDossierDoc(groupId,
									dossier.getDossierId(), refUid, dossierAction.getDossierActionId(),
									dt.getTypeCode(), dt.getDocumentName(), documentCode, 0L, dt.getDocSync(), context);

							//Generate PDF
							String formData = dossierAction.getPayload();
							JSONObject payloadTmp = JSONFactoryUtil.createJSONObject(formData);
							if (payloadTmp != null && payloadTmp.has(PAYLOAD_KEY_COMPLEMENT_DATE)) {
								if (payloadTmp.getLong(PAYLOAD_KEY_COMPLEMENT_DATE) > 0) {
									Timestamp ts = new Timestamp(payloadTmp.getLong(PAYLOAD_KEY_COMPLEMENT_DATE));
									SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
									payloadTmp.put(PAYLOAD_KEY_COMPLEMENT_DATE, format.format(ts));
								}
							}

							JSONObject formDataObj = processMergeDossierFormData(dossier, payloadTmp);
							formDataObj = processMergeDossierProcessRole(dossier, 1, formDataObj, dossierAction);
							formDataObj.put(ConstantUtils.VALUE_URL, context.getPortalURL());
							if (employee != null) {
								formDataObj.put(Field.USER_NAME, employee.getFullName());
							} else {
								formDataObj.put(Field.USER_NAME, user.getFullName());
							}

							Message message = new Message();
							//						_log.info("Document script: " + dt.getDocumentScript());
							JSONObject msgData = JSONFactoryUtil.createJSONObject();
							msgData.put(ConstantUtils.CLASS_NAME, DossierDocument.class.getName());
							msgData.put(Field.CLASS_PK, dossierDocument.getDossierDocumentId());
							msgData.put(ConstantUtils.JRXML_TEMPLATE, dt.getDocumentScript());
							msgData.put(ConstantUtils.FORM_DATA, formDataObj.toJSONString());
							msgData.put(Field.USER_ID, userId);

							message.put(ConstantUtils.MSG_ENG, msgData);
							MessageBusUtil.sendMessage(ConstantUtils.JASPER_DESTINATION, message);

							payloadObject.put(DossierDocumentTerm.DOSSIER_DOCUMENT_ID,
									dossierDocument.getDossierDocumentId());
						}
					}
				}
			}
		}
	}

	private boolean createDossierDocumentPostAction(long groupId, long userId, Dossier dossier,
			DossierAction dossierAction, JSONObject payloadObject, Employee employee, User user,
			String documentTypeList, ServiceContext context)
			throws com.liferay.portal.kernel.search.ParseException, JSONException, SearchException {
		//Check if generate dossier document
		if (dossier.getOriginality() != DossierTerm.ORIGINALITY_DVCTT) {
			// Generate document
			String[] documentTypes = documentTypeList.split(StringPool.COMMA);
			for (String documentType : documentTypes) {
				DocumentType dt = DocumentTypeLocalServiceUtil.getByTypeCode(groupId, documentType.trim());
				if (dt != null) {
					String documentCode = DocumentTypeNumberGenerator.generateDossierDocumentNumber(groupId,
							dossier.getCompanyId(), dossier.getServiceCode(), dossier.getGovAgencyCode(),
							dt.getCodePattern());
					DossierDocument dossierDocument = DossierDocumentLocalServiceUtil.addDossierDoc(groupId,
							dossier.getDossierId(), UUID.randomUUID().toString(), dossierAction.getDossierActionId(),
							dt.getTypeCode(), dt.getDocumentName(), documentCode, 0L, dt.getDocSync(), context);

					// Generate PDF
					String formData = dossierAction.getPayload();
					JSONObject payloadTmp = JSONFactoryUtil.createJSONObject(formData);
					if (payloadTmp != null && payloadTmp.has(PAYLOAD_KEY_COMPLEMENT_DATE)) {
						if (payloadTmp.getLong(PAYLOAD_KEY_COMPLEMENT_DATE) > 0) {
							Timestamp ts = new Timestamp(payloadTmp.getLong(PAYLOAD_KEY_COMPLEMENT_DATE));
							SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
							payloadTmp.put(PAYLOAD_KEY_COMPLEMENT_DATE, format.format(ts));
						}
					}

					JSONObject formDataObj = processMergeDossierFormData(dossier, payloadTmp);
					formDataObj = processMergeDossierProcessRole(dossier, 1, formDataObj, dossierAction);
					formDataObj.put(ConstantUtils.VALUE_URL, context.getPortalURL());
					formDataObj.put(DossierDocumentTerm.DOCUMENT_CODE, documentCode);
					if (employee != null) {
						formDataObj.put(Field.USER_NAME, employee.getFullName());
					} else {
						formDataObj.put(Field.USER_NAME, user.getFullName());
					}

					Message message = new Message();
					// _log.info("Document script: " + dt.getDocumentScript());
					JSONObject msgData = JSONFactoryUtil.createJSONObject();
					msgData.put(ConstantUtils.CLASS_NAME, DossierDocument.class.getName());
					msgData.put(Field.CLASS_PK, dossierDocument.getDossierDocumentId());
					msgData.put(ConstantUtils.JRXML_TEMPLATE, dt.getDocumentScript());
					msgData.put(ConstantUtils.FORM_DATA, formDataObj.toJSONString());
					msgData.put(Field.USER_ID, userId);

					message.put(ConstantUtils.MSG_ENG, msgData);
					MessageBusUtil.sendMessage(ConstantUtils.JASPER_DESTINATION, message);

					payloadObject.put(PAYLOAD_KEY_dOSSIER_DOCUMENT, dossierDocument.getDossierDocumentId());
				}
			}
		}
		return true;
	}

	private void createDossierSync(long groupId, long userId, ActionConfig actionConfig, ProcessAction proAction,
								   DossierAction dossierAction, Dossier dossier, int syncType, ProcessOption option, JSONObject payloadObject,
								   Map<String, Boolean> flagChanged, String actionCode, String actionUser, String actionNote,
								   ServiceProcess serviceProcess, ServiceContext context) throws PortalException {
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
			} else {
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
				} else {
					//					ServiceConfig serviceConfig = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, dossier.getServiceCode(), dossier.getGovAgencyCode());
					//					List<ProcessOption> lstOptions = ProcessOptionLocalServiceUtil.getByServiceProcessId(serviceConfig.getServiceConfigId());

					//					if (serviceConfig != null) {
					//						if (lstOptions.size() > 0) {
					//							ProcessOption processOption = lstOptions.get(0);
					ProcessOption processOption = option;

					DossierTemplate dossierTemplate = dossierTemplateLocalService
							.fetchDossierTemplate(processOption.getDossierTemplateId());
					List<DossierPart> lstParts = dossierPartLocalService.getByTemplateNo(groupId,
							dossierTemplate.getTemplateNo());

					List<DossierFile> lstOriginFiles = dossierFileLocalService.findByDID(dossier.getOriginDossierId());
					if (lstOriginFiles.size() > 0) {
						List<String> lstCheckParts = new ArrayList<String>();

						if (payloadObject.has(DossierSyncTerm.PAYLOAD_SYNC_DOSSIER_PARTS)) {
							try {
								JSONArray partArrs = payloadObject
										.getJSONArray(DossierSyncTerm.PAYLOAD_SYNC_DOSSIER_PARTS);
								if (partArrs != null && partArrs.length() > 0) {
									for (int tempI = 0; tempI <= partArrs.length(); tempI++) {
										JSONObject partObj = partArrs.getJSONObject(tempI);
										lstCheckParts.add(partObj.getString(DossierPartTerm.PART_NO));
									}
								}
							} catch (Exception e) {
								_log.debug(e);
							}
						}

						for (DossierFile df : lstOriginFiles) {
							boolean flagHslt = false;
							for (DossierPart dp : lstParts) {
								if (dp.getPartNo().equals(df.getDossierPartNo()) && (lstCheckParts.size() == 0
										|| (lstCheckParts.size() > 0 && lstCheckParts.contains(dp.getPartNo())))) {
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
			} else {
				//Sync result files
				if (Validator.isNotNull(dossier.getDossierNo())) {
					payloadObject.put(DossierTerm.DOSSIER_NO, dossier.getDossierNo());
				}
			}

			if (actionConfig.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST
					|| actionConfig.getSyncType() == DossierSyncTerm.SYNCTYPE_INFORM) {

				payloadObject.put(DossierTerm.CONSTANT_DOSSIER_FILES, dossierFilesArr);

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
					payloadObject.put(DossierTerm.CONSTANT_DOSSIER_FILES, dossierFilesArr);
				}

				List<DossierDocument> lstDossierDocuments = dossierDocumentLocalService
						.getDossierDocumentList(dossier.getDossierId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				JSONArray dossierDocumentArr = JSONFactoryUtil.createJSONArray();

				for (DossierDocument dossierDocument : lstDossierDocuments) {
					JSONObject dossierDocumentObj = JSONFactoryUtil.createJSONObject();
					dossierDocumentObj.put(DossierDocumentTerm.REFERENCE_UID, dossierDocument.getReferenceUid());
					dossierDocumentArr.put(dossierDocumentObj);
				}
				payloadObject.put(DossierTerm.CONSTANT_DOSSIER_FILES, dossierFilesArr);
				payloadObject.put(DossierTerm.CONSTANT_DOSSIER_DOC, dossierDocumentArr);

				//Put dossier note
				payloadObject.put(DossierTerm.DOSSIER_NOTE, dossier.getDossierNote());
				//Put dossier note
				payloadObject.put(DossierTerm.SUBMIT_DATE,
						dossier.getSubmitDate() != null ? dossier.getSubmitDate().getTime() : 0);

				//			_log.info("Flag changed: " + flagChanged);
				payloadObject = DossierActionUtils.buildChangedPayload(payloadObject, flagChanged, dossier);
				//Always inform due date
				if (actionConfig.getSyncType() == DossierSyncTerm.SYNCTYPE_INFORM
						&& Validator.isNotNull(dossier.getDueDate())) {
					payloadObject.put(DossierTerm.DUE_DATE, dossier.getDueDate().getTime());
				}
				if (actionConfig.getSyncType() == DossierSyncTerm.SYNCTYPE_INFORM
						&& Validator.isNotNull(dossier.getReceiveDate())) {
					payloadObject.put(DossierTerm.RECEIVE_DATE, dossier.getReceiveDate().getTime());
				}
				if (Validator.isNotNull(dossier.getServerNo())
						&& dossier.getServerNo().split(StringPool.COMMA).length > 1) {
					String serverNo = null;
					if (syncType == 1) {
						serverNo = dossier.getServerNo().split(StringPool.COMMA)[0].split(StringPool.AT)[0];
					} else {
						serverNo = dossier.getServerNo().split(StringPool.COMMA)[1].split(StringPool.AT)[0];
					}
					//String serverNo = dossier.getServerNo().split(StringPool.COMMA)[0].split(StringPool.AT)[0];
					if (Validator.isNotNull(serverNo)) {
						dossierSyncLocalService.updateDossierSync(groupId, userId, dossier.getDossierId(), dossierRefUid,
								syncRefUid, dossierAction.getPrimaryKey(), actionCode, proAction.getActionName(),
								actionUser, actionNote, syncType, actionConfig.getInfoType(), payloadObject.toJSONString(),
								serverNo, state);
					}
				} else {
					dossierSyncLocalService.updateDossierSync(groupId, userId, dossier.getDossierId(), dossierRefUid,
							syncRefUid, dossierAction.getPrimaryKey(), actionCode, proAction.getActionName(),
							actionUser, actionNote, syncType, actionConfig.getInfoType(), payloadObject.toJSONString(),
							dossier.getServerNo(), state);
				}
				//Gửi thông tin hồ sơ để tra cứu
				if (state == DossierSyncTerm.STATE_NOT_SYNC && actionConfig != null
						&& actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_SENT
						&& OpenCPSConfigUtil.isPublishEventEnable()) {
					publishEvent(dossier, context, dossierAction.getDossierActionId());
				}
			} else if (actionConfig.getSyncType() == DossierSyncTerm.SYNCTYPE_INFORM_DOSSIER) {
				if (Validator.isNotNull(dossier.getDossierNo())) {
					payloadObject.put(DossierTerm.DOSSIER_NO, dossier.getDossierNo());
				}
				//
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

				payloadObject = DossierActionUtils.buildChangedPayload(payloadObject, flagChanged, dossier);
				if (Validator.isNotNull(dossier.getServerNo())
						&& dossier.getServerNo().split(StringPool.COMMA).length > 1) {
					String serverNo = null;
					if (syncType == 1) {
						serverNo = dossier.getServerNo().split(StringPool.COMMA)[0].split(StringPool.AT)[0];
					} else {
						serverNo = dossier.getServerNo().split(StringPool.COMMA)[1].split(StringPool.AT)[0];
					}
					//String serverNo = dossier.getServerNo().split(StringPool.COMMA)[0].split(StringPool.AT)[0];
					if (Validator.isNotNull(serverNo)) {
						dossierSyncLocalService.updateDossierSync(groupId, userId, dossier.getDossierId(),
								dossierRefUid, syncRefUid, dossierAction.getPrimaryKey(), actionCode,
								proAction.getActionName(), actionUser, actionNote, syncType, actionConfig.getInfoType(),
								payloadObject.toJSONString(), serverNo, state);
					}
				} else {
					dossierSyncLocalService.updateDossierSync(groupId, userId, dossier.getDossierId(), dossierRefUid,
							syncRefUid, dossierAction.getPrimaryKey(), actionCode, proAction.getActionName(),
							actionUser, actionNote, syncType, actionConfig.getInfoType(), payloadObject.toJSONString(),
							dossier.getServerNo(), state);
				}
			}
		} else if (actionConfig != null && (actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_SENT || actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_SENT_FILE)
				&& OpenCPSConfigUtil.isPublishEventEnable()) {
			publishEvent(dossier, context, dossierAction.getDossierActionId());
		}
	}

	private void doMappingAction(long groupId, long userId, Employee employee, Dossier dossier,
								 ActionConfig actionConfig, String actionUser, String actionNote, String payload, String assignUsers,
								 String payment, ServiceContext context) throws PortalException, Exception {
		ActionConfig mappingConfig = actionConfigLocalService.getByCode(groupId, actionConfig.getMappingAction());
		if (dossier.getOriginDossierId() != 0) {
			Dossier hslt = dossierLocalService.fetchDossier(dossier.getOriginDossierId());
			ProcessOption optionHslt = getProcessOption(hslt.getServiceCode(), hslt.getGovAgencyCode(),
					hslt.getDossierTemplateNo(), groupId);
			ProcessAction actionHslt = getProcessAction(groupId, hslt.getDossierId(), hslt.getReferenceUid(),
					actionConfig.getMappingAction(), optionHslt.getServiceProcessId());
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
				} catch (JSONException e) {
					_log.debug(e);
				}
			}
			doAction(groupId, userId, hslt, optionHslt, actionHslt, actionConfig.getMappingAction(), actionUserHslt,
					actionNote, payload, assignUsers, payment, mappingConfig.getSyncType(), context);
		} else {
			Dossier originDossier = dossierLocalService.getByOrigin(groupId, dossier.getDossierId());
			if (originDossier != null) {
				ProcessOption optionOrigin = getProcessOption(originDossier.getServiceCode(),
						originDossier.getGovAgencyCode(), originDossier.getDossierTemplateNo(), groupId);
				ProcessAction actionOrigin = getProcessAction(groupId, originDossier.getDossierId(),
						originDossier.getReferenceUid(), actionConfig.getMappingAction(),
						optionOrigin.getServiceProcessId());
				doAction(groupId, userId, originDossier, optionOrigin, actionOrigin, actionConfig.getMappingAction(),
						actionUser, actionNote, payload, assignUsers, payment, mappingConfig.getSyncType(), context);
			}
		}
	}

	private DossierAction createActionAndAssignUser(long groupId, long userId, ProcessStep curStep,
			ActionConfig actionConfig, DossierAction dossierAction, DossierAction previousAction,
			ProcessAction proAction, Dossier dossier, String actionCode, String actionUser, String actionNote,
			String payload, String assignUsers, String payment, ServiceProcess serviceProcess, ProcessOption option,
			Map<String, Boolean> flagChanged, Integer dateOption, ServiceContext context) throws PortalException {
		int actionOverdue = getActionDueDate(groupId, dossier.getDossierId(), dossier.getReferenceUid(),
				proAction.getProcessActionId());
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
			int eventStatus = (actionConfig != null
					? (actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_NOT_SENT
					? DossierActionTerm.EVENT_STATUS_NOT_CREATED
					: DossierActionTerm.EVENT_STATUS_WAIT_SENDING)
					: DossierActionTerm.EVENT_STATUS_NOT_CREATED);

			boolean rollbackable = false;
			if (actionConfig != null) {
				if (actionConfig.getRollbackable()) {
					rollbackable = true;
				} else {
				}
			} else {
				if (proAction.isRollbackable()) {
					rollbackable = true;
				} else {
				}
			}
			dossierAction = dossierActionLocalService.updateDossierAction(groupId, 0, dossier.getDossierId(),
					serviceProcess.getServiceProcessId(), dossier.getDossierActionId(), fromStepCode, fromStepName,
					fromSequenceNo, actionCode, actionUser, actionName, actionNote, actionOverdue, stepCode, stepName,
					sequenceNo, null, 0l, payload, stepInstruction, state, eventStatus, rollbackable, context);
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
					jsonDataStatusText != null ? jsonDataStatusText.getString(curStatus) : StringPool.BLANK,
					curSubStatus,
					jsonDataStatusText != null ? jsonDataStatusText.getString(curSubStatus) : StringPool.BLANK,
					curStep.getLockState(), dossierNote, context);

			//Cáº­p nháº­t cá»� Ä‘á»“ng bá»™ ngÃ y thÃ¡ng sang cÃ¡c há»‡ thá»‘ng khÃ¡c
			Map<String, Boolean> resultFlagChanged = updateProcessingDate(dossierAction, previousAction, curStep,
					dossier, curStatus, curSubStatus, prevStatus,
					dateOption != null ? dateOption : (actionConfig != null ? actionConfig.getDateOption() : 0), option,
					serviceProcess, payload, context);
			for (Map.Entry<String, Boolean> entry : resultFlagChanged.entrySet()) {
				flagChanged.put(entry.getKey(), entry.getValue());
			}
			dossierAction = dossierActionLocalService.fetchDossierAction(dossier.getDossierActionId());
		}

		//Thiết lập quyền thao tác hồ sơ

		int allowAssignUser = proAction.getAllowAssignUser();
		//_log.info("allowAssignUser: "+allowAssignUser);
		JSONArray assignedUsersArray = JSONFactoryUtil.createJSONArray(assignUsers);
		if (allowAssignUser != ProcessActionTerm.NOT_ASSIGNED) {
			if (Validator.isNotNull(assignUsers) && assignedUsersArray.length() > 0) {
				//				JSONArray assignedUsersArray = JSONFactoryUtil.createJSONArray(assignUsers);
				assignDossierActionUser(dossier, allowAssignUser, dossierAction, userId, groupId,
						proAction.getAssignUserId(), assignedUsersArray);
				if (OpenCPSConfigUtil.isNotificationEnable()) {
					createNotificationSMS(userId, groupId, dossier, assignedUsersArray, dossierAction, context);
				}
			} else {
				initDossierActionUser(proAction, dossier, allowAssignUser, dossierAction, userId, groupId,
						proAction.getAssignUserId());
			}
		} else {
			//Process role as step
			//_log.info("curStep.getRoleAsStep(): "+curStep.getRoleAsStep());
			if (curStep != null && Validator.isNotNull(curStep.getRoleAsStep())) {
				copyRoleAsStep(curStep, dossier);
			} else {
				initDossierActionUser(proAction, dossier, allowAssignUser, dossierAction, userId, groupId,
						proAction.getAssignUserId());
			}
		}

		return dossierAction;
	}

	//public static final String CACHE_ServiceProcess = "ServiceProcess";
	public static final String CREATE_DOCUMENT = "CREATE_DOCUMENT";
	public static final String CHANGE_DATE = "CHANGE_DATE";
	public static final String CALL_API = "CALL_API";
	public static final String MULTIPLE_CHECK = "MULTIPLE_CHECK";

	private DossierAction doActionInsideProcess(long groupId, long userId, Dossier dossier, ActionConfig actionConfig,
			ProcessOption option, ProcessAction proAction, String actionCode, String actionUser, String actionNote,
			String payload, String assignUsers, String payment, int syncType, ServiceContext context)
			throws PortalException, SystemException, Exception {

		System.out.println("payload Action: "+ payload);
		_log.info("payload Action: "+ payload);
		context.setUserId(userId);
		DossierAction dossierAction = null;
		Map<String, Boolean> flagChanged = new HashMap<>();
		JSONObject payloadObject = JSONFactoryUtil.createJSONObject();
		User user = userLocalService.fetchUser(userId);
		String dossierStatus = dossier.getDossierStatus().toLowerCase();
		if (Validator.isNull(dossier.getApplicantName()) && Validator.isNull(dossier.getApplicantIdNo())) {
			_log.info("TRACE_LOG_LOST_DOSSIER: "+JSONFactoryUtil.looseSerialize(dossier));
		}

		Employee employee = null;
		Serializable employeeCache = cache.getFromCache(CacheTerm.MASTER_DATA_EMPLOYEE,
				groupId + StringPool.UNDERLINE + userId);
		//		_log.info("EMPLOYEE CACHE: " + employeeCache);
		if (employeeCache == null) {
			employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
			if (employee != null) {
				cache.addToCache(CacheTerm.MASTER_DATA_EMPLOYEE, groupId + StringPool.UNDERLINE + userId,
						(Serializable) employee, ttl);
			}
		} else {
			employee = (Employee) employeeCache;
		}
		try {
			payloadObject = JSONFactoryUtil.createJSONObject(payload);
		} catch (JSONException e) {
			_log.debug(e);
		}
		if (Validator.isNotNull(dossierStatus)) {
			if (!ReadFilePropertiesUtils.get(ConstantUtils.STATUS_NEW).equals(dossierStatus)) {
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
			long serviceProcessId = (option != null ? option.getServiceProcessId()
					: previousAction.getServiceProcessId());
//			Serializable serviceProcessCache = cache.getFromCache(CACHE_ServiceProcess,
//					groupId + StringPool.UNDERLINE + serviceProcessId);
//			if (serviceProcessCache == null) {
			serviceProcess = serviceProcessLocalService.fetchServiceProcess(serviceProcessId);
//				if (serviceProcess != null) {
//					cache.addToCache(CACHE_ServiceProcess, groupId + StringPool.UNDERLINE + serviceProcessId,
//							(Serializable) serviceProcess, ttl);
//				}
//			} else {
//				serviceProcess = (ServiceProcess) serviceProcessCache;
//			}
			String paymentFee = StringPool.BLANK;

			String postStepCode = proAction.getPostStepCode();

			String postAction = proAction.getPostAction();
			boolean flagDocument = false;
			Integer dateOption = null;
			String documentTypeList = StringPool.BLANK;
			if (Validator.isNotNull(postAction)) {
				JSONObject jsonPostData = JSONFactoryUtil.createJSONObject(postAction);
				if (jsonPostData != null) {
					JSONObject jsonDocument = JSONFactoryUtil.createJSONObject(jsonPostData.getString(CREATE_DOCUMENT));
					if (jsonDocument != null && jsonDocument.has(DossierDocumentTerm.DOCUMENT_TYPE)) {
						documentTypeList = jsonDocument.getString(DossierDocumentTerm.DOCUMENT_TYPE);
						flagDocument = true;
					}
					JSONObject jsonChangeDate = JSONFactoryUtil.createJSONObject(jsonPostData.getString(CHANGE_DATE));
					if (jsonChangeDate != null && jsonChangeDate.has(DossierTerm.DATE_OPTION)) {
						String strDateOption = jsonChangeDate.getString(DossierTerm.DATE_OPTION);
						if (Validator.isNotNull(strDateOption)) {
							dateOption = Integer.valueOf(strDateOption);
						}
					}
//					JSONObject jsonCallAPI = JSONFactoryUtil.createJSONObject(jsonPostData.getString(CALL_API));
//					if (jsonCallAPI != null && jsonCallAPI.has(DossierTerm.SERVER_NO)) {
//						String serverNo = jsonCallAPI.getString(DossierTerm.SERVER_NO);
//						if (Validator.isNotNull(serverNo)) {
//							ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByCode(groupId, serverNo);
//							if (serverConfig != null) {
//								JSONObject configObj = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
//								//
//								String method = StringPool.BLANK;
//								if (configObj != null && configObj.has(KeyPayTerm.METHOD)) {
//									method = configObj.getString(KeyPayTerm.METHOD);
//									System.out.println("method: " + method);
//								}
//								//params
//								JSONObject jsonParams = null;
//								if (configObj != null && configObj.has(KeyPayTerm.PARAMS)) {
//									jsonParams = JSONFactoryUtil
//											.createJSONObject(configObj.getString(KeyPayTerm.PARAMS));
//								}
//								if (jsonParams != null) {
//									JSONObject jsonHeader = JSONFactoryUtil
//											.createJSONObject(jsonParams.getString(KeyPayTerm.HEADER));
//									JSONObject jsonBody = JSONFactoryUtil
//											.createJSONObject(jsonParams.getString(KeyPayTerm.BODY));
//
//									String authStrEnc = StringPool.BLANK;
//									String apiUrl = StringPool.BLANK;
//									StringBuilder sb = new StringBuilder();
//									try {
//										URL urlVal = null;
//										String groupIdRequest = StringPool.BLANK;
//										StringBuilder postData = new StringBuilder();
//										Iterator<?> keys = jsonBody.keys();
//										while (keys.hasNext()) {
//											String key = (String) keys.next();
//											if (!StringPool.BLANK.equals(postData.toString())) {
//												postData.append(StringPool.AMPERSAND);
//											}
//											postData.append(key);
//											postData.append(StringPool.EQUAL);
//											postData.append(jsonBody.get(key));
//										}
//
//										if (configObj.has(SyncServerTerm.SERVER_USERNAME)
//												&& configObj.has(SyncServerTerm.SERVER_SECRET)
//												&& Validator
//														.isNotNull(configObj.getString(SyncServerTerm.SERVER_USERNAME))
//												&& Validator
//														.isNotNull(configObj.getString(SyncServerTerm.SERVER_SECRET))) {
//											authStrEnc = Base64.getEncoder()
//													.encodeToString((configObj.getString(SyncServerTerm.SERVER_USERNAME)
//															+ StringPool.COLON
//															+ configObj.getString(SyncServerTerm.SERVER_SECRET))
//																	.getBytes());
//										}
//										if (configObj.has(SyncServerTerm.SERVER_URL)) {
//											apiUrl = configObj.getString(SyncServerTerm.SERVER_URL);
//											if (apiUrl.contains("{_dossierId}")) {
//												apiUrl = apiUrl.replace("{_dossierId}", String.valueOf(dossierId));
//											}
//											if (apiUrl.contains("{_dossierCounter}")) {
//												apiUrl = apiUrl.replace("{_dossierCounter}",
//														String.valueOf(dossier.getDossierCounter()));
//											}
//										}
//										if (configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
//											groupIdRequest = configObj.getString(SyncServerTerm.SERVER_GROUP_ID);
//										}
//										if (jsonHeader != null && Validator.isNotNull(groupIdRequest)) {
//											if (jsonHeader.has(Field.GROUP_ID)) {
//												groupIdRequest = String.valueOf(jsonHeader.getLong(Field.GROUP_ID));
//											}
//										}
//
//										if (HttpMethods.GET.equals(method)) {
//											if (Validator.isNotNull(postData.toString())) {
//												urlVal = new URL(apiUrl + StringPool.QUESTION + postData.toString());
//											} else {
//												urlVal = new URL(apiUrl);
//											}
//										} else {
//											urlVal = new URL(apiUrl);
//										}
//										_log.debug("API URL: " + apiUrl);
//										java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal
//												.openConnection();
//										conn.setRequestProperty(Field.GROUP_ID, groupIdRequest);
//										conn.setRequestMethod(method);
//										conn.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
//										if (Validator.isNotNull(authStrEnc)) {
//											conn.setRequestProperty(HttpHeaders.AUTHORIZATION, "Basic " + authStrEnc);
//										}
//										if (HttpMethods.POST.equals(method) || HttpMethods.PUT.equals(method)) {
//											conn.setRequestProperty(HttpHeaders.CONTENT_TYPE,
//													MediaType.APPLICATION_FORM_URLENCODED);
//											conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, StringPool.BLANK
//													+ Integer.toString(postData.toString().getBytes().length));
//
//											conn.setUseCaches(false);
//											conn.setDoInput(true);
//											conn.setDoOutput(true);
//											_log.debug("POST DATA: " + postData.toString());
//											OutputStream os = conn.getOutputStream();
//											os.write(postData.toString().getBytes());
//											os.close();
//										}
//
//										BufferedReader brf = new BufferedReader(
//												new InputStreamReader(conn.getInputStream()));
//
//										int cp;
//										while ((cp = brf.read()) != -1) {
//											sb.append((char) cp);
//										}
//										_log.debug("RESULT PROXY: " + sb.toString());
//									} catch (IOException e) {
//										_log.debug(e);
//										//_log.debug("Something went wrong while reading/writing in stream!!");
//									}
//								}
//							}
//						}
//					}
				}
			}
			//A Duẩn cấu hình action 8888
			// Không có bước trước bước sau thì cập nhật PaymentFile
			// <==> thì doAction bình thường chuyển bước về trả kết quả
			// Nếu có cấu hình 8888 thì ưu tiên doAction chuyển bước trả về kết quả
			if(actionCode.equals(DossierActionTerm.OUTSIDE_ACTION_PAYMENT)){
				_log.info("actionCode " + actionCode);
				dossierAction = dossierActionLocalService.fetchDossierAction(dossier.getDossierActionId());
				PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByG_DID(groupId, dossier.getDossierId());
				if (paymentFile != null) {
					paymentFile.setPaymentStatus(5);
					paymentFile.setApproveDatetime(new Date());
				}
				PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);
				return dossierAction;
			}

			//Bước sau không có thì mặc định quay lại bước trước đó
			if (Validator.isNull(proAction.getPreStepCode()) && Validator.isNull(postStepCode)) {
				PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByG_DID(groupId, dossier.getDossierId());
				if (paymentFile != null) {
					paymentFile.setPaymentStatus(5);
					paymentFile.setApproveDatetime(new Date());
				}
				PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);
				//
				return dossierAction;
			}
			else if (Validator.isNull(postStepCode)) {
				//Xử lý phiếu thanh toán
				processPaymentFile(groupId, userId, payment, option, proAction, previousAction, dossier, context);

				postStepCode = previousAction.getFromStepCode();
				_log.info("postStepCode: "+postStepCode);
				ProcessStep backCurStep = processStepLocalService.fetchBySC_GID(postStepCode, groupId,
						serviceProcessId);
				String curStatus = backCurStep.getDossierStatus();
				String curSubStatus = backCurStep.getDossierSubStatus();

				JSONObject jsonDataStatusText = getStatusText(groupId, DOSSIER_SATUS_DC_CODE, curStatus, curSubStatus);

				//update dossierStatus
				dossier = DossierLocalServiceUtil.updateStatus(groupId, dossierId, dossier.getReferenceUid(), curStatus,
						jsonDataStatusText != null ? jsonDataStatusText.getString(curStatus) : StringPool.BLANK,
						curSubStatus,
						jsonDataStatusText != null ? jsonDataStatusText.getString(curSubStatus) : StringPool.BLANK,
						backCurStep.getLockState(), dossier.getDossierNote(), context);

				dossierAction = dossierActionLocalService.fetchDossierAction(dossier.getDossierActionId());

				int actionOverdue = getActionDueDate(groupId, dossier.getDossierId(), dossier.getReferenceUid(),
						proAction.getProcessActionId());

				/*ProcessStep curStep = processStepLocalService.fetchBySC_GID(dossierAction.getStepCode(), groupId,
						serviceProcessId);*/

				int state = DossierActionTerm.STATE_WAITING_PROCESSING;
				int eventStatus = (actionConfig != null
						? (actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_NOT_SENT
						? DossierActionTerm.EVENT_STATUS_NOT_CREATED
						: DossierActionTerm.EVENT_STATUS_WAIT_SENDING)
						: DossierActionTerm.EVENT_STATUS_NOT_CREATED);

				boolean rollbackable = false;
				if (actionConfig != null) {
					if (actionConfig.getRollbackable()) {
						rollbackable = true;
					} else {
					}
				} else {
					if (proAction.isRollbackable()) {
						rollbackable = true;
					} else {
					}
				}

				_log.debug("groupId=" + groupId + "|dossierId=" + dossier.getDossierId() + "|serviceProcessId="
						+ serviceProcess.getServiceProcessId() + "|dossierActionId=" + dossier.getDossierActionId()
						+ "|fromStepCode=" + previousAction.getStepCode() + "|fromStepName="
						+ previousAction.getStepName() + "|fromSequenceNo= " + dossierAction.getSequenceNo()
						+ "|actionCode=" + actionCode + "|actionUser=" + actionUser + "|actionName="
						+ proAction.getActionName() + "|actionNote=" + actionNote + "|actionOverdue=" + actionOverdue
						+ "|stepCode=" + postStepCode + "|stepName=" + backCurStep.getStepName() + "|sequenceNo="
						+ backCurStep.getSequenceNo() + "|nextactionId=" + "|stepInstruction="
						+ previousAction.getStepInstruction() + "|previousAction=" + previousAction.getDossierActionId()
						+ "|dossier.getDossierActionId=" + dossier.getDossierActionId()
						+ "|proAction.getAllowAssignUser()=" + proAction.getAllowAssignUser());

				DossierAction newAction = dossierActionLocalService.updateDossierAction(groupId, 0,
						dossier.getDossierId(), serviceProcess.getServiceProcessId(), dossier.getDossierActionId(),
						previousAction.getStepCode(), previousAction.getStepName(), dossierAction.getSequenceNo(),
						actionCode, actionUser, proAction.getActionName(), actionNote, actionOverdue, postStepCode,
						backCurStep.getStepName(), backCurStep.getSequenceNo(), null, 0l, payload,
						previousAction.getStepInstruction(), state, eventStatus, rollbackable, context);

				dossier.setDossierActionId(newAction.getDossierActionId());
				dossierLocalService.updateDossier(dossier);
				//_log.info("TRACE_LOG_INFO doAction CPS Update L1 dossier: "+JSONFactoryUtil.looseSerialize(dossier));
				//update
				dossierAction.setNextActionId(newAction.getDossierActionId());
				dossierActionLocalService.updateDossierAction(dossierAction);

				// chỉ cán bộ thao tác trước đó có moderator = 1
				_log.info("previousAction.getStepCode(): "+previousAction.getStepCode());
				_log.info("previousAction.getFromStepCode(): "+previousAction.getFromStepCode());
				List<DossierActionUser> lstDaus = DossierActionUserLocalServiceUtil
						.getByDossierAndStepCode(dossier.getDossierId(), previousAction.getFromStepCode());
				_log.info("lstDaus: "+JSONFactoryUtil.looseSerialize(lstDaus));
				if (lstDaus != null && lstDaus.size() > 0) {
					for (DossierActionUser dau : lstDaus) {
//						dau.setDossierActionId(newAction.getDossierActionId());
//						DossierActionUserLocalServiceUtil.updateDossierActionUser(dau);
						DossierActionUserLocalServiceUtil.addOrUpdateDossierActionUser(dau.getUserId(), groupId, newAction.getDossierActionId(),
								dau.getDossierId(), dau.getStepCode(), dau.getModerator(), dau.getAssigned(), dau.getVisited(), dau.getDelegacy());
						// Remove dau
						DossierActionUserLocalServiceUtil.deleteDossierActionUser(dau);
					}
				}
//				if (lstDaus != null && lstDaus.size() > 0) {
//					for (DossierActionUser dau : lstDaus) {
//						if (dau.getUserId() == backCurStep.getUserId()) {
//							dau.setModerator(1);
//						} else {
//							dau.setModerator(0);
//						}
//						DossierActionUserLocalServiceUtil.updateDossierActionUser(dau);
//					}
//				}

//				ProcessAction processAction = processActionLocalService.fetchBySPID_AC(serviceProcessId,
//						newAction.getActionCode());

//				_log.debug("|processAction.getPostStepCode()=" + processAction.getPostStepCode()
//						+ "|processAction.getActionCode()=" + processAction.getActionCode()
//						+ "|processAction.getProcessActionId()=" + processAction.getProcessActionId() + "|"
//						+ processAction.getAllowAssignUser() + "|" + processAction.getAssignUserId());

//				int allowAssignUser = processAction.getAllowAssignUser();

				//thiet lap phan quyen cho user
//				initDossierActionUser(newAction.getStepCode(), newAction.getServiceProcessId(), dossier, processAction,
//						allowAssignUser, newAction, userId, groupId, allowAssignUser);

				if (syncType == 2) {
					//Tạo thông tin đồng bộ hồ sơ
					createDossierSync(groupId, userId, actionConfig, proAction, newAction, dossier, syncType, option,
							payloadObject, flagChanged, actionCode, actionUser, actionNote, serviceProcess, context);
				}

//				if (Validator.isNotNull(postAction)) {
//					processPostAction(postAction, groupId, dossier);
//				}

				return newAction;
			}

			ProcessStep curStep = processStepLocalService.fetchBySC_GID(postStepCode, groupId, serviceProcessId);
			_log.info("ProcessStep:" + JSONFactoryUtil.looseSerialize(curStep));
			//Kiểm tra cấu hình cần tạo hồ sơ liên thông
			Dossier hsltDossier = createCrossDossier(groupId, proAction, curStep, previousAction, employee, dossier,
					user, payloadObject, context);
			if ((payloadObject != null && payloadObject.has("createDossiers")
					&& Validator.isNotNull(payloadObject.get("createDossiers")))
					|| Validator.isNotNull(proAction.getCreateDossiers())) {
				if (Validator.isNull(hsltDossier)) {
					return null;
				}
			}

			//Cập nhật hành động và quyền người dùng với hồ sơ
			dossierAction = createActionAndAssignUser(groupId, userId, curStep, actionConfig, dossierAction,
					previousAction, proAction, dossier, actionCode, actionUser, actionNote, payload, assignUsers,
					paymentFee, serviceProcess, option, flagChanged, dateOption, context);

			_log.info("dossier generate_DossierNo: "+JSONFactoryUtil.looseSerialize(dossier));

			//Xử lý phiếu thanh toán
			processPaymentFile(groupId, userId, payment, option, proAction, previousAction, dossier, context);
			//Tạo văn bản đính kèm
			if (OpenCPSConfigUtil.isDossierDocumentEnable()) {
				if (!flagDocument) {
					createDossierDocument(groupId, userId, actionConfig, dossier, dossierAction, payloadObject,
							employee, user, context);
				} else {
					createDossierDocumentPostAction(groupId, userId, dossier, dossierAction, payloadObject, employee,
							user, documentTypeList, context);
				}
			}

			//Kiểm tra xem có gửi dịch vụ vận chuyển hay không
			if (proAction.getPreCondition().toLowerCase().contains(ProcessActionTerm.PRECONDITION_SEND_VIAPOSTAL)) {
				boolean bdhnConnect = false;
				String senderAddress = StringPool.BLANK;
				// Kiểm tra có cấu hình VNPOST_CLS không
				ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, ServerConfigTerm.VNPOST_CLS);
				if(Validator.isNotNull(sc)) {
					JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
					bdhnConnect = configObj.getBoolean(ServerConfigTerm.BDHN_CONNECT);
					senderAddress = configObj.getString(ServerConfigTerm.BDHN_CONNECT);
				}
				if(bdhnConnect){
					if (dossier.getViaPostal() == 2) {
						_log.info(" Call API VNPOST HN");
						PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByG_DID(groupId, dossier.getDossierId());
						String resultHNPost = VNPostCLSUtils.insertCLS(groupId,ServerConfigTerm.VNPOST_CLS ,dossierId,
								dossier.getServiceCode(),dossier.getServiceName(),dossier.getDossierNo(),
								paymentFile.getPaymentAmount(),dossier.getApplicantName(), dossier.getDelegateName(),
								dossier.getApplicantIdNo(), dossier.getAddress(), dossier.getReceiveDate(), senderAddress);
						_log.info("Result HNPost : " + resultHNPost);
					}
				}else{
					vnpostEvent(dossier, dossierAction.getDossierActionId());
				}
			}
			if (proAction.getPreCondition().toLowerCase()
					.contains(ProcessActionTerm.PRECONDITION_SEND_COLLECTION_VNPOST)) {
				collectVnpostEvent(dossier, dossierAction.getDossierActionId());
			}
			if (proAction.getPreCondition().toLowerCase().contains(ProcessActionTerm.PRECONDITION_REC_COLLECTION_VNPOST)
					&& dossier.getVnpostalStatus() == VnpostCollectionTerm.VNPOSTAL_STAUS_2) {
				dossier.setVnpostalStatus(VnpostCollectionTerm.VNPOSTAL_STAUS_3);
			}
			//Check buu dien ha noi
			if (dossier.getViaPostal() == 2) {
				_log.info("START SEND VNPOST HN");
				ServerConfig config = ServerConfigLocalServiceUtil.getByCode(groupId, ServerConfigTerm.SERVER_VNPOST_HN);
				if (config != null){
					for (int i = 0; i < 3; i++) {

					}
				}
			}
		} else {

		}

		//Create notification
		if (OpenCPSConfigUtil.isNotificationEnable()) {
			JSONObject notificationPayload = buildNotificationPayload(dossier, payloadObject);
			createNotificationQueue(user, groupId, dossier, proAction, actionConfig, dossierAction, notificationPayload,
					context);
		}

		//Create subcription
		createSubcription(userId, groupId, dossier, actionConfig, dossierAction, context);

		//Tạo thông tin đồng bộ hồ sơ
		createDossierSync(groupId, userId, actionConfig, proAction, dossierAction, dossier, syncType, option,
				payloadObject, flagChanged, actionCode, actionUser, actionNote, serviceProcess, context);

		JSONObject newObj = JSONFactoryUtil.createJSONObject(payload);
		if (payloadObject.has(DossierTerm.CROSS_DOSSIER)) {
			newObj.put(DossierTerm.CROSS_DOSSIER, payloadObject.getJSONObject(DossierTerm.CROSS_DOSSIER));
		}

		//Add by TrungNT - Fix tam theo y/k cua a TrungDK va Duantv
		if (dossier.isOnline() && proAction != null && "listener".equals(proAction.getAutoEvent().toString())
				&& OpenCPSConfigUtil.isPublishEventEnable()) {
			publishEvent(dossier, context, dossierAction.getDossierActionId());
		}

		// check multiple check in postAction
		if (proAction != null && Validator.isNotNull(proAction.getPostAction())) {
			JSONObject jsonPostData = JSONFactoryUtil.createJSONObject(proAction.getPostAction());
			if (jsonPostData != null && jsonPostData.has(MULTIPLE_CHECK)) {
				String multipleCheck = jsonPostData.getString(MULTIPLE_CHECK);
				if (Validator.isNotNull(multipleCheck)) {
					dossier.setMultipleCheck(multipleCheck);
				}
			}
			//Call API postAction
//			String strPostAction = processPostAction(proAction.getPostAction(), groupId, dossier);
//			_log.info("strPostAction: "+strPostAction);
		}

		//Thực hiện thao tác lên hồ sơ gốc hoặc hồ sơ liên thông trong trường hợp có cấu hình mappingAction
		if (Validator.isNotNull(actionConfig) && Validator.isNotNull(actionConfig.getMappingAction())) {
			doMappingAction(groupId, userId, employee, dossier, actionConfig, actionUser, actionNote, newObj.toJSONString(),
					assignUsers, payment, context);
		}

		//Update dossier
		dossierLocalService.updateDossier(dossier);

		//		Indexer<Dossier> indexer = IndexerRegistryUtil
		//				.nullSafeGetIndexer(Dossier.class);
		//		indexer.reindex(dossier);

		return dossierAction;
	}

	public void initDossierActionUser(String stepCode, long serviceProcessId, Dossier dossier,
									  ProcessAction processAction, int allowAssignUser, DossierAction dossierAction, long userId, long groupId,
									  long assignUserId) throws PortalException {
		// Delete record in dossierActionUser
		List<org.opencps.dossiermgt.model.DossierActionUser> dossierActionUser = dossierActionUserLocalService
				.getListUser(dossierAction.getDossierActionId());
		if (dossierActionUser != null && dossierActionUser.size() > 0) {
			dossierActionUserLocalService.deleteByDossierAction(dossierAction.getDossierActionId());
		}

		// Get ProcessStep
		ProcessStep processStep = processStepLocalService.fetchBySC_GID(stepCode, groupId, serviceProcessId);

		long processStepId = processStep.getProcessStepId();
		int assigned;

		// Get List ProcessStepRole
		List<ProcessStepRole> listProcessStepRole = processStepRoleLocalService.findByP_S_ID(processStepId);
		ProcessStepRole processStepRole = null;
		List<DossierAction> lstStepActions = dossierActionLocalService.getByDID_FSC_NOT_DAI(dossier.getDossierId(),
				stepCode, dossierAction.getDossierActionId());
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
					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(dossier.getGroupId(),
							user.getUserId());
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
						} else {
							assigned = DossierActionUserTerm.NOT_ASSIGNED;
						}

						updateDossierUser(dossier, processStepRole, user);
						List<DossierActionUser> lstDau = dossierActionUserLocalService
								.getByDossierUserAndStepCode(dossier.getDossierId(), user.getUserId(), stepCode);
						DossierActionUser lastDau = (lstDau.size() > 0 ? lstDau.get(0) : null);
						for (DossierActionUser dau : lstDau) {
							if (dau.getDossierActionId() > lastDau.getDossierActionId()) {
								lastDau = dau;
							}
						}

						if (lastDau != null) {
							addDossierActionUserByAssigned(groupId, processAction.getAllowAssignUser(),
									user.getUserId(), dossierAction.getDossierActionId(), lastDau.getModerator(), false,
									stepCode, dossier.getDossierId(), lastDau.getAssigned(), lastDau.getDelegacy());
						} else {
							addDossierActionUserByAssigned(groupId, processAction.getAllowAssignUser(),
									user.getUserId(), dossierAction.getDossierActionId(), mod, false, stepCode,
									dossier.getDossierId(), assigned, 0);
						}
					}
				}
			}
		} else {
			//Get role from service process
			initDossierActionUserByServiceProcessRole(dossier, allowAssignUser, dossierAction, userId, groupId,
					assignUserId);
		}
	}

	//Post Action
	private String processPostAction(String postAction, long groupId, Dossier dossier) throws JSONException {
		if (Validator.isNotNull(postAction)) {
			JSONObject jsonPostData = JSONFactoryUtil.createJSONObject(postAction);
			if (jsonPostData != null) {
				JSONObject jsonCallAPI = JSONFactoryUtil.createJSONObject(jsonPostData.getString(CALL_API));
				if (jsonCallAPI != null && jsonCallAPI.has(DossierTerm.SERVER_NO)) {
					String serverNo = jsonCallAPI.getString(DossierTerm.SERVER_NO);
					if (Validator.isNotNull(serverNo)) {
						ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByCode(groupId, serverNo);
						if (serverConfig != null) {
							JSONObject configObj = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
							//
							String method = StringPool.BLANK;
							if (configObj != null && configObj.has(KeyPayTerm.METHOD)) {
								method = configObj.getString(KeyPayTerm.METHOD);
								System.out.println("method: " + method);
							}
							//params
							JSONObject jsonParams = null;
							if (configObj != null && configObj.has(KeyPayTerm.PARAMS)) {
								jsonParams = JSONFactoryUtil
										.createJSONObject(configObj.getString(KeyPayTerm.PARAMS));
							}
							if (jsonParams != null) {
								JSONObject jsonHeader = JSONFactoryUtil
										.createJSONObject(jsonParams.getString(KeyPayTerm.HEADER));
								JSONObject jsonBody = JSONFactoryUtil
										.createJSONObject(jsonParams.getString(KeyPayTerm.BODY));

								String authStrEnc = StringPool.BLANK;
								String apiUrl = StringPool.BLANK;
								StringBuilder sb = new StringBuilder();
								try {
									URL urlVal = null;
									String groupIdRequest = StringPool.BLANK;
									StringBuilder postData = new StringBuilder();
									Iterator<?> keys = jsonBody.keys();
									while (keys.hasNext()) {
										String key = (String) keys.next();
										if (!StringPool.BLANK.equals(postData.toString())) {
											postData.append(StringPool.AMPERSAND);
										}
										postData.append(key);
										postData.append(StringPool.EQUAL);
										postData.append(jsonBody.get(key));
									}

									if (configObj.has(SyncServerTerm.SERVER_USERNAME)
											&& configObj.has(SyncServerTerm.SERVER_SECRET)
											&& Validator
											.isNotNull(configObj.getString(SyncServerTerm.SERVER_USERNAME))
											&& Validator
											.isNotNull(configObj.getString(SyncServerTerm.SERVER_SECRET))) {
										authStrEnc = Base64.getEncoder()
												.encodeToString((configObj.getString(SyncServerTerm.SERVER_USERNAME)
														+ StringPool.COLON
														+ configObj.getString(SyncServerTerm.SERVER_SECRET))
														.getBytes());
									}
									if (configObj.has(SyncServerTerm.SERVER_URL)) {
										apiUrl = configObj.getString(SyncServerTerm.SERVER_URL);
										if (apiUrl.contains("{_dossierId}")) {
											apiUrl = apiUrl.replace("{_dossierId}", String.valueOf(dossier.getDossierId()));
										}
										if (apiUrl.contains("{_dossierCounter}")) {
											apiUrl = apiUrl.replace("{_dossierCounter}",
													String.valueOf(dossier.getDossierCounter()));
										}
										if (apiUrl.contains("{_dossierNo}")) {
											apiUrl = apiUrl.replace("{_dossierNo}",
													String.valueOf(dossier.getDossierNo()));
										}
									}
									if (configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
										groupIdRequest = configObj.getString(SyncServerTerm.SERVER_GROUP_ID);
									}
									if (jsonHeader != null && Validator.isNotNull(groupIdRequest)) {
										if (jsonHeader.has(Field.GROUP_ID)) {
											groupIdRequest = String.valueOf(jsonHeader.getLong(Field.GROUP_ID));
										}
									}

									if (HttpMethods.GET.equals(method)) {
										if (Validator.isNotNull(postData.toString())) {
											urlVal = new URL(apiUrl + StringPool.QUESTION + postData.toString());
										} else {
											urlVal = new URL(apiUrl);
										}
									} else {
										urlVal = new URL(apiUrl);
									}
									_log.debug("API URL: " + apiUrl);
									java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal
											.openConnection();
									conn.setRequestProperty(Field.GROUP_ID, groupIdRequest);
									conn.setRequestMethod(method);
									conn.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
									if (Validator.isNotNull(authStrEnc)) {
										conn.setRequestProperty(HttpHeaders.AUTHORIZATION, "Basic " + authStrEnc);
									}
									if (HttpMethods.POST.equals(method) || HttpMethods.PUT.equals(method)) {
										conn.setRequestProperty(HttpHeaders.CONTENT_TYPE,
												MediaType.APPLICATION_FORM_URLENCODED);
										conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, StringPool.BLANK
												+ Integer.toString(postData.toString().getBytes().length));

										conn.setUseCaches(false);
										conn.setDoInput(true);
										conn.setDoOutput(true);
										_log.debug("POST DATA: " + postData.toString());
										OutputStream os = conn.getOutputStream();
										os.write(postData.toString().getBytes());
										os.close();
									}

									BufferedReader brf = new BufferedReader(
											new InputStreamReader(conn.getInputStream()));

									int cp;
									while ((cp = brf.read()) != -1) {
										sb.append((char) cp);
									}
									_log.debug("RESULT PROXY: " + sb.toString());
									return sb.toString();
								} catch (IOException e) {
									_log.debug(e);
									//_log.debug("Something went wrong while reading/writing in stream!!");
								}
							}
						}
					}
				}
			}
		}
		return StringPool.BLANK;
	}

	private JSONObject buildNotificationPayload(Dossier dossier, JSONObject payloadObject) {
		JSONObject returnObject;
		try {
			returnObject = JSONFactoryUtil.createJSONObject(payloadObject.toJSONString());
			_log.debug("=======> BUILD NOTIFICATION QUEUE SMS PAYLOAD");
			if (dossier.getReceiveDate() != null) {
				returnObject.put(DossierTerm.RECEIVE_DATE, APIDateTimeUtils
						.convertDateToString(dossier.getReceiveDate(), APIDateTimeUtils._NORMAL_DATE_TIME));
				_log.debug("=======> BUILD NOTIFICATION QUEUE SMS RECEIVE DATE: "
						+ returnObject.get(DossierTerm.RECEIVE_DATE));
			} else {
				returnObject.put(DossierTerm.RECEIVE_DATE, StringPool.BLANK);
			}
			if (!returnObject.has(DossierTerm.DOSSIER_NO)) {
				if (Validator.isNotNull(dossier.getDossierNo())) {
					returnObject.put(DossierTerm.DOSSIER_NO, dossier.getDossierNo());
				}
			}
			int durationUnit = dossier.getDurationUnit();
			double durationCount = dossier.getDurationCount();
			String durationText = StringPool.BLANK;
			if (durationUnit == DossierTerm.DURATION_UNIT_DAY) {
				durationText = String.valueOf(durationCount);
			} else if (durationUnit == DossierTerm.DURATION_UNIT_HOUR) {
				durationText = String.valueOf(durationCount * 1.0 / DossierTerm.WORKING_HOUR_PER_DAY);
			}

			returnObject.put(DossierTerm.DURATION_TEXT, durationText);
			if (!returnObject.has(DossierTerm.GOV_AGENCY_NAME)) {
				returnObject.put(DossierTerm.GOV_AGENCY_NAME, dossier.getGovAgencyName());
			}
			if (!returnObject.has(DossierTerm.POSTAL_ADDRESS)) {
				returnObject.put(DossierTerm.POSTAL_ADDRESS, dossier.getPostalAddress());
			}

			PaymentFile pf = paymentFileLocalService.getByDossierId(dossier.getGroupId(), dossier.getDossierId());
			if (pf != null) {
				if (!returnObject.has(PaymentFileTerm.PAYMENT_AMOUNT)) {
					returnObject.put(PaymentFileTerm.PAYMENT_AMOUNT, String.valueOf(pf.getPaymentAmount()));
				}
			}
			return returnObject;
		} catch (JSONException e) {
			_log.debug(e);
			return payloadObject;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
	public DossierAction doAction(long groupId, long userId, Dossier dossier, ProcessOption option,
								  ProcessAction proAction, String actionCode, String actionUser, String actionNote, String payload,
								  String assignUsers, String payment, int syncType, ServiceContext context)
			throws PortalException, SystemException, Exception {
		context.setUserId(userId);
		DossierAction dossierAction = null;

		ActionConfig actionConfig = null;
		actionConfig = actionConfigLocalService.getByCode(groupId, actionCode);

		if (actionConfig != null && !actionConfig.getInsideProcess()) {
			_log.info("OutSide : " + !actionConfig.getInsideProcess());
			dossierAction = doActionOutsideProcess(groupId, userId, dossier, actionConfig, option, proAction,
					actionCode, actionUser, actionNote, payload, assignUsers, payment, syncType, context);
		} else {

			dossierAction = doActionInsideProcess(groupId, userId, dossier, actionConfig, option, proAction, actionCode,
					actionUser, actionNote, payload, assignUsers, payment, syncType, context);
		}
		//Integrate TTTT
		if (dossierAction != null) {
			integrateTTTT(dossier, context, dossierAction.getDossierActionId());
		}

		return dossierAction;
	}

	private void createNotificationQueue(User user, long groupId, Dossier dossier, ProcessAction proAction,
										 ActionConfig actionConfig, DossierAction dossierAction, JSONObject payloadObject, ServiceContext context)
			throws PortalException {
		//		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		//		User u = UserLocalServiceUtil.fetchUser(userId);
		JSONObject payloadObj = JSONFactoryUtil.createJSONObject(payloadObject.toJSONString());

		try {
			JSONObject dossierObj = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(dossier));
			String serviceNameUnsigned = StringPool.BLANK;
			if (Validator.isNotNull(dossier.getServiceName())) {
				serviceNameUnsigned = AccentUtils.removeAccent(dossier.getServiceName());
			}
			dossierObj.put("serviceNameUnsigned", serviceNameUnsigned);
			String govAgencyNameUnsigned = StringPool.BLANK;
			if (Validator.isNotNull(dossier.getGovAgencyName())) {
				govAgencyNameUnsigned = AccentUtils.removeAccent(dossier.getGovAgencyName());
			}
			dossierObj.put("govAgencyNameUnsigned", govAgencyNameUnsigned);
			//dueDate
			/*String strDueDate = null;
			if (dossier.getDueDate() != null) {
				strDueDate = APIDateTimeUtils.convertDateToString(dossier.getDueDate(),
						APIDateTimeUtils._NORMAL_PARTTERN);
			}*/
			String strDueDate = processDueDate(groupId, dossier.getReceiveDate(),  dossier.getDueDate(), dossier.getProcessNo());
			dossierObj.put("dueDateNotify", strDueDate != null ? strDueDate : StringPool.BLANK);
			//Payment
			PaymentFile payment = PaymentFileLocalServiceUtil.getByG_DID(groupId, dossier.getDossierId());
			long paymentAmount = 0;
			if (payment != null) {
				paymentAmount = payment.getPaymentAmount();
			}
			dossierObj.put("paymentAmount", paymentAmount);
			//
			dossierObj = buildNotificationPayload(dossier, dossierObj);

			payloadObj.put(KeyPayTerm.DOSSIER, dossierObj);


			if (dossierAction != null) {
				payloadObj.put(DossierActionTerm.ACTION_CODE, dossierAction.getActionCode());
				payloadObj.put(DossierActionTerm.ACTION_USER, dossierAction.getActionUser());
				payloadObj.put(DossierActionTerm.ACTION_NAME, dossierAction.getActionName());
				payloadObj.put(DossierActionTerm.ACTION_NOTE, dossierAction.getActionNote());
			}
			//
			if (payloadObject != null) {
				Iterator<String> keys = payloadObject.keys();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					if (PAYLOAD_KEY_COMPLEMENT_DATE.equalsIgnoreCase(key)) {
						Long complementDate = payloadObject.getLong(PAYLOAD_KEY_COMPLEMENT_DATE);
						if (complementDate != null && complementDate > 0) {
							String strDate = APIDateTimeUtils.convertDateToString(new Date(complementDate),
									APIDateTimeUtils._NORMAL_PARTTERN);
							payloadObj.put(key, strDate);
						}
					} else {
						payloadObj.put(key, payloadObject.getString(key));
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}

		String notificationType = StringPool.BLANK;
		String preCondition = StringPool.BLANK;
		if (actionConfig != null && Validator.isNotNull(actionConfig.getNotificationType())) {
			_log.info("NOTIFICATION TYPE: " + actionConfig.getNotificationType());
			if (actionConfig.getNotificationType().contains(StringPool.AT)) {
				String[] split = StringUtil.split(actionConfig.getNotificationType(), StringPool.AT);
				if (split.length == 2) {
					notificationType = split[0];
					preCondition = split[1];
				}
			} else {
				notificationType = actionConfig.getNotificationType();
			}
			_log.info("NOTIFICATION TYPE: " + notificationType + ", CONDITION: " + preCondition);

			//			Notificationtemplate notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, actionConfig.getNotificationType());
			Serializable notiCache = cache.getFromCache(CACHE_NOTIFICATION_TEMPLATE,
					groupId + StringPool.UNDERLINE + notificationType);
			Notificationtemplate notiTemplate = null;
			//			notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, actionConfig.getNotificationType());
			if (notiCache == null) {
				notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId,
						notificationType);
				if (notiTemplate != null) {
					cache.addToCache(CACHE_NOTIFICATION_TEMPLATE, groupId + StringPool.UNDERLINE + notificationType,
							(Serializable) notiTemplate, ttl);
				}
			} else {
				notiTemplate = (Notificationtemplate) notiCache;
			}

			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);

			boolean isSendSMS = NotificationUtil.isSendSMS(preCondition);
			boolean isSendEmail = NotificationUtil.isSendEmail(preCondition);
			boolean isSendNotiSMS = true;
			boolean isSendNotiEmail = true;
			if (notiTemplate != null) {
				isSendNotiSMS = notiTemplate.getSendSMS();
				isSendNotiEmail = notiTemplate.getSendEmail();
			}
			if (Validator.isNotNull(preCondition)) {
				if (!DossierMgtUtils.checkPreCondition(new String[] { preCondition }, dossier, null)) {
					if (isSendSMS) {
						isSendNotiSMS = false;
						isSendNotiEmail = true;
					} else {
						isSendNotiSMS = false;
						isSendNotiEmail = false;
					}
				} else {
					isSendNotiSMS = isSendSMS;
					isSendNotiEmail = isSendEmail;
				}
				if(preCondition.contains(DossierTerm.CONTAIN_ORIGINAL)) {
					int originalityDossier = dossier.getOriginality();
					int originality ;
					String split[] = preCondition.split(",");
					for (int i = 0; i < split.length; i++)
					{
						if (split[i].contains(DossierTerm.CONTAIN_ORIGINAL))
						{
							String oriSplit[] =split[i].split(StringPool.EQUAL);
							originality = Integer.valueOf(oriSplit[1]);
							if (originality!=originalityDossier)
							{
								isSendNotiSMS = false;
								isSendNotiEmail = false;
							}
						}
					}
				}
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			if (notiTemplate != null) {
				 if (KeyPayTerm.MINUTELY.equals(notiTemplate.getInterval())) {
					cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());
				} else if (KeyPayTerm.HOURLY.equals(notiTemplate.getInterval())) {
					cal.add(Calendar.HOUR, notiTemplate.getExpireDuration());
				} else {
					cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());
				}
				//Tính publicationDate == now + 120 thời gian cấu hình (MINUTELY#120)
				if(notiTemplate.getInterval().contains(KeyPayTerm.MINUTELY_PATTERN)) {
					String interVal = notiTemplate.getInterval();
					String intervalSub = interVal.substring(interVal.indexOf('#') + 1);
					_log.info("Tính thời gian publicationDate " + intervalSub);
					calendar.add(Calendar.MINUTE, Integer.valueOf(intervalSub));
				}

				Date expired = cal.getTime();

				Date publicationDate = calendar.getTime();
				Date expiredCal = new Date();
				//Tính expiredDate == publiCationDate + 1h
				if(Validator.isNotNull(calendar.getTime())){
					calendar.add(Calendar.HOUR, 1);
					expiredCal = calendar.getTime();
				}
				_log.info("PublicationDate : " + publicationDate + "expiredCal : " + expiredCal);

				if (notificationType.startsWith(KeyPayTerm.APLC)) {
					if (dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA
							|| dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) {
						try {
							//							Applicant applicant = ApplicantLocalServiceUtil.fetchByAppId(dossier.getApplicantIdNo());
							List<Applicant> applicants = ApplicantLocalServiceUtil
									.findByAppIds(dossier.getApplicantIdNo());
							Applicant foundApplicant = (applicants.isEmpty() ? null : applicants.get(0));

							for (Applicant applicant : applicants) {
								long toUserId = (applicant != null ? applicant.getMappingUserId() : 0l);
								if (toUserId != 0) {
									foundApplicant = applicant;
									break;
								}
							}

							if (foundApplicant != null) {
								JSONObject filesAttach = getFileAttachMailForApplicant(dossier, proAction);
								payloadObj.put("filesAttach", filesAttach);
								payloadObj = verifyPayloadMail(payloadObj);
								_log.info("====================payloadattach2=========" + payloadObj);
								String fromFullName = user.getFullName();
								if (Validator.isNotNull(OpenCPSConfigUtil.getMailToApplicantFrom())) {
									fromFullName = OpenCPSConfigUtil.getMailToApplicantFrom();
								}
								NotificationQueueLocalServiceUtil.addNotificationQueue(user.getUserId(), groupId,
										notificationType, Dossier.class.getName(),
										String.valueOf(dossier.getDossierId()), payloadObj.toJSONString(), fromFullName,
										dossier.getApplicantName(), foundApplicant.getMappingUserId(),
										isSendNotiEmail ? dossier.getContactEmail() : StringPool.BLANK,
										isSendNotiSMS ? dossier.getContactTelNo() : StringPool.BLANK,
										Validator.isNotNull(publicationDate) ? publicationDate : now,
										Validator.isNotNull(expiredCal) ? expiredCal : expired,
										context);
							}
						} catch (NoSuchUserException e) {
							_log.debug(e);
						}
					}
				} else if (notificationType.startsWith(KeyPayTerm.USER)) {

				} else if (notificationType.startsWith("EMPL")) {
					_log.debug("ADD NOTI EMPL");
					List<DossierActionUser> lstDaus = DossierActionUserLocalServiceUtil
							.getByDossierAndStepCode(dossier.getDossierId(), dossierAction.getStepCode());
					_log.debug("ADD NOTI LIST DAU: " + lstDaus.size());
					for (DossierActionUser dau : lstDaus) {
						_log.debug("ADD NOTI DAU: " + dau.getAssigned());
						if (dau.getAssigned() == DossierActionUserTerm.ASSIGNED_TH
								|| dau.getAssigned() == DossierActionUserTerm.ASSIGNED_PH) {
							//							Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, dau.getUserId());
							Serializable employeeCache = cache.getFromCache("Employee",
									groupId + "_" + dau.getUserId());
							Employee employee = null;
							//							employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, dau.getUserId());
							if (employeeCache == null) {
								employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, dau.getUserId());
								if (employee != null) {
									cache.addToCache("Employee", groupId + "_" + dau.getUserId(),
											(Serializable) employee, ttl);
								}
							} else {
								employee = (Employee) employeeCache;
							}

							_log.debug("ADD NOTI EMPLOYEE: " + employee);
							if (employee != null) {
								String telNo = employee != null ? employee.getTelNo() : StringPool.BLANK;
								String fullName = employee != null ? employee.getFullName() : StringPool.BLANK;
								long start = System.currentTimeMillis();
								_log.debug("BEFORE ADD NOTI EMPLOYEE: " + actionConfig.getNotificationType());
								NotificationQueueLocalServiceUtil.addNotificationQueue(user.getUserId(), groupId,
										notificationType, Dossier.class.getName(),
										String.valueOf(dossier.getDossierId()), payloadObj.toJSONString(),
										user.getFullName(), fullName, dau.getUserId(), employee.getEmail(), telNo, now,
										expired, context);
								_log.debug("ADD NOTI QUEUE: " + (System.currentTimeMillis() - start));
							}
						}
					}
				}
			}
		}

		//		Notificationtemplate emplTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, NotificationTemplateTerm.EMPL_01);
		Serializable emplCache = cache.getFromCache(CACHE_NOTIFICATION_TEMPLATE,
				groupId + StringPool.UNDERLINE + NotificationTemplateTerm.EMPL_01);
		//Notificationtemplate emplTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, NotificationTemplateTerm.EMPL_01);
		Notificationtemplate emplTemplate = null;
		if (emplCache == null) {
			emplTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId,
					NotificationTemplateTerm.EMPL_01);
			if (emplTemplate != null) {
				cache.addToCache(CACHE_NOTIFICATION_TEMPLATE,
						groupId + StringPool.UNDERLINE + actionConfig.getNotificationType(),
						(Serializable) emplTemplate, ttl);
			}
		} else {
			emplTemplate = (Notificationtemplate) emplCache;
		}

		Date now = new Date();
		Calendar calEmpl = Calendar.getInstance();
		calEmpl.setTime(now);

		if (emplTemplate != null) {
			if (KeyPayTerm.MINUTELY.equals(emplTemplate.getInterval())) {
				calEmpl.add(Calendar.MINUTE, emplTemplate.getExpireDuration());
			} else if (KeyPayTerm.HOURLY.equals(emplTemplate.getInterval())) {
				calEmpl.add(Calendar.HOUR, emplTemplate.getExpireDuration());
			} else {
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
					if ((stepConfig != null
							&& stepConfig.getStepType() == StepConfigTerm.STEP_TYPE_DISPLAY_MENU_BY_PROCESSED)
							|| (stepConfigX != null && stepConfigX
							.getStepType() == StepConfigTerm.STEP_TYPE_DISPLAY_MENU_BY_PROCESSED)) {
						List<DossierActionUser> lstDaus = DossierActionUserLocalServiceUtil
								.getByDossierAndStepCode(dossier.getDossierId(), dossierAction.getStepCode());
						for (DossierActionUser dau : lstDaus) {
							if (dau.getAssigned() == DossierActionUserTerm.ASSIGNED_TH
									|| dau.getAssigned() == DossierActionUserTerm.ASSIGNED_PH) {
								//								Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, dau.getUserId());
								Serializable employeeCache = cache.getFromCache(CacheTerm.MASTER_DATA_EMPLOYEE,
										groupId + StringPool.UNDERLINE + dau.getUserId());
								Employee employee = null;
								//								employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, dau.getUserId());
								if (employeeCache == null) {
									employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId,
											dau.getUserId());
									if (employee != null) {
										cache.addToCache(CacheTerm.MASTER_DATA_EMPLOYEE,
												groupId + StringPool.UNDERLINE + dau.getUserId(),
												(Serializable) employee, ttl);
									}
								} else {
									employee = (Employee) employeeCache;
								}

								if (employee != null) {
									String telNo = employee != null ? employee.getTelNo() : StringPool.BLANK;
									String fullName = employee != null ? employee.getFullName() : StringPool.BLANK;
									long start = System.currentTimeMillis();
									NotificationQueueLocalServiceUtil.addNotificationQueue(user.getUserId(), groupId,
											NotificationTemplateTerm.EMPL_01, Dossier.class.getName(),
											String.valueOf(dossier.getDossierId()), payloadObj.toJSONString(),
											user.getFullName(), fullName, dau.getUserId(), employee.getEmail(), telNo,
											now, expired, context);
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

	private String processDueDate(long groupId, Date receiveDate,  Date dueDate, String processNo) {
		if (dueDate != null) {
			ServiceProcess process = ServiceProcessLocalServiceUtil.getByG_PNO(groupId, processNo);
			if (process != null) {
				String dueDatePattern = process.getDueDatePattern();
				if (Validator.isNotNull(dueDatePattern)) {
					try {
						JSONObject jsonDueDate = JSONFactoryUtil.createJSONObject(dueDatePattern);
						//_log.info("jsonDueDate: " + jsonDueDate);
						if (jsonDueDate != null) {
							JSONObject hours = jsonDueDate.getJSONObject(DossierDocumentTerm.HOUR);
							JSONObject processHours = jsonDueDate.getJSONObject(DossierDocumentTerm.PROCESS_HOUR);
							//_log.info("hours: " + hours);
							if (hours != null && hours.has(DossierDocumentTerm.AM) && hours.has(DossierDocumentTerm.PM)) {
								//_log.info("AM-PM: ");
								Calendar receiveCalendar = Calendar.getInstance();
								receiveCalendar.setTime(receiveDate);

								Calendar dueCalendar = Calendar.getInstance();
								//_log.info("hours: " + receiveCalendar.get(Calendar.HOUR_OF_DAY));
								if (receiveCalendar.get(Calendar.HOUR_OF_DAY) < 12) {
									dueCalendar.setTime(dueDate);

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
									dueCalendar.setTime(dueDate);
									String hoursAfterNoon = hours.getString(DossierDocumentTerm.PM);
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
								return  APIDateTimeUtils.convertDateToString(dueCalendar.getTime(), APIDateTimeUtils._NORMAL_PARTTERN);
							} else if (processHours != null && processHours.has(DossierDocumentTerm.START_HOUR) && processHours.has(DossierDocumentTerm.DUE_HOUR)) {
								//_log.info("STRART check new: ");
								Calendar receiveCalendar = Calendar.getInstance();
								receiveCalendar.setTime(receiveDate);
								//
								String receiveHour = processHours.getString(DossierDocumentTerm.START_HOUR);
								//_log.info("receiveHour: " + receiveHour);

								if (Validator.isNotNull(receiveHour)) {
									String[] splitHour = StringUtil.split(receiveHour, StringPool.COLON);
									if (splitHour != null) {
										int hourStart = GetterUtil.getInteger(splitHour[0]);
										if (receiveCalendar.get(Calendar.HOUR_OF_DAY) < hourStart) {
											String[] splitdueHour = StringUtil.split(processHours.getString(DossierDocumentTerm.DUE_HOUR),
													StringPool.COLON);
											Calendar dueCalendar = Calendar.getInstance();
											if (splitdueHour != null) {
												dueCalendar.set(Calendar.HOUR_OF_DAY,
														GetterUtil.getInteger(splitdueHour[0]));
												dueCalendar.set(Calendar.MINUTE,
														GetterUtil.getInteger(splitdueHour[1]));
											} else {
												return APIDateTimeUtils.convertDateToString(dueDate, APIDateTimeUtils._NORMAL_PARTTERN);
											}
											return APIDateTimeUtils.convertDateToString(dueCalendar.getTime(), APIDateTimeUtils._NORMAL_PARTTERN);
										} else {
											return APIDateTimeUtils.convertDateToString(dueDate, APIDateTimeUtils._NORMAL_PARTTERN);
										}
									}
								}
							} else {
								return APIDateTimeUtils.convertDateToString(dueDate, APIDateTimeUtils._NORMAL_PARTTERN);
							}
						} else {
							return APIDateTimeUtils.convertDateToString(dueDate, APIDateTimeUtils._NORMAL_PARTTERN);
						}
					} catch (JSONException e) {
						_log.error(e);
						return APIDateTimeUtils.convertDateToString(dueDate, APIDateTimeUtils._NORMAL_PARTTERN);
					}
				} else {
					return APIDateTimeUtils.convertDateToString(dueDate, APIDateTimeUtils._NORMAL_PARTTERN);
				}
			} else {
				return APIDateTimeUtils.convertDateToString(dueDate, APIDateTimeUtils._NORMAL_PARTTERN);
			}
		}
			return StringPool.BLANK;
	}

	private void updateDossierPayload(Dossier dossier, JSONObject obj) {
		if (obj.has(DossierTerm.DOSSIER_NOTE)) {
			if (!obj.getString(DossierTerm.DOSSIER_NOTE).equals(dossier.getDossierNote())) {
				dossier.setDossierNote(obj.getString(DossierTerm.DOSSIER_NOTE));
			}
		}
		if (obj.has(DossierTerm.EXTEND_DATE) && Validator.isNotNull(obj.get(DossierTerm.EXTEND_DATE))
				&& GetterUtil.getLong(obj.get(DossierTerm.EXTEND_DATE)) > 0) {
			if (dossier.getExtendDate() == null
					|| obj.getLong(DossierTerm.EXTEND_DATE) != dossier.getExtendDate().getTime()) {
				dossier.setExtendDate(new Date(obj.getLong(DossierTerm.EXTEND_DATE)));
			}
		}
		if (obj.has(DossierTerm.DOSSIER_NO)) {
			//_log.info("Sync dossier no");
			if (Validator.isNotNull(obj.getString(DossierTerm.DOSSIER_NO))
					&& !obj.getString(DossierTerm.DOSSIER_NO).equals(dossier.getDossierNo())
					&& (dossier.getOriginDossierId() > 0 || Validator.isNull(dossier.getDossierNo()))) {
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
			if (dossier.getFinishDate() == null
					|| obj.getLong(DossierTerm.FINISH_DATE) != dossier.getFinishDate().getTime()) {
				dossier.setFinishDate(new Date(obj.getLong(DossierTerm.FINISH_DATE)));
			}
		}
		if (obj.has(DossierTerm.RECEIVE_DATE) && Validator.isNotNull(obj.get(DossierTerm.RECEIVE_DATE))
				&& GetterUtil.getLong(obj.get(DossierTerm.RECEIVE_DATE)) > 0) {
			if (dossier.getReceiveDate() == null
					|| obj.getLong(DossierTerm.RECEIVE_DATE) != dossier.getReceiveDate().getTime()) {
				dossier.setReceiveDate(new Date(obj.getLong(DossierTerm.RECEIVE_DATE)));
			}
		}
		if (obj.has(DossierTerm.SUBMIT_DATE) && Validator.isNotNull(obj.get(DossierTerm.SUBMIT_DATE))
				&& GetterUtil.getLong(obj.get(DossierTerm.SUBMIT_DATE)) > 0) {
			if (dossier.getSubmitDate() == null || (dossier.getSubmitDate() != null
					&& obj.getLong(DossierTerm.SUBMIT_DATE) != dossier.getSubmitDate().getTime())) {
				dossier.setSubmitDate(new Date(obj.getLong(DossierTerm.SUBMIT_DATE)));
			}
		}
		if (obj.has(DossierTerm.EXTEND_DATE) && Validator.isNotNull(obj.get(DossierTerm.EXTEND_DATE))
				&& GetterUtil.getLong(obj.get(DossierTerm.EXTEND_DATE)) > 0) {
			if (dossier.getExtendDate() == null
					|| obj.getLong(DossierTerm.EXTEND_DATE) != dossier.getExtendDate().getTime()) {
				dossier.setExtendDate(new Date(obj.getLong(DossierTerm.EXTEND_DATE)));
			}
		}
		if (obj.has(DossierTerm.DOSSIER_NOTE)) {
			if (dossier.getDossierNote() == null
					|| !obj.getString(DossierTerm.DOSSIER_NOTE).equals(dossier.getDossierNote())) {
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
			if (dossier.getReleaseDate() == null
					|| obj.getLong(DossierTerm.RELEASE_DATE) != dossier.getReleaseDate().getTime()) {
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
		if (obj.has(DossierTerm.REGISTER_BOOK_CODE)) {
			if (!obj.getString(DossierTerm.REGISTER_BOOK_CODE).equals(dossier.getRegisterBookCode())) {
				dossier.setRegisterBookCode(obj.getString(DossierTerm.REGISTER_BOOK_CODE));
			}
		}
		if (obj.has(DossierTerm.COUNTER)) {
			long counter = Validator.isNotNull(obj.getString(DossierTerm.COUNTER))
					? Integer.valueOf(obj.getString(DossierTerm.COUNTER))
					: 0;
			if (counter != dossier.getCounter()) {
				dossier.setCounter(counter);
			}
		}
		if (obj.has(DossierTerm.VIA_POSTAL) && Validator.isNotNull(obj.get(DossierTerm.VIA_POSTAL))) {
			if (obj.getInt(DossierTerm.VIA_POSTAL) != dossier.getViaPostal()) {
				dossier.setViaPostal(obj.getInt(DossierTerm.VIA_POSTAL));
			}
		}

		//Update mat thong tin chu ho so
		if (Validator.isNull(dossier.getDossierStatus()) && Validator.isNull(dossier.getApplicantName())) {
			//
			String applicantName = null;
			String applicantIdNo = null;
			String address = null;
			String cityCode = null;
			String cityName = null;
			String districtCode = null;
			String districtName = null;
			String wardCode = null;
			String wardName = null;
			String contactTelNo = null;
			String contactEmail = null;

			if (obj.has(DossierTerm.APPLICANT_NAME) && Validator.isNotNull(obj.get(DossierTerm.APPLICANT_NAME))) {
				applicantName = String.valueOf(obj.get(DossierTerm.APPLICANT_NAME));
				dossier.setApplicantName(applicantName);
			}
			if (obj.has(DossierTerm.APPLICANT_ID_TYPE) && Validator.isNotNull(obj.get(DossierTerm.APPLICANT_ID_TYPE))) {
				dossier.setApplicantIdType(String.valueOf(obj.get(DossierTerm.APPLICANT_ID_TYPE)));
			}
			if (obj.has(DossierTerm.APPLICANT_ID_NO) && Validator.isNotNull(obj.get(DossierTerm.APPLICANT_ID_NO))) {
				applicantIdNo = String.valueOf(obj.get(DossierTerm.APPLICANT_ID_NO));
				dossier.setApplicantIdNo(applicantIdNo);
			}
			if (obj.has(DossierTerm.ADDRESS) && Validator.isNotNull(obj.get(DossierTerm.ADDRESS))) {
				address = String.valueOf(obj.get(DossierTerm.ADDRESS));
				dossier.setAddress(address);
			}
			if (obj.has(DossierTerm.CITY_CODE) && Validator.isNotNull(obj.get(DossierTerm.CITY_CODE))) {
				cityCode = String.valueOf(obj.get(DossierTerm.CITY_CODE));
				dossier.setCityCode(cityCode);
				//
				cityName = getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION,
						String.valueOf(obj.get(DossierTerm.CITY_CODE)));
				if (Validator.isNotNull(cityName)) {
					dossier.setCityName(cityName);
				}

			}
			if (obj.has(DossierTerm.DISTRICT_CODE) && Validator.isNotNull(obj.get(DossierTerm.DISTRICT_CODE))) {
				districtCode = String.valueOf(obj.get(DossierTerm.DISTRICT_CODE));
				dossier.setDistrictCode(districtCode);
				//
				districtName = getDictItemName(
						dossier.getGroupId(), ADMINISTRATIVE_REGION, String.valueOf(obj.get(DossierTerm.DISTRICT_CODE)));
				if (Validator.isNotNull(districtName)) {
					dossier.setDistrictName(districtName);
				}
			}
			if (obj.has(DossierTerm.WARD_CODE) && Validator.isNotNull(obj.get(DossierTerm.WARD_CODE))) {
				wardCode = String.valueOf(obj.get(DossierTerm.WARD_CODE));
				dossier.setWardCode(wardCode);
				//
				wardName = getDictItemName(
						dossier.getGroupId(), ADMINISTRATIVE_REGION, String.valueOf(obj.get(DossierTerm.WARD_CODE)));
				if (Validator.isNotNull(wardName)) {
					dossier.setWardName(wardName);
				}
			}
			if (obj.has(DossierTerm.CONTACT_EMAIL) && Validator.isNotNull(obj.get(DossierTerm.CONTACT_EMAIL))) {
				contactEmail = String.valueOf(obj.get(DossierTerm.CONTACT_EMAIL));
				dossier.setContactEmail(contactEmail);
			}
			if (obj.has(DossierTerm.CONTACT_TEL_NO) && Validator.isNotNull(obj.get(DossierTerm.CONTACT_TEL_NO))) {
				contactTelNo = String.valueOf(obj.get(DossierTerm.CONTACT_TEL_NO));
				dossier.setContactTelNo(contactTelNo);
			}
			if (obj.has(DossierTerm.SAMPLE_COUNT) && Validator.isNotNull(obj.get(DossierTerm.SAMPLE_COUNT))) {
				dossier.setSampleCount(obj.getInt(DossierTerm.SAMPLE_COUNT));
			}
			if (obj.has(DossierTerm.VIA_POSTAL) && Validator.isNotNull(obj.get(DossierTerm.VIA_POSTAL))) {
				int viaPostal = obj.getInt(DossierTerm.VIA_POSTAL);
				dossier.setViaPostal(viaPostal);
				//
				if (viaPostal == 2) {
					if (obj.has(DossierTerm.POSTAL_ADDRESS) && Validator.isNotNull(obj.get(DossierTerm.POSTAL_ADDRESS))) {
						dossier.setPostalAddress(String.valueOf(obj.get(DossierTerm.POSTAL_ADDRESS)));
					}
					if (obj.has(DossierTerm.POSTAL_CITY_CODE) && Validator.isNotNull(obj.get(DossierTerm.POSTAL_CITY_CODE))) {
						dossier.setPostalCityCode(String.valueOf(obj.get(DossierTerm.POSTAL_CITY_CODE)));

						String postalCityName = getDictItemName(dossier.getGroupId(), VNPOST_CITY_CODE,
								String.valueOf(obj.get(DossierTerm.POSTAL_CITY_CODE)));
						if (Validator.isNotNull(postalCityName))
							dossier.setPostalCityName(postalCityName);
					}
					if (obj.has(DossierTerm.POSTAL_DISTRICT_CODE) && Validator.isNotNull(obj.get(DossierTerm.POSTAL_DISTRICT_CODE))) {
						dossier.setPostalDistrictCode(String.valueOf(obj.get(DossierTerm.POSTAL_DISTRICT_CODE)));

						String postalDistrictName = getDictItemName(dossier.getGroupId(), VNPOST_CITY_CODE,
								String.valueOf(obj.get(DossierTerm.POSTAL_DISTRICT_CODE)));
						if (Validator.isNotNull(postalDistrictName))
							dossier.setPostalDistrictName(postalDistrictName);
					}
					if (obj.has(DossierTerm.POSTAL_TEL_NO) && Validator.isNotNull(obj.get(DossierTerm.POSTAL_TEL_NO))) {
						dossier.setPostalTelNo(String.valueOf(obj.get(DossierTerm.POSTAL_TEL_NO)));
					}
					if (obj.has(DossierTerm.CONTACT_TEL_NO) && Validator.isNotNull(obj.get(DossierTerm.CONTACT_TEL_NO))) {
						dossier.setContactTelNo(String.valueOf(obj.get(DossierTerm.CONTACT_TEL_NO)));
					}
					if (obj.has(DossierTerm.CONTACT_TEL_NO) && Validator.isNotNull(obj.get(DossierTerm.CONTACT_TEL_NO))) {
						dossier.setContactTelNo(String.valueOf(obj.get(DossierTerm.CONTACT_TEL_NO)));
					}
				}
				else {
					dossier.setPostalAddress(StringPool.BLANK);
					dossier.setPostalCityCode(StringPool.BLANK);
					dossier.setPostalTelNo(StringPool.BLANK);
					dossier.setPostalDistrictCode(StringPool.BLANK);
				}
			}

			if (obj.has(DossierTerm.IS_SAME_APPLICANT) && Validator.isNotNull(obj.get(DossierTerm.IS_SAME_APPLICANT))
					&& obj.getBoolean(DossierTerm.IS_SAME_APPLICANT)) {
				dossier.setDelegateName(applicantName);
				dossier.setDelegateIdNo(applicantIdNo);
				dossier.setDelegateTelNo(contactTelNo);
				dossier.setDelegateAddress(address);
				dossier.setDelegateEmail(contactEmail);
				if (Validator.isNotNull(cityCode)) {
					dossier.setDelegateCityCode(cityCode);
					dossier.setDelegateCityName(cityName != null ? cityName : StringPool.BLANK);
				}

				if (Validator.isNotNull(districtCode)) {
					dossier.setDelegateDistrictCode(districtCode);
					dossier.setDelegateDistrictName(districtName != null ? districtName : StringPool.BLANK);
				}

				if (Validator.isNotNull(wardCode)) {
					dossier.setDelegateWardCode(wardCode);
					dossier.setDelegateWardName(wardName != null ? wardName : StringPool.BLANK);
				}
			} else {
				//
				if (obj.has(DossierTerm.DELEGATE_NAME) && Validator.isNotNull(obj.get(DossierTerm.DELEGATE_NAME))) {
					dossier.setDelegateName(String.valueOf(obj.get(DossierTerm.DELEGATE_NAME)));
				}
				if (obj.has(DossierTerm.DELEGATE_ID_NO) && Validator.isNotNull(obj.get(DossierTerm.DELEGATE_ID_NO))) {
					dossier.setDelegateIdNo(String.valueOf(obj.get(DossierTerm.DELEGATE_ID_NO)));
				}
				if (obj.has(DossierTerm.DELEGATE_ADDRESS)
						&& Validator.isNotNull(obj.get(DossierTerm.DELEGATE_ADDRESS))) {
					dossier.setDelegateAddress(String.valueOf(obj.get(DossierTerm.DELEGATE_ADDRESS)));
				}
				if (obj.has(DossierTerm.DELEGATE_CITYCODE)
						&& Validator.isNotNull(obj.get(DossierTerm.DELEGATE_CITYCODE))) {
					dossier.setDelegateCityCode(String.valueOf(obj.get(DossierTerm.DELEGATE_CITYCODE)));
					//
					String delegateCityName = getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION,
							String.valueOf(obj.get(DossierTerm.DELEGATE_CITYCODE)));
					if (Validator.isNotNull(delegateCityName)) {
						dossier.setDelegateCityName(delegateCityName);
					}

				}
				if (obj.has(DossierTerm.DELEGATE_DISTRICTCODE)
						&& Validator.isNotNull(obj.get(DossierTerm.DELEGATE_DISTRICTCODE))) {
					dossier.setDistrictCode(String.valueOf(obj.get(DossierTerm.DELEGATE_DISTRICTCODE)));
					//
					String delegateDistrictName = getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION,
							String.valueOf(obj.get(DossierTerm.DELEGATE_DISTRICTCODE)));
					if (Validator.isNotNull(delegateDistrictName)) {
						dossier.setDelegateDistrictName(delegateDistrictName);
					}
				}
				if (obj.has(DossierTerm.DELEGATE_WARDCODE)
						&& Validator.isNotNull(obj.get(DossierTerm.DELEGATE_WARDCODE))) {
					dossier.setDelegateWardCode(String.valueOf(obj.get(DossierTerm.DELEGATE_WARDCODE)));
					//
					String delegateWardName = getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION,
							String.valueOf(obj.get(DossierTerm.DELEGATE_WARDCODE)));
					if (Validator.isNotNull(delegateWardName)) {
						dossier.setDelegateWardName(delegateWardName);
					}
				}
				if (obj.has(DossierTerm.DELEGATE_EMAIL) && Validator.isNotNull(obj.get(DossierTerm.DELEGATE_EMAIL))) {
					dossier.setDelegateEmail(String.valueOf(obj.get(DossierTerm.DELEGATE_EMAIL)));
				}
				if (obj.has(DossierTerm.DELEGATE_TELNO) && Validator.isNotNull(obj.get(DossierTerm.DELEGATE_TELNO))) {
					dossier.setDelegateTelNo(String.valueOf(obj.get(DossierTerm.DELEGATE_TELNO)));
				}
			}

			if (obj.has(DossierTerm.APPLICANT_NOTE) && Validator.isNotNull(obj.get(DossierTerm.APPLICANT_NOTE))) {
				dossier.setApplicantNote(String.valueOf(obj.get(DossierTerm.APPLICANT_NOTE)));
			}
			if (obj.has(DossierTerm.DOSSIER_NAME) && Validator.isNotNull(obj.get(DossierTerm.DOSSIER_NAME))) {
				dossier.setDossierName(String.valueOf(obj.get(DossierTerm.DOSSIER_NAME)));
			}
		}
	}

	private void processPaymentFile(long groupId, long userId, String payment, ProcessOption option,
									ProcessAction proAction, DossierAction previousAction, Dossier dossier, ServiceContext context)
			throws PortalException {
		//		long serviceProcessId = (option != null ? option.getServiceProcessId() : previousAction.getServiceProcessId());
		//		ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);
		String paymentMethod = "";
		String confirmPayload = "";
		try {
			JSONObject paymentObj = JSONFactoryUtil.createJSONObject(payment);
			if (paymentObj.has("paymentMethod")) {
				paymentMethod = paymentObj.getString("paymentMethod");
			}
			if (paymentObj.has("confirmPayload")) {
				confirmPayload = paymentObj.getString("confirmPayload");
			}
		} catch (Exception e) {
			_log.debug(e);
		}
		//Yêu cầu nộp tạm ứng
		if (proAction.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_YEU_CAU_NOP_TAM_UNG
				|| proAction.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_YEU_CAU_QUYET_TOAN_PHI
				&& Validator.isNotNull(payment)) {

			createPaymentFile(groupId, userId, payment, option, proAction, previousAction, dossier, context);
		} else if (proAction.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_XAC_NHAN_HOAN_THANH_THU_PHI) {

			// neu chua co payment file thi phai tao payment file
			PaymentFile oldPaymentFile = paymentFileLocalService.getByDossierId(groupId, dossier.getDossierId());
			if (Validator.isNull(oldPaymentFile)) {

				oldPaymentFile = createPaymentFile(groupId, userId, payment, option, proAction, previousAction, dossier,
						context);
			}

//			try {
//				_log.debug("groupId=" + groupId + " dossierId=" + dossier.getDossierId());
//				ExecuteOneActionTerm.invokeSInvoice(groupId, dossier, context);
//			} catch (Exception e) {
//				// TODO: do sth
//				_log.error(e);
//			}

			String CINVOICEUrl = "postal/invoice";

			JSONObject resultObj = null;
			Map<String, Object> params = new HashMap<>();

			int intpaymentMethod = 0;
			if (Validator.isNotNull(proAction.getPreCondition())) {
				intpaymentMethod = checkPaymentMethodinPrecondition(proAction.getPreCondition());
			}

			if (oldPaymentFile != null && proAction.getPreCondition().toLowerCase().contains("sendinvoice=1")) {
				params = createParamsInvoice(oldPaymentFile, dossier, intpaymentMethod);
				InvokeREST callRest = new InvokeREST();
				String baseUrl = RESTFulConfiguration.SERVER_PATH_BASE;
				HashMap<String, String> properties = new HashMap<String, String>();

				resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON, baseUrl,
						CINVOICEUrl, StringPool.BLANK, StringPool.BLANK, properties, params, context);

			}

			if (Validator.isNotNull(oldPaymentFile)) {
				//				String paymentMethod = "";
				//				if (intpaymentMethod != 0) {
				//					paymentMethod = checkPaymentMethod(intpaymentMethod);
				//				}
				if (resultObj != null) {
					oldPaymentFile.setEinvoice(resultObj.toString());
					oldPaymentFile.setInvoicePayload(params.toString());
					//					if (Validator.isNotNull(paymentMethod)) {
					//						oldPaymentFile.setPaymentMethod(paymentMethod);
					//					}
					if (Validator.isNotNull(paymentMethod)) {
						oldPaymentFile.setPaymentMethod(paymentMethod);
					}
				}

				if (Validator.isNotNull(payment)) {
					try {
						JSONObject paymentObj = JSONFactoryUtil.createJSONObject(payment);
						if (paymentObj.has(KeyPayTerm.PAYMENTNOTE)) {
							if (oldPaymentFile != null)
								oldPaymentFile.setPaymentNote(paymentObj.getString(KeyPayTerm.PAYMENTNOTE));
							String epaymentProfile = oldPaymentFile != null ? oldPaymentFile.getEpaymentProfile()
									: StringPool.BLANK;
							if (Validator.isNotNull(epaymentProfile)) {
								JSONObject jsonEpayment = JSONFactoryUtil.createJSONObject(epaymentProfile);
								jsonEpayment.put(KeyPayTerm.PAYMENTNOTE, paymentObj.getString(KeyPayTerm.PAYMENTNOTE));
								if (oldPaymentFile != null)
									oldPaymentFile.setEpaymentProfile(jsonEpayment.toJSONString());
							}
						}
					} catch (JSONException e) {
						_log.debug(e);
					}

				}
				if (oldPaymentFile != null) {
					_log.info("=======================paymmm========" + oldPaymentFile);
					oldPaymentFile.setPaymentStatus(proAction.getRequestPayment());
					if (Validator.isNotNull(confirmPayload)) {
						oldPaymentFile.setConfirmPayload(confirmPayload);
					}
					if (Validator.isNotNull(paymentMethod)) {
						oldPaymentFile.setPaymentMethod(paymentMethod);
					}
					if (oldPaymentFile.getApproveDatetime() == null)
						oldPaymentFile.setApproveDatetime(new Date());
					paymentFileLocalService.updatePaymentFile(oldPaymentFile);
				}
			}
		} else if (proAction.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_BAO_DA_NOP_PHI) {
			PaymentFile oldPaymentFile = paymentFileLocalService.getByDossierId(groupId, dossier.getDossierId());
			//			int intpaymentMethod = checkPaymentMethodinPrecondition(proAction.getPreCondition());
			//			String paymentMethod = checkPaymentMethod(intpaymentMethod);
			if (oldPaymentFile != null) {
				_log.info("=======================paymmm========" + payment);
				oldPaymentFile.setPaymentStatus(proAction.getRequestPayment());
				//				oldPaymentFile.setPaymentMethod(paymentMethod);
				if (Validator.isNotNull(paymentMethod)) {
					oldPaymentFile.setPaymentMethod(paymentMethod);
				}
				paymentFileLocalService.updatePaymentFile(oldPaymentFile);
			}
		}
	}

	private PaymentFile createPaymentFile(long groupId, long userId, String payment, ProcessOption option,
										  ProcessAction proAction, DossierAction previousAction, Dossier dossier, ServiceContext context)
			throws PortalException {
		PaymentFile oldPaymentFile = paymentFileLocalService.getByDossierId(groupId, dossier.getDossierId());

		String paymentFee =  StringPool.BLANK;
		Long feeAmount =  0l, serviceAmount =  0l, shipAmount =  0l;
		String paymentNote =  StringPool.BLANK;
		long advanceAmount = 0l;
		if (Validator.isNotNull(oldPaymentFile))
		{
			paymentFee = Validator.isNotNull(oldPaymentFile.getPaymentFee()) ? oldPaymentFile.getPaymentFee() : StringPool.BLANK;
			feeAmount = Validator.isNotNull(oldPaymentFile.getFeeAmount()) ? oldPaymentFile.getFeeAmount() : 0l;
			serviceAmount = Validator.isNotNull(oldPaymentFile.getServiceAmount()) ? oldPaymentFile.getServiceAmount() : 0l;
			shipAmount = Validator.isNotNull(oldPaymentFile.getShipAmount()) ? oldPaymentFile.getShipAmount() : 0l;
			paymentNote = Validator.isNotNull(oldPaymentFile.getPaymentNote()) ? oldPaymentFile.getPaymentNote() : StringPool.BLANK;
			advanceAmount = Validator.isNotNull(oldPaymentFile.getAdvanceAmount()) ? oldPaymentFile.getAdvanceAmount() : 0l;
		}
		//long paymentAmount = 0l;
		String epaymentProfile = StringPool.BLANK;
		String bankInfo = StringPool.BLANK;
		int paymentStatus = 0;
		String paymentMethod = StringPool.BLANK;
		NumberFormat fmt = NumberFormat.getNumberInstance(LocaleUtil.getDefault());
		DecimalFormatSymbols customSymbol = new DecimalFormatSymbols();
		customSymbol.setDecimalSeparator(',');
		customSymbol.setGroupingSeparator('.');
		((DecimalFormat) fmt).setDecimalFormatSymbols(customSymbol);
		fmt.setGroupingUsed(true);

		try {
			JSONObject paymentObj = JSONFactoryUtil.createJSONObject(payment);
			if (paymentObj.has(KeyPayTerm.PAYMENTNOTE)) {
				paymentNote = paymentObj.getString(KeyPayTerm.PAYMENTNOTE);
			}
			if (paymentObj.has(KeyPayTerm.FEEAMOUNT)) {
				feeAmount = (Long) fmt.parse(paymentObj.getString(KeyPayTerm.FEEAMOUNT));
			}
			if (paymentObj.has(KeyPayTerm.SERVICEAMOUNT)) {
				serviceAmount = (Long) fmt.parse(paymentObj.getString(KeyPayTerm.SERVICEAMOUNT));
			}
			if (paymentObj.has(KeyPayTerm.SHIPAMOUNT)) {
				shipAmount = (Long) fmt.parse(paymentObj.getString(KeyPayTerm.SHIPAMOUNT));
			}
			if (paymentObj.has(KeyPayTerm.REQUESTPAYMENT)) {
				paymentStatus = paymentObj.getInt(KeyPayTerm.REQUESTPAYMENT);
			}
			if (paymentObj.has(KeyPayTerm.ADVANCEAMOUNT)) {
				advanceAmount = (Long) fmt.parse(paymentObj.getString(KeyPayTerm.ADVANCEAMOUNT));
			}

			JSONObject paymentObj2 = JSONFactoryUtil.createJSONObject(proAction.getPaymentFee());
			if (paymentObj2.has("paymentFee")) {
				paymentFee = paymentObj2.getString("paymentFee");
			}
			if (paymentObj.has("paymentMethod")) {
				paymentMethod = paymentObj.getString("paymentMethod");
			}
		} catch (JSONException e) {
			_log.debug(e);
		} catch (ParseException e) {
			_log.debug(e);
		}


		if (oldPaymentFile != null) {
			if (Validator.isNotNull(paymentMethod)) {
				oldPaymentFile.setPaymentMethod(paymentMethod);
			}
			if (Validator.isNotNull(paymentNote))
				oldPaymentFile.setPaymentNote(paymentNote);
			try {
				PaymentFile paymentFile = paymentFileLocalService.updateApplicantFeeAmount(
						oldPaymentFile.getPaymentFileId(), proAction.getRequestPayment(), feeAmount, serviceAmount,
						shipAmount, paymentNote, dossier.getOriginality());
				String generatorPayURL = PaymentUrlGenerator.generatorPayURL(groupId, paymentFile.getPaymentFileId(),
						paymentFee, dossier);
				JSONObject epaymentProfileJsonNew = JSONFactoryUtil.createJSONObject(paymentFile.getEpaymentProfile());
				epaymentProfileJsonNew.put(KeyPayTerm.KEYPAYURL, generatorPayURL);

				PaymentConfig paymentConfig = paymentConfigLocalService.getPaymentConfigByGovAgencyCode(groupId,
						dossier.getGovAgencyCode());
				JSONObject epaymentConfigJSON = paymentConfig != null
						? JSONFactoryUtil.createJSONObject(paymentConfig.getEpaymentConfig())
						: JSONFactoryUtil.createJSONObject();

				_log.debug("==========VTPayTerm.VTP_CONFIG========" + epaymentConfigJSON);
				if (epaymentConfigJSON.has(VTPayTerm.VTP_CONFIG)) {
					try {
						JSONObject schema = epaymentConfigJSON.getJSONObject(VTPayTerm.VTP_CONFIG);
						JSONObject data = JSONFactoryUtil.createJSONObject();

						data.put(schema.getJSONObject(VTPayTerm.PRIORITY).getString(VTPayTerm.KEY),
								schema.getJSONObject(VTPayTerm.PRIORITY).getString(VTPayTerm.VALUE));
						data.put(schema.getJSONObject(VTPayTerm.VERSION).getString(VTPayTerm.KEY),
								schema.getJSONObject(VTPayTerm.VERSION).getString(VTPayTerm.VALUE));
						data.put(schema.getJSONObject(VTPayTerm.TYPE).getString(VTPayTerm.KEY),
								schema.getJSONObject(VTPayTerm.TYPE).getString(VTPayTerm.VALUE));
						data.put(schema.getJSONObject(VTPayTerm.BILLCODE).getString(VTPayTerm.KEY),
								VTPayTerm.createBillCode(dossier.getGovAgencyCode(), paymentFile.getInvoiceNo()));
						data.put(schema.getJSONObject(VTPayTerm.ORDER_ID).getString(VTPayTerm.KEY),
								VTPayTerm.createOrderId(dossier.getDossierId(), dossier.getDossierNo()));
						data.put(schema.getJSONObject(VTPayTerm.AMOUNT).getString(VTPayTerm.KEY),
								paymentFile.getPaymentAmount());
						data.put(schema.getJSONObject(VTPayTerm.MERCHANT_CODE).getString(VTPayTerm.KEY),
								schema.getJSONObject(VTPayTerm.MERCHANT_CODE).getString(VTPayTerm.VALUE));

						epaymentProfileJsonNew.put(VTPayTerm.VTPAY_GENQR, data);
					} catch (Exception e) {
						_log.error(e);
					}

				}
				_log.info("==========VTPayTerm.KP_DVCQG_CONFIG========" + epaymentConfigJSON);
				if (epaymentConfigJSON.has(KeyPayTerm.KP_DVCQG_CONFIG)) {
					try {
						epaymentProfileJsonNew.put(KeyPayTerm.KPDVCQG, true);
						JSONObject schema = epaymentConfigJSON.getJSONObject(KeyPayTerm.KP_DVCQG_CONFIG);
						epaymentProfileJsonNew.put(KeyPayTerm.KP_DVCQG_CONFIG, schema);
					} catch (Exception e) {
						_log.error(e);
					}

				}
				if (epaymentConfigJSON.has(KeyPayTerm.PP_DVCGQ_CONFIG)) {
					try {
						epaymentProfileJsonNew.put(KeyPayTerm.PP_KPDVCQG, true);
						JSONObject schema = epaymentConfigJSON.getJSONObject(KeyPayTerm.PP_DVCGQ_CONFIG);
						epaymentProfileJsonNew.put(KeyPayTerm.PP_DVCGQ_CONFIG, schema);
					} catch (Exception e) {
						_log.error(e);
					}

				}
				if (epaymentConfigJSON.has(KeyPayTerm.KEYPAY_LATE_CONFIG)) {
					try {
						epaymentProfileJsonNew.put(KeyPayTerm.KEYPAY_LATE, true);
						JSONObject schema = epaymentConfigJSON.getJSONObject(KeyPayTerm.KEYPAY_LATE_CONFIG);
						epaymentProfileJsonNew.put(KeyPayTerm.KEYPAY_LATE_CONFIG, schema);
					} catch (Exception e) {
						_log.error(e);
					}

				}
				_log.info(333333);
				_log.info("==========Payment before add paygov111: " + epaymentConfigJSON);
				if (epaymentConfigJSON.has(KeyPayTerm.PAYGOV_CONFIG)) {
					try {
						JSONObject schema = epaymentConfigJSON.getJSONObject(KeyPayTerm.PAYGOV_CONFIG);
						epaymentProfileJsonNew.put(KeyPayTerm.PAYGOV_CONFIG, schema);
						paymentFileLocalService.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(),
								epaymentProfileJsonNew.toJSONString(), context);
					} catch (Exception e) {
						_log.error(e);
					}

				}
				_log.info("==========Payment after add paygov111: " + epaymentConfigJSON);
				paymentFileLocalService.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(),
						epaymentProfileJsonNew.toJSONString(), context);
			} catch (JSONException e) {
				_log.debug(e);
			}
		} else {
			long paymentAmount = feeAmount + serviceAmount + shipAmount - advanceAmount;

			PaymentFile paymentFile = paymentFileLocalService.createPaymentFiles(userId, groupId,
					dossier.getDossierId(), dossier.getReferenceUid(), paymentFee, advanceAmount, feeAmount,
					serviceAmount, shipAmount, paymentAmount, paymentNote, epaymentProfile, bankInfo, paymentStatus,
					paymentMethod, context);

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
			JSONObject epaymentConfigJSON = paymentConfig != null
					? JSONFactoryUtil.createJSONObject(paymentConfig.getEpaymentConfig())
					: JSONFactoryUtil.createJSONObject();
			JSONObject epaymentProfileJSON = JSONFactoryUtil.createJSONObject();

			if (epaymentConfigJSON.has("paymentKeypayDomain")) {
				String generatorPayURL = PaymentUrlGenerator.generatorPayURL(groupId,
						paymentFile.getPaymentFileId(), paymentFee, dossier);
				epaymentProfileJSON.put(KeyPayTerm.KEYPAYURL, generatorPayURL);

				String pattern1 = KeyPayTerm.GOOD_CODE_EQ;
				String pattern2 = StringPool.AMPERSAND;

				String regexString = Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2);

				Pattern p = Pattern.compile(regexString);
				Matcher m = p.matcher(generatorPayURL);

				if (m.find()) {
					String goodCode = m.group(1);

					epaymentProfileJSON.put(KeyPayTerm.KEYPAYGOODCODE, goodCode);
				} else {
					epaymentProfileJSON.put(KeyPayTerm.KEYPAYGOODCODE, StringPool.BLANK);
				}

				epaymentProfileJSON.put(KeyPayTerm.KEYPAYMERCHANTCODE,
						epaymentConfigJSON.get("paymentMerchantCode"));
				epaymentProfileJSON.put("paymentMerchantSecureKey", epaymentConfigJSON.get("paymentMerchantSecureKey"));
				epaymentProfileJSON.put("paymentHashAlgorithm", epaymentConfigJSON.get("paymentHashAlgorithm"));
				epaymentProfileJSON.put(KeyPayTerm.BANK, String.valueOf(true));
				epaymentProfileJSON.put(KeyPayTerm.PAYGATE, String.valueOf(true));
				epaymentProfileJSON.put(KeyPayTerm.SERVICEAMOUNT, serviceAmount);
				epaymentProfileJSON.put(KeyPayTerm.PAYMENTNOTE, paymentNote);
				epaymentProfileJSON.put(KeyPayTerm.PAYMENTFEE, paymentFee);
//					paymentFileLocalService.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(),
//							epaymentProfileJSON.toJSONString(), context);
			}

			_log.debug("==========VTPayTerm.VTP_CONFIG========" + epaymentConfigJSON);
			if (epaymentConfigJSON.has(VTPayTerm.VTP_CONFIG)) {
				try {
					JSONObject schema = epaymentConfigJSON.getJSONObject(VTPayTerm.VTP_CONFIG);
					JSONObject data = JSONFactoryUtil.createJSONObject();

					data.put(schema.getJSONObject(VTPayTerm.PRIORITY).getString(VTPayTerm.KEY),
							schema.getJSONObject(VTPayTerm.PRIORITY).getString(VTPayTerm.VALUE));
					data.put(schema.getJSONObject(VTPayTerm.VERSION).getString(VTPayTerm.KEY),
							schema.getJSONObject(VTPayTerm.VERSION).getString(VTPayTerm.VALUE));
					data.put(schema.getJSONObject(VTPayTerm.TYPE).getString(VTPayTerm.KEY),
							schema.getJSONObject(VTPayTerm.TYPE).getString(VTPayTerm.VALUE));
					data.put(schema.getJSONObject(VTPayTerm.BILLCODE).getString(VTPayTerm.KEY),
							VTPayTerm.createBillCode(dossier.getGovAgencyCode(), paymentFile.getInvoiceNo()));
					data.put(schema.getJSONObject(VTPayTerm.ORDER_ID).getString(VTPayTerm.KEY),
							VTPayTerm.createOrderId(dossier.getDossierId(), dossier.getDossierNo()));
					data.put(schema.getJSONObject(VTPayTerm.AMOUNT).getString(VTPayTerm.KEY),
							paymentFile.getPaymentAmount());
					data.put(schema.getJSONObject(VTPayTerm.MERCHANT_CODE).getString(VTPayTerm.KEY),
							schema.getJSONObject(VTPayTerm.MERCHANT_CODE).getString(VTPayTerm.VALUE));

					epaymentProfileJSON.put(VTPayTerm.VTPAY_GENQR, data);
//					paymentFileLocalService.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(),
//							epaymentProfileJSON.toJSONString(), context);
				} catch (Exception e) {
					_log.error(e);
				}

			}
			_log.info("==========VTPayTerm.KP_DVCQG_CONFIG========" + epaymentConfigJSON);
			if (epaymentConfigJSON.has(KeyPayTerm.KP_DVCQG_CONFIG)) {
				try {
					epaymentProfileJSON.put(KeyPayTerm.KPDVCQG, true);
					JSONObject schema = epaymentConfigJSON.getJSONObject(KeyPayTerm.KP_DVCQG_CONFIG);
					epaymentProfileJSON.put(KeyPayTerm.KP_DVCQG_CONFIG, schema);
//					paymentFileLocalService.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(),
//							epaymentProfileJSON.toJSONString(), context);
				} catch (Exception e) {
					_log.error(e);
				}

			}
			if (epaymentConfigJSON.has(KeyPayTerm.PP_DVCGQ_CONFIG)) {
				try {
					epaymentProfileJSON.put(KeyPayTerm.PP_KPDVCQG, true);
					JSONObject schema = epaymentConfigJSON.getJSONObject(KeyPayTerm.PP_DVCGQ_CONFIG);
					epaymentProfileJSON.put(KeyPayTerm.PP_DVCGQ_CONFIG, schema);
//					paymentFileLocalService.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(),
//							epaymentProfileJSON.toJSONString(), context);
				} catch (Exception e) {
					_log.error(e);
				}

			}
			if (epaymentConfigJSON.has(KeyPayTerm.KEYPAY_LATE_CONFIG)) {
				try {
					epaymentProfileJSON.put(KeyPayTerm.KEYPAY_LATE, true);
					JSONObject schema = epaymentConfigJSON.getJSONObject(KeyPayTerm.KEYPAY_LATE_CONFIG);
					epaymentProfileJSON.put(KeyPayTerm.KEYPAY_LATE_CONFIG, schema);
					createTransactionKeypayV3(dossier, dossier.getDossierActionId());
//					paymentFileLocalService.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(),
//							epaymentProfileJSON.toJSONString(), context);
				} catch (Exception e) {
					_log.error(e);
				}

			}
			paymentFileLocalService.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(),
					epaymentProfileJSON.toJSONString(), context);
			_log.info("==========Payment before add paygov222: " + epaymentConfigJSON);
			if (epaymentConfigJSON.has(KeyPayTerm.PAYGOV_CONFIG)) {
				try {
					JSONObject schema = epaymentConfigJSON.getJSONObject(KeyPayTerm.PAYGOV_CONFIG);
					epaymentProfileJSON.put(KeyPayTerm.PAYGOV_CONFIG, schema);
					paymentFileLocalService.updateEProfile(dossier.getDossierId(), paymentFile.getReferenceUid(),
							epaymentProfileJSON.toJSONString(), context);
				} catch (Exception e) {
					_log.error(e);
				}

			}
			_log.info("==========Payment after add paygov222: " + epaymentConfigJSON);
		}
		return oldPaymentFile;
	}

	private void createNotificationSMS(long userId, long groupId, Dossier dossier, JSONArray assignedUsers,
									   DossierAction dossierAction, ServiceContext context) {
		//		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		User u = UserLocalServiceUtil.fetchUser(userId);
		JSONObject payloadObj = JSONFactoryUtil.createJSONObject();
		try {
			payloadObj.put(KeyPayTerm.DOSSIER,
					JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(dossier)));

			if (dossierAction != null) {

				payloadObj.put(DossierActionTerm.ACTION_CODE, dossierAction.getActionCode());
				payloadObj.put(DossierActionTerm.ACTION_USER, dossierAction.getActionUser());
				payloadObj.put(DossierActionTerm.ACTION_NAME, dossierAction.getActionName());
				payloadObj.put(DossierActionTerm.ACTION_NOTE, dossierAction.getActionNote());
			}
		} catch (Exception e) {
			_log.error(e);
		}

		Notificationtemplate emplTemplate = NotificationtemplateLocalServiceUtil
				.fetchByF_NotificationtemplateByType(groupId, NotificationTemplateTerm.EMPL_03);
		Date now = new Date();
		Calendar calEmpl = Calendar.getInstance();
		calEmpl.setTime(now);

		if (emplTemplate != null) {
			if (KeyPayTerm.MINUTELY.equals(emplTemplate.getInterval())) {
				calEmpl.add(Calendar.MINUTE, emplTemplate.getExpireDuration());
			} else if (KeyPayTerm.HOURLY.equals(emplTemplate.getInterval())) {
				calEmpl.add(Calendar.HOUR, emplTemplate.getExpireDuration());
			} else {
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
					if ((stepConfig != null
							&& stepConfig.getStepType() == StepConfigTerm.STEP_TYPE_DISPLAY_MENU_BY_PROCESSED)
							|| (stepConfigX != null && stepConfigX
							.getStepType() == StepConfigTerm.STEP_TYPE_DISPLAY_MENU_BY_PROCESSED)) {
						for (int n = 0; n < assignedUsers.length(); n++) {
							JSONObject subUser = assignedUsers.getJSONObject(n);
							if (subUser != null && subUser.has(DossierActionUserTerm.ASSIGNED) && subUser
									.getInt(DossierActionUserTerm.ASSIGNED) == DossierActionUserTerm.ASSIGNED_TH) {
								long userIdAssigned = subUser.getLong(Field.USER_ID);
								Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId,
										userIdAssigned);
								if (employee != null) {
									String telNo = employee != null ? employee.getTelNo() : StringPool.BLANK;
									String fullName = employee != null ? employee.getFullName() : StringPool.BLANK;
									NotificationQueueLocalServiceUtil.addNotificationQueue(userId, groupId,
											NotificationTemplateTerm.EMPL_03, Dossier.class.getName(),
											String.valueOf(dossier.getDossierId()), payloadObj.toJSONString(),
											u.getFullName(), fullName, userIdAssigned, employee.getEmail(), telNo, now,
											expired, context);
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

	private void createSubcription(long userId, long groupId, Dossier dossier, ActionConfig actionConfig,
								   DossierAction dossierAction, ServiceContext context) {
		if (actionConfig != null && Validator.isNotNull(actionConfig.getNotificationType())) {
			//			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
			//			User u = UserLocalServiceUtil.fetchUser(userId);

			Notificationtemplate notiTemplate = NotificationtemplateLocalServiceUtil
					.fetchByF_NotificationtemplateByType(groupId, actionConfig.getNotificationType());
			if (notiTemplate != null) {
				if (actionConfig.getDocumentType().startsWith(KeyPayTerm.APLC)) {
					if (dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA
							|| dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) {
					}
				} else if (actionConfig.getDocumentType().startsWith("EMPL")) {
					if ((dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA
							|| dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG)
							&& dossierAction != null) {
						StepConfig stepConfig = stepConfigLocalService.getByCode(groupId, dossierAction.getStepCode());
						List<DossierActionUser> lstDaus = dossierActionUserLocalService.getByDID_DAI_SC_AS(
								dossier.getDossierId(), dossierAction.getDossierActionId(), dossierAction.getStepCode(),
								new int[] { 1, 2 });
						if (NotificationTemplateTerm.EMPL_01.equals(actionConfig.getDocumentType())
								&& stepConfig != null
								&& String.valueOf(1)
								.equals(Validator.isNotNull(stepConfig.getStepType())
										? String.valueOf(stepConfig.getStepType())
										: StringPool.BLANK)) {
							for (DossierActionUser dau : lstDaus) {
								try {
									SubscriptionLocalServiceUtil.addSubscription(dau.getUserId(), groupId,
											NotificationTemplateTerm.EMPL_01, 0);
								} catch (PortalException e) {
									_log.debug(e);
								}
							}
						}
					}
				} else if (actionConfig.getDocumentType().startsWith("USER")) {

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

	private String generatorPayURL(long groupId, long paymentFileId, String pattern, Dossier dossier)
			throws IOException {
		String result = StringPool.BLANK;
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

				String ship_fee = String.valueOf(0);
				String tax = String.valueOf(0);

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
				String algorithm = KeyPayTerm.VALUE_MD5;
				if (epaymentConfigJSON.has("paymentHashAlgorithm")) {
					algorithm = epaymentConfigJSON.getString("paymentHashAlgorithm");
				}
				// dossier = _getDossier(dossierId);

				// TODO : update returnURL keyPay

				String return_url;
				_log.info("SONDT GENURL paymentReturnUrl ====================== "
						+ JSONFactoryUtil.looseSerialize(epaymentConfigJSON));
				//				return_url = epaymentConfigJSON.getString("paymentReturnUrl")+ "/" + dossier.getReferenceUid() + "/" + paymentFile.getReferenceUid();
				return_url = epaymentConfigJSON.getString("paymentReturnUrl") + "&dossierId=" + dossier.getDossierId()
						+ "&goodCode=" + good_code + "&transId=" + merchant_trans_id + "&referenceUid="
						+ dossier.getReferenceUid();
				_log.info("SONDT GENURL paymentReturnUrl ====================== " + return_url);
				// http://119.17.200.66:2681/web/bo-van-hoa/dich-vu-cong/#/thanh-toan-thanh-cong?paymentPortal=KEYPAY&dossierId=77603&goodCode=123&transId=555
				KeyPay keypay = new KeyPay(String.valueOf(merchant_trans_id), merchant_code, good_code, net_cost,
						ship_fee, tax, bank_code, service_code, version, command, currency_code, desc_1, desc_2, desc_3,
						desc_4, desc_5, xml_description, current_locale, country_code, return_url, internal_bank,
						merchant_secure_key, algorithm);

				// keypay.setKeypay_url(paymentConfig.getKeypayDomain());

				StringBuffer param = new StringBuffer();
				param.append(KeyPayTerm.MERCHANT_CODE_EQ)
						.append(URLEncoder.encode(keypay.getMerchant_code(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				//				param.append(KeyPayTerm.MERCHANT_SECURE_KEY_EQ).append(URLEncoder.encode(keypay.getMerchant_secure_key(), StandardCharsets.UTF_8.name()))
				//						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.BANK_CODE_EQ)
						.append(URLEncoder.encode(keypay.getBank_code(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.INTERNAL_BANK_EQ)
						.append(URLEncoder.encode(keypay.getInternal_bank(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.MERCHANT_TRANS_ID_EQ)
						.append(URLEncoder.encode(keypay.getMerchant_trans_id(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.GOOD_CODE_EQ)
						.append(URLEncoder.encode(keypay.getGood_code(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.NET_COST_EQ)
						.append(URLEncoder.encode(keypay.getNet_cost(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.SHIP_FEE_EQ)
						.append(URLEncoder.encode(keypay.getShip_fee(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.TAX_EQ)
						.append(URLEncoder.encode(keypay.getTax(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.RETURN_URL_EQ)
						.append(URLEncoder.encode(keypay.getReturn_url(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.VERSION_EQ)
						.append(URLEncoder.encode(keypay.getVersion(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.COMMAND_EQ)
						.append(URLEncoder.encode(keypay.getCommand(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.CURRENT_LOCALE_EQ)
						.append(URLEncoder.encode(keypay.getCurrent_locale(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.CURRENCY_CODE_EQ)
						.append(URLEncoder.encode(keypay.getCurrency_code(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.SERVICE_CODE_EQ)
						.append(URLEncoder.encode(keypay.getService_code(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.COUNTRY_CODE_EQ)
						.append(URLEncoder.encode(keypay.getCountry_code(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);

				param.append(KeyPayTerm.DESC_1_EQ)
						.append(URLEncoder.encode(keypay.getDesc_1(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.DESC_2_EQ)
						.append(URLEncoder.encode(keypay.getDesc_2(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.DESC_3_EQ)
						.append(URLEncoder.encode(keypay.getDesc_3(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.DESC_4_EQ)
						.append(URLEncoder.encode(keypay.getDesc_4(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.DESC_5_EQ)
						.append(URLEncoder.encode(keypay.getDesc_5(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.XML_DESCRIPTION_EQ)
						.append(URLEncoder.encode(keypay.getXml_description(), StandardCharsets.UTF_8.name()))
						.append(StringPool.AMPERSAND);
				param.append(KeyPayTerm.SECURE_HASH_EQ).append(keypay.getSecure_hash());

				result = epaymentConfigJSON.getString(KeyPayTerm.PAYMENTKEYPAYDOMAIN) + StringPool.QUESTION
						+ param.toString();

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
		address.append(dossier.getAddress());
		address.append(", ");
		address.append(dossier.getWardName());
		address.append(", ");
		address.append(dossier.getDistrictName());
		address.append(", ");
		address.append(dossier.getCityName());

		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		String dateformatted = sdf.format(new Date());
		_log.info("SONDT CINVOICE DATEFORMATED ============= " + dateformatted);

		params.put(Field.USER_NAME, StringPool.BLANK);
		params.put(CInvoiceTerm.secret, String.valueOf(1));
		params.put(CInvoiceTerm.soid, String.valueOf(0));
		params.put(CInvoiceTerm.maHoadon, StringPool.BLANK);
		params.put(CInvoiceTerm.ngayHd, dateformatted); //"01/08/2018"
		params.put(CInvoiceTerm.seri, String.valueOf(12314));
		params.put(CInvoiceTerm.maNthue, "01");
		params.put(CInvoiceTerm.kieuSo, "G");
		params.put(CInvoiceTerm.maKhackHang, Long.toString(dossier.getUserId()));
		params.put(CInvoiceTerm.ten, dossier.getApplicantName());
		params.put(CInvoiceTerm.phone, dossier.getContactTelNo());
		if (dossier.getApplicantIdType().contentEquals("business")) {
			params.put(CInvoiceTerm.tax, dossier.getApplicantIdNo());
		} else {
			params.put(CInvoiceTerm.tax, StringPool.BLANK);
		}
		params.put(CInvoiceTerm.dchi, address);
		params.put(CInvoiceTerm.maTk, StringPool.BLANK);
		params.put(CInvoiceTerm.tenNh, StringPool.BLANK);
		params.put(CInvoiceTerm.mailH, GetterUtil.getString(dossier.getContactEmail()));
		params.put(CInvoiceTerm.phoneH, GetterUtil.getString(dossier.getContactTelNo()));
		params.put(CInvoiceTerm.tenM, GetterUtil.getString(dossier.getDelegateName()));
		params.put(CInvoiceTerm.maKhL, "K");
		params.put(CInvoiceTerm.maNt, "VND");
		params.put(CInvoiceTerm.tg, String.valueOf(1));
		if (intpaymentMethod == 3) {
			params.put(CInvoiceTerm.hthuc, CInvoiceTerm.hthuc_M);
		} else {
			params.put(CInvoiceTerm.hthuc, CInvoiceTerm.hthuc_C);
		}
		params.put(CInvoiceTerm.han, StringPool.BLANK);
		params.put(CInvoiceTerm.tlGgia, String.valueOf(0));
		params.put(CInvoiceTerm.ggia, String.valueOf(0));
		params.put(CInvoiceTerm.phi, String.valueOf(0));
		params.put(CInvoiceTerm.noidung, dossier.getDossierNo());
		params.put(CInvoiceTerm.tien, Long.toString(oldPaymentFile.getPaymentAmount()));
		params.put(CInvoiceTerm.ttoan, Long.toString(oldPaymentFile.getPaymentAmount()));
		params.put(CInvoiceTerm.maVtDetail, dossier.getDossierNo());
		params.put(CInvoiceTerm.tenDetail, GetterUtil.getString(dossier.getServiceName()));
		params.put(CInvoiceTerm.dvtDetail, "bo");
		params.put(CInvoiceTerm.luongDetail, String.valueOf(1));
		params.put(CInvoiceTerm.giaDetail, Long.toString(oldPaymentFile.getPaymentAmount()));
		params.put(CInvoiceTerm.tienDetail, Long.toString(oldPaymentFile.getPaymentAmount()));
		params.put(CInvoiceTerm.tsDetail, String.valueOf(0));
		params.put(CInvoiceTerm.thueDetail, String.valueOf(0));
		params.put(CInvoiceTerm.ttoanDetail, Long.toString(oldPaymentFile.getPaymentAmount()));

		return params;
	}

	private int checkPaymentMethodinPrecondition(String preCondition) {
		int paymentMethod = 0;
		String[] preConditions = StringUtil.split(preCondition);
		for (String pre : preConditions) {
			pre = pre.trim();
			if (pre.toLowerCase().contains(KeyPayTerm.PAYMENTMETHOD_EQ)) {
				String[] splitPaymentMethod = pre.split(StringPool.EQUAL);
				if (splitPaymentMethod.length == 2) {
					paymentMethod = Integer.parseInt(splitPaymentMethod[1]);
				}
				break;
			}
		}
		return paymentMethod;
	}

	private String checkPaymentMethod(int mt) {
		String pmMethod = StringPool.BLANK;
		if (mt == 1) {
			pmMethod = KeyPayTerm.PM_METHOD_1; //KeyPay
		} else if (mt == 2) {
			pmMethod = KeyPayTerm.PM_METHOD_2;
		} else if (mt == 3) {
			pmMethod = KeyPayTerm.PM_METHOD_3;
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
			if (Validator.isNotNull(it)) {
				return it.getItemName();
			} else {
				return StringPool.BLANK;
			}
		} else {
			return StringPool.BLANK;
		}

	}

	private void updateStatus(Dossier dossier, String status, String statusText, String subStatus, String subStatusText,
							  String lockState, String stepInstruction, ServiceContext context) throws PortalException {

		Date now = new Date();

		dossier.setModifiedDate(now);

		dossier.setDossierStatus(status);
		dossier.setDossierStatusText(statusText);
		dossier.setDossierSubStatus(subStatus);
		dossier.setDossierSubStatusText(subStatusText);
		if (dossier != null && !DossierTerm.PAUSE_OVERDUE_LOCK_STATE.equals(dossier.getLockState())) {

			dossier.setLockState(lockState);
		}
		dossier.setDossierNote(stepInstruction);

		//		if (status.equalsIgnoreCase(DossierStatusConstants.RELEASING)) {
		//			dossier.setReleaseDate(now);
		//		}
		//
		//		if (status.equalsIgnoreCase(DossierStatusConstants.DONE)) {
		//			dossier.setFinishDate(now);
		//		}
	}

	private Map<String, Boolean> updateProcessingDate(DossierAction dossierAction, DossierAction prevAction,
			ProcessStep processStep, Dossier dossier, String curStatus, String curSubStatus, String prevStatus,
			int dateOption, ProcessOption option, ServiceProcess serviceProcess, String payload, ServiceContext context) {
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
				if (Validator.isNotNull(option) && Validator.isNull(dossier.getDossierNo())
						&& dossier.getOriginDossierId() > 0) {

					String dossierRef = DossierNumberGenerator.generateDossierNumber(dossier.getGroupId(),
							dossier.getCompanyId(), dossier.getDossierId(), option.getProcessOptionId(),
							serviceProcess.getDossierNoPattern(), params);

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
				&& DossierTerm.DOSSIER_STATUS_RECEIVING.equals(curStatus))
				|| (dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA
				&& DossierTerm.DOSSIER_STATUS_NEW.equals(curStatus))) {
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
		if (dossier.getOriginality() != DossierTerm.ORIGINALITY_DVCTT
				&& ((DossierTerm.DOSSIER_STATUS_PROCESSING.equals(curStatus)
				&& dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG)
				|| (DossierTerm.DOSSIER_STATUS_NEW.equals(curStatus)
				&& dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA)
				|| (DossierTerm.DOSSIER_STATUS_NEW.equals(curStatus)
				&& dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG)
				|| (dateOption == DossierTerm.DATE_OPTION_TWO))
				&& dossier.getReceiveDate() == null) {
			//			try {
			//				DossierLocalServiceUtil.updateReceivingDate(dossier.getGroupId(), dossier.getDossierId(), dossier.getReferenceUid(), now, context);

			dossier.setReceiveDate(now);
			bResult.put(DossierTerm.RECEIVE_DATE, true);

			Double durationCount = serviceProcess.getDurationCount();
			int durationUnit = serviceProcess.getDurationUnit();
			//Process after dueDate
			String dueDatePattern = serviceProcess.getDueDatePattern();
			JSONObject jsonDueDate = null;
			try {
				jsonDueDate = Validator.isNotNull(dueDatePattern) ? JSONFactoryUtil.createJSONObject(dueDatePattern) : null;
			} catch (JSONException e) {
				_log.debug(e);
			}
			List<Integer> dueHour = null;
			if (jsonDueDate != null) {
				JSONObject afterHours = jsonDueDate.getJSONObject(DossierDocumentTerm.AFTER_HOUR);
					if (afterHours != null && afterHours.has(DossierDocumentTerm.START_HOUR) && afterHours.has(DossierDocumentTerm.DUE_HOUR)) {
					//_log.info("STRART check new: ");
					Calendar receiveCalendar = Calendar.getInstance();
					receiveCalendar.setTime(now);
					//
					String receiveHour = afterHours.getString(DossierDocumentTerm.START_HOUR);
					//_log.info("receiveHour: " + receiveHour);

					if (Validator.isNotNull(receiveHour)) {
						String[] splitHour = StringUtil.split(receiveHour, StringPool.COLON);
						if (splitHour != null) {
							int hourStart = GetterUtil.getInteger(splitHour[0]);
							int minuteStart = GetterUtil.getInteger(splitHour[1]);
							if (receiveCalendar.get(Calendar.HOUR_OF_DAY) > hourStart
									|| (receiveCalendar.get(Calendar.HOUR_OF_DAY) == hourStart
											&& receiveCalendar.get(Calendar.MINUTE) > minuteStart)) {
								dueHour = new ArrayList<>();
								String[] splitdueHour = StringUtil.split(afterHours.getString(DossierDocumentTerm.DUE_HOUR),
										StringPool.COLON);
								if (splitdueHour != null) {
									dueHour.add(0, GetterUtil.getInteger(splitdueHour[0]));
									dueHour.add(1, GetterUtil.getInteger(splitdueHour[1]));
								}
							}
						}
					}
				}
			}

			Date dueDate = null;
			if (Validator.isNotNull(getDueDateByPayload(payload))) {
				dueDate = getDueDateByPayload(payload);
			} else if (Validator.isNotNull(durationCount) && durationCount > 0 && !areEqualDouble(durationCount, 0.00d, 3)) {
				// dueDate = HolidayUtils.getDueDate(now, durationCount, durationUnit, dossier.getGroupId());
				DueDateUtils dueDateUtils = new DueDateUtils(now, durationCount, durationUnit, dossier.getGroupId());
				dueDate = dueDateUtils.getDueDate();
			}

			if (Validator.isNotNull(dueDate)) {
				//dossier.setDueDate(dueDate);
				if (dueHour != null && dueHour.size() == 2) {
					Calendar dueCalendar = Calendar.getInstance();
					dueCalendar.setTime(dueDate);
					//
					dueCalendar.set(Calendar.HOUR_OF_DAY, dueHour.get(0));
					dueCalendar.set(Calendar.MINUTE, dueHour.get(1));
					//
					dossier.setDueDate(dueCalendar.getTime());
				} else {
					dossier.setDueDate(dueDate);
				}
				bResult.put(DossierTerm.DUE_DATE, true);
			}

			if (Validator.isNull(dossier.getDurationCount()))
				dossier.setDurationCount(durationCount);
			dossier.setDurationUnit(durationUnit);

			if (dossier.getCounter() == 0 && Validator.isNotNull(dossier.getRegisterBookCode())) {
				long counterCode = DossierNumberGenerator.countByRegiterBookCode(dossier.getGroupId(),
						dossier.getRegisterBookCode(), dossier.getGovAgencyCode());
				dossier.setCounter(counterCode);
			}

			if (Validator.isNull(dossier.getDossierNo())) {
				try {
					String dossierRef = DossierNumberGenerator.generateDossierNumber(dossier.getGroupId(),
							dossier.getCompanyId(), dossier.getDossierId(), option.getProcessOptionId(),
							serviceProcess.getDossierNoPattern(), params);
					dossier.setDossierNo(dossierRef.trim());
				} catch (Exception e) {
					_log.debug(e);
				}
			}
			if (Validator.isNull(dossier.getDossierCounter()) && Validator.isNotNull(serviceProcess.getCounterCode())) {
				ConfigCounter counterConfig = ConfigCounterLocalServiceUtil.fetchByCountrCode(dossier.getGroupId(),
						serviceProcess.getCounterCode());
				if (counterConfig != null) {
					if (Validator.isNotNull(counterConfig.getStartCounter()) && counterConfig.getStartCounter() > 0) {
						String patternCode = counterConfig.getPatternCode();
						try {
							_log.info("dossierId: " + dossier.getDossierId() + "| option.getProcessOptionId(): "
									+ option.getProcessOptionId() + "| serviceProcess.getCounterCode(): "
									+ serviceProcess.getCounterCode() + "| getStartCounter(): "
									+ counterConfig.getStartCounter());
							String dossierCounter = ConfigCounterNumberGenerator.generateCounterNumber(
									dossier.getGroupId(), dossier.getCompanyId(), dossier.getDossierId(),
									option.getProcessOptionId(), patternCode, counterConfig, params);
							dossier.setDossierCounter(dossierCounter.trim());
							_log.info("dossierCounter: " + dossierCounter);
						} catch (Exception e) {
							_log.debug(e);
						}
					} else {
						String patternCode = counterConfig.getPatternCode();
						try {
							_log.info("dossierId: " + dossier.getDossierId() + "| option.getProcessOptionId(): "
									+ option.getProcessOptionId() + "| serviceProcess.getCounterCode(): "
									+ serviceProcess.getCounterCode());
							String dossierCounter = ConfigCounterNumberGenerator.generateCounterNumber(
									dossier.getGroupId(), dossier.getCompanyId(), dossier.getDossierId(),
									option.getProcessOptionId(), patternCode, counterConfig, params);
							dossier.setDossierCounter(dossierCounter.trim());
							_log.info("dossierCounter: " + dossierCounter);
						} catch (Exception e) {
							_log.debug(e);
						}
					}
				}
			}
		}

		//Update counter and dossierNo
		if (dateOption == DossierTerm.DATE_OPTION_TWO || dateOption == DossierTerm.DATE_OPTION_TEN) {

			if (dossier.getCounter() == 0 && Validator.isNotNull(dossier.getRegisterBookCode())) {
				long counterCode = DossierNumberGenerator.countByRegiterBookCode(dossier.getGroupId(),
						dossier.getRegisterBookCode(), dossier.getGovAgencyCode());
				dossier.setCounter(counterCode);
			}

			if (Validator.isNull(dossier.getDossierNo())) {
				try {
					String dossierRef = DossierNumberGenerator.generateDossierNumber(dossier.getGroupId(),
							dossier.getCompanyId(), dossier.getDossierId(), option.getProcessOptionId(),
							serviceProcess.getDossierNoPattern(), params);
					dossier.setDossierNo(dossierRef.trim());
				} catch (Exception e) {
					_log.debug(e);
				}
			}
			if (Validator.isNull(dossier.getDossierCounter()) && Validator.isNotNull(serviceProcess.getCounterCode())) {
				ConfigCounter counterConfig = ConfigCounterLocalServiceUtil.fetchByCountrCode(dossier.getGroupId(),
						serviceProcess.getCounterCode());
				if (counterConfig != null) {
					if (Validator.isNotNull(counterConfig.getStartCounter()) && counterConfig.getStartCounter() > 0) {
						String patternCode = counterConfig.getPatternCode();
						try {
							_log.info("dossierId: " + dossier.getDossierId() + "| option.getProcessOptionId(): "
									+ option.getProcessOptionId() + "| serviceProcess.getCounterCode(): "
									+ serviceProcess.getCounterCode() + "| getStartCounter(): "
									+ counterConfig.getStartCounter());
							String dossierCounter = ConfigCounterNumberGenerator.generateCounterNumber(
									dossier.getGroupId(), dossier.getCompanyId(), dossier.getDossierId(),
									option.getProcessOptionId(), patternCode, counterConfig, params);
							dossier.setDossierCounter(dossierCounter.trim());
							_log.info("dossierCounter: " + dossierCounter);
						} catch (Exception e) {
							_log.debug(e);
						}
					} else {
						String patternCode = counterConfig.getPatternCode();
						try {
							_log.info("dossierId: " + dossier.getDossierId() + "| option.getProcessOptionId(): "
									+ option.getProcessOptionId() + "| serviceProcess.getCounterCode(): "
									+ serviceProcess.getCounterCode());
							String dossierCounter = ConfigCounterNumberGenerator.generateCounterNumber(
									dossier.getGroupId(), dossier.getCompanyId(), dossier.getDossierId(),
									option.getProcessOptionId(), patternCode, counterConfig, params);
							dossier.setDossierCounter(dossierCounter.trim());
							_log.info("dossierCounter: " + dossierCounter);
						} catch (Exception e) {
							_log.debug(e);
						}
					}
				}
			}
		}

		if (DossierTerm.DOSSIER_STATUS_RECEIVING.equals(prevStatus)
				&& DossierTerm.DOSSIER_STATUS_PROCESSING.equals(curStatus)) {
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
				|| DossierTerm.DOSSIER_STATUS_CANCELLED.equals(curStatus) || (dateOption == 4)) {
			if (Validator.isNull(dossier.getReleaseDate())) {
				//				try {
				//					DossierLocalServiceUtil.updateReleaseDate(dossier.getGroupId(), dossier.getDossierId(), dossier.getReferenceUid(), now, context);
				dossier.setReleaseDate(now);
				if (OpenCPSConfigUtil.isAutoBetimes()) {
					int valueCompareRelease = BetimeUtils.getValueCompareRelease(dossier.getGroupId(), now,
							dossier.getDueDate());
					if (3 == valueCompareRelease) {
						dossier.setExtendDate(now);
					}
				}
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
			dossierAction = dossierActionLocalService.updateState(dossierAction.getDossierActionId(),
					DossierActionTerm.STATE_ALREADY_PROCESSED);
		}

		//Check verification
		if (DossierTerm.DOSSIER_STATUS_DONE.contentEquals(curStatus)) {
			Applicant checkApplicant = dossier.getUserId() > 0 ? ApplicantLocalServiceUtil.fetchByMappingID(dossier.getUserId()) : null;
			if (checkApplicant != null && dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT) {
				if (checkApplicant.getVerification() == ApplicantTerm.LOCKED
						|| checkApplicant.getVerification() == ApplicantTerm.LOCKED_DOSSIER) {
					checkApplicant.setVerification(ApplicantTerm.UNLOCKED);
					ApplicantLocalServiceUtil.updateApplicant(checkApplicant);
				}
			}
		}

		//		if (DossierTerm.DOSSIER_STATUS_DENIED.equals(curStatus)
		//				|| DossierTerm.DOSSIER_STATUS_UNRESOLVED.equals(curStatus)
		//				|| DossierTerm.DOSSIER_STATUS_CANCELLED.equals(curStatus)
		//				|| DossierTerm.DOSSIER_STATUS_DONE.equals(curStatus)) {
		if (DossierTerm.DOSSIER_STATUS_DENIED.equals(curStatus) || DossierTerm.DOSSIER_STATUS_DONE.equals(curStatus)
				|| DossierTerm.DOSSIER_STATUS_UNRESOLVED.equals(curStatus)
				|| DossierTerm.DOSSIER_STATUS_CANCELLED.equals(curStatus) || (dateOption == 5)) {
			if (Validator.isNull(dossier.getFinishDate())) {
				//				try {
				//					DossierLocalServiceUtil.updateFinishDate(dossier.getGroupId(), dossier.getDossierId(), dossier.getReferenceUid(), now, context);
				dossier.setFinishDate(now);
				bResult.put(DossierTerm.FINISH_DATE, true);
				//					dossierAction.setState(DossierActionTerm.STATE_ALREADY_PROCESSED);
				//					dossierAction.setModifiedDate(new Date());
				dossierAction = dossierActionLocalService.updateState(dossierAction.getDossierActionId(),
						DossierActionTerm.STATE_ALREADY_PROCESSED);
				//				} catch (PortalException e) {
				//					_log.error(e);
				//					e.printStackTrace();
				//				}
			}
			if (Validator.isNull(dossier.getReleaseDate())) {
				//				try {
				//					DossierLocalServiceUtil.updateReleaseDate(dossier.getGroupId(), dossier.getDossierId(), dossier.getReferenceUid(), now, context);
				dossier.setReleaseDate(now);
				if (OpenCPSConfigUtil.isAutoBetimes()) {
					int valueCompareRelease = BetimeUtils.getValueCompareRelease(dossier.getGroupId(), now,
							dossier.getDueDate());
					if (3 == valueCompareRelease) {
						dossier.setExtendDate(now);
					}
				}
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
		if (DossierTerm.DOSSIER_STATUS_NEW.equals(prevStatus)
				&& dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG
				&& Validator.isNotNull(dossier.getReceiveDate())) {
			bResult.put(DossierTerm.RECEIVE_DATE, true);
		}

		if (DossierTerm.DOSSIER_STATUS_RECEIVING.contentEquals(dossier.getDossierStatus())
				&& dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT) {
			Applicant checkApplicant = dossier.getUserId() > 0 ? ApplicantLocalServiceUtil.fetchByMappingID(dossier.getUserId()) : null;
			if (checkApplicant != null) {
				int countDossier = DossierLocalServiceUtil.countByG_UID_DS(dossier.getGroupId(), dossier.getUserId(),
						DossierTerm.DOSSIER_STATUS_RECEIVING);
				_log.debug("APPLICANT NUMBER OF CREATE DOSSIER: " + countDossier);
				if (countDossier >= DossierTerm.MAX_DOSSIER_WITHOUT_VERIFICATION) {
					if (checkApplicant.getVerification() != ApplicantTerm.UNLOCKED) {
						checkApplicant.setVerification(ApplicantTerm.LOCKED_DOSSIER);
						ApplicantLocalServiceUtil.updateApplicant(checkApplicant);
					}
				}
			}
		}
		//int dateOption = actionConfig.getDateOption();
		_log.debug("dateOption: " + dateOption);
		if (dateOption == DossierTerm.DATE_OPTION_CAL_WAITING) {
			DossierAction dActEnd = dossierActionLocalService.fetchDossierAction(dossierAction.getDossierActionId());
			//			DossierAction dActEnd = dossierAction;
			if (dActEnd != null && dossier.getDurationCount() > 0) {
				_log.debug("dActEnd.getPreviousActionId(): " + dActEnd.getPreviousActionId());
				DossierAction dActPrevious = dossierActionLocalService
						.fetchDossierAction(dActEnd.getPreviousActionId());
				//				DossierAction dActStart = prevAction;
				if (dActPrevious != null) {
					ActionConfig actPrevious = ActionConfigLocalServiceUtil.getByCode(dActPrevious.getGroupId(),
							dActPrevious.getActionCode());
					_log.debug("actPrevious: " + actPrevious.getDateOption());
					long createEnd = dActEnd.getCreateDate().getTime();
					long createStart = 0;
					if (actPrevious != null && actPrevious.getDateOption() != 1) {
						createStart = dActPrevious.getCreateDate().getTime();
					} else {
						List<DossierAction> dActionList = DossierActionLocalServiceUtil
								.findByG_DID(dActEnd.getGroupId(), dActEnd.getDossierId());
						if (dActionList != null && dActionList.size() > 1) {
							int lengthAction = dActionList.size();
							for (int i = lengthAction - 2; i >= 0; i--) {
								DossierAction dAction = dActionList.get(i);
								_log.debug("dAction: " + i + ": " + dAction);
								ActionConfig actDetail = ActionConfigLocalServiceUtil.getByCode(dAction.getGroupId(),
										dAction.getActionCode());
								_log.debug("actDetail: " + i + ": " + actDetail.getDateOption());
								if (actDetail.getDateOption() == 1) {
									createStart = dAction.getCreateDate().getTime();
								} else {
									break;
								}
							}
						}
					}

					_log.debug("createStart: " + createStart);
					_log.debug("createEnd: " + createEnd);
					if (createEnd > createStart) {
						DueDateUtils dueDateUtils = new DueDateUtils(new Date(createStart), new Date(createEnd), 1,
								dActEnd.getGroupId());
						//long extendDateTimeStamp = ExtendDueDateUtils.getTimeWaitingByHoliday(createStart, createEnd, dossier.getGroupId());
						long extendDateTimeStamp = dueDateUtils.getOverDue();
						_log.debug("extendDateTimeStamp: " + extendDateTimeStamp);
						if (extendDateTimeStamp > 0) {
							double hoursCount = extendDateTimeStamp * 1.0 / (1000 * 60 * 60);
							_log.debug("hoursCount: " + hoursCount);
							//_log.info("dossier.getExtendDate(): "+dossier.getExtendDate());
							//							List<Holiday> holidayList = HolidayLocalServiceUtil
							//									.getHolidayByGroupIdAndType(dossier.getGroupId(), 0);
							//							List<Holiday> extendWorkDayList = HolidayLocalServiceUtil
							//									.getHolidayByGroupIdAndType(dossier.getGroupId(), 1);

							//Date dueDateExtend = HolidayUtils.getEndDate(dossier.getGroupId(),
							//		dossier.getDueDate(), hoursCount, holidayList,
							//		extendWorkDayList);
							//
							DueDateUtils dateUtils = new DueDateUtils(dossier.getDueDate(), hoursCount, 1,
									dossier.getGroupId());
							Date dueDateExtend = dateUtils.getDueDate();

							_log.debug("dueDateExtend: " + dueDateExtend);
							if (dueDateExtend != null) {
								dossier.setDueDate(dueDateExtend);
								//dossier.setCorrecttingDate(null);
								bResult.put(DossierTerm.DUE_DATE, true);
							}
						}
					}
				}
			} else if (dossier.getDurationCount() <= 0) {
				dossier.setDueDate(null);
			}
			if (Validator.isNotNull(getDueDateByPayload(payload))) {
				dossier.setDueDate(getDueDateByPayload(payload));
			}
			// Cập nhật ngày tiếp nhận cuối cùng cho hồ sơ
//			dossier.setLastSendDate(new Date());
			dossier.setLastReceiveDate(new Date());
			_log.debug("Log LastReiveDate " + new Date());
		} else if (dateOption == DossierTerm.DATE_OPTION_CHANGE_DUE_DATE) {
			if (dossier.getDueDate() != null) {
				//dossier.setCorrecttingDate(dossier.getDueDate());
				//dossier.setDueDate(null);
				dossier.setLockState(DossierTerm.PAUSE_STATE);
			}
		} else if (dateOption == DossierTerm.DATE_OPTION_RESET_DUE_DATE) {
			if (dossier != null && !DossierTerm.PAUSE_OVERDUE_LOCK_STATE.equals(dossier.getLockState())) {

				dossier.setLockState(StringPool.BLANK);
			}
			if (Validator.isNotNull(getDueDateByPayload(payload))) {
				dossier.setDueDate(getDueDateByPayload(payload));
			} else if (dossier.getDueDate() != null && serviceProcess != null) {
//				Date newDueDate = HolidayUtils.getDueDate(new Date(),
				//							serviceProcess.getDurationCount(),
				//							serviceProcess.getDurationUnit(), dossier.getGroupId());
				DueDateUtils dueDateUtils = new DueDateUtils(new Date(), serviceProcess.getDurationCount(),
						serviceProcess.getDurationUnit(), dossier.getGroupId());
				Date newDueDate = dueDateUtils.getDueDate();
				if (newDueDate != null) {
					//dossier.setReceiveDate(new Date());
					dossier.setDueDate(newDueDate);
					bResult.put(DossierTerm.DUE_DATE, true);
				}
			}
			// Cập nhật ngày tiếp nhận cuối cùng cho hồ sơ
//			dossier.setLastSendDate(new Date());
			dossier.setLastReceiveDate(new Date());
			_log.debug("Log LastReiveDate " + new Date());
		} else if (dateOption == DossierTerm.DATE_OPTION_PAUSE_OVERDUE) {
			if (dossier.getDueDate() != null) {
				dossier.setLockState(DossierTerm.PAUSE_OVERDUE_LOCK_STATE);
			}
		} else if ((dateOption == DossierTerm.DATE_OPTION_DUEDATE_PHASE_1
				|| dateOption == DossierTerm.DATE_OPTION_DUEDATE_PHASE_2
				|| dateOption == DossierTerm.DATE_OPTION_DUEDATE_PHASE_3) && serviceProcess != null) {

			if (!DossierTerm.PAUSE_OVERDUE_LOCK_STATE.equals(dossier.getLockState())) {

				dossier.setLockState(StringPool.BLANK);
			}

			if (Validator.isNotNull(getDueDateByPayload(payload))) {
				Date dueDate = getDueDateByPayload(payload);
				dossier.setDueDate(dueDate);
				String metadata = getDossierMetaKeyDateOption(dossier, dueDate, getReceiveDateByPayload(payload), getDurationByPayload(payload), dateOption, serviceProcess.getDueDatePattern());
				dossier.setMetaData(metadata);
				bResult.put(DossierTerm.META_DATA, true);
				bResult.put(DossierTerm.DUE_DATE, true);
			} else {
				DueDatePhaseUtil dueDatePharse = new DueDatePhaseUtil(dossier.getGroupId(), new Date(), dateOption,
						serviceProcess.getDueDatePattern());
				dossier.setDueDate(dueDatePharse.getDueDate());
				String metadata = getDossierMetaKeyDateOption(dossier, dueDatePharse.getDueDate(), dueDatePharse.getReceiveDate(), dueDatePharse.getDuration(), dateOption, serviceProcess.getDueDatePattern());
				dossier.setMetaData(metadata);
				bResult.put(DossierTerm.META_DATA, true);
				bResult.put(DossierTerm.DUE_DATE, true);
			}
			dossier = setDossierNoNDueDate(dossier, serviceProcess, option, true, false, null, params);
		} else //Update counter and dossierNo
			if (dateOption == DossierTerm.DATE_OPTION_TEN) {

				/**
				 * THANHNV create common init DueDate DossierNo DossierCounter

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
				 if (Validator.isNull(dossier.getDossierCounter()) && Validator.isNotNull(serviceProcess.getCounterCode())) {
				 ConfigCounter counterConfig = ConfigCounterLocalServiceUtil.fetchByCountrCode(dossier.getGroupId(),
				 serviceProcess.getCounterCode());
				 if (counterConfig != null) {
				 if (Validator.isNotNull(counterConfig.getStartCounter()) && counterConfig.getStartCounter() > 0) {
				 String patternCode = counterConfig.getPatternCode();
				 try {
				 _log.info("dossierId: " + dossier.getDossierId() + "| option.getProcessOptionId(): "
				 + option.getProcessOptionId() + "| serviceProcess.getCounterCode(): "
				 + serviceProcess.getCounterCode() + "| getStartCounter(): "
				 + counterConfig.getStartCounter());
				 String dossierCounter = ConfigCounterNumberGenerator.generateCounterNumber(dossier.getGroupId(),
				 dossier.getCompanyId(), dossier.getDossierId(), option.getProcessOptionId(),
				 patternCode, counterConfig, params);
				 dossier.setDossierCounter(dossierCounter.trim());
				 _log.info("dossierCounter: " + dossierCounter);
				 } catch (Exception e) {
				 _log.debug(e);
				 }
				 } else {
				 String patternCode = counterConfig.getPatternCode();
				 try {
				 _log.info("dossierId: " + dossier.getDossierId() + "| option.getProcessOptionId(): "
				 + option.getProcessOptionId() + "| serviceProcess.getCounterCode(): "
				 + serviceProcess.getCounterCode());
				 String dossierCounter = ConfigCounterNumberGenerator.generateCounterNumber(dossier.getGroupId(),
				 dossier.getCompanyId(), dossier.getDossierId(), option.getProcessOptionId(),
				 patternCode, counterConfig, params);
				 dossier.setDossierCounter(dossierCounter.trim());
				 _log.info("dossierCounter: " + dossierCounter);
				 } catch (Exception e) {
				 _log.debug(e);
				 }
				 }
				 }
				 }
				 THANHNV: end
				 */
				dossier = setDossierNoNDueDate(dossier, serviceProcess, option, true, false, null, params);
			} else if(dateOption == DossierTerm.DATE_OPTION_TWO) {
				if (Validator.isNotNull(getDueDateByPayload(payload))) {
					dossier.setDueDate(getDueDateByPayload(payload));
				} else {
					dossier = setDossierNoNDueDate(dossier, serviceProcess, option, true, Validator.isNull(dossier.getDueDate()), dossier.getReceiveDate(), params);
				}
			}

		//Check if dossier is done
		if (DossierTerm.DOSSIER_STATUS_DONE.equals(curStatus)) {
			List<DossierFile> lstFiles = dossierFileLocalService.getAllDossierFile(dossier.getDossierId());
			int countTemplateNo = 0;

			for (DossierFile df : lstFiles) {
				//GS. Ta Tuan Anh
				if (!df.getRemoved()) {
					df.setOriginal(true);
				}
				dossierFileLocalService.updateDossierFile(df);

				//GS. DuanTV ApplicantData
				if (Validator.isNotNull(df.getFileTemplateNo())) {
					countTemplateNo++;
				}
			}
			String[] fileTemplateNos = new String[countTemplateNo];
			DossierFile[] files = new DossierFile[countTemplateNo];

			int count = 0;
			for (DossierFile df : lstFiles) {
				if (Validator.isNotNull(df.getFileTemplateNo())) {
					files[count] = df;
					fileTemplateNos[count++] = df.getFileTemplateNo();
				}
			}

			List<FileItem> lstFileItems = FileItemLocalServiceUtil.findByG_FTNS(dossier.getGroupId(), fileTemplateNos);
			for (int i = 0; i < lstFileItems.size(); i++) {
				FileItem item = lstFileItems.get(i);
				try {
					ApplicantDataLocalServiceUtil.updateApplicantData(context, dossier.getGroupId(),
							item.getFileTemplateNo(), files[i].getDisplayName(), files[i].getFileEntryId(),
							StringPool.BLANK, ApplicantDataTerm.STATUS_ACTIVE, dossier.getApplicantIdNo(), 1,
							dossier.getDossierNo(), StringPool.BLANK);
				} catch (SystemException e) {
					_log.debug(e);
				} catch (PortalException e) {
					_log.debug(e);
				}
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
		if (Validator.isNotNull(durationCount) && durationCount > 0 && !areEqualDouble(durationCount, 0.00d, 3)) {
			//			_log.info("========STEP DUE DATE CACULATE DUE DATE");
			DueDateUtils dueDateUtils = new DueDateUtils(rootDate, durationCount, durationUnit, dossier.getGroupId());
			// dueDate = HolidayUtils.getDueDate(rootDate, durationCount, durationUnit, dossier.getGroupId());
			dueDate = dueDateUtils.getDueDate();
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
			} else {
				dossierActionLocalService.updateDossierAction(dossierAction);
			}
		}

		// create submit date
		if(dateOption == DossierTerm.CREATE_SUBMIT_DATE){
			if (Validator.isNull(dossier.getSubmitDate())) {
				dossier.setSubmitDate(new Date());
			}
			// Cập nhật ngày gửi theo submitDate
			if(Validator.isNotNull(dossier.getSubmitDate())){
				dossier.setLastSendDate(dossier.getSubmitDate());
			}else{
				dossier.setLastSendDate(new Date());
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
			return (int) overDue;
		} else {
			dueCount = (double) subTimeStamp / VALUE_CONVERT_HOUR_TIMESTAMP;
			overDue = (double) Math.round(dueCount);
		}

		return (int) overDue;
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
		jsonData.put(DossierTerm.APPLICANT_NOTE, dossier.getApplicantNote());
		jsonData.put(DossierTerm.DOSSIER_COUNTER, dossier.getDossierCounter());

		// MetaData
		String metaData = dossier.getMetaData();
		if (Validator.isNotNull(metaData)) {
			try {
				JSONObject jsonMetaData = JSONFactoryUtil.createJSONObject(metaData);
				//
				Iterator<String> keys = jsonMetaData.keys();

				while (keys.hasNext()) {
					String key = keys.next();
					String value = jsonMetaData.getString(key);
					if (Validator.isNotNull(value)) {
						try {
							JSONArray valueObject = JSONFactoryUtil.createJSONArray(value);
							jsonData.put(key, valueObject);
						} catch (JSONException e) {
							_log.debug(e);
							try {
								JSONObject valueObject = JSONFactoryUtil.createJSONObject(value);
								jsonData.put(key, valueObject);
							} catch (JSONException e1) {
								_log.debug(e1);
								jsonData.put(key, value);
							}
						}

					}
				}
			} catch (JSONException e) {
				_log.debug(e);
			}
		}

		try {
			ServiceInfo service = ServiceInfoLocalServiceUtil.getByCode(dossier.getGroupId(), dossier.getServiceCode());
			jsonData.put(ServiceInfoTerm.FEE_TEXT, service != null ? service.getFeeText() : StringPool.BLANK);
			jsonData.put(ServiceInfoTerm.DURATION_TEXT, service != null ? service.getDurationText() : StringPool.BLANK);
		} catch (PortalException e1) {
			_log.debug(e1);
			jsonData.put(ServiceInfoTerm.FEE_TEXT, StringPool.BLANK);
			jsonData.put(ServiceInfoTerm.DURATION_TEXT, StringPool.BLANK);
		}
		//
		Date dueDate = dossier.getDueDate();
		if (dueDate != null) {
			ServiceProcess process = ServiceProcessLocalServiceUtil.getByG_PNO(dossier.getGroupId(),
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
							JSONObject hours = jsonDueDate.getJSONObject(KeyPayTerm.HOUR);
							JSONObject processHours = jsonDueDate.getJSONObject(KeyPayTerm.PROCESSHOUR);
							//_log.info("hours: " + hours);
							if (hours != null && hours.has(KeyPayTerm.AM) && hours.has(KeyPayTerm.PM)) {
								//_log.info("AM-PM: ");
								Calendar receiveCalendar = Calendar.getInstance();
								receiveCalendar.setTime(dossier.getReceiveDate());

								Calendar dueCalendar = Calendar.getInstance();
								//_log.info("hours: " + receiveCalendar.get(Calendar.HOUR_OF_DAY));
								if (receiveCalendar.get(Calendar.HOUR_OF_DAY) < 12) {
									dueCalendar.setTime(dossier.getDueDate());

									String hoursAfterNoon = hours.getString(KeyPayTerm.AM);
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
									String hoursAfterNoon = hours.getString(KeyPayTerm.PM);
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
							} else if (processHours != null && processHours.has(KeyPayTerm.STARTHOUR)
									&& processHours.has(KeyPayTerm.DUEHOUR)) {
								//_log.info("STRART check new: ");
								Calendar receiveCalendar = Calendar.getInstance();
								receiveCalendar.setTime(dossier.getReceiveDate());
								//
								String receiveHour = processHours.getString(KeyPayTerm.STARTHOUR);
								//_log.info("receiveHour: " + receiveHour);

								if (Validator.isNotNull(receiveHour)) {
									String[] splitHour = StringUtil.split(receiveHour, StringPool.COLON);
									if (splitHour != null) {
										int hourStart = GetterUtil.getInteger(splitHour[0]);
										if (receiveCalendar.get(Calendar.HOUR_OF_DAY) < hourStart) {
											String[] splitdueHour = StringUtil.split(
													processHours.getString(KeyPayTerm.DUEHOUR), StringPool.COLON);
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
		List<DossierMark> dossierMarkList = DossierMarkLocalServiceUtil.getDossierMarksByFileMark(groupId, dossierId,
				0);
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
						jsonMark.put(DossierPartTerm.PART_NAME_TITLE, part.getPartNameTitle());
					}
				}
				jsonMark.put(DossierPartTerm.PART_NO, partNo);
				jsonMark.put(DossierPartTerm.FILE_MARK, dossierMark.getFileMark());
				jsonMark.put(DossierPartTerm.FILE_CHECK, dossierMark.getFileCheck());
				jsonMark.put(DossierPartTerm.FILE_COMMENT, dossierMark.getFileComment());
				jsonMark.put(DossierPartTerm.RECORD_COUNT, dossierMark.getRecordCount());
				//				String strDossierMark = JSONFactoryUtil.looseSerialize(dossierMark);
				dossierMarkArr.put(jsonMark);
			}
		}

		//Hot fix TP99
		DossierMark dossierMark = DossierMarkLocalServiceUtil.getDossierMarkbyDossierId(groupId, dossierId,
				KeyPayTerm.TP99);
		if (dossierMark != null) {
			JSONObject jsonMark = null;
			String partNo = dossierMark.getDossierPartNo();
			if (Validator.isNotNull(partNo)) {
				List<DossierFile> fileList = DossierFileLocalServiceUtil.getDossierFileByDID_DPNO(dossierId, partNo,
						false);
				DossierPart part = DossierPartLocalServiceUtil.getByTempAndPartNo(groupId, templateNo, partNo);
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
						jsonMark.put(DossierPartTerm.RECORD_COUNT, dossierMark.getRecordCount());
						//						String strDossierMark = JSONFactoryUtil.looseSerialize(dossierMark);
						dossierMarkArr.put(jsonMark);
					}
				}
			}
		}

		jsonData.put(DossierTerm.DOSSIER_MARKS, dossierMarkArr);

		PaymentFile payment = PaymentFileLocalServiceUtil.getByDossierId(groupId, dossierId);
		if (payment != null) {
			jsonData.put(PaymentFileTerm.ADVANCE_AMOUNT, payment.getAdvanceAmount());
			jsonData.put(PaymentFileTerm.PAYMENT_AMOUNT, payment.getPaymentAmount());
			jsonData.put(PaymentFileTerm.PAYMENT_FEE, payment.getPaymentFee());
			jsonData.put(PaymentFileTerm.SERVICE_AMOUNT, payment.getServiceAmount());
			jsonData.put(PaymentFileTerm.SHIP_AMOUNT, payment.getShipAmount());
		}

		if (dossier.getOriginality() == DossierTerm.ORIGINALITY_HOSONHOM) {
			JSONArray groupDossierArr = JSONFactoryUtil.createJSONArray();
			List<Dossier> lstDossiers = DossierLocalServiceUtil.findByG_GDID(groupId, String.valueOf(dossier.getDossierId()));
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
		DictCollection govCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(GOVERNMENT_AGENCY,
				dossier.getGroupId());
		if (govCollection != null) {
			DictItem govAgenItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(dossier.getGovAgencyCode(),
					govCollection.getDictCollectionId(), dossier.getGroupId());
			String metaDataItem = govAgenItem.getMetaData();
			try {
				JSONObject metaObj = JSONFactoryUtil.createJSONObject(metaDataItem);
				if (govAgenItem != null) {
					if (metaObj.has(BN_TELEPHONE)) {
						jsonData.put(BN_TELEPHONE, metaObj.getString(BN_TELEPHONE));
					}
					if (metaObj.has(BN_EMAIL)) {
						jsonData.put(BN_EMAIL, metaObj.getString(BN_EMAIL));
					}
					if (metaObj.has(BN_ADDRESS)) {
						jsonData.put(BN_ADDRESS, metaObj.getString(BN_ADDRESS));
					}
				}
			} catch (Exception e) {
				_log.debug(e);
			}
		}

		if (jsonData.has(DossierTerm.EXTEND_DATE) && Validator.isNotNull(jsonData.get(DossierTerm.EXTEND_DATE))
				&& GetterUtil.getLong(jsonData.get(DossierTerm.EXTEND_DATE)) > 0) {
			Date extendDate = new Date(GetterUtil.getLong(jsonData.get(DossierTerm.EXTEND_DATE)));
			jsonData.put(DossierTerm.EXTEND_DATE,
					APIDateTimeUtils.convertDateToString(extendDate, APIDateTimeUtils._NORMAL_DATE_TIME));
		}

		//Notarization
		ServiceInfo si;
		try {
			si = ServiceInfoLocalServiceUtil.getByCode(dossier.getGroupId(), dossier.getServiceCode());
			if (si != null) {
				jsonData.put(ServiceInfoTerm.IS_NOTARIZATION, si.isIsNotarization());
				jsonData.put(ServiceInfoTerm.SERVICE_NAME_TITLE, si.getServiceNameTitle());
			} else {
				jsonData.put(ServiceInfoTerm.IS_NOTARIZATION, false);
				jsonData.put(ServiceInfoTerm.SERVICE_NAME_TITLE, StringPool.BLANK);
			}
		} catch (PortalException e) {
			_log.debug(e);
		}

		List<Notarization> lstNotarizations = NotarizationLocalServiceUtil.findByG_DID(groupId, dossierId);
		JSONArray notarizationArr = JSONFactoryUtil.createJSONArray();
		for (Notarization nt : lstNotarizations) {
			JSONObject notObj = JSONFactoryUtil.createJSONObject();
			notObj.put(NotarizationTerm.NOTARIZATION_ID, nt.getNotarizationId());
			notObj.put(NotarizationTerm.DOSSIER_ID, nt.getDossierId());
			notObj.put(NotarizationTerm.GROUP_ID, nt.getGroupId());
			notObj.put(NotarizationTerm.FILE_NAME, nt.getFileName());
			notObj.put(NotarizationTerm.NOTARIZATION_DATE,
					nt.getNotarizationDate() != null
							? APIDateTimeUtils.convertDateToString(nt.getNotarizationDate(),
							APIDateTimeUtils._NORMAL_PARTTERN)
							: StringPool.BLANK);
			notObj.put(NotarizationTerm.NOTARIZATION_NO, nt.getNotarizationNo());
			notObj.put(NotarizationTerm.NOTARIZATION_YEAR, nt.getNotarizationYear());
			notObj.put(NotarizationTerm.SIGNER_NAME, nt.getSignerName());
			notObj.put(NotarizationTerm.SIGNER_POSITION, nt.getSignerPosition());
			notObj.put(NotarizationTerm.STATUS_CODE, nt.getStatusCode());
			notObj.put(NotarizationTerm.TOTAL_COPY, nt.getTotalCopy());
			notObj.put(NotarizationTerm.TOTAL_FEE, nt.getTotalFee());
			notObj.put(NotarizationTerm.TOTAL_PAGE, nt.getTotalPage());
			notObj.put(NotarizationTerm.TOTAL_RECORD, nt.getTotalRecord());

			notarizationArr.put(notObj);
		}
		jsonData.put(DossierTerm.NOTARIZATIONS, notarizationArr);

		// Meta data
		if (Validator.isNotNull(dossier.getMetaData())) {
			try {
				jsonData.put(DossierTerm.META_DATA, JSONFactoryUtil.createJSONObject(dossier.getMetaData()));
			} catch (JSONException e) {
				jsonData.put(DossierTerm.META_DATA, StringPool.BLANK);
			}
		} else {
			jsonData.put(DossierTerm.META_DATA, StringPool.BLANK);
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
			//List<ProcessSequence> sequenceList = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId,
			//		serviceProcessId);
			//TODO: Using cache
			//				List<ProcessSequence> sequenceList = null;
			//				Serializable data = CacheLocalServiceUtil.getFromCache("ProcessSequence", groupId +"_"+serviceProcessId);
			//				if (data != null) {
			//					sequenceList = (List<ProcessSequence>) data;
			//				} else {
			//					sequenceList = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId,
			//							serviceProcessId);
			//					if (sequenceList != null) {
			//						//_log.info("START_ Serlist null");
			//						CacheLocalServiceUtil.addToCache("ProcessSequence",
			//								groupId +"_"+serviceProcessId, (Serializable) sequenceList,
			//								(int) Time.MINUTE * 30);
			//					}
			//				}
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
			//Process array sequence
			JSONArray jsonSequenceArr = getProcessSequencesJSON(sequenceArr, sequenceList);
			if (jsonSequenceArr != null) {
				jsonData.put(KeyPayTerm.processSequenceArr, jsonSequenceArr);
			}
		}

		return jsonData;
	}

	private static JSONArray getProcessSequencesJSON(String[] sequenceArr, List<ProcessSequence> sequenceList) {

		JSONArray jsonSequenceArr = JSONFactoryUtil.createJSONArray();
		if (sequenceArr != null && sequenceArr.length > 0) {
			for (int i = 0; i < sequenceArr.length - 1; i++) {
				String sequenceNo = sequenceArr[i];
				JSONObject sequenceObj = JSONFactoryUtil.createJSONObject();
				for (ProcessSequence proSeq : sequenceList) {
					if (sequenceNo.equals(proSeq.getSequenceNo())) {
						sequenceObj.put(DossierTerm.SEQUENCE_NO, proSeq.getSequenceNo());
						sequenceObj.put(DossierTerm.SEQUENCE_NAME, proSeq.getSequenceName());
						sequenceObj.put(DossierTerm.SEQUENCE_ROLE, proSeq.getSequenceRole());
						sequenceObj.put(DossierTerm.DURATION_COUNT, proSeq.getDurationCount());
						sequenceObj.put(Field.CREATE_DATE, proSeq.getCreateDate());
					}
				}
				String nextSequenceNo = sequenceArr[i + 1];
				for (ProcessSequence proSeq : sequenceList) {
					if (nextSequenceNo.equals(proSeq.getSequenceNo())) {
						sequenceObj.put(DossierTerm.NEXT_SEQUENCE_NO, proSeq.getSequenceNo());
						sequenceObj.put(DossierTerm.NEXT_SEQUENCE_NAME, proSeq.getSequenceName());
						sequenceObj.put(DossierTerm.NEXT_SEQUENCE_ROLE, proSeq.getSequenceRole());
						sequenceObj.put(DossierTerm.NEXT_CREATE_DATE, proSeq.getCreateDate());
					}
				}
				jsonSequenceArr.put(sequenceObj);
			}
		}

		return jsonSequenceArr;
	}

	private void vnpostHN_CLS(long groupId, Dossier dossier) {
		try {

			ServerConfig config = ServerConfigLocalServiceUtil.getByCode(groupId, ServerConfigTerm.SERVER_VNPOST_HN);
			_log.debug("SERVER PROXY: " + config.getConfigs());
			if (config != null) {
				JSONObject configObj = JSONFactoryUtil.createJSONObject(config.getConfigs());
				String authStrEnc = StringPool.BLANK;
				String serverUrl = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();
				try
				{
					StringBuilder postData = new StringBuilder();
					//
					postData.append(DossierTerm.DOSSIER_ID);
					postData.append(StringPool.EQUAL);
					postData.append(dossier.getDossierId());
					//
					postData.append(StringPool.AMPERSAND);
					postData.append("MaThuTuc");
					postData.append(StringPool.EQUAL);
					postData.append(dossier.getServiceCode());
					//
					postData.append(StringPool.AMPERSAND);
					postData.append("TenThuTuc");
					postData.append(StringPool.EQUAL);
					postData.append(dossier.getServiceName());
					//
					postData.append(StringPool.AMPERSAND);
					postData.append("MaHoSo");
					postData.append(StringPool.EQUAL);
					postData.append(dossier.getDossierNo());
					//
					postData.append(StringPool.AMPERSAND);
					postData.append("LePhi");
					postData.append(StringPool.EQUAL);
					postData.append("");
					//
					postData.append(StringPool.AMPERSAND);
					postData.append("ChuHoSo");
					postData.append(StringPool.EQUAL);
					postData.append(dossier.getApplicantName());
					//
					postData.append(StringPool.AMPERSAND);
					postData.append("NguoiNop");
					postData.append(StringPool.EQUAL);
					postData.append(dossier.getDelegateName());
					//
					postData.append(StringPool.AMPERSAND);
					postData.append("CMT");
					postData.append(StringPool.EQUAL);
					postData.append(dossier.getApplicantIdNo());
					//
					postData.append(StringPool.AMPERSAND);
					postData.append("DiaChi");
					postData.append(StringPool.EQUAL);
					postData.append(dossier.getAddress());
					//
					postData.append(StringPool.AMPERSAND);
					postData.append("NgayTiepNhan");
					postData.append(StringPool.EQUAL);
					postData.append(dossier.getReceiveDate());
					//
					postData.append(StringPool.AMPERSAND);
					postData.append("DiaChiBC");
					postData.append(StringPool.EQUAL);
					postData.append(dossier.getPostalAddress());

					if (configObj.has(SyncServerTerm.SERVER_USERNAME)
							&& configObj.has(SyncServerTerm.SERVER_SECRET)
							&& configObj.has(SyncServerTerm.SERVER_URL)) {
						authStrEnc = Base64.getEncoder().encodeToString((configObj.getString(SyncServerTerm.SERVER_USERNAME) + ":" + configObj.getString(SyncServerTerm.SERVER_SECRET)).getBytes());
						serverUrl = configObj.getString(SyncServerTerm.SERVER_URL);
					}

					URL urlVal = new URL(serverUrl);
					_log.debug("API URL: " + serverUrl);

					java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal.openConnection();
					conn.setRequestMethod(HttpMethod.POST);
					conn.setRequestProperty(HttpHeaders.ACCEPT, "application/json");
					conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");

					String authorization = "Basic " + authStrEnc;
					conn.setRequestProperty(HttpHeaders.AUTHORIZATION, authorization);
					_log.debug("BASIC AUTHEN: " + authStrEnc);
					conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
					conn.setRequestProperty(ConstantUtils.CONTENT_LENGTH, StringPool.BLANK + Integer.toString(postData.toString().getBytes().length));

					conn.setUseCaches(false);
					conn.setDoInput(true);
					conn.setDoOutput(true);
					_log.debug("POST DATA: " + postData.toString());
					OutputStream os = conn.getOutputStream();
					os.write( postData.toString().getBytes() );
					os.close();

					BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					int cp;
					while ((cp = brf.read()) != -1) {
						sb.append((char) cp);
					}
					_log.debug("sb.tostring : "+ sb.toString());
				}
				catch (IOException e) {
					_log.error("err ",e);
				}
			}
		}
		catch (Exception e) {
			_log.debug(e);
		}
	}

	private void vnpostEvent(Dossier dossier, long dossierActionId) {
		Message message = new Message();
		JSONObject msgData = JSONFactoryUtil.createJSONObject();

		message.put(ConstantUtils.MSG_ENG, msgData);
		message.put(DossierTerm.CONSTANT_DOSSIER, DossierMgtUtils.convertDossierToJSON(dossier, dossierActionId));

		MessageBusUtil.sendMessage(DossierTerm.VNPOST_DOSSIER_DESTINATION, message);
	}

	private void collectVnpostEvent(Dossier dossier, long dossierActionId) {
		Message message = new Message();
		JSONObject msgData = JSONFactoryUtil.createJSONObject();

		message.put(ConstantUtils.MSG_ENG, msgData);
		message.put(DossierTerm.CONSTANT_DOSSIER, DossierMgtUtils.convertDossierToJSON(dossier, dossierActionId));
		_log.info("=============create collectVnpostEvent============");
		MessageBusUtil.sendMessage(DossierTerm.COLLECTION_VNPOST_DOSSIER_DESTINATION, message);
	}

	private void createTransactionKeypayV3(Dossier dossier, long dossierActionId) {
		Message message = new Message();
		JSONObject msgData = JSONFactoryUtil.createJSONObject();

		message.put(ConstantUtils.MSG_ENG, msgData);
		message.put(DossierTerm.CONSTANT_DOSSIER, DossierMgtUtils.convertDossierToJSON(dossier, dossierActionId));
		_log.info("=============create keypay v3============");
		MessageBusUtil.sendMessage(DossierTerm.KEYPAY_V3_DESTINATION, message);
	}

	private void integrateTTTT(Dossier dossier, ServiceContext context, long dossierActionId) {
		//Add tich hop Thong tin truyen thong
		try{
			List<ServerConfig> listServerConfig = ServerConfigLocalServiceUtil.getByProtocol(
					dossier.getGroupId(), ServerConfigTerm.TTTT_INTEGRATION);
			for (ServerConfig serverConfig : listServerConfig) {
				List<PublishQueue> lstQueues = PublishQueueLocalServiceUtil.getByG_DID_SN_ST(dossier.getGroupId(),
						dossier.getDossierId(), serverConfig.getServerNo(),
						new int[] { PublishQueueTerm.STATE_WAITING_SYNC, PublishQueueTerm.STATE_ALREADY_SENT });
				if (lstQueues == null || lstQueues.isEmpty()) {
					publishQueueLocalService.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(),
							serverConfig.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);
				}
			}
		}catch(Exception e) {
			_log.error(e);
		}
	}

	private void publishEvent(Dossier dossier, ServiceContext context, long dossierActionId) {
		_log.info("=======================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> getOriginDossierNo " + dossier.getOriginDossierId() + "|" + dossier.getOriginDossierNo());
		if (dossier.getOriginDossierId() != 0 || Validator.isNotNull(dossier.getOriginDossierNo())) {
			return;
		}
		Message message = new Message();
		JSONObject msgData = JSONFactoryUtil.createJSONObject();

		message.put(ConstantUtils.MSG_ENG, msgData);
		message.put(DossierTerm.CONSTANT_DOSSIER, DossierMgtUtils.convertDossierToJSON(dossier, dossierActionId));

		//		MessageBusUtil.sendMessage(DossierTerm.PUBLISH_DOSSIER_DESTINATION, message);

		Message lgspMessage = new Message();
		JSONObject lgspMsgData = msgData;

		lgspMessage.put(ConstantUtils.MSG_ENG, lgspMsgData);
		lgspMessage.put(DossierTerm.CONSTANT_DOSSIER, DossierMgtUtils.convertDossierToJSON(dossier, dossierActionId));

		//		MessageBusUtil.sendMessage(DossierTerm.LGSP_DOSSIER_DESTINATION, lgspMessage);

		//Add publish queue
		List<ServerConfig> lstScs = ServerConfigLocalServiceUtil.getByProtocol(dossier.getGroupId(),
				ServerConfigTerm.PUBLISH_PROTOCOL);

		//add by TrungNT - 16/11/2020
		ActionConfig actionConfig = null;
		int eventType = 1;
		DossierAction dossierAction = dossierActionLocalService.fetchDossierAction(dossierActionId);
		if(dossierAction != null) {
			actionConfig = ActionConfigLocalServiceUtil.getByCode(dossier.getGroupId(), dossierAction.getActionCode());
			eventType = actionConfig.getEventType();
		}

		_log.info("=======================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> eventType " + eventType);

		//--------------------------------
		for (ServerConfig sc : lstScs) {

			try {
				List<PublishQueue> lstQueues = PublishQueueLocalServiceUtil.getByG_DID_SN_ST(dossier.getGroupId(),
						dossier.getDossierId(), sc.getServerNo(),
						new int[] { PublishQueueTerm.STATE_WAITING_SYNC, PublishQueueTerm.STATE_ALREADY_SENT });
				if (lstQueues == null || lstQueues.isEmpty()) {

					PublishQueue publishQueue = publishQueueLocalService.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(),
							sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);

					//add by TrungNT - 16/11/2020
					if(eventType == 2) {
						List<DossierFile> dossierFiles = dossierFileLocalService.findByDID_GROUP(dossier.getGroupId(), dossier.getDossierId());
						if(dossierFiles != null) {
							JSONObject dossierFileObj = JSONFactoryUtil.createJSONObject();
							JSONArray dossierFileArrayObj = JSONFactoryUtil.createJSONArray();
							for(DossierFile dossierFile : dossierFiles) {
								if(dossierFile.getFileEntryId() > 0){
									JSONObject object = JSONFactoryUtil.createJSONObject();
									object.put("referenceUid", dossierFile.getReferenceUid());
									dossierFileArrayObj.put(object);
								}
							}

							dossierFileObj.put("dossierFiles", dossierFileArrayObj);

							publishQueue.setPublishData(dossierFileObj.toJSONString());

							publishQueueLocalService.updatePublishQueue(publishQueue);
						}

					}
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
				List<PublishQueue> lstQueues = publishQueueLocalService.getByG_DID_SN_ST(dossier.getGroupId(),
						dossier.getDossierId(), sc.getServerNo(),
						new int[] { PublishQueueTerm.STATE_WAITING_SYNC, PublishQueueTerm.STATE_ALREADY_SENT });
				if (lstQueues == null || lstQueues.isEmpty()) {
					publishQueueLocalService.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(),
							sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);
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

		//add by TrungNT
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		String mappingDossierStatus = actionImpl.getMappingStatus(dossier.getGroupId(), dossier);
		if (Validator.isNotNull(mappingDossierStatus)) {
			lstScs = ServerConfigLocalServiceUtil.getByProtocol(dossier.getGroupId(),
					ServerConfigTerm.DVCQG_INTEGRATION);
			for (ServerConfig sc : lstScs) {
				try {
					List<PublishQueue> lstQueues = publishQueueLocalService.getByG_DID_SN_ST(dossier.getGroupId(),
							dossier.getDossierId(), sc.getServerNo(),
							new int[] { PublishQueueTerm.STATE_WAITING_SYNC, PublishQueueTerm.STATE_ALREADY_SENT });
					if (lstQueues == null || lstQueues.isEmpty()) {
						publishQueueLocalService.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(),
								sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);
					}
					//					PublishQueue pq = PublishQueueLocalServiceUtil.getByG_DID_SN(dossier.getGroupId(), dossier.getDossierId(), sc.getServerNo());
					//					if (pq == null) {
					//						PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);
					//					}
					//					else {
					//						if (pq.getStatus() == PublishQueueTerm.STATE_ACK_ERROR) {
					//							PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), pq.getPublishQueueId(), dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, context);
					//						}
					//					}
				} catch (PortalException e) {
					_log.debug(e);
				}
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

	private DossierAction doActionOutsideProcess(long groupId, long userId, Dossier dossier, ActionConfig actionConfig,
												 ProcessOption option, ProcessAction proAction, String actionCode, String actionUser, String actionNote,
												 String payload, String assignUsers, String payment, int syncType, ServiceContext context)
			throws Exception {
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
		if (DossierActionTerm.OUTSIDE_ACTION_PAYMENT.equals(actionCode)) {
				PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByG_DID(groupId, dossier.getDossierId());
				if (paymentFile != null) {
					paymentFile.setPaymentStatus(5);
					paymentFile.setApproveDatetime(new Date());
				}
				PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);
				//
				return dossierAction;
		}
		//		ActionConfig ac = actionConfigLocalService.getByCode(groupId, actionCode);
		ActionConfig ac = actionConfig;
		JSONObject payloadObject = JSONFactoryUtil.createJSONObject(payload);

		if (Validator.isNotNull(payload)) {
			if (DossierActionTerm.OUTSIDE_ACTION_9100.equals(actionCode)) {
				dossier = dossierLocalService.updateDossierSpecial(dossier.getDossierId(), payloadObject);
			} else {
				dossier = dossierLocalService.updateDossier(dossier.getDossierId(), payloadObject);
			}
		}
//		int actionOverdue = getActionDueDate(groupId, dossier.getDossierId(), dossier.getReferenceUid(),
//				proAction.getProcessActionId());
		ProcessStep backCurStep = processStepLocalService.fetchBySC_GID(dossierAction.getFromStepCode(), groupId,
				dossierAction.getServiceProcessId());
		if(DossierTerm.ACTION_CODE_SPECIAL.equals(actionCode)){
			_log.info("Log action :" + actionCode + "dossierActionId " + dossierAction.getDossierActionId());
			 DossierAction action = dossierActionLocalService.updateDossierAction(groupId, 0,
					dossier.getDossierId(), dossierAction.getServiceProcessId(), dossier.getDossierActionId(),
					dossierAction.getStepCode(), dossierAction.getStepName(), dossierAction.getSequenceNo(),
					actionCode, actionUser, actionConfig.getActionName(), actionNote, 0, dossierAction.getStepCode(),
					dossierAction.getStepName(), "", null, 0l, payload,
					dossierAction.getStepInstruction(), 1, 1, false, context);
			 _log.info("Log DossierAction : " + JSONFactoryUtil.looseSerialize(action));
		}
		if (DossierActionTerm.OUTSIDE_ACTION_ROLLBACK.equals(actionCode)) {
			Dossier hslt = dossierLocalService.getByOrigin(dossier.getGroupId(), dossier.getDossierId());

			// chi nguoi thuc hien buoc truoc moi duoc phep quay lai buoc
			User user = UserLocalServiceUtil.getUser(userId);
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			if (dossierAction != null && !dossierAction.getPending()
					&& (dossierAction.isRollbackable() || hslt.getOriginality() < 0)
					&& (isAdmin || dossierAction.getUserId() == userId)) {

				dossierActionLocalService.updateState(dossierAction.getDossierActionId(),
						DossierActionTerm.STATE_ROLLBACK);

				DossierAction previousAction = dossierActionLocalService
						.fetchDossierAction(dossierAction.getPreviousActionId());
				if (previousAction != null) {
					dossierActionLocalService.updateState(previousAction.getDossierActionId(),
							DossierActionTerm.STATE_WAITING_PROCESSING);
					dossierActionLocalService.updateNextActionId(previousAction.getDossierActionId(), 0);
					dossierLocalService.rollback(dossier, previousAction);
				} else {
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
					&& dossier.getServerNo().split(StringPool.COMMA).length > 1) {
				String serverNo = null;
				if (syncType == 1) {
					serverNo = dossier.getServerNo().split(StringPool.COMMA)[0].split(StringPool.AT)[0];
				} else {
					serverNo = dossier.getServerNo().split(StringPool.COMMA)[1].split(StringPool.AT)[0];
				}
				//String serverNo = dossier.getServerNo().split(StringPool.COMMA)[0].split(StringPool.AT)[0];
				if (Validator.isNotNull(serverNo)) {
					dossierSyncLocalService.updateDossierSync(groupId, userId, dossier.getDossierId(), dossierRefUid,
							syncRefUid, dossierAction.getPrimaryKey(), actionCode, ac.getActionName(), actionUser,
							actionNote, syncType, ac.getInfoType(), payloadObject.toJSONString(), serverNo, state);
				}
			} else {
				dossierSyncLocalService.updateDossierSync(groupId, userId, dossier.getDossierId(), dossierRefUid,
						syncRefUid, dossierAction.getPrimaryKey(), actionCode, ac.getActionName(), actionUser,
						actionNote, syncType, ac.getInfoType(), payloadObject.toJSONString(), dossier.getServerNo(),
						state);
			}
		}

		if (ac != null && dossierAction != null) {
			//Only create dossier document if 2 && 3
			if (dossier.getOriginality() != DossierTerm.ORIGINALITY_DVCTT) {
				if (Validator.isNotNull(ac.getDocumentType()) && !ac.getActionCode().startsWith(StringPool.AT)) {
					//Generate document
					DocumentType dt = documentTypeLocalService.getByTypeCode(groupId, ac.getDocumentType());
					if (dt != null) {
						String documentCode = DocumentTypeNumberGenerator.generateDocumentTypeNumber(groupId,
								ac.getCompanyId(), dt.getDocumentTypeId());

						DossierDocument dossierDocument = dossierDocumentLocalService.addDossierDoc(groupId,
								dossier.getDossierId(), UUID.randomUUID().toString(),
								dossierAction.getDossierActionId(), dt.getTypeCode(), dt.getDocumentName(),
								documentCode, 0L, dt.getDocSync(), context);

						//Generate PDF
						String formData = payload;
						JSONObject formDataObj = processMergeDossierFormData(dossier,
								JSONFactoryUtil.createJSONObject(formData));
						formDataObj = processMergeDossierProcessRole(dossier, 1, formDataObj, dossierAction);
						formDataObj.put("documentCode", Validator.isNotNull(documentCode) ? documentCode: StringPool.BLANK);
						//_log.info("Dossier document form data action outside: " + formDataObj.toJSONString());
						Message message = new Message();
						//_log.info("Document script: " + dt.getDocumentScript());
						JSONObject msgData = JSONFactoryUtil.createJSONObject();
						msgData.put(ConstantUtils.CLASS_NAME, DossierDocument.class.getName());
						msgData.put(Field.CLASS_PK, dossierDocument.getDossierDocumentId());
						msgData.put(ConstantUtils.JRXML_TEMPLATE, dt.getDocumentScript());
						msgData.put(ConstantUtils.FORM_DATA, formDataObj.toJSONString());
						msgData.put(Field.USER_ID, userId);

						message.put(ConstantUtils.MSG_ENG, msgData);
						MessageBusUtil.sendMessage(ConstantUtils.JASPER_DESTINATION, message);
					}
				}
			}
		}

		if (ac != null && ac.getEventType() == ActionConfigTerm.EVENT_TYPE_SENT
				&& OpenCPSConfigUtil.isPublishEventEnable()) {
			publishEvent(dossier, context, dossierAction.getDossierActionId());
		}

		//Add by TrungNT - Fix tam theo y/k cua a TrungDK va Duantv
		if (dossier.isOnline() && proAction != null && "listener".equals(proAction.getAutoEvent().toString())
				&& OpenCPSConfigUtil.isPublishEventEnable()) {
			publishEvent(dossier, context, dossierAction.getDossierActionId());
		}

		if (OpenCPSConfigUtil.isNotificationEnable()) {
			createNotificationQueueOutsideProcess(userId, groupId, dossier, proAction, actionConfig, payload, context);
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
						DossierAction lastAction = dossierActionLocalService
								.fetchDossierAction(originDossier.getDossierActionId());
						ActionConfig mappingAction = actionConfigLocalService.getByCode(groupId,
								lastAction.getActionCode());
						if (Validator.isNotNull(mappingAction.getMappingAction())) {
							DossierAction previousOriginAction = dossierActionLocalService
									.fetchDossierAction(lastAction.getPreviousActionId());
							dossierActionLocalService.updateState(previousOriginAction.getDossierActionId(),
									DossierActionTerm.STATE_WAITING_PROCESSING);
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
					} catch (JSONException e) {
						_log.debug(e);
					}
				}
				DossierAction lastAction = dossierActionLocalService.fetchDossierAction(hslt.getDossierActionId());
				ActionConfig mappingAction = actionConfigLocalService.getByCode(groupId, lastAction.getActionCode());

				if (Validator.isNotNull(mappingAction.getMappingAction())) {

					doAction(groupId, userId, hslt, optionHslt, null, DossierActionTerm.OUTSIDE_ACTION_ROLLBACK,
							actionUserHslt, actionNote, payload, assignUsers, payment, 0, context);
				}
			}
		}
		if(DossierActionTerm.OUTSIDE_ACTION_EDIT.equals(actionCode)){
			Employee employee = null;
			Serializable employeeCache = cache.getFromCache(CacheTerm.MASTER_DATA_EMPLOYEE,
					groupId + StringPool.UNDERLINE + userId);
			_log.info("EMPLOYEE CACHE: " + employeeCache);
			if (employeeCache == null) {
				employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
				if (employee != null) {
					cache.addToCache(CacheTerm.MASTER_DATA_EMPLOYEE, groupId + StringPool.UNDERLINE + userId,
							(Serializable) employee, ttl);
				}
			} else {
				employee = (Employee) employeeCache;
			}
			User user = userLocalService.fetchUser(userId);
			createDossierDocument(groupId,userId,actionConfig, dossier, dossierAction, payloadObject,employee, user, context);
		}

		if(DossierActionTerm.ACTION_SPECIAL_WAITING_PAYMENT.equals(actionCode)){
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(groupId,dossier.getDossierId());
			if (paymentFile != null) {
				paymentFile.setPaymentStatus(3);
				paymentFile.setApproveDatetime(new Date());
			}
			PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);
		}
		if(DossierActionTerm.ACTION_SPECIAL_CONFIRM_PAYMENT.equals(actionCode)){
//			POSVCBUtils.saleRequestDataPOSVCB();
		}

		return dossierAction;
	}

	private boolean isSmsNotify(Dossier dossier) {
		String metaData = dossier.getMetaData();
		if (Validator.isNotNull(metaData)) {
			try {
				JSONObject jsonMetaData = JSONFactoryUtil.createJSONObject(metaData);
				//
				Iterator<String> keys = jsonMetaData.keys();

				while (keys.hasNext()) {
					String key = keys.next();
					String value = jsonMetaData.getString(key);
					if (Validator.isNotNull(value) && value.contentEquals(DossierTerm.SMS_NOTIFY)) {
						try {
							Boolean isSmsNotify = Boolean.parseBoolean(value);
							return isSmsNotify;
						} catch (Exception e) {
							_log.debug(e);
						}

					}
				}
			} catch (JSONException e) {
				_log.debug(e);
			}
		}

		return true;
	}

	private boolean isEmailNotify(Dossier dossier) {
		String metaData = dossier.getMetaData();
		if (Validator.isNotNull(metaData)) {
			try {
				JSONObject jsonMetaData = JSONFactoryUtil.createJSONObject(metaData);
				//
				Iterator<String> keys = jsonMetaData.keys();

				while (keys.hasNext()) {
					String key = keys.next();
					String value = jsonMetaData.getString(key);
					if (Validator.isNotNull(value) && value.contentEquals(DossierTerm.EMAIL_NOTIFY)) {
						try {
							Boolean isEmailNotify = Boolean.parseBoolean(value);
							return isEmailNotify;
						} catch (Exception e) {
							_log.debug(e);
						}

					}
				}
			} catch (JSONException e) {
				_log.debug(e);
			}
		}

		return true;
	}

	private void createNotificationQueueOutsideProcess(long userId, long groupId, Dossier dossier,
													   ProcessAction proAction, ActionConfig actionConfig, String actionPayload, ServiceContext context) {
		DossierAction dossierAction = dossierActionLocalService.fetchDossierAction(dossier.getDossierActionId());
		User u = UserLocalServiceUtil.fetchUser(userId);
		JSONObject payloadObj = JSONFactoryUtil.createJSONObject();

		try {
			payloadObj = buildNotificationPayload(dossier, JSONFactoryUtil.createJSONObject(actionPayload));
			JSONObject dossierObj = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(dossier));
			dossierObj = buildNotificationPayload(dossier, dossierObj);

			payloadObj.put(KeyPayTerm.DOSSIER, dossierObj);

			if (dossierAction != null) {
				payloadObj.put(DossierActionTerm.ACTION_CODE, dossierAction.getActionCode());
				payloadObj.put(DossierActionTerm.ACTION_USER, dossierAction.getActionUser());
				payloadObj.put(DossierActionTerm.ACTION_NAME, dossierAction.getActionName());
				payloadObj.put(DossierActionTerm.ACTION_NOTE, dossierAction.getActionNote());
			}
		} catch (Exception e) {
			_log.error(e);
		}

		String notificationType = StringPool.BLANK;
		String preCondition = StringPool.BLANK;
		if (actionConfig != null && Validator.isNotNull(actionConfig.getNotificationType())) {
			if (actionConfig.getNotificationType().contains(StringPool.AT)) {
				String[] split = StringUtil.split(actionConfig.getNotificationType(), StringPool.AT);
				if (split.length == 2) {
					notificationType = split[0];
					preCondition = split[1];
				}
			} else {
				notificationType = actionConfig.getNotificationType();
			}
			boolean isSendSMS = NotificationUtil.isSendSMS(preCondition);
			boolean isSendEmail = NotificationUtil.isSendEmail(preCondition);
			boolean isSendNotiSMS = true;
			boolean isSendNotiEmail = true;
			if (Validator.isNotNull(preCondition)) {
				if (!DossierMgtUtils.checkPreCondition(new String[] { preCondition }, dossier, null)) {
					if (isSendSMS) {
						isSendNotiSMS = false;
						isSendNotiEmail = true;
					} else {
						isSendNotiSMS = false;
						isSendNotiEmail = false;
					}
				} else {
					isSendNotiSMS = isSendSMS;
					isSendNotiEmail = isSendEmail;
				}
			}
			Notificationtemplate notiTemplate = NotificationtemplateLocalServiceUtil
					.fetchByF_NotificationtemplateByType(groupId, notificationType);
			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);

			if (notiTemplate != null) {
				if (KeyPayTerm.MINUTELY.equals(notiTemplate.getInterval())) {
					cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());
				} else if (KeyPayTerm.HOURLY.equals(notiTemplate.getInterval())) {
					cal.add(Calendar.HOUR, notiTemplate.getExpireDuration());
				} else {
					cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());
				}
				Date expired = cal.getTime();

				if (notificationType.startsWith(KeyPayTerm.APLC)) {
					if (dossier.getOriginality() == DossierTerm.ORIGINALITY_MOTCUA
							|| dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) {
						try {
							Applicant applicant = ApplicantLocalServiceUtil.fetchByAppId(dossier.getApplicantIdNo());
							long toUserId = (applicant != null ? applicant.getMappingUserId() : 0l);
							String contactEmail = (isEmailNotify(dossier)) ? dossier.getContactEmail()
									: StringPool.BLANK;
							String telNo = (isSmsNotify(dossier)) ? dossier.getContactTelNo() : StringPool.BLANK;
							String fromFullName = u.getFullName();
							if (Validator.isNotNull(OpenCPSConfigUtil.getMailToApplicantFrom())) {
								fromFullName = OpenCPSConfigUtil.getMailToApplicantFrom();
							}
							JSONObject filesAttach = getFileAttachMailForApplicant(dossier, proAction);
							payloadObj.put("filesAttach", filesAttach);
							payloadObj = verifyPayloadMail(payloadObj);
							_log.info("====================payloadattach1=========" + payloadObj);
							NotificationQueueLocalServiceUtil.addNotificationQueue(userId, groupId, notificationType,
									Dossier.class.getName(), String.valueOf(dossier.getDossierId()),
									payloadObj.toJSONString(), fromFullName, dossier.getApplicantName(), toUserId,
									isSendNotiEmail ? contactEmail : StringPool.BLANK,
									isSendNotiSMS ? telNo : StringPool.BLANK, now, expired, context);
						} catch (NoSuchUserException e) {
							_log.error(e);
						}
					}
				} else if (notificationType.startsWith(KeyPayTerm.USER)) {

				}
			}
		}

	}

	/**
	 * @param lenght
	 * @return
	 */
	private String _generatorUniqueString(int lenght) {

		char[] chars = KeyPayTerm.ZERO_TO_NINE.toCharArray();

		StringBuilder sb = new StringBuilder();

		Random random = new Random();

		for (int i = 0; i < lenght; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}

		return sb.toString();

	}

	private void assignDossierActionUser(Dossier dossier, int allowAssignUser, DossierAction dossierAction, long userId,
										 long groupId, long assignUserId, JSONArray assignedUsers) throws PortalException {
		int moderator = 1;
		List<DossierUser> lstDus = dossierUserLocalService.findByDID(dossier.getDossierId());
		HashMap<Long, DossierUser> mapDus = new HashMap<>();
		for (DossierUser du : lstDus) {
			mapDus.put(du.getUserId(), du);
		}
		List<org.opencps.dossiermgt.model.DossierActionUser> lstDaus = dossierActionUserLocalService
				.getByDossierId(dossier.getDossierId());

		HashMap<Long, Map<Long, org.opencps.dossiermgt.model.DossierActionUser>> mapDaus = new HashMap<>();
		for (org.opencps.dossiermgt.model.DossierActionUser dau : lstDaus) {
			if (mapDaus.get(dau.getDossierActionId()) != null) {
				Map<Long, org.opencps.dossiermgt.model.DossierActionUser> temp = mapDaus.get(dau.getDossierActionId());
				temp.put(dau.getUserId(), dau);
			} else {
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
				long userIdAssigned = subUser.getLong(Field.USER_ID);
				int assigned = subUser.has(DossierActionUserTerm.ASSIGNED)
						? subUser.getInt(DossierActionUserTerm.ASSIGNED)
						: 0;

				pk.setDossierActionId(dossierAction.getDossierActionId());

				pk.setUserId(subUser.getLong(Field.USER_ID));

				DossierUser dossierUser = null;
				dossierUser = mapDus.get(subUser.getLong(Field.USER_ID));

				if (dossierUser != null) {
					//Update dossier user if assigned
					if (allowAssignUser != ProcessActionTerm.NOT_ASSIGNED) {
						dossierUser.setModerator(1);
						dossierUserLocalService.updateDossierUser(dossierUser.getDossierId(), dossierUser.getUserId(),
								dossierUser.getModerator(), dossierUser.getVisited());

						//					model.setModerator(dossierUser.getModerator());
						moderator = dossierUser.getModerator();
					}

				} else {
					if (allowAssignUser != ProcessActionTerm.NOT_ASSIGNED) {
						dossierUserLocalService.addDossierUser(groupId, dossier.getDossierId(), userIdAssigned, 1,
								true);
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
						addDossierActionUserByAssigned(groupId, allowAssignUser, userIdAssigned,
								dossierAction.getDossierActionId(), moderator, false, dAction.getStepCode(),
								dossier.getDossierId(), assigned, 0);
					}
					//					else {
					//						addDossierActionUserByAssigned(allowAssignUser, userIdAssigned, dossierAction.getDossierActionId(), moderator, false,
					//								StringPool.BLANK, dossier.getDossierId(), assigned);
					//					}
				} else {
					dau.setModerator(1);
					dau.setAssigned(assigned);
					dossierActionUserLocalService.updateDossierActionUser(dau);
				}
			} else if (subUser != null && subUser.has(DossierActionUserTerm.ASSIGNED)
					&& subUser.getInt(DossierActionUserTerm.ASSIGNED) == DossierActionUserTerm.NOT_ASSIGNED) {
				//			model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
				DossierActionUserPK pk = new DossierActionUserPK();

				pk.setDossierActionId(dossierAction.getDossierActionId());
				pk.setUserId(subUser.getLong(Field.USER_ID));

				org.opencps.dossiermgt.model.DossierActionUser dau = dossierActionUserLocalService
						.fetchDossierActionUser(pk);
				if (Validator.isNull(dau)) {
				} else {
					dau.setModerator(0);
					dossierActionUserLocalService.updateDossierActionUser(dau);
				}
			}
		}
	}

	private void addDossierActionUserByAssigned(long groupId, int allowAssignUser, long userId, long dossierActionId,
												int moderator, boolean visited, String stepCode, long dossierId, int assigned, int delegacy) {
		org.opencps.dossiermgt.model.DossierActionUser model = new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();

		//		int assigned = DossierActionUserTerm.NOT_ASSIGNED;
		model.setVisited(visited);
		model.setDossierId(dossierId);
		model.setStepCode(stepCode);
		model.setAssigned(assigned);
		model.setDelegacy(delegacy);
		//Check employee is exits and wokingStatus
		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
		_log.debug("Employee : " + employee);
		_log.debug("ADD DOSSIER ACTION USER ASSIGNED : " + stepCode);

		if (employee != null && employee.getWorkingStatus() == 1) {

			DossierActionUserPK pk = new DossierActionUserPK(dossierActionId, userId);

			org.opencps.dossiermgt.model.DossierActionUser dau = dossierActionUserLocalService
					.fetchDossierActionUser(pk);
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
			_log.debug("DOSSIER ACTION ASSIGNED USER: " + dau);
			if (dau == null) {
				dossierActionUserLocalService.addDossierActionUser(model);
			} else {
				dossierActionUserLocalService.updateDossierActionUser(model);
			}
		}
	}

	public void initDossierActionUser(ProcessAction processAction, Dossier dossier, int allowAssignUser,
									  DossierAction dossierAction, long userId, long groupId, long assignUserId) throws PortalException {
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
		List<DossierAction> lstStepActions = dossierActionLocalService.getByDID_FSC_NOT_DAI(dossier.getDossierId(),
				stepCode, dossierAction.getDossierActionId());
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
					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(dossier.getGroupId(),
							user.getUserId());
					//_log.debug("Employee : " + employee);
					if (employee != null && employee.getWorkingStatus() == 1
							&& (Validator.isNull(employee.getScope()) || (Validator.isNotNull(employee.getScope())
							&& Arrays.asList(employee.getScope().split(StringPool.COMMA)).indexOf(dossier.getGovAgencyCode()) >= 0))) {
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
						} else {
							assigned = DossierActionUserTerm.NOT_ASSIGNED;
						}

						updateDossierUser(dossier, processStepRole, user);
						List<DossierActionUser> lstDau = dossierActionUserLocalService
								.getByDossierUserAndStepCode(dossier.getDossierId(), user.getUserId(), stepCode);
						DossierActionUser lastDau = (lstDau.size() > 0 ? lstDau.get(0) : null);
						for (DossierActionUser dau : lstDau) {
							if (dau.getDossierActionId() > lastDau.getDossierActionId()) {
								lastDau = dau;
							}
						}

						if (lastDau != null) {
							addDossierActionUserByAssigned(groupId, processAction.getAllowAssignUser(),
									user.getUserId(), dossierAction.getDossierActionId(), lastDau.getModerator(), false,
									stepCode, dossier.getDossierId(), lastDau.getAssigned(), lastDau.getDelegacy());
						} else {
							addDossierActionUserByAssigned(groupId, processAction.getAllowAssignUser(),
									user.getUserId(), dossierAction.getDossierActionId(), mod, false, stepCode,
									dossier.getDossierId(), assigned, 0);
						}
					}
				}
			}
		} else {
			//Get role from service process
			initDossierActionUserByServiceProcessRole(dossier, allowAssignUser, dossierAction, userId, groupId,
					assignUserId);
		}
	}

	private void updateDossierUser(Dossier dossier, ProcessStepRole processStepRole, User user) {
		DossierUserPK pk = new DossierUserPK();
		pk.setDossierId(dossier.getDossierId());
		pk.setUserId(user.getUserId());
		DossierUser du = dossierUserLocalService.fetchDossierUser(pk);
		if (du == null) {
			dossierUserLocalService.addDossierUser(dossier.getGroupId(), dossier.getDossierId(), user.getUserId(),
					processStepRole.getModerator() ? 1 : 0, true);
		} else {
			try {
				if ((processStepRole.getModerator() && du.getModerator() != DossierActionUserTerm.ASSIGNED_PH)
						|| (!processStepRole.getModerator()
						&& du.getModerator() != DossierActionUserTerm.ASSIGNED_PH)) {
					dossierUserLocalService.updateDossierUser(dossier.getDossierId(), user.getUserId(),
							du.getModerator() == 0 ? (processStepRole.getModerator() ? 1 : 0) : 1, true);
				}
			} catch (NoSuchDossierUserException e) {
				_log.error(e);
			}
		}
	}

	private boolean checkGovDossierEmployee(Dossier dossier, Employee e) {
		if (e != null && (Validator.isNull(e.getScope()) || (Arrays.asList(e.getScope().split(StringPool.COMMA)).indexOf(dossier.getGovAgencyCode()) >= 0))) {
			return true;
		}

		return false;
	}

	private void initDossierActionUserByServiceProcessRole(Dossier dossier, int allowAssignUser,
														   DossierAction dossierAction, long userId, long groupId, long assignUserId) {
		try {
			Map<Long, Employee> mapEmps = new HashMap<Long, Employee>();
			List<Employee> lstEmps = EmployeeLocalServiceUtil.findByG(dossier.getGroupId());
			for (Employee e : lstEmps) {
				mapEmps.put(e.getMappingUserId(), e);
			}

			ServiceProcess serviceProcess = serviceProcessLocalService.getServiceByCode(groupId,
					dossier.getServiceCode(), dossier.getGovAgencyCode(), dossier.getDossierTemplateNo());
			List<ServiceProcessRole> listSprs = serviceProcessRoleLocalService
					.findByS_P_ID(serviceProcess.getServiceProcessId());

			DossierAction da = dossierAction;

			for (ServiceProcessRole spr : listSprs) {
				int mod = 0;
				boolean moderator = spr.getModerator();

				if (moderator) {
					mod = 1;
				}
				List<User> users = UserLocalServiceUtil.getRoleUsers(spr.getRoleId());
				for (User user : users) {
					if (mapEmps.containsKey(user.getUserId())) {
						Employee e = mapEmps.get(user.getUserId());
						if (checkGovDossierEmployee(dossier, e)) {
							int assigned = user.getUserId() == assignUserId ? DossierActionUserTerm.ASSIGNED_TH
									: (moderator ? DossierActionUserTerm.ASSIGNED_TH
									: DossierActionUserTerm.NOT_ASSIGNED);
							org.opencps.dossiermgt.model.DossierActionUser dau = dossierActionUserLocalService
									.getByDossierAndUser(dossierAction.getDossierActionId(), user.getUserId());
							if (dau != null) {
								dau.setModerator(mod);
								dau.setAssigned(assigned);
								dossierActionUserLocalService.updateDossierActionUser(dau);
							} else {
								addDossierActionUserByAssigned(groupId, allowAssignUser, user.getUserId(),
										dossierAction.getDossierActionId(), mod, false, da.getStepCode(),
										dossier.getDossierId(), assigned, 0);
							}
						}
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
		Map<Long, Employee> mapEmps = new HashMap<Long, Employee>();
		List<Employee> lstEmps = EmployeeLocalServiceUtil.findByG(dossier.getGroupId());
		for (Employee e : lstEmps) {
			mapEmps.put(e.getMappingUserId(), e);
		}
		if (stepCodeArr.length > 0) {
			for (String stepCode : stepCodeArr) {
				if (stepCode.startsWith(StringPool.EXCLAMATION)) {
					int index = stepCode.indexOf(StringPool.EXCLAMATION);
					String stepCodePunc = stepCode.substring(index + 1);
					List<org.opencps.dossiermgt.model.DossierActionUser> lstDaus = dossierActionUserLocalService
							.getByDossierAndStepCode(dossier.getDossierId(), stepCodePunc);
					List<DossierAction> lstDossierActions = dossierActionLocalService
							.findDossierActionByDID_STEP(dossier.getDossierId(), stepCodePunc);

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
								} else {
									try {
										if (duModel.getModerator() == 0 && moderator == 1) {
											dossierUserLocalService.updateDossierUser(dossier.getDossierId(),
													dau.getUserId(), moderator, true);
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
								dossierActionUserLocalService.addOrUpdateDossierActionUser(dau.getUserId(),
										dossier.getGroupId(), dossier.getDossierActionId(), dossier.getDossierId(),
										curStep.getStepCode(), moderator, assigned, true);
							}
						}
					} catch (Exception e) {
						_log.error(e);
					}
				} else {
					ServiceProcess serviceProcess = null;
					try {
						serviceProcess = serviceProcessLocalService.getServiceByCode(dossier.getGroupId(),
								dossier.getServiceCode(), dossier.getGovAgencyCode(), dossier.getDossierTemplateNo());
//						_log.info("stepCode: "+ JSONFactoryUtil.looseSerialize(serviceProcess));
						if (serviceProcess != null) {
							ProcessStep processStep = processStepLocalService.fetchBySC_GID(stepCode,
									dossier.getGroupId(), serviceProcess.getServiceProcessId());
//							_log.info("processStep: "+ JSONFactoryUtil.looseSerialize(processStep));
							if (processStep == null)
								continue;
							List<ProcessStepRole> lstRoles = processStepRoleLocalService
									.findByP_S_ID(processStep.getProcessStepId());
//							_log.info("lstRoles: "+ JSONFactoryUtil.looseSerialize(lstRoles));
							if (lstRoles != null && lstRoles.size() > 0) {
								_log.info("stepCode: "+ stepCode + "|lstRoles : "+JSONFactoryUtil.looseSerialize(lstRoles.get(0)));
								for (ProcessStepRole psr : lstRoles) {
									List<User> users = UserLocalServiceUtil.getRoleUsers(psr.getRoleId());
//									_log.info("users: "+JSONFactoryUtil.looseSerialize(users));
									//_log.info("mapEmps: "+JSONFactoryUtil.looseSerialize(mapEmps));
									for (User u : users) {
										if (mapEmps.containsKey(u.getUserId())) {
											Employee emp = mapEmps.get(u.getUserId());
											_log.info("user: "+ u.getEmailAddress() + "|checkGovDossierEmployee: "+ checkGovDossierEmployee(dossier, emp));
											if (checkGovDossierEmployee(dossier, emp)) {
												DossierUserPK duPk = new DossierUserPK();
												duPk.setDossierId(dossier.getDossierId());
												duPk.setUserId(u.getUserId());
												int moderator = (psr.getModerator() ? 1 : 0);

												DossierUser duModel = dossierUserLocalService.fetchDossierUser(duPk);

												if (duModel == null) {
													dossierUserLocalService.addDossierUser(dossier.getGroupId(),
															dossier.getDossierId(), u.getUserId(), moderator, true);
												} else {
													try {
														if (duModel.getModerator() == 0 && moderator == 1) {
															dossierUserLocalService.updateDossierUser(
																	dossier.getDossierId(), u.getUserId(), moderator, true);
														}
													} catch (NoSuchDossierUserException e) {
														_log.error(e);
													}
												}

												DossierActionUserPK dauPk = new DossierActionUserPK();
												dauPk.setDossierActionId(dossier.getDossierActionId());
												dauPk.setUserId(u.getUserId());
												int assigned = moderator == 1 ? 1 : 0;
												dossierActionUserLocalService.addOrUpdateDossierActionUser(u.getUserId(),
														dossier.getGroupId(), dossier.getDossierActionId(),
														dossier.getDossierId(), curStep.getStepCode(), moderator, assigned,
														true);
											}
										}
									}
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

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
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
		_log.info("userId: "+userId);
		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
		_log.info("employee: "+JSONFactoryUtil.looseSerialize(employee));
		if (employee != null) {
			long employeeId = employee.getEmployeeId();
			if (employeeId > 0) {
				List<EmployeeJobPos> empJobList = EmployeeJobPosLocalServiceUtil.findByF_EmployeeId(employeeId);
				_log.info("empJobList: "+JSONFactoryUtil.looseSerialize(empJobList));
				if (empJobList != null && empJobList.size() > 0) {
					for (EmployeeJobPos employeeJobPos : empJobList) {
						_log.info("employeeJobPos: "+JSONFactoryUtil.looseSerialize(employeeJobPos));
						long jobPosId = employeeJobPos.getJobPostId();
						if (jobPosId > 0) {
							JobPos job = JobPosLocalServiceUtil.fetchJobPos(jobPosId);
							if (job != null) {
								ServiceProcessRolePK pk = new ServiceProcessRolePK(serviceProcessId,
										job.getMappingRoleId());
								_log.info("serviceProcessId: "+serviceProcessId);
								_log.info("job.getMappingRoleId(): "+job.getMappingRoleId());
								_log.info("pk: "+JSONFactoryUtil.looseSerialize(pk));
								ServiceProcessRole role = serviceProcessRoleLocalService.fetchServiceProcessRole(pk);
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
		_log.debug("CREATE DOSSIER 1.X: " + input.getServiceCode() + ", " + input.getGovAgencyCode() + ", "
				+ input.getDossierTemplateNo());

		//dossierPermission.hasCreateDossier(groupId, user.getUserId(), input.getServiceCode(), input.getGovAgencyCode(),
		//		input.getDossierTemplateNo());

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
		} else if (config != null) {
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
			_log.debug("DOSSIER SECRET LENGTH: " + OpenCPSConfigUtil.getDefaultDossierSecretLength());
			if (OpenCPSConfigUtil.getDefaultDossierSecretLength() > DossierTerm.PIN_LENGTH) {
				password = PwdGenerator.getPassword(OpenCPSConfigUtil.getDefaultDossierSecretLength(),
						PwdGenerator.KEY1);
			} else {
				password = PwdGenerator.getPinNumber();
			}
		}

		String postalCityName = StringPool.BLANK;
		String postalDistrictName = StringPool.BLANK;

		if (Validator.isNotNull(input.getPostalCityCode())) {
			postalCityName = getDictItemName(groupId, VNPOST_CITY_CODE, input.getPostalCityCode());
		}
		if (Validator.isNotNull(input.getPostalDistrictCode())) {
			postalDistrictName = getDictItemName(groupId, VNPOST_CITY_CODE, input.getPostalDistrictCode());
		}
		Long sampleCount = (option != null ? option.getSampleCount() : 1l);
		String registerBookCode = (option != null
				? (Validator.isNotNull(option.getRegisterBookCode()) ? option.getRegisterBookCode() : StringPool.BLANK)
				: StringPool.BLANK);
		//String registerBookName = (Validator.isNotNull(registerBookCode) ? getDictItemName(groupId, REGISTER_BOOK, registerBookCode) : StringPool.BLANK);
		String registerBookName = StringPool.BLANK;
		if (Validator.isNotNull(registerBookCode)) {
			DynamicReport report = DynamicReportLocalServiceUtil.fetchByG_CODE(groupId, registerBookCode);
			if (report != null) {
				registerBookName = report.getReportName();
			}
		}

		// Process group dossier
		if (originality == 9) {

			_log.debug("CREATE DOSSIER 2: " + (System.currentTimeMillis() - start) + " ms");

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date appIdDate = null;

			try {
				appIdDate = sdf.parse(input.getApplicantIdDate());

			} catch (Exception e) {
				_log.debug(e);
			}
			Dossier dossier = dossierLocalService.initDossier(groupId, 0l, referenceUid, counter,
					input.getServiceCode(), serviceName, input.getGovAgencyCode(), govAgencyName,
					input.getApplicantName(), input.getApplicantIdType(), input.getApplicantIdNo(), appIdDate,
					input.getAddress(), input.getCityCode(), cityName, input.getDistrictCode(), districtName,
					input.getWardCode(), wardName, input.getContactName(), input.getContactTelNo(),
					input.getContactEmail(), input.getDossierTemplateNo(), password, viaPostal,
					input.getPostalAddress(), input.getPostalCityCode(), postalCityName,
					input.getPostalDistrictCode(), postalDistrictName, input.getPostalTelNo(), online,
					process.getDirectNotification(), input.getApplicantNote(), Integer.valueOf(input.getOriginality()),
					service, process, option, serviceContext);

			if (Validator.isNotNull(input.getDossierName())) {
				dossier.setDossierName(input.getDossierName());
			} else {
				dossier.setDossierName(serviceName);
			}
			dossier.setSampleCount(sampleCount);
			dossier.setSystemId(input.getSystemId());

			//Delegate dossier
			dossier.setDelegateType(input.getDelegateType() != null ? input.getDelegateType() : 0);
			if (Validator.isNotNull(input.getDocumentNo())) {
				dossier.setDocumentNo(input.getDocumentNo());
			}
			if (input.getDocumentDate() != null && 0 != input.getDocumentDate()) {
				dossier.setDocumentDate(new Date(input.getDocumentDate()));
			}

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
			_log.debug("originality: " + originality);
			String templateNo = dossier.getDossierTemplateNo();
			_log.debug("templateNo: " + templateNo);
			if (Validator.isNotNull(input.getDossierMarkArr())) {
				JSONArray markArr = JSONFactoryUtil.createJSONArray(input.getDossierMarkArr());
				if (markArr != null && markArr.length() > 0) {
					List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId,
							dossier.getDossierId());
					Map<String, DossierMark> mapMarks = new HashMap<>();
					for (DossierMark dm : lstMarks) {
						mapMarks.put(dm.getDossierPartNo(), dm);
					}

					for (int i = 0; i < markArr.length(); i++) {
						JSONObject jsonMark = markArr.getJSONObject(i);
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[markArr
								.length()];

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
					_log.debug("partList.size(): " + partList.size());
					_log.debug("CREATE DOSSIER 4.1: " + (System.currentTimeMillis() - start) + " ms");
					org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList
							.size()];
					int count = 0;
					List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId,
							dossier.getDossierId());
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
			List<ServiceProcessRole> lstProcessRoles = serviceProcessRoleLocalService
					.findByS_P_ID(process.getServiceProcessId());
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
			if (Validator.isNotNull(input.getVnpostalStatus())) {
				dossier.setVnpostalStatus(input.getVnpostalStatus());
			}
			if (Validator.isNotNull(input.getVnpostalProfile())) {
				dossier.setVnpostalProfile(input.getVnpostalProfile());
			}
			if (Validator.isNotNull(input.getFromViaPostal())) {
				dossier.setFromViaPostal(input.getFromViaPostal());
			}
			if (Validator.isNotNull(input.getServerNo())) {
				dossier.setServerNo(input.getServerNo());
			}
			_log.debug("CREATE DOSSIER 7: " + (System.currentTimeMillis() - start) + " ms");
			return dossierLocalService.updateDossier(dossier);

		} else {
			List<Dossier> oldDossiers = dossierLocalService.getByU_G_GAC_SC_DTNO_DS_O(userId, groupId,
					input.getServiceCode(), input.getGovAgencyCode(), input.getDossierTemplateNo(), StringPool.BLANK,
					Integer.valueOf(input.getOriginality()));

			Dossier dossier = null;

			Dossier oldRefDossier = Validator.isNotNull(input.getReferenceUid())
					? dossierLocalService.getByRef(groupId, input.getReferenceUid())
					: null;

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
				if (serviceProcess != null) {
					durationCount = serviceProcess.getDurationCount();
					durationUnit = serviceProcess.getDurationUnit();
					dossier.setDurationCount(durationCount);
					dossier.setDurationUnit(durationUnit);
				}

				if (durationCount > 0) {
					// Date dueDate = HolidayUtils.getDueDate(new Date(), durationCount, durationUnit, groupId);
					DueDateUtils dueDateUtils = new DueDateUtils(new Date(), durationCount, durationUnit, groupId);
					Date dueDate = dueDateUtils.getDueDate();
					dossier.setDueDate(dueDate);
				}

				//Delegate
				//Delegate dossier
				dossier.setDelegateType(input.getDelegateType() != null ? input.getDelegateType() : 0);
				if (Validator.isNotNull(input.getDocumentNo())) {
					dossier.setDocumentNo(input.getDocumentNo());
				}
				if (input.getDocumentDate() != null && 0 != input.getDocumentDate()) {
					dossier.setDocumentDate(new Date(input.getDocumentDate()));
				}
			} else if (oldDossiers.size() > 0) {
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
				if (serviceProcess != null) {
					durationCount = serviceProcess.getDurationCount();
					durationUnit = serviceProcess.getDurationUnit();
					dossier.setDurationCount(durationCount);
					dossier.setDurationUnit(durationUnit);
				}

				if (durationCount > 0) {
					// Date dueDate = HolidayUtils.getDueDate(new Date(), durationCount, durationUnit, groupId);
					DueDateUtils dueDateUtils = new DueDateUtils(new Date(), durationCount, durationUnit, groupId);
					Date dueDate = dueDateUtils.getDueDate();
					dossier.setDueDate(dueDate);
				}

				dossier.setOnline(online);
				if (Validator.isNotNull(input.getDossierName()))
					dossier.setDossierName(input.getDossierName());
				if (serviceProcess != null) {
					dossier.setProcessNo(serviceProcess.getProcessNo());
				}

				//Delegate dossier
				dossier.setDelegateType(input.getDelegateType() != null ? input.getDelegateType() : 0);
				if (Validator.isNotNull(input.getDocumentNo())) {
					dossier.setDocumentNo(input.getDocumentNo());
				}
				if (input.getDocumentDate() != null && 0 != input.getDocumentDate()) {
					dossier.setDocumentDate(new Date(input.getDocumentDate()));
				}

				//					dossier = DossierLocalServiceUtil.updateDossier(dossier);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
				Date appIdDate = null;

				try {
					appIdDate = sdf.parse(input.getApplicantIdDate());

				} catch (Exception e) {
					_log.debug(e);
				}
				dossier = dossierLocalService.initDossier(groupId, 0l, referenceUid, counter, input.getServiceCode(),
						serviceName, input.getGovAgencyCode(), govAgencyName, input.getApplicantName(),
						input.getApplicantIdType(), input.getApplicantIdNo(), appIdDate, input.getAddress(),
						input.getCityCode(), cityName, input.getDistrictCode(), districtName, input.getWardCode(),
						wardName, input.getContactName(), input.getContactTelNo(), input.getContactEmail(),
						input.getDossierTemplateNo(), password, viaPostal, input.getPostalAddress(),
						input.getPostalCityCode(), postalCityName, input.getPostalDistrictCode(), postalDistrictName, input.getPostalTelNo(), online,
						process.getDirectNotification(), input.getApplicantNote(),
						Integer.valueOf(input.getOriginality()), service, process, option, serviceContext);
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
				dossier.setPostalDistrictName(postalDistrictName);
				dossier.setPostalWardCode(input.getPostalWardCode());
				dossier.setPostalWardName(input.getPostalWardName());
				dossier.setOriginDossierNo(input.getOriginDossierNo());

				//dossier.setRegisterBookCode(registerBookCode);
				//dossier.setRegisterBookName(registerBookName);
				dossier.setSampleCount(sampleCount);
				dossier.setSystemId(input.getSystemId());
				if (Validator.isNotNull(input.getMetaData()))
					dossier.setMetaData(input.getMetaData());

				//Delegate dossier

				if (Validator.isNotNull(input.getDelegateType())) {
					dossier.setDelegateType(input.getDelegateType());
				}
				if (Validator.isNotNull(input.getDocumentNo())) {
					dossier.setDocumentNo(input.getDocumentNo());
				}
				if (input.getDocumentDate() != null && 0 != input.getDocumentDate()) {
					dossier.setDocumentDate(new Date(input.getDocumentDate()));
				}

				updateDelegateApplicant(dossier, input);

				//if (process != null) {
				//	dossier.setProcessNo(process.getProcessNo());
				//}
				//					dossier = DossierLocalServiceUtil.updateDossier(dossier);
			}
			_log.debug("CREATE DOSSIER 3: " + (System.currentTimeMillis() - start) + " ms");

			if (originality != DossierTerm.ORIGINALITY_LIENTHONG) {
				Applicant applicant = serviceContext.getUserId() > 0 ? ApplicantLocalServiceUtil.fetchByMappingID(serviceContext.getUserId()) : null;
				if (applicant != null) {
					updateApplicantInfo(dossier, applicant.getApplicantIdDate(), applicant.getApplicantIdNo(),
							applicant.getApplicantIdType(), applicant.getApplicantName(), applicant.getAddress(),
							applicant.getCityCode(), applicant.getCityName(), applicant.getDistrictCode(),
							applicant.getDistrictName(), applicant.getWardCode(), applicant.getWardName(),
							applicant.getContactEmail(), applicant.getContactTelNo());
				}
			}
			if (Validator.isNull(dossier)) {
				throw new NotFoundException("Cant add DOSSIER");
			}

			_log.debug("CREATE DOSSIER 4: " + (System.currentTimeMillis() - start) + " ms");
			//Create DossierMark
			_log.debug("flagOldDossier: " + flagOldDossier);
			_log.debug("originality: " + originality);
			if ((originality == DossierTerm.ORIGINALITY_MOTCUA || originality == DossierTerm.ORIGINALITY_LIENTHONG)
					&& !flagOldDossier) {
				String templateNo = dossier.getDossierTemplateNo();
				_log.debug("templateNo: " + templateNo);
				if (Validator.isNotNull(templateNo)) {
					List<DossierPart> partList = dossierPartLocalService.getByTemplateNo(groupId, templateNo);
					//						_log.info("partList: "+partList);
					if (partList != null && partList.size() > 0) {
						_log.debug("partList.size(): " + partList.size());
						_log.debug("CREATE DOSSIER 4.1: " + (System.currentTimeMillis() - start) + " ms");
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList
								.size()];
						int count = 0;
						List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId,
								dossier.getDossierId());
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
			List<ServiceProcessRole> lstProcessRoles = serviceProcessRoleLocalService
					.findByS_P_ID(process.getServiceProcessId());
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
				//Check if have DOSSIER-01 template
				Notificationtemplate dossierTemplate = null;
				try {
					dossierTemplate = NotificationtemplateLocalServiceUtil
					.fetchByF_NotificationtemplateByType(groupId, NotificationType.DOSSIER_01);
				} catch (Exception e) {
					// TODO: handle exception
					_log.debug(e);
				}
				if (dossierTemplate != null) {
					long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());

					NotificationQueue queue = NotificationQueueLocalServiceUtil
							.createNotificationQueue(notificationQueueId);
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
					if (isEmailNotify(dossier)) {
						queue.setToEmail(dossier.getContactEmail());
					}
					if (isSmsNotify(dossier)) {
						queue.setToTelNo(dossier.getContactTelNo());
					}

					JSONObject payload = JSONFactoryUtil.createJSONObject();
					try {
						//							_log.info("START PAYLOAD: ");
						payload.put(KeyPayTerm.DOSSIER,
								JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(dossier)));
					} catch (JSONException parse) {
						_log.error(parse);
					}
					//						_log.info("payloadTest: "+payload.toJSONString());
					queue.setPayload(payload.toJSONString());
					queue.setExpireDate(cal.getTime());

					NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
				}
			}

			_log.debug("CREATE DOSSIER 6: " + (System.currentTimeMillis() - start) + " ms");
			//Add to dossier user based on service process role
			createDossierUsers(groupId, dossier, process, lstProcessRoles);
			if (Validator.isNotNull(input.getVnpostalStatus())) {
				dossier.setVnpostalStatus(input.getVnpostalStatus());
			}
			if (Validator.isNotNull(input.getVnpostalProfile())) {
				dossier.setVnpostalProfile(input.getVnpostalProfile());
			}
			if (Validator.isNotNull(input.getFromViaPostal())) {
				dossier.setFromViaPostal(input.getFromViaPostal());
			}
			if (Validator.isNotNull(input.getServerNo())) {
				dossier.setServerNo(input.getServerNo());
			}
			_log.debug("CREATE DOSSIER 7: " + (System.currentTimeMillis() - start) + " ms");
			dossierLocalService.updateDossier(dossier);
			_log.debug("CREATE DOSSIER 8: " + (System.currentTimeMillis() - start) + " ms");

			//Check verification applicant
			Applicant checkApplicant = user.getUserId() > 0 ? ApplicantLocalServiceUtil.fetchByMappingID(user.getUserId()) : null;
			if (checkApplicant != null) {
				int countDossier = DossierLocalServiceUtil.countByG_UID_DS(groupId, user.getUserId(),
						DossierTerm.DOSSIER_STATUS_RECEIVING);
				_log.debug("APPLICANT NUMBER OF CREATE DOSSIER: " + countDossier);
				if (countDossier >= DossierTerm.MAX_DOSSIER_WITHOUT_VERIFICATION) {
					if (checkApplicant.getVerification() != ApplicantTerm.UNLOCKED) {
						checkApplicant.setVerification(ApplicantTerm.LOCKED_DOSSIER);
						ApplicantLocalServiceUtil.updateApplicant(checkApplicant);
					}
				}
			}
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
								ServiceProcessRole role = serviceProcessRoleLocalService.fetchServiceProcessRole(pk);
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
		dossierPermission.hasCreateDossier(groupId, user.getUserId(), input.getServiceCode(), input.getGovAgencyCode(),
				input.getDossierTemplateNo());

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
					? GetterUtil.getInteger(jsonDossier.getString(DossierTerm.VIA_POSTAL))
					: 0;
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
			String serviceName = service != null ? service.getServiceName() : StringPool.BLANK;

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
				if (OpenCPSConfigUtil.getDefaultDossierSecretLength() > DossierTerm.PIN_LENGTH) {
					password = PwdGenerator.getPassword(OpenCPSConfigUtil.getDefaultDossierSecretLength(),
							PwdGenerator.KEY1);
				} else {
					password = PwdGenerator.getPinNumber();
				}
			}

			String postalCityName = StringPool.BLANK;
			if (Validator.isNotNull(jsonDossier.getString(DossierTerm.POSTAL_CITY_CODE))) {
				postalCityName = getDictItemName(groupId, VNPOST_CITY_CODE,
						jsonDossier.getString(DossierTerm.POSTAL_CITY_CODE));
			}
			int sampleCount = (option != null ? (int) option.getSampleCount() : 1);

			String registerBookCode = (option != null
					? (Validator.isNotNull(option.getRegisterBookCode()) ? option.getRegisterBookCode()
					: StringPool.BLANK)
					: StringPool.BLANK);
			//String registerBookName = (Validator.isNotNull(registerBookCode)
			//		? getDictItemName(groupId, REGISTER_BOOK, registerBookCode) : StringPool.BLANK);
			String registerBookName = StringPool.BLANK;
			if (Validator.isNotNull(registerBookCode)) {
				DynamicReport report = DynamicReportLocalServiceUtil.fetchByG_CODE(groupId, registerBookCode);
				if (report != null) {
					registerBookName = report.getReportName();
				}
			}
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
			int durationCount = 0;
			if (jsonDossier.has(ServiceProcessTerm.DURATION_COUNT) && jsonDossier.get(ServiceProcessTerm.DURATION_COUNT) != null)
				durationCount = jsonDossier.getInt(ServiceProcessTerm.DURATION_COUNT);
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
			String dossierName = Validator.isNotNull(jsonDossier.getString(DossierTerm.DOSSIER_NAME))
					? jsonDossier.getString(DossierTerm.DOSSIER_NAME)
					: serviceName;

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
					delegateWardName, registerBookCode, registerBookName, sampleCount, dossierName, durationCount, service, process,
					option, serviceContext);

			if (receiveDate != null)
				dossier.setReceiveDate(receiveDate);
			if (dueDate != null)
				dossier.setDueDate(dueDate);
			if (Validator.isNotNull(metaData))
				dossier.setMetaData(metaData);
			//
			dossier.setSystemId(input.getSystemId());

			//TODO: Process then
			//updateDelegateApplicant(dossier, input);
			_log.debug("CREATE DOSSIER 3: " + (System.currentTimeMillis() - start) + " ms");

			if (originality != DossierTerm.ORIGINALITY_LIENTHONG) {
				Applicant applicant = serviceContext.getUserId() > 0 ? ApplicantLocalServiceUtil.fetchByMappingID(serviceContext.getUserId()) : null;
				if (applicant != null) {
					updateApplicantInfo(dossier, applicant.getApplicantIdDate(), applicant.getApplicantIdNo(),
							applicant.getApplicantIdType(), applicant.getApplicantName(), applicant.getAddress(),
							applicant.getCityCode(), applicant.getCityName(), applicant.getDistrictCode(),
							applicant.getDistrictName(), applicant.getWardCode(), applicant.getWardName(),
							applicant.getContactEmail(), applicant.getContactTelNo());
				}
			}
			if (Validator.isNull(dossier)) {
				throw new NotFoundException("Cant add DOSSIER");
			}

			_log.debug("CREATE DOSSIER 4: " + (System.currentTimeMillis() - start) + " ms");
			//TODO
			/** Create DossierMark */
			//_log.debug("flagOldDossier: "+flagOldDossier);
			_log.debug("originality: " + originality);
			String templateNo = dossier.getDossierTemplateNo();
			_log.debug("templateNo: " + templateNo);
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

						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[markArr
								.length()];
						for (int i = 0; i < markArr.length(); i++) {
							JSONObject jsonMark = markArr.getJSONObject(i);

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
						_log.debug("partList.size(): " + partList.size());
						_log.debug("CREATE DOSSIER 4.1: " + (System.currentTimeMillis() - start) + " ms");
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList
								.size()];
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
			List<ServiceProcessRole> lstProcessRoles = serviceProcessRoleLocalService
					.findByS_P_ID(process.getServiceProcessId());
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

				Double durationCountProcess = durationCount > 1 ? durationCount : process.getDurationCount();
				if (Validator.isNotNull(String.valueOf(durationCountProcess)) && durationCountProcess > 0d) {
					//					Date dueDateCal = HolidayUtils.getDueDate(new Date(), process.getDurationCount(),
					//							process.getDurationUnit(), groupId);
					DueDateUtils dueDateUtils = new DueDateUtils(new Date(), durationCountProcess,
							process.getDurationUnit(), groupId);
					Date dueDateCal = dueDateUtils.getDueDate();
					jsonDate.put(DossierTerm.DUE_DATE, dueDateCal != null ? dueDateCal.getTime() : 0);
				}
				if (Validator.isNotNull(jsonDate)) {
					payload = jsonDate.toJSONString();
				}
			}
			//
			ProcessAction proAction = getProcessAction(user.getUserId(), groupId, dossier, actionCode,
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
	public Dossier createDossierFrequency(long groupId, Company company, User user,
										  ServiceContext serviceContext, ProfileInModel input, String actionCode)  throws Exception {
		try{
			ProcessOption option = getProcessOption(input.getServiceCode(), input.getGovAgencyCode(),
					input.getTemplateNo(), groupId);

			long serviceProcessId = 0;
			final Integer NO_LOGISTIC = 0;
			if (option != null) {
				serviceProcessId = option.getServiceProcessId();
			}
			_log.info("----Creating dossier info 1...");
			Dossier dossier = null;
			String referenceUid = input.getRef_code();
			Integer viaPostal = NO_LOGISTIC;

			//Get service process
			ServiceProcess process = null;
			if (option != null) {
				process = serviceProcessLocalService.getServiceProcess(serviceProcessId);
				if (process == null) {
					throw new NotFoundException("Cant find process");
				}
			}

			ServiceInfo service = serviceInfoLocalService.getByCode(groupId, input.getServiceCode());
			String serviceName = service != null ? service.getServiceName() : StringPool.BLANK;

			String govAgencyName = getDictItemName(groupId, GOVERNMENT_AGENCY, input.getGovAgencyCode());
			String password = StringPool.BLANK;
			String postalCityName = StringPool.BLANK;

			int sampleCount = (option != null ? (int) option.getSampleCount() : 1);
			String registerBookCode = (option != null
					? (Validator.isNotNull(option.getRegisterBookCode()) ? option.getRegisterBookCode()
					: StringPool.BLANK)
					: StringPool.BLANK);
			String registerBookName = StringPool.BLANK;

			if (Validator.isNotNull(registerBookCode)) {
				DynamicReport report = DynamicReportLocalServiceUtil.fetchByG_CODE(groupId, registerBookCode);
				if (report != null) {
					registerBookName = report.getReportName();
				}
			}

			_log.info("----Creating dossier info 2...");

			String applicantName = input.getProfileOwner().getName();
			String applicantIdType = String.valueOf(input.getApplicants_type());
			String applicantIdNo = "";
			String address = input.getProfileOwner().getAddress();
			String contactName = "";
			String contactTelNo = input.getProfileOwner().getTel();
			String contactEmail = "";

			String postalServiceCode = "";
			String postalServiceName = "";
			String postalAddress = "";
			String postalCityCode = "";
			String postalDistrictCode = "";
			String postalDistrictName = "";
			String postalWardCode = "";
			String postalWardName = "";
			String postalTelNo = "";
			String applicantNote = "";
			String delegateIdNo = input.getProfileApplicant().getIdentify();
			String delegateName = input.getProfileApplicant().getName();
			String delegateTelNo = input.getProfileApplicant().getTel();
			String delegateEmail = input.getProfileApplicant().getEmail();
			String delegateAddress = input.getProfileApplicant().getAddress();
			Integer durationCount = 0;
			String cityCode = StringPool.BLANK;
			String cityName = StringPool.BLANK;
			String districtCode = StringPool.BLANK;
			String districtName = StringPool.BLANK;
			String wardCode = StringPool.BLANK;
			String wardName = StringPool.BLANK;
			String delegateCityCode = StringPool.BLANK;
			String delegateCityName = StringPool.BLANK;
			String delegateDistrictCode = StringPool.BLANK;
			String delegateDistrictName = StringPool.BLANK;
			String delegateWardCode = StringPool.BLANK;
			String delegateWardName = StringPool.BLANK;
			String dossierName = serviceName;
			DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date receiveDate = formatter.parse(input.getCreation_date());
			Date dueDate     = Validator.isNotNull(input.getAccept_date()) ?
									formatter.parse(input.getAccept_date()) :
									null;

			Integer counter = 0;
			Date appIdDate = null;
			String metaData = StringPool.BLANK;
			boolean online = false;
			Integer originality = DossierTerm.ORIGINALITY_LIENTHONG;

			dossier = dossierLocalService.initMultipleDossier(groupId, 0l, referenceUid, counter,
					input.getServiceCode(), serviceName, input.getGovAgencyCode(), govAgencyName, applicantName,
					applicantIdType, applicantIdNo, appIdDate, address, contactName, contactTelNo, contactEmail,
					input.getTemplateNo(), password, viaPostal, postalServiceCode, postalServiceName,
					postalAddress, postalCityCode, postalCityName, postalDistrictCode, postalDistrictName,
					postalWardCode, postalWardName, postalTelNo, online, process.getDirectNotification(), applicantNote,
					originality, delegateIdNo, delegateName, delegateTelNo, delegateEmail, delegateAddress,
					delegateCityCode, delegateCityName, delegateDistrictCode, delegateDistrictName, delegateWardCode,
					delegateWardName, registerBookCode, registerBookName, sampleCount, dossierName, durationCount, service, process,
					option, serviceContext);

			if (Validator.isNull(dossier)) {
				throw new NotFoundException("Cant add DOSSIER");
			}

			if(Validator.isNotNull(input.getFrom_unit_code()) && !input.getFrom_unit_code().isEmpty()) {
				JSONObject metaDataJson = JSONFactoryUtil.createJSONObject();
				metaDataJson.put("fromUnitCode", input.getFrom_unit_code());
				metaData = metaDataJson.toString();
			}

			if (receiveDate != null)
				dossier.setReceiveDate(receiveDate);
			if (dueDate != null)
				dossier.setDueDate(dueDate);
			if (Validator.isNotNull(metaData))
				dossier.setMetaData(metaData);
			if (Validator.isNotNull(address))
				dossier.setAddress(address);
			if (Validator.isNotNull(cityCode))
				dossier.setCityCode(cityCode);
			if (Validator.isNotNull(cityName))
				dossier.setCityName(cityName);
			if (Validator.isNotNull(districtCode))
				dossier.setDistrictCode(districtCode);
			if (Validator.isNotNull(districtName))
				dossier.setDistrictName(districtName);
			if (Validator.isNotNull(wardCode))
				dossier.setWardCode(wardCode);
			if (Validator.isNotNull(wardName))
				dossier.setWardName(wardName);

			dossier.setSystemId(0);
			_log.info("----Creating dossier info 3...");
			List<DossierUser> lstDus = dossierUserLocalService.findByDID(dossier.getDossierId());
			List<ServiceProcessRole> lstProcessRoles = serviceProcessRoleLocalService
					.findByS_P_ID(process.getServiceProcessId());
			if (lstDus.size() == 0) {
				DossierUserActions duActions = new DossierUserActionsImpl();
				duActions.initDossierUser(groupId, dossier, process, lstProcessRoles);
			}

			_log.info("----Creating dossier info 4...");
			createDossierUsers(groupId, dossier, process, lstProcessRoles);
			_log.info("----Creating dossier info 5...");
			dossierLocalService.updateDossier(dossier);
			_log.info("----Creating dossier info 6...");
			ObjectMapper objMapper = new ObjectMapper();
			ProcessAction proAction = getProcessAction(user.getUserId(), groupId, dossier, actionCode,
					serviceProcessId);
			doAction(groupId, serviceContext.getUserId(), dossier, option, proAction, actionCode, StringPool.BLANK, StringPool.BLANK,
					StringPool.BLANK, StringPool.BLANK, objMapper.writeValueAsString(input.getPayment()), 0, serviceContext);

			return dossier;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
	public FrequencyDoAction updateDossierFrequencyAction(long groupId, ServiceContext serviceContext, Dossier dossier,
														  ProfileInModel input, String actionCode) throws Exception{
		try {
			_log.info("Doing action frequency with actionCode: " + actionCode + "...");

			ObjectMapper objMapper = new ObjectMapper();
			ProcessAction proAction = null;
			ProcessAction processActionCurrent = null;
			ProcessOption option = null;
			DossierAction dossierActionResult;
			Integer syncType = 0;
			ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
			long serviceProcessId = 0;
			if(Validator.isNotNull(actConfig)) {
				_log.info("---Act config is valid");
				syncType = actConfig.getSyncType();
				boolean insideProcess = actConfig.getInsideProcess();
				option = getProcessOption(dossier.getServiceCode(), dossier.getGovAgencyCode(),
						dossier.getDossierTemplateNo(), groupId);
				if(insideProcess) {
					_log.info("---Inside process");
					if(dossier.getDossierActionId() == 0) {
						_log.info("---Dossier action id = 0");
						if(Validator.isNotNull(option)) {
							_log.info("---Option not null with process option id: " + option.getProcessOptionId());
							serviceProcessId = option.getServiceProcessId();
							proAction = getProcessAction(serviceContext.getUserId(), groupId, dossier, actionCode,
									serviceProcessId);
						}
					} else {
						_log.info("---Dossier action id = " + dossier.getDossierActionId());
						//dossierActionId in dossier = 0
						DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
						if(Validator.isNotNull(dossierAction)) {
							_log.info("---Dossier action is not null");
							serviceProcessId = dossierAction.getServiceProcessId();
							DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getByTemplateNo(groupId,
											dossier.getDossierTemplateNo());
							if(Validator.isNotNull(dossierTemplate)) {
								_log.info("---Dossier template is not null");
								option = ProcessOptionLocalServiceUtil.fetchBySP_DT(serviceProcessId,
										dossierTemplate.getDossierTemplateId());
								proAction = getProcessAction(serviceContext.getUserId(), groupId, dossier, actionCode,
										serviceProcessId);
							}
						}
					}
				} else {
					_log.info("---Notttt Inside process");
					proAction = null;
				}
			} else {
				_log.info("---Act config is nullllll");
				option = getProcessOption(dossier.getServiceCode(), dossier.getGovAgencyCode(),
						dossier.getDossierTemplateNo(), groupId);
				if(Validator.isNotNull(option)) {
					_log.info("---Option is not null");
					serviceProcessId = option.getServiceProcessId();
					proAction = getProcessAction(serviceContext.getUserId(), groupId, dossier, actionCode,
							serviceProcessId);
				}
			}

			if(Validator.isNull(option)) {
				_log.info("-----WARNING: Option is null");
			}

			if(Validator.isNull(proAction)) {
				_log.info("-----WARNING: Pro Action is null");
			}

			processActionCurrent = proAction;

			if(Validator.isNull(input.getPayment())) {
				dossierActionResult = doAction(groupId, serviceContext.getUserId(), dossier, option, proAction, actionCode, StringPool.BLANK, StringPool.BLANK,
						StringPool.BLANK, StringPool.BLANK, null, syncType, serviceContext);
			} else {
				dossierActionResult = doAction(groupId, serviceContext.getUserId(), dossier, option, proAction, actionCode, StringPool.BLANK, StringPool.BLANK,
						StringPool.BLANK, StringPool.BLANK, objMapper.writeValueAsString(input.getPayment()), syncType, serviceContext);
			}

			if(Validator.isNull(dossierActionResult)) {
				throw new Exception("Do action fail with actionCode: " + actionCode);
			}

			FrequencyDoAction frequencyDoAction = new FrequencyDoAction();
			frequencyDoAction.setDossierAction(dossierActionResult);
			frequencyDoAction.setProcessAction(processActionCurrent);
			_log.info("Done action frequency with actionCode: " + actionCode + "...");
			return frequencyDoAction;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
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
								ServiceProcessRole role = serviceProcessRoleLocalService.fetchServiceProcessRole(pk);
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
		dossierPermission.hasCreateDossier(groupId, user.getUserId(), input.getServiceCode(), input.getGovAgencyCode(),
				input.getDossierTemplateNo());

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
					? GetterUtil.getInteger(jsonDossier.getString(DossierTerm.VIA_POSTAL))
					: 0;
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
			String serviceName = service != null ? service.getServiceName() : StringPool.BLANK;

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
				if (OpenCPSConfigUtil.getDefaultDossierSecretLength() > DossierTerm.PIN_LENGTH) {
					password = PwdGenerator.getPassword(OpenCPSConfigUtil.getDefaultDossierSecretLength(),
							PwdGenerator.KEY1);
				} else {
					password = PwdGenerator.getPinNumber();
				}
			}

			String postalCityName = StringPool.BLANK;
			if (Validator.isNotNull(jsonDossier.getString(DossierTerm.POSTAL_CITY_CODE))) {
				postalCityName = getDictItemName(groupId, VNPOST_CITY_CODE,
						jsonDossier.getString(DossierTerm.POSTAL_CITY_CODE));
			}
			int sampleCount = (option != null ? (int) option.getSampleCount() : 1);

			String registerBookCode = (option != null
					? (Validator.isNotNull(option.getRegisterBookCode()) ? option.getRegisterBookCode()
					: StringPool.BLANK)
					: StringPool.BLANK);
			//String registerBookName = (Validator.isNotNull(registerBookCode)
			//		? getDictItemName(groupId, REGISTER_BOOK, registerBookCode) : StringPool.BLANK);
			String registerBookName = StringPool.BLANK;
			if (Validator.isNotNull(registerBookCode)) {
				DynamicReport report = DynamicReportLocalServiceUtil.fetchByG_CODE(groupId, registerBookCode);
				if (report != null) {
					registerBookName = report.getReportName();
				}
			}
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
			int durationCount = 0;
			if (jsonDossier.has(ServiceProcessTerm.DURATION_COUNT) && jsonDossier.get(ServiceProcessTerm.DURATION_COUNT) != null) {
				durationCount = GetterUtil.getInteger(jsonDossier.get(ServiceProcessTerm.DURATION_COUNT));
				if (durationCount == 2 || durationCount == 6) durationCount -= 1;
				if (durationCount == 4) durationCount += 1;
			}

			_log.info("durationCount: "+durationCount);
			//TODO
			String cityCode = jsonDossier.getString(DossierTerm.CITY_CODE);
			String cityName = StringPool.BLANK;
			if (Validator.isNotNull(cityCode)) {
				cityName = getDictItemName(groupId, ADMINISTRATIVE_REGION, cityCode);
			}

			String districtCode = jsonDossier.getString(DossierTerm.DISTRICT_CODE);
			String districtName = StringPool.BLANK;
			if (Validator.isNotNull(districtCode)) {
				districtName = getDictItemName(groupId, ADMINISTRATIVE_REGION, districtCode);
			}

			String wardCode = jsonDossier.getString(DossierTerm.WARD_CODE);
			String wardName = StringPool.BLANK;
			if (Validator.isNotNull(wardCode)) {
				wardName = getDictItemName(groupId, ADMINISTRATIVE_REGION, wardCode);
			}
			//
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
			String dossierName = Validator.isNotNull(jsonDossier.getString(DossierTerm.DOSSIER_NAME))
					? jsonDossier.getString(DossierTerm.DOSSIER_NAME)
					: serviceName;

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
					delegateWardName, registerBookCode, registerBookName, sampleCount, dossierName, durationCount, service, process,
					option, serviceContext);

			_log.info("Dossier Duration COUNT: "+dossier.getDurationCount());

			if (receiveDate != null)
				dossier.setReceiveDate(receiveDate);
			if (dueDate != null)
				dossier.setDueDate(dueDate);
			if (Validator.isNotNull(metaData))
				dossier.setMetaData(metaData);
			if (Validator.isNotNull(address))
				dossier.setAddress(address);
			if (Validator.isNotNull(cityCode))
				dossier.setCityCode(cityCode);
			if (Validator.isNotNull(cityName))
				dossier.setCityName(cityName);
			if (Validator.isNotNull(districtCode))
				dossier.setDistrictCode(districtCode);
			if (Validator.isNotNull(districtName))
				dossier.setDistrictName(districtName);
			if (Validator.isNotNull(wardCode))
				dossier.setWardCode(wardCode);
			if (Validator.isNotNull(wardName))
				dossier.setWardName(wardName);

			//
			dossier.setSystemId(input.getSystemId());

			//TODO: Process then
			//updateDelegateApplicant(dossier, input);
			_log.debug("CREATE DOSSIER 3: " + (System.currentTimeMillis() - start) + " ms");

			if (originality != DossierTerm.ORIGINALITY_LIENTHONG) {
				Applicant applicant = serviceContext.getUserId() > 0 ? ApplicantLocalServiceUtil.fetchByMappingID(serviceContext.getUserId()) : null;
				if (applicant != null) {
					updateApplicantInfo(dossier, applicant.getApplicantIdDate(), applicant.getApplicantIdNo(),
							applicant.getApplicantIdType(), applicant.getApplicantName(), applicant.getAddress(),
							applicant.getCityCode(), applicant.getCityName(), applicant.getDistrictCode(),
							applicant.getDistrictName(), applicant.getWardCode(), applicant.getWardName(),
							applicant.getContactEmail(), applicant.getContactTelNo());
				}
			}
			if (Validator.isNull(dossier)) {
				throw new NotFoundException("Cant add DOSSIER");
			}

			_log.debug("CREATE DOSSIER 4: " + (System.currentTimeMillis() - start) + " ms");
			/** Create DossierMark */
			//_log.debug("flagOldDossier: "+flagOldDossier);
			_log.debug("originality: " + originality);
			String templateNo = dossier.getDossierTemplateNo();
			_log.debug("templateNo: " + templateNo);
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

						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[markArr
								.length()];
						for (int i = 0; i < markArr.length(); i++) {
							JSONObject jsonMark = markArr.getJSONObject(i);

							org.opencps.dossiermgt.input.model.DossierMarkBatchModel model = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel();
							model.setDossierId(dossier.getDossierId());
							model.setDossierPartNo(jsonMark.getString("dossierPartNo"));
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
						_log.debug("partList.size(): " + partList.size());
						_log.debug("CREATE DOSSIER 4.1: " + (System.currentTimeMillis() - start) + " ms");
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList
								.size()];
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
						//
						String referenceFileUid = DossierNumberGenerator.generateReferenceUID(groupId);
						JSONObject jsonFile = dossierFileArr.getJSONObject(j);

						boolean eform = Boolean.valueOf(jsonFile.getString("eform"));
						long fileEntryId = 0;
						if(jsonFile.has(ConstantUtils.FILE_ENTRY_ID)) {
							fileEntryId = jsonFile.getLong(ConstantUtils.FILE_ENTRY_ID);
						}
						//DuanTV
						if (eform) {
							//EFORM
							_log.info("In dossier file create by eform");
							try {
								//								String referenceUidFile = UUID.randomUUID().toString();
								String partNo = jsonFile.getString(DossierPartTerm.PART_NO);
								String formData = jsonFile.getString(ConstantUtils.FORM_DATA);
								
								DossierFile dossierFile = null;
								//								DossierFileActions action = new DossierFileActionsImpl();
								DossierPart dossierPart = dossierPartLocalService.fetchByTemplatePartNo(groupId,
										templateNo, partNo);
								//_log.info("__file:" + file);
								//DataHandler dataHandler = (file != null) ? file.getDataHandler() : null;
								dossierFile = DossierFileLocalServiceUtil.getByGID_DID_PART_EFORM(groupId, dossierId,
										partNo, true, false);
								if (dossierFile == null) {
									_log.info("dossierFile NULL");
									dossierFile = dossierFileLocalService.addDossierFileEForm(groupId, dossierId,
											referenceFileUid, templateNo, partNo, dossierPart.getFileTemplateNo(),
											dossierPart.getPartName(), dossierPart.getPartName(), 0, null,
											StringPool.BLANK, "true", serviceContext);

								}

								if (Validator.isNotNull(formData)) {
									dossierFile.setFormData(formData);
								}
								if (Validator.isNotNull(eform)) {
									dossierFile.setEForm(eform);
								}

								_log.info("__Start update dossier file at:" + new Date());
								DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

								dossierFileLocalService.updateFormData(groupId, dossierId,
										dossierFile.getReferenceUid(), formData, serviceContext);
								_log.info("__End update dossier file at:" + new Date());

							} catch (Exception e) {
								_log.debug(e);
							}
						}else {
							//Update by DuanTV
							String partNo = jsonFile.getString(DossierPartTerm.PART_NO);
							DossierPart dossierPart = dossierPartLocalService.fetchByTemplatePartNo(groupId,
									templateNo, partNo);

							DossierFile dossierFile = dossierFileLocalService.addDossierFileEForm(groupId, dossierId,
									referenceFileUid, templateNo, partNo, dossierPart.getFileTemplateNo(),
									dossierPart.getPartName(), dossierPart.getPartName(), 0, null,
									StringPool.BLANK, "true", serviceContext);

							if(fileEntryId > 0) {
								dossierFile.setEForm(false);
								dossierFile.setFileEntryId(fileEntryId);
								//File sinh từ eForm default PDF
								String displayName = dossierFile.getDisplayName();
								dossierFile.setDisplayName(displayName + ".pdf");
								_log.info("Log dossierFile : " + dossierFile.getDossierFileId() + " DisplayName : " + dossierFile.getDisplayName());
								dossierFileLocalService.updateDossierFile(dossierFile);
							}

						}
					}
				}
			}

			/**Create dossier user */
			List<DossierUser> lstDus = dossierUserLocalService.findByDID(dossier.getDossierId());
			List<ServiceProcessRole> lstProcessRoles = serviceProcessRoleLocalService
					.findByS_P_ID(process.getServiceProcessId());
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

				if (jsonDossier.has(ServiceProcessTerm.DURATION_COUNT) && jsonDossier.get(ServiceProcessTerm.DURATION_COUNT) != null) {
					durationCount = GetterUtil.getInteger(jsonDossier.get(ServiceProcessTerm.DURATION_COUNT));
				}
				Double durationCountProcess = durationCount > 1 ? durationCount : process.getDurationCount();
				if (Validator.isNotNull(String.valueOf(durationCountProcess)) && durationCountProcess > 0d) {
					//					Date dueDateCal = HolidayUtils.getDueDate(new Date(), process.getDurationCount(),
					//							process.getDurationUnit(), groupId);
					DueDateUtils dueDateUtils = new DueDateUtils(new Date(), durationCountProcess,
							process.getDurationUnit(), groupId);
					Date dueDateCal = dueDateUtils.getDueDate();
					//Fix dueDate 8h30
					if (dueDateCal != null) {
						Calendar calFix = Calendar.getInstance();
						calFix.setTime(dueDateCal);
						calFix.set(Calendar.HOUR_OF_DAY, 8);
						calFix.set(Calendar.MINUTE, 30);
						//
						dueDateCal = calFix.getTime();
					}

					jsonDate.put(DossierTerm.DUE_DATE, dueDateCal != null ? dueDateCal.getTime() : 0);
				}
				if (Validator.isNotNull(jsonDate)) {
					payload = jsonDate.toJSONString();
				}
			}
			//
			_log.info("dossier 8: " + dossier.getDurationCount());
			ProcessAction proAction = getProcessAction(user.getUserId(), groupId, dossier, actionCode,
					serviceProcessId);
			doAction(groupId, userId, dossier, option, proAction, actionCode, StringPool.BLANK, StringPool.BLANK,
					payload, StringPool.BLANK, input.getPayment(), 0, serviceContext);

		}
		return dossier;
	}

	private void createDossierUsers(long groupId, Dossier dossier, ServiceProcess process,
									List<ServiceProcessRole> lstProcessRoles) {
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
			} else {
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
						dossierUserLocalService.addDossierUser(groupId, dossier.getDossierId(), e.getMappingUserId(),
								moderator, Boolean.FALSE);
					} else {
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

	private void updateApplicantInfo(Dossier dossier, Date applicantIdDate, String applicantIdNo,
									 String applicantIdType, String applicantName, String address, String cityCode, String cityName,
									 String districtCode, String districtName, String wardCode, String wardName, String contactEmail,
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
			if (Validator.isNotNull(it)) {
				return it.getItemName();
			} else {
				return StringPool.BLANK;
			}
		} else {
			return StringPool.BLANK;
		}

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
	public DossierFile addDossierFileFrequency(long groupId,
											   ServiceContext serviceContext, InputStream inputStream, String referenceUid, Dossier dossier,
											   String displayName, String fileType, String isSync,
											   String formData, String removed, String eForm) throws Exception {
		try {
			String dossierPartNo = "TP99";
			String dossierTemplateNo = dossier.getDossierTemplateNo();
			String fileTemplateNo = "";
			List<DossierPart> lstParts = dossierPartLocalService.getByTemplateNo(groupId,
					dossier.getDossierTemplateNo());
			DossierPart dossierPart = null;
			DossierFile dossierFile = null;
			for (DossierPart dp : lstParts) {
				if (dp.getPartNo().equals(dossierPartNo)) {
					fileTemplateNo = dp.getFileTemplateNo();
					break;
				}
			}

			if (inputStream != null) {
				dossierFile = dossierFileLocalService.addDossierFile(groupId, dossier.getDossierId(), referenceUid,
						dossierTemplateNo, dossierPartNo, fileTemplateNo, displayName, displayName, 0,
						inputStream, fileType, isSync, serviceContext);
			} else {
				dossierFile = dossierFileLocalService.addDossierFile(groupId, dossier.getDossierId(), referenceUid,
						dossierTemplateNo, dossierPartNo, fileTemplateNo, displayName, displayName, 0, null,
						fileType, isSync, serviceContext);
			}
			return dossierFile;
		} catch (Exception e) {
			_log.error("Error when add dossier file frequency: " + e.getMessage());
			return null;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception io) {
					_log.error(io);
				}
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
	public DossierFile addDossierFileByDossierId(long groupId, Company company, User user,
												 ServiceContext serviceContext, Attachment file, String id, String referenceUid, String dossierTemplateNo,
												 String dossierPartNo, String fileTemplateNo, String displayName, String fileType, String isSync,
												 String formData, String removed, String eForm, Long modifiedDate)
			throws UnauthenticationException, PortalException, Exception {
		BackendAuth auth = new BackendAuthImpl();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		long dossierId = GetterUtil.getLong(id);
		Dossier dossier = null;

//		if (dossierId != 0) {
//			dossier = dossierLocalService.fetchDossier(dossierId);
//			if (Validator.isNull(dossier)) {
//				dossier = dossierLocalService.getByRef(groupId, id);
//			}
//		} else {
//			dossier = dossierLocalService.getByRef(groupId, id);
//		}
		if (dossierId > 0) {
			dossier = dossierLocalService.fetchDossier(dossierId);
		} else {
			dossier = dossierLocalService.getByDossierNo(groupId, id);
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
		}

		DataHandler dataHandler = (file != null) ? file.getDataHandler() : null;

		long originDossierId = dossier.getOriginDossierId();
		DossierPart dossierPart = null;
		String originDossierTemplateNo = StringPool.BLANK;

		if (originDossierId != 0) {
			//HSLT
			Dossier hsltDossier = dossierLocalService.fetchDossier(dossier.getDossierId());
			dossier = dossierLocalService.fetchDossier(dossier.getOriginDossierId());
			ServiceConfig serviceConfig = serviceConfigLocalService.getBySICodeAndGAC(groupId, dossier.getServiceCode(),
					hsltDossier.getGovAgencyCode());
			List<ProcessOption> lstOptions = processOptionLocalService
					.getByServiceProcessId(serviceConfig.getServiceConfigId());
			originDossierTemplateNo = dossier.getDossierTemplateNo();

			if (serviceConfig != null) {
				if (lstOptions.size() > 0) {
					ProcessOption processOption = lstOptions.get(0);
					DossierTemplate dossierTemplate = dossierTemplateLocalService
							.fetchDossierTemplate(processOption.getDossierTemplateId());
					List<DossierPart> lstParts = dossierPartLocalService.getByTemplateNo(groupId,
							dossierTemplate.getTemplateNo());
					for (DossierPart dp : lstParts) {
						if (dp.getPartNo().equals(dossierPartNo) && dp.getFileTemplateNo().equals(fileTemplateNo)) {
							dossierTemplateNo = dp.getTemplateNo();
							dossierPart = dp;
							break;
						}
					}
				}
			}
		} else {
			List<DossierPart> lstParts = dossierPartLocalService.getByTemplateNo(groupId,
					dossier.getDossierTemplateNo());
			for (DossierPart dp : lstParts) {
				if (dp.getPartNo().equals(dossierPartNo)) {
					fileTemplateNo = dp.getFileTemplateNo();
					dossierTemplateNo = dossier.getDossierTemplateNo();
					dossierPart = dp;
					break;
				}
			}
		}

		if (originDossierId > 0) {
			_log.debug("__Start add file at:" + new Date());
			DossierFile dossierFile = null;
			DossierFile oldDossierFile = null;
			if (Validator.isNotNull(referenceUid)) {
				oldDossierFile = dossierFileLocalService.getByDossierAndRef(dossier.getDossierId(), referenceUid);
			} else if (dossierPart != null && !dossierPart.getMultiple()) {
				//					oldDossierFile = dossierFileLocalService.getByGID_DID_TEMP_PART_EFORM(groupId, dossier.getDossierId(),
				//							dossierTemplateNo, dossierPartNo, false, false);
				oldDossierFile = dossierFileLocalService.getByGID_DID_TEMP_PART_EFORM(groupId, dossier.getDossierId(),
						originDossierTemplateNo, dossierPartNo, false, false);
			}
			if (oldDossierFile != null && modifiedDate != null) {
				if (oldDossierFile.getModifiedDate() != null
						&& oldDossierFile.getModifiedDate().getTime() < modifiedDate) {

					if (dataHandler != null && dataHandler.getInputStream() != null) {
						dossierFile = dossierFileLocalService.updateDossierFile(groupId, dossier.getDossierId(),
								oldDossierFile.getReferenceUid(), displayName, dataHandler.getName(), 0,
								dataHandler.getInputStream(), fileType, isSync, serviceContext);
					} else {
						dossierFile = dossierFileLocalService.updateDossierFile(groupId, dossier.getDossierId(),
								oldDossierFile.getReferenceUid(), displayName, displayName, 0, null, fileType, isSync,
								serviceContext);
					}

					_log.debug("__End add file at:" + new Date());

					if (Validator.isNotNull(formData)) {
						dossierFile.setFormData(formData);
					}
					_log.debug("REMOVED:" + removed);
					if (Validator.isNotNull(removed)) {
						dossierFile.setRemoved(Boolean.parseBoolean(removed));
					}
					if (Validator.isNotNull(eForm)) {
						dossierFile.setEForm(Boolean.parseBoolean(eForm));
					}
					if (Validator.isNull(eForm) || (Validator.isNotNull(eForm) && !Boolean.parseBoolean(eForm))) {
						dossierFile.setFormData(StringPool.BLANK);
						dossierFile.setIsNew(false);
					}
					_log.debug("__Start update dossier file at:" + new Date());

					dossierFileLocalService.updateDossierFile(dossierFile);

					_log.debug("__End update dossier file at:" + new Date());

					_log.debug("__End bind to dossierFile" + new Date());
					return dossierFile;
				} else {
					throw new DataConflictException("Conflict dossier file");
				}
			} else {
				_log.debug("__Start add file at:" + new Date());
				if (dataHandler != null && dataHandler.getInputStream() != null) {
					//						dossierFile = dossierFileLocalService.addDossierFile(groupId, dossier.getDossierId(), referenceUid, dossierTemplateNo,
					//								dossierPartNo, fileTemplateNo, displayName, dataHandler.getName(), 0,
					//								dataHandler.getInputStream(), fileType, isSync, serviceContext);
					dossierFile = dossierFileLocalService.addDossierFile(groupId, dossier.getDossierId(), referenceUid,
							originDossierTemplateNo, dossierPartNo, fileTemplateNo, displayName, dataHandler.getName(),
							0, dataHandler.getInputStream(), fileType, isSync, serviceContext);
				} else {
					//						dossierFile = dossierFileLocalService.addDossierFile(groupId, dossier.getDossierId(), referenceUid, dossierTemplateNo,
					//								dossierPartNo, fileTemplateNo, displayName, displayName, 0, null, fileType, isSync,
					//								serviceContext);
					dossierFile = dossierFileLocalService.addDossierFile(groupId, dossier.getDossierId(), referenceUid,
							originDossierTemplateNo, dossierPartNo, fileTemplateNo, displayName, displayName, 0, null,
							fileType, isSync, serviceContext);
				}

				_log.debug("__End add file at:" + new Date());

				if (Validator.isNotNull(formData)) {
					dossierFile.setFormData(formData);
				}
				if (Validator.isNotNull(removed)) {
					dossierFile.setRemoved(Boolean.parseBoolean(removed));
				}
				if (Validator.isNotNull(eForm)) {
					dossierFile.setEForm(Boolean.parseBoolean(eForm));
				}
				if (Validator.isNull(eForm) || (Validator.isNotNull(eForm) && !Boolean.parseBoolean(eForm))) {
					dossierFile.setFormData(StringPool.BLANK);
					dossierFile.setIsNew(false);
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
			} else if (dossierPart != null && !dossierPart.getMultiple()) {
				oldDossierFile = dossierFileLocalService.getByGID_DID_TEMP_PART_EFORM(groupId, dossier.getDossierId(),
						dossierTemplateNo, dossierPartNo, false, false);
				_log.info("dossierPart.getMultiple: " + dossierPart.getMultiple());
			}
			_log.info("oldDossierFile: " + oldDossierFile);

			if (oldDossierFile != null && modifiedDate != null) {
				if (oldDossierFile.getModifiedDate() != null
						&& oldDossierFile.getModifiedDate().getTime() < modifiedDate) {
					_log.debug("__Start add file at:" + new Date());
					DossierFile dossierFile = null;

					if (dataHandler != null && dataHandler.getInputStream() != null) {
						dossierFile = dossierFileLocalService.updateDossierFile(groupId, dossier.getDossierId(),
								oldDossierFile.getReferenceUid(), displayName, dataHandler.getName(), 0,
								dataHandler.getInputStream(), fileType, isSync, serviceContext);
					} else {
						dossierFile = dossierFileLocalService.updateDossierFile(groupId, dossier.getDossierId(),
								oldDossierFile.getReferenceUid(), displayName, displayName, 0, null, fileType, isSync,
								serviceContext);
					}

					_log.debug("__End add file at:" + new Date());

					if (Validator.isNotNull(formData)) {
						dossierFile.setFormData(formData);
					}
					if (Validator.isNotNull(removed)) {
						dossierFile.setRemoved(Boolean.parseBoolean(removed));
					}
					if (Validator.isNotNull(eForm)) {
						dossierFile.setEForm(Boolean.parseBoolean(eForm));
					}
					if (Validator.isNull(eForm) || (Validator.isNotNull(eForm) && !Boolean.parseBoolean(eForm))) {
						dossierFile.setFormData(StringPool.BLANK);
						dossierFile.setIsNew(false);
					}
					_log.debug("__Start update dossier file at:" + new Date());

					DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

					_log.debug("__End update dossier file at:" + new Date());

					_log.debug("__End bind to dossierFile" + new Date());
					return dossierFile;
				} else {
					throw new DataConflictException("Conflict dossier file");
				}
			} else {
				_log.debug("__Start add file at:" + new Date());
				DossierFile dossierFile = null;

				if (dataHandler != null && dataHandler.getInputStream() != null) {
					dossierFile = dossierFileLocalService.addDossierFile(groupId, dossier.getDossierId(), referenceUid,
							dossierTemplateNo, dossierPartNo, fileTemplateNo, displayName, dataHandler.getName(), 0,
							dataHandler.getInputStream(), fileType, isSync, serviceContext);
				} else {
					dossierFile = dossierFileLocalService.addDossierFile(groupId, dossier.getDossierId(), referenceUid,
							dossierTemplateNo, dossierPartNo, fileTemplateNo, displayName, displayName, 0, null,
							fileType, isSync, serviceContext);
				}

				_log.debug("__End add file at:" + new Date());

				if (Validator.isNotNull(formData)) {
					dossierFile.setFormData(formData);
				}
				if (Validator.isNotNull(removed)) {
					dossierFile.setRemoved(Boolean.parseBoolean(removed));
				}
				if (Validator.isNotNull(eForm)) {
					dossierFile.setEForm(Boolean.parseBoolean(eForm));
				}
				if (Validator.isNull(eForm) || (Validator.isNotNull(eForm) && !Boolean.parseBoolean(eForm))) {
					dossierFile.setFormData(StringPool.BLANK);
					dossierFile.setIsNew(false);
				}
				_log.debug("__Start update dossier file at:" + new Date());

				dossierFileLocalService.updateDossierFile(dossierFile);

				_log.debug("__End update dossier file at:" + new Date());

				_log.debug("__End bind to dossierFile" + new Date());
				return dossierFile;
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
	public DossierFile updateDossierFile(long groupId, Company company, ServiceContext serviceContext, long id,
										 String referenceUid, Attachment file) throws UnauthenticationException, PortalException, Exception {

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

		DossierFile dossierFile = dossierFileLocalService.updateDossierFile(groupId, id, referenceUid,
				dataHandle.getName(), StringPool.BLANK, dataHandle.getInputStream(), serviceContext);

		return dossierFile;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
	public DossierFile updateDossierFileFormData(long groupId, Company company, ServiceContext serviceContext, long id,
												 String referenceUid, String formdata) throws UnauthenticationException, PortalException, Exception {

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

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
	public DossierFile resetformdataDossierFileFormData(long groupId, Company company, ServiceContext serviceContext,
														long id, String referenceUid, String formdata)
			throws UnauthenticationException, PortalException, Exception {
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

				defaultData = AutoFillFormData.sampleDataBinding(part.getSampleData(), dossier.getDossierId(),
						serviceContext);
				dossierFile = dossierFileLocalService.getByReferenceUid(referenceUid).get(0);
				JSONObject defaultDataObj = JSONFactoryUtil.createJSONObject(defaultData);
				defaultDataObj.put(KeyPayTerm.LicenceNo, dossierFile.getDeliverableCode());
				defaultData = defaultDataObj.toJSONString();
			}

			dossierFile = dossierFileLocalService.updateFormData(groupId, dossier.getDossierId(), referenceUid,
					defaultData, serviceContext);

			String deliverableCode = dossierFile.getDeliverableCode();

			if (Validator.isNotNull(deliverableCode)) {
				Deliverable deliverable = deliverableLocalService.getByCode(deliverableCode);
				deliverableLocalService.deleteDeliverable(deliverable);
			}
		}
		return dossierFile;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
	public PaymentFile createPaymentFileByDossierId(long groupId, ServiceContext serviceContext, String id,
													PaymentFileInputModel input) throws UnauthenticationException, PortalException, Exception {
		long userId = serviceContext.getUserId();

		BackendAuth auth = new BackendAuthImpl();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}
		Dossier dossier = getDossier(id, groupId);
		PaymentFile paymentFile = null;
		if (dossier != null) {
			long dossierId = dossier.getPrimaryKey();

			//			if (!auth.hasResource(serviceContext, PaymentFile.class.getName(), ActionKeys.ADD_ENTRY)) {
			//				throw new UnauthorizationException();
			//			}

			PaymentFile oldPaymentFile = paymentFileLocalService.getByDossierId(groupId, dossier.getDossierId());
			String referenceUid = input.getReferenceUid();
			if (Validator.isNull(referenceUid)) {
				referenceUid = PortalUUIDUtil.generate();
			}

			long paymentAmount = 0;
			if (Validator.isNull(input.getPaymentAmount()) || input.getPaymentAmount() == 0) {
				paymentAmount = GetterUtil.getLong(input.getFeeAmount()) + GetterUtil.getLong(input.getServiceAmount()) +
						GetterUtil.getLong(input.getShipAmount()) - GetterUtil.getLong(input.getAdvanceAmount());
			} else {
				paymentAmount = input.getPaymentAmount();
			}

			if (oldPaymentFile != null) {
				paymentFile = oldPaymentFile;
			} else {
				paymentFile = paymentFileLocalService.createPaymentFiles(userId, groupId, dossierId, referenceUid,
						input.getPaymentFee(), input.getAdvanceAmount(), input.getFeeAmount(), input.getServiceAmount(),
						input.getShipAmount(), paymentAmount, input.getPaymentNote(),
						input.getEpaymentProfile(), input.getBankInfo(), 0, input.getPaymentMethod(), serviceContext);
			}

			if (Validator.isNotNull(input.getInvoiceTemplateNo())) {
				paymentFile.setInvoiceTemplateNo(input.getInvoiceTemplateNo());
			}
			if (Validator.isNotNull(input.getConfirmFileEntryId())) {
				paymentFile.setConfirmFileEntryId(input.getConfirmFileEntryId());
			}
			if (Validator.isNotNull(input.getPaymentStatus())) {
				paymentFile.setPaymentStatus(input.getPaymentStatus());
			}
			if (Validator.isNotNull(input.getEinvoice())) {
				paymentFile.setEinvoice(input.getEinvoice());
			}
			if (input.getPaymentAmount() != null && input.getPaymentAmount() != 0) {
				paymentFile.setPaymentAmount(input.getPaymentAmount());
			} else {
				paymentFile.setPaymentAmount(paymentAmount);
			}
			if (Validator.isNotNull(input.getPaymentMethod())) {
				paymentFile.setPaymentMethod(input.getPaymentMethod());
			}
			if (input.getServiceAmount() != null) {
				paymentFile.setServiceAmount(input.getServiceAmount());
			}
			if (input.getShipAmount() != null) {
				paymentFile.setShipAmount(input.getShipAmount());
			}
			if (input.getAdvanceAmount() != null) {
				paymentFile.setAdvanceAmount(input.getAdvanceAmount());
			}
			if (input.getFeeAmount() != null) {
				paymentFile.setFeeAmount(input.getFeeAmount());
			}
			if (Validator.isNotNull(input.getPaymentNote())) {
				paymentFile.setPaymentNote(input.getPaymentNote());
			}
			if (paymentFile.getApproveDatetime() == null)
				paymentFile.setApproveDatetime(new Date());

			if (Validator.isNotNull(input.getConfirmPayload())) {
				paymentFile.setConfirmPayload(input.getConfirmPayload());
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

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
	public Dossier addDossierPublish(long groupId, Company company, User user, ServiceContext serviceContext,
									 org.opencps.dossiermgt.input.model.DossierPublishModel input)
			throws UnauthenticationException, PortalException, Exception {

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
					StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, Boolean.valueOf(online), false, applicantNote, originality,
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
					input.getDurationUnit(), input.getDossierName(), input.getProcessNo(), input.getMetaData(), input.getVnpostalStatus(), input.getVnpostalProfile(),
					input.getFromViaPostal(), serviceContext);

			return dossier;
		}
		return oldDossier;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
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
								ServiceProcessRole role = serviceProcessRoleLocalService.fetchServiceProcessRole(pk);
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
		dossierPermission.hasCreateDossier(groupId, user.getUserId(), input.getServiceCode(), input.getGovAgencyCode(),
				input.getDossierTemplateNo());

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
		} else if (config != null) {
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
			if (OpenCPSConfigUtil.getDefaultDossierSecretLength() > DossierTerm.PIN_LENGTH) {
				password = PwdGenerator.getPassword(OpenCPSConfigUtil.getDefaultDossierSecretLength(),
						PwdGenerator.KEY1);
			} else {
				password = PwdGenerator.getPinNumber();
			}
		}

		String postalCityName = StringPool.BLANK;
		String postalDistrictName = StringPool.BLANK;

		if (Validator.isNotNull(input.getPostalCityCode())) {
			postalCityName = getDictItemName(groupId, VNPOST_CITY_CODE, input.getPostalCityCode());
		}
		if (Validator.isNotNull(input.getPostalDistrictCode())) {
			postalDistrictName = getDictItemName(groupId, VNPOST_CITY_CODE, input.getPostalDistrictCode());
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
			Dossier dossier = dossierLocalService.initDossier(groupId, 0l, referenceUid, counter,
					input.getServiceCode(), serviceName, input.getGovAgencyCode(), govAgencyName,
					input.getApplicantName(), input.getApplicantIdType(), input.getApplicantIdNo(), appIdDate,
					input.getAddress(), input.getCityCode(), cityName, input.getDistrictCode(), districtName,
					input.getWardCode(), wardName, input.getContactName(), input.getContactTelNo(),
					input.getContactEmail(), input.getDossierTemplateNo(), password, viaPostal,
					input.getPostalAddress(), input.getPostalCityCode(), postalCityName, input.getPostalDistrictCode(), postalDistrictName, input.getPostalTelNo(), online,
					process.getDirectNotification(), input.getApplicantNote(), Integer.valueOf(input.getOriginality()),
					service, process, option, serviceContext);

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
			_log.debug("originality: " + originality);
			String templateNo = dossier.getDossierTemplateNo();
			_log.debug("templateNo: " + templateNo);
			if (Validator.isNotNull(input.getDossierMarkArr())) {
				JSONArray markArr = JSONFactoryUtil.createJSONArray(input.getDossierMarkArr());
				if (markArr != null && markArr.length() > 0) {
					List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId,
							dossier.getDossierId());
					Map<String, DossierMark> mapMarks = new HashMap<>();
					for (DossierMark dm : lstMarks) {
						mapMarks.put(dm.getDossierPartNo(), dm);
					}

					for (int i = 0; i < markArr.length(); i++) {
						JSONObject jsonMark = markArr.getJSONObject(i);
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[markArr
								.length()];

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
					_log.debug("partList.size(): " + partList.size());
					_log.debug("CREATE DOSSIER 4.1: " + (System.currentTimeMillis() - start) + " ms");
					org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList
							.size()];
					int count = 0;
					List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId,
							dossier.getDossierId());
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
			List<ServiceProcessRole> lstProcessRoles = serviceProcessRoleLocalService
					.findByS_P_ID(process.getServiceProcessId());
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
			List<Dossier> oldDossiers = dossierLocalService.getByU_G_GAC_SC_DTNO_DS_O(userId, groupId,
					input.getServiceCode(), input.getGovAgencyCode(), input.getDossierTemplateNo(), StringPool.BLANK,
					Integer.valueOf(input.getOriginality()));

			Dossier dossier = null;

			Dossier oldRefDossier = Validator.isNotNull(input.getReferenceUid())
					? dossierLocalService.getByRef(groupId, input.getReferenceUid())
					: null;

			if (originality == DossierTerm.ORIGINALITY_DVCTT) {
				online = true;
			}
			boolean flagOldDossier = false;
			String registerBookCode = (option != null
					? (Validator.isNotNull(option.getRegisterBookCode()) ? option.getRegisterBookCode()
					: StringPool.BLANK)
					: StringPool.BLANK);
			//String registerBookName = (Validator.isNotNull(registerBookCode) ? getDictItemName(groupId, REGISTER_BOOK, registerBookCode) : StringPool.BLANK);
			String registerBookName = StringPool.BLANK;
			if (Validator.isNotNull(registerBookCode)) {
				DynamicReport report = DynamicReportLocalServiceUtil.fetchByG_CODE(groupId, registerBookCode);
				if (report != null) {
					registerBookName = report.getReportName();
				}
			}
			_log.debug("CREATE DOSSIER 2: " + (System.currentTimeMillis() - start) + " ms");

			if (oldRefDossier != null) {
				dossier = oldRefDossier;
				dossier.setSubmitDate(new Date());
				ServiceProcess serviceProcess = process;

				double durationCount = 0;
				int durationUnit = 0;
				if (serviceProcess != null) {
					durationCount = serviceProcess.getDurationCount();
					durationUnit = serviceProcess.getDurationUnit();
					dossier.setDurationCount(durationCount);
					dossier.setDurationUnit(durationUnit);
				}

				if (durationCount > 0) {
					// Date dueDate = HolidayUtils.getDueDate(new Date(), durationCount, durationUnit, groupId);
					DueDateUtils dueDateUtils = new DueDateUtils(new Date(), durationCount, durationUnit, groupId);
					Date dueDate = dueDateUtils.getDueDate();
					dossier.setDueDate(dueDate);
				}

			} else if (oldDossiers.size() > 0) {
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
				dossier.setPostalDistrictCode(input.getPostalDistrictCode());
				dossier.setPostalDistrictName(postalDistrictName);
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
				if (serviceProcess != null) {
					durationCount = serviceProcess.getDurationCount();
					durationUnit = serviceProcess.getDurationUnit();
					dossier.setDurationCount(durationCount);
					dossier.setDurationUnit(durationUnit);
				}

				if (durationCount > 0) {
					// Date dueDate = HolidayUtils.getDueDate(new Date(), durationCount, durationUnit, groupId);
					DueDateUtils dueDateUtils = new DueDateUtils(new Date(), durationCount, durationUnit, groupId);
					Date dueDate = dueDateUtils.getDueDate();
					dossier.setDueDate(dueDate);
				}

				dossier.setOnline(online);
				if (Validator.isNotNull(input.getDossierName()))
					dossier.setDossierName(input.getDossierName());
				if (serviceProcess != null) {
					dossier.setProcessNo(serviceProcess.getProcessNo());
				}

				//					dossier = DossierLocalServiceUtil.updateDossier(dossier);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				Date appIdDate = null;

				try {
					appIdDate = sdf.parse(input.getApplicantIdDate());

				} catch (Exception e) {
					_log.debug(e);
				}
				dossier = dossierLocalService.initDossier(groupId, 0l, referenceUid, counter, input.getServiceCode(),
						serviceName, input.getGovAgencyCode(), govAgencyName, input.getApplicantName(),
						input.getApplicantIdType(), input.getApplicantIdNo(), appIdDate, input.getAddress(),
						input.getCityCode(), cityName, input.getDistrictCode(), districtName, input.getWardCode(),
						wardName, input.getContactName(), input.getContactTelNo(), input.getContactEmail(),
						input.getDossierTemplateNo(), password, viaPostal, input.getPostalAddress(),
						input.getPostalCityCode(), postalCityName, input.getPostalDistrictCode(), postalDistrictName, input.getPostalTelNo(), online,
						process.getDirectNotification(), input.getApplicantNote(),
						Integer.valueOf(input.getOriginality()), service, process, option, serviceContext);
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
				Applicant applicant = serviceContext.getUserId() > 0 ? ApplicantLocalServiceUtil.fetchByMappingID(serviceContext.getUserId()) : null;
				if (applicant != null) {
					updateApplicantInfo(dossier, applicant.getApplicantIdDate(), applicant.getApplicantIdNo(),
							applicant.getApplicantIdType(), applicant.getApplicantName(), applicant.getAddress(),
							applicant.getCityCode(), applicant.getCityName(), applicant.getDistrictCode(),
							applicant.getDistrictName(), applicant.getWardCode(), applicant.getWardName(),
							applicant.getContactEmail(), applicant.getContactTelNo());
				}
			}
			if (Validator.isNull(dossier)) {
				throw new NotFoundException("Cant add DOSSIER");
			}

			_log.debug("CREATE DOSSIER 4: " + (System.currentTimeMillis() - start) + " ms");
			//Create DossierMark
			_log.debug("flagOldDossier: " + flagOldDossier);
			_log.debug("originality: " + originality);
			if ((originality == DossierTerm.ORIGINALITY_MOTCUA || originality == DossierTerm.ORIGINALITY_LIENTHONG)
					&& !flagOldDossier) {
				String templateNo = dossier.getDossierTemplateNo();
				_log.debug("templateNo: " + templateNo);
				if (Validator.isNotNull(templateNo)) {
					List<DossierPart> partList = dossierPartLocalService.getByTemplateNo(groupId, templateNo);
					//						_log.info("partList: "+partList);
					if (partList != null && partList.size() > 0) {
						_log.debug("partList.size(): " + partList.size());
						_log.debug("CREATE DOSSIER 4.1: " + (System.currentTimeMillis() - start) + " ms");
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList
								.size()];
						int count = 0;
						List<DossierMark> lstMarks = dossierMarkLocalService.getDossierMarks(groupId,
								dossier.getDossierId());
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
			List<ServiceProcessRole> lstProcessRoles = serviceProcessRoleLocalService
					.findByS_P_ID(process.getServiceProcessId());
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

				NotificationQueue queue = NotificationQueueLocalServiceUtil
						.createNotificationQueue(notificationQueueId);
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
				if (isEmailNotify(dossier)) {
					queue.setToEmail(dossier.getContactEmail());
				}
				if (isSmsNotify(dossier)) {
					queue.setToTelNo(dossier.getContactTelNo());
				}

				JSONObject payload = JSONFactoryUtil.createJSONObject();
				try {
					//						_log.info("START PAYLOAD: ");
					payload.put("Dossier", JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(dossier)));
				} catch (JSONException parse) {
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

	public static final String GOVERNMENT_AGENCY = ReadFilePropertiesUtils.get(ConstantUtils.GOVERNMENT_AGENCY);
	public static final String ADMINISTRATIVE_REGION = ReadFilePropertiesUtils
			.get(ConstantUtils.VALUE_ADMINISTRATIVE_REGION);
	public static final String VNPOST_CITY_CODE = ReadFilePropertiesUtils.get(ConstantUtils.VNPOST_CITY_CODE);
	public static final String REGISTER_BOOK = ReadFilePropertiesUtils.get(ConstantUtils.REGISTER_BOOK);

	private static final long VALUE_CONVERT_DATE_TIMESTAMP = 1000 * 60 * 60 * 24;
	private static final long VALUE_CONVERT_HOUR_TIMESTAMP = 1000 * 60 * 60;

	private static ProcessAction getProcessAction(long userId, long groupId, Dossier dossier, String actionCode,
												  long serviceProcessId) throws PortalException {

		//_log.debug("GET PROCESS ACTION____");
		ProcessAction action = null;
		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		User systemUser = UserLocalServiceUtil.fetchUser(userId);
		try {
			List<ProcessAction> actions = ProcessActionLocalServiceUtil.getByActionCode(groupId, actionCode,
					serviceProcessId);

			//_log.debug("GET PROCESS ACTION____" + groupId + "," + actionCode + "," + serviceProcessId);

			String dossierStatus = dossier.getDossierStatus();
			String dossierSubStatus = dossier.getDossierSubStatus();
			String preStepCode;
			String curStepCode = StringPool.BLANK;
			if (dossier.getDossierActionId() > 0) {
				DossierAction curAction = DossierActionLocalServiceUtil
						.fetchDossierAction(dossier.getDossierActionId());
				if (curAction != null) {
					curStepCode = curAction.getStepCode();
				}
			}
			for (ProcessAction act : actions) {

				preStepCode = act.getPreStepCode();
				//_log.debug("LamTV_preStepCode: "+preStepCode);
				if (Validator.isNotNull(curStepCode) && !preStepCode.contentEquals(curStepCode))
					continue;

				ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(preStepCode, groupId, serviceProcessId);
				//				_log.info("LamTV_ProcessStep: "+step);

				if (Validator.isNull(step) && dossierAction == null) {
					action = act;
					break;
				} else {
					String stepStatus = step != null ? step.getDossierStatus() : StringPool.BLANK;
					String stepSubStatus = step != null ? step.getDossierSubStatus() : StringPool.BLANK;
					boolean flagCheck = false;

					if (dossierAction != null) {
						if (act.getPreStepCode().equals(dossierAction.getStepCode())) {
							flagCheck = true;
						}
					} else {
						flagCheck = true;
					}
					//_log.debug("LamTV_preStepCode: "+stepStatus + "," + stepSubStatus + "," + dossierStatus + "," + dossierSubStatus + "," + act.getPreCondition() + "," + flagCheck);
					if (stepStatus.contentEquals(dossierStatus)
							&& StringUtil.containsIgnoreCase(stepSubStatus, dossierSubStatus) && flagCheck) {
						if (Validator.isNotNull(act.getPreCondition()) && DossierMgtUtils.checkPreCondition(
								act.getPreCondition().split(StringPool.COMMA), dossier, systemUser)) {
							action = act;
							break;
						} else if (Validator.isNull(act.getPreCondition())) {
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

	private JSONObject getFileAttachMailForApplicant(Dossier dossier, ProcessAction proAction) {
		//		processAction
		//		returnDossierFile
		JSONObject filesAttach = JSONFactoryUtil.createJSONObject();
		JSONArray files = JSONFactoryUtil.createJSONArray();
		JSONArray documents = JSONFactoryUtil.createJSONArray();
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		_log.debug("============getFileAttachMailForApplicant=================");
		try {
			List<String> returnFileTempNoList = ListUtil.toList(StringUtil.split(proAction.getReturnDossierFiles()));
			_log.debug("==========proAction.getReturnDossierFiles()===========" + proAction.getReturnDossierFiles());
			if (returnFileTempNoList.size() > 0) {

				List<DossierFile> dossierFiles = dossierFileLocalService.getDossierFilesByD_DP(dossier.getDossierId(),
						DossierPartTerm.DOSSIER_PART_TYPE_OUTPUT);
				boolean returnDocument = true;
				for (DossierFile dossierFile : dossierFiles) {
					// TODO: xu ly loc dossierFIle de dinh kem mail thong bao bo sung
					_log.info("================DOSSIERFILE=============" + dossierFile.getFileEntryId());
					if (returnFileTempNoList.indexOf(dossierFile.getFileTemplateNo()) >= 0) {
						_log.debug("=============dossierFile.getFileTemplateNo()================");
						FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
						if (fileEntry != null) {
							jsonObject = JSONFactoryUtil.createJSONObject();
							jsonObject.put("name", fileEntry.getFileName());
							jsonObject.put("url", "documents/" + fileEntry.getGroupId() + StringPool.FORWARD_SLASH
									+ fileEntry.getFolderId() + StringPool.FORWARD_SLASH + fileEntry.getTitle());
							files.put(jsonObject);
							returnDocument = false;
						}
					}
				}

				if (returnDocument) {
					jsonObject = JSONFactoryUtil.createJSONObject();
					jsonObject.put("name", proAction.getActionName());
					jsonObject.put("url", "o/rest/v2/dossiers/documentpreviews?dossierId=" + dossier.getDossierId() +
							"&documentTypeCode=" + proAction.getReturnDossierFiles());
					files.put(jsonObject);
					returnDocument = true;
				}
				/**
				List<DossierDocument> dossierDocuments = DossierDocumentLocalServiceUtil
						.getDossierDocumentList(dossier.getDossierId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

				for (DossierDocument dossierDocument : dossierDocuments) {
					// TODO: xu ly loc dossierDocument de dinh kem mail thong bao bo sung
					_log.info("================dossierDocument=============" + dossierDocument.getDocumentFileId());
					if (returnFileTempNoList.indexOf(dossierDocument.getDocumentType()) >= 0) {
						_log.info("================dossierDocument.getDocumentType()=============");
						FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierDocument.getDocumentFileId());
						if (fileEntry != null) {
							jsonObject = JSONFactoryUtil.createJSONObject();
							jsonObject.put("name", fileEntry.getFileName());
							jsonObject.put("url", "documents/" + fileEntry.getGroupId() + StringPool.FORWARD_SLASH
									+ fileEntry.getFolderId() + StringPool.FORWARD_SLASH + fileEntry.getTitle());
							documents.put(jsonObject);
						}
					}
				}
				*/
			}
		} catch (Exception e) {
			_log.error(e);
			e.printStackTrace();
		}
		filesAttach.put("dossierFiles", files);
		filesAttach.put("dossierDocuments", documents);
		return filesAttach;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SystemException.class, PortalException.class,
			Exception.class })
	public Dossier eparPublish(long groupId, Company company, User user, ServiceContext serviceContext, long id,
							   org.opencps.dossiermgt.input.model.DossierPublishModel input)
			throws UnauthenticationException, PortalException, Exception {

		BackendAuth auth = new BackendAuthImpl();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		//			String referenceUid = input.getReferenceUid();
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

		Dossier oldDossier = null;
		oldDossier = DossierLocalServiceUtil.fetchDossier(id);
		if (oldDossier != null && oldDossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT) {
			Date appIdDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat(APIDateTimeUtils._NORMAL_DATE);
			try {
				appIdDate = sdf.parse(applicantIdDate);
			} catch (Exception e) {
				_log.debug(e);
			}

			Dossier dossier = dossierLocalService.eparPublishDossier(groupId, 0l, oldDossier.getReferenceUid(), counter,
					serviceCode, serviceName, govAgencyCode, govAgencyName, applicantName, applicantType, applicantIdNo,
					appIdDate, address, cityCode, cityName, districtCode, districtName, wardCode, wardName, contactName,
					contactTelNo, contactEmail, dossierTemplateNo, password, 0, StringPool.BLANK, StringPool.BLANK,
					StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, Boolean.valueOf(online), false, applicantNote, originality,
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
					input.getDurationUnit(), input.getDossierName(), input.getProcessNo(), input.getMetaData(),
					serviceContext);

			return dossier;
		}
		return oldDossier;
	}

	private String getDossierMetaKeyDateOption (Dossier dossier, Date dueDate, Date receiveDate, double duration, int dateOption, String dueDatePattern) {

		try {
			JSONObject metaData = Validator.isNotNull(dossier.getMetaData()) ?
					JSONFactoryUtil.createJSONObject(dossier.getMetaData()) :
					JSONFactoryUtil.createJSONObject();
			String dueDateStr = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(dueDate);
			String receiveDateStr = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(receiveDate);

			JSONObject phase = JSONFactoryUtil.createJSONObject();
			JSONArray dueDatePhases = JSONFactoryUtil.createJSONObject(dueDatePattern).getJSONArray("dueDatePhase");
			switch (dateOption) {
			case 11:
				phase = dueDatePhases.getJSONObject(0);
				break;
			case 12:
				phase = dueDatePhases.getJSONObject(1);
				break;
			case 13:
				phase = dueDatePhases.getJSONObject(2);
				break;

			default:
				break;
			}

			metaData.put(DossierTerm.DATE_OPTION + dateOption, dueDateStr);
			metaData.put(DossierTerm.DATE_OPTION_RECEIVER + dateOption, receiveDateStr);
			metaData.put(DossierTerm.DATE_OPTION_DURATION + dateOption, duration);
			metaData.put(DossierTerm.WORK_DAY + dateOption, phase.getDouble("workDay", 0D) );
			metaData.put(DossierTerm.ALL_DAY + dateOption, phase.getInt("allDay", 0));
			_log.info("===============metaData==========" +metaData);
			return metaData.toJSONString();
		} catch (Exception e) {
			_log.debug(e);
		}
		return StringPool.BLANK;
	}
	private Dossier setDossierNoNDueDate(Dossier dossier, ServiceProcess serviceProcess, ProcessOption option,
										 boolean setDossierNo, boolean setDueDate, Date dueDateStart, LinkedHashMap<String, Object> params) {

		if (setDueDate) {
			Double durationCount = serviceProcess.getDurationCount();
			int durationUnit = serviceProcess.getDurationUnit();
			Date dueDate = null;
			if (Validator.isNotNull(durationCount) && durationCount > 0 && !areEqualDouble(durationCount, 0.00d, 3)) {
				// dueDate = HolidayUtils.getDueDate(now, durationCount, durationUnit,
				// dossier.getGroupId());
				DueDateUtils dueDateUtils = new DueDateUtils(dueDateStart, durationCount, durationUnit,
						dossier.getGroupId());
				dueDate = dueDateUtils.getDueDate();
			}

			if (Validator.isNotNull(dueDate)) {
				dossier.setDueDate(dueDate);
				//					DossierLocalServiceUtil.updateDueDate(dossier.getGroupId(), dossier.getDossierId(), dossier.getReferenceUid(), dueDate, context);
				// bResult.put(DossierTerm.DUE_DATE, true);
			}

			dossier.setDurationCount(durationCount);
			dossier.setDurationUnit(durationUnit);
		}

		if (setDossierNo) {
			if (dossier.getCounter() == 0 && Validator.isNotNull(dossier.getRegisterBookCode())) {
				long counterCode = DossierNumberGenerator.countByRegiterBookCode(dossier.getGroupId(),
						dossier.getRegisterBookCode(), dossier.getGovAgencyCode());
				dossier.setCounter(counterCode);
			}

			if (Validator.isNull(dossier.getDossierNo())) {
				try {
					String dossierRef = DossierNumberGenerator.generateDossierNumber(dossier.getGroupId(),
							dossier.getCompanyId(), dossier.getDossierId(), option.getProcessOptionId(),
							serviceProcess.getDossierNoPattern(), params);
					dossier.setDossierNo(dossierRef.trim());
				} catch (Exception e) {
					_log.debug(e);
				}
			}
			if (Validator.isNull(dossier.getDossierCounter()) && Validator.isNotNull(serviceProcess.getCounterCode())) {
				ConfigCounter counterConfig = ConfigCounterLocalServiceUtil.fetchByCountrCode(dossier.getGroupId(),
						serviceProcess.getCounterCode());
				if (counterConfig != null) {
					if (Validator.isNotNull(counterConfig.getStartCounter()) && counterConfig.getStartCounter() > 0) {
						String patternCode = counterConfig.getPatternCode();
						try {
							_log.info("dossierId: " + dossier.getDossierId() + "| option.getProcessOptionId(): "
									+ option.getProcessOptionId() + "| serviceProcess.getCounterCode(): "
									+ serviceProcess.getCounterCode() + "| getStartCounter(): "
									+ counterConfig.getStartCounter());
							String dossierCounter = ConfigCounterNumberGenerator.generateCounterNumber(
									dossier.getGroupId(), dossier.getCompanyId(), dossier.getDossierId(),
									option.getProcessOptionId(), patternCode, counterConfig, params);
							dossier.setDossierCounter(dossierCounter.trim());
							_log.info("dossierCounter: " + dossierCounter);
						} catch (Exception e) {
							_log.debug(e);
						}
					} else {
						String patternCode = counterConfig.getPatternCode();
						try {
							_log.info("dossierId: " + dossier.getDossierId() + "| option.getProcessOptionId(): "
									+ option.getProcessOptionId() + "| serviceProcess.getCounterCode(): "
									+ serviceProcess.getCounterCode());
							String dossierCounter = ConfigCounterNumberGenerator.generateCounterNumber(
									dossier.getGroupId(), dossier.getCompanyId(), dossier.getDossierId(),
									option.getProcessOptionId(), patternCode, counterConfig, params);
							dossier.setDossierCounter(dossierCounter.trim());
							_log.info("dossierCounter: " + dossierCounter);
						} catch (Exception e) {
							_log.debug(e);
						}
					}
				}
			}
		}
		return dossier;
	}

	private Date getDueDateByPayload (String payload) {
		try {
			if (Validator.isNotNull(payload)) {
				JSONObject payloadJ = JSONFactoryUtil.createJSONObject(payload);
				long dueDate = payloadJ.getLong(DossierTerm.DUE_DATE);
				if (dueDate > 0) {
					return new Date(dueDate);
				}
			}
		} catch (Exception e) {
			_log.debug(e);
		}
		return null;
	}
	private Date getReceiveDateByPayload (String payload) {
		try {
			if (Validator.isNotNull(payload)) {
				JSONObject payloadJ = JSONFactoryUtil.createJSONObject(payload);
				long dueDate = payloadJ.getLong(DossierTerm.RECEIVE_DATE);
				if (dueDate > 0) {
					return new Date(dueDate);
				}
			}
		} catch (Exception e) {
			_log.debug(e);
		}
		return new Date();
	}
	private long getDurationByPayload (String payload) {
		try {
			if (Validator.isNotNull(payload)) {
				JSONObject payloadJ = JSONFactoryUtil.createJSONObject(payload);
				return payloadJ.has(ProcessActionTerm.DURATION_PHASE) ? 
					payloadJ.getLong(ProcessActionTerm.DURATION_PHASE, 0) :
					payloadJ.getLong(DossierTerm.DURATION_COUNT, 0);
			}
		} catch (Exception e) {
			_log.debug(e);
		}
		return 0;
	}

	private JSONObject verifyPayloadMail (JSONObject payload) {
		Iterator payloadKeys = payload.keys();
		String tmp_key;
		JSONObject result = payload;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			while (payloadKeys.hasNext()) {
				tmp_key = (String) payloadKeys.next();
				if (tmp_key.toLowerCase().indexOf("date") >= 0 && payload.getLong(tmp_key) > 0) {
					result.put(tmp_key + "Str", sdf.format(new Date(payload.getLong(tmp_key))));
				}
			}
		} catch (Exception e) {
			_log.debug(e);
		}
		return result;
	}

	private static Log _log = LogFactoryUtil.getLog(CPSDossierBusinessLocalServiceImpl.class);
}