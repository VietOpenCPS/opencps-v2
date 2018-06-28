package backend.api.rest.application.v21.impl;

import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.apache.cxf.helpers.IOUtils;
import org.opencps.dossiermgt.action.StatisticActions;
import org.opencps.dossiermgt.action.impl.StatisticActionsImpl;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import backend.api.rest.application.utils.ReadFileJSONUtils;
import backend.api.rest.application.v21.elasticwrap.ElasticQueryWrapUtil;
import io.swagger.api.DossierStatisticApi;
import io.swagger.model.DossierStatisticResultModel;

public class StatisticApiImpl implements DossierStatisticApi{

	@Context
	private User user;
	@Context
	private HttpHeaders header;
	@Context
	ServiceContext serviceContext;
	@Context
	Company company;
	@Context
	HttpServletResponse respones;

	private static Log _log = LogFactoryUtil.getLog(StatisticApiImpl.class);
	@Override
	public DossierStatisticResultModel getDossierTodoTest() {
//		BackendAuth auth = new BackendAuthImpl();
		StatisticActions actions = new StatisticActionsImpl();
		DossierStatisticResultModel results = null;
		try {

//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long userId = user.getUserId();
			int stepType = 0;

			_log.info("START");
			// Get info input
//			long notStatusReg = query.getNotStatusReg();
//			String status = query.getDossierStatus();
//			String substatus = query.getDossierSubStatus();
			List<StepConfig> stepList = StepConfigLocalServiceUtil.getByStepType(stepType);
			_log.info("START");
			JSONArray statistics = JSONFactoryUtil.createJSONArray();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.USER_ID, String.valueOf(userId));
			params.put(DossierTerm.OWNER, String.valueOf(true));
//			params.put(DossierTerm.NOT_STATUS_REG, notStatusReg);
			int total = 0;
			if (stepList != null && stepList.size() > 0) {
				_log.info("length: "+stepList.size());
				for (StepConfig step: stepList) {
					params.put(DossierTerm.STATUS, step.getDossierStatus());
					params.put(DossierTerm.SUBSTATUS, step.getDossierSubStatus());
					_log.info("START");
					long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId, params,
							null, serviceContext);
					_log.info("START");
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

//			ReadFileJSONUtils.readFileJSON("Statistic");
			ClassLoader classLoader = getClass().getClassLoader();
			_log.info("classLoader: "+classLoader.toString());
			String test = IOUtils.toString(classLoader.getResourceAsStream("test.json"));
			_log.info("test: "+test);
			
			JSONArray tt1 = JSONFactoryUtil.createJSONArray(test);
			StringBuilder sb1 = new StringBuilder();
			if (tt1 != null && tt1.length() > 0) {
				int length = tt1.length();
				for (int i = 0; i < length; i++) {
					JSONObject json = tt1.getJSONObject(i);
					if (i%2 != 0) {
						json.put("from", 0);
						json.put("size", 3);
						sb1.append(json.toString());
						sb1.append(System.lineSeparator());
					} else {
						sb1.append(json.toString());
						sb1.append(System.lineSeparator());
					}
					
				}
			}
//			tt1.put("from", 0);
//			tt1.put("size", 3);
			_log.info("tt1: "+tt1.toString());
			
			//
			String test11 = IOUtils.toString(classLoader.getResourceAsStream("Statistic.json"));
			_log.info("test11: "+test11);
			
			JSONObject test1111 = JSONFactoryUtil.createJSONObject(test11);
//			test1111.put("from", 0);
//			test1111.put("size", 2);
			_log.info("test1111: "+test1111.toString());
			
//			StringBuilder sb = new StringBuilder();
//			
//			sb.append("{}");
//			sb.append(System.lineSeparator());
//			sb.append(tt1.toString());
//			sb.append(System.lineSeparator());
//			sb.append("{}");
//			sb.append(System.lineSeparator());
//			sb.append(test1111.toString());
//			sb.append(System.lineSeparator());
			
			_log.info("sb: "+sb1.toString());
			//
//			String test = ReadFileJSONUtils.readFileJSON("Statistic");
//			_log.info("test: "+test);
			JSONObject data = ElasticQueryWrapUtil.queryMultiple(sb1.toString(), Dossier.class.getName(), company.getCompanyId());
			_log.info("data: "+data);
			JSONArray resultData = data.getJSONObject("hits").getJSONArray("hits");
//			_log.info("resultData: "+resultData);
			results = new DossierStatisticResultModel();

			results.setTotal(total);
			_log.info("total: "+total);
			results.setData(StatisticUtils.mapperStatisticDossierList(statistics));

		} catch (Exception e) {
			_log.error(e);
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		return results;
	}

}
