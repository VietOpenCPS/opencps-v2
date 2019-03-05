package org.opencps.datamgt.model.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

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
//		try {
//			model.setDay(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getDay()))));
//			model.setHours(HtmlUtil.escape(model.getHours()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(WorkTime model) throws ModelListenerException {
//		try {
//			model.setDay(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getDay()))));
//			model.setHours(HtmlUtil.escape(model.getHours()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	//private Log _log = LogFactoryUtil.getLog(WorkTimeTempListener.class.getName());
}
