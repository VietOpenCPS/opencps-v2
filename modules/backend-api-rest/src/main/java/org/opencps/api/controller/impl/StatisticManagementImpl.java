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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.opencps.adminconfig.model.DynamicReport;
import org.opencps.adminconfig.service.DynamicReportLocalServiceUtil;
import org.opencps.api.controller.StatisticManagement;
import org.opencps.api.controller.util.StatisticUtils;
import org.opencps.api.dossier.model.DossierSearchModel;
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
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
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
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();
		
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
//			long notStatusReg = query.getNotStatusReg();
//			String status = query.getDossierStatus();
//			String substatus = query.getDossierSubStatus();
//			_log.info("START");
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
							//TODO
							if (isAdmin) {
							}
							else {
								String permission = user.getUserId() + StringPool.UNDERLINE + "write";
								params.put(DossierTerm.MAPPING_PERMISSION, permission);
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
				List<StepConfig> stepList = StepConfigLocalServiceUtil.getStepByGroupId(groupId);
				if (stepList != null && stepList.size() > 0) {
//					_log.info("length: "+stepList.size());
					for (StepConfig step: stepList) {
						params.put(DossierTerm.STATUS, step.getDossierStatus());
						params.put(DossierTerm.SUBSTATUS, step.getDossierSubStatus());
						//TODO
						if (isAdmin) {
						}
						else {
							String permission = user.getUserId() + StringPool.UNDERLINE + "write";
							params.put(DossierTerm.MAPPING_PERMISSION, permission);
						}
//						_log.info("DossierStatus: "+step.getDossierStatus());
						long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId, params,
								null, serviceContext);
//						_log.info("count: "+count);
						JSONObject statistic = JSONFactoryUtil.createJSONObject();
						statistic.put("stepCode", step.getStepCode());
						statistic.put("stepName", step.getStepName());
						statistic.put("dossierStatus", step.getDossierStatus());
						statistic.put("dossierSubStatus", step.getDossierSubStatus());
						statistic.put("totalCount", count);
						total += count;
						statistics.put(statistic);
					}
				}
			}
//			_log.info("START");
//			JSONArray statistics = JSONFactoryUtil.createJSONArray();
//
//			params.put(Field.GROUP_ID, String.valueOf(groupId));
//			params.put(Field.USER_ID, String.valueOf(userId));
//			params.put(DossierTerm.OWNER, String.valueOf(true));
//			params.put(DossierTerm.NOT_STATUS_REG, notStatusReg);

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
			Locale locale, User user, ServiceContext serviceContext, DossierSearchModel query, String reportCode) {
		BackendAuth auth = new BackendAuthImpl();
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			DynamicReport dynamicReport = DynamicReportLocalServiceUtil.fetchByCode(groupId, reportCode);
			ScriptEngineManager manager = new ScriptEngineManager(null);
			ScriptEngine engine = manager.getEngineByExtension("js");
			engine.put("jsonObject", dynamicReport.getTableConfig());
			engine.eval("var json = " + dynamicReport.getTableConfig() + " ; var contentArr = json.docDefinition.content; var bodyArray = [];\n" + 
					"var headerRows;\n" + 
					"\n" + 
					"for (i = 0; i < contentArr.length; i++) {\n" + 
					"	if (contentArr[i].hasOwnProperty('table')) {\n" + 
					"    	headerRows = contentArr[i].table.headerRows;\n" + 
					"        bodyArray = JSON.stringify(contentArr[i].table.body);\n" + 
					"        break;\n" + 
					"    }\n" + 
					"}");
			_log.debug("EXPORT STATISTIC SCRIPT: " + engine.get("bodyArray"));
			
			JSONArray contentArr = JSONFactoryUtil.createJSONArray(engine.get("bodyArray").toString());
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet mainSheet = workbook.createSheet("report");
			_log.debug("EXPORT STATISTIC SCRIPT LENGTH: " + contentArr.length());
			for (int i = 0; i < contentArr.length(); i++) {
				JSONObject contentObj = contentArr.getJSONObject(i);
				if (contentObj.has("table")) {
					JSONObject tableObj = contentObj.getJSONObject("table");
					JSONArray bodyArr = tableObj.getJSONArray("body");
					int headerRows = tableObj.getInt("headerRows");
					int startRow = 0;
					int maxRow = 1;
					for (int tempi = 0; tempi < headerRows; tempi++) {
						JSONArray tempObj = bodyArr.getJSONArray(tempi);
						int startCol = 0;
						
						for (int tempj = 0; tempj < tempObj.length(); tempj++) {
							JSONObject columnObj = tempObj.getJSONObject(tempj);
							HSSFRow row = mainSheet.createRow(startRow);
							int spanCol = 0;
							int spanRow = 0;
							if (columnObj.has("rowSpan")) {
								if (maxRow < columnObj.getInt("rowSpan")) {
									maxRow = columnObj.getInt("rowSpan");
								}
								spanRow = columnObj.getInt("rowSpan");
							}
							row.createCell(startCol).setCellValue(columnObj.getString("text"));
							if (columnObj.has("colSpan")) {
								startCol += columnObj.getInt("colSpan");
								spanCol = columnObj.getInt("colSpan");
							}
							mainSheet.addMergedRegion(new CellRangeAddress(startRow, startRow + spanRow, startCol, startCol + spanCol));								
						}
						
						startRow = maxRow;
					}
					
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
				e.printStackTrace(); 
			}   
			
			ResponseBuilder responseBuilder = Response.ok((Object) xlsFile);
			responseBuilder.header("Content-Disposition",
					"attachment; filename=\"" + fileName + "\"");
			responseBuilder.header("Content-Type", "application/vnd.ms-excel");

			return responseBuilder.build();			
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}

	}

}
