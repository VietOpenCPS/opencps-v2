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
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.opencps.cache.actions.CacheActions;
import org.opencps.cache.actions.impl.CacheActionsImpl;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.util.HolidayUtils;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.util.AutoFillFormData;
import org.opencps.dossiermgt.action.util.DeliverableNumberGenerator;
import org.opencps.dossiermgt.action.util.DossierContentGenerator;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.action.util.DossierNumberGenerator;
import org.opencps.dossiermgt.action.util.DossierPaymentUtils;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.constants.ActionConfigTerm;
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
import org.opencps.dossiermgt.model.ProcessPlugin;
import org.opencps.dossiermgt.model.ProcessSequence;
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
import org.opencps.dossiermgt.service.DossierRequestUDLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessPluginLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;
import org.opencps.usermgt.service.util.OCPSUserUtils;

import backend.auth.api.exception.ErrorMsgModel;
import backend.auth.api.exception.NotFoundException;

public class DossierActionsImpl implements DossierActions {

	public static final String SPECIAL_ACTION = "1100";
	public static final String FIX_DOSSIER_ACTION = "9100";
	public static final String AUTO_EVENT_SUBMIT = "submit";
	public static final String AUTO_EVENT_TIMMER = "timer";
	public static final String AUTO_EVENT_LISTENER = "listener";
	public static final String AUTO_EVENT_SPECIAL = "special";
	public static final String DOSSIER_SATUS_DC_CODE = "DOSSIER_STATUS";
	public static final String DOSSIER_SUB_SATUS_DC_CODE = "DOSSIER_SUB_STATUS";
	private static final long VALUE_CONVERT_DATE_TIMESTAMP = 1000 * 60 * 60 * 24;
	private static final long VALUE_CONVERT_HOUR_TIMESTAMP = 1000 * 60 * 60;
	private static final String EXTEND_ONE_VALUE = ".0";
	private static final String EXTEND_TWO_VALUE = ".00";
	
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

			result.put("data", hits.toList());

			long total = DossierLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

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

				result.put("data", hits.toList());

				long total = DossierLocalServiceUtil.countLucene(params, searchContext);

				result.put("total", total);

				return result;
			}

			// Get collection with collection Code
			DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode("DOSSIER_STATUS",
					groupId);
