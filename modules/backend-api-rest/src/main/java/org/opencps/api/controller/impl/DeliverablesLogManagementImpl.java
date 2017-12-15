package org.opencps.api.controller.impl;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.DeliverablesLogManagement;
import org.opencps.dossiermgt.action.DeliverableLogActions;
import org.opencps.dossiermgt.action.impl.DeliverableLogActionsImpl;
import org.opencps.dossiermgt.model.DeliverableLog;

public class DeliverablesLogManagementImpl implements DeliverablesLogManagement {

	@Override
	public Response getDeliverables(Long id) {
		try {
			DeliverableLogActions action = new DeliverableLogActionsImpl();
			DeliverableLog deliverable = action.getDeliverableLog(id);
			return Response.status(200).entity(deliverable).build();
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}
	}

}
