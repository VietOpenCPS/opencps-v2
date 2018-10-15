package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class PaymentConfigTempListener extends BaseModelListener<PaymentConfig> {

	@Override
	public void onAfterCreate(PaymentConfig model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(PaymentConfig model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(PaymentConfig model) throws ModelListenerException {
//		try {
//
//			model.setGovAgencyCode(StringEscapeUtils.escapeHtml4(model.getGovAgencyCode()));
//			model.setGovAgencyName(StringEscapeUtils.escapeHtml4(model.getGovAgencyName()));
//			model.setGovAgencyTaxNo(StringEscapeUtils.escapeHtml4(model.getGovAgencyTaxNo()));
//			model.setInvoiceTemplateNo(StringEscapeUtils.escapeHtml4(model.getInvoiceTemplateNo()));
//			model.setInvoiceIssueNo(StringEscapeUtils.escapeHtml4(model.getInvoiceIssueNo()));
//			model.setInvoiceLastNo(StringEscapeUtils.escapeHtml4(model.getInvoiceLastNo()));
//			model.setInvoiceForm(StringEscapeUtils.escapeHtml4(model.getInvoiceForm()));
//			model.setBankInfo(StringEscapeUtils.escapeHtml4(model.getBankInfo()));
//			model.setEpaymentConfig(StringEscapeUtils.escapeHtml4(model.getEpaymentConfig()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(PaymentConfig model) throws ModelListenerException {
//		try {
//
//			model.setGovAgencyCode(StringEscapeUtils.escapeHtml4(model.getGovAgencyCode()));
//			model.setGovAgencyName(StringEscapeUtils.escapeHtml4(model.getGovAgencyName()));
//			model.setGovAgencyTaxNo(StringEscapeUtils.escapeHtml4(model.getGovAgencyTaxNo()));
//			model.setInvoiceTemplateNo(StringEscapeUtils.escapeHtml4(model.getInvoiceTemplateNo()));
//			model.setInvoiceIssueNo(StringEscapeUtils.escapeHtml4(model.getInvoiceIssueNo()));
//			model.setInvoiceLastNo(StringEscapeUtils.escapeHtml4(model.getInvoiceLastNo()));
//			model.setInvoiceForm(StringEscapeUtils.escapeHtml4(model.getInvoiceForm()));
//			model.setBankInfo(StringEscapeUtils.escapeHtml4(model.getBankInfo()));
//			model.setEpaymentConfig(StringEscapeUtils.escapeHtml4(model.getEpaymentConfig()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(PaymentConfigTempListener.class.getName());
}
