package org.opencps.dossiermgt.action.impl;

import org.opencps.dossiermgt.action.DeliverableLogActions;
import org.opencps.dossiermgt.model.DeliverableLog;
import org.opencps.dossiermgt.service.DeliverableLogLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;

public class DeliverableLogActionsImpl implements DeliverableLogActions {

	@Override
	public DeliverableLog getDeliverableLog(long id) throws PortalException {

		DeliverableLog log = DeliverableLogLocalServiceUtil.getDeliverableLog(id);
		return log;
	}

}
