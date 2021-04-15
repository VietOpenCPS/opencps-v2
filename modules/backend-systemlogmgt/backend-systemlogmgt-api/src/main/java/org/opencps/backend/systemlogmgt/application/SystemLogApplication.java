package org.opencps.backend.systemlogmgt.application;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException;
import org.opencps.backend.systemlogmgt.service.SystemLogLocalServiceUtil;
import org.opencps.backend.systemlogmgt.util.ActionUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;


/**
 * @author trungnt
 *
 */
@Component(immediate = true, property = { JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/secure/systemlog/",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=OpenCPS.systemlog" }, service = Application.class)
public class SystemLogApplication extends Application {

	@Override
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}
	public final static ThreadLocal<String> threadIdContext = new ThreadLocal<>();
	
	@POST
	@Path("/test")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response test(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @QueryParam("typeList") int typeList) {
		threadIdContext.set(UUID.randomUUID().toString());
		JSONObject result = ActionUtil.getListSystemLog("1301,1302", "5", "delSystemLog", null,117, "delSystemLog", 113, "actionDelete", "Connect to Delete Action", "List Type 1", "339f9293-d7df-4e88-b6ef-fbfbc3120f53", typeList);
		return Response.status(HttpURLConnection.HTTP_OK).entity("OK").build();
	}
	
	@POST
	@Path("/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response listSystemLog(
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@QueryParam("logId") String logId, @QueryParam("groupId") String groupId, 
			@QueryParam("moduleName") String moduleName, @QueryParam("createDate") Long createDate, 
			@QueryParam("preLine") Integer preLine, @QueryParam("preMethod") String preMethod, 
			@QueryParam("line") Integer line, @QueryParam("method") String method, 
			@QueryParam("message") String message, @QueryParam("type") String type, 
			@QueryParam("threadId") String threadId, @QueryParam("typeList") int typeList) {
		
		threadIdContext.set(UUID.randomUUID().toString());
		JSONObject result = ActionUtil.getListSystemLog(logId, groupId, moduleName, createDate, preLine, preMethod, line, method, message, type, threadId, typeList);
		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}
	@POST
	@Path("/find")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findSystemLog(
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@QueryParam("fromDate") Long fromDate, @QueryParam("toDate") Long toDate, @QueryParam("preMethod") String preMethods, 
			@QueryParam("method") String methods, @QueryParam("type") String types, @QueryParam("threadId") String threadId) {
		
		threadIdContext.set(UUID.randomUUID().toString());
		JSONObject result = ActionUtil.findSystemLog(fromDate, toDate, preMethods, methods, types, threadId);
		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}
	
	@POST
	@Path("/diagram")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response actionProcessDiagram(
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@QueryParam("threadId") String threadId) {
		
		threadIdContext.set(UUID.randomUUID().toString());
		JSONObject result = ActionUtil.execDiagram(threadId);
		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}
	
	@POST
	@Path("/action")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addSystemLog(
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@QueryParam("logId") Long logId, @QueryParam("groupId") Long groupId, 
			@QueryParam("moduleName") String moduleName, @QueryParam("createDate") String createDate, 
			@QueryParam("preLine") Integer preLine, @QueryParam("preMethod") String preMethod, 
			@QueryParam("line") Integer line, @QueryParam("method") String method, 
			@QueryParam("message") String message, @QueryParam("type") String type, 
			@QueryParam("threadId") String threadId) {

		JSONObject result = ActionUtil.actionCreateNew(groupId, moduleName, preLine, preMethod, line, method, message, type, threadId);
		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}
	
	@DELETE
	@Path("/action")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response delSystemLog(
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@QueryParam("logId") Long logId) {
		
		threadIdContext.set(UUID.randomUUID().toString());
		JSONObject result = ActionUtil.actionDelete(logId);
		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}

	
	
}
