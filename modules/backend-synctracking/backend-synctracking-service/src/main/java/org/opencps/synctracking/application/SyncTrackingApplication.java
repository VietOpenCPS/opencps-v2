package org.opencps.synctracking.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

@Component(immediate = true, property = { JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/secure/log-report/",
        JaxrsWhiteboardConstants.JAX_RS_NAME + "=OpenCPS.log-report" }, service = Application.class)
public class SyncTrackingApplication extends Application{
    private SyncTrackingAction syncTrackingAction;
    private ObjectMapper       objectMapper;
    private static final String DOSSIER_BTTTT = "DOSSIER_BTTTT";

    private final static Log _log = LogFactoryUtil.getLog(SyncTrackingApplication.class);

    static class CounterSyncTracking {
        private volatile static long count = 0;
        public static long getCount(){
            return count;
        }
        public static synchronized void decreaseCount(){
            count--;
        }

        public static synchronized void setCount(long countNew){
            count = countNew;
        }
    }


    public SyncTrackingApplication() {
        TransformAction transformAction = new TransformActionImpl();
        IntegrationOutsideApi integrationApi = new IntegrationOutsideApiImpl();
        this.syncTrackingAction = new SyncTrackingActionImpl(transformAction, integrationApi);
        this.objectMapper       = new ObjectMapper();
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<Object>();
        // add REST endpoints (resources)
//		singletons.add(new DigitalSignatureManagementImpl());
        singletons.add(this);
        return singletons;
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
            _log.error(e);
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
            _log.error(e);
            return Response.status(500).entity("Error").build();
        }
    }

    @POST
    @Path("/dvcqg")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response createSynTrackingByDVCQG(@HeaderParam("groupId") long groupId, SyncTrackingQuery query) {
        try {
            if(groupId != 0) {
                query.groupId = groupId;
            }
            syncTrackingAction.createSynTrackingDVCQG(query);
            return Response.status(200).entity("Saved").build();
        } catch (Exception e) {
            _log.error(e);
            return Response.status(500).entity("Error").build();
        }
    }

    @POST
    @Path("/resend")
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
            _log.error(e);
            return Response.status(500).entity("Error").build();
        }
    }

    private static volatile ThreadPoolExecutor threadPoolExecutor;
    private int corePoolSize    = 5;
    private int maximumPoolSize = 15;
    private int queueCapacity   = 6;
    private int keepAliveTime   = 10;

    @POST
    @Path("ping")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces("text/plain")
    public String ping(String body) {
        try {
            _log.info("testing...");
            if(Validator.isNull(threadPoolExecutor)) {
                _log.info("Creating threadPoolExecutor first time...");
                threadPoolExecutor = new ThreadPoolExecutor(
                        corePoolSize, // Số thread mặc định được cấp để xử lý request
                        maximumPoolSize, //Số thread tối đa được dùng
                        keepAliveTime, //thời gian sống 1 thread nếu thread đang ko làm gì
                        java.util.concurrent.TimeUnit.SECONDS, //đơn vị thời gian
                        new ArrayBlockingQueue<>(queueCapacity), //Queue để lưu số lượng request chờ khi số thread trong
                        // corePoolSize được dùng hết, khi số lượng request = queueCapacity thì sẽ tạo 1 thread mới
                        new ThreadPoolExecutor.CallerRunsPolicy()); //Tự động xử lý exception khi số lượng request vượt quá queueCapacity
            }


            JSONObject bodyJson = JSONFactoryUtil.createJSONObject(body);
            String pattern = bodyJson.getString("pattern");
            int quantity  = bodyJson.getInt("quantity");
            _log.info("start calling sync function");
            for(int i = 0; i< quantity; i++) {
                threadPoolExecutor.execute(() -> StaticCounter.increaseCounter(pattern));
                _log.info("Number thread active: " + threadPoolExecutor.getActiveCount());
            }


            return "ok";
        } catch (Exception e) {
            _log.error(e);
            return "error";
        }
    }



}
