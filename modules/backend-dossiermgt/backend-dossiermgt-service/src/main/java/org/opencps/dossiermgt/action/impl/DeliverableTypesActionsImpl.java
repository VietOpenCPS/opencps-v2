package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.action.DeliverableTypesActions;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

public class DeliverableTypesActionsImpl implements DeliverableTypesActions {

	private static final Log _log = LogFactoryUtil.getLog(DeliverableTypesActionsImpl.class);

	@Override
	public JSONObject getDeliverableTypes(long groupId, int start, int end) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			if (start == 0) {
				start = -1;
				end = -1;
			}

			List<DeliverableType> lstDeliverableType = DeliverableTypeLocalServiceUtil.getDeliverableTypes(start, end);

			int total = DeliverableTypeLocalServiceUtil.getDeliverableTypesCount();

			result.put("total", total);
			result.put("lstDeliverableType", lstDeliverableType);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public DeliverableType addDeliverableType(long groupId, String deliverableName, String deliverableType_,
			String codePattern, String counter, String formScript, String formReport, String mappingData,
			ServiceContext serviceContext) throws PortalException, SystemException {

		return DeliverableTypeLocalServiceUtil.addDeliverableType(groupId, deliverableName, deliverableType_,
				codePattern, counter, formScript, formReport, mappingData, serviceContext);
	}

	@Override
	public DeliverableType updateDeliverableType(long groupId, long deliverableTypeId, String deliverableName,
			String deliverableType_, String codePattern, String counter, String formScript, String formReport,
			String mappingData, ServiceContext serviceContext) throws SystemException, PortalException {

		return DeliverableTypeLocalServiceUtil.updateDeliverableType(groupId, deliverableTypeId, deliverableName,
				deliverableType_, codePattern, counter, formScript, formReport, mappingData, serviceContext);
	}
	
	@Override
	public DeliverableType removeDeliverableType(long groupId, long deliverableTypeId, String deliverableType_) throws PortalException {

		return DeliverableTypeLocalServiceUtil.removeDeliverableType(groupId, deliverableTypeId, deliverableType_);
	}
	
	@Override
	public DeliverableType updateDeliverableTypeFormScript(long groupId, long deliverableTypeId, String formScript,
			ServiceContext serviceContext) throws SystemException, PortalException {

		return DeliverableTypeLocalServiceUtil.updateFormScript(groupId, deliverableTypeId, formScript, serviceContext);
	}
	
	@Override
	public DeliverableType updateDeliverableTypeFormReport(long groupId, long deliverableTypeId, String formReport,
			ServiceContext serviceContext) throws SystemException, PortalException {

		return DeliverableTypeLocalServiceUtil.updateFormScript(groupId, deliverableTypeId, formReport, serviceContext);
	}
	
	@Override
	public DeliverableType updateDeliverableTypeMappingData(long groupId, long deliverableTypeId, String mappingData,
			ServiceContext serviceContext) throws SystemException, PortalException {

		return DeliverableTypeLocalServiceUtil.updateFormScript(groupId, deliverableTypeId, mappingData, serviceContext);
	}
}
