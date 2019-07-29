package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.adminconfig.model.AdminConfig;
import org.opencps.adminconfig.service.AdminConfigLocalServiceUtil;
import org.opencps.api.controller.AdminConfigManagement;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import backend.admin.config.whiteboard.AdminEndpoind;
import backend.admin.config.whiteboard.BundleLoader;
import backend.auth.api.exception.BusinessExceptionImpl;

public class AdminConfigManagementImpl implements AdminConfigManagement {
	private static String convertDateToString(Date date) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(_TIMESTAMP);

		if (Validator.isNull(date) || Validator.isNull(_TIMESTAMP)) {
			return StringPool.BLANK;
		}

		dateFormat.setTimeZone(TimeZoneUtil.getTimeZone("Asia/Ho_Chi_Minh"));

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return dateFormat.format(calendar.getTime());
	}

	public static final String _TIMESTAMP = "dd/MM/yyyy HH:mm";
	private static final Log _log = LogFactoryUtil.getLog(AdminConfigManagementImpl.class);

	private static final String TYPE = "type";
	private static final String CMD = "cmd";
	private static final String GET = "get";
	private static final String DELETE = "delete";
	private static final String ADMIN = "admin";
	private static final String API = "api";
	private static final String BUNDLE_NAME = "bundle_name";
	private static final String SERVICE_UTIL_NAME = "service_util_name";
	private static final String MODEL_NAME = "model_name";
	private static final String DATA = "data";
	private static final String ID = "id";
	private static final String STATUS = "status";

	private static final String CODE = "code";

	private static final String CONFIG = "config";

	private static final String FILTER = "filter";

	private static final String RESPONE = "respone";

	private static final String COMPARE = "compare";

	private static final String COUNTER = "counter";

	private static final String END = "end";

	private static final String START = "start";

	@Override
	public Response onMessage(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User u,
			ServiceContext serviceContext, String text) {
		JSONObject messageData = JSONFactoryUtil.createJSONObject();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.debug("SOCKET WEB: " + groupId);
		String portalURL = PortalUtil.getPortalURL(request);
		
		try {
			JSONObject message = JSONFactoryUtil.createJSONObject(text);
			_log.debug("SOCKET MESSAGE: " + message.toJSONString());
			try {
				
				if (message.getString(TYPE).equals(ADMIN)) {
					
					String code = message.getString(CODE);
	
					AdminConfig adminConfig = AdminConfigLocalServiceUtil.fetchByCode(code);
	
					String bunderStr;
					String modelStr;
					String serviceUtilStr;
	
					if (Validator.isNull(adminConfig)) {
						bunderStr = message.getString(BUNDLE_NAME);
						modelStr = message.getString(MODEL_NAME);
						serviceUtilStr = message.getString(SERVICE_UTIL_NAME);
					} else {
						bunderStr = adminConfig.getBundleName();
						modelStr = adminConfig.getModelName();
						serviceUtilStr = adminConfig.getServiceUtilName();
					}
	
					BundleLoader bundleLoader = new BundleLoader(bunderStr != null ? bunderStr : StringPool.BLANK);
					Class<?> model = bundleLoader.getClassLoader().loadClass(modelStr != null ? modelStr : StringPool.BLANK);
	
					Method method = null;
	
					int lengColumns = 0;
					
					if (message.getString(CMD).equals(GET)) {
	
						method = bundleLoader.getClassLoader().loadClass(serviceUtilStr != null ? serviceUtilStr : StringPool.BLANK).getMethod("dynamicQuery");
	
						DynamicQuery dynamicQuery = (DynamicQuery) method.invoke(model);
	
						if (Validator.isNotNull(adminConfig) && !"detail".equalsIgnoreCase(message.getString("responeType"))) {
	
							String columns = adminConfig.getColumns();
	
							JSONArray arraysColumn = JSONFactoryUtil.createJSONArray(columns);
							
							if (arraysColumn.length() > 0) {
								
								lengColumns = arraysColumn.length();
								
								ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
	
								for (int i = 0; i < arraysColumn.length(); i++) {
	
									JSONObject column = arraysColumn.getJSONObject(i);
									
									projectionList.add(ProjectionFactoryUtil.property(column.getString("column")));
	
								}
								
								if ("listTableMenu".equalsIgnoreCase(message.getString(RESPONE))) {
									projectionList.add(ProjectionFactoryUtil.property("publicManager"));
								}
	
								dynamicQuery.setProjection(projectionList);
	
							}
	
						} else if ("menu".equalsIgnoreCase(message.getString("responeType"))) {
							ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
	
							projectionList.add(ProjectionFactoryUtil.property("code"));
							projectionList.add(ProjectionFactoryUtil.property("name"));
	
							dynamicQuery.setProjection(projectionList);
						}
	
						if (Validator.isNotNull(message.getJSONArray(FILTER))
								&& message.getJSONArray(FILTER).length() > 0) {
	
							for (int i = 0; i < message.getJSONArray(FILTER).length(); i++) {
	
								JSONObject filter = message.getJSONArray(FILTER).getJSONObject(i);
	
								if (Validator.isNotNull(filter.getString("value_filter"))
										&& filter.getString("value_filter").length() > 0) {
	
									if ("=".equals(filter.getString(COMPARE))) {
										if ("number".equalsIgnoreCase(filter.getString("type")) || "autocomplete".equalsIgnoreCase(filter.getString("type"))) {
											if (filter.getBoolean("int")) {
												dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
														.eq(filter.getInt("value_filter")));
											} else {
												dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
														.eq(filter.getLong("value_filter")));
											}
											
										} else if ("checkbox".equalsIgnoreCase(filter.getString("type"))) {
											if ("int".equalsIgnoreCase(filter.getString("data_type"))) {
												dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
														.eq(filter.getBoolean("value_filter") ? 1 : 0));
											} else {
												dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
														.eq(filter.getBoolean("value_filter")));
											}
										} else {
											dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
													.eq(filter.getString("value_filter")));
										}
									} else if ("like".equalsIgnoreCase(filter.getString(COMPARE))) {
										dynamicQuery.add(
												PropertyFactoryUtil.forName(filter.getString("key")).like(StringPool.PERCENT
														+ filter.getString("value_filter") + StringPool.PERCENT));
									} else if ("lt".equalsIgnoreCase(filter.getString(COMPARE))) {
										dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
												.lt(filter.getLong("value_filter")));
									} else if ("le".equalsIgnoreCase(filter.getString(COMPARE))) {
										dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
												.le(filter.getLong("value_filter")));
									} else if ("gt".equalsIgnoreCase(filter.getString(COMPARE))) {
										dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
												.gt(filter.getLong("value_filter")));
									} else if ("ge".equalsIgnoreCase(filter.getString(COMPARE))) {
										dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
												.ge(filter.getLong("value_filter")));
									}
	
								}
	
							}
	
						}
	
						if (groupId > 0 && adminConfig.getGroupFilter()) {
							Disjunction disjunction = RestrictionsFactoryUtil.disjunction();
							if (Validator.isNotNull(code)
									&& ("opencps_workingunit".equalsIgnoreCase(code)) || "opencps_applicant".equalsIgnoreCase(code)) {
								disjunction.add(RestrictionsFactoryUtil.eq("groupId", groupId));
							} else {
								disjunction.add(RestrictionsFactoryUtil.eq("groupId", 0l));
								disjunction.add(RestrictionsFactoryUtil.eq("groupId", groupId));
							}
							dynamicQuery.add(disjunction);
						}
	
						method = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod("dynamicQuery",
								DynamicQuery.class, int.class, int.class);
	
						messageData.put(STATUS, HttpStatus.OK);
	
						JSONObject headersObj = JSONFactoryUtil.createJSONObject(adminConfig.getHeadersName());
	
	//					System.out.println("code: " + code.equals("opencps_employee"));
						_log.debug("code: " + "opencps_employee".equalsIgnoreCase(code));
						
						if (message.getBoolean(CONFIG)) {
	
							JSONObject config = JSONFactoryUtil.createJSONObject();
							config.put("code", adminConfig.getCode());
							config.put("name", adminConfig.getName());
							config.put("headersName", headersObj.getJSONArray("headers"));
							config.put("columns", adminConfig.getColumns());
							config.put("detailColumns", adminConfig.getDetailColumns());
							config.put("extForm", adminConfig.getExtForm());
							config.put("dependency_title", headersObj.get("dependency_title"));
							config.put("dependency_link", headersObj.get("dependency_link"));
	
							messageData.put(message.getString(RESPONE), config);
	
						} else if (message.getBoolean(COUNTER)) {
	
							Method methodCounter = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod("dynamicQueryCount",
									DynamicQuery.class);
							
							messageData.put(message.getString(RESPONE), methodCounter.invoke(model, dynamicQuery));
	
						} else {
	
							int start = Validator.isNotNull(message.getString(START)) ? message.getInt(START) : 0;
							int end = Validator.isNotNull(message.getString(END)) ? message.getInt(END) : 1;
							
	//						System.out.println("code: " + code.equals("opencps_employee"));
							_log.debug("code: " + "opencps_employee".equalsIgnoreCase(code));
							_log.debug("lengColumns: " + lengColumns);
							if ("opencps_employee".equalsIgnoreCase(code)) {
								
								List<Object[]> employees = (List<Object[]>) method.invoke(model, dynamicQuery, start, end);
	
								_log.debug("employees: " + employees);
								if (lengColumns > 0) {
									for (Object[] obj: employees) {
	
										_log.debug("obj[lengColumns - 1]: " + obj[lengColumns - 1]);
										if (Validator.isNotNull(obj[lengColumns - 1])) {
											long userIdMapping = (long) obj[lengColumns - 1];
											User user = UserLocalServiceUtil.fetchUser(userIdMapping);
											if (user != null && user.getLoginDate() != null) {
												obj[lengColumns - 1] = convertDateToString(user.getLoginDate());												
											}
										}
										
									}
								}
	//							System.out.println("employees.employees()" + employees);
								messageData.put(message.getString(RESPONE), employees);
								
							} else {
								messageData.put(message.getString(RESPONE), method.invoke(model, dynamicQuery, start, end));
							}
							
						}
	
						messageData.put("title", headersObj.getString("title"));
	
					} else {
	
						if (message.getString(CMD).equals(DELETE)) {
	
							method = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod("adminProcessDelete",
									Long.class);
	
							messageData.put(message.getString(RESPONE), method.invoke(model, message.getLong(ID)));
							messageData.put(STATUS, HttpStatus.OK);
	
						} else {
	
							method = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod("adminProcessData",
									JSONObject.class);
	
							JSONObject postData = message.getJSONObject(DATA);
							
							postData.put("groupId", groupId);
							postData.put("companyId", company.getCompanyId());
							postData.put("userId", u.getUserId());
							postData.put("userName", u.getFullName());
							
							messageData.put(message.getString(RESPONE), method.invoke(model, message.getJSONObject(DATA)));
	
							messageData.put(STATUS, HttpStatus.OK);
	
						}
	
					}
				} else if (message.getString(TYPE).equals(API)) {

					RestTemplate restTemplate = new RestTemplate();

					restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

					org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

					headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

					JSONObject headerObject = message.getJSONObject("headers");

					JSONArray keys = headerObject.names();

					for (int i = 0; i < keys.length(); ++i) {

						String key = keys.getString(i);
						String value = headerObject.getString(key);

						headers.set(key, value);

					}
					headers.set("localaccess", headerObject.getString("Token"));
					headers.set("userid", headerObject.getString("USER_ID"));
					
					HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
					
//					HttpEntity<String> response = restTemplate.exchange("http://" + portalURL + message.getString("api"),
//							HttpMethod.GET, entity, String.class);
					//BNG
					HttpEntity<String> response = restTemplate.exchange(portalURL + message.getString("api"),
							HttpMethod.GET, entity, String.class);

					String resultString = response.getBody();

					JSONArray responeData = JSONFactoryUtil.createJSONArray();
					try {
						responeData = JSONFactoryUtil.createJSONObject(resultString).getJSONArray("data");
					} catch (Exception e) {
						_log.debug(e);
						responeData = JSONFactoryUtil.createJSONArray(resultString);
					}
					messageData.put(message.getString(RESPONE), responeData);

					messageData.put(STATUS, HttpStatus.OK);
				}
	
				messageData.put(RESPONE, message.getString(RESPONE));
				messageData.put(CMD, message.getString(CMD));
	
			} catch (Exception e) {
				_log.debug(e);
				messageData.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
				messageData.put(RESPONE, message.getString(RESPONE));
				messageData.put(CMD, message.getString(CMD));
			}
	
			messageData.put(TYPE, message.getString(TYPE));
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(messageData.toJSONString()).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}		
	}
	
}
