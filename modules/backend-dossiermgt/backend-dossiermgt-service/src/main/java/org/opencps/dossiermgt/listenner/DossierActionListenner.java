package org.opencps.dossiermgt.listenner;

import org.opencps.dossiermgt.model.DossierAction;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.util.Validator;

public class DossierActionListenner extends BaseModelListener<DossierAction> {
	@Override
	public void onAfterCreate(DossierAction model) throws ModelListenerException {

		if (Validator.isNotNull(model.getSyncActionCode()) && model.getSyncActionCode().length() != 0) {
			
		}

		super.onAfterCreate(model);
	}
}
