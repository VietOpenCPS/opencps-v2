package org.graphql.api.controller.adminconfig.crud;

import com.liferay.portal.kernel.util.Validator;

import java.util.List;

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
public class GetDynamicReports implements DataFetcher<List<DynamicReport>> {

	@Autowired
	private final HttpServletRequest request;

	@Autowired
	public GetDynamicReports(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public List<DynamicReport> get(DataFetchingEnvironment dataFetchingEnvironment) {
		
		int start = dataFetchingEnvironment.getArgument(WebKeys.START);
		int end = dataFetchingEnvironment.getArgument(WebKeys.END);

		String reportType = dataFetchingEnvironment.getArgument("reportType");
		
		long groupId = 0;

		if (Validator.isNotNull(request.getHeader(WebKeys.GROUPID))) {
			groupId = Long.valueOf(request.getHeader(WebKeys.GROUPID));
		}

		List<DynamicReport> results = DynamicReportLocalServiceUtil.getByGroupType(groupId, reportType, start, end);

		return results;

	}
}
