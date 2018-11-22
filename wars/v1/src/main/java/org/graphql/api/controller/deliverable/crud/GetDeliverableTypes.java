package org.graphql.api.controller.deliverable.crud;

import com.liferay.portal.kernel.util.Validator;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.graphql.api.controller.utils.WebKeys;
import org.opencps.deliverable.model.OpenCPSDeliverableType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import backend.deliverable.action.impl.DeliverableTypeActions;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class GetDeliverableTypes implements DataFetcher<List<OpenCPSDeliverableType>> {

	@Autowired
	private final HttpServletRequest request;

	@Autowired
	public GetDeliverableTypes(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public List<OpenCPSDeliverableType> get(DataFetchingEnvironment dataFetchingEnvironment) {

		DeliverableTypeActions actions = new DeliverableTypeActions();

		int start = dataFetchingEnvironment.getArgument(WebKeys.START);
		int end = dataFetchingEnvironment.getArgument(WebKeys.END);

		long groupId = 0;

		if (Validator.isNotNull(request.getHeader(WebKeys.GROUPID))) {
			groupId = Long.valueOf(request.getHeader(WebKeys.GROUPID));
		}

		System.out.println("GetDeliverableTypes.get(start)" + start);
		System.out.println("GetDeliverableTypes.get(end)" + end);
		System.out.println("GetDeliverableTypes.get(start)" + start);
		
		List<OpenCPSDeliverableType> results = actions.getDeliverableTypes(groupId, start, end);

		System.out.println("GetDeliverableTypes.get(results)" + results);
		return results;

	}
}
