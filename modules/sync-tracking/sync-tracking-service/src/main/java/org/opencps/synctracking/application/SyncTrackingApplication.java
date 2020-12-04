package org.opencps.synctracking.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.synctracking.action.IntegrationOutsideApi;
import org.opencps.synctracking.action.SyncTrackingAction;
import org.opencps.synctracking.action.TransformAction;
import org.opencps.synctracking.action.impl.IntegrationOutsideApiImpl;
import org.opencps.synctracking.action.impl.SyncTrackingActionImpl;
import org.opencps.synctracking.action.impl.TransformActionImpl;
import org.opencps.synctracking.model.DtoResponse;
import org.opencps.synctracking.model.SyncTrackingQuery;
import org.opencps.synctracking.model.SyncTrackingResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component(immediate = true, property = { JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/secure/log-report/",
        JaxrsWhiteboardConstants.JAX_RS_NAME + "=OpenCPS.log-report" }, service = Application.class)
public class SyncTrackingApplication extends Application{
    private SyncTrackingAction syncTrackingAction;
    private ObjectMapper       objectMapper;
    private static final String DOSSIER_BTTTT = "DOSSIER_BTTTT";

    private final static Log _log = LogFactoryUtil.getLog(SyncTrackingApplication.class);

    public SyncTrackingApplication() {
        TransformAction transformAction = new TransformActionImpl();
        IntegrationOutsideApi integrationApi = new IntegrationOutsideApiImpl();
        this.syncTrackingAction = new SyncTrackingActionImpl(transformAction, integrationApi);
        this.objectMapper       = new ObjectMapper();
    }

    @Override
    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }

    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response get(@HeaderParam("groupId") long groupId, @BeanParam SyncTrackingQuery query) {
        DtoResponse<SyncTrackingResponse> response;

        try {
            if(groupId != 0) {
                query.groupId = groupId;
            }
            response = syncTrackingAction.get(query);
            return Response.status(200).entity(objectMapper.writeValueAsString(response)).build();
        } catch (Exception e) {
            _log.error("Error when get log report: " + e.getMessage());
            return Response.status(500).entity("Error").build();
        }
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response create(@HeaderParam("groupId") long groupId, SyncTrackingQuery query) {
        try {
            if(groupId != 0) {
                query.groupId = groupId;
            }
            syncTrackingAction.create(query);
            return Response.status(200).entity("Saved").build();
        } catch (Exception e) {
            _log.error("Error when save log report: " + e.getMessage());
            return Response.status(500).entity("Error").build();
        }
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response resend(@HeaderParam("groupId") long groupId, SyncTrackingQuery query) {
        try {
            if(groupId != 0) {
                query.groupId = groupId;
            }
            List<ServerConfig> listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(DOSSIER_BTTTT, DOSSIER_BTTTT);
            ServerConfig serverConfig = listConfig.get(0);
            JSONObject configJson = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());

            syncTrackingAction.resend(query, configJson);
            return Response.status(200).entity("Resent!!!").build();
        } catch (Exception e) {
            _log.error("Error when resend api: " + e.getMessage());
            return Response.status(500).entity("Error").build();
        }
    }

    @GET
    @Path("ping")
    @Produces("text/plain")
    public String ping() {
        try {
            return "ok";
        } catch (Exception e) {
            return "error";
        }
    }
}
