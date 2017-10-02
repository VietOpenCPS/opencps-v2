package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.paymentconfig.model.PaymentConfigDataModel;
import org.opencps.api.paymentconfig.model.PaymentConfigInputModel;
import org.opencps.dossiermgt.constants.PaymentConfigTerm;
import org.opencps.dossiermgt.model.PaymentConfig;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;

public class PaymentConfigUtils {

	public static List<PaymentConfigDataModel> mappingDataModel(List<Document> documents) {

		List<PaymentConfigDataModel> outputs = new ArrayList<PaymentConfigDataModel>();

		for (Document doc : documents) {
			PaymentConfigDataModel model = new PaymentConfigDataModel();

			model.setPaymentConfigId(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));

			model.setGovAgencyCode(doc.get(PaymentConfigTerm.GOV_AGENCY_CODE));
			model.setGovAgencyName(doc.get(PaymentConfigTerm.GOV_AGENCY_NAME));
			model.setGovAgencyTaxNo(doc.get(PaymentConfigTerm.GOV_AGENCY_TAX_NO));
			model.setInvoiceIssueNo(doc.get(PaymentConfigTerm.INVOICE_ISSUE_NO));
			model.setInvoiceLastNo(doc.get(PaymentConfigTerm.INVOICE_LAST_NO));
			model.setInvoiceTemplateNo(doc.get(PaymentConfigTerm.INVOICE_TEMPLATE_NO));
			model.setBankInfo(doc.get(PaymentConfigTerm.BANK_INFO));

			outputs.add(model);
		}

		return outputs;
	}
	
	public static PaymentConfigInputModel mappingToModel(PaymentConfig paymentConfig) {
		PaymentConfigInputModel model = new PaymentConfigInputModel();
		
		model.setPaymentConfigId(paymentConfig.getPrimaryKey());

		model.setGovAgencyCode(paymentConfig.getGovAgencyCode());
		model.setGovAgencyName(paymentConfig.getGovAgencyName());
		model.setGovAgencyTaxNo(paymentConfig.getGovAgencyTaxNo());
		model.setInvoiceIssueNo(paymentConfig.getInvoiceIssueNo());
		model.setInvoiceLastNo(paymentConfig.getInvoiceLastNo());
		model.setInvoiceTemplateNo(paymentConfig.getInvoiceTemplateNo());
		model.setBankInfo(paymentConfig.getBankInfo());
		
		return model;
	}
}
