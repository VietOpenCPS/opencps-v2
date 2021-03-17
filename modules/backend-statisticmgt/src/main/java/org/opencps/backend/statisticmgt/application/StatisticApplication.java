package org.opencps.backend.statisticmgt.application;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
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

import org.opencps.backend.statisticmgt.util.ActionUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author trungnt
 *
 */
@Component(immediate = true, property = { JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/secure/statistic/",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=OpenCPS.statistic" }, service = Application.class)
public class StatisticApplication extends Application {

	@Override
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/dossier/test")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response test(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext) {
		return Response.status(HttpURLConnection.HTTP_OK).entity("OK").build();
	}

	@POST
	@Path("/dossier/count")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response countDossier(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, 
			@QueryParam("fromDate") long fromDate,
			@QueryParam("toDate") long toDate, @QueryParam("originalities") String originalities,
			@QueryParam("domainCode") String domainCode, @QueryParam("dossierStatus") String dossierStatus,
			@QueryParam("type") int type, 
			@QueryParam("day") Integer day) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		JSONObject result = ActionUtil.getCountDossier(groupId, fromDate, toDate, originalities, domainCode, 
				dossierStatus, type, day);
	
		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}
	
	@POST
	@Path("/dossier/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response listDossier(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@Context HttpHeaders header, @Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, 
			@QueryParam("fromDate") long fromDate,
			@QueryParam("toDate") long toDate, @QueryParam("originalities") String originalities,
			@QueryParam("domainCode") String domainCode, @QueryParam("dossierStatus") String dossierStatus,
			@QueryParam("type") int type, 
			@QueryParam("day") Integer day,
			@QueryParam("start") Integer start,
			@QueryParam("end") Integer end) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		JSONObject result = ActionUtil.getListDossier(groupId, fromDate, toDate, originalities, domainCode, 
				dossierStatus, type, day, start, end);
	
		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}
}
