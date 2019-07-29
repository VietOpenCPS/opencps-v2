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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
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

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
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

			results.setTotal(jsonData.getInt("total"));

			results.getStatisticDossierModel()
					.addAll(StatisticUtils.mapperStatisticDossierList(jsonData.getJSONArray("data")));

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
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
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

			results.setTotal(jsonData.getInt("total"));

			results.getStatisticDossierModel()
					.addAll(StatisticUtils.mapperStatisticDossierList(jsonData.getJSONArray("data")));

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
		//backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long userId = user.getUserId();
//			int stepType = 0;

//			_log.info("START");
			// Get info input
			JSONArray statistics = JSONFactoryUtil.createJSONArray();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			boolean isAdmin = false;
			List<Role> roles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
			if (roles != null && roles.size() > 0) {
				for (Role role : roles) {
					// LamTV_Fix sonarqube
					if ("Administrator".equals(role.getName())) {
						isAdmin = true;
						break;
					}
					if ("Administrator_data".equals(role.getName())) {
						isAdmin = true;
						break;
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
				String permission = user.getUserId() + StringPool.UNDERLINE + "write";
				params.put(DossierTerm.MAPPING_PERMISSION, permission);
			}

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
							statistic.put("stepCode", stepConfig.getStepCode());
							statistic.put("stepName", stepConfig.getStepName());
							statistic.put("dossierStatus", stepConfig.getDossierStatus());
							statistic.put("dossierSubStatus", stepConfig.getDossierSubStatus());
							statistic.put("totalCount", count);
							total += count;
							statistics.put(statistic);
						}
					}
				}
			} else {
				//List<StepConfig> stepList = StepConfigLocalServiceUtil.getStepByGroupId(groupId);
//				if (stepList != null && stepList.size() > 0) {
//					_log.debug("length: "+stepList.size());
//					for (StepConfig step: stepList) {
//						params.put(DossierTerm.STATUS, step.getDossierStatus());
//						params.put(DossierTerm.SUBSTATUS, step.getDossierSubStatus());
//						
//						//Process MenuConfig
//						String menuGroup = step.getMenuGroup();
//						if (Validator.isNotNull(menuGroup)) {
//							MenuConfig menu = MenuConfigLocalServiceUtil.getByG_MENU(groupId, menuGroup);
//							if (menu != null) {
//								String queryParams = menu.getQueryParams();
//								if (Validator.isNotNull(queryParams)) {
//									params = processAddQueryParams(queryParams, user.getUserId(), step.getStepCode(), params);
//								}
//							}
//						}
//						//System.out.println("params: "+params);
////						_log.info("DossierStatus: "+step.getDossierStatus());
//						long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId, params,
//								null, serviceContext);
////						_log.info("count: "+count);
//						JSONObject statistic = JSONFactoryUtil.createJSONObject();
//						statistic.put("stepCode", step.getStepCode());
//						statistic.put("stepName", step.getStepName());
//						statistic.put("dossierStatus", step.getDossierStatus());
//						statistic.put("dossierSubStatus", step.getDossierSubStatus());
//						statistic.put("totalCount", count);
//						total += count;
//						statistics.put(statistic);
//					}
//				}
				//
				List<MenuConfig> menuList = MenuConfigLocalServiceUtil.getByGroupId(groupId);
				if (menuList != null && menuList.size() > 0) {
					for (MenuConfig menuConfig : menuList) {
						
						String queryParams = menuConfig.getQueryParams();
						if (Validator.isNotNull(queryParams)) {
							int length = queryParams.lastIndexOf("?");
							if (length > 0) {
								String subQuery = queryParams.substring(length + 1);
								String[] elementParams = Validator.isNotNull(subQuery) ? subQuery.split("&") : null;
								for (String param : elementParams) {
									if (Validator.isNotNull(param) && param.contains("step=")) {
										String[] paramSplit = param.split("=");
										if (Validator.isNotNull(paramSplit[1]) && paramSplit[1].contains(StringPool.COMMA)) {
											long totalGroup = 0;
											String[] splitStep = paramSplit[1].split(StringPool.COMMA);
											for (String strStep : splitStep) {
												StepConfig step = StepConfigLocalServiceUtil.getByCode(groupId, strStep);
												if (step != null) {
													//
													params.put(DossierTerm.STATUS, step.getDossierStatus());
													params.put(DossierTerm.SUBSTATUS, step.getDossierSubStatus());
													
													params = processAddQueryParams(subQuery, user.getUserId(), step.getStepCode(), params);
													//
													long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId, params,
															null, serviceContext);
//													_log.info("count: "+count);
													if (Validator.isNotNull(step.getMenuGroup()) && step.getMenuGroup().contains(menuConfig.getMenuGroup())) {
														JSONObject statistic = JSONFactoryUtil.createJSONObject();
														statistic.put("stepCode", step.getStepCode());
														statistic.put("stepName", step.getStepName());
														statistic.put("dossierStatus", step.getDossierStatus());
														statistic.put("dossierSubStatus", step.getDossierSubStatus());
														statistic.put("menuGroup", menuConfig.getMenuGroup());
														statistic.put("totalCount", count);
														statistics.put(statistic);
													}
													
													total += count;
													totalGroup += count;
												}
											}
											//
											JSONObject statistic = JSONFactoryUtil.createJSONObject();
											statistic.put("stepCode", paramSplit[1]);
											statistic.put("stepName", "");
											statistic.put("dossierStatus", "");
											statistic.put("dossierSubStatus", "");
											statistic.put("menuGroup", menuConfig.getMenuGroup());
											statistic.put("totalCount", totalGroup);
											statistics.put(statistic);
										} else {
											StepConfig step = StepConfigLocalServiceUtil.getByCode(groupId, paramSplit[1]);
											//
											if (step != null) {
												params.put(DossierTerm.STATUS, step.getDossierStatus());
												params.put(DossierTerm.SUBSTATUS, step.getDossierSubStatus());
												
												params = processAddQueryParams(subQuery, user.getUserId(), step.getStepCode(), params);
												//
												long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId, params,
														null, serviceContext);
//												_log.info("count: "+count);
												JSONObject statistic = JSONFactoryUtil.createJSONObject();
												statistic.put("stepCode", step.getStepCode());
												statistic.put("stepName", step.getStepName());
												statistic.put("dossierStatus", step.getDossierStatus());
												statistic.put("dossierSubStatus", step.getDossierSubStatus());
												statistic.put("menuGroup", menuConfig.getMenuGroup());
												statistic.put("totalCount", count);
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

	private LinkedHashMap<String, Object> processAddQueryParams(String subQuery, long userId, String stepCode, LinkedHashMap<String, Object> params) {

		if (Validator.isNotNull(subQuery)) {
			String[] elementParams = subQuery.split("&");
			for (String param : elementParams) {
				if (Validator.isNotNull(param) && param.contains("=")) {
					String[] paramSplit = param.split("=");
					if (Validator.isNotNull(paramSplit[1])) {
						if ("assigned".equalsIgnoreCase(paramSplit[0])) {
							if (stepCode.contains("x")) {
								StringBuilder sbParams = new StringBuilder();
								for (int i = 0; i < 9; i++) {
									if (i == 0) {
										sbParams.append(userId + "_" + stepCode.replace("x", String.valueOf(i)) + "_" + paramSplit[1]);
									} else {
										sbParams.append(StringPool.COMMA);
										sbParams.append(userId + "_" + stepCode.replace("x", String.valueOf(i)) + "_" + paramSplit[1]);
									}
								}
								params.put(DossierTerm.ASSIGNED_USER_ID,
										sbParams.toString());
							} else {
								params.put(DossierTerm.ASSIGNED_USER_ID,
										userId + "_" + stepCode + "_" + paramSplit[1]);
							}
						} else if (!"step".equalsIgnoreCase(paramSplit[0])
								&& !"order".equalsIgnoreCase(paramSplit[0])) {
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

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long userId = user.getUserId();
			_log.debug("userId: " + userId);

			// Declare params
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.USER_ID, String.valueOf(userId));
			// boolean ownerBoolean = GetterUtil.getBoolean(query.getOwner());
			// if (ownerBoolean) {
			// params.put(DossierTerm.OWNER, String.valueOf(true));
			// }
			// _log.info("ownerBoolean: "+ownerBoolean);
			long total = 0;
			//
			JSONArray statisticArr = JSONFactoryUtil.createJSONArray();
			DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode("DOSSIER_STATUS",
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

							statistic.put("key", statusCode);
							statistic.put("title", dictItem.getItemName());
							statistic.put("count", count);

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
					topName = "Chậm hạn";
					break;
				case 1:
					top = DossierTerm.OVER_DUE;
					topName = "Quá hạn";
					break;
				case 2:
					top = DossierTerm.COMING;
					topName = "Sắp đến hạn";
					break;

				default:
					break;
				}
				params.put(DossierTerm.TOP, top);
				long count = DossierLocalServiceUtil.countLucene(params, searchContext);
				JSONObject jsonTop = JSONFactoryUtil.createJSONObject();
				jsonTop.put("key", top);
				jsonTop.put("title", topName);
				jsonTop.put("count", count);
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

			return Response.status(200).entity(results).build();

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
			JSONArray contentArr = docDefinition.getJSONArray("content");
			JSONArray bodyArr = null;
			int headerRows = 0;
			workbook = new HSSFWorkbook();
			HSSFSheet mainSheet = workbook.createSheet("report");
			int maxCol = 0;
	
			for (int i = 0; i < contentArr.length(); i++) {
				if (contentArr.getJSONObject(i).has("table")) {
					bodyArr = contentArr.getJSONObject(i).getJSONObject("table").getJSONArray("body");
					headerRows = contentArr.getJSONObject(i).getJSONObject("table").getInt("headerRows");
					
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
				if (contentArr.getJSONObject(i).has("table")) {
					//int startBorderRow = startRow;
					bodyArr = contentArr.getJSONObject(i).getJSONObject("table").getJSONArray("body");
					headerRows = contentArr.getJSONObject(i).getJSONObject("table").getInt("headerRows");
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
							if (columnObj != null && columnObj.has("rowSpan")) {
								spanRow = columnObj.getInt("rowSpan");
							}
							if (columnObj != null) {
								row.createCell(startCol).setCellValue(columnObj != null ? columnObj.getString("text") : StringPool.BLANK);						
								CellStyle cellStyle = row.getCell(startCol).getCellStyle();
								cellStyle.setWrapText(true);
								if (columnObj.has("alignment")) {
									String alignment = columnObj.getString("alignment");
									if ("left".equals(alignment)) {
										cellStyle.setAlignment(HorizontalAlignment.LEFT);
									}
									else if ("right".equals(alignment)) {
										cellStyle.setAlignment(HorizontalAlignment.RIGHT);
									}
									else {
										cellStyle.setAlignment(HorizontalAlignment.CENTER);
									}
									row.getCell(startCol).setCellStyle(cellStyle);
								}
								if (columnObj != null && columnObj.has("colSpan")) {
									spanCol = columnObj.getInt("colSpan");
								}
								else {
								}
								if (spanRow > 1 || spanCol > 1) {
									mainSheet.addMergedRegion(new CellRangeAddress(startRow, startRow + spanRow - 1, startCol, startCol + spanCol - 1));		
									_log.debug("EXPORT STATISTIC: " + (columnObj != null ? columnObj.getString("text") : StringPool.BLANK) + ", " + startRow + ", " + (startRow + spanRow - 1) + ", " + startCol + ", " + (startCol + spanCol - 1));
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
							if (columnObj != null && columnObj.has("rowSpan")) {
								spanRow = columnObj.getInt("rowSpan");
							}
							if (columnObj != null) {
								row.createCell(startCol).setCellValue(columnObj != null ? columnObj.getString("text") : StringPool.BLANK);						
								CellStyle cellStyle = row.getCell(startCol).getCellStyle();
								cellStyle.setWrapText(true);
								if (columnObj.has("alignment")) {
									String alignment = columnObj.getString("alignment");
									if ("left".equals(alignment)) {
										cellStyle.setAlignment(HorizontalAlignment.LEFT);
									}
									else if ("right".equals(alignment)) {
										cellStyle.setAlignment(HorizontalAlignment.RIGHT);
									}
									else {
										cellStyle.setAlignment(HorizontalAlignment.CENTER);
									}
									row.getCell(startCol).setCellStyle(cellStyle);
								}
								if (columnObj != null && columnObj.has("colSpan")) {
									spanCol = columnObj.getInt("colSpan");
								}
								else {
								}
								if (spanRow > 1 || spanCol > 1) {
									mainSheet.addMergedRegion(new CellRangeAddress(startRow, startRow + spanRow - 1, startCol, startCol + spanCol - 1));		
									_log.debug("EXPORT STATISTIC: " + (columnObj != null ? columnObj.getString("text") : StringPool.BLANK) + ", " + startRow + ", " + (startRow + spanRow - 1) + ", " + startCol + ", " + (startCol + spanCol - 1));
								}
							}
							startCol++;
						}
						startRow++;
					}	
					
				} else if (contentArr.getJSONObject(i).has("text")) {
					HSSFRow row = mainSheet.createRow(startRow);
					JSONArray textArr = contentArr.getJSONObject(i).getJSONArray("text");
					String text = null;
					if (textArr == null) {
						text = contentArr.getJSONObject(i).getString("text");
					}
					else {
						StringBuilder textBd = new StringBuilder();
						for (int tempi = 0; tempi < textArr.length(); tempi++) {
							textBd.append(textArr.getJSONObject(tempi).getString("text"));
						}
						text = textBd.toString();
					}
					row.createCell(0).setCellValue(text != null ? text : StringPool.BLANK);	
					CellStyle cellStyle = row.getCell(0).getCellStyle();
					cellStyle.setWrapText(true);
					if (contentArr.getJSONObject(i).has("alignment")) {
						String alignment = contentArr.getJSONObject(i).getString("alignment");
						if ("left".equals(alignment)) {
							cellStyle.setAlignment(HorizontalAlignment.LEFT);
						}
						else if ("right".equals(alignment)) {
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
				else if (contentArr.getJSONObject(i).has("columns")) {
					HSSFRow row = mainSheet.createRow(startRow);
					JSONArray columnArr = contentArr.getJSONObject(i).getJSONArray("columns");
					int mergeColumn = maxCol / columnArr.length();
					String text = null;
					
					for (int tempi = 0; tempi < columnArr.length(); tempi++) {
						JSONArray textArr = columnArr.getJSONObject(tempi).getJSONArray("text");
						if (textArr == null) {
							text = columnArr.getJSONObject(tempi).getString("text");
						}
						else {
							StringBuilder textBd = new StringBuilder();
							for (int tempj = 0; tempj < textArr.length(); tempj++) {
								textBd.append(textArr.getJSONObject(tempj).getString("text"));
							}
							text = textBd.toString();							
						}						
						row.createCell(tempi * mergeColumn).setCellValue(text != null ? text : StringPool.BLANK);	
						CellStyle cellStyle = row.getCell(tempi * mergeColumn).getCellStyle();
						cellStyle.setWrapText(true);
						if (contentArr.getJSONObject(i).has("alignment")) {
							String alignment = contentArr.getJSONObject(i).getString("alignment");
							if ("left".equals(alignment)) {
								cellStyle.setAlignment(HorizontalAlignment.LEFT);
							}
							else if ("right".equals(alignment)) {
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
			
						
			File exportDir = new File("exported");
			if (!exportDir.exists()) {
				exportDir.mkdirs();
			}
			File xlsFile = new File(exportDir.getAbsolutePath() + "/" + (new Date()).getTime() + ".xls");
			String fileName = (new Date()).getTime() + ".xls";
			
			try { 
				FileOutputStream out = new FileOutputStream(new File(exportDir.getAbsolutePath() + "/" + (new Date()).getTime() + ".xls")); 
				workbook.write(out); 
				out.close();
				workbook.close();
			} 
			catch (Exception e) { 
				_log.debug(e);
			}
			
			ResponseBuilder responseBuilder = Response.ok((Object) xlsFile);
			responseBuilder.header("Content-Disposition",
					"attachment; filename=\"" + fileName + "\"");
			responseBuilder.header("Content-Type", "application/vnd.ms-excel");

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

//	private void setRegionBorderWithMedium(CellRangeAddress region, Sheet sheet) {
//        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, region, sheet);
//        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, region, sheet);
//        RegionUtil.setBorderRight(BorderStyle.MEDIUM, region, sheet);
//        RegionUtil.setBorderTop(BorderStyle.MEDIUM, region, sheet);
//    }	
}
