package org.opencps.dossiermgt.action.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.util.DossierContentGenerator;
import org.opencps.dossiermgt.action.util.DossierNumberGenerator;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierSyncLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.opencps.usermgt.service.util.OCPSUserUtils;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.NotFoundException;

public class DossierActionsImpl implements DossierActions {

	public static final String SPECIAL_ACTION = "1100";
	public static final String AUTO_EVENT_SUBMIT = "submit";
	public static final String AUTO_EVENT_TIMMER = "timer";
	public static final String DOSSIER_SATUS_DC_CODE = "DOSSIER_STATUS";
	public static final String DOSSIER_SUB_SATUS_DC_CODE = "DOSSIER_SUB_STATUS";

	@Override
	public JSONObject getDossiers(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DossierLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DossierLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;

	}

	@Override
	public Dossier addDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, Date applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String dossierNote,
			String submissionNote, String applicantNote, String briefNote, String dossierNo, boolean submitting,
			Date correctingDate, String dossierStatus, String dossierStatusText, String dossierSubStatus,
			String dossierSubStatusText, long folderId, long dossierActionId, int viaPostal, String postalAddress,
			String postalCityCode, String postalCityName, String postalTelNo, String password, boolean notification,
			boolean online, String serverNo, ServiceContext context) throws PortalException {

		Dossier dossier = DossierLocalServiceUtil.updateDossier(groupId, dossierId, referenceUid, counter, serviceCode,
				serviceName, govAgencyCode, govAgencyName, applicantName, applicantIdType, applicantIdNo,
				applicantIdDate, address, cityCode, cityName, districtCode, districtName, wardCode, wardName,
				contactName, contactTelNo, contactEmail, dossierTemplateNo, dossierNote, submissionNote, applicantNote,
				briefNote, dossierNo, submitting, correctingDate, dossierStatus, dossierStatusText, dossierSubStatus,
				dossierSubStatusText, folderId, dossierActionId, viaPostal, postalAddress, postalCityCode,
				postalCityName, postalTelNo, password, notification, online, serverNo, context);

		return dossier;
	}

	@Override
	public Dossier getDossierDetail(long groupId, long dossierId, String referenceUid) throws PortalException {
		if (dossierId != 0) {
			return DossierLocalServiceUtil.fetchDossier(dossierId);
		} else {
			return DossierLocalServiceUtil.getByRef(groupId, referenceUid);
		}

	}

	@Override
	public Dossier removeDossier(long groupId, long dossierId, String referenceUid) throws PortalException {

		return DossierLocalServiceUtil.removeDossier(groupId, dossierId, referenceUid);
	}

	@Override
	public Dossier cancelDossier(long groupId, long dossierId, String referenceUid, ServiceContext context)
			throws PortalException {
		return DossierLocalServiceUtil.updateCancellingDate(groupId, dossierId, referenceUid, new Date(), context);
	}

	@Override
	public Dossier correctDossier(long groupId, long dossierId, String referenceUid, ServiceContext context)
			throws PortalException {
		return DossierLocalServiceUtil.updateCancellingDate(groupId, dossierId, referenceUid, new Date(), context);
	}

	@Override
	public Dossier submitDossier(long groupId, long dossierId, String referenceUid, ServiceContext context)
			throws PortalException {
		return DossierLocalServiceUtil.submitting(groupId, dossierId, referenceUid, context);
	}

	@Override
	public Dossier resetDossier(long groupId, long dossierId, String referenceUid, ServiceContext context)
			throws PortalException {
		return DossierLocalServiceUtil.reset(groupId, dossierId, referenceUid, context);
	}

	@Override
	public JSONArray getNextActions(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException {

		JSONArray results = JSONFactoryUtil.createJSONArray();

		List<ProcessAction> lstProcessAction = new ArrayList<ProcessAction>();

		Dossier dossier = null;

		DossierAction dossierAction = null;

		long serviceProcessId = 0;

		String stepCode = StringPool.BLANK;

		String actionCode = GetterUtil.getString(params.get(DossierActionTerm.ACTION_CODE));
		// TODO filter by Auto
		// String auto =
		// GetterUtil.getString(params.get(DossierActionTerm.AUTO));
		String referenceUid = GetterUtil.getString(params.get(DossierTerm.REFERENCE_UID));

		long dossierId = GetterUtil.getLong(params.get(DossierTerm.DOSSIER_ID));

		if (Validator.isNotNull(referenceUid)) {
			dossier = DossierLocalServiceUtil.getByRef(groupId, referenceUid);
		} else {
			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		}

		if (dossier != null) {

			long dossierActionId = dossier.getDossierActionId();

			if (Validator.isNotNull(actionCode)) {
				dossierAction = getDossierAction(dossier.getDossierId(), actionCode);
			} else {
				dossierAction = DossierActionLocalServiceUtil.getDossierAction(dossierActionId);
			}

			serviceProcessId = dossierAction != null ? dossierAction.getServiceProcessId() : 0;

			stepCode = dossierAction != null ? dossierAction.getStepCode() : StringPool.BLANK;

			if (Validator.isNotNull(stepCode)) {

				try {
					lstProcessAction = ProcessActionLocalServiceUtil.getProcessActionByG_SPID_PRESC(groupId,
							serviceProcessId, stepCode);
					for (ProcessAction processAction : lstProcessAction) {

						String[] preConditions = StringUtil.split(processAction.getPreCondition());

						String createDossierFiles = processAction.getCreateDossierFiles();

						if (preConditions != null && preConditions.length > 0) {
							for (String preCondition : preConditions) {
								if (preCondition.equalsIgnoreCase("payok")) {
									// TODO check palyment
									// continue;
								} else if (preCondition.equalsIgnoreCase("cancelling")) {
									if (dossier.getCancellingDate() == null) {
										continue;
									}

								} else if (preCondition.equalsIgnoreCase("correcting")) {
									if (dossier.getCorrecttingDate() == null) {
										continue;
									}

								}
							}
						}

						JSONObject result = JSONFactoryUtil.createJSONObject();

						String postStepCode = processAction.getPostStepCode();

						ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(postStepCode, groupId,
								processAction.getServiceProcessId());

						List<ProcessStepRole> lstProcessStepRole = ProcessStepRoleLocalServiceUtil
								.findByP_S_ID(processStep.getProcessStepId());

						List<User> lstUser = new ArrayList<User>();

						if (lstProcessStepRole == null || lstProcessStepRole.isEmpty()) {
							// Search in ServiceProcessRole
							List<ServiceProcessRole> serviceProcessRoles = ServiceProcessRoleLocalServiceUtil
									.findByS_P_ID(serviceProcessId);

							for (ServiceProcessRole serviceProcessRole : serviceProcessRoles) {

								List<User> users = UserLocalServiceUtil.getRoleUsers(serviceProcessRole.getRoleId());

								if (users != null) {
									for (User user : users) {
										HashMap<String, Object> moderator = new HashMap<>();
										moderator.put("moderator", serviceProcessRole.getModerator());
										user.setModelAttributes(moderator);
									}

									lstUser.addAll(users);
								}

							}
						} else {
							for (ProcessStepRole processStepRole : lstProcessStepRole) {

								List<User> users = UserLocalServiceUtil.getRoleUsers(processStepRole.getRoleId());

								if (users != null) {
									for (User user : users) {
										HashMap<String, Object> moderator = new HashMap<>();
										moderator.put("moderator", processStepRole.getModerator());
										user.setModelAttributes(moderator);
									}

									lstUser.addAll(users);
								}
							}
						}

						List<String> dossierFileTemplateNos = ListUtil.toList(StringUtil.split(createDossierFiles));

						List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getDossierFilesByD_DP(dossierId,
								2);

						JSONArray createFiles = JSONFactoryUtil.createJSONArray();

						if (dossierFileTemplateNos != null && !dossierFileTemplateNos.isEmpty()) {
							DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getByTemplateNo(groupId,
									dossier.getDossierTemplateNo());
							List<DossierPart> dossierParts = DossierPartLocalServiceUtil.getByTemplateNo(groupId,
									dossierTemplate.getTemplateNo());
							if (dossierParts != null) {
								for (DossierPart dossierPart : dossierParts) {
									String fileTemplateNo = dossierPart.getFileTemplateNo();
									if (dossierFileTemplateNos.contains(fileTemplateNo)) {
										JSONObject createFile = JSONFactoryUtil.createJSONObject();
										createFile.put("dossierPartId", dossierPart.getDossierPartId());
										createFile.put("partNo", dossierPart.getPartNo());
										createFile.put("partName", dossierPart.getPartName());
										createFile.put("partTip", dossierPart.getPartTip());
										createFile.put("multiple", dossierPart.getMultiple());
										createFile.put("templateFileNo", dossierPart.getTemplateNo());

										boolean eForm = false;
										String formData = StringPool.BLANK;
										String formScript = StringPool.BLANK;
										String docFileReferenceUid = StringPool.BLANK;
										int counter = 0;

										//List<DossierFile> dossierFilesResult = DossierFileLocalServiceUtil
										//		.getDossierFileByDID_DPNO(dossierId, dossierPart.getPartNo(), false);
										
										List<DossierFile> dossierFilesResult = DossierFileLocalServiceUtil
													.getDossierFileByDID_FTNO_DPT(dossierId, fileTemplateNo, 2, false);

										counter = (dossierFilesResult != null && dossierFilesResult.isEmpty())
												? dossierFilesResult.size() : 0;
												
												
										/*if (dossierFiles != null) {
											df: for (DossierFile dossierFile : dossierFiles) {
												if (dossierFile.getDossierPartNo().equals(dossierPart.getPartNo())) {
													eForm = dossierFile.getEForm();
													formData = dossierFile.getFormData();
													formScript = dossierFile.getFormScript();
													docFileReferenceUid = dossierFile.getReferenceUid();
													break df;
												}
											}
										}*/
												
										if (dossierFilesResult != null) {
											df: for (DossierFile dossierFile : dossierFilesResult) {
												if (dossierFile.getDossierPartNo().equals(dossierPart.getPartNo())) {
													eForm = dossierFile.getEForm();
													formData = dossierFile.getFormData();
													formScript = dossierFile.getFormScript();
													docFileReferenceUid = dossierFile.getReferenceUid();
													break df;
												}
											}
										}

										createFile.put("eform", eForm);
										createFile.put("formData", formData);
										createFile.put("formScript", formScript);
										createFile.put("referenceUid", docFileReferenceUid);
										createFile.put("counter", counter);
										createFiles.put(createFile);

									}
								}
							}
						}

						result.put("processAction", processAction);
						result.put("lstUser", lstUser);
						result.put("createFiles", createFiles);
						results.put(result);
					}
				} catch (Exception e) {
					_log.error("Can not get ProcessStep", e);
				}
			}
		}

		return results;
	}

	@Override
	public JSONObject getDossierActions(long dossierId, long groupId, Boolean owner, int start, int end, String sort,
			String order, ServiceContext serviceContext) throws PortalException {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(serviceContext.getCompanyId());

		try {
			String referenceUid = null;
			if (dossierId == 0) {
				referenceUid = String.valueOf(dossierId);
			}
			if (start == 0) {
				start = -1;
				end = -1;
			}
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierActionTerm.DOSSIER_ID, dossierId);
			if (owner != null && owner.booleanValue()) {
				params.put(Field.USER_ID, serviceContext.getUserId());
			}
			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(sort + "_sortable", Sort.STRING_TYPE, GetterUtil.getBoolean(order)) };

			hits = DossierActionLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DossierActionLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public DossierAction doAction(long groupId, long dossierId, String referenceUid, String actionCode,
			long processActionId, String actionUser, String actionNote, long assignUserId, long userId,
			ServiceContext context) throws PortalException {

		// Add DossierAction

		// Update DossierStatus

		// Update DossierLog

		// Update DossierSync (if it in the client)

		DossierAction dossierAction = null;

		Dossier dossier = getDossier(groupId, dossierId, referenceUid);

		if (Validator.isNull(dossier)) {
			throw new NotFoundException("DossierNotFoundException");
		}

		ProcessOption option = null;

		try {
			option = getProcessOption(dossier.getServiceCode(), dossier.getGovAgencyCode(),
					dossier.getDossierTemplateNo(), groupId);
		} catch (Exception e) {
			throw new NotFoundException("ServiceProcessNotFoundException");
		}

		long serviceProcessId = option.getServiceProcessId();

		ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);

		ProcessAction processAction = null;

		if (processActionId != 0) {
			processAction = ProcessActionLocalServiceUtil.getProcessAction(processActionId);
		} else {
			processAction = getProcessAction(groupId, dossierId, referenceUid, actionCode, serviceProcessId);
		}

		if (Validator.isNull(processAction))
			throw new NotFoundException("ProcessActionNotFoundException");

		boolean isSubmitType = isSubmitType(processAction);

		boolean hasDossierSync = false;

		if (processActionId != 0) {
			hasDossierSync = hasDossierSync(groupId, dossierId, referenceUid, processAction);
		}

		// TODO take a look later

		boolean hasForedDossierSync = forcedDossierSync(groupId, dossierId, referenceUid, processAction, isSubmitType);

		boolean isCreateDossier = hasCreateDossier(groupId, dossierId, referenceUid, actionCode, serviceProcessId,
				hasDossierSync);

		// TODO Hard fix for test
		List<String> types = new ArrayList<>();
		types.add(OCPSUserUtils.APPLICANT_01);
		types.add(OCPSUserUtils.APPLICANT_02);
		types.add(OCPSUserUtils.EMPLOYEE_01);
		types.add(OCPSUserUtils.EMPLOYEE_02);

		String postStepCode = processAction.getPostStepCode();

		ProcessStep curStep = ProcessStepLocalServiceUtil.fetchBySC_GID(postStepCode, groupId, serviceProcessId);

		int actionOverdue = getActionDueDate(groupId, dossierId, referenceUid, processActionId);

		Date dueDate = getDueDate(groupId, dossierId, referenceUid, processActionId);

		String payload = buildPayload(groupId, dossierId, referenceUid, processActionId);

		// In the special action (actionCode = 1100, save DOSSIER in SERVER)
		if (actionCode.contentEquals(SPECIAL_ACTION)
				&& (types.contains(OCPSUserUtils.APPLICANT_01) || types.contains(OCPSUserUtils.APPLICANT_02))) {
			// TODO check DossierStatus
			_log.info("DO_SPECIAL_ACTION");

			// Set dossierStatus is NEW
			JSONObject jsStatus = JSONFactoryUtil.createJSONObject();

			getDossierStatus(jsStatus, groupId, DOSSIER_SATUS_DC_CODE, DossierStatusConstants.NEW);


			dossierAction = DossierActionLocalServiceUtil.updateDossierAction(groupId, 0, dossierId, serviceProcessId,
					0l, actionCode, actionUser, processAction.getActionName(), actionNote, actionOverdue,
					processAction.getSyncActionCode(), false, processAction.getRollbackable(), curStep.getStepCode(),
					curStep.getStepName(), dueDate, 0l, payload, curStep.getStepInstruction(), context);
			
			// Add DossierActionUser

			DossierActionUserImpl dossierActionUser = new DossierActionUserImpl();

			dossierActionUser.initDossierActionUser(dossierAction.getDossierActionId(), userId, groupId, assignUserId);
			
			DossierLocalServiceUtil.updateStatus(groupId, dossierId, referenceUid, DossierStatusConstants.NEW,
					jsStatus.getString(DossierStatusConstants.NEW), StringPool.BLANK, StringPool.BLANK, context);


		} else {

			_log.info("NEXT_ACTION");

			JSONObject jsStatus = JSONFactoryUtil.createJSONObject();
			JSONObject jsSubStatus = JSONFactoryUtil.createJSONObject();

			// String syncActionCode = processAction.getSyncActionCode();

			getDossierStatus(jsStatus, groupId, DOSSIER_SATUS_DC_CODE, curStep.getDossierStatus());

			getDossierStatus(jsStatus, groupId, DOSSIER_SUB_SATUS_DC_CODE, curStep.getDossierSubStatus());


			DossierAction prvAction = DossierActionLocalServiceUtil.getByNextActionId(dossierId, 0l);

			dossierAction = DossierActionLocalServiceUtil.updateDossierAction(groupId, 0, dossierId, serviceProcessId,
					0l, actionCode, actionUser, processAction.getActionName(), actionNote, actionOverdue,
					processAction.getSyncActionCode(), hasDossierSync, processAction.getRollbackable(),
					curStep.getStepCode(), curStep.getStepName(), dueDate, 0l, payload, curStep.getStepInstruction(),
					context);
			
			// Add DossierActionUser

			DossierActionUserImpl dossierActionUser = new DossierActionUserImpl();

			dossierActionUser.initDossierActionUser(dossierAction.getDossierActionId(), userId, groupId, assignUserId);
			
			// Set dossierStatus by CUR_STEP
			DossierLocalServiceUtil.updateStatus(groupId, dossierId, referenceUid, curStep.getDossierStatus(),
					jsStatus.getString(curStep.getDossierStatus()), curStep.getDossierSubStatus(),
					jsSubStatus.getString(curStep.getDossierSubStatus()), context);


			// update nextActionId
			if (Validator.isNotNull(prvAction)) {
				DossierActionLocalServiceUtil.updateNextActionId(prvAction.getDossierActionId(),
						dossierAction.getDossierActionId());
			}

			if (hasDossierSync) {
				// SyncAction
				int method = 0;

				_log.info(new Date());

				_log.info("GROUPID_" + groupId + "dossierId_" + dossierId);

				DossierSyncLocalServiceUtil.updateDossierSync(groupId, userId, dossierId, dossier.getReferenceUid(),
						isCreateDossier, method, dossier.getPrimaryKey(), StringPool.BLANK,
						serviceProcess.getServerNo());

				// TODO add SYNC for DossierFile and PaymentFile here

				// SyncDossierFile
				List<DossierFile> lsDossierFile = DossierFileLocalServiceUtil.getByDossierIdAndIsNew(dossierId, true);

				for (DossierFile dosserFile : lsDossierFile) {

					DossierSyncLocalServiceUtil.updateDossierSync(groupId, userId, dossierId, dossier.getReferenceUid(),
							false, 1, dosserFile.getDossierFileId(), dosserFile.getReferenceUid(),
							serviceProcess.getServerNo());
				}

			}

		}

		ProcessStep postStep = ProcessStepLocalServiceUtil.fetchBySC_GID(postStepCode, groupId, serviceProcessId);

		String dossierBriefNote = DossierContentGenerator.getBriefNote(groupId, dossierId, postStep.getBriefNote());

		DossierLocalServiceUtil.updateDossierBriefNote(dossierId, dossierBriefNote);



		return dossierAction;
	}

	@Override
	public Dossier markerVisited(long groupId, long dossierId, String referenceUid) throws PortalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DossierAction doRollback(long groupId, long dossierId, String referenceUid, long userId)
			throws PortalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getContacts(long groupId, long dossierId, String referenceUid) throws PortalException {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		// get DossierAction
		DossierAction dossierAction = DossierActionLocalServiceUtil.getByNextActionId(dossierId, 0);
		long dossierActionId = dossierAction.getDossierActionId();
		List<DossierActionUser> listUser = DossierActionUserLocalServiceUtil.getListUser(dossierActionId);
		result.put("listUser", listUser);
		return result;
	}

	protected long getNextActionId(long groupId, long dossierId, String refId, ProcessAction currentAction) {
		// TODO get the recently DOSSIER_ACTION, set nextActionId for the
		// recently DOSSIER_ACTION by currentActionId

		return 0l;
	}

	protected void getDossierStatus(JSONObject ob, long groupId, String collectionCode, String itemCode) {

		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

		if (Validator.isNotNull(dc)) {
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dc.getPrimaryKey(), groupId);

			if (Validator.isNotNull(it)) {
				ob.put(itemCode, it.getItemName());
			}
		}
	}

	protected boolean isSubmitType(ProcessAction processAction) {

		// TODO add more logics here
		if (processAction.getAutoEvent().contentEquals(AUTO_EVENT_SUBMIT)) {
			return true;
		} else {
			return false;
		}

	}

	protected Date getDueDate(long groupId, long dossierId, String refId, long processActionId) {
		// TODO add implement here

		return new Date();
	}

	protected String buildPayload(long groupId, long dossierId, String refId, long processActionId) {
		// TODO add implement here

		return StringPool.BLANK;
	}

	protected int getActionDueDate(long groupId, long dossierId, String refId, long processActionId) {
		// TODO add implement here

		return 0;
	}

	protected ProcessOption getProcessOption(String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
			long groupId) throws PortalException {

		ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceInfoCode, govAgencyCode);

		return ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTemplateNo,
				config.getServiceConfigId());
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

	protected boolean hasCreateDossier(long groupId, long dossierId, String refId, String actionCode,
			long serviceProcessId, boolean hasDossierSync) throws PortalException {

		boolean isCreate = false;

		Dossier dossier = getDossier(groupId, dossierId, refId);

		ProcessAction action = getProcessAction(groupId, dossierId, refId, actionCode, serviceProcessId);

		String postStepCode = action.getPostStepCode();

		// ProcessStep step =
		// ProcessStepLocalServiceUtil.fetchBySC_GID(postStepCode, groupId,
		// serviceProcessId);

		// DOSSIER has nextAction form NEW to PROCESSING that it was created on
		// client and it wasn't created ONLINE
		if (hasDossierSync && dossier.getDossierStatus().contentEquals(DossierStatusConstants.NEW)
				&& !dossier.isOnline()) {
			isCreate = true;
		}

		return isCreate;
	}

	protected boolean hasDossierSync(long groupId, long dossierId, String refId, ProcessAction action)
			throws PortalException {

		Dossier dossier = getDossier(groupId, dossierId, refId);

		// TODO add more logic here
		boolean isSync = false;

		if (dossier.getOnline() && action.getSyncActionCode().length() != 0) {
			isSync = true;
		}

		_log.info("GROUPID_" + groupId);
		_log.info("ISSYNC_" + isSync);

		return isSync;

	}

	protected boolean forcedDossierSync(long groupId, long dossierId, String refId, ProcessAction action,
			boolean isSubmit) throws PortalException {

		Dossier dossier = getDossier(groupId, dossierId, refId);

		// TODO add more logic here
		boolean isSync = false;

		if (!isSubmit && dossier.getOnline() && (action.getSyncActionCode().length() != 0
				&& action.getSyncActionCode().contains(StringPool.EXCLAMATION))) {
			isSync = true;
		}

		return isSync;

	}

	protected ProcessAction getProcessAction(long groupId, long dossierId, String refId, String actionCode,
			long serviceProcessId) throws PortalException {

		ProcessAction action = null;

		try {
			List<ProcessAction> actions = ProcessActionLocalServiceUtil.getByActionCode(groupId, actionCode);

			Dossier dossier = getDossier(groupId, dossierId, refId);

			String dossierStatus = dossier.getDossierStatus();

			for (ProcessAction act : actions) {

				String preStepCode = act.getPreStepCode();

				ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(preStepCode, groupId, serviceProcessId);

				if (Validator.isNotNull(step)) {
					if (step.getDossierStatus().equalsIgnoreCase(dossierStatus)) {
						action = act;
						break;
					}
				} else {
					action = act;
					break;
				}

			}

		} catch (Exception e) {
			throw new NotFoundException("NotProcessActionFound");
		}

		return action;
	}

	@Override
	public Dossier initDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String password,
			int viaPostal, String postalAddress, String postalCityCode, String postalCityName, String postalTelNo,
			boolean online, boolean notification, String applicantNote, ServiceContext context) throws PortalException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date appIdDate = null;

		try {
			appIdDate = sdf.parse(applicantIdDate);

		} catch (Exception e) {
			// TODO: handle exception
		}
		

		Dossier dossier = null;

		try {

			dossier = DossierLocalServiceUtil.initDossier(groupId, dossierId, referenceUid, counter, serviceCode,
					serviceName, govAgencyCode, govAgencyName, applicantName, applicantIdType, applicantIdNo, appIdDate,
					address, cityCode, cityName, districtCode, districtName, wardCode, wardName, contactName,
					contactTelNo, contactEmail, dossierTemplateNo, password, viaPostal, postalAddress, postalCityCode,
					postalCityName, postalTelNo, online, notification, applicantNote, context);

		} catch (Exception e) {
			_log.error(e);
		}

		return dossier;
	}

	@Override
	public Dossier assignDossierToProcess(long dossierId, String dossierNote, String submissionNote, String briefNote,
			String dossierNo, long folderId, long dossierActionId, String serverNo, ServiceContext context)
			throws PortalException {

		return DossierLocalServiceUtil.assignToProcess(dossierId, dossierNote, submissionNote, briefNote, dossierNo,
				folderId, dossierActionId, serverNo, context);
	}

	protected DossierAction getDossierAction(long dossierId, String actionCode) {

		DossierAction dossierAction = DossierActionLocalServiceUtil.getDossierActionbyDossierIdandActionCode(dossierId,
				actionCode);

		return dossierAction;
	}

	protected List<ProcessAction> getListProcessAtion(long serviceProcessId) throws PortalException {

		List<ProcessAction> lstprocessAction = ProcessActionLocalServiceUtil
				.getProcessActionbyServiceProcessId(serviceProcessId);

		return lstprocessAction;

	}

	protected List<ProcessStep> getListProcessStep(long serviceProcessId) throws PortalException {

		List<ProcessStep> lstprocessStep = ProcessStepLocalServiceUtil
				.getProcessStepbyServiceProcessId(serviceProcessId);

		return lstprocessStep;

	}

	protected Log _log = LogFactoryUtil.getLog(DossierActionsImpl.class);

	@Override
	public Dossier cloneDossier(long groupId, long dossierId, ServiceContext context) throws PortalException {

		Dossier srcDossier = DossierLocalServiceUtil.fetchDossier(dossierId);

		long desDossierId = CounterLocalServiceUtil.increment(Dossier.class.getName());

		srcDossier.setUuid(UUID.randomUUID().toString());
		srcDossier.setDossierId(desDossierId);
		srcDossier.setDossierStatus(StringPool.BLANK);
		srcDossier.setDossierStatusText(StringPool.BLANK);
		srcDossier.setDossierSubStatus(StringPool.BLANK);
		srcDossier.setDossierSubStatusText(StringPool.BLANK);

		int counter = DossierNumberGenerator.counterDossier(srcDossier.getUserId(), groupId);
		String referenceUid = DossierNumberGenerator.generateReferenceUID(groupId);

		srcDossier.setCounter(counter);
		srcDossier.setReferenceUid(referenceUid);

		Dossier desDossier = DossierLocalServiceUtil.addDossier(srcDossier);

		DossierFileLocalServiceUtil.cloneDossierFilesByDossierId(groupId, srcDossier.getPrimaryKey(), dossierId, 1,
				context);

		return desDossier;
	}

	@Override
	public JSONObject getDossierTodo(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);

		String statusCode = StringPool.BLANK;

		String subStatusCode = StringPool.BLANK;

		JSONArray statistics = JSONFactoryUtil.createJSONArray();

		long total = 0;

		try {
			DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode("DOSSIER_STATUS",
					groupId);
			statusCode = GetterUtil.getString(params.get(DossierTerm.STATUS));

			subStatusCode = GetterUtil.getString(params.get(DossierTerm.SUBSTATUS));

			if (Validator.isNotNull(statusCode) || Validator.isNotNull(subStatusCode)) {
				DictItem dictItem = null;
				if (Validator.isNotNull(statusCode)) {
					dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(statusCode,
							dictCollection.getDictCollectionId(), groupId);
				} else {
					dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(subStatusCode,
							dictCollection.getDictCollectionId(), groupId);
				}

				if (dictItem != null) {
					long count = DossierLocalServiceUtil.countLucene(params, searchContext);

					JSONObject statistic = JSONFactoryUtil.createJSONObject();
					statistic.put("dossierStatus", statusCode);
					statistic.put("dossierSubStatus", subStatusCode);
					statistic.put("level", dictItem.getLevel());
					statistic.put("statusName", dictItem.getItemName());
					statistic.put("count", count);

					statistics.put(statistic);

					total = count;
				}

			} else {

				// DictItem: treeIndex chua luu dung nen chua dung duoc ham nay
				/*
				 * List<DictItem> dictItems =
				 * DictItemLocalServiceUtil.findByF_dictCollectionId(
				 * dictCollection.getDictCollectionId(), QueryUtil.ALL_POS,
				 * QueryUtil.ALL_POS, new DictItemComparator(false, "treeIndex",
				 * String.class));
				 * 
				 * for (DictItem dictItem : dictItems) { statusCode =
				 * dictItem.getLevel() == 0 ? dictItem.getItemCode() :
				 * StringPool.BLANK; subStatusCode = dictItem.getLevel() == 1 ?
				 * dictItem.getItemCode() : StringPool.BLANK;
				 * params.put(DossierTerm.DOSSIER_STATUS, statusCode);
				 * params.put(DossierTerm.DOSSIER_SUB_STATUS, subStatusCode);
				 * long count = DossierLocalServiceUtil.countLucene(params,
				 * searchContext);
				 * 
				 * JSONObject statistic = JSONFactoryUtil.createJSONObject();
				 * 
				 * statistic.put("dossierStatus", statusCode);
				 * statistic.put("dossierSubStatus", subStatusCode);
				 * statistic.put("level", dictItem.getLevel());
				 * statistic.put("statusName", dictItem.getItemName());
				 * statistic.put("count", count); total += count;
				 * statistics.put(statistic); }
				 */

				List<DictItem> dictItems = DictItemLocalServiceUtil
						.findByF_dictCollectionId_parentItemId(dictCollection.getDictCollectionId(), 0);

				for (DictItem dictItem : dictItems) {

					statusCode = dictItem.getItemCode();
					subStatusCode = StringPool.BLANK;

					params.put(DossierTerm.STATUS, statusCode);
					params.put(DossierTerm.SUBSTATUS, subStatusCode);

					long count = DossierLocalServiceUtil.countLucene(params, searchContext);

					JSONObject statistic = JSONFactoryUtil.createJSONObject();

					statistic.put("dossierStatus", statusCode);
					statistic.put("dossierSubStatus", subStatusCode);
					statistic.put("level", dictItem.getLevel());
					statistic.put("statusName", dictItem.getItemName());
					statistic.put("count", count);
					total += count;
					statistics.put(statistic);

					List<DictItem> childDictItems = DictItemLocalServiceUtil.findByF_dictCollectionId_parentItemId(
							dictCollection.getDictCollectionId(), dictItem.getDictItemId());

					if (childDictItems != null) {
						for (DictItem childDictItem : childDictItems) {

							subStatusCode = childDictItem.getItemCode();

							statusCode = StringPool.BLANK;

							params.put(DossierTerm.STATUS, statusCode);

							params.put(DossierTerm.SUBSTATUS, subStatusCode);

							long childCount = DossierLocalServiceUtil.countLucene(params, searchContext);

							JSONObject childStatistic = JSONFactoryUtil.createJSONObject();

							childStatistic.put("dossierStatus", dictItem.getItemCode());
							childStatistic.put("dossierSubStatus", subStatusCode);
							childStatistic.put("level", childDictItem.getLevel());
							childStatistic.put("statusName", childDictItem.getItemName());
							childStatistic.put("count", childCount);

							// Khong tinh tong so cua con do da tinh tong cua
							// cha
							// total += childCount;
							statistics.put(childStatistic);
						}
					}
				}
			}

			result.put("data", statistics);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

}
