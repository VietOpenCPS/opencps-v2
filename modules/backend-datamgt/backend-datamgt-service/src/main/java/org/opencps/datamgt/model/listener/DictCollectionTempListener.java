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
		DictCollection dict = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(model.getCollectionCode(), 0l);
		if (dict == null) {
			long companyId = model.getCompanyId();
			long userId = model.getUserId();
			String userName = model.getUserName();
			String collectionCode = model.getCollectionCode();
			String collectionName = model.getCollectionName();
			String collectionNameEN = model.getCollectionNameEN();
			String description = model.getDescription();
			int status = model.getStatus();

			DictCollectionLocalServiceUtil.updateDictCollectionPublish(companyId, userId, 0l, userName, collectionCode,
					collectionName, collectionNameEN, description, status);
		}
	}

	@Override
	public void onAfterUpdate(DictCollection model) throws ModelListenerException {
	}

	private Log _log = LogFactoryUtil.getLog(DictCollectionTempListener.class.getName());
}
