package org.graphql.api.controller.deliverable.crud;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.graphql.api.controller.utils.WebKeys;
import org.graphql.api.model.DeliverableTypeDynamic;
import org.opencps.dossiermgt.action.DeliverableTypesActions;
import org.opencps.dossiermgt.action.impl.DeliverableTypesActionsImpl;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.DeliverableTypeRole;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created binhth
 */
@Component
public class GetDeliverableTypes implements DataFetcher<List<DeliverableTypeDynamic>> {

	@Autowired
	private final HttpServletRequest request;

	@Autowired
	public GetDeliverableTypes(HttpServletRequest request) {
		this.request = request;
	}

	private static Log _log = LogFactoryUtil.getLog(GetDeliverableTypes.class);

	@Override
	public List<DeliverableTypeDynamic> get(DataFetchingEnvironment dataFetchingEnvironment) {

		DeliverableTypesActions actions = new DeliverableTypesActionsImpl();

		long groupId = 0;

		int start = dataFetchingEnvironment.getArgument(WebKeys.START);
		int end = dataFetchingEnvironment.getArgument(WebKeys.END);


		if (Validator.isNotNull(request.getHeader(WebKeys.GROUPID))) {
				groupId = Long.valueOf(request.getHeader(WebKeys.GROUPID));
		}
		long[] groupIds = new long [] {0, groupId > 0 ? groupId : null};

		long userId = 0;
		
		if (Validator.isNotNull(request.getAttribute(WebKeys.USER_ID))) {
			userId = (long) request.getAttribute(WebKeys.USER_ID);
		}
		
		List<DeliverableTypeDynamic> results = new ArrayList<>();
		
		if (userId > 0) {
			//getDeliverableTypesList
			try {
				List<DeliverableType> resultsTemp = DeliverableTypeLocalServiceUtil.getDeliverableTypeByGroupId(groupIds, start, end);
				_log.info("Size: " + resultsTemp.size());
				User user = UserLocalServiceUtil.getUser(userId);
				
				Long[] longObjects = ArrayUtils.toObject(user.getRoleIds());
				List<Long> roleIds = Arrays.asList(longObjects);
				//List<Long> rIds = new ArrayList<>();
				
				for (DeliverableType openCPSDeliverableType : resultsTemp) {
					openCPSDeliverableType.setGroupId(openCPSDeliverableType.getGroupId());
					
					// List<Long> rIds = actions.getRoleIdByTypes(openCPSDeliverableType.getDeliverableTypeId());
					List<DeliverableTypeRole> deliverableTypeRoles = actions.getRolesByType(openCPSDeliverableType.getDeliverableTypeId());
					if (deliverableTypeRoles != null && deliverableTypeRoles.size() > 0) {
						Boolean moderator = false;
						Boolean allowAdd = false;
						for (DeliverableTypeRole deliverableTypeRole : deliverableTypeRoles) {
							if (roleIds.contains(deliverableTypeRole.getRoleId())) {
								allowAdd = true;
								if (deliverableTypeRole.getModerator()) {
									moderator = deliverableTypeRole.getModerator();
								}
							}
						}
						if (allowAdd) {
							results.add(new DeliverableTypeDynamic(openCPSDeliverableType, moderator));
						}
					}
				}
//				results = resultsTemp;
				
			} catch (PortalException e) {
				_log.debug(e);
			}
		}
		
		return results;

	}
}
