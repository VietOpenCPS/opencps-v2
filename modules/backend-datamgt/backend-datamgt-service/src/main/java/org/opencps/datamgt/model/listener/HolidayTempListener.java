package org.opencps.datamgt.model.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.datamgt.model.Holiday;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class HolidayTempListener extends BaseModelListener<Holiday> {

	@Override
	public void onAfterCreate(Holiday model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(Holiday model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(Holiday model) throws ModelListenerException {
		try {
			model.setDescription(StringEscapeUtils.escapeHtml4(model.getDescription()));

		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onBeforeUpdate(Holiday model) throws ModelListenerException {
		try {
			model.setDescription(StringEscapeUtils.escapeHtml4(model.getDescription()));

		} catch (Exception e) {
			_log.error(e);
		}
	}


	private Log _log = LogFactoryUtil.getLog(HolidayTempListener.class.getName());
}
