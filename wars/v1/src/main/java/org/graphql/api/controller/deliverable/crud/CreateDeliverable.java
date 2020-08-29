package org.graphql.api.controller.deliverable.crud;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

import org.graphql.api.controller.utils.WebKeys;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.util.DeliverableNumberGenerator;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.kernel.util.DateTimeUtil;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.Date;

/**
 * Created binhth
 */
@Component
public class CreateDeliverable implements DataFetcher<Deliverable> {
	
	private static final Log _log = LogFactoryUtil.getLog(CreateDeliverable.class);

	@Autowired
	private final HttpServletRequest request;

	@Autowired
	public CreateDeliverable(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public Deliverable get(DataFetchingEnvironment dataFetchingEnvironment) {

		Deliverable result = null;
		
		String input = dataFetchingEnvironment.getArgument(WebKeys.BODY);
		_log.info("input body: "+input);

		long groupId = 0;

		if (Validator.isNotNull(request.getHeader(WebKeys.GROUPID))) {
			groupId = Long.valueOf(request.getHeader(WebKeys.GROUPID));
		}
		long userId = 0;
		if(Validator.isNotNull(request.getAttribute(WebKeys.USER_ID))){
			userId = Long.valueOf(request.getAttribute(WebKeys.USER_ID).toString());
		}

		//System.out.println("CreateDeliverable.get(input)" + input);
		
		JSONObject inputObject;
		try {
			inputObject = JSONFactoryUtil.createJSONObject(input);
			
			inputObject.put(WebKeys.GROUPID, groupId);
			inputObject.put(WebKeys.USERID, request.getAttribute(WebKeys.USER_ID));
			inputObject.put(WebKeys.COMPANYID, request.getAttribute(WebKeys.COMPANY_ID));
			JSONObject formData = inputObject.getJSONObject("formData");
			
			if (!inputObject.has("deliverableCode")) {
				DeliverableType delType = DeliverableTypeLocalServiceUtil.getByCode(groupId, inputObject.getString("deliverableType"));
				String issueDate = formData.getString("issueDate");
				Date sysDate = new Date();
				String deliverableCode = "";
				if(Validator.isNotNull(issueDate) ) {
					Date issue = APIDateTimeUtils
							.convertStringToDate(issueDate, APIDateTimeUtils._NORMAL_DATE);
					if(Validator.isNotNull(issue)){
						deliverableCode = DeliverableNumberGenerator.generateDeliverableNumber(
								groupId, delType.getCodePattern(), issueDate);
					}
				}else{
					String dateNow = APIDateTimeUtils
							.convertDateToString(sysDate, APIDateTimeUtils._NORMAL_PARTTERN);
					deliverableCode = DeliverableNumberGenerator.generateDeliverableNumber(
							groupId, delType.getCodePattern(), dateNow);
				}
				inputObject.put("deliverableCode", deliverableCode);
			}
			Employee employee = null;
			if(Validator.isNotNull(groupId) && Validator.isNotNull(userId)) {
				 employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
				 inputObject.put("userName", employee.getFullName());
			}
			if(!inputObject.has("govAgencyCode")){
				if(employee != null){
					String govAgencyCode [] = employee.getScope().split(StringPool.COMMA);
					inputObject.put("govAgencyCode", govAgencyCode[0]);
				}
			}

			_log.info("inputObject: "+JSONFactoryUtil.looseSerialize(inputObject));
			result = DeliverableLocalServiceUtil.adminProcessData(inputObject);
			
		} catch (JSONException e) {
			_log.debug(e);
		}
		
		return result;

	}
}
