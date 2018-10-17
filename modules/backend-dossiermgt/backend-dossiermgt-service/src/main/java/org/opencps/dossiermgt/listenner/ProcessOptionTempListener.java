package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.dossiermgt.model.ProcessOption;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class ProcessOptionTempListener extends BaseModelListener<ProcessOption> {

	@Override
	public void onAfterCreate(ProcessOption model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(ProcessOption model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(ProcessOption model) throws ModelListenerException {
//		try {
//
//			model.setOptionOrder(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getOptionOrder()))));
//			model.setAutoSelect(HtmlUtil.escape(model.getAutoSelect()));
//			model.setInstructionNote(HtmlUtil.escape(model.getInstructionNote()));
//			model.setSubmissionNote(HtmlUtil.escape(model.getSubmissionNote()));
//			model.setDossierTemplateId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getDossierTemplateId()))));
//			model.setServiceProcessId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getServiceProcessId()))));
//			model.setOptionName(HtmlUtil.escape(model.getOptionName()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(ProcessOption model) throws ModelListenerException {
//		try {
//
//			model.setOptionOrder(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getOptionOrder()))));
//			model.setAutoSelect(HtmlUtil.escape(model.getAutoSelect()));
//			model.setInstructionNote(HtmlUtil.escape(model.getInstructionNote()));
//			model.setSubmissionNote(HtmlUtil.escape(model.getSubmissionNote()));
//			model.setDossierTemplateId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getDossierTemplateId()))));
//			model.setServiceProcessId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getServiceProcessId()))));
//			model.setOptionName(HtmlUtil.escape(model.getOptionName()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(DossierTemplateTempListener.class.getName());
}
