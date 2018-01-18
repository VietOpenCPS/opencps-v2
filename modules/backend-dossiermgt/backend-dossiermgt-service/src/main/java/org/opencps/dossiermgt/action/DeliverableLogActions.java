package org.opencps.dossiermgt.action;

import org.opencps.dossiermgt.model.DeliverableLog;

import com.liferay.portal.kernel.exception.PortalException;

public interface DeliverableLogActions {

	public DeliverableLog getDeliverableLog(long id) throws PortalException;

}
