package org.opencps.socket.whiteboard;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"org.osgi.http.websocket.endpoint.path=/o/socket-gate"
		},
		service = Endpoint.class
	)
public class ExampleEndpoind extends Endpoint {

	private static final Log LOG = LogFactoryUtil.getLog(ExampleEndpoind.class);
	
	/**
	 * @Override onOpen websocket connect
	 * 
	 * @param Session
	 * @param EndpointConfig
	 */
	@Override
	public void onOpen(Session session, EndpointConfig config) {
		
		// create messagehandeler to send message to client
		MessageHandler handler = new MessageHandler.Whole<String>() {

			@Override
			public void onMessage(String text) {
				onMessageHandler(text, session);
			}

		};
		
		session.addMessageHandler(handler);

	}
	
	private void onMessageHandler(String text, Session session) {
		try {
			// process data recive from client
			BundleLoader bundleLoader = new BundleLoader("etet.service");
			Class<?> model = bundleLoader.getClassLoader().loadClass("etet.service.FooFFLocalServiceUtil");

			Method method = bundleLoader.getClassLoader().loadClass(
					"etet.service.FooFFLocalServiceUtil").getMethod("dynamicQuery");

			DynamicQuery dynamicQuery = (DynamicQuery) method.invoke(model);
			ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
			projectionList.add(ProjectionFactoryUtil.property("field1"));
			projectionList.add(ProjectionFactoryUtil.property("field2"));
			dynamicQuery.setProjection(projectionList);
			
			try {
				method = bundleLoader.getClassLoader().loadClass(
						"etet.service.FooFFLocalServiceUtil").getMethod("dynamicQuery", DynamicQuery.class);
				
//				List<Object[]> userList = (List<Object[]>) method.invoke(model, dynamicQuery);
				broadcast(JSONFactoryUtil.looseSerialize(method.invoke(model, dynamicQuery)), session);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
