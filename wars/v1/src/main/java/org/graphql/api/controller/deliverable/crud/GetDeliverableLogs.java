package org.graphql.api.controller.deliverable.crud;

import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.graphql.api.controller.utils.WebKeys;
import org.opencps.dossiermgt.model.DeliverableLog;
import org.opencps.dossiermgt.service.DeliverableLogLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class GetDeliverableLogs implements DataFetcher<List<DeliverableLog>> {

	@Autowired
	private final HttpServletRequest request;

	@Autowired
	public GetDeliverableLogs(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public List<DeliverableLog> get(DataFetchingEnvironment dataFetchingEnvironment) {

		long fk = dataFetchingEnvironment.getArgument(WebKeys.FK);
		int start = dataFetchingEnvironment.getArgument(WebKeys.START);
		int end = dataFetchingEnvironment.getArgument(WebKeys.END);
//		long groupId = 0;

		if (Validator.isNotNull(request.getHeader(WebKeys.GROUPID))) {
//			groupId = Long.valueOf(request.getHeader(WebKeys.GROUPID));
		}

		System.out.println("GetDeliverableTypes.get(start)" + start);
		System.out.println("GetDeliverableTypes.get(end)" + end);
		System.out.println("GetDeliverableTypes.get(start)" + start);
		
		List<DeliverableLog> results = DeliverableLogLocalServiceUtil.findByF_deliverableId(fk, start, end);

		System.out.println("GetDeliverableTypes.get(results)" + results);
		return results;

	}
}