//			_log.info("dictCollection: "+dictCollection+" |groupId: "+groupId);

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
//					_log.info("53");
					String metaData = dictItem.getMetaData();
					String specialStatus = StringPool.BLANK;
					if (Validator.isNotNull(metaData)) {
//						_log.info("metaData: " + metaData);
						try {
							JSONObject metaJson = JSONFactoryUtil.createJSONObject(metaData);
							specialStatus = metaJson.getString("specialStatus");
//							_log.info("specialStatus: " + specialStatus);

						} catch (Exception e) {
							_log.debug(e);
							//_log.error(e);
						}
					}

					if (Validator.isNotNull(specialStatus) && Boolean.parseBoolean(specialStatus)) {
//						params.put(DossierTerm.DOSSIER_ACTION_ID, sb.toString());
						params.put(DossierTerm.FOLLOW, String.valueOf(true));
					} else {
						params.put(DossierTerm.FOLLOW, false);
					}

					hits = DossierLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);
					if (hits != null && hits.getLength() > 0) {
						result.put("data", hits.toList());
//						_log.info("hits.toList(): " + hits.toList().size());
						total = DossierLocalServiceUtil.countLucene(params, searchContext);
//						_log.info("total: " + total);
						result.put("total", total);
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
//						_log.info("dictItem: " + dictItem);
						String metaData = dictItem.getMetaData();
//						_log.info("metaData: " + metaData);
						String specialStatus = StringPool.BLANK;
						if (Validator.isNotNull(metaData)) {
//							_log.info("metaData: " + metaData);
							try {
								JSONObject metaJson = JSONFactoryUtil.createJSONObject(metaData);
								specialStatus = metaJson.getString("specialStatus");
//								_log.info("specialStatus: " + specialStatus);

							} catch (Exception e) {
								_log.debug(e);
								//_log.error(e);
							}
						}

//						statusCode = StringPool.BLANK;
						subStatusCode = StringPool.BLANK;
						// Get info status of dossier
						if (dictItem.getParentItemId() != 0) {
							subStatusCode = dictItem.getItemCode();
//							_log.info("subStatusCode: " + subStatusCode);
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

							if (Validator.isNotNull(specialStatus) && Boolean.parseBoolean(specialStatus)) {
								// Add params
								params.put(DossierTerm.STATUS, statusCode);
								params.put(DossierTerm.SUBSTATUS, subStatusCode);
								// params.put(DossierTerm.DOSSIER_ACTION_ID,
								// sb.toString());
								params.put(DossierTerm.FOLLOW, String.valueOf(true));

								hits = DossierLocalServiceUtil.searchLucene(params, sorts, -1, -1, searchContext);

								if (hits != null && hits.getLength() > 0) {
									long count = DossierLocalServiceUtil.countLucene(params, searchContext);
//									_log.info("count: " + count);
									if (dictItem.getParentItemId() != 0) {
										allDocsList.addAll(hits.toList());
//										_log.info("SizeList1: " + hits.toList().size());
										total += count;
									}
								}

							} else {
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
					}
					// Add all list dossier of multiple status
					result.put("data", allDocsList);
					result.put("total", total);
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

			result.put("data", hits.toList());

			long total = DossierLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

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

//		String auto = GetterUtil.getString(params.get(DossierActionTerm.AUTO));
//		_log.info("auto =: " + auto);
		long dossierId = GetterUtil.getLong(params.get(DossierTerm.DOSSIER_ID));
//		_log.info("dossierId =: " + dossierId);

		DossierAction dossierAction = null;
		List<ProcessAction> processActionList = null;
		JSONArray results = null;
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		User user = UserLocalServiceUtil.fetchUser(userId);

		List<Role> userRolesAdminCheck = user.getRoles();
		boolean isAdministratorData = false;
		for (Role r : userRolesAdminCheck) {
			if ("Administrator".equalsIgnoreCase(r.getName())) {
				isAdministratorData = true;
				break;
			}
		}
		
//		_log.info("dossier: "+dossier);
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
					ActionConfig ac = ActionConfigLocalServiceUtil.getByCode(groupId, FIX_DOSSIER_ACTION);
					if (ac != null) {
						if (results == null) {
							results = JSONFactoryUtil.createJSONArray();							
						}
						JSONObject data = JSONFactoryUtil.createJSONObject();
						data.put(ProcessActionTerm.ENABLE, 1);
						data.put(ProcessActionTerm.PROCESS_ACTION_ID, ac.getActionCode());
						data.put(ProcessActionTerm.ACTION_CODE, FIX_DOSSIER_ACTION);
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
//					 _log.info("Dossier action user :" + JSONFactoryUtil.looseSerialize(dActionUser));
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
							if ("Administrator".equalsIgnoreCase(r.getName())) {
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
						DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getByTemplateNo(dossier.getGroupId(), dossier.getDossierTemplateNo());
						option = getProcessOption(dossierAction.getServiceProcessId(), dossierTemplate.getDossierTemplateId());
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
					result.put("payment", payment);
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
							if (stepCode.startsWith("!")) {
								int index = stepCode.indexOf("!");
								String stepCodePunc = stepCode.substring(index + 1);
								lstUser.addAll(processRoleAsStepDonedListUser(dossier, stepCodePunc, serviceProcessId, processStep, processStepRoleList));
							}
							else {
								lstUser.addAll(processRoleAsStepListUser(dossier, stepCode, serviceProcessId, processStep, processStepRoleList));								
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
						result.put("lstUser", lstUser);
					}
				}

				if (processAction != null) {
					ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);
					
//					ProcessStep postStep = ProcessStepLocalServiceUtil.fetchBySC_GID(processAction.getPostStepCode(), groupId, serviceProcessId);
					ProcessStep postStep = processStep;

					JSONObject receivingObj = JSONFactoryUtil.createJSONObject();
					Date receiveDate = new Date();
					receivingObj.put(DossierTerm.RECEIVE_DATE, dossier.getReceiveDate() != null ? dossier.getReceiveDate().getTime() : receiveDate.getTime());
					Date dueDate = null;
					if (dossier.getDueDate() != null) {
						dueDate = dossier.getDueDate();
					} else {
						Double durationCount = serviceProcess.getDurationCount();
						if (Validator.isNotNull(String.valueOf(durationCount)) && durationCount > 0d) {
							dueDate = HolidayUtils.getDueDate(new Date(), serviceProcess.getDurationCount(), serviceProcess.getDurationUnit(), groupId);
						}
					}

					receivingObj.put(DossierTerm.DUE_DATE, dueDate != null ? dueDate.getTime() : 0l);
					receivingObj.put("editable", DossierMgtUtils.isDueDateEditable(serviceProcess.getDueDatePattern()));
					if (postStep != null) {
						if (DossierTerm.DOSSIER_STATUS_NEW.equals(postStep.getDossierStatus())
								|| DossierTerm.DOSSIER_STATUS_PROCESSING.equals(postStep.getDossierStatus())
								&& dossier.getReceiveDate() == null) {
							result.put("receiving", receivingObj);
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
								List<DossierFile> fileList = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_DPT(dossierId, fileTemplateNo, dossierPartType, false);
								if (fileList != null && fileList.size() > 0) {
										returnFiles.addAll(fileList);
								}
							}
						}
					}
					result.put("returnFiles", returnFiles);
				}

				JSONArray createFiles = JSONFactoryUtil.createJSONArray();
				if (createFileTempNoList != null && !createFileTempNoList.isEmpty()) {
//					DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getByTemplateNo(groupId,
//							dossierTempNo);

//					List<DossierPart> partList = DossierPartLocalServiceUtil.getByTemplateNo(groupId, dossierTemplate.getTemplateNo());

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
									createFile.put(DossierPartTerm.FILE_MARK, dossierMark.getFileMark());
									createFile.put(DossierPartTerm.FILE_COMMENT, dossierMark.getFileComment());
								} else {
									createFile.put(DossierPartTerm.FILE_MARK, 0);
									createFile.put(DossierPartTerm.FILE_MARK, 0);
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
								_log.info("strDeliverableType: "+strDeliverableType);
								if (Validator.isNull(strDeliverableType)) {
									List<DossierFile> dossierFilesResult = DossierFileLocalServiceUtil
											.getDossierFileByDID_FTNO_DPT(dossierId, fileTemplateNo, 2, false,
													QueryUtil.ALL_POS, QueryUtil.ALL_POS,
													new DossierFileComparator(false, "createDate", Date.class));
									_log.debug("dossierFilesResult: "+dossierFilesResult.size());
									if (dossierFilesResult != null && !dossierFilesResult.isEmpty()) {
										createFile = processFileResult(dossierFilesResult, createFile,
												dossierPart.getPartNo());
										_log.debug("createFile: "+createFile.toJSONString());

									} else {
										createFile = processEFormByCreateFile(dossierPart, groupId, dossierId,
												sampleData, fileTemplateNo, dossierTempNo, createFile, serviceContext);
										dossierFilesResult = DossierFileLocalServiceUtil
												.getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(dossierId, fileTemplateNo, 2, 0,
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
														.getDossierFileByDID_FTNO_DPT(dossierId, fileTemplateNo, 2,
																false, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
																new DossierFileComparator(false, "createDate",
																		Date.class));
												if (dossierFilesResult != null && !dossierFilesResult.isEmpty()) {
													createFile = processFileResult(dossierFilesResult, createFile,
															dossierPart.getPartNo());
												} else {
													formScript = dossierPart.getFormScript();
//													eForm = Validator.isNotNull(formScript) ? true : false;
													eForm = dossierPart.getEForm();
													formData = AutoFillFormData.sampleDataBinding(
															dossierPart.getSampleData(), dossierId, serviceContext);
//													_log.info("Dossier part: " + dossierPart.getPartNo());
//													_log.info("Form data: " + formData);
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
																			.getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(
																					dossierId, fileTemplateNo, 2, 0,
																					false);

																	counter = (dossierFilesResult != null
																			&& !dossierFilesResult.isEmpty())
																					? dossierFilesResult.size() : 0;

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
														.getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(dossierId,
																fileTemplateNo, 2, 0, false);

												counter = (dossierFilesResult != null && !dossierFilesResult.isEmpty())
														? dossierFilesResult.size() : 0;
												createFile.put(DossierFileTerm.COUNTER, counter);
												createFile.put("fileEntryId", fileEntryId);
												createFiles.put(createFile);
											} else {
												List<DossierFile> dossierFilesResult = DossierFileLocalServiceUtil
														.getDossierFileByDID_FTNO_DPT(dossierId, fileTemplateNo, 2,
																false, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
																new DossierFileComparator(false, "createDate",
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
															dossierPart.getSampleData(), dossierId, serviceContext);
													formScript = dossierPart.getFormScript();

													if (eForm && !dossierPart.getESign()) {
														DossierFileActions actions = new DossierFileActionsImpl();

														DossierFile dossierFile = null;

														try {
															dossierFile = DossierFileLocalServiceUtil
																	.getDossierFileByDID_FTNO_DPT_First(dossierId,
																			fileTemplateNo, 2, false,
																			new DossierFileComparator(false,
																					"createDate", Date.class));
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
																				newFormDataObj.put("LicenceNo",
																						DeliverableNumberGenerator
																								.generateDeliverableNumber(
																										groupId,
																										companyId,
																										dlt.getDeliverableTypeId()));

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
															.getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(dossierId,
																	fileTemplateNo, 2, 0, false);

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
					result.put("processAction", processAction);
				}
				else {
					String actionCode = GetterUtil.getString(params.get(ProcessActionTerm.PROCESS_ACTION_ID));
					if (Validator.isNotNull(actionCode) && FIX_DOSSIER_ACTION.equals(actionCode)) {
						ActionConfig ac = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
						ProcessAction newProcessAction = new ProcessActionImpl();
						newProcessAction.setActionCode(ac.getActionCode());
						newProcessAction.setGroupId(ac.getGroupId());
						result.put("processAction", newProcessAction);
					}
					else {
						result.put("processAction", processAction);
					}
				}
				result.put("createFiles", createFiles);
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
								String formData = AutoFillFormData.sampleDataBinding(sampleData, dossierId,
										serviceContext);
								JSONObject formDataJson = JSONFactoryUtil.createJSONObject(formData);

								JSONObject formConfigObj = JSONFactoryUtil.createJSONObject(formConfig);

								JSONArray formConfigArr = formConfigObj.getJSONArray("fields");
								
								//_log.info("formConfigArr: " + formConfigArr);
								if (formConfigArr != null && formConfigArr.length() > 0) {
									int length = formConfigArr.length();
									for (int i = 0; i < length; i++) {
										JSONObject jsonObject = formConfigArr.getJSONObject(i);
										String value = formDataJson.getString(jsonObject.getString("fieldName"));
										jsonObject.put("value", value);
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
			// TODO: handle exception
//			e.printStackTrace();
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
					false, new DossierFileComparator(false, "createDate", Date.class));

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
			formDataObj.put("LicenceNo", dossierFile.getDeliverableCode());

//			_log.info("UPDATE FORM DATA GENERATE RESULT FILE");
			actions.updateDossierFileFormData(groupId, dossierId, docFileReferenceUid, formData, serviceContext);
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
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
						2, false, new DossierFileComparator(false, "createDate", Date.class));
			}
			catch (Exception e) {
				_log.debug(e);
			}
			_log.info("dossierFile create:" + dossierFile);
			if (dossierFile == null) {
				DossierFileActions actions = new DossierFileActionsImpl();
//				dossierFile = actions.addDossierFile(groupId, dossierId, StringPool.BLANK, dossierTempNo,
//						dossierPart.getPartNo(), fileTemplateNo, dossierPart.getPartName(), StringPool.BLANK, 0L,
//						null, StringPool.BLANK, String.valueOf(false), serviceContext);
				//
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
			//_log.error(e);
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
//		boolean eForm = Validator.isNotNull(formScript) ? true : false;
		boolean eForm = dossierPart.getEForm();
		String formData = eForm ? AutoFillFormData.sampleDataBinding(sampleData, dossierId, serviceContext) : StringPool.BLANK;

		// create Dossier File
		if (eForm) {
			DossierFile dossierFile = null;

			try {
				try {
					dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_DPT_First(dossierId, fileTemplateNo,
							2, false, new DossierFileComparator(false, "createDate", Date.class));
				}
				catch (Exception e) {
					_log.debug(e);
				}
				if (dossierFile == null) {
					DossierFileActions actions = new DossierFileActionsImpl();
					dossierFile = actions.addDossierFile(groupId, dossierId, StringPool.BLANK, dossierTempNo,
							dossierPart.getPartNo(), fileTemplateNo, dossierPart.getPartName(), StringPool.BLANK, 0L,
							null, StringPool.BLANK, String.valueOf(false), serviceContext);
//					_log.info("dossierFile create:" + dossierFile.getDossierPartNo() + "Timer create :" + new Date());
					dossierFile.setFormScript(formScript);
					dossierFile.setEForm(eForm);
					dossierFile = DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
				}

				docFileReferenceUid = dossierFile.getReferenceUid();
				dossierFileId = dossierFile.getDossierFileId();
			} catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
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
		// TODO filter by Auto
		String auto = GetterUtil.getString(params.get(DossierActionTerm.AUTO));

		// auto != null ko check role, check dk preCondition = auto or submmit

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

						// String createDossierFiles =
						// processAction.getCreateDossierFiles();

						// String returnDossierFiles =
						// processAction.getReturnDossierFiles();

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

						if (Validator.isNotNull(auto) && ("submit".equals(processAction.getAutoEvent())
								|| "timer".equals(processAction.getAutoEvent()))) {
							// TODO ?
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

						}

						result.put("pending", pending);
						result.put("processAction", processAction);
						result.put("lstUser", lstUser);

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
	
	private void generateCreateDossierFiles(long groupId, long userId, Dossier dossier, ProcessAction processAction) throws PortalException {
		ServiceContext context = new ServiceContext();
		context.setScopeGroupId(groupId);
		context.setCompanyId(dossier.getCompanyId());
		context.setUserId(userId);

		String createDossierFiles = processAction.getCreateDossierFiles();

		List<String> dossierFileTemplateNos = ListUtil.toList(StringUtil.split(createDossierFiles));

		DossierFileActions actions = new DossierFileActionsImpl();
		DossierFile dossierFile = null;
		String docFileReferenceUid;
		List<DossierFile> dossierFilesResult = null;

		if (dossierFileTemplateNos != null && !dossierFileTemplateNos.isEmpty()) {
			DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getByTemplateNo(groupId,
					dossier.getDossierTemplateNo());

			List<DossierPart> dossierParts = DossierPartLocalServiceUtil.getByTemplateNo(groupId,
					dossierTemplate.getTemplateNo());

			if (dossierParts != null) {
				for (DossierPart dossierPart : dossierParts) {

					String fileTemplateNo = dossierPart.getFileTemplateNo();

					if (dossierFileTemplateNos.contains(fileTemplateNo)) {
						String formData;
												
						//End add generate deliverable if has deliverable type
						if (Validator.isNull(dossierPart.getDeliverableType())) {						
							formData = AutoFillFormData.sampleDataBinding(dossierPart.getSampleData(),
									dossier.getDossierId(), context);
							try {
								dossierFile = DossierFileLocalServiceUtil
										.getDossierFileByDID_FTNO_DPT_First(dossier.getDossierId(),
												fileTemplateNo, 2, false, new DossierFileComparator(
														false, "createDate", Date.class));
							} catch (Exception e) {
								_log.debug(e);
								//_log.error(e);
							}

							if (Validator.isNull(dossierFile)
									&& dossierPart.getEForm()) {
								dossierFile = actions.addDossierFile(groupId, dossier.getDossierId(),
										StringPool.BLANK, dossier.getDossierTemplateNo(),
										dossierPart.getPartNo(), fileTemplateNo,
										dossierPart.getPartName(), StringPool.BLANK, 0L, null,
										StringPool.BLANK, String.valueOf(false), context);
								dossierFile.setFormScript(dossierPart.getFormScript());
								dossierFile.setEForm(dossierPart.getEForm());
								dossierFile = DossierFileLocalServiceUtil.updateDossierFile(dossierFile);								
								docFileReferenceUid = dossierFile.getReferenceUid();
								actions.updateDossierFileFormData(groupId, dossier.getDossierId(), docFileReferenceUid, formData, context);																													
							}
						}
						else {
							String deliverableTypeStr = dossierPart.getDeliverableType();
//							String formDataDeliverables = AutoFillFormData.sampleDataBinding(dossierPart.getSampleData(), dossier.getDossierId(), context);

							JSONObject formDataObj;
								
							DeliverableType deliverableTypeObject = DeliverableTypeLocalServiceUtil.getByCode(groupId, deliverableTypeStr);
							if (deliverableTypeObject != null) {
								String mappingData = deliverableTypeObject.getMappingData();
								JSONObject mappingDataObj = JSONFactoryUtil.createJSONObject(mappingData);
								if (mappingDataObj.has(DeliverableTypesTerm.DELIVERABLES_KEY)) {
									String deliverables = mappingDataObj.getString(DeliverableTypesTerm.DELIVERABLES_KEY);
									if (Validator.isNull(deliverables)) {
										//Add one deliverable
										dossierFilesResult = DossierFileLocalServiceUtil
														.getDossierFileByDID_FTNO_DPT(dossier.getDossierId(), fileTemplateNo, 2, false,
																QueryUtil.ALL_POS, QueryUtil.ALL_POS,
																new DossierFileComparator(false, "createDate", Date.class));
										if (dossierFilesResult != null && !dossierFilesResult.isEmpty()) {
										} else {
											formData = AutoFillFormData.sampleDataBinding(dossierPart.getSampleData(),
															dossier.getDossierId(), context);
											try {
												dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_DPT_First(dossier.getDossierId(),
																			fileTemplateNo, 2, false, new DossierFileComparator(
																					false, "createDate", Date.class));
											} catch (Exception e) {
												_log.debug(e);
												//_log.error(e);
											}
											if (Validator.isNull(dossierFile)) {
												dossierFile = actions.addDossierFile(groupId, dossier.getDossierId(),
																	StringPool.BLANK, dossier.getDossierTemplateNo(),
																	dossierPart.getPartNo(), fileTemplateNo,
																	dossierPart.getPartName(), StringPool.BLANK, 0L, null,
																	StringPool.BLANK, String.valueOf(false), context);
												dossierFile.setFormScript(dossierPart.getFormScript());
												dossierFile.setEForm(dossierPart.getEForm());
												dossierFile = DossierFileLocalServiceUtil.updateDossierFile(dossierFile);												
											}
		
//											docFileReferenceUid = dossierFile.getReferenceUid();
		
											dossierFile.setFormData(formData);
											DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
										}
									}
									else {
										formData = AutoFillFormData.sampleDataBinding(dossierPart.getSampleData(),
												dossier.getDossierId(), context);
										
										DeliverableType dlt = DeliverableTypeLocalServiceUtil.getByCode(groupId, dossierPart.getDeliverableType());
										mappingData = dlt.getMappingData();
										mappingDataObj = JSONFactoryUtil.createJSONObject(mappingData);
										formDataObj = JSONFactoryUtil.createJSONObject(formData);
										if (mappingDataObj.has(DeliverableTypesTerm.DELIVERABLES_KEY)) {
											deliverables = mappingDataObj.getString(DeliverableTypesTerm.DELIVERABLES_KEY);
											if (Validator.isNull(deliverables)) {
												try {
													dossierFile = DossierFileLocalServiceUtil
																			.getDossierFileByDID_FTNO_DPT_First(dossier.getDossierId(),
																					fileTemplateNo, 2, false, new DossierFileComparator(
																							false, "createDate", Date.class));
												} catch (Exception e) {
													_log.debug(e);
													//_log.error(e);
												}
												if (Validator.isNull(dossierFile)) {
													dossierFile = actions.addDossierFile(groupId, dossier.getDossierId(),
																			StringPool.BLANK, dossier.getDossierTemplateNo(),
																			dossierPart.getPartNo(), fileTemplateNo,
																			dossierPart.getPartName(), StringPool.BLANK, 0L, null,
																			StringPool.BLANK, String.valueOf(false), context);
												}

												docFileReferenceUid = dossierFile.getReferenceUid();
				
												actions.updateDossierFileFormData(groupId, dossier.getDossierId(), docFileReferenceUid, formData, context);																						
											}
											else {
												if (formDataObj.has(deliverables)) {
													formDataObj = JSONFactoryUtil.createJSONObject(formData);
													JSONArray deliverablesArr = JSONFactoryUtil.createJSONArray(formDataObj.getString(deliverables));
//													dossierFilesResult = DossierFileLocalServiceUtil
//														.getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(dossier.getDossierId(), fileTemplateNo, 2,
//																					0, false);
													for (int i = 0; i < deliverablesArr.length(); i++) {
														JSONObject newFormDataObj = JSONFactoryUtil.createJSONObject();
														Iterator<?> keys = formDataObj.keys();
														while( keys.hasNext() ) {
															String key = (String)keys.next();
															if (!key.equals(deliverables)) {
																newFormDataObj.put(key, formDataObj.get(key));
															}
														}							
	
														JSONObject deliverableObj = deliverablesArr.getJSONObject(i);
														keys = deliverableObj.keys();
	
														while( keys.hasNext() ) {
															String key = (String)keys.next();
															newFormDataObj.put(key, deliverableObj.get(key));
														}	
																			
														try {
															dossierFile = DossierFileLocalServiceUtil
																	.getDossierFileByDID_FTNO_DPT_First(dossier.getDossierId(),
																			fileTemplateNo, 2, false, new DossierFileComparator(
																false, "createDate", Date.class));
														} catch (Exception e) {
															_log.debug(e);
															//_log.error(e);
														}
														if (Validator.isNull(dossierFile)) {
															dossierFile = actions.addDossierFile(groupId, dossier.getDossierId(),
																	StringPool.BLANK, dossier.getDossierTemplateNo(),
																	dossierPart.getPartNo(), fileTemplateNo,
																	dossierPart.getPartName(), StringPool.BLANK, 0L, null,
																	StringPool.BLANK, String.valueOf(false), context);
															dossierFile.setFormScript(dossierPart.getFormScript());
															dossierFile.setEForm(dossierPart.getEForm());
															dossierFile = DossierFileLocalServiceUtil.updateDossierFile(dossierFile);															
														}
	
														docFileReferenceUid = dossierFile.getReferenceUid();			
														actions.updateDossierFileFormData(groupId, dossier.getDossierId(), docFileReferenceUid, newFormDataObj.toJSONString(), context);																																							
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}	
	}

	@Override
	public DossierAction doAction(long groupId, long userId, Dossier dossier, ProcessOption option,
			ProcessAction proAction, String actionCode, String actionUser, String actionNote, String payload,
			String assignUsers, String payment, int syncType, ServiceContext context, ErrorMsgModel errorModel)
			throws Exception {
		return CPSDossierBusinessLocalServiceUtil.doAction(groupId, userId, dossier, option, proAction, actionCode, actionUser, actionNote, payload, assignUsers, payment, syncType, context);
	}

	private static JSONArray getProcessSequencesJSON(String[] sequenceArr, List<ProcessSequence> sequenceList) {

		JSONArray jsonSequenceArr = JSONFactoryUtil.createJSONArray();
		if (sequenceArr != null && sequenceArr.length > 0) {
			for (int i = 0; i < sequenceArr.length - 1; i++) {
				String sequenceNo = sequenceArr[i];
				JSONObject sequenceObj = JSONFactoryUtil.createJSONObject();
				for (ProcessSequence proSeq : sequenceList) {
					if (sequenceNo.equals(proSeq.getSequenceNo())) {
						sequenceObj.put("sequenceNo", proSeq.getSequenceNo());
						sequenceObj.put("sequenceName", proSeq.getSequenceName());
						sequenceObj.put("sequenceRole", proSeq.getSequenceRole());
						sequenceObj.put("durationCount", proSeq.getDurationCount());
						sequenceObj.put("createDate", proSeq.getCreateDate());
					}
				}
				String nextSequenceNo = sequenceArr[i + 1];
				for (ProcessSequence proSeq : sequenceList) {
					if (nextSequenceNo.equals(proSeq.getSequenceNo())) {
						sequenceObj.put("nextSequenceNo", proSeq.getSequenceNo());
						sequenceObj.put("nextSequenceName", proSeq.getSequenceName());
						sequenceObj.put(DossierTerm.NEXT_SEQUENCE_ROLE, proSeq.getSequenceRole());
						sequenceObj.put("nextCreateDate", proSeq.getCreateDate());
					}
				}
				jsonSequenceArr.put(sequenceObj);
			}
		}

		return jsonSequenceArr;
	}

	public static boolean areEqualDouble(double a, double b, int precision) {
		return Math.abs(a - b) <= Math.pow(10, -precision);
	}

	@Override
	public DossierAction doAction(long groupId, long dossierId, String referenceUid, String actionCode,
			long processActionId, String actionUser, String actionNote, long assignUserId, long userId,
			String subUsers, ServiceContext context) throws PortalException {

		_log.info("STRART DO ACTION ==========:GroupID: "+groupId);
		context.setUserId(userId);

		DossierAction dossierAction = null;

		Dossier dossier = getDossier(groupId, dossierId, referenceUid);
		// _log.info("dossier: " + dossier);
		
//		String type = StringPool.BLANK;

//		if (dossier != null) {
//			String dossierStatus = dossier.getDossierStatus().toLowerCase();
//			if (Validator.isNotNull(dossierStatus) && !dossierStatus.equals("new")) {
//				String applicantNote = _buildDossierNote(dossier, actionNote, groupId, type);
//				_log.info("applicantNote: "+applicantNote);
//
//				dossier.setApplicantNote(applicantNote);
//
//				DossierLocalServiceUtil.updateDossier(dossier);
//			}
//		}

		if (Validator.isNull(dossier)) {
			throw new NotFoundException("DossierNotFoundException");
		}

		ProcessOption option = null;
		ActionConfig actionConfig = null;

		try {
			_log.info("dossier.getServiceCode(): " + dossier.getServiceCode());
			_log.info("dossier.getGovAgencyCode(): " + dossier.getGovAgencyCode());
			_log.info("dossier.getDossierTemplateNo(): " + dossier.getDossierTemplateNo());
			option = getProcessOption(dossier.getServiceCode(), dossier.getGovAgencyCode(),
					dossier.getDossierTemplateNo(), groupId);
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			throw new NotFoundException("ServiceProcessNotFoundException");
		}

		long serviceProcessId = option.getServiceProcessId();
		_log.info("serviceProcessId: " + serviceProcessId);

		ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);

		ProcessAction processAction = null;

		if (processActionId != 0) {
			_log.info("PROCESS_ACTION");
			processAction = ProcessActionLocalServiceUtil.getProcessAction(processActionId);
			if (processAction != null) {
				_log.info("SYNC action: " + processAction.getSyncActionCode());
			}
		} else {
			_log.info("PROCESS_ACTION_BY_CODE");

			processAction = getProcessAction(groupId, dossierId, referenceUid, actionCode, serviceProcessId);
			if (processAction != null) {
				_log.info("processAction: " + processAction.getActionCode());
			}
		}

		// Add paymentFile
		if (processAction != null && Validator.isNotNull(processAction.getPaymentFee())) {
			try {
				DossierPaymentUtils.processPaymentFile(processAction, processAction.getPaymentFee(), groupId, dossierId, userId,
						context, serviceProcess.getServerNo());
			} catch (Exception e) {

				_log.debug(e);
				//_log.error(e);
				_log.info("Can not create PaymentFile with pattern \"" + processAction.getPaymentFee() + "\"");
			}

		}

//		boolean isSubmitType = isSubmitType(processAction);

		boolean hasDossierSync = false;

		if (processActionId != 0) {
			hasDossierSync = hasDossierSync(groupId, dossierId, referenceUid, processAction);
		}

		// TODO take a look later

//		boolean hasForedDossierSync = forcedDossierSync(groupId, dossierId, referenceUid, processAction, isSubmitType);
//
//		boolean isCreateDossier = hasCreateDossier(groupId, dossierId, referenceUid, actionCode, serviceProcessId,
//				hasDossierSync);

		// TODO Hard fix for test
		List<String> types = new ArrayList<>();
		types.add(OCPSUserUtils.APPLICANT_01);
		types.add(OCPSUserUtils.APPLICANT_02);
		types.add(OCPSUserUtils.EMPLOYEE_01);
		types.add(OCPSUserUtils.EMPLOYEE_02);

		String postStepCode = processAction.getPostStepCode();

		ProcessStep curStep = ProcessStepLocalServiceUtil.fetchBySC_GID(postStepCode, groupId, serviceProcessId);

		int actionOverdue = getActionDueDate(groupId, dossierId, referenceUid, processActionId);

//		Date dueDate = getDueDate(groupId, dossierId, referenceUid, processActionId);

		String payload = buildPayload(groupId, dossierId, referenceUid, processActionId);
		actionConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);

		DossierAction previousAction = null;
		if (dossier.getDossierActionId() != 0) {
			previousAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		}

		// In the special action (actionCode = 1100, save DOSSIER in SERVER)
		if (actionCode.contentEquals(SPECIAL_ACTION)
				&& (types.contains(OCPSUserUtils.APPLICANT_01) || types.contains(OCPSUserUtils.APPLICANT_02))) {
			// TODO check DossierStatus
			_log.info("DO_SPECIAL_ACTION");

			// Set dossierStatus is NEW
			JSONObject jsStatus = JSONFactoryUtil.createJSONObject();

			getDossierStatus(jsStatus, groupId, DOSSIER_SATUS_DC_CODE, DossierStatusConstants.NEW);

			if (curStep != null && actionConfig != null) {
				String fromStepCode = previousAction != null ? previousAction.getStepCode() : StringPool.BLANK;
				String fromStepName = previousAction != null ? previousAction.getStepName() : StringPool.BLANK;
				String fromSequenceNo = previousAction != null ? previousAction.getSequenceNo() : StringPool.BLANK;
				
				dossierAction = DossierActionLocalServiceUtil.updateDossierAction(groupId, 0, dossierId, serviceProcessId,
						0l, 
						fromStepCode, fromStepName, fromSequenceNo,
						actionCode, actionUser, processAction.getActionName(), actionNote, actionOverdue,
						processAction.getSyncActionCode(), false, processAction.getRollbackable(), curStep.getStepCode(),
						curStep.getStepName(), 
						curStep.getSequenceNo(),
						null, 0l, payload, curStep.getStepInstruction(), 
						DossierActionTerm.STATE_ALREADY_PROCESSED, (actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_NOT_SENT ? DossierActionTerm.EVENT_STATUS_NOT_CREATED : DossierActionTerm.EVENT_STATUS_WAIT_SENDING), 
						context);				
				if (actionConfig.getRollbackable()) {
					DossierActionLocalServiceUtil.updateRollbackable(dossierAction.getDossierActionId(), true);
				}
			}
			else {
				dossierAction = DossierActionLocalServiceUtil.updateDossierAction(groupId, 0, dossierId, serviceProcessId,
						0l, 
						StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
						actionCode, actionUser, processAction.getActionName(), actionNote, actionOverdue,
						processAction.getSyncActionCode(), false, processAction.getRollbackable(), curStep != null ? curStep.getStepCode() : StringPool.BLANK,
						curStep != null ? curStep.getStepName() : StringPool.BLANK, 
						curStep != null ? curStep.getSequenceNo() : StringPool.BLANK,
						null, 0l, payload, curStep != null ? curStep.getStepInstruction() : StringPool.BLANK, 
						DossierActionTerm.STATE_ALREADY_PROCESSED, DossierActionTerm.EVENT_STATUS_NOT_CREATED, 						
						context);				
			}

			// Add DossierActionUser

			DossierActionUserImpl dossierActionUser = new DossierActionUserImpl();

			_log.debug("subUsers***" + subUsers);

			if (Validator.isNotNull(subUsers)) {
				JSONArray subUsersArray = JSONFactoryUtil.createJSONArray(subUsers);
				dossierActionUser.assignDossierActionUser(dossier, processAction.getAllowAssignUser(), dossierAction, userId, groupId,
						assignUserId, subUsersArray, 0);
			} else {
				dossierActionUser.initDossierActionUser(processAction, dossier, processAction.getAllowAssignUser(), dossierAction, userId, groupId,
						assignUserId);
			}

			dossier = DossierLocalServiceUtil.updateStatus(groupId, dossierId, referenceUid, DossierStatusConstants.NEW,
					jsStatus.getString(DossierStatusConstants.NEW), StringPool.BLANK, StringPool.BLANK,
					StringPool.BLANK, dossierAction.getStepInstruction(), context);

		} else {

			_log.info("NEXT_ACTION");

			JSONObject jsStatus = JSONFactoryUtil.createJSONObject();

			JSONObject jsSubStatus = JSONFactoryUtil.createJSONObject();

			// String syncActionCode = processAction.getSyncActionCode();

			_log.info("curStep.getDossierStatus(): " + curStep.getDossierStatus());
			getDossierStatus(jsStatus, groupId, DOSSIER_SATUS_DC_CODE, curStep.getDossierStatus());

			// update reference dossier
			DossierAction prvAction = DossierActionLocalServiceUtil.getByNextActionId(dossierId, 0l);

			String fromStepCode = previousAction != null ? previousAction.getStepCode() : StringPool.BLANK;
			String fromStepName = previousAction != null ? previousAction.getStepName() : StringPool.BLANK;
			String fromSequenceNo = previousAction != null ? previousAction.getSequenceNo() : StringPool.BLANK;

			int state = DossierActionTerm.STATE_ALREADY_PROCESSED;
			int eventStatus = (actionConfig != null ? (actionConfig.getEventType() == ActionConfigTerm.EVENT_TYPE_NOT_SENT ? DossierActionTerm.EVENT_STATUS_NOT_CREATED : DossierActionTerm.EVENT_STATUS_WAIT_SENDING) : DossierActionTerm.EVENT_STATUS_NOT_CREATED);
			dossierAction = DossierActionLocalServiceUtil.updateDossierAction(groupId, 0, dossierId, serviceProcessId,
					0l, 
					fromStepCode, fromStepName, fromSequenceNo,
					actionCode, actionUser, processAction.getActionName(), actionNote, actionOverdue,
					processAction.getSyncActionCode(), hasDossierSync, processAction.getRollbackable(),
					curStep.getStepCode(), curStep.getStepName(), 
					curStep.getSequenceNo(),
					null, 0l, payload, curStep.getStepInstruction(),
					state, eventStatus,
					context);

			if (actionConfig != null) {
				if (actionConfig.getRollbackable()) {
					DossierActionLocalServiceUtil.updateRollbackable(dossierAction.getDossierActionId(), true);
				}
			}
			else {
				if (processAction.isRollbackable()) {
					DossierActionLocalServiceUtil.updateRollbackable(dossierAction.getDossierActionId(), true);
				}
			}

			// Add DossierActionUser

			DossierActionUserImpl dossierActionUser = new DossierActionUserImpl();

			if (Validator.isNotNull(subUsers)) {
				_log.info("PROCESS subUsers != null");
				JSONArray subUsersArray = JSONFactoryUtil.createJSONArray(subUsers);
				dossierActionUser.assignDossierActionUser(dossier, processAction.getAllowAssignUser(), dossierAction, userId, groupId,
						assignUserId, subUsersArray, 0);
			} else {
				_log.info("PROCESS subUsers == null");
				dossierActionUser.initDossierActionUser(processAction, dossier, processAction.getAllowAssignUser(), dossierAction, userId, groupId,
						assignUserId);
			}
			//_log.info("UPDATE DOSSIER STATUS************");
			//_log.info(curStep.getDossierStatus());
			//_log.info(curStep.getDossierSubStatus());
			//_log.info("*********************************");
			// Set dossierStatus by CUR_STEP
			// LamTV: Update lockState when Sync
			dossier = DossierLocalServiceUtil.updateStatus(groupId, dossierId, referenceUid, curStep.getDossierStatus(),
					jsStatus.getString(curStep.getDossierStatus()), curStep.getDossierSubStatus(),
					jsSubStatus.getString(curStep.getDossierSubStatus()), curStep.getLockState(),
					dossierAction.getStepInstruction(), context);
			
			//_log.info(jsStatus.toJSONString());
			//_log.info(jsSubStatus.toJSONString());

			//_log.info("dossier_" + dossier.getDossierStatus());

			//_log.info("*********************************");

			if (Validator.isNull(dossier.getDossierNo())
					&& (curStep.getDossierStatus().contentEquals(DossierStatusConstants.PAYING)
							|| (curStep.getDossierStatus().contentEquals(DossierStatusConstants.PROCESSING)))) {

				_log.info("PROCESS getDossierStatus == PAYING or PROCESSING");
				LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
				params.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
				params.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
				params.put(DossierTerm.DOSSIER_TEMPLATE_NO, dossier.getDossierTemplateNo());
				params.put(DossierTerm.DOSSIER_STATUS, StringPool.BLANK);

				String dossierRef = DossierNumberGenerator.generateDossierNumber(groupId, dossier.getCompanyId(),
						dossierId, option.getProcessOptionId(), serviceProcess.getDossierNoPattern(), params);

				// Cap nhat ngay tiep nhan khi duoc cap so

				dossier.setReceiveDate(new Date());

				dossier.setDossierNo(dossierRef.trim());
				// To index
				DossierLocalServiceUtil.syncDossier(dossier);
			}

			// update nextActionId
			_log.info("prvAction:" + prvAction);
			if (Validator.isNotNull(prvAction)) {
				DossierActionLocalServiceUtil.updateNextActionId(prvAction.getDossierActionId(),
						dossierAction.getDossierActionId());
			}

			_log.info("hasDossierSync:" + hasDossierSync);
			if (hasDossierSync) {
				// SyncAction
//				int method = 0;
				_log.info("PROCESS update Dossier Sync:" + hasDossierSync);
//				DossierSyncLocalServiceUtil.updateDossierSync(groupId, userId, dossierId, dossier.getReferenceUid(),
//						isCreateDossier, method, dossierAction.getPrimaryKey(), StringPool.BLANK,
//						serviceProcess.getServerNo());

				// TODO add SYNC for DossierFile and PaymentFile here

				//TODO: process SyncDossierFile
				processSyncDossierFile(dossierId, dossier.getReferenceUid(), processAction, groupId, userId, serviceProcess.getServerNo());
				// SyncDossierFile
				//List<DossierFile> lsDossierFile = DossierFileLocalServiceUtil.getByDossierIdAndIsNew(dossierId, true);

				// check return file
				//List<String> returnDossierFileTemplateNos = ListUtil
				//		.toList(StringUtil.split(processAction.getReturnDossierFiles()));

				//_log.info("__return dossierFiles" + processAction.getReturnDossierFiles());

				//for (DossierFile dosserFile : lsDossierFile) {

				//	_log.info("&&&StartUpdateDossierFile" + new Date());

				//	dosserFile.setIsNew(false);

				//	DossierFileLocalServiceUtil.updateDossierFile(dosserFile);

				//	_log.info("&&&EndUpdateDossierFile" + new Date());

				//	_log.info("__dossierPart" + processAction.getReturnDossierFiles());
				//	_log.info("__dossierPart" + dosserFile.getFileTemplateNo());

				//	if (returnDossierFileTemplateNos.contains(dosserFile.getFileTemplateNo())) {
				//		_log.info("START SYNC DOSSIER FILE");
				//		DossierSyncLocalServiceUtil.updateDossierSync(groupId, userId, dossierId,
				//				dossier.getReferenceUid(), false, 1, dosserFile.getDossierFileId(),
				//				dosserFile.getReferenceUid(), serviceProcess.getServerNo());

				//	}

				//}

			}

			String preCondition = processAction.getPreCondition();
			String autoEvent = processAction.getAutoEvent();
			_log.info("preCondition: "+preCondition);

			//LamTV: Process case auto Event
			boolean flagEvent = false;
			if (Validator.isNotNull(autoEvent) && autoEvent.toLowerCase().contentEquals("timmer")) {
				flagEvent = true;
			}

			if (Validator.isNotNull(preCondition)) {
				// case reject_cancelling
				_log.info("REJECT_CANCELLING....");

				if (preCondition.toLowerCase().contentEquals("reject_cancelling")) {
					// flag-off
					_log.info("DO REJECT_CANCELLING....");

					Dossier sourceDossier = DossierLocalServiceUtil.getByRef(55217, dossier.getReferenceUid());
					_log.info("DO REJECT_CANCELLING.... FIND RESOURCE");

					sourceDossier.setCancellingDate(null);

					DossierLocalServiceUtil.updateDossier(sourceDossier);

					dossier.setCancellingDate(null);

					// To index
					DossierLocalServiceUtil.syncDossier(dossier);

					// add dossierLog

					// in CLIENT

					String refUid = PortalUUIDUtil.generate();
					int status = 2;

					DossierRequestUDLocalServiceUtil.updateDossierRequest(0, dossierId, refUid, "reject_cancelling",
							actionNote, 0, status, context);

					// in SERVER

					context.setScopeGroupId(sourceDossier.getGroupId());
					DossierRequestUDLocalServiceUtil.updateDossierRequest(0, sourceDossier.getDossierId(), refUid, "reject_cancelling",
							actionNote, 0, status, context);
					
					context.setScopeGroupId(dossier.getGroupId());
				}

				//LamTV: Update status when approved canceling
				if (preCondition.toLowerCase().contentEquals("cancelling")) {
					// flag-off
					_log.info("START CANCELLING....");

					// in CLIENT

					String refUid = PortalUUIDUtil.generate();
					int status = 1;

					DossierRequestUDLocalServiceUtil.updateDossierRequest(0, dossierId, refUid, "cancelling",
							actionNote, 0, status, context);

					// in SERVER

					Dossier sourceDossier = DossierLocalServiceUtil.getByRef(55217, dossier.getReferenceUid());
					if (sourceDossier != null) {
						context.setScopeGroupId(sourceDossier.getGroupId());
						DossierRequestUDLocalServiceUtil.updateDossierRequest(0, sourceDossier.getDossierId(), refUid, "cancelling",
								actionNote, 0, status, context);
					}

					context.setScopeGroupId(dossier.getGroupId());

				}

				_log.info("REJECT_SUBMIT....");
				if (preCondition.toLowerCase().contentEquals("reject_submitting")) {
					// flag-off
					_log.info("DO REJECT_SUBMIT....");

					Dossier sourceDossier = DossierLocalServiceUtil.getByRef(55217, dossier.getReferenceUid());
					_log.info("DO REJECT_SUBMIT.... FIND RESOURCE");

					sourceDossier.setEndorsementDate(null);

					DossierLocalServiceUtil.updateDossier(sourceDossier);

					dossier.setEndorsementDate(null);

					// To index
					DossierLocalServiceUtil.syncDossier(dossier);

					String refUid = PortalUUIDUtil.generate();
					int status = 2;

					DossierRequestUDLocalServiceUtil.updateDossierRequest(0, dossierId, refUid, "reject_submitting",
							actionNote, 0, status, context);

					// in SERVER

					context.setScopeGroupId(sourceDossier.getGroupId());
					DossierRequestUDLocalServiceUtil.updateDossierRequest(0, sourceDossier.getDossierId(), refUid, "reject_submitting",
							actionNote, 0, status, context);
					
					context.setScopeGroupId(dossier.getGroupId());

				}

				//LamTV: Update process approved endorsement
				if (preCondition.toLowerCase().contentEquals("submitting")) {
					if (flagEvent) {
						// flag-off
						_log.info("START APPROVED SUBMIT....");

						String refUid = PortalUUIDUtil.generate();
						int status = 3;

						DossierRequestUDLocalServiceUtil.updateDossierRequest(0, dossierId, refUid, "submitting",
								actionNote, 0, status, context);

						// in SERVER

						Dossier sourceDossier = DossierLocalServiceUtil.getByRef(55217, dossier.getReferenceUid());
						if (sourceDossier != null) {
							context.setScopeGroupId(sourceDossier.getGroupId());
							DossierRequestUDLocalServiceUtil.updateDossierRequest(0, sourceDossier.getDossierId(), refUid,
									"submitting", actionNote, 0, status, context);
						}

						context.setScopeGroupId(dossier.getGroupId());
					} else {
						// flag-off
						_log.info("START APPROVED SUBMIT....");

						String refUid = PortalUUIDUtil.generate();
						int status = 1;

						DossierRequestUDLocalServiceUtil.updateDossierRequest(0, dossierId, refUid, "submitting",
								actionNote, 0, status, context);

						// in SERVER

						Dossier sourceDossier = DossierLocalServiceUtil.getByRef(55217, dossier.getReferenceUid());
						if (sourceDossier != null) {
							context.setScopeGroupId(sourceDossier.getGroupId());
							DossierRequestUDLocalServiceUtil.updateDossierRequest(0, sourceDossier.getDossierId(), refUid,
									"submitting", actionNote, 0, status, context);
						}

						context.setScopeGroupId(dossier.getGroupId());
					}

				}

				_log.info("REJECT_CORRECTING....");
				if (preCondition.toLowerCase().contentEquals("reject_correcting")) {
					// flag-off
					_log.info("DO REJECT_CORRECTING....");

					Dossier sourceDossier = DossierLocalServiceUtil.getByRef(55217, dossier.getReferenceUid());
					_log.info("DO REJECT_CORRECTING.... FIND RESOURCE");

					sourceDossier.setCorrecttingDate(null);

					DossierLocalServiceUtil.updateDossier(sourceDossier);

					dossier.setCorrecttingDate(null);

					// To index
					DossierLocalServiceUtil.syncDossier(dossier);

					String refUid = PortalUUIDUtil.generate();
					int status = 2;

					DossierRequestUDLocalServiceUtil.updateDossierRequest(0, dossierId, refUid, "reject_correcting",
							actionNote, 0, status, context);

					// in SERVER

					context.setScopeGroupId(sourceDossier.getGroupId());
					DossierRequestUDLocalServiceUtil.updateDossierRequest(0, sourceDossier.getDossierId(), refUid, "reject_correcting",
							actionNote, 0, status, context);
					
					context.setScopeGroupId(dossier.getGroupId());

				}

				//LamTV: Update process approved correcting
				if (preCondition.toLowerCase().contentEquals("correcting")) {
					if (flagEvent) {
						// flag-off
						_log.info("START APPROVED CORRECTING....");

						String refUid = PortalUUIDUtil.generate();
						int status = 3;

						// IN CLIENT
						DossierRequestUDLocalServiceUtil.updateDossierRequest(0, dossierId, refUid, "correcting",
								actionNote, 0, status, context);

						// IN SERVER
						Dossier sourceDossier = DossierLocalServiceUtil.getByRef(55217, dossier.getReferenceUid());
						if (sourceDossier != null) {
							context.setScopeGroupId(sourceDossier.getGroupId());
							DossierRequestUDLocalServiceUtil.updateDossierRequest(0, sourceDossier.getDossierId(), refUid,
									"correcting", actionNote, 0, status, context);
						}
						context.setScopeGroupId(dossier.getGroupId());
					}else {
						// flag-off
						_log.info("START APPROVED CORRECTING....");

						String refUid = PortalUUIDUtil.generate();
						int status = 1;

						// IN CLIENT
						DossierRequestUDLocalServiceUtil.updateDossierRequest(0, dossierId, refUid, "correcting",
								actionNote, 0, status, context);

						// IN SERVER
						Dossier sourceDossier = DossierLocalServiceUtil.getByRef(55217, dossier.getReferenceUid());
						if (sourceDossier != null) {
							context.setScopeGroupId(sourceDossier.getGroupId());
							DossierRequestUDLocalServiceUtil.updateDossierRequest(0, sourceDossier.getDossierId(), refUid,
									"correcting", actionNote, 0, status, context);
						}
						context.setScopeGroupId(dossier.getGroupId());
					}
				}

			}

			// Add PaymentSync


		}

		ProcessStep postStep = ProcessStepLocalServiceUtil.fetchBySC_GID(postStepCode, groupId, serviceProcessId);

		String dossierBriefNote = DossierContentGenerator.getBriefNote(groupId, dossierId, postStep != null ? postStep.getBriefNote() : StringPool.BLANK);

		if (Validator.isNotNull(dossierBriefNote)) {
			DossierLocalServiceUtil.updateDossierBriefNote(dossierId, dossierBriefNote);
		}

		// do plugin auto

		// 1. get current Step
		// 2. get all plugins of this step
		// 3. get plugin has autoRun
		// 4. Create update formData

//		_log.info("IN_CURRENT_STEP:" + curStep.getStepCode() + curStep.getStepName());

		List<ProcessPlugin> plugins = ProcessPluginLocalServiceUtil.getProcessPlugins(serviceProcessId,
				curStep != null ? curStep.getStepCode() : StringPool.BLANK);

//		_log.info("WE_HAVE_PLUGINS:" + plugins.size());

		List<ProcessPlugin> autoPlugins = new ArrayList<ProcessPlugin>();

		for (ProcessPlugin plg : plugins) {
			if (plg.getAutoRun()) {
				autoPlugins.add(plg);
			}
		}

		_log.info("AND_HAVE_AUTO_RUN_PLUGINS:" + autoPlugins.size());

		for (ProcessPlugin plg : autoPlugins) {
			// do create file
			String fileTemplateNo = plg.getSampleData();

			fileTemplateNo = StringUtil.replaceFirst(fileTemplateNo, "#", StringPool.BLANK);

			_doAutoRun(groupId, fileTemplateNo, dossierId, dossier.getDossierTemplateNo(), context);
		}

		_log.info("END DO ACTION ==========");
		return dossierAction;
	}

	private void _doAutoRun(long groupId, String fileTemplateNo, long dossierId, String dossierTemplateNo,
			ServiceContext context) {

		String formData = StringPool.BLANK;

		fileTemplateNo = StringUtil.replaceFirst(fileTemplateNo, "#", StringPool.BLANK);

		try {
			// Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);

			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(dossierId,
					fileTemplateNo, false, new DossierFileComparator(false, "createDate", Date.class));

			DossierPart dossierPart = DossierPartLocalServiceUtil.getByFileTemplateNo(groupId, fileTemplateNo);

			formData = AutoFillFormData.sampleDataBinding(dossierPart.getSampleData(), dossierId, context);

			if (Validator.isNull(dossierFile)) {

				DossierFileActions actions = new DossierFileActionsImpl();

				dossierFile = actions.addDossierFile(groupId, dossierId, PortalUUIDUtil.generate(), dossierTemplateNo,
						dossierPart.getPartNo(), fileTemplateNo, dossierPart.getPartName(), StringPool.BLANK, 0L, null,
						StringPool.BLANK, String.valueOf(false), context);
				dossierFile.setEForm(dossierPart.getEForm());
				dossierFile.setFormScript(dossierPart.getFormScript());
				dossierFile = DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
			}

			DossierFileActions actions = new DossierFileActionsImpl();

			actions.updateDossierFileFormData(groupId, dossierId, dossierFile.getReferenceUid(), formData, context);

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			_log.info("Cant get formdata with fileTemplateNo_" + fileTemplateNo);
		}

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

	//LamTV_Process get dossierStatus and dossierSubStatus text
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

		// ProcessAction action = getProcessAction(groupId, dossierId, refId,
		// actionCode, serviceProcessId);

		// String postStepCode = action.getPostStepCode();

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

	@Override
	public Dossier initDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String password,
			int viaPostal, String postalAddress, String postalCityCode, String postalCityName, String postalTelNo,
			boolean online, boolean notification, String applicantNote, int originality, ServiceContext context) throws PortalException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date appIdDate = null;

		try {
			appIdDate = sdf.parse(applicantIdDate);

		} catch (Exception e) {
			// TODO: handle exception
			_log.debug(e);
			//_log.error(e);
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


				List<DictItem> dictItems = DictItemLocalServiceUtil
						.findByF_dictCollectionId(dictCollection.getDictCollectionId());

				for (DictItem dictItem : dictItems) {

//					statusCode = StringPool.BLANK;
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

					statistic.put("dossierStatus", statusCode);
					statistic.put("dossierSubStatus", subStatusCode);
					statistic.put("level", dictItem.getLevel());
					statistic.put("statusName", dictItem.getItemName());
					statistic.put("count", count);
					if (dictItem.getParentItemId() == 0) {
						total += count;
					}

					statistics.put(statistic);
				}
			}

			result.put("data", statistics);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

private String _buildDossierNote(Dossier dossier, String actionNote, long groupId, String type) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String defaultTimezone = TimeZone.getDefault().getID();
		sdf.setTimeZone(TimeZone.getTimeZone(defaultTimezone));
		Date date = new Date();

		StringBuilder sb = new StringBuilder();

		String oldNote = dossier.getApplicantNote();
		_log.info("oldNote: "+oldNote);
		_log.info("actionNote: "+actionNote);

		if (Validator.isNotNull(oldNote) && oldNote.contains("<br>")) {
			if (Validator.isNotNull(actionNote)) {
				if (groupId != 55217) {
					sb.append("<br>");
					sb.append("[" + sdf.format(date) + "]");
					sb.append(": ");
					sb.append(actionNote);
					sb.append(oldNote);
				} else {
					sb.append("<br>");
					sb.append("[" + sdf.format(date) + "]");
					sb.append(": ");
					sb.append(actionNote);
				}
			} else {
				if (groupId != 55217) {
					sb.append(oldNote);
				}
			}
		} else if (Validator.isNotNull(actionNote)) {
			sb.append("<br>");
			sb.append("[" + sdf.format(date) + "]");
			sb.append(": ");
			sb.append(actionNote);
		}

		return sb.toString();

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
					// TODO: handle exception
					_log.info(e);
				}
			}
		}
		// List<User> users = new ArrayList<>();

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
				List<DictItem> dictItems = DictItemLocalServiceUtil
						.findByF_dictCollectionId(dictCollection.getDictCollectionId());

				// Get list dossierActionId


				for (DictItem dictItem : dictItems) {
					String metaData = dictItem.getMetaData();
					String specialStatus = StringPool.BLANK;
					if (Validator.isNotNull(metaData)) {
						// _log.info("metaData: " +metaData);
						try {
							JSONObject metaJson = JSONFactoryUtil.createJSONObject(metaData);
							specialStatus = metaJson.getString("specialStatus");
							// _log.info("specialStatus: " +specialStatus);

						} catch (Exception e) {
							// TODO: handle exception
							_log.info(e);
						}
					}

//					statusCode = StringPool.BLANK;
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
						// _log.info("isPermission: " +isPermission);
						// _log.info("userId: " +userId);
						// _log.info("strdossierActionId: " +sb.toString());

						JSONObject statistic = JSONFactoryUtil.createJSONObject();

						if (Validator.isNotNull(specialStatus) && Boolean.parseBoolean(specialStatus)) {
							// Add params
							params.put(DossierTerm.STATUS, statusCode);
							params.put(DossierTerm.SUBSTATUS, subStatusCode);
//							params.put(DossierTerm.DOSSIER_ACTION_ID, sb.toString());
							params.put(DossierTerm.FOLLOW, String.valueOf(true));

							long count = DossierLocalServiceUtil.countLucene(params, searchContext);
							// _log.info("count: " + count);

							statistic.put("dossierStatus", statusCode);
							statistic.put("dossierSubStatus", subStatusCode);
							statistic.put("level", dictItem.getLevel());
							statistic.put("statusName", dictItem.getItemName());
							statistic.put("count", count);
							if (dictItem.getParentItemId() != 0) {
								total += count;
							}
							statistics.put(statistic);
						} else {
							params.put(DossierTerm.STATUS, statusCode);
							params.put(DossierTerm.SUBSTATUS, subStatusCode);
							params.put(DossierTerm.FOLLOW, String.valueOf(false));

							long count = DossierLocalServiceUtil.countLucene(params, searchContext);

							statistic.put("dossierStatus", statusCode);
							statistic.put("dossierSubStatus", subStatusCode);
							statistic.put("level", dictItem.getLevel());
							statistic.put("statusName", dictItem.getItemName());
							statistic.put("count", count);
							if (dictItem.getParentItemId() != 0) {
								total += count;
							}
							statistics.put(statistic);
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
							if (!"done".equals(strStatus) && !"cancelled".equals(strStatus)) {
								params.put(DossierTerm.NOT_STATE, "cancelling");
							}
							if("cancelled".equals(strStatus)) {
								params.put(DossierTerm.NOT_STATE, StringPool.BLANK);
								params.put(DossierTerm.NOT_STATUS_REG, null);
							}

							long count = DossierLocalServiceUtil.countLucene(params, searchContext);

							JSONObject statistic = JSONFactoryUtil.createJSONObject();
							statistic.put("dossierStatus", strStatus);
							statistic.put("dossierSubStatus", subStatusCode);
							statistic.put("count", count);

							statistics.put(statistic);

							total += count;
						}
					}
				}
			}

			result.put("data", statistics);
//			_log.info("statistics: "+statistics);

			result.put("total", total);
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

	private void processSyncDossierFile(long dossierId, String referenceUid, ProcessAction processAction, long groupId,
			long userId, String serverNo) {

		// check return file
		List<String> returnDossierFileTemplateNos = ListUtil
				.toList(StringUtil.split(processAction.getReturnDossierFiles()));
		_log.info("__return dossierFiles" + processAction.getReturnDossierFiles());
		_log.info("__return dossierId" + dossierId);

		List<DossierFile> dossierFileList = null;
		if (returnDossierFileTemplateNos != null && returnDossierFileTemplateNos.size() > 0) {
			dossierFileList = DossierFileLocalServiceUtil.getDossierFilesByD_DP(dossierId, 2);
		} else {
			dossierFileList = DossierFileLocalServiceUtil.getByDossierIdAndIsNew(dossierId, true);
		}

		// Update and sync dossierFile
		if (returnDossierFileTemplateNos != null && returnDossierFileTemplateNos.size() > 0) {
			_log.info("__return dossierFiles" + processAction.getReturnDossierFiles());
			if (dossierFileList != null && dossierFileList.size() > 0) {
				_log.info("__return size" + dossierFileList.size());
				String fileTemplateNo;
				for (DossierFile dossierFile : dossierFileList) {

					// Update record dossierFile
					if (dossierFile.getIsNew()) {
						updateIsNewDossierFile(dossierFile);
					}

					_log.info("__dossierPart" + processAction.getReturnDossierFiles());
					_log.info("__dossierPart" + dossierFile.getFileTemplateNo());
					fileTemplateNo = dossierFile.getFileTemplateNo();

					if (returnDossierFileTemplateNos.contains(fileTemplateNo)) {
						_log.info("START SYNC DOSSIER FILE");
//						DossierSyncLocalServiceUtil.updateDossierSync(groupId, userId, dossierId, referenceUid, false,
//								1, dossierFile.getDossierFileId(), dossierFile.getReferenceUid(), serverNo);

					}
				}
			}
		} else {// only update dossierFile
			if (dossierFileList != null && dossierFileList.size() > 0) {
				for (DossierFile dossierFile : dossierFileList) {
					updateIsNewDossierFile(dossierFile);
				}
			}
		}
	}

	//Update DossierFile when Sync
	private void updateIsNewDossierFile(DossierFile dossierFile) {
		_log.info("&&&StartUpdateDossierFile" + new Date());
		dossierFile.setIsNew(false);
		DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
		_log.info("&&&EndUpdateDossierFile" + new Date());
	}

	//LamTV_Process check permission action
	private boolean processCheckEnable(String preCondition, String autoEvent, Dossier dossier, String actionCode,
			long groupId) {
		if (AUTO_EVENT_SUBMIT.equals(autoEvent) || AUTO_EVENT_TIMMER.equals(autoEvent)
				|| AUTO_EVENT_LISTENER.equals(autoEvent) || AUTO_EVENT_SPECIAL.equals(autoEvent)) {
			return false;
		}
		String[] preConditionArr = StringUtil.split(preCondition);
//		_log.info("SONDT processCheckEnable PRECONDISTIONARR ========= " + JSONFactoryUtil.looseSerialize(preConditionArr));
//		_log.info("SONDT processCheckEnable dossier ========= " + JSONFactoryUtil.looseSerialize(dossier));
		if (preConditionArr != null && preConditionArr.length > 0) {
			return DossierMgtUtils.checkPreCondition(preConditionArr, dossier);
		}

//		int originality = dossier.getOriginality();
//		ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
//		if (actConfig != null) {
//			int syncType = actConfig.getSyncType();
//			if (originality == 1 && syncType == 2) {
//				result = true;
//				return result;
//			} else if (syncType == 1) {
//				if (originality == 2 || originality == 3) {
//					result = true;
//					return result;
//				}
//			}
//		}

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
								moderator.put("moderator", serviceProcessRole.getModerator());
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

//		List<ProcessStepRole> processStepRoleList = ProcessStepRoleLocalServiceUtil
//				.findByP_S_ID(processStep.getProcessStepId());
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
//		List<ProcessStepRole> processStepRoleList = ProcessStepRoleLocalServiceUtil
//				.findByP_S_ID(processStep.getProcessStepId());
		
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

//	@Override
//	public Dossier publishDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
//			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
//			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
//			String cityName, String districtCode, String districtName, String wardCode, String wardName,
//			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String password,
//			int viaPostal, String postalAddress, String postalCityCode, String postalCityName, String postalTelNo,
//			boolean online, boolean notification, String applicantNote, int originality, 
//			Date createDate, Date modifiedDate, Date submitDate, Date receiveDate, Date dueDate,
//			Date releaseDate, Date finishDate, Date cancellingDate, Date correctingDate, 
//			Date endorsementDate, Date extendDate,
//			Date processDate, ServiceContext context)
//			throws PortalException {
//
////		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//		Date appIdDate = null;
////		try {
////			appIdDate = sdf.parse(applicantIdDate);
////		} catch (Exception e) {
////			// TODO: handle exception
////			_log.debug(e);
////			//_log.error(e);
////		}
//
//		Dossier dossier = null;
//
//		try {
//
//			//Process
//			dossier = DossierLocalServiceUtil.publishDossier(groupId, dossierId, referenceUid, counter, serviceCode,
//					serviceName, govAgencyCode, govAgencyName, applicantName, applicantIdType, applicantIdNo, appIdDate,
//					address, cityCode, cityName, districtCode, districtName, wardCode, wardName, contactName,
//					contactTelNo, contactEmail, dossierTemplateNo, password, viaPostal, postalAddress, postalCityCode,
//					postalCityName, postalTelNo, online, notification, applicantNote, originality, createDate, modifiedDate, submitDate, receiveDate, dueDate,
//					releaseDate, finishDate, cancellingDate, correctingDate, 
//					endorsementDate, extendDate,
//					processDate, context);
//
//		} catch (Exception e) {
//			_log.debug(e);
//			//_log.error(e);
//		}
//
//		return dossier;
//	}

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
		//_log.info("SONDT checkPaymentMethodinPrecondition preCondition ===== " + preCondition);
		int paymentMethod = 0;
		String[] preConditions = StringUtil.split(preCondition);
		for(String pre : preConditions) {
			pre = pre.trim();
			//_log.info("SONDT checkPaymentMethodinPrecondition pre ===== " + pre);
			if (pre.toLowerCase().contains("paymentmethod=")) {
				String[] splitPaymentMethod = pre.split("=");
				//_log.info("SONDT checkPaymentMethodinPrecondition splitPaymentMethod ===== " + splitPaymentMethod);
				if (splitPaymentMethod.length == 2) {
					paymentMethod = Integer.parseInt(splitPaymentMethod[1]);
					//_log.info("SONDT checkPaymentMethodinPrecondition paymentMethod in if ===== " + paymentMethod);
				}
				break;
			}
		}
		_log.info("SONDT checkPaymentMethodinPrecondition paymentMethod ===== " + paymentMethod);
		return paymentMethod;
	}

	private String checkPaymentMethod(int mt) {
		String pmMethod = "";
		if (mt == 1) {
			pmMethod = "Chuyển khoản";//KeyPay
		} else if (mt == 2) {
			pmMethod = "Chuyển khoản";
		} else if (mt == 3) {
			pmMethod = "Tiền mặt";
		}

		_log.info("SONDT checkPaymentMethod pmMethod ===== " + pmMethod);
		return pmMethod;
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
					if (stepCode.startsWith("!")) {
						int index = stepCode.indexOf("!");
						String stepCodePunc = stepCode.substring(index + 1);
						lstUser.addAll(processRoleAsStepDonedListUser(dossier, stepCodePunc, ps.getServiceProcessId(), ps, processStepRoleList));
					}
					else {
						lstUser.addAll(processRoleAsStepListUser(dossier, stepCode, ps.getServiceProcessId(), ps, processStepRoleList));								
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date appIdDate = null;

		try {
			appIdDate = sdf.parse(applicantIdDate);

		} catch (Exception e) {
			// TODO: handle exception
			_log.debug(e);
			//_log.error(e);
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
			if (dossier.getReleaseDate() == null) {
				dossier.setReleaseDate(now);
			}
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
			//_log.error(e);
		}

		return dossier;
	}

	@Override
	public Dossier publishImportDossier(long groupId, long dossierId, String referenceUid, int counter,
			String serviceCode, String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantType, String applicantIdNo, Date applicantIdDate, String address, String contactName,
			String contactTelNo, String contactEmail, String dossierNo, String dossierStatus, String dossierStatusText,
			Boolean online, int originality, int sampleCount, Double durationCount, Integer durationUnit,
			Date createDate, Date modifiedDate, Date submitDate, Date receiveDate, Date dueDate, Date releaseDate,
			Date finishDate, String dossierTemplateNo, String dossierTemplateName, ServiceContext serviceContext) {

		Dossier dossier = null;
		try {
			ProcessOption option = null;
			if (Validator.isNotNull(serviceCode) && Validator.isNotNull(govAgencyCode)) {
				ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceCode,
						govAgencyCode);
				if (config != null) {
					List<ProcessOption> optionList = ProcessOptionLocalServiceUtil
							.getByServiceProcessId(config.getServiceConfigId());
					if (optionList != null && optionList.size() > 0) {
						option = optionList.get(0);
						if (Validator.isNull(dossierTemplateNo) && Validator.isNull(dossierTemplateName)) {
							DossierTemplate template = DossierTemplateLocalServiceUtil
									.fetchDossierTemplate(option.getDossierTemplateId());
							if (template != null) {
								dossierTemplateNo = template.getTemplateNo();
								dossierTemplateName = template.getTemplateName();
							}
						}
					}
				}
			}
			// if (Validator.isNull(dossierTemplateNo) &&
			// Validator.isNull(dossierTemplateName)) {
			// dossierTemplateNo = "MAU_SVHTTDL_TP99";
			// dossierTemplateName = "Mẫu giấy phép khác";
			// }
			// Create DossierAction
			DossierAction dossierAction = null;
			if (dossierId == 0) {
				String fromStepCode = "300";
				String fromStepName = "Trả kết quả";
				String fromSequenceNo = "04";
				String actionCode = "4000";
				String actionUser = "Tiếp nhận";
				String actionName = "Trả hoàn thiện";
				String stepCode = "400";
				String stepName = "Hoàn thành";
				dossierAction = DossierActionLocalServiceUtil.updateImportDossierAction(groupId, 0l,
						option != null ? option.getServiceProcessId() : 0, fromStepCode, fromStepName, fromSequenceNo,
						actionCode, actionUser, actionName, stepCode, stepName, null, 0l, 1, serviceContext);
			} else {
				List<DossierAction> dossierActionList = DossierActionLocalServiceUtil.getDossierActionById(dossierId);
				if (dossierActionList != null && dossierActionList.size() > 0) {
					dossierAction = dossierActionList.get(0);
				} else {
					String fromStepCode = "300";
					String fromStepName = "Trả kết quả";
					String fromSequenceNo = "04";
					String actionCode = "4000";
					String actionUser = "Tiếp nhận";
					String actionName = "Trả hoàn thiện";
					String stepCode = "400";
					String stepName = "Hoàn thành";
					dossierAction = DossierActionLocalServiceUtil.updateImportDossierAction(groupId, 0l,
							option != null ? option.getServiceProcessId() : 0, fromStepCode, fromStepName,
							fromSequenceNo, actionCode, actionUser, actionName, stepCode, stepName, null, 0l, 1,
							serviceContext);
				}
			}

			//
			dossier = DossierLocalServiceUtil.publishImportDossier(groupId, dossierId, referenceUid, counter,
					serviceCode, serviceName, govAgencyCode, govAgencyName, applicantName, applicantType, applicantIdNo,
					applicantIdDate, address, contactName, contactTelNo, contactEmail, online, originality, dossierNo,
					dossierStatus, dossierStatusText, dossierAction != null ? dossierAction.getDossierActionId() : 0,
					durationCount, durationUnit, sampleCount, createDate, modifiedDate, submitDate, receiveDate,
					dueDate, releaseDate, finishDate, dossierTemplateNo, dossierTemplateName, serviceContext);
			// Update dossierAction
			if (dossierAction != null) {
				dossierAction.setDossierId(dossier.getDossierId());
				DossierActionLocalServiceUtil.updateDossierAction(dossierAction);
			}

		} catch (PortalException e) {
			_log.error(e);
		}

		return dossier;
	}
		
}
