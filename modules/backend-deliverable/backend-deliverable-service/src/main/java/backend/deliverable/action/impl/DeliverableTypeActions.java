package backend.deliverable.action.impl;

import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

import org.opencps.deliverable.model.OpenCPSDeliverableType;
import org.opencps.deliverable.service.OpenCPSDeliverableTypeLocalServiceUtil;
import org.opencps.deliverable.service.OpenCPSDeliverableTypeRoleLocalServiceUtil;

import backend.deliverable.action.DeliverableTypeInterface;

public class DeliverableTypeActions implements DeliverableTypeInterface {

	@Override
	public OpenCPSDeliverableType getByTypeCode(long userId, long groupId, String typeCode, ServiceContext serviceContext) {
		return OpenCPSDeliverableTypeLocalServiceUtil.getByTypeCode(typeCode, groupId);
	}

	@Override
	public List<OpenCPSDeliverableType> getDeliverableTypes(long groupId, int start, int end) {
		return OpenCPSDeliverableTypeLocalServiceUtil.getDeliverableTypes(groupId, start, end);
	}

	@Override
	public List<Long> getRoleIdByTypes(long deliverableTypeId) {
		// TODO Auto-generated method stub
		return OpenCPSDeliverableTypeRoleLocalServiceUtil.getRoleIdByTypes(deliverableTypeId);
	}

}
