package org.opencps.synchronization.model.listener;

import java.util.List;

import org.opencps.synchronization.model.DictItemGroupTemp;
import org.opencps.synchronization.model.DictItemTemp;
import org.opencps.synchronization.service.DictItemGroupTempLocalServiceUtil;
import org.opencps.synchronization.service.DictItemTempLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;

@Component(immediate = true, service = ModelListener.class)
public class DictItemTempListener extends BaseModelListener<DictItemTemp> {

	@Override
	public void onAfterCreate(DictItemTemp model) throws ModelListenerException {
		super.onAfterCreate(model);

		_log.info("onAfterCreate DictItemTemp" + model);

	}

	@Override
	public void onAfterUpdate(DictItemTemp model) throws ModelListenerException {
		super.onAfterUpdate(model);

		_log.info("onAfterUpdate DictItemTemp" + model);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());

		// update child item
		List<DictItemTemp> listChilds = DictItemTempLocalServiceUtil.findByF_parentItemId(model.getDictItemId());

		for (DictItemTemp dictItem : listChilds) {

			_log.info("onAfterUpdate DictItemTemp listChilds" + dictItem.getDictItemId());

			try {
				dictItem = DictItemTempLocalServiceUtil.updateDictItemTempListener(dictItem.getUserId(), dictItem.getDictItemId(),
						dictItem.getDictCollectionId(), dictItem.getItemCode(), dictItem.getItemName(),
						dictItem.getItemNameEN(), dictItem.getItemDescription(), dictItem.getParentItemId(), dictItem.getSibling(), dictItem.getLevel(), dictItem.getMetaData(),
						dictItem.getStatus(),
						serviceContext);

				Indexer<DictItemTemp> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictItemTemp.class);

				try {
					indexer.reindex(dictItem);
				} catch (SearchException e) {
					_log.error(e);
				} catch (NumberFormatException e) {
					_log.error(e);
				}

			} catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@Override
	public void onAfterRemove(DictItemTemp model) throws ModelListenerException {
		super.onAfterRemove(model);

		_log.info("onAfterRemove DictItemTemp" + model);

		try {

			long dictItemId = model.getDictItemId();

			List<DictItemGroupTemp> listGroups = DictItemGroupTempLocalServiceUtil.findByF_dictItemId(model.getGroupId(),
					dictItemId);

			ServiceContext serviceContext = new ServiceContext();

			for (DictItemGroupTemp dictItemGroup : listGroups) {

				DictItemGroupTempLocalServiceUtil.deleteDictItemGroupTempNoneAuthen(dictItemGroup.getDictItemGroupId(),
						serviceContext);
			}

		} catch (Exception e) {
			_log.error(e);
		}

	}

	@Override
	public void onBeforeCreate(DictItemTemp model) throws ModelListenerException {
		super.onBeforeCreate(model);
	}

	@Override
	public void onBeforeRemove(DictItemTemp model) throws ModelListenerException {
		super.onBeforeRemove(model);

		long id = model.getDictItemId();
		_log.info("id" + id);

		try {
//			modelBefore = DictItemTempLocalServiceUtil.fetchDictItemTemp(id);
			DictItemTempLocalServiceUtil.fetchDictItemTemp(id);

		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onBeforeUpdate(DictItemTemp model) throws ModelListenerException {
		super.onBeforeUpdate(model);

		long id = model.getDictItemId();
		_log.info("id" + id);

		try {
//			modelBefore = DictItemTempLocalServiceUtil.fetchDictItemTemp(id);
			DictItemTempLocalServiceUtil.fetchDictItemTemp(id);

		} catch (Exception e) {
			_log.error(e);
		}
	}

//	public static DictItemTemp modelBefore;

	Log _log = LogFactoryUtil.getLog(DictItemTempListener.class);

}
