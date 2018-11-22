package org.graphql.api.controller.deliverable.crud;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

import org.graphql.api.controller.utils.WebKeys;
import org.opencps.deliverable.model.OpenCPSDeliverable;
import org.opencps.deliverable.service.OpenCPSDeliverableLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class CreateDeliverable implements DataFetcher<OpenCPSDeliverable> {

	@Autowired
	private final HttpServletRequest request;

	@Autowired
	public CreateDeliverable(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public OpenCPSDeliverable get(DataFetchingEnvironment dataFetchingEnvironment) {

		OpenCPSDeliverable result = null;
		
		String input = dataFetchingEnvironment.getArgument(WebKeys.BODY);

		long groupId = 0;

		if (Validator.isNotNull(request.getHeader(WebKeys.GROUPID))) {
			groupId = Long.valueOf(request.getHeader(WebKeys.GROUPID));
		}
		
		System.out.println("CreateDeliverable.get()");
		System.out.println("CreateDeliverable.get(input)" + input);
		
		JSONObject inputObject;
		try {
			inputObject = JSONFactoryUtil.createJSONObject(input);
			
			inputObject.put(WebKeys.GROUPID, groupId);
			inputObject.put(WebKeys.USERID, request.getAttribute(WebKeys.USER_ID));
			inputObject.put(WebKeys.COMPANYID, request.getAttribute(WebKeys.COMPANY_ID));
			
			result = OpenCPSDeliverableLocalServiceUtil.adminProcessData(inputObject);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return result;

	}
}
