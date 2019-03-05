package org.opencps.datamgt.model.listener;

import java.util.List;

import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemGroup;
import org.opencps.datamgt.service.DictItemGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
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
public class DictItemListener extends BaseModelListener<DictItem> {

	@Override
	public void onAfterCreate(DictItem model) throws ModelListenerException {
		super.onAfterCreate(model);

		_log.info("onAfterCreate DictItem" + model);

	}

	@Override
	public void onAfterUpdate(DictItem model) throws ModelListenerException {
		super.onAfterUpdate(model);

		//_log.info("onAfterUpdate DictItem" + model);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());

		// update child item
		List<DictItem> listChilds = DictItemLocalServiceUtil.findByF_parentItemId(model.getDictItemId());

		for (DictItem dictItem : listChilds) {

			//_log.info("onAfterUpdate DictItem listChilds" + dictItem.getDictItemId());

			try {
				dictItem = DictItemLocalServiceUtil.updateDictItemListener(dictItem.getUserId(), dictItem.getDictItemId(),
						dictItem.getDictCollectionId(), dictItem.getItemCode(), dictItem.getItemName(),
						dictItem.getItemNameEN(), dictItem.getItemDescription(), dictItem.getParentItemId(), dictItem.getSibling(), dictItem.getLevel(), dictItem.getMetaData(),
						serviceContext);

				Indexer<DictItem> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictItem.class);

				try {
					indexer.reindex(dictItem);
				} catch (SearchException e) {
					_log.debug(e);
					//_log.error(e);
				} catch (NumberFormatException e) {
					_log.debug(e);
					//_log.error(e);
				}

			} catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@Override
	public void onAfterRemove(DictItem model) throws ModelListenerException {
		super.onAfterRemove(model);

		_log.info("onAfterRemove DictItem" + model);

		try {

			long dictItemId = model.getDictItemId();

			List<DictItemGroup> listGroups = DictItemGroupLocalServiceUtil.findByF_dictItemId(model.getGroupId(),
					dictItemId);

			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(model.getCompanyId());

			for (DictItemGroup dictItemGroup : listGroups) {

				DictItemGroupLocalServiceUtil.deleteDictItemGroupNoneAuthen(dictItemGroup.getDictItemGroupId(),
						serviceContext);
			}

		} catch (Exception e) {
			_log.error(e);
		}

	}

	@Override
	public void onBeforeCreate(DictItem model) throws ModelListenerException {
//		try {
//			// Other fields
//			model.setDictCollectionId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getDictCollectionId()))));
//			model.setItemCode(HtmlUtil.escape(model.getItemCode()));
//			model.setItemName(HtmlUtil.escape(model.getItemName()));
//			model.setItemNameEN(HtmlUtil.escape(model.getItemNameEN()));
//			model.setItemDescription(HtmlUtil.escape(model.getItemDescription()));
//			model.setParentItemId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getParentItemId()))));
//			model.setSibling(HtmlUtil.escape(model.getSibling()));
//			model.setTreeIndex(HtmlUtil.escape(model.getTreeIndex()));
//			model.setLevel(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getLevel()))));
//			model.setMetaData(HtmlUtil.escape(model.getMetaData()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeRemove(DictItem model) throws ModelListenerException {
		super.onBeforeRemove(model);

		long id = model.getDictItemId();
		//_log.info("id" + id);

		try {
			DictItemLocalServiceUtil.fetchDictItem(id);

		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onBeforeUpdate(DictItem model) throws ModelListenerException {
//		try {
//			// Other fields
//			model.setDictCollectionId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getDictCollectionId()))));
//			model.setItemCode(HtmlUtil.escape(model.getItemCode()));
//			model.setItemName(HtmlUtil.escape(model.getItemName()));
//			model.setItemNameEN(HtmlUtil.escape(model.getItemNameEN()));
//			model.setItemDescription(HtmlUtil.escape(model.getItemDescription()));
//			model.setParentItemId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getParentItemId()))));
//			model.setSibling(HtmlUtil.escape(model.getSibling()));
//			model.setTreeIndex(HtmlUtil.escape(model.getTreeIndex()));
//			model.setLevel(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getLevel()))));
//			model.setMetaData(HtmlUtil.escape(model.getMetaData()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

//	private static DictItem modelBefore;

	Log _log = LogFactoryUtil.getLog(DictItemListener.class);

}
