package org.opencps.dossiermgt.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.cache.actions.CacheActions;
import org.opencps.cache.actions.impl.CacheActionsImpl;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.util.DueDateUtils;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.util.AutoFillFormData;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DeliverableTypesTerm;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.ProcessStepRoleTerm;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.model.impl.ProcessActionImpl;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.CPSDossierBusinessLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierMarkLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;

import backend.auth.api.exception.ErrorMsgModel;
import backend.auth.api.exception.NotFoundException;

public class DossierActionsImpl implements DossierActions {

	public static final String SPECIAL_ACTION = "1100";
	public static final String DOSSIER_SATUS_DC_CODE = "DOSSIER_STATUS";
	public static final String DOSSIER_SUB_SATUS_DC_CODE = "DOSSIER_SUB_STATUS";
	
	CacheActions cache = new CacheActionsImpl();
	long ttl = OpenCPSConfigUtil.getCacheTTL();
	
	@Override
	public JSONObject getDossiers(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DossierLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put(ConstantUtils.DATA, hits.toList());

			long total = DossierLocalServiceUtil.countLucene(params, searchContext);

			result.put(ConstantUtils.TOTAL, total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;

	}
	@Override
	public JSONObject getDossiersTest(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			String statusCode = GetterUtil.getString(params.get(DossierTerm.STATUS));

			String subStatusCode = GetterUtil.getString(params.get(DossierTerm.SUBSTATUS));
			if (groupId == 55217) {
				hits = DossierLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

				result.put(ConstantUtils.DATA, hits.toList());

				long total = DossierLocalServiceUtil.countLucene(params, searchContext);

				result.put(ConstantUtils.TOTAL, total);

				return result;
			}

			// Get collection with collection Code
			DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(ReadFilePropertiesUtils.get(ConstantUtils.DOSSIER_STATUS),
					groupId);

			// Get list dossier follow status Code
			if (Validator.isNotNull(statusCode) || Validator.isNotNull(subStatusCode)) {
//				_log.info("52" + sb.toString());

				DictItem dictItem = null;
				if (Validator.isNotNull(subStatusCode)) {
					dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(subStatusCode,
							dictCollection.getDictCollectionId(), groupId);
				} else {
					dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(statusCode,
							dictCollection.getDictCollectionId(), groupId);
				}

				long total = 0;
				if (dictItem != null) {
					hits = DossierLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);
					if (hits != null && hits.getLength() > 0) {
						result.put(ConstantUtils.DATA, hits.toList());
//						_log.info("hits.toList(): " + hits.toList().size());
						total = DossierLocalServiceUtil.countLucene(params, searchContext);
//						_log.info("total: " + total);
						result.put(ConstantUtils.TOTAL, total);
					}
				}
			} else { /* Get list dossier follow roles user login */

				List<DictItem> dictItems = DictItemLocalServiceUtil
						.findByF_dictCollectionId(dictCollection.getDictCollectionId());

				// Get list dossier follow each status
				if (dictItems != null && dictItems.size() > 0) {
//					_log.info("dictItems: " + dictItems.size());
					List<Document> allDocsList = new ArrayList<Document>();
					long total = 0;
					for (DictItem dictItem : dictItems) {

						subStatusCode = StringPool.BLANK;
						// Get info status of dossier
						if (dictItem.getParentItemId() != 0) {
							subStatusCode = dictItem.getItemCode();
							DictItem parentDictItem = DictItemLocalServiceUtil.getDictItem(dictItem.getParentItemId());
							statusCode = parentDictItem.getItemCode();
//							_log.info("statusCode: " + statusCode);
						} else {
							statusCode = dictItem.getItemCode();
//							_log.info("statusCode: " + statusCode);
						}

						// Check permission user login
						boolean isPermission = checkPermission(statusCode, subStatusCode, groupId, userId);

						if (isPermission) {
//							_log.info("isPermission: " + isPermission);
//							_log.info("userId: " + userId);
							// _log.info("strdossierActionId: " +
							// sb.toString());

							params.put(DossierTerm.STATUS, statusCode);
							params.put(DossierTerm.SUBSTATUS, subStatusCode);
							params.put(DossierTerm.FOLLOW, String.valueOf(false));

							hits = DossierLocalServiceUtil.searchLucene(params, sorts, -1, -1, searchContext);

							if (hits != null && hits.getLength() > 0) {
								// allDocsList.addAll(hits.toList());
								// _log.info("SizeList2: " +
								// hits.toList().size());
								long count = DossierLocalServiceUtil.countLucene(params, searchContext);
								_log.info("count: " + count);
								if (dictItem.getParentItemId() != 0) {
									allDocsList.addAll(hits.toList());
									_log.info("SizeList2: " + hits.toList().size());
									total += count;
								}
							}
						}
					}
					// Add all list dossier of multiple status
					result.put(ConstantUtils.DATA, allDocsList);
					result.put(ConstantUtils.TOTAL, total);
				}
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return result;

	}
	@Override
	public JSONObject getDossierProcessList(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, Integer start, Integer end,
			ServiceContext serviceContext) {

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;

		try {

			hits = DossierLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put(ConstantUtils.DATA, hits.toList());

			long total = DossierLocalServiceUtil.countLucene(params, searchContext);

			result.put(ConstantUtils.TOTAL, total);

			return result;

		} catch (Exception e) {
			_log.error(e);
		}

		return result;

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
		return DossierLocalServiceUtil.updateCorrectingDate(groupId, dossierId, referenceUid, new Date(), context);
	}

	@Override
	public Dossier submitPostDossier(long groupId, long dossierId, String referenceUid, ServiceContext context)
			throws PortalException {
		return DossierLocalServiceUtil.updateEndosementDate(groupId, dossierId, referenceUid, new Date(), context);
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
	public JSONArray getNextActionList(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, Integer start, Integer end, ServiceContext serviceContext) {

		long dossierId = GetterUtil.getLong(params.get(DossierTerm.DOSSIER_ID));

		DossierAction dossierAction = null;
		List<ProcessAction> processActionList = null;
		JSONArray results = null;
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		User user = UserLocalServiceUtil.fetchUser(userId);

		List<Role> userRolesAdminCheck = user.getRoles();
		boolean isAdministratorData = false;
		for (Role r : userRolesAdminCheck) {
			if (ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN).equalsIgnoreCase(r.getName())) {
				isAdministratorData = true;
				break;
			}
		}
		
		try {
			if (dossier != null) {
				long serviceProcessId = 0;
				String stepCode = StringPool.BLANK;
				boolean pending = false;
				long dossierActionId = dossier.getDossierActionId();
				// _log.info("dossierActionId: "+dossierActionId);
				if (dossierActionId > 0) {
					dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
				}

				if (dossierAction != null) {
					serviceProcessId = dossierAction.getServiceProcessId();
					stepCode = dossierAction.getStepCode();
					pending = dossierAction.getPending();
				}

				if (isAdministratorData) {
					ActionConfig ac = ActionConfigLocalServiceUtil.getByCode(groupId,
							ReadFilePropertiesUtils.get(ConstantUtils.FIX_DOSSIER_ACTION));
					if (ac != null) {
						if (results == null) {
							results = JSONFactoryUtil.createJSONArray();
						}
						JSONObject data = JSONFactoryUtil.createJSONObject();
						data.put(ProcessActionTerm.ENABLE, 1);
						data.put(ProcessActionTerm.PROCESS_ACTION_ID, ac.getActionCode());
						data.put(ProcessActionTerm.ACTION_CODE,
								ReadFilePropertiesUtils.get(ConstantUtils.FIX_DOSSIER_ACTION));
						data.put(ProcessActionTerm.ACTION_NAME, ac.getActionName());
						data.put(ProcessActionTerm.PRESTEP_CODE, StringPool.BLANK);
						data.put(ProcessActionTerm.POSTSTEP_CODE, StringPool.BLANK);
						data.put(ProcessActionTerm.AUTO_EVENT, StringPool.BLANK);
						data.put(ProcessActionTerm.PRE_CONDITION, StringPool.BLANK);
						//
						results.put(data);
					}
				}

				if (Validator.isNotNull(stepCode) && serviceProcessId > 0) {
					DossierActionUser dActionUser = DossierActionUserLocalServiceUtil
							.getByDossierAndUser(dossierActionId, userId);
					// _log.info("User id: " + userId);
					// GS.AnhTT_Process
					int enable = 2;
					if (dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT) {
						if (dossier.getUserId() == userId && !pending) {
							enable = 1;
						}
					}
					if (dActionUser != null) {
						int assign = dActionUser.getAssigned();
						if (assign == 1 && !pending)
							enable = 1;
					}
					//Check if user if admin
					User checkAU = UserLocalServiceUtil.fetchUser(userId);
//					_log.info("SONDT checkAU: " + JSONFactoryUtil.looseSerialize(checkAU));
					if (checkAU != null) {
						List<Role> userRoles = checkAU.getRoles();
						boolean isAdmin = false;
						for (Role r : userRoles) {
//							_log.info("SONDT userRoles: " + JSONFactoryUtil.looseSerialize(r));
							if (ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN).equalsIgnoreCase(r.getName())) {
								isAdmin = true;
								break;
							}
						}
						if (isAdmin) {
							enable = 1;
						}
					}
					// _log.info("Enable: " + enable);
					processActionList = ProcessActionLocalServiceUtil.getProcessActionByG_SPID_PRESC(groupId,
							serviceProcessId, stepCode);
					// _log.info("processActionList:
					// "+processActionList.size());
					if (processActionList != null && processActionList.size() > 0) {
						if (results == null) {
							results = JSONFactoryUtil.createJSONArray();
						}
						JSONObject data = null;
						long processActionId = 0;
						String actionCode;
						String actionName;
						String preStepCode;
						String postStepCode;
						String autoEvent;
						String preCondition;
						for (ProcessAction processAction : processActionList) {
							// _log.info("processAction: "+processAction);
							data = JSONFactoryUtil.createJSONObject();
							processActionId = processAction.getProcessActionId();
							actionCode = processAction.getActionCode();
							actionName = processAction.getActionName();
							preStepCode = processAction.getPreStepCode();
							postStepCode = processAction.getPostStepCode();
							autoEvent = processAction.getAutoEvent();
							preCondition = processAction.getPreCondition();
							// Check permission enable button
//							_log.info("SONDT NEXTACTIONLIST PRECONDITION ======== " + preCondition);
							if (!isAdministratorData) {
								if (processCheckEnable(preCondition, autoEvent, dossier, actionCode, groupId))
									data.put(ProcessActionTerm.ENABLE, enable);
								else
									data.put(ProcessActionTerm.ENABLE, 0);
							}
							else {
								data.put(ProcessActionTerm.ENABLE, enable);								
							}
						
							data.put(ProcessActionTerm.PROCESS_ACTION_ID, processActionId);
							data.put(ProcessActionTerm.ACTION_CODE, actionCode);
							data.put(ProcessActionTerm.ACTION_NAME, actionName);
							data.put(ProcessActionTerm.PRESTEP_CODE, preStepCode);
							data.put(ProcessActionTerm.POSTSTEP_CODE, postStepCode);
							data.put(ProcessActionTerm.AUTO_EVENT, autoEvent);
							data.put(ProcessActionTerm.PRE_CONDITION, preCondition);
							data.put(ProcessActionTerm.ALLOW_ASSIGN_USER, processAction.getAllowAssignUser());
							
							//
							results.put(data);
						}
					}
				} else {
					results = JSONFactoryUtil.createJSONArray();
					ProcessOption option = null;
					if (dossierAction != null) {
						DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil
								.getByTemplateNo(dossier.getGroupId(), dossier.getDossierTemplateNo());
						option = getProcessOption(dossierAction.getServiceProcessId(),
								dossierTemplate.getDossierTemplateId());
					}
					else {
						option = getProcessOption(dossier.getServiceCode(), dossier.getGovAgencyCode(),
								dossier.getDossierTemplateNo(), groupId);
					}

					if (option != null) {
						serviceProcessId = option.getServiceProcessId();
					}
						processActionList = ProcessActionLocalServiceUtil
								.getByServiceStepCode(groupId, serviceProcessId, StringPool.BLANK);
					if (processActionList != null && processActionList.size() > 0) {
						JSONObject data = null;
						long processActionId = 0;
						String actionCode;
						String actionName;
						String preStepCode;
						String postStepCode;
						String autoEvent;
						String preCondition;
						for (ProcessAction processAction : processActionList) {
							// _log.info("processAction: "+processAction);
							data = JSONFactoryUtil.createJSONObject();
							processActionId = processAction.getProcessActionId();
							actionCode = processAction.getActionCode();
							actionName = processAction.getActionName();
							preStepCode = processAction.getPreStepCode();
							postStepCode = processAction.getPostStepCode();
							autoEvent = processAction.getAutoEvent();
							preCondition = processAction.getPreCondition();
							data.put(ProcessActionTerm.PROCESS_ACTION_ID, processActionId);
							data.put(ProcessActionTerm.ACTION_CODE, actionCode);
							data.put(ProcessActionTerm.ACTION_NAME, actionName);
							data.put(ProcessActionTerm.PRESTEP_CODE, preStepCode);
							data.put(ProcessActionTerm.POSTSTEP_CODE, postStepCode);
							data.put(ProcessActionTerm.AUTO_EVENT, autoEvent);
							data.put(ProcessActionTerm.PRE_CONDITION, preCondition);
							data.put(ProcessActionTerm.ALLOW_ASSIGN_USER, processAction.getAllowAssignUser());
							
							data.put(ProcessActionTerm.ENABLE, 1);
							//
							results.put(data);
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	@Override
	public JSONObject getDetailNextActions(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, Integer start, Integer end,
			ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		long dossierId = GetterUtil.getLong(params.get(DossierTerm.DOSSIER_ID));

		try {
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			if (dossier != null) {
				String dossierTempNo = dossier.getDossierTemplateNo();
				long processActionId = GetterUtil.getLong(params.get(ProcessActionTerm.PROCESS_ACTION_ID));
				ProcessAction processAction = ProcessActionLocalServiceUtil.fetchProcessAction(processActionId);

				JSONObject payment = JSONFactoryUtil.createJSONObject();
				String postStepCode = StringPool.BLANK;
				long serviceProcessId = 0;
				if (processAction != null) {
					postStepCode = processAction.getPostStepCode();
					serviceProcessId = processAction.getServiceProcessId();
					payment.put(PaymentFileTerm.PAYMENT_REQUEST, processAction.getRequestPayment());
					String paymentFeeData = processAction.getPaymentFee();
					PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(groupId, dossierId);
					String advanceAmount = StringPool.BLANK;
					String feeAmount = StringPool.BLANK;
					String serviceAmount = StringPool.BLANK;
					String shipAmount = StringPool.BLANK;
					String paymentFee = StringPool.BLANK;
					String paymentNote = StringPool.BLANK;
					int editable = 0;

					if (Validator.isNotNull(paymentFeeData)) {
						JSONObject jsonPaymentFee = JSONFactoryUtil.createJSONObject(paymentFeeData);

						advanceAmount = jsonPaymentFee.getString(PaymentFileTerm.ADVANCE_AMOUNT);
						feeAmount = jsonPaymentFee.getString(PaymentFileTerm.FEE_AMOUNT);
						serviceAmount = jsonPaymentFee.getString(PaymentFileTerm.SERVICE_AMOUNT);
						shipAmount = jsonPaymentFee.getString(PaymentFileTerm.SHIP_AMOUNT);
						paymentFee = jsonPaymentFee.getString(PaymentFileTerm.PAYMENT_FEE);
						paymentNote = jsonPaymentFee.getString(PaymentFileTerm.PAYMENT_NOTE);
						editable = GetterUtil.getInteger(jsonPaymentFee.getString(PaymentFileTerm.EDITABLE));
					}
					if (paymentFile != null) {
						payment.put(PaymentFileTerm.ADVANCE_AMOUNT, paymentFile.getAdvanceAmount());
						payment.put(PaymentFileTerm.FEE_AMOUNT, paymentFile.getFeeAmount());
						payment.put(PaymentFileTerm.SERVICE_AMOUNT, paymentFile.getServiceAmount());
						payment.put(PaymentFileTerm.SHIP_AMOUNT, paymentFile.getShipAmount());
						payment.put(PaymentFileTerm.PAYMENT_FEE, paymentFile.getPaymentFee());
						payment.put(PaymentFileTerm.PAYMENT_NOTE, paymentFile.getPaymentNote());
						payment.put(PaymentFileTerm.EDITABLE, editable);
					} else {
						if (Validator.isNotNull(paymentFeeData)) {
							JSONObject jsonPaymentFee = JSONFactoryUtil.createJSONObject(paymentFeeData);
							if (jsonPaymentFee != null) {
								payment.put(PaymentFileTerm.ADVANCE_AMOUNT, advanceAmount);
								payment.put(PaymentFileTerm.FEE_AMOUNT, feeAmount);
								payment.put(PaymentFileTerm.SERVICE_AMOUNT, serviceAmount);
								payment.put(PaymentFileTerm.SHIP_AMOUNT, shipAmount);
								payment.put(PaymentFileTerm.PAYMENT_FEE, paymentFee);
								payment.put(PaymentFileTerm.PAYMENT_NOTE, paymentNote);
								payment.put(PaymentFileTerm.EDITABLE, editable);

							} else {
							}
						} else {
							payment.put(PaymentFileTerm.ADVANCE_AMOUNT, 0);
							payment.put(PaymentFileTerm.FEE_AMOUNT, 0);
							payment.put(PaymentFileTerm.SERVICE_AMOUNT, 0);
							payment.put(PaymentFileTerm.SHIP_AMOUNT, 0);
							payment.put(PaymentFileTerm.PAYMENT_FEE, 0);
							payment.put(PaymentFileTerm.PAYMENT_NOTE, 0);
							payment.put(PaymentFileTerm.EDITABLE, editable);
						}
					}
					result.put(ProcessActionTerm.PAYMENT, payment);
				}

				ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(postStepCode, groupId,
						serviceProcessId);
				if (processStep != null) {
					List<User> lstUser = new ArrayList<>();

					List<ProcessStepRole> processStepRoleList = ProcessStepRoleLocalServiceUtil
							.findByP_S_ID(processStep.getProcessStepId());
					if (Validator.isNotNull(processStep.getRoleAsStep())) {
						String[] steps = StringUtil.split(processStep.getRoleAsStep());
						for (String stepCode : steps) {
							if (stepCode.startsWith(StringPool.EXCLAMATION)) {
								int index = stepCode.indexOf(StringPool.EXCLAMATION);
								String stepCodePunc = stepCode.substring(index + 1);
								lstUser.addAll(processRoleAsStepDonedListUser(dossier, stepCodePunc, serviceProcessId,
										processStep, processStepRoleList));
							} else {
								lstUser.addAll(processRoleAsStepListUser(dossier, stepCode, serviceProcessId,
										processStep, processStepRoleList));
							}
						}
					}
					else {
						if (processStepRoleList != null && !processStepRoleList.isEmpty()) {
							List<ProcessStepRole> lstStepRoles = new ArrayList<>();
							for (ProcessStepRole psr : processStepRoleList) {
								if (Validator.isNotNull(psr.getCondition())) {
									String[] conditions = StringUtil.split(psr.getCondition());

									if (DossierMgtUtils.checkPreCondition(conditions, dossier)) {
										lstStepRoles.add(psr);
									}
								}
								else {
									lstStepRoles.add(psr);
								}
							}
							lstUser.addAll(processRoleListUser(lstStepRoles, serviceProcessId));
						}						
					}
					if (lstUser != null && !lstUser.isEmpty()) {
						result.put(ProcessActionTerm.LIST_USER, lstUser);
					}
				}

				if (processAction != null) {
					ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil
							.fetchServiceProcess(serviceProcessId);

					ProcessStep postStep = processStep;

					JSONObject receivingObj = JSONFactoryUtil.createJSONObject();
					Date receiveDate = new Date();
					receivingObj.put(DossierTerm.RECEIVE_DATE,
							dossier.getReceiveDate() != null ? dossier.getReceiveDate().getTime()
									: receiveDate.getTime());
					Date dueDate = null;
					if (dossier.getDueDate() != null) {
						dueDate = dossier.getDueDate();
					} else {
						Double durationCount = serviceProcess.getDurationCount();
						if (Validator.isNotNull(String.valueOf(durationCount)) && durationCount > 0d) {
							DueDateUtils dueDateUtils = new DueDateUtils(new Date(), durationCount,
									serviceProcess.getDurationUnit(), groupId);
							dueDate = dueDateUtils.getDueDate();
							dossier.setDueDate(dueDate);
						}
					}

					receivingObj.put(DossierTerm.DUE_DATE, dueDate != null ? dueDate.getTime() : 0l);
					receivingObj.put(ProcessActionTerm.DUE_DATE_EDITABLE,
							DossierMgtUtils.isDueDateEditable(serviceProcess.getDueDatePattern()));
					if (postStep != null) {
						if (ReadFilePropertiesUtils.get(ConstantUtils.STATUS_NEW).equals(postStep.getDossierStatus())
								|| DossierTerm.DOSSIER_STATUS_PROCESSING.equals(postStep.getDossierStatus())
										&& dossier.getReceiveDate() == null) {
							result.put(ProcessActionTerm.RECEIVING, receivingObj);
						}
					}
				}
				//
				String createDossierFiles = StringPool.BLANK;
				String returnDossierFiles = StringPool.BLANK;
				if (processAction != null) {
					createDossierFiles = processAction.getCreateDossierFiles();
					returnDossierFiles = processAction.getReturnDossierFiles();
				}

				List<String> createFileTempNoList = ListUtil.toList(StringUtil.split(createDossierFiles));
				List<String> returnFileTempNoList = ListUtil.toList(StringUtil.split(returnDossierFiles));

				DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getByTemplateNo(groupId,
						dossier.getDossierTemplateNo());

				List<DossierPart> partList = DossierPartLocalServiceUtil.getByTemplateNo(groupId,
						dossierTemplate.getTemplateNo());

				if (returnFileTempNoList != null && !returnFileTempNoList.isEmpty()) {
					List<DossierFile> returnFiles = new ArrayList<>();
					if (partList != null && partList.size() > 0) {
						for (DossierPart dossierPart : partList) {
							String fileTemplateNo = dossierPart.getFileTemplateNo();
							int dossierPartType = dossierPart.getPartType();
							if (returnFileTempNoList.contains(fileTemplateNo)) {
								List<DossierFile> fileList = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_DPT(
										dossierId, fileTemplateNo, dossierPartType, false);
								if (fileList != null && fileList.size() > 0) {
									returnFiles.addAll(fileList);
								}
							}
						}
					}
					result.put(ProcessActionTerm.RETURN_FILES, returnFiles);
				}

				JSONArray createFiles = JSONFactoryUtil.createJSONArray();
				if (createFileTempNoList != null && !createFileTempNoList.isEmpty()) {

					if (partList != null && partList.size() > 0) {
						long fileEntryId = 0;
						boolean eForm = false;
						boolean multiple = false;
						String formData;
						String formScript;
						String docFileReferenceUid = StringPool.BLANK;
						int counter = 0;
						long dossierFileId = 0;
						for (DossierPart dossierPart : partList) {
							String fileTemplateNo = dossierPart.getFileTemplateNo();
							eForm = dossierPart.getEForm();
							multiple = dossierPart.getMultiple();

							if (createFileTempNoList.contains(fileTemplateNo)) {
								JSONObject createFile = JSONFactoryUtil.createJSONObject();
								createFile.put(DossierPartTerm.DOSSIERPART_ID, dossierPart.getDossierPartId());
								createFile.put(DossierPartTerm.PART_NO, dossierPart.getPartNo());
								createFile.put(DossierPartTerm.PART_NAME, dossierPart.getPartName());
								createFile.put(DossierPartTerm.PART_TIP, dossierPart.getPartTip());
								createFile.put(DossierPartTerm.MULTIPLE, dossierPart.getMultiple());
								createFile.put(DossierPartTerm.FILE_TEMPLATE_NO, fileTemplateNo);
								createFile.put(DossierPartTerm.PART_TYPE, dossierPart.getPartType());
								createFile.put(DossierPartTerm.EFORM, eForm);
								createFile.put(DossierPartTerm.MULTIPLE, multiple);
								
								//Get dossierMark
								DossierMark dossierMark = DossierMarkLocalServiceUtil.getDossierMarkbyDossierId(groupId,
										dossierId, dossierPart.getPartNo());
								if (dossierMark != null) {
									createFile.put(DossierPartTerm.FILE_MARK, dossierMark.getFileMark());
									createFile.put(DossierPartTerm.FILE_CHECK, dossierMark.getFileCheck());
									createFile.put(DossierPartTerm.FILE_COMMENT, dossierMark.getFileComment());
								} else {
									createFile.put(DossierPartTerm.FILE_MARK, 0);
									createFile.put(DossierPartTerm.FILE_CHECK, 0);
									createFile.put(DossierPartTerm.FILE_COMMENT, StringPool.BLANK);
								}

								ServiceContext context = new ServiceContext();
								context.setScopeGroupId(dossierPart.getGroupId());
								context.setCompanyId(dossierPart.getCompanyId());

								String strDeliverableType = dossierPart.getDeliverableType();
								String sampleData = dossierPart.getSampleData();
								String formDataDeliverables = AutoFillFormData.sampleDataBinding(sampleData, dossier,
										context);
								JSONObject formDataObj = JSONFactoryUtil.createJSONObject(formDataDeliverables);

								// End add generate deliverable if has deliverable type
								if (Validator.isNull(strDeliverableType)) {
									List<DossierFile> dossierFilesResult = DossierFileLocalServiceUtil
											.getDossierFileByDID_FTNO_DPTS(dossierId, fileTemplateNo, new int[] { DossierPartTerm.DOSSIER_PART_TYPE_OUTPUT, DossierPartTerm.DOSSIER_PART_TYPE_GROUP_OUTPUT }, false,
													QueryUtil.ALL_POS, QueryUtil.ALL_POS,
													new DossierFileComparator(false, Field.CREATE_DATE, Date.class));
									_log.debug("dossierFilesResult: "+dossierFilesResult.size());
									if (dossierFilesResult != null && !dossierFilesResult.isEmpty()) {
										createFile = processFileResult(dossierFilesResult, createFile,
												dossierPart.getPartNo());
										_log.debug("createFile: "+createFile.toJSONString());

									} else {
										createFile = processEFormByCreateFile(dossierPart, groupId, dossierId,
												sampleData, fileTemplateNo, dossierTempNo, createFile, serviceContext);
										dossierFilesResult = DossierFileLocalServiceUtil
												.getDossierFileByDID_FTNO_DPTS_NOT_NULL_FID(dossierId, fileTemplateNo, new int[] { DossierPartTerm.DOSSIER_PART_TYPE_OUTPUT, DossierPartTerm.DOSSIER_PART_TYPE_GROUP_OUTPUT }, 0,
														false);
									}
									_log.debug("dossierFilesResult1: "+dossierFilesResult.size());

									counter = (dossierFilesResult != null && !dossierFilesResult.isEmpty())
											? dossierFilesResult.size() : 0;
									createFile.put(DossierFileTerm.COUNTER, counter);
									createFiles.put(createFile);
								} else {
									DeliverableType deliverableTypeObject = DeliverableTypeLocalServiceUtil
											.getByCode(groupId, strDeliverableType);
//									_log.info("Deliverable type: " + deliverableTypeObject);
									if (deliverableTypeObject != null) {
										String mappingData = deliverableTypeObject.getMappingData();
										JSONObject mappingDataObj = JSONFactoryUtil.createJSONObject(mappingData);
										if (mappingDataObj.has(DeliverableTypesTerm.DELIVERABLES_KEY)) {
											String deliverables = mappingDataObj
													.getString(DeliverableTypesTerm.DELIVERABLES_KEY);
											_log.info("--------DELIVERABLES----------" + deliverables);
//											_log.info("--------HAS E SIGNATURE----------"
//													+ processAction.getESignature());
//											_log.info("---------FILE TEMPLATE NO--------" + fileTemplateNo);
											if (Validator.isNull(deliverables)) {
												// Add one deliverable
												List<DossierFile> dossierFilesResult = DossierFileLocalServiceUtil
														.getDossierFileByDID_FTNO_DPTS(dossierId, fileTemplateNo,
																new int[] { DossierPartTerm.DOSSIER_PART_TYPE_OUTPUT,
																		DossierPartTerm.DOSSIER_PART_TYPE_GROUP_OUTPUT },
																false, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
																new DossierFileComparator(false, Field.CREATE_DATE,
																		Date.class));
												if (dossierFilesResult != null && !dossierFilesResult.isEmpty()) {
													createFile = processFileResult(dossierFilesResult, createFile,
															dossierPart.getPartNo());
												} else {
													formScript = dossierPart.getFormScript();
													eForm = dossierPart.getEForm();
													formData = AutoFillFormData.sampleDataBinding(
															dossierPart.getSampleData(), dossier, serviceContext);
													_log.info("--------eForm----------: " + eForm);

													if (eForm) {
														createFile = processEFormAndUpdateDossierFile(dossierPart,
																groupId, dossierId, formData, fileTemplateNo,
																dossierTempNo, formDataObj, createFile, serviceContext);
													} else if (Validator.isNotNull(formData)) {
														formDataObj = JSONFactoryUtil.createJSONObject(formData);
														if (mappingDataObj.has(DeliverableTypesTerm.DELIVERABLES_KEY)) {
															deliverables = mappingDataObj
																	.getString(DeliverableTypesTerm.DELIVERABLES_KEY);
															if (Validator.isNull(deliverables)) {
																createFile = processEFormAndUpdateFormData(dossierPart,
																		groupId, dossierId, formData, fileTemplateNo,
																		dossierTempNo, formDataObj, createFile,
																		serviceContext);
															} else {
																if (formDataObj.has(deliverables)) {
																	formDataObj = JSONFactoryUtil
																			.createJSONObject(formData);
																	JSONArray deliverablesArr = JSONFactoryUtil
																			.createJSONArray(formDataObj
																					.getString(deliverables));
																	dossierFilesResult = DossierFileLocalServiceUtil
																			.getDossierFileByDID_FTNO_DPTS_NOT_NULL_FID(
																					dossierId, fileTemplateNo,
																					new int[] {
																							DossierPartTerm.DOSSIER_PART_TYPE_OUTPUT,
																							DossierPartTerm.DOSSIER_PART_TYPE_GROUP_OUTPUT },
																					0, false);

																	counter = (dossierFilesResult != null
																			&& !dossierFilesResult.isEmpty())
																					? dossierFilesResult.size()
																					: 0;

																	for (int i = 0; i < deliverablesArr.length(); i++) {
																		JSONObject newFormDataObj = JSONFactoryUtil
																				.createJSONObject();
																		Iterator<?> keys = formDataObj.keys();
																		while (keys.hasNext()) {
																			String key = (String) keys.next();
																			if (!key.equals(deliverables)) {
																				newFormDataObj.put(key,
																						formDataObj.get(key));
																			}
																		}

																		JSONObject deliverableObj = deliverablesArr
																				.getJSONObject(i);
																		keys = deliverableObj.keys();
																		while (keys.hasNext()) {
																			String key = (String) keys.next();
																			newFormDataObj.put(key,
																					deliverableObj.get(key));
																		}

																		createFile = processEFormAndUpdateFormData(
																				dossierPart, groupId, dossierId,
																				formData, fileTemplateNo, dossierTempNo,
																				formDataObj, createFile,
																				serviceContext);

																		createFile.put(DossierFileTerm.E_FORM, eForm);
																		createFile.put(DossierFileTerm.FORM_SCRIPT,
																				formScript);
																		createFile.put(DossierFileTerm.COUNTER,
																				counter);

																		createFiles.put(createFile);
																	}
																}
															}
														}
													}
												}
												dossierFilesResult = DossierFileLocalServiceUtil
														.getDossierFileByDID_FTNO_DPTS_NOT_NULL_FID(dossierId,
																fileTemplateNo,
																new int[] { DossierPartTerm.DOSSIER_PART_TYPE_OUTPUT,
																		DossierPartTerm.DOSSIER_PART_TYPE_GROUP_OUTPUT },
																0, false);

												counter = (dossierFilesResult != null && !dossierFilesResult.isEmpty())
														? dossierFilesResult.size()
														: 0;
												createFile.put(DossierFileTerm.COUNTER, counter);
												createFile.put(ConstantUtils.FILE_ENTRY_ID, fileEntryId);
												createFiles.put(createFile);
											} else {
												List<DossierFile> dossierFilesResult = DossierFileLocalServiceUtil
														.getDossierFileByDID_FTNO_DPTS(dossierId, fileTemplateNo, new int[] { DossierPartTerm.DOSSIER_PART_TYPE_OUTPUT, DossierPartTerm.DOSSIER_PART_TYPE_GROUP_OUTPUT },
																false, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
																new DossierFileComparator(false, Field.CREATE_DATE,
																		Date.class));

												if (dossierFilesResult != null && !dossierFilesResult.isEmpty()) {
													createFile = JSONFactoryUtil.createJSONObject();
													createFile = processFileResult(dossierFilesResult, createFile,
															dossierPart.getPartNo());
													createFile.put(DossierPartTerm.DOSSIERPART_ID,
															dossierPart.getDossierPartId());
													createFile.put(DossierPartTerm.PART_NO, dossierPart.getPartNo());
													createFile.put(DossierPartTerm.PART_NAME,
															dossierPart.getPartName());
													createFile.put(DossierPartTerm.PART_TIP, dossierPart.getPartTip());
													createFile.put(DossierPartTerm.MULTIPLE, dossierPart.getMultiple());
													createFile.put(DossierPartTerm.FILE_TEMPLATE_NO, fileTemplateNo);
													createFile.put(DossierFileTerm.COUNTER, 1);
													createFiles.put(createFile);
												} else {
													eForm = Validator.isNotNull(dossierPart.getFormScript()) ? true
															: false;
													formData = AutoFillFormData.sampleDataBinding(
															dossierPart.getSampleData(), dossier, serviceContext);
													formScript = dossierPart.getFormScript();

													if (eForm && !dossierPart.getESign()) {
														DossierFileActions actions = new DossierFileActionsImpl();

														DossierFile dossierFile = null;

														try {
															dossierFile = DossierFileLocalServiceUtil
																	.getDossierFileByDID_FTNO_DPTS_First(dossierId,
																			fileTemplateNo, DossierPartTerm.DOSSIER_PART_TYPE_OUTPUT, false,
																			new DossierFileComparator(false,
																					Field.CREATE_DATE, Date.class));
														} catch (Exception e) {
															_log.debug(e);
														}
														try {
															dossierFile = DossierFileLocalServiceUtil
																	.getDossierFileByDID_FTNO_DPTS_First(dossierId,
																			fileTemplateNo, DossierPartTerm.DOSSIER_PART_TYPE_GROUP_OUTPUT, false,
																			new DossierFileComparator(false,
																					Field.CREATE_DATE, Date.class));
														} catch (Exception e) {
															_log.debug(e);
														}

														if (Validator.isNull(dossierFile)) {

															dossierFile = actions.addDossierFile(groupId, dossierId,
																	StringPool.BLANK, dossier.getDossierTemplateNo(),
																	dossierPart.getPartNo(), fileTemplateNo,
																	dossierPart.getPartName(), StringPool.BLANK, 0L,
																	null, StringPool.BLANK, String.valueOf(false),
																	serviceContext);
														}

														docFileReferenceUid = dossierFile.getReferenceUid();

														dossierFileId = dossierFile.getDossierFileId();
													} else if (Validator.isNotNull(formData)) {
														DossierFileActions actions = new DossierFileActionsImpl();

														DossierFile dossierFile = null;

														if (Validator.isNotNull(dossierPart.getDeliverableType())) {
															DeliverableType dlt = DeliverableTypeLocalServiceUtil
																	.getByCode(groupId,
																			dossierPart.getDeliverableType());
															if (dlt != null) {
																mappingData = dlt.getMappingData();
																mappingDataObj = JSONFactoryUtil
																		.createJSONObject(mappingData);
																if (mappingDataObj
																		.has(DeliverableTypesTerm.DELIVERABLES_KEY)) {
																	deliverables = mappingDataObj.getString(
																			DeliverableTypesTerm.DELIVERABLES_KEY);
																	formDataObj = JSONFactoryUtil
																			.createJSONObject(formData);
																	JSONArray deliverableListArr = JSONFactoryUtil
																			.createJSONArray();

																	if (Validator.isNull(deliverables)) {
																	} else {
																		if (formDataObj.has(deliverables)) {

																			JSONArray deliverablesArr = JSONFactoryUtil
																					.createJSONArray(formDataObj
																							.getString(deliverables));
																			for (int i = 0; i < deliverablesArr
																					.length(); i++) {
																				JSONObject newFormDataObj = JSONFactoryUtil
																						.createJSONObject();
																				Iterator<?> keys = formDataObj.keys();
																				while (keys.hasNext()) {
																					String key = (String) keys.next();
																					if (!key.equals(deliverables)) {
																						newFormDataObj.put(key,
																								formDataObj.get(key));
																					}
																				}
																				JSONObject deliverableObj = deliverablesArr
																						.getJSONObject(i);
																				keys = deliverableObj.keys();
																				while (keys.hasNext()) {
																					String key = (String) keys.next();
																					newFormDataObj.put(key,
																							deliverableObj.get(key));
																				}
																				deliverableListArr.put(newFormDataObj);

																				dossierFile = actions.addDossierFile(
																						groupId, dossierId,
																						StringPool.BLANK,
																						dossier.getDossierTemplateNo(),
																						dossierPart.getPartNo(),
																						fileTemplateNo,
																						dossierPart.getPartName(),
																						StringPool.BLANK, 0L, null,
																						StringPool.BLANK,
																						String.valueOf(false),
																						serviceContext);
																				dossierFile.setFormScript(dossierPart.getFormScript());
																				dossierFile.setEForm(dossierPart.getEForm());
																				dossierFile = DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

																				docFileReferenceUid = dossierFile
																						.getReferenceUid();
																				dossierFileId = dossierFile
																						.getDossierFileId();

																				DossierFileLocalServiceUtil
																						.updateFormData(groupId,
																								dossierId,
																								docFileReferenceUid,
																								newFormDataObj
																										.toJSONString(),
																								serviceContext);
																			}
																		}
																	}

																	formData = deliverableListArr.toJSONString();
																}
															}
														}
													}

													dossierFilesResult = DossierFileLocalServiceUtil
															.getDossierFileByDID_FTNO_DPTS_NOT_NULL_FID(dossierId,
																	fileTemplateNo,
																	new int[] {
																			DossierPartTerm.DOSSIER_PART_TYPE_OUTPUT,
																			DossierPartTerm.DOSSIER_PART_TYPE_GROUP_OUTPUT },
																	0, false);

													counter = (dossierFilesResult != null
															&& !dossierFilesResult.isEmpty())
																	? dossierFilesResult.size() : 0;

													createFile.put(DossierFileTerm.E_FORM, eForm);
													createFile.put(DossierFileTerm.DOSSIER_FILE_ID, dossierFileId);
													createFile.put(DossierFileTerm.FORM_DATA, formData);
													createFile.put(DossierFileTerm.FORM_SCRIPT, formScript);
													createFile.put(DossierFileTerm.REFERENCE_UID, docFileReferenceUid);
													createFile.put(DossierFileTerm.COUNTER, counter);
													createFile.put(DossierFileTerm.FILE_ENTRY_ID, fileEntryId);
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if (processAction != null) {
					result.put(ProcessActionTerm.STR_PROCESS_ACTION, processAction);
				}
				else {
					String actionCode = GetterUtil.getString(params.get(ProcessActionTerm.PROCESS_ACTION_ID));
					if (Validator.isNotNull(actionCode)
							&& ReadFilePropertiesUtils.get(ConstantUtils.FIX_DOSSIER_ACTION).equals(actionCode)) {
						ActionConfig ac = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
						ProcessAction newProcessAction = new ProcessActionImpl();
						newProcessAction.setActionCode(ac.getActionCode());
						newProcessAction.setGroupId(ac.getGroupId());
						result.put(ProcessActionTerm.STR_PROCESS_ACTION, newProcessAction);
					} else {
						result.put(ProcessActionTerm.STR_PROCESS_ACTION, processAction);
					}
				}
				result.put(ProcessActionTerm.CREATE_DOSSIERS, createFiles);
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONArray getPayloadNextActions(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, Integer start, Integer end,
			ServiceContext serviceContext) {

		long dossierId = GetterUtil.getLong(params.get(DossierTerm.DOSSIER_ID));
//		_log.info("dossierId =: " + dossierId);

		try {
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
//			_log.info("dossier: " + dossier);
			if (dossier != null) {
				DossierAction dossierAction = DossierActionLocalServiceUtil
						.fetchDossierAction(dossier.getDossierActionId());

				JSONArray result = JSONFactoryUtil.createJSONArray();
				if (dossierAction != null) {
					String actionCode = params.containsKey(DossierActionTerm.ACTION_CODE) ? (String)params.get(DossierActionTerm.ACTION_CODE) : StringPool.BLANK;
					
					if (Validator.isNotNull(actionCode)) {
						ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
						if (actConfig != null) {
							boolean extraForm = actConfig.getExtraForm();
							if (extraForm) {
								String formConfig = actConfig.getFormConfig();
								//_log.info("formConfig: " + formConfig);
								String sampleData = Validator.isNotNull(actConfig.getSampleData()) ? actConfig.getSampleData() : "{}";
								//_log.info("sampleData: "+sampleData);
								String formData = AutoFillFormData.sampleDataBinding(sampleData, dossier,
										serviceContext);
								JSONObject formDataJson = JSONFactoryUtil.createJSONObject(formData);

								JSONObject formConfigObj = JSONFactoryUtil.createJSONObject(formConfig);

								JSONArray formConfigArr = formConfigObj.getJSONArray(DossierTerm.DOSSIER_FIELD);
								
								//_log.info("formConfigArr: " + formConfigArr);
								if (formConfigArr != null && formConfigArr.length() > 0) {
									int length = formConfigArr.length();
									for (int i = 0; i < length; i++) {
										JSONObject jsonObject = formConfigArr.getJSONObject(i);
										String value = formDataJson.getString(jsonObject.getString(DossierTerm.DOSSIER_FIELD_NAME));
										jsonObject.put(DossierTerm.DOSSIER_VALUE, value);
//										_log.info("formConfigArr: " + formConfigArr);
										result.put(jsonObject);
									}
								}
								return result;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			_log.info(e);
		}
		return null;
	}

	private JSONObject processEFormAndUpdateFormData(DossierPart dossierPart, long groupId, long dossierId,
			String formData, String fileTemplateNo, String dossierTempNo, JSONObject formDataObj,
			JSONObject createFile, ServiceContext serviceContext) {

		String docFileReferenceUid = StringPool.BLANK;
		long dossierFileId  = 0;

		DossierFile dossierFile = null;
		try {
			dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_DPT_First(dossierId, fileTemplateNo, 2,
					false, new DossierFileComparator(false, Field.CREATE_DATE, Date.class));

			DossierFileActions actions = new DossierFileActionsImpl();
			if (Validator.isNull(dossierFile)) {
				dossierFile = actions.addDossierFile(groupId, dossierId, StringPool.BLANK, dossierTempNo,
						dossierPart.getPartNo(), fileTemplateNo, dossierPart.getPartName(), StringPool.BLANK, 0L, null,
						StringPool.BLANK, String.valueOf(false), serviceContext);
				dossierFile.setFormScript(dossierPart.getFormScript());
				dossierFile.setEForm(dossierPart.getEForm());
				dossierFile = DossierFileLocalServiceUtil.updateDossierFile(dossierFile);				
			}

			docFileReferenceUid = dossierFile.getReferenceUid();
			dossierFileId = dossierFile.getDossierFileId();
			//
			formDataObj = JSONFactoryUtil.createJSONObject(formData);
			formDataObj.put(DeliverableTerm.LICENCE_NO, dossierFile.getDeliverableCode());

//			_log.info("UPDATE FORM DATA GENERATE RESULT FILE");
			actions.updateDossierFileFormData(groupId, dossierId, docFileReferenceUid, formData, serviceContext);
		} catch (Exception e) {
			_log.debug(e);
		}
		createFile.put(DossierFileTerm.DOSSIER_FILE_ID, dossierFileId);
		createFile.put(DossierFileTerm.FORM_DATA, formData);
		createFile.put(DossierFileTerm.REFERENCE_UID, docFileReferenceUid);
		createFile.put(DossierFileTerm.FILE_ENTRY_ID, 0l);

		return createFile;
	}

	private JSONObject processEFormAndUpdateDossierFile(DossierPart dossierPart, long groupId, long dossierId,
			String formData, String fileTemplateNo, String dossierTempNo, JSONObject formDataObj,
			JSONObject createFile, ServiceContext serviceContext) {

		String docFileReferenceUid = StringPool.BLANK;
		long dossierFileId  = 0;

		// create Dossier File
		DossierFile dossierFile = null;
		try {
			try {
				dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_DPT_First(dossierId, fileTemplateNo,
						2, false, new DossierFileComparator(false, Field.CREATE_DATE, Date.class));
			}
			catch (Exception e) {
				_log.debug(e);
			}
			_log.info("dossierFile create:" + dossierFile);
			if (dossierFile == null) {
				DossierFileActions actions = new DossierFileActionsImpl();
				dossierFile = actions.addDossierFile(groupId, dossierId, StringPool.BLANK, dossierTempNo,
						dossierPart.getPartNo(), fileTemplateNo, dossierPart.getPartName(), StringPool.BLANK, 0L,
						null, StringPool.BLANK, String.valueOf(false), dossierPart.getFormScript(), dossierPart.getFormReport(),
						dossierPart.getEForm(), formData, serviceContext);

				_log.info("dossierFile create:" + dossierFile.getDossierPartNo() + "Timer create :" + new Date());
			}

			docFileReferenceUid = dossierFile.getReferenceUid();
			dossierFileId = dossierFile.getDossierFileId();
		} catch (Exception e) {
			_log.debug(e);
		}
		createFile.put(DossierFileTerm.DOSSIER_FILE_ID, dossierFileId);
		createFile.put(DossierFileTerm.FORM_DATA, dossierFile != null ? dossierFile.getFormData() : StringPool.BLANK);
		createFile.put(DossierFileTerm.REFERENCE_UID, docFileReferenceUid);
		createFile.put(DossierFileTerm.FILE_ENTRY_ID, 0l);

		return createFile;
	}

	private JSONObject processEFormByCreateFile(DossierPart dossierPart, long groupId, long dossierId, String sampleData,
			String fileTemplateNo, String dossierTempNo, JSONObject createFile, ServiceContext serviceContext) {

		String docFileReferenceUid = StringPool.BLANK;
		long dossierFileId  = 0;
		String formScript = dossierPart.getFormScript();
		boolean eForm = dossierPart.getEForm();
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		String formData = eForm ? AutoFillFormData.sampleDataBinding(sampleData, dossier, serviceContext) : StringPool.BLANK;

		// create Dossier File
		if (eForm) {
			DossierFile dossierFile = null;

			try {
				try {
					dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_DPT_First(dossierId, fileTemplateNo,
							2, false, new DossierFileComparator(false, Field.CREATE_DATE, Date.class));
				}
				catch (Exception e) {
					_log.debug(e);
				}
				if (dossierFile == null) {
					DossierFileActions actions = new DossierFileActionsImpl();
					dossierFile = actions.addDossierFile(groupId, dossierId, StringPool.BLANK, dossierTempNo,
							dossierPart.getPartNo(), fileTemplateNo, dossierPart.getPartName(), StringPool.BLANK, 0L,
							null, StringPool.BLANK, String.valueOf(false), serviceContext);
					dossierFile.setFormScript(formScript);
					dossierFile.setEForm(eForm);
					dossierFile = DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
				}

				docFileReferenceUid = dossierFile.getReferenceUid();
				dossierFileId = dossierFile.getDossierFileId();
			} catch (Exception e) {
				_log.debug(e);
			}
		}
		createFile.put(DossierFileTerm.E_FORM, eForm);
		createFile.put(DossierFileTerm.DOSSIER_FILE_ID, dossierFileId);
		createFile.put(DossierFileTerm.FORM_DATA, formData);
		createFile.put(DossierFileTerm.FORM_SCRIPT, formScript);
		createFile.put(DossierFileTerm.REFERENCE_UID, docFileReferenceUid);
		createFile.put(DossierFileTerm.FILE_ENTRY_ID, 0l);

		return createFile;

	}

	public JSONArray getNextActionTimmer(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext)
			throws PortalException {

		JSONArray results = JSONFactoryUtil.createJSONArray();

		List<ProcessAction> lstProcessAction;

		Dossier dossier = null;

		DossierAction dossierAction = null;

		long serviceProcessId = 0;

		String stepCode;

		String actionCode = GetterUtil.getString(params.get(DossierActionTerm.ACTION_CODE));
		String auto = GetterUtil.getString(params.get(DossierActionTerm.AUTO));

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
				dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
			}

			serviceProcessId = dossierAction != null ? dossierAction.getServiceProcessId() : 0;

			stepCode = dossierAction != null ? dossierAction.getStepCode() : StringPool.BLANK;

			boolean pending = dossierAction != null ? dossierAction.getPending() : false;

			if (Validator.isNotNull(stepCode)) {

				try {
					lstProcessAction = ProcessActionLocalServiceUtil.getProcessActionByG_SPID_PRESC(groupId,
							serviceProcessId, stepCode);
					for (ProcessAction processAction : lstProcessAction) {

						String[] preConditions = StringUtil.split(processAction.getPreCondition());

						boolean checkPreCondition = DossierMgtUtils.checkPreCondition(preConditions, dossier);

						if (!checkPreCondition) {
							continue;
						}

						JSONObject result = JSONFactoryUtil.createJSONObject();

						String postStepCode = processAction.getPostStepCode();

						if (Validator.isNull(postStepCode)) {
							continue;
						}

						ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(postStepCode, groupId,
								processAction.getServiceProcessId());

						List<ProcessStepRole> lstProcessStepRole = ProcessStepRoleLocalServiceUtil
								.findByP_S_ID(processStep.getProcessStepId());
						List<User> lstUser = new ArrayList<User>();

						if (Validator.isNotNull(auto) && (DossierTerm.PRECONDITION_SUBMIT.equals(processAction.getAutoEvent())
								|| DossierTerm.PRECONDITION_TIMER.equals(processAction.getAutoEvent()))) {
						} else {
							// Check roles

							if (lstProcessStepRole == null || lstProcessStepRole.isEmpty()) {
								// Search in ServiceProcessRole
								List<ServiceProcessRole> serviceProcessRoles = ServiceProcessRoleLocalServiceUtil
										.findByS_P_ID(serviceProcessId);

								for (ServiceProcessRole serviceProcessRole : serviceProcessRoles) {

									List<User> users = UserLocalServiceUtil
											.getRoleUsers(serviceProcessRole.getRoleId());

									if (users != null) {
										for (User user : users) {
											HashMap<String, Object> moderator = new HashMap<>();
											moderator.put(ProcessStepRoleTerm.MODERATOR, serviceProcessRole.getModerator());
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
											moderator.put(ProcessStepRoleTerm.MODERATOR, processStepRole.getModerator());
											user.setModelAttributes(moderator);
										}

										lstUser.addAll(users);
									}
								}
							}

						}

						result.put(ProcessActionTerm.PENDING, pending);
						result.put(ProcessActionTerm.STR_PROCESS_ACTION, processAction);
						result.put(ProcessActionTerm.LIST_USER, lstUser);

						results.put(result);
					}
				} catch (Exception e) {
					_log.error(e);
				}
			}
		}

		return results;
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
	

	@Override
	public DossierAction doAction(long groupId, long userId, Dossier dossier, ProcessOption option,
			ProcessAction proAction, String actionCode, String actionUser, String actionNote, String payload,
			String assignUsers, String payment, int syncType, ServiceContext context, ErrorMsgModel errorModel)
			throws Exception {
		return CPSDossierBusinessLocalServiceUtil.doAction(groupId, userId, dossier, option, proAction, actionCode, actionUser, actionNote, payload, assignUsers, payment, syncType, context);
	}

	public static boolean areEqualDouble(double a, double b, int precision) {
		return Math.abs(a - b) <= Math.pow(10, -precision);
	}


	@Override
	public Dossier markerVisited(long groupId, long dossierId, String referenceUid) throws PortalException {
		return null;
	}

	@Override
	public DossierAction doRollback(long groupId, long dossierId, String referenceUid, long userId)
			throws PortalException {
		return null;
	}

	@Override
	public JSONObject getContacts(long groupId, long dossierId, String referenceUid) throws PortalException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		// get DossierAction
		DossierAction dossierAction = DossierActionLocalServiceUtil.getByNextActionId(dossierId, 0);
		long dossierActionId = dossierAction.getDossierActionId();
		List<DossierActionUser> listUser = DossierActionUserLocalServiceUtil.getListUser(dossierActionId);
		result.put(ProcessActionTerm.LIST_USER, listUser);
		return result;
	}

	protected long getNextActionId(long groupId, long dossierId, String refId, ProcessAction currentAction) {

		return 0l;
	}

	protected JSONObject getDossierStatus(JSONObject ob, long groupId, String collectionCode, String itemCode) {

		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

		if (Validator.isNotNull(dc)) {
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dc.getPrimaryKey(), groupId);

			if (Validator.isNotNull(it)) {
				ob.put(itemCode, it.getItemName());
			}
		}

		return null;
	}

	protected boolean isSubmitType(ProcessAction processAction) {
		if (processAction.getAutoEvent().contentEquals(DossierTerm.PRECONDITION_SUBMIT)) {
			return true;
		} else {
			return false;
		}

	}

	protected Date getDueDate(long groupId, long dossierId, String refId, long processActionId) {
		return new Date();
	}

	protected String buildPayload(long groupId, long dossierId, String refId, long processActionId) {
		return StringPool.BLANK;
	}

	protected int getActionDueDate(long groupId, long dossierId, String refId, long processActionId) {
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

		if (hasDossierSync && dossier.getDossierStatus().contentEquals(DossierStatusConstants.NEW)
				&& !dossier.isOnline()) {
			isCreate = true;
		}

		return isCreate;
	}

	protected boolean hasDossierSync(long groupId, long dossierId, String refId, ProcessAction action)
			throws PortalException {

		Dossier dossier = getDossier(groupId, dossierId, refId);

		boolean isSync = false;

		if (dossier.getOnline() && action.getSyncActionCode().length() != 0) {
			isSync = true;
		}

		return isSync;

	}

	protected boolean forcedDossierSync(long groupId, long dossierId, String refId, ProcessAction action,
			boolean isSubmit) throws PortalException {

		Dossier dossier = getDossier(groupId, dossierId, refId);

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
			boolean online, boolean notification, String applicantNote, int originality, ServiceContext context) throws PortalException {

		SimpleDateFormat sdf = new SimpleDateFormat(APIDateTimeUtils._NORMAL_PARTTERN);
		Date appIdDate = null;

		try {
			appIdDate = sdf.parse(applicantIdDate);
		} catch (Exception e) {
			_log.debug(e);
		}

		Dossier dossier = null;

		try {

			//Process
			dossier = DossierLocalServiceUtil.initDossier(groupId, dossierId, referenceUid, counter, serviceCode,
					serviceName, govAgencyCode, govAgencyName, applicantName, applicantIdType, applicantIdNo, appIdDate,
					address, cityCode, cityName, districtCode, districtName, wardCode, wardName, contactName,
					contactTelNo, contactEmail, dossierTemplateNo, password, viaPostal, postalAddress, postalCityCode,
					postalCityName, postalTelNo, online, notification, applicantNote, originality, context);

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

	protected static Log _log = LogFactoryUtil.getLog(DossierActionsImpl.class);

	@Override
	public Dossier cloneDossier(long groupId, long dossierId, ServiceContext context) throws PortalException {

		Dossier srcDossier = DossierLocalServiceUtil.fetchDossier(dossierId);

		Dossier desDossier = DossierLocalServiceUtil.cloneDossier(srcDossier);

		DossierFileLocalServiceUtil.cloneDossierFilesByDossierId(groupId, desDossier.getPrimaryKey(), dossierId, 1,
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
			DictCollection dictCollection = DictCollectionLocalServiceUtil
					.fetchByF_dictCollectionCode(ReadFilePropertiesUtils.get(ConstantUtils.DOSSIER_STATUS), groupId);
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
					statistic.put(DossierTerm.KEY_DOSSIER_STATUS, statusCode);
					statistic.put(DossierTerm.KEY_DOSSIER_SUB_STATUS, subStatusCode);
					statistic.put(DossierTerm.KEY_DOSSIER_LEVEL, dictItem.getLevel());
					statistic.put(DossierTerm.KEY_DOSSIER_STATUS_NAME, dictItem.getItemName());
					statistic.put(ConstantUtils.VALUE_COUNT, count);

					statistics.put(statistic);

					total = count;
				}
			} else {

				List<DictItem> dictItems = DictItemLocalServiceUtil
						.findByF_dictCollectionId(dictCollection.getDictCollectionId());

				for (DictItem dictItem : dictItems) {

					subStatusCode = StringPool.BLANK;

					if (dictItem.getParentItemId() != 0) {
						subStatusCode = dictItem.getItemCode();
						DictItem parentDictItem = DictItemLocalServiceUtil.getDictItem(dictItem.getParentItemId());
						statusCode = parentDictItem.getItemCode();
					} else {
						statusCode = dictItem.getItemCode();
					}
					params.put(DossierTerm.STATUS, statusCode);
					params.put(DossierTerm.SUBSTATUS, subStatusCode);

					long count = DossierLocalServiceUtil.countLucene(params, searchContext);

					JSONObject statistic = JSONFactoryUtil.createJSONObject();

					statistic.put(DossierTerm.KEY_DOSSIER_STATUS, statusCode);
					statistic.put(DossierTerm.KEY_DOSSIER_SUB_STATUS, subStatusCode);
					statistic.put(DossierTerm.KEY_DOSSIER_LEVEL, dictItem.getLevel());
					statistic.put(DossierTerm.KEY_DOSSIER_STATUS_NAME, dictItem.getItemName());
					statistic.put(ConstantUtils.VALUE_COUNT, count);

					if (dictItem.getParentItemId() == 0) {
						total += count;
					}

					statistics.put(statistic);
				}
			}

			result.put(ConstantUtils.DATA, statistics);

			result.put(ConstantUtils.TOTAL, total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	private boolean checkPermission(String status, String subStatus, long groupId, long userId) {
		boolean isPermission = false;

		List<ProcessStep> processSteps;

		processSteps = ProcessStepLocalServiceUtil.getByStatusAnsSubStatus(status, subStatus, groupId);

		List<Role> roles = new ArrayList<Role>();

		for (ProcessStep step : processSteps) {
			List<ProcessStepRole> processStepRoles;

			processStepRoles = ProcessStepRoleLocalServiceUtil.findByP_S_ID(step.getPrimaryKey());

			for (ProcessStepRole stepRole : processStepRoles) {
				Role role = null;

				try {
					role = RoleLocalServiceUtil.getRole(stepRole.getRoleId());

					roles.add(role);
				} catch (Exception e) {
					_log.info(e);
				}
			}
		}

		for (Role role : roles) {

			long[] elmUsers = RoleLocalServiceUtil.getUserPrimaryKeys(role.getRoleId());

			for (long elmUserId : elmUsers) {
				if (elmUserId == userId) {
					isPermission = true;

					break;
				}
			}
		}

		return isPermission;
	}

	@Override
	public JSONObject getDossierTodoPermission(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);

		String statusCode = StringPool.BLANK;

		String subStatusCode = StringPool.BLANK;

		JSONArray statistics = JSONFactoryUtil.createJSONArray();

		long total = 0;

		try {
			DictCollection dictCollection = DictCollectionLocalServiceUtil
					.fetchByF_dictCollectionCode(ReadFilePropertiesUtils.get(ConstantUtils.DOSSIER_STATUS), groupId);
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
					statistic.put(DossierTerm.KEY_DOSSIER_STATUS, statusCode);
					statistic.put(DossierTerm.KEY_DOSSIER_SUB_STATUS, subStatusCode);
					statistic.put(DossierTerm.KEY_DOSSIER_LEVEL, dictItem.getLevel());
					statistic.put(DossierTerm.KEY_DOSSIER_STATUS_NAME, dictItem.getItemName());
					statistic.put(ConstantUtils.VALUE_COUNT, count);

					statistics.put(statistic);

					total = count;
				}

			} else {
				List<DictItem> dictItems = DictItemLocalServiceUtil
						.findByF_dictCollectionId(dictCollection.getDictCollectionId());

				// Get list dossierActionId


				for (DictItem dictItem : dictItems) {
					subStatusCode = StringPool.BLANK;
					// Get info status of dossier
					if (dictItem.getParentItemId() != 0) {
						subStatusCode = dictItem.getItemCode();
						// _log.info("subStatusCode: " +subStatusCode);
						DictItem parentDictItem = DictItemLocalServiceUtil.getDictItem(dictItem.getParentItemId());
						statusCode = parentDictItem.getItemCode();
						// _log.info("statusCode: " +statusCode);
					} else {
						statusCode = dictItem.getItemCode();
						// _log.info("statusCode: " +statusCode);
					}
					// Check permission user login
					boolean isPermission = checkPermission(statusCode, subStatusCode, groupId, userId);

					if (isPermission) {
						JSONObject statistic = JSONFactoryUtil.createJSONObject();

						params.put(DossierTerm.STATUS, statusCode);
						params.put(DossierTerm.SUBSTATUS, subStatusCode);
						params.put(DossierTerm.FOLLOW, String.valueOf(false));

						long count = DossierLocalServiceUtil.countLucene(params, searchContext);

						statistic.put(DossierTerm.KEY_DOSSIER_STATUS, statusCode);
						statistic.put(DossierTerm.KEY_DOSSIER_SUB_STATUS, subStatusCode);
						statistic.put(DossierTerm.KEY_DOSSIER_LEVEL, dictItem.getLevel());
						statistic.put(DossierTerm.KEY_DOSSIER_STATUS_NAME, dictItem.getItemName());
						statistic.put(ConstantUtils.VALUE_COUNT, count);

						if (dictItem.getParentItemId() != 0) {
							total += count;
						}
						statistics.put(statistic);
					}

				}
			}

			result.put(ConstantUtils.DATA, statistics);

			result.put(ConstantUtils.TOTAL, total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDossierCountTodoPermission(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Object object, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);

		String statusCode = StringPool.BLANK;

		String subStatusCode = StringPool.BLANK;

		JSONArray statistics = JSONFactoryUtil.createJSONArray();

		long total = 0;

		try {
			statusCode = GetterUtil.getString(params.get(DossierTerm.STATUS));

			if (Validator.isNotNull(statusCode) ) {

				String[] statusCodeArr = statusCode.split(StringPool.COMMA);
				if (statusCodeArr != null && statusCodeArr.length > 0) {
					for (String strStatus : statusCodeArr) {
						if (Validator.isNotNull(strStatus)) {
//							_log.info("strStatus: "+strStatus);
							params.put(DossierTerm.STATUS, strStatus);
							params.put(DossierTerm.SUBSTATUS, subStatusCode);
							params.put(DossierTerm.OWNER, String.valueOf(true));
							if (!DossierTerm.DOSSIER_STATUS_DONE.equals(strStatus) && !DossierTerm.DOSSIER_STATUS_CANCELLED.equals(strStatus)) {
								params.put(DossierTerm.NOT_STATE, DossierTerm.DOSSIER_STATUS_CANCELLING);
							}
							if(DossierTerm.DOSSIER_STATUS_CANCELLED.equals(strStatus)) {
								params.put(DossierTerm.NOT_STATE, StringPool.BLANK);
								params.put(DossierTerm.NOT_STATUS_REG, null);
							}

							long count = DossierLocalServiceUtil.countLucene(params, searchContext);

							JSONObject statistic = JSONFactoryUtil.createJSONObject();
							
							statistic.put(DossierTerm.KEY_DOSSIER_STATUS, strStatus);
							statistic.put(DossierTerm.KEY_DOSSIER_SUB_STATUS, subStatusCode);
							statistic.put(ConstantUtils.VALUE_COUNT, count);

							statistics.put(statistic);

							total += count;
						}
					}
				}
			}

			result.put(ConstantUtils.DATA, statistics);
//			_log.info("statistics: "+statistics);

			result.put(ConstantUtils.TOTAL, total);
//			_log.info("total: "+total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public Dossier createDossier(long groupId,String serviceCode, String govAgencyCode, String applicantName,
			String applicantIdType, String applicantIdNo, Date applicantIdDate, String address, String cityCode,
			String districtCode, String wardCode, String contactName, String contactTelNo, String contactEmail,
			boolean isSameAsApplicant, String delegateName, String delegateIdNo, String delegateTelNo,
			String delegateEmail, String delegateAddress, String delegateCityCode, String delegateDistrictCode,
			String delegateWardCode, String applicantNote, String briefNote,
			String dossierNo, String dossierTemplateNo, int viaPostal, String postalServiceCode,
			String postalServiceName, String postalAddress, String postalCityCode, String postalDistrictCode,
			String postalWardCode, String postalTelNo, int originality, ServiceContext context) throws PortalException {

		return DossierLocalServiceUtil.createDossier(groupId, serviceCode, govAgencyCode, applicantName,
				applicantIdType, applicantIdNo, applicantIdDate, address, cityCode, districtCode, wardCode, contactName,
				contactTelNo, contactEmail, isSameAsApplicant, delegateName, delegateIdNo, delegateTelNo, delegateEmail,
				delegateAddress, delegateCityCode, delegateDistrictCode, delegateWardCode, applicantNote, briefNote,
				dossierNo, dossierTemplateNo, viaPostal, postalServiceCode, postalServiceName, postalAddress, postalCityCode, postalDistrictCode, postalWardCode, postalTelNo, originality, context);
				
	}

	//LamTV_Process check permission action
	private boolean processCheckEnable(String preCondition, String autoEvent, Dossier dossier, String actionCode,
			long groupId) {
		if (DossierTerm.PRECONDITION_SUBMIT.equals(autoEvent) || DossierTerm.PRECONDITION_TIMER.equals(autoEvent)
				|| DossierTerm.PRECONDITION_LISTENER.equals(autoEvent) || DossierTerm.PRECONDITION_SPECIAL.equals(autoEvent)) {
			return false;
		}
		String[] preConditionArr = StringUtil.split(preCondition);
		if (preConditionArr != null && preConditionArr.length > 0) {
			return DossierMgtUtils.checkPreCondition(preConditionArr, dossier);
		}

		return true;
	}

	// LamTV_Process role list user
	private List<User> processRoleListUser(List<ProcessStepRole> processStepRoleList, long serviceProcessId) {
		List<User> lstUser = null;
		// Check roles
		_log.debug("processStepRoleList: "+processStepRoleList);
		if (processStepRoleList != null && processStepRoleList.size() > 0) {
			_log.debug("processStepRoleList.size(): "+processStepRoleList.size());
			lstUser = new ArrayList<User>();
			for (ProcessStepRole processStepRole : processStepRoleList) {
				List<User> users = UserLocalServiceUtil.getRoleUsers(processStepRole.getRoleId());
				if (users != null && users.size() > 0) {
					_log.debug("users.size(): "+users.size());
					HashMap<String, Object> assigned = new HashMap<>();
					assigned.put(ProcessStepRoleTerm.ASSIGNED, 0);
					for (User user : users) {
						if (!user.isLockout() && user.isActive()) {
							HashMap<String, Object> moderator = new HashMap<>();
							moderator.put(ProcessStepRoleTerm.MODERATOR, processStepRole.getModerator());
							user.setModelAttributes(moderator);
							user.setModelAttributes(assigned);

							lstUser.add(user);
						}
					}
				}
			}
		} else {
			// Search in ServiceProcessRole
			List<ServiceProcessRole> serviceProcessRoleList = ServiceProcessRoleLocalServiceUtil
					.findByS_P_ID(serviceProcessId);
			if (serviceProcessRoleList != null && serviceProcessRoleList.size() > 0) {
				lstUser = new ArrayList<User>();
				for (ServiceProcessRole serviceProcessRole : serviceProcessRoleList) {
					List<User> users = UserLocalServiceUtil
							.getRoleUsers(serviceProcessRole.getRoleId());
					if (users != null && users.size() > 0) {
						for (User user : users) {
							if (!user.isLockout() && user.isActive()) {
								HashMap<String, Object> moderator = new HashMap<>();
								moderator.put(ProcessStepRoleTerm.MODERATOR, serviceProcessRole.getModerator());
								user.setModelAttributes(moderator);

								lstUser.add(user);
							}
						}
					}
				}
			}
		}

		return lstUser;
	}

	private List<User> processRoleAsStepListUser(Dossier dossier, String stepCode, long serviceProcessId, ProcessStep processStep, 
			List<ProcessStepRole> processStepRoleList) {
		List<User> lstUser = null;
		// Check roles		
		lstUser = new ArrayList<User>();

		for (ProcessStepRole role : processStepRoleList) {
			List<User> lstUsers = UserLocalServiceUtil.getRoleUsers(role.getRoleId());
			for (User u : lstUsers) {
				if (!u.isLockout() && u.isActive()) {
					HashMap<String, Object> assigned = new HashMap<>();
					assigned.put(ProcessStepRoleTerm.ASSIGNED, 0);	
					HashMap<String, Object> moderator = new HashMap<>();
					moderator.put(ProcessStepRoleTerm.MODERATOR, role.getModerator());
					u.setModelAttributes(moderator);
					u.setModelAttributes(assigned);

					lstUser.add(u);
				}
			}
		}

		ProcessStep stepRoleAsStep = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, dossier.getGroupId(),
				serviceProcessId);
		if (stepRoleAsStep != null) {
			List<ProcessStepRole> stepRoleAsStepList = ProcessStepRoleLocalServiceUtil
					.findByP_S_ID(stepRoleAsStep.getProcessStepId());
			if (stepRoleAsStepList != null && stepRoleAsStepList.size() > 0) {
				for (ProcessStepRole role : stepRoleAsStepList) {
					List<User> lstUsers = UserLocalServiceUtil.getRoleUsers(role.getRoleId());
					for (User u : lstUsers) {
						if (!u.isLockout() && u.isActive()) {
							HashMap<String, Object> assigned = new HashMap<>();
							assigned.put(ProcessStepRoleTerm.ASSIGNED, 0);
							HashMap<String, Object> moderator = new HashMap<>();
							moderator.put(ProcessStepRoleTerm.MODERATOR, role.getModerator());
							u.setModelAttributes(moderator);
							u.setModelAttributes(assigned);

							lstUser.add(u);
						}
					}
				}
			}
		}

		return lstUser;
	}
	
	private List<User> processRoleAsStepDonedListUser(Dossier dossier, String stepCode, long serviceProcessId, ProcessStep processStep,
			List<ProcessStepRole> processStepRoleList) {
		List<User> lstUser = null;
		// Check roles
		List<DossierActionUser> lstDaus = DossierActionUserLocalServiceUtil.getByDossierAndStepCode(dossier.getDossierId(), stepCode);
		
		lstUser = new ArrayList<User>();

		for (ProcessStepRole role : processStepRoleList) {
			List<User> lstUsers = UserLocalServiceUtil.getRoleUsers(role.getRoleId());
			for (User u : lstUsers) {
				for (DossierActionUser dau : lstDaus) {
					if (dau.getUserId() == u.getUserId()) {
						User user = UserLocalServiceUtil.fetchUser(dau.getUserId());

						if (!user.isLockout() && user.isActive()) {
							HashMap<String, Object> assigned = new HashMap<>();
							assigned.put(ProcessStepRoleTerm.ASSIGNED, dau.getAssigned());
							HashMap<String, Object> moderator = new HashMap<>();
							moderator.put(ProcessStepRoleTerm.MODERATOR, dau.getModerator());
							user.setModelAttributes(moderator);
							user.setModelAttributes(assigned);

							lstUser.add(user);
						}
						break;
					}
				}
			}
		}

		return lstUser;
	}

	//LamTV_Process list file result
	private JSONObject processFileResult(List<DossierFile> dossierFilesResult, JSONObject createFile, String partNo) {
		boolean eForm = false;
		String formData;
		String formScript;
		String docFileReferenceUid;
		long fileEntryId = 0;
		long dossierFileId = 0;
		for (DossierFile dossierFile : dossierFilesResult) {
			if (dossierFile.getDossierPartNo().equals(partNo)) {
				eForm = dossierFile.getEForm();
				formData = dossierFile.getFormData();
				formScript = dossierFile.getFormScript();
				docFileReferenceUid = dossierFile.getReferenceUid();
				fileEntryId = dossierFile.getFileEntryId();
				dossierFileId = dossierFile.getDossierFileId();
				//
				createFile.put(DossierFileTerm.E_FORM, eForm);
				createFile.put(DossierFileTerm.DOSSIER_FILE_ID, dossierFileId);
				createFile.put(DossierFileTerm.FORM_DATA, formData);
				createFile.put(DossierFileTerm.FORM_SCRIPT, formScript);
				createFile.put(DossierFileTerm.REFERENCE_UID, docFileReferenceUid);
				createFile.put(DossierFileTerm.FILE_ENTRY_ID, fileEntryId);
				break;
			}
		}
		return createFile;
	}

	//LamTV_Process update Dossier
	@Override
	public Dossier initUpdateDossier(long groupId, long id, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address, String cityCode, String cityName,
			String districtCode, String districtName, String wardCode, String wardName, String contactName,
			String contactTelNo, String contactEmail, String dossierTemplateNo, Integer viaPostal, String postalAddress,
			String postalCityCode, String postalCityName, String postalTelNo, String applicantNote, boolean isSameAsApplicant,
			String delegateName, String delegateIdNo, String delegateTelNo, String delegateEmail,
			String delegateAddress, String delegateCityCode, String delegateDistrictCode, String delegateWardCode,
			Long sampleCount, ServiceContext serviceContext) {

		try {
			return DossierLocalServiceUtil.initUpdateDossier(groupId, id, applicantName, applicantIdType, applicantIdNo,
					applicantIdDate, address, cityCode, cityName, districtCode, districtName, wardCode, wardName,
					contactName, contactTelNo, contactEmail, dossierTemplateNo, viaPostal, postalAddress,
					postalCityCode, postalCityName, postalTelNo, applicantNote, isSameAsApplicant, delegateName,
					delegateIdNo, delegateTelNo, delegateEmail, delegateAddress, delegateCityCode, delegateDistrictCode,
					delegateWardCode, sampleCount, serviceContext);

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			return null;
		}
	}

	@Override
	public List<User> getAssignUsersByStep(Dossier dossier, ProcessStep ps) {
		List<User> lstUser = new ArrayList<>();

		if (ps != null) {

			List<ProcessStepRole> processStepRoleList = ProcessStepRoleLocalServiceUtil
					.findByP_S_ID(ps.getProcessStepId());
			if (Validator.isNotNull(ps.getRoleAsStep())) {
				String[] steps = StringUtil.split(ps.getRoleAsStep());
				for (String stepCode : steps) {
					if (stepCode.startsWith(StringPool.EXCLAMATION)) {
						int index = stepCode.indexOf(StringPool.EXCLAMATION);
						String stepCodePunc = stepCode.substring(index + 1);
						lstUser.addAll(processRoleAsStepDonedListUser(dossier, stepCodePunc, ps.getServiceProcessId(), ps, processStepRoleList));
					}
					else {
						lstUser.addAll(processRoleAsStepListUser(dossier, stepCode, ps.getServiceProcessId(), ps,
								processStepRoleList));
					}
				}
			}
			else {
				if (processStepRoleList != null && !processStepRoleList.isEmpty()) {
					List<ProcessStepRole> lstStepRoles = new ArrayList<>();
					for (ProcessStepRole psr : processStepRoleList) {
						if (Validator.isNotNull(psr.getCondition())) {
							String[] conditions = StringUtil.split(psr.getCondition());

							if (DossierMgtUtils.checkPreCondition(conditions, dossier)) {
								lstStepRoles.add(psr);
							}
						}
						else {
							lstStepRoles.add(psr);
						}
					}
					lstUser.addAll(processRoleListUser(lstStepRoles, ps.getServiceProcessId()));
				}						
			}
		}
		
		return lstUser;
	}

	@Override
	public ProcessOption getProcessOption(long serviceProcessId, long dossierTemplateId) {
		return ProcessOptionLocalServiceUtil.fetchBySP_DT(serviceProcessId, dossierTemplateId);
	}

	@Override
	public Dossier initDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String password,
			int viaPostal, String postalAddress, String postalCityCode, String postalCityName, String postalTelNo,
			boolean online, boolean notification, String applicantNote, int originality, ServiceInfo service,
			ServiceProcess serviceProcess, ProcessOption processOption, ServiceContext context) throws PortalException {

		SimpleDateFormat sdf = new SimpleDateFormat(APIDateTimeUtils._NORMAL_PARTTERN);
		Date appIdDate = null;

		try {
			appIdDate = sdf.parse(applicantIdDate);
		} catch (Exception e) {
			_log.debug(e);
		}

		Dossier dossier = null;

		try {

			//Process
			dossier = DossierLocalServiceUtil.initDossier(groupId, dossierId, referenceUid, counter, serviceCode,
					serviceName, govAgencyCode, govAgencyName, applicantName, applicantIdType, applicantIdNo, appIdDate,
					address, cityCode, cityName, districtCode, districtName, wardCode, wardName, contactName,
					contactTelNo, contactEmail, dossierTemplateNo, password, viaPostal, postalAddress, postalCityCode,
					postalCityName, postalTelNo, online, notification, applicantNote, originality, 
					service, serviceProcess, processOption,
					context);

		} catch (Exception e) {
			_log.error(e);
		}

		return dossier;
	}

	@Override
	public Dossier initUpdateDossier(long groupId, long id, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address, String cityCode, String cityName,
			String districtCode, String districtName, String wardCode, String wardName, String contactName,
			String contactTelNo, String contactEmail, String dossierTemplateNo, Integer viaPostal, String postalAddress,
			String postalCityCode, String postalCityName, String postalTelNo, String applicantNote,
			boolean isSameAsApplicant, String delegateName, String delegateIdNo, String delegateTelNo,
			String delegateEmail, String delegateAddress, String delegateCityCode, String delegateDistrictCode,
			String delegateWardCode, Long sampleCount, String dossierName, ServiceContext serviceContext) {
		try {
			return DossierLocalServiceUtil.initUpdateDossier(groupId, id, applicantName, applicantIdType, applicantIdNo,
					applicantIdDate, address, cityCode, cityName, districtCode, districtName, wardCode, wardName,
					contactName, contactTelNo, contactEmail, dossierTemplateNo, viaPostal, postalAddress,
					postalCityCode, postalCityName, postalTelNo, applicantNote, isSameAsApplicant, delegateName,
					delegateIdNo, delegateTelNo, delegateEmail, delegateAddress, delegateCityCode, delegateDistrictCode,
					delegateWardCode, sampleCount, dossierName, serviceContext);

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			return null;
		}		
	}	

	@Override
	public Dossier initUpdateDossier(long groupId, long id, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address, String cityCode, String cityName,
			String districtCode, String districtName, String wardCode, String wardName, String contactName,
			String contactTelNo, String contactEmail, String dossierTemplateNo, Integer viaPostal, String postalAddress,
			String postalCityCode, String postalCityName, String postalTelNo, String applicantNote,
			boolean isSameAsApplicant, String delegateName, String delegateIdNo, String delegateTelNo,
			String delegateEmail, String delegateAddress, String delegateCityCode, String delegateDistrictCode,
			String delegateWardCode, Long sampleCount, String dossierName, String briefNote,
			ServiceContext serviceContext) {
		try {
			return DossierLocalServiceUtil.initUpdateDossier(groupId, id, applicantName, applicantIdType, applicantIdNo,
					applicantIdDate, address, cityCode, cityName, districtCode, districtName, wardCode, wardName,
					contactName, contactTelNo, contactEmail, dossierTemplateNo, viaPostal, postalAddress,
					postalCityCode, postalCityName, postalTelNo, applicantNote, isSameAsApplicant, delegateName,
					delegateIdNo, delegateTelNo, delegateEmail, delegateAddress, delegateCityCode, delegateDistrictCode,
					delegateWardCode, sampleCount, dossierName, briefNote, serviceContext);

		} catch (Exception e) {
			_log.debug(e);
			return null;
		}		
	}

	@Override
	public Dossier initUpdateDossierFull(long groupId, long id, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address, String cityCode, String cityName,
			String districtCode, String districtName, String wardCode, String wardName, String contactName,
			String contactTelNo, String contactEmail, String dossierTemplateNo, Integer viaPostal, String postalAddress,
			String postalCityCode, String postalCityName, String postalTelNo, String applicantNote,
			boolean isSameAsApplicant, String delegateName, String delegateIdNo, String delegateTelNo,
			String delegateEmail, String delegateAddress, String delegateCityCode, String delegateDistrictCode,
			String delegateWardCode, Long sampleCount, String dossierName, String briefNote, Integer delegateType,
			String documentNo, Date documentDate, int systemId, ServiceContext serviceContext) {
		try {
			return DossierLocalServiceUtil.initUpdateDossierFull(groupId, id, applicantName, applicantIdType,
					applicantIdNo, applicantIdDate, address, cityCode, cityName, districtCode, districtName, wardCode,
					wardName, contactName, contactTelNo, contactEmail, dossierTemplateNo, viaPostal, postalAddress,
					postalCityCode, postalCityName, postalTelNo, applicantNote, isSameAsApplicant, delegateName,
					delegateIdNo, delegateTelNo, delegateEmail, delegateAddress, delegateCityCode, delegateDistrictCode,
					delegateWardCode, sampleCount, dossierName, briefNote, delegateType, documentNo, documentDate,
					systemId, serviceContext);

		} catch (Exception e) {
			_log.debug(e);
			return null;
		}		
	}
	
	@Override
	public Dossier publishDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String password,
			int viaPostal, String postalAddress, String postalCityCode, String postalCityName, String postalTelNo,
			boolean online, boolean notification, String applicantNote, int originality, Date createDate,
			Date modifiedDate, Date submitDate, Date receiveDate, Date dueDate, Date releaseDate, Date finishDate,
			Date cancellingDate, Date correctingDate, Date endorsementDate, Date extendDate, Date processDate,
			String dossierNo, String dossierStatus, String dossierStatusText, String dossierSubStatus,
			String dossierSubStatusText, long dossierActionId, String submissionNote, String lockState,
			String delegateName, String delegateIdNo, String delegateTelNo, String delegateEmail,
			String delegateAddress, String delegateCityCode, String delegateCityName, String delegateDistrictCode,
			String delegateDistrictName, String delegateWardCode, String delegateWardName, double durationCount,
			int durationUnit, String dossierName, String processNo, String metaData, ServiceContext context) throws PortalException {

		Date appIdDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(APIDateTimeUtils._NORMAL_DATE);
		try {
			appIdDate = sdf.parse(applicantIdDate);
		} catch (Exception e) {
			_log.debug(e);
		}
		Dossier dossier = null;
		try {
			//Process
			dossier = DossierLocalServiceUtil.publishDossier(groupId, dossierId, referenceUid, counter, serviceCode,
					serviceName, govAgencyCode, govAgencyName, applicantName, applicantIdType, applicantIdNo, appIdDate,
					address, cityCode, cityName, districtCode, districtName, wardCode, wardName, contactName,
					contactTelNo, contactEmail, dossierTemplateNo, password, viaPostal, postalAddress, postalCityCode,
					postalCityName, postalTelNo, online, notification, applicantNote, originality, createDate,
					modifiedDate, submitDate, receiveDate, dueDate, releaseDate, finishDate, cancellingDate,
					correctingDate, endorsementDate, extendDate, processDate, dossierNo, dossierStatus,
					dossierStatusText, dossierSubStatus, dossierSubStatusText, dossierActionId, submissionNote,
					lockState, delegateName, delegateIdNo, delegateTelNo, delegateEmail, delegateAddress,
					delegateCityCode, delegateCityName, delegateDistrictCode, delegateDistrictName, delegateWardCode,
					delegateWardName, durationCount, durationUnit, dossierName, processNo, metaData, context);

		} catch (Exception e) {
			_log.debug(e);
		}

		return dossier;
	}

}
