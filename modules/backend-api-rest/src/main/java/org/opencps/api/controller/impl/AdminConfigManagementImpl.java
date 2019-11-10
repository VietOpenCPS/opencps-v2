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
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.adminconfig.model.AdminConfig;
import org.opencps.adminconfig.service.AdminConfigLocalServiceUtil;
import org.opencps.api.controller.AdminConfigManagement;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.springframework.http.HttpStatus;

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
	private static final String ADMIN = ReadFilePropertiesUtils.get(ConstantUtils.USER_ADMIN);
	private static final String API = "api";
	private static final String BUNDLE_NAME = "bundle_name";
	private static final String SERVICE_UTIL_NAME = "service_util_name";
	private static final String MODEL_NAME = "model_name";
	private static final String DATA = ConstantUtils.DATA;
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
	private static final String COMPANY_ID = "companyId";
	private static final String DYNAMIC_QUERY = "dynamicQuery";
	private static final String RESPONSE_TYPE = "responeType";
	private static final String DETAIL = "detail";
	private static final String TITLE = "title";
	private static final String COLUMN = "column";
	private static final String LIST_TABLE_MENU = "listTableMenu";
	private static final String PUBLIC_MANAGER = "publicManager";
	private static final String MENU = "menu";
	private static final String NAME = "name";
	private static final String VALUE_FILTER = "value_filter";
	private static final String NUMBER = "number";
	private static final String AUTO_COMPLETE = "autocomplete";
	private static final String TYPE_INT = "int";
	private static final String DATA_TYPE = "data_type";
	private static final String KEY = ConstantUtils.VALUE_KEY;
	private static final String CHECK_BOX = "checkbox";
	private static final String QUERY_LIKE = "like";
	private static final String COMPARE_LT = "lt";
	private static final String COMPARE_LE = "le";
	private static final String COMPARE_GT = "gt";
	private static final String COMPARE_GE = "ge";
	private static final String LOCAL_ACCESSS = "localaccess";
	private static final String P_AUTH = "Token";
	private static final String USER_REQUEST_ID = "userid";
	private static final String USER_ID = "USER_ID";
	private static final String CLASSNAME_WORKING_UNIT = "opencps_workingunit";
	private static final String CLASSNAME_APPLICANT = "opencps_applicant";
	private static final String HEARDER_NAME = "headersName";
	private static final String DETAIL_COLUMN = "detailColumns";
	private static final String EXT_FORM = "extForm";
	private static final String DEPENDENCY_TITLE = "dependency_title";
	private static final String DEPENDENCY_LINK = "dependency_link";
	private static final String DYNAMIC_COUNT = "dynamicQueryCount";
	private static final String PROCESS_DELETE = "adminProcessDelete";
	private static final String PROCESS_DATA = "adminProcessData";
	private static final String HEADERS = "headers";
	private static final String CLASSNAME_EMPLOYEE = "opencps_employee";
	private static final String ACCEPT = "Accept";
	private static final String CONTENT_TYPE = ConstantUtils.CONTENT_TYPE;
	private static final String APP_JSON = "application/json";

	@SuppressWarnings("unchecked")
	@Override
	public Response onMessage(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User u,
			ServiceContext serviceContext, String text) {
		JSONObject messageData = JSONFactoryUtil.createJSONObject();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		//String portalURL = PortalUtil.getPortalURL(request);
		
		try {
			JSONObject message = JSONFactoryUtil.createJSONObject(text);
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
	
						method = bundleLoader.getClassLoader().loadClass(serviceUtilStr != null ? serviceUtilStr : StringPool.BLANK).getMethod(DYNAMIC_QUERY);
	
						DynamicQuery dynamicQuery = (DynamicQuery) method.invoke(model);
	
						if (Validator.isNotNull(adminConfig) && !DETAIL.equals(message.getString(RESPONSE_TYPE))) {
							String columns = adminConfig.getColumns();
							JSONArray arraysColumn = JSONFactoryUtil.createJSONArray(columns);
							if (arraysColumn.length() > 0) {
								
								lengColumns = arraysColumn.length();
								
								ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

								for (int i = 0; i < arraysColumn.length(); i++) {

									JSONObject column = arraysColumn.getJSONObject(i);
									
									projectionList.add(ProjectionFactoryUtil.property(column.getString(COLUMN)));

								}
								
								if (LIST_TABLE_MENU.equals(message.getString(RESPONE))) {
									projectionList.add(ProjectionFactoryUtil.property(PUBLIC_MANAGER));
								}

								dynamicQuery.setProjection(projectionList);

							}

						} else if (MENU.equals(message.getString(RESPONSE_TYPE))) {
							ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

							projectionList.add(ProjectionFactoryUtil.property(CODE));
							projectionList.add(ProjectionFactoryUtil.property(NAME));

							dynamicQuery.setProjection(projectionList);
						}
	
						if (Validator.isNotNull(message.getJSONArray(FILTER))
								&& message.getJSONArray(FILTER).length() > 0) {

							for (int i = 0; i < message.getJSONArray(FILTER).length(); i++) {

								JSONObject filter = message.getJSONArray(FILTER).getJSONObject(i);

								if (Validator.isNotNull(filter.getString(VALUE_FILTER))
										&& filter.getString(VALUE_FILTER).length() > 0) {

									if (StringPool.EQUAL.equals(filter.getString(COMPARE))) {
										if (NUMBER.equals(filter.getString(TYPE)) || AUTO_COMPLETE.equals(filter.getString(TYPE))) {
											if (filter.getBoolean(TYPE_INT)) {
												dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
														.eq(filter.getInt(VALUE_FILTER)));
											} else {
												dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
														.eq(filter.getLong(VALUE_FILTER)));
											}
											
										} else if (CHECK_BOX.equals(filter.getString(TYPE))) {
											if (TYPE_INT.equals(filter.getString(DATA_TYPE))) {
												dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
														.eq(filter.getBoolean(VALUE_FILTER) ? 1 : 0));
											} else {
												dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
														.eq(filter.getBoolean(VALUE_FILTER)));
											}
										} else {
											dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
													.eq(filter.getString(VALUE_FILTER)));
										}
									} else if (QUERY_LIKE.equals(filter.getString(COMPARE))) {
										dynamicQuery.add(
												PropertyFactoryUtil.forName(filter.getString(KEY)).like(StringPool.PERCENT
														+ filter.getString(VALUE_FILTER) + StringPool.PERCENT));
									} else if (COMPARE_LT.equals(filter.getString(COMPARE))) {
										dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
												.lt(filter.getLong(VALUE_FILTER)));
									} else if (COMPARE_LE.equals(filter.getString(COMPARE))) {
										dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
												.le(filter.getLong(VALUE_FILTER)));
									} else if (COMPARE_GT.equals(filter.getString(COMPARE))) {
										dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
												.gt(filter.getLong(VALUE_FILTER)));
									} else if (COMPARE_GE.equals(filter.getString(COMPARE))) {
										dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
												.ge(filter.getLong(VALUE_FILTER)));
									}

								}

							}

						}
	
						if (groupId > 0 && adminConfig.getGroupFilter()) {
							Disjunction disjunction = RestrictionsFactoryUtil.disjunction();
							if (Validator.isNotNull(code)
									&& (CLASSNAME_WORKING_UNIT.equals(code) || CLASSNAME_APPLICANT.equals(code))) {
								disjunction.add(RestrictionsFactoryUtil.eq(Field.GROUP_ID, groupId));
							} else {
								disjunction.add(RestrictionsFactoryUtil.eq(Field.GROUP_ID, 0l));
								disjunction.add(RestrictionsFactoryUtil.eq(Field.GROUP_ID, groupId));
							}
							dynamicQuery.add(disjunction);
						}
	
						method = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod(DYNAMIC_QUERY,
								DynamicQuery.class, int.class, int.class);
	
						messageData.put(STATUS, HttpStatus.OK);
	
						JSONObject headersObj = JSONFactoryUtil.createJSONObject(adminConfig.getHeadersName());
	
						
						if (message.getBoolean(CONFIG)) {

							JSONObject config = JSONFactoryUtil.createJSONObject();
							config.put(CODE, adminConfig.getCode());
							config.put(NAME, adminConfig.getName());
							config.put(HEARDER_NAME, headersObj.getJSONArray(HEADERS));
							config.put(COLUMN, adminConfig.getColumns());
							config.put(DETAIL_COLUMN, adminConfig.getDetailColumns());
							config.put(EXT_FORM, adminConfig.getExtForm());
							config.put(DEPENDENCY_TITLE, headersObj.get(DEPENDENCY_TITLE));
							config.put(DEPENDENCY_LINK, headersObj.get(DEPENDENCY_LINK));

							messageData.put(message.getString(RESPONE), config);

						} else if (message.getBoolean(COUNTER)) {

							Method methodCounter = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod(DYNAMIC_COUNT,
									DynamicQuery.class);
							
							messageData.put(message.getString(RESPONE), methodCounter.invoke(model, dynamicQuery));

						} else {

							int start = Validator.isNotNull(message.getString(START)) ? message.getInt(START) : 0;
							int end = Validator.isNotNull(message.getString(END)) ? message.getInt(END) : 1;
							if (CLASSNAME_EMPLOYEE.equals(code)) {
								
								List<Object[]> employees = (List<Object[]>) method.invoke(model, dynamicQuery, start, end);

								if (lengColumns > 0) {
									for (Object[] obj: employees) {
										if (Validator.isNotNull(obj[lengColumns - 1])) {
											long userIdMapping = (long) obj[lengColumns - 1];
											User user = UserLocalServiceUtil.fetchUser(userIdMapping);
											obj[lengColumns - 1] = convertDateToString(user.getLoginDate());
										}
										
									}
								}
//								System.out.println("employees.employees()" + employees);
								messageData.put(message.getString(RESPONE), employees);
								
							} else {
								messageData.put(message.getString(RESPONE), method.invoke(model, dynamicQuery, start, end));
							}
							
						}
	
						messageData.put(TITLE, headersObj.getString(TITLE));
	
					} else {
	
						if (message.getString(CMD).equals(DELETE)) {
	
							method = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod(PROCESS_DELETE,
									Long.class);
	
							messageData.put(message.getString(RESPONE), method.invoke(model, message.getLong(ID)));
							messageData.put(STATUS, HttpStatus.OK);
	
						} else {
	
							method = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod(PROCESS_DATA,
									JSONObject.class);
	
							JSONObject postData = message.getJSONObject(DATA);
							
							postData.put(Field.GROUP_ID, groupId);
							postData.put(COMPANY_ID, company.getCompanyId());
							postData.put(Field.USER_ID, u.getUserId());
							postData.put(Field.USER_NAME, u.getFullName());
							
							messageData.put(message.getString(RESPONE), method.invoke(model, message.getJSONObject(DATA)));

							messageData.put(STATUS, HttpStatus.OK);
	
						}
	
					}
				} else if (message.getString(TYPE).equals(API)) {

				    String apiUrl = StringPool.BLANK;
				    
				    StringBuilder sb = new StringBuilder();
				    try
				    {
				        URL urlVal = null;
						apiUrl = OpenCPSConfigUtil.getAdminProxyUrl() + message.getString(API);
						urlVal = new URL(apiUrl);
						
						JSONObject headerObject = message.getJSONObject(HEADERS);
						HttpURLConnection conn = (HttpURLConnection) urlVal.openConnection();

						JSONArray keys = headerObject.names();

						for (int i = 0; i < keys.length(); ++i) {

							String key = keys.getString(i);
							String value = headerObject.getString(key);

							conn.setRequestProperty(key, value);

						}
				
						conn.setRequestProperty(LOCAL_ACCESSS, headerObject.getString(P_AUTH));
						conn.setRequestProperty(USER_REQUEST_ID, headerObject.getString(USER_ID));

				        conn.setRequestMethod(message.getString(CMD).toUpperCase());
				        conn.setRequestProperty(ACCEPT, APP_JSON);
				        conn.setRequestProperty(CONTENT_TYPE, APP_JSON);
				        
				        conn.setDoInput(true);
						conn.setDoOutput(true);
						conn.setConnectTimeout(OpenCPSConfigUtil.getRestConnectionTimeout());
						conn.setReadTimeout(OpenCPSConfigUtil.getRestReadTimeout());
						
				        BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				        			        
				        int cp;
				        while ((cp = brf.read()) != -1) {
				          sb.append((char) cp);
				        }
						JSONArray responeData = JSONFactoryUtil.createJSONArray();
						try {
							responeData = JSONFactoryUtil.createJSONObject(sb.toString()).getJSONArray(DATA);
						} catch (Exception e) {
							_log.debug(e);
							responeData = JSONFactoryUtil.createJSONArray(sb.toString());
						}
						messageData.put(message.getString(RESPONE), responeData);
						messageData.put(STATUS, HttpStatus.OK);
						
						conn.disconnect();
				    }
				    catch(IOException e)
				    {
				        _log.debug("Something went wrong while reading/writing in stream!!");
				        _log.debug(e);
				    }
					
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
