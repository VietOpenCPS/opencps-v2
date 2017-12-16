package org.opencps.dossiermgt.action;

import org.opencps.dossiermgt.exception.NoSuchDeliverableException;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.impl.DeliverableImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;

public interface DeliverableActions {
	public JSONObject getListDeliverable(String state, String agency, String type, String applicant);

	public void addDeliverable(DeliverableImpl model);

	public Deliverable getListDeliverableDetail(Long deliverableId) throws NoSuchDeliverableException;

	public void updateDeliverable(Deliverable model);

	public void deleteDeliverable(Long deliverableId) throws PortalException;

}
