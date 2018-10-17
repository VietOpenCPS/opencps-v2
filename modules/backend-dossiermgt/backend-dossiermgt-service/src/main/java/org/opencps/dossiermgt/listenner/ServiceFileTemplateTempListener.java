package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class ServiceFileTemplateTempListener extends BaseModelListener<ServiceFileTemplate> {

	@Override
	public void onAfterCreate(ServiceFileTemplate model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(ServiceFileTemplate model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(ServiceFileTemplate model) throws ModelListenerException {
//		try {
//			model.setFileTemplateNo(HtmlUtil.escape(model.getFileTemplateNo()));
////
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(ServiceFileTemplate model) throws ModelListenerException {
//		try {
//			model.setFileTemplateNo(HtmlUtil.escape(model.getFileTemplateNo()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(ServiceFileTemplateTempListener.class.getName());
}
