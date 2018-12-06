package org.graphql.api.controller.deliverable.crud;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.graphql.api.controller.utils.WebKeys;
import org.opencps.deliverable.model.OpenCPSDeliverableType;
import org.opencps.deliverable.model.OpenCPSDeliverableTypeRole;
import org.opencps.deliverable.service.OpenCPSDeliverableTypeRoleLocalServiceUtil;
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
		long userId = 0;
		
		if (Validator.isNotNull(request.getAttribute(WebKeys.USER_ID))) {
			userId = (long) request.getAttribute(WebKeys.USER_ID);
		}
		
		List<OpenCPSDeliverableType> results = new ArrayList<>();
		
		if (userId > 0) {
			
			List<OpenCPSDeliverableType> resultsTemp = actions.getDeliverableTypes(groupId, start, end);
			
			try {
				
				User user = UserLocalServiceUtil.getUser(userId);
				
				Long[] longObjects = ArrayUtils.toObject(user.getRoleIds());
				List<Long> roleIds = Arrays.asList(longObjects);
				
				List<Long> rIds = new ArrayList<>();
				
				for (OpenCPSDeliverableType openCPSDeliverableType : resultsTemp) {
					
					rIds = actions.getRoleIdByTypes(openCPSDeliverableType.getDeliverableTypeId());
					
					for (Long rId : rIds) {
						if (roleIds.contains(rId)) {
							results.add(openCPSDeliverableType);
							break;
						}
					}
					
				}
				
//				results = resultsTemp;
				
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		return results;

	}
}
