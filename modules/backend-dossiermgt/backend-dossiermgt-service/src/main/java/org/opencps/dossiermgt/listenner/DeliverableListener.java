package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.opencps.deliverable.model.OpenCPSDeliverable;
import org.opencps.deliverable.service.OpenCPSDeliverableLocalServiceUtil;
import org.opencps.dossiermgt.model.Deliverable;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class DeliverableListener extends BaseModelListener<Deliverable> {

	@Override
	public void onAfterCreate(Deliverable model) throws ModelListenerException {

		// Binhth add message bus to processing jasper file
		_log.info("IN DeliverableListener onAfterCreate");
		
	}

	@Override
	public void onAfterUpdate(Deliverable model) throws ModelListenerException {
		_log.info("IN DeliverableListener onAfterUpdate" + model.getPrimaryKey());
		_log.info("IN DeliverableListener onAfterUpdate" + model.getDeliverableId());
		_log.info("DossierFileListenner.onAfterUpdate(openCPSDeliverable)" + OpenCPSDeliverableLocalServiceUtil.getOpenCPSDeliverables(-1, -1));
		OpenCPSDeliverable openCPSDeliverable = OpenCPSDeliverableLocalServiceUtil.fetchOpenCPSDeliverable(model.getDeliverableId());

		_log.info("DossierFileListenner.onAfterUpdate(openCPSDeliverable)" + openCPSDeliverable);
//		openCPSDeliverable.setDeliverableState(1);

		System.out.println("DossierFileListenner.onAfterUpdate(openCPSDeliverable)" + openCPSDeliverable);
		
		try {
			OpenCPSDeliverableLocalServiceUtil.adminProcessData(JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(openCPSDeliverable)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("DossierFileListenner.onAfterUpdate(openCPSDeliverable22)" + openCPSDeliverable);
	}

	@Override
	public void onBeforeCreate(Deliverable model) throws ModelListenerException {
	}

	@Override
	public void onBeforeUpdate(Deliverable model) throws ModelListenerException {
	}

	private Log _log = LogFactoryUtil.getLog(DeliverableListener.class.getName());
}
