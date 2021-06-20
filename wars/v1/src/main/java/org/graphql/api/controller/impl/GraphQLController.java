package org.graphql.api.controller.impl;

import com.google.gson.Gson;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.graphql.api.controller.utils.WebKeys;
import org.opencps.adminconfig.model.ReportRole;
import org.opencps.adminconfig.service.ReportRoleLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;

/**
 * Created by binhth
 */
@RestController
public class GraphQLController {

	private static Log _log = LogFactoryUtil.getLog(GraphQLController.class);
	public GraphQLController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	DeliverableService deliverableService;
	@Autowired
	DataItemService dataItemService;
	@Autowired
	AdminConfigService adminConfigService;

	public GraphQLController(DeliverableService deliverableService, DataItemService dataItemService,
			AdminConfigService adminConfigService) {
		this.deliverableService = deliverableService;
		this.dataItemService = dataItemService;
		this.adminConfigService = adminConfigService;
	}

	@RequestMapping(value = "/deliverable", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> deliverable(@RequestBody String query) {
		ExecutionResult result = deliverableService.getGraphQL().execute(query);

		Gson gson = new Gson();

		String json = gson.toJson(result.getData(), LinkedHashMap.class);

		return new ResponseEntity<>(json, HttpStatus.OK);

	}

	@RequestMapping(value = "/dataitem", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> dataitem(@RequestBody String query) {

		ExecutionResult result = dataItemService.getGraphQL().execute(query);

		Gson gson = new Gson();

		String json = gson.toJson(result.getData(), LinkedHashMap.class);

		return new ResponseEntity<>(json, HttpStatus.OK);

	}

	@RequestMapping(value = "/adminconfig", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> adminconfig(HttpServletRequest request, HttpServletResponse response, @RequestBody String query) {

		ExecutionResult result = adminConfigService.getGraphQL().execute(query);
		Gson gson = new Gson();

		String json = gson.toJson(result.getData(), LinkedHashMap.class);
		if (ReportRoleLocalServiceUtil.countAll() > 0) {
			long userId = Long.valueOf(request.getAttribute(WebKeys.USER_ID).toString());

			User user = UserLocalServiceUtil.fetchUser(userId);

			List<Role> roles = user.getRoles();
			long[] roleIds = new long[roles.size()];
			int count = 0;
			for (Role r : roles) {
				roleIds[count++] = r.getRoleId();
			}
			List<ReportRole> lstRrs = ReportRoleLocalServiceUtil.findByRIDS(roleIds);
			List<Long> lstDrs = new ArrayList<Long>();
			for (ReportRole r : lstRrs) {
				lstDrs.add(r.getDynamicReportId());
			}
			try {
				JSONObject dynamicReportObj = JSONFactoryUtil.createJSONObject(json);
				if (dynamicReportObj.has(WebKeys.GET_DYNAMIC_REPORTS)) {
					JSONArray oldDrs = dynamicReportObj.getJSONArray(WebKeys.GET_DYNAMIC_REPORTS);
					JSONArray newDrs = JSONFactoryUtil.createJSONArray();
					for (int i = 0; i < oldDrs.length(); i++) {
						JSONObject obj = oldDrs.getJSONObject(i);
						if (obj.has(WebKeys.DYNAMIC_REPORT_ID) && lstDrs.contains(obj.getLong(WebKeys.DYNAMIC_REPORT_ID))) {
							newDrs.put(obj);
						}
					}
					dynamicReportObj.put(WebKeys.GET_DYNAMIC_REPORTS, newDrs);
					json = dynamicReportObj.toJSONString();
				}
			} catch (JSONException e) {
				_log.debug(e);
			}			
		}
		
		return new ResponseEntity<>(json, HttpStatus.OK);

	}

}
