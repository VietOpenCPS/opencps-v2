package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.dossiermgt.model.ProcessAction;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class ProcessActionTempListener extends BaseModelListener<ProcessAction> {

	@Override
	public void onAfterCreate(ProcessAction model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(ProcessAction model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(ProcessAction model) throws ModelListenerException {
//		try {
//			model.setServiceProcessId(
//					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getServiceProcessId()))));
//			model.setPreStepCode(StringEscapeUtils.escapeHtml4(model.getPreStepCode()));
//			model.setPostStepCode(StringEscapeUtils.escapeHtml4(model.getPostStepCode()));
//			model.setAutoEvent(StringEscapeUtils.escapeHtml4(model.getAutoEvent()));
//			model.setPreCondition(StringEscapeUtils.escapeHtml4(model.getPreCondition()));
//			model.setActionCode(StringEscapeUtils.escapeHtml4(model.getActionCode()));
//			model.setActionName(StringEscapeUtils.escapeHtml4(model.getActionName()));
//			model.setAllowAssignUser(
//					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getAllowAssignUser()))));
//			model.setAssignUserId(
//					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getAssignUserId()))));
//			model.setRequestPayment(
//					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getRequestPayment()))));
//			model.setPaymentFee(StringEscapeUtils.escapeHtml4(model.getPaymentFee()));
//			model.setCreateDossierFiles(StringEscapeUtils.escapeHtml4(model.getCreateDossierFiles()));
//			model.setReturnDossierFiles(StringEscapeUtils.escapeHtml4(model.getReturnDossierFiles()));
//			model.setMakeBriefNote(StringEscapeUtils.escapeHtml4(model.getMakeBriefNote()));
//			model.setSyncActionCode(StringEscapeUtils.escapeHtml4(model.getSyncActionCode()));
//			model.setRollbackable(
//					Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getRollbackable()))));
//			model.setCreateDossierNo(
//					Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getCreateDossierNo()))));
//			model.setESignature(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getESignature()))));
//			model.setConfigNote(StringEscapeUtils.escapeHtml4(model.getConfigNote()));
//			model.setDossierTemplateNo(StringEscapeUtils.escapeHtml4(model.getDossierTemplateNo()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(ProcessAction model) throws ModelListenerException {
//		try {
//			model.setServiceProcessId(
//					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getServiceProcessId()))));
//			model.setPreStepCode(StringEscapeUtils.escapeHtml4(model.getPreStepCode()));
//			model.setPostStepCode(StringEscapeUtils.escapeHtml4(model.getPostStepCode()));
//			model.setAutoEvent(StringEscapeUtils.escapeHtml4(model.getAutoEvent()));
//			model.setPreCondition(StringEscapeUtils.escapeHtml4(model.getPreCondition()));
//			model.setActionCode(StringEscapeUtils.escapeHtml4(model.getActionCode()));
//			model.setActionName(StringEscapeUtils.escapeHtml4(model.getActionName()));
//			model.setAllowAssignUser(
//					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getAllowAssignUser()))));
//			model.setAssignUserId(
//					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getAssignUserId()))));
//			model.setRequestPayment(
//					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getRequestPayment()))));
//			model.setPaymentFee(StringEscapeUtils.escapeHtml4(model.getPaymentFee()));
//			model.setCreateDossierFiles(StringEscapeUtils.escapeHtml4(model.getCreateDossierFiles()));
//			model.setReturnDossierFiles(StringEscapeUtils.escapeHtml4(model.getReturnDossierFiles()));
//			model.setMakeBriefNote(StringEscapeUtils.escapeHtml4(model.getMakeBriefNote()));
//			model.setSyncActionCode(StringEscapeUtils.escapeHtml4(model.getSyncActionCode()));
//			model.setRollbackable(
//					Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getRollbackable()))));
//			model.setCreateDossierNo(
//					Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getCreateDossierNo()))));
//			model.setESignature(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getESignature()))));
//			model.setConfigNote(StringEscapeUtils.escapeHtml4(model.getConfigNote()));
//			model.setDossierTemplateNo(StringEscapeUtils.escapeHtml4(model.getDossierTemplateNo()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(ProcessActionTempListener.class.getName());
}
