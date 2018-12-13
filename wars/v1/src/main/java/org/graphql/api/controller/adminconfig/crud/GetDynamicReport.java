package org.graphql.api.controller.adminconfig.crud;

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
public class GetDynamicReport implements DataFetcher<DynamicReport> {

	@Autowired
	private final HttpServletRequest request;

	@Autowired
	public GetDynamicReport(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public DynamicReport get(DataFetchingEnvironment dataFetchingEnvironment) {

		String reportCode = dataFetchingEnvironment.getArgument(WebKeys.REPORTCODE);

		long groupId = 0;

		if (Validator.isNotNull(request.getHeader(WebKeys.GROUPID))) {
			groupId = Long.valueOf(request.getHeader(WebKeys.GROUPID));
		}

		return DynamicReportLocalServiceUtil.fetchByCode(groupId, reportCode);

	}
}
