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
//			model.setGovAgencyCode(HtmlUtil.escape(model.getGovAgencyCode()));
//			model.setGovAgencyName(HtmlUtil.escape(model.getGovAgencyName()));
//			model.setGovAgencyTaxNo(HtmlUtil.escape(model.getGovAgencyTaxNo()));
//			model.setInvoiceTemplateNo(HtmlUtil.escape(model.getInvoiceTemplateNo()));
//			model.setInvoiceIssueNo(HtmlUtil.escape(model.getInvoiceIssueNo()));
//			model.setInvoiceLastNo(HtmlUtil.escape(model.getInvoiceLastNo()));
//			model.setInvoiceForm(HtmlUtil.escape(model.getInvoiceForm()));
//			model.setBankInfo(HtmlUtil.escape(model.getBankInfo()));
//			model.setEpaymentConfig(HtmlUtil.escape(model.getEpaymentConfig()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(PaymentConfig model) throws ModelListenerException {
//		try {
//
//			model.setGovAgencyCode(HtmlUtil.escape(model.getGovAgencyCode()));
//			model.setGovAgencyName(HtmlUtil.escape(model.getGovAgencyName()));
//			model.setGovAgencyTaxNo(HtmlUtil.escape(model.getGovAgencyTaxNo()));
//			model.setInvoiceTemplateNo(HtmlUtil.escape(model.getInvoiceTemplateNo()));
//			model.setInvoiceIssueNo(HtmlUtil.escape(model.getInvoiceIssueNo()));
//			model.setInvoiceLastNo(HtmlUtil.escape(model.getInvoiceLastNo()));
//			model.setInvoiceForm(HtmlUtil.escape(model.getInvoiceForm()));
//			model.setBankInfo(HtmlUtil.escape(model.getBankInfo()));
//			model.setEpaymentConfig(HtmlUtil.escape(model.getEpaymentConfig()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(PaymentConfigTempListener.class.getName());
}
