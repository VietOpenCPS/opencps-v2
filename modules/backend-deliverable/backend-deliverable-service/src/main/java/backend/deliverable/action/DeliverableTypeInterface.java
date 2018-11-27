package backend.deliverable.action;

import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

import org.opencps.deliverable.model.OpenCPSDeliverableType;

public interface DeliverableTypeInterface {

	OpenCPSDeliverableType getByTypeCode(long userId, long groupId, String typeCode, ServiceContext serviceContext);
	
	List<OpenCPSDeliverableType> getDeliverableTypes(long groupId, int start, int end);
	
	public List<Long> getRoleIdByTypes(long deliverableTypeId);
}
