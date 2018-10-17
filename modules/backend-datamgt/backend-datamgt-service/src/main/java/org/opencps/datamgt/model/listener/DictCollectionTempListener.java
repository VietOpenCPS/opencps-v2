package org.opencps.datamgt.model.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.datamgt.model.DictCollection;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class DictCollectionTempListener extends BaseModelListener<DictCollection> {

	@Override
	public void onAfterCreate(DictCollection model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(DictCollection model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(DictCollection model) throws ModelListenerException {
//		try {
//			// Other fields
//			model.setCollectionCode(HtmlUtil.escape(model.getCollectionCode()));
//			model.setCollectionName(HtmlUtil.escape(model.getCollectionName()));
//			model.setCollectionNameEN(HtmlUtil.escape(model.getCollectionNameEN()));
//			model.setDescription(HtmlUtil.escape(model.getDescription()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(DictCollection model) throws ModelListenerException {
//		try {
//			// Other fields
//			model.setCollectionCode(HtmlUtil.escape(model.getCollectionCode()));
//			model.setCollectionName(HtmlUtil.escape(model.getCollectionName()));
//			model.setCollectionNameEN(HtmlUtil.escape(model.getCollectionNameEN()));
//			model.setDescription(HtmlUtil.escape(model.getDescription()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(DictCollectionTempListener.class.getName());
}
