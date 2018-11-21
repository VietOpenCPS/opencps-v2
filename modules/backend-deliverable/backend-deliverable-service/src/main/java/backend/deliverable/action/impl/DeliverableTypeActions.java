package backend.deliverable.action.impl;

import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

import org.opencps.deliverable.model.OpenCPSDeliverableType;
import org.opencps.deliverable.service.OpenCPSDeliverableTypeLocalServiceUtil;

import backend.deliverable.action.DeliverableTypeInterface;

public class DeliverableTypeActions implements DeliverableTypeInterface {

	@Override
	public OpenCPSDeliverableType getByTypeCode(long userId, String typeCode, ServiceContext serviceContext) {
		return OpenCPSDeliverableTypeLocalServiceUtil.getByTypeCode(typeCode);
	}

	@Override
	public List<OpenCPSDeliverableType> getDeliverableTypes(long groupId, int start, int end) {
		return OpenCPSDeliverableTypeLocalServiceUtil.getDeliverableTypes(groupId, start, end);
	}

}
