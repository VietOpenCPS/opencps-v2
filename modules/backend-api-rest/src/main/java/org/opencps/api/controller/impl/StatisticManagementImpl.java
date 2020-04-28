package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.constants.StatisticManagementConstants;
import org.opencps.api.constants.SystemManagementConstants;
import org.opencps.api.controller.StatisticManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.controller.util.StatisticUtils;
import org.opencps.api.statistic.model.StatisticCountResultModel;
import org.opencps.api.statistic.model.StatisticDossierResults;
import org.opencps.api.statistic.model.StatisticDossierSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.StatisticActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.impl.StatisticActionsImpl;
import org.opencps.dossiermgt.constants.DossierActionUserTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.StepConfigTerm;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.model.MenuRole;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.MenuRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class StatisticManagementImpl implements StatisticManagement {

	private static final Log _log = LogFactoryUtil.getLog(StatisticManagementImpl.class);

	@Override
	public Response getDossierTodo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, StatisticDossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierTerm.STATUS, query.getDossierStatus());
			params.put(DossierTerm.SUBSTATUS, query.getDossierSubStatus());
			params.put(Field.USER_ID, String.valueOf(user.getUserId()));
			params.put(DossierTerm.FOLLOW, String.valueOf(false));

			JSONObject jsonData = actions.getDossierTodoPermission(user.getUserId(), company.getCompanyId(), groupId, params,
					null, serviceContext);

			StatisticDossierResults results = new StatisticDossierResults();

			results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));

			results.getStatisticDossierModel()
					.addAll(StatisticUtils.mapperStatisticDossierList(jsonData.getJSONArray(ConstantUtils.DATA)));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossierCountTodo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, StatisticDossierSearchModel query) {

		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			long userId = user.getUserId();

			// Get info input
			long notStatusReg = query.getNotStatusReg();
			String status = query.getDossierStatus();
			String substatus = query.getDossierSubStatus();
			

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierTerm.STATUS, status);
			params.put(DossierTerm.SUBSTATUS, substatus);
			params.put(Field.USER_ID, String.valueOf(userId));
			params.put(DossierTerm.OWNER, String.valueOf(true));
			params.put(DossierTerm.NOT_STATUS_REG, notStatusReg);

			JSONObject jsonData = actions.getDossierCountTodoPermission(user.getUserId(), company.getCompanyId(), groupId, params,
					null, serviceContext);

			StatisticDossierResults results = new StatisticDossierResults();

			results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));

			results.getStatisticDossierModel()
					.addAll(StatisticUtils.mapperStatisticDossierList(jsonData.getJSONArray(ConstantUtils.DATA)));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private static final String STEP_EQUAL = "step=";
	
	@Override
	public Response getDossierTodoTest(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, StatisticDossierSearchModel query, String owner) {
		BackendAuth auth = new BackendAuthImpl();
		StatisticActions actions = new StatisticActionsImpl();
		//backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			long userId = user.getUserId();
//			int stepType = 0;

//			_log.info("START");
			// Get info input
			JSONArray statistics = JSONFactoryUtil.createJSONArray();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			boolean isAdmin = false;
			List<Role> roles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
			long[] menuConfigArr = null;
			if (roles != null && roles.size() > 0) {
				int length = roles.size();
				long[] roleIds = new long[length];
				for (Role role : roles) {
					// LamTV_Fix sonarqube
					if (ConstantUtils.ROLE_ADMIN.equals(role.getName())) {
						isAdmin = true;
						break;
					}
					if (ConstantUtils.ROLE_ADMIN_DATA.equals(role.getName())) {
						isAdmin = true;
						break;
					}
				}
				//
				for (int i = 0; i < length; i++) {
					roleIds[i] = roles.get(i).getRoleId();
				}
				
				List<MenuRole> meunuRoleList = MenuRoleLocalServiceUtil.getByRoles(roleIds);
//				Map<Long , Long> mapMenuRole = new HashMap<>();
				List<Long> menuConfigList = new ArrayList<>();
				for (MenuRole menuRole : meunuRoleList) {
					if (menuConfigList.size() > 0 && menuConfigList.contains(menuRole.getMenuConfigId())) {
						continue;
					} else {
						menuConfigList.add(menuRole.getMenuConfigId());
					}
				}
				//
				menuConfigArr = new long[menuConfigList.size()];
				for (int i = 0; i < menuConfigList.size(); i++) {
					menuConfigArr[i] = menuConfigList.get(i);
				}
				//_log.info("menuConfigList: "+JSONFactoryUtil.looseSerialize(menuConfigList));
			}
			//Applicant
			Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(user.getUserId());
			if (applicant != null) {
				JobPos job = JobPosLocalServiceUtil.getByJobCode(groupId, SystemManagementConstants.APPLICANT);
				if (job != null) {
					List<MenuRole> lstMenuRoles = MenuRoleLocalServiceUtil.getByRoleId(job.getMappingRoleId());
					if (lstMenuRoles != null && lstMenuRoles.size() > 0) {
						int length = lstMenuRoles.size();
						menuConfigArr = new long[length];
						for (int i = 0; i < length; i++) {
							MenuRole menu = lstMenuRoles.get(i);
							menuConfigArr[i] = menu.getMenuConfigId();
						}
					}
				}
			}
			//if (auth2.isAdmin(serviceContext, "admin")) {
			if (isAdmin) {
			}
			else {
				params.put(Field.USER_ID, String.valueOf(userId));
			}
			boolean ownerBoolean = GetterUtil.getBoolean(owner);
			if (ownerBoolean) {
				params.put(DossierTerm.OWNER, String.valueOf(true));				
			}
			int total = 0;
			//
			//TODO
			if (isAdmin) {
			}
			else {
				String permission = user.getUserId() + StringPool.UNDERLINE + ConstantUtils.PERMISSION_WRITE;
				params.put(DossierTerm.MAPPING_PERMISSION, permission);
			}
			if (Validator.isNotNull(query.getAgency())) {
				
			}
			else {
				Employee e = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
				if (e != null && !Validator.isNull(e.getScope())) {
					params.put(DossierTerm.AGENCY, e.getScope());
				}
			}
			String stepCode = query.getStep();
			//_log.info("STEPCODE: "+stepCode);
			if (Validator.isNotNull(stepCode)) {
				String[] stepArr = stepCode.split(StringPool.COMMA);
				if (stepArr != null && stepArr.length > 0) {
					for (int i = 0; i < stepArr.length; i++) {
						StepConfig stepConfig = StepConfigLocalServiceUtil.getByCode(groupId, stepArr[i]);
						if (stepConfig != null) {
							params.put(DossierTerm.STATUS, stepConfig.getDossierStatus());
							params.put(DossierTerm.SUBSTATUS, stepConfig.getDossierSubStatus());

							//Process MenuConfig
							String menuGroup = stepConfig.getMenuGroup();
							if (Validator.isNotNull(menuGroup)) {
								MenuConfig menu = MenuConfigLocalServiceUtil.getByG_MENU(groupId, menuGroup);
								if (menu != null) {
									String queryParams = menu.getQueryParams();
									if (Validator.isNotNull(queryParams)) {
										params = processAddQueryParams(queryParams, user.getUserId(), stepCode, params);
									}
								}
							}

//							_log.info("START");
							long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId,
									params, null, serviceContext);
//							_log.info("START");
							JSONObject statistic = JSONFactoryUtil.createJSONObject();
							statistic.put(StepConfigTerm.STEP_CODE, stepConfig.getStepCode());
							statistic.put(StepConfigTerm.STEP_NAME, stepConfig.getStepName());
							statistic.put(StepConfigTerm.DOSSIER_STATUS, stepConfig.getDossierStatus());
							statistic.put(StepConfigTerm.DOSSIER_SUB_STATUS, stepConfig.getDossierSubStatus());
							statistic.put(StepConfigTerm.TOTAL_COUNT, count);
							total += count;
							statistics.put(statistic);
						}
					}
				}
			} else {
				if (isAdmin) {
					List<MenuConfig> menuList = MenuConfigLocalServiceUtil.getByGroupId(groupId);
					if (menuList != null && menuList.size() > 0) {
						for (MenuConfig menuConfig : menuList) {
							
							String queryParams = menuConfig.getQueryParams();
							LinkedHashMap<String, Object> paramMenuDetail = new LinkedHashMap<String, Object>();
							if (Validator.isNotNull(queryParams)) {
								int length = queryParams.lastIndexOf(StringPool.QUESTION);
								if (length > 0) {
									String subQuery = queryParams.substring(length + 1);
									String[] elementParams = Validator.isNotNull(subQuery) ? subQuery.split(StringPool.AMPERSAND) : null;
									for (String param : elementParams) {
										if (Validator.isNotNull(param) && param.contains(STEP_EQUAL)) {
											String[] paramSplit = param.split(StringPool.EQUAL);
											if (Validator.isNotNull(paramSplit[1]) && paramSplit[1].contains(StringPool.COMMA)) {
												long totalGroup = 0;
												String[] splitStep = paramSplit[1].split(StringPool.COMMA);
												for (String strStep : splitStep) {
													StepConfig step = StepConfigLocalServiceUtil.getByCode(groupId, strStep);
													if (step != null) {
														//
														paramMenuDetail.putAll(params);
														paramMenuDetail.put(DossierTerm.STATUS, step.getDossierStatus());
														paramMenuDetail.put(DossierTerm.SUBSTATUS, step.getDossierSubStatus());
														
														paramMenuDetail = processAddQueryParams(subQuery, user.getUserId(), step.getStepCode(), paramMenuDetail);
														//
														long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId, paramMenuDetail,
																null, serviceContext);
//														_log.info("count: "+count);
														if (Validator.isNotNull(step.getMenuGroup()) && step.getMenuGroup().contains(menuConfig.getMenuGroup())) {
															JSONObject statistic = JSONFactoryUtil.createJSONObject();
															statistic.put(StepConfigTerm.STEP_CODE, step.getStepCode());
															statistic.put(StepConfigTerm.STEP_NAME, step.getStepName());
															statistic.put(StepConfigTerm.DOSSIER_STATUS, step.getDossierStatus());
															statistic.put(StepConfigTerm.DOSSIER_SUB_STATUS, step.getDossierSubStatus());
															statistic.put(StepConfigTerm.MENU_GROUP, menuConfig.getMenuGroup());
															statistic.put(StepConfigTerm.TOTAL_COUNT, count);
															statistics.put(statistic);
														}
														
														total += count;
														totalGroup += count;
													}
												}
												//
												JSONObject statistic = JSONFactoryUtil.createJSONObject();
												statistic.put(StepConfigTerm.STEP_CODE, paramSplit[1]);
												statistic.put(StepConfigTerm.STEP_NAME, "");
												statistic.put(StepConfigTerm.DOSSIER_STATUS, "");
												statistic.put(StepConfigTerm.DOSSIER_SUB_STATUS, "");
												statistic.put(StepConfigTerm.MENU_GROUP, menuConfig.getMenuGroup());
												statistic.put(StepConfigTerm.TOTAL_COUNT, totalGroup);
												statistics.put(statistic);
											} else {
												StepConfig step = StepConfigLocalServiceUtil.getByCode(groupId, paramSplit[1]);
												//
												if (step != null) {
													paramMenuDetail.putAll(params);
													paramMenuDetail.put(DossierTerm.STATUS, step.getDossierStatus());
													paramMenuDetail.put(DossierTerm.SUBSTATUS, step.getDossierSubStatus());
													
													paramMenuDetail = processAddQueryParams(subQuery, user.getUserId(), step.getStepCode(), paramMenuDetail);
													//
													long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId, paramMenuDetail,
															null, serviceContext);
													JSONObject statistic = JSONFactoryUtil.createJSONObject();
													statistic.put(StepConfigTerm.STEP_CODE, step.getStepCode());
													statistic.put(StepConfigTerm.STEP_NAME, step.getStepName());
													statistic.put(StepConfigTerm.DOSSIER_STATUS, step.getDossierStatus());
													statistic.put(StepConfigTerm.DOSSIER_SUB_STATUS, step.getDossierSubStatus());
													statistic.put(StepConfigTerm.MENU_GROUP, menuConfig.getMenuGroup());
													statistic.put(StepConfigTerm.TOTAL_COUNT, count);
													total += count;
													statistics.put(statistic);
												}
											}
										}
									}
								}
							}
						}
					}
				} else {
					List<MenuConfig> menuList = MenuConfigLocalServiceUtil.getByMenus(menuConfigArr);
					if (menuList != null && menuList.size() > 0) {
						for (MenuConfig menuConfig : menuList) {
							String queryParams = menuConfig.getQueryParams();
							LinkedHashMap<String, Object> paramMenuDetail = new LinkedHashMap<String, Object>();
							if (Validator.isNotNull(queryParams)) {
								int length = queryParams.lastIndexOf(StringPool.QUESTION);
								if (length > 0) {
									String subQuery = queryParams.substring(length + 1);
									String[] elementParams = Validator.isNotNull(subQuery) ? subQuery.split(StringPool.AMPERSAND) : null;
									for (String param : elementParams) {
										if (Validator.isNotNull(param) && param.contains(STEP_EQUAL)) {
											String[] paramSplit = param.split(StringPool.EQUAL);
											if (Validator.isNotNull(paramSplit[1]) && paramSplit[1].contains(StringPool.COMMA)) {
												long totalGroup = 0;
												String[] splitStep = paramSplit[1].split(StringPool.COMMA);
												for (String strStep : splitStep) {
													StepConfig step = StepConfigLocalServiceUtil.getByCode(groupId, strStep);
													if (step != null) {
														//
														paramMenuDetail.putAll(params);
														paramMenuDetail.put(DossierTerm.STATUS, step.getDossierStatus());
														paramMenuDetail.put(DossierTerm.SUBSTATUS, step.getDossierSubStatus());
														
														paramMenuDetail = processAddQueryParams(subQuery, user.getUserId(), step.getStepCode(), paramMenuDetail);
														//
														long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId, paramMenuDetail,
																null, serviceContext);
//														_log.info("count: "+count);
														if (Validator.isNotNull(step.getMenuGroup()) && step.getMenuGroup().contains(menuConfig.getMenuGroup())) {
															JSONObject statistic = JSONFactoryUtil.createJSONObject();
															statistic.put(StepConfigTerm.STEP_CODE, step.getStepCode());
															statistic.put(StepConfigTerm.STEP_NAME, step.getStepName());
															statistic.put(StepConfigTerm.DOSSIER_STATUS, step.getDossierStatus());
															statistic.put(StepConfigTerm.DOSSIER_SUB_STATUS, step.getDossierSubStatus());
															statistic.put(StepConfigTerm.MENU_GROUP, menuConfig.getMenuGroup());
															statistic.put(StepConfigTerm.TOTAL_COUNT, count);
															statistics.put(statistic);
															
														}
														
														total += count;
														totalGroup += count;
													}
												}
												//
												JSONObject statistic = JSONFactoryUtil.createJSONObject();
												statistic.put(StepConfigTerm.STEP_CODE, paramSplit[1]);
												statistic.put(StepConfigTerm.STEP_NAME, StringPool.BLANK);
												statistic.put(StepConfigTerm.DOSSIER_STATUS, StringPool.BLANK);
												statistic.put(StepConfigTerm.DOSSIER_SUB_STATUS, StringPool.BLANK);
												statistic.put(StepConfigTerm.MENU_GROUP, menuConfig.getMenuGroup());
												statistic.put(StepConfigTerm.TOTAL_COUNT, totalGroup);
												statistics.put(statistic);
											} else {
												StepConfig step = StepConfigLocalServiceUtil.getByCode(groupId, paramSplit[1]);
												//_log.info("paramSplit[1]: "+paramSplit[1]);
												//
												if (step != null) {
													paramMenuDetail.putAll(params);
													paramMenuDetail.put(DossierTerm.STATUS, step.getDossierStatus());
													paramMenuDetail.put(DossierTerm.SUBSTATUS, step.getDossierSubStatus());
													
													//_log.info("paramMenuDetail1: "+paramMenuDetail);
													paramMenuDetail = processAddQueryParams(subQuery, user.getUserId(), step.getStepCode(), paramMenuDetail);
													//
													//_log.info("paramMenuDetail12: "+paramMenuDetail);
													long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId, paramMenuDetail,
															null, serviceContext);
													//_log.info("count: "+count);
													JSONObject statistic = JSONFactoryUtil.createJSONObject();
													statistic.put(StepConfigTerm.STEP_CODE, step.getStepCode());
													statistic.put(StepConfigTerm.STEP_NAME, step.getStepName());
													statistic.put(StepConfigTerm.DOSSIER_STATUS, step.getDossierStatus());
													statistic.put(StepConfigTerm.DOSSIER_SUB_STATUS, step.getDossierSubStatus());
													statistic.put(StepConfigTerm.MENU_GROUP, menuConfig.getMenuGroup());
													statistic.put(StepConfigTerm.TOTAL_COUNT, count);
													total += count;
													statistics.put(statistic);
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

			StatisticDossierResults results = new StatisticDossierResults();

			results.setTotal(total);
//			_log.info("total: "+total);
			results.getStatisticDossierModel()
					.addAll(StatisticUtils.mapperStatisticDossierList(statistics));

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private LinkedHashMap<String, Object> processAddQueryParams(String subQuery, long userId, String stepCode, LinkedHashMap<String, Object> params) {

		params.put(DossierTerm.ASSIGNED_USER_ID, StringPool.BLANK);
		if (Validator.isNotNull(subQuery)) {
			String[] elementParams = subQuery.split(StringPool.AMPERSAND);
			for (String param : elementParams) {
				if (Validator.isNotNull(param) && param.contains(StringPool.EQUAL)) {
					String[] paramSplit = param.split(StringPool.EQUAL);
					if (Validator.isNotNull(paramSplit[1])) {
						if (DossierActionUserTerm.ASSIGNED.equalsIgnoreCase(paramSplit[0])) {
							if (stepCode.contains(StepConfigTerm.X)) {
								StringBuilder sbParams = new StringBuilder();
								for (int i = 0; i < 9; i++) {
									if (i == 0) {
										sbParams.append(userId + StringPool.UNDERLINE + stepCode.replace(StepConfigTerm.X, String.valueOf(i)) + StringPool.UNDERLINE + paramSplit[1]);
									} else {
										sbParams.append(StringPool.COMMA);
										sbParams.append(userId + StringPool.UNDERLINE + stepCode.replace(StepConfigTerm.X, String.valueOf(i)) + StringPool.UNDERLINE + paramSplit[1]);
									}
								}
								params.put(DossierTerm.ASSIGNED_USER_ID,
										sbParams.toString());
							} else {
								params.put(DossierTerm.ASSIGNED_USER_ID,
										userId + StringPool.UNDERLINE + stepCode + StringPool.UNDERLINE + paramSplit[1]);
							}
						} else if (!StatisticManagementConstants.STEP_KEY.equalsIgnoreCase(paramSplit[0])
								&& !StatisticManagementConstants.ORDER_KEY.equalsIgnoreCase(paramSplit[0])) {
							params.put(paramSplit[0], paramSplit[1]);
						}
					}
				}
			}
		}
		
		return params;
	}

	@Override
	public Response getDossierCounting(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, StatisticDossierSearchModel query) {
		BackendAuth auth = new BackendAuthImpl();
		//
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			long userId = user.getUserId();
			_log.debug("userId: " + userId);
			
			// Declare params
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.USER_ID, String.valueOf(userId));

			//Count if employee scope
			Employee e = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
			if (e != null && !Validator.isNull(e.getScope())) {
				params.put(DossierTerm.AGENCY, e.getScope());
			}
			
			// boolean ownerBoolean = GetterUtil.getBoolean(query.getOwner());
			// if (ownerBoolean) {
			// params.put(DossierTerm.OWNER, String.valueOf(true));
			// }
			// _log.info("ownerBoolean: "+ownerBoolean);
			long total = 0;
			//
			JSONArray statisticArr = JSONFactoryUtil.createJSONArray();
			DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(StatisticManagementConstants.DOSSIER_STATUS,
					groupId);
			if (dictCollection != null) {
				List<DictItem> dictItemList = DictItemLocalServiceUtil.
						findByF_dictCollectionId_parentItemId(dictCollection.getDictCollectionId(), 0);
//						.findByF_dictCollectionId(dictCollection.getDictCollectionId());

				if (dictItemList != null && dictItemList.size() > 0) {
					for (DictItem dictItem : dictItemList) {
//						long parentItemId = dictItem.getParentItemId();
//						if (parentItemId == 0) {
							String statusCode = dictItem.getItemCode();
							JSONObject statistic = JSONFactoryUtil.createJSONObject();
							params.put(DossierTerm.STATUS, statusCode);

							long count = DossierLocalServiceUtil.countLucene(params, searchContext);
							total += count;

							statistic.put(StatisticManagementConstants.KEY, statusCode);
							statistic.put(StatisticManagementConstants.TITLE, dictItem.getItemName());
							statistic.put(StatisticManagementConstants.COUNT, count);

							_log.debug("statistic: " + statistic.toJSONString());
							statisticArr.put(statistic);
//						}
					}
				}
			}

			String top = StringPool.BLANK;
			String topName = StringPool.BLANK;
			params.put(DossierTerm.STATUS, StringPool.BLANK);
			for (int i = 0; i < 3; i++) {
				switch (i) {
				case 0:
					top = DossierTerm.DELAY;
					topName = MessageUtil.getMessage(StatisticManagementConstants.DELAY);
					break;
				case 1:
					top = DossierTerm.OVER_DUE;
					topName = MessageUtil.getMessage(StatisticManagementConstants.OVERDUE);
					break;
				case 2:
					top = DossierTerm.COMING;
					topName = MessageUtil.getMessage(StatisticManagementConstants.COMING);
					break;

				default:
					break;
				}
				params.put(DossierTerm.TOP, top);
				long count = DossierLocalServiceUtil.countLucene(params, searchContext);
				JSONObject jsonTop = JSONFactoryUtil.createJSONObject();
				jsonTop.put(StatisticManagementConstants.KEY, top);
				jsonTop.put(StatisticManagementConstants.TITLE, topName);
				jsonTop.put(StatisticManagementConstants.COUNT, count);
				statisticArr.put(jsonTop);
				_log.debug("top: " + top);
				_log.debug("title: " + topName);
				_log.debug("jsonTop: " + jsonTop.toJSONString());
			}

			StatisticCountResultModel results = new StatisticCountResultModel();

			_log.debug("total: " + total);
			_log.debug("statisticArr: " + statisticArr.toJSONString());
			if (total > 0) {
				results.setTotal(total);
				results.getData().addAll(StatisticUtils.mapperStatisticDossierCountList(statisticArr));
			} else {
				results.setTotal(0);
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response exportDossierStatistic(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String data) {
		BackendAuth auth = new BackendAuthImpl();

		HSSFWorkbook workbook = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			JSONObject docDefinition = JSONFactoryUtil.createJSONObject(data);
			JSONArray contentArr = docDefinition.getJSONArray(StatisticManagementConstants.CONTENT);
			JSONArray bodyArr = null;
			int headerRows = 0;
			workbook = new HSSFWorkbook();
			HSSFSheet mainSheet = workbook.createSheet(StatisticManagementConstants.REPORT);
			int maxCol = 0;
	
			for (int i = 0; i < contentArr.length(); i++) {
				if (contentArr.getJSONObject(i).has(StatisticManagementConstants.TABLE)) {
					bodyArr = contentArr.getJSONObject(i).getJSONObject(StatisticManagementConstants.TABLE).getJSONArray(StatisticManagementConstants.BODY);
					headerRows = contentArr.getJSONObject(i).getJSONObject(StatisticManagementConstants.TABLE).getInt(StatisticManagementConstants.HEADER_ROWS);
					
					for (int tempi = headerRows + 1; tempi < bodyArr.length(); tempi++) {
						JSONArray tempObj = bodyArr.getJSONArray(tempi);
						
						if (maxCol < tempObj.length()) {
							maxCol = tempObj.length();
						}
					}		
				}
			}
			
			int startRow = 0;
			for (int i = 0; i < contentArr.length(); i++) {
				if (contentArr.getJSONObject(i).has(StatisticManagementConstants.TABLE)) {
					//int startBorderRow = startRow;
					bodyArr = contentArr.getJSONObject(i).getJSONObject(StatisticManagementConstants.TABLE).getJSONArray(StatisticManagementConstants.BODY);
					headerRows = contentArr.getJSONObject(i).getJSONObject(StatisticManagementConstants.TABLE).getInt(StatisticManagementConstants.HEADER_ROWS);
					for (int tempi = 0; tempi <= headerRows; tempi++) {
						JSONArray tempObj = bodyArr.getJSONArray(tempi);
						int startCol = 0;
						
						HSSFRow row = mainSheet.createRow(startRow);

						for (int tempj = 0; tempj < tempObj.length(); tempj++) {
							JSONObject columnObj = tempObj.getJSONObject(tempj);
							
							_log.debug("EXPORT STATISTIC ROW: " + startRow + ", COLUMN: " + startCol);
							int spanCol = 1;
							int spanRow = 1;
							if (columnObj == null) {
							}
							if (columnObj != null && columnObj.has(StatisticManagementConstants.ROW_SPAN)) {
								spanRow = columnObj.getInt(StatisticManagementConstants.ROW_SPAN);
							}
							if (columnObj != null) {
								row.createCell(startCol).setCellValue(columnObj != null ? columnObj.getString(StatisticManagementConstants.TEXT) : StringPool.BLANK);						
								CellStyle cellStyle = row.getCell(startCol).getCellStyle();
								cellStyle.setWrapText(true);
								if (columnObj.has(StatisticManagementConstants.ALIGNMENT)) {
									String alignment = columnObj.getString(StatisticManagementConstants.ALIGNMENT);
									if (StatisticManagementConstants.ALIGNMENT_LEFT.equals(alignment)) {
										cellStyle.setAlignment(HorizontalAlignment.LEFT);
									}
									else if (StatisticManagementConstants.ALIGNMENT_RIGHT.equals(alignment)) {
										cellStyle.setAlignment(HorizontalAlignment.RIGHT);
									}
									else {
										cellStyle.setAlignment(HorizontalAlignment.CENTER);
									}
									row.getCell(startCol).setCellStyle(cellStyle);
								}
								if (columnObj != null && columnObj.has(StatisticManagementConstants.COL_SPAN)) {
									spanCol = columnObj.getInt(StatisticManagementConstants.COL_SPAN);
								}
								else {
								}
								if (spanRow > 1 || spanCol > 1) {
									mainSheet.addMergedRegion(new CellRangeAddress(startRow, startRow + spanRow - 1, startCol, startCol + spanCol - 1));		
//									_log.debug("EXPORT STATISTIC: " + (columnObj != null ? columnObj.getString(StatisticManagementConstants.TEXT) : StringPool.BLANK) + ", " + startRow + ", " + (startRow + spanRow - 1) + ", " + startCol + ", " + (startCol + spanCol - 1));
								}
							}
							startCol++;
						}
						
						startRow++;
					}
					
					for (int tempi = headerRows + 1; tempi < bodyArr.length(); tempi++) {
						JSONArray tempObj = bodyArr.getJSONArray(tempi);
						int startCol = 0;
						
						HSSFRow row = mainSheet.createRow(startRow);
						if (maxCol < tempObj.length()) {
							maxCol = tempObj.length();
						}
						for (int tempj = 0; tempj < tempObj.length(); tempj++) {
							JSONObject columnObj = tempObj.getJSONObject(tempj);
							
							_log.debug("EXPORT STATISTIC ROW: " + startRow + ", COLUMN: " + startCol);
							int spanCol = 1;
							int spanRow = 1;
							if (columnObj == null) {
							}
							if (columnObj != null && columnObj.has(StatisticManagementConstants.ROW_SPAN)) {
								spanRow = columnObj.getInt(StatisticManagementConstants.ROW_SPAN);
							}
							if (columnObj != null) {
								row.createCell(startCol).setCellValue(columnObj != null ? columnObj.getString(StatisticManagementConstants.TEXT) : StringPool.BLANK);						
								CellStyle cellStyle = row.getCell(startCol).getCellStyle();
								cellStyle.setWrapText(true);
								if (columnObj.has(StatisticManagementConstants.ALIGNMENT)) {
									String alignment = columnObj.getString(StatisticManagementConstants.ALIGNMENT);
									if (StatisticManagementConstants.ALIGNMENT_LEFT.equals(alignment)) {
										cellStyle.setAlignment(HorizontalAlignment.LEFT);
									}
									else if (StatisticManagementConstants.ALIGNMENT_RIGHT.equals(alignment)) {
										cellStyle.setAlignment(HorizontalAlignment.RIGHT);
									}
									else {
										cellStyle.setAlignment(HorizontalAlignment.CENTER);
									}
									row.getCell(startCol).setCellStyle(cellStyle);
								}
								if (columnObj != null && columnObj.has(StatisticManagementConstants.COL_SPAN)) {
									spanCol = columnObj.getInt(StatisticManagementConstants.COL_SPAN);
								}
								else {
								}
								if (spanRow > 1 || spanCol > 1) {
									mainSheet.addMergedRegion(new CellRangeAddress(startRow, startRow + spanRow - 1, startCol, startCol + spanCol - 1));		
//									_log.debug("EXPORT STATISTIC: " + (columnObj != null ? columnObj.getString("text") : StringPool.BLANK) + ", " + startRow + ", " + (startRow + spanRow - 1) + ", " + startCol + ", " + (startCol + spanCol - 1));
								}
							}
							startCol++;
						}
						startRow++;
					}	
					
				} else if (contentArr.getJSONObject(i).has(StatisticManagementConstants.TEXT)) {
					HSSFRow row = mainSheet.createRow(startRow);
					JSONArray textArr = contentArr.getJSONObject(i).getJSONArray(StatisticManagementConstants.TEXT);
					String text = null;
					if (textArr == null) {
						text = contentArr.getJSONObject(i).getString(StatisticManagementConstants.TEXT);
					}
					else {
						StringBuilder textBd = new StringBuilder();
						for (int tempi = 0; tempi < textArr.length(); tempi++) {
							textBd.append(textArr.getJSONObject(tempi).getString(StatisticManagementConstants.TEXT));
						}
						text = textBd.toString();
					}
					row.createCell(0).setCellValue(text != null ? text : StringPool.BLANK);	
					CellStyle cellStyle = row.getCell(0).getCellStyle();
					cellStyle.setWrapText(true);
					if (contentArr.getJSONObject(i).has(StatisticManagementConstants.ALIGNMENT)) {
						String alignment = contentArr.getJSONObject(i).getString(StatisticManagementConstants.ALIGNMENT);
						if (StatisticManagementConstants.ALIGNMENT_LEFT.equals(alignment)) {
							cellStyle.setAlignment(HorizontalAlignment.LEFT);
						}
						else if (StatisticManagementConstants.ALIGNMENT_RIGHT.equals(alignment)) {
							cellStyle.setAlignment(HorizontalAlignment.RIGHT);
						}
						else {
							cellStyle.setAlignment(HorizontalAlignment.CENTER);
						}
						row.getCell(0).setCellStyle(cellStyle);
					}
					mainSheet.addMergedRegion(new CellRangeAddress(startRow, startRow, 0, maxCol - 1));		
					startRow++;
				}
				else if (contentArr.getJSONObject(i).has(StatisticManagementConstants.COLUMNS)) {
					HSSFRow row = mainSheet.createRow(startRow);
					JSONArray columnArr = contentArr.getJSONObject(i).getJSONArray(StatisticManagementConstants.COLUMNS);
					int mergeColumn = maxCol / columnArr.length();
					String text = null;
					
					for (int tempi = 0; tempi < columnArr.length(); tempi++) {
						JSONArray textArr = columnArr.getJSONObject(tempi).getJSONArray(StatisticManagementConstants.TEXT);
						if (textArr == null) {
							text = columnArr.getJSONObject(tempi).getString(StatisticManagementConstants.TEXT);
						}
						else {
							StringBuilder textBd = new StringBuilder();
							for (int tempj = 0; tempj < textArr.length(); tempj++) {
								textBd.append(textArr.getJSONObject(tempj).getString(StatisticManagementConstants.TEXT));
							}
							text = textBd.toString();							
						}						
						row.createCell(tempi * mergeColumn).setCellValue(text != null ? text : StringPool.BLANK);	
						CellStyle cellStyle = row.getCell(tempi * mergeColumn).getCellStyle();
						cellStyle.setWrapText(true);
						if (contentArr.getJSONObject(i).has(StatisticManagementConstants.ALIGNMENT)) {
							String alignment = contentArr.getJSONObject(i).getString(StatisticManagementConstants.ALIGNMENT);
							if (StatisticManagementConstants.ALIGNMENT_LEFT.equals(alignment)) {
								cellStyle.setAlignment(HorizontalAlignment.LEFT);
							}
							else if (StatisticManagementConstants.ALIGNMENT_RIGHT.equals(alignment)) {
								cellStyle.setAlignment(HorizontalAlignment.RIGHT);
							}
							else {
								cellStyle.setAlignment(HorizontalAlignment.CENTER);
							}
							row.getCell(0).setCellStyle(cellStyle);
						}
						mainSheet.addMergedRegion(new CellRangeAddress(startRow, startRow, tempi * mergeColumn, tempi * mergeColumn + mergeColumn - 1));		
					}
					startRow++;
				}
			}
			
						
			File exportDir = new File(StatisticManagementConstants.FOLDER_EXPORTED);
			if (!exportDir.exists()) {
				exportDir.mkdirs();
			}
			File xlsFile = new File(exportDir.getAbsolutePath() + StringPool.SLASH + (new Date()).getTime() + ConstantUtils.DOT_XLS);
			String fileName = (new Date()).getTime() + ConstantUtils.DOT_XLS;
			
			try { 
				FileOutputStream out = new FileOutputStream(new File(exportDir.getAbsolutePath() + StringPool.SLASH + (new Date()).getTime() + ConstantUtils.DOT_XLS)); 
				workbook.write(out); 
				out.close();
				workbook.close();
			} 
			catch (Exception e) { 
				_log.debug(e);
			}
			
			ResponseBuilder responseBuilder = Response.ok((Object) xlsFile);
			String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), fileName);
			responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION,
					attachmentFilename);
			responseBuilder.header(HttpHeaders.CONTENT_TYPE, ConstantUtils.MEDIA_TYPE_EXCEL);

			return responseBuilder.build();
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}  finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					_log.debug(e);
				}
			}
		}

	}

	@Override
	public Response getDossierPerson(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String from, String to) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
//		String query = "{\r\n" + 
//				"  \"size\": 0,\r\n" + 
//				"  \"query\": {\r\n" + 
//				"    \"bool\": {\r\n" + 
//				"      \"filter\": [\r\n" + 
//				"        {\r\n" + 
//				"          \"term\": {\r\n" + 
//				"            \"entryClassName\": \"org.opencps.dossiermgt.model.DossierAction\"\r\n" + 
//				"          }\r\n" + 
//				"        },\r\n" + 
//				"		{\r\n" + 
//				"			\"range\" : {\r\n" + 
//				"				\"modified\" : { \r\n" + 
//				"					\"gte\" : \"20190709000000\",\r\n" + 
//				"					\"lte\": \"20190609235959\"\r\n" + 
//				"				}\r\n" + 
//				"			}			\r\n" + 
//				"		}\r\n" + 
//				"      ],\r\n" + 
//				"	  \"must_not\" : {\r\n" + 
//				"        \"term\" : {\r\n" + 
//				"          \"actionOverdue\" : 0\r\n" + 
//				"        }\r\n" + 
//				"      },\r\n" + 
//				"	  \"must\": {\r\n" + 
//				"		\"term\": {\r\n" + 
//				"			\"groupId\": 51801\r\n" + 
//				"		}\r\n" + 
//				"	  }	  \r\n" + 
//				"    }\r\n" + 
//				"  },\r\n" + 
//				"  \"aggs\": {\r\n" + 
//				"    \"group_by_user_id\": {\r\n" + 
//				"      \"terms\": {\r\n" + 
//				"		\"size\":10000,\r\n" + 
//				"        \"field\": \"userId\"\r\n" + 
//				"      },\r\n" + 
//				"	  \"aggs\": {\r\n" + 
//				"		\"count\":{\r\n" + 
//				"			\"cardinality\": {\r\n" + 
//				"				\"field\": \"dossierId_Number_sortable\"\r\n" + 
//				"			}\r\n" + 
//				"		}			\r\n" + 
//				"	  }\r\n" + 
//				"    }\r\n" + 
//				"  }\r\n" + 
//				"}'";		
		Date fromDate = APIDateTimeUtils.convertVNStrToDate(from);
		Date toDate = APIDateTimeUtils.convertVNStrToDate(to);
		List lstStatistics = DossierActionLocalServiceUtil.findActionOverdue(fromDate, toDate, groupId);
		long[] userIdArr = new long[lstStatistics.size()];
		int count = 0;
		String serilizeString=null;
		JSONArray actionOverdueDataJsonArray = null;
		for (Object objectData  : lstStatistics) {
			serilizeString = JSONFactoryUtil.serialize(objectData);
			try {
				actionOverdueDataJsonArray = JSONFactoryUtil.createJSONArray(serilizeString);
				userIdArr[count++] = actionOverdueDataJsonArray.getLong(0);
			} catch (JSONException e1) {
				_log.debug(e1);
			}
		}
		List<Employee> lstEmps = EmployeeLocalServiceUtil.findByG_MUSERID(groupId, userIdArr);
		
		Map<Long, Employee> mapEmps = new HashedMap<Long, Employee>();
		List<Long> lstJobPosIds = new ArrayList<Long>();
		for (Employee e : lstEmps) {
			mapEmps.put(e.getMappingUserId(), e);
			if (!lstJobPosIds.contains(e.getMainJobPostId())) {
				lstJobPosIds.add(e.getMainJobPostId());
			}
		}
		Long[] jobPosIds = new Long[lstJobPosIds.size()];
		lstJobPosIds.toArray(jobPosIds);
		long[] jobPosFinds = new long[jobPosIds.length];
		int tempCount = 0;
		for (Long jobPosId : jobPosIds) {
			jobPosFinds[tempCount++] = jobPosId;
		}
		List<JobPos> lstJobPos = JobPosLocalServiceUtil.findByF_jobPosIds(groupId, jobPosFinds);
		Map<Long, JobPos> mapJobs = new HashedMap<Long, JobPos>();
		for (JobPos jp : lstJobPos) {
			mapJobs.put(jp.getJobPosId(), jp);
		}
		JSONArray result = JSONFactoryUtil.createJSONArray();
		for (Object objectData  : lstStatistics){
			serilizeString = JSONFactoryUtil.serialize(objectData);
			try {
				actionOverdueDataJsonArray = JSONFactoryUtil.createJSONArray(serilizeString);
				if (mapEmps.containsKey(actionOverdueDataJsonArray.getLong(0))) {
					JSONObject obj = JSONFactoryUtil.createJSONObject();
					obj.put(StatisticManagementConstants.FULLNAME_KEY, mapEmps.get(actionOverdueDataJsonArray.getLong(0)).getFullName());
					obj.put(StatisticManagementConstants.OVERDUE_KEY, actionOverdueDataJsonArray.getLong(1));
					obj.put(Field.USER_ID, actionOverdueDataJsonArray.getLong(0));
					obj.put(StatisticManagementConstants.UNDUE_KEY, 0);
					obj.put(StatisticManagementConstants.JOBPOS_NAME, mapJobs.get(mapEmps.get(actionOverdueDataJsonArray.getLong(0)).getMainJobPostId()).getTitle());
					result.put(obj);					
				}
			} catch (JSONException e) {
				_log.debug(e);
			}
		}

		//Find overdue not processing
		lstStatistics = DossierActionLocalServiceUtil.findActionOverdueFuture(groupId);
		count = 0;
		userIdArr = new long[lstStatistics.size()];
		
		for (Object objectData  : lstStatistics) {
			serilizeString = JSONFactoryUtil.serialize(objectData);
			try {
				actionOverdueDataJsonArray = JSONFactoryUtil.createJSONArray(serilizeString);
				userIdArr[count++] = actionOverdueDataJsonArray.getLong(0);
			} catch (JSONException e) {
				_log.debug(e);
			}
		}
		lstEmps = EmployeeLocalServiceUtil.findByG_MUSERID(groupId, userIdArr);
		for (Employee e : lstEmps) {
			mapEmps.put(e.getMappingUserId(), e);
			if (!lstJobPosIds.contains(e.getMainJobPostId())) {
				lstJobPosIds.add(e.getMainJobPostId());
			}
		}
		jobPosIds = new Long[lstJobPosIds.size()];
		lstJobPosIds.toArray(jobPosIds);
		jobPosFinds = new long[jobPosIds.length];
		tempCount = 0;
		for (Long jobPosId : jobPosIds) {
			jobPosFinds[tempCount++] = jobPosId;
		}
		lstJobPos = JobPosLocalServiceUtil.findByF_jobPosIds(groupId, jobPosFinds);
		for (JobPos jp : lstJobPos) {
			mapJobs.put(jp.getJobPosId(), jp);
		}

		for (Object objectData  : lstStatistics){
			serilizeString = JSONFactoryUtil.serialize(objectData);
			try {
				actionOverdueDataJsonArray = JSONFactoryUtil.createJSONArray(serilizeString);
				for (int i = 0; i < result.length(); i++) {
					JSONObject obj = result.getJSONObject(i);
					if (obj.has(Field.USER_ID) && obj.getLong(Field.USER_ID) == actionOverdueDataJsonArray.getLong(0)) {
						long overdue = obj.getLong(StatisticManagementConstants.OVERDUE_KEY);
						overdue += actionOverdueDataJsonArray.getLong(1);
						obj.put(StatisticManagementConstants.OVERDUE_KEY, overdue);
					}
				}
			} catch (JSONException e1) {
				_log.debug(e1);
			}
		}		
		
		//Find undue
		lstStatistics = DossierActionLocalServiceUtil.findActionUndue(fromDate, toDate, groupId);
		count = 0;
		userIdArr = new long[lstStatistics.size()];
		
		for (Object objectData  : lstStatistics) {
			serilizeString = JSONFactoryUtil.serialize(objectData);
			try {
				actionOverdueDataJsonArray = JSONFactoryUtil.createJSONArray(serilizeString);
				userIdArr[count++] = actionOverdueDataJsonArray.getLong(0);
			} catch (JSONException e) {
				_log.debug(e);
			}
		}
		lstEmps = EmployeeLocalServiceUtil.findByG_MUSERID(groupId, userIdArr);
		for (Employee e : lstEmps) {
			mapEmps.put(e.getMappingUserId(), e);
			if (!lstJobPosIds.contains(e.getMainJobPostId())) {
				lstJobPosIds.add(e.getMainJobPostId());
			}
		}
		jobPosIds = new Long[lstJobPosIds.size()];
		lstJobPosIds.toArray(jobPosIds);
		jobPosFinds = new long[jobPosIds.length];
		tempCount = 0;
		for (Long jobPosId : jobPosIds) {
			jobPosFinds[tempCount++] = jobPosId;
		}
		lstJobPos = JobPosLocalServiceUtil.findByF_jobPosIds(groupId, jobPosFinds);
		for (JobPos jp : lstJobPos) {
			mapJobs.put(jp.getJobPosId(), jp);
		}

		for (Object objectData  : lstStatistics){
			serilizeString = JSONFactoryUtil.serialize(objectData);
			try {
				actionOverdueDataJsonArray = JSONFactoryUtil.createJSONArray(serilizeString);
				boolean foundStatistic = false;
				for (int i = 0; i < result.length(); i++) {
					JSONObject obj = result.getJSONObject(i);
					if (obj.has(Field.USER_ID) && obj.getLong(Field.USER_ID) == actionOverdueDataJsonArray.getLong(0)) {
						foundStatistic = true;
						obj.put(StatisticManagementConstants.UNDUE_KEY, actionOverdueDataJsonArray.getLong(1));
						break;
					}
				}
				if (!foundStatistic) {
					if (mapEmps.containsKey(actionOverdueDataJsonArray.getLong(0))) {
						JSONObject obj = JSONFactoryUtil.createJSONObject();
						obj.put(StatisticManagementConstants.FULLNAME_KEY, mapEmps.get(actionOverdueDataJsonArray.getLong(0)).getFullName());
						obj.put(StatisticManagementConstants.OVERDUE_KEY, actionOverdueDataJsonArray.getLong(1));
						obj.put(Field.USER_ID, actionOverdueDataJsonArray.getLong(0));
						obj.put(StatisticManagementConstants.UNDUE_KEY, 0);
						obj.put(StatisticManagementConstants.JOBPOS_NAME, mapJobs.get(mapEmps.get(actionOverdueDataJsonArray.getLong(0)).getMainJobPostId()).getTitle());
						
						result.put(obj);										
					}
				}
			} catch (JSONException e1) {
				_log.debug(e1);
			}

		}
		
		return Response.ok().entity(result.toJSONString()).build();
	}

//	private void setRegionBorderWithMedium(CellRangeAddress region, Sheet sheet) {
//        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, region, sheet);
//        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, region, sheet);
//        RegionUtil.setBorderRight(BorderStyle.MEDIUM, region, sheet);
//        RegionUtil.setBorderTop(BorderStyle.MEDIUM, region, sheet);
//    }	
}
