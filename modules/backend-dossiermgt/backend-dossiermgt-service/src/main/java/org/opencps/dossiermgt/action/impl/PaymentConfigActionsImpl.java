package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.action.PaymentConfigActions;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public class PaymentConfigActionsImpl implements PaymentConfigActions{

	@Override
	public boolean deleteAllPaymentConfig(long groupId, long userId, ServiceContext serviceContext) {
		boolean flag = false;
		List<PaymentConfig> paymentList = PaymentConfigLocalServiceUtil.getPaymentConfigs(-1, -1);
		if (paymentList != null && paymentList.size() > 0) {
			for (PaymentConfig paymentConfig : paymentList) {
				PaymentConfigLocalServiceUtil.deletePaymentConfig(paymentConfig);
				flag = true;
			}
		} else {
			flag = true;
		}
		return flag;
	}

	@Override
	public void updatePaymentConfigDB(long userId, long groupId, String govAgencyCode, String govAgencyName,
			String govAgencyTaxNo, String invoiceTemplateNo, String invoiceIssueNo, String invoiceLastNo,
			String bankInfo, String epaymentConfig, ServiceContext serviceContext) throws PortalException {

		PaymentConfigLocalServiceUtil.updatePaymentConfigDB(userId, groupId, govAgencyCode, govAgencyName, govAgencyTaxNo, invoiceTemplateNo, invoiceIssueNo,
				invoiceLastNo, bankInfo, epaymentConfig, serviceContext);
	}

}
