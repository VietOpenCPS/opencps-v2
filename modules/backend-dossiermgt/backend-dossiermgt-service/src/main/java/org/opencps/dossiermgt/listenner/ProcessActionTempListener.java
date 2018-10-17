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
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getServiceProcessId()))));
//			model.setPreStepCode(HtmlUtil.escape(model.getPreStepCode()));
//			model.setPostStepCode(HtmlUtil.escape(model.getPostStepCode()));
//			model.setAutoEvent(HtmlUtil.escape(model.getAutoEvent()));
//			model.setPreCondition(HtmlUtil.escape(model.getPreCondition()));
//			model.setActionCode(HtmlUtil.escape(model.getActionCode()));
//			model.setActionName(HtmlUtil.escape(model.getActionName()));
//			model.setAllowAssignUser(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getAllowAssignUser()))));
//			model.setAssignUserId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getAssignUserId()))));
//			model.setRequestPayment(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getRequestPayment()))));
//			model.setPaymentFee(HtmlUtil.escape(model.getPaymentFee()));
//			model.setCreateDossierFiles(HtmlUtil.escape(model.getCreateDossierFiles()));
//			model.setReturnDossierFiles(HtmlUtil.escape(model.getReturnDossierFiles()));
//			model.setMakeBriefNote(HtmlUtil.escape(model.getMakeBriefNote()));
//			model.setSyncActionCode(HtmlUtil.escape(model.getSyncActionCode()));
//			model.setRollbackable(
//					Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getRollbackable()))));
//			model.setCreateDossierNo(
//					Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getCreateDossierNo()))));
//			model.setESignature(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getESignature()))));
//			model.setConfigNote(HtmlUtil.escape(model.getConfigNote()));
//			model.setDossierTemplateNo(HtmlUtil.escape(model.getDossierTemplateNo()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(ProcessAction model) throws ModelListenerException {
//		try {
//			model.setServiceProcessId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getServiceProcessId()))));
//			model.setPreStepCode(HtmlUtil.escape(model.getPreStepCode()));
//			model.setPostStepCode(HtmlUtil.escape(model.getPostStepCode()));
//			model.setAutoEvent(HtmlUtil.escape(model.getAutoEvent()));
//			model.setPreCondition(HtmlUtil.escape(model.getPreCondition()));
//			model.setActionCode(HtmlUtil.escape(model.getActionCode()));
//			model.setActionName(HtmlUtil.escape(model.getActionName()));
//			model.setAllowAssignUser(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getAllowAssignUser()))));
//			model.setAssignUserId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getAssignUserId()))));
//			model.setRequestPayment(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getRequestPayment()))));
//			model.setPaymentFee(HtmlUtil.escape(model.getPaymentFee()));
//			model.setCreateDossierFiles(HtmlUtil.escape(model.getCreateDossierFiles()));
//			model.setReturnDossierFiles(HtmlUtil.escape(model.getReturnDossierFiles()));
//			model.setMakeBriefNote(HtmlUtil.escape(model.getMakeBriefNote()));
//			model.setSyncActionCode(HtmlUtil.escape(model.getSyncActionCode()));
//			model.setRollbackable(
//					Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getRollbackable()))));
//			model.setCreateDossierNo(
//					Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getCreateDossierNo()))));
//			model.setESignature(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getESignature()))));
//			model.setConfigNote(HtmlUtil.escape(model.getConfigNote()));
//			model.setDossierTemplateNo(HtmlUtil.escape(model.getDossierTemplateNo()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(ProcessActionTempListener.class.getName());
}
