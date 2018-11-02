package org.opencps.socket.whiteboard;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.opencps.adminconfig.model.AdminConfig;
import org.opencps.adminconfig.service.AdminConfigLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Component(immediate = true, property = {
		"org.osgi.http.websocket.endpoint.path=/o/gate/socket/web" }, service = Endpoint.class)
public class ExampleEndpoind extends Endpoint {

	private static final Log _log = LogFactoryUtil.getLog(ExampleEndpoind.class);

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

	/**
	 * @Override onOpen websocket connect
	 * 
	 * @param Session
	 * @param EndpointConfig
	 */
	@Override
	public void onOpen(Session session, EndpointConfig config) {

		MessageHandler handler = new MessageHandler.Whole<String>() {

			@Override
			public void onMessage(String text) {
				try {
					onMessageHandler(text, session);
				} catch (JSONException e) {
					_log.error(e);
				}
			}

		};

		session.addMessageHandler(handler);

	}

	private void onMessageHandler(String text, Session session) throws JSONException {

		Map<String, String[]> parameters = HttpUtil.getParameterMap(session.getQueryString());
		
		// user parameters
		String groupIdStr = parameters.get("groupId")[0];
		String portalURL = parameters.get("portalURL")[0];

		JSONObject messageData = JSONFactoryUtil.createJSONObject();

		JSONObject message = JSONFactoryUtil.createJSONObject(text);

		try {
			
			if (message.getString(TYPE).equals(ADMIN)) {
				
				String code = message.getString(CODE);

				AdminConfig adminConfig = AdminConfigLocalServiceUtil.fetchByCode(code);

				String bunderStr = StringPool.BLANK;
				String modelStr = StringPool.BLANK;
				String serviceUtilStr = StringPool.BLANK;

				if (Validator.isNull(adminConfig)) {
					bunderStr = message.getString(BUNDLE_NAME);
					modelStr = message.getString(MODEL_NAME);
					serviceUtilStr = message.getString(SERVICE_UTIL_NAME);
				} else {
					bunderStr = adminConfig.getBundleName();
					modelStr = adminConfig.getModelName();
					serviceUtilStr = adminConfig.getServiceUtilName();
				}

				BundleLoader bundleLoader = new BundleLoader(bunderStr);
				Class<?> model = bundleLoader.getClassLoader().loadClass(modelStr);

				Method method = null;

				if (message.getString(CMD).equals(GET)) {

					method = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod("dynamicQuery");

					DynamicQuery dynamicQuery = (DynamicQuery) method.invoke(model);

					if (Validator.isNotNull(adminConfig) && !message.getString("responeType").equals("detail")) {

						String columns = adminConfig.getColumns();

						JSONArray arraysColumn = JSONFactoryUtil.createJSONArray(columns);

						if (arraysColumn.length() > 0) {

							ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

							for (int i = 0; i < arraysColumn.length(); i++) {

								JSONObject column = arraysColumn.getJSONObject(i);
								
								projectionList.add(ProjectionFactoryUtil.property(column.getString("column")));

							}

							dynamicQuery.setProjection(projectionList);

						}

					} else if (message.getString("responeType").equals("menu")) {
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

								if (filter.getString(COMPARE).equals("=")) {
									if (filter.getString("type").equals("number") || filter.getString("type").equals("autocomplete")) {
										if (filter.getBoolean("int")) {
											dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
													.eq(filter.getInt("value_filter")));
										} else {
											dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
													.eq(filter.getLong("value_filter")));
										}
										
									} else if (filter.getString("type").equals("checkbox")) {
										if (filter.getString("data_type").equals("int")) {
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
								} else if (filter.getString(COMPARE).equals("like")) {
									dynamicQuery.add(
											PropertyFactoryUtil.forName(filter.getString("key")).like(StringPool.PERCENT
													+ filter.getString("value_filter") + StringPool.PERCENT));
								} else if (filter.getString(COMPARE).equals("lt")) {
									dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
											.lt(filter.getLong("value_filter")));
								} else if (filter.getString(COMPARE).equals("le")) {
									dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
											.le(filter.getLong("value_filter")));
								} else if (filter.getString(COMPARE).equals("gt")) {
									dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
											.gt(filter.getLong("value_filter")));
								} else if (filter.getString(COMPARE).equals("ge")) {
									dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString("key"))
											.ge(filter.getLong("value_filter")));
								}

							}

						}

					}

					long groupId = Long.valueOf(groupIdStr);
					
					if (groupId > 0 && adminConfig.getGroupFilter()) {
						Disjunction disjunction = RestrictionsFactoryUtil.disjunction();
						disjunction.add(RestrictionsFactoryUtil.eq("groupId", 0l));
						disjunction.add(RestrictionsFactoryUtil.eq("groupId", groupId));
						dynamicQuery.add(disjunction);
					}

					method = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod("dynamicQuery",
							DynamicQuery.class, int.class, int.class);

					messageData.put(STATUS, HttpStatus.OK);

					JSONObject headersObj = JSONFactoryUtil.createJSONObject(adminConfig.getHeadersName());

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

						
						messageData.put(message.getString(RESPONE), method.invoke(model, dynamicQuery, start, end));
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

						messageData.put(message.getString(RESPONE), method.invoke(model, message.getJSONObject(DATA)));

						messageData.put(STATUS, HttpStatus.OK);

					}

				}

			} else if (message.getString(TYPE).equals(API)) {

				RestTemplate restTemplate = new RestTemplate();

				restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

				HttpHeaders headers = new HttpHeaders();

				headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

				JSONObject headerObject = message.getJSONObject("headers");

				JSONArray keys = headerObject.names();

				for (int i = 0; i < keys.length(); ++i) {

					String key = keys.getString(i);
					String value = headerObject.getString(key);

					headers.set(key, value);

				}
				
				HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
				
				HttpEntity<String> response = restTemplate.exchange("http://" + portalURL + message.getString("api"),
						HttpMethod.GET, entity, String.class);

				String resultString = response.getBody();

				JSONArray responeData = JSONFactoryUtil.createJSONArray();
				try {
					responeData = JSONFactoryUtil.createJSONObject(resultString).getJSONArray("data");
				} catch (Exception e) {
					responeData = JSONFactoryUtil.createJSONArray(resultString);
				}
				messageData.put(message.getString(RESPONE), responeData);

				messageData.put(STATUS, HttpStatus.OK);
			}

			messageData.put(RESPONE, message.getString(RESPONE));
			messageData.put(CMD, message.getString(CMD));

		} catch (Exception e) {
			e.printStackTrace();
			messageData.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
			messageData.put(RESPONE, message.getString(RESPONE));
		}

		messageData.put(TYPE, message.getString(TYPE));

		broadcast(messageData.toJSONString(), session);

	}

	/**
	 * Sends message to every opened session
	 * 
	 * @param message
	 * @param sessions
	 */
	private void broadcast(String message, Session session) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}
}
