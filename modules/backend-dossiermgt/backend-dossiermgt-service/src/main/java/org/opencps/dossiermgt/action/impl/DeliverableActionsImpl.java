package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.action.DeliverableActions;
import org.opencps.dossiermgt.exception.NoSuchDeliverableException;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.impl.DeliverableImpl;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class DeliverableActionsImpl implements DeliverableActions {

	@Override
	public JSONObject getListDeliverable(String state, String agency, String type, String applicant) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<Deliverable> listDeliverable = DeliverableLocalServiceUtil.getListDeliverable(state, agency, type,
				applicant);
		result.put("data", listDeliverable);
		result.put("total", listDeliverable.size());
		return result;
	}

	@Override
	public void addDeliverable(DeliverableImpl model) {
		DeliverableLocalServiceUtil.insert(model);
	}

	@Override
	public void getListDeliverableDetail(Long deliverableId) throws NoSuchDeliverableException {
		DeliverableLocalServiceUtil.getListDeliverableDetai(deliverableId);
	}

}
