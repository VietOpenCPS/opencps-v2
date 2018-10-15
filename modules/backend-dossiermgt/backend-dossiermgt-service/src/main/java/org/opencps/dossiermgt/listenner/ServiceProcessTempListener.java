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
//			model.setProcessNo(StringEscapeUtils.escapeHtml4(model.getProcessNo()));
//			model.setProcessName(StringEscapeUtils.escapeHtml4(model.getProcessName()));
//			model.setDescription(StringEscapeUtils.escapeHtml4(model.getDescription()));
//			model.setDurationCount(
//					Double.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getDurationCount()))));
//			model.setDurationUnit(
//					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getDurationUnit()))));
//			model.setCounter(Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getCounter()))));
//			model.setGenerateDossierNo(
//					Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getGenerateDossierNo()))));
//			model.setDossierNoPattern(StringEscapeUtils.escapeHtml4(model.getDossierNoPattern()));
//			model.setGenerateDueDate(
//					Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getGenerateDueDate()))));
//			model.setDueDatePattern(StringEscapeUtils.escapeHtml4(model.getDueDatePattern()));
//			model.setGeneratePassword(
//					Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getGeneratePassword()))));
//			model.setDirectNotification(
//					Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getDirectNotification()))));
//			model.setServerNo(StringEscapeUtils.escapeHtml4(model.getServerNo()));
//			model.setPaymentFee(StringEscapeUtils.escapeHtml4(model.getPaymentFee()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(ServiceProcess model) throws ModelListenerException {
//		try {
//			// Add other fields
//			model.setProcessNo(StringEscapeUtils.escapeHtml4(model.getProcessNo()));
//			model.setProcessName(StringEscapeUtils.escapeHtml4(model.getProcessName()));
//			model.setDescription(StringEscapeUtils.escapeHtml4(model.getDescription()));
//			model.setDurationCount(
//					Double.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getDurationCount()))));
//			model.setDurationUnit(
//					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getDurationUnit()))));
//			model.setCounter(Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getCounter()))));
//			model.setGenerateDossierNo(
//					Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getGenerateDossierNo()))));
//			model.setDossierNoPattern(StringEscapeUtils.escapeHtml4(model.getDossierNoPattern()));
//			model.setGenerateDueDate(
//					Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getGenerateDueDate()))));
//			model.setDueDatePattern(StringEscapeUtils.escapeHtml4(model.getDueDatePattern()));
//			model.setGeneratePassword(
//					Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getGeneratePassword()))));
//			model.setDirectNotification(
//					Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getDirectNotification()))));
//			model.setServerNo(StringEscapeUtils.escapeHtml4(model.getServerNo()));
//			model.setPaymentFee(StringEscapeUtils.escapeHtml4(model.getPaymentFee()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(ServiceProcessTempListener.class.getName());
}
