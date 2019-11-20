package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.opencps.dossiermgt.model.DossierLog;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class DossierLogListenner extends BaseModelListener<DossierLog> {
	
	@Override
	public void onAfterCreate(DossierLog model) throws ModelListenerException {
		
		//TODO: add notification when add
//		DossierListennerUltils.createNotificationQueue(model);
	
	}

	@Override
	public void onAfterUpdate(DossierLog model) throws ModelListenerException {
		
		//TODO: add notification when add
		
	}

	@Override
	public void onBeforeUpdate(DossierLog model) throws ModelListenerException {
//		try {
//			modelBeforeUpdate = DossierLogLocalServiceUtil.getDossierLog(model.getPrimaryKey());
//		} catch (Exception e) {
//			_log.debug(e);
//		}
	}

	public DossierLog modelBeforeUpdate;

}
