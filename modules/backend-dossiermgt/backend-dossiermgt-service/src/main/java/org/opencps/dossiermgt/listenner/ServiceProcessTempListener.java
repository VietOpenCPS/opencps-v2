package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class ServiceProcessTempListener extends BaseModelListener<ServiceProcess> {

	@Override
	public void onAfterCreate(ServiceProcess model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(ServiceProcess model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(ServiceProcess model) throws ModelListenerException {
//		try {
//			// Add other fields
//			model.setProcessNo(HtmlUtil.escape(model.getProcessNo()));
//			model.setProcessName(HtmlUtil.escape(model.getProcessName()));
//			model.setDescription(HtmlUtil.escape(model.getDescription()));
//			model.setDurationCount(
//					Double.valueOf(HtmlUtil.escape(String.valueOf(model.getDurationCount()))));
//			model.setDurationUnit(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getDurationUnit()))));
//			model.setCounter(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getCounter()))));
//			model.setGenerateDossierNo(
//					Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getGenerateDossierNo()))));
//			model.setDossierNoPattern(HtmlUtil.escape(model.getDossierNoPattern()));
//			model.setGenerateDueDate(
//					Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getGenerateDueDate()))));
//			model.setDueDatePattern(HtmlUtil.escape(model.getDueDatePattern()));
//			model.setGeneratePassword(
//					Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getGeneratePassword()))));
//			model.setDirectNotification(
//					Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getDirectNotification()))));
//			model.setServerNo(HtmlUtil.escape(model.getServerNo()));
//			model.setPaymentFee(HtmlUtil.escape(model.getPaymentFee()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(ServiceProcess model) throws ModelListenerException {
//		try {
//			// Add other fields
//			model.setProcessNo(HtmlUtil.escape(model.getProcessNo()));
//			model.setProcessName(HtmlUtil.escape(model.getProcessName()));
//			model.setDescription(HtmlUtil.escape(model.getDescription()));
//			model.setDurationCount(
//					Double.valueOf(HtmlUtil.escape(String.valueOf(model.getDurationCount()))));
//			model.setDurationUnit(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getDurationUnit()))));
//			model.setCounter(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getCounter()))));
//			model.setGenerateDossierNo(
//					Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getGenerateDossierNo()))));
//			model.setDossierNoPattern(HtmlUtil.escape(model.getDossierNoPattern()));
//			model.setGenerateDueDate(
//					Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getGenerateDueDate()))));
//			model.setDueDatePattern(HtmlUtil.escape(model.getDueDatePattern()));
//			model.setGeneratePassword(
//					Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getGeneratePassword()))));
//			model.setDirectNotification(
//					Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getDirectNotification()))));
//			model.setServerNo(HtmlUtil.escape(model.getServerNo()));
//			model.setPaymentFee(HtmlUtil.escape(model.getPaymentFee()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(ServiceProcessTempListener.class.getName());
}
