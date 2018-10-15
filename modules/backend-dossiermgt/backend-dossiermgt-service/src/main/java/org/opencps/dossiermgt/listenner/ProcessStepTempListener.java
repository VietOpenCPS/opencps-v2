package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.dossiermgt.model.ProcessStep;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class ProcessStepTempListener extends BaseModelListener<ProcessStep> {

	@Override
	public void onAfterCreate(ProcessStep model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(ProcessStep model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(ProcessStep model) throws ModelListenerException {
//		try {
//			model.setStepCode(StringEscapeUtils.escapeHtml4(model.getStepCode()));
//			model.setServiceProcessId(
//					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getServiceProcessId()))));
//			model.setStepName(StringEscapeUtils.escapeHtml4(model.getStepName()));
//			model.setSequenceNo(StringEscapeUtils.escapeHtml4(model.getSequenceNo()));
//			model.setDossierStatus(StringEscapeUtils.escapeHtml4(model.getDossierStatus()));
//			model.setDossierSubStatus(StringEscapeUtils.escapeHtml4(model.getDossierSubStatus()));
//			model.setDurationCount(
//					Double.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getDurationCount()))));
//			model.setCustomProcessUrl(StringEscapeUtils.escapeHtml4(model.getCustomProcessUrl()));
//			model.setStepInstruction(StringEscapeUtils.escapeHtml4(model.getStepInstruction()));
//			model.setBriefNote(StringEscapeUtils.escapeHtml4(model.getBriefNote()));
//			model.setEditable(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getEditable()))));
//			model.setLockState(StringEscapeUtils.escapeHtml4(model.getLockState()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(ProcessStep model) throws ModelListenerException {
//		try {
//			model.setStepCode(StringEscapeUtils.escapeHtml4(model.getStepCode()));
//			model.setServiceProcessId(
//					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getServiceProcessId()))));
//			model.setStepName(StringEscapeUtils.escapeHtml4(model.getStepName()));
//			model.setSequenceNo(StringEscapeUtils.escapeHtml4(model.getSequenceNo()));
//			model.setDossierStatus(StringEscapeUtils.escapeHtml4(model.getDossierStatus()));
//			model.setDossierSubStatus(StringEscapeUtils.escapeHtml4(model.getDossierSubStatus()));
//			model.setDurationCount(
//					Double.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getDurationCount()))));
//			model.setCustomProcessUrl(StringEscapeUtils.escapeHtml4(model.getCustomProcessUrl()));
//			model.setStepInstruction(StringEscapeUtils.escapeHtml4(model.getStepInstruction()));
//			model.setBriefNote(StringEscapeUtils.escapeHtml4(model.getBriefNote()));
//			model.setEditable(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getEditable()))));
//			model.setLockState(StringEscapeUtils.escapeHtml4(model.getLockState()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(ProcessStepTempListener.class.getName());
}
