package org.opencps.datamgt.model.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class DictCollectionTempListener extends BaseModelListener<DictCollection> {

	@Override
	public void onAfterCreate(DictCollection model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(DictCollection model) throws ModelListenerException {
	}

}
