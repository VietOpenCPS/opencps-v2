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
import javax.ws.rs.PathParam;
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
		return Response.status(HttpURLConnection.HTTP_OK).entity("OK").build();
	}
	
	@GET
	@Path("/search/{threadId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getByThreadId(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @PathParam(value = "threadId") String threadId) throws JSONException {
		JSONObject result = ActionUtil.execDiagram(threadId, null);
		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}
	
	
	@POST
	@Path("/search")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response searchSystemLog(
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company,
			@Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@QueryParam("logId") Long logId, @QueryParam("groupId") Long groupId, 
			@QueryParam("moduleName") String moduleName,
			@QueryParam("method") String method, 
			@QueryParam("threadId") String threadId, @QueryParam("fromDate") Long fromDate, @QueryParam("toDate") Long toDate) throws JSONException {
		
		JSONObject result = ActionUtil.searchSystemLog(logId, groupId, moduleName, method, threadId, fromDate, toDate);
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
			@QueryParam("threadId") String threadId, @QueryParam("logId") Long logId) throws JSONException {
		
		JSONObject result = ActionUtil.execDiagram(threadId, logId);
		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}
}
