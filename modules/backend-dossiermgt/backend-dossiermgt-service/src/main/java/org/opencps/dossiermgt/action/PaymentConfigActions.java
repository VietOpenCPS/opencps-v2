package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public interface PaymentConfigActions {

	public boolean deleteAllPaymentConfig(long groupId, long userId, ServiceContext serviceContext);

	public void updatePaymentConfigDB(long userId, long groupId, String govAgencyCode, String govAgencyName,
			String govAgencyTaxNo, String invoiceTemplateNo, String invoiceIssueNo, String invoiceLastNo,
			String bankInfo, String epaymentConfig, ServiceContext serviceContext) throws PortalException;

}
