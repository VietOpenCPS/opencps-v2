package backend.api.rest.application.v21.impl;

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
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;
import org.opencps.rest.application.api.DossierStatisticApi;
import org.opencps.rest.application.model.DossierStatisticResultModel;

import backend.api.rest.application.v21.elasticwrap.ElasticQueryWrapUtil;

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
		_log.info("====START GET STATISTIC==== ");
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

			// Get info input
//			long notStatusReg = query.getNotStatusReg();
//			String status = query.getDossierStatus();
//			String substatus = query.getDossierSubStatus();
			List<StepConfig> stepList = StepConfigLocalServiceUtil.getByStepType(groupId, stepType);
			JSONArray statistics = JSONFactoryUtil.createJSONArray();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.USER_ID, String.valueOf(userId));
			params.put(DossierTerm.OWNER, String.valueOf(true));
//			params.put(DossierTerm.NOT_STATUS_REG, notStatusReg);
			int total = 0;
			if (stepList != null && stepList.size() > 0) {
				for (StepConfig step: stepList) {
					params.put(DossierTerm.STATUS, step.getDossierStatus());
					params.put(DossierTerm.SUBSTATUS, step.getDossierSubStatus());
					long count = actions.countTodoTest(user.getUserId(), company.getCompanyId(), groupId, params,
							null, serviceContext);
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
			String test = IOUtils.toString(classLoader.getResourceAsStream("test.json"));
			
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
			//
			//String test11 = IOUtils.toString(classLoader.getResourceAsStream("Statistic.json"));
			//JSONObject data = ElasticQueryWrapUtil.queryMultiple(sb1.toString(), Dossier.class.getName(), company.getCompanyId());
//			JSONArray resultData = data.getJSONObject("hits").getJSONArray("hits");
//			_log.info("resultData: "+resultData);
			results = new DossierStatisticResultModel();

			results.setTotal(total);
			results.setData(StatisticUtils.mapperStatisticDossierList(statistics));
			_log.info("====END GET STATISTIC==== ");

		} catch (Exception e) {
			_log.debug(e);
			_log.error("====STATISTIC ERROR==== ");
			respones.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		return results;
	}

}
