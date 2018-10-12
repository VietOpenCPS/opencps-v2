package org.opencps.datamgt.model.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.datamgt.model.WorkTime;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class WorkTimeTempListener extends BaseModelListener<WorkTime> {

	@Override
	public void onAfterCreate(WorkTime model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(WorkTime model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(WorkTime model) throws ModelListenerException {
		try {
			model.setDay(Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getDay()))));
			model.setHours(StringEscapeUtils.escapeHtml4(model.getHours()));

		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onBeforeUpdate(WorkTime model) throws ModelListenerException {
		try {
			model.setDay(Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getDay()))));
			model.setHours(StringEscapeUtils.escapeHtml4(model.getHours()));

		} catch (Exception e) {
			_log.error(e);
		}
	}


	private Log _log = LogFactoryUtil.getLog(WorkTimeTempListener.class.getName());
}
