package org.opencps.api.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.opencps.api.controller.QLVGManagement;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.QLVBIntegrationAction;
import org.opencps.dossiermgt.action.impl.QLVBIntegrationActionImpl;

import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.List;

public class QLVGManagementImpl implements QLVGManagement {
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Log _log = LogFactoryUtil.getLog(QLVGManagementImpl.class);
    private static final String API_SYNC_QLVB = "API_SYNC_QLVB";
    @Override
    public Response sendProfile(long dossierId) {
        try {
            List<ServerConfig> listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(API_SYNC_QLVB, API_SYNC_QLVB);
            ServerConfig serverConfig = listConfig.get(0);
            QLVBIntegrationAction qlvbAction = new QLVBIntegrationActionImpl(serverConfig);
            String token = qlvbAction.getTokenHG();

            qlvbAction.sendVBHG(token, dossierId);
            return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();
        } catch (Exception e) {
            _log.error("Error when sync dossier: " + e.getMessage());
            return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
        }
    }
}
