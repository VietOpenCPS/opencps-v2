package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import org.opencps.api.controller.StatisticManagement;
import org.opencps.api.controller.util.StatisticUtils;
import org.opencps.api.statistic.model.StatisticCountResultModel;
import org.opencps.api.statistic.model.StatisticDossierResults;
import org.opencps.api.statistic.model.StatisticDossierSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.StatisticActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.impl.StatisticActionsImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;

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

	@Override
	public Response getDossierTodoTest(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, StatisticDossierSearchModel query, String owner) {
		BackendAuth auth = new BackendAuthImpl();
		StatisticActions actions = new StatisticActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			long userId = user.getUserId();

			// Get info input
			JSONArray statistics = JSONFactoryUtil.createJSONArray();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			boolean isAdmin = false;
			List<Role> roles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
			if (roles != null && roles.size() > 0) {
				for (Role role : roles) {
					// LamTV_Fix sonarqube
					if (ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN).equals(role.getName())) {
						isAdmin = true;
						break;
					}
					if (ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN_DATA).equals(role.getName())) {
						isAdmin = true;
						break;
					}
				}
			}
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

			String stepCode = query.getStep();
//			_log.info("STEPCODE: "+stepCode);
			if (Validator.isNotNull(stepCode)) {
				String[] stepArr = stepCode.split(StringPool.COMMA);
				if (stepArr != null && stepArr.length > 0) {
					for (int i = 0; i < stepArr.length; i++) {
						StepConfig stepConfig = StepConfigLocalServiceUtil.getByCode(groupId, stepArr[i]);
						if (stepConfig != null) {
							params.put(DossierTerm.STATUS, stepConfig.getDossierStatus());
							params.put(DossierTerm.SUBSTATUS, stepConfig.getDossierSubStatus());

							//Process MenuConfig
							long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId,
									params, null, serviceContext);
//							_log.info("START");
							JSONObject statistic = JSONFactoryUtil.createJSONObject();
							statistic.put(ConstantUtils.VALUE_STEP_CODE, stepConfig.getStepCode());
							statistic.put(ConstantUtils.VALUE_STEP_NAME, stepConfig.getStepName());
							statistic.put(ConstantUtils.VALUE_DOSSIER_STATUS, stepConfig.getDossierStatus());
							statistic.put(ConstantUtils.VALUE_DOSSIER_SUB_STATUS, stepConfig.getDossierSubStatus());
							statistic.put(ConstantUtils.VALUE_TOTAL_COUNT, count);
							total += count;
							statistics.put(statistic);
						}
					}
				}
			} else {
				//
				List<MenuConfig> menuList = MenuConfigLocalServiceUtil.getByGroupId(groupId);
				if (menuList != null && menuList.size() > 0) {
					for (MenuConfig menuConfig : menuList) {
						
						String queryParams = menuConfig.getQueryParams();
						if (Validator.isNotNull(queryParams)) {
							int length = queryParams.lastIndexOf(StringPool.QUESTION);
							if (length > 0) {
								String subQuery = queryParams.substring(length + 1);
								String[] elementParams = Validator.isNotNull(subQuery) ? subQuery.split(StringPool.AMPERSAND) : null;
								for (String param : elementParams) {
									if (Validator.isNotNull(param)) {
										String[] paramSplit = param.split(StringPool.EQUAL);
										if (Validator.isNotNull(paramSplit[1]) && paramSplit[1].contains(StringPool.COMMA)) {
											long totalGroup = 0;
											String[] splitStep = paramSplit[1].split(StringPool.COMMA);
											for (String strStep : splitStep) {
												StepConfig step = StepConfigLocalServiceUtil.getByCode(groupId, strStep);
												if (step != null) {
													//
													params.put(DossierTerm.STATUS, step.getDossierStatus());
													params.put(DossierTerm.SUBSTATUS, step.getDossierSubStatus());
													//
													long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId, params,
															null, serviceContext);
//													_log.info("count: "+count);
													if (Validator.isNotNull(step.getMenuGroup()) && step.getMenuGroup().contains(menuConfig.getMenuGroup())) {
														JSONObject statistic = JSONFactoryUtil.createJSONObject();
														statistic.put(ConstantUtils.VALUE_STEP_CODE, step.getStepCode());
														statistic.put(ConstantUtils.VALUE_STEP_NAME, step.getStepName());
														statistic.put(ConstantUtils.VALUE_DOSSIER_STATUS, step.getDossierStatus());
														statistic.put(ConstantUtils.VALUE_DOSSIER_SUB_STATUS, step.getDossierSubStatus());
														statistic.put(ConstantUtils.VALUE_MENU_GROUP, menuConfig.getMenuGroup());
														statistic.put(ConstantUtils.VALUE_TOTAL_COUNT, count);
														statistics.put(statistic);
													}
													
													total += count;
													totalGroup += count;
												}
											}
											//
											JSONObject statistic = JSONFactoryUtil.createJSONObject();
											statistic.put(ConstantUtils.VALUE_STEP_CODE, paramSplit[1]);
											statistic.put(ConstantUtils.VALUE_STEP_NAME, StringPool.BLANK);
											statistic.put(ConstantUtils.VALUE_DOSSIER_STATUS, StringPool.BLANK);
											statistic.put(ConstantUtils.VALUE_DOSSIER_SUB_STATUS, StringPool.BLANK);
											statistic.put(ConstantUtils.VALUE_MENU_GROUP, menuConfig.getMenuGroup());
											statistic.put(ConstantUtils.VALUE_TOTAL_COUNT, totalGroup);
											statistics.put(statistic);
										} else {
											StepConfig step = StepConfigLocalServiceUtil.getByCode(groupId, paramSplit[1]);
											//
											if (step != null) {
												params.put(DossierTerm.STATUS, step.getDossierStatus());
												params.put(DossierTerm.SUBSTATUS, step.getDossierSubStatus());
												
												long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId, params,
														null, serviceContext);
//												_log.info("count: "+count);
												JSONObject statistic = JSONFactoryUtil.createJSONObject();
												statistic.put(ConstantUtils.VALUE_STEP_CODE, step.getStepCode());
												statistic.put(ConstantUtils.VALUE_STEP_NAME, step.getStepName());
												statistic.put(ConstantUtils.VALUE_DOSSIER_STATUS, step.getDossierStatus());
												statistic.put(ConstantUtils.VALUE_DOSSIER_SUB_STATUS, step.getDossierSubStatus());
												statistic.put(ConstantUtils.VALUE_MENU_GROUP, menuConfig.getMenuGroup());
												statistic.put(ConstantUtils.VALUE_TOTAL_COUNT, count);
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

			StatisticDossierResults results = new StatisticDossierResults();

			results.setTotal(total);
//			_log.info("total: "+total);
			results.getStatisticDossierModel()
					.addAll(StatisticUtils.mapperStatisticDossierList(statistics));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
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
			long total = 0;
			//
			JSONArray statisticArr = JSONFactoryUtil.createJSONArray();
			DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(ReadFilePropertiesUtils.get(ConstantUtils.DOSSIER_STATUS),
					groupId);
			if (dictCollection != null) {
				List<DictItem> dictItemList = DictItemLocalServiceUtil.
						findByF_dictCollectionId_parentItemId(dictCollection.getDictCollectionId(), 0);

				if (dictItemList != null && dictItemList.size() > 0) {
					for (DictItem dictItem : dictItemList) {
							String statusCode = dictItem.getItemCode();
							JSONObject statistic = JSONFactoryUtil.createJSONObject();
							params.put(DossierTerm.STATUS, statusCode);

							long count = DossierLocalServiceUtil.countLucene(params, searchContext);
							total += count;

							statistic.put(ConstantUtils.VALUE_KEY, statusCode);
							statistic.put(ConstantUtils.VALUE_TITLE, dictItem.getItemName());
							statistic.put(ConstantUtils.VALUE_COUNT, count);

							statisticArr.put(statistic);
//						}
					}
				}
			}

			params.put(DossierTerm.STATUS, StringPool.BLANK);

			StatisticCountResultModel results = new StatisticCountResultModel();

			if (total > 0) {
				results.setTotal(total);
				results.getData().addAll(StatisticUtils.mapperStatisticDossierCountList(statisticArr));
			} else {
				results.setTotal(0);
			}

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
