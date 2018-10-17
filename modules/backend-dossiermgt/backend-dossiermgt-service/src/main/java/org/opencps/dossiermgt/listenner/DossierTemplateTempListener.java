package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class DossierTemplateTempListener extends BaseModelListener<DossierTemplate> {

	@Override
	public void onAfterCreate(DossierTemplate model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(DossierTemplate model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(DossierTemplate model) throws ModelListenerException {
//		try {
//			model.setTemplateName(HtmlUtil.escape(model.getTemplateName()));
//			model.setTemplateNo(HtmlUtil.escape(model.getTemplateNo()));
//			model.setDescription(HtmlUtil.escape(model.getDescription()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(DossierTemplate model) throws ModelListenerException {
//		try {
//			model.setTemplateName(HtmlUtil.escape(model.getTemplateName()));
//			model.setTemplateNo(HtmlUtil.escape(model.getTemplateNo()));
//			model.setDescription(HtmlUtil.escape(model.getDescription()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(DossierTemplateTempListener.class.getName());
}
