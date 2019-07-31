package org.graphql.api.controller.adminconfig.crud;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

import org.graphql.api.controller.utils.WebKeys;
import org.opencps.adminconfig.model.DynamicReport;
import org.opencps.adminconfig.service.DynamicReportLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class UpdateDynamicReport implements DataFetcher<DynamicReport> {

	@Autowired
	private final HttpServletRequest request;

	@Autowired
	public UpdateDynamicReport(HttpServletRequest request) {
		this.request = request;
	}

	private static Log _log = LogFactoryUtil.getLog(UpdateDynamicReport.class);

	@Override
	public DynamicReport get(DataFetchingEnvironment dataFetchingEnvironment) {

		DynamicReport result = null;
		
		String input = dataFetchingEnvironment.getArgument(WebKeys.BODY);

		long groupId = 0;

		if (Validator.isNotNull(request.getHeader(WebKeys.GROUPID))) {
			groupId = Long.valueOf(request.getHeader(WebKeys.GROUPID));
		}
		
		System.out.println("UpdateDynamicReport.get(input)" + input);
		
		JSONObject inputObject;
		try {
			inputObject = JSONFactoryUtil.createJSONObject(input);
			
			inputObject.put(WebKeys.GROUPID, groupId);
			inputObject.put(WebKeys.USERID, request.getAttribute(WebKeys.USER_ID));
			inputObject.put(WebKeys.COMPANYID, request.getAttribute(WebKeys.COMPANY_ID));
			
			long dynamicReportId = inputObject.getLong("dynamicReportId");
			
			DynamicReport dynamicReport = DynamicReportLocalServiceUtil.fetchDynamicReport(dynamicReportId);
			dynamicReport.setUserConfig(inputObject.getString("userConfig"));

			System.out.println("UpdateDynamicReport.get()" + dynamicReport);
			
			result = DynamicReportLocalServiceUtil.adminProcessData(JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(dynamicReport)));
			
		} catch (JSONException e) {
			_log.debug(e);
		}
		
		return result;

	}
}
