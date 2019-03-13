package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

import org.opencps.dossiermgt.model.DeliverableType;

public interface DeliverableTypeInterface {

	DeliverableType getByTypeCode(long userId, long groupId, String typeCode, ServiceContext serviceContext);
	
	List<DeliverableType> getDeliverableTypesList(long groupId, int start, int end);
	
	public List<Long> getRoleIdByTypes(long deliverableTypeId);
}
