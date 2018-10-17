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
//			model.setStepCode(HtmlUtil.escape(model.getStepCode()));
//			model.setServiceProcessId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getServiceProcessId()))));
//			model.setStepName(HtmlUtil.escape(model.getStepName()));
//			model.setSequenceNo(HtmlUtil.escape(model.getSequenceNo()));
//			model.setDossierStatus(HtmlUtil.escape(model.getDossierStatus()));
//			model.setDossierSubStatus(HtmlUtil.escape(model.getDossierSubStatus()));
//			model.setDurationCount(
//					Double.valueOf(HtmlUtil.escape(String.valueOf(model.getDurationCount()))));
//			model.setCustomProcessUrl(HtmlUtil.escape(model.getCustomProcessUrl()));
//			model.setStepInstruction(HtmlUtil.escape(model.getStepInstruction()));
//			model.setBriefNote(HtmlUtil.escape(model.getBriefNote()));
//			model.setEditable(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getEditable()))));
//			model.setLockState(HtmlUtil.escape(model.getLockState()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(ProcessStep model) throws ModelListenerException {
//		try {
//			model.setStepCode(HtmlUtil.escape(model.getStepCode()));
//			model.setServiceProcessId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getServiceProcessId()))));
//			model.setStepName(HtmlUtil.escape(model.getStepName()));
//			model.setSequenceNo(HtmlUtil.escape(model.getSequenceNo()));
//			model.setDossierStatus(HtmlUtil.escape(model.getDossierStatus()));
//			model.setDossierSubStatus(HtmlUtil.escape(model.getDossierSubStatus()));
//			model.setDurationCount(
//					Double.valueOf(HtmlUtil.escape(String.valueOf(model.getDurationCount()))));
//			model.setCustomProcessUrl(HtmlUtil.escape(model.getCustomProcessUrl()));
//			model.setStepInstruction(HtmlUtil.escape(model.getStepInstruction()));
//			model.setBriefNote(HtmlUtil.escape(model.getBriefNote()));
//			model.setEditable(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getEditable()))));
//			model.setLockState(HtmlUtil.escape(model.getLockState()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(ProcessStepTempListener.class.getName());
}
